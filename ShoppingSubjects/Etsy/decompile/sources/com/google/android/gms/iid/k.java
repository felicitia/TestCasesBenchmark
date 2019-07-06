package com.google.android.gms.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.GuardedBy;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public final class k {
    private SharedPreferences a;
    private Context b;
    private final q c;
    @GuardedBy("this")
    private final Map<String, r> d;

    public k(Context context) {
        this(context, new q());
    }

    @VisibleForTesting
    private k(Context context, q qVar) {
        this.d = new ArrayMap();
        this.b = context;
        this.a = context.getSharedPreferences("com.google.android.gms.appid", 0);
        this.c = qVar;
        File file = new File(ContextCompat.getNoBackupFilesDir(this.b), "com.google.android.gms.appid-no-backup");
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !a()) {
                    Log.i("InstanceID/Store", "App restored, clearing state");
                    InstanceIDListenerService.zzd(this.b, this);
                }
            } catch (IOException e) {
                if (Log.isLoggable("InstanceID/Store", 3)) {
                    String str = "InstanceID/Store";
                    String str2 = "Error creating file in no backup dir: ";
                    String valueOf = String.valueOf(e.getMessage());
                    Log.d(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                }
            }
        }
    }

    static String a(String str, String str2) {
        StringBuilder sb = new StringBuilder(3 + String.valueOf(str).length() + String.valueOf(str2).length());
        sb.append(str);
        sb.append("|S|");
        sb.append(str2);
        return sb.toString();
    }

    private static String c(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(4 + String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append(str);
        sb.append("|T|");
        sb.append(str2);
        sb.append("|");
        sb.append(str3);
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final synchronized String a(String str) {
        return this.a.getString(str, null);
    }

    public final synchronized String a(String str, String str2, String str3) {
        return this.a.getString(c(str, str2, str3), null);
    }

    public final synchronized void a(String str, String str2, String str3, String str4, String str5) {
        String c2 = c(str, str2, str3);
        Editor edit = this.a.edit();
        edit.putString(c2, str4);
        edit.putString("appVersion", str5);
        edit.putString("lastToken", Long.toString(System.currentTimeMillis() / 1000));
        edit.commit();
    }

    public final boolean a() {
        return this.a.getAll().isEmpty();
    }

    public final synchronized void b() {
        this.d.clear();
        q.a(this.b);
        this.a.edit().clear().commit();
    }

    public final synchronized void b(String str) {
        Editor edit = this.a.edit();
        for (String str2 : this.a.getAll().keySet()) {
            if (str2.startsWith(str)) {
                edit.remove(str2);
            }
        }
        edit.commit();
    }

    public final synchronized void b(String str, String str2, String str3) {
        String c2 = c(str, str2, str3);
        Editor edit = this.a.edit();
        edit.remove(c2);
        edit.commit();
    }

    public final synchronized r c(String str) {
        r rVar;
        r rVar2 = (r) this.d.get(str);
        if (rVar2 != null) {
            return rVar2;
        }
        try {
            rVar = this.c.a(this.b, str);
        } catch (zzp unused) {
            Log.w("InstanceID/Store", "Stored data is corrupt, generating new identity");
            InstanceIDListenerService.zzd(this.b, this);
            rVar = this.c.b(this.b, str);
        }
        this.d.put(str, rVar);
        return rVar;
    }

    /* access modifiers changed from: 0000 */
    public final void d(String str) {
        synchronized (this) {
            this.d.remove(str);
        }
        q.c(this.b, str);
        b(String.valueOf(str).concat("|"));
    }
}
