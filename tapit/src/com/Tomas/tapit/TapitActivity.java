package com.Tomas.tapit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TapitActivity extends Activity {	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    
    public void play(View v){
    	Intent intent = new Intent(Intent.ACTION_VIEW);
    	intent.setClassName(this, DifficultyActivity.class.getName());
    	this.startActivity(intent);	
    }
    
    public void highScore(View v){
    	Intent intent = new Intent(Intent.ACTION_VIEW);
    	intent.setClassName(this, HighScoreActivity.class.getName());
    	this.startActivity(intent);
    	
    }
    
    public void exit(View v){
    	finish();
    }


	
    
}