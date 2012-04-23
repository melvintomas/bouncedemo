package com.Tomas.tapit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class PauseActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pause);
	}
	
	public void resume(View v){
		Intent intent = new Intent(Intent.ACTION_VIEW);
    	intent.setClassName(this, CountDownActivity.class.getName());
    	intent.putExtra("isNewGame", false);
    	this.startActivity(intent);
    	finish();
	}
	
	public void changeDifficulty(View v){
		Intent intent = new Intent(Intent.ACTION_VIEW);
    	intent.setClassName(this, DifficultyActivity.class.getName());
    	this.startActivity(intent);
    	finish();
	}
	
	public void exit(View v){
		finish();
	}
	
}
