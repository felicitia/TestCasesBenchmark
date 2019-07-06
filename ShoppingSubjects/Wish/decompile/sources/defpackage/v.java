package defpackage;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Build.VERSION;

/* renamed from: v reason: default package */
/* compiled from: GA */
public final class v extends q {
    private final int b;

    public v(int i) {
        this.b = i;
    }

    @SuppressLint({"HardwareIds"})
    public final void c() {
        switch (this.b) {
            case 0:
                super.a(c.r);
                return;
            case 1:
                super.a(Integer.toString(VERSION.SDK_INT));
                return;
            case 2:
                super.a(Build.ID);
                return;
            case 3:
                super.a(Build.FINGERPRINT);
                return;
            case 4:
                super.a(Build.MODEL);
                return;
            case 5:
                super.a(Build.DISPLAY);
                return;
            case 6:
                super.a(Build.BRAND);
                return;
            case 7:
                super.a("2.6.2");
                return;
            case 8:
                super.a(Build.MANUFACTURER);
                return;
            case 9:
                super.a(Build.PRODUCT);
                return;
            case 10:
                super.a(Build.SERIAL);
                break;
        }
    }
}
