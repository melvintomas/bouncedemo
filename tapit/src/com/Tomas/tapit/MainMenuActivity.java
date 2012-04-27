package com.Tomas.tapit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainMenuActivity extends Activity {
	Animation pushLeftIn;
	Button play;
	Button highScore;
	Button exit;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("MAINMENU", "Success: ");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		pushLeftIn = AnimationUtils.loadAnimation(this, R.anim.push_left_in);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			((Button) findViewById(R.id.play)).startAnimation(pushLeftIn);
			((Button) findViewById(R.id.highScore)).startAnimation(pushLeftIn);
			((Button) findViewById(R.id.exit)).startAnimation(pushLeftIn);
		}
	}

	public void play(View v) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(this, DifficultyActivity.class.getName());
		this.startActivity(intent);
	}

	public void highScore(View v) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(this, HighScoreActivity.class.getName());
		this.startActivity(intent);
	}

	public void about(View v) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(this, AboutActivity.class.getName());
		this.startActivity(intent);
	}

	public void exit(View v) {
		finish();
	}

}