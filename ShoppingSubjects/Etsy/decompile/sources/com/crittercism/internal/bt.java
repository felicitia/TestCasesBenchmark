package com.crittercism.internal;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.NotificationCompat;
import com.crittercism.internal.at.b;
import com.crittercism.internal.at.d;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

public final class bt extends br {
    private ExecutorService b;
    /* access modifiers changed from: private */
    public ay<at> c;
    /* access modifiers changed from: private */
    public ap d;

    public bt(Application application, ExecutorService executorService, ay<at> ayVar, ap apVar) {
        super(application);
        this.b = executorService;
        this.c = ayVar;
        this.d = apVar;
        a();
    }

    public final void b() {
        this.b.submit(new Runnable() {
            public final void run() {
                if (((Boolean) bt.this.d.a(ap.am)).booleanValue()) {
                    ay b = bt.this.c;
                    HashMap hashMap = new HashMap();
                    hashMap.put(NotificationCompat.CATEGORY_EVENT, "foregrounded");
                    b.a(new at(b.d, new JSONObject(hashMap)));
                }
            }
        });
    }

    public final void c() {
        this.b.submit(new Runnable() {
            public final void run() {
                if (((Boolean) bt.this.d.a(ap.am)).booleanValue()) {
                    ay b = bt.this.c;
                    HashMap hashMap = new HashMap();
                    hashMap.put(NotificationCompat.CATEGORY_EVENT, "backgrounded");
                    b.a(new at(b.d, new JSONObject(hashMap)));
                }
            }
        });
    }

    public final void a(final Activity activity) {
        this.b.submit(new Runnable() {
            public final void run() {
                if (((Boolean) bt.this.d.a(ap.an)).booleanValue()) {
                    String name = activity.getClass().getName();
                    int i = d.a;
                    HashMap hashMap = new HashMap();
                    hashMap.put(NotificationCompat.CATEGORY_EVENT, Integer.valueOf(i - 1));
                    hashMap.put("viewName", name);
                    bt.this.c.a(new at(b.f, new JSONObject(hashMap)));
                }
            }
        });
    }
}
