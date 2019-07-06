package com.squareup.seismic;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeDetector implements SensorEventListener {
    private int accelerationThreshold = 13;
    private Sensor accelerometer;
    private final Listener listener;
    private final SampleQueue queue = new SampleQueue();
    private SensorManager sensorManager;

    public interface Listener {
        void hearShake();
    }

    static class Sample {
        boolean accelerating;
        Sample next;
        long timestamp;

        Sample() {
        }
    }

    static class SamplePool {
        private Sample head;

        SamplePool() {
        }

        /* access modifiers changed from: 0000 */
        public Sample acquire() {
            Sample sample = this.head;
            if (sample == null) {
                return new Sample();
            }
            this.head = sample.next;
            return sample;
        }

        /* access modifiers changed from: 0000 */
        public void release(Sample sample) {
            sample.next = this.head;
            this.head = sample;
        }
    }

    static class SampleQueue {
        private int acceleratingCount;
        private Sample newest;
        private Sample oldest;
        private final SamplePool pool = new SamplePool();
        private int sampleCount;

        SampleQueue() {
        }

        /* access modifiers changed from: 0000 */
        public void add(long j, boolean z) {
            purge(j - 500000000);
            Sample acquire = this.pool.acquire();
            acquire.timestamp = j;
            acquire.accelerating = z;
            acquire.next = null;
            if (this.newest != null) {
                this.newest.next = acquire;
            }
            this.newest = acquire;
            if (this.oldest == null) {
                this.oldest = acquire;
            }
            this.sampleCount++;
            if (z) {
                this.acceleratingCount++;
            }
        }

        /* access modifiers changed from: 0000 */
        public void clear() {
            while (this.oldest != null) {
                Sample sample = this.oldest;
                this.oldest = sample.next;
                this.pool.release(sample);
            }
            this.newest = null;
            this.sampleCount = 0;
            this.acceleratingCount = 0;
        }

        /* access modifiers changed from: 0000 */
        public void purge(long j) {
            while (this.sampleCount >= 4 && this.oldest != null && j - this.oldest.timestamp > 0) {
                Sample sample = this.oldest;
                if (sample.accelerating) {
                    this.acceleratingCount--;
                }
                this.sampleCount--;
                this.oldest = sample.next;
                if (this.oldest == null) {
                    this.newest = null;
                }
                this.pool.release(sample);
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean isShaking() {
            return this.newest != null && this.oldest != null && this.newest.timestamp - this.oldest.timestamp >= 250000000 && this.acceleratingCount >= (this.sampleCount >> 1) + (this.sampleCount >> 2);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public ShakeDetector(Listener listener2) {
        this.listener = listener2;
    }

    public boolean start(SensorManager sensorManager2) {
        boolean z = true;
        if (this.accelerometer != null) {
            return true;
        }
        this.accelerometer = sensorManager2.getDefaultSensor(1);
        if (this.accelerometer != null) {
            this.sensorManager = sensorManager2;
            sensorManager2.registerListener(this, this.accelerometer, 0);
        }
        if (this.accelerometer == null) {
            z = false;
        }
        return z;
    }

    public void stop() {
        if (this.accelerometer != null) {
            this.sensorManager.unregisterListener(this, this.accelerometer);
            this.sensorManager = null;
            this.accelerometer = null;
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        boolean isAccelerating = isAccelerating(sensorEvent);
        this.queue.add(sensorEvent.timestamp, isAccelerating);
        if (this.queue.isShaking()) {
            this.queue.clear();
            this.listener.hearShake();
        }
    }

    private boolean isAccelerating(SensorEvent sensorEvent) {
        float f = sensorEvent.values[0];
        float f2 = sensorEvent.values[1];
        float f3 = sensorEvent.values[2];
        if (((double) ((f * f) + (f2 * f2) + (f3 * f3))) > ((double) (this.accelerationThreshold * this.accelerationThreshold))) {
            return true;
        }
        return false;
    }

    public void setSensitivity(int i) {
        this.accelerationThreshold = i;
    }
}
