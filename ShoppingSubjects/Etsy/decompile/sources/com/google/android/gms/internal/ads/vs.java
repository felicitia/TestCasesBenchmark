package com.google.android.gms.internal.ads;

import com.google.a.a.a.a.a.a;
import java.io.PrintWriter;
import java.util.List;

final class vs extends vp {
    private final vq a = new vq();

    vs() {
    }

    public final void a(Throwable th, PrintWriter printWriter) {
        a.a(th, printWriter);
        List<Throwable> a2 = this.a.a(th, false);
        if (a2 != null) {
            synchronized (a2) {
                for (Throwable th2 : a2) {
                    printWriter.print("Suppressed: ");
                    a.a(th2, printWriter);
                }
            }
        }
    }
}
