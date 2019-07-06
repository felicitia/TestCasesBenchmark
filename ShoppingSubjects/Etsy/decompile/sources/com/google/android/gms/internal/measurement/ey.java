package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ey implements cq {
    private static volatile ey a;
    private bp b;
    private au c;
    private z d;
    private az e;
    private eu f;
    private u g;
    private final fe h;
    private final bu i;
    private boolean j;
    private boolean k;
    @VisibleForTesting
    private long l;
    private List<Runnable> m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private boolean r;
    private FileLock s;
    private FileChannel t;
    private List<Long> u;
    private List<Long> v;
    private long w;

    class a implements ab {
        fx a;
        List<Long> b;
        List<fu> c;
        private long d;

        private a() {
        }

        /* synthetic */ a(ey eyVar, ez ezVar) {
            this();
        }

        private static long a(fu fuVar) {
            return ((fuVar.e.longValue() / 1000) / 60) / 60;
        }

        public final void a(fx fxVar) {
            Preconditions.checkNotNull(fxVar);
            this.a = fxVar;
        }

        public final boolean a(long j, fu fuVar) {
            Preconditions.checkNotNull(fuVar);
            if (this.c == null) {
                this.c = new ArrayList();
            }
            if (this.b == null) {
                this.b = new ArrayList();
            }
            if (this.c.size() > 0 && a((fu) this.c.get(0)) != a(fuVar)) {
                return false;
            }
            long d2 = this.d + ((long) fuVar.d());
            if (d2 >= ((long) Math.max(0, ((Integer) ak.q.b()).intValue()))) {
                return false;
            }
            this.d = d2;
            this.c.add(fuVar);
            this.b.add(Long.valueOf(j));
            return this.c.size() < Math.max(1, ((Integer) ak.r.b()).intValue());
        }
    }

    private ey(fd fdVar) {
        this(fdVar, null);
    }

    private ey(fd fdVar, bu buVar) {
        this.j = false;
        Preconditions.checkNotNull(fdVar);
        this.i = bu.a(fdVar.a, null, null);
        this.w = -1;
        fe feVar = new fe(this);
        feVar.J();
        this.h = feVar;
        au auVar = new au(this);
        auVar.J();
        this.c = auVar;
        bp bpVar = new bp(this);
        bpVar.J();
        this.b = bpVar;
        this.i.q().a((Runnable) new ez(this, fdVar));
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean A() {
        String str;
        as asVar;
        v();
        try {
            this.t = new RandomAccessFile(new File(this.i.n().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.s = this.t.tryLock();
            if (this.s != null) {
                this.i.r().w().a("Storage concurrent access okay");
                return true;
            }
            this.i.r().h_().a("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e2) {
            e = e2;
            asVar = this.i.r().h_();
            str = "Failed to acquire storage lock";
            asVar.a(str, e);
            return false;
        } catch (IOException e3) {
            e = e3;
            asVar = this.i.r().h_();
            str = "Failed to access storage lock file";
            asVar.a(str, e);
            return false;
        }
    }

    @WorkerThread
    private final boolean B() {
        v();
        i();
        return this.k;
    }

    @WorkerThread
    @VisibleForTesting
    private final int a(FileChannel fileChannel) {
        v();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.i.r().h_().a("Bad channel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read != 4) {
                if (read != -1) {
                    this.i.r().i().a("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
                return 0;
            }
            allocate.flip();
            return allocate.getInt();
        } catch (IOException e2) {
            this.i.r().h_().a("Failed to read from channel", e2);
            return 0;
        }
    }

    public static ey a(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (a == null) {
            synchronized (ey.class) {
                if (a == null) {
                    a = new ey(new fd(context));
                }
            }
        }
        return a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0057 A[Catch:{ NameNotFoundException -> 0x00bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.measurement.zzeb a(android.content.Context r26, java.lang.String r27, java.lang.String r28, boolean r29, boolean r30, boolean r31, long r32) {
        /*
            r25 = this;
            r0 = r25
            r2 = r27
            java.lang.String r1 = "Unknown"
            java.lang.String r3 = "Unknown"
            java.lang.String r4 = "Unknown"
            android.content.pm.PackageManager r5 = r26.getPackageManager()
            r6 = 0
            if (r5 != 0) goto L_0x0021
            com.google.android.gms.internal.measurement.bu r1 = r0.i
            com.google.android.gms.internal.measurement.aq r1 = r1.r()
            com.google.android.gms.internal.measurement.as r1 = r1.h_()
            java.lang.String r2 = "PackageManager is null, can not log app install information"
            r1.a(r2)
            return r6
        L_0x0021:
            java.lang.String r5 = r5.getInstallerPackageName(r2)     // Catch:{ IllegalArgumentException -> 0x0026 }
            goto L_0x003a
        L_0x0026:
            com.google.android.gms.internal.measurement.bu r5 = r0.i
            com.google.android.gms.internal.measurement.aq r5 = r5.r()
            com.google.android.gms.internal.measurement.as r5 = r5.h_()
            java.lang.String r7 = "Error retrieving installer package name. appId"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.aq.a(r27)
            r5.a(r7, r8)
            r5 = r1
        L_0x003a:
            if (r5 != 0) goto L_0x0040
            java.lang.String r1 = "manual_install"
        L_0x003e:
            r7 = r1
            goto L_0x004c
        L_0x0040:
            java.lang.String r1 = "com.android.vending"
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L_0x004b
            java.lang.String r1 = ""
            goto L_0x003e
        L_0x004b:
            r7 = r5
        L_0x004c:
            com.google.android.gms.common.wrappers.PackageManagerWrapper r1 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r26)     // Catch:{ NameNotFoundException -> 0x00bb }
            r5 = 0
            android.content.pm.PackageInfo r1 = r1.getPackageInfo(r2, r5)     // Catch:{ NameNotFoundException -> 0x00bb }
            if (r1 == 0) goto L_0x006f
            com.google.android.gms.common.wrappers.PackageManagerWrapper r3 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r26)     // Catch:{ NameNotFoundException -> 0x00bb }
            java.lang.CharSequence r3 = r3.getApplicationLabel(r2)     // Catch:{ NameNotFoundException -> 0x00bb }
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ NameNotFoundException -> 0x00bb }
            if (r5 != 0) goto L_0x006a
            java.lang.String r3 = r3.toString()     // Catch:{ NameNotFoundException -> 0x00bb }
            r4 = r3
        L_0x006a:
            java.lang.String r3 = r1.versionName     // Catch:{ NameNotFoundException -> 0x00bb }
            int r1 = r1.versionCode     // Catch:{ NameNotFoundException -> 0x00bb }
            goto L_0x0071
        L_0x006f:
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x0071:
            r4 = r3
            r16 = 0
            com.google.android.gms.internal.measurement.bu r3 = r0.i
            r3.u()
            r5 = 0
            com.google.android.gms.internal.measurement.bu r3 = r0.i
            com.google.android.gms.internal.measurement.w r3 = r3.b()
            boolean r3 = r3.l(r2)
            if (r3 == 0) goto L_0x008a
            r18 = r32
            goto L_0x008c
        L_0x008a:
            r18 = r5
        L_0x008c:
            com.google.android.gms.internal.measurement.zzeb r24 = new com.google.android.gms.internal.measurement.zzeb
            long r5 = (long) r1
            com.google.android.gms.internal.measurement.bu r1 = r0.i
            com.google.android.gms.internal.measurement.w r1 = r1.b()
            long r8 = r1.f()
            com.google.android.gms.internal.measurement.bu r1 = r0.i
            com.google.android.gms.internal.measurement.fg r1 = r1.k()
            r3 = r26
            long r10 = r1.a(r3, r2)
            r12 = 0
            r14 = 0
            java.lang.String r15 = ""
            r20 = 0
            r23 = 0
            r1 = r24
            r3 = r28
            r13 = r29
            r21 = r30
            r22 = r31
            r1.<init>(r2, r3, r4, r5, r7, r8, r10, r12, r13, r14, r15, r16, r18, r20, r21, r22, r23)
            return r24
        L_0x00bb:
            com.google.android.gms.internal.measurement.bu r1 = r0.i
            com.google.android.gms.internal.measurement.aq r1 = r1.r()
            com.google.android.gms.internal.measurement.as r1 = r1.h_()
            java.lang.String r3 = "Error retrieving newly installed package info. appId, appName"
            java.lang.Object r2 = com.google.android.gms.internal.measurement.aq.a(r27)
            r1.a(r3, r2, r4)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.ey.a(android.content.Context, java.lang.String, java.lang.String, boolean, boolean, boolean, long):com.google.android.gms.internal.measurement.zzeb");
    }

    @WorkerThread
    private final zzeb a(String str) {
        as v2;
        String str2;
        Object obj;
        String str3 = str;
        t b2 = d().b(str3);
        if (b2 == null || TextUtils.isEmpty(b2.i())) {
            v2 = this.i.r().v();
            str2 = "No app data available; dropping";
            obj = str3;
        } else {
            Boolean b3 = b(b2);
            if (b3 == null || b3.booleanValue()) {
                zzeb zzeb = new zzeb(str3, b2.d(), b2.i(), b2.j(), b2.k(), b2.l(), b2.m(), (String) null, b2.n(), false, b2.f(), b2.A(), 0, 0, b2.B(), b2.C(), false);
                return zzeb;
            }
            v2 = this.i.r().h_();
            str2 = "App version does not match; dropping. appId";
            obj = aq.a(str);
        }
        v2.a(str2, obj);
        return null;
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void a(fd fdVar) {
        this.i.q().d();
        z zVar = new z(this);
        zVar.J();
        this.d = zVar;
        this.i.b().a((y) this.b);
        u uVar = new u(this);
        uVar.J();
        this.g = uVar;
        eu euVar = new eu(this);
        euVar.J();
        this.f = euVar;
        this.e = new az(this);
        if (this.n != this.o) {
            this.i.r().h_().a("Not all upload components initialized", Integer.valueOf(this.n), Integer.valueOf(this.o));
        }
        this.j = true;
    }

    @WorkerThread
    private final void a(t tVar) {
        Map map;
        v();
        if (TextUtils.isEmpty(tVar.d())) {
            a(tVar.b(), 204, null, null, null);
            return;
        }
        w b2 = this.i.b();
        String d2 = tVar.d();
        String c2 = tVar.c();
        Builder builder = new Builder();
        Builder encodedAuthority = builder.scheme((String) ak.m.b()).encodedAuthority((String) ak.n.b());
        String str = "config/app/";
        String valueOf = String.valueOf(d2);
        encodedAuthority.path(valueOf.length() != 0 ? str.concat(valueOf) : new String(str)).appendQueryParameter("app_instance_id", c2).appendQueryParameter(ResponseConstants.PLATFORM, AppBuild.ANDROID_PLATFORM).appendQueryParameter("gmp_version", String.valueOf(b2.f()));
        String uri = builder.build().toString();
        try {
            URL url = new URL(uri);
            this.i.r().w().a("Fetching remote configuration", tVar.b());
            fq a2 = p().a(tVar.b());
            String b3 = p().b(tVar.b());
            if (a2 == null || TextUtils.isEmpty(b3)) {
                map = null;
            } else {
                ArrayMap arrayMap = new ArrayMap();
                arrayMap.put("If-Modified-Since", b3);
                map = arrayMap;
            }
            this.p = true;
            au c3 = c();
            String b4 = tVar.b();
            fb fbVar = new fb(this);
            c3.d();
            c3.I();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(fbVar);
            bq q2 = c3.q();
            ay ayVar = new ay(c3, b4, url, null, map, fbVar);
            q2.b((Runnable) ayVar);
        } catch (MalformedURLException unused) {
            this.i.r().h_().a("Failed to parse config URL. Not fetching. appId", aq.a(tVar.b()), uri);
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean a(int i2, FileChannel fileChannel) {
        v();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.i.r().h_().a("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i2);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                this.i.r().h_().a("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e2) {
            this.i.r().h_().a("Failed to write to channel", e2);
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0225, code lost:
        if (r5 != null) goto L_0x01d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0040, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0248, code lost:
        if (r6 == null) goto L_0x0287;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
        r2 = r0;
        r6 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0045, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0284, code lost:
        if (r6 != null) goto L_0x024a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0046, code lost:
        r6 = null;
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0087, code lost:
        if (r3 != null) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a0, code lost:
        r6 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:460:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01d7, code lost:
        if (r5 != null) goto L_0x01d9;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0040 A[ExcHandler: all (r0v20 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r3 
      PHI: (r3v48 android.database.Cursor) = (r3v42 android.database.Cursor), (r3v53 android.database.Cursor), (r3v53 android.database.Cursor), (r3v53 android.database.Cursor), (r3v53 android.database.Cursor), (r3v1 android.database.Cursor), (r3v1 android.database.Cursor) binds: [B:48:0x00e5, B:25:0x0081, B:31:0x008e, B:33:0x0092, B:34:?, B:9:0x0031, B:10:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:9:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x028b A[Catch:{ SQLiteException -> 0x0b5a, all -> 0x0b95 }] */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0299 A[Catch:{ SQLiteException -> 0x0b5a, all -> 0x0b95 }] */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x05a9 A[Catch:{ SQLiteException -> 0x0b5a, all -> 0x0b95 }] */
    /* JADX WARNING: Removed duplicated region for block: B:282:0x0690 A[Catch:{ SQLiteException -> 0x0b5a, all -> 0x0b95 }] */
    /* JADX WARNING: Removed duplicated region for block: B:288:0x06aa A[Catch:{ SQLiteException -> 0x0b5a, all -> 0x0b95 }] */
    /* JADX WARNING: Removed duplicated region for block: B:291:0x06ca A[Catch:{ SQLiteException -> 0x0b5a, all -> 0x0b95 }] */
    /* JADX WARNING: Removed duplicated region for block: B:452:0x0b7d A[SYNTHETIC, Splitter:B:452:0x0b7d] */
    /* JADX WARNING: Removed duplicated region for block: B:459:0x0b91 A[SYNTHETIC, Splitter:B:459:0x0b91] */
    /* JADX WARNING: Removed duplicated region for block: B:481:0x06a7 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0125 A[SYNTHETIC, Splitter:B:61:0x0125] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0147 A[SYNTHETIC, Splitter:B:70:0x0147] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:146:0x0287=Splitter:B:146:0x0287, B:29:0x0089=Splitter:B:29:0x0089, B:99:0x01d9=Splitter:B:99:0x01d9, B:125:0x024a=Splitter:B:125:0x024a} */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean a(java.lang.String r60, long r61) {
        /*
            r59 = this;
            r1 = r59
            com.google.android.gms.internal.measurement.z r2 = r59.d()
            r2.f()
            com.google.android.gms.internal.measurement.ey$a r2 = new com.google.android.gms.internal.measurement.ey$a     // Catch:{ all -> 0x0b95 }
            r3 = 0
            r2.<init>(r1, r3)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.z r4 = r59.d()     // Catch:{ all -> 0x0b95 }
            long r5 = r1.w     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)     // Catch:{ all -> 0x0b95 }
            r4.d()     // Catch:{ all -> 0x0b95 }
            r4.I()     // Catch:{ all -> 0x0b95 }
            r7 = -1
            r9 = 2
            r10 = 0
            r11 = 1
            android.database.sqlite.SQLiteDatabase r15 = r4.i()     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            boolean r12 = android.text.TextUtils.isEmpty(r3)     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            if (r12 == 0) goto L_0x00a2
            int r12 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r12 == 0) goto L_0x004b
            java.lang.String[] r12 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x0045, all -> 0x0040 }
            java.lang.String r13 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0045, all -> 0x0040 }
            r12[r10] = r13     // Catch:{ SQLiteException -> 0x0045, all -> 0x0040 }
            java.lang.String r13 = java.lang.String.valueOf(r61)     // Catch:{ SQLiteException -> 0x0045, all -> 0x0040 }
            r12[r11] = r13     // Catch:{ SQLiteException -> 0x0045, all -> 0x0040 }
            goto L_0x0053
        L_0x0040:
            r0 = move-exception
            r2 = r0
            r6 = r3
            goto L_0x0b8f
        L_0x0045:
            r0 = move-exception
            r6 = r3
            r12 = r6
        L_0x0048:
            r3 = r0
            goto L_0x0273
        L_0x004b:
            java.lang.String[] r12 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            java.lang.String r13 = java.lang.String.valueOf(r61)     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            r12[r10] = r13     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
        L_0x0053:
            int r13 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r13 == 0) goto L_0x005a
            java.lang.String r13 = "rowid <= ? and "
            goto L_0x005c
        L_0x005a:
            java.lang.String r13 = ""
        L_0x005c:
            r14 = 148(0x94, float:2.07E-43)
            java.lang.String r3 = java.lang.String.valueOf(r13)     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            int r3 = r3.length()     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            int r14 = r14 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            r3.<init>(r14)     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            java.lang.String r14 = "select app_id, metadata_fingerprint from raw_events where "
            r3.append(r14)     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            r3.append(r13)     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            java.lang.String r13 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
            r3.append(r13)     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            java.lang.String r3 = r3.toString()     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            android.database.Cursor r3 = r15.rawQuery(r3, r12)     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            boolean r12 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0265, all -> 0x0040 }
            if (r12 != 0) goto L_0x008e
            if (r3 == 0) goto L_0x0287
        L_0x0089:
            r3.close()     // Catch:{ all -> 0x0b95 }
            goto L_0x0287
        L_0x008e:
            java.lang.String r12 = r3.getString(r10)     // Catch:{ SQLiteException -> 0x0265, all -> 0x0040 }
            java.lang.String r13 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x009f, all -> 0x0040 }
            r3.close()     // Catch:{ SQLiteException -> 0x009f, all -> 0x0040 }
            r22 = r3
            r3 = r12
            r21 = r13
            goto L_0x00fa
        L_0x009f:
            r0 = move-exception
            r6 = r3
            goto L_0x0048
        L_0x00a2:
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x00b2
            java.lang.String[] r3 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            r12 = 0
            r3[r10] = r12     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            java.lang.String r12 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            r3[r11] = r12     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            goto L_0x00b7
        L_0x00b2:
            java.lang.String[] r3 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            r12 = 0
            r3[r10] = r12     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
        L_0x00b7:
            int r12 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r12 == 0) goto L_0x00be
            java.lang.String r12 = " and rowid <= ?"
            goto L_0x00c0
        L_0x00be:
            java.lang.String r12 = ""
        L_0x00c0:
            r13 = 84
            java.lang.String r14 = java.lang.String.valueOf(r12)     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            int r14 = r14.length()     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            int r13 = r13 + r14
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            r14.<init>(r13)     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            java.lang.String r13 = "select metadata_fingerprint from raw_events where app_id = ?"
            r14.append(r13)     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            r14.append(r12)     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            java.lang.String r12 = " order by rowid limit 1;"
            r14.append(r12)     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            java.lang.String r12 = r14.toString()     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            android.database.Cursor r3 = r15.rawQuery(r12, r3)     // Catch:{ SQLiteException -> 0x026f, all -> 0x026a }
            boolean r12 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0265, all -> 0x0040 }
            if (r12 != 0) goto L_0x00ee
            if (r3 == 0) goto L_0x0287
            goto L_0x0089
        L_0x00ee:
            java.lang.String r13 = r3.getString(r10)     // Catch:{ SQLiteException -> 0x0265, all -> 0x0040 }
            r3.close()     // Catch:{ SQLiteException -> 0x0265, all -> 0x0040 }
            r22 = r3
            r21 = r13
            r3 = 0
        L_0x00fa:
            java.lang.String r13 = "raw_events_metadata"
            java.lang.String[] r14 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x025f, all -> 0x0259 }
            java.lang.String r12 = "metadata"
            r14[r10] = r12     // Catch:{ SQLiteException -> 0x025f, all -> 0x0259 }
            java.lang.String r16 = "app_id = ? and metadata_fingerprint = ?"
            java.lang.String[] r12 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x025f, all -> 0x0259 }
            r12[r10] = r3     // Catch:{ SQLiteException -> 0x025f, all -> 0x0259 }
            r12[r11] = r21     // Catch:{ SQLiteException -> 0x025f, all -> 0x0259 }
            r17 = 0
            r18 = 0
            java.lang.String r19 = "rowid"
            java.lang.String r20 = "2"
            r23 = r12
            r12 = r15
            r24 = r15
            r15 = r16
            r16 = r23
            android.database.Cursor r15 = r12.query(r13, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ SQLiteException -> 0x025f, all -> 0x0259 }
            boolean r12 = r15.moveToFirst()     // Catch:{ SQLiteException -> 0x0254, all -> 0x0250 }
            if (r12 != 0) goto L_0x0147
            com.google.android.gms.internal.measurement.aq r5 = r4.r()     // Catch:{ SQLiteException -> 0x0142, all -> 0x013d }
            com.google.android.gms.internal.measurement.as r5 = r5.h_()     // Catch:{ SQLiteException -> 0x0142, all -> 0x013d }
            java.lang.String r6 = "Raw event metadata record is missing. appId"
            java.lang.Object r12 = com.google.android.gms.internal.measurement.aq.a(r3)     // Catch:{ SQLiteException -> 0x0142, all -> 0x013d }
            r5.a(r6, r12)     // Catch:{ SQLiteException -> 0x0142, all -> 0x013d }
            if (r15 == 0) goto L_0x0287
            r15.close()     // Catch:{ all -> 0x0b95 }
            goto L_0x0287
        L_0x013d:
            r0 = move-exception
            r2 = r0
            r6 = r15
            goto L_0x0b8f
        L_0x0142:
            r0 = move-exception
            r12 = r3
            r6 = r15
            goto L_0x0048
        L_0x0147:
            byte[] r12 = r15.getBlob(r10)     // Catch:{ SQLiteException -> 0x0254, all -> 0x0250 }
            int r13 = r12.length     // Catch:{ SQLiteException -> 0x0254, all -> 0x0250 }
            com.google.android.gms.internal.measurement.c r12 = com.google.android.gms.internal.measurement.c.a(r12, r10, r13)     // Catch:{ SQLiteException -> 0x0254, all -> 0x0250 }
            com.google.android.gms.internal.measurement.fx r13 = new com.google.android.gms.internal.measurement.fx     // Catch:{ SQLiteException -> 0x0254, all -> 0x0250 }
            r13.<init>()     // Catch:{ SQLiteException -> 0x0254, all -> 0x0250 }
            r13.a(r12)     // Catch:{ IOException -> 0x0235 }
            boolean r12 = r15.moveToNext()     // Catch:{ SQLiteException -> 0x0254, all -> 0x0250 }
            if (r12 == 0) goto L_0x016f
            com.google.android.gms.internal.measurement.aq r12 = r4.r()     // Catch:{ SQLiteException -> 0x0142, all -> 0x013d }
            com.google.android.gms.internal.measurement.as r12 = r12.i()     // Catch:{ SQLiteException -> 0x0142, all -> 0x013d }
            java.lang.String r14 = "Get multiple raw event metadata records, expected one. appId"
            java.lang.Object r9 = com.google.android.gms.internal.measurement.aq.a(r3)     // Catch:{ SQLiteException -> 0x0142, all -> 0x013d }
            r12.a(r14, r9)     // Catch:{ SQLiteException -> 0x0142, all -> 0x013d }
        L_0x016f:
            r15.close()     // Catch:{ SQLiteException -> 0x0254, all -> 0x0250 }
            r2.a(r13)     // Catch:{ SQLiteException -> 0x0254, all -> 0x0250 }
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            r14 = 3
            if (r9 == 0) goto L_0x018d
            java.lang.String r9 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?"
            java.lang.String[] r12 = new java.lang.String[r14]     // Catch:{ SQLiteException -> 0x0142, all -> 0x013d }
            r12[r10] = r3     // Catch:{ SQLiteException -> 0x0142, all -> 0x013d }
            r12[r11] = r21     // Catch:{ SQLiteException -> 0x0142, all -> 0x013d }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0142, all -> 0x013d }
            r6 = 2
            r12[r6] = r5     // Catch:{ SQLiteException -> 0x0142, all -> 0x013d }
            r5 = r9
            r16 = r12
            goto L_0x0198
        L_0x018d:
            java.lang.String r5 = "app_id = ? and metadata_fingerprint = ?"
            r6 = 2
            java.lang.String[] r9 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x0254, all -> 0x0250 }
            r9[r10] = r3     // Catch:{ SQLiteException -> 0x0254, all -> 0x0250 }
            r9[r11] = r21     // Catch:{ SQLiteException -> 0x0254, all -> 0x0250 }
            r16 = r9
        L_0x0198:
            java.lang.String r13 = "raw_events"
            r6 = 4
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x0254, all -> 0x0250 }
            java.lang.String r9 = "rowid"
            r6[r10] = r9     // Catch:{ SQLiteException -> 0x0254, all -> 0x0250 }
            java.lang.String r9 = "name"
            r6[r11] = r9     // Catch:{ SQLiteException -> 0x0254, all -> 0x0250 }
            java.lang.String r9 = "timestamp"
            r12 = 2
            r6[r12] = r9     // Catch:{ SQLiteException -> 0x0254, all -> 0x0250 }
            java.lang.String r9 = "data"
            r6[r14] = r9     // Catch:{ SQLiteException -> 0x0254, all -> 0x0250 }
            r17 = 0
            r18 = 0
            java.lang.String r19 = "rowid"
            r20 = 0
            r12 = r24
            r9 = r14
            r14 = r6
            r6 = r15
            r15 = r5
            android.database.Cursor r5 = r12.query(r13, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ SQLiteException -> 0x024e }
            boolean r6 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            if (r6 != 0) goto L_0x01de
            com.google.android.gms.internal.measurement.aq r6 = r4.r()     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            com.google.android.gms.internal.measurement.as r6 = r6.i()     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            java.lang.String r9 = "Raw event data disappeared while in transaction. appId"
            java.lang.Object r12 = com.google.android.gms.internal.measurement.aq.a(r3)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            r6.a(r9, r12)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            if (r5 == 0) goto L_0x0287
        L_0x01d9:
            r5.close()     // Catch:{ all -> 0x0b95 }
            goto L_0x0287
        L_0x01de:
            long r12 = r5.getLong(r10)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            byte[] r6 = r5.getBlob(r9)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            int r14 = r6.length     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            com.google.android.gms.internal.measurement.c r6 = com.google.android.gms.internal.measurement.c.a(r6, r10, r14)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            com.google.android.gms.internal.measurement.fu r14 = new com.google.android.gms.internal.measurement.fu     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            r14.<init>()     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            r14.a(r6)     // Catch:{ IOException -> 0x020d }
            java.lang.String r6 = r5.getString(r11)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            r14.d = r6     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            r6 = 2
            long r7 = r5.getLong(r6)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            java.lang.Long r6 = java.lang.Long.valueOf(r7)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            r14.e = r6     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            boolean r6 = r2.a(r12, r14)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            if (r6 != 0) goto L_0x021f
            if (r5 == 0) goto L_0x0287
            goto L_0x01d9
        L_0x020d:
            r0 = move-exception
            com.google.android.gms.internal.measurement.aq r6 = r4.r()     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            com.google.android.gms.internal.measurement.as r6 = r6.h_()     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            java.lang.String r7 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.aq.a(r3)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            r6.a(r7, r8, r0)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
        L_0x021f:
            boolean r6 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x0230, all -> 0x022b }
            if (r6 != 0) goto L_0x0228
            if (r5 == 0) goto L_0x0287
            goto L_0x01d9
        L_0x0228:
            r7 = -1
            goto L_0x01de
        L_0x022b:
            r0 = move-exception
            r2 = r0
            r6 = r5
            goto L_0x0b8f
        L_0x0230:
            r0 = move-exception
            r12 = r3
            r6 = r5
            goto L_0x0048
        L_0x0235:
            r0 = move-exception
            r6 = r15
            com.google.android.gms.internal.measurement.aq r5 = r4.r()     // Catch:{ SQLiteException -> 0x024e }
            com.google.android.gms.internal.measurement.as r5 = r5.h_()     // Catch:{ SQLiteException -> 0x024e }
            java.lang.String r7 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.aq.a(r3)     // Catch:{ SQLiteException -> 0x024e }
            r5.a(r7, r8, r0)     // Catch:{ SQLiteException -> 0x024e }
            if (r6 == 0) goto L_0x0287
        L_0x024a:
            r6.close()     // Catch:{ all -> 0x0b95 }
            goto L_0x0287
        L_0x024e:
            r0 = move-exception
            goto L_0x0256
        L_0x0250:
            r0 = move-exception
            r6 = r15
            goto L_0x0b8e
        L_0x0254:
            r0 = move-exception
            r6 = r15
        L_0x0256:
            r12 = r3
            goto L_0x0048
        L_0x0259:
            r0 = move-exception
            r2 = r0
            r6 = r22
            goto L_0x0b8f
        L_0x025f:
            r0 = move-exception
            r12 = r3
            r6 = r22
            goto L_0x0048
        L_0x0265:
            r0 = move-exception
            r6 = r3
            r12 = 0
            goto L_0x0048
        L_0x026a:
            r0 = move-exception
            r2 = r0
            r6 = 0
            goto L_0x0b8f
        L_0x026f:
            r0 = move-exception
            r3 = r0
            r6 = 0
            r12 = 0
        L_0x0273:
            com.google.android.gms.internal.measurement.aq r4 = r4.r()     // Catch:{ all -> 0x0b8d }
            com.google.android.gms.internal.measurement.as r4 = r4.h_()     // Catch:{ all -> 0x0b8d }
            java.lang.String r5 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.aq.a(r12)     // Catch:{ all -> 0x0b8d }
            r4.a(r5, r7, r3)     // Catch:{ all -> 0x0b8d }
            if (r6 == 0) goto L_0x0287
            goto L_0x024a
        L_0x0287:
            java.util.List<com.google.android.gms.internal.measurement.fu> r3 = r2.c     // Catch:{ all -> 0x0b95 }
            if (r3 == 0) goto L_0x0296
            java.util.List<com.google.android.gms.internal.measurement.fu> r3 = r2.c     // Catch:{ all -> 0x0b95 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0b95 }
            if (r3 == 0) goto L_0x0294
            goto L_0x0296
        L_0x0294:
            r3 = r10
            goto L_0x0297
        L_0x0296:
            r3 = r11
        L_0x0297:
            if (r3 != 0) goto L_0x0b7d
            com.google.android.gms.internal.measurement.fx r3 = r2.a     // Catch:{ all -> 0x0b95 }
            java.util.List<com.google.android.gms.internal.measurement.fu> r4 = r2.c     // Catch:{ all -> 0x0b95 }
            int r4 = r4.size()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fu[] r4 = new com.google.android.gms.internal.measurement.fu[r4]     // Catch:{ all -> 0x0b95 }
            r3.d = r4     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.bu r4 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.w r4 = r4.b()     // Catch:{ all -> 0x0b95 }
            java.lang.String r5 = r3.q     // Catch:{ all -> 0x0b95 }
            boolean r4 = r4.e(r5)     // Catch:{ all -> 0x0b95 }
            r7 = r10
            r8 = r7
            r9 = r8
            r12 = 0
        L_0x02b6:
            java.util.List<com.google.android.gms.internal.measurement.fu> r14 = r2.c     // Catch:{ all -> 0x0b95 }
            int r14 = r14.size()     // Catch:{ all -> 0x0b95 }
            if (r7 >= r14) goto L_0x05ff
            java.util.List<com.google.android.gms.internal.measurement.fu> r14 = r2.c     // Catch:{ all -> 0x0b95 }
            java.lang.Object r14 = r14.get(r7)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fu r14 = (com.google.android.gms.internal.measurement.fu) r14     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.bp r15 = r59.p()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fx r11 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r11 = r11.q     // Catch:{ all -> 0x0b95 }
            java.lang.String r5 = r14.d     // Catch:{ all -> 0x0b95 }
            boolean r5 = r15.b(r11, r5)     // Catch:{ all -> 0x0b95 }
            if (r5 == 0) goto L_0x0342
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.aq r5 = r5.r()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.as r5 = r5.i()     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = "Dropping blacklisted raw event. appId"
            com.google.android.gms.internal.measurement.fx r11 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r11 = r11.q     // Catch:{ all -> 0x0b95 }
            java.lang.Object r11 = com.google.android.gms.internal.measurement.aq.a(r11)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.bu r15 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.ao r15 = r15.l()     // Catch:{ all -> 0x0b95 }
            java.lang.String r10 = r14.d     // Catch:{ all -> 0x0b95 }
            java.lang.String r10 = r15.a(r10)     // Catch:{ all -> 0x0b95 }
            r5.a(r6, r11, r10)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.bp r5 = r59.p()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fx r6 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = r6.q     // Catch:{ all -> 0x0b95 }
            boolean r5 = r5.e(r6)     // Catch:{ all -> 0x0b95 }
            if (r5 != 0) goto L_0x0318
            com.google.android.gms.internal.measurement.bp r5 = r59.p()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fx r6 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = r6.q     // Catch:{ all -> 0x0b95 }
            boolean r5 = r5.f(r6)     // Catch:{ all -> 0x0b95 }
            if (r5 == 0) goto L_0x0316
            goto L_0x0318
        L_0x0316:
            r5 = 0
            goto L_0x0319
        L_0x0318:
            r5 = 1
        L_0x0319:
            if (r5 != 0) goto L_0x033e
            java.lang.String r5 = "_err"
            java.lang.String r6 = r14.d     // Catch:{ all -> 0x0b95 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x0b95 }
            if (r5 != 0) goto L_0x033e
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fg r15 = r5.k()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fx r5 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r5 = r5.q     // Catch:{ all -> 0x0b95 }
            r17 = 11
            java.lang.String r18 = "_ev"
            java.lang.String r6 = r14.d     // Catch:{ all -> 0x0b95 }
            r20 = 0
            r16 = r5
            r19 = r6
            r15.a(r16, r17, r18, r19, r20)     // Catch:{ all -> 0x0b95 }
        L_0x033e:
            r28 = r7
            goto L_0x05f9
        L_0x0342:
            com.google.android.gms.internal.measurement.bp r5 = r59.p()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fx r6 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = r6.q     // Catch:{ all -> 0x0b95 }
            java.lang.String r10 = r14.d     // Catch:{ all -> 0x0b95 }
            boolean r5 = r5.c(r6, r10)     // Catch:{ all -> 0x0b95 }
            if (r5 != 0) goto L_0x0399
            r59.f()     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = r14.d     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r6)     // Catch:{ all -> 0x0b95 }
            r10 = -1
            int r11 = r6.hashCode()     // Catch:{ all -> 0x0b95 }
            r15 = 94660(0x171c4, float:1.32647E-40)
            if (r11 == r15) goto L_0x0383
            r15 = 95025(0x17331, float:1.33158E-40)
            if (r11 == r15) goto L_0x0379
            r15 = 95027(0x17333, float:1.33161E-40)
            if (r11 == r15) goto L_0x036f
            goto L_0x038c
        L_0x036f:
            java.lang.String r11 = "_ui"
            boolean r6 = r6.equals(r11)     // Catch:{ all -> 0x0b95 }
            if (r6 == 0) goto L_0x038c
            r10 = 1
            goto L_0x038c
        L_0x0379:
            java.lang.String r11 = "_ug"
            boolean r6 = r6.equals(r11)     // Catch:{ all -> 0x0b95 }
            if (r6 == 0) goto L_0x038c
            r10 = 2
            goto L_0x038c
        L_0x0383:
            java.lang.String r11 = "_in"
            boolean r6 = r6.equals(r11)     // Catch:{ all -> 0x0b95 }
            if (r6 == 0) goto L_0x038c
            r10 = 0
        L_0x038c:
            switch(r10) {
                case 0: goto L_0x0391;
                case 1: goto L_0x0391;
                case 2: goto L_0x0391;
                default: goto L_0x038f;
            }     // Catch:{ all -> 0x0b95 }
        L_0x038f:
            r6 = 0
            goto L_0x0392
        L_0x0391:
            r6 = 1
        L_0x0392:
            if (r6 == 0) goto L_0x0395
            goto L_0x0399
        L_0x0395:
            r28 = r7
            goto L_0x0599
        L_0x0399:
            com.google.android.gms.internal.measurement.fv[] r6 = r14.c     // Catch:{ all -> 0x0b95 }
            if (r6 != 0) goto L_0x03a2
            r6 = 0
            com.google.android.gms.internal.measurement.fv[] r10 = new com.google.android.gms.internal.measurement.fv[r6]     // Catch:{ all -> 0x0b95 }
            r14.c = r10     // Catch:{ all -> 0x0b95 }
        L_0x03a2:
            com.google.android.gms.internal.measurement.fv[] r6 = r14.c     // Catch:{ all -> 0x0b95 }
            int r10 = r6.length     // Catch:{ all -> 0x0b95 }
            r11 = 0
            r15 = 0
            r16 = 0
        L_0x03a9:
            if (r11 >= r10) goto L_0x03ea
            r25 = r10
            r10 = r6[r11]     // Catch:{ all -> 0x0b95 }
            r26 = r6
            java.lang.String r6 = "_c"
            r27 = r8
            java.lang.String r8 = r10.c     // Catch:{ all -> 0x0b95 }
            boolean r6 = r6.equals(r8)     // Catch:{ all -> 0x0b95 }
            if (r6 == 0) goto L_0x03c9
            r28 = r7
            r6 = 1
            java.lang.Long r8 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b95 }
            r10.e = r8     // Catch:{ all -> 0x0b95 }
            r15 = 1
            goto L_0x03df
        L_0x03c9:
            r28 = r7
            java.lang.String r6 = "_r"
            java.lang.String r7 = r10.c     // Catch:{ all -> 0x0b95 }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x0b95 }
            if (r6 == 0) goto L_0x03df
            r6 = 1
            java.lang.Long r8 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b95 }
            r10.e = r8     // Catch:{ all -> 0x0b95 }
            r16 = 1
        L_0x03df:
            int r11 = r11 + 1
            r10 = r25
            r6 = r26
            r8 = r27
            r7 = r28
            goto L_0x03a9
        L_0x03ea:
            r28 = r7
            r27 = r8
            if (r15 != 0) goto L_0x0432
            if (r5 == 0) goto L_0x0432
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.aq r6 = r6.r()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.as r6 = r6.w()     // Catch:{ all -> 0x0b95 }
            java.lang.String r7 = "Marking event as conversion"
            com.google.android.gms.internal.measurement.bu r8 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.ao r8 = r8.l()     // Catch:{ all -> 0x0b95 }
            java.lang.String r10 = r14.d     // Catch:{ all -> 0x0b95 }
            java.lang.String r8 = r8.a(r10)     // Catch:{ all -> 0x0b95 }
            r6.a(r7, r8)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r6 = r14.c     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r7 = r14.c     // Catch:{ all -> 0x0b95 }
            int r7 = r7.length     // Catch:{ all -> 0x0b95 }
            r8 = 1
            int r7 = r7 + r8
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r7)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r6 = (com.google.android.gms.internal.measurement.fv[]) r6     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv r7 = new com.google.android.gms.internal.measurement.fv     // Catch:{ all -> 0x0b95 }
            r7.<init>()     // Catch:{ all -> 0x0b95 }
            java.lang.String r8 = "_c"
            r7.c = r8     // Catch:{ all -> 0x0b95 }
            r10 = 1
            java.lang.Long r8 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x0b95 }
            r7.e = r8     // Catch:{ all -> 0x0b95 }
            int r8 = r6.length     // Catch:{ all -> 0x0b95 }
            r10 = 1
            int r8 = r8 - r10
            r6[r8] = r7     // Catch:{ all -> 0x0b95 }
            r14.c = r6     // Catch:{ all -> 0x0b95 }
        L_0x0432:
            if (r16 != 0) goto L_0x0474
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.aq r6 = r6.r()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.as r6 = r6.w()     // Catch:{ all -> 0x0b95 }
            java.lang.String r7 = "Marking event as real-time"
            com.google.android.gms.internal.measurement.bu r8 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.ao r8 = r8.l()     // Catch:{ all -> 0x0b95 }
            java.lang.String r10 = r14.d     // Catch:{ all -> 0x0b95 }
            java.lang.String r8 = r8.a(r10)     // Catch:{ all -> 0x0b95 }
            r6.a(r7, r8)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r6 = r14.c     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r7 = r14.c     // Catch:{ all -> 0x0b95 }
            int r7 = r7.length     // Catch:{ all -> 0x0b95 }
            r8 = 1
            int r7 = r7 + r8
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r7)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r6 = (com.google.android.gms.internal.measurement.fv[]) r6     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv r7 = new com.google.android.gms.internal.measurement.fv     // Catch:{ all -> 0x0b95 }
            r7.<init>()     // Catch:{ all -> 0x0b95 }
            java.lang.String r8 = "_r"
            r7.c = r8     // Catch:{ all -> 0x0b95 }
            r10 = 1
            java.lang.Long r8 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x0b95 }
            r7.e = r8     // Catch:{ all -> 0x0b95 }
            int r8 = r6.length     // Catch:{ all -> 0x0b95 }
            r10 = 1
            int r8 = r8 - r10
            r6[r8] = r7     // Catch:{ all -> 0x0b95 }
            r14.c = r6     // Catch:{ all -> 0x0b95 }
        L_0x0474:
            com.google.android.gms.internal.measurement.z r29 = r59.d()     // Catch:{ all -> 0x0b95 }
            long r30 = r59.w()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fx r6 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = r6.q     // Catch:{ all -> 0x0b95 }
            r33 = 0
            r34 = 0
            r35 = 0
            r36 = 0
            r37 = 1
            r32 = r6
            com.google.android.gms.internal.measurement.aa r6 = r29.a(r30, r32, r33, r34, r35, r36, r37)     // Catch:{ all -> 0x0b95 }
            long r6 = r6.e     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.bu r8 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.w r8 = r8.b()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fx r10 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r10 = r10.q     // Catch:{ all -> 0x0b95 }
            int r8 = r8.a(r10)     // Catch:{ all -> 0x0b95 }
            long r10 = (long) r8     // Catch:{ all -> 0x0b95 }
            int r8 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r8 <= 0) goto L_0x04dd
            r6 = 0
        L_0x04a6:
            com.google.android.gms.internal.measurement.fv[] r7 = r14.c     // Catch:{ all -> 0x0b95 }
            int r7 = r7.length     // Catch:{ all -> 0x0b95 }
            if (r6 >= r7) goto L_0x04da
            java.lang.String r7 = "_r"
            com.google.android.gms.internal.measurement.fv[] r8 = r14.c     // Catch:{ all -> 0x0b95 }
            r8 = r8[r6]     // Catch:{ all -> 0x0b95 }
            java.lang.String r8 = r8.c     // Catch:{ all -> 0x0b95 }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x0b95 }
            if (r7 == 0) goto L_0x04d7
            com.google.android.gms.internal.measurement.fv[] r7 = r14.c     // Catch:{ all -> 0x0b95 }
            int r7 = r7.length     // Catch:{ all -> 0x0b95 }
            r8 = 1
            int r7 = r7 - r8
            com.google.android.gms.internal.measurement.fv[] r7 = new com.google.android.gms.internal.measurement.fv[r7]     // Catch:{ all -> 0x0b95 }
            if (r6 <= 0) goto L_0x04c8
            com.google.android.gms.internal.measurement.fv[] r8 = r14.c     // Catch:{ all -> 0x0b95 }
            r10 = 0
            java.lang.System.arraycopy(r8, r10, r7, r10, r6)     // Catch:{ all -> 0x0b95 }
        L_0x04c8:
            int r8 = r7.length     // Catch:{ all -> 0x0b95 }
            if (r6 >= r8) goto L_0x04d4
            com.google.android.gms.internal.measurement.fv[] r8 = r14.c     // Catch:{ all -> 0x0b95 }
            int r10 = r6 + 1
            int r11 = r7.length     // Catch:{ all -> 0x0b95 }
            int r11 = r11 - r6
            java.lang.System.arraycopy(r8, r10, r7, r6, r11)     // Catch:{ all -> 0x0b95 }
        L_0x04d4:
            r14.c = r7     // Catch:{ all -> 0x0b95 }
            goto L_0x04da
        L_0x04d7:
            int r6 = r6 + 1
            goto L_0x04a6
        L_0x04da:
            r8 = r27
            goto L_0x04de
        L_0x04dd:
            r8 = 1
        L_0x04de:
            java.lang.String r6 = r14.d     // Catch:{ all -> 0x0b95 }
            boolean r6 = com.google.android.gms.internal.measurement.fg.a(r6)     // Catch:{ all -> 0x0b95 }
            if (r6 == 0) goto L_0x0599
            if (r5 == 0) goto L_0x0599
            com.google.android.gms.internal.measurement.z r29 = r59.d()     // Catch:{ all -> 0x0b95 }
            long r30 = r59.w()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fx r5 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r5 = r5.q     // Catch:{ all -> 0x0b95 }
            r33 = 0
            r34 = 0
            r35 = 1
            r36 = 0
            r37 = 0
            r32 = r5
            com.google.android.gms.internal.measurement.aa r5 = r29.a(r30, r32, r33, r34, r35, r36, r37)     // Catch:{ all -> 0x0b95 }
            long r5 = r5.c     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.bu r7 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.w r7 = r7.b()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fx r10 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r10 = r10.q     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.ak$a<java.lang.Integer> r11 = com.google.android.gms.internal.measurement.ak.v     // Catch:{ all -> 0x0b95 }
            int r7 = r7.b(r10, r11)     // Catch:{ all -> 0x0b95 }
            long r10 = (long) r7     // Catch:{ all -> 0x0b95 }
            int r7 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r7 <= 0) goto L_0x0599
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.aq r5 = r5.r()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.as r5 = r5.i()     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = "Too many conversions. Not logging as conversion. appId"
            com.google.android.gms.internal.measurement.fx r7 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r7 = r7.q     // Catch:{ all -> 0x0b95 }
            java.lang.Object r7 = com.google.android.gms.internal.measurement.aq.a(r7)     // Catch:{ all -> 0x0b95 }
            r5.a(r6, r7)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r5 = r14.c     // Catch:{ all -> 0x0b95 }
            int r6 = r5.length     // Catch:{ all -> 0x0b95 }
            r7 = 0
            r10 = 0
            r11 = 0
        L_0x0538:
            if (r7 >= r6) goto L_0x055e
            r15 = r5[r7]     // Catch:{ all -> 0x0b95 }
            r38 = r5
            java.lang.String r5 = "_c"
            r39 = r6
            java.lang.String r6 = r15.c     // Catch:{ all -> 0x0b95 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x0b95 }
            if (r5 == 0) goto L_0x054c
            r11 = r15
            goto L_0x0557
        L_0x054c:
            java.lang.String r5 = "_err"
            java.lang.String r6 = r15.c     // Catch:{ all -> 0x0b95 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x0b95 }
            if (r5 == 0) goto L_0x0557
            r10 = 1
        L_0x0557:
            int r7 = r7 + 1
            r5 = r38
            r6 = r39
            goto L_0x0538
        L_0x055e:
            if (r10 == 0) goto L_0x0573
            if (r11 == 0) goto L_0x0573
            com.google.android.gms.internal.measurement.fv[] r5 = r14.c     // Catch:{ all -> 0x0b95 }
            r6 = 1
            com.google.android.gms.internal.measurement.fv[] r7 = new com.google.android.gms.internal.measurement.fv[r6]     // Catch:{ all -> 0x0b95 }
            r6 = 0
            r7[r6] = r11     // Catch:{ all -> 0x0b95 }
            java.lang.Object[] r5 = com.google.android.gms.common.util.ArrayUtils.removeAll((T[]) r5, (T[]) r7)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r5 = (com.google.android.gms.internal.measurement.fv[]) r5     // Catch:{ all -> 0x0b95 }
            r14.c = r5     // Catch:{ all -> 0x0b95 }
            goto L_0x0599
        L_0x0573:
            if (r11 == 0) goto L_0x0582
            java.lang.String r5 = "_err"
            r11.c = r5     // Catch:{ all -> 0x0b95 }
            r5 = 10
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0b95 }
            r11.e = r5     // Catch:{ all -> 0x0b95 }
            goto L_0x0599
        L_0x0582:
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.aq r5 = r5.r()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.as r5 = r5.h_()     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = "Did not find conversion parameter. appId"
            com.google.android.gms.internal.measurement.fx r7 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r7 = r7.q     // Catch:{ all -> 0x0b95 }
            java.lang.Object r7 = com.google.android.gms.internal.measurement.aq.a(r7)     // Catch:{ all -> 0x0b95 }
            r5.a(r6, r7)     // Catch:{ all -> 0x0b95 }
        L_0x0599:
            if (r4 == 0) goto L_0x05f0
            java.lang.String r5 = "_e"
            java.lang.String r6 = r14.d     // Catch:{ all -> 0x0b95 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x0b95 }
            if (r5 == 0) goto L_0x05f0
            com.google.android.gms.internal.measurement.fv[] r5 = r14.c     // Catch:{ all -> 0x0b95 }
            if (r5 == 0) goto L_0x05db
            com.google.android.gms.internal.measurement.fv[] r5 = r14.c     // Catch:{ all -> 0x0b95 }
            int r5 = r5.length     // Catch:{ all -> 0x0b95 }
            if (r5 != 0) goto L_0x05af
            goto L_0x05db
        L_0x05af:
            r59.f()     // Catch:{ all -> 0x0b95 }
            java.lang.String r5 = "_et"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.fe.b(r14, r5)     // Catch:{ all -> 0x0b95 }
            java.lang.Long r5 = (java.lang.Long) r5     // Catch:{ all -> 0x0b95 }
            if (r5 != 0) goto L_0x05d4
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.aq r5 = r5.r()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.as r5 = r5.i()     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = "Engagement event does not include duration. appId"
            com.google.android.gms.internal.measurement.fx r7 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r7 = r7.q     // Catch:{ all -> 0x0b95 }
            java.lang.Object r7 = com.google.android.gms.internal.measurement.aq.a(r7)     // Catch:{ all -> 0x0b95 }
        L_0x05d0:
            r5.a(r6, r7)     // Catch:{ all -> 0x0b95 }
            goto L_0x05f0
        L_0x05d4:
            long r5 = r5.longValue()     // Catch:{ all -> 0x0b95 }
            long r10 = r12 + r5
            goto L_0x05f1
        L_0x05db:
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.aq r5 = r5.r()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.as r5 = r5.i()     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = "Engagement event does not contain any parameters. appId"
            com.google.android.gms.internal.measurement.fx r7 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r7 = r7.q     // Catch:{ all -> 0x0b95 }
            java.lang.Object r7 = com.google.android.gms.internal.measurement.aq.a(r7)     // Catch:{ all -> 0x0b95 }
            goto L_0x05d0
        L_0x05f0:
            r10 = r12
        L_0x05f1:
            com.google.android.gms.internal.measurement.fu[] r5 = r3.d     // Catch:{ all -> 0x0b95 }
            int r6 = r9 + 1
            r5[r9] = r14     // Catch:{ all -> 0x0b95 }
            r9 = r6
            r12 = r10
        L_0x05f9:
            int r7 = r28 + 1
            r10 = 0
            r11 = 1
            goto L_0x02b6
        L_0x05ff:
            r27 = r8
            java.util.List<com.google.android.gms.internal.measurement.fu> r5 = r2.c     // Catch:{ all -> 0x0b95 }
            int r5 = r5.size()     // Catch:{ all -> 0x0b95 }
            if (r9 >= r5) goto L_0x0613
            com.google.android.gms.internal.measurement.fu[] r5 = r3.d     // Catch:{ all -> 0x0b95 }
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r5, r9)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fu[] r5 = (com.google.android.gms.internal.measurement.fu[]) r5     // Catch:{ all -> 0x0b95 }
            r3.d = r5     // Catch:{ all -> 0x0b95 }
        L_0x0613:
            if (r4 == 0) goto L_0x06e2
            com.google.android.gms.internal.measurement.z r4 = r59.d()     // Catch:{ all -> 0x0b95 }
            java.lang.String r5 = r3.q     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = "_lte"
            com.google.android.gms.internal.measurement.ff r4 = r4.c(r5, r6)     // Catch:{ all -> 0x0b95 }
            if (r4 == 0) goto L_0x064e
            java.lang.Object r5 = r4.e     // Catch:{ all -> 0x0b95 }
            if (r5 != 0) goto L_0x0628
            goto L_0x064e
        L_0x0628:
            com.google.android.gms.internal.measurement.ff r5 = new com.google.android.gms.internal.measurement.ff     // Catch:{ all -> 0x0b95 }
            java.lang.String r15 = r3.q     // Catch:{ all -> 0x0b95 }
            java.lang.String r16 = "auto"
            java.lang.String r17 = "_lte"
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.common.util.Clock r6 = r6.m()     // Catch:{ all -> 0x0b95 }
            long r18 = r6.currentTimeMillis()     // Catch:{ all -> 0x0b95 }
            java.lang.Object r4 = r4.e     // Catch:{ all -> 0x0b95 }
            java.lang.Long r4 = (java.lang.Long) r4     // Catch:{ all -> 0x0b95 }
            long r6 = r4.longValue()     // Catch:{ all -> 0x0b95 }
            long r8 = r6 + r12
            java.lang.Long r20 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x0b95 }
            r14 = r5
            r14.<init>(r15, r16, r17, r18, r20)     // Catch:{ all -> 0x0b95 }
            r4 = r5
            goto L_0x066b
        L_0x064e:
            com.google.android.gms.internal.measurement.ff r4 = new com.google.android.gms.internal.measurement.ff     // Catch:{ all -> 0x0b95 }
            java.lang.String r5 = r3.q     // Catch:{ all -> 0x0b95 }
            java.lang.String r30 = "auto"
            java.lang.String r31 = "_lte"
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.common.util.Clock r6 = r6.m()     // Catch:{ all -> 0x0b95 }
            long r32 = r6.currentTimeMillis()     // Catch:{ all -> 0x0b95 }
            java.lang.Long r34 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0b95 }
            r28 = r4
            r29 = r5
            r28.<init>(r29, r30, r31, r32, r34)     // Catch:{ all -> 0x0b95 }
        L_0x066b:
            com.google.android.gms.internal.measurement.ga r5 = new com.google.android.gms.internal.measurement.ga     // Catch:{ all -> 0x0b95 }
            r5.<init>()     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = "_lte"
            r5.d = r6     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.common.util.Clock r6 = r6.m()     // Catch:{ all -> 0x0b95 }
            long r6 = r6.currentTimeMillis()     // Catch:{ all -> 0x0b95 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b95 }
            r5.c = r6     // Catch:{ all -> 0x0b95 }
            java.lang.Object r6 = r4.e     // Catch:{ all -> 0x0b95 }
            java.lang.Long r6 = (java.lang.Long) r6     // Catch:{ all -> 0x0b95 }
            r5.f = r6     // Catch:{ all -> 0x0b95 }
            r6 = 0
        L_0x068b:
            com.google.android.gms.internal.measurement.ga[] r7 = r3.e     // Catch:{ all -> 0x0b95 }
            int r7 = r7.length     // Catch:{ all -> 0x0b95 }
            if (r6 >= r7) goto L_0x06a7
            java.lang.String r7 = "_lte"
            com.google.android.gms.internal.measurement.ga[] r8 = r3.e     // Catch:{ all -> 0x0b95 }
            r8 = r8[r6]     // Catch:{ all -> 0x0b95 }
            java.lang.String r8 = r8.d     // Catch:{ all -> 0x0b95 }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x0b95 }
            if (r7 == 0) goto L_0x06a4
            com.google.android.gms.internal.measurement.ga[] r7 = r3.e     // Catch:{ all -> 0x0b95 }
            r7[r6] = r5     // Catch:{ all -> 0x0b95 }
            r6 = 1
            goto L_0x06a8
        L_0x06a4:
            int r6 = r6 + 1
            goto L_0x068b
        L_0x06a7:
            r6 = 0
        L_0x06a8:
            if (r6 != 0) goto L_0x06c4
            com.google.android.gms.internal.measurement.ga[] r6 = r3.e     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.ga[] r7 = r3.e     // Catch:{ all -> 0x0b95 }
            int r7 = r7.length     // Catch:{ all -> 0x0b95 }
            r8 = 1
            int r7 = r7 + r8
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r7)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.ga[] r6 = (com.google.android.gms.internal.measurement.ga[]) r6     // Catch:{ all -> 0x0b95 }
            r3.e = r6     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.ga[] r6 = r3.e     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fx r7 = r2.a     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.ga[] r7 = r7.e     // Catch:{ all -> 0x0b95 }
            int r7 = r7.length     // Catch:{ all -> 0x0b95 }
            r8 = 1
            int r7 = r7 - r8
            r6[r7] = r5     // Catch:{ all -> 0x0b95 }
        L_0x06c4:
            r5 = 0
            int r7 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x06e2
            com.google.android.gms.internal.measurement.z r5 = r59.d()     // Catch:{ all -> 0x0b95 }
            r5.a(r4)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.aq r5 = r5.r()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.as r5 = r5.v()     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = "Updated lifetime engagement user property with value. Value"
            java.lang.Object r4 = r4.e     // Catch:{ all -> 0x0b95 }
            r5.a(r6, r4)     // Catch:{ all -> 0x0b95 }
        L_0x06e2:
            java.lang.String r4 = r3.q     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.ga[] r5 = r3.e     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fu[] r6 = r3.d     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fs[] r4 = r1.a(r4, r5, r6)     // Catch:{ all -> 0x0b95 }
            r3.C = r4     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.bu r4 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.w r4 = r4.b()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fx r5 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r5 = r5.q     // Catch:{ all -> 0x0b95 }
            boolean r4 = r4.d(r5)     // Catch:{ all -> 0x0b95 }
            if (r4 == 0) goto L_0x09ba
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ all -> 0x0b95 }
            r4.<init>()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fu[] r5 = r3.d     // Catch:{ all -> 0x0b95 }
            int r5 = r5.length     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fu[] r5 = new com.google.android.gms.internal.measurement.fu[r5]     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fg r6 = r6.k()     // Catch:{ all -> 0x0b95 }
            java.security.SecureRandom r6 = r6.h()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fu[] r7 = r3.d     // Catch:{ all -> 0x0b95 }
            int r8 = r7.length     // Catch:{ all -> 0x0b95 }
            r9 = 0
            r10 = 0
        L_0x0717:
            if (r9 >= r8) goto L_0x0988
            r11 = r7[r9]     // Catch:{ all -> 0x0b95 }
            java.lang.String r12 = r11.d     // Catch:{ all -> 0x0b95 }
            java.lang.String r13 = "_ep"
            boolean r12 = r12.equals(r13)     // Catch:{ all -> 0x0b95 }
            if (r12 == 0) goto L_0x07a4
            r59.f()     // Catch:{ all -> 0x0b95 }
            java.lang.String r12 = "_en"
            java.lang.Object r12 = com.google.android.gms.internal.measurement.fe.b(r11, r12)     // Catch:{ all -> 0x0b95 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x0b95 }
            java.lang.Object r13 = r4.get(r12)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.ai r13 = (com.google.android.gms.internal.measurement.ai) r13     // Catch:{ all -> 0x0b95 }
            if (r13 != 0) goto L_0x0747
            com.google.android.gms.internal.measurement.z r13 = r59.d()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fx r14 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r14 = r14.q     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.ai r13 = r13.a(r14, r12)     // Catch:{ all -> 0x0b95 }
            r4.put(r12, r13)     // Catch:{ all -> 0x0b95 }
        L_0x0747:
            java.lang.Long r12 = r13.g     // Catch:{ all -> 0x0b95 }
            if (r12 != 0) goto L_0x079b
            java.lang.Long r12 = r13.h     // Catch:{ all -> 0x0b95 }
            long r14 = r12.longValue()     // Catch:{ all -> 0x0b95 }
            r16 = 1
            int r12 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r12 <= 0) goto L_0x0766
            r59.f()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r12 = r11.c     // Catch:{ all -> 0x0b95 }
            java.lang.String r14 = "_sr"
            java.lang.Long r15 = r13.h     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r12 = com.google.android.gms.internal.measurement.fe.a(r12, r14, r15)     // Catch:{ all -> 0x0b95 }
            r11.c = r12     // Catch:{ all -> 0x0b95 }
        L_0x0766:
            java.lang.Boolean r12 = r13.i     // Catch:{ all -> 0x0b95 }
            if (r12 == 0) goto L_0x0788
            java.lang.Boolean r12 = r13.i     // Catch:{ all -> 0x0b95 }
            boolean r12 = r12.booleanValue()     // Catch:{ all -> 0x0b95 }
            if (r12 == 0) goto L_0x0788
            r59.f()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r12 = r11.c     // Catch:{ all -> 0x0b95 }
            java.lang.String r13 = "_efs"
            r40 = r7
            r14 = 1
            java.lang.Long r7 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r7 = com.google.android.gms.internal.measurement.fe.a(r12, r13, r7)     // Catch:{ all -> 0x0b95 }
            r11.c = r7     // Catch:{ all -> 0x0b95 }
            goto L_0x078a
        L_0x0788:
            r40 = r7
        L_0x078a:
            int r7 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0b95 }
            r57 = r2
            r58 = r3
            r56 = r6
            r10 = r7
        L_0x0795:
            r41 = r8
        L_0x0797:
            r14 = 1
            goto L_0x097a
        L_0x079b:
            r40 = r7
            r57 = r2
            r58 = r3
            r56 = r6
            goto L_0x0795
        L_0x07a4:
            r40 = r7
            java.lang.String r7 = "_dbg"
            r12 = 1
            java.lang.Long r14 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0b95 }
            boolean r12 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x0b95 }
            if (r12 != 0) goto L_0x07f8
            if (r14 != 0) goto L_0x07b7
            goto L_0x07f8
        L_0x07b7:
            com.google.android.gms.internal.measurement.fv[] r12 = r11.c     // Catch:{ all -> 0x0b95 }
            int r13 = r12.length     // Catch:{ all -> 0x0b95 }
            r15 = 0
        L_0x07bb:
            if (r15 >= r13) goto L_0x07f8
            r41 = r8
            r8 = r12[r15]     // Catch:{ all -> 0x0b95 }
            r42 = r12
            java.lang.String r12 = r8.c     // Catch:{ all -> 0x0b95 }
            boolean r12 = r7.equals(r12)     // Catch:{ all -> 0x0b95 }
            if (r12 == 0) goto L_0x07f1
            boolean r7 = r14 instanceof java.lang.Long     // Catch:{ all -> 0x0b95 }
            if (r7 == 0) goto L_0x07d7
            java.lang.Long r7 = r8.e     // Catch:{ all -> 0x0b95 }
            boolean r7 = r14.equals(r7)     // Catch:{ all -> 0x0b95 }
            if (r7 != 0) goto L_0x07ef
        L_0x07d7:
            boolean r7 = r14 instanceof java.lang.String     // Catch:{ all -> 0x0b95 }
            if (r7 == 0) goto L_0x07e3
            java.lang.String r7 = r8.d     // Catch:{ all -> 0x0b95 }
            boolean r7 = r14.equals(r7)     // Catch:{ all -> 0x0b95 }
            if (r7 != 0) goto L_0x07ef
        L_0x07e3:
            boolean r7 = r14 instanceof java.lang.Double     // Catch:{ all -> 0x0b95 }
            if (r7 == 0) goto L_0x07fa
            java.lang.Double r7 = r8.f     // Catch:{ all -> 0x0b95 }
            boolean r7 = r14.equals(r7)     // Catch:{ all -> 0x0b95 }
            if (r7 == 0) goto L_0x07fa
        L_0x07ef:
            r7 = 1
            goto L_0x07fb
        L_0x07f1:
            int r15 = r15 + 1
            r8 = r41
            r12 = r42
            goto L_0x07bb
        L_0x07f8:
            r41 = r8
        L_0x07fa:
            r7 = 0
        L_0x07fb:
            if (r7 != 0) goto L_0x080c
            com.google.android.gms.internal.measurement.bp r7 = r59.p()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fx r8 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r8 = r8.q     // Catch:{ all -> 0x0b95 }
            java.lang.String r12 = r11.d     // Catch:{ all -> 0x0b95 }
            int r7 = r7.d(r8, r12)     // Catch:{ all -> 0x0b95 }
            goto L_0x080d
        L_0x080c:
            r7 = 1
        L_0x080d:
            if (r7 > 0) goto L_0x0831
            com.google.android.gms.internal.measurement.bu r8 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.aq r8 = r8.r()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.as r8 = r8.i()     // Catch:{ all -> 0x0b95 }
            java.lang.String r12 = "Sample rate must be positive. event, rate"
            java.lang.String r13 = r11.d     // Catch:{ all -> 0x0b95 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x0b95 }
            r8.a(r12, r13, r7)     // Catch:{ all -> 0x0b95 }
            int r7 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0b95 }
        L_0x0828:
            r57 = r2
            r58 = r3
            r56 = r6
            r10 = r7
            goto L_0x0797
        L_0x0831:
            java.lang.String r8 = r11.d     // Catch:{ all -> 0x0b95 }
            java.lang.Object r8 = r4.get(r8)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.ai r8 = (com.google.android.gms.internal.measurement.ai) r8     // Catch:{ all -> 0x0b95 }
            if (r8 != 0) goto L_0x0883
            com.google.android.gms.internal.measurement.z r8 = r59.d()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fx r12 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r12 = r12.q     // Catch:{ all -> 0x0b95 }
            java.lang.String r13 = r11.d     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.ai r8 = r8.a(r12, r13)     // Catch:{ all -> 0x0b95 }
            if (r8 != 0) goto L_0x0883
            com.google.android.gms.internal.measurement.bu r8 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.aq r8 = r8.r()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.as r8 = r8.i()     // Catch:{ all -> 0x0b95 }
            java.lang.String r12 = "Event being bundled has no eventAggregate. appId, eventName"
            com.google.android.gms.internal.measurement.fx r13 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r13 = r13.q     // Catch:{ all -> 0x0b95 }
            java.lang.String r14 = r11.d     // Catch:{ all -> 0x0b95 }
            r8.a(r12, r13, r14)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.ai r8 = new com.google.android.gms.internal.measurement.ai     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fx r12 = r2.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r12 = r12.q     // Catch:{ all -> 0x0b95 }
            java.lang.String r13 = r11.d     // Catch:{ all -> 0x0b95 }
            r45 = 1
            r47 = 1
            java.lang.Long r14 = r11.e     // Catch:{ all -> 0x0b95 }
            long r49 = r14.longValue()     // Catch:{ all -> 0x0b95 }
            r51 = 0
            r53 = 0
            r54 = 0
            r55 = 0
            r42 = r8
            r43 = r12
            r44 = r13
            r42.<init>(r43, r44, r45, r47, r49, r51, r53, r54, r55)     // Catch:{ all -> 0x0b95 }
        L_0x0883:
            r59.f()     // Catch:{ all -> 0x0b95 }
            java.lang.String r12 = "_eid"
            java.lang.Object r12 = com.google.android.gms.internal.measurement.fe.b(r11, r12)     // Catch:{ all -> 0x0b95 }
            java.lang.Long r12 = (java.lang.Long) r12     // Catch:{ all -> 0x0b95 }
            if (r12 == 0) goto L_0x0892
            r13 = 1
            goto L_0x0893
        L_0x0892:
            r13 = 0
        L_0x0893:
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r13)     // Catch:{ all -> 0x0b95 }
            r14 = 1
            if (r7 != r14) goto L_0x08bc
            int r7 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0b95 }
            boolean r10 = r13.booleanValue()     // Catch:{ all -> 0x0b95 }
            if (r10 == 0) goto L_0x0828
            java.lang.Long r10 = r8.g     // Catch:{ all -> 0x0b95 }
            if (r10 != 0) goto L_0x08b0
            java.lang.Long r10 = r8.h     // Catch:{ all -> 0x0b95 }
            if (r10 != 0) goto L_0x08b0
            java.lang.Boolean r10 = r8.i     // Catch:{ all -> 0x0b95 }
            if (r10 == 0) goto L_0x0828
        L_0x08b0:
            r10 = 0
            com.google.android.gms.internal.measurement.ai r8 = r8.a(r10, r10, r10)     // Catch:{ all -> 0x0b95 }
            java.lang.String r10 = r11.d     // Catch:{ all -> 0x0b95 }
            r4.put(r10, r8)     // Catch:{ all -> 0x0b95 }
            goto L_0x0828
        L_0x08bc:
            int r14 = r6.nextInt(r7)     // Catch:{ all -> 0x0b95 }
            if (r14 != 0) goto L_0x08ff
            r59.f()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r12 = r11.c     // Catch:{ all -> 0x0b95 }
            java.lang.String r14 = "_sr"
            r56 = r6
            long r6 = (long) r7     // Catch:{ all -> 0x0b95 }
            java.lang.Long r15 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r12 = com.google.android.gms.internal.measurement.fe.a(r12, r14, r15)     // Catch:{ all -> 0x0b95 }
            r11.c = r12     // Catch:{ all -> 0x0b95 }
            int r12 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0b95 }
            boolean r10 = r13.booleanValue()     // Catch:{ all -> 0x0b95 }
            if (r10 == 0) goto L_0x08e9
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b95 }
            r7 = 0
            com.google.android.gms.internal.measurement.ai r8 = r8.a(r7, r6, r7)     // Catch:{ all -> 0x0b95 }
        L_0x08e9:
            java.lang.String r6 = r11.d     // Catch:{ all -> 0x0b95 }
            java.lang.Long r7 = r11.e     // Catch:{ all -> 0x0b95 }
            long r10 = r7.longValue()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.ai r7 = r8.b(r10)     // Catch:{ all -> 0x0b95 }
            r4.put(r6, r7)     // Catch:{ all -> 0x0b95 }
            r57 = r2
            r58 = r3
            r10 = r12
            goto L_0x0797
        L_0x08ff:
            r56 = r6
            long r14 = r8.f     // Catch:{ all -> 0x0b95 }
            java.lang.Long r6 = r11.e     // Catch:{ all -> 0x0b95 }
            long r16 = r6.longValue()     // Catch:{ all -> 0x0b95 }
            r57 = r2
            r58 = r3
            long r2 = r16 - r14
            long r2 = java.lang.Math.abs(r2)     // Catch:{ all -> 0x0b95 }
            r14 = 86400000(0x5265c00, double:4.2687272E-316)
            int r6 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r6 < 0) goto L_0x0968
            r59.f()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r2 = r11.c     // Catch:{ all -> 0x0b95 }
            java.lang.String r3 = "_efs"
            r14 = 1
            java.lang.Long r6 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r2 = com.google.android.gms.internal.measurement.fe.a(r2, r3, r6)     // Catch:{ all -> 0x0b95 }
            r11.c = r2     // Catch:{ all -> 0x0b95 }
            r59.f()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r2 = r11.c     // Catch:{ all -> 0x0b95 }
            java.lang.String r3 = "_sr"
            long r6 = (long) r7     // Catch:{ all -> 0x0b95 }
            java.lang.Long r12 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fv[] r2 = com.google.android.gms.internal.measurement.fe.a(r2, r3, r12)     // Catch:{ all -> 0x0b95 }
            r11.c = r2     // Catch:{ all -> 0x0b95 }
            int r2 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0b95 }
            boolean r3 = r13.booleanValue()     // Catch:{ all -> 0x0b95 }
            if (r3 == 0) goto L_0x0957
            java.lang.Long r3 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b95 }
            r6 = 1
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r6)     // Catch:{ all -> 0x0b95 }
            r6 = 0
            com.google.android.gms.internal.measurement.ai r8 = r8.a(r6, r3, r7)     // Catch:{ all -> 0x0b95 }
        L_0x0957:
            java.lang.String r3 = r11.d     // Catch:{ all -> 0x0b95 }
            java.lang.Long r6 = r11.e     // Catch:{ all -> 0x0b95 }
            long r6 = r6.longValue()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.ai r6 = r8.b(r6)     // Catch:{ all -> 0x0b95 }
            r4.put(r3, r6)     // Catch:{ all -> 0x0b95 }
            r10 = r2
            goto L_0x097a
        L_0x0968:
            r14 = 1
            boolean r2 = r13.booleanValue()     // Catch:{ all -> 0x0b95 }
            if (r2 == 0) goto L_0x097a
            java.lang.String r2 = r11.d     // Catch:{ all -> 0x0b95 }
            r3 = 0
            com.google.android.gms.internal.measurement.ai r6 = r8.a(r12, r3, r3)     // Catch:{ all -> 0x0b95 }
            r4.put(r2, r6)     // Catch:{ all -> 0x0b95 }
        L_0x097a:
            int r9 = r9 + 1
            r7 = r40
            r8 = r41
            r6 = r56
            r2 = r57
            r3 = r58
            goto L_0x0717
        L_0x0988:
            r57 = r2
            r2 = r3
            com.google.android.gms.internal.measurement.fu[] r3 = r2.d     // Catch:{ all -> 0x0b95 }
            int r3 = r3.length     // Catch:{ all -> 0x0b95 }
            if (r10 >= r3) goto L_0x0998
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r5, r10)     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fu[] r3 = (com.google.android.gms.internal.measurement.fu[]) r3     // Catch:{ all -> 0x0b95 }
            r2.d = r3     // Catch:{ all -> 0x0b95 }
        L_0x0998:
            java.util.Set r3 = r4.entrySet()     // Catch:{ all -> 0x0b95 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0b95 }
        L_0x09a0:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x0b95 }
            if (r4 == 0) goto L_0x09bd
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x0b95 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.z r5 = r59.d()     // Catch:{ all -> 0x0b95 }
            java.lang.Object r4 = r4.getValue()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.ai r4 = (com.google.android.gms.internal.measurement.ai) r4     // Catch:{ all -> 0x0b95 }
            r5.a(r4)     // Catch:{ all -> 0x0b95 }
            goto L_0x09a0
        L_0x09ba:
            r57 = r2
            r2 = r3
        L_0x09bd:
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0b95 }
            r2.g = r3     // Catch:{ all -> 0x0b95 }
            r3 = -9223372036854775808
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0b95 }
            r2.h = r3     // Catch:{ all -> 0x0b95 }
            r3 = 0
        L_0x09d1:
            com.google.android.gms.internal.measurement.fu[] r4 = r2.d     // Catch:{ all -> 0x0b95 }
            int r4 = r4.length     // Catch:{ all -> 0x0b95 }
            if (r3 >= r4) goto L_0x0a05
            com.google.android.gms.internal.measurement.fu[] r4 = r2.d     // Catch:{ all -> 0x0b95 }
            r4 = r4[r3]     // Catch:{ all -> 0x0b95 }
            java.lang.Long r5 = r4.e     // Catch:{ all -> 0x0b95 }
            long r5 = r5.longValue()     // Catch:{ all -> 0x0b95 }
            java.lang.Long r7 = r2.g     // Catch:{ all -> 0x0b95 }
            long r7 = r7.longValue()     // Catch:{ all -> 0x0b95 }
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 >= 0) goto L_0x09ee
            java.lang.Long r5 = r4.e     // Catch:{ all -> 0x0b95 }
            r2.g = r5     // Catch:{ all -> 0x0b95 }
        L_0x09ee:
            java.lang.Long r5 = r4.e     // Catch:{ all -> 0x0b95 }
            long r5 = r5.longValue()     // Catch:{ all -> 0x0b95 }
            java.lang.Long r7 = r2.h     // Catch:{ all -> 0x0b95 }
            long r7 = r7.longValue()     // Catch:{ all -> 0x0b95 }
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L_0x0a02
            java.lang.Long r4 = r4.e     // Catch:{ all -> 0x0b95 }
            r2.h = r4     // Catch:{ all -> 0x0b95 }
        L_0x0a02:
            int r3 = r3 + 1
            goto L_0x09d1
        L_0x0a05:
            r3 = r57
            com.google.android.gms.internal.measurement.fx r4 = r3.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r4 = r4.q     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.z r5 = r59.d()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.t r5 = r5.b(r4)     // Catch:{ all -> 0x0b95 }
            if (r5 != 0) goto L_0x0a2d
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.aq r5 = r5.r()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.as r5 = r5.h_()     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = "Bundling raw events w/o app info. appId"
            com.google.android.gms.internal.measurement.fx r7 = r3.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r7 = r7.q     // Catch:{ all -> 0x0b95 }
            java.lang.Object r7 = com.google.android.gms.internal.measurement.aq.a(r7)     // Catch:{ all -> 0x0b95 }
            r5.a(r6, r7)     // Catch:{ all -> 0x0b95 }
            goto L_0x0a89
        L_0x0a2d:
            com.google.android.gms.internal.measurement.fu[] r6 = r2.d     // Catch:{ all -> 0x0b95 }
            int r6 = r6.length     // Catch:{ all -> 0x0b95 }
            if (r6 <= 0) goto L_0x0a89
            long r6 = r5.h()     // Catch:{ all -> 0x0b95 }
            r8 = 0
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 == 0) goto L_0x0a41
            java.lang.Long r8 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b95 }
            goto L_0x0a42
        L_0x0a41:
            r8 = 0
        L_0x0a42:
            r2.j = r8     // Catch:{ all -> 0x0b95 }
            long r8 = r5.g()     // Catch:{ all -> 0x0b95 }
            r10 = 0
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 != 0) goto L_0x0a4f
            goto L_0x0a50
        L_0x0a4f:
            r6 = r8
        L_0x0a50:
            int r8 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r8 == 0) goto L_0x0a59
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b95 }
            goto L_0x0a5a
        L_0x0a59:
            r6 = 0
        L_0x0a5a:
            r2.i = r6     // Catch:{ all -> 0x0b95 }
            r5.r()     // Catch:{ all -> 0x0b95 }
            long r6 = r5.o()     // Catch:{ all -> 0x0b95 }
            int r6 = (int) r6     // Catch:{ all -> 0x0b95 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0b95 }
            r2.y = r6     // Catch:{ all -> 0x0b95 }
            java.lang.Long r6 = r2.g     // Catch:{ all -> 0x0b95 }
            long r6 = r6.longValue()     // Catch:{ all -> 0x0b95 }
            r5.a(r6)     // Catch:{ all -> 0x0b95 }
            java.lang.Long r6 = r2.h     // Catch:{ all -> 0x0b95 }
            long r6 = r6.longValue()     // Catch:{ all -> 0x0b95 }
            r5.b(r6)     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = r5.z()     // Catch:{ all -> 0x0b95 }
            r2.z = r6     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.z r6 = r59.d()     // Catch:{ all -> 0x0b95 }
            r6.a(r5)     // Catch:{ all -> 0x0b95 }
        L_0x0a89:
            com.google.android.gms.internal.measurement.fu[] r5 = r2.d     // Catch:{ all -> 0x0b95 }
            int r5 = r5.length     // Catch:{ all -> 0x0b95 }
            if (r5 <= 0) goto L_0x0adc
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x0b95 }
            r5.u()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.bp r5 = r59.p()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fx r6 = r3.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = r6.q     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.fq r5 = r5.a(r6)     // Catch:{ all -> 0x0b95 }
            if (r5 == 0) goto L_0x0aab
            java.lang.Long r6 = r5.c     // Catch:{ all -> 0x0b95 }
            if (r6 != 0) goto L_0x0aa6
            goto L_0x0aab
        L_0x0aa6:
            java.lang.Long r5 = r5.c     // Catch:{ all -> 0x0b95 }
        L_0x0aa8:
            r2.G = r5     // Catch:{ all -> 0x0b95 }
            goto L_0x0ad3
        L_0x0aab:
            com.google.android.gms.internal.measurement.fx r5 = r3.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r5 = r5.A     // Catch:{ all -> 0x0b95 }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x0b95 }
            if (r5 == 0) goto L_0x0abc
            r5 = -1
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0b95 }
            goto L_0x0aa8
        L_0x0abc:
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.aq r5 = r5.r()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.as r5 = r5.i()     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = "Did not find measurement config or missing version info. appId"
            com.google.android.gms.internal.measurement.fx r7 = r3.a     // Catch:{ all -> 0x0b95 }
            java.lang.String r7 = r7.q     // Catch:{ all -> 0x0b95 }
            java.lang.Object r7 = com.google.android.gms.internal.measurement.aq.a(r7)     // Catch:{ all -> 0x0b95 }
            r5.a(r6, r7)     // Catch:{ all -> 0x0b95 }
        L_0x0ad3:
            com.google.android.gms.internal.measurement.z r5 = r59.d()     // Catch:{ all -> 0x0b95 }
            r10 = r27
            r5.a(r2, r10)     // Catch:{ all -> 0x0b95 }
        L_0x0adc:
            com.google.android.gms.internal.measurement.z r2 = r59.d()     // Catch:{ all -> 0x0b95 }
            java.util.List<java.lang.Long> r3 = r3.b     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)     // Catch:{ all -> 0x0b95 }
            r2.d()     // Catch:{ all -> 0x0b95 }
            r2.I()     // Catch:{ all -> 0x0b95 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = "rowid in ("
            r5.<init>(r6)     // Catch:{ all -> 0x0b95 }
            r6 = 0
        L_0x0af3:
            int r7 = r3.size()     // Catch:{ all -> 0x0b95 }
            if (r6 >= r7) goto L_0x0b10
            if (r6 == 0) goto L_0x0b00
            java.lang.String r7 = ","
            r5.append(r7)     // Catch:{ all -> 0x0b95 }
        L_0x0b00:
            java.lang.Object r7 = r3.get(r6)     // Catch:{ all -> 0x0b95 }
            java.lang.Long r7 = (java.lang.Long) r7     // Catch:{ all -> 0x0b95 }
            long r7 = r7.longValue()     // Catch:{ all -> 0x0b95 }
            r5.append(r7)     // Catch:{ all -> 0x0b95 }
            int r6 = r6 + 1
            goto L_0x0af3
        L_0x0b10:
            java.lang.String r6 = ")"
            r5.append(r6)     // Catch:{ all -> 0x0b95 }
            android.database.sqlite.SQLiteDatabase r6 = r2.i()     // Catch:{ all -> 0x0b95 }
            java.lang.String r7 = "raw_events"
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0b95 }
            r8 = 0
            int r5 = r6.delete(r7, r5, r8)     // Catch:{ all -> 0x0b95 }
            int r6 = r3.size()     // Catch:{ all -> 0x0b95 }
            if (r5 == r6) goto L_0x0b43
            com.google.android.gms.internal.measurement.aq r2 = r2.r()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.as r2 = r2.h_()     // Catch:{ all -> 0x0b95 }
            java.lang.String r6 = "Deleted fewer rows from raw events table than expected"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0b95 }
            int r3 = r3.size()     // Catch:{ all -> 0x0b95 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0b95 }
            r2.a(r6, r5, r3)     // Catch:{ all -> 0x0b95 }
        L_0x0b43:
            com.google.android.gms.internal.measurement.z r2 = r59.d()     // Catch:{ all -> 0x0b95 }
            android.database.sqlite.SQLiteDatabase r3 = r2.i()     // Catch:{ all -> 0x0b95 }
            java.lang.String r5 = "delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)"
            r6 = 2
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x0b5a }
            r7 = 0
            r6[r7] = r4     // Catch:{ SQLiteException -> 0x0b5a }
            r7 = 1
            r6[r7] = r4     // Catch:{ SQLiteException -> 0x0b5a }
            r3.execSQL(r5, r6)     // Catch:{ SQLiteException -> 0x0b5a }
            goto L_0x0b6d
        L_0x0b5a:
            r0 = move-exception
            r3 = r0
            com.google.android.gms.internal.measurement.aq r2 = r2.r()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.as r2 = r2.h_()     // Catch:{ all -> 0x0b95 }
            java.lang.String r5 = "Failed to remove unused event metadata. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.aq.a(r4)     // Catch:{ all -> 0x0b95 }
            r2.a(r5, r4, r3)     // Catch:{ all -> 0x0b95 }
        L_0x0b6d:
            com.google.android.gms.internal.measurement.z r2 = r59.d()     // Catch:{ all -> 0x0b95 }
            r2.g()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.z r2 = r59.d()
            r2.h()
            r2 = 1
            return r2
        L_0x0b7d:
            com.google.android.gms.internal.measurement.z r2 = r59.d()     // Catch:{ all -> 0x0b95 }
            r2.g()     // Catch:{ all -> 0x0b95 }
            com.google.android.gms.internal.measurement.z r2 = r59.d()
            r2.h()
            r2 = 0
            return r2
        L_0x0b8d:
            r0 = move-exception
        L_0x0b8e:
            r2 = r0
        L_0x0b8f:
            if (r6 == 0) goto L_0x0b94
            r6.close()     // Catch:{ all -> 0x0b95 }
        L_0x0b94:
            throw r2     // Catch:{ all -> 0x0b95 }
        L_0x0b95:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.internal.measurement.z r3 = r59.d()
            r3.h()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.ey.a(java.lang.String, long):boolean");
    }

    private final boolean a(String str, zzex zzex) {
        long j2;
        ff ffVar;
        String string = zzex.zzahg.getString("currency");
        if ("ecommerce_purchase".equals(zzex.name)) {
            double doubleValue = zzex.zzahg.zzbk(ResponseConstants.VALUE).doubleValue() * 1000000.0d;
            if (doubleValue == 0.0d) {
                doubleValue = ((double) zzex.zzahg.getLong(ResponseConstants.VALUE).longValue()) * 1000000.0d;
            }
            if (doubleValue > 9.223372036854776E18d || doubleValue < -9.223372036854776E18d) {
                this.i.r().i().a("Data lost. Currency value is too big. appId", aq.a(str), Double.valueOf(doubleValue));
                return false;
            }
            j2 = Math.round(doubleValue);
        } else {
            j2 = zzex.zzahg.getLong(ResponseConstants.VALUE).longValue();
        }
        if (!TextUtils.isEmpty(string)) {
            String upperCase = string.toUpperCase(Locale.US);
            if (upperCase.matches("[A-Z]{3}")) {
                String valueOf = String.valueOf("_ltv_");
                String valueOf2 = String.valueOf(upperCase);
                String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                ff c2 = d().c(str, concat);
                if (c2 == null || !(c2.e instanceof Long)) {
                    z d2 = d();
                    int b2 = this.i.b().b(str, ak.M) - 1;
                    Preconditions.checkNotEmpty(str);
                    d2.d();
                    d2.I();
                    try {
                        d2.i().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(b2)});
                    } catch (SQLiteException e2) {
                        d2.r().h_().a("Error pruning currencies. appId", aq.a(str), e2);
                    }
                    ffVar = new ff(str, zzex.origin, concat, this.i.m().currentTimeMillis(), Long.valueOf(j2));
                } else {
                    ff ffVar2 = new ff(str, zzex.origin, concat, this.i.m().currentTimeMillis(), Long.valueOf(((Long) c2.e).longValue() + j2));
                    ffVar = ffVar2;
                }
                if (!d().a(ffVar)) {
                    this.i.r().h_().a("Too many unique user properties are set. Ignoring user property. appId", aq.a(str), this.i.l().c(ffVar.c), ffVar.e);
                    this.i.k().a(str, 9, (String) null, (String) null, 0);
                }
            }
        }
        return true;
    }

    private final fs[] a(String str, ga[] gaVarArr, fu[] fuVarArr) {
        Preconditions.checkNotEmpty(str);
        return e().a(str, fuVarArr, gaVarArr);
    }

    @WorkerThread
    private final Boolean b(t tVar) {
        try {
            if (tVar.j() != -2147483648L) {
                if (tVar.j() == ((long) Wrappers.packageManager(this.i.n()).getPackageInfo(tVar.b(), 0).versionCode)) {
                    return Boolean.valueOf(true);
                }
            } else {
                String str = Wrappers.packageManager(this.i.n()).getPackageInfo(tVar.b(), 0).versionName;
                if (tVar.i() != null && tVar.i().equals(str)) {
                    return Boolean.valueOf(true);
                }
            }
            return Boolean.valueOf(false);
        } catch (NameNotFoundException unused) {
            return null;
        }
    }

    private static void b(ex exVar) {
        if (exVar == null) {
            throw new IllegalStateException("Upload Component not created");
        } else if (!exVar.H()) {
            String valueOf = String.valueOf(exVar.getClass());
            StringBuilder sb = new StringBuilder(27 + String.valueOf(valueOf).length());
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:144:0x05c9 A[Catch:{ IOException -> 0x05cc, all -> 0x063d }] */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x05f7 A[Catch:{ IOException -> 0x05cc, all -> 0x063d }] */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void b(com.google.android.gms.internal.measurement.zzex r32, com.google.android.gms.internal.measurement.zzeb r33) {
        /*
            r31 = this;
            r1 = r31
            r2 = r32
            r3 = r33
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r33)
            java.lang.String r4 = r3.packageName
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)
            long r4 = java.lang.System.nanoTime()
            r31.v()
            r31.i()
            java.lang.String r15 = r3.packageName
            com.google.android.gms.internal.measurement.fe r6 = r31.f()
            boolean r6 = r6.a(r2, r3)
            if (r6 != 0) goto L_0x0025
            return
        L_0x0025:
            boolean r6 = r3.zzafk
            if (r6 != 0) goto L_0x002d
            r1.e(r3)
            return
        L_0x002d:
            com.google.android.gms.internal.measurement.bp r6 = r31.p()
            java.lang.String r7 = r2.name
            boolean r6 = r6.b(r15, r7)
            r14 = 0
            r13 = 1
            if (r6 == 0) goto L_0x00d8
            com.google.android.gms.internal.measurement.bu r3 = r1.i
            com.google.android.gms.internal.measurement.aq r3 = r3.r()
            com.google.android.gms.internal.measurement.as r3 = r3.i()
            java.lang.String r4 = "Dropping blacklisted event. appId"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.aq.a(r15)
            com.google.android.gms.internal.measurement.bu r6 = r1.i
            com.google.android.gms.internal.measurement.ao r6 = r6.l()
            java.lang.String r7 = r2.name
            java.lang.String r6 = r6.a(r7)
            r3.a(r4, r5, r6)
            com.google.android.gms.internal.measurement.bp r3 = r31.p()
            boolean r3 = r3.e(r15)
            if (r3 != 0) goto L_0x0070
            com.google.android.gms.internal.measurement.bp r3 = r31.p()
            boolean r3 = r3.f(r15)
            if (r3 == 0) goto L_0x006f
            goto L_0x0070
        L_0x006f:
            r13 = r14
        L_0x0070:
            if (r13 != 0) goto L_0x008d
            java.lang.String r3 = "_err"
            java.lang.String r4 = r2.name
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x008d
            com.google.android.gms.internal.measurement.bu r3 = r1.i
            com.google.android.gms.internal.measurement.fg r6 = r3.k()
            r8 = 11
            java.lang.String r9 = "_ev"
            java.lang.String r10 = r2.name
            r11 = 0
            r7 = r15
            r6.a(r7, r8, r9, r10, r11)
        L_0x008d:
            if (r13 == 0) goto L_0x00d7
            com.google.android.gms.internal.measurement.z r2 = r31.d()
            com.google.android.gms.internal.measurement.t r2 = r2.b(r15)
            if (r2 == 0) goto L_0x00d7
            long r3 = r2.q()
            long r5 = r2.p()
            long r3 = java.lang.Math.max(r3, r5)
            com.google.android.gms.internal.measurement.bu r5 = r1.i
            com.google.android.gms.common.util.Clock r5 = r5.m()
            long r5 = r5.currentTimeMillis()
            long r7 = r5 - r3
            long r3 = java.lang.Math.abs(r7)
            com.google.android.gms.internal.measurement.ak$a<java.lang.Long> r5 = com.google.android.gms.internal.measurement.ak.H
            java.lang.Object r5 = r5.b()
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x00d7
            com.google.android.gms.internal.measurement.bu r3 = r1.i
            com.google.android.gms.internal.measurement.aq r3 = r3.r()
            com.google.android.gms.internal.measurement.as r3 = r3.v()
            java.lang.String r4 = "Fetching config for blacklisted app"
            r3.a(r4)
            r1.a(r2)
        L_0x00d7:
            return
        L_0x00d8:
            com.google.android.gms.internal.measurement.bu r6 = r1.i
            com.google.android.gms.internal.measurement.aq r6 = r6.r()
            r12 = 2
            boolean r6 = r6.a(r12)
            if (r6 == 0) goto L_0x00fe
            com.google.android.gms.internal.measurement.bu r6 = r1.i
            com.google.android.gms.internal.measurement.aq r6 = r6.r()
            com.google.android.gms.internal.measurement.as r6 = r6.w()
            java.lang.String r7 = "Logging event"
            com.google.android.gms.internal.measurement.bu r8 = r1.i
            com.google.android.gms.internal.measurement.ao r8 = r8.l()
            java.lang.String r8 = r8.a(r2)
            r6.a(r7, r8)
        L_0x00fe:
            com.google.android.gms.internal.measurement.z r6 = r31.d()
            r6.f()
            r1.e(r3)     // Catch:{ all -> 0x063d }
            java.lang.String r6 = "_iap"
            java.lang.String r7 = r2.name     // Catch:{ all -> 0x063d }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x063d }
            if (r6 != 0) goto L_0x011c
            java.lang.String r6 = "ecommerce_purchase"
            java.lang.String r7 = r2.name     // Catch:{ all -> 0x063d }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x063d }
            if (r6 == 0) goto L_0x0131
        L_0x011c:
            boolean r6 = r1.a(r15, r2)     // Catch:{ all -> 0x063d }
            if (r6 != 0) goto L_0x0131
            com.google.android.gms.internal.measurement.z r2 = r31.d()     // Catch:{ all -> 0x063d }
            r2.g()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.z r2 = r31.d()
            r2.h()
            return
        L_0x0131:
            java.lang.String r6 = r2.name     // Catch:{ all -> 0x063d }
            boolean r16 = com.google.android.gms.internal.measurement.fg.a(r6)     // Catch:{ all -> 0x063d }
            java.lang.String r6 = "_err"
            java.lang.String r7 = r2.name     // Catch:{ all -> 0x063d }
            boolean r17 = r6.equals(r7)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.z r6 = r31.d()     // Catch:{ all -> 0x063d }
            long r7 = r31.w()     // Catch:{ all -> 0x063d }
            r10 = 1
            r18 = 0
            r19 = 0
            r9 = r15
            r11 = r16
            r12 = r18
            r13 = r17
            r20 = r4
            r4 = r14
            r14 = r19
            com.google.android.gms.internal.measurement.aa r5 = r6.a(r7, r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x063d }
            long r6 = r5.b     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ak$a<java.lang.Integer> r8 = com.google.android.gms.internal.measurement.ak.s     // Catch:{ all -> 0x063d }
            java.lang.Object r8 = r8.b()     // Catch:{ all -> 0x063d }
            java.lang.Integer r8 = (java.lang.Integer) r8     // Catch:{ all -> 0x063d }
            int r8 = r8.intValue()     // Catch:{ all -> 0x063d }
            long r8 = (long) r8     // Catch:{ all -> 0x063d }
            long r10 = r6 - r8
            r13 = 0
            int r6 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            r7 = 1000(0x3e8, double:4.94E-321)
            r13 = 1
            if (r6 <= 0) goto L_0x01a4
            long r10 = r10 % r7
            int r2 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r2 != 0) goto L_0x0195
            com.google.android.gms.internal.measurement.bu r2 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.aq r2 = r2.r()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.as r2 = r2.h_()     // Catch:{ all -> 0x063d }
            java.lang.String r3 = "Data loss. Too many events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.aq.a(r15)     // Catch:{ all -> 0x063d }
            long r5 = r5.b     // Catch:{ all -> 0x063d }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x063d }
            r2.a(r3, r4, r5)     // Catch:{ all -> 0x063d }
        L_0x0195:
            com.google.android.gms.internal.measurement.z r2 = r31.d()     // Catch:{ all -> 0x063d }
            r2.g()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.z r2 = r31.d()
            r2.h()
            return
        L_0x01a4:
            if (r16 == 0) goto L_0x01fc
            long r9 = r5.a     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ak$a<java.lang.Integer> r6 = com.google.android.gms.internal.measurement.ak.u     // Catch:{ all -> 0x063d }
            java.lang.Object r6 = r6.b()     // Catch:{ all -> 0x063d }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ all -> 0x063d }
            int r6 = r6.intValue()     // Catch:{ all -> 0x063d }
            long r11 = (long) r6     // Catch:{ all -> 0x063d }
            long r18 = r9 - r11
            r9 = 0
            int r6 = (r18 > r9 ? 1 : (r18 == r9 ? 0 : -1))
            if (r6 <= 0) goto L_0x01fc
            long r18 = r18 % r7
            int r3 = (r18 > r13 ? 1 : (r18 == r13 ? 0 : -1))
            if (r3 != 0) goto L_0x01dc
            com.google.android.gms.internal.measurement.bu r3 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.aq r3 = r3.r()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.as r3 = r3.h_()     // Catch:{ all -> 0x063d }
            java.lang.String r4 = "Data loss. Too many public events logged. appId, count"
            java.lang.Object r6 = com.google.android.gms.internal.measurement.aq.a(r15)     // Catch:{ all -> 0x063d }
            long r7 = r5.a     // Catch:{ all -> 0x063d }
            java.lang.Long r5 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x063d }
            r3.a(r4, r6, r5)     // Catch:{ all -> 0x063d }
        L_0x01dc:
            com.google.android.gms.internal.measurement.bu r3 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.fg r6 = r3.k()     // Catch:{ all -> 0x063d }
            r8 = 16
            java.lang.String r9 = "_ev"
            java.lang.String r10 = r2.name     // Catch:{ all -> 0x063d }
            r11 = 0
            r7 = r15
            r6.a(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.z r2 = r31.d()     // Catch:{ all -> 0x063d }
            r2.g()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.z r2 = r31.d()
            r2.h()
            return
        L_0x01fc:
            if (r17 == 0) goto L_0x024e
            long r6 = r5.d     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r8 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.w r8 = r8.b()     // Catch:{ all -> 0x063d }
            java.lang.String r9 = r3.packageName     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ak$a<java.lang.Integer> r10 = com.google.android.gms.internal.measurement.ak.t     // Catch:{ all -> 0x063d }
            int r8 = r8.b(r9, r10)     // Catch:{ all -> 0x063d }
            r9 = 1000000(0xf4240, float:1.401298E-39)
            int r8 = java.lang.Math.min(r9, r8)     // Catch:{ all -> 0x063d }
            int r8 = java.lang.Math.max(r4, r8)     // Catch:{ all -> 0x063d }
            long r8 = (long) r8     // Catch:{ all -> 0x063d }
            long r10 = r6 - r8
            r6 = 0
            int r8 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x024e
            int r2 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r2 != 0) goto L_0x023f
            com.google.android.gms.internal.measurement.bu r2 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.aq r2 = r2.r()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.as r2 = r2.h_()     // Catch:{ all -> 0x063d }
            java.lang.String r3 = "Too many error events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.aq.a(r15)     // Catch:{ all -> 0x063d }
            long r5 = r5.d     // Catch:{ all -> 0x063d }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x063d }
            r2.a(r3, r4, r5)     // Catch:{ all -> 0x063d }
        L_0x023f:
            com.google.android.gms.internal.measurement.z r2 = r31.d()     // Catch:{ all -> 0x063d }
            r2.g()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.z r2 = r31.d()
            r2.h()
            return
        L_0x024e:
            com.google.android.gms.internal.measurement.zzeu r5 = r2.zzahg     // Catch:{ all -> 0x063d }
            android.os.Bundle r5 = r5.zzin()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.fg r6 = r6.k()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = "_o"
            java.lang.String r8 = r2.origin     // Catch:{ all -> 0x063d }
            r6.a(r5, r7, r8)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.fg r6 = r6.k()     // Catch:{ all -> 0x063d }
            boolean r6 = r6.h(r15)     // Catch:{ all -> 0x063d }
            if (r6 == 0) goto L_0x028b
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.fg r6 = r6.k()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = "_dbg"
            java.lang.Long r8 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x063d }
            r6.a(r5, r7, r8)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.fg r6 = r6.k()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = "_r"
            java.lang.Long r8 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x063d }
            r6.a(r5, r7, r8)     // Catch:{ all -> 0x063d }
        L_0x028b:
            com.google.android.gms.internal.measurement.z r6 = r31.d()     // Catch:{ all -> 0x063d }
            long r6 = r6.c(r15)     // Catch:{ all -> 0x063d }
            r13 = 0
            int r8 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            if (r8 <= 0) goto L_0x02b0
            com.google.android.gms.internal.measurement.bu r8 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.aq r8 = r8.r()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.as r8 = r8.i()     // Catch:{ all -> 0x063d }
            java.lang.String r9 = "Data lost. Too many events stored on disk, deleted. appId"
            java.lang.Object r10 = com.google.android.gms.internal.measurement.aq.a(r15)     // Catch:{ all -> 0x063d }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x063d }
            r8.a(r9, r10, r6)     // Catch:{ all -> 0x063d }
        L_0x02b0:
            com.google.android.gms.internal.measurement.ah r11 = new com.google.android.gms.internal.measurement.ah     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r7 = r1.i     // Catch:{ all -> 0x063d }
            java.lang.String r8 = r2.origin     // Catch:{ all -> 0x063d }
            java.lang.String r10 = r2.name     // Catch:{ all -> 0x063d }
            long r13 = r2.zzahr     // Catch:{ all -> 0x063d }
            r17 = 0
            r6 = r11
            r9 = r15
            r2 = r11
            r11 = r13
            r13 = r17
            r4 = r15
            r15 = r5
            r6.<init>(r7, r8, r9, r10, r11, r13, r15)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.z r5 = r31.d()     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r2.b     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ai r5 = r5.a(r4, r6)     // Catch:{ all -> 0x063d }
            if (r5 != 0) goto L_0x0338
            com.google.android.gms.internal.measurement.z r5 = r31.d()     // Catch:{ all -> 0x063d }
            long r5 = r5.f(r4)     // Catch:{ all -> 0x063d }
            r7 = 500(0x1f4, double:2.47E-321)
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 < 0) goto L_0x031f
            if (r16 == 0) goto L_0x031f
            com.google.android.gms.internal.measurement.bu r3 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.aq r3 = r3.r()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.as r3 = r3.h_()     // Catch:{ all -> 0x063d }
            java.lang.String r5 = "Too many event names used, ignoring event. appId, name, supported count"
            java.lang.Object r6 = com.google.android.gms.internal.measurement.aq.a(r4)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r7 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ao r7 = r7.l()     // Catch:{ all -> 0x063d }
            java.lang.String r2 = r2.b     // Catch:{ all -> 0x063d }
            java.lang.String r2 = r7.a(r2)     // Catch:{ all -> 0x063d }
            r7 = 500(0x1f4, float:7.0E-43)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x063d }
            r3.a(r5, r6, r2, r7)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r2 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.fg r6 = r2.k()     // Catch:{ all -> 0x063d }
            r8 = 8
            r9 = 0
            r10 = 0
            r11 = 0
            r7 = r4
            r6.a(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.z r2 = r31.d()
            r2.h()
            return
        L_0x031f:
            com.google.android.gms.internal.measurement.ai r5 = new com.google.android.gms.internal.measurement.ai     // Catch:{ all -> 0x063d }
            java.lang.String r8 = r2.b     // Catch:{ all -> 0x063d }
            r9 = 0
            r11 = 0
            long r13 = r2.c     // Catch:{ all -> 0x063d }
            r15 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r6 = r5
            r7 = r4
            r6.<init>(r7, r8, r9, r11, r13, r15, r17, r18, r19)     // Catch:{ all -> 0x063d }
            r11 = r2
            goto L_0x0346
        L_0x0338:
            com.google.android.gms.internal.measurement.bu r4 = r1.i     // Catch:{ all -> 0x063d }
            long r6 = r5.e     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ah r11 = r2.a(r4, r6)     // Catch:{ all -> 0x063d }
            long r6 = r11.c     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ai r5 = r5.a(r6)     // Catch:{ all -> 0x063d }
        L_0x0346:
            com.google.android.gms.internal.measurement.z r2 = r31.d()     // Catch:{ all -> 0x063d }
            r2.a(r5)     // Catch:{ all -> 0x063d }
            r31.v()     // Catch:{ all -> 0x063d }
            r31.i()     // Catch:{ all -> 0x063d }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r11)     // Catch:{ all -> 0x063d }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r33)     // Catch:{ all -> 0x063d }
            java.lang.String r2 = r11.a     // Catch:{ all -> 0x063d }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)     // Catch:{ all -> 0x063d }
            java.lang.String r2 = r11.a     // Catch:{ all -> 0x063d }
            java.lang.String r4 = r3.packageName     // Catch:{ all -> 0x063d }
            boolean r2 = r2.equals(r4)     // Catch:{ all -> 0x063d }
            com.google.android.gms.common.internal.Preconditions.checkArgument(r2)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.fx r2 = new com.google.android.gms.internal.measurement.fx     // Catch:{ all -> 0x063d }
            r2.<init>()     // Catch:{ all -> 0x063d }
            r4 = 1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x063d }
            r2.c = r5     // Catch:{ all -> 0x063d }
            java.lang.String r5 = "android"
            r2.k = r5     // Catch:{ all -> 0x063d }
            java.lang.String r5 = r3.packageName     // Catch:{ all -> 0x063d }
            r2.q = r5     // Catch:{ all -> 0x063d }
            java.lang.String r5 = r3.zzafh     // Catch:{ all -> 0x063d }
            r2.p = r5     // Catch:{ all -> 0x063d }
            java.lang.String r5 = r3.zztg     // Catch:{ all -> 0x063d }
            r2.r = r5     // Catch:{ all -> 0x063d }
            long r5 = r3.zzafg     // Catch:{ all -> 0x063d }
            r7 = -2147483648(0xffffffff80000000, double:NaN)
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            r5 = 0
            if (r9 != 0) goto L_0x0391
            r6 = r5
            goto L_0x0398
        L_0x0391:
            long r6 = r3.zzafg     // Catch:{ all -> 0x063d }
            int r6 = (int) r6     // Catch:{ all -> 0x063d }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x063d }
        L_0x0398:
            r2.E = r6     // Catch:{ all -> 0x063d }
            long r6 = r3.zzafi     // Catch:{ all -> 0x063d }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x063d }
            r2.s = r6     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r3.zzafa     // Catch:{ all -> 0x063d }
            r2.A = r6     // Catch:{ all -> 0x063d }
            long r6 = r3.zzafj     // Catch:{ all -> 0x063d }
            r8 = 0
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x03b0
            r6 = r5
            goto L_0x03b6
        L_0x03b0:
            long r6 = r3.zzafj     // Catch:{ all -> 0x063d }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x063d }
        L_0x03b6:
            r2.x = r6     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bb r6 = r6.c()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = r3.packageName     // Catch:{ all -> 0x063d }
            android.util.Pair r6 = r6.a(r7)     // Catch:{ all -> 0x063d }
            if (r6 == 0) goto L_0x03e1
            java.lang.Object r7 = r6.first     // Catch:{ all -> 0x063d }
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch:{ all -> 0x063d }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x063d }
            if (r7 != 0) goto L_0x03e1
            boolean r7 = r3.zzafm     // Catch:{ all -> 0x063d }
            if (r7 == 0) goto L_0x043e
            java.lang.Object r7 = r6.first     // Catch:{ all -> 0x063d }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x063d }
            r2.u = r7     // Catch:{ all -> 0x063d }
            java.lang.Object r6 = r6.second     // Catch:{ all -> 0x063d }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x063d }
            r2.v = r6     // Catch:{ all -> 0x063d }
            goto L_0x043e
        L_0x03e1:
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ag r6 = r6.v()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r7 = r1.i     // Catch:{ all -> 0x063d }
            android.content.Context r7 = r7.n()     // Catch:{ all -> 0x063d }
            boolean r6 = r6.a(r7)     // Catch:{ all -> 0x063d }
            if (r6 != 0) goto L_0x043e
            boolean r6 = r3.zzafn     // Catch:{ all -> 0x063d }
            if (r6 == 0) goto L_0x043e
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x063d }
            android.content.Context r6 = r6.n()     // Catch:{ all -> 0x063d }
            android.content.ContentResolver r6 = r6.getContentResolver()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = "android_id"
            java.lang.String r6 = android.provider.Settings.Secure.getString(r6, r7)     // Catch:{ all -> 0x063d }
            if (r6 != 0) goto L_0x0421
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.aq r6 = r6.r()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.as r6 = r6.i()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = "null secure ID. appId"
            java.lang.String r10 = r2.q     // Catch:{ all -> 0x063d }
            java.lang.Object r10 = com.google.android.gms.internal.measurement.aq.a(r10)     // Catch:{ all -> 0x063d }
            r6.a(r7, r10)     // Catch:{ all -> 0x063d }
            java.lang.String r6 = "null"
            goto L_0x043c
        L_0x0421:
            boolean r7 = r6.isEmpty()     // Catch:{ all -> 0x063d }
            if (r7 == 0) goto L_0x043c
            com.google.android.gms.internal.measurement.bu r7 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.aq r7 = r7.r()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.as r7 = r7.i()     // Catch:{ all -> 0x063d }
            java.lang.String r10 = "empty secure ID. appId"
            java.lang.String r12 = r2.q     // Catch:{ all -> 0x063d }
            java.lang.Object r12 = com.google.android.gms.internal.measurement.aq.a(r12)     // Catch:{ all -> 0x063d }
            r7.a(r10, r12)     // Catch:{ all -> 0x063d }
        L_0x043c:
            r2.F = r6     // Catch:{ all -> 0x063d }
        L_0x043e:
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ag r6 = r6.v()     // Catch:{ all -> 0x063d }
            r6.z()     // Catch:{ all -> 0x063d }
            java.lang.String r6 = android.os.Build.MODEL     // Catch:{ all -> 0x063d }
            r2.m = r6     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ag r6 = r6.v()     // Catch:{ all -> 0x063d }
            r6.z()     // Catch:{ all -> 0x063d }
            java.lang.String r6 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x063d }
            r2.l = r6     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ag r6 = r6.v()     // Catch:{ all -> 0x063d }
            long r6 = r6.g_()     // Catch:{ all -> 0x063d }
            int r6 = (int) r6     // Catch:{ all -> 0x063d }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x063d }
            r2.o = r6     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ag r6 = r6.v()     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r6.g()     // Catch:{ all -> 0x063d }
            r2.n = r6     // Catch:{ all -> 0x063d }
            r2.t = r5     // Catch:{ all -> 0x063d }
            r2.f = r5     // Catch:{ all -> 0x063d }
            r2.g = r5     // Catch:{ all -> 0x063d }
            r2.h = r5     // Catch:{ all -> 0x063d }
            long r6 = r3.zzafl     // Catch:{ all -> 0x063d }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x063d }
            r2.H = r6     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x063d }
            boolean r6 = r6.y()     // Catch:{ all -> 0x063d }
            if (r6 == 0) goto L_0x0495
            boolean r6 = com.google.android.gms.internal.measurement.w.w()     // Catch:{ all -> 0x063d }
            if (r6 == 0) goto L_0x0495
            r2.I = r5     // Catch:{ all -> 0x063d }
        L_0x0495:
            com.google.android.gms.internal.measurement.z r5 = r31.d()     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r3.packageName     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.t r5 = r5.b(r6)     // Catch:{ all -> 0x063d }
            if (r5 != 0) goto L_0x0503
            com.google.android.gms.internal.measurement.t r5 = new com.google.android.gms.internal.measurement.t     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x063d }
            java.lang.String r7 = r3.packageName     // Catch:{ all -> 0x063d }
            r5.<init>(r6, r7)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.al r6 = r6.w()     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r6.B()     // Catch:{ all -> 0x063d }
            r5.a(r6)     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r3.zzafc     // Catch:{ all -> 0x063d }
            r5.d(r6)     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r3.zzafa     // Catch:{ all -> 0x063d }
            r5.b(r6)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r6 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bb r6 = r6.c()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = r3.packageName     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r6.b(r7)     // Catch:{ all -> 0x063d }
            r5.c(r6)     // Catch:{ all -> 0x063d }
            r5.f(r8)     // Catch:{ all -> 0x063d }
            r5.a(r8)     // Catch:{ all -> 0x063d }
            r5.b(r8)     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r3.zztg     // Catch:{ all -> 0x063d }
            r5.e(r6)     // Catch:{ all -> 0x063d }
            long r6 = r3.zzafg     // Catch:{ all -> 0x063d }
            r5.c(r6)     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r3.zzafh     // Catch:{ all -> 0x063d }
            r5.f(r6)     // Catch:{ all -> 0x063d }
            long r6 = r3.zzafi     // Catch:{ all -> 0x063d }
            r5.d(r6)     // Catch:{ all -> 0x063d }
            long r6 = r3.zzafj     // Catch:{ all -> 0x063d }
            r5.e(r6)     // Catch:{ all -> 0x063d }
            boolean r6 = r3.zzafk     // Catch:{ all -> 0x063d }
            r5.a(r6)     // Catch:{ all -> 0x063d }
            long r6 = r3.zzafl     // Catch:{ all -> 0x063d }
            r5.o(r6)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.z r6 = r31.d()     // Catch:{ all -> 0x063d }
            r6.a(r5)     // Catch:{ all -> 0x063d }
        L_0x0503:
            java.lang.String r6 = r5.c()     // Catch:{ all -> 0x063d }
            r2.w = r6     // Catch:{ all -> 0x063d }
            java.lang.String r5 = r5.f()     // Catch:{ all -> 0x063d }
            r2.D = r5     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.z r5 = r31.d()     // Catch:{ all -> 0x063d }
            java.lang.String r3 = r3.packageName     // Catch:{ all -> 0x063d }
            java.util.List r3 = r5.a(r3)     // Catch:{ all -> 0x063d }
            int r5 = r3.size()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ga[] r5 = new com.google.android.gms.internal.measurement.ga[r5]     // Catch:{ all -> 0x063d }
            r2.e = r5     // Catch:{ all -> 0x063d }
            r5 = 0
        L_0x0522:
            int r6 = r3.size()     // Catch:{ all -> 0x063d }
            if (r5 >= r6) goto L_0x055b
            com.google.android.gms.internal.measurement.ga r6 = new com.google.android.gms.internal.measurement.ga     // Catch:{ all -> 0x063d }
            r6.<init>()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ga[] r7 = r2.e     // Catch:{ all -> 0x063d }
            r7[r5] = r6     // Catch:{ all -> 0x063d }
            java.lang.Object r7 = r3.get(r5)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ff r7 = (com.google.android.gms.internal.measurement.ff) r7     // Catch:{ all -> 0x063d }
            java.lang.String r7 = r7.c     // Catch:{ all -> 0x063d }
            r6.d = r7     // Catch:{ all -> 0x063d }
            java.lang.Object r7 = r3.get(r5)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ff r7 = (com.google.android.gms.internal.measurement.ff) r7     // Catch:{ all -> 0x063d }
            long r12 = r7.d     // Catch:{ all -> 0x063d }
            java.lang.Long r7 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x063d }
            r6.c = r7     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.fe r7 = r31.f()     // Catch:{ all -> 0x063d }
            java.lang.Object r10 = r3.get(r5)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ff r10 = (com.google.android.gms.internal.measurement.ff) r10     // Catch:{ all -> 0x063d }
            java.lang.Object r10 = r10.e     // Catch:{ all -> 0x063d }
            r7.a(r6, r10)     // Catch:{ all -> 0x063d }
            int r5 = r5 + 1
            goto L_0x0522
        L_0x055b:
            com.google.android.gms.internal.measurement.z r3 = r31.d()     // Catch:{ IOException -> 0x05cc }
            long r5 = r3.a(r2)     // Catch:{ IOException -> 0x05cc }
            com.google.android.gms.internal.measurement.z r2 = r31.d()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzeu r3 = r11.e     // Catch:{ all -> 0x063d }
            if (r3 == 0) goto L_0x05c2
            com.google.android.gms.internal.measurement.zzeu r3 = r11.e     // Catch:{ all -> 0x063d }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x063d }
        L_0x0571:
            boolean r7 = r3.hasNext()     // Catch:{ all -> 0x063d }
            if (r7 == 0) goto L_0x0586
            java.lang.Object r7 = r3.next()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x063d }
            java.lang.String r10 = "_r"
            boolean r7 = r10.equals(r7)     // Catch:{ all -> 0x063d }
            if (r7 == 0) goto L_0x0571
            goto L_0x05c3
        L_0x0586:
            com.google.android.gms.internal.measurement.bp r3 = r31.p()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = r11.a     // Catch:{ all -> 0x063d }
            java.lang.String r10 = r11.b     // Catch:{ all -> 0x063d }
            boolean r3 = r3.c(r7, r10)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.z r22 = r31.d()     // Catch:{ all -> 0x063d }
            long r23 = r31.w()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = r11.a     // Catch:{ all -> 0x063d }
            r26 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r30 = 0
            r25 = r7
            com.google.android.gms.internal.measurement.aa r7 = r22.a(r23, r25, r26, r27, r28, r29, r30)     // Catch:{ all -> 0x063d }
            if (r3 == 0) goto L_0x05c2
            long r12 = r7.e     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r3 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.w r3 = r3.b()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = r11.a     // Catch:{ all -> 0x063d }
            int r3 = r3.a(r7)     // Catch:{ all -> 0x063d }
            long r14 = (long) r3     // Catch:{ all -> 0x063d }
            int r3 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r3 >= 0) goto L_0x05c2
            goto L_0x05c3
        L_0x05c2:
            r4 = 0
        L_0x05c3:
            boolean r2 = r2.a(r11, r5, r4)     // Catch:{ all -> 0x063d }
            if (r2 == 0) goto L_0x05e3
            r1.l = r8     // Catch:{ all -> 0x063d }
            goto L_0x05e3
        L_0x05cc:
            r0 = move-exception
            r3 = r0
            com.google.android.gms.internal.measurement.bu r4 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.aq r4 = r4.r()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.as r4 = r4.h_()     // Catch:{ all -> 0x063d }
            java.lang.String r5 = "Data loss. Failed to insert raw event metadata. appId"
            java.lang.String r2 = r2.q     // Catch:{ all -> 0x063d }
            java.lang.Object r2 = com.google.android.gms.internal.measurement.aq.a(r2)     // Catch:{ all -> 0x063d }
            r4.a(r5, r2, r3)     // Catch:{ all -> 0x063d }
        L_0x05e3:
            com.google.android.gms.internal.measurement.z r2 = r31.d()     // Catch:{ all -> 0x063d }
            r2.g()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.bu r2 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.aq r2 = r2.r()     // Catch:{ all -> 0x063d }
            r3 = 2
            boolean r2 = r2.a(r3)     // Catch:{ all -> 0x063d }
            if (r2 == 0) goto L_0x0610
            com.google.android.gms.internal.measurement.bu r2 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.aq r2 = r2.r()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.as r2 = r2.w()     // Catch:{ all -> 0x063d }
            java.lang.String r3 = "Event recorded"
            com.google.android.gms.internal.measurement.bu r4 = r1.i     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.ao r4 = r4.l()     // Catch:{ all -> 0x063d }
            java.lang.String r4 = r4.a(r11)     // Catch:{ all -> 0x063d }
            r2.a(r3, r4)     // Catch:{ all -> 0x063d }
        L_0x0610:
            com.google.android.gms.internal.measurement.z r2 = r31.d()
            r2.h()
            r31.y()
            com.google.android.gms.internal.measurement.bu r2 = r1.i
            com.google.android.gms.internal.measurement.aq r2 = r2.r()
            com.google.android.gms.internal.measurement.as r2 = r2.w()
            java.lang.String r3 = "Background event processing time, ms"
            long r4 = java.lang.System.nanoTime()
            long r6 = r4 - r20
            r4 = 500000(0x7a120, double:2.47033E-318)
            long r8 = r6 + r4
            r4 = 1000000(0xf4240, double:4.940656E-318)
            long r8 = r8 / r4
            java.lang.Long r4 = java.lang.Long.valueOf(r8)
            r2.a(r3, r4)
            return
        L_0x063d:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.internal.measurement.z r3 = r31.d()
            r3.h()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.ey.b(com.google.android.gms.internal.measurement.zzex, com.google.android.gms.internal.measurement.zzeb):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0148  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0150  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.t e(com.google.android.gms.internal.measurement.zzeb r9) {
        /*
            r8 = this;
            r8.v()
            r8.i()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r9)
            java.lang.String r0 = r9.packageName
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r0)
            com.google.android.gms.internal.measurement.z r0 = r8.d()
            java.lang.String r1 = r9.packageName
            com.google.android.gms.internal.measurement.t r0 = r0.b(r1)
            com.google.android.gms.internal.measurement.bu r1 = r8.i
            com.google.android.gms.internal.measurement.bb r1 = r1.c()
            java.lang.String r2 = r9.packageName
            java.lang.String r1 = r1.b(r2)
            r2 = 1
            if (r0 != 0) goto L_0x0042
            com.google.android.gms.internal.measurement.t r0 = new com.google.android.gms.internal.measurement.t
            com.google.android.gms.internal.measurement.bu r3 = r8.i
            java.lang.String r4 = r9.packageName
            r0.<init>(r3, r4)
            com.google.android.gms.internal.measurement.bu r3 = r8.i
            com.google.android.gms.internal.measurement.al r3 = r3.w()
            java.lang.String r3 = r3.B()
            r0.a(r3)
            r0.c(r1)
        L_0x0040:
            r1 = r2
            goto L_0x005e
        L_0x0042:
            java.lang.String r3 = r0.e()
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x005d
            r0.c(r1)
            com.google.android.gms.internal.measurement.bu r1 = r8.i
            com.google.android.gms.internal.measurement.al r1 = r1.w()
            java.lang.String r1 = r1.B()
            r0.a(r1)
            goto L_0x0040
        L_0x005d:
            r1 = 0
        L_0x005e:
            java.lang.String r3 = r9.zzafa
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0078
            java.lang.String r3 = r9.zzafa
            java.lang.String r4 = r0.d()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0078
            java.lang.String r1 = r9.zzafa
            r0.b(r1)
            r1 = r2
        L_0x0078:
            java.lang.String r3 = r9.zzafc
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0092
            java.lang.String r3 = r9.zzafc
            java.lang.String r4 = r0.f()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0092
            java.lang.String r1 = r9.zzafc
            r0.d(r1)
            r1 = r2
        L_0x0092:
            long r3 = r9.zzafi
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00aa
            long r3 = r9.zzafi
            long r5 = r0.l()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00aa
            long r3 = r9.zzafi
            r0.d(r3)
            r1 = r2
        L_0x00aa:
            java.lang.String r3 = r9.zztg
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x00c4
            java.lang.String r3 = r9.zztg
            java.lang.String r4 = r0.i()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x00c4
            java.lang.String r1 = r9.zztg
            r0.e(r1)
            r1 = r2
        L_0x00c4:
            long r3 = r9.zzafg
            long r5 = r0.j()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00d4
            long r3 = r9.zzafg
            r0.c(r3)
            r1 = r2
        L_0x00d4:
            java.lang.String r3 = r9.zzafh
            if (r3 == 0) goto L_0x00ea
            java.lang.String r3 = r9.zzafh
            java.lang.String r4 = r0.k()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x00ea
            java.lang.String r1 = r9.zzafh
            r0.f(r1)
            r1 = r2
        L_0x00ea:
            long r3 = r9.zzafj
            long r5 = r0.m()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00fa
            long r3 = r9.zzafj
            r0.e(r3)
            r1 = r2
        L_0x00fa:
            boolean r3 = r9.zzafk
            boolean r4 = r0.n()
            if (r3 == r4) goto L_0x0108
            boolean r1 = r9.zzafk
            r0.a(r1)
            r1 = r2
        L_0x0108:
            java.lang.String r3 = r9.zzafy
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0122
            java.lang.String r3 = r9.zzafy
            java.lang.String r4 = r0.y()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0122
            java.lang.String r1 = r9.zzafy
            r0.g(r1)
            r1 = r2
        L_0x0122:
            long r3 = r9.zzafl
            long r5 = r0.A()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0132
            long r3 = r9.zzafl
            r0.o(r3)
            r1 = r2
        L_0x0132:
            boolean r3 = r9.zzafm
            boolean r4 = r0.B()
            if (r3 == r4) goto L_0x0140
            boolean r1 = r9.zzafm
            r0.b(r1)
            r1 = r2
        L_0x0140:
            boolean r3 = r9.zzafn
            boolean r4 = r0.C()
            if (r3 == r4) goto L_0x014e
            boolean r9 = r9.zzafn
            r0.c(r9)
            r1 = r2
        L_0x014e:
            if (r1 == 0) goto L_0x0157
            com.google.android.gms.internal.measurement.z r9 = r8.d()
            r9.a(r0)
        L_0x0157:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.ey.e(com.google.android.gms.internal.measurement.zzeb):com.google.android.gms.internal.measurement.t");
    }

    private final bp p() {
        b((ex) this.b);
        return this.b;
    }

    private final az s() {
        if (this.e != null) {
            return this.e;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final eu t() {
        b((ex) this.f);
        return this.f;
    }

    @WorkerThread
    private final void v() {
        this.i.q().d();
    }

    private final long w() {
        long currentTimeMillis = this.i.m().currentTimeMillis();
        bb c2 = this.i.c();
        c2.z();
        c2.d();
        long a2 = c2.g.a();
        if (a2 == 0) {
            long nextInt = 1 + ((long) c2.p().h().nextInt(86400000));
            c2.g.a(nextInt);
            a2 = nextInt;
        }
        return ((((currentTimeMillis + a2) / 1000) / 60) / 60) / 24;
    }

    private final boolean x() {
        v();
        i();
        return d().y() || !TextUtils.isEmpty(d().j());
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x017e  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x019c  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void y() {
        /*
            r20 = this;
            r0 = r20
            r20.v()
            r20.i()
            boolean r1 = r20.B()
            if (r1 != 0) goto L_0x000f
            return
        L_0x000f:
            long r1 = r0.l
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0056
            com.google.android.gms.internal.measurement.bu r1 = r0.i
            com.google.android.gms.common.util.Clock r1 = r1.m()
            long r1 = r1.elapsedRealtime()
            r5 = 3600000(0x36ee80, double:1.7786363E-317)
            long r7 = r0.l
            long r9 = r1 - r7
            long r1 = java.lang.Math.abs(r9)
            long r7 = r5 - r1
            int r1 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x0054
            com.google.android.gms.internal.measurement.bu r1 = r0.i
            com.google.android.gms.internal.measurement.aq r1 = r1.r()
            com.google.android.gms.internal.measurement.as r1 = r1.w()
            java.lang.String r2 = "Upload has been suspended. Will update scheduling later in approximately ms"
            java.lang.Long r3 = java.lang.Long.valueOf(r7)
            r1.a(r2, r3)
            com.google.android.gms.internal.measurement.az r1 = r20.s()
            r1.b()
            com.google.android.gms.internal.measurement.eu r1 = r20.t()
            r1.f()
            return
        L_0x0054:
            r0.l = r3
        L_0x0056:
            com.google.android.gms.internal.measurement.bu r1 = r0.i
            boolean r1 = r1.D()
            if (r1 == 0) goto L_0x0247
            boolean r1 = r20.x()
            if (r1 != 0) goto L_0x0066
            goto L_0x0247
        L_0x0066:
            com.google.android.gms.internal.measurement.bu r1 = r0.i
            com.google.android.gms.common.util.Clock r1 = r1.m()
            long r1 = r1.currentTimeMillis()
            com.google.android.gms.internal.measurement.ak$a<java.lang.Long> r5 = com.google.android.gms.internal.measurement.ak.I
            java.lang.Object r5 = r5.b()
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            long r5 = java.lang.Math.max(r3, r5)
            com.google.android.gms.internal.measurement.z r7 = r20.d()
            boolean r7 = r7.z()
            if (r7 != 0) goto L_0x0097
            com.google.android.gms.internal.measurement.z r7 = r20.d()
            boolean r7 = r7.k()
            if (r7 == 0) goto L_0x0095
            goto L_0x0097
        L_0x0095:
            r7 = 0
            goto L_0x0098
        L_0x0097:
            r7 = 1
        L_0x0098:
            if (r7 == 0) goto L_0x00b8
            com.google.android.gms.internal.measurement.bu r9 = r0.i
            com.google.android.gms.internal.measurement.w r9 = r9.b()
            java.lang.String r9 = r9.v()
            boolean r10 = android.text.TextUtils.isEmpty(r9)
            if (r10 != 0) goto L_0x00b5
            java.lang.String r10 = ".none."
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x00b5
            com.google.android.gms.internal.measurement.ak$a<java.lang.Long> r9 = com.google.android.gms.internal.measurement.ak.D
            goto L_0x00ba
        L_0x00b5:
            com.google.android.gms.internal.measurement.ak$a<java.lang.Long> r9 = com.google.android.gms.internal.measurement.ak.C
            goto L_0x00ba
        L_0x00b8:
            com.google.android.gms.internal.measurement.ak$a<java.lang.Long> r9 = com.google.android.gms.internal.measurement.ak.B
        L_0x00ba:
            java.lang.Object r9 = r9.b()
            java.lang.Long r9 = (java.lang.Long) r9
            long r9 = r9.longValue()
            long r9 = java.lang.Math.max(r3, r9)
            com.google.android.gms.internal.measurement.bu r11 = r0.i
            com.google.android.gms.internal.measurement.bb r11 = r11.c()
            com.google.android.gms.internal.measurement.be r11 = r11.c
            long r11 = r11.a()
            com.google.android.gms.internal.measurement.bu r13 = r0.i
            com.google.android.gms.internal.measurement.bb r13 = r13.c()
            com.google.android.gms.internal.measurement.be r13 = r13.d
            long r13 = r13.a()
            com.google.android.gms.internal.measurement.z r15 = r20.d()
            r16 = r9
            long r8 = r15.w()
            com.google.android.gms.internal.measurement.z r10 = r20.d()
            r18 = r5
            long r5 = r10.x()
            long r5 = java.lang.Math.max(r8, r5)
            int r8 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r8 != 0) goto L_0x00ff
        L_0x00fc:
            r5 = r3
            goto L_0x017a
        L_0x00ff:
            long r8 = r5 - r1
            long r5 = java.lang.Math.abs(r8)
            long r8 = r1 - r5
            long r5 = r11 - r1
            long r5 = java.lang.Math.abs(r5)
            long r10 = r1 - r5
            long r5 = r13 - r1
            long r5 = java.lang.Math.abs(r5)
            long r12 = r1 - r5
            long r1 = java.lang.Math.max(r10, r12)
            long r5 = r8 + r18
            if (r7 == 0) goto L_0x012a
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r7 <= 0) goto L_0x012a
            long r5 = java.lang.Math.min(r8, r1)
            long r10 = r5 + r16
            r5 = r10
        L_0x012a:
            com.google.android.gms.internal.measurement.fe r7 = r20.f()
            r10 = r16
            boolean r7 = r7.a(r1, r10)
            if (r7 != 0) goto L_0x0138
            long r5 = r1 + r10
        L_0x0138:
            int r1 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x017a
            int r1 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
            if (r1 < 0) goto L_0x017a
            r1 = 0
        L_0x0141:
            r2 = 20
            com.google.android.gms.internal.measurement.ak$a<java.lang.Integer> r7 = com.google.android.gms.internal.measurement.ak.K
            java.lang.Object r7 = r7.b()
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r8 = 0
            int r7 = java.lang.Math.max(r8, r7)
            int r2 = java.lang.Math.min(r2, r7)
            if (r1 >= r2) goto L_0x00fc
            r9 = 1
            long r9 = r9 << r1
            com.google.android.gms.internal.measurement.ak$a<java.lang.Long> r2 = com.google.android.gms.internal.measurement.ak.J
            java.lang.Object r2 = r2.b()
            java.lang.Long r2 = (java.lang.Long) r2
            long r14 = r2.longValue()
            long r14 = java.lang.Math.max(r3, r14)
            long r14 = r14 * r9
            long r9 = r5 + r14
            int r2 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r2 <= 0) goto L_0x0176
            r5 = r9
            goto L_0x017a
        L_0x0176:
            int r1 = r1 + 1
            r5 = r9
            goto L_0x0141
        L_0x017a:
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x019c
            com.google.android.gms.internal.measurement.bu r1 = r0.i
            com.google.android.gms.internal.measurement.aq r1 = r1.r()
            com.google.android.gms.internal.measurement.as r1 = r1.w()
            java.lang.String r2 = "Next upload time is 0"
            r1.a(r2)
            com.google.android.gms.internal.measurement.az r1 = r20.s()
            r1.b()
            com.google.android.gms.internal.measurement.eu r1 = r20.t()
            r1.f()
            return
        L_0x019c:
            com.google.android.gms.internal.measurement.au r1 = r20.c()
            boolean r1 = r1.f()
            if (r1 != 0) goto L_0x01c4
            com.google.android.gms.internal.measurement.bu r1 = r0.i
            com.google.android.gms.internal.measurement.aq r1 = r1.r()
            com.google.android.gms.internal.measurement.as r1 = r1.w()
            java.lang.String r2 = "No network"
            r1.a(r2)
            com.google.android.gms.internal.measurement.az r1 = r20.s()
            r1.a()
            com.google.android.gms.internal.measurement.eu r1 = r20.t()
            r1.f()
            return
        L_0x01c4:
            com.google.android.gms.internal.measurement.bu r1 = r0.i
            com.google.android.gms.internal.measurement.bb r1 = r1.c()
            com.google.android.gms.internal.measurement.be r1 = r1.e
            long r1 = r1.a()
            com.google.android.gms.internal.measurement.ak$a<java.lang.Long> r7 = com.google.android.gms.internal.measurement.ak.z
            java.lang.Object r7 = r7.b()
            java.lang.Long r7 = (java.lang.Long) r7
            long r7 = r7.longValue()
            long r7 = java.lang.Math.max(r3, r7)
            com.google.android.gms.internal.measurement.fe r9 = r20.f()
            boolean r9 = r9.a(r1, r7)
            if (r9 != 0) goto L_0x01f0
            long r9 = r1 + r7
            long r5 = java.lang.Math.max(r5, r9)
        L_0x01f0:
            com.google.android.gms.internal.measurement.az r1 = r20.s()
            r1.b()
            com.google.android.gms.internal.measurement.bu r1 = r0.i
            com.google.android.gms.common.util.Clock r1 = r1.m()
            long r1 = r1.currentTimeMillis()
            long r7 = r5 - r1
            int r1 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x022c
            com.google.android.gms.internal.measurement.ak$a<java.lang.Long> r1 = com.google.android.gms.internal.measurement.ak.E
            java.lang.Object r1 = r1.b()
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            long r7 = java.lang.Math.max(r3, r1)
            com.google.android.gms.internal.measurement.bu r1 = r0.i
            com.google.android.gms.internal.measurement.bb r1 = r1.c()
            com.google.android.gms.internal.measurement.be r1 = r1.c
            com.google.android.gms.internal.measurement.bu r2 = r0.i
            com.google.android.gms.common.util.Clock r2 = r2.m()
            long r2 = r2.currentTimeMillis()
            r1.a(r2)
        L_0x022c:
            com.google.android.gms.internal.measurement.bu r1 = r0.i
            com.google.android.gms.internal.measurement.aq r1 = r1.r()
            com.google.android.gms.internal.measurement.as r1 = r1.w()
            java.lang.String r2 = "Upload scheduled in approximately ms"
            java.lang.Long r3 = java.lang.Long.valueOf(r7)
            r1.a(r2, r3)
            com.google.android.gms.internal.measurement.eu r1 = r20.t()
            r1.a(r7)
            return
        L_0x0247:
            com.google.android.gms.internal.measurement.bu r1 = r0.i
            com.google.android.gms.internal.measurement.aq r1 = r1.r()
            com.google.android.gms.internal.measurement.as r1 = r1.w()
            java.lang.String r2 = "Nothing to upload or uploading impossible"
            r1.a(r2)
            com.google.android.gms.internal.measurement.az r1 = r20.s()
            r1.b()
            com.google.android.gms.internal.measurement.eu r1 = r20.t()
            r1.f()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.ey.y():void");
    }

    @WorkerThread
    private final void z() {
        v();
        if (this.p || this.q || this.r) {
            this.i.r().w().a("Not stopping services. fetch, network, upload", Boolean.valueOf(this.p), Boolean.valueOf(this.q), Boolean.valueOf(this.r));
            return;
        }
        this.i.r().w().a("Stopping uploading service(s)");
        if (this.m != null) {
            for (Runnable run : this.m) {
                run.run();
            }
            this.m.clear();
        }
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void a() {
        this.i.q().d();
        d().v();
        if (this.i.c().c.a() == 0) {
            this.i.c().c.a(this.i.m().currentTimeMillis());
        }
        y();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: 0000 */
    @WorkerThread
    @VisibleForTesting
    public final void a(int i2, Throwable th, byte[] bArr, String str) {
        z d2;
        v();
        i();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.q = false;
                z();
                throw th2;
            }
        }
        List<Long> list = this.u;
        this.u = null;
        boolean z = true;
        if ((i2 == 200 || i2 == 204) && th == null) {
            try {
                this.i.c().c.a(this.i.m().currentTimeMillis());
                this.i.c().d.a(0);
                y();
                this.i.r().w().a("Successful upload. Got network response. code, size", Integer.valueOf(i2), Integer.valueOf(bArr.length));
                d().f();
                try {
                    for (Long l2 : list) {
                        try {
                            d2 = d();
                            long longValue = l2.longValue();
                            d2.d();
                            d2.I();
                            if (d2.i().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                                throw new SQLiteException("Deleted fewer rows from queue than expected");
                            }
                        } catch (SQLiteException e2) {
                            d2.r().h_().a("Failed to delete a bundle in a queue table", e2);
                            throw e2;
                        } catch (SQLiteException e3) {
                            if (this.v == null || !this.v.contains(l2)) {
                                throw e3;
                            }
                        }
                    }
                    d().g();
                    d().h();
                    this.v = null;
                    if (!c().f() || !x()) {
                        this.w = -1;
                        y();
                    } else {
                        j();
                    }
                    this.l = 0;
                } catch (Throwable th3) {
                    d().h();
                    throw th3;
                }
            } catch (SQLiteException e4) {
                this.i.r().h_().a("Database error while trying to delete uploaded bundles", e4);
                this.l = this.i.m().elapsedRealtime();
                this.i.r().w().a("Disable upload, time", Long.valueOf(this.l));
            }
        } else {
            this.i.r().w().a("Network upload failed. Will retry later. code, error", Integer.valueOf(i2), th);
            this.i.c().d.a(this.i.m().currentTimeMillis());
            if (i2 != 503) {
                if (i2 != 429) {
                    z = false;
                }
            }
            if (z) {
                this.i.c().e.a(this.i.m().currentTimeMillis());
            }
            if (this.i.b().g(str)) {
                d().a(list);
            }
            y();
        }
        this.q = false;
        z();
    }

    /* access modifiers changed from: 0000 */
    public final void a(ex exVar) {
        this.n++;
    }

    /* access modifiers changed from: 0000 */
    public final void a(zzeb zzeb) {
        v();
        i();
        Preconditions.checkNotEmpty(zzeb.packageName);
        e(zzeb);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void a(zzef zzef) {
        zzeb a2 = a(zzef.packageName);
        if (a2 != null) {
            a(zzef, a2);
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void a(zzef zzef, zzeb zzeb) {
        as h_;
        String str;
        Object a2;
        String c2;
        Object value;
        as h_2;
        String str2;
        Object a3;
        String c3;
        Object obj;
        Preconditions.checkNotNull(zzef);
        Preconditions.checkNotEmpty(zzef.packageName);
        Preconditions.checkNotNull(zzef.origin);
        Preconditions.checkNotNull(zzef.zzage);
        Preconditions.checkNotEmpty(zzef.zzage.name);
        v();
        i();
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            if (!zzeb.zzafk) {
                e(zzeb);
                return;
            }
            zzef zzef2 = new zzef(zzef);
            boolean z = false;
            zzef2.active = false;
            d().f();
            try {
                zzef d2 = d().d(zzef2.packageName, zzef2.zzage.name);
                if (d2 != null && !d2.origin.equals(zzef2.origin)) {
                    this.i.r().i().a("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.i.l().c(zzef2.zzage.name), zzef2.origin, d2.origin);
                }
                if (d2 != null && d2.active) {
                    zzef2.origin = d2.origin;
                    zzef2.creationTimestamp = d2.creationTimestamp;
                    zzef2.triggerTimeout = d2.triggerTimeout;
                    zzef2.triggerEventName = d2.triggerEventName;
                    zzef2.zzagg = d2.zzagg;
                    zzef2.active = d2.active;
                    zzka zzka = new zzka(zzef2.zzage.name, d2.zzage.zzast, zzef2.zzage.getValue(), d2.zzage.origin);
                    zzef2.zzage = zzka;
                } else if (TextUtils.isEmpty(zzef2.triggerEventName)) {
                    zzka zzka2 = new zzka(zzef2.zzage.name, zzef2.creationTimestamp, zzef2.zzage.getValue(), zzef2.zzage.origin);
                    zzef2.zzage = zzka2;
                    zzef2.active = true;
                    z = true;
                }
                if (zzef2.active) {
                    zzka zzka3 = zzef2.zzage;
                    ff ffVar = new ff(zzef2.packageName, zzef2.origin, zzka3.name, zzka3.zzast, zzka3.getValue());
                    if (d().a(ffVar)) {
                        h_2 = this.i.r().v();
                        str2 = "User property updated immediately";
                        a3 = zzef2.packageName;
                        c3 = this.i.l().c(ffVar.c);
                        obj = ffVar.e;
                    } else {
                        h_2 = this.i.r().h_();
                        str2 = "(2)Too many active user properties, ignoring";
                        a3 = aq.a(zzef2.packageName);
                        c3 = this.i.l().c(ffVar.c);
                        obj = ffVar.e;
                    }
                    h_2.a(str2, a3, c3, obj);
                    if (z && zzef2.zzagg != null) {
                        b(new zzex(zzef2.zzagg, zzef2.creationTimestamp), zzeb);
                    }
                }
                if (d().a(zzef2)) {
                    h_ = this.i.r().v();
                    str = "Conditional property added";
                    a2 = zzef2.packageName;
                    c2 = this.i.l().c(zzef2.zzage.name);
                    value = zzef2.zzage.getValue();
                } else {
                    h_ = this.i.r().h_();
                    str = "Too many conditional properties, ignoring";
                    a2 = aq.a(zzef2.packageName);
                    c2 = this.i.l().c(zzef2.zzage.name);
                    value = zzef2.zzage.getValue();
                }
                h_.a(str, a2, c2, value);
                d().g();
            } finally {
                d().h();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void a(zzex zzex, zzeb zzeb) {
        List<zzef> list;
        List<zzef> list2;
        List list3;
        as h_;
        String str;
        Object a2;
        String c2;
        Object obj;
        zzex zzex2 = zzex;
        zzeb zzeb2 = zzeb;
        Preconditions.checkNotNull(zzeb);
        Preconditions.checkNotEmpty(zzeb2.packageName);
        v();
        i();
        String str2 = zzeb2.packageName;
        long j2 = zzex2.zzahr;
        if (f().a(zzex2, zzeb2)) {
            if (!zzeb2.zzafk) {
                e(zzeb2);
                return;
            }
            d().f();
            try {
                z d2 = d();
                Preconditions.checkNotEmpty(str2);
                d2.d();
                d2.I();
                if (j2 < 0) {
                    d2.r().i().a("Invalid time querying timed out conditional properties", aq.a(str2), Long.valueOf(j2));
                    list = Collections.emptyList();
                } else {
                    list = d2.a("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str2, String.valueOf(j2)});
                }
                for (zzef zzef : list) {
                    if (zzef != null) {
                        this.i.r().v().a("User property timed out", zzef.packageName, this.i.l().c(zzef.zzage.name), zzef.zzage.getValue());
                        if (zzef.zzagf != null) {
                            b(new zzex(zzef.zzagf, j2), zzeb2);
                        }
                        d().e(str2, zzef.zzage.name);
                    }
                }
                z d3 = d();
                Preconditions.checkNotEmpty(str2);
                d3.d();
                d3.I();
                if (j2 < 0) {
                    d3.r().i().a("Invalid time querying expired conditional properties", aq.a(str2), Long.valueOf(j2));
                    list2 = Collections.emptyList();
                } else {
                    list2 = d3.a("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str2, String.valueOf(j2)});
                }
                ArrayList arrayList = new ArrayList(list2.size());
                for (zzef zzef2 : list2) {
                    if (zzef2 != null) {
                        this.i.r().v().a("User property expired", zzef2.packageName, this.i.l().c(zzef2.zzage.name), zzef2.zzage.getValue());
                        d().b(str2, zzef2.zzage.name);
                        if (zzef2.zzagh != null) {
                            arrayList.add(zzef2.zzagh);
                        }
                        d().e(str2, zzef2.zzage.name);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj2 = arrayList2.get(i2);
                    i2++;
                    b(new zzex((zzex) obj2, j2), zzeb2);
                }
                z d4 = d();
                String str3 = zzex2.name;
                Preconditions.checkNotEmpty(str2);
                Preconditions.checkNotEmpty(str3);
                d4.d();
                d4.I();
                if (j2 < 0) {
                    d4.r().i().a("Invalid time querying triggered conditional properties", aq.a(str2), d4.o().a(str3), Long.valueOf(j2));
                    list3 = Collections.emptyList();
                } else {
                    list3 = d4.a("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str2, str3, String.valueOf(j2)});
                }
                ArrayList arrayList3 = new ArrayList(list3.size());
                Iterator it = list3.iterator();
                while (it.hasNext()) {
                    zzef zzef3 = (zzef) it.next();
                    if (zzef3 != null) {
                        zzka zzka = zzef3.zzage;
                        ff ffVar = r5;
                        Iterator it2 = it;
                        zzef zzef4 = zzef3;
                        ff ffVar2 = new ff(zzef3.packageName, zzef3.origin, zzka.name, j2, zzka.getValue());
                        if (d().a(ffVar)) {
                            h_ = this.i.r().v();
                            str = "User property triggered";
                            a2 = zzef4.packageName;
                            c2 = this.i.l().c(ffVar.c);
                            obj = ffVar.e;
                        } else {
                            h_ = this.i.r().h_();
                            str = "Too many active user properties, ignoring";
                            a2 = aq.a(zzef4.packageName);
                            c2 = this.i.l().c(ffVar.c);
                            obj = ffVar.e;
                        }
                        h_.a(str, a2, c2, obj);
                        if (zzef4.zzagg != null) {
                            arrayList3.add(zzef4.zzagg);
                        }
                        zzef4.zzage = new zzka(ffVar);
                        zzef4.active = true;
                        d().a(zzef4);
                        it = it2;
                    }
                }
                b(zzex, zzeb);
                ArrayList arrayList4 = arrayList3;
                int size2 = arrayList4.size();
                int i3 = 0;
                while (i3 < size2) {
                    Object obj3 = arrayList4.get(i3);
                    i3++;
                    b(new zzex((zzex) obj3, j2), zzeb2);
                }
                d().g();
                d().h();
            } catch (Throwable th) {
                Throwable th2 = th;
                d().h();
                throw th2;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void a(zzex zzex, String str) {
        zzex zzex2 = zzex;
        String str2 = str;
        t b2 = d().b(str2);
        if (b2 == null || TextUtils.isEmpty(b2.i())) {
            this.i.r().v().a("No app data available; dropping event", str2);
            return;
        }
        Boolean b3 = b(b2);
        if (b3 == null) {
            if (!"_ui".equals(zzex2.name)) {
                this.i.r().i().a("Could not find package. appId", aq.a(str));
            }
        } else if (!b3.booleanValue()) {
            this.i.r().h_().a("App version does not match; dropping event. appId", aq.a(str));
            return;
        }
        zzeb zzeb = r2;
        zzeb zzeb2 = new zzeb(str2, b2.d(), b2.i(), b2.j(), b2.k(), b2.l(), b2.m(), (String) null, b2.n(), false, b2.f(), b2.A(), 0, 0, b2.B(), b2.C(), false);
        a(zzex2, zzeb);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void a(zzka zzka, zzeb zzeb) {
        v();
        i();
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            if (!zzeb.zzafk) {
                e(zzeb);
                return;
            }
            int d2 = this.i.k().d(zzka.name);
            int i2 = 0;
            if (d2 != 0) {
                this.i.k();
                this.i.k().a(zzeb.packageName, d2, "_ev", fg.a(zzka.name, 24, true), zzka.name != null ? zzka.name.length() : 0);
                return;
            }
            int b2 = this.i.k().b(zzka.name, zzka.getValue());
            if (b2 != 0) {
                this.i.k();
                String a2 = fg.a(zzka.name, 24, true);
                Object value = zzka.getValue();
                if (value != null && ((value instanceof String) || (value instanceof CharSequence))) {
                    i2 = String.valueOf(value).length();
                }
                this.i.k().a(zzeb.packageName, b2, "_ev", a2, i2);
                return;
            }
            Object c2 = this.i.k().c(zzka.name, zzka.getValue());
            if (c2 != null) {
                ff ffVar = new ff(zzeb.packageName, zzka.origin, zzka.name, zzka.zzast, c2);
                this.i.r().v().a("Setting user property", this.i.l().c(ffVar.c), c2);
                d().f();
                try {
                    e(zzeb);
                    boolean a3 = d().a(ffVar);
                    d().g();
                    if (a3) {
                        this.i.r().v().a("User property set", this.i.l().c(ffVar.c), ffVar.e);
                    } else {
                        this.i.r().h_().a("Too many unique user properties are set. Ignoring user property", this.i.l().c(ffVar.c), ffVar.e);
                        this.i.k().a(zzeb.packageName, 9, (String) null, (String) null, 0);
                    }
                } finally {
                    d().h();
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void a(Runnable runnable) {
        v();
        if (this.m == null) {
            this.m = new ArrayList();
        }
        this.m.add(runnable);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0136 A[Catch:{ all -> 0x017d, all -> 0x000f }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0146 A[Catch:{ all -> 0x017d, all -> 0x000f }] */
    @android.support.annotation.WorkerThread
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r7, int r8, java.lang.Throwable r9, byte[] r10, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r11) {
        /*
            r6 = this;
            r6.v()
            r6.i()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)
            r0 = 0
            if (r10 != 0) goto L_0x0012
            byte[] r10 = new byte[r0]     // Catch:{ all -> 0x000f }
            goto L_0x0012
        L_0x000f:
            r7 = move-exception
            goto L_0x0186
        L_0x0012:
            com.google.android.gms.internal.measurement.bu r1 = r6.i     // Catch:{ all -> 0x000f }
            com.google.android.gms.internal.measurement.aq r1 = r1.r()     // Catch:{ all -> 0x000f }
            com.google.android.gms.internal.measurement.as r1 = r1.w()     // Catch:{ all -> 0x000f }
            java.lang.String r2 = "onConfigFetched. Response size"
            int r3 = r10.length     // Catch:{ all -> 0x000f }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x000f }
            r1.a(r2, r3)     // Catch:{ all -> 0x000f }
            com.google.android.gms.internal.measurement.z r1 = r6.d()     // Catch:{ all -> 0x000f }
            r1.f()     // Catch:{ all -> 0x000f }
            com.google.android.gms.internal.measurement.z r1 = r6.d()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.t r1 = r1.b(r7)     // Catch:{ all -> 0x017d }
            r2 = 200(0xc8, float:2.8E-43)
            r3 = 1
            r4 = 304(0x130, float:4.26E-43)
            if (r8 == r2) goto L_0x0042
            r2 = 204(0xcc, float:2.86E-43)
            if (r8 == r2) goto L_0x0042
            if (r8 != r4) goto L_0x0046
        L_0x0042:
            if (r9 != 0) goto L_0x0046
            r2 = r3
            goto L_0x0047
        L_0x0046:
            r2 = r0
        L_0x0047:
            if (r1 != 0) goto L_0x005e
            com.google.android.gms.internal.measurement.bu r8 = r6.i     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.aq r8 = r8.r()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.as r8 = r8.i()     // Catch:{ all -> 0x017d }
            java.lang.String r9 = "App does not exist in onConfigFetched. appId"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.aq.a(r7)     // Catch:{ all -> 0x017d }
            r8.a(r9, r7)     // Catch:{ all -> 0x017d }
            goto L_0x0171
        L_0x005e:
            r5 = 404(0x194, float:5.66E-43)
            if (r2 != 0) goto L_0x00ce
            if (r8 != r5) goto L_0x0065
            goto L_0x00ce
        L_0x0065:
            com.google.android.gms.internal.measurement.bu r10 = r6.i     // Catch:{ all -> 0x017d }
            com.google.android.gms.common.util.Clock r10 = r10.m()     // Catch:{ all -> 0x017d }
            long r10 = r10.currentTimeMillis()     // Catch:{ all -> 0x017d }
            r1.h(r10)     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.z r10 = r6.d()     // Catch:{ all -> 0x017d }
            r10.a(r1)     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.bu r10 = r6.i     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.aq r10 = r10.r()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.as r10 = r10.w()     // Catch:{ all -> 0x017d }
            java.lang.String r11 = "Fetching config failed. code, error"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x017d }
            r10.a(r11, r1, r9)     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.bp r9 = r6.p()     // Catch:{ all -> 0x017d }
            r9.c(r7)     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.bu r7 = r6.i     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.bb r7 = r7.c()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.be r7 = r7.d     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.bu r9 = r6.i     // Catch:{ all -> 0x017d }
            com.google.android.gms.common.util.Clock r9 = r9.m()     // Catch:{ all -> 0x017d }
            long r9 = r9.currentTimeMillis()     // Catch:{ all -> 0x017d }
            r7.a(r9)     // Catch:{ all -> 0x017d }
            r7 = 503(0x1f7, float:7.05E-43)
            if (r8 == r7) goto L_0x00b2
            r7 = 429(0x1ad, float:6.01E-43)
            if (r8 != r7) goto L_0x00b1
            goto L_0x00b2
        L_0x00b1:
            r3 = r0
        L_0x00b2:
            if (r3 == 0) goto L_0x00c9
            com.google.android.gms.internal.measurement.bu r7 = r6.i     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.bb r7 = r7.c()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.be r7 = r7.e     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.bu r8 = r6.i     // Catch:{ all -> 0x017d }
            com.google.android.gms.common.util.Clock r8 = r8.m()     // Catch:{ all -> 0x017d }
            long r8 = r8.currentTimeMillis()     // Catch:{ all -> 0x017d }
            r7.a(r8)     // Catch:{ all -> 0x017d }
        L_0x00c9:
            r6.y()     // Catch:{ all -> 0x017d }
            goto L_0x0171
        L_0x00ce:
            r9 = 0
            if (r11 == 0) goto L_0x00da
            java.lang.String r2 = "Last-Modified"
            java.lang.Object r11 = r11.get(r2)     // Catch:{ all -> 0x017d }
            java.util.List r11 = (java.util.List) r11     // Catch:{ all -> 0x017d }
            goto L_0x00db
        L_0x00da:
            r11 = r9
        L_0x00db:
            if (r11 == 0) goto L_0x00ea
            int r2 = r11.size()     // Catch:{ all -> 0x017d }
            if (r2 <= 0) goto L_0x00ea
            java.lang.Object r11 = r11.get(r0)     // Catch:{ all -> 0x017d }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x017d }
            goto L_0x00eb
        L_0x00ea:
            r11 = r9
        L_0x00eb:
            if (r8 == r5) goto L_0x0107
            if (r8 != r4) goto L_0x00f0
            goto L_0x0107
        L_0x00f0:
            com.google.android.gms.internal.measurement.bp r9 = r6.p()     // Catch:{ all -> 0x017d }
            boolean r9 = r9.a(r7, r10, r11)     // Catch:{ all -> 0x017d }
            if (r9 != 0) goto L_0x0120
            com.google.android.gms.internal.measurement.z r7 = r6.d()     // Catch:{ all -> 0x000f }
        L_0x00fe:
            r7.h()     // Catch:{ all -> 0x000f }
            r6.p = r0
            r6.z()
            return
        L_0x0107:
            com.google.android.gms.internal.measurement.bp r11 = r6.p()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.fq r11 = r11.a(r7)     // Catch:{ all -> 0x017d }
            if (r11 != 0) goto L_0x0120
            com.google.android.gms.internal.measurement.bp r11 = r6.p()     // Catch:{ all -> 0x017d }
            boolean r9 = r11.a(r7, r9, r9)     // Catch:{ all -> 0x017d }
            if (r9 != 0) goto L_0x0120
            com.google.android.gms.internal.measurement.z r7 = r6.d()     // Catch:{ all -> 0x000f }
            goto L_0x00fe
        L_0x0120:
            com.google.android.gms.internal.measurement.bu r9 = r6.i     // Catch:{ all -> 0x017d }
            com.google.android.gms.common.util.Clock r9 = r9.m()     // Catch:{ all -> 0x017d }
            long r2 = r9.currentTimeMillis()     // Catch:{ all -> 0x017d }
            r1.g(r2)     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.z r9 = r6.d()     // Catch:{ all -> 0x017d }
            r9.a(r1)     // Catch:{ all -> 0x017d }
            if (r8 != r5) goto L_0x0146
            com.google.android.gms.internal.measurement.bu r8 = r6.i     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.aq r8 = r8.r()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.as r8 = r8.j()     // Catch:{ all -> 0x017d }
            java.lang.String r9 = "Config not found. Using empty config. appId"
            r8.a(r9, r7)     // Catch:{ all -> 0x017d }
            goto L_0x015e
        L_0x0146:
            com.google.android.gms.internal.measurement.bu r7 = r6.i     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.aq r7 = r7.r()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.as r7 = r7.w()     // Catch:{ all -> 0x017d }
            java.lang.String r9 = "Successfully fetched config. Got network response. code, size"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x017d }
            int r10 = r10.length     // Catch:{ all -> 0x017d }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x017d }
            r7.a(r9, r8, r10)     // Catch:{ all -> 0x017d }
        L_0x015e:
            com.google.android.gms.internal.measurement.au r7 = r6.c()     // Catch:{ all -> 0x017d }
            boolean r7 = r7.f()     // Catch:{ all -> 0x017d }
            if (r7 == 0) goto L_0x00c9
            boolean r7 = r6.x()     // Catch:{ all -> 0x017d }
            if (r7 == 0) goto L_0x00c9
            r6.j()     // Catch:{ all -> 0x017d }
        L_0x0171:
            com.google.android.gms.internal.measurement.z r7 = r6.d()     // Catch:{ all -> 0x017d }
            r7.g()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.z r7 = r6.d()     // Catch:{ all -> 0x000f }
            goto L_0x00fe
        L_0x017d:
            r7 = move-exception
            com.google.android.gms.internal.measurement.z r8 = r6.d()     // Catch:{ all -> 0x000f }
            r8.h()     // Catch:{ all -> 0x000f }
            throw r7     // Catch:{ all -> 0x000f }
        L_0x0186:
            r6.p = r0
            r6.z()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.ey.a(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    /* access modifiers changed from: 0000 */
    public final void a(boolean z) {
        y();
    }

    public final w b() {
        return this.i.b();
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    @VisibleForTesting
    public final void b(zzeb zzeb) {
        if (this.u != null) {
            this.v = new ArrayList();
            this.v.addAll(this.u);
        }
        z d2 = d();
        String str = zzeb.packageName;
        Preconditions.checkNotEmpty(str);
        d2.d();
        d2.I();
        try {
            SQLiteDatabase i2 = d2.i();
            String[] strArr = {str};
            int delete = 0 + i2.delete("apps", "app_id=?", strArr) + i2.delete("events", "app_id=?", strArr) + i2.delete("user_attributes", "app_id=?", strArr) + i2.delete("conditional_properties", "app_id=?", strArr) + i2.delete("raw_events", "app_id=?", strArr) + i2.delete("raw_events_metadata", "app_id=?", strArr) + i2.delete("queue", "app_id=?", strArr) + i2.delete("audience_filter_values", "app_id=?", strArr) + i2.delete("main_event_params", "app_id=?", strArr);
            if (delete > 0) {
                d2.r().w().a("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e2) {
            d2.r().h_().a("Error resetting analytics data. appId, error", aq.a(str), e2);
        }
        zzeb a2 = a(this.i.n(), zzeb.packageName, zzeb.zzafa, zzeb.zzafk, zzeb.zzafm, zzeb.zzafn, zzeb.zzaga);
        if (!this.i.b().k(zzeb.packageName) || zzeb.zzafk) {
            c(a2);
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void b(zzef zzef) {
        zzeb a2 = a(zzef.packageName);
        if (a2 != null) {
            b(zzef, a2);
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void b(zzef zzef, zzeb zzeb) {
        Preconditions.checkNotNull(zzef);
        Preconditions.checkNotEmpty(zzef.packageName);
        Preconditions.checkNotNull(zzef.zzage);
        Preconditions.checkNotEmpty(zzef.zzage.name);
        v();
        i();
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            if (!zzeb.zzafk) {
                e(zzeb);
                return;
            }
            d().f();
            try {
                e(zzeb);
                zzef d2 = d().d(zzef.packageName, zzef.zzage.name);
                if (d2 != null) {
                    this.i.r().v().a("Removing conditional user property", zzef.packageName, this.i.l().c(zzef.zzage.name));
                    d().e(zzef.packageName, zzef.zzage.name);
                    if (d2.active) {
                        d().b(zzef.packageName, zzef.zzage.name);
                    }
                    if (zzef.zzagh != null) {
                        Bundle bundle = null;
                        if (zzef.zzagh.zzahg != null) {
                            bundle = zzef.zzagh.zzahg.zzin();
                        }
                        Bundle bundle2 = bundle;
                        b(this.i.k().a(zzef.packageName, zzef.zzagh.name, bundle2, d2.origin, zzef.zzagh.zzahr, true, false), zzeb);
                    }
                } else {
                    this.i.r().i().a("Conditional user property doesn't exist", aq.a(zzef.packageName), this.i.l().c(zzef.zzage.name));
                }
                d().g();
            } finally {
                d().h();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void b(zzka zzka, zzeb zzeb) {
        v();
        i();
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            if (!zzeb.zzafk) {
                e(zzeb);
                return;
            }
            this.i.r().v().a("Removing user property", this.i.l().c(zzka.name));
            d().f();
            try {
                e(zzeb);
                d().b(zzeb.packageName, zzka.name);
                d().g();
                this.i.r().v().a("User property removed", this.i.l().c(zzka.name));
            } finally {
                d().h();
            }
        }
    }

    /* JADX WARNING: type inference failed for: r18v1 */
    /* JADX WARNING: type inference failed for: r18v3 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] b(@android.support.annotation.NonNull com.google.android.gms.internal.measurement.zzex r33, @android.support.annotation.Size(min = 1) java.lang.String r34) {
        /*
            r32 = this;
            r1 = r32
            r2 = r33
            r15 = r34
            r32.i()
            r32.v()
            com.google.android.gms.internal.measurement.bu r3 = r1.i
            r3.B()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r33)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r34)
            com.google.android.gms.internal.measurement.fw r14 = new com.google.android.gms.internal.measurement.fw
            r14.<init>()
            com.google.android.gms.internal.measurement.z r3 = r32.d()
            r3.f()
            com.google.android.gms.internal.measurement.z r3 = r32.d()     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.t r12 = r3.b(r15)     // Catch:{ all -> 0x04f6 }
            r13 = 0
            if (r12 != 0) goto L_0x0047
            com.google.android.gms.internal.measurement.bu r2 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.aq r2 = r2.r()     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.as r2 = r2.v()     // Catch:{ all -> 0x04f6 }
            java.lang.String r3 = "Log and bundle not available. package_name"
            r2.a(r3, r15)     // Catch:{ all -> 0x04f6 }
        L_0x003d:
            byte[] r2 = new byte[r13]     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.z r3 = r32.d()
            r3.h()
            return r2
        L_0x0047:
            boolean r3 = r12.n()     // Catch:{ all -> 0x04f6 }
            if (r3 != 0) goto L_0x005d
            com.google.android.gms.internal.measurement.bu r2 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.aq r2 = r2.r()     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.as r2 = r2.v()     // Catch:{ all -> 0x04f6 }
            java.lang.String r3 = "Log and bundle disabled. package_name"
            r2.a(r3, r15)     // Catch:{ all -> 0x04f6 }
            goto L_0x003d
        L_0x005d:
            java.lang.String r3 = "_iap"
            java.lang.String r4 = r2.name     // Catch:{ all -> 0x04f6 }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x04f6 }
            if (r3 != 0) goto L_0x0071
            java.lang.String r3 = "ecommerce_purchase"
            java.lang.String r4 = r2.name     // Catch:{ all -> 0x04f6 }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x04f6 }
            if (r3 == 0) goto L_0x008a
        L_0x0071:
            boolean r3 = r1.a(r15, r2)     // Catch:{ all -> 0x04f6 }
            if (r3 != 0) goto L_0x008a
            com.google.android.gms.internal.measurement.bu r3 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.aq r3 = r3.r()     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.as r3 = r3.i()     // Catch:{ all -> 0x04f6 }
            java.lang.String r4 = "Failed to handle purchase event at single event bundle creation. appId"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.aq.a(r34)     // Catch:{ all -> 0x04f6 }
            r3.a(r4, r5)     // Catch:{ all -> 0x04f6 }
        L_0x008a:
            com.google.android.gms.internal.measurement.bu r3 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.w r3 = r3.b()     // Catch:{ all -> 0x04f6 }
            boolean r3 = r3.e(r15)     // Catch:{ all -> 0x04f6 }
            r10 = 0
            java.lang.Long r4 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x04f6 }
            if (r3 == 0) goto L_0x00eb
            java.lang.String r5 = "_e"
            java.lang.String r6 = r2.name     // Catch:{ all -> 0x04f6 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x04f6 }
            if (r5 == 0) goto L_0x00eb
            com.google.android.gms.internal.measurement.zzeu r5 = r2.zzahg     // Catch:{ all -> 0x04f6 }
            if (r5 == 0) goto L_0x00da
            com.google.android.gms.internal.measurement.zzeu r5 = r2.zzahg     // Catch:{ all -> 0x04f6 }
            int r5 = r5.size()     // Catch:{ all -> 0x04f6 }
            if (r5 != 0) goto L_0x00b3
            goto L_0x00da
        L_0x00b3:
            com.google.android.gms.internal.measurement.zzeu r5 = r2.zzahg     // Catch:{ all -> 0x04f6 }
            java.lang.String r6 = "_et"
            java.lang.Long r5 = r5.getLong(r6)     // Catch:{ all -> 0x04f6 }
            if (r5 != 0) goto L_0x00d1
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.aq r5 = r5.r()     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.as r5 = r5.i()     // Catch:{ all -> 0x04f6 }
            java.lang.String r6 = "The engagement event does not include duration. appId"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.aq.a(r34)     // Catch:{ all -> 0x04f6 }
        L_0x00cd:
            r5.a(r6, r7)     // Catch:{ all -> 0x04f6 }
            goto L_0x00eb
        L_0x00d1:
            com.google.android.gms.internal.measurement.zzeu r4 = r2.zzahg     // Catch:{ all -> 0x04f6 }
            java.lang.String r5 = "_et"
            java.lang.Long r4 = r4.getLong(r5)     // Catch:{ all -> 0x04f6 }
            goto L_0x00eb
        L_0x00da:
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.aq r5 = r5.r()     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.as r5 = r5.i()     // Catch:{ all -> 0x04f6 }
            java.lang.String r6 = "The engagement event does not contain any parameters. appId"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.aq.a(r34)     // Catch:{ all -> 0x04f6 }
            goto L_0x00cd
        L_0x00eb:
            com.google.android.gms.internal.measurement.fx r8 = new com.google.android.gms.internal.measurement.fx     // Catch:{ all -> 0x04f6 }
            r8.<init>()     // Catch:{ all -> 0x04f6 }
            r9 = 1
            com.google.android.gms.internal.measurement.fx[] r5 = new com.google.android.gms.internal.measurement.fx[r9]     // Catch:{ all -> 0x04f6 }
            r5[r13] = r8     // Catch:{ all -> 0x04f6 }
            r14.c = r5     // Catch:{ all -> 0x04f6 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x04f6 }
            r8.c = r5     // Catch:{ all -> 0x04f6 }
            java.lang.String r5 = "android"
            r8.k = r5     // Catch:{ all -> 0x04f6 }
            java.lang.String r5 = r12.b()     // Catch:{ all -> 0x04f6 }
            r8.q = r5     // Catch:{ all -> 0x04f6 }
            java.lang.String r5 = r12.k()     // Catch:{ all -> 0x04f6 }
            r8.p = r5     // Catch:{ all -> 0x04f6 }
            java.lang.String r5 = r12.i()     // Catch:{ all -> 0x04f6 }
            r8.r = r5     // Catch:{ all -> 0x04f6 }
            long r5 = r12.j()     // Catch:{ all -> 0x04f6 }
            r16 = -2147483648(0xffffffff80000000, double:NaN)
            int r7 = (r5 > r16 ? 1 : (r5 == r16 ? 0 : -1))
            r23 = r14
            r14 = 0
            if (r7 != 0) goto L_0x0123
            r5 = r14
            goto L_0x0128
        L_0x0123:
            int r5 = (int) r5     // Catch:{ all -> 0x04f6 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x04f6 }
        L_0x0128:
            r8.E = r5     // Catch:{ all -> 0x04f6 }
            long r5 = r12.l()     // Catch:{ all -> 0x04f6 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x04f6 }
            r8.s = r5     // Catch:{ all -> 0x04f6 }
            java.lang.String r5 = r12.d()     // Catch:{ all -> 0x04f6 }
            r8.A = r5     // Catch:{ all -> 0x04f6 }
            long r5 = r12.m()     // Catch:{ all -> 0x04f6 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x04f6 }
            r8.x = r5     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x04f6 }
            boolean r5 = r5.y()     // Catch:{ all -> 0x04f6 }
            if (r5 == 0) goto L_0x0162
            boolean r5 = com.google.android.gms.internal.measurement.w.w()     // Catch:{ all -> 0x04f6 }
            if (r5 == 0) goto L_0x0162
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.w r5 = r5.b()     // Catch:{ all -> 0x04f6 }
            java.lang.String r6 = r8.q     // Catch:{ all -> 0x04f6 }
            boolean r5 = r5.c(r6)     // Catch:{ all -> 0x04f6 }
            if (r5 == 0) goto L_0x0162
            r8.I = r14     // Catch:{ all -> 0x04f6 }
        L_0x0162:
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.bb r5 = r5.c()     // Catch:{ all -> 0x04f6 }
            java.lang.String r6 = r12.b()     // Catch:{ all -> 0x04f6 }
            android.util.Pair r5 = r5.a(r6)     // Catch:{ all -> 0x04f6 }
            boolean r6 = r12.B()     // Catch:{ all -> 0x04f6 }
            if (r6 == 0) goto L_0x018e
            if (r5 == 0) goto L_0x018e
            java.lang.Object r6 = r5.first     // Catch:{ all -> 0x04f6 }
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ all -> 0x04f6 }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x04f6 }
            if (r6 != 0) goto L_0x018e
            java.lang.Object r6 = r5.first     // Catch:{ all -> 0x04f6 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x04f6 }
            r8.u = r6     // Catch:{ all -> 0x04f6 }
            java.lang.Object r5 = r5.second     // Catch:{ all -> 0x04f6 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x04f6 }
            r8.v = r5     // Catch:{ all -> 0x04f6 }
        L_0x018e:
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ag r5 = r5.v()     // Catch:{ all -> 0x04f6 }
            r5.z()     // Catch:{ all -> 0x04f6 }
            java.lang.String r5 = android.os.Build.MODEL     // Catch:{ all -> 0x04f6 }
            r8.m = r5     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ag r5 = r5.v()     // Catch:{ all -> 0x04f6 }
            r5.z()     // Catch:{ all -> 0x04f6 }
            java.lang.String r5 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x04f6 }
            r8.l = r5     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ag r5 = r5.v()     // Catch:{ all -> 0x04f6 }
            long r5 = r5.g_()     // Catch:{ all -> 0x04f6 }
            int r5 = (int) r5     // Catch:{ all -> 0x04f6 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x04f6 }
            r8.o = r5     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ag r5 = r5.v()     // Catch:{ all -> 0x04f6 }
            java.lang.String r5 = r5.g()     // Catch:{ all -> 0x04f6 }
            r8.n = r5     // Catch:{ all -> 0x04f6 }
            java.lang.String r5 = r12.c()     // Catch:{ all -> 0x04f6 }
            r8.w = r5     // Catch:{ all -> 0x04f6 }
            java.lang.String r5 = r12.f()     // Catch:{ all -> 0x04f6 }
            r8.D = r5     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.z r5 = r32.d()     // Catch:{ all -> 0x04f6 }
            java.lang.String r6 = r12.b()     // Catch:{ all -> 0x04f6 }
            java.util.List r5 = r5.a(r6)     // Catch:{ all -> 0x04f6 }
            int r6 = r5.size()     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ga[] r6 = new com.google.android.gms.internal.measurement.ga[r6]     // Catch:{ all -> 0x04f6 }
            r8.e = r6     // Catch:{ all -> 0x04f6 }
            if (r3 == 0) goto L_0x024b
            com.google.android.gms.internal.measurement.z r6 = r32.d()     // Catch:{ all -> 0x04f6 }
            java.lang.String r7 = r8.q     // Catch:{ all -> 0x04f6 }
            java.lang.String r13 = "_lte"
            com.google.android.gms.internal.measurement.ff r6 = r6.c(r7, r13)     // Catch:{ all -> 0x04f6 }
            if (r6 == 0) goto L_0x022f
            java.lang.Object r7 = r6.e     // Catch:{ all -> 0x04f6 }
            if (r7 != 0) goto L_0x01fa
            goto L_0x022f
        L_0x01fa:
            long r16 = r4.longValue()     // Catch:{ all -> 0x04f6 }
            int r7 = (r16 > r10 ? 1 : (r16 == r10 ? 0 : -1))
            if (r7 <= 0) goto L_0x024c
            com.google.android.gms.internal.measurement.ff r7 = new com.google.android.gms.internal.measurement.ff     // Catch:{ all -> 0x04f6 }
            java.lang.String r13 = r8.q     // Catch:{ all -> 0x04f6 }
            java.lang.String r18 = "auto"
            java.lang.String r19 = "_lte"
            com.google.android.gms.internal.measurement.bu r14 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.common.util.Clock r14 = r14.m()     // Catch:{ all -> 0x04f6 }
            long r20 = r14.currentTimeMillis()     // Catch:{ all -> 0x04f6 }
            java.lang.Object r6 = r6.e     // Catch:{ all -> 0x04f6 }
            java.lang.Long r6 = (java.lang.Long) r6     // Catch:{ all -> 0x04f6 }
            long r16 = r6.longValue()     // Catch:{ all -> 0x04f6 }
            long r24 = r4.longValue()     // Catch:{ all -> 0x04f6 }
            long r10 = r16 + r24
            java.lang.Long r22 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x04f6 }
            r16 = r7
            r17 = r13
            r16.<init>(r17, r18, r19, r20, r22)     // Catch:{ all -> 0x04f6 }
            r6 = r7
            goto L_0x024c
        L_0x022f:
            com.google.android.gms.internal.measurement.ff r6 = new com.google.android.gms.internal.measurement.ff     // Catch:{ all -> 0x04f6 }
            java.lang.String r7 = r8.q     // Catch:{ all -> 0x04f6 }
            java.lang.String r18 = "auto"
            java.lang.String r19 = "_lte"
            com.google.android.gms.internal.measurement.bu r10 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.common.util.Clock r10 = r10.m()     // Catch:{ all -> 0x04f6 }
            long r20 = r10.currentTimeMillis()     // Catch:{ all -> 0x04f6 }
            r16 = r6
            r17 = r7
            r22 = r4
            r16.<init>(r17, r18, r19, r20, r22)     // Catch:{ all -> 0x04f6 }
            goto L_0x024c
        L_0x024b:
            r6 = 0
        L_0x024c:
            r7 = 0
            r10 = 0
        L_0x024e:
            int r11 = r5.size()     // Catch:{ all -> 0x04f6 }
            if (r7 >= r11) goto L_0x02aa
            com.google.android.gms.internal.measurement.ga r11 = new com.google.android.gms.internal.measurement.ga     // Catch:{ all -> 0x04f6 }
            r11.<init>()     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ga[] r13 = r8.e     // Catch:{ all -> 0x04f6 }
            r13[r7] = r11     // Catch:{ all -> 0x04f6 }
            java.lang.Object r13 = r5.get(r7)     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ff r13 = (com.google.android.gms.internal.measurement.ff) r13     // Catch:{ all -> 0x04f6 }
            java.lang.String r13 = r13.c     // Catch:{ all -> 0x04f6 }
            r11.d = r13     // Catch:{ all -> 0x04f6 }
            java.lang.Object r13 = r5.get(r7)     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ff r13 = (com.google.android.gms.internal.measurement.ff) r13     // Catch:{ all -> 0x04f6 }
            long r13 = r13.d     // Catch:{ all -> 0x04f6 }
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x04f6 }
            r11.c = r13     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.fe r13 = r32.f()     // Catch:{ all -> 0x04f6 }
            java.lang.Object r14 = r5.get(r7)     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ff r14 = (com.google.android.gms.internal.measurement.ff) r14     // Catch:{ all -> 0x04f6 }
            java.lang.Object r14 = r14.e     // Catch:{ all -> 0x04f6 }
            r13.a(r11, r14)     // Catch:{ all -> 0x04f6 }
            if (r3 == 0) goto L_0x02a7
            java.lang.String r13 = "_lte"
            java.lang.String r14 = r11.d     // Catch:{ all -> 0x04f6 }
            boolean r13 = r13.equals(r14)     // Catch:{ all -> 0x04f6 }
            if (r13 == 0) goto L_0x02a7
            java.lang.Object r10 = r6.e     // Catch:{ all -> 0x04f6 }
            java.lang.Long r10 = (java.lang.Long) r10     // Catch:{ all -> 0x04f6 }
            r11.f = r10     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.bu r10 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.common.util.Clock r10 = r10.m()     // Catch:{ all -> 0x04f6 }
            long r13 = r10.currentTimeMillis()     // Catch:{ all -> 0x04f6 }
            java.lang.Long r10 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x04f6 }
            r11.c = r10     // Catch:{ all -> 0x04f6 }
            r10 = r11
        L_0x02a7:
            int r7 = r7 + 1
            goto L_0x024e
        L_0x02aa:
            if (r3 == 0) goto L_0x02e3
            if (r10 != 0) goto L_0x02e3
            com.google.android.gms.internal.measurement.ga r3 = new com.google.android.gms.internal.measurement.ga     // Catch:{ all -> 0x04f6 }
            r3.<init>()     // Catch:{ all -> 0x04f6 }
            java.lang.String r5 = "_lte"
            r3.d = r5     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.bu r5 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.common.util.Clock r5 = r5.m()     // Catch:{ all -> 0x04f6 }
            long r10 = r5.currentTimeMillis()     // Catch:{ all -> 0x04f6 }
            java.lang.Long r5 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x04f6 }
            r3.c = r5     // Catch:{ all -> 0x04f6 }
            java.lang.Object r5 = r6.e     // Catch:{ all -> 0x04f6 }
            java.lang.Long r5 = (java.lang.Long) r5     // Catch:{ all -> 0x04f6 }
            r3.f = r5     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ga[] r5 = r8.e     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ga[] r7 = r8.e     // Catch:{ all -> 0x04f6 }
            int r7 = r7.length     // Catch:{ all -> 0x04f6 }
            int r7 = r7 + r9
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r5, r7)     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ga[] r5 = (com.google.android.gms.internal.measurement.ga[]) r5     // Catch:{ all -> 0x04f6 }
            r8.e = r5     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ga[] r5 = r8.e     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ga[] r7 = r8.e     // Catch:{ all -> 0x04f6 }
            int r7 = r7.length     // Catch:{ all -> 0x04f6 }
            int r7 = r7 - r9
            r5[r7] = r3     // Catch:{ all -> 0x04f6 }
        L_0x02e3:
            long r3 = r4.longValue()     // Catch:{ all -> 0x04f6 }
            r10 = 0
            int r5 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r5 <= 0) goto L_0x02f4
            com.google.android.gms.internal.measurement.z r3 = r32.d()     // Catch:{ all -> 0x04f6 }
            r3.a(r6)     // Catch:{ all -> 0x04f6 }
        L_0x02f4:
            com.google.android.gms.internal.measurement.zzeu r3 = r2.zzahg     // Catch:{ all -> 0x04f6 }
            android.os.Bundle r14 = r3.zzin()     // Catch:{ all -> 0x04f6 }
            java.lang.String r3 = "_iap"
            java.lang.String r4 = r2.name     // Catch:{ all -> 0x04f6 }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x04f6 }
            r4 = 1
            if (r3 == 0) goto L_0x031f
            java.lang.String r3 = "_c"
            r14.putLong(r3, r4)     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.bu r3 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.aq r3 = r3.r()     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.as r3 = r3.v()     // Catch:{ all -> 0x04f6 }
            java.lang.String r6 = "Marking in-app purchase as real-time"
            r3.a(r6)     // Catch:{ all -> 0x04f6 }
            java.lang.String r3 = "_r"
            r14.putLong(r3, r4)     // Catch:{ all -> 0x04f6 }
        L_0x031f:
            java.lang.String r3 = "_o"
            java.lang.String r6 = r2.origin     // Catch:{ all -> 0x04f6 }
            r14.putString(r3, r6)     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.bu r3 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.fg r3 = r3.k()     // Catch:{ all -> 0x04f6 }
            java.lang.String r6 = r8.q     // Catch:{ all -> 0x04f6 }
            boolean r3 = r3.h(r6)     // Catch:{ all -> 0x04f6 }
            if (r3 == 0) goto L_0x0352
            com.google.android.gms.internal.measurement.bu r3 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.fg r3 = r3.k()     // Catch:{ all -> 0x04f6 }
            java.lang.String r6 = "_dbg"
            java.lang.Long r7 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x04f6 }
            r3.a(r14, r6, r7)     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.bu r3 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.fg r3 = r3.k()     // Catch:{ all -> 0x04f6 }
            java.lang.String r6 = "_r"
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x04f6 }
            r3.a(r14, r6, r4)     // Catch:{ all -> 0x04f6 }
        L_0x0352:
            com.google.android.gms.internal.measurement.z r3 = r32.d()     // Catch:{ all -> 0x04f6 }
            java.lang.String r4 = r2.name     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ai r3 = r3.a(r15, r4)     // Catch:{ all -> 0x04f6 }
            if (r3 != 0) goto L_0x039d
            com.google.android.gms.internal.measurement.ai r13 = new com.google.android.gms.internal.measurement.ai     // Catch:{ all -> 0x04f6 }
            java.lang.String r5 = r2.name     // Catch:{ all -> 0x04f6 }
            r6 = 1
            r16 = 0
            long r3 = r2.zzahr     // Catch:{ all -> 0x04f6 }
            r18 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r24 = r3
            r3 = r13
            r4 = r15
            r28 = r8
            r8 = r16
            r26 = r10
            r10 = r24
            r29 = r12
            r30 = r13
            r12 = r18
            r17 = r14
            r31 = r23
            r18 = 0
            r14 = r20
            r15 = r21
            r16 = r22
            r3.<init>(r4, r5, r6, r8, r10, r12, r14, r15, r16)     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.z r3 = r32.d()     // Catch:{ all -> 0x04f6 }
            r4 = r30
            r3.a(r4)     // Catch:{ all -> 0x04f6 }
            r9 = r26
            goto L_0x03bd
        L_0x039d:
            r28 = r8
            r26 = r10
            r29 = r12
            r17 = r14
            r31 = r23
            r18 = 0
            long r4 = r3.e     // Catch:{ all -> 0x04f6 }
            long r6 = r2.zzahr     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ai r3 = r3.a(r6)     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ai r3 = r3.a()     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.z r6 = r32.d()     // Catch:{ all -> 0x04f6 }
            r6.a(r3)     // Catch:{ all -> 0x04f6 }
            r9 = r4
        L_0x03bd:
            com.google.android.gms.internal.measurement.ah r12 = new com.google.android.gms.internal.measurement.ah     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.bu r3 = r1.i     // Catch:{ all -> 0x04f6 }
            java.lang.String r4 = r2.origin     // Catch:{ all -> 0x04f6 }
            java.lang.String r6 = r2.name     // Catch:{ all -> 0x04f6 }
            long r7 = r2.zzahr     // Catch:{ all -> 0x04f6 }
            r2 = r12
            r5 = r34
            r11 = r17
            r2.<init>(r3, r4, r5, r6, r7, r9, r11)     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.fu r2 = new com.google.android.gms.internal.measurement.fu     // Catch:{ all -> 0x04f6 }
            r2.<init>()     // Catch:{ all -> 0x04f6 }
            r3 = 1
            com.google.android.gms.internal.measurement.fu[] r3 = new com.google.android.gms.internal.measurement.fu[r3]     // Catch:{ all -> 0x04f6 }
            r4 = 0
            r3[r4] = r2     // Catch:{ all -> 0x04f6 }
            r5 = r28
            r5.d = r3     // Catch:{ all -> 0x04f6 }
            long r6 = r12.c     // Catch:{ all -> 0x04f6 }
            java.lang.Long r3 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x04f6 }
            r2.e = r3     // Catch:{ all -> 0x04f6 }
            java.lang.String r3 = r12.b     // Catch:{ all -> 0x04f6 }
            r2.d = r3     // Catch:{ all -> 0x04f6 }
            long r6 = r12.d     // Catch:{ all -> 0x04f6 }
            java.lang.Long r3 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x04f6 }
            r2.f = r3     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.zzeu r3 = r12.e     // Catch:{ all -> 0x04f6 }
            int r3 = r3.size()     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.fv[] r3 = new com.google.android.gms.internal.measurement.fv[r3]     // Catch:{ all -> 0x04f6 }
            r2.c = r3     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.zzeu r3 = r12.e     // Catch:{ all -> 0x04f6 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x04f6 }
            r6 = r4
        L_0x0403:
            boolean r7 = r3.hasNext()     // Catch:{ all -> 0x04f6 }
            if (r7 == 0) goto L_0x042b
            java.lang.Object r7 = r3.next()     // Catch:{ all -> 0x04f6 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.fv r8 = new com.google.android.gms.internal.measurement.fv     // Catch:{ all -> 0x04f6 }
            r8.<init>()     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.fv[] r9 = r2.c     // Catch:{ all -> 0x04f6 }
            int r10 = r6 + 1
            r9[r6] = r8     // Catch:{ all -> 0x04f6 }
            r8.c = r7     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.zzeu r6 = r12.e     // Catch:{ all -> 0x04f6 }
            java.lang.Object r6 = r6.get(r7)     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.fe r7 = r32.f()     // Catch:{ all -> 0x04f6 }
            r7.a(r8, r6)     // Catch:{ all -> 0x04f6 }
            r6 = r10
            goto L_0x0403
        L_0x042b:
            r3 = r29
            java.lang.String r6 = r3.b()     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.ga[] r7 = r5.e     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.fu[] r8 = r5.d     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.fs[] r6 = r1.a(r6, r7, r8)     // Catch:{ all -> 0x04f6 }
            r5.C = r6     // Catch:{ all -> 0x04f6 }
            java.lang.Long r6 = r2.e     // Catch:{ all -> 0x04f6 }
            r5.g = r6     // Catch:{ all -> 0x04f6 }
            java.lang.Long r2 = r2.e     // Catch:{ all -> 0x04f6 }
            r5.h = r2     // Catch:{ all -> 0x04f6 }
            long r6 = r3.h()     // Catch:{ all -> 0x04f6 }
            int r2 = (r6 > r26 ? 1 : (r6 == r26 ? 0 : -1))
            if (r2 == 0) goto L_0x0450
            java.lang.Long r14 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x04f6 }
            goto L_0x0452
        L_0x0450:
            r14 = r18
        L_0x0452:
            r5.j = r14     // Catch:{ all -> 0x04f6 }
            long r8 = r3.g()     // Catch:{ all -> 0x04f6 }
            int r2 = (r8 > r26 ? 1 : (r8 == r26 ? 0 : -1))
            if (r2 != 0) goto L_0x045d
            goto L_0x045e
        L_0x045d:
            r6 = r8
        L_0x045e:
            int r2 = (r6 > r26 ? 1 : (r6 == r26 ? 0 : -1))
            if (r2 == 0) goto L_0x0467
            java.lang.Long r14 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x04f6 }
            goto L_0x0469
        L_0x0467:
            r14 = r18
        L_0x0469:
            r5.i = r14     // Catch:{ all -> 0x04f6 }
            r3.r()     // Catch:{ all -> 0x04f6 }
            long r6 = r3.o()     // Catch:{ all -> 0x04f6 }
            int r2 = (int) r6     // Catch:{ all -> 0x04f6 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x04f6 }
            r5.y = r2     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.bu r2 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.w r2 = r2.b()     // Catch:{ all -> 0x04f6 }
            long r6 = r2.f()     // Catch:{ all -> 0x04f6 }
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x04f6 }
            r5.t = r2     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.bu r2 = r1.i     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.common.util.Clock r2 = r2.m()     // Catch:{ all -> 0x04f6 }
            long r6 = r2.currentTimeMillis()     // Catch:{ all -> 0x04f6 }
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x04f6 }
            r5.f = r2     // Catch:{ all -> 0x04f6 }
            java.lang.Boolean r2 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x04f6 }
            r5.B = r2     // Catch:{ all -> 0x04f6 }
            java.lang.Long r2 = r5.g     // Catch:{ all -> 0x04f6 }
            long r6 = r2.longValue()     // Catch:{ all -> 0x04f6 }
            r3.a(r6)     // Catch:{ all -> 0x04f6 }
            java.lang.Long r2 = r5.h     // Catch:{ all -> 0x04f6 }
            long r5 = r2.longValue()     // Catch:{ all -> 0x04f6 }
            r3.b(r5)     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.z r2 = r32.d()     // Catch:{ all -> 0x04f6 }
            r2.a(r3)     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.z r2 = r32.d()     // Catch:{ all -> 0x04f6 }
            r2.g()     // Catch:{ all -> 0x04f6 }
            com.google.android.gms.internal.measurement.z r2 = r32.d()
            r2.h()
            r2 = r31
            int r3 = r2.d()     // Catch:{ IOException -> 0x04e0 }
            byte[] r3 = new byte[r3]     // Catch:{ IOException -> 0x04e0 }
            int r5 = r3.length     // Catch:{ IOException -> 0x04e0 }
            com.google.android.gms.internal.measurement.d r4 = com.google.android.gms.internal.measurement.d.a(r3, r4, r5)     // Catch:{ IOException -> 0x04e0 }
            r2.a(r4)     // Catch:{ IOException -> 0x04e0 }
            r4.a()     // Catch:{ IOException -> 0x04e0 }
            com.google.android.gms.internal.measurement.fe r2 = r32.f()     // Catch:{ IOException -> 0x04e0 }
            byte[] r2 = r2.b(r3)     // Catch:{ IOException -> 0x04e0 }
            return r2
        L_0x04e0:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.internal.measurement.bu r3 = r1.i
            com.google.android.gms.internal.measurement.aq r3 = r3.r()
            com.google.android.gms.internal.measurement.as r3 = r3.h_()
            java.lang.String r4 = "Data loss. Failed to bundle and serialize. appId"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.aq.a(r34)
            r3.a(r4, r5, r2)
            return r18
        L_0x04f6:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.internal.measurement.z r3 = r32.d()
            r3.h()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.ey.b(com.google.android.gms.internal.measurement.zzex, java.lang.String):byte[]");
    }

    public final au c() {
        b((ex) this.c);
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x03b6 A[Catch:{ SQLiteException -> 0x0144, all -> 0x03df }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01ea A[Catch:{ SQLiteException -> 0x0144, all -> 0x03df }] */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c(com.google.android.gms.internal.measurement.zzeb r21) {
        /*
            r20 = this;
            r1 = r20
            r2 = r21
            r20.v()
            r20.i()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r21)
            java.lang.String r3 = r2.packageName
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)
            java.lang.String r3 = r2.zzafa
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x001b
            return
        L_0x001b:
            com.google.android.gms.internal.measurement.z r3 = r20.d()
            java.lang.String r4 = r2.packageName
            com.google.android.gms.internal.measurement.t r3 = r3.b(r4)
            r4 = 0
            if (r3 == 0) goto L_0x004e
            java.lang.String r6 = r3.d()
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 == 0) goto L_0x004e
            java.lang.String r6 = r2.zzafa
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L_0x004e
            r3.g(r4)
            com.google.android.gms.internal.measurement.z r6 = r20.d()
            r6.a(r3)
            com.google.android.gms.internal.measurement.bp r3 = r20.p()
            java.lang.String r6 = r2.packageName
            r3.d(r6)
        L_0x004e:
            boolean r3 = r2.zzafk
            if (r3 != 0) goto L_0x0056
            r20.e(r21)
            return
        L_0x0056:
            long r6 = r2.zzaga
            int r3 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r3 != 0) goto L_0x0066
            com.google.android.gms.internal.measurement.bu r3 = r1.i
            com.google.android.gms.common.util.Clock r3 = r3.m()
            long r6 = r3.currentTimeMillis()
        L_0x0066:
            int r3 = r2.zzagb
            r14 = 0
            r15 = 1
            if (r3 == 0) goto L_0x0088
            if (r3 == r15) goto L_0x0088
            com.google.android.gms.internal.measurement.bu r8 = r1.i
            com.google.android.gms.internal.measurement.aq r8 = r8.r()
            com.google.android.gms.internal.measurement.as r8 = r8.i()
            java.lang.String r9 = "Incorrect app type, assuming installed app. appId, appType"
            java.lang.String r10 = r2.packageName
            java.lang.Object r10 = com.google.android.gms.internal.measurement.aq.a(r10)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r8.a(r9, r10, r3)
            r3 = r14
        L_0x0088:
            com.google.android.gms.internal.measurement.z r8 = r20.d()
            r8.f()
            com.google.android.gms.internal.measurement.z r8 = r20.d()     // Catch:{ all -> 0x03df }
            java.lang.String r9 = r2.packageName     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.t r8 = r8.b(r9)     // Catch:{ all -> 0x03df }
            r16 = 0
            if (r8 == 0) goto L_0x0159
            java.lang.String r9 = r8.d()     // Catch:{ all -> 0x03df }
            if (r9 == 0) goto L_0x0159
            java.lang.String r9 = r8.d()     // Catch:{ all -> 0x03df }
            java.lang.String r10 = r2.zzafa     // Catch:{ all -> 0x03df }
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x03df }
            if (r9 != 0) goto L_0x0159
            com.google.android.gms.internal.measurement.bu r9 = r1.i     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.aq r9 = r9.r()     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.as r9 = r9.i()     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "New GMP App Id passed in. Removing cached database data. appId"
            java.lang.String r11 = r8.b()     // Catch:{ all -> 0x03df }
            java.lang.Object r11 = com.google.android.gms.internal.measurement.aq.a(r11)     // Catch:{ all -> 0x03df }
            r9.a(r10, r11)     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.z r9 = r20.d()     // Catch:{ all -> 0x03df }
            java.lang.String r8 = r8.b()     // Catch:{ all -> 0x03df }
            r9.I()     // Catch:{ all -> 0x03df }
            r9.d()     // Catch:{ all -> 0x03df }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)     // Catch:{ all -> 0x03df }
            android.database.sqlite.SQLiteDatabase r10 = r9.i()     // Catch:{ SQLiteException -> 0x0144 }
            java.lang.String[] r11 = new java.lang.String[r15]     // Catch:{ SQLiteException -> 0x0144 }
            r11[r14] = r8     // Catch:{ SQLiteException -> 0x0144 }
            java.lang.String r12 = "events"
            java.lang.String r13 = "app_id=?"
            int r12 = r10.delete(r12, r13, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r14
            java.lang.String r13 = "user_attributes"
            java.lang.String r14 = "app_id=?"
            int r13 = r10.delete(r13, r14, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r13
            java.lang.String r13 = "conditional_properties"
            java.lang.String r14 = "app_id=?"
            int r13 = r10.delete(r13, r14, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r13
            java.lang.String r13 = "apps"
            java.lang.String r14 = "app_id=?"
            int r13 = r10.delete(r13, r14, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r13
            java.lang.String r13 = "raw_events"
            java.lang.String r14 = "app_id=?"
            int r13 = r10.delete(r13, r14, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r13
            java.lang.String r13 = "raw_events_metadata"
            java.lang.String r14 = "app_id=?"
            int r13 = r10.delete(r13, r14, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r13
            java.lang.String r13 = "event_filters"
            java.lang.String r14 = "app_id=?"
            int r13 = r10.delete(r13, r14, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r13
            java.lang.String r13 = "property_filters"
            java.lang.String r14 = "app_id=?"
            int r13 = r10.delete(r13, r14, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r13
            java.lang.String r13 = "audience_filter_values"
            java.lang.String r14 = "app_id=?"
            int r10 = r10.delete(r13, r14, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r10
            if (r12 <= 0) goto L_0x0157
            com.google.android.gms.internal.measurement.aq r10 = r9.r()     // Catch:{ SQLiteException -> 0x0144 }
            com.google.android.gms.internal.measurement.as r10 = r10.w()     // Catch:{ SQLiteException -> 0x0144 }
            java.lang.String r11 = "Deleted application data. app, records"
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ SQLiteException -> 0x0144 }
            r10.a(r11, r8, r12)     // Catch:{ SQLiteException -> 0x0144 }
            goto L_0x0157
        L_0x0144:
            r0 = move-exception
            r10 = r0
            com.google.android.gms.internal.measurement.aq r9 = r9.r()     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.as r9 = r9.h_()     // Catch:{ all -> 0x03df }
            java.lang.String r11 = "Error deleting application data. appId, error"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.aq.a(r8)     // Catch:{ all -> 0x03df }
            r9.a(r11, r8, r10)     // Catch:{ all -> 0x03df }
        L_0x0157:
            r8 = r16
        L_0x0159:
            if (r8 == 0) goto L_0x01c9
            long r9 = r8.j()     // Catch:{ all -> 0x03df }
            r11 = -2147483648(0xffffffff80000000, double:NaN)
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 == 0) goto L_0x0195
            long r9 = r8.j()     // Catch:{ all -> 0x03df }
            long r11 = r2.zzafg     // Catch:{ all -> 0x03df }
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 == 0) goto L_0x01c9
            android.os.Bundle r9 = new android.os.Bundle     // Catch:{ all -> 0x03df }
            r9.<init>()     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "_pv"
            java.lang.String r8 = r8.i()     // Catch:{ all -> 0x03df }
            r9.putString(r10, r8)     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzex r14 = new com.google.android.gms.internal.measurement.zzex     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "_au"
            com.google.android.gms.internal.measurement.zzeu r11 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03df }
            r11.<init>(r9)     // Catch:{ all -> 0x03df }
            java.lang.String r12 = "auto"
            r8 = r14
            r9 = r10
            r10 = r11
            r11 = r12
            r12 = r6
            r8.<init>(r9, r10, r11, r12)     // Catch:{ all -> 0x03df }
        L_0x0191:
            r1.a(r14, r2)     // Catch:{ all -> 0x03df }
            goto L_0x01c9
        L_0x0195:
            java.lang.String r9 = r8.i()     // Catch:{ all -> 0x03df }
            if (r9 == 0) goto L_0x01c9
            java.lang.String r9 = r8.i()     // Catch:{ all -> 0x03df }
            java.lang.String r10 = r2.zztg     // Catch:{ all -> 0x03df }
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x03df }
            if (r9 != 0) goto L_0x01c9
            android.os.Bundle r9 = new android.os.Bundle     // Catch:{ all -> 0x03df }
            r9.<init>()     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "_pv"
            java.lang.String r8 = r8.i()     // Catch:{ all -> 0x03df }
            r9.putString(r10, r8)     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzex r14 = new com.google.android.gms.internal.measurement.zzex     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "_au"
            com.google.android.gms.internal.measurement.zzeu r11 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03df }
            r11.<init>(r9)     // Catch:{ all -> 0x03df }
            java.lang.String r12 = "auto"
            r8 = r14
            r9 = r10
            r10 = r11
            r11 = r12
            r12 = r6
            r8.<init>(r9, r10, r11, r12)     // Catch:{ all -> 0x03df }
            goto L_0x0191
        L_0x01c9:
            r20.e(r21)     // Catch:{ all -> 0x03df }
            if (r3 != 0) goto L_0x01db
            com.google.android.gms.internal.measurement.z r8 = r20.d()     // Catch:{ all -> 0x03df }
            java.lang.String r9 = r2.packageName     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "_f"
        L_0x01d6:
            com.google.android.gms.internal.measurement.ai r8 = r8.a(r9, r10)     // Catch:{ all -> 0x03df }
            goto L_0x01e8
        L_0x01db:
            if (r3 != r15) goto L_0x01e6
            com.google.android.gms.internal.measurement.z r8 = r20.d()     // Catch:{ all -> 0x03df }
            java.lang.String r9 = r2.packageName     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "_v"
            goto L_0x01d6
        L_0x01e6:
            r8 = r16
        L_0x01e8:
            if (r8 != 0) goto L_0x03b6
            r8 = 3600000(0x36ee80, double:1.7786363E-317)
            long r10 = r6 / r8
            r13 = 1
            long r17 = r13 + r10
            long r8 = r8 * r17
            if (r3 != 0) goto L_0x0345
            com.google.android.gms.internal.measurement.zzka r3 = new com.google.android.gms.internal.measurement.zzka     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "_fot"
            java.lang.Long r12 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x03df }
            java.lang.String r17 = "auto"
            r8 = r3
            r9 = r10
            r10 = r6
            r4 = r13
            r13 = r17
            r8.<init>(r9, r10, r12, r13)     // Catch:{ all -> 0x03df }
            r1.a(r3, r2)     // Catch:{ all -> 0x03df }
            r20.v()     // Catch:{ all -> 0x03df }
            r20.i()     // Catch:{ all -> 0x03df }
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x03df }
            r3.<init>()     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_c"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_r"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_uwa"
            r9 = 0
            r3.putLong(r8, r9)     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_pfo"
            r3.putLong(r8, r9)     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_sys"
            r3.putLong(r8, r9)     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_sysu"
            r3.putLong(r8, r9)     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.bu r8 = r1.i     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.w r8 = r8.b()     // Catch:{ all -> 0x03df }
            java.lang.String r9 = r2.packageName     // Catch:{ all -> 0x03df }
            boolean r8 = r8.k(r9)     // Catch:{ all -> 0x03df }
            if (r8 == 0) goto L_0x024f
            boolean r8 = r2.zzagc     // Catch:{ all -> 0x03df }
            if (r8 == 0) goto L_0x024f
            java.lang.String r8 = "_dac"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
        L_0x024f:
            com.google.android.gms.internal.measurement.bu r8 = r1.i     // Catch:{ all -> 0x03df }
            android.content.Context r8 = r8.n()     // Catch:{ all -> 0x03df }
            android.content.pm.PackageManager r8 = r8.getPackageManager()     // Catch:{ all -> 0x03df }
            if (r8 != 0) goto L_0x0272
            com.google.android.gms.internal.measurement.bu r8 = r1.i     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.aq r8 = r8.r()     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.as r8 = r8.h_()     // Catch:{ all -> 0x03df }
            java.lang.String r9 = "PackageManager is null, first open report might be inaccurate. appId"
            java.lang.String r10 = r2.packageName     // Catch:{ all -> 0x03df }
            java.lang.Object r10 = com.google.android.gms.internal.measurement.aq.a(r10)     // Catch:{ all -> 0x03df }
            r8.a(r9, r10)     // Catch:{ all -> 0x03df }
            goto L_0x0311
        L_0x0272:
            com.google.android.gms.internal.measurement.bu r8 = r1.i     // Catch:{ NameNotFoundException -> 0x0284 }
            android.content.Context r8 = r8.n()     // Catch:{ NameNotFoundException -> 0x0284 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r8 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r8)     // Catch:{ NameNotFoundException -> 0x0284 }
            java.lang.String r9 = r2.packageName     // Catch:{ NameNotFoundException -> 0x0284 }
            r10 = 0
            android.content.pm.PackageInfo r8 = r8.getPackageInfo(r9, r10)     // Catch:{ NameNotFoundException -> 0x0284 }
            goto L_0x029d
        L_0x0284:
            r0 = move-exception
            r8 = r0
            com.google.android.gms.internal.measurement.bu r9 = r1.i     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.aq r9 = r9.r()     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.as r9 = r9.h_()     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "Package info is null, first open report might be inaccurate. appId"
            java.lang.String r11 = r2.packageName     // Catch:{ all -> 0x03df }
            java.lang.Object r11 = com.google.android.gms.internal.measurement.aq.a(r11)     // Catch:{ all -> 0x03df }
            r9.a(r10, r11, r8)     // Catch:{ all -> 0x03df }
            r8 = r16
        L_0x029d:
            if (r8 == 0) goto L_0x02cf
            long r9 = r8.firstInstallTime     // Catch:{ all -> 0x03df }
            r11 = 0
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 == 0) goto L_0x02cf
            long r9 = r8.firstInstallTime     // Catch:{ all -> 0x03df }
            long r11 = r8.lastUpdateTime     // Catch:{ all -> 0x03df }
            int r8 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r8 == 0) goto L_0x02b6
            java.lang.String r8 = "_uwa"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
            r8 = 0
            goto L_0x02b7
        L_0x02b6:
            r8 = r15
        L_0x02b7:
            com.google.android.gms.internal.measurement.zzka r14 = new com.google.android.gms.internal.measurement.zzka     // Catch:{ all -> 0x03df }
            java.lang.String r9 = "_fi"
            if (r8 == 0) goto L_0x02bf
            r10 = r4
            goto L_0x02c1
        L_0x02bf:
            r10 = 0
        L_0x02c1:
            java.lang.Long r12 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x03df }
            java.lang.String r13 = "auto"
            r8 = r14
            r10 = r6
            r8.<init>(r9, r10, r12, r13)     // Catch:{ all -> 0x03df }
            r1.a(r14, r2)     // Catch:{ all -> 0x03df }
        L_0x02cf:
            com.google.android.gms.internal.measurement.bu r8 = r1.i     // Catch:{ NameNotFoundException -> 0x02e1 }
            android.content.Context r8 = r8.n()     // Catch:{ NameNotFoundException -> 0x02e1 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r8 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r8)     // Catch:{ NameNotFoundException -> 0x02e1 }
            java.lang.String r9 = r2.packageName     // Catch:{ NameNotFoundException -> 0x02e1 }
            r10 = 0
            android.content.pm.ApplicationInfo r8 = r8.getApplicationInfo(r9, r10)     // Catch:{ NameNotFoundException -> 0x02e1 }
            goto L_0x02fa
        L_0x02e1:
            r0 = move-exception
            r8 = r0
            com.google.android.gms.internal.measurement.bu r9 = r1.i     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.aq r9 = r9.r()     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.as r9 = r9.h_()     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "Application info is null, first open report might be inaccurate. appId"
            java.lang.String r11 = r2.packageName     // Catch:{ all -> 0x03df }
            java.lang.Object r11 = com.google.android.gms.internal.measurement.aq.a(r11)     // Catch:{ all -> 0x03df }
            r9.a(r10, r11, r8)     // Catch:{ all -> 0x03df }
            r8 = r16
        L_0x02fa:
            if (r8 == 0) goto L_0x0311
            int r9 = r8.flags     // Catch:{ all -> 0x03df }
            r9 = r9 & r15
            if (r9 == 0) goto L_0x0306
            java.lang.String r9 = "_sys"
            r3.putLong(r9, r4)     // Catch:{ all -> 0x03df }
        L_0x0306:
            int r8 = r8.flags     // Catch:{ all -> 0x03df }
            r8 = r8 & 128(0x80, float:1.794E-43)
            if (r8 == 0) goto L_0x0311
            java.lang.String r8 = "_sysu"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
        L_0x0311:
            com.google.android.gms.internal.measurement.z r8 = r20.d()     // Catch:{ all -> 0x03df }
            java.lang.String r9 = r2.packageName     // Catch:{ all -> 0x03df }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9)     // Catch:{ all -> 0x03df }
            r8.d()     // Catch:{ all -> 0x03df }
            r8.I()     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "first_open_count"
            long r8 = r8.h(r9, r10)     // Catch:{ all -> 0x03df }
            r10 = 0
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 < 0) goto L_0x0331
            java.lang.String r10 = "_pfo"
            r3.putLong(r10, r8)     // Catch:{ all -> 0x03df }
        L_0x0331:
            com.google.android.gms.internal.measurement.zzex r14 = new com.google.android.gms.internal.measurement.zzex     // Catch:{ all -> 0x03df }
            java.lang.String r9 = "_f"
            com.google.android.gms.internal.measurement.zzeu r10 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03df }
            r10.<init>(r3)     // Catch:{ all -> 0x03df }
            java.lang.String r11 = "auto"
            r8 = r14
            r12 = r6
            r8.<init>(r9, r10, r11, r12)     // Catch:{ all -> 0x03df }
        L_0x0341:
            r1.a(r14, r2)     // Catch:{ all -> 0x03df }
            goto L_0x0398
        L_0x0345:
            r4 = r13
            if (r3 != r15) goto L_0x0398
            com.google.android.gms.internal.measurement.zzka r3 = new com.google.android.gms.internal.measurement.zzka     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "_fvt"
            java.lang.Long r12 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x03df }
            java.lang.String r13 = "auto"
            r8 = r3
            r9 = r10
            r10 = r6
            r8.<init>(r9, r10, r12, r13)     // Catch:{ all -> 0x03df }
            r1.a(r3, r2)     // Catch:{ all -> 0x03df }
            r20.v()     // Catch:{ all -> 0x03df }
            r20.i()     // Catch:{ all -> 0x03df }
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x03df }
            r3.<init>()     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_c"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_r"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.bu r8 = r1.i     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.w r8 = r8.b()     // Catch:{ all -> 0x03df }
            java.lang.String r9 = r2.packageName     // Catch:{ all -> 0x03df }
            boolean r8 = r8.k(r9)     // Catch:{ all -> 0x03df }
            if (r8 == 0) goto L_0x0387
            boolean r8 = r2.zzagc     // Catch:{ all -> 0x03df }
            if (r8 == 0) goto L_0x0387
            java.lang.String r8 = "_dac"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
        L_0x0387:
            com.google.android.gms.internal.measurement.zzex r14 = new com.google.android.gms.internal.measurement.zzex     // Catch:{ all -> 0x03df }
            java.lang.String r9 = "_v"
            com.google.android.gms.internal.measurement.zzeu r10 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03df }
            r10.<init>(r3)     // Catch:{ all -> 0x03df }
            java.lang.String r11 = "auto"
            r8 = r14
            r12 = r6
            r8.<init>(r9, r10, r11, r12)     // Catch:{ all -> 0x03df }
            goto L_0x0341
        L_0x0398:
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x03df }
            r3.<init>()     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_et"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzex r4 = new com.google.android.gms.internal.measurement.zzex     // Catch:{ all -> 0x03df }
            java.lang.String r9 = "_e"
            com.google.android.gms.internal.measurement.zzeu r10 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03df }
            r10.<init>(r3)     // Catch:{ all -> 0x03df }
            java.lang.String r11 = "auto"
            r8 = r4
            r12 = r6
            r8.<init>(r9, r10, r11, r12)     // Catch:{ all -> 0x03df }
        L_0x03b2:
            r1.a(r4, r2)     // Catch:{ all -> 0x03df }
            goto L_0x03d0
        L_0x03b6:
            boolean r3 = r2.zzafz     // Catch:{ all -> 0x03df }
            if (r3 == 0) goto L_0x03d0
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x03df }
            r3.<init>()     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzex r4 = new com.google.android.gms.internal.measurement.zzex     // Catch:{ all -> 0x03df }
            java.lang.String r9 = "_cd"
            com.google.android.gms.internal.measurement.zzeu r10 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03df }
            r10.<init>(r3)     // Catch:{ all -> 0x03df }
            java.lang.String r11 = "auto"
            r8 = r4
            r12 = r6
            r8.<init>(r9, r10, r11, r12)     // Catch:{ all -> 0x03df }
            goto L_0x03b2
        L_0x03d0:
            com.google.android.gms.internal.measurement.z r2 = r20.d()     // Catch:{ all -> 0x03df }
            r2.g()     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.z r2 = r20.d()
            r2.h()
            return
        L_0x03df:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.internal.measurement.z r3 = r20.d()
            r3.h()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.ey.c(com.google.android.gms.internal.measurement.zzeb):void");
    }

    public final z d() {
        b((ex) this.d);
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public final String d(zzeb zzeb) {
        try {
            return (String) this.i.q().a((Callable<V>) new fc<V>(this, zzeb)).get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e2) {
            this.i.r().h_().a("Failed to get app instance id. appId", aq.a(zzeb.packageName), e2);
            return null;
        }
    }

    public final u e() {
        b((ex) this.g);
        return this.g;
    }

    public final fe f() {
        b((ex) this.h);
        return this.h;
    }

    public final ao g() {
        return this.i.l();
    }

    public final fg h() {
        return this.i.k();
    }

    /* access modifiers changed from: 0000 */
    public final void i() {
        if (!this.j) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:77|78) */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        r14.i.r().h_().a("Failed to parse upload URL. Not uploading. appId", com.google.android.gms.internal.measurement.aq.a(r4), r5);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:77:0x027c */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void j() {
        /*
            r14 = this;
            r14.v()
            r14.i()
            r0 = 1
            r14.r = r0
            r1 = 0
            com.google.android.gms.internal.measurement.bu r2 = r14.i     // Catch:{ all -> 0x02b6 }
            r2.u()     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.bu r2 = r14.i     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.dq r2 = r2.t()     // Catch:{ all -> 0x02b6 }
            java.lang.Boolean r2 = r2.G()     // Catch:{ all -> 0x02b6 }
            if (r2 != 0) goto L_0x0030
            com.google.android.gms.internal.measurement.bu r0 = r14.i     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.aq r0 = r0.r()     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.as r0 = r0.i()     // Catch:{ all -> 0x02b6 }
            java.lang.String r2 = "Upload data called on the client side before use of service was decided"
        L_0x0027:
            r0.a(r2)     // Catch:{ all -> 0x02b6 }
        L_0x002a:
            r14.r = r1
            r14.z()
            return
        L_0x0030:
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x02b6 }
            if (r2 == 0) goto L_0x0043
            com.google.android.gms.internal.measurement.bu r0 = r14.i     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.aq r0 = r0.r()     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.as r0 = r0.h_()     // Catch:{ all -> 0x02b6 }
            java.lang.String r2 = "Upload called in the client side when service should be used"
            goto L_0x0027
        L_0x0043:
            long r2 = r14.l     // Catch:{ all -> 0x02b6 }
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x004f
        L_0x004b:
            r14.y()     // Catch:{ all -> 0x02b6 }
            goto L_0x002a
        L_0x004f:
            r14.v()     // Catch:{ all -> 0x02b6 }
            java.util.List<java.lang.Long> r2 = r14.u     // Catch:{ all -> 0x02b6 }
            if (r2 == 0) goto L_0x0058
            r2 = r0
            goto L_0x0059
        L_0x0058:
            r2 = r1
        L_0x0059:
            if (r2 == 0) goto L_0x0068
            com.google.android.gms.internal.measurement.bu r0 = r14.i     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.aq r0 = r0.r()     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.as r0 = r0.w()     // Catch:{ all -> 0x02b6 }
            java.lang.String r2 = "Uploading requested multiple times"
            goto L_0x0027
        L_0x0068:
            com.google.android.gms.internal.measurement.au r2 = r14.c()     // Catch:{ all -> 0x02b6 }
            boolean r2 = r2.f()     // Catch:{ all -> 0x02b6 }
            if (r2 != 0) goto L_0x0082
            com.google.android.gms.internal.measurement.bu r0 = r14.i     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.aq r0 = r0.r()     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.as r0 = r0.w()     // Catch:{ all -> 0x02b6 }
            java.lang.String r2 = "Network not connected, ignoring upload request"
            r0.a(r2)     // Catch:{ all -> 0x02b6 }
            goto L_0x004b
        L_0x0082:
            com.google.android.gms.internal.measurement.bu r2 = r14.i     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.common.util.Clock r2 = r2.m()     // Catch:{ all -> 0x02b6 }
            long r2 = r2.currentTimeMillis()     // Catch:{ all -> 0x02b6 }
            long r6 = com.google.android.gms.internal.measurement.w.k()     // Catch:{ all -> 0x02b6 }
            long r8 = r2 - r6
            r6 = 0
            r14.a(r6, r8)     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.bu r7 = r14.i     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.bb r7 = r7.c()     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.be r7 = r7.c     // Catch:{ all -> 0x02b6 }
            long r7 = r7.a()     // Catch:{ all -> 0x02b6 }
            int r9 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x00bf
            com.google.android.gms.internal.measurement.bu r4 = r14.i     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.aq r4 = r4.r()     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.as r4 = r4.v()     // Catch:{ all -> 0x02b6 }
            java.lang.String r5 = "Uploading events. Elapsed time since last upload attempt (ms)"
            long r9 = r2 - r7
            long r7 = java.lang.Math.abs(r9)     // Catch:{ all -> 0x02b6 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x02b6 }
            r4.a(r5, r7)     // Catch:{ all -> 0x02b6 }
        L_0x00bf:
            com.google.android.gms.internal.measurement.z r4 = r14.d()     // Catch:{ all -> 0x02b6 }
            java.lang.String r4 = r4.j()     // Catch:{ all -> 0x02b6 }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x02b6 }
            r7 = -1
            if (r5 != 0) goto L_0x0291
            long r9 = r14.w     // Catch:{ all -> 0x02b6 }
            int r5 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r5 != 0) goto L_0x00df
            com.google.android.gms.internal.measurement.z r5 = r14.d()     // Catch:{ all -> 0x02b6 }
            long r7 = r5.A()     // Catch:{ all -> 0x02b6 }
            r14.w = r7     // Catch:{ all -> 0x02b6 }
        L_0x00df:
            com.google.android.gms.internal.measurement.bu r5 = r14.i     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.w r5 = r5.b()     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.ak$a<java.lang.Integer> r7 = com.google.android.gms.internal.measurement.ak.o     // Catch:{ all -> 0x02b6 }
            int r5 = r5.b(r4, r7)     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.bu r7 = r14.i     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.w r7 = r7.b()     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.ak$a<java.lang.Integer> r8 = com.google.android.gms.internal.measurement.ak.p     // Catch:{ all -> 0x02b6 }
            int r7 = r7.b(r4, r8)     // Catch:{ all -> 0x02b6 }
            int r7 = java.lang.Math.max(r1, r7)     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.z r8 = r14.d()     // Catch:{ all -> 0x02b6 }
            java.util.List r5 = r8.a(r4, r5, r7)     // Catch:{ all -> 0x02b6 }
            boolean r7 = r5.isEmpty()     // Catch:{ all -> 0x02b6 }
            if (r7 != 0) goto L_0x002a
            java.util.Iterator r7 = r5.iterator()     // Catch:{ all -> 0x02b6 }
        L_0x010d:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x02b6 }
            if (r8 == 0) goto L_0x0128
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x02b6 }
            android.util.Pair r8 = (android.util.Pair) r8     // Catch:{ all -> 0x02b6 }
            java.lang.Object r8 = r8.first     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.fx r8 = (com.google.android.gms.internal.measurement.fx) r8     // Catch:{ all -> 0x02b6 }
            java.lang.String r9 = r8.u     // Catch:{ all -> 0x02b6 }
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x02b6 }
            if (r9 != 0) goto L_0x010d
            java.lang.String r7 = r8.u     // Catch:{ all -> 0x02b6 }
            goto L_0x0129
        L_0x0128:
            r7 = r6
        L_0x0129:
            if (r7 == 0) goto L_0x0154
            r8 = r1
        L_0x012c:
            int r9 = r5.size()     // Catch:{ all -> 0x02b6 }
            if (r8 >= r9) goto L_0x0154
            java.lang.Object r9 = r5.get(r8)     // Catch:{ all -> 0x02b6 }
            android.util.Pair r9 = (android.util.Pair) r9     // Catch:{ all -> 0x02b6 }
            java.lang.Object r9 = r9.first     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.fx r9 = (com.google.android.gms.internal.measurement.fx) r9     // Catch:{ all -> 0x02b6 }
            java.lang.String r10 = r9.u     // Catch:{ all -> 0x02b6 }
            boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x02b6 }
            if (r10 != 0) goto L_0x0151
            java.lang.String r9 = r9.u     // Catch:{ all -> 0x02b6 }
            boolean r9 = r9.equals(r7)     // Catch:{ all -> 0x02b6 }
            if (r9 != 0) goto L_0x0151
            java.util.List r5 = r5.subList(r1, r8)     // Catch:{ all -> 0x02b6 }
            goto L_0x0154
        L_0x0151:
            int r8 = r8 + 1
            goto L_0x012c
        L_0x0154:
            com.google.android.gms.internal.measurement.fw r7 = new com.google.android.gms.internal.measurement.fw     // Catch:{ all -> 0x02b6 }
            r7.<init>()     // Catch:{ all -> 0x02b6 }
            int r8 = r5.size()     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.fx[] r8 = new com.google.android.gms.internal.measurement.fx[r8]     // Catch:{ all -> 0x02b6 }
            r7.c = r8     // Catch:{ all -> 0x02b6 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x02b6 }
            int r9 = r5.size()     // Catch:{ all -> 0x02b6 }
            r8.<init>(r9)     // Catch:{ all -> 0x02b6 }
            boolean r9 = com.google.android.gms.internal.measurement.w.w()     // Catch:{ all -> 0x02b6 }
            if (r9 == 0) goto L_0x017e
            com.google.android.gms.internal.measurement.bu r9 = r14.i     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.w r9 = r9.b()     // Catch:{ all -> 0x02b6 }
            boolean r9 = r9.c(r4)     // Catch:{ all -> 0x02b6 }
            if (r9 == 0) goto L_0x017e
            r9 = r0
            goto L_0x017f
        L_0x017e:
            r9 = r1
        L_0x017f:
            r10 = r1
        L_0x0180:
            com.google.android.gms.internal.measurement.fx[] r11 = r7.c     // Catch:{ all -> 0x02b6 }
            int r11 = r11.length     // Catch:{ all -> 0x02b6 }
            if (r10 >= r11) goto L_0x01d8
            com.google.android.gms.internal.measurement.fx[] r11 = r7.c     // Catch:{ all -> 0x02b6 }
            java.lang.Object r12 = r5.get(r10)     // Catch:{ all -> 0x02b6 }
            android.util.Pair r12 = (android.util.Pair) r12     // Catch:{ all -> 0x02b6 }
            java.lang.Object r12 = r12.first     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.fx r12 = (com.google.android.gms.internal.measurement.fx) r12     // Catch:{ all -> 0x02b6 }
            r11[r10] = r12     // Catch:{ all -> 0x02b6 }
            java.lang.Object r11 = r5.get(r10)     // Catch:{ all -> 0x02b6 }
            android.util.Pair r11 = (android.util.Pair) r11     // Catch:{ all -> 0x02b6 }
            java.lang.Object r11 = r11.second     // Catch:{ all -> 0x02b6 }
            java.lang.Long r11 = (java.lang.Long) r11     // Catch:{ all -> 0x02b6 }
            r8.add(r11)     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.fx[] r11 = r7.c     // Catch:{ all -> 0x02b6 }
            r11 = r11[r10]     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.bu r12 = r14.i     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.w r12 = r12.b()     // Catch:{ all -> 0x02b6 }
            long r12 = r12.f()     // Catch:{ all -> 0x02b6 }
            java.lang.Long r12 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x02b6 }
            r11.t = r12     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.fx[] r11 = r7.c     // Catch:{ all -> 0x02b6 }
            r11 = r11[r10]     // Catch:{ all -> 0x02b6 }
            java.lang.Long r12 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x02b6 }
            r11.f = r12     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.fx[] r11 = r7.c     // Catch:{ all -> 0x02b6 }
            r11 = r11[r10]     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.bu r12 = r14.i     // Catch:{ all -> 0x02b6 }
            r12.u()     // Catch:{ all -> 0x02b6 }
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x02b6 }
            r11.B = r12     // Catch:{ all -> 0x02b6 }
            if (r9 != 0) goto L_0x01d5
            com.google.android.gms.internal.measurement.fx[] r11 = r7.c     // Catch:{ all -> 0x02b6 }
            r11 = r11[r10]     // Catch:{ all -> 0x02b6 }
            r11.I = r6     // Catch:{ all -> 0x02b6 }
        L_0x01d5:
            int r10 = r10 + 1
            goto L_0x0180
        L_0x01d8:
            com.google.android.gms.internal.measurement.bu r5 = r14.i     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.aq r5 = r5.r()     // Catch:{ all -> 0x02b6 }
            r9 = 2
            boolean r5 = r5.a(r9)     // Catch:{ all -> 0x02b6 }
            if (r5 == 0) goto L_0x01ed
            com.google.android.gms.internal.measurement.fe r5 = r14.f()     // Catch:{ all -> 0x02b6 }
            java.lang.String r6 = r5.b(r7)     // Catch:{ all -> 0x02b6 }
        L_0x01ed:
            com.google.android.gms.internal.measurement.fe r5 = r14.f()     // Catch:{ all -> 0x02b6 }
            byte[] r11 = r5.a(r7)     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.ak$a<java.lang.String> r5 = com.google.android.gms.internal.measurement.ak.y     // Catch:{ all -> 0x02b6 }
            java.lang.Object r5 = r5.b()     // Catch:{ all -> 0x02b6 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x02b6 }
            java.net.URL r10 = new java.net.URL     // Catch:{ MalformedURLException -> 0x027c }
            r10.<init>(r5)     // Catch:{ MalformedURLException -> 0x027c }
            boolean r9 = r8.isEmpty()     // Catch:{ MalformedURLException -> 0x027c }
            r9 = r9 ^ r0
            com.google.android.gms.common.internal.Preconditions.checkArgument(r9)     // Catch:{ MalformedURLException -> 0x027c }
            java.util.List<java.lang.Long> r9 = r14.u     // Catch:{ MalformedURLException -> 0x027c }
            if (r9 == 0) goto L_0x021e
            com.google.android.gms.internal.measurement.bu r8 = r14.i     // Catch:{ MalformedURLException -> 0x027c }
            com.google.android.gms.internal.measurement.aq r8 = r8.r()     // Catch:{ MalformedURLException -> 0x027c }
            com.google.android.gms.internal.measurement.as r8 = r8.h_()     // Catch:{ MalformedURLException -> 0x027c }
            java.lang.String r9 = "Set uploading progress before finishing the previous upload"
            r8.a(r9)     // Catch:{ MalformedURLException -> 0x027c }
            goto L_0x0225
        L_0x021e:
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ MalformedURLException -> 0x027c }
            r9.<init>(r8)     // Catch:{ MalformedURLException -> 0x027c }
            r14.u = r9     // Catch:{ MalformedURLException -> 0x027c }
        L_0x0225:
            com.google.android.gms.internal.measurement.bu r8 = r14.i     // Catch:{ MalformedURLException -> 0x027c }
            com.google.android.gms.internal.measurement.bb r8 = r8.c()     // Catch:{ MalformedURLException -> 0x027c }
            com.google.android.gms.internal.measurement.be r8 = r8.d     // Catch:{ MalformedURLException -> 0x027c }
            r8.a(r2)     // Catch:{ MalformedURLException -> 0x027c }
            java.lang.String r2 = "?"
            com.google.android.gms.internal.measurement.fx[] r3 = r7.c     // Catch:{ MalformedURLException -> 0x027c }
            int r3 = r3.length     // Catch:{ MalformedURLException -> 0x027c }
            if (r3 <= 0) goto L_0x023d
            com.google.android.gms.internal.measurement.fx[] r2 = r7.c     // Catch:{ MalformedURLException -> 0x027c }
            r2 = r2[r1]     // Catch:{ MalformedURLException -> 0x027c }
            java.lang.String r2 = r2.q     // Catch:{ MalformedURLException -> 0x027c }
        L_0x023d:
            com.google.android.gms.internal.measurement.bu r3 = r14.i     // Catch:{ MalformedURLException -> 0x027c }
            com.google.android.gms.internal.measurement.aq r3 = r3.r()     // Catch:{ MalformedURLException -> 0x027c }
            com.google.android.gms.internal.measurement.as r3 = r3.w()     // Catch:{ MalformedURLException -> 0x027c }
            java.lang.String r7 = "Uploading data. app, uncompressed size, data"
            int r8 = r11.length     // Catch:{ MalformedURLException -> 0x027c }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ MalformedURLException -> 0x027c }
            r3.a(r7, r2, r8, r6)     // Catch:{ MalformedURLException -> 0x027c }
            r14.q = r0     // Catch:{ MalformedURLException -> 0x027c }
            com.google.android.gms.internal.measurement.au r8 = r14.c()     // Catch:{ MalformedURLException -> 0x027c }
            com.google.android.gms.internal.measurement.fa r13 = new com.google.android.gms.internal.measurement.fa     // Catch:{ MalformedURLException -> 0x027c }
            r13.<init>(r14, r4)     // Catch:{ MalformedURLException -> 0x027c }
            r8.d()     // Catch:{ MalformedURLException -> 0x027c }
            r8.I()     // Catch:{ MalformedURLException -> 0x027c }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r10)     // Catch:{ MalformedURLException -> 0x027c }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r11)     // Catch:{ MalformedURLException -> 0x027c }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)     // Catch:{ MalformedURLException -> 0x027c }
            com.google.android.gms.internal.measurement.bq r0 = r8.q()     // Catch:{ MalformedURLException -> 0x027c }
            com.google.android.gms.internal.measurement.ay r2 = new com.google.android.gms.internal.measurement.ay     // Catch:{ MalformedURLException -> 0x027c }
            r12 = 0
            r7 = r2
            r9 = r4
            r7.<init>(r8, r9, r10, r11, r12, r13)     // Catch:{ MalformedURLException -> 0x027c }
            r0.b(r2)     // Catch:{ MalformedURLException -> 0x027c }
            goto L_0x002a
        L_0x027c:
            com.google.android.gms.internal.measurement.bu r0 = r14.i     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.aq r0 = r0.r()     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.as r0 = r0.h_()     // Catch:{ all -> 0x02b6 }
            java.lang.String r2 = "Failed to parse upload URL. Not uploading. appId"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.aq.a(r4)     // Catch:{ all -> 0x02b6 }
            r0.a(r2, r3, r5)     // Catch:{ all -> 0x02b6 }
            goto L_0x002a
        L_0x0291:
            r14.w = r7     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.z r0 = r14.d()     // Catch:{ all -> 0x02b6 }
            long r4 = com.google.android.gms.internal.measurement.w.k()     // Catch:{ all -> 0x02b6 }
            long r6 = r2 - r4
            java.lang.String r0 = r0.a(r6)     // Catch:{ all -> 0x02b6 }
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x02b6 }
            if (r2 != 0) goto L_0x002a
            com.google.android.gms.internal.measurement.z r2 = r14.d()     // Catch:{ all -> 0x02b6 }
            com.google.android.gms.internal.measurement.t r0 = r2.b(r0)     // Catch:{ all -> 0x02b6 }
            if (r0 == 0) goto L_0x002a
            r14.a(r0)     // Catch:{ all -> 0x02b6 }
            goto L_0x002a
        L_0x02b6:
            r0 = move-exception
            r14.r = r1
            r14.z()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.ey.j():void");
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void k() {
        as h_;
        String str;
        v();
        i();
        if (!this.k) {
            this.i.r().k().a("This instance being marked as an uploader");
            v();
            i();
            if (B() && A()) {
                int a2 = a(this.t);
                int E = this.i.w().E();
                v();
                if (a2 > E) {
                    h_ = this.i.r().h_();
                    str = "Panic: can't downgrade version. Previous, current version";
                } else if (a2 < E) {
                    if (a(E, this.t)) {
                        h_ = this.i.r().w();
                        str = "Storage version upgraded. Previous, current version";
                    } else {
                        h_ = this.i.r().h_();
                        str = "Storage version upgrade failed. Previous, current version";
                    }
                }
                h_.a(str, Integer.valueOf(a2), Integer.valueOf(E));
            }
            this.k = true;
            y();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void l() {
        this.o++;
    }

    public final Clock m() {
        return this.i.m();
    }

    public final Context n() {
        return this.i.n();
    }

    /* access modifiers changed from: 0000 */
    public final bu o() {
        return this.i;
    }

    public final bq q() {
        return this.i.q();
    }

    public final aq r() {
        return this.i.r();
    }

    public final v u() {
        return this.i.u();
    }
}
