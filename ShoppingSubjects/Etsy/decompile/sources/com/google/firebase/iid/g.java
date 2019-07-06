package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Base64;
import android.util.Log;
import com.etsy.android.lib.convos.Draft;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.firebase.a;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public final class g {
    private final Context a;
    private String b;
    private String c;
    private int d;
    private int e = 0;

    public g(Context context) {
        this.a = context;
    }

    private final PackageInfo a(String str) {
        try {
            return this.a.getPackageManager().getPackageInfo(str, 0);
        } catch (NameNotFoundException e2) {
            String valueOf = String.valueOf(e2);
            StringBuilder sb = new StringBuilder(23 + String.valueOf(valueOf).length());
            sb.append("Failed to find package ");
            sb.append(valueOf);
            Log.w("FirebaseInstanceId", sb.toString());
            return null;
        }
    }

    public static String a(a aVar) {
        String b2 = aVar.c().b();
        if (b2 != null) {
            return b2;
        }
        String a2 = aVar.c().a();
        if (!a2.startsWith("1:")) {
            return a2;
        }
        String[] split = a2.split(Draft.IMAGE_DELIMITER);
        if (split.length < 2) {
            return null;
        }
        String str = split[1];
        if (str.isEmpty()) {
            return null;
        }
        return str;
    }

    public static String a(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance(AndroidUtilsLight.DIGEST_ALGORITHM_SHA1).digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) (112 + (digest[0] & 15));
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException unused) {
            Log.w("FirebaseInstanceId", "Unexpected error, device missing required algorithms");
            return null;
        }
    }

    private final synchronized void e() {
        PackageInfo a2 = a(this.a.getPackageName());
        if (a2 != null) {
            this.b = Integer.toString(a2.versionCode);
            this.c = a2.versionName;
        }
    }

    public final synchronized int a() {
        if (this.e != 0) {
            return this.e;
        }
        PackageManager packageManager = this.a.getPackageManager();
        if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", "com.google.android.gms") == -1) {
            Log.e("FirebaseInstanceId", "Google Play services missing or without correct permission.");
            return 0;
        }
        if (!PlatformVersion.isAtLeastO()) {
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            List queryIntentServices = packageManager.queryIntentServices(intent, 0);
            if (queryIntentServices != null && queryIntentServices.size() > 0) {
                this.e = 1;
                return this.e;
            }
        }
        Intent intent2 = new Intent("com.google.iid.TOKEN_REQUEST");
        intent2.setPackage("com.google.android.gms");
        List queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent2, 0);
        if (queryBroadcastReceivers == null || queryBroadcastReceivers.size() <= 0) {
            Log.w("FirebaseInstanceId", "Failed to resolve IID implementation package, falling back");
            if (PlatformVersion.isAtLeastO()) {
                this.e = 2;
            } else {
                this.e = 1;
            }
            return this.e;
        }
        this.e = 2;
        return this.e;
    }

    public final synchronized String b() {
        if (this.b == null) {
            e();
        }
        return this.b;
    }

    public final synchronized String c() {
        if (this.c == null) {
            e();
        }
        return this.c;
    }

    public final synchronized int d() {
        if (this.d == 0) {
            PackageInfo a2 = a("com.google.android.gms");
            if (a2 != null) {
                this.d = a2.versionCode;
            }
        }
        return this.d;
    }
}
