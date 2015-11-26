package com.serveroverload.ticktock;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.ogaclejapan.arclayout.ArcLayout;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class TickTockActivity extends ActionBarActivity {

	private float secondHandRotation;
	private Handler handler = new Handler();
	private ArcLayout arc;
	private Runnable runnable;
	private Button hour1, hour2, hour3, hour4, hour5, hour6, hour7, hour8, hour9, hour10, hour11, hour12;
	ObjectAnimator dialorHandAnimator;
	ObjectAnimator hour1Animator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
		setContentView(R.layout.activity_main);

		arc = (ArcLayout) findViewById(R.id.arc_layout);
		hour1 = (Button) findViewById(R.id.hour_1);
		hour2 = (Button) findViewById(R.id.hour_2);
		hour3 = (Button) findViewById(R.id.hour_3);
		hour4 = (Button) findViewById(R.id.hour_4);
		hour5 = (Button) findViewById(R.id.hour_5);
		hour6 = (Button) findViewById(R.id.hour_6);
		hour7 = (Button) findViewById(R.id.hour_7);
		hour8 = (Button) findViewById(R.id.hour_8);
		hour9 = (Button) findViewById(R.id.hour_9);
		hour10 = (Button) findViewById(R.id.hour_10);
		hour11 = (Button) findViewById(R.id.hour_11);
		hour12 = (Button) findViewById(R.id.hour_12);

		//hour1.setSelected(true);

		runnable = new Runnable() {

			@Override
			public void run() {
				
				Log.d("TICKTOCK", "Rotation is ----------------------"+secondHandRotation);
				
				if(secondHandRotation==360.0f)
				{
					secondHandRotation=0;
				}
				
				dialorHandAnimator = ObjectAnimator.ofFloat(arc, "rotation", secondHandRotation,
						secondHandRotation + 30.0f);
//
//				hour1Animator = ObjectAnimator.ofFloat(hour12, "rotation",  secondHandRotation,
//						secondHandRotation + 30.0f);

				dialorHandAnimator.setDuration(300); // miliseconds
				dialorHandAnimator.setInterpolator(new LinearInterpolator());

//				if ((secondHandRotation = secondHandRotation + 30.0f) >= 360.0f) {
//
//					secondHandRotation = 0;
//				} else {
//					secondHandRotation = secondHandRotation + 30.0f;
//				}
				secondHandRotation = secondHandRotation + 30.0f;

			//	hour1Animator.start();

				dialorHandAnimator.start();

				((TextView) findViewById(R.id.time_now)).setText(sdf.format(new Date(System.currentTimeMillis())));
				handler.postDelayed(this, 1000);

			}
		};

		// handler.postDelayed(runnable, 0);
		//
		// runnable.run();

	}

	@Override
	protected void onResume() {
		if (null != handler && null != runnable) {
			handler.postDelayed(runnable, 0);
			// runnable.run();
		}
		super.onResume();
	}

	@Override
	protected void onPause() {
		if (null != handler && null != runnable)
			handler.removeCallbacks(runnable);
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		if (null != handler && null != runnable)
			handler.removeCallbacks(runnable);
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
