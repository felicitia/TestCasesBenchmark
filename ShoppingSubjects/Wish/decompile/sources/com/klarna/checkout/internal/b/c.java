package com.klarna.checkout.internal.b;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Environment;
import android.preference.PreferenceManager;
import com.klarna.checkout.internal.g;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.json.JSONObject;

public final class c {
    public final a a = new a(this);
    public final b b = new b();
    public String c;
    public String d;

    public c(String str) {
        this.c = str;
    }

    public static boolean a(Context context) {
        if (VERSION.SDK_INT >= 23 && context.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            return false;
        }
        return "mounted".equals(Environment.getExternalStorageState());
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0066 A[SYNTHETIC, Splitter:B:23:0x0066] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x006e A[SYNTHETIC, Splitter:B:30:0x006e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String a() {
        /*
            r5 = this;
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x006a, all -> 0x0063 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x006a, all -> 0x0063 }
            r2.<init>()     // Catch:{ Exception -> 0x006a, all -> 0x0063 }
            java.lang.String r3 = "EXTERNAL_STORAGE"
            java.lang.String r3 = java.lang.System.getenv(r3)     // Catch:{ Exception -> 0x006a, all -> 0x0063 }
            r2.append(r3)     // Catch:{ Exception -> 0x006a, all -> 0x0063 }
            java.lang.String r3 = "/"
            r2.append(r3)     // Catch:{ Exception -> 0x006a, all -> 0x0063 }
            java.lang.String r3 = r5.c     // Catch:{ Exception -> 0x006a, all -> 0x0063 }
            r2.append(r3)     // Catch:{ Exception -> 0x006a, all -> 0x0063 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x006a, all -> 0x0063 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x006a, all -> 0x0063 }
            boolean r2 = r1.exists()     // Catch:{ Exception -> 0x006a, all -> 0x0063 }
            if (r2 != 0) goto L_0x002b
            java.lang.String r1 = "-1"
            return r1
        L_0x002b:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x006a, all -> 0x0063 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x006a, all -> 0x0063 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x006a, all -> 0x0063 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x006a, all -> 0x0063 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x006a, all -> 0x0063 }
            r1.<init>(r3)     // Catch:{ Exception -> 0x006a, all -> 0x0063 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0061, all -> 0x005c }
            r0.<init>()     // Catch:{ Exception -> 0x0061, all -> 0x005c }
        L_0x003f:
            java.lang.String r2 = r1.readLine()     // Catch:{ Exception -> 0x0061, all -> 0x005c }
            if (r2 == 0) goto L_0x004e
            r0.append(r2)     // Catch:{ Exception -> 0x0061, all -> 0x005c }
            java.lang.String r2 = "\n"
            r0.append(r2)     // Catch:{ Exception -> 0x0061, all -> 0x005c }
            goto L_0x003f
        L_0x004e:
            com.klarna.checkout.internal.b.b r2 = r5.b     // Catch:{ Exception -> 0x0061, all -> 0x005c }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0061, all -> 0x005c }
            java.lang.String r0 = r2.b(r0)     // Catch:{ Exception -> 0x0061, all -> 0x005c }
            r1.close()     // Catch:{ IOException -> 0x005b }
        L_0x005b:
            return r0
        L_0x005c:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x0064
        L_0x0061:
            r0 = r1
            goto L_0x006a
        L_0x0063:
            r1 = move-exception
        L_0x0064:
            if (r0 == 0) goto L_0x0069
            r0.close()     // Catch:{ IOException -> 0x0069 }
        L_0x0069:
            throw r1
        L_0x006a:
            java.lang.String r1 = "-1"
            if (r0 == 0) goto L_0x0071
            r0.close()     // Catch:{ IOException -> 0x0071 }
        L_0x0071:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.klarna.checkout.internal.b.c.a():java.lang.String");
    }

    public final void a(Context context, g gVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("cookies", this.a.a());
            jSONObject.put("storage_set", this.d);
            if (!a(context)) {
                try {
                    String a2 = com.klarna.checkout.internal.c.c.a(jSONObject.toString());
                    Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
                    StringBuilder sb = new StringBuilder("com.klarna.data");
                    sb.append(this.c);
                    edit.putString(sb.toString(), this.b.a(a2)).commit();
                    gVar.a("1");
                } catch (Exception unused) {
                    gVar.a("-1");
                }
            } else {
                String a3 = this.b.a(com.klarna.checkout.internal.c.c.a(jSONObject.toString()));
                try {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(System.getenv("EXTERNAL_STORAGE"));
                    sb2.append("/");
                    sb2.append(this.c);
                    File file = new File(sb2.toString());
                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    outputStreamWriter.append(a3);
                    outputStreamWriter.close();
                    fileOutputStream.close();
                    gVar.a("1");
                } catch (IOException unused2) {
                    gVar.a("-1");
                }
            }
        } catch (Exception unused3) {
            gVar.a("-1");
        }
    }
}
