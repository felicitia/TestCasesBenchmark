package com.facebook.marketing;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class ViewIndexingTrigger implements SensorEventListener {
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final double SHAKE_THRESHOLD_GRAVITY = 2.700000047683716d;
    private a mListener;
    private int mShakeCount;
    private long mShakeTimestamp;

    public interface a {
        void a(int i);
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void setOnShakeListener(a aVar) {
        this.mListener = aVar;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (this.mListener != null) {
            float f = sensorEvent.values[0];
            double d = (double) (f / 9.80665f);
            double d2 = (double) (sensorEvent.values[1] / 9.80665f);
            double d3 = (double) (sensorEvent.values[2] / 9.80665f);
            if (Math.sqrt((d * d) + (d2 * d2) + (d3 * d3)) > SHAKE_THRESHOLD_GRAVITY) {
                long currentTimeMillis = System.currentTimeMillis();
                if (this.mShakeTimestamp + 500 <= currentTimeMillis) {
                    if (this.mShakeTimestamp + 3000 < currentTimeMillis) {
                        this.mShakeCount = 0;
                    }
                    this.mShakeTimestamp = currentTimeMillis;
                    this.mShakeCount++;
                    this.mListener.a(this.mShakeCount);
                }
            }
        }
    }

    public void resetCount() {
        this.mShakeCount = 0;
    }
}
