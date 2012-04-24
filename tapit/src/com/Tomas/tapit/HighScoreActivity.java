package com.Tomas.tapit;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HighScoreActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscore);
		
	}
	
	
	
	
	@Override
	protected void onStart() {
		SharedPreferences highScore = getSharedPreferences("tap it", 0);
		String[] scores = highScore.getString("highscore", "-|-|-|-|-|0|0|0|0|0").split("\\|");
		
		Log.d("HIGHSCOREACTIVITY", scores[0] + " " + scores.length);
		((TextView) findViewById(R.id.name1)).setText(scores[0]);
		((TextView) findViewById(R.id.name2)).setText(scores[1]);
		((TextView) findViewById(R.id.name3)).setText(scores[2]);
		((TextView) findViewById(R.id.name4)).setText(scores[3]);
		((TextView) findViewById(R.id.name5)).setText(scores[4]);
		((TextView) findViewById(R.id.score1)).setText(scores[5]);
		((TextView) findViewById(R.id.score2)).setText(scores[6]);
		((TextView) findViewById(R.id.score3)).setText(scores[7]);
		((TextView) findViewById(R.id.score4)).setText(scores[8]);
		((TextView) findViewById(R.id.score5)).setText(scores[9]);
		super.onStart();
	}



}
