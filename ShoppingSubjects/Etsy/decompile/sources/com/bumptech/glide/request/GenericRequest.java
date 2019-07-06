package com.bumptech.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.b;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.b.c;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.f;
import com.bumptech.glide.request.a.d;
import com.bumptech.glide.request.target.h;
import java.util.Queue;
import org.apache.commons.math3.geometry.VectorFormat;

public final class GenericRequest<A, T, Z, R> implements a, d, h {
    private static final Queue<GenericRequest<?, ?, ?, ?>> a = com.bumptech.glide.g.h.a(0);
    private i<?> A;
    private c B;
    private long C;
    private Status D;
    private final String b = String.valueOf(hashCode());
    private b c;
    private Drawable d;
    private int e;
    private int f;
    private int g;
    private Context h;
    private f<Z> i;
    private com.bumptech.glide.e.f<A, T, Z, R> j;
    private b k;
    private A l;
    private Class<R> m;
    private boolean n;
    private Priority o;
    private com.bumptech.glide.request.target.i<R> p;
    private c<? super A, R> q;
    private float r;
    private com.bumptech.glide.load.engine.b s;
    private d<R> t;
    private int u;
    private int v;
    private DiskCacheStrategy w;
    private Drawable x;
    private Drawable y;
    private boolean z;

    private enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CANCELLED,
        CLEARED,
        PAUSED
    }

    public static <A, T, Z, R> GenericRequest<A, T, Z, R> a(com.bumptech.glide.e.f<A, T, Z, R> fVar, A a2, b bVar, Context context, Priority priority, com.bumptech.glide.request.target.i<R> iVar, float f2, Drawable drawable, int i2, Drawable drawable2, int i3, Drawable drawable3, int i4, c<? super A, R> cVar, b bVar2, com.bumptech.glide.load.engine.b bVar3, f<Z> fVar2, Class<R> cls, boolean z2, d<R> dVar, int i5, int i6, DiskCacheStrategy diskCacheStrategy) {
        GenericRequest<A, T, Z, R> genericRequest = (GenericRequest) a.poll();
        if (genericRequest == null) {
            genericRequest = new GenericRequest<>();
        }
        genericRequest.b(fVar, a2, bVar, context, priority, iVar, f2, drawable, i2, drawable2, i3, drawable3, i4, cVar, bVar2, bVar3, fVar2, cls, z2, dVar, i5, i6, diskCacheStrategy);
        return genericRequest;
    }

    private GenericRequest() {
    }

    public void a() {
        this.j = null;
        this.l = null;
        this.h = null;
        this.p = null;
        this.x = null;
        this.y = null;
        this.d = null;
        this.q = null;
        this.k = null;
        this.i = null;
        this.t = null;
        this.z = false;
        this.B = null;
        a.offer(this);
    }

    private void b(com.bumptech.glide.e.f<A, T, Z, R> fVar, A a2, b bVar, Context context, Priority priority, com.bumptech.glide.request.target.i<R> iVar, float f2, Drawable drawable, int i2, Drawable drawable2, int i3, Drawable drawable3, int i4, c<? super A, R> cVar, b bVar2, com.bumptech.glide.load.engine.b bVar3, f<Z> fVar2, Class<R> cls, boolean z2, d<R> dVar, int i5, int i6, DiskCacheStrategy diskCacheStrategy) {
        A a3 = a2;
        f<Z> fVar3 = fVar2;
        com.bumptech.glide.e.f<A, T, Z, R> fVar4 = fVar;
        this.j = fVar4;
        this.l = a3;
        this.c = bVar;
        this.d = drawable3;
        this.e = i4;
        this.h = context.getApplicationContext();
        this.o = priority;
        this.p = iVar;
        this.r = f2;
        this.x = drawable;
        this.f = i2;
        this.y = drawable2;
        this.g = i3;
        this.q = cVar;
        this.k = bVar2;
        this.s = bVar3;
        this.i = fVar3;
        this.m = cls;
        this.n = z2;
        this.t = dVar;
        this.u = i5;
        this.v = i6;
        this.w = diskCacheStrategy;
        this.D = Status.PENDING;
        if (a3 != null) {
            a("ModelLoader", fVar4.e(), "try .using(ModelLoader)");
            a("Transcoder", fVar4.f(), "try .as*(Class).transcode(ResourceTranscoder)");
            a("Transformation", fVar3, "try .transform(UnitTransformation.get())");
            if (diskCacheStrategy.cacheSource()) {
                a("SourceEncoder", fVar4.c(), "try .sourceEncoder(Encoder) or .diskCacheStrategy(NONE/RESULT)");
            } else {
                a("SourceDecoder", fVar4.b(), "try .decoder/.imageDecoder/.videoDecoder(ResourceDecoder) or .diskCacheStrategy(ALL/SOURCE)");
            }
            if (diskCacheStrategy.cacheSource() || diskCacheStrategy.cacheResult()) {
                a("CacheDecoder", fVar4.a(), "try .cacheDecoder(ResouceDecoder) or .diskCacheStrategy(NONE)");
            }
            if (diskCacheStrategy.cacheResult()) {
                a("Encoder", fVar4.d(), "try .encode(ResourceEncoder) or .diskCacheStrategy(NONE/SOURCE)");
            }
        }
    }

    private static void a(String str, Object obj, String str2) {
        if (obj == null) {
            StringBuilder sb = new StringBuilder(str);
            sb.append(" must not be null");
            if (str2 != null) {
                sb.append(", ");
                sb.append(str2);
            }
            throw new NullPointerException(sb.toString());
        }
    }

    public void b() {
        this.C = com.bumptech.glide.g.d.a();
        if (this.l == null) {
            a((Exception) null);
            return;
        }
        this.D = Status.WAITING_FOR_SIZE;
        if (com.bumptech.glide.g.h.a(this.u, this.v)) {
            a(this.u, this.v);
        } else {
            this.p.a((h) this);
        }
        if (!g() && !j() && o()) {
            this.p.c(m());
        }
        if (Log.isLoggable("GenericRequest", 2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("finished run method in ");
            sb.append(com.bumptech.glide.g.d.a(this.C));
            a(sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        this.D = Status.CANCELLED;
        if (this.B != null) {
            this.B.a();
            this.B = null;
        }
    }

    public void d() {
        com.bumptech.glide.g.h.a();
        if (this.D != Status.CLEARED) {
            c();
            if (this.A != null) {
                b((i) this.A);
            }
            if (o()) {
                this.p.b(m());
            }
            this.D = Status.CLEARED;
        }
    }

    public void e() {
        d();
        this.D = Status.PAUSED;
    }

    private void b(i iVar) {
        this.s.a(iVar);
        this.A = null;
    }

    public boolean f() {
        return this.D == Status.RUNNING || this.D == Status.WAITING_FOR_SIZE;
    }

    public boolean g() {
        return this.D == Status.COMPLETE;
    }

    public boolean h() {
        return g();
    }

    public boolean i() {
        return this.D == Status.CANCELLED || this.D == Status.CLEARED;
    }

    public boolean j() {
        return this.D == Status.FAILED;
    }

    private Drawable k() {
        if (this.d == null && this.e > 0) {
            this.d = this.h.getResources().getDrawable(this.e);
        }
        return this.d;
    }

    private void b(Exception exc) {
        if (o()) {
            Drawable k2 = this.l == null ? k() : null;
            if (k2 == null) {
                k2 = l();
            }
            if (k2 == null) {
                k2 = m();
            }
            this.p.a(exc, k2);
        }
    }

    private Drawable l() {
        if (this.y == null && this.g > 0) {
            this.y = this.h.getResources().getDrawable(this.g);
        }
        return this.y;
    }

    private Drawable m() {
        if (this.x == null && this.f > 0) {
            this.x = this.h.getResources().getDrawable(this.f);
        }
        return this.x;
    }

    public void a(int i2, int i3) {
        if (Log.isLoggable("GenericRequest", 2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Got onSizeReady in ");
            sb.append(com.bumptech.glide.g.d.a(this.C));
            a(sb.toString());
        }
        if (this.D == Status.WAITING_FOR_SIZE) {
            this.D = Status.RUNNING;
            int round = Math.round(this.r * ((float) i2));
            int round2 = Math.round(this.r * ((float) i3));
            com.bumptech.glide.load.a.c a2 = this.j.e().a(this.l, round, round2);
            if (a2 == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Failed to load model: '");
                sb2.append(this.l);
                sb2.append("'");
                a(new Exception(sb2.toString()));
                return;
            }
            com.bumptech.glide.load.resource.c.c f2 = this.j.f();
            if (Log.isLoggable("GenericRequest", 2)) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("finished setup for calling load in ");
                sb3.append(com.bumptech.glide.g.d.a(this.C));
                a(sb3.toString());
            }
            boolean z2 = true;
            this.z = true;
            this.B = this.s.a(this.c, round, round2, a2, this.j, this.i, f2, this.o, this.n, this.w, this);
            if (this.A == null) {
                z2 = false;
            }
            this.z = z2;
            if (Log.isLoggable("GenericRequest", 2)) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("finished onSizeReady in ");
                sb4.append(com.bumptech.glide.g.d.a(this.C));
                a(sb4.toString());
            }
        }
    }

    private boolean n() {
        return this.k == null || this.k.a(this);
    }

    private boolean o() {
        return this.k == null || this.k.b(this);
    }

    private boolean p() {
        return this.k == null || !this.k.c();
    }

    private void q() {
        if (this.k != null) {
            this.k.c(this);
        }
    }

    public void a(i<?> iVar) {
        if (iVar == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected to receive a Resource<R> with an object of ");
            sb.append(this.m);
            sb.append(" inside, but instead got null.");
            a(new Exception(sb.toString()));
            return;
        }
        Object b2 = iVar.b();
        if (b2 == null || !this.m.isAssignableFrom(b2.getClass())) {
            b((i) iVar);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Expected to receive an object of ");
            sb2.append(this.m);
            sb2.append(" but instead got ");
            sb2.append(b2 != null ? b2.getClass() : "");
            sb2.append(VectorFormat.DEFAULT_PREFIX);
            sb2.append(b2);
            sb2.append(VectorFormat.DEFAULT_SUFFIX);
            sb2.append(" inside Resource{");
            sb2.append(iVar);
            sb2.append("}.");
            sb2.append(b2 != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data.");
            a(new Exception(sb2.toString()));
        } else if (!n()) {
            b((i) iVar);
            this.D = Status.COMPLETE;
        } else {
            a(iVar, (R) b2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x001c, code lost:
        if (r7.q.a(r9, r7.l, r7.p, r7.z, r6) == false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.bumptech.glide.load.engine.i<?> r8, R r9) {
        /*
            r7 = this;
            boolean r6 = r7.p()
            com.bumptech.glide.request.GenericRequest$Status r0 = com.bumptech.glide.request.GenericRequest.Status.COMPLETE
            r7.D = r0
            r7.A = r8
            com.bumptech.glide.request.c<? super A, R> r0 = r7.q
            if (r0 == 0) goto L_0x001e
            com.bumptech.glide.request.c<? super A, R> r0 = r7.q
            A r2 = r7.l
            com.bumptech.glide.request.target.i<R> r3 = r7.p
            boolean r4 = r7.z
            r1 = r9
            r5 = r6
            boolean r0 = r0.a(r1, r2, r3, r4, r5)
            if (r0 != 0) goto L_0x002b
        L_0x001e:
            com.bumptech.glide.request.a.d<R> r0 = r7.t
            boolean r1 = r7.z
            com.bumptech.glide.request.a.c r0 = r0.a(r1, r6)
            com.bumptech.glide.request.target.i<R> r1 = r7.p
            r1.a(r9, r0)
        L_0x002b:
            r7.q()
            java.lang.String r9 = "GenericRequest"
            r0 = 2
            boolean r9 = android.util.Log.isLoggable(r9, r0)
            if (r9 == 0) goto L_0x006b
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "Resource ready in "
            r9.append(r0)
            long r0 = r7.C
            double r0 = com.bumptech.glide.g.d.a(r0)
            r9.append(r0)
            java.lang.String r0 = " size: "
            r9.append(r0)
            int r8 = r8.c()
            double r0 = (double) r8
            r2 = 4517110426252607488(0x3eb0000000000000, double:9.5367431640625E-7)
            double r0 = r0 * r2
            r9.append(r0)
            java.lang.String r8 = " fromCache: "
            r9.append(r8)
            boolean r8 = r7.z
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            r7.a(r8)
        L_0x006b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.GenericRequest.a(com.bumptech.glide.load.engine.i, java.lang.Object):void");
    }

    public void a(Exception exc) {
        if (Log.isLoggable("GenericRequest", 3)) {
            Log.d("GenericRequest", "load failed", exc);
        }
        this.D = Status.FAILED;
        if (this.q == null || !this.q.a(exc, this.l, this.p, p())) {
            b(exc);
        }
    }

    private void a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" this: ");
        sb.append(this.b);
        Log.v("GenericRequest", sb.toString());
    }
}
