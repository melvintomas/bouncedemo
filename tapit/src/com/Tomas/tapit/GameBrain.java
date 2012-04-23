package com.Tomas.tapit;

import java.util.Random;

import android.util.Log;


public class GameBrain {
	int command;
	int score = 0;
	int difficulty;
	int pointValue;
	Random random = new Random();
	String difficultyText;
	
	
	//PURPLE 0
	//PINK 1
	//GREEN 2
	//BLUE 3
	

	
	
	
	
	
	
	public GameBrain(int difficulty) {
		this.difficulty = difficulty;
		if (difficulty == 3000){
			difficultyText = "EASY";
			pointValue = 45;
		}
		if (difficulty == 2000){
			difficultyText = "MEDIUM";
			pointValue = 48;
		}
		if (difficulty == 1500){
			difficultyText = "HARD";
			pointValue = 50;
		}
		
		
		
	}
	
	public GameBrain(){
		System.exit(0);
	}

	boolean isCorrect(int input){
		
		if (input == command){
			Log.d("GAMEBRAIN", "Pre Score: "+getScore());
			score += pointValue;
			Log.d("GAMEBRAIN", "Post Score: "+getScore());
			return true;
		}
		else return false;
	}
	
	int getCommand(){
		command = random.nextInt(4);
		return command;
	}
	
	int getScore(){
		return score;
	}
	
	String getStringCommand(){
		getCommand();
		if (command == 0){
			return "TAP IT: PURPLE!";
		} else if (command == 1){
			return "TAP IT: PINK!";
		} else if (command == 2){
			return "TAP IT: GREEN!";
		} else if (command == 3){
			return "TAP IT: BLUE!";
		} else return "error";
	}
	
	int getDifficulty(){
		return difficulty;
	}
	
	String getDifficultyText(){
		return difficultyText;
	}
	
	void restart(){
		score = 0;
	}
	

	

}


