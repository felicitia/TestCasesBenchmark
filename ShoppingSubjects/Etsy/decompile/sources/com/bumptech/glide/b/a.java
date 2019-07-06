package com.bumptech.glide.b;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;

/* compiled from: GifDecoder */
public class a {
    private static final String a = "a";
    private static final Config b = Config.ARGB_8888;
    private int[] c;
    private ByteBuffer d;
    private final byte[] e = new byte[256];
    private short[] f;
    private byte[] g;
    private byte[] h;
    private byte[] i;
    private int[] j;
    private int k;
    private byte[] l;
    private c m;
    private C0008a n;
    private Bitmap o;
    private boolean p;
    private int q;

    /* renamed from: com.bumptech.glide.b.a$a reason: collision with other inner class name */
    /* compiled from: GifDecoder */
    public interface C0008a {
        Bitmap a(int i, int i2, Config config);

        void a(Bitmap bitmap);
    }

    public a(C0008a aVar) {
        this.n = aVar;
        this.m = new c();
    }

    public void a() {
        this.k = (this.k + 1) % this.m.c;
    }

    public int a(int i2) {
        if (i2 < 0 || i2 >= this.m.c) {
            return -1;
        }
        return ((b) this.m.e.get(i2)).i;
    }

    public int b() {
        if (this.m.c <= 0 || this.k < 0) {
            return -1;
        }
        return a(this.k);
    }

    public int c() {
        return this.m.c;
    }

    public int d() {
        return this.k;
    }

    public int e() {
        return this.m.m;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b9, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00db, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.graphics.Bitmap f() {
        /*
            r9 = this;
            monitor-enter(r9)
            com.bumptech.glide.b.c r0 = r9.m     // Catch:{ all -> 0x00dc }
            int r0 = r0.c     // Catch:{ all -> 0x00dc }
            r1 = 3
            r2 = 1
            if (r0 <= 0) goto L_0x000d
            int r0 = r9.k     // Catch:{ all -> 0x00dc }
            if (r0 >= 0) goto L_0x003b
        L_0x000d:
            java.lang.String r0 = a     // Catch:{ all -> 0x00dc }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00dc }
            if (r0 == 0) goto L_0x0039
            java.lang.String r0 = a     // Catch:{ all -> 0x00dc }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00dc }
            r3.<init>()     // Catch:{ all -> 0x00dc }
            java.lang.String r4 = "unable to decode frame, frameCount="
            r3.append(r4)     // Catch:{ all -> 0x00dc }
            com.bumptech.glide.b.c r4 = r9.m     // Catch:{ all -> 0x00dc }
            int r4 = r4.c     // Catch:{ all -> 0x00dc }
            r3.append(r4)     // Catch:{ all -> 0x00dc }
            java.lang.String r4 = " framePointer="
            r3.append(r4)     // Catch:{ all -> 0x00dc }
            int r4 = r9.k     // Catch:{ all -> 0x00dc }
            r3.append(r4)     // Catch:{ all -> 0x00dc }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00dc }
            android.util.Log.d(r0, r3)     // Catch:{ all -> 0x00dc }
        L_0x0039:
            r9.q = r2     // Catch:{ all -> 0x00dc }
        L_0x003b:
            int r0 = r9.q     // Catch:{ all -> 0x00dc }
            r3 = 0
            if (r0 == r2) goto L_0x00ba
            int r0 = r9.q     // Catch:{ all -> 0x00dc }
            r4 = 2
            if (r0 != r4) goto L_0x0047
            goto L_0x00ba
        L_0x0047:
            r0 = 0
            r9.q = r0     // Catch:{ all -> 0x00dc }
            com.bumptech.glide.b.c r4 = r9.m     // Catch:{ all -> 0x00dc }
            java.util.List<com.bumptech.glide.b.b> r4 = r4.e     // Catch:{ all -> 0x00dc }
            int r5 = r9.k     // Catch:{ all -> 0x00dc }
            java.lang.Object r4 = r4.get(r5)     // Catch:{ all -> 0x00dc }
            com.bumptech.glide.b.b r4 = (com.bumptech.glide.b.b) r4     // Catch:{ all -> 0x00dc }
            int r5 = r9.k     // Catch:{ all -> 0x00dc }
            int r5 = r5 - r2
            if (r5 < 0) goto L_0x0066
            com.bumptech.glide.b.c r6 = r9.m     // Catch:{ all -> 0x00dc }
            java.util.List<com.bumptech.glide.b.b> r6 = r6.e     // Catch:{ all -> 0x00dc }
            java.lang.Object r5 = r6.get(r5)     // Catch:{ all -> 0x00dc }
            com.bumptech.glide.b.b r5 = (com.bumptech.glide.b.b) r5     // Catch:{ all -> 0x00dc }
            goto L_0x0067
        L_0x0066:
            r5 = r3
        L_0x0067:
            int[] r6 = r4.k     // Catch:{ all -> 0x00dc }
            if (r6 != 0) goto L_0x0072
            com.bumptech.glide.b.c r6 = r9.m     // Catch:{ all -> 0x00dc }
            int[] r6 = r6.a     // Catch:{ all -> 0x00dc }
            r9.c = r6     // Catch:{ all -> 0x00dc }
            goto L_0x0082
        L_0x0072:
            int[] r6 = r4.k     // Catch:{ all -> 0x00dc }
            r9.c = r6     // Catch:{ all -> 0x00dc }
            com.bumptech.glide.b.c r6 = r9.m     // Catch:{ all -> 0x00dc }
            int r6 = r6.j     // Catch:{ all -> 0x00dc }
            int r7 = r4.h     // Catch:{ all -> 0x00dc }
            if (r6 != r7) goto L_0x0082
            com.bumptech.glide.b.c r6 = r9.m     // Catch:{ all -> 0x00dc }
            r6.l = r0     // Catch:{ all -> 0x00dc }
        L_0x0082:
            boolean r6 = r4.f     // Catch:{ all -> 0x00dc }
            if (r6 == 0) goto L_0x0093
            int[] r6 = r9.c     // Catch:{ all -> 0x00dc }
            int r7 = r4.h     // Catch:{ all -> 0x00dc }
            r6 = r6[r7]     // Catch:{ all -> 0x00dc }
            int[] r7 = r9.c     // Catch:{ all -> 0x00dc }
            int r8 = r4.h     // Catch:{ all -> 0x00dc }
            r7[r8] = r0     // Catch:{ all -> 0x00dc }
            r0 = r6
        L_0x0093:
            int[] r6 = r9.c     // Catch:{ all -> 0x00dc }
            if (r6 != 0) goto L_0x00aa
            java.lang.String r0 = a     // Catch:{ all -> 0x00dc }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00dc }
            if (r0 == 0) goto L_0x00a6
            java.lang.String r0 = a     // Catch:{ all -> 0x00dc }
            java.lang.String r1 = "No Valid Color Table"
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00dc }
        L_0x00a6:
            r9.q = r2     // Catch:{ all -> 0x00dc }
            monitor-exit(r9)
            return r3
        L_0x00aa:
            android.graphics.Bitmap r1 = r9.a(r4, r5)     // Catch:{ all -> 0x00dc }
            boolean r2 = r4.f     // Catch:{ all -> 0x00dc }
            if (r2 == 0) goto L_0x00b8
            int[] r2 = r9.c     // Catch:{ all -> 0x00dc }
            int r3 = r4.h     // Catch:{ all -> 0x00dc }
            r2[r3] = r0     // Catch:{ all -> 0x00dc }
        L_0x00b8:
            monitor-exit(r9)
            return r1
        L_0x00ba:
            java.lang.String r0 = a     // Catch:{ all -> 0x00dc }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00dc }
            if (r0 == 0) goto L_0x00da
            java.lang.String r0 = a     // Catch:{ all -> 0x00dc }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00dc }
            r1.<init>()     // Catch:{ all -> 0x00dc }
            java.lang.String r2 = "Unable to decode frame, status="
            r1.append(r2)     // Catch:{ all -> 0x00dc }
            int r2 = r9.q     // Catch:{ all -> 0x00dc }
            r1.append(r2)     // Catch:{ all -> 0x00dc }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00dc }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00dc }
        L_0x00da:
            monitor-exit(r9)
            return r3
        L_0x00dc:
            r0 = move-exception
            monitor-exit(r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.b.a.f():android.graphics.Bitmap");
    }

    public void g() {
        this.m = null;
        this.l = null;
        this.i = null;
        this.j = null;
        if (this.o != null) {
            this.n.a(this.o);
        }
        this.o = null;
        this.d = null;
    }

    public void a(c cVar, byte[] bArr) {
        this.m = cVar;
        this.l = bArr;
        this.q = 0;
        this.k = -1;
        this.d = ByteBuffer.wrap(bArr);
        this.d.rewind();
        this.d.order(ByteOrder.LITTLE_ENDIAN);
        this.p = false;
        Iterator it = cVar.e.iterator();
        while (true) {
            if (it.hasNext()) {
                if (((b) it.next()).g == 3) {
                    this.p = true;
                    break;
                }
            } else {
                break;
            }
        }
        this.i = new byte[(cVar.f * cVar.g)];
        this.j = new int[(cVar.f * cVar.g)];
    }

    private Bitmap a(b bVar, b bVar2) {
        int i2;
        int i3 = this.m.f;
        int i4 = this.m.g;
        int[] iArr = this.j;
        int i5 = 0;
        if (bVar2 != null && bVar2.g > 0) {
            if (bVar2.g == 2) {
                Arrays.fill(iArr, !bVar.f ? this.m.l : 0);
            } else if (bVar2.g == 3 && this.o != null) {
                this.o.getPixels(iArr, 0, i3, 0, 0, i3, i4);
            }
        }
        a(bVar);
        int i6 = 8;
        int i7 = 1;
        int i8 = 0;
        while (i5 < bVar.d) {
            if (bVar.e) {
                if (i8 >= bVar.d) {
                    i7++;
                    switch (i7) {
                        case 2:
                            i8 = 4;
                            break;
                        case 3:
                            i6 = 4;
                            i8 = 2;
                            break;
                        case 4:
                            i8 = 1;
                            i6 = 2;
                            break;
                    }
                }
                i2 = i8 + i6;
            } else {
                i2 = i8;
                i8 = i5;
            }
            int i9 = i8 + bVar.b;
            if (i9 < this.m.g) {
                int i10 = i9 * this.m.f;
                int i11 = bVar.a + i10;
                int i12 = bVar.c + i11;
                if (this.m.f + i10 < i12) {
                    i12 = this.m.f + i10;
                }
                int i13 = bVar.c * i5;
                while (i11 < i12) {
                    int i14 = i13 + 1;
                    int i15 = this.c[this.i[i13] & 255];
                    if (i15 != 0) {
                        iArr[i11] = i15;
                    }
                    i11++;
                    i13 = i14;
                }
            }
            i5++;
            i8 = i2;
        }
        if (this.p && (bVar.g == 0 || bVar.g == 1)) {
            if (this.o == null) {
                this.o = j();
            }
            this.o.setPixels(iArr, 0, i3, 0, 0, i3, i4);
        }
        Bitmap j2 = j();
        j2.setPixels(iArr, 0, i3, 0, 0, i3, i4);
        return j2;
    }

    /* JADX WARNING: type inference failed for: r17v0 */
    /* JADX WARNING: type inference failed for: r17v1 */
    /* JADX WARNING: type inference failed for: r23v0 */
    /* JADX WARNING: type inference failed for: r23v1 */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: type inference failed for: r17v4 */
    /* JADX WARNING: type inference failed for: r17v5 */
    /* JADX WARNING: type inference failed for: r16v6 */
    /* JADX WARNING: type inference failed for: r17v6 */
    /* JADX WARNING: type inference failed for: r3v22, types: [short[]] */
    /* JADX WARNING: type inference failed for: r2v17, types: [short] */
    /* JADX WARNING: type inference failed for: r2v19, types: [int] */
    /* JADX WARNING: type inference failed for: r23v3 */
    /* JADX WARNING: type inference failed for: r23v5 */
    /* JADX WARNING: type inference failed for: r23v6 */
    /* JADX WARNING: type inference failed for: r17v11 */
    /* JADX WARNING: type inference failed for: r16v12 */
    /* JADX WARNING: type inference failed for: r2v35 */
    /* JADX WARNING: type inference failed for: r23v7 */
    /* JADX WARNING: type inference failed for: r23v8 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=short, code=int, for r2v17, types: [short] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=short[], code=null, for r3v22, types: [short[]] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r17v5
      assigns: []
      uses: []
      mth insns count: 198
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/183835416.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/170174037.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 10 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.bumptech.glide.b.b r29) {
        /*
            r28 = this;
            r0 = r28
            r1 = r29
            if (r1 == 0) goto L_0x000d
            java.nio.ByteBuffer r2 = r0.d
            int r3 = r1.j
            r2.position(r3)
        L_0x000d:
            if (r1 != 0) goto L_0x0019
            com.bumptech.glide.b.c r1 = r0.m
            int r1 = r1.f
            com.bumptech.glide.b.c r2 = r0.m
            int r2 = r2.g
            int r1 = r1 * r2
            goto L_0x001e
        L_0x0019:
            int r2 = r1.c
            int r1 = r1.d
            int r1 = r1 * r2
        L_0x001e:
            byte[] r2 = r0.i
            if (r2 == 0) goto L_0x0027
            byte[] r2 = r0.i
            int r2 = r2.length
            if (r2 >= r1) goto L_0x002b
        L_0x0027:
            byte[] r2 = new byte[r1]
            r0.i = r2
        L_0x002b:
            short[] r2 = r0.f
            r3 = 4096(0x1000, float:5.74E-42)
            if (r2 != 0) goto L_0x0035
            short[] r2 = new short[r3]
            r0.f = r2
        L_0x0035:
            byte[] r2 = r0.g
            if (r2 != 0) goto L_0x003d
            byte[] r2 = new byte[r3]
            r0.g = r2
        L_0x003d:
            byte[] r2 = r0.h
            if (r2 != 0) goto L_0x0047
            r2 = 4097(0x1001, float:5.741E-42)
            byte[] r2 = new byte[r2]
            r0.h = r2
        L_0x0047:
            int r2 = r28.h()
            r4 = 1
            int r5 = r4 << r2
            int r6 = r5 + 1
            int r7 = r5 + 2
            int r2 = r2 + r4
            int r8 = r4 << r2
            int r8 = r8 - r4
            r9 = 0
            r10 = r9
        L_0x0058:
            if (r10 >= r5) goto L_0x0066
            short[] r11 = r0.f
            r11[r10] = r9
            byte[] r11 = r0.g
            byte r12 = (byte) r10
            r11[r10] = r12
            int r10 = r10 + 1
            goto L_0x0058
        L_0x0066:
            r10 = -1
            r21 = r2
            r19 = r7
            r20 = r8
            r11 = r9
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r16 = r15
            r17 = r16
            r18 = r17
            r22 = r10
        L_0x007a:
            if (r11 >= r1) goto L_0x0177
            r9 = 3
            if (r12 != 0) goto L_0x008a
            int r12 = r28.i()
            if (r12 > 0) goto L_0x0089
            r0.q = r9
            goto L_0x0177
        L_0x0089:
            r15 = 0
        L_0x008a:
            byte[] r3 = r0.e
            byte r3 = r3[r15]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << r16
            int r14 = r14 + r3
            int r16 = r16 + 8
            int r15 = r15 + r4
            int r12 = r12 + r10
            r3 = r16
            r23 = r17
            r4 = r22
            r16 = r11
            r17 = r13
            r13 = r19
            r11 = r21
        L_0x00a5:
            if (r3 < r11) goto L_0x015b
            r10 = r14 & r20
            int r14 = r14 >> r11
            int r3 = r3 - r11
            if (r10 != r5) goto L_0x00b4
            r11 = r2
            r13 = r7
            r20 = r8
            r4 = -1
        L_0x00b2:
            r10 = -1
            goto L_0x00a5
        L_0x00b4:
            if (r10 <= r13) goto L_0x00b9
            r0.q = r9
            goto L_0x00bb
        L_0x00b9:
            if (r10 != r6) goto L_0x00ce
        L_0x00bb:
            r22 = r4
            r21 = r11
            r19 = r13
            r11 = r16
            r13 = r17
            r17 = r23
            r4 = 1
            r9 = 0
            r10 = -1
            r16 = r3
            goto L_0x0173
        L_0x00ce:
            r9 = -1
            if (r4 != r9) goto L_0x00e2
            byte[] r4 = r0.h
            int r19 = r18 + 1
            byte[] r9 = r0.g
            byte r9 = r9[r10]
            r4[r18] = r9
            r4 = r10
            r23 = r4
            r18 = r19
        L_0x00e0:
            r9 = 3
            goto L_0x00b2
        L_0x00e2:
            if (r10 < r13) goto L_0x00f3
            byte[] r9 = r0.h
            int r19 = r18 + 1
            r24 = r2
            r2 = r23
            byte r2 = (byte) r2
            r9[r18] = r2
            r2 = r4
            r18 = r19
            goto L_0x00f6
        L_0x00f3:
            r24 = r2
            r2 = r10
        L_0x00f6:
            if (r2 < r5) goto L_0x010d
            byte[] r9 = r0.h
            int r19 = r18 + 1
            r25 = r3
            byte[] r3 = r0.g
            byte r3 = r3[r2]
            r9[r18] = r3
            short[] r3 = r0.f
            short r2 = r3[r2]
            r18 = r19
            r3 = r25
            goto L_0x00f6
        L_0x010d:
            r25 = r3
            byte[] r3 = r0.g
            byte r2 = r3[r2]
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte[] r3 = r0.h
            int r9 = r18 + 1
            r26 = r5
            byte r5 = (byte) r2
            r3[r18] = r5
            r3 = 4096(0x1000, float:5.74E-42)
            if (r13 >= r3) goto L_0x013c
            short[] r3 = r0.f
            short r4 = (short) r4
            r3[r13] = r4
            byte[] r3 = r0.g
            r3[r13] = r5
            int r13 = r13 + 1
            r3 = r13 & r20
            if (r3 != 0) goto L_0x013a
            r3 = 4096(0x1000, float:5.74E-42)
            if (r13 >= r3) goto L_0x013c
            int r11 = r11 + 1
            int r20 = r20 + r13
            goto L_0x013c
        L_0x013a:
            r3 = 4096(0x1000, float:5.74E-42)
        L_0x013c:
            r18 = r9
        L_0x013e:
            if (r18 <= 0) goto L_0x0151
            int r18 = r18 + -1
            byte[] r4 = r0.i
            int r5 = r17 + 1
            byte[] r9 = r0.h
            byte r9 = r9[r18]
            r4[r17] = r9
            int r16 = r16 + 1
            r17 = r5
            goto L_0x013e
        L_0x0151:
            r23 = r2
            r4 = r10
            r2 = r24
            r3 = r25
            r5 = r26
            goto L_0x00e0
        L_0x015b:
            r24 = r2
            r26 = r5
            r2 = r23
            r22 = r4
            r21 = r11
            r19 = r13
            r11 = r16
            r13 = r17
            r4 = 1
            r9 = 0
            r17 = r2
            r16 = r3
            r2 = r24
        L_0x0173:
            r3 = 4096(0x1000, float:5.74E-42)
            goto L_0x007a
        L_0x0177:
            if (r13 >= r1) goto L_0x0181
            byte[] r2 = r0.i
            r3 = 0
            r2[r13] = r3
            int r13 = r13 + 1
            goto L_0x0177
        L_0x0181:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.b.a.a(com.bumptech.glide.b.b):void");
    }

    private int h() {
        try {
            return this.d.get() & 255;
        } catch (Exception unused) {
            this.q = 1;
            return 0;
        }
    }

    private int i() {
        int h2 = h();
        int i2 = 0;
        if (h2 > 0) {
            while (i2 < h2) {
                int i3 = h2 - i2;
                try {
                    this.d.get(this.e, i2, i3);
                    i2 += i3;
                } catch (Exception e2) {
                    Log.w(a, "Error Reading Block", e2);
                    this.q = 1;
                }
            }
        }
        return i2;
    }

    private Bitmap j() {
        Bitmap a2 = this.n.a(this.m.f, this.m.g, b);
        if (a2 == null) {
            a2 = Bitmap.createBitmap(this.m.f, this.m.g, b);
        }
        a(a2);
        return a2;
    }

    @TargetApi(12)
    private static void a(Bitmap bitmap) {
        if (VERSION.SDK_INT >= 12) {
            bitmap.setHasAlpha(true);
        }
    }
}
