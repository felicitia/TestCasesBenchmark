package com.salesforce.marketingcloud.c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.os.EnvironmentCompat;
import android.support.v4.util.ArrayMap;
import com.salesforce.marketingcloud.i;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.k;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

@RestrictTo({Scope.LIBRARY})
public class f extends i {
    /* access modifiers changed from: private */
    public static final String b = j.a(f.class);
    @VisibleForTesting
    final Map<d, a> a = new ArrayMap();
    private final Map<String, String> c = new LinkedHashMap<String, String>() {
        /* access modifiers changed from: protected */
        public boolean removeEldestEntry(Entry<String, String> entry) {
            return size() > 10;
        }
    };
    private final Context d;
    private final SharedPreferences e;
    private BroadcastReceiver f;

    public interface a {
        void a(e eVar, g gVar);
    }

    private class b extends BroadcastReceiver {
        private b() {
        }

        public void onReceive(Context context, Intent intent) {
            String a2;
            String str;
            if (intent == null) {
                a2 = f.b;
                str = "Received null intent";
            } else {
                String action = intent.getAction();
                if (action == null) {
                    a2 = f.b;
                    str = "Received null action";
                } else {
                    char c = 65535;
                    if (action.hashCode() == 431436234 && action.equals("com.salesforce.marketingcloud.http.RESPONSE")) {
                        c = 0;
                    }
                    if (c != 0) {
                        j.b(f.b, "Received unknown action: %s", action);
                        return;
                    }
                    e a3 = e.a(intent.getBundleExtra("http_request"));
                    g gVar = (g) intent.getParcelableExtra("http_response");
                    if (a3 == null || gVar == null) {
                        j.a(f.b, "Received null request/response", new Object[0]);
                        return;
                    } else {
                        f.this.a(a3, gVar);
                        return;
                    }
                }
            }
            j.a(a2, str, new Object[0]);
        }
    }

    public f(Context context, SharedPreferences sharedPreferences) {
        this.d = (Context) com.salesforce.marketingcloud.e.f.a(context, "Context is null");
        this.e = (SharedPreferences) com.salesforce.marketingcloud.e.f.a(sharedPreferences, "SharedPreferences is null");
    }

    /* access modifiers changed from: private */
    public void a(@NonNull e eVar, @NonNull g gVar) {
        String str = b;
        String str2 = "%s request took %dms with code: %d";
        Object[] objArr = new Object[3];
        objArr[0] = eVar.h() != null ? eVar.h().name() : EnvironmentCompat.MEDIA_UNKNOWN;
        objArr[1] = Long.valueOf(gVar.i());
        objArr[2] = Integer.valueOf(gVar.c());
        j.a(str, str2, objArr);
        if (eVar.h() != null) {
            eVar.h().a(this.e, gVar);
        }
        try {
            this.c.put(eVar.f(), String.format(Locale.ENGLISH, "%s - %d", new Object[]{gVar.b(), Integer.valueOf(gVar.c())}));
        } catch (Exception e2) {
            j.c(b, e2, "Failed to record response.", new Object[0]);
        }
        synchronized (this.a) {
            a aVar = (a) this.a.get(eVar.h());
            if (aVar != null) {
                try {
                    aVar.a(eVar, gVar);
                } catch (Exception e3) {
                    j.c(b, e3, "Failed to deliver response.", new Object[0]);
                }
            } else {
                j.e(b, "Request %s complete, but no listener was present to handle response %d.", eVar.f(), Integer.valueOf(gVar.c()));
            }
        }
    }

    private void c() {
        com.google.android.gms.c.a.a(this.d);
    }

    /* access modifiers changed from: protected */
    public final void a(com.salesforce.marketingcloud.InitializationStatus.a aVar) {
        try {
            c();
        } catch (Exception e2) {
            aVar.e(true);
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to install providers: ");
            sb.append(e2.getMessage());
            aVar.b(sb.toString());
        }
        this.f = new b();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.salesforce.marketingcloud.http.RESPONSE");
        LocalBroadcastManager.getInstance(this.d).registerReceiver(this.f, intentFilter);
    }

    public void a(@NonNull d dVar) {
        synchronized (this.a) {
            this.a.remove(dVar);
        }
    }

    public void a(@NonNull d dVar, @NonNull a aVar) {
        synchronized (aVar) {
            if (this.a.put(dVar, aVar) != null) {
                j.b(b, "%s replaces previous listener for $s requests", aVar.getClass().getName(), dVar.name());
            }
        }
    }

    public void a(@NonNull e eVar) {
        com.salesforce.marketingcloud.e.f.a(eVar, "request is null");
        try {
            c();
        } catch (Exception unused) {
            j.d(b, "Failed to verify SSL providers via Google Play Services.", new Object[0]);
        }
        if (eVar.h().a(this.e) < System.currentTimeMillis()) {
            k.a(this.d, eVar);
            return;
        }
        synchronized (this.a) {
            a aVar = (a) this.a.get(eVar.h());
            if (aVar != null) {
                aVar.a(eVar, g.a("Too many requests", -1));
            }
        }
    }

    public final void a(boolean z) {
        synchronized (this.a) {
            this.a.clear();
        }
        if (this.d != null && this.f != null) {
            LocalBroadcastManager.getInstance(this.d).unregisterReceiver(this.f);
        }
    }

    @NonNull
    public final String b() {
        return "RequestManager";
    }
}
