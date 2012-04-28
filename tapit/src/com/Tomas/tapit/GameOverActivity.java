package com.Tomas.tapit;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends Activity {

	Animation pushLeftIn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameover);
		
		((TextView) findViewById(R.id.reason)).setText(getIntent().getExtras()
				.getString("reason"));

		pushLeftIn = AnimationUtils.loadAnimation(this, R.anim.push_left_in);
		Log.d("DEBUG", "CHECK");
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			((Button) findViewById(R.id.restart)).startAnimation(pushLeftIn);
			((Button) findViewById(R.id.changeDifficultyG)).startAnimation(pushLeftIn);
			((Button) findViewById(R.id.menuG)).startAnimation(pushLeftIn);
		}
		
	}

	public void restart(View v) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(this, CountDownActivity.class.getName());
		intent.putExtra("difficulty",
				getIntent().getExtras().getInt("difficulty"));
		intent.putExtra("isNewGame", true);
		Log.d("GAMEOVERACTIVITY",
				"" + getIntent().getExtras().getInt("difficulty"));
		this.startActivity(intent);
		finish();
	}

	public void changeDifficultyG(View v) {

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(this, DifficultyActivity.class.getName());
		this.startActivity(intent);
		finish();
	}

	public void menuG(View v) {
		finish();
	}

}
