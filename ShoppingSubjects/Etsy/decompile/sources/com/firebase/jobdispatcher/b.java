package com.firebase.jobdispatcher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.firebase.jobdispatcher.q.a;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* compiled from: DefaultJobValidator */
public class b implements r {
    private final Context a;

    public b(Context context) {
        this.a = context;
    }

    private static int a(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        bundle.writeToParcel(obtain, 0);
        int dataSize = obtain.dataSize();
        obtain.recycle();
        return dataSize;
    }

    @Nullable
    private static List<String> a(@Nullable List<String> list, @Nullable List<String> list2) {
        if (list == null) {
            return list2;
        }
        if (list2 == null) {
            return list;
        }
        list.addAll(list2);
        return list;
    }

    @Nullable
    private static List<String> a(@Nullable List<String> list, String str) {
        if (str == null) {
            return list;
        }
        if (list == null) {
            return c(str);
        }
        Collections.addAll(list, new String[]{str});
        return list;
    }

    @Nullable
    private static List<String> a(boolean z, List<String> list, String str) {
        return z ? a(list, str) : list;
    }

    @Nullable
    @CallSuper
    public List<String> a(o oVar) {
        List a2 = a(a(null, a(oVar.f())), a(oVar.c()));
        if (oVar.h() && oVar.f() == u.a) {
            a2 = a(a2, "ImmediateTriggers can't be used with recurring jobs");
        }
        List a3 = a(a2, c(oVar.b()));
        if (oVar.g() > 1) {
            a3 = a(a3, b(oVar.b()));
        }
        return a(a(a3, b(oVar.e())), a(oVar.i()));
    }

    @Nullable
    @CallSuper
    public List<String> a(q qVar) {
        if (qVar == u.a || (qVar instanceof com.firebase.jobdispatcher.q.b) || (qVar instanceof a)) {
            return null;
        }
        return c("Unknown trigger provided");
    }

    @Nullable
    @CallSuper
    public List<String> a(t tVar) {
        int a2 = tVar.a();
        int b = tVar.b();
        int c = tVar.c();
        boolean z = false;
        List a3 = a(300 > c, a(c < b, a((a2 == 1 || a2 == 2) ? false : true, null, "Unknown retry policy provided"), "Maximum backoff must be greater than or equal to initial backoff"), "Maximum backoff must be greater than 300s (5 minutes)");
        if (b < 30) {
            z = true;
        }
        return a(z, a3, "Initial backoff must be at least 30s");
    }

    @Nullable
    private static List<String> b(Bundle bundle) {
        List<String> list = null;
        if (bundle != null) {
            for (String a2 : bundle.keySet()) {
                list = a(list, a(bundle, a2));
            }
        }
        return list;
    }

    @Nullable
    private static List<String> c(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        int a2 = a(bundle);
        if (a2 <= 10240) {
            return null;
        }
        return c(String.format(Locale.US, "Extras too large: %d bytes is > the max (%d bytes)", new Object[]{Integer.valueOf(a2), Integer.valueOf(Task.EXTRAS_LIMIT_BYTES)}));
    }

    @Nullable
    private static String a(Bundle bundle, String str) {
        Object obj = bundle.get(str);
        Class cls = null;
        if (obj == null || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof String) || (obj instanceof Boolean)) {
            return null;
        }
        Locale locale = Locale.US;
        String str2 = "Received value of type '%s' for key '%s', but only the following extra parameter types are supported: Integer, Long, Double, String, and Boolean";
        Object[] objArr = new Object[2];
        if (obj != null) {
            cls = obj.getClass();
        }
        objArr[0] = cls;
        objArr[1] = str;
        return String.format(locale, str2, objArr);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public List<String> a(String str) {
        if (str == null || str.isEmpty()) {
            return c("Service can't be empty");
        }
        if (this.a == null) {
            return c("Context is null, can't query PackageManager");
        }
        PackageManager packageManager = this.a.getPackageManager();
        if (packageManager == null) {
            return c("PackageManager is null, can't validate service");
        }
        Intent intent = new Intent("com.firebase.jobdispatcher.ACTION_EXECUTE");
        intent.setClassName(this.a, str);
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Couldn't find a registered service with the name ");
            sb.append(str);
            sb.append(". Is it declared in the manifest with the right intent-filter? If not, the job won't be started.");
            Log.e("FJD.GooglePlayReceiver", sb.toString());
            return null;
        }
        for (ResolveInfo resolveInfo : queryIntentServices) {
            if (resolveInfo.serviceInfo != null && resolveInfo.serviceInfo.enabled) {
                return null;
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(" is disabled.");
        return c(sb2.toString());
    }

    private static List<String> b(String str) {
        if (str == null) {
            return c("Tag can't be null");
        }
        if (str.length() > 100) {
            return c("Tag must be shorter than 100");
        }
        return null;
    }

    @NonNull
    private static List<String> c(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return arrayList;
    }
}
