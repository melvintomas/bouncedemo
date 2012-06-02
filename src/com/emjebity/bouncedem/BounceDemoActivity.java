package com.emjebity.bouncedem;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BounceDemoActivity extends Activity implements SensorEventListener {

	private Bouncer bouncer;
	private int screenHeight, screenWidth;
	private SensorManager sensorManager;
	private Sensor sensor;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		this.sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    this.screenHeight = Math.round(getWindowManager().getDefaultDisplay().getHeight() * 0.90f);
    this.screenWidth = getWindowManager().getDefaultDisplay().getWidth();
    
		this.bouncer = new Bouncer((ImageView) findViewById(R.id.imageView1), this.screenHeight, this.screenWidth, 1, 20);
		
		new Timer().execute();
	}
	
	/** On the pause -- do this */
	@Override
	protected void onPause() {
		sensorManager.unregisterListener(this);
		super.onPause();
	}

	/** When the application resumes back up */
	@Override
	protected void onResume() {
		/* Listen to the sensors again */
		this.sensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
		super.onResume();
	}

	public class Timer extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			while (true) {
				try {
					Thread.sleep(10);
					publishProgress();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

		@Override
		protected void onProgressUpdate(Integer... values) {

			bouncer.bounce();
			
			//Refresh Bouncer
			bouncer.getImage().invalidate();

			super.onProgressUpdate(values);
		}
	}

	//@Override
	public void onSensorChanged(SensorEvent event) {
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];

		((TextView) findViewById(R.id.textView1)).setText("x: " + x);
		((TextView) findViewById(R.id.textView2)).setText("y: " + y);
		((TextView) findViewById(R.id.textView3)).setText("z: " + z);

		/* Set the horizontal speed to the x sensor value */
		this.bouncer.setHorizontalSpeed((int) x);
	}

	//@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
}
