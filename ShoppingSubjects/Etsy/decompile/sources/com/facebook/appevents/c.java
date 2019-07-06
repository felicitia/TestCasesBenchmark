package com.facebook.appevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.b;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger.FlushBehavior;
import com.facebook.f;
import com.facebook.internal.j;
import com.facebook.internal.k;
import com.facebook.internal.t;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AppEventQueue */
class c {
    private static final String a = "com.facebook.appevents.c";
    /* access modifiers changed from: private */
    public static volatile b b = new b();
    /* access modifiers changed from: private */
    public static final ScheduledExecutorService c = Executors.newSingleThreadScheduledExecutor();
    /* access modifiers changed from: private */
    public static ScheduledFuture d;
    /* access modifiers changed from: private */
    public static final Runnable e = new Runnable() {
        public void run() {
            c.d = null;
            if (AppEventsLogger.a() != FlushBehavior.EXPLICIT_ONLY) {
                c.b(FlushReason.TIMER);
            }
        }
    };

    c() {
    }

    public static void a() {
        c.execute(new Runnable() {
            public void run() {
                d.a(c.b);
                c.b = new b();
            }
        });
    }

    public static void a(final FlushReason flushReason) {
        c.execute(new Runnable() {
            public void run() {
                c.b(flushReason);
            }
        });
    }

    public static void a(final AccessTokenAppIdPair accessTokenAppIdPair, final AppEvent appEvent) {
        c.execute(new Runnable() {
            public void run() {
                c.b.a(accessTokenAppIdPair, appEvent);
                if (AppEventsLogger.a() != FlushBehavior.EXPLICIT_ONLY && c.b.b() > 100) {
                    c.b(FlushReason.EVENT_THRESHOLD);
                } else if (c.d == null) {
                    c.d = c.c.schedule(c.e, 15, TimeUnit.SECONDS);
                }
            }
        });
    }

    public static Set<AccessTokenAppIdPair> b() {
        return b.a();
    }

    static void b(FlushReason flushReason) {
        b.a(d.a());
        try {
            e a2 = a(flushReason, b);
            if (a2 != null) {
                Intent intent = new Intent("com.facebook.sdk.APP_EVENTS_FLUSHED");
                intent.putExtra("com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED", a2.a);
                intent.putExtra("com.facebook.sdk.APP_EVENTS_FLUSH_RESULT", a2.b);
                LocalBroadcastManager.getInstance(f.f()).sendBroadcast(intent);
            }
        } catch (Exception e2) {
            Log.w(a, "Caught unexpected exception while flushing app events: ", e2);
        }
    }

    private static e a(FlushReason flushReason, b bVar) {
        e eVar = new e();
        boolean b2 = f.b(f.f());
        ArrayList<GraphRequest> arrayList = new ArrayList<>();
        for (AccessTokenAppIdPair accessTokenAppIdPair : bVar.a()) {
            GraphRequest a2 = a(accessTokenAppIdPair, bVar.a(accessTokenAppIdPair), b2, eVar);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        t.a(LoggingBehavior.APP_EVENTS, a, "Flushing %d events due to %s.", Integer.valueOf(eVar.a), flushReason.toString());
        for (GraphRequest i : arrayList) {
            i.i();
        }
        return eVar;
    }

    private static GraphRequest a(final AccessTokenAppIdPair accessTokenAppIdPair, final f fVar, boolean z, final e eVar) {
        String applicationId = accessTokenAppIdPair.getApplicationId();
        boolean z2 = false;
        j a2 = k.a(applicationId, false);
        final GraphRequest a3 = GraphRequest.a((AccessToken) null, String.format("%s/activities", new Object[]{applicationId}), (JSONObject) null, (b) null);
        Bundle e2 = a3.e();
        if (e2 == null) {
            e2 = new Bundle();
        }
        e2.putString(AccessToken.ACCESS_TOKEN_KEY, accessTokenAppIdPair.getAccessTokenString());
        String d2 = AppEventsLogger.d();
        if (d2 != null) {
            e2.putString("device_token", d2);
        }
        a3.a(e2);
        if (a2 != null) {
            z2 = a2.a();
        }
        int a4 = fVar.a(a3, f.f(), z2, z);
        if (a4 == 0) {
            return null;
        }
        eVar.a += a4;
        a3.a((b) new b() {
            public void a(GraphResponse graphResponse) {
                c.b(accessTokenAppIdPair, a3, graphResponse, fVar, eVar);
            }
        });
        return a3;
    }

    /* access modifiers changed from: private */
    public static void b(final AccessTokenAppIdPair accessTokenAppIdPair, GraphRequest graphRequest, GraphResponse graphResponse, final f fVar, e eVar) {
        String str;
        FacebookRequestError a2 = graphResponse.a();
        String str2 = "Success";
        FlushResult flushResult = FlushResult.SUCCESS;
        boolean z = true;
        if (a2 != null) {
            if (a2.getErrorCode() == -1) {
                str2 = "Failed: No Connectivity";
                flushResult = FlushResult.NO_CONNECTIVITY;
            } else {
                str2 = String.format("Failed:\n  Response: %s\n  Error %s", new Object[]{graphResponse.toString(), a2.toString()});
                flushResult = FlushResult.SERVER_ERROR;
            }
        }
        if (f.a(LoggingBehavior.APP_EVENTS)) {
            try {
                str = new JSONArray((String) graphRequest.h()).toString(2);
            } catch (JSONException unused) {
                str = "<Can't encode events for debug logging>";
            }
            t.a(LoggingBehavior.APP_EVENTS, a, "Flush completed\nParams: %s\n  Result: %s\n  Events JSON: %s", graphRequest.a().toString(), str2, str);
        }
        if (a2 == null) {
            z = false;
        }
        fVar.a(z);
        if (flushResult == FlushResult.NO_CONNECTIVITY) {
            f.d().execute(new Runnable() {
                public void run() {
                    d.a(accessTokenAppIdPair, fVar);
                }
            });
        }
        if (flushResult != FlushResult.SUCCESS && eVar.b != FlushResult.NO_CONNECTIVITY) {
            eVar.b = flushResult;
        }
    }
}
