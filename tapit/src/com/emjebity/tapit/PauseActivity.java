package com.emjebity.tapit;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class PauseActivity extends Activity {

	Animation pushLeftIn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pause);
		pushLeftIn = AnimationUtils.loadAnimation(this, R.anim.push_left_in);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			((Button) findViewById(R.id.resume)).startAnimation(pushLeftIn);
			((Button) findViewById(R.id.changeDifficultyP)).startAnimation(pushLeftIn);
			((Button) findViewById(R.id.menuP)).startAnimation(pushLeftIn);
		}
	}

	public void resume(View v) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(this, CountDownActivity.class.getName());
		intent.putExtra("isNewGame", false);
		this.startActivity(intent);
		finish();
	}

	public void changeDifficultyP(View v) {
		setResult(1);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(this, DifficultyActivity.class.getName());
		this.startActivity(intent);
		
		finish();
	}

	public void menuP(View v) {
		setResult(1);
		finish();
	}

}
