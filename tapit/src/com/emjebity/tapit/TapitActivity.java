package com.emjebity.tapit;



import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class TapitActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Sound.loadSound(getApplicationContext());
		Log.d("LOAD", ""+getApplicationContext());
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emjebityscreen);
		new Timer().execute();
		Sound.loadSound(getApplicationContext());
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	
	public class Timer extends AsyncTask<String, Integer, String> {
		
		//Keep track of time
		@Override
		protected String doInBackground(String... params) {
			try {
				Thread.sleep(2000);
				publishProgress(1);
				Thread.sleep(2000);
				publishProgress(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		//change logos
		@Override
		protected void onProgressUpdate(Integer... values) {
			if (values[0] == 1) {
				((ImageView) findViewById(R.id.emjebity))
						.setVisibility(View.GONE);
				((ImageView) findViewById(R.id.meaghan))
						.setVisibility(View.VISIBLE);
			} else
				run();
			super.onProgressUpdate(values);
		}
	}
	
	//end activity
	void run() {
		Log.d("TAPITACTIVITY", "Run: ");
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(this, MainMenuActivity.class.getName());
		Log.d("TAPITACTIVITY", "Run: ");
		this.startActivity(intent);
		Log.d("TAPITACTIVITY", "Success: ");
		finish();
	}

}
