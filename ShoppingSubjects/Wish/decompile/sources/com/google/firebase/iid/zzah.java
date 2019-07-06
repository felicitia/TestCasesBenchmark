package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.firebase.FirebaseApp;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public final class zzah {
    private String zzcb;
    private String zzcc;
    private int zzcd;
    private int zzce = 0;
    private final Context zzz;

    public zzah(Context context) {
        this.zzz = context;
    }

    public static String zza(FirebaseApp firebaseApp) {
        String gcmSenderId = firebaseApp.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            return gcmSenderId;
        }
        String applicationId = firebaseApp.getOptions().getApplicationId();
        if (!applicationId.startsWith("1:")) {
            return applicationId;
        }
        String[] split = applicationId.split(":");
        if (split.length < 2) {
            return null;
        }
        String str = split[1];
        if (str.isEmpty()) {
            return null;
        }
        return str;
    }

    public static String zza(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) ((digest[0] & 15) + 112);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException unused) {
            Log.w("FirebaseInstanceId", "Unexpected error, device missing required algorithms");
            return null;
        }
    }

    private final synchronized void zzaa() {
        PackageInfo zze = zze(this.zzz.getPackageName());
        if (zze != null) {
            this.zzcb = Integer.toString(zze.versionCode);
            this.zzcc = zze.versionName;
        }
    }

    private final PackageInfo zze(String str) {
        try {
            return this.zzz.getPackageManager().getPackageInfo(str, 0);
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
            sb.append("Failed to find package ");
            sb.append(valueOf);
            Log.w("FirebaseInstanceId", sb.toString());
            return null;
        }
    }

    public final synchronized int zzw() {
        if (this.zzce != 0) {
            return this.zzce;
        }
        PackageManager packageManager = this.zzz.getPackageManager();
        if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", "com.google.android.gms") == -1) {
            Log.e("FirebaseInstanceId", "Google Play services missing or without correct permission.");
            return 0;
        }
        if (!PlatformVersion.isAtLeastO()) {
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            List queryIntentServices = packageManager.queryIntentServices(intent, 0);
            if (queryIntentServices != null && queryIntentServices.size() > 0) {
                this.zzce = 1;
                return this.zzce;
            }
        }
        Intent intent2 = new Intent("com.google.iid.TOKEN_REQUEST");
        intent2.setPackage("com.google.android.gms");
        List queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent2, 0);
        if (queryBroadcastReceivers == null || queryBroadcastReceivers.size() <= 0) {
            Log.w("FirebaseInstanceId", "Failed to resolve IID implementation package, falling back");
            if (PlatformVersion.isAtLeastO()) {
                this.zzce = 2;
            } else {
                this.zzce = 1;
            }
            return this.zzce;
        }
        this.zzce = 2;
        return this.zzce;
    }

    public final synchronized String zzx() {
        if (this.zzcb == null) {
            zzaa();
        }
        return this.zzcb;
    }

    public final synchronized String zzy() {
        if (this.zzcc == null) {
            zzaa();
        }
        return this.zzcc;
    }

    public final synchronized int zzz() {
        if (this.zzcd == 0) {
            PackageInfo zze = zze("com.google.android.gms");
            if (zze != null) {
                this.zzcd = zze.versionCode;
            }
        }
        return this.zzcd;
    }
}
