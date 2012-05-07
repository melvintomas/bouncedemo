package com.emjebity.tapit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class ScoreActivity extends Activity {

	int position;
	String[] score;
	Facebook facebook = new Facebook("341220242599041");
	private SharedPreferences mPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		setContentView(R.layout.newscore);
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		facebook.authorizeCallback(requestCode, resultCode, data);
	}

	@Override
	protected void onStart() {
		Log.d("SCOREACTIVITY", "onStart: ");
		SharedPreferences highScore = getSharedPreferences("tap it", 0);
		score = highScore.getString("highscore", "-|-|-|-|-|0|0|0|0|0").split(
				"\\|");
		position = getIntent().getExtras().getInt("position");
		Log.d("SCOREACTIVITY", " " + position);
		

		// make room for new score
		if (position == 1) {
			score[4] = score[3];
			score[3] = score[2];
			score[2] = score[1];
			score[1] = score[0];
			score[9] = score[8];
			score[8] = score[7];
			score[7] = score[6];
			score[6] = score[5];
		}
		if (position == 2) {
			score[4] = score[3];
			score[3] = score[2];
			score[2] = score[1];
			score[9] = score[8];
			score[8] = score[7];
			score[7] = score[6];
		}

		if (position == 3) {
			score[4] = score[3];
			score[3] = score[2];
			score[9] = score[8];
			score[8] = score[7];
		}
		if (position == 4) {
			score[4] = score[3];
			score[9] = score[8];
		}
		((TextView) findViewById(R.id.scoreDisplay)).setText("Your Score: "
				+ getIntent().getExtras().getInt("score"));
		score[position + 4] = getIntent().getExtras().getInt("score") + "";
		super.onStart();
	}

	// encode scores into string
	String encodedScore() {
		String highScoreString = "";
		for (int i = 0; i <= 8; i++) {
			highScoreString += score[i] + "|";
			Log.d("SCOREACTIVITY", " " + highScoreString);
		}
		highScoreString += score[9];
		return highScoreString;
	}

	// post score to facebook
	public void post(View v) {

		mPrefs = getPreferences(MODE_PRIVATE);
		String access_token = mPrefs.getString("access_token1", null);
		long expires = mPrefs.getLong("access_expires", 0);
		if (access_token != null) {
			facebook.setAccessToken(access_token);
		}
		if (expires != 0) {
			facebook.setAccessExpires(expires);
		}

		if (!facebook.isSessionValid()) {
			facebook.authorize(this, new String[] { "publish_stream" },
					new DialogListener() {
						@Override
						public void onComplete(Bundle values) {
							SharedPreferences.Editor editor = mPrefs.edit();
							editor.putString("access_token",
									facebook.getAccessToken());
							editor.putLong("access_expires",
									facebook.getAccessExpires());
							editor.commit();
							Log.d("onComplete", "" + values);

							try {

								Bundle parameters = new Bundle();
								parameters.putString("message",
										"I just earned " + score[position + 4]
												+ " points on " + getIntent().getExtras().getString("difficultyString") + " mode. A new high score!");
								parameters.putString("icon",
										"http://emjebity.com/images/icon.gif");
								parameters
										.putString("picture",
												"http://emjebity.com/images/images.gif");
								parameters
										.putString(
												"description",
												"How fast can you react before time runs out? TAP IT, DOUBLE TAP IT, AND SHAKE IT as fast as you can! Post your high scores to Facebook and challenge your friends. Are you the next Tap It! master?");
								parameters
										.putString("link",
												"https://play.google.com/store/apps/details?id=com.emjebity.tapit");
								parameters.putString("caption",
										"Try and beat my score!");
								parameters.putString("name", "Tap it!");
								String request = facebook.request("me/feed",
										parameters, "POST");

								Log.d("facebook", request);
								((Button) findViewById(R.id.facebook))
										.setText("Posted Successfully");
								((Button) findViewById(R.id.facebook))
										.setClickable(false);
								// saveScore();

							} catch (Exception e) {
								e.printStackTrace();
								((Button) findViewById(R.id.facebook))
										.setText("Posting failed. Try again?");

							}
						}

						@Override
						public void onFacebookError(FacebookError error) {
							Log.d("onFacebookError", "" + error);
							((Button) findViewById(R.id.facebook))
							.setText("Posting failed. Try again?");
						}

						@Override
						public void onError(DialogError e) {
							Log.d("onError", "" + e);
							((Button) findViewById(R.id.facebook))
							.setText("Posting failed. Try again?");
						}

						@Override
						public void onCancel() {
							Log.d("onCancel", "cancel");
							((Button) findViewById(R.id.facebook))
							.setText("Posting failed. Try again?");
						}
					});
		} else {

			try {

				Bundle parameters = new Bundle();
				parameters.putString("message", "I just earned "
						+ score[position + 4] + " points on " + getIntent().getExtras().getString("difficultyString") + " mode. A new high score!");
				parameters.putString("icon",
						"http://emjebity.com/images/icon.gif");
				parameters.putString("picture",
						"http://emjebity.com/images/images.gif");
				parameters
						.putString(
								"description",
								"How fast can you react before time runs out? TAP IT, DOUBLE TAP IT, AND SHAKE IT as fast as you can! Post your high scores to Facebook and challenge your friends. Are you the next Tap It! master?");
				parameters
						.putString("link",
								"https://play.google.com/store/apps/details?id=com.emjebity.tapit");
				parameters.putString("caption", "Try and beat my score!");
				parameters.putString("name", "Tap it!");
				String request = facebook
						.request("me/feed", parameters, "POST");

				Log.d("facebook", request);
				((Button) findViewById(R.id.facebook))
						.setText("Posted Successfully");
				((Button) findViewById(R.id.facebook)).setClickable(false);
				// saveScore();

			} catch (Exception e) {
				e.printStackTrace();
				((Button) findViewById(R.id.facebook))
						.setText("Posting failed. Try again?");

			}

		}

	}

	public void save(View v) {
		saveScore();
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(this, GameOverActivity.class.getName());
		intent.putExtra("difficulty",
				(getIntent().getExtras().getInt("difficulty")));
		intent.putExtra("reason", (getIntent().getExtras().getInt("reason")));
		this.startActivity(intent);
		finish();

	}

	// save score and close activity
	void saveScore() {
		score[position - 1] = ((TextView) findViewById(R.id.scoreinput))
				.getText().toString();
		SharedPreferences highScore = getSharedPreferences("tap it", 0);
		SharedPreferences.Editor editor = highScore.edit();
		editor.putString("highscore", encodedScore());
		editor.commit();

	}

}
