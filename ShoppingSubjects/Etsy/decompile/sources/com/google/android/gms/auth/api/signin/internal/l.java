package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.util.VisibleForTesting;

public final class l {
    private static l a;
    @VisibleForTesting
    private b b;
    @VisibleForTesting
    private GoogleSignInAccount c = this.b.a();
    @VisibleForTesting
    private GoogleSignInOptions d = this.b.b();

    private l(Context context) {
        this.b = b.a(context);
    }

    public static synchronized l a(@NonNull Context context) {
        l b2;
        synchronized (l.class) {
            b2 = b(context.getApplicationContext());
        }
        return b2;
    }

    private static synchronized l b(Context context) {
        l lVar;
        synchronized (l.class) {
            if (a == null) {
                a = new l(context);
            }
            lVar = a;
        }
        return lVar;
    }

    public final synchronized void a() {
        this.b.e();
        this.c = null;
        this.d = null;
    }

    public final synchronized void a(GoogleSignInOptions googleSignInOptions, GoogleSignInAccount googleSignInAccount) {
        this.b.a(googleSignInAccount, googleSignInOptions);
        this.c = googleSignInAccount;
        this.d = googleSignInOptions;
    }
}
