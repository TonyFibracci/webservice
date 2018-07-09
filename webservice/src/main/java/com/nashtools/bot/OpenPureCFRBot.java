package com.nashtools.bot;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.nashtools.bot.framework.Action;
import com.nashtools.bot.framework.ActionAbstraction;
import com.nashtools.bot.framework.ActionType;
import com.nashtools.bot.framework.BettingNode;
import com.nashtools.bot.framework.CardAbstraction;
import com.nashtools.bot.framework.Constants;
import com.nashtools.bot.framework.Game;
import com.nashtools.bot.framework.Player_Module;



public class OpenPureCFRBot{
	private static String playerName = "Suiteng88";
	private static float flopHSThreshold = 0.75f;
	private static float turnHSThreshold = 0.75f;
	private static float riverHSThreshold = 0.85f;
	
	public Player_Module module;
	BettingNode bettingNode;
		
	/**
	 * Creates the bot and loads the strategy file 
	 */
	public OpenPureCFRBot(String strategyPath, Game game, CardAbstraction cardAbstraction, ActionAbstraction actionAbstraction){		
		module  = new Player_Module(strategyPath, game, cardAbstraction, actionAbstraction);
		bettingNode = module.ag.betting_tree_root;
	}
	
	/**
	 * Creates the bot with postprocessing and loads the strategy file 
	 */
	public OpenPureCFRBot(String strategyPath, Game game, CardAbstraction cardAbstraction, ActionAbstraction actionAbstraction, Postprocessor postprocessor){		
		module  = new Player_Module(strategyPath, game, cardAbstraction, actionAbstraction, postprocessor);
		bettingNode = module.ag.betting_tree_root;
	}
	
	public static Action convertAction(DetailedAction action, double spent){
		Action newAction = new Action();
		if(action.getAction().ordinal() == 2){
			newAction.type = ActionType.RAISE;
			newAction.size = (int)(action.getAmount() * 10 + spent);
			System.out.println("Newaction: " + newAction.size);
		}
		else if(action.getAction().ordinal() == 1)
			newAction.type = ActionType.CALL;
		else if(action.getAction().ordinal() == 0)
			newAction.type = ActionType.FOLD;					
		return newAction;
	}
	
	public DetailedAction handleGameStateChange(TableScraper scraper, ArrayList<String> strings, PlayerModuleInformations infos) {		
			
		/* Create new State */
		State state = new State();
		Game.initState(module.ag.game, 0, state);
		
		ArrayList<ArrayList<DetailedAction>> actions = scraper.getParser().getActions();
		if(actions.size() != scraper.getStreet().ordinal() + 1){
			DetailedAction action = new DetailedAction(Actions.INVALID, null, 0);
			return action;
		}
		
		if(scraper.getStreet().ordinal() == 0 && 
				(actions.get(scraper.getStreet().ordinal()).size() == 0 || actions.get(scraper.getStreet().ordinal()).size() == 1)){
			Game.initState(module.ag.game, 0, infos.oldState);
		}
		
		/* Iterate over actions to find duplicates and erase them */
		for(int i = 0; i <= scraper.getStreet().ordinal(); i++){
			for(int j = 0; j < actions.get(i).size(); j++){
				/* Check for duplicate actions because of laggs and erase one of them*/
				if(j > 0){
					if(actions.get(i).get(j).getPosition().equals(actions.get(i).get(j - 1).getPosition())){
						actions.get(i).remove(actions.get(i).get(j - 1));
					}
				}				
			}
		}
		
		/* Keep track of how much each player spent before the current street, for easier calculation of the betsize on needed on current Street */
		int spentBeforeCurrentStreet = 0;
		
		/* Walk the tree by updating the state for each action taken by one of the players */
		for(int i = 0; i <= scraper.getStreet().ordinal(); i++){
			for(int j = 0; j < actions.get(i).size(); j++){
				
				int player = Game.currentPlayer(module.ag.game, state);
				int spent = state.spent[player];

				if(i == 0 && (j == 0 || j == 1))
					spent = 0;
				Game.doAction(module.ag.game, convertAction(actions.get(i).get(j), spent), state);
				if(i < scraper.getStreet().ordinal())
					spentBeforeCurrentStreet = state.maxSpent;
			}
		}
		
		Action[][] stateActions = state.action;
		String stateActionString = "";
		for(int i = 0; i < stateActions.length; i++){
			for(int j = 0; j < stateActions[i].length; j++){
				if(stateActions[i][j] != null){
					stateActionString += stateActions[i][j].toString();
//					if(stateActions[i][j].type == ActionType.RAISE)
//						stateActionString += ((1.0 * stateActions[i][j].size) / module.ag.game.blind[0]);
				}
			}
			if(i != stateActions.length-1)
				stateActionString += "/";
		}
		
		ArrayList<ArrayList<DetailedAction>> scraperActions = scraper.getParser().getActions();
		String scraperActionString = "";
		for(int i = 0; i < scraperActions.size(); i++){
			for(int j = 0; j < scraperActions.get(i).size(); j++){
				if(scraperActions.get(i).get(j) != null){
					scraperActionString += scraperActions.get(i).get(j).getAction().toString();
//					if(scraperActions.get(i).get(j).getAction().equals(Actions.RAISE))
//						scraperActionString += scraperActions.get(i).get(j).getAmount();
				}
			}
			if(i != scraperActions.size() - 1){
				scraperActionString += "/";
			}
		}	
		strings.add(scraperActionString);
		strings.add(stateActionString);
		int[] board = new int[5];
		if(scraper.getBoardCards() != null)
		for(int i = 0; i < scraper.getBoardCards().size(); i++){
			board[i] = scraper.getBoardCards().get(i).ordinal();
		}
		state.boardCards = board;
		state.round = scraper.getStreet().ordinal();
		PositionName[] names = scraper.getParser().getPositionNames();
		int index = -1;
		for(int i = 0; i < 2; i++){
			if(names[i].getName().equals(playerName)){
				if(names[i].getPosition() == Positions.BU)
					index = 1;
				else if(names[i].getPosition() == Positions.BB)
					index = 0;
				else
					throw new RuntimeException();
			}
		}
		
		int[] holeCards = new int[2];
		holeCards[0] = scraper.getHeroCards()[0].ordinal();
		holeCards[1] = scraper.getHeroCards()[1].ordinal();
		if(holeCards[0] == 0 && holeCards[1] == 0)
			throw new RuntimeException();
		
		if(state.round == 1){
			float hs = HS2Tables.getFlopHS2(board, holeCards);
			System.out.println("Flop HS2: " + hs);	
		}

		state.holeCards[index] = holeCards;
		Action a = null;
		try{
			Action[] abstract_actions = new Action[Constants.MAX_ABSTRACT_ACTIONS];
			int num_actions = module.ag.action_abs.get_actions(module.ag.game, state, abstract_actions);
			double[] actionProbs = new double[Constants.MAX_ABSTRACT_ACTIONS];
			
			a = module.get_action(state, actionProbs, infos);
			
			if(infos.strings.size() > 0)
				strings.set(strings.size() - 1, strings.get(strings.size() - 1).concat("   ").concat(infos.strings.get(0)));
			String actionProbsString = "";
			DecimalFormat formatter = new DecimalFormat("###.##");
			for(int aP = 0; aP < num_actions; aP++){
				double action = actionProbs[aP];
				actionProbsString += formatter.format(action);
				actionProbsString += "  ";
			}
			strings.add(actionProbsString);
		}catch(Exception e){
			e.printStackTrace();
			DetailedAction action = new DetailedAction(Actions.INVALID, null, 0);
			return action;		
		}
		System.out.println(a.type);		
		System.out.println("Spent: " + spentBeforeCurrentStreet);

		
		if(a.type == ActionType.FOLD)
			return new DetailedAction(Actions.FOLD, null, 0);
		else if(a.type == ActionType.RAISE){
			System.out.println("Original Size: " + a.size);
			/* Check for All In */
			if(a.size == module.ag.game.stack[0]){
				infos.heroAllIn = true;
			}			
			return new DetailedAction(Actions.RAISE, null, (a.size - spentBeforeCurrentStreet) * (1.0f / module.ag.game.blind[0]));
		}
		else {
			if(infos.wasAllIn){
				if(scraper.getStacks()[Explorer.huVillainIndex] > 0 && state.maxSpent < module.ag.game.stack[0]){
					System.out.println("ALL IN MAPPING");					
					float hs = 0;
					if(state.round == 0){
						infos.hsAllIn = true;
					}
					if(state.round == 1){
						hs = HS2Tables.getFlopHS2(board, holeCards);
						System.out.println("Flop HS2: " + hs);	
						if(hs > flopHSThreshold){
							infos.hsAllIn = true;
						}
						else {
							infos.hsCall = true;
						}
					}
					if(state.round == 2){
						hs = HS2Tables.getTurnHS2(board, holeCards);
						System.out.println("Turn HS2: " + hs);	
						if(hs > turnHSThreshold){
							infos.hsAllIn = true;
						}
						else {
							infos.hsCall = true;
						}
					}
					if(state.round == 3){
						hs = HS2Tables.getRiverHS(board, holeCards);
						System.out.println("River HS: " + hs);		
						if(hs > riverHSThreshold){
							infos.hsAllIn = true;
						}
						else {
							infos.hsCall = true;
						}
					}
				}
			}
			return new DetailedAction(Actions.CALL, null, 0);	
		}
	}
}
