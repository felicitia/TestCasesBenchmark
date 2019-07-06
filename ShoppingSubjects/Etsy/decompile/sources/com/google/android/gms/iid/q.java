package com.google.android.gms.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.internal.gcm.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

final class q {
    q() {
    }

    @Nullable
    private static r a(SharedPreferences sharedPreferences, String str) throws zzp {
        r rVar = null;
        String string = sharedPreferences.getString(k.a(str, "|P|"), null);
        String string2 = sharedPreferences.getString(k.a(str, "|K|"), null);
        if (string != null) {
            if (string2 == null) {
                return null;
            }
            rVar = new r(a(string, string2), b(sharedPreferences, str));
        }
        return rVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0041, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0045, code lost:
        a(r5, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0048, code lost:
        throw r1;
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.iid.r a(java.io.File r5) throws com.google.android.gms.iid.zzp, java.io.IOException {
        /*
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r5)
            r5 = 0
            java.util.Properties r1 = new java.util.Properties     // Catch:{ Throwable -> 0x0043 }
            r1.<init>()     // Catch:{ Throwable -> 0x0043 }
            r1.load(r0)     // Catch:{ Throwable -> 0x0043 }
            java.lang.String r2 = "pub"
            java.lang.String r2 = r1.getProperty(r2)     // Catch:{ Throwable -> 0x0043 }
            java.lang.String r3 = "pri"
            java.lang.String r3 = r1.getProperty(r3)     // Catch:{ Throwable -> 0x0043 }
            if (r2 == 0) goto L_0x003d
            if (r3 != 0) goto L_0x001f
            goto L_0x003d
        L_0x001f:
            java.security.KeyPair r2 = a(r2, r3)     // Catch:{ Throwable -> 0x0043 }
            java.lang.String r3 = "cre"
            java.lang.String r1 = r1.getProperty(r3)     // Catch:{ NumberFormatException -> 0x0036 }
            long r3 = java.lang.Long.parseLong(r1)     // Catch:{ NumberFormatException -> 0x0036 }
            com.google.android.gms.iid.r r1 = new com.google.android.gms.iid.r     // Catch:{ Throwable -> 0x0043 }
            r1.<init>(r2, r3)     // Catch:{ Throwable -> 0x0043 }
            a(r5, r0)
            return r1
        L_0x0036:
            r1 = move-exception
            com.google.android.gms.iid.zzp r2 = new com.google.android.gms.iid.zzp     // Catch:{ Throwable -> 0x0043 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x0043 }
            throw r2     // Catch:{ Throwable -> 0x0043 }
        L_0x003d:
            a(r5, r0)
            return r5
        L_0x0041:
            r1 = move-exception
            goto L_0x0045
        L_0x0043:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0041 }
        L_0x0045:
            a(r5, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.iid.q.a(java.io.File):com.google.android.gms.iid.r");
    }

    private static KeyPair a(String str, String str2) throws zzp {
        try {
            byte[] decode = Base64.decode(str, 8);
            byte[] decode2 = Base64.decode(str2, 8);
            try {
                KeyFactory instance = KeyFactory.getInstance("RSA");
                return new KeyPair(instance.generatePublic(new X509EncodedKeySpec(decode)), instance.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(19 + String.valueOf(valueOf).length());
                sb.append("Invalid key stored ");
                sb.append(valueOf);
                Log.w("InstanceID", sb.toString());
                throw new zzp(e);
            }
        } catch (IllegalArgumentException e2) {
            throw new zzp(e2);
        }
    }

    static void a(Context context) {
        File[] listFiles;
        for (File file : b(context).listFiles()) {
            if (file.getName().startsWith("com.google.InstanceId")) {
                file.delete();
            }
        }
    }

    private static void a(Context context, String str, r rVar) {
        FileOutputStream fileOutputStream;
        try {
            if (Log.isLoggable("InstanceID", 3)) {
                Log.d("InstanceID", "Writing key to properties file");
            }
            File f = f(context, str);
            f.createNewFile();
            Properties properties = new Properties();
            properties.setProperty("pub", rVar.b());
            properties.setProperty("pri", rVar.c());
            properties.setProperty("cre", String.valueOf(rVar.b));
            fileOutputStream = new FileOutputStream(f);
            properties.store(fileOutputStream, null);
            a((Throwable) null, fileOutputStream);
        } catch (IOException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(21 + String.valueOf(valueOf).length());
            sb.append("Failed to write key: ");
            sb.append(valueOf);
            Log.w("InstanceID", sb.toString());
        } catch (Throwable th) {
            a(r3, fileOutputStream);
            throw th;
        }
    }

    private static /* synthetic */ void a(Throwable th, FileInputStream fileInputStream) {
        if (th != null) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                b.a(th, th2);
            }
        } else {
            fileInputStream.close();
        }
    }

    private static /* synthetic */ void a(Throwable th, FileOutputStream fileOutputStream) {
        if (th != null) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                b.a(th, th2);
            }
        } else {
            fileOutputStream.close();
        }
    }

    private static long b(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(k.a(str, "cre"), null);
        if (string != null) {
            try {
                return Long.parseLong(string);
            } catch (NumberFormatException unused) {
            }
        }
        return 0;
    }

    private static File b(Context context) {
        File noBackupFilesDir = ContextCompat.getNoBackupFilesDir(context);
        if (noBackupFilesDir != null && noBackupFilesDir.isDirectory()) {
            return noBackupFilesDir;
        }
        Log.w("InstanceID", "noBackupFilesDir doesn't exist, using regular files directory instead");
        return context.getFilesDir();
    }

    private final void b(Context context, String str, r rVar) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.appid", 0);
        try {
            if (rVar.equals(a(sharedPreferences, str))) {
                return;
            }
        } catch (zzp unused) {
        }
        if (Log.isLoggable("InstanceID", 3)) {
            Log.d("InstanceID", "Writing key to shared preferences");
        }
        Editor edit = sharedPreferences.edit();
        edit.putString(k.a(str, "|P|"), rVar.b());
        edit.putString(k.a(str, "|K|"), rVar.c());
        edit.putString(k.a(str, "cre"), String.valueOf(rVar.b));
        edit.commit();
    }

    static void c(Context context, String str) {
        File f = f(context, str);
        if (f.exists()) {
            f.delete();
        }
    }

    @Nullable
    private final r d(Context context, String str) throws zzp {
        try {
            r e = e(context, str);
            if (e != null) {
                b(context, str, e);
                return e;
            }
            e = null;
            try {
                r a = a(context.getSharedPreferences("com.google.android.gms.appid", 0), str);
                if (a != null) {
                    a(context, str, a);
                    return a;
                }
            } catch (zzp e2) {
                e = e2;
            }
            if (e == null) {
                return null;
            }
            throw e;
        } catch (zzp e3) {
            e = e3;
        }
    }

    @Nullable
    private final r e(Context context, String str) throws zzp {
        File f = f(context, str);
        if (!f.exists()) {
            return null;
        }
        try {
            return a(f);
        } catch (IOException e) {
            if (Log.isLoggable("InstanceID", 3)) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(40 + String.valueOf(valueOf).length());
                sb.append("Failed to read key from file, retrying: ");
                sb.append(valueOf);
                Log.d("InstanceID", sb.toString());
            }
            try {
                return a(f);
            } catch (IOException e2) {
                String valueOf2 = String.valueOf(e2);
                StringBuilder sb2 = new StringBuilder(45 + String.valueOf(valueOf2).length());
                sb2.append("IID file exists, but failed to read from it: ");
                sb2.append(valueOf2);
                Log.w("InstanceID", sb2.toString());
                throw new zzp(e2);
            }
        }
    }

    private static File f(Context context, String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "com.google.InstanceId.properties";
        } else {
            try {
                String encodeToString = Base64.encodeToString(str.getBytes("UTF-8"), 11);
                StringBuilder sb = new StringBuilder(33 + String.valueOf(encodeToString).length());
                sb.append("com.google.InstanceId_");
                sb.append(encodeToString);
                sb.append(".properties");
                str2 = sb.toString();
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
        return new File(b(context), str2);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final r a(Context context, String str) throws zzp {
        r d = d(context, str);
        return d != null ? d : b(context, str);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final r b(Context context, String str) {
        r rVar = new r(l.a(), System.currentTimeMillis());
        try {
            r d = d(context, str);
            if (d != null) {
                if (Log.isLoggable("InstanceID", 3)) {
                    Log.d("InstanceID", "Loaded key after generating new one, using loaded one");
                }
                return d;
            }
        } catch (zzp unused) {
        }
        if (Log.isLoggable("InstanceID", 3)) {
            Log.d("InstanceID", "Generated new key");
        }
        a(context, str, rVar);
        b(context, str, rVar);
        return rVar;
    }
}
