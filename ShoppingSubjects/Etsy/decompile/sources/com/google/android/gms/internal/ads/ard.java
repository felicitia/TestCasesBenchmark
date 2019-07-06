package com.google.android.gms.internal.ads;

final class ard implements Runnable {
    private final /* synthetic */ zzxa a;
    private final /* synthetic */ arc b;

    ard(arc arc, zzxa zzxa) {
        this.b = arc;
        this.a = zzxa;
    }

    public final void run() {
        synchronized (this.b.i) {
            if (this.b.s == -2) {
                this.b.r = this.b.d();
                if (this.b.r == null) {
                    this.b.a(4);
                } else if (!this.b.e() || this.b.b(1)) {
                    this.a.zza((arg) this.b);
                    this.b.a(this.a);
                } else {
                    String f = this.b.a;
                    StringBuilder sb = new StringBuilder(56 + String.valueOf(f).length());
                    sb.append("Ignoring adapter ");
                    sb.append(f);
                    sb.append(" as delayed impression is not supported");
                    gv.e(sb.toString());
                    this.b.a(2);
                }
            }
        }
    }
}
