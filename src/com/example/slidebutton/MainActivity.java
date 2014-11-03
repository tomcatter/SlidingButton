package com.example.slidebutton;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		DisplayMetrics dm = getResources().getDisplayMetrics();
		final int screenWidth = dm.widthPixels;
		final int screenHeight = dm.heightPixels - 50;
		final Button button = (Button) findViewById(R.id.button);
		button.setOnTouchListener(new OnTouchListener() {
			
			int lastX, lastY;
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
				Log.i("info", "button:touch-->" + action);
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					lastX = (int) event.getRawX();
					lastY = (int) event.getRawY();
					break;
				case MotionEvent.ACTION_CANCEL:
					break;
				case MotionEvent.ACTION_MOVE:
					int dx = (int) event.getRawX() - lastX;
					int dy = (int) event.getRawY() - lastY;
					
					int left = v.getLeft() + dx;
					int bottom = v.getBottom() + dy;
					int right = v.getRight() + dx;
					int top =  v.getTop() + dy;
					if(left < 0){
						left = 0;
						right = left + v.getWidth();
					}
					
					if (top < 0) {
						top = 0;
						bottom = top + v.getHeight();
					}
					
					if(right > screenWidth){
						right = screenWidth;
						left = right - v.getWidth();
					}
					
					if(bottom > screenHeight){
						bottom = screenHeight;
						top = bottom - v.getHeight();
					}
					
					v.layout(left, top, right, bottom);
					
					lastX = (int) event.getRawX();
					lastY = (int) event.getRawY();
					Toast.makeText(MainActivity.this, "µ±«∞Œª÷√:" + left +"," + top +","
							+ right + "," + bottom, Toast.LENGTH_LONG).show();
					v.postInvalidate();
					break;
				default:
					break;
				}
				return false;
			}
		});
	}

}
