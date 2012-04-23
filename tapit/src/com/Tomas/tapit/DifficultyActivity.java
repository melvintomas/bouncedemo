package com.Tomas.tapit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DifficultyActivity extends Activity{
	
	final int easySpeed = 3000;  //milliseconds
	final int mediumSpeed = 2000; //milliseconds
	final int hardSpeed = 1500; //milliseconds
	int userSpeed = 0;	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulty);
    }
    
    public void easy (View v){
    	userSpeed = easySpeed;
    	runGame();
    }
    
    public void medium (View v){
    	userSpeed = mediumSpeed;
    	runGame();
    }
    
    public void hard (View v){
    	userSpeed = hardSpeed;
    	runGame();
    }
    
    public void runGame(){
    	Intent intent = new Intent(Intent.ACTION_VIEW);
    	intent.setClassName(this, CountDownActivity.class.getName());
    	intent.putExtra("difficulty", userSpeed);
    	intent.putExtra("isNewGame", true);
    	this.startActivity(intent);
    	finish();
    	
    	
    }
 
}
