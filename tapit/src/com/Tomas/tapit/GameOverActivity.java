package com.Tomas.tapit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameover);
		((TextView) findViewById(R.id.reason)).setText(getIntent().getExtras()
				.getString("reason"));
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

	public void changeDifficulty(View v) {

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(this, DifficultyActivity.class.getName());
		this.startActivity(intent);
		finish();
	}

	public void exit(View v) {
		finish();
	}

}
