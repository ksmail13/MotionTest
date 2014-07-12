package com.micky.motiontest;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * 센서 관리 객체
 * @author micky
 *
 */
public class MoveSensorManager implements SensorEventListener{
	private SensorManager sensorManager;
	private Sensor accSensor;
	private Sensor gyroSensor;
	
	private MoveSensorDataChangeListener tsl = null;
	
	public static final int PERIOD = SensorManager.SENSOR_DELAY_GAME;
	
	public MoveSensorManager (Activity currAct, MoveSensorDataChangeListener tsl) {
		setSensorManager((SensorManager) currAct.getSystemService(Activity.SENSOR_SERVICE));
		setAccSensor(sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION));
		setGyroSensor(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
		
		this.tsl = tsl;
	}

	public SensorManager getSensorManager() {
		return sensorManager;
	}

	public void setSensorManager(SensorManager sensorManager) {
		this.sensorManager = sensorManager;
	}

	public Sensor getAccSensor() {
		return accSensor;
	}

	public void setAccSensor(Sensor accSensor) {
		this.accSensor = accSensor;
		if(accSensor == null) {
			Log.e("sensor error", "This device not contain acceleration sensor");
		}
	}

	public Sensor getGyroSensor() {
		return gyroSensor;
	}

	public void setGyroSensor(Sensor gyroSensor) {
		this.gyroSensor = gyroSensor;
		if(gyroSensor == null) {
			Log.e("sensor error", "This device not contain gyro sensor");
		}
	}
	
	/**
	 * 센서를 등록한다.
	 */
	public void registerSensors() {
		if(getAccSensor() != null)
			getSensorManager().registerListener(this, getAccSensor(), PERIOD);
		if(getGyroSensor() != null)
			getSensorManager().registerListener(this, getGyroSensor(), PERIOD);
	}
	
	public void unregisterSensors() {
		getSensorManager().unregisterListener(this);
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		if(tsl == null) return;
		if(event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
			tsl.onAccelerationDataChanged(event.values[0], event.values[1], event.values[2]);
		}
		else if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
			tsl.onGyroDataChanged(event.values[0], event.values[1], event.values[2]);
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO: nothing
	}
}

/**
 * 센서가 반응했을 때 응답해줄 리스너
 * @author micky
 *
 */
interface MoveSensorDataChangeListener {
	
	/**
	 * 가속도계가 변경 됬을 때 실행되는 리스너
	 * @param x
	 * @param y
	 * @param z
	 */
	void onAccelerationDataChanged(float x, float y, float z);
	
	/**
	 * 자이로센서의 데이터가 변경 됬을때 실행되는 리스너
	 * @param x
	 * @param y
	 * @param z
	 */
	void onGyroDataChanged(float x, float y, float z);
}