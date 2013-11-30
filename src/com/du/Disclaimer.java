package com.du;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Disclaimer extends Activity {

	private final int SPLASH_DISPLAY_LENGHT = 2000;

	/** Called when the activity is first created. */

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.disclaimer);

		/**
		 * New handler to start the Springboard-Activity and close this Splash-Screen
		 * after the specified time.
		 */
		new Handler().postDelayed(new Runnable() {

			public void run() {
				/* Create an Intent that will start the Springboard-Activity. */
				Intent mainIntent = new Intent(Disclaimer.this, Springboard.class);
				Disclaimer.this.startActivity(mainIntent);
				Disclaimer.this.finish();
			}
		}, SPLASH_DISPLAY_LENGHT);
	}
}