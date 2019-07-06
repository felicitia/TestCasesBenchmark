package com.pinterest.pinit;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Log;

/* compiled from: PinIt */
public class a {
    private static boolean a;
    private static String g;
    private String b;
    private Uri c;
    private String d;
    private String e;
    private b f;

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Context r6) {
        /*
            r5 = this;
            boolean r0 = a()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            com.pinterest.pinit.b r0 = r5.f
            if (r0 == 0) goto L_0x0010
            com.pinterest.pinit.b r0 = r5.f
            r0.a()
        L_0x0010:
            boolean r0 = c(r6)
            if (r0 != 0) goto L_0x0028
            com.pinterest.pinit.b r0 = r5.f
            if (r0 == 0) goto L_0x0024
            com.pinterest.pinit.b r0 = r5.f
            com.pinterest.pinit.exceptions.NotInstalledException r1 = new com.pinterest.pinit.exceptions.NotInstalledException
            r1.<init>()
            r0.a(r1)
        L_0x0024:
            b(r6)
            return
        L_0x0028:
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r1 = "com.pinterest.action.PIN_IT"
            r0.setAction(r1)
            java.lang.String r1 = r5.b
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0049
            java.lang.String r1 = r5.b
            int r1 = r1.length()
            if (r1 <= 0) goto L_0x0049
            java.lang.String r1 = "com.pinterest.EXTRA_IMAGE"
            java.lang.String r4 = r5.b
            r0.putExtra(r1, r4)
        L_0x0047:
            r1 = r2
            goto L_0x0056
        L_0x0049:
            android.net.Uri r1 = r5.c
            if (r1 == 0) goto L_0x0055
            java.lang.String r1 = "com.pinterest.EXTRA_URI"
            android.net.Uri r4 = r5.c
            r0.putExtra(r1, r4)
            goto L_0x0047
        L_0x0055:
            r1 = r3
        L_0x0056:
            if (r1 != 0) goto L_0x006e
            com.pinterest.pinit.b r6 = r5.f
            if (r6 == 0) goto L_0x0066
            com.pinterest.pinit.b r6 = r5.f
            com.pinterest.pinit.exceptions.ImageException r0 = new com.pinterest.pinit.exceptions.ImageException
            r0.<init>()
            r6.a(r0)
        L_0x0066:
            java.lang.String r6 = "imageUrl and/or imageUri cannot be null! Did you call setImageUrl(String) or setImageUri(Uri)?"
            java.lang.Object[] r0 = new java.lang.Object[r3]
            a(r6, r0)
            return
        L_0x006e:
            java.lang.String r1 = g
            if (r1 == 0) goto L_0x00da
            java.lang.String r1 = g
            int r1 = r1.length()
            if (r1 != 0) goto L_0x007b
            goto L_0x00da
        L_0x007b:
            java.lang.String r1 = "com.pinterest.EXTRA_PARTNER_ID"
            java.lang.String r4 = g
            r0.putExtra(r1, r4)
            java.lang.String r1 = r5.d
            if (r1 != 0) goto L_0x00a0
            android.net.Uri r1 = r5.c
            if (r1 != 0) goto L_0x00a7
            com.pinterest.pinit.b r6 = r5.f
            if (r6 == 0) goto L_0x0098
            com.pinterest.pinit.b r6 = r5.f
            com.pinterest.pinit.exceptions.SourceUrlException r0 = new com.pinterest.pinit.exceptions.SourceUrlException
            r0.<init>()
            r6.a(r0)
        L_0x0098:
            java.lang.String r6 = "url cannot be null! Did you call setUrl?"
            java.lang.Object[] r0 = new java.lang.Object[r3]
            a(r6, r0)
            return
        L_0x00a0:
            java.lang.String r1 = "com.pinterest.EXTRA_URL"
            java.lang.String r4 = r5.d
            r0.putExtra(r1, r4)
        L_0x00a7:
            java.lang.String r1 = r5.e
            if (r1 != 0) goto L_0x00b3
            java.lang.String r1 = "description is null. You can optionally set it with setDescription."
            java.lang.Object[] r4 = new java.lang.Object[r3]
            b(r1, r4)
            goto L_0x00ba
        L_0x00b3:
            java.lang.String r1 = "com.pinterest.EXTRA_DESCRIPTION"
            java.lang.String r4 = r5.e
            r0.putExtra(r1, r4)
        L_0x00ba:
            java.lang.String r1 = "com.pinterest.EXTRA_PARTNER_PACKAGE"
            java.lang.String r4 = r6.getPackageName()
            r0.putExtra(r1, r4)
            r6.startActivity(r0)     // Catch:{ Exception -> 0x00d0 }
            com.pinterest.pinit.b r6 = r5.f     // Catch:{ Exception -> 0x00d0 }
            if (r6 == 0) goto L_0x00d9
            com.pinterest.pinit.b r6 = r5.f     // Catch:{ Exception -> 0x00d0 }
            r6.a(r2)     // Catch:{ Exception -> 0x00d0 }
            goto L_0x00d9
        L_0x00d0:
            com.pinterest.pinit.b r6 = r5.f
            if (r6 == 0) goto L_0x00d9
            com.pinterest.pinit.b r6 = r5.f
            r6.a(r3)
        L_0x00d9:
            return
        L_0x00da:
            com.pinterest.pinit.b r6 = r5.f
            if (r6 == 0) goto L_0x00e8
            com.pinterest.pinit.b r6 = r5.f
            com.pinterest.pinit.exceptions.PartnerIdException r0 = new com.pinterest.pinit.exceptions.PartnerIdException
            r0.<init>()
            r6.a(r0)
        L_0x00e8:
            java.lang.String r6 = "partnerId cannot be null! Did you call setPartnerId?"
            java.lang.Object[] r0 = new java.lang.Object[r3]
            a(r6, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pinterest.pinit.a.a(android.content.Context):void");
    }

    private static void a(String str, Object... objArr) {
        if (a) {
            Log.e("Pinterest Pin It", String.format(str, objArr));
        }
    }

    private static void b(String str, Object... objArr) {
        if (a) {
            Log.i("Pinterest Pin It", String.format(str, objArr));
        }
    }

    private static boolean b(Context context) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=com.pinterest")));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean a() {
        return VERSION.SDK_INT >= 8;
    }

    private static boolean c(Context context) {
        boolean z;
        if (!a()) {
            return false;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.pinterest", 0);
            if (packageInfo != null) {
                z = packageInfo.versionCode >= 16;
                a("Pinterest versionCode:%s versionName:%s", Integer.valueOf(packageInfo.versionCode), packageInfo.versionName);
            } else {
                a("Pinterest app not installed!", new Object[0]);
                z = false;
            }
            if (!z) {
                a("Pinterest app version too low!", new Object[0]);
            }
        } catch (Exception e2) {
            if (a) {
                com.google.a.a.a.a.a.a.a(e2);
            }
            z = false;
        }
        return z;
    }

    public void b() {
        this.d = null;
        this.c = null;
        this.b = null;
        this.e = null;
        this.f = null;
    }

    public static boolean c() {
        return a;
    }

    public static void a(boolean z) {
        a = z;
    }

    public String d() {
        return this.b;
    }

    public void a(String str) {
        this.c = null;
        this.b = str;
    }

    public Uri e() {
        return this.c;
    }

    public void a(Uri uri) {
        this.b = null;
        this.c = uri;
    }

    public String f() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public String g() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }

    public b h() {
        return this.f;
    }

    public void a(b bVar) {
        this.f = bVar;
    }

    public static String i() {
        return g;
    }

    public static void d(String str) {
        g = str;
    }
}
