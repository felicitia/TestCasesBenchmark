package com.facebook.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ImageDownloader */
public class n {
    private static Handler a;
    private static ab b = new ab(8);
    private static ab c = new ab(2);
    private static final Map<d, c> d = new HashMap();

    /* compiled from: ImageDownloader */
    private static class a implements Runnable {
        private Context a;
        private d b;
        private boolean c;

        a(Context context, d dVar, boolean z) {
            this.a = context;
            this.b = dVar;
            this.c = z;
        }

        public void run() {
            n.b(this.b, this.a, this.c);
        }
    }

    /* compiled from: ImageDownloader */
    private static class b implements Runnable {
        private Context a;
        private d b;

        b(Context context, d dVar) {
            this.a = context;
            this.b = dVar;
        }

        public void run() {
            n.b(this.b, this.a);
        }
    }

    /* compiled from: ImageDownloader */
    private static class c {
        com.facebook.internal.ab.a a;
        o b;
        boolean c;

        private c() {
        }
    }

    /* compiled from: ImageDownloader */
    private static class d {
        Uri a;
        Object b;

        d(Uri uri, Object obj) {
            this.a = uri;
            this.b = obj;
        }

        public int hashCode() {
            return ((1073 + this.a.hashCode()) * 37) + this.b.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof d)) {
                return false;
            }
            d dVar = (d) obj;
            if (dVar.a == this.a && dVar.b == this.b) {
                return true;
            }
            return false;
        }
    }

    public static void a(o oVar) {
        if (oVar != null) {
            d dVar = new d(oVar.b(), oVar.e());
            synchronized (d) {
                c cVar = (c) d.get(dVar);
                if (cVar != null) {
                    cVar.b = oVar;
                    cVar.c = false;
                    cVar.a.b();
                } else {
                    a(oVar, dVar, oVar.d());
                }
            }
        }
    }

    public static boolean b(o oVar) {
        boolean z;
        d dVar = new d(oVar.b(), oVar.e());
        synchronized (d) {
            c cVar = (c) d.get(dVar);
            z = true;
            if (cVar == null) {
                z = false;
            } else if (cVar.a.a()) {
                d.remove(dVar);
            } else {
                cVar.c = true;
            }
        }
        return z;
    }

    private static void a(o oVar, d dVar, boolean z) {
        a(oVar, dVar, c, (Runnable) new a(oVar.a(), dVar, z));
    }

    private static void a(o oVar, d dVar) {
        a(oVar, dVar, b, (Runnable) new b(oVar.a(), dVar));
    }

    private static void a(o oVar, d dVar, ab abVar, Runnable runnable) {
        synchronized (d) {
            c cVar = new c();
            cVar.b = oVar;
            d.put(dVar, cVar);
            cVar.a = abVar.a(runnable);
        }
    }

    private static void a(d dVar, Exception exc, Bitmap bitmap, boolean z) {
        c a2 = a(dVar);
        if (a2 != null && !a2.c) {
            final o oVar = a2.b;
            final com.facebook.internal.o.b c2 = oVar.c();
            if (c2 != null) {
                Handler a3 = a();
                final Exception exc2 = exc;
                final boolean z2 = z;
                final Bitmap bitmap2 = bitmap;
                AnonymousClass1 r1 = new Runnable() {
                    public void run() {
                        c2.a(new p(oVar, exc2, z2, bitmap2));
                    }
                };
                a3.post(r1);
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x001f  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(com.facebook.internal.n.d r2, android.content.Context r3, boolean r4) {
        /*
            r0 = 0
            r1 = 0
            if (r4 == 0) goto L_0x0014
            android.net.Uri r4 = r2.a
            android.net.Uri r4 = com.facebook.internal.y.a(r4)
            if (r4 == 0) goto L_0x0014
            java.io.InputStream r4 = com.facebook.internal.q.a(r4, r3)
            if (r4 == 0) goto L_0x0015
            r0 = 1
            goto L_0x0015
        L_0x0014:
            r4 = r1
        L_0x0015:
            if (r0 != 0) goto L_0x001d
            android.net.Uri r4 = r2.a
            java.io.InputStream r4 = com.facebook.internal.q.a(r4, r3)
        L_0x001d:
            if (r4 == 0) goto L_0x002a
            android.graphics.Bitmap r3 = android.graphics.BitmapFactory.decodeStream(r4)
            com.facebook.internal.z.a(r4)
            a(r2, r1, r3, r0)
            goto L_0x0039
        L_0x002a:
            com.facebook.internal.n$c r3 = a(r2)
            if (r3 == 0) goto L_0x0039
            boolean r4 = r3.c
            if (r4 != 0) goto L_0x0039
            com.facebook.internal.o r3 = r3.b
            a(r3, r2)
        L_0x0039:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.n.b(com.facebook.internal.n$d, android.content.Context, boolean):void");
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Exception] */
    /* JADX WARNING: type inference failed for: r10v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r4v1 */
    /* JADX WARNING: type inference failed for: r8v0 */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r4v3, types: [java.io.IOException] */
    /* JADX WARNING: type inference failed for: r10v2 */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r4v6, types: [java.io.IOException] */
    /* JADX WARNING: type inference failed for: r10v3 */
    /* JADX WARNING: type inference failed for: r10v4, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r10v5, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r10v6 */
    /* JADX WARNING: type inference failed for: r4v10, types: [java.io.IOException] */
    /* JADX WARNING: type inference failed for: r10v7 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: type inference failed for: r4v11, types: [java.io.IOException] */
    /* JADX WARNING: type inference failed for: r10v9 */
    /* JADX WARNING: type inference failed for: r10v11 */
    /* JADX WARNING: type inference failed for: r10v13, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r5v2, types: [com.facebook.FacebookException] */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: type inference failed for: r4v18 */
    /* JADX WARNING: type inference failed for: r4v19 */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: type inference failed for: r10v14 */
    /* JADX WARNING: type inference failed for: r10v15 */
    /* JADX WARNING: type inference failed for: r10v16 */
    /* JADX WARNING: type inference failed for: r10v17 */
    /* JADX WARNING: type inference failed for: r4v20 */
    /* JADX WARNING: type inference failed for: r4v21 */
    /* JADX WARNING: type inference failed for: r10v18 */
    /* JADX WARNING: type inference failed for: r10v19 */
    /* JADX WARNING: type inference failed for: r10v20 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x008a, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008b, code lost:
        r10 = 0;
        r2 = false;
        r4 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009d, code lost:
        r9 = th;
        r0 = r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v4
      assigns: []
      uses: []
      mth insns count: 96
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
    /* JADX WARNING: Removed duplicated region for block: B:39:0x009d A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0014] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 17 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(com.facebook.internal.n.d r9, android.content.Context r10) {
        /*
            r0 = 0
            r1 = 0
            r2 = 1
            java.net.URL r3 = new java.net.URL     // Catch:{ IOException -> 0x00ab, all -> 0x00a2 }
            android.net.Uri r4 = r9.a     // Catch:{ IOException -> 0x00ab, all -> 0x00a2 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x00ab, all -> 0x00a2 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x00ab, all -> 0x00a2 }
            java.net.URLConnection r3 = r3.openConnection()     // Catch:{ IOException -> 0x00ab, all -> 0x00a2 }
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ IOException -> 0x00ab, all -> 0x00a2 }
            r3.setInstanceFollowRedirects(r1)     // Catch:{ IOException -> 0x009f, all -> 0x009d }
            int r4 = r3.getResponseCode()     // Catch:{ IOException -> 0x009f, all -> 0x009d }
            r5 = 200(0xc8, float:2.8E-43)
            if (r4 == r5) goto L_0x008e
            switch(r4) {
                case 301: goto L_0x005b;
                case 302: goto L_0x005b;
                default: goto L_0x0022;
            }     // Catch:{ IOException -> 0x009f, all -> 0x009d }
        L_0x0022:
            java.io.InputStream r10 = r3.getErrorStream()     // Catch:{ IOException -> 0x009f, all -> 0x009d }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0059, all -> 0x0056 }
            r4.<init>()     // Catch:{ IOException -> 0x0059, all -> 0x0056 }
            if (r10 == 0) goto L_0x0045
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0059, all -> 0x0056 }
            r5.<init>(r10)     // Catch:{ IOException -> 0x0059, all -> 0x0056 }
            r6 = 128(0x80, float:1.794E-43)
            char[] r6 = new char[r6]     // Catch:{ IOException -> 0x0059, all -> 0x0056 }
        L_0x0036:
            int r7 = r6.length     // Catch:{ IOException -> 0x0059, all -> 0x0056 }
            int r7 = r5.read(r6, r1, r7)     // Catch:{ IOException -> 0x0059, all -> 0x0056 }
            if (r7 <= 0) goto L_0x0041
            r4.append(r6, r1, r7)     // Catch:{ IOException -> 0x0059, all -> 0x0056 }
            goto L_0x0036
        L_0x0041:
            com.facebook.internal.z.a(r5)     // Catch:{ IOException -> 0x0059, all -> 0x0056 }
            goto L_0x004a
        L_0x0045:
            java.lang.String r5 = "Unexpected error while downloading an image."
            r4.append(r5)     // Catch:{ IOException -> 0x0059, all -> 0x0056 }
        L_0x004a:
            com.facebook.FacebookException r5 = new com.facebook.FacebookException     // Catch:{ IOException -> 0x0059, all -> 0x0056 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x0059, all -> 0x0056 }
            r5.<init>(r4)     // Catch:{ IOException -> 0x0059, all -> 0x0056 }
            r4 = r0
            r0 = r5
            goto L_0x0096
        L_0x0056:
            r9 = move-exception
            r0 = r10
            goto L_0x00a4
        L_0x0059:
            r4 = move-exception
            goto L_0x00ae
        L_0x005b:
            java.lang.String r10 = "location"
            java.lang.String r10 = r3.getHeaderField(r10)     // Catch:{ IOException -> 0x008a, all -> 0x009d }
            boolean r2 = com.facebook.internal.z.a(r10)     // Catch:{ IOException -> 0x008a, all -> 0x009d }
            if (r2 != 0) goto L_0x0086
            android.net.Uri r10 = android.net.Uri.parse(r10)     // Catch:{ IOException -> 0x008a, all -> 0x009d }
            android.net.Uri r2 = r9.a     // Catch:{ IOException -> 0x008a, all -> 0x009d }
            com.facebook.internal.y.a(r2, r10)     // Catch:{ IOException -> 0x008a, all -> 0x009d }
            com.facebook.internal.n$c r2 = a(r9)     // Catch:{ IOException -> 0x008a, all -> 0x009d }
            if (r2 == 0) goto L_0x0086
            boolean r4 = r2.c     // Catch:{ IOException -> 0x008a, all -> 0x009d }
            if (r4 != 0) goto L_0x0086
            com.facebook.internal.o r2 = r2.b     // Catch:{ IOException -> 0x008a, all -> 0x009d }
            com.facebook.internal.n$d r4 = new com.facebook.internal.n$d     // Catch:{ IOException -> 0x008a, all -> 0x009d }
            java.lang.Object r5 = r9.b     // Catch:{ IOException -> 0x008a, all -> 0x009d }
            r4.<init>(r10, r5)     // Catch:{ IOException -> 0x008a, all -> 0x009d }
            a(r2, r4, r1)     // Catch:{ IOException -> 0x008a, all -> 0x009d }
        L_0x0086:
            r10 = r0
            r4 = r10
            r2 = r1
            goto L_0x0096
        L_0x008a:
            r4 = move-exception
            r10 = r0
            r2 = r1
            goto L_0x00ae
        L_0x008e:
            java.io.InputStream r10 = com.facebook.internal.q.a(r10, r3)     // Catch:{ IOException -> 0x009f, all -> 0x009d }
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeStream(r10)     // Catch:{ IOException -> 0x0059, all -> 0x0056 }
        L_0x0096:
            com.facebook.internal.z.a(r10)
            com.facebook.internal.z.a(r3)
            goto L_0x00b7
        L_0x009d:
            r9 = move-exception
            goto L_0x00a4
        L_0x009f:
            r4 = move-exception
            r10 = r0
            goto L_0x00ae
        L_0x00a2:
            r9 = move-exception
            r3 = r0
        L_0x00a4:
            com.facebook.internal.z.a(r0)
            com.facebook.internal.z.a(r3)
            throw r9
        L_0x00ab:
            r4 = move-exception
            r10 = r0
            r3 = r10
        L_0x00ae:
            com.facebook.internal.z.a(r10)
            com.facebook.internal.z.a(r3)
            r8 = r4
            r4 = r0
            r0 = r8
        L_0x00b7:
            if (r2 == 0) goto L_0x00bc
            a(r9, r0, r4, r1)
        L_0x00bc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.n.b(com.facebook.internal.n$d, android.content.Context):void");
    }

    private static synchronized Handler a() {
        Handler handler;
        synchronized (n.class) {
            if (a == null) {
                a = new Handler(Looper.getMainLooper());
            }
            handler = a;
        }
        return handler;
    }

    private static c a(d dVar) {
        c cVar;
        synchronized (d) {
            cVar = (c) d.remove(dVar);
        }
        return cVar;
    }
}
