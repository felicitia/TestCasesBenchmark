package com.onfido.c.a;

import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings.Secure;
import android.util.Pair;
import com.onfido.c.a.a.f;
import com.onfido.c.a.b.a;
import java.util.concurrent.CountDownLatch;

class h extends AsyncTask<Context, Void, Pair<String, Boolean>> {
    private final b a;
    private final CountDownLatch b;
    private final f c;

    h(b bVar, CountDownLatch countDownLatch, f fVar) {
        this.a = bVar;
        this.b = countDownLatch;
        this.c = fVar;
    }

    private Pair<String, Boolean> a(Context context) {
        Boolean valueOf;
        Object[] objArr = {context};
        String str = null;
        Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, objArr);
        if (((Boolean) invoke.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(invoke, new Object[0])).booleanValue()) {
            this.c.c("Not collecting advertising ID because isLimitAdTrackingEnabled (Google Play Services) is true.", new Object[0]);
            valueOf = Boolean.valueOf(false);
        } else {
            str = (String) invoke.getClass().getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
            valueOf = Boolean.valueOf(true);
        }
        return Pair.create(str, valueOf);
    }

    private Pair<String, Boolean> b(Context context) {
        String string;
        Boolean valueOf;
        ContentResolver contentResolver = context.getContentResolver();
        if (Secure.getInt(contentResolver, "limit_ad_tracking") != 0) {
            this.c.c("Not collecting advertising ID because limit_ad_tracking (Amazon Fire OS) is true.", new Object[0]);
            string = null;
            valueOf = Boolean.valueOf(false);
        } else {
            string = Secure.getString(contentResolver, "advertising_id");
            valueOf = Boolean.valueOf(true);
        }
        return Pair.create(string, valueOf);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Pair<String, Boolean> doInBackground(Context... contextArr) {
        Context context = contextArr[0];
        try {
            return a(context);
        } catch (Exception e) {
            this.c.a(e, "Unable to collect advertising ID from Google Play Services.", new Object[0]);
            try {
                return b(context);
            } catch (Exception e2) {
                this.c.a(e2, "Unable to collect advertising ID from Amazon Fire OS.", new Object[0]);
                this.c.c("Unable to collect advertising ID from Amazon Fire OS and Google Play Services.", new Object[0]);
                return null;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Pair<String, Boolean> pair) {
        super.onPostExecute(pair);
        if (pair != null) {
            try {
                a c2 = this.a.c();
                if (c2 == null) {
                    this.c.c("Not collecting advertising ID because context.device is null.", new Object[0]);
                } else {
                    c2.a((String) pair.first, ((Boolean) pair.second).booleanValue());
                    return;
                }
            } finally {
                this.b.countDown();
            }
        }
        this.b.countDown();
    }
}
