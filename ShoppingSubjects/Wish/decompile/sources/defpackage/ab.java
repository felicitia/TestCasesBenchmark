package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.List;
import java.util.Locale;

/* renamed from: ab reason: default package */
/* compiled from: GA */
public final class ab extends q {
    private final Context b;

    public ab(Context context) {
        this.b = context;
    }

    public final void c() {
        if (this.b != null) {
            PackageManager packageManager = this.b.getPackageManager();
            if (packageManager != null) {
                List<PackageInfo> installedPackages = packageManager.getInstalledPackages(128);
                if (installedPackages != null) {
                    for (PackageInfo packageInfo : installedPackages) {
                        for (int i = 0; i < c.z.length; i++) {
                            if (packageInfo.packageName.toLowerCase().contains(c.z[i].toLowerCase())) {
                                super.a(String.format(Locale.US, "%s", new Object[]{Integer.toString(i)}));
                            }
                        }
                    }
                }
            }
        }
    }

    public final boolean b() {
        return this.a != null && this.a.size() > 0;
    }
}
