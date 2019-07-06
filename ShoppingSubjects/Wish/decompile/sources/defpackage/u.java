package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* renamed from: u reason: default package */
/* compiled from: GA */
public final class u extends q {
    private final Context b;
    private final int c;

    public u(Context context, int i) {
        this.b = context;
        this.c = i;
    }

    public final void c() {
        String str;
        Intent registerReceiver = this.b != null ? this.b.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")) : null;
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra("level", -1);
            int intExtra2 = registerReceiver.getIntExtra("scale", -1);
            int intExtra3 = registerReceiver.getIntExtra("status", -1);
            String stringExtra = registerReceiver.getStringExtra("technology");
            int intExtra4 = registerReceiver.getIntExtra("temperature", -1);
            int intExtra5 = registerReceiver.getIntExtra("voltage", -1);
            int intExtra6 = registerReceiver.getIntExtra("plugged", -1);
            switch (this.c) {
                case 0:
                    if (intExtra == -1 || intExtra2 == -1 || intExtra2 == 0) {
                        super.a(Integer.toString(-100));
                        return;
                    } else {
                        super.a(Integer.toString(Math.round((((float) intExtra) / ((float) intExtra2)) * 100.0f)));
                        return;
                    }
                case 1:
                    if (intExtra3 != -1) {
                        if (intExtra3 == 2 || intExtra3 == 5) {
                            str = Integer.toString(1);
                        } else {
                            str = Integer.toString(0);
                        }
                        super.a(str);
                        return;
                    }
                    break;
                case 2:
                    if (stringExtra != null) {
                        super.a(stringExtra);
                        return;
                    }
                    break;
                case 3:
                    if (intExtra4 != -1) {
                        super.a(Integer.toString(intExtra4 / 10));
                        return;
                    }
                    break;
                case 4:
                    if (intExtra5 != -1) {
                        super.a(Integer.toString(intExtra5));
                        return;
                    }
                    break;
                case 5:
                    if (intExtra6 != -1) {
                        if (intExtra6 == 1) {
                            super.a(c.p);
                            return;
                        } else if (intExtra6 == 2) {
                            super.a(c.o);
                            return;
                        } else if (intExtra6 == 4) {
                            super.a(c.q);
                            return;
                        }
                    }
                    break;
                case 6:
                    try {
                        Class loadClass = this.b.getClassLoader().loadClass(c.A[1]);
                        super.a(Double.toString(((Double) loadClass.getMethod("getBatteryCapacity", new Class[0]).invoke(loadClass.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{this.b}), null)).doubleValue()));
                        return;
                    } catch (Exception unused) {
                        super.a(c.m);
                        break;
                    }
            }
        }
    }
}
