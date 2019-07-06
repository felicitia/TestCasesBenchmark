package com.apiguard;

import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: GA */
public class APIGuard {
    o a = new o();
    private AtomicBoolean b = new AtomicBoolean();

    /* compiled from: GA */
    public enum AG_HTTP_COMMAND {
        GET,
        HEAD,
        POST,
        PUT,
        DELETE
    }

    public APIGuard() {
        this.b.set(false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x001f A[SYNTHETIC, Splitter:B:13:0x001f] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initializeWithProperties(android.app.Application r8, java.util.Map<java.lang.String, java.lang.Object> r9, com.apiguard.AGCallbackInterface r10) {
        /*
            r7 = this;
            java.util.concurrent.atomic.AtomicBoolean r0 = r7.b
            boolean r0 = r0.get()
            if (r0 != 0) goto L_0x00a1
            r0 = 0
            r1 = 0
            r2 = 1
            java.lang.String r3 = defpackage.c.d     // Catch:{ NoSuchAlgorithmException -> 0x001b, Exception -> 0x0016 }
            javax.crypto.Mac r3 = javax.crypto.Mac.getInstance(r3)     // Catch:{ NoSuchAlgorithmException -> 0x001b, Exception -> 0x0016 }
            if (r3 != 0) goto L_0x0014
            goto L_0x001c
        L_0x0014:
            r0 = 0
            goto L_0x001d
        L_0x0016:
            java.lang.String r3 = "M100: "
            defpackage.al.b(r3)
        L_0x001b:
            r3 = r0
        L_0x001c:
            r0 = 1
        L_0x001d:
            if (r0 != 0) goto L_0x0052
            javax.crypto.spec.SecretKeySpec r4 = new javax.crypto.spec.SecretKeySpec     // Catch:{ InvalidKeyException -> 0x004b, Exception -> 0x0045 }
            java.lang.String r5 = defpackage.c.f     // Catch:{ InvalidKeyException -> 0x004b, Exception -> 0x0045 }
            byte[] r5 = r5.getBytes()     // Catch:{ InvalidKeyException -> 0x004b, Exception -> 0x0045 }
            java.lang.String r6 = defpackage.c.d     // Catch:{ InvalidKeyException -> 0x004b, Exception -> 0x0045 }
            r4.<init>(r5, r6)     // Catch:{ InvalidKeyException -> 0x004b, Exception -> 0x0045 }
            r3.init(r4)     // Catch:{ InvalidKeyException -> 0x004b, Exception -> 0x0045 }
            java.lang.String r4 = defpackage.c.g     // Catch:{ InvalidKeyException -> 0x004b, Exception -> 0x0045 }
            byte[] r4 = r4.getBytes()     // Catch:{ InvalidKeyException -> 0x004b, Exception -> 0x0045 }
            byte[] r3 = r3.doFinal(r4)     // Catch:{ InvalidKeyException -> 0x004b, Exception -> 0x0045 }
            java.lang.String r3 = defpackage.al.a(r3)     // Catch:{ InvalidKeyException -> 0x004b, Exception -> 0x0045 }
            java.lang.String r4 = defpackage.c.h     // Catch:{ InvalidKeyException -> 0x004b, Exception -> 0x0045 }
            boolean r3 = r3.equals(r4)     // Catch:{ InvalidKeyException -> 0x004b, Exception -> 0x0045 }
            r3 = r3 ^ r2
            goto L_0x0053
        L_0x0045:
            java.lang.String r3 = "M100: M29"
            defpackage.al.b(r3)
            goto L_0x0050
        L_0x004b:
            java.lang.String r3 = "M30"
            defpackage.al.b(r3)
        L_0x0050:
            r3 = 1
            goto L_0x0053
        L_0x0052:
            r3 = 0
        L_0x0053:
            if (r0 != 0) goto L_0x0057
            if (r3 == 0) goto L_0x0067
        L_0x0057:
            d r0 = new d
            r0.<init>()
            int r0 = java.security.Security.addProvider(r0)
            if (r0 >= 0) goto L_0x0067
            java.lang.String r0 = "M28"
            defpackage.al.b(r0)
        L_0x0067:
            if (r10 == 0) goto L_0x0074
            o r0 = r7.a
            android.content.Context r1 = r8.getApplicationContext()
            boolean r1 = r0.a(r1, r10, r9)
            goto L_0x008b
        L_0x0074:
            boolean r10 = r8 instanceof com.apiguard.AGCallbackInterface
            if (r10 == 0) goto L_0x0086
            o r10 = r7.a
            android.content.Context r0 = r8.getApplicationContext()
            r1 = r8
            com.apiguard.AGCallbackInterface r1 = (com.apiguard.AGCallbackInterface) r1
            boolean r1 = r10.a(r0, r1, r9)
            goto L_0x008b
        L_0x0086:
            java.lang.String r9 = "M4: "
            defpackage.al.b(r9)
        L_0x008b:
            if (r1 == 0) goto L_0x00a1
            a r9 = new a
            o r10 = r7.a
            r9.<init>(r10)
            r8.registerActivityLifecycleCallbacks(r9)
            java.lang.String r8 = "eA4RqrEO"
            java.lang.System.loadLibrary(r8)
            java.util.concurrent.atomic.AtomicBoolean r8 = r7.b
            r8.set(r2)
        L_0x00a1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apiguard.APIGuard.initializeWithProperties(android.app.Application, java.util.Map, com.apiguard.AGCallbackInterface):void");
    }

    public Request transformRequest(Request request) {
        if (!this.b.get()) {
            return request;
        }
        try {
            return (Request) new f(request, this.a).a();
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("M6: ");
            sb.append(e.getLocalizedMessage());
            al.b(sb.toString());
            return request;
        }
    }

    public Response transformResponse(Response response) {
        if (!this.b.get()) {
            return response;
        }
        try {
            return (Response) new g(response, this.a).a();
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("M7: ");
            sb.append(e.getLocalizedMessage());
            al.b(sb.toString());
            return response;
        }
    }
}
