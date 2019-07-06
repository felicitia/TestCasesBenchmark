package com.threatmetrix.TrustDefender.internal;

import android.os.Handler;
import android.os.Looper;
import com.threatmetrix.TrustDefender.internal.N.Q;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class R extends K {
    /* access modifiers changed from: private */

    /* renamed from: byte reason: not valid java name */
    public static final String f507byte = TL.m331if(R.class);

    /* renamed from: break reason: not valid java name */
    private final long f508break;

    /* renamed from: goto reason: not valid java name */
    private boolean f509goto = false;

    /* renamed from: long reason: not valid java name */
    private CountDownLatch f510long;
    /* access modifiers changed from: private */

    /* renamed from: this reason: not valid java name */
    public final EF f511this;
    /* access modifiers changed from: private */

    /* renamed from: try reason: not valid java name */
    public final com.threatmetrix.TrustDefender.internal.P.O f512try;

    /* renamed from: void reason: not valid java name */
    private final WY f513void;

    static class O implements Runnable {

        /* renamed from: if reason: not valid java name */
        final CountDownLatch f516if;

        /* renamed from: new reason: not valid java name */
        WY f517new = null;

        public O(CountDownLatch countDownLatch) {
            this.f516if = countDownLatch;
        }

        public void run() {
            throw new NoSuchMethodError();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004a, code lost:
        if (r0.isEmpty() == false) goto L_0x0051;
     */
    /* renamed from: int reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String m315int() {
        /*
            r7 = this;
            java.lang.String r0 = r7.f257do
            if (r0 != 0) goto L_0x0053
            boolean r0 = r7.f509goto
            if (r0 == 0) goto L_0x000f
            com.threatmetrix.TrustDefender.internal.P$O r0 = r7.f512try
            java.lang.String r0 = com.threatmetrix.TrustDefender.internal.WY.m370for(r0)
            goto L_0x0051
        L_0x000f:
            com.threatmetrix.TrustDefender.internal.WY r0 = r7.f513void
            com.threatmetrix.TrustDefender.internal.P$O r1 = r7.f512try
            boolean r2 = com.threatmetrix.TrustDefender.internal.N.Q.m167for()
            if (r2 == 0) goto L_0x004d
            r2 = 0
            java.lang.reflect.Method r3 = com.threatmetrix.TrustDefender.internal.WY.f594if
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r5 = 0
            android.content.Context r6 = r1.f487for
            r4[r5] = r6
            java.lang.Object r2 = com.threatmetrix.TrustDefender.internal.WY.m39do(r2, r3, r4)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto L_0x0034
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L_0x0034
            r0 = r2
            goto L_0x0051
        L_0x0034:
            boolean r3 = r0.f599case
            if (r3 == 0) goto L_0x0043
            android.webkit.WebSettings r3 = r0.f600char
            if (r3 == 0) goto L_0x0043
            android.webkit.WebSettings r0 = r0.f600char
            java.lang.String r0 = r0.getUserAgentString()
            goto L_0x0044
        L_0x0043:
            r0 = r2
        L_0x0044:
            if (r0 == 0) goto L_0x004d
            boolean r2 = r0.isEmpty()
            if (r2 != 0) goto L_0x004d
            goto L_0x0051
        L_0x004d:
            java.lang.String r0 = com.threatmetrix.TrustDefender.internal.WY.m370for(r1)
        L_0x0051:
            r7.f260if = r0
        L_0x0053:
            java.lang.String r0 = r7.f260if
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.R.m315int():java.lang.String");
    }

    public R(com.threatmetrix.TrustDefender.internal.P.O o, long j) throws InterruptedException {
        boolean z = false;
        CountDownLatch countDownLatch = null;
        this.f510long = null;
        this.f512try = o;
        this.f508break = j;
        CountDownLatch countDownLatch2 = new CountDownLatch(1);
        Handler handler = new Handler(Looper.getMainLooper());
        if (WY.m371for() || WY.m372new()) {
            z = true;
        }
        if (z) {
            countDownLatch = countDownLatch2;
        }
        this.f511this = new EF(countDownLatch);
        String str = f507byte;
        StringBuilder sb = new StringBuilder("Firing off initJSExecutor on UI thread using latch: ");
        sb.append(countDownLatch2.hashCode());
        sb.append(" with count: ");
        sb.append(countDownLatch2.getCount());
        TL.m338new(str, sb.toString());
        AnonymousClass4 r0 = new O(countDownLatch2) {
            public final void run() {
                String str;
                CountDownLatch countDownLatch;
                TL.m338new(R.f507byte, "Calling initJSExecutor() - on UI thread");
                WY wy = new WY(R.this.f512try.f487for, R.this.f511this);
                try {
                    if (Q.m167for()) {
                        String str2 = WY.f595int;
                        StringBuilder sb = new StringBuilder("init() - init'd = ");
                        sb.append(wy.f604new);
                        TL.m338new(str2, sb.toString());
                        if (!wy.f604new) {
                            if (wy.f601do == null) {
                                TL.m338new(WY.f595int, "init() - No web view, nothing needs to be done");
                            } else {
                                TL.m338new(WY.f595int, "init() loading bogus page");
                                if (wy.f602else || WY.m372new()) {
                                    str = "<html><head></head><body></body></html>";
                                    countDownLatch = null;
                                } else {
                                    countDownLatch = new CountDownLatch(1);
                                    String str3 = WY.f595int;
                                    StringBuilder sb2 = new StringBuilder("Creating latch: ");
                                    sb2.append(countDownLatch.hashCode());
                                    sb2.append(" with count: ");
                                    sb2.append(countDownLatch.getCount());
                                    TL.m338new(str3, sb2.toString());
                                    str = "<html><head></head><body onLoad='javascript:window.androidJSInterface.getString(1)'></body></html>";
                                    wy.f603for.m52do(countDownLatch);
                                    wy.f603for.f154for = null;
                                }
                                if (!Thread.currentThread().isInterrupted()) {
                                    wy.f601do.loadData(str, "text/html", null);
                                    if (!wy.f602else && countDownLatch != null && !WY.m372new()) {
                                        String str4 = WY.f595int;
                                        StringBuilder sb3 = new StringBuilder("waiting for latch: ");
                                        sb3.append(countDownLatch.hashCode());
                                        sb3.append(" with count: ");
                                        sb3.append(countDownLatch.getCount());
                                        TL.m338new(str4, sb3.toString());
                                        if (!countDownLatch.await(5, TimeUnit.SECONDS)) {
                                            TL.m332if(WY.f595int, "timed out waiting for javascript");
                                        } else {
                                            wy.f604new = true;
                                            String str5 = WY.f595int;
                                            StringBuilder sb4 = new StringBuilder("in init() count = ");
                                            sb4.append(countDownLatch.getCount());
                                            TL.m338new(str5, sb4.toString());
                                            if (wy.f603for.f154for == null) {
                                                TL.m338new(WY.f595int, "init() After latch: got null");
                                            } else {
                                                String str6 = WY.f595int;
                                                StringBuilder sb5 = new StringBuilder("init() After latch: got ");
                                                sb5.append(wy.f603for.f154for);
                                                TL.m338new(str6, sb5.toString());
                                            }
                                        }
                                    }
                                }
                            }
                            wy.f604new = true;
                        }
                    }
                    this.f517new = wy;
                } catch (InterruptedException unused) {
                    TL.m332if(R.f507byte, "Interrupted initing js engine");
                }
                TL.m338new(R.f507byte, "js exec init complete");
                if (this.f516if != null) {
                    String str7 = R.f507byte;
                    StringBuilder sb6 = new StringBuilder("js exec init countdown using latch: ");
                    sb6.append(this.f516if.hashCode());
                    sb6.append(" with count: ");
                    sb6.append(this.f516if.getCount());
                    TL.m338new(str7, sb6.toString());
                    this.f516if.countDown();
                }
            }
        };
        handler.post(r0);
        if (!countDownLatch2.await(10, TimeUnit.SECONDS)) {
            this.f509goto = true;
            String str2 = f507byte;
            StringBuilder sb2 = new StringBuilder("initJSExecutor no response from UI thread before timeout using init latch: ");
            sb2.append(countDownLatch2.hashCode());
            sb2.append(" with count: ");
            sb2.append(countDownLatch2.getCount());
            TL.m332if(str2, sb2.toString());
            throw new InterruptedException();
        }
        this.f513void = r0.f517new;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00d8 A[Catch:{ InterruptedException -> 0x014b }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f6 A[Catch:{ InterruptedException -> 0x014b }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0111 A[Catch:{ InterruptedException -> 0x014b }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0114 A[Catch:{ InterruptedException -> 0x014b }] */
    /* renamed from: do reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void m313do() {
        /*
            r11 = this;
            java.util.concurrent.CountDownLatch r0 = r11.f510long
            if (r0 == 0) goto L_0x0153
            java.lang.String r0 = f507byte     // Catch:{ InterruptedException -> 0x014b }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r2 = "waiting for getBrowserInfo to finished, latch: "
            r1.<init>(r2)     // Catch:{ InterruptedException -> 0x014b }
            java.util.concurrent.CountDownLatch r2 = r11.f510long     // Catch:{ InterruptedException -> 0x014b }
            long r2 = r2.getCount()     // Catch:{ InterruptedException -> 0x014b }
            r1.append(r2)     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r2 = " - "
            r1.append(r2)     // Catch:{ InterruptedException -> 0x014b }
            java.util.concurrent.CountDownLatch r2 = r11.f510long     // Catch:{ InterruptedException -> 0x014b }
            int r2 = r2.hashCode()     // Catch:{ InterruptedException -> 0x014b }
            r1.append(r2)     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r1 = r1.toString()     // Catch:{ InterruptedException -> 0x014b }
            com.threatmetrix.TrustDefender.internal.TL.m338new(r0, r1)     // Catch:{ InterruptedException -> 0x014b }
            java.util.concurrent.CountDownLatch r0 = r11.f510long     // Catch:{ InterruptedException -> 0x014b }
            r1 = 10
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x014b }
            boolean r0 = r0.await(r1, r3)     // Catch:{ InterruptedException -> 0x014b }
            r1 = 1
            if (r0 == 0) goto L_0x0121
            boolean r0 = com.threatmetrix.TrustDefender.internal.WY.m371for()     // Catch:{ InterruptedException -> 0x014b }
            if (r0 != 0) goto L_0x0044
            boolean r0 = com.threatmetrix.TrustDefender.internal.WY.m372new()     // Catch:{ InterruptedException -> 0x014b }
            if (r0 == 0) goto L_0x014a
        L_0x0044:
            long r2 = r11.f508break     // Catch:{ InterruptedException -> 0x014b }
            r4 = 32
            long r6 = r2 & r4
            r2 = 0
            int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            r4 = 0
            if (r0 == 0) goto L_0x0077
            com.threatmetrix.TrustDefender.internal.EF r0 = r11.f511this     // Catch:{ InterruptedException -> 0x014b }
            java.util.ArrayList<java.lang.String> r0 = r0.f156int     // Catch:{ InterruptedException -> 0x014b }
            int r0 = r0.size()     // Catch:{ InterruptedException -> 0x014b }
            if (r0 <= 0) goto L_0x0077
            com.threatmetrix.TrustDefender.internal.EF r0 = r11.f511this     // Catch:{ InterruptedException -> 0x014b }
            java.util.ArrayList<java.lang.String> r0 = r0.f156int     // Catch:{ InterruptedException -> 0x014b }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ InterruptedException -> 0x014b }
            if (r0 == 0) goto L_0x0071
            boolean r5 = r0.isEmpty()     // Catch:{ InterruptedException -> 0x014b }
            if (r5 != 0) goto L_0x0071
            r11.m310int(r0)     // Catch:{ InterruptedException -> 0x014b }
            goto L_0x0075
        L_0x0071:
            java.lang.String r0 = ""
            r11.f259for = r0     // Catch:{ InterruptedException -> 0x014b }
        L_0x0075:
            r0 = 1
            goto L_0x0078
        L_0x0077:
            r0 = 0
        L_0x0078:
            java.lang.Thread r5 = java.lang.Thread.currentThread()     // Catch:{ InterruptedException -> 0x014b }
            boolean r5 = r5.isInterrupted()     // Catch:{ InterruptedException -> 0x014b }
            if (r5 != 0) goto L_0x0120
            long r5 = r11.f508break     // Catch:{ InterruptedException -> 0x014b }
            r7 = 4
            long r9 = r5 & r7
            int r5 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x0120
            com.threatmetrix.TrustDefender.internal.EF r2 = r11.f511this     // Catch:{ InterruptedException -> 0x014b }
            java.util.ArrayList<java.lang.String> r2 = r2.f156int     // Catch:{ InterruptedException -> 0x014b }
            int r2 = r2.size()     // Catch:{ InterruptedException -> 0x014b }
            if (r2 <= r0) goto L_0x0120
            com.threatmetrix.TrustDefender.internal.EF r2 = r11.f511this     // Catch:{ InterruptedException -> 0x014b }
            java.util.ArrayList<java.lang.String> r2 = r2.f156int     // Catch:{ InterruptedException -> 0x014b }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ InterruptedException -> 0x014b }
            int r0 = r0 + r1
            if (r2 == 0) goto L_0x00b8
            boolean r1 = r2.isEmpty()     // Catch:{ InterruptedException -> 0x014b }
            if (r1 != 0) goto L_0x00b8
            int r1 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x00b0 }
            r11.f256char = r1     // Catch:{ NumberFormatException -> 0x00b0 }
            goto L_0x00ba
        L_0x00b0:
            r1 = move-exception
            java.lang.String r2 = f507byte     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r3 = "failed to convert"
            com.threatmetrix.TrustDefender.internal.TL.m337int(r2, r3, r1)     // Catch:{ InterruptedException -> 0x014b }
        L_0x00b8:
            r11.f256char = r4     // Catch:{ InterruptedException -> 0x014b }
        L_0x00ba:
            int r1 = r11.f256char     // Catch:{ InterruptedException -> 0x014b }
            if (r1 <= 0) goto L_0x00d4
            com.threatmetrix.TrustDefender.internal.EF r1 = r11.f511this     // Catch:{ InterruptedException -> 0x014b }
            java.util.ArrayList<java.lang.String> r1 = r1.f156int     // Catch:{ InterruptedException -> 0x014b }
            int r1 = r1.size()     // Catch:{ InterruptedException -> 0x014b }
            if (r1 <= r0) goto L_0x00d4
            com.threatmetrix.TrustDefender.internal.EF r1 = r11.f511this     // Catch:{ InterruptedException -> 0x014b }
            java.util.ArrayList<java.lang.String> r1 = r1.f156int     // Catch:{ InterruptedException -> 0x014b }
            java.lang.Object r0 = r1.get(r0)     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ InterruptedException -> 0x014b }
            r11.f255case = r0     // Catch:{ InterruptedException -> 0x014b }
        L_0x00d4:
            java.lang.String r0 = r11.f255case     // Catch:{ InterruptedException -> 0x014b }
            if (r0 == 0) goto L_0x00f6
            java.lang.String r0 = r11.f255case     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r0 = com.threatmetrix.TrustDefender.internal.NK.m208do(r0)     // Catch:{ InterruptedException -> 0x014b }
            r11.f258else = r0     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r0 = f507byte     // Catch:{ InterruptedException -> 0x014b }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r2 = "Got:"
            r1.<init>(r2)     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r2 = r11.f255case     // Catch:{ InterruptedException -> 0x014b }
            r1.append(r2)     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r1 = r1.toString()     // Catch:{ InterruptedException -> 0x014b }
            com.threatmetrix.TrustDefender.internal.TL.m338new(r0, r1)     // Catch:{ InterruptedException -> 0x014b }
            goto L_0x00fa
        L_0x00f6:
            java.lang.String r0 = ""
            r11.f258else = r0     // Catch:{ InterruptedException -> 0x014b }
        L_0x00fa:
            java.lang.String r0 = f507byte     // Catch:{ InterruptedException -> 0x014b }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r2 = "Got mime "
            r1.<init>(r2)     // Catch:{ InterruptedException -> 0x014b }
            int r2 = r11.f256char     // Catch:{ InterruptedException -> 0x014b }
            r1.append(r2)     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r2 = ":"
            r1.append(r2)     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r2 = r11.f255case     // Catch:{ InterruptedException -> 0x014b }
            if (r2 == 0) goto L_0x0114
            java.lang.String r2 = r11.f255case     // Catch:{ InterruptedException -> 0x014b }
            goto L_0x0116
        L_0x0114:
            java.lang.String r2 = ""
        L_0x0116:
            r1.append(r2)     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r1 = r1.toString()     // Catch:{ InterruptedException -> 0x014b }
            com.threatmetrix.TrustDefender.internal.TL.m338new(r0, r1)     // Catch:{ InterruptedException -> 0x014b }
        L_0x0120:
            return
        L_0x0121:
            java.lang.String r0 = f507byte     // Catch:{ InterruptedException -> 0x014b }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r3 = "getBrowserInfo no response from UI thread before timeout using latch: "
            r2.<init>(r3)     // Catch:{ InterruptedException -> 0x014b }
            java.util.concurrent.CountDownLatch r3 = r11.f510long     // Catch:{ InterruptedException -> 0x014b }
            int r3 = r3.hashCode()     // Catch:{ InterruptedException -> 0x014b }
            r2.append(r3)     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r3 = " with count: "
            r2.append(r3)     // Catch:{ InterruptedException -> 0x014b }
            java.util.concurrent.CountDownLatch r3 = r11.f510long     // Catch:{ InterruptedException -> 0x014b }
            long r3 = r3.getCount()     // Catch:{ InterruptedException -> 0x014b }
            r2.append(r3)     // Catch:{ InterruptedException -> 0x014b }
            java.lang.String r2 = r2.toString()     // Catch:{ InterruptedException -> 0x014b }
            com.threatmetrix.TrustDefender.internal.TL.m332if(r0, r2)     // Catch:{ InterruptedException -> 0x014b }
            r11.f509goto = r1     // Catch:{ InterruptedException -> 0x014b }
        L_0x014a:
            return
        L_0x014b:
            r0 = move-exception
            java.lang.String r1 = f507byte
            java.lang.String r2 = "getBrowserInfo interrupted"
            com.threatmetrix.TrustDefender.internal.TL.m337int(r1, r2, r0)
        L_0x0153:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.R.m313do():void");
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public final boolean m314for() {
        int i;
        boolean z = false;
        if ((this.f508break & 38) == 0 || !(!this.f509goto)) {
            return false;
        }
        if (WY.m371for() || WY.m372new()) {
            z = true;
        }
        if (z) {
            int i2 = (this.f508break & 32) != 0 ? 2 : 1;
            i = (this.f508break & 4) != 0 ? i2 + 2 : i2;
        } else {
            i = 1;
        }
        this.f510long = new CountDownLatch(i);
        Handler handler = new Handler(Looper.getMainLooper());
        String str = f507byte;
        StringBuilder sb = new StringBuilder("Firing off getBrowserInfo on UI thread using latch: ");
        sb.append(this.f510long.hashCode());
        sb.append(" with count: ");
        sb.append(i);
        TL.m338new(str, sb.toString());
        this.f511this.m52do(z ? this.f510long : null);
        handler.post(new O(this.f510long) {
            public final void run() {
                try {
                    TL.m338new(R.f507byte, "Calling getBrowserInfo() - on UI thread");
                    R.m312new(R.this);
                } catch (InterruptedException e) {
                    TL.m328for(R.f507byte, "getBrowserInfo interrupted", (Throwable) e);
                }
                if (this.f516if != null) {
                    String str = R.f507byte;
                    StringBuilder sb = new StringBuilder("getBrowserInfo countdown using latch: ");
                    sb.append(this.f516if.hashCode());
                    sb.append(" with count: ");
                    sb.append(this.f516if.getCount());
                    TL.m338new(str, sb.toString());
                    this.f516if.countDown();
                }
            }
        });
        return true;
    }

    /* renamed from: int reason: not valid java name */
    private void m310int(String str) throws InterruptedException {
        this.f257do = str.replaceAll("(<FIELD_SEP>|<REC_SEP>)", "");
        this.f259for = NK.m208do(this.f257do);
        ArrayList arrayList = new ArrayList();
        String[] split = str.split("<REC_SEP>");
        int length = split.length;
        int i = 0;
        while (i < length) {
            String str2 = split[i];
            if (!Thread.currentThread().isInterrupted()) {
                HashMap hashMap = new HashMap();
                String[] split2 = str2.split("<FIELD_SEP>");
                if (split2.length == 4) {
                    hashMap.put("name", split2[0]);
                    hashMap.put("description", split2[1]);
                    hashMap.put("filename", split2[2]);
                    hashMap.put("length", split2[3]);
                    arrayList.add(hashMap);
                }
                i++;
            } else {
                return;
            }
        }
        this.f262new = Integer.toString(arrayList.size());
        StringBuilder sb = new StringBuilder();
        sb.append(m309int("QuickTime Plug-in", "plugin_quicktime", arrayList));
        sb.append(m309int("Adobe Acrobat", "plugin_adobe_acrobat", arrayList));
        sb.append(m309int("Java", "plugin_java", arrayList));
        sb.append(m309int("SVG Viewer", "plugin_svg_viewer", arrayList));
        sb.append(m309int("Flash", "plugin_flash", arrayList));
        sb.append(m309int("Windows Media Player", "plugin_windows_media_player", arrayList));
        sb.append(m309int("Silverlight", "plugin_silverlight", arrayList));
        sb.append(m309int("Real Player", "plugin_realplayer", arrayList));
        sb.append(m309int("ShockWave Director", "plugin_shockwave", arrayList));
        sb.append(m309int("VLC", "plugin_vlc_player", arrayList));
        sb.append(m309int("DevalVR", "plugin_devalvr", arrayList));
        String obj = sb.toString();
        this.f261int = obj;
        String str3 = f507byte;
        StringBuilder sb2 = new StringBuilder("Got");
        sb2.append(this.f262new);
        sb2.append(":");
        sb2.append(obj);
        TL.m338new(str3, sb2.toString());
    }

    /* renamed from: int reason: not valid java name */
    private static String m309int(String str, String str2, ArrayList<HashMap<String, String>> arrayList) {
        HashMap hashMap;
        String str3 = "false";
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                hashMap = null;
                break;
            }
            hashMap = (HashMap) it.next();
            String str4 = (String) hashMap.get("name");
            if (str4 != null && str4.toLowerCase(Locale.US).contains(str.toLowerCase(Locale.US))) {
                break;
            }
        }
        if (hashMap != null) {
            String str5 = (String) hashMap.get("name");
            if (str5 != null) {
                str3 = str5.replace("[abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXY -]", "");
                if (!str3.isEmpty()) {
                    str3 = "true";
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append("^");
        sb.append(str3);
        sb.append("!");
        return sb.toString();
    }

    /* renamed from: new reason: not valid java name */
    static /* synthetic */ void m312new(R r) throws InterruptedException {
        if (!Thread.currentThread().isInterrupted()) {
            if ((r.f508break & 32) != 0) {
                String str = r.f513void.m373if("(function () { var plugins_string='', i=0; for (p=navigator.plugins[0]; i< navigator.plugins.length;p=navigator.plugins[i++]) {  plugins_string += p.name + '<FIELD_SEP>' + p.description + '<FIELD_SEP>' + p.filename + '<FIELD_SEP>' + p.length.toString() + '<REC_SEP>'; } return plugins_string;})();");
                if (str != null) {
                    r.m310int(str);
                }
            }
            if (!Thread.currentThread().isInterrupted() && (r.f508break & 4) != 0) {
                String str2 = r.f513void.m373if("navigator.mimeTypes.length");
                if (str2 != null) {
                    try {
                        r.f256char = Integer.parseInt(str2);
                    } catch (NumberFormatException e) {
                        TL.m337int(f507byte, "failed to convert", e);
                    }
                }
                r.f255case = r.f513void.m373if("(function () { var mime_string='', i=0; for (var m=navigator.mimeTypes[0]; i< navigator.mimeTypes.length;m=navigator.mimeTypes[i++]) {  mime_string += m.type; } return mime_string;})();");
                if (r.f255case != null) {
                    r.f258else = NK.m208do(r.f255case);
                    String str3 = f507byte;
                    StringBuilder sb = new StringBuilder("Got:");
                    sb.append(r.f255case);
                    TL.m338new(str3, sb.toString());
                    return;
                }
                r.f258else = "";
            }
        }
    }
}
