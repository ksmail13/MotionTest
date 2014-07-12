package com.micky.motiontest;

import com.micky.motiontest.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements MoveSensorDataChangeListener{
	private TextView point_view = null;
	private MoveSensorManager sensorMgr = null;
	
	boolean isTabed = false;
	
	private Point currentPoint;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		point_view = (TextView)findViewById(R.id.point_view);
		point_view = new TextView(this);
		point_view.setText("Get OFF!");
		
		sensorMgr = new MoveSensorManager(this, this);
		
		currentPoint = new Point();
	}

	@Override
	protected void onResume() {
		super.onResume();
		sensorMgr.registerSensors();
	}

	@Override
	protected void onPause() {
		super.onPause();
		sensorMgr.unregisterSensors();;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			isTabed = true;
			Log.i("Touch", "move start");
			break;
		case MotionEvent.ACTION_UP:
			isTabed = false;
			Log.i("Touch", "move end");
			break;
		}
		// true 리턴시 뷰에 이벤트가 넘어가지 않는다.
		return false;
	}



	@Override
	public void onAccelerationDataChanged(float x, float y, float z) {
		if(isTabed) {
			currentPoint.x += (int)Math.round(x);
			currentPoint.y += (int)Math.round(y);
			currentPoint.z += (int)Math.round(z);
			String msg = (int)Math.round(x) + " " + (int)Math.round(y);
			
			point_view.setText(msg);
			Log.i("Move", currentPoint.toString());
		}
	}



	@Override
	public void onGyroDataChanged(float x, float y, float z) {
//		if(!isTabed) {
//			String msg = (int)Math.round(x) + " " + (int)Math.round(y) + " " + (int)Math.round(z);
//			point_view.setText(msg);
//			Log.i("Rotation", msg);
//		}
	}
	
	protected class Point {
		public int x;
		public int y;
		public int z;
		
		public Point() {
			x = 0; y = 0; z = 0;
		}
		
		public String toString() {
			return "x: " + x + "\ty: " + y + "\tz: " + z;
		}
	}
}


