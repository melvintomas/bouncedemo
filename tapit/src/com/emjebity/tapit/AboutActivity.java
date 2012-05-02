package com.emjebity.tapit;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		setContentView(R.layout.about);
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		TextView about = (TextView) findViewById(R.id.about);

		about.setText(Html.fromHtml
				("Under the direction of <a href=\"http://linklens.blogspot.com\">"
						+ "Samuel Joseph, PhD</a>, two Hawaii "
						+ "Pacific University students created Tap It! "
						+ "Melvin Tomas, of <a href=\"http://www.emjebity"
						+ ".com\">emjebity</a>, was in Joseph’s <a href=\""
						+ "http://www.techhui.com/events/csci-4702-mobile-"
						+ "programming-at-hpu-spring-2012\">CSCI 4702 Mobile "
						+ "Programming </a> course, while Meaghan Who?, was in "
						+ "Joseph’s <a href=\"http://www.techhui.com/events/hpu"
						+ "-mult-4702-mobile-design-spring-2012\">MULT 4702 "
						+ "Mobile Design</a>course. As such, Tomas’ main "
						+ "role was programmer, and Meaghan Who’s role was designer."
						
						));
		

		
				
		
		about.setMovementMethod(LinkMovementMethod.getInstance());
	}
	
	public void backA(View v){
		finish();
	}

}
