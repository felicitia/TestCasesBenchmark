package com.google.android.gms.internal.ads;

final class acf implements Runnable {
    private final /* synthetic */ ace a;

    acf(ace ace) {
        this.a = ace;
    }

    public final void run() {
        if (this.a.b == null) {
            synchronized (ace.d) {
                if (this.a.b == null) {
                    boolean booleanValue = ((Boolean) ajh.f().a(akl.bC)).booleanValue();
                    if (booleanValue) {
                        try {
                            ace.a = new ahl(this.a.c.a, "ADSHIELD", null);
                        } catch (Throwable unused) {
                            booleanValue = false;
                        }
                    }
                    this.a.b = Boolean.valueOf(booleanValue);
                    ace.d.open();
                }
            }
        }
    }
}
