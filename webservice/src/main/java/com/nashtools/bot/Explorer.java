package com.nashtools.bot;


import java.util.ArrayList;

import com.nashtools.bot.framework.BettingNode;
import com.nashtools.bot.framework.CapGame;
import com.nashtools.bot.framework.FullActionAbstractionFive;
import com.nashtools.bot.framework.FullActionAbstractionFour;
import com.nashtools.bot.framework.FullActionAbstractionThree;
import com.nashtools.bot.framework.HUSNGGame;
import com.nashtools.bot.framework.State;



public class Explorer {
	
	/* Old Abstract State so soft translations remain constant for one hand */
	public State oldState = new State();
	public BettingNode oldNode;
	
	public String action = "";
	public String actions = "";
	public String scraperactions = "";
	
	public static OpenPureCFRBot flBot = null;
			
			//new OpenPureCFRBot("F:\\CleverPiggy\\KE_KO_1286792_60000_180000.flhu.iter-50747m.secs-36000.regrets", 
			//new FLGame(), new OpenPureCFRCardAbstraction(), new NullActionAbstraction());
	
	public static OpenPureCFRBot capBot = new OpenPureCFRBot("G:\\cap1009.dat", 
			new CapGame(), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree());
	
	/* SNG */
	private double effectiveStack = -1;

	public static OpenPureCFRBot sng1_5Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy1.5.dat", 
			new HUSNGGame(15), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng2Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy2.dat", 
			new HUSNGGame(20), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng2_5Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy2.5.dat", 
			new HUSNGGame(25), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng3Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy3.dat", 
			new HUSNGGame(30), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng3_5Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy3.5.dat", 
			new HUSNGGame(35), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng4Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy4.dat", 
			new HUSNGGame(40), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng4_5Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy4.5.dat", 
			new HUSNGGame(45), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng5Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy5.dat", 
			new HUSNGGame(50), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng5_5Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy5.5.dat", 
			new HUSNGGame(55), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng6Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy6.dat", 
			new HUSNGGame(60), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng6_5Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy6.5.dat", 
			new HUSNGGame(65), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng7Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy7.dat", 
			new HUSNGGame(70), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng7_5Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy7.5.dat", 
			new HUSNGGame(75), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng8Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy8.dat", 
			new HUSNGGame(80), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng8_5Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy8.5.dat", 
			new HUSNGGame(85), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng9Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy9.dat", 
			new HUSNGGame(90), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng9_5Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy9.5_2.dat", 
			new HUSNGGame(95), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng10Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy10.dat", 
			new HUSNGGame(100), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng10_5Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy10.5.dat", 
			new HUSNGGame(105), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng11Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy11.dat", 
			new HUSNGGame(110), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng11_5Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy11.5.dat", 
			new HUSNGGame(115), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng12Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy12_2.dat", 
			new HUSNGGame(120), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng13Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy13_2.dat", 
			new HUSNGGame(130), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng14Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy14.dat", 
			new HUSNGGame(140), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng15Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy15_2.dat", 
			new HUSNGGame(150), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng16Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy16_2.dat", 
			new HUSNGGame(160), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng17Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy17_2.dat", 
			new HUSNGGame(170), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng18Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy18_2.dat", 
			new HUSNGGame(180), new OpenPureCFRCardAbstractionCap(), new FullActionAbstractionThree(), new Postprocessor());
	public static OpenPureCFRBot sng19Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy19.dat", 
			new HUSNGGame(190), new OpenPureCFRCardAbstractionCP(), new FullActionAbstractionFour(), new Postprocessor());
	public static OpenPureCFRBot sng21Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy21.dat", 
			new HUSNGGame(210), new OpenPureCFRCardAbstractionCP(), new FullActionAbstractionFive(), new Postprocessor());
	public static OpenPureCFRBot sng22Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy22.dat", 
			new HUSNGGame(220), new OpenPureCFRCardAbstractionCP(), new FullActionAbstractionFive(), new Postprocessor());
	public static OpenPureCFRBot sng23Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy23.dat", 
			new HUSNGGame(230), new OpenPureCFRCardAbstractionCP(), new FullActionAbstractionFive(), new Postprocessor());
	public static OpenPureCFRBot sng24Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy24.dat", 
			new HUSNGGame(240), new OpenPureCFRCardAbstractionCP(), new FullActionAbstractionFive(), new Postprocessor());
	public static OpenPureCFRBot sng25Bot = new OpenPureCFRBot("G:\\HUSNG\\strategy25.dat", 
			new HUSNGGame(250), new OpenPureCFRCardAbstractionCP(), new FullActionAbstractionFive(), new Postprocessor());
	
	//public static OpenPureCFRBot nlCompetitionBot = new OpenPureCFRBot("H:\\NLCompetition\\CompetitionBot.nlhu.iter-85886m.secs-844797.regrets", 
	//		new HUSNGGame(2000), new OpenPureCFRCardAbstractionNLCompetition(), new ActionAbstractionNLCompetition());
	
	private static double roundToHalf(double d){
		return Math.round(d * 2)/2.0;
	}
	
	
	public static int huHeroIndex = 1;
	public static int huVillainIndex = 0;
	private static int shHeroIndex = 2;
	
	private boolean remove = false;
	
	public boolean toRemove(){
		return remove;
	}
	
	public void remove(){
		remove = true;
	}
	
	
	public void keep(){
		remove = false;
	}
	
	public Explorer(){

	}
	
	private String title;
	
	
	private void computeSNG(ArrayList<String> strings){
		System.out.println("SNG");
		float stack0 = tableScraper.getStacks()[0];
		float stack1 = tableScraper.getStacks()[1];
		float stack0BB = stack0 / tableScraper.getBigBlind();
		float stack1BB = stack1 / tableScraper.getBigBlind();
		System.out.println("stack0: " + stack0BB);
		System.out.println("stack1: " + stack1BB);
		ArrayList<ArrayList<DetailedAction>> actions = tableScraper.getParser().getActions();
		if(tableScraper.getParser().getActions().get(0).isEmpty()){
			if(tableScraper.getButtonPosition() == 0){
				double stack0BeforPosting = stack0 / tableScraper.getBigBlind() + 0.5;
				double stack1BeforPosting = stack1 / tableScraper.getBigBlind() + 1;	
				effectiveStack = Math.min(stack0BeforPosting, stack1BeforPosting);
				System.out.println("effective Stack: " + effectiveStack);
			}
			else if(tableScraper.getButtonPosition() == 1){
				double stack0BeforPosting = stack0 / tableScraper.getBigBlind() + 1;
				double stack1BeforPosting = stack1 / tableScraper.getBigBlind() + 0.5;
				effectiveStack = Math.min(stack0BeforPosting, stack1BeforPosting);
				System.out.println("effective Stack: " + effectiveStack);
			}
		}
		else if(tableScraper.getParser().getActions().get(0).size() == 1){
			if(tableScraper.getButtonPosition() == 0){
				double stack0BeforPosting = stack0 / tableScraper.getBigBlind() + 0.5 + tableScraper.getBets()[0] / tableScraper.getBigBlind();
				double stack1BeforPosting = stack1 / tableScraper.getBigBlind() + 1;
				effectiveStack = Math.min(stack0BeforPosting, stack1BeforPosting);
				System.out.println("effective Stack: " + effectiveStack);
			}
			else if(tableScraper.getButtonPosition() == 1){
				double stack0BeforPosting = stack0 / tableScraper.getBigBlind() + 1;
				double stack1BeforPosting = stack1 / tableScraper.getBigBlind() + 0.5 + tableScraper.getBets()[1] / tableScraper.getBigBlind();
				effectiveStack = Math.min(stack0BeforPosting, stack1BeforPosting);
				System.out.println("effective Stack: " + effectiveStack);
			}
		}
		double roundedEffectiveStack = -1;
		if(effectiveStack < 12)
			roundedEffectiveStack = roundToHalf(effectiveStack);
		else
		roundedEffectiveStack = Math.round(effectiveStack);
		DetailedAction a = null;
		if(roundedEffectiveStack <= 1){
			strings.add("All IN");
			strings.add("All IN");
			strings.add("All IN");
		}
		PlayerModuleInformations infos = new PlayerModuleInformations();
		infos.oldNode = oldNode;
		infos.oldState = oldState;
		if(roundedEffectiveStack == 1.5){
			a = sng1_5Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 2){
			a = sng2Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 2.5){
			a = sng2_5Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 3){
			a = sng3Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 3.5){
			a = sng3_5Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 4){
			a = sng4Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 4.5){
			a = sng4_5Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 5){
			a = sng5Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 5.5){
			a = sng5_5Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 6){
			a = sng6Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 6.5){
			a = sng6_5Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 7){
			a = sng7Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 7.5){
			a = sng7_5Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 8){
			a = sng8Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 8.5){
			a = sng8_5Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 9){
			a = sng9Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 9.5){
			a = sng9_5Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 10){
			a = sng10Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 10.5){
			a = sng10_5Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 11){
			a = sng11Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 11.5){
			a = sng11_5Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 12){
			a = sng12Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 13){
			a = sng13Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 14){
			a = sng14Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 15){
			a = sng15Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 16){
			a = sng16Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 17){
			a = sng17Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 18){
			a = sng18Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 19){
			a = sng19Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 20){
			a = capBot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 21){
			a = sng21Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 22){
			a = sng22Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 23){
			a = sng23Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(roundedEffectiveStack == 24){
			a = sng24Bot.handleGameStateChange(tableScraper, strings, infos);
		}		
		if(roundedEffectiveStack == 25){
			a = sng25Bot.handleGameStateChange(tableScraper, strings, infos);
		}
		if(a == null){
			strings.add("INVALID");
			strings.add("INVALID");
			strings.add("INVALID");
			return;
		}
		oldNode = infos.oldNode;
		strings.set(strings.size() - 1, strings.get(strings.size() - 1) + "   " + roundedEffectiveStack);
		if(a.getAction().equals(Actions.FOLD))
			strings.add("GTO Fold");
		else if(a.getAction().equals(Actions.RAISE)){
			if(infos.heroAllIn)
				strings.add("ALL IN");
			else
				strings.add("GTO Raise: " + a.getAmount() * tableScraper.getBigBlind());
		}
		else if(a.getAction().equals(Actions.CALL)){
			if(infos.hsAllIn)
				strings.add("HS2 ALL IN");
			else if(infos.hsCall)
				strings.add("HS2 Call");
			else
				strings.add("GTO Call");
		}
		else
			strings.add("INVALID");
	}
	
}
