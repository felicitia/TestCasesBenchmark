package com.etsy.android.ui.cart;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.i;
import com.etsy.android.lib.core.j;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.models.UserCounters;
import com.etsy.android.lib.requests.UserCountersRequest;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: CartUtil */
public class b {
    private static AtomicInteger a = new AtomicInteger();
    /* access modifiers changed from: private */
    public static boolean b = false;

    /* compiled from: CartUtil */
    public static class a extends i<UserCounters> {
        private Context a;

        public a(Context context) {
            this.a = context;
        }

        /* access modifiers changed from: protected */
        /* renamed from: i */
        public UserCountersRequest a() {
            return UserCountersRequest.getCartCount();
        }

        /* access modifiers changed from: protected */
        public void a_(k<UserCounters> kVar) {
            if (kVar.a()) {
                int i = 0;
                if (kVar.i()) {
                    i = ((UserCounters) kVar.g().get(0)).getCartCount();
                }
                b.a(this.a, i);
                b.b = true;
            }
        }
    }

    public static void a(Context context, j jVar) {
        if (!b) {
            jVar.a((g<Result>) new a<Result>(context));
        }
    }

    public static void a(Context context, int i) {
        a.set(i);
        b(context);
    }

    public static int a() {
        return a.get();
    }

    public static void b() {
        b = false;
    }

    public static void a(Context context) {
        a.incrementAndGet();
        b(context);
    }

    public static void b(Context context) {
        if (context != null) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.etsy.android.cart.UPDATE_CART"));
        }
    }
}
