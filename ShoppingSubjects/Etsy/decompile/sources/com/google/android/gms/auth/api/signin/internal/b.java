package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.etsy.android.lib.convos.Draft;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

public class b {
    private static final Lock a = new ReentrantLock();
    private static b b;
    private final Lock c = new ReentrantLock();
    private final SharedPreferences d;

    @VisibleForTesting
    private b(Context context) {
        this.d = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static b a(Context context) {
        Preconditions.checkNotNull(context);
        a.lock();
        try {
            if (b == null) {
                b = new b(context.getApplicationContext());
            }
            return b;
        } finally {
            a.unlock();
        }
    }

    private static String b(String str, String str2) {
        StringBuilder sb = new StringBuilder(1 + String.valueOf(str).length() + String.valueOf(str2).length());
        sb.append(str);
        sb.append(Draft.IMAGE_DELIMITER);
        sb.append(str2);
        return sb.toString();
    }

    @VisibleForTesting
    private final GoogleSignInAccount c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String a2 = a(b("googleSignInAccount", str));
        if (a2 != null) {
            try {
                return GoogleSignInAccount.fromJsonString(a2);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    @VisibleForTesting
    private final GoogleSignInOptions d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String a2 = a(b("googleSignInOptions", str));
        if (a2 != null) {
            try {
                return GoogleSignInOptions.fromJsonString(a2);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public GoogleSignInAccount a() {
        return c(a("defaultGoogleSignInAccount"));
    }

    /* access modifiers changed from: protected */
    public String a(String str) {
        this.c.lock();
        try {
            return this.d.getString(str, null);
        } finally {
            this.c.unlock();
        }
    }

    public void a(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        Preconditions.checkNotNull(googleSignInAccount);
        Preconditions.checkNotNull(googleSignInOptions);
        a("defaultGoogleSignInAccount", googleSignInAccount.getObfuscatedIdentifier());
        Preconditions.checkNotNull(googleSignInAccount);
        Preconditions.checkNotNull(googleSignInOptions);
        String obfuscatedIdentifier = googleSignInAccount.getObfuscatedIdentifier();
        a(b("googleSignInAccount", obfuscatedIdentifier), googleSignInAccount.toJsonForStorage());
        a(b("googleSignInOptions", obfuscatedIdentifier), googleSignInOptions.toJson());
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2) {
        this.c.lock();
        try {
            this.d.edit().putString(str, str2).apply();
        } finally {
            this.c.unlock();
        }
    }

    public GoogleSignInOptions b() {
        return d(a("defaultGoogleSignInAccount"));
    }

    /* access modifiers changed from: protected */
    public void b(String str) {
        this.c.lock();
        try {
            this.d.edit().remove(str).apply();
        } finally {
            this.c.unlock();
        }
    }

    public String c() {
        return a("refreshToken");
    }

    public void d() {
        String a2 = a("defaultGoogleSignInAccount");
        b("defaultGoogleSignInAccount");
        if (!TextUtils.isEmpty(a2)) {
            b(b("googleSignInAccount", a2));
            b(b("googleSignInOptions", a2));
        }
    }

    public void e() {
        this.c.lock();
        try {
            this.d.edit().clear().apply();
        } finally {
            this.c.unlock();
        }
    }
}
