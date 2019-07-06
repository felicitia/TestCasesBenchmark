package defpackage;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: af reason: default package */
/* compiled from: GA */
public final class af extends s {
    private float[] g = new float[3];
    private float[] h = new float[3];
    private AtomicBoolean i = new AtomicBoolean();
    private AtomicBoolean j = new AtomicBoolean();

    public af(Context context) {
        super(context);
        this.i.set(false);
        this.j.set(false);
    }

    public final void c() {
        if (this.b != null) {
            Sensor defaultSensor = this.b.getDefaultSensor(1);
            Sensor defaultSensor2 = this.b.getDefaultSensor(2);
            if (this.b == null || defaultSensor == null || defaultSensor2 == null) {
                super.a(c.m);
                this.e.set(false);
            } else {
                boolean registerListener = this.b.registerListener(this, defaultSensor, this.d);
                boolean registerListener2 = this.b.registerListener(this, defaultSensor2, this.d);
                if (registerListener && registerListener2) {
                    this.e.set(true);
                }
            }
        }
    }

    public final void onSensorChanged(SensorEvent sensorEvent) {
        if (!(!this.e.get() || this.f == 0 || this.f == -1)) {
            if (this.a.size() < this.c) {
                if (sensorEvent.sensor.getType() == 1 && !this.i.get()) {
                    System.arraycopy(sensorEvent.values, 0, this.g, 0, sensorEvent.values.length);
                    this.i.set(true);
                } else if (sensorEvent.sensor.getType() == 2 && !this.j.get()) {
                    System.arraycopy(sensorEvent.values, 0, this.h, 0, sensorEvent.values.length);
                    this.j.set(true);
                }
                if (this.i.get() && this.j.get()) {
                    float[] fArr = new float[9];
                    float[] fArr2 = new float[3];
                    SensorManager.getRotationMatrix(fArr, null, this.g, this.h);
                    float[] orientation = SensorManager.getOrientation(fArr, fArr2);
                    super.a(String.format(Locale.US, "%s, %s, %s", new Object[]{Float.valueOf(orientation[0]), Float.valueOf(orientation[1]), Float.valueOf(orientation[2])}));
                    this.g = new float[3];
                    this.h = new float[3];
                    this.i.set(false);
                    this.j.set(false);
                }
            } else {
                a();
            }
        }
    }

    public final void onAccuracyChanged(Sensor sensor, int i2) {
        if (!this.e.get()) {
            return;
        }
        if (sensor.getType() == 1 || sensor.getType() == 2) {
            this.f = i2;
        }
    }
}
