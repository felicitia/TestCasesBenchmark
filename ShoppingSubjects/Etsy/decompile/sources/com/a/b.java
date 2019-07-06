package com.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/* compiled from: Correlator */
final class b implements a {
    private Context a;
    private a b;
    private SharedPreferences c;

    /* compiled from: Correlator */
    interface a {
        void a(boolean z);
    }

    b(Context context) {
        this.a = context;
        this.c = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void a(String str, String str2, a aVar) {
        this.b = aVar;
        if (System.currentTimeMillis() - this.c.getLong("bitly.last.correlation", 0) > 1209600000) {
            Log.d("BitlySDK", "Bitly SDK correlator initializing");
            g.a(this.a, a(str, str2), this);
            this.c.edit().putLong("bitly.last.correlation", System.currentTimeMillis()).apply();
        } else {
            Log.d("BitlySDK", "Bitly SDK correlator not run");
            if (aVar != null) {
                aVar.a(false);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x010e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.net.Uri a(java.lang.String r12, java.lang.String r13) {
        /*
            r11 = this;
            android.content.Context r0 = r11.a
            java.lang.String r1 = "window"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            android.view.Display r0 = r0.getDefaultDisplay()
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 0
            r3 = 17
            if (r1 < r3) goto L_0x0028
            android.util.DisplayMetrics r1 = new android.util.DisplayMetrics
            r1.<init>()
            r0.getRealMetrics(r1)
            int r0 = r1.widthPixels
            int r3 = r1.heightPixels
            float r1 = r1.density
            r10 = r3
            r3 = r0
            r0 = r1
            r1 = r10
            goto L_0x006f
        L_0x0028:
            java.lang.Class<android.view.Display> r1 = android.view.Display.class
            java.lang.String r3 = "getRawHeight"
            java.lang.Class[] r4 = new java.lang.Class[r2]     // Catch:{ Exception -> 0x0064 }
            java.lang.reflect.Method r1 = r1.getMethod(r3, r4)     // Catch:{ Exception -> 0x0064 }
            java.lang.Class<android.view.Display> r3 = android.view.Display.class
            java.lang.String r4 = "getRawWidth"
            java.lang.Class[] r5 = new java.lang.Class[r2]     // Catch:{ Exception -> 0x0064 }
            java.lang.reflect.Method r3 = r3.getMethod(r4, r5)     // Catch:{ Exception -> 0x0064 }
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x0064 }
            java.lang.Object r3 = r3.invoke(r0, r4)     // Catch:{ Exception -> 0x0064 }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ Exception -> 0x0064 }
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x0064 }
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x0061 }
            java.lang.Object r1 = r1.invoke(r0, r4)     // Catch:{ Exception -> 0x0061 }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ Exception -> 0x0061 }
            int r1 = r1.intValue()     // Catch:{ Exception -> 0x0061 }
            android.util.DisplayMetrics r4 = new android.util.DisplayMetrics     // Catch:{ Exception -> 0x005f }
            r4.<init>()     // Catch:{ Exception -> 0x005f }
            r0.getMetrics(r4)     // Catch:{ Exception -> 0x005f }
            float r0 = r4.density     // Catch:{ Exception -> 0x005f }
            goto L_0x006f
        L_0x005f:
            r0 = move-exception
            goto L_0x0067
        L_0x0061:
            r0 = move-exception
            r1 = r2
            goto L_0x0067
        L_0x0064:
            r0 = move-exception
            r1 = r2
            r3 = r1
        L_0x0067:
            java.lang.String r4 = "BitlySDK"
            java.lang.String r5 = "Bitly SDK failed to retrieve screen dimensions"
            android.util.Log.d(r4, r5, r0)
            r0 = 0
        L_0x006f:
            java.lang.String r4 = android.os.Build.VERSION.RELEASE
            java.lang.String r5 = "\\."
            java.lang.String[] r4 = r4.split(r5)
            r5 = r4[r2]
            r6 = 1
            int r7 = r4.length
            if (r7 <= r6) goto L_0x0080
            r7 = r4[r6]
            goto L_0x0082
        L_0x0080:
            java.lang.String r7 = "0"
        L_0x0082:
            r8 = 2
            int r9 = r4.length
            if (r9 <= r8) goto L_0x0089
            r4 = r4[r8]
            goto L_0x008b
        L_0x0089:
            java.lang.String r4 = "0"
        L_0x008b:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = com.a.a.d()
            r8.append(r9)
            java.lang.String r9 = "v3/mobile/register"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            android.net.Uri r8 = android.net.Uri.parse(r8)
            android.net.Uri$Builder r8 = r8.buildUpon()
            java.lang.String r9 = "app_id"
            android.net.Uri$Builder r12 = r8.appendQueryParameter(r9, r12)
            java.lang.String r8 = "device_id"
            android.net.Uri$Builder r12 = r12.appendQueryParameter(r8, r13)
            java.lang.String r13 = "device_id_type"
            java.lang.String r8 = "android"
            android.net.Uri$Builder r12 = r12.appendQueryParameter(r13, r8)
            java.lang.String r13 = "dsw"
            java.lang.String r3 = java.lang.String.valueOf(r3)
            android.net.Uri$Builder r12 = r12.appendQueryParameter(r13, r3)
            java.lang.String r13 = "dsh"
            java.lang.String r1 = java.lang.String.valueOf(r1)
            android.net.Uri$Builder r12 = r12.appendQueryParameter(r13, r1)
            java.lang.String r13 = "dsos"
            java.lang.String r1 = "android"
            android.net.Uri$Builder r12 = r12.appendQueryParameter(r13, r1)
            java.lang.String r13 = "dsosmaj"
            android.net.Uri$Builder r12 = r12.appendQueryParameter(r13, r5)
            java.lang.String r13 = "dsosmin"
            android.net.Uri$Builder r12 = r12.appendQueryParameter(r13, r7)
            java.lang.String r13 = "dsosp"
            android.net.Uri$Builder r12 = r12.appendQueryParameter(r13, r4)
            java.lang.String r13 = "dsb"
            java.lang.String r1 = android.os.Build.BRAND
            android.net.Uri$Builder r12 = r12.appendQueryParameter(r13, r1)
            java.lang.String r13 = "dsm"
            java.lang.String r1 = android.os.Build.MODEL
            android.net.Uri$Builder r12 = r12.appendQueryParameter(r13, r1)
            java.lang.String r13 = "dsd"
            java.lang.String r0 = java.lang.String.valueOf(r0)
            android.net.Uri$Builder r12 = r12.appendQueryParameter(r13, r0)
            android.content.SharedPreferences r13 = r11.c
            java.lang.String r0 = "bitly.has.launched"
            boolean r13 = r13.getBoolean(r0, r2)
            if (r13 != 0) goto L_0x0124
            java.lang.String r13 = "il"
            java.lang.String r0 = "true"
            r12.appendQueryParameter(r13, r0)
            android.content.SharedPreferences r13 = r11.c
            android.content.SharedPreferences$Editor r13 = r13.edit()
            java.lang.String r0 = "bitly.has.launched"
            android.content.SharedPreferences$Editor r13 = r13.putBoolean(r0, r6)
            r13.apply()
        L_0x0124:
            android.net.Uri r12 = r12.build()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.b.a(java.lang.String, java.lang.String):android.net.Uri");
    }

    public void a() {
        Log.d("BitlySDK", "Bitly SDK correlator started");
    }

    public void b() {
        Log.d("BitlySDK", "Bitly SDK correlator completed");
        if (this.b != null) {
            this.b.a(true);
        }
    }
}
