package com.emjebity.tapit;



import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class DifficultyActivity extends Activity {

	final int easySpeed = 3000; // milliseconds
	final int mediumSpeed = 2000; // milliseconds
	final int hardSpeed = 1500; // milliseconds
	int userSpeed = 0;
	Animation pushLeftIn;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.difficulty);
		pushLeftIn = AnimationUtils.loadAnimation(this, R.anim.push_left_in);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			((TextView) findViewById(R.id.easy)).startAnimation(pushLeftIn);
			((TextView) findViewById(R.id.medium)).startAnimation(pushLeftIn);
			((TextView) findViewById(R.id.hard)).startAnimation(pushLeftIn);
			((TextView) findViewById(R.id.practice)).startAnimation(pushLeftIn);
			
		}
	}

	public void easy(View v) {
		userSpeed = easySpeed;
		runGame();
	}

	public void medium(View v) {
		userSpeed = mediumSpeed;
		runGame();
	}

	public void hard(View v) {
		userSpeed = hardSpeed;
		runGame();
	}
	
	public void practice(View v){
		runGame();
	}

	public void runGame() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(this, CountDownActivity.class.getName());
		intent.putExtra("difficulty", userSpeed);
		intent.putExtra("isNewGame", true);
		this.startActivity(intent);
		finish();

	}

}
