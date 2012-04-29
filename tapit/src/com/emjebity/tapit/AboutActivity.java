package com.emjebity.tapit;



import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.about);
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

}
