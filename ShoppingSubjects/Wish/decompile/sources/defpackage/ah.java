package defpackage;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import java.util.Locale;

/* renamed from: ah reason: default package */
/* compiled from: GA */
public final class ah extends s {
    public ah(Context context) {
        super(context);
    }

    public final void c() {
        if (this.b != null) {
            Sensor defaultSensor = this.b.getDefaultSensor(8);
            if (defaultSensor != null) {
                if (this.b.registerListener(this, defaultSensor, this.d)) {
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
                super.a(String.format(Locale.US, "%s, %s", new Object[]{Float.valueOf(sensorEvent.values[0]), Integer.valueOf(this.f)}));
                return;
            }
            a();
        }
    }

    public final void onAccuracyChanged(Sensor sensor, int i) {
        if (this.e.get() && sensor.getType() == 8) {
            this.f = i;
        }
    }
}
