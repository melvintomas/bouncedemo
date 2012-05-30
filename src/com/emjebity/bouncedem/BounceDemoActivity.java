package com.emjebity.bouncedem;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class BounceDemoActivity extends Activity implements SensorEventListener {

	ImageView coco;
	int[] cocoCoordinates;
	int height;
	int width;
	int speed;
	int top;
	int bottom;
	int right;
	int left;
	int bounceHeight;
	int horizontalSpeed;
	SensorManager sensorManager;
	Sensor sensor;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		coco = (ImageView) findViewById(R.id.imageView1);

		height = getWindowManager().getDefaultDisplay().getHeight() - 60;
		 width = getWindowManager().getDefaultDisplay().getWidth();
		
		/* Set default speed */
		speed = 20;
		horizontalSpeed = 0;
		bounceHeight = 500;

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

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
		sensorManager.registerListener(this, sensor,
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
			/*
			Log.d("coco",
					"width = " + width + ", height = " + height + ", left = "
							+ coco.getLeft() + ", top = " + coco.getTop()
							+ ", right = " + coco.getRight() + ", bottom = "
							+ coco.getBottom() + ", speed: " + speed);
			*/

			bottom = coco.getBottom();
			top = coco.getTop();
			right = coco.getRight();
			left = coco.getLeft();
			
			// bounce
			if (bottom >= height)
				speed = -Math.abs(speed);
			
			

			// start falling
			if (coco.getTop() <= height / 2)
				speed = +Math.abs(speed);

			// update location
			coco.layout(coco.getLeft() - horizontalSpeed,
					coco.getTop() + speed, coco.getRight() - horizontalSpeed,
					coco.getBottom() + speed);
			
			if (coco.getLeft() < 0) {
				coco.layout(coco.getLeft(), coco.getTop(), coco.getRight(),
						coco.getBottom());
			} else if (coco.getRight() > width) {
				coco.layout(coco.getLeft(), coco.getTop(), coco.getRight(),
						coco.getBottom());
			}
			
			// refresh coconut
			coco.invalidate();

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
		horizontalSpeed = (int) x;
	}

	//@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
}
