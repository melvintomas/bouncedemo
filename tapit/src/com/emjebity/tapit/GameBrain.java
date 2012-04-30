package com.emjebity.tapit;

import java.util.Random;

import android.media.SoundPool;
import android.util.Log;

public class GameBrain {
	int command;
	int score = 0;
	int difficulty;
	int pointValue;
	Random random = new Random();
	String difficultyText;
	SoundPool soundPool;

	// PURPLE 1
	// PINK 2
	// GREEN 3
	// BLUE 4

	public GameBrain(int difficulty) {
		this.difficulty = difficulty;
		if (difficulty == 3000) {
			difficultyText = "EASY";
			pointValue = 45;
		}
		if (difficulty == 2000) {
			difficultyText = "MEDIUM";
			pointValue = 48;
		}
		if (difficulty == 1500) {
			difficultyText = "HARD";
			pointValue = 50;
		}
		if (difficulty == 0) {
			difficultyText = "PRACTICE";
			pointValue = 0;
		}

	}

	public GameBrain() {
		System.exit(0);
	}

	boolean isCorrect(int input) {
		if (input == command) {
			return true;
		} else
			return false;
	}

	void earnPoints() {
		Log.d("GAMEBRAIN", "Pre Score: " + getScore());
		score += pointValue;
		Log.d("GAMEBRAIN", "Post Score: " + getScore());
	}

	void getNewCommand() {
		command = random.nextInt(4) + 1;
		int randomNum = random.nextInt(99);
		if (randomNum > 40) {
			command += 4;
		}
		if (randomNum > 70) {
			command = 9;
		}
	}

	int getCommand() {
		return command;
	}

	boolean isDouble() {
		if (command >= 5 && command <= 8)
			return true;
		else
			return false;
	}

	void setSingle() {
		command -= 4;
	}

	int getScore() {
		return score;
	}

	String getStringCommand() {
		getNewCommand();
		if (command == 1) {
			return "TAP IT: PURPLE!";
		} else if (command == 2) {
			return "TAP IT: PINK!";
		} else if (command == 3) {
			return "TAP IT: GREEN!";
		} else if (command == 4) {
			return "TAP IT: BLUE!";
		} else if (command == 5) {
			return "DOUBLE TAP: PURPLE!";
		} else if (command == 6) {
			return "DOUBLE TAP: PINK!";
		} else if (command == 7) {
			return "DOUBLE TAP: GREEN!";
		} else if (command == 8) {
			return "DOUBLE TAP: BLUE!";
		} else if (command == 9) {
			return "SHAKE IT!";
		}
		return "error";
	}

	int getDifficulty() {
		return difficulty;
	}

	String getDifficultyText() {
		return difficultyText;
	}

	void restart() {
		score = 0;
	}

}
