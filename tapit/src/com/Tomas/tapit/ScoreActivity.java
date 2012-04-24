package com.Tomas.tapit;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

public class ScoreActivity extends Activity{
	
	int position;
	String[] score;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		setContentView(R.layout.newscore);
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onStart() {
		Log.d("SCOREACTIVITY", "onStart: ");
		
		SharedPreferences highScore = getSharedPreferences("tap it", 0);		
		score = highScore.getString("highscore", "-|-|-|-|-|0|0|0|0|0").split("\\|");		
		position = getIntent().getExtras().getInt("position");
		Log.d("SCOREACTIVITY", " " + position);	
		EditText input = (EditText) findViewById(R.id.scoreinput);
		
		
		if(position==1){
			input.setLayoutParams(findViewById(R.id.hname1).getLayoutParams());
			findViewById(R.id.hname1).setVisibility(View.INVISIBLE);
			
			score[4]=score[3];
			score[3]=score[2];
			score[2]=score[1];
			score[1]=score[0];
			score[9]=score[8];
			score[8]=score[7];
			score[7]=score[6];
			score[6]=score[5];
		}
		
		if(position==2){
			input.setLayoutParams(findViewById(R.id.hname2).getLayoutParams());
			findViewById(R.id.hname2).setVisibility(View.INVISIBLE);
			score[4]=score[3];
			score[3]=score[2];
			score[2]=score[1];
			score[9]=score[8];
			score[8]=score[7];
			score[7]=score[6];


		}
		
		if(position==3){
			input.setLayoutParams(findViewById(R.id.hname3).getLayoutParams());
			findViewById(R.id.hname3).setVisibility(View.INVISIBLE);
			score[4]=score[3];
			score[3]=score[2];
			score[9]=score[8];
			score[8]=score[7];

		}
		
		if(position==4){
			input.setLayoutParams(findViewById(R.id.hname4).getLayoutParams());
			findViewById(R.id.hname4).setVisibility(View.INVISIBLE);
			score[4]=score[3];
			score[9]=score[8];
		}
		
		if(position==5){
			input.setLayoutParams(findViewById(R.id.hname5).getLayoutParams());
			findViewById(R.id.hname5).setVisibility(View.INVISIBLE);
		}
		
		score[position+4] = getIntent().getExtras().getInt("score")+"";
		((TextView) findViewById(R.id.hname1)).setText(score[0]);
		((TextView) findViewById(R.id.hname2)).setText(score[1]);
		((TextView) findViewById(R.id.hname3)).setText(score[2]);
		((TextView) findViewById(R.id.hname4)).setText(score[3]);
		((TextView) findViewById(R.id.hname5)).setText(score[4]);
		((TextView) findViewById(R.id.hscore1)).setText(score[5]);
		((TextView) findViewById(R.id.hscore2)).setText(score[6]);
		((TextView) findViewById(R.id.hscore3)).setText(score[7]);
		((TextView) findViewById(R.id.hscore4)).setText(score[8]);
		((TextView) findViewById(R.id.hscore5)).setText(score[9]);
		
		
		
		
		super.onStart();
	}


	
	String encodedScore(){
		String highScoreString = "";
		for(int i = 0; i <=8; i++){
			highScoreString += score[i] + "|";
			Log.d("SCOREACTIVITY", " " + highScoreString);	
		}
		highScoreString += score[9];
		return highScoreString;
	}
	
	public void save(View v){
		score[position-1]= ((EditText) findViewById(R.id.scoreinput)).getText().toString();
		
		SharedPreferences highScore = getSharedPreferences("tap it", 0);
		
	      SharedPreferences.Editor editor = highScore.edit();
	      editor.putString("highscore", encodedScore());

	      // Commit the edits!
	      editor.commit();
	      finish();
	}
	
	


}
