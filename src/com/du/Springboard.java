package com.du;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class Springboard extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

		setContentView(R.layout.dashboard);

		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);

		final Button admission = (Button) this.findViewById(R.id.btn_admission);
		final Button latest = (Button) this.findViewById(R.id.btn_latest);
		final Button courses = (Button) this.findViewById(R.id.btn_courses);
		final Button examination = (Button) this
				.findViewById(R.id.btn_examination);
		final Button foreign = (Button) this.findViewById(R.id.btn_foreign);
		final Button conferences = (Button) this
				.findViewById(R.id.btn_conferences);
		final Button directory = (Button) this.findViewById(R.id.btn_directory);


		final String map_url = this.getResources().getString(R.string.map_url);
		final String latestsec = this.getResources().getString(R.string.latest);
		final Context thisContext = this;

		directory.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(Springboard.this, GenericMenu.class);
				intent.putExtra("sec", thisContext.getResources().getString(R.string.directory));
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				Springboard.this.startActivity(intent);
			}
		});

		conferences.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(Springboard.this, GenericMenu.class);
				intent.putExtra("sec", thisContext.getResources().getString(R.string.conferences));
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				Springboard.this.startActivity(intent);
			}
		});

		foreign.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(Springboard.this, GenericMenu.class);
				intent.putExtra("sec", thisContext.getResources().getString(R.string.foreign));
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				Springboard.this.startActivity(intent);
			}
		});

		examination.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(Springboard.this, GenericMenu.class);
				intent.putExtra("sec",thisContext.getResources().getString(R.string.examination));
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				Springboard.this.startActivity(intent);
			}
		});

		courses.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(Springboard.this, GenericMenu.class);
				intent.putExtra("sec", thisContext.getResources().getString(R.string.courses));
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				Springboard.this.startActivity(intent);
			}
		});

		latest.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(Springboard.this, GenericMenu.class);
				intent.putExtra("sec", latestsec);
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				Springboard.this.startActivity(intent);

			}
		});

		admission.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(Springboard.this, GenericMenu.class);
				intent.putExtra("sec", thisContext.getResources().getString(R.string.admission));
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				Springboard.this.startActivity(intent);
			}
		});

		Button colleges = (Button) this.findViewById(R.id.btn_colleges);
		colleges.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Intent collegesIntent = new Intent(Springboard.this,
						GenericMenu.class);
				collegesIntent.putExtra("sec", thisContext.getResources().getString(R.string.colleges));
				Springboard.this.startActivity(collegesIntent);
			}
		});

		Button social = (Button) this.findViewById(R.id.btn_social);
		social.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Intent socialIntent = new Intent(Springboard.this, Social.class);
				Springboard.this.startActivity(socialIntent);
			}
		});

		Button cic = (Button) this.findViewById(R.id.btn_cic);
		cic.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Intent cicIntent = new Intent(Springboard.this, Cic.class);
				Springboard.this.startActivity(cicIntent);
			}
		});

		Button map = (Button) this.findViewById(R.id.btn_map);
		map.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(map_url));
				startActivity(intent);

			}
		});
	}
}
