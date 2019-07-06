package io.branch.referral;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: SystemObserver */
class aa {
    static String a;
    int b = 0;
    private Context c;
    private boolean d;

    /* compiled from: SystemObserver */
    interface a {
        void e();
    }

    /* compiled from: SystemObserver */
    private class b extends BranchAsyncTask<Void, Void, Void> {
        private final a b;

        public b(a aVar) {
            this.b = aVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Thread(new Runnable() {
                public void run() {
                    Process.setThreadPriority(-19);
                    Object a2 = aa.this.p();
                    aa.this.a(a2);
                    aa.this.b(a2);
                    countDownLatch.countDown();
                }
            }).start();
            try {
                countDownLatch.await(1500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                com.google.a.a.a.a.a.a.a(e);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            if (this.b != null) {
                this.b.e();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public String j() {
        return "Android";
    }

    aa(Context context) {
        this.c = context;
        this.d = true;
    }

    /* access modifiers changed from: 0000 */
    public String a(boolean z) {
        if (this.c == null) {
            return "bnc_no_value";
        }
        String str = null;
        if (!z && !Branch.a) {
            str = Secure.getString(this.c.getContentResolver(), "android_id");
        }
        if (str == null) {
            str = UUID.randomUUID().toString();
            this.d = false;
        }
        return str;
    }

    /* access modifiers changed from: 0000 */
    public boolean a() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public String b() {
        String str = "";
        try {
            return this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 0).packageName;
        } catch (NameNotFoundException e) {
            com.google.a.a.a.a.a.a.a(e);
            return str;
        }
    }

    /* access modifiers changed from: 0000 */
    public String c() {
        return a(this.c.getPackageName());
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x004e A[SYNTHETIC, Splitter:B:25:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0053 A[Catch:{ IOException -> 0x0056 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x005a A[SYNTHETIC, Splitter:B:35:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x005f A[Catch:{ IOException -> 0x0062 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r0 = "bnc_no_value"
            boolean r1 = r4.d()
            if (r1 != 0) goto L_0x0062
            r1 = 0
            android.content.Context r2 = r4.c     // Catch:{ Exception -> 0x0057, all -> 0x004a }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ Exception -> 0x0057, all -> 0x004a }
            r3 = 0
            android.content.pm.ApplicationInfo r5 = r2.getApplicationInfo(r5, r3)     // Catch:{ Exception -> 0x0057, all -> 0x004a }
            java.lang.String r5 = r5.publicSourceDir     // Catch:{ Exception -> 0x0057, all -> 0x004a }
            java.util.jar.JarFile r2 = new java.util.jar.JarFile     // Catch:{ Exception -> 0x0057, all -> 0x004a }
            r2.<init>(r5)     // Catch:{ Exception -> 0x0057, all -> 0x004a }
            java.lang.String r5 = "AndroidManifest.xml"
            java.util.zip.ZipEntry r5 = r2.getEntry(r5)     // Catch:{ Exception -> 0x0058, all -> 0x0048 }
            java.io.InputStream r5 = r2.getInputStream(r5)     // Catch:{ Exception -> 0x0058, all -> 0x0048 }
            int r1 = r5.available()     // Catch:{ Exception -> 0x0046, all -> 0x0043 }
            byte[] r1 = new byte[r1]     // Catch:{ Exception -> 0x0046, all -> 0x0043 }
            r5.read(r1)     // Catch:{ Exception -> 0x0046, all -> 0x0043 }
            io.branch.referral.a r3 = new io.branch.referral.a     // Catch:{ Exception -> 0x0046, all -> 0x0043 }
            r3.<init>()     // Catch:{ Exception -> 0x0046, all -> 0x0043 }
            java.lang.String r1 = r3.a(r1)     // Catch:{ Exception -> 0x0046, all -> 0x0043 }
            if (r5 == 0) goto L_0x003c
            r5.close()     // Catch:{ IOException -> 0x0041 }
        L_0x003c:
            if (r2 == 0) goto L_0x0041
            r2.close()     // Catch:{ IOException -> 0x0041 }
        L_0x0041:
            r0 = r1
            goto L_0x0062
        L_0x0043:
            r0 = move-exception
            r1 = r5
            goto L_0x004c
        L_0x0046:
            r1 = r5
            goto L_0x0058
        L_0x0048:
            r0 = move-exception
            goto L_0x004c
        L_0x004a:
            r0 = move-exception
            r2 = r1
        L_0x004c:
            if (r1 == 0) goto L_0x0051
            r1.close()     // Catch:{ IOException -> 0x0056 }
        L_0x0051:
            if (r2 == 0) goto L_0x0056
            r2.close()     // Catch:{ IOException -> 0x0056 }
        L_0x0056:
            throw r0
        L_0x0057:
            r2 = r1
        L_0x0058:
            if (r1 == 0) goto L_0x005d
            r1.close()     // Catch:{ IOException -> 0x0062 }
        L_0x005d:
            if (r2 == 0) goto L_0x0062
            r2.close()     // Catch:{ IOException -> 0x0062 }
        L_0x0062:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.aa.a(java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: 0000 */
    public boolean d() {
        ActivityManager activityManager = (ActivityManager) this.c.getSystemService("activity");
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.lowMemory;
    }

    /* access modifiers changed from: 0000 */
    public String e() {
        try {
            PackageInfo packageInfo = this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 0);
            return packageInfo.versionName != null ? packageInfo.versionName : "bnc_no_value";
        } catch (NameNotFoundException unused) {
            return "bnc_no_value";
        }
    }

    /* access modifiers changed from: 0000 */
    public String f() {
        return Build.MANUFACTURER;
    }

    /* access modifiers changed from: 0000 */
    public String g() {
        return Build.MODEL;
    }

    /* access modifiers changed from: 0000 */
    public String h() {
        return Locale.getDefault() != null ? Locale.getDefault().getCountry() : "";
    }

    /* access modifiers changed from: 0000 */
    public String i() {
        return Locale.getDefault() != null ? Locale.getDefault().getLanguage() : "";
    }

    /* access modifiers changed from: 0000 */
    public int k() {
        return VERSION.SDK_INT;
    }

    /* access modifiers changed from: 0000 */
    public DisplayMetrics l() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.c.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public boolean m() {
        boolean z = false;
        if (this.c.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return false;
        }
        NetworkInfo networkInfo = ((ConnectivityManager) this.c.getSystemService("connectivity")).getNetworkInfo(1);
        if (networkInfo != null && networkInfo.isConnected()) {
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public Object p() {
        try {
            return Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{this.c});
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public String a(Object obj) {
        try {
            a = (String) obj.getClass().getMethod("getId", new Class[0]).invoke(obj, new Object[0]);
        } catch (Exception unused) {
        }
        return a;
    }

    /* access modifiers changed from: private */
    public int b(Object obj) {
        try {
            this.b = ((Boolean) obj.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(obj, new Object[0])).booleanValue() ? 1 : 0;
        } catch (Exception unused) {
        }
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(a aVar) {
        if (!TextUtils.isEmpty(a)) {
            return false;
        }
        new b(aVar).executeTask(new Void[0]);
        return true;
    }

    static String n() {
        String str = "";
        try {
            for (NetworkInterface inetAddresses : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                Iterator it = Collections.list(inetAddresses.getInetAddresses()).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    InetAddress inetAddress = (InetAddress) it.next();
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        if (hostAddress.indexOf(58) < 0) {
                            str = hostAddress;
                            break;
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return str;
    }

    /* access modifiers changed from: 0000 */
    public String o() {
        switch (((UiModeManager) this.c.getSystemService("uimode")).getCurrentModeType()) {
            case 0:
                return "UI_MODE_TYPE_UNDEFINED";
            case 1:
                return "UI_MODE_TYPE_NORMAL";
            case 2:
                return "UI_MODE_TYPE_DESK";
            case 3:
                return "UI_MODE_TYPE_CAR";
            case 4:
                return "UI_MODE_TYPE_TELEVISION";
            case 5:
                return "UI_MODE_TYPE_APPLIANCE";
            case 6:
                return "UI_MODE_TYPE_WATCH";
            default:
                return "UI_MODE_TYPE_UNDEFINED";
        }
    }
}
