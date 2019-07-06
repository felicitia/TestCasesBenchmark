package defpackage;

import android.content.Context;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: s reason: default package */
/* compiled from: GA */
public abstract class s extends r implements SensorEventListener {
    protected SensorManager b;
    protected int c = 5;
    protected int d = 1;
    protected AtomicBoolean e = new AtomicBoolean();
    protected int f = 0;

    protected s(Context context) {
        this.b = (SensorManager) context.getSystemService("sensor");
        this.e.set(false);
    }

    public final void a() {
        if (this.e.get() && this.b != null) {
            this.b.unregisterListener(this);
        }
        this.e.set(false);
    }

    public final boolean b() {
        return this.a != null;
    }
}
