package com.micky.motiontest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class FootPrintView extends View {

	private Paint foot = null;
	
	public FootPrintView(Context context) {
		super(context);
		initPaint();
	}

	public FootPrintView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPaint();
	}

	public FootPrintView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initPaint();
	}

	
	private void initPaint() {
		foot = new Paint();
		foot.setAntiAlias(true);
		foot.setColor(Color.RED);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}
	
	
	
	
}
