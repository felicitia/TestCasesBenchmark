package defpackage;

import android.content.Context;
import com.apiguard.AGCallbackInterface;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: l reason: default package */
/* compiled from: GA */
public final class l {
    private static final long m = TimeUnit.SECONDS.convert(1, TimeUnit.HOURS);
    private static final long n = TimeUnit.SECONDS.convert(30, TimeUnit.DAYS);
    private static final long o = TimeUnit.SECONDS.convert(5, TimeUnit.MINUTES);
    private static final long p = TimeUnit.SECONDS.convert(14, TimeUnit.DAYS);
    private static final long q = TimeUnit.SECONDS.convert(90, TimeUnit.DAYS);
    public String a = "";
    public Set<String> b = new HashSet();
    public Set<String> c = new HashSet();
    public Map<String, Boolean> d = new HashMap();
    public Map<String, String> e = new HashMap();
    public int f = 429;
    public long g = -1;
    public int h = -1;
    public long i = TimeUnit.MILLISECONDS.convert(m, TimeUnit.SECONDS);
    public long j = TimeUnit.MILLISECONDS.convert(o, TimeUnit.SECONDS);
    public volatile AtomicBoolean k = new AtomicBoolean();
    public AtomicBoolean l = new AtomicBoolean();
    private long r = TimeUnit.MILLISECONDS.convert(p, TimeUnit.SECONDS);
    private AtomicReference<m> s = new AtomicReference<>();
    private WeakReference<Context> t = null;
    private WeakReference<AGCallbackInterface> u = null;

    public l() {
        this.k.set(false);
        this.s.set(new m(null, null, 0));
        this.l.set(false);
    }

    public final Context a() {
        if (this.t != null) {
            return (Context) this.t.get();
        }
        return null;
    }

    public final AGCallbackInterface b() {
        if (this.u != null) {
            return (AGCallbackInterface) this.u.get();
        }
        return null;
    }

    private void a(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            for (String str : strArr) {
                if (str != null) {
                    if (str.startsWith(".")) {
                        hashSet2.add(str.toLowerCase(Locale.ROOT));
                    } else {
                        String c2 = al.c(str);
                        if (c2.length() > 1) {
                            hashSet.add(c2);
                        }
                    }
                }
            }
            synchronized (this) {
                this.b.clear();
                this.c.clear();
                this.d.clear();
                this.b.addAll(hashSet);
                this.c.addAll(hashSet2);
            }
        }
    }

    public final m c() {
        return (m) this.s.get();
    }

    public final boolean a(m mVar) {
        if (!al.a(mVar)) {
            return false;
        }
        this.s.set(mVar);
        return true;
    }

    public final void a(boolean z) {
        this.k.set(z);
    }

    public final void b(boolean z) {
        this.l.set(z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x007f A[SYNTHETIC, Splitter:B:39:0x007f] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0097 A[SYNTHETIC, Splitter:B:44:0x0097] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00a7 A[SYNTHETIC, Splitter:B:51:0x00a7] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00bf A[SYNTHETIC, Splitter:B:56:0x00bf] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void d() {
        /*
            r6 = this;
            android.content.Context r0 = r6.a()
            if (r0 == 0) goto L_0x00d6
            long r0 = r6.r
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x00d6
            r0 = 0
            java.io.File r1 = defpackage.al.d()     // Catch:{ Exception -> 0x006a, all -> 0x0067 }
            if (r1 == 0) goto L_0x005f
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x006a, all -> 0x0067 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x006a, all -> 0x0067 }
            java.io.ObjectOutputStream r1 = new java.io.ObjectOutputStream     // Catch:{ Exception -> 0x005d }
            r1.<init>(r2)     // Catch:{ Exception -> 0x005d }
            m r0 = r6.c()     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
            r1.writeObject(r0)     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
            r1.close()     // Catch:{ IOException -> 0x002a }
            goto L_0x003c
        L_0x002a:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "M31: "
            r1.<init>(r3)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            defpackage.al.b(r0)
        L_0x003c:
            r2.close()     // Catch:{ IOException -> 0x0040 }
            return
        L_0x0040:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "M31: "
            r1.<init>(r2)
        L_0x0048:
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            defpackage.al.b(r0)
            return
        L_0x0053:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x00a5
        L_0x0058:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x006c
        L_0x005d:
            r1 = move-exception
            goto L_0x006c
        L_0x005f:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ Exception -> 0x006a, all -> 0x0067 }
            java.lang.String r2 = "M33"
            r1.<init>(r2)     // Catch:{ Exception -> 0x006a, all -> 0x0067 }
            throw r1     // Catch:{ Exception -> 0x006a, all -> 0x0067 }
        L_0x0067:
            r1 = move-exception
            r2 = r0
            goto L_0x00a5
        L_0x006a:
            r1 = move-exception
            r2 = r0
        L_0x006c:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a4 }
            java.lang.String r4 = "M31: "
            r3.<init>(r4)     // Catch:{ all -> 0x00a4 }
            r3.append(r1)     // Catch:{ all -> 0x00a4 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x00a4 }
            defpackage.al.b(r1)     // Catch:{ all -> 0x00a4 }
            if (r0 == 0) goto L_0x0095
            r0.close()     // Catch:{ IOException -> 0x0083 }
            goto L_0x0095
        L_0x0083:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "M31: "
            r1.<init>(r3)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            defpackage.al.b(r0)
        L_0x0095:
            if (r2 == 0) goto L_0x00d6
            r2.close()     // Catch:{ IOException -> 0x009b }
            return
        L_0x009b:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "M31: "
            r1.<init>(r2)
            goto L_0x0048
        L_0x00a4:
            r1 = move-exception
        L_0x00a5:
            if (r0 == 0) goto L_0x00bd
            r0.close()     // Catch:{ IOException -> 0x00ab }
            goto L_0x00bd
        L_0x00ab:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "M31: "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            defpackage.al.b(r0)
        L_0x00bd:
            if (r2 == 0) goto L_0x00d5
            r2.close()     // Catch:{ IOException -> 0x00c3 }
            goto L_0x00d5
        L_0x00c3:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "M31: "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            defpackage.al.b(r0)
        L_0x00d5:
            throw r1
        L_0x00d6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.l.d():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c8 A[SYNTHETIC, Splitter:B:53:0x00c8] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00e0 A[SYNTHETIC, Splitter:B:58:0x00e0] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00f0 A[SYNTHETIC, Splitter:B:64:0x00f0] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0108 A[SYNTHETIC, Splitter:B:69:0x0108] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final defpackage.m e() {
        /*
            r11 = this;
            android.content.Context r0 = r11.a()
            r1 = 0
            if (r0 == 0) goto L_0x0122
            boolean r0 = defpackage.al.c()
            if (r0 == 0) goto L_0x0122
            long r2 = r11.r
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x011f
            java.io.File r0 = defpackage.al.d()     // Catch:{ Exception -> 0x00b2, all -> 0x00ad }
            if (r0 == 0) goto L_0x0078
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00b2, all -> 0x00ad }
            r2.<init>(r0)     // Catch:{ Exception -> 0x00b2, all -> 0x00ad }
            java.io.ObjectInputStream r0 = new java.io.ObjectInputStream     // Catch:{ Exception -> 0x0075, all -> 0x006f }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0075, all -> 0x006f }
            java.lang.Object r3 = r0.readObject()     // Catch:{ Exception -> 0x006d }
            m r3 = (defpackage.m) r3     // Catch:{ Exception -> 0x006d }
            if (r3 == 0) goto L_0x0069
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x006d }
            long r6 = r3.timeOfStateCreation     // Catch:{ Exception -> 0x006d }
            r8 = 0
            long r8 = r4 - r6
            long r4 = r11.r     // Catch:{ Exception -> 0x006d }
            int r6 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r6 > 0) goto L_0x0069
            r0.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x0052
        L_0x0040:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r4 = "M31: "
            r1.<init>(r4)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            defpackage.al.b(r0)
        L_0x0052:
            r2.close()     // Catch:{ IOException -> 0x0056 }
            goto L_0x0068
        L_0x0056:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "M31: "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            defpackage.al.b(r0)
        L_0x0068:
            return r3
        L_0x0069:
            f()     // Catch:{ Exception -> 0x006d }
            goto L_0x007a
        L_0x006d:
            r3 = move-exception
            goto L_0x00b5
        L_0x006f:
            r0 = move-exception
            r10 = r1
            r1 = r0
            r0 = r10
            goto L_0x00ee
        L_0x0075:
            r3 = move-exception
            r0 = r1
            goto L_0x00b5
        L_0x0078:
            r0 = r1
            r2 = r0
        L_0x007a:
            if (r0 == 0) goto L_0x0092
            r0.close()     // Catch:{ IOException -> 0x0080 }
            goto L_0x0092
        L_0x0080:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "M31: "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            defpackage.al.b(r0)
        L_0x0092:
            if (r2 == 0) goto L_0x0122
            r2.close()     // Catch:{ IOException -> 0x0099 }
            goto L_0x0122
        L_0x0099:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "M31: "
            r2.<init>(r3)
        L_0x00a1:
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            defpackage.al.b(r0)
            goto L_0x0122
        L_0x00ad:
            r0 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
            goto L_0x00ee
        L_0x00b2:
            r3 = move-exception
            r0 = r1
            r2 = r0
        L_0x00b5:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ed }
            java.lang.String r5 = "M31: "
            r4.<init>(r5)     // Catch:{ all -> 0x00ed }
            r4.append(r3)     // Catch:{ all -> 0x00ed }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x00ed }
            defpackage.al.b(r3)     // Catch:{ all -> 0x00ed }
            if (r0 == 0) goto L_0x00de
            r0.close()     // Catch:{ IOException -> 0x00cc }
            goto L_0x00de
        L_0x00cc:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "M31: "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            defpackage.al.b(r0)
        L_0x00de:
            if (r2 == 0) goto L_0x0122
            r2.close()     // Catch:{ IOException -> 0x00e4 }
            goto L_0x0122
        L_0x00e4:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "M31: "
            r2.<init>(r3)
            goto L_0x00a1
        L_0x00ed:
            r1 = move-exception
        L_0x00ee:
            if (r0 == 0) goto L_0x0106
            r0.close()     // Catch:{ IOException -> 0x00f4 }
            goto L_0x0106
        L_0x00f4:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "M31: "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            defpackage.al.b(r0)
        L_0x0106:
            if (r2 == 0) goto L_0x011e
            r2.close()     // Catch:{ IOException -> 0x010c }
            goto L_0x011e
        L_0x010c:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "M31: "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            defpackage.al.b(r0)
        L_0x011e:
            throw r1
        L_0x011f:
            f()
        L_0x0122:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.l.e():m");
    }

    private static void f() {
        File d2 = al.d();
        if (d2 != null && !d2.delete()) {
            al.b("M31: M32");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x0131 A[Catch:{ Exception -> 0x028b }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0179 A[Catch:{ Exception -> 0x028b }] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01c5 A[Catch:{ Exception -> 0x028b }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01d8 A[Catch:{ Exception -> 0x028b }] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01da A[Catch:{ Exception -> 0x028b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(android.content.Context r23, com.apiguard.AGCallbackInterface r24, java.util.Map<java.lang.String, java.lang.Object> r25) {
        /*
            r22 = this;
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = r25
            if (r3 == 0) goto L_0x028e
            java.lang.ref.WeakReference<com.apiguard.AGCallbackInterface> r6 = r1.u     // Catch:{ Exception -> 0x028b }
            if (r6 == 0) goto L_0x0013
            java.lang.ref.WeakReference<com.apiguard.AGCallbackInterface> r6 = r1.u     // Catch:{ Exception -> 0x028b }
            r6.clear()     // Catch:{ Exception -> 0x028b }
        L_0x0013:
            java.lang.ref.WeakReference r6 = new java.lang.ref.WeakReference     // Catch:{ Exception -> 0x028b }
            r6.<init>(r3)     // Catch:{ Exception -> 0x028b }
            r1.u = r6     // Catch:{ Exception -> 0x028b }
            defpackage.al.a(r22)     // Catch:{ Exception -> 0x028b }
            if (r2 != 0) goto L_0x0027
            java.util.InvalidPropertiesFormatException r2 = new java.util.InvalidPropertiesFormatException     // Catch:{ Exception -> 0x028b }
            java.lang.String r3 = "M13: "
            r2.<init>(r3)     // Catch:{ Exception -> 0x028b }
            throw r2     // Catch:{ Exception -> 0x028b }
        L_0x0027:
            java.lang.String r3 = "INTEGRITYCHECKURL"
            boolean r3 = r4.containsKey(r3)     // Catch:{ Exception -> 0x028b }
            if (r3 == 0) goto L_0x0038
            java.lang.String r3 = "INTEGRITYCHECKURL"
            java.lang.Object r3 = r4.get(r3)     // Catch:{ Exception -> 0x028b }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x028b }
            goto L_0x003a
        L_0x0038:
            java.lang.String r3 = ""
        L_0x003a:
            int r6 = r3.length()     // Catch:{ Exception -> 0x028b }
            r7 = 8
            if (r6 < r7) goto L_0x0283
            boolean r6 = android.webkit.URLUtil.isValidUrl(r3)     // Catch:{ Exception -> 0x028b }
            if (r6 != 0) goto L_0x004a
            goto L_0x0283
        L_0x004a:
            java.lang.String r6 = "GUARDEDDOMAINS"
            java.lang.Object r6 = r4.get(r6)     // Catch:{ Exception -> 0x028b }
            java.lang.String[] r6 = (java.lang.String[]) r6     // Catch:{ Exception -> 0x028b }
            if (r6 == 0) goto L_0x027b
            int r8 = r6.length     // Catch:{ Exception -> 0x028b }
            if (r8 != 0) goto L_0x0059
            goto L_0x027b
        L_0x0059:
            long r8 = m     // Catch:{ Exception -> 0x028b }
            long r10 = o     // Catch:{ Exception -> 0x028b }
            long r12 = p     // Catch:{ Exception -> 0x028b }
            r16 = -1
            java.lang.String r14 = "UPDATEINTERVAL"
            boolean r14 = r4.containsKey(r14)     // Catch:{ Exception -> 0x028b }
            if (r14 == 0) goto L_0x00a8
            java.lang.String r14 = "UPDATEINTERVAL"
            java.lang.Object r14 = r4.get(r14)     // Catch:{ Exception -> 0x028b }
            java.lang.Class r15 = r14.getClass()     // Catch:{ Exception -> 0x028b }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            boolean r7 = r15.equals(r7)     // Catch:{ Exception -> 0x028b }
            if (r7 == 0) goto L_0x0082
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ Exception -> 0x028b }
            long r8 = java.lang.Long.parseLong(r14)     // Catch:{ Exception -> 0x028b }
            goto L_0x00a8
        L_0x0082:
            java.lang.Class r7 = r14.getClass()     // Catch:{ Exception -> 0x028b }
            java.lang.Class<java.lang.Integer> r15 = java.lang.Integer.class
            boolean r7 = r7.equals(r15)     // Catch:{ Exception -> 0x028b }
            if (r7 == 0) goto L_0x0096
            java.lang.Integer r14 = (java.lang.Integer) r14     // Catch:{ Exception -> 0x028b }
            int r7 = r14.intValue()     // Catch:{ Exception -> 0x028b }
            long r8 = (long) r7     // Catch:{ Exception -> 0x028b }
            goto L_0x00a8
        L_0x0096:
            java.lang.Class r7 = r14.getClass()     // Catch:{ Exception -> 0x028b }
            java.lang.Class<java.lang.Long> r15 = java.lang.Long.class
            boolean r7 = r7.equals(r15)     // Catch:{ Exception -> 0x028b }
            if (r7 == 0) goto L_0x00a8
            java.lang.Long r14 = (java.lang.Long) r14     // Catch:{ Exception -> 0x028b }
            long r8 = r14.longValue()     // Catch:{ Exception -> 0x028b }
        L_0x00a8:
            java.lang.String r7 = "RETRYINTERVAL"
            boolean r7 = r4.containsKey(r7)     // Catch:{ Exception -> 0x028b }
            if (r7 == 0) goto L_0x00ef
            java.lang.String r7 = "RETRYINTERVAL"
            java.lang.Object r7 = r4.get(r7)     // Catch:{ Exception -> 0x028b }
            java.lang.Class r14 = r7.getClass()     // Catch:{ Exception -> 0x028b }
            java.lang.Class<java.lang.String> r15 = java.lang.String.class
            boolean r14 = r14.equals(r15)     // Catch:{ Exception -> 0x028b }
            if (r14 == 0) goto L_0x00c9
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Exception -> 0x028b }
            long r10 = java.lang.Long.parseLong(r7)     // Catch:{ Exception -> 0x028b }
            goto L_0x00ef
        L_0x00c9:
            java.lang.Class r14 = r7.getClass()     // Catch:{ Exception -> 0x028b }
            java.lang.Class<java.lang.Integer> r15 = java.lang.Integer.class
            boolean r14 = r14.equals(r15)     // Catch:{ Exception -> 0x028b }
            if (r14 == 0) goto L_0x00dd
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ Exception -> 0x028b }
            int r7 = r7.intValue()     // Catch:{ Exception -> 0x028b }
            long r10 = (long) r7     // Catch:{ Exception -> 0x028b }
            goto L_0x00ef
        L_0x00dd:
            java.lang.Class r14 = r7.getClass()     // Catch:{ Exception -> 0x028b }
            java.lang.Class<java.lang.Long> r15 = java.lang.Long.class
            boolean r14 = r14.equals(r15)     // Catch:{ Exception -> 0x028b }
            if (r14 == 0) goto L_0x00ef
            java.lang.Long r7 = (java.lang.Long) r7     // Catch:{ Exception -> 0x028b }
            long r10 = r7.longValue()     // Catch:{ Exception -> 0x028b }
        L_0x00ef:
            java.lang.String r7 = "CUSTOMREAUTHSTATUSCODE"
            boolean r7 = r4.containsKey(r7)     // Catch:{ Exception -> 0x028b }
            if (r7 == 0) goto L_0x0127
            java.lang.String r7 = "CUSTOMREAUTHSTATUSCODE"
            java.lang.Object r7 = r4.get(r7)     // Catch:{ Exception -> 0x028b }
            java.lang.Class r15 = r7.getClass()     // Catch:{ Exception -> 0x028b }
            java.lang.Class<java.lang.String> r14 = java.lang.String.class
            boolean r14 = r15.equals(r14)     // Catch:{ Exception -> 0x028b }
            if (r14 == 0) goto L_0x0114
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Exception -> 0x028b }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ Exception -> 0x028b }
            int r14 = r7.intValue()     // Catch:{ Exception -> 0x028b }
            goto L_0x0129
        L_0x0114:
            java.lang.Class r14 = r7.getClass()     // Catch:{ Exception -> 0x028b }
            java.lang.Class<java.lang.Integer> r15 = java.lang.Integer.class
            boolean r14 = r14.equals(r15)     // Catch:{ Exception -> 0x028b }
            if (r14 == 0) goto L_0x0127
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ Exception -> 0x028b }
            int r14 = r7.intValue()     // Catch:{ Exception -> 0x028b }
            goto L_0x0129
        L_0x0127:
            r14 = 429(0x1ad, float:6.01E-43)
        L_0x0129:
            java.lang.String r7 = "KEYCACHETTL"
            boolean r7 = r4.containsKey(r7)     // Catch:{ Exception -> 0x028b }
            if (r7 == 0) goto L_0x0170
            java.lang.String r7 = "KEYCACHETTL"
            java.lang.Object r7 = r4.get(r7)     // Catch:{ Exception -> 0x028b }
            java.lang.Class r15 = r7.getClass()     // Catch:{ Exception -> 0x028b }
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            boolean r5 = r15.equals(r5)     // Catch:{ Exception -> 0x028b }
            if (r5 == 0) goto L_0x014a
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Exception -> 0x028b }
            long r12 = java.lang.Long.parseLong(r7)     // Catch:{ Exception -> 0x028b }
            goto L_0x0170
        L_0x014a:
            java.lang.Class r5 = r7.getClass()     // Catch:{ Exception -> 0x028b }
            java.lang.Class<java.lang.Integer> r15 = java.lang.Integer.class
            boolean r5 = r5.equals(r15)     // Catch:{ Exception -> 0x028b }
            if (r5 == 0) goto L_0x015e
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ Exception -> 0x028b }
            int r5 = r7.intValue()     // Catch:{ Exception -> 0x028b }
            long r12 = (long) r5     // Catch:{ Exception -> 0x028b }
            goto L_0x0170
        L_0x015e:
            java.lang.Class r5 = r7.getClass()     // Catch:{ Exception -> 0x028b }
            java.lang.Class<java.lang.Long> r15 = java.lang.Long.class
            boolean r5 = r5.equals(r15)     // Catch:{ Exception -> 0x028b }
            if (r5 == 0) goto L_0x0170
            java.lang.Long r7 = (java.lang.Long) r7     // Catch:{ Exception -> 0x028b }
            long r12 = r7.longValue()     // Catch:{ Exception -> 0x028b }
        L_0x0170:
            java.lang.String r5 = "AID"
            boolean r5 = r4.containsKey(r5)     // Catch:{ Exception -> 0x028b }
            r7 = 1
            if (r5 == 0) goto L_0x01c5
            java.lang.String r5 = "AID"
            java.lang.Object r4 = r4.get(r5)     // Catch:{ Exception -> 0x028b }
            java.lang.Class r5 = r4.getClass()     // Catch:{ Exception -> 0x028b }
            java.lang.Class<java.lang.String> r15 = java.lang.String.class
            boolean r5 = r5.equals(r15)     // Catch:{ Exception -> 0x028b }
            if (r5 == 0) goto L_0x01bd
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x028b }
            byte[] r4 = defpackage.al.a(r4)     // Catch:{ Exception -> 0x028b }
            java.lang.String r5 = new java.lang.String     // Catch:{ Exception -> 0x028b }
            r5.<init>(r4)     // Catch:{ Exception -> 0x028b }
            java.lang.String r4 = ";"
            java.lang.String[] r4 = r5.split(r4)     // Catch:{ Exception -> 0x028b }
            int r5 = r4.length     // Catch:{ Exception -> 0x028b }
            r15 = 2
            if (r5 == r15) goto L_0x01a8
            java.util.InvalidPropertiesFormatException r2 = new java.util.InvalidPropertiesFormatException     // Catch:{ Exception -> 0x028b }
            java.lang.String r3 = "M39"
            r2.<init>(r3)     // Catch:{ Exception -> 0x028b }
            throw r2     // Catch:{ Exception -> 0x028b }
        L_0x01a8:
            r5 = 0
            r15 = r4[r5]     // Catch:{ Exception -> 0x028b }
            long r15 = java.lang.Long.parseLong(r15)     // Catch:{ Exception -> 0x028b }
            r4 = r4[r7]     // Catch:{ Exception -> 0x028b }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x028b }
            r18 = r8
            r20 = r15
            r15 = r4
            r4 = r20
            goto L_0x01ca
        L_0x01bd:
            java.util.InvalidPropertiesFormatException r2 = new java.util.InvalidPropertiesFormatException     // Catch:{ Exception -> 0x028b }
            java.lang.String r3 = "M39"
            r2.<init>(r3)     // Catch:{ Exception -> 0x028b }
            throw r2     // Catch:{ Exception -> 0x028b }
        L_0x01c5:
            r18 = r8
            r4 = -1
            r15 = -1
        L_0x01ca:
            r7 = 0
            int r9 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r9 < 0) goto L_0x0273
            r9 = 8
            if (r15 < r9) goto L_0x0273
            r9 = 32
            if (r15 <= r9) goto L_0x01da
            goto L_0x0273
        L_0x01da:
            java.lang.ref.WeakReference<android.content.Context> r9 = r1.t     // Catch:{ Exception -> 0x028b }
            if (r9 == 0) goto L_0x01e3
            java.lang.ref.WeakReference<android.content.Context> r9 = r1.t     // Catch:{ Exception -> 0x028b }
            r9.clear()     // Catch:{ Exception -> 0x028b }
        L_0x01e3:
            java.lang.ref.WeakReference r9 = new java.lang.ref.WeakReference     // Catch:{ Exception -> 0x028b }
            r9.<init>(r2)     // Catch:{ Exception -> 0x028b }
            r1.t = r9     // Catch:{ Exception -> 0x028b }
            r1.a = r3     // Catch:{ Exception -> 0x028b }
            r1.a(r6)     // Catch:{ Exception -> 0x028b }
            r2 = 1
            int r6 = (r18 > r2 ? 1 : (r18 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x01fa
            long r16 = m     // Catch:{ Exception -> 0x028b }
            r7 = r16
            goto L_0x01fc
        L_0x01fa:
            r7 = r18
        L_0x01fc:
            int r6 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x0202
            long r10 = o     // Catch:{ Exception -> 0x028b }
        L_0x0202:
            long r16 = n     // Catch:{ Exception -> 0x028b }
            int r6 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r6 <= 0) goto L_0x0215
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ Exception -> 0x028b }
            long r7 = n     // Catch:{ Exception -> 0x028b }
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x028b }
            long r6 = r6.convert(r7, r9)     // Catch:{ Exception -> 0x028b }
            r1.i = r6     // Catch:{ Exception -> 0x028b }
            goto L_0x021f
        L_0x0215:
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ Exception -> 0x028b }
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x028b }
            long r6 = r6.convert(r7, r9)     // Catch:{ Exception -> 0x028b }
            r1.i = r6     // Catch:{ Exception -> 0x028b }
        L_0x021f:
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ Exception -> 0x028b }
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x028b }
            long r6 = r6.convert(r10, r7)     // Catch:{ Exception -> 0x028b }
            long r8 = r1.i     // Catch:{ Exception -> 0x028b }
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 <= 0) goto L_0x0232
            long r6 = r1.i     // Catch:{ Exception -> 0x028b }
            r1.j = r6     // Catch:{ Exception -> 0x028b }
            goto L_0x0234
        L_0x0232:
            r1.j = r6     // Catch:{ Exception -> 0x028b }
        L_0x0234:
            int r6 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x023d
            r2 = 0
            r1.r = r2     // Catch:{ Exception -> 0x028b }
            goto L_0x025a
        L_0x023d:
            long r2 = q     // Catch:{ Exception -> 0x028b }
            int r6 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r6 <= 0) goto L_0x0250
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ Exception -> 0x028b }
            long r6 = q     // Catch:{ Exception -> 0x028b }
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x028b }
            long r2 = r2.convert(r6, r3)     // Catch:{ Exception -> 0x028b }
            r1.r = r2     // Catch:{ Exception -> 0x028b }
            goto L_0x025a
        L_0x0250:
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ Exception -> 0x028b }
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x028b }
            long r2 = r2.convert(r12, r3)     // Catch:{ Exception -> 0x028b }
            r1.r = r2     // Catch:{ Exception -> 0x028b }
        L_0x025a:
            r2 = 201(0xc9, float:2.82E-43)
            if (r14 < r2) goto L_0x0266
            r2 = 999(0x3e7, float:1.4E-42)
            if (r14 <= r2) goto L_0x0263
            goto L_0x0266
        L_0x0263:
            r1.f = r14     // Catch:{ Exception -> 0x028b }
            goto L_0x026a
        L_0x0266:
            r2 = 429(0x1ad, float:6.01E-43)
            r1.f = r2     // Catch:{ Exception -> 0x028b }
        L_0x026a:
            r1.g = r4     // Catch:{ Exception -> 0x028b }
            r1.h = r15     // Catch:{ Exception -> 0x028b }
            defpackage.al.a(r22)     // Catch:{ Exception -> 0x028b }
            r2 = 1
            return r2
        L_0x0273:
            java.util.InvalidPropertiesFormatException r2 = new java.util.InvalidPropertiesFormatException     // Catch:{ Exception -> 0x028b }
            java.lang.String r3 = "M39"
            r2.<init>(r3)     // Catch:{ Exception -> 0x028b }
            throw r2     // Catch:{ Exception -> 0x028b }
        L_0x027b:
            java.util.InvalidPropertiesFormatException r2 = new java.util.InvalidPropertiesFormatException     // Catch:{ Exception -> 0x028b }
            java.lang.String r3 = "M40"
            r2.<init>(r3)     // Catch:{ Exception -> 0x028b }
            throw r2     // Catch:{ Exception -> 0x028b }
        L_0x0283:
            java.util.InvalidPropertiesFormatException r2 = new java.util.InvalidPropertiesFormatException     // Catch:{ Exception -> 0x028b }
            java.lang.String r3 = "M38"
            r2.<init>(r3)     // Catch:{ Exception -> 0x028b }
            throw r2     // Catch:{ Exception -> 0x028b }
        L_0x028b:
            r0 = move-exception
            r2 = r0
            goto L_0x0296
        L_0x028e:
            java.util.InvalidPropertiesFormatException r2 = new java.util.InvalidPropertiesFormatException     // Catch:{ Exception -> 0x028b }
            java.lang.String r3 = "M4: "
            r2.<init>(r3)     // Catch:{ Exception -> 0x028b }
            throw r2     // Catch:{ Exception -> 0x028b }
        L_0x0296:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "M3: "
            r3.<init>(r4)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            defpackage.al.b(r2)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.l.a(android.content.Context, com.apiguard.AGCallbackInterface, java.util.Map):boolean");
    }
}
