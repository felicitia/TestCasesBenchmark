package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/* renamed from: ag reason: default package */
/* compiled from: GA */
public final class ag extends q {
    private final Context b;
    private final int c;
    private PackageInfo d = null;

    public ag(Context context, int i) {
        this.b = context;
        this.c = i;
    }

    public final void c() {
        try {
            if (this.b != null) {
                this.d = this.b.getPackageManager().getPackageInfo(this.b.getPackageName(), 0);
            }
        } catch (NameNotFoundException unused) {
            al.b("M100: M34");
        }
        if (this.d != null) {
            switch (this.c) {
                case 0:
                    super.a(this.d.versionName);
                    return;
                case 1:
                    super.a(Integer.toString(this.d.versionCode));
                    return;
                case 2:
                    super.a(Long.toString(this.d.firstInstallTime));
                    return;
                case 3:
                    super.a(Long.toString(this.d.lastUpdateTime));
                    return;
                case 4:
                    super.a(this.d.packageName);
                    break;
            }
        }
    }
}
