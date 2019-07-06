package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.ConditionVariable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.GooglePlayServicesUtilLight;

@bu
public final class akj {
    private final Object a = new Object();
    private final ConditionVariable b = new ConditionVariable();
    private volatile boolean c = false;
    /* access modifiers changed from: private */
    @Nullable
    public SharedPreferences d = null;
    private Context e;

    public final <T> T a(akb<T> akb) {
        if (!this.b.block(5000)) {
            throw new IllegalStateException("Flags.initialize() was not called!");
        }
        if (!this.c || this.d == null) {
            synchronized (this.a) {
                if (this.c) {
                    if (this.d == null) {
                    }
                }
                T b2 = akb.b();
                return b2;
            }
        }
        return jg.a(this.e, new akk(this, akb));
    }

    public final void a(Context context) {
        if (!this.c) {
            synchronized (this.a) {
                if (!this.c) {
                    this.e = context.getApplicationContext() == null ? context : context.getApplicationContext();
                    try {
                        Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
                        if (remoteContext == null && context != null) {
                            remoteContext = context.getApplicationContext();
                            if (remoteContext == null) {
                                remoteContext = context;
                            }
                        }
                        if (remoteContext != null) {
                            ajh.d();
                            this.d = remoteContext.getSharedPreferences("google_ads_flags", 0);
                            this.c = true;
                            this.b.open();
                        }
                    } finally {
                        this.b.open();
                    }
                }
            }
        }
    }
}
