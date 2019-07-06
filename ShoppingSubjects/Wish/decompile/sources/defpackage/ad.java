package defpackage;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Build.VERSION;
import java.util.Locale;

/* renamed from: ad reason: default package */
/* compiled from: GA */
public final class ad extends s {
    public ad(Context context) {
        super(context);
    }

    public final void c() {
        Sensor sensor;
        if (this.b != null) {
            if (VERSION.SDK_INT >= 18) {
                sensor = this.b.getDefaultSensor(14);
            } else {
                sensor = this.b.getDefaultSensor(2);
            }
            if (sensor != null) {
                if (this.b.registerListener(this, sensor, this.d)) {
                    this.e.set(true);
                }
                return;
            }
            super.a(c.m);
            this.e.set(false);
        }
    }

    public final void onSensorChanged(SensorEvent sensorEvent) {
        if (!(!this.e.get() || this.f == 0 || this.f == -1)) {
            if (this.a.size() < this.c) {
                super.a(String.format(Locale.US, "%s, %s, %s, %s", new Object[]{Float.valueOf(sensorEvent.values[0]), Float.valueOf(sensorEvent.values[1]), Float.valueOf(sensorEvent.values[2]), Integer.valueOf(this.f)}));
                return;
            }
            a();
        }
    }

    public final void onAccuracyChanged(Sensor sensor, int i) {
        if (this.e.get()) {
            if (VERSION.SDK_INT >= 18 && sensor.getType() == 14) {
                this.f = i;
            } else if (VERSION.SDK_INT < 18 && sensor.getType() == 2) {
                this.f = i;
            }
        }
    }
}
