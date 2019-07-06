package com.firebase.jobdispatcher;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import com.firebase.jobdispatcher.q.a;
import com.firebase.jobdispatcher.q.b;

/* compiled from: GooglePlayJobWriter */
final class h {
    private final m a = new m("com.firebase.jobdispatcher.");

    private static int a(int i) {
        return i != 2 ? 0 : 1;
    }

    private static int b(int i) {
        int i2 = 2;
        if ((i & 2) == 2) {
            i2 = 0;
        }
        if ((i & 1) == 1) {
            return 1;
        }
        return i2;
    }

    h() {
    }

    private static void a(o oVar, Bundle bundle, b bVar) {
        bundle.putInt("trigger_type", 1);
        if (oVar.h()) {
            bundle.putLong("period", (long) bVar.b());
            bundle.putLong("period_flex", (long) (bVar.b() - bVar.a()));
            return;
        }
        bundle.putLong("window_start", (long) bVar.a());
        bundle.putLong("window_end", (long) bVar.b());
    }

    private static void a(Bundle bundle) {
        bundle.putInt("trigger_type", 2);
        bundle.putLong("window_start", 0);
        bundle.putLong("window_end", 1);
    }

    private static void a(Bundle bundle, a aVar) {
        bundle.putInt("trigger_type", 3);
        int size = aVar.a().size();
        int[] iArr = new int[size];
        Uri[] uriArr = new Uri[size];
        for (int i = 0; i < size; i++) {
            s sVar = (s) aVar.a().get(i);
            iArr[i] = sVar.b();
            uriArr[i] = sVar.a();
        }
        bundle.putIntArray("content_uri_flags_array", iArr);
        bundle.putParcelableArray("content_uri_array", uriArr);
    }

    public Bundle a(o oVar, Bundle bundle) {
        bundle.putString("tag", oVar.e());
        bundle.putBoolean("update_current", oVar.d());
        bundle.putBoolean("persisted", oVar.g() == 2);
        bundle.putString(NotificationCompat.CATEGORY_SERVICE, GooglePlayReceiver.class.getName());
        c(oVar, bundle);
        d(oVar, bundle);
        b(oVar, bundle);
        Bundle b = oVar.b();
        if (b == null) {
            b = new Bundle();
        }
        bundle.putBundle("extras", this.a.a(oVar, b));
        return bundle;
    }

    private static void b(o oVar, Bundle bundle) {
        t c = oVar.c();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("retry_policy", a(c.a()));
        bundle2.putInt("initial_backoff_seconds", c.b());
        bundle2.putInt("maximum_backoff_seconds", c.c());
        bundle.putBundle("retryStrategy", bundle2);
    }

    private static void c(o oVar, Bundle bundle) {
        q f = oVar.f();
        if (f == u.a) {
            a(bundle);
        } else if (f instanceof b) {
            a(oVar, bundle, (b) f);
        } else if (f instanceof a) {
            a(bundle, (a) f);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown trigger: ");
            sb.append(f.getClass());
            throw new IllegalArgumentException(sb.toString());
        }
    }

    private static void d(o oVar, Bundle bundle) {
        int a2 = a.a(oVar.a());
        boolean z = false;
        bundle.putBoolean("requiresCharging", (a2 & 4) == 4);
        String str = "requiresIdle";
        if ((a2 & 8) == 8) {
            z = true;
        }
        bundle.putBoolean(str, z);
        bundle.putInt("requiredNetwork", b(a2));
    }
}
