package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class acy {
    private static final String b = "acy";
    protected Context a;
    private ExecutorService c;
    private DexClassLoader d;
    private acl e;
    private byte[] f;
    private volatile AdvertisingIdClient g = null;
    private volatile boolean h = false;
    private Future i = null;
    private boolean j;
    /* access modifiers changed from: private */
    public volatile vy k = null;
    private Future l = null;
    private ace m;
    private boolean n = false;
    private boolean o = false;
    private Map<Pair<String, String>, aeg> p;
    private boolean q = false;
    /* access modifiers changed from: private */
    public boolean r;
    private boolean s;

    final class a extends BroadcastReceiver {
        private a() {
        }

        /* synthetic */ a(acy acy, ada ada) {
            this();
        }

        public final void onReceive(Context context, Intent intent) {
            if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                acy.this.r = true;
                return;
            }
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                acy.this.r = false;
            }
        }
    }

    private acy(Context context) {
        boolean z = true;
        this.r = true;
        this.s = false;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            z = false;
        }
        this.j = z;
        if (this.j) {
            context = applicationContext;
        }
        this.a = context;
        this.p = new HashMap();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(18:1|2|(1:4)|5|6|7|8|(1:10)(1:11)|12|(1:14)(1:15)|16|17|18|(2:20|(2:22|23))|24|25|26|(15:27|28|(2:30|(2:32|33))|34|(1:36)|37|38|39|40|41|42|43|(1:45)|46|47)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004b */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0054 A[Catch:{ zzcl -> 0x0150, zzcw -> 0x0157 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0086 A[Catch:{ all -> 0x011d, FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b0 A[Catch:{ all -> 0x011d, FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f8 A[Catch:{ zzcl -> 0x0150, zzcw -> 0x0157 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.acy a(android.content.Context r8, java.lang.String r9, java.lang.String r10, boolean r11) {
        /*
            com.google.android.gms.internal.ads.acy r0 = new com.google.android.gms.internal.ads.acy
            r0.<init>(r8)
            com.google.android.gms.internal.ads.ada r8 = new com.google.android.gms.internal.ads.ada     // Catch:{ zzcw -> 0x0157 }
            r8.<init>()     // Catch:{ zzcw -> 0x0157 }
            java.util.concurrent.ExecutorService r8 = java.util.concurrent.Executors.newCachedThreadPool(r8)     // Catch:{ zzcw -> 0x0157 }
            r0.c = r8     // Catch:{ zzcw -> 0x0157 }
            r0.h = r11     // Catch:{ zzcw -> 0x0157 }
            if (r11 == 0) goto L_0x0021
            java.util.concurrent.ExecutorService r8 = r0.c     // Catch:{ zzcw -> 0x0157 }
            com.google.android.gms.internal.ads.adb r11 = new com.google.android.gms.internal.ads.adb     // Catch:{ zzcw -> 0x0157 }
            r11.<init>(r0)     // Catch:{ zzcw -> 0x0157 }
            java.util.concurrent.Future r8 = r8.submit(r11)     // Catch:{ zzcw -> 0x0157 }
            r0.i = r8     // Catch:{ zzcw -> 0x0157 }
        L_0x0021:
            java.util.concurrent.ExecutorService r8 = r0.c     // Catch:{ zzcw -> 0x0157 }
            com.google.android.gms.internal.ads.add r11 = new com.google.android.gms.internal.ads.add     // Catch:{ zzcw -> 0x0157 }
            r11.<init>(r0)     // Catch:{ zzcw -> 0x0157 }
            r8.execute(r11)     // Catch:{ zzcw -> 0x0157 }
            r8 = 0
            r11 = 1
            com.google.android.gms.common.GoogleApiAvailabilityLight r1 = com.google.android.gms.common.GoogleApiAvailabilityLight.getInstance()     // Catch:{ Throwable -> 0x004b }
            android.content.Context r2 = r0.a     // Catch:{ Throwable -> 0x004b }
            int r2 = r1.getApkVersion(r2)     // Catch:{ Throwable -> 0x004b }
            if (r2 <= 0) goto L_0x003b
            r2 = r11
            goto L_0x003c
        L_0x003b:
            r2 = r8
        L_0x003c:
            r0.n = r2     // Catch:{ Throwable -> 0x004b }
            android.content.Context r2 = r0.a     // Catch:{ Throwable -> 0x004b }
            int r1 = r1.isGooglePlayServicesAvailable(r2)     // Catch:{ Throwable -> 0x004b }
            if (r1 != 0) goto L_0x0048
            r1 = r11
            goto L_0x0049
        L_0x0048:
            r1 = r8
        L_0x0049:
            r0.o = r1     // Catch:{ Throwable -> 0x004b }
        L_0x004b:
            r0.a(r8, r11)     // Catch:{ zzcw -> 0x0157 }
            boolean r1 = com.google.android.gms.internal.ads.adg.a()     // Catch:{ zzcw -> 0x0157 }
            if (r1 == 0) goto L_0x006e
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r1 = com.google.android.gms.internal.ads.akl.bM     // Catch:{ zzcw -> 0x0157 }
            com.google.android.gms.internal.ads.akj r2 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ zzcw -> 0x0157 }
            java.lang.Object r1 = r2.a(r1)     // Catch:{ zzcw -> 0x0157 }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ zzcw -> 0x0157 }
            boolean r1 = r1.booleanValue()     // Catch:{ zzcw -> 0x0157 }
            if (r1 == 0) goto L_0x006e
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch:{ zzcw -> 0x0157 }
            java.lang.String r9 = "Task Context initialization must not be called from the UI thread."
            r8.<init>(r9)     // Catch:{ zzcw -> 0x0157 }
            throw r8     // Catch:{ zzcw -> 0x0157 }
        L_0x006e:
            com.google.android.gms.internal.ads.acl r1 = new com.google.android.gms.internal.ads.acl     // Catch:{ zzcw -> 0x0157 }
            r2 = 0
            r1.<init>(r2)     // Catch:{ zzcw -> 0x0157 }
            r0.e = r1     // Catch:{ zzcw -> 0x0157 }
            com.google.android.gms.internal.ads.acl r1 = r0.e     // Catch:{ zzcl -> 0x0150 }
            byte[] r9 = r1.a(r9)     // Catch:{ zzcl -> 0x0150 }
            r0.f = r9     // Catch:{ zzcl -> 0x0150 }
            android.content.Context r9 = r0.a     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            java.io.File r9 = r9.getCacheDir()     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            if (r9 != 0) goto L_0x0096
            android.content.Context r9 = r0.a     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            java.lang.String r1 = "dex"
            java.io.File r9 = r9.getDir(r1, r8)     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            if (r9 != 0) goto L_0x0096
            com.google.android.gms.internal.ads.zzcw r8 = new com.google.android.gms.internal.ads.zzcw     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            r8.<init>()     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            throw r8     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
        L_0x0096:
            java.lang.String r1 = "1521499837408"
            java.io.File r3 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            java.lang.String r4 = "%s/%s.jar"
            r5 = 2
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            r6[r8] = r9     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            r6[r11] = r1     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            java.lang.String r4 = java.lang.String.format(r4, r6)     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            r3.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            boolean r4 = r3.exists()     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            if (r4 != 0) goto L_0x00c7
            com.google.android.gms.internal.ads.acl r4 = r0.e     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            byte[] r6 = r0.f     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            byte[] r10 = r4.a(r6, r10)     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            r3.createNewFile()     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            r4.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            int r6 = r10.length     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            r4.write(r10, r8, r6)     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            r4.close()     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
        L_0x00c7:
            r0.b(r9, r1)     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            dalvik.system.DexClassLoader r10 = new dalvik.system.DexClassLoader     // Catch:{ all -> 0x011d }
            java.lang.String r4 = r3.getAbsolutePath()     // Catch:{ all -> 0x011d }
            java.lang.String r6 = r9.getAbsolutePath()     // Catch:{ all -> 0x011d }
            android.content.Context r7 = r0.a     // Catch:{ all -> 0x011d }
            java.lang.ClassLoader r7 = r7.getClassLoader()     // Catch:{ all -> 0x011d }
            r10.<init>(r4, r6, r2, r7)     // Catch:{ all -> 0x011d }
            r0.d = r10     // Catch:{ all -> 0x011d }
            a(r3)     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            r0.a(r9, r1)     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            java.lang.String r10 = "%s/%s.dex"
            java.lang.Object[] r3 = new java.lang.Object[r5]     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            r3[r8] = r9     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            r3[r11] = r1     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            java.lang.String r8 = java.lang.String.format(r10, r3)     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            a(r8)     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            boolean r8 = r0.s     // Catch:{ zzcw -> 0x0157 }
            if (r8 != 0) goto L_0x0113
            android.content.IntentFilter r8 = new android.content.IntentFilter     // Catch:{ zzcw -> 0x0157 }
            r8.<init>()     // Catch:{ zzcw -> 0x0157 }
            java.lang.String r9 = "android.intent.action.USER_PRESENT"
            r8.addAction(r9)     // Catch:{ zzcw -> 0x0157 }
            java.lang.String r9 = "android.intent.action.SCREEN_OFF"
            r8.addAction(r9)     // Catch:{ zzcw -> 0x0157 }
            android.content.Context r9 = r0.a     // Catch:{ zzcw -> 0x0157 }
            com.google.android.gms.internal.ads.acy$a r10 = new com.google.android.gms.internal.ads.acy$a     // Catch:{ zzcw -> 0x0157 }
            r10.<init>(r0, r2)     // Catch:{ zzcw -> 0x0157 }
            r9.registerReceiver(r10, r8)     // Catch:{ zzcw -> 0x0157 }
            r0.s = r11     // Catch:{ zzcw -> 0x0157 }
        L_0x0113:
            com.google.android.gms.internal.ads.ace r8 = new com.google.android.gms.internal.ads.ace     // Catch:{ zzcw -> 0x0157 }
            r8.<init>(r0)     // Catch:{ zzcw -> 0x0157 }
            r0.m = r8     // Catch:{ zzcw -> 0x0157 }
            r0.q = r11     // Catch:{ zzcw -> 0x0157 }
            return r0
        L_0x011d:
            r10 = move-exception
            a(r3)     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            r0.a(r9, r1)     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            java.lang.String r2 = "%s/%s.dex"
            java.lang.Object[] r3 = new java.lang.Object[r5]     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            r3[r8] = r9     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            r3[r11] = r1     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            java.lang.String r8 = java.lang.String.format(r2, r3)     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            a(r8)     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
            throw r10     // Catch:{ FileNotFoundException -> 0x0149, IOException -> 0x0142, zzcl -> 0x013b, NullPointerException -> 0x0134 }
        L_0x0134:
            r8 = move-exception
            com.google.android.gms.internal.ads.zzcw r9 = new com.google.android.gms.internal.ads.zzcw     // Catch:{ zzcw -> 0x0157 }
            r9.<init>(r8)     // Catch:{ zzcw -> 0x0157 }
            throw r9     // Catch:{ zzcw -> 0x0157 }
        L_0x013b:
            r8 = move-exception
            com.google.android.gms.internal.ads.zzcw r9 = new com.google.android.gms.internal.ads.zzcw     // Catch:{ zzcw -> 0x0157 }
            r9.<init>(r8)     // Catch:{ zzcw -> 0x0157 }
            throw r9     // Catch:{ zzcw -> 0x0157 }
        L_0x0142:
            r8 = move-exception
            com.google.android.gms.internal.ads.zzcw r9 = new com.google.android.gms.internal.ads.zzcw     // Catch:{ zzcw -> 0x0157 }
            r9.<init>(r8)     // Catch:{ zzcw -> 0x0157 }
            throw r9     // Catch:{ zzcw -> 0x0157 }
        L_0x0149:
            r8 = move-exception
            com.google.android.gms.internal.ads.zzcw r9 = new com.google.android.gms.internal.ads.zzcw     // Catch:{ zzcw -> 0x0157 }
            r9.<init>(r8)     // Catch:{ zzcw -> 0x0157 }
            throw r9     // Catch:{ zzcw -> 0x0157 }
        L_0x0150:
            r8 = move-exception
            com.google.android.gms.internal.ads.zzcw r9 = new com.google.android.gms.internal.ads.zzcw     // Catch:{ zzcw -> 0x0157 }
            r9.<init>(r8)     // Catch:{ zzcw -> 0x0157 }
            throw r9     // Catch:{ zzcw -> 0x0157 }
        L_0x0157:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.acy.a(android.content.Context, java.lang.String, java.lang.String, boolean):com.google.android.gms.internal.ads.acy");
    }

    private static void a(File file) {
        if (!file.exists()) {
            Log.d(b, String.format("File %s not found. No need for deletion", new Object[]{file.getAbsolutePath()}));
            return;
        }
        file.delete();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:20|21|22|23|24|25|26|27|28|30) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0091 */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a3 A[SYNTHETIC, Splitter:B:39:0x00a3] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a8 A[SYNTHETIC, Splitter:B:43:0x00a8] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00b2 A[SYNTHETIC, Splitter:B:52:0x00b2] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00b7 A[SYNTHETIC, Splitter:B:56:0x00b7] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void a(java.io.File r8, java.lang.String r9) {
        /*
            r7 = this;
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "%s/%s.tmp"
            r2 = 2
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r4 = 0
            r3[r4] = r8
            r5 = 1
            r3[r5] = r9
            java.lang.String r1 = java.lang.String.format(r1, r3)
            r0.<init>(r1)
            boolean r1 = r0.exists()
            if (r1 == 0) goto L_0x001b
            return
        L_0x001b:
            java.io.File r1 = new java.io.File
            java.lang.String r3 = "%s/%s.dex"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r4] = r8
            r2[r5] = r9
            java.lang.String r8 = java.lang.String.format(r3, r2)
            r1.<init>(r8)
            boolean r8 = r1.exists()
            if (r8 != 0) goto L_0x0033
            return
        L_0x0033:
            long r2 = r1.length()
            r5 = 0
            int r8 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r8 > 0) goto L_0x003e
            return
        L_0x003e:
            int r8 = (int) r2
            byte[] r8 = new byte[r8]
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00af, all -> 0x009f }
            r3.<init>(r1)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00af, all -> 0x009f }
            int r5 = r3.read(r8)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            if (r5 > 0) goto L_0x0054
            r3.close()     // Catch:{ IOException -> 0x0050 }
        L_0x0050:
            a(r1)
            return
        L_0x0054:
            com.google.android.gms.internal.ads.zr r5 = new com.google.android.gms.internal.ads.zr     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            r5.<init>()     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            java.lang.String r6 = android.os.Build.VERSION.SDK     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            byte[] r6 = r6.getBytes()     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            r5.d = r6     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            byte[] r9 = r9.getBytes()     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            r5.c = r9     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            com.google.android.gms.internal.ads.acl r9 = r7.e     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            byte[] r6 = r7.f     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            java.lang.String r8 = r9.a(r6, r8)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            byte[] r8 = r8.getBytes()     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            r5.a = r8     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            byte[] r8 = com.google.android.gms.internal.ads.abl.a(r8)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            r5.b = r8     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            r0.createNewFile()     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            r8.<init>(r0)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b0, all -> 0x009d }
            byte[] r9 = com.google.android.gms.internal.ads.aar.a(r5)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x009b, all -> 0x0098 }
            int r0 = r9.length     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x009b, all -> 0x0098 }
            r8.write(r9, r4, r0)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x009b, all -> 0x0098 }
            r8.close()     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x009b, all -> 0x0098 }
            r3.close()     // Catch:{ IOException -> 0x0091 }
        L_0x0091:
            r8.close()     // Catch:{ IOException -> 0x0094 }
        L_0x0094:
            a(r1)
            return
        L_0x0098:
            r9 = move-exception
            r2 = r8
            goto L_0x00a1
        L_0x009b:
            r2 = r8
            goto L_0x00b0
        L_0x009d:
            r9 = move-exception
            goto L_0x00a1
        L_0x009f:
            r9 = move-exception
            r3 = r2
        L_0x00a1:
            if (r3 == 0) goto L_0x00a6
            r3.close()     // Catch:{ IOException -> 0x00a6 }
        L_0x00a6:
            if (r2 == 0) goto L_0x00ab
            r2.close()     // Catch:{ IOException -> 0x00ab }
        L_0x00ab:
            a(r1)
            throw r9
        L_0x00af:
            r3 = r2
        L_0x00b0:
            if (r3 == 0) goto L_0x00b5
            r3.close()     // Catch:{ IOException -> 0x00b5 }
        L_0x00b5:
            if (r2 == 0) goto L_0x00ba
            r2.close()     // Catch:{ IOException -> 0x00ba }
        L_0x00ba:
            a(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.acy.a(java.io.File, java.lang.String):void");
    }

    private static void a(String str) {
        a(new File(str));
    }

    /* access modifiers changed from: private */
    public static boolean b(int i2, vy vyVar) {
        if (i2 < 4) {
            if (vyVar == null) {
                return true;
            }
            if (((Boolean) ajh.f().a(akl.bP)).booleanValue() && (vyVar.n == null || vyVar.n.equals("0000000000000000000000000000000000000000000000000000000000000000"))) {
                return true;
            }
            if (((Boolean) ajh.f().a(akl.bQ)).booleanValue() && (vyVar.X == null || vyVar.X.a == null || vyVar.X.a.longValue() == -2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:29|30|31|32|33|34|35|36) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x00b1 */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c7 A[SYNTHETIC, Splitter:B:52:0x00c7] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00cc A[SYNTHETIC, Splitter:B:56:0x00cc] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00d3 A[SYNTHETIC, Splitter:B:64:0x00d3] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00d8 A[SYNTHETIC, Splitter:B:68:0x00d8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean b(java.io.File r10, java.lang.String r11) {
        /*
            r9 = this;
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "%s/%s.tmp"
            r2 = 2
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r4 = 0
            r3[r4] = r10
            r5 = 1
            r3[r5] = r11
            java.lang.String r1 = java.lang.String.format(r1, r3)
            r0.<init>(r1)
            boolean r1 = r0.exists()
            if (r1 != 0) goto L_0x001b
            return r4
        L_0x001b:
            java.io.File r1 = new java.io.File
            java.lang.String r3 = "%s/%s.dex"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r4] = r10
            r2[r5] = r11
            java.lang.String r10 = java.lang.String.format(r3, r2)
            r1.<init>(r10)
            boolean r10 = r1.exists()
            if (r10 == 0) goto L_0x0033
            return r4
        L_0x0033:
            r10 = 0
            long r2 = r0.length()     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00c3 }
            r6 = 0
            int r8 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r8 > 0) goto L_0x0042
            a(r0)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00c3 }
            return r4
        L_0x0042:
            int r2 = (int) r2     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00c3 }
            byte[] r2 = new byte[r2]     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00c3 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00c3 }
            r3.<init>(r0)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00c3 }
            int r6 = r3.read(r2)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            if (r6 > 0) goto L_0x005e
            java.lang.String r11 = b     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            java.lang.String r1 = "Cannot read the cache data."
            android.util.Log.d(r11, r1)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            a(r0)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            r3.close()     // Catch:{ IOException -> 0x005d }
        L_0x005d:
            return r4
        L_0x005e:
            com.google.android.gms.internal.ads.zr r6 = new com.google.android.gms.internal.ads.zr     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            r6.<init>()     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            com.google.android.gms.internal.ads.aar r2 = com.google.android.gms.internal.ads.aar.a(r6, r2)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            com.google.android.gms.internal.ads.zr r2 = (com.google.android.gms.internal.ads.zr) r2     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            java.lang.String r6 = new java.lang.String     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            byte[] r7 = r2.c     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            r6.<init>(r7)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            boolean r11 = r11.equals(r6)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            if (r11 == 0) goto L_0x00ba
            byte[] r11 = r2.b     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            byte[] r6 = r2.a     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            byte[] r6 = com.google.android.gms.internal.ads.abl.a(r6)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            boolean r11 = java.util.Arrays.equals(r11, r6)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            if (r11 == 0) goto L_0x00ba
            byte[] r11 = r2.d     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            java.lang.String r6 = android.os.Build.VERSION.SDK     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            byte[] r6 = r6.getBytes()     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            boolean r11 = java.util.Arrays.equals(r11, r6)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            if (r11 != 0) goto L_0x0093
            goto L_0x00ba
        L_0x0093:
            com.google.android.gms.internal.ads.acl r11 = r9.e     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            byte[] r0 = r9.f     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            java.lang.String r6 = new java.lang.String     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            byte[] r2 = r2.a     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            r6.<init>(r2)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            byte[] r11 = r11.a(r0, r6)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            r1.createNewFile()     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            r0.<init>(r1)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            int r10 = r11.length     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b8, all -> 0x00b5 }
            r0.write(r11, r4, r10)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00b8, all -> 0x00b5 }
            r3.close()     // Catch:{ IOException -> 0x00b1 }
        L_0x00b1:
            r0.close()     // Catch:{ IOException -> 0x00b4 }
        L_0x00b4:
            return r5
        L_0x00b5:
            r11 = move-exception
            r10 = r0
            goto L_0x00c5
        L_0x00b8:
            r10 = r0
            goto L_0x00d1
        L_0x00ba:
            a(r0)     // Catch:{ zzcl | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c1 }
            r3.close()     // Catch:{ IOException -> 0x00c0 }
        L_0x00c0:
            return r4
        L_0x00c1:
            r11 = move-exception
            goto L_0x00c5
        L_0x00c3:
            r11 = move-exception
            r3 = r10
        L_0x00c5:
            if (r3 == 0) goto L_0x00ca
            r3.close()     // Catch:{ IOException -> 0x00ca }
        L_0x00ca:
            if (r10 == 0) goto L_0x00cf
            r10.close()     // Catch:{ IOException -> 0x00cf }
        L_0x00cf:
            throw r11
        L_0x00d0:
            r3 = r10
        L_0x00d1:
            if (r3 == 0) goto L_0x00d6
            r3.close()     // Catch:{ IOException -> 0x00d6 }
        L_0x00d6:
            if (r10 == 0) goto L_0x00db
            r10.close()     // Catch:{ IOException -> 0x00db }
        L_0x00db:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.acy.b(java.io.File, java.lang.String):boolean");
    }

    /* access modifiers changed from: private */
    public final void o() {
        try {
            if (this.g == null && this.j) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.a);
                advertisingIdClient.start();
                this.g = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException unused) {
            this.g = null;
        }
    }

    @VisibleForTesting
    private final vy p() {
        try {
            return pv.a(this.a, this.a.getPackageName(), Integer.toString(this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).versionCode));
        } catch (Throwable unused) {
            return null;
        }
    }

    public final Context a() {
        return this.a;
    }

    public final Method a(String str, String str2) {
        aeg aeg = (aeg) this.p.get(new Pair(str, str2));
        if (aeg == null) {
            return null;
        }
        return aeg.a();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void a(int i2, boolean z) {
        if (this.o) {
            Future submit = this.c.submit(new adc(this, i2, z));
            if (i2 == 0) {
                this.l = submit;
            }
        }
    }

    public final boolean a(String str, String str2, Class<?>... clsArr) {
        if (this.p.containsKey(new Pair(str, str2))) {
            return false;
        }
        this.p.put(new Pair(str, str2), new aeg(this, str, str2, clsArr));
        return true;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final vy b(int i2, boolean z) {
        if (i2 > 0 && z) {
            try {
                Thread.sleep((long) (i2 * 1000));
            } catch (InterruptedException unused) {
            }
        }
        return p();
    }

    public final boolean b() {
        return this.q;
    }

    public final ExecutorService c() {
        return this.c;
    }

    public final DexClassLoader d() {
        return this.d;
    }

    public final acl e() {
        return this.e;
    }

    public final byte[] f() {
        return this.f;
    }

    public final boolean g() {
        return this.n;
    }

    public final ace h() {
        return this.m;
    }

    public final boolean i() {
        return this.o;
    }

    public final boolean j() {
        return this.r;
    }

    public final vy k() {
        return this.k;
    }

    public final Future l() {
        return this.l;
    }

    public final AdvertisingIdClient m() {
        if (!this.h) {
            return null;
        }
        if (this.g != null) {
            return this.g;
        }
        if (this.i != null) {
            try {
                this.i.get(2000, TimeUnit.MILLISECONDS);
                this.i = null;
            } catch (InterruptedException | ExecutionException unused) {
            } catch (TimeoutException unused2) {
                this.i.cancel(true);
            }
        }
        return this.g;
    }

    public final int n() {
        if (this.m != null) {
            return ace.a();
        }
        return Integer.MIN_VALUE;
    }
}
