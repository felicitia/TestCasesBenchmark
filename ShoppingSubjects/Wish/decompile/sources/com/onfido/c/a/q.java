package com.onfido.c.a;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.JsonWriter;
import com.onfido.c.a.a.d;
import com.onfido.c.a.a.e;
import com.onfido.c.a.a.f;
import com.onfido.c.a.a.g;
import com.onfido.c.a.a.h;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class q extends e<Void> {
    static final com.onfido.c.a.a.e.a a = new com.onfido.c.a.a.e.a() {
        public e<?> a(t tVar, a aVar) {
            return q.a(aVar.c(), aVar.k, aVar.l, aVar.e, aVar.f, Collections.unmodifiableMap(aVar.s), aVar.j, aVar.r, aVar.q, aVar.d(), aVar.m);
        }

        public String a() {
            return "Segment.io";
        }
    };
    static final Charset b = Charset.forName("UTF-8");
    final Object c = new Object();
    private final Context d;
    private final l e;
    private final e f;
    private final int g;
    private final r h;
    private final Handler i;
    private final HandlerThread j;
    private final f k;
    private final Map<String, Boolean> l;
    private final d m;
    private final ExecutorService n;
    private final ScheduledExecutorService o;
    private final g p;

    static class a implements Closeable {
        private final JsonWriter a;
        private final BufferedWriter b;
        private boolean c = false;

        a(OutputStream outputStream) {
            this.b = new BufferedWriter(new OutputStreamWriter(outputStream));
            this.a = new JsonWriter(this.b);
        }

        /* access modifiers changed from: 0000 */
        public a a() {
            this.a.beginObject();
            return this;
        }

        /* access modifiers changed from: 0000 */
        public a a(String str) {
            if (this.c) {
                this.b.write(44);
            } else {
                this.c = true;
            }
            this.b.write(str);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public a b() {
            this.a.name("batch").beginArray();
            this.c = false;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public a c() {
            if (!this.c) {
                throw new IOException("At least one payload must be provided.");
            }
            this.a.endArray();
            return this;
        }

        public void close() {
            this.a.close();
        }

        /* access modifiers changed from: 0000 */
        public a d() {
            this.a.name("sentAt").value(com.onfido.c.a.b.b.a(new Date())).endObject();
            return this;
        }
    }

    static class b implements a {
        final a a;
        final g b;
        int c;
        int d;

        b(a aVar, g gVar) {
            this.a = aVar;
            this.b = gVar;
        }

        public boolean a(InputStream inputStream, int i) {
            InputStream a2 = this.b.a(inputStream);
            int i2 = this.c + i;
            if (i2 > 475000) {
                return false;
            }
            this.c = i2;
            byte[] bArr = new byte[i];
            a2.read(bArr, 0, i);
            this.a.a(new String(bArr, q.b));
            this.d++;
            return true;
        }
    }

    static class c extends Handler {
        private final q a;

        c(Looper looper, q qVar) {
            super(looper);
            this.a = qVar;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.a((com.onfido.c.a.a.b) message.obj);
                    return;
                case 1:
                    this.a.b();
                    return;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unknown dispatcher message: ");
                    sb.append(message.what);
                    throw new AssertionError(sb.toString());
            }
        }
    }

    q(Context context, e eVar, d dVar, ExecutorService executorService, l lVar, r rVar, Map<String, Boolean> map, long j2, int i2, f fVar, g gVar) {
        int i3 = i2;
        this.d = context;
        this.f = eVar;
        this.n = executorService;
        l lVar2 = lVar;
        this.e = lVar2;
        this.h = rVar;
        this.k = fVar;
        this.l = map;
        this.m = dVar;
        this.g = i3;
        this.o = Executors.newScheduledThreadPool(1, new com.onfido.c.a.b.b.c());
        this.p = gVar;
        this.j = new HandlerThread("Segment-SegmentDispatcher", 10);
        this.j.start();
        this.i = new c(this.j.getLooper(), this);
        this.o.scheduleAtFixedRate(new Runnable() {
            public void run() {
                q.this.a();
            }
        }, lVar2.a() >= i3 ? 0 : j2, j2, TimeUnit.MILLISECONDS);
    }

    static o a(File file, String str) {
        com.onfido.c.a.b.b.a(file);
        File file2 = new File(file, str);
        try {
            return new o(file2);
        } catch (IOException unused) {
            if (file2.delete()) {
                return new o(file2);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Could not create queue file (");
            sb.append(str);
            sb.append(") in ");
            sb.append(file);
            sb.append(".");
            throw new IOException(sb.toString());
        }
    }

    /* JADX INFO: finally extract failed */
    static synchronized q a(Context context, e eVar, d dVar, ExecutorService executorService, r rVar, Map<String, Boolean> map, String str, long j2, int i2, f fVar, g gVar) {
        f fVar2;
        l lVar;
        q qVar;
        synchronized (q.class) {
            Context context2 = context;
            try {
                fVar2 = fVar;
                lVar = new c(a(context2.getDir("segment-disk-queue", 0), str));
            } catch (IOException e2) {
                fVar2 = fVar;
                fVar2.a(e2, "Could not create disk queue. Falling back to memory queue.", new Object[0]);
                lVar = new b();
            } catch (Throwable th) {
                throw th;
            }
            qVar = new q(context2, eVar, dVar, executorService, lVar, rVar, map, j2, i2, fVar2, gVar);
        }
        return qVar;
    }

    private void b(com.onfido.c.a.a.b bVar) {
        this.i.sendMessage(this.i.obtainMessage(0, bVar));
    }

    private boolean e() {
        return this.e.a() > 0 && com.onfido.c.a.b.b.b(this.d);
    }

    public void a() {
        this.i.sendMessage(this.i.obtainMessage(1));
    }

    public void a(com.onfido.c.a.a.a aVar) {
        b(aVar);
    }

    /* access modifiers changed from: 0000 */
    public void a(com.onfido.c.a.a.b bVar) {
        t d2 = bVar.d();
        LinkedHashMap linkedHashMap = new LinkedHashMap(d2.size() + this.l.size());
        linkedHashMap.putAll(d2);
        linkedHashMap.putAll(this.l);
        linkedHashMap.remove("Segment.io");
        t tVar = new t();
        tVar.putAll(bVar);
        tVar.put("integrations", linkedHashMap);
        if (this.e.a() >= 1000) {
            synchronized (this.c) {
                if (this.e.a() >= 1000) {
                    this.k.b("Queue is at max capacity (%s), removing oldest payload.", Integer.valueOf(this.e.a()));
                    try {
                        this.e.a(1);
                    } catch (IOException e2) {
                        this.k.a(e2, "Unable to remove oldest payload from queue.", new Object[0]);
                        return;
                    }
                }
            }
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            this.m.a((Map<?, ?>) tVar, (Writer) new OutputStreamWriter(this.p.a((OutputStream) byteArrayOutputStream)));
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (!(byteArray == null || byteArray.length == 0)) {
                if (byteArray.length <= 15000) {
                    this.e.a(byteArray);
                    this.k.a("Enqueued %s payload. %s elements in the queue.", bVar, Integer.valueOf(this.e.a()));
                    if (this.e.a() >= this.g) {
                        b();
                    }
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Could not serialize payload ");
            sb.append(tVar);
            throw new IOException(sb.toString());
        } catch (IOException e3) {
            this.k.a(e3, "Could not add payload %s to queue: %s.", tVar, this.e);
        }
    }

    public void a(com.onfido.c.a.a.c cVar) {
        b(cVar);
    }

    public void a(d dVar) {
        b(dVar);
    }

    public void a(g gVar) {
        b(gVar);
    }

    public void a(h hVar) {
        b(hVar);
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        if (e()) {
            this.n.submit(new Runnable() {
                public void run() {
                    synchronized (q.this.c) {
                        q.this.c();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0098, code lost:
        r3 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0099, code lost:
        r7 = r1;
        r1 = r0;
        r0 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x009d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:44:0x00d7 */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x009d A[ExcHandler: IOException (e java.io.IOException), Splitter:B:6:0x0018] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c2 A[Catch:{ all -> 0x00a2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c() {
        /*
            r8 = this;
            boolean r0 = r8.e()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            com.onfido.c.a.a.f r0 = r8.k
            java.lang.String r1 = "Uploading payloads in queue to Segment."
            r2 = 0
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r0.a(r1, r3)
            r0 = 0
            com.onfido.c.a.e r1 = r8.f     // Catch:{ b -> 0x00ba, IOException -> 0x00a4 }
            com.onfido.c.a.e$a r1 = r1.a()     // Catch:{ b -> 0x00ba, IOException -> 0x00a4 }
            com.onfido.c.a.q$a r0 = new com.onfido.c.a.q$a     // Catch:{ b -> 0x009f, IOException -> 0x009d }
            java.io.OutputStream r3 = r1.c     // Catch:{ b -> 0x009f, IOException -> 0x009d }
            r0.<init>(r3)     // Catch:{ b -> 0x009f, IOException -> 0x009d }
            com.onfido.c.a.q$a r0 = r0.a()     // Catch:{ b -> 0x009f, IOException -> 0x009d }
            com.onfido.c.a.q$a r0 = r0.b()     // Catch:{ b -> 0x009f, IOException -> 0x009d }
            com.onfido.c.a.q$b r3 = new com.onfido.c.a.q$b     // Catch:{ b -> 0x009f, IOException -> 0x009d }
            com.onfido.c.a.g r4 = r8.p     // Catch:{ b -> 0x009f, IOException -> 0x009d }
            r3.<init>(r0, r4)     // Catch:{ b -> 0x009f, IOException -> 0x009d }
            com.onfido.c.a.l r4 = r8.e     // Catch:{ b -> 0x009f, IOException -> 0x009d }
            r4.a(r3)     // Catch:{ b -> 0x009f, IOException -> 0x009d }
            com.onfido.c.a.q$a r0 = r0.c()     // Catch:{ b -> 0x009f, IOException -> 0x009d }
            com.onfido.c.a.q$a r0 = r0.d()     // Catch:{ b -> 0x009f, IOException -> 0x009d }
            r0.close()     // Catch:{ b -> 0x009f, IOException -> 0x009d }
            int r0 = r3.d     // Catch:{ b -> 0x009f, IOException -> 0x009d }
            r1.close()     // Catch:{ b -> 0x0098, IOException -> 0x009d }
            com.onfido.c.a.b.b.a(r1)
            com.onfido.c.a.l r1 = r8.e     // Catch:{ IOException -> 0x0079 }
            r1.a(r0)     // Catch:{ IOException -> 0x0079 }
            com.onfido.c.a.a.f r1 = r8.k
            java.lang.String r3 = "Uploaded %s payloads. %s remain in the queue."
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
            r4[r2] = r5
            r2 = 1
            com.onfido.c.a.l r5 = r8.e
            int r5 = r5.a()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r4[r2] = r5
            r1.a(r3, r4)
            com.onfido.c.a.r r1 = r8.h
            r1.a(r0)
            com.onfido.c.a.l r0 = r8.e
            int r0 = r0.a()
            if (r0 <= 0) goto L_0x0078
            r8.c()
        L_0x0078:
            return
        L_0x0079:
            r1 = move-exception
            com.onfido.c.a.a.f r3 = r8.k
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Unable to remove "
            r4.append(r5)
            r4.append(r0)
            java.lang.String r0 = " payload(s) from queue."
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3.a(r1, r0, r2)
            return
        L_0x0098:
            r3 = move-exception
            r7 = r1
            r1 = r0
            r0 = r7
            goto L_0x00bc
        L_0x009d:
            r0 = move-exception
            goto L_0x00a8
        L_0x009f:
            r3 = move-exception
            r0 = r1
            goto L_0x00bb
        L_0x00a2:
            r1 = move-exception
            goto L_0x0105
        L_0x00a4:
            r1 = move-exception
            r7 = r1
            r1 = r0
            r0 = r7
        L_0x00a8:
            com.onfido.c.a.a.f r3 = r8.k     // Catch:{ all -> 0x00b5 }
            java.lang.String r4 = "Error while uploading payloads"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x00b5 }
            r3.a(r0, r4, r2)     // Catch:{ all -> 0x00b5 }
            com.onfido.c.a.b.b.a(r1)
            return
        L_0x00b5:
            r0 = move-exception
            r7 = r1
            r1 = r0
            r0 = r7
            goto L_0x0105
        L_0x00ba:
            r3 = move-exception
        L_0x00bb:
            r1 = 0
        L_0x00bc:
            int r4 = r3.a     // Catch:{ all -> 0x00a2 }
            r5 = 400(0x190, float:5.6E-43)
            if (r4 < r5) goto L_0x00f8
            int r4 = r3.a     // Catch:{ all -> 0x00a2 }
            r5 = 500(0x1f4, float:7.0E-43)
            if (r4 >= r5) goto L_0x00f8
            com.onfido.c.a.a.f r4 = r8.k     // Catch:{ all -> 0x00a2 }
            java.lang.String r5 = "Payloads were rejected by server. Marked for removal."
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ all -> 0x00a2 }
            r4.a(r3, r5, r6)     // Catch:{ all -> 0x00a2 }
            com.onfido.c.a.l r4 = r8.e     // Catch:{ IOException -> 0x00d7 }
            r4.a(r1)     // Catch:{ IOException -> 0x00d7 }
            goto L_0x00f4
        L_0x00d7:
            com.onfido.c.a.a.f r4 = r8.k     // Catch:{ all -> 0x00a2 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a2 }
            r5.<init>()     // Catch:{ all -> 0x00a2 }
            java.lang.String r6 = "Unable to remove "
            r5.append(r6)     // Catch:{ all -> 0x00a2 }
            r5.append(r1)     // Catch:{ all -> 0x00a2 }
            java.lang.String r1 = " payload(s) from queue."
            r5.append(r1)     // Catch:{ all -> 0x00a2 }
            java.lang.String r1 = r5.toString()     // Catch:{ all -> 0x00a2 }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x00a2 }
            r4.a(r3, r1, r2)     // Catch:{ all -> 0x00a2 }
        L_0x00f4:
            com.onfido.c.a.b.b.a(r0)
            return
        L_0x00f8:
            com.onfido.c.a.a.f r1 = r8.k     // Catch:{ all -> 0x00a2 }
            java.lang.String r4 = "Error while uploading payloads"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x00a2 }
            r1.a(r3, r4, r2)     // Catch:{ all -> 0x00a2 }
            com.onfido.c.a.b.b.a(r0)
            return
        L_0x0105:
            com.onfido.c.a.b.b.a(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.c.a.q.c():void");
    }
}
