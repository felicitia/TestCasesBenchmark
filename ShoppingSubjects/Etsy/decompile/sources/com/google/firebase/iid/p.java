package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.GuardedBy;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Map;

final class p {
    private final SharedPreferences a;
    private final Context b;
    private final ah c;
    @GuardedBy("this")
    private final Map<String, ai> d;

    public p(Context context) {
        this(context, new ah());
    }

    private p(Context context, ah ahVar) {
        this.d = new ArrayMap();
        this.b = context;
        this.a = context.getSharedPreferences("com.google.android.gms.appid", 0);
        this.c = ahVar;
        File file = new File(ContextCompat.getNoBackupFilesDir(this.b), "com.google.android.gms.appid-no-backup");
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !c()) {
                    Log.i("FirebaseInstanceId", "App restored, clearing state");
                    b();
                    FirebaseInstanceId.a().g();
                }
            } catch (IOException e) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String str = "FirebaseInstanceId";
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

    private static String b(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(4 + String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append(str);
        sb.append("|T|");
        sb.append(str2);
        sb.append("|");
        sb.append(str3);
        return sb.toString();
    }

    private final synchronized boolean c() {
        return this.a.getAll().isEmpty();
    }

    public final synchronized q a(String str, String str2, String str3) {
        return q.a(this.a.getString(b(str, str2, str3), null));
    }

    public final synchronized String a() {
        return this.a.getString("topic_operaion_queue", "");
    }

    public final synchronized void a(String str) {
        this.a.edit().putString("topic_operaion_queue", str).apply();
    }

    public final synchronized void a(String str, String str2, String str3, String str4, String str5) {
        String a2 = q.a(str4, str5, System.currentTimeMillis());
        if (a2 != null) {
            Editor edit = this.a.edit();
            edit.putString(b(str, str2, str3), a2);
            edit.commit();
        }
    }

    public final synchronized ai b(String str) {
        ai aiVar;
        ai aiVar2 = (ai) this.d.get(str);
        if (aiVar2 != null) {
            return aiVar2;
        }
        try {
            aiVar = this.c.a(this.b, str);
        } catch (zzu unused) {
            Log.w("FirebaseInstanceId", "Stored data is corrupt, generating new identity");
            FirebaseInstanceId.a().g();
            aiVar = this.c.b(this.b, str);
        }
        this.d.put(str, aiVar);
        return aiVar;
    }

    public final synchronized void b() {
        this.d.clear();
        ah.a(this.b);
        this.a.edit().clear().commit();
    }

    public final synchronized void c(String str) {
        String concat = String.valueOf(str).concat("|T|");
        Editor edit = this.a.edit();
        for (String str2 : this.a.getAll().keySet()) {
            if (str2.startsWith(concat)) {
                edit.remove(str2);
            }
        }
        edit.commit();
    }
}
