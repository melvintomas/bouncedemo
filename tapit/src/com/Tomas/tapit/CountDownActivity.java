package com.Tomas.tapit;



import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.media.SoundPool;


public class CountDownActivity extends Activity{
	
	SoundPool soundPool;
	int soundShort;
	int soundLong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
			
		setContentView(R.layout.countdown);
		soundPool = new SoundPool(1,AudioManager.STREAM_MUSIC, 0);
		soundShort = soundPool.load(getApplicationContext(), R.raw.shortbuzz, 1);
		soundLong = soundPool.load(getApplicationContext(), R.raw.longbuzz, 1);
		new Timer().execute();
	}
	
	public class Timer extends AsyncTask<String, Integer, String>{


		@Override
		protected String doInBackground(String... params) {
			
			soundPool.play(soundShort, 1, 1, 1, 0, 1);
			for (int i = 0; i<=4; i++){
				publishProgress(i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}Log.d("COUNTDOWNACTIVITY", "Loop: "+i);
			}
			
			
			return null;
		}


		@Override
		protected void onProgressUpdate(Integer... values) {
			if (values[0] == 0){
				
				findViewById(R.id.three).setVisibility(View.VISIBLE);
			}
			
			if (values[0] == 1){
				soundPool.play(soundShort, 1, 1, 1, 0, 1);
				findViewById(R.id.three).setVisibility(View.GONE);
				findViewById(R.id.two).setVisibility(View.VISIBLE);
			}
			
			if (values[0] == 2){
				soundPool.play(soundShort, 1, 1, 1, 0, 1);
				findViewById(R.id.two).setVisibility(View.GONE);
				findViewById(R.id.one).setVisibility(View.VISIBLE);

			}

			if (values[0] == 3){
				soundPool.play(soundLong, 1, 1, 1, 0, 1);
				findViewById(R.id.one).setVisibility(View.GONE);
				findViewById(R.id.go).setVisibility(View.VISIBLE);
			}
			if (values[0] == 4){
				run();
			}
			
			
			super.onProgressUpdate(values);
			
		}
		
		
		

	
	}
	
	void run(){
		if (getIntent().getExtras().getBoolean("isNewGame")){
		Intent intent = new Intent(Intent.ACTION_VIEW);
    	intent.setClassName(this, GameActivity.class.getName());
    	intent.putExtra("difficulty", getIntent().getExtras().getInt("difficulty"));
    	this.startActivity(intent);
		}
    	finish();
	}
}
