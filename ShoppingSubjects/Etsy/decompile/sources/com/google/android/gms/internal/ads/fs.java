package com.google.android.gms.internal.ads;

public class fs implements ajo {
    private static final boolean a = ct.a;
    @Deprecated
    private final nq b;
    private final ey c;
    private final gt d;

    public fs(ey eyVar) {
        this(eyVar, new gt(4096));
    }

    private fs(ey eyVar, gt gtVar) {
        this.c = eyVar;
        this.b = eyVar;
        this.d = gtVar;
    }

    @Deprecated
    public fs(nq nqVar) {
        this(nqVar, new gt(4096));
    }

    @Deprecated
    private fs(nq nqVar, gt gtVar) {
        this.b = nqVar;
        this.c = new ej(nqVar);
        this.d = gtVar;
    }

    private static void a(String str, amf<?> amf, zzae zzae) throws zzae {
        s j = amf.j();
        int i = amf.i();
        try {
            j.a(zzae);
            amf.b(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(i)}));
        } catch (zzae e) {
            amf.b(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(i)}));
            throw e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0046 A[SYNTHETIC, Splitter:B:23:0x0046] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final byte[] a(java.io.InputStream r6, int r7) throws java.io.IOException, com.google.android.gms.internal.ads.zzac {
        /*
            r5 = this;
            com.google.android.gms.internal.ads.pz r0 = new com.google.android.gms.internal.ads.pz
            com.google.android.gms.internal.ads.gt r1 = r5.d
            r0.<init>(r1, r7)
            r7 = 0
            r1 = 0
            if (r6 != 0) goto L_0x0013
            com.google.android.gms.internal.ads.zzac r2 = new com.google.android.gms.internal.ads.zzac     // Catch:{ all -> 0x0011 }
            r2.<init>()     // Catch:{ all -> 0x0011 }
            throw r2     // Catch:{ all -> 0x0011 }
        L_0x0011:
            r2 = move-exception
            goto L_0x0044
        L_0x0013:
            com.google.android.gms.internal.ads.gt r2 = r5.d     // Catch:{ all -> 0x0011 }
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r2 = r2.a(r3)     // Catch:{ all -> 0x0011 }
        L_0x001b:
            int r1 = r6.read(r2)     // Catch:{ all -> 0x0040 }
            r3 = -1
            if (r1 == r3) goto L_0x0026
            r0.write(r2, r7, r1)     // Catch:{ all -> 0x0040 }
            goto L_0x001b
        L_0x0026:
            byte[] r1 = r0.toByteArray()     // Catch:{ all -> 0x0040 }
            if (r6 == 0) goto L_0x0037
            r6.close()     // Catch:{ IOException -> 0x0030 }
            goto L_0x0037
        L_0x0030:
            java.lang.String r6 = "Error occurred when closing InputStream"
            java.lang.Object[] r7 = new java.lang.Object[r7]
            com.google.android.gms.internal.ads.ct.a(r6, r7)
        L_0x0037:
            com.google.android.gms.internal.ads.gt r6 = r5.d
            r6.a(r2)
            r0.close()
            return r1
        L_0x0040:
            r1 = move-exception
            r4 = r2
            r2 = r1
            r1 = r4
        L_0x0044:
            if (r6 == 0) goto L_0x0051
            r6.close()     // Catch:{ IOException -> 0x004a }
            goto L_0x0051
        L_0x004a:
            java.lang.String r6 = "Error occurred when closing InputStream"
            java.lang.Object[] r7 = new java.lang.Object[r7]
            com.google.android.gms.internal.ads.ct.a(r6, r7)
        L_0x0051:
            com.google.android.gms.internal.ads.gt r6 = r5.d
            r6.a(r1)
            r0.close()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.fs.a(java.io.InputStream, int):byte[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01cb, code lost:
        if (r13 != null) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01cd, code lost:
        r11 = new com.google.android.gms.internal.ads.all(r5, r13, false, android.os.SystemClock.elapsedRealtime() - r3, r17);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01dd, code lost:
        if (r5 == 401) goto L_0x0206;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01e6, code lost:
        if (r5 < 400) goto L_0x01f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01f1, code lost:
        throw new com.google.android.gms.internal.ads.zzg(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x01f4, code lost:
        if (r5 < 500) goto L_0x0200;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x01ff, code lost:
        throw new com.google.android.gms.internal.ads.zzac(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0205, code lost:
        throw new com.google.android.gms.internal.ads.zzac(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0206, code lost:
        a("auth", r2, new com.google.android.gms.internal.ads.zza(r11));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0212, code lost:
        r5 = "network";
        r6 = new com.google.android.gms.internal.ads.zzo();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x021f, code lost:
        throw new com.google.android.gms.internal.ads.zzq(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0220, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0221, code lost:
        r3 = r0;
        r5 = "Bad URL ";
        r2 = java.lang.String.valueOf(r24.e());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0232, code lost:
        if (r2.length() != 0) goto L_0x0234;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0234, code lost:
        r2 = r5.concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0239, code lost:
        r2 = new java.lang.String(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0241, code lost:
        throw new java.lang.RuntimeException(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0242, code lost:
        r5 = "socket";
        r6 = new com.google.android.gms.internal.ads.zzad();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0249, code lost:
        a(r5, r2, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0114, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0115, code lost:
        r5 = r0;
        r17 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0118, code lost:
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0197, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0199, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x019a, code lost:
        r7 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x019b, code lost:
        r13 = r5;
        r17 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x019f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01a0, code lost:
        r5 = r0;
        r17 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01a6, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01a7, code lost:
        r17 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01aa, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01ab, code lost:
        r17 = r5;
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01ae, code lost:
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01b2, code lost:
        r5 = r10.a();
        com.google.android.gms.internal.ads.ct.c("Unexpected response code %d for %s", java.lang.Integer.valueOf(r5), r24.e());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0220 A[ExcHandler: MalformedURLException (r0v0 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:2:0x000d] */
    /* JADX WARNING: Removed duplicated region for block: B:131:? A[ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException), SYNTHETIC, Splitter:B:2:0x000d] */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x021a A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.ads.all a(com.google.android.gms.internal.ads.amf<?> r24) throws com.google.android.gms.internal.ads.zzae {
        /*
            r23 = this;
            r1 = r23
            r2 = r24
            long r3 = android.os.SystemClock.elapsedRealtime()
        L_0x0008:
            java.util.List r5 = java.util.Collections.emptyList()
            r9 = 0
            com.google.android.gms.internal.ads.acb r10 = r24.f()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x01aa }
            if (r10 != 0) goto L_0x0018
            java.util.Map r10 = java.util.Collections.emptyMap()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x01aa }
            goto L_0x003c
        L_0x0018:
            java.util.HashMap r11 = new java.util.HashMap     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x01aa }
            r11.<init>()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x01aa }
            java.lang.String r12 = r10.b     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x01aa }
            if (r12 == 0) goto L_0x0028
            java.lang.String r12 = "If-None-Match"
            java.lang.String r13 = r10.b     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x01aa }
            r11.put(r12, r13)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x01aa }
        L_0x0028:
            long r12 = r10.d     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x01aa }
            r14 = 0
            int r16 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r16 <= 0) goto L_0x003b
            java.lang.String r12 = "If-Modified-Since"
            long r13 = r10.d     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x01aa }
            java.lang.String r10 = com.google.android.gms.internal.ads.lu.a(r13)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x01aa }
            r11.put(r12, r10)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x01aa }
        L_0x003b:
            r10 = r11
        L_0x003c:
            com.google.android.gms.internal.ads.ey r11 = r1.c     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x01aa }
            com.google.android.gms.internal.ads.mr r10 = r11.a(r2, r10)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x01aa }
            int r12 = r10.a()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x01a6 }
            java.util.List r11 = r10.b()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x01a6 }
            r5 = 304(0x130, float:4.26E-43)
            if (r12 != r5) goto L_0x011b
            com.google.android.gms.internal.ads.acb r5 = r24.f()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            if (r5 != 0) goto L_0x0068
            com.google.android.gms.internal.ads.all r5 = new com.google.android.gms.internal.ads.all     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            r14 = 304(0x130, float:4.26E-43)
            r15 = 0
            r16 = 1
            long r12 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            long r17 = r12 - r3
            r13 = r5
            r19 = r11
            r13.<init>(r14, r15, r16, r17, r19)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            return r5
        L_0x0068:
            java.util.TreeSet r12 = new java.util.TreeSet     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            java.util.Comparator r13 = java.lang.String.CASE_INSENSITIVE_ORDER     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            r12.<init>(r13)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            boolean r13 = r11.isEmpty()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            if (r13 != 0) goto L_0x008d
            java.util.Iterator r13 = r11.iterator()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
        L_0x0079:
            boolean r14 = r13.hasNext()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            if (r14 == 0) goto L_0x008d
            java.lang.Object r14 = r13.next()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            com.google.android.gms.internal.ads.ajj r14 = (com.google.android.gms.internal.ads.ajj) r14     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            java.lang.String r14 = r14.a()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            r12.add(r14)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            goto L_0x0079
        L_0x008d:
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            r13.<init>(r11)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            java.util.List<com.google.android.gms.internal.ads.ajj> r14 = r5.h     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            if (r14 == 0) goto L_0x00be
            java.util.List<com.google.android.gms.internal.ads.ajj> r14 = r5.h     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            boolean r14 = r14.isEmpty()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            if (r14 != 0) goto L_0x00fd
            java.util.List<com.google.android.gms.internal.ads.ajj> r14 = r5.h     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            java.util.Iterator r14 = r14.iterator()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
        L_0x00a4:
            boolean r15 = r14.hasNext()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            if (r15 == 0) goto L_0x00fd
            java.lang.Object r15 = r14.next()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            com.google.android.gms.internal.ads.ajj r15 = (com.google.android.gms.internal.ads.ajj) r15     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            java.lang.String r8 = r15.a()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            boolean r8 = r12.contains(r8)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            if (r8 != 0) goto L_0x00a4
            r13.add(r15)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            goto L_0x00a4
        L_0x00be:
            java.util.Map<java.lang.String, java.lang.String> r8 = r5.g     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            boolean r8 = r8.isEmpty()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            if (r8 != 0) goto L_0x00fd
            java.util.Map<java.lang.String, java.lang.String> r8 = r5.g     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            java.util.Set r8 = r8.entrySet()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
        L_0x00d0:
            boolean r14 = r8.hasNext()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            if (r14 == 0) goto L_0x00fd
            java.lang.Object r14 = r8.next()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            java.lang.Object r15 = r14.getKey()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            boolean r15 = r12.contains(r15)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            if (r15 != 0) goto L_0x00d0
            com.google.android.gms.internal.ads.ajj r15 = new com.google.android.gms.internal.ads.ajj     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            java.lang.Object r16 = r14.getKey()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            r6 = r16
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            java.lang.Object r14 = r14.getValue()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            r15.<init>(r6, r14)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            r13.add(r15)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            goto L_0x00d0
        L_0x00fd:
            com.google.android.gms.internal.ads.all r6 = new com.google.android.gms.internal.ads.all     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            r16 = 304(0x130, float:4.26E-43)
            byte[] r5 = r5.a     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            r18 = 1
            long r14 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            long r19 = r14 - r3
            r15 = r6
            r17 = r5
            r21 = r13
            r15.<init>(r16, r17, r18, r19, r21)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            return r6
        L_0x0114:
            r0 = move-exception
            r5 = r0
            r17 = r11
        L_0x0118:
            r13 = 0
            goto L_0x01b0
        L_0x011b:
            java.io.InputStream r5 = r10.d()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x019f }
            if (r5 == 0) goto L_0x012a
            int r6 = r10.c()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            byte[] r5 = r1.a(r5, r6)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0114 }
            goto L_0x012c
        L_0x012a:
            byte[] r5 = new byte[r9]     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x019f }
        L_0x012c:
            long r13 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0199 }
            long r7 = r13 - r3
            boolean r6 = a     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0199 }
            if (r6 != 0) goto L_0x013c
            r13 = 3000(0xbb8, double:1.482E-320)
            int r6 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r6 <= 0) goto L_0x0175
        L_0x013c:
            java.lang.String r6 = "HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]"
            r13 = 5
            java.lang.Object[] r13 = new java.lang.Object[r13]     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0199 }
            r13[r9] = r2     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0199 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0199 }
            r8 = 1
            r13[r8] = r7     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0199 }
            if (r5 == 0) goto L_0x0157
            int r7 = r5.length     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0152 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0152 }
            goto L_0x0159
        L_0x0152:
            r0 = move-exception
            r13 = r5
            r17 = r11
            goto L_0x01af
        L_0x0157:
            java.lang.String r7 = "null"
        L_0x0159:
            r8 = 2
            r13[r8] = r7     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0199 }
            r7 = 3
            java.lang.Integer r8 = java.lang.Integer.valueOf(r12)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0199 }
            r13[r7] = r8     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0199 }
            r7 = 4
            com.google.android.gms.internal.ads.s r8 = r24.j()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0199 }
            int r8 = r8.b()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0199 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0199 }
            r13[r7] = r8     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0199 }
            com.google.android.gms.internal.ads.ct.b(r6, r13)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0199 }
        L_0x0175:
            r6 = 200(0xc8, float:2.8E-43)
            if (r12 < r6) goto L_0x0190
            r6 = 299(0x12b, float:4.19E-43)
            if (r12 <= r6) goto L_0x017e
            goto L_0x0190
        L_0x017e:
            com.google.android.gms.internal.ads.all r6 = new com.google.android.gms.internal.ads.all     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0199 }
            r14 = 0
            long r7 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0199 }
            long r15 = r7 - r3
            r7 = r11
            r11 = r6
            r13 = r5
            r17 = r7
            r11.<init>(r12, r13, r14, r15, r17)     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0197 }
            return r6
        L_0x0190:
            r7 = r11
            java.io.IOException r6 = new java.io.IOException     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0197 }
            r6.<init>()     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0197 }
            throw r6     // Catch:{ SocketTimeoutException -> 0x0242, MalformedURLException -> 0x0220, IOException -> 0x0197 }
        L_0x0197:
            r0 = move-exception
            goto L_0x019b
        L_0x0199:
            r0 = move-exception
            r7 = r11
        L_0x019b:
            r13 = r5
            r17 = r7
            goto L_0x01af
        L_0x019f:
            r0 = move-exception
            r7 = r11
            r5 = r0
            r17 = r7
            goto L_0x0118
        L_0x01a6:
            r0 = move-exception
            r17 = r5
            goto L_0x01ae
        L_0x01aa:
            r0 = move-exception
            r17 = r5
            r10 = 0
        L_0x01ae:
            r13 = 0
        L_0x01af:
            r5 = r0
        L_0x01b0:
            if (r10 == 0) goto L_0x021a
            int r5 = r10.a()
            java.lang.String r6 = "Unexpected response code %d for %s"
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]
            java.lang.Integer r8 = java.lang.Integer.valueOf(r5)
            r7[r9] = r8
            java.lang.String r8 = r24.e()
            r9 = 1
            r7[r9] = r8
            com.google.android.gms.internal.ads.ct.c(r6, r7)
            if (r13 == 0) goto L_0x0212
            com.google.android.gms.internal.ads.all r6 = new com.google.android.gms.internal.ads.all
            r14 = 0
            long r7 = android.os.SystemClock.elapsedRealtime()
            long r15 = r7 - r3
            r11 = r6
            r12 = r5
            r11.<init>(r12, r13, r14, r15, r17)
            r7 = 401(0x191, float:5.62E-43)
            if (r5 == r7) goto L_0x0206
            r7 = 403(0x193, float:5.65E-43)
            if (r5 != r7) goto L_0x01e4
            goto L_0x0206
        L_0x01e4:
            r2 = 400(0x190, float:5.6E-43)
            if (r5 < r2) goto L_0x01f2
            r2 = 499(0x1f3, float:6.99E-43)
            if (r5 > r2) goto L_0x01f2
            com.google.android.gms.internal.ads.zzg r2 = new com.google.android.gms.internal.ads.zzg
            r2.<init>(r6)
            throw r2
        L_0x01f2:
            r2 = 500(0x1f4, float:7.0E-43)
            if (r5 < r2) goto L_0x0200
            r2 = 599(0x257, float:8.4E-43)
            if (r5 > r2) goto L_0x0200
            com.google.android.gms.internal.ads.zzac r2 = new com.google.android.gms.internal.ads.zzac
            r2.<init>(r6)
            throw r2
        L_0x0200:
            com.google.android.gms.internal.ads.zzac r2 = new com.google.android.gms.internal.ads.zzac
            r2.<init>(r6)
            throw r2
        L_0x0206:
            java.lang.String r5 = "auth"
            com.google.android.gms.internal.ads.zza r7 = new com.google.android.gms.internal.ads.zza
            r7.<init>(r6)
            a(r5, r2, r7)
            goto L_0x0008
        L_0x0212:
            java.lang.String r5 = "network"
            com.google.android.gms.internal.ads.zzo r6 = new com.google.android.gms.internal.ads.zzo
            r6.<init>()
            goto L_0x0249
        L_0x021a:
            com.google.android.gms.internal.ads.zzq r2 = new com.google.android.gms.internal.ads.zzq
            r2.<init>(r5)
            throw r2
        L_0x0220:
            r0 = move-exception
            r3 = r0
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            java.lang.String r5 = "Bad URL "
            java.lang.String r2 = r24.e()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r6 = r2.length()
            if (r6 == 0) goto L_0x0239
            java.lang.String r2 = r5.concat(r2)
            goto L_0x023e
        L_0x0239:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r5)
        L_0x023e:
            r4.<init>(r2, r3)
            throw r4
        L_0x0242:
            java.lang.String r5 = "socket"
            com.google.android.gms.internal.ads.zzad r6 = new com.google.android.gms.internal.ads.zzad
            r6.<init>()
        L_0x0249:
            a(r5, r2, r6)
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.fs.a(com.google.android.gms.internal.ads.amf):com.google.android.gms.internal.ads.all");
    }
}
