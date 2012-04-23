package com.Tomas.tapit;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class GameActivity extends Activity{
	


	GameBrain brain;
	TextView score;
	TextView command;
	TextView difficultyText;
	Boolean isPlaying;
	int commandInput;
	int timeLeft;
	Boolean isPaused;
	Vibrator vibe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		
		
		super.onCreate(savedInstanceState);
		
		Log.d("GAMEACTIVITY", "OnCreate: ");
		setContentView(R.layout.game);
		Drawable d = findViewById(R.id.pink).getBackground();  
        PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.rgb(254, 0, 152), PorterDuff.Mode.SRC_ATOP);  
        d.setColorFilter(filter); 
		
		d = findViewById(R.id.purple).getBackground();  
        filter = new PorterDuffColorFilter(Color.rgb(102, 0, 102), PorterDuff.Mode.SRC_ATOP);  
        d.setColorFilter(filter);  
        
        d = findViewById(R.id.green).getBackground();  
        filter = new PorterDuffColorFilter(Color.rgb(1, 204, 52), PorterDuff.Mode.SRC_ATOP);  
        d.setColorFilter(filter);
        
        d = findViewById(R.id.blue).getBackground();  
        filter = new PorterDuffColorFilter(Color.rgb(0, 102, 102), PorterDuff.Mode.SRC_ATOP);  
        d.setColorFilter(filter);  
        
        
        
        
        getDifficultyText().setText(getBrain().getDifficultyText());
        
		prep();
		new Timer().execute();
		isPlaying = true;
		
		
		Log.d("GAMEOVERACTIVITY", "onCreate: " + getBrain().difficulty);
		vibe = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

	}
	
	public class Timer extends AsyncTask<String, Integer, String>{
		

		


		@Override
		protected String doInBackground(String... params) {

			while(isPlaying && timeLeft>0){
				
				try {
					Thread.sleep(100);
					timeLeft -= 100;
					Log.d("GAMEACTIVITY", "TimeLeft: " + timeLeft);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();					
				}                
				if(isPaused){
					break;
				}
			}
			if (isPlaying && !isPaused)
				gameOver("TOO SLOW!");
			return null;
		}	
	}
	
	
	

	
	
	@Override
	protected void onPause() {
		isPaused = true;
		super.onPause();
	}

	@Override
	protected void onResume() {
		isPaused = false;
		timeLeft = getBrain().getDifficulty();
		new Timer().execute();
		super.onResume();
	}

	TextView getScore(){
		if (this.score == null)
			this.score = (TextView) findViewById(R.id.score);
		return this.score;
	}
	
	TextView getCommand(){
		if (this.command == null)
			this.command = (TextView) findViewById(R.id.command);
		return this.command;
	}
	
	TextView getDifficultyText(){
		if (this.difficultyText == null)
			this.difficultyText = (TextView) findViewById(R.id.difficulty);
		return this.difficultyText;
	}
	
	
	
	GameBrain getBrain(){
		if (this.brain == null){
			this.brain = new GameBrain(getIntent().getExtras().getInt("difficulty"));
		}
		return this.brain;
	}
	
	public void prep(){
		getScore().setText("YOUR SCORE: " + getBrain().getScore());
		getCommand().setText(getBrain().getStringCommand());
		timeLeft = getBrain().getDifficulty();
		isPaused = false;
	}
	
	public void purple(View v){		
		vibe.vibrate(50);
		if (getBrain().isCorrect(0)){
			getScore().setText("YOUR SCORE: " + getBrain().getScore());
			getCommand().setText(getBrain().getStringCommand());
			timeLeft = getBrain().getDifficulty();
		} else gameOver("WRONG!");		
	}
	
	public void pink(View v){
		vibe.vibrate(50);
		if (getBrain().isCorrect(1)){
			getScore().setText("YOUR SCORE: " + getBrain().getScore());
			getCommand().setText(getBrain().getStringCommand());	
			timeLeft = getBrain().getDifficulty();
		} else gameOver("WRONG!");	
	}
	
	public void green(View v){
		vibe.vibrate(50);
		if (getBrain().isCorrect(2)){
			getScore().setText("YOUR SCORE: " + getBrain().getScore());
			getCommand().setText(getBrain().getStringCommand());
			timeLeft = getBrain().getDifficulty();
		} else gameOver("WRONG!");	
	}
	
	
	
	public void blue(View v){
		vibe.vibrate(50);
		if (getBrain().isCorrect(3)){
			getScore().setText("YOUR SCORE: " + getBrain().getScore());
			getCommand().setText(getBrain().getStringCommand());
			timeLeft = getBrain().getDifficulty();
		} else gameOver("WRONG!");				
	}
	
	public void pause(View v){
		Intent intent = new Intent(Intent.ACTION_VIEW);
    	intent.setClassName(this, PauseActivity.class.getName());
    	this.startActivity(intent);
	}
	
	void gameOver(String reason){
		isPlaying = false;
		Log.d("GAMEACTIVITY", "gameOver: " + checkIfHighScore());
		
		Intent intent = new Intent(Intent.ACTION_VIEW);
		if (checkIfHighScore() <= 5){
			intent.setClassName(this, ScoreActivity.class.getName());
	    	intent.putExtra("position", checkIfHighScore());
	    	intent.putExtra("score", getBrain().getScore() );
		}else {	
	    	intent.setClassName(this, GameOverActivity.class.getName());
	    	Log.d("GAMEOVER INTENT", ""+getBrain().getDifficulty());
	    	intent.putExtra("difficulty", getBrain().getDifficulty());
	    	intent.putExtra("reason", reason);			
		}
		this.startActivity(intent);
    	finish();
	}
	
	int checkIfHighScore(){
		Log.d("GAMEACTIVITY", "CheckIfHighScore: " + getBrain().getScore());
		int finalScore = (getBrain().getScore());
		SharedPreferences highScore = getSharedPreferences("tap it", 0);
		String[] scores = highScore.getString("highScore", "1.|2.|3.|4.|5.|0|0|0|0|0").split("\\|");
		Log.d("GAMEACTIVITY", "CheckIfHighScore: " + highScore.getString("highScore", "1.|2.|3.|4.|5.|0|0|0|0|0"));
		if (finalScore >= Integer.parseInt(scores[5])){
			Log.d("GAMEACTIVITY", "CheckIfHighScore: 1 "+scores[5]);
			return 1;
		}
		if (finalScore >= Integer.parseInt(scores[6])){
			Log.d("GAMEACTIVITY", "CheckIfHighScore: 2 "+scores[6]);
			return 2;
		}
		if (finalScore >= Integer.parseInt(scores[7])){
			Log.d("GAMEACTIVITY", "CheckIfHighScore: 3 "+scores[7]);
			return 3;
		}
		if (finalScore >= Integer.parseInt(scores[8])){
			Log.d("GAMEACTIVITY", "CheckIfHighScore: 4 "+scores[8]);
			return 4;
		}
		if (finalScore >= Integer.parseInt(scores[9])){
			Log.d("GAMEACTIVITY", "CheckIfHighScore: 5 "+scores[9]);
			return 5;
		}else return 0;
			
	}

	
	
	
}
