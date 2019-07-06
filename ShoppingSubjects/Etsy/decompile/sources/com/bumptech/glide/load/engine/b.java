package com.bumptech.glide.load.engine;

import android.os.Looper;
import android.os.MessageQueue.IdleHandler;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.a.a.C0012a;
import com.bumptech.glide.load.engine.a.h;
import com.bumptech.glide.load.f;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* compiled from: Engine */
public class b implements com.bumptech.glide.load.engine.a.h.a, d, a {
    private final Map<com.bumptech.glide.load.b, c> a;
    private final f b;
    private final h c;
    private final a d;
    private final Map<com.bumptech.glide.load.b, WeakReference<g<?>>> e;
    private final j f;
    private final C0013b g;
    private ReferenceQueue<g<?>> h;

    /* compiled from: Engine */
    static class a {
        private final ExecutorService a;
        private final ExecutorService b;
        private final d c;

        public a(ExecutorService executorService, ExecutorService executorService2, d dVar) {
            this.a = executorService;
            this.b = executorService2;
            this.c = dVar;
        }

        public c a(com.bumptech.glide.load.b bVar, boolean z) {
            c cVar = new c(bVar, this.a, this.b, z, this.c);
            return cVar;
        }
    }

    /* renamed from: com.bumptech.glide.load.engine.b$b reason: collision with other inner class name */
    /* compiled from: Engine */
    private static class C0013b implements C0011a {
        private final C0012a a;
        private volatile com.bumptech.glide.load.engine.a.a b;

        public C0013b(C0012a aVar) {
            this.a = aVar;
        }

        public com.bumptech.glide.load.engine.a.a a() {
            if (this.b == null) {
                synchronized (this) {
                    if (this.b == null) {
                        this.b = this.a.a();
                    }
                    if (this.b == null) {
                        this.b = new com.bumptech.glide.load.engine.a.b();
                    }
                }
            }
            return this.b;
        }
    }

    /* compiled from: Engine */
    public static class c {
        private final c a;
        private final com.bumptech.glide.request.d b;

        public c(com.bumptech.glide.request.d dVar, c cVar) {
            this.b = dVar;
            this.a = cVar;
        }

        public void a() {
            this.a.b(this.b);
        }
    }

    /* compiled from: Engine */
    private static class d implements IdleHandler {
        private final Map<com.bumptech.glide.load.b, WeakReference<g<?>>> a;
        private final ReferenceQueue<g<?>> b;

        public d(Map<com.bumptech.glide.load.b, WeakReference<g<?>>> map, ReferenceQueue<g<?>> referenceQueue) {
            this.a = map;
            this.b = referenceQueue;
        }

        public boolean queueIdle() {
            e eVar = (e) this.b.poll();
            if (eVar != null) {
                this.a.remove(eVar.a);
            }
            return true;
        }
    }

    /* compiled from: Engine */
    private static class e extends WeakReference<g<?>> {
        /* access modifiers changed from: private */
        public final com.bumptech.glide.load.b a;

        public e(com.bumptech.glide.load.b bVar, g<?> gVar, ReferenceQueue<? super g<?>> referenceQueue) {
            super(gVar, referenceQueue);
            this.a = bVar;
        }
    }

    public b(h hVar, C0012a aVar, ExecutorService executorService, ExecutorService executorService2) {
        this(hVar, aVar, executorService, executorService2, null, null, null, null, null);
    }

    b(h hVar, C0012a aVar, ExecutorService executorService, ExecutorService executorService2, Map<com.bumptech.glide.load.b, c> map, f fVar, Map<com.bumptech.glide.load.b, WeakReference<g<?>>> map2, a aVar2, j jVar) {
        this.c = hVar;
        this.g = new C0013b(aVar);
        if (map2 == null) {
            map2 = new HashMap<>();
        }
        this.e = map2;
        if (fVar == null) {
            fVar = new f();
        }
        this.b = fVar;
        if (map == null) {
            map = new HashMap<>();
        }
        this.a = map;
        if (aVar2 == null) {
            aVar2 = new a(executorService, executorService2, this);
        }
        this.d = aVar2;
        if (jVar == null) {
            jVar = new j();
        }
        this.f = jVar;
        hVar.a((com.bumptech.glide.load.engine.a.h.a) this);
    }

    public <T, Z, R> c a(com.bumptech.glide.load.b bVar, int i, int i2, com.bumptech.glide.load.a.c<T> cVar, com.bumptech.glide.e.b<T, Z> bVar2, f<Z> fVar, com.bumptech.glide.load.resource.c.c<Z, R> cVar2, Priority priority, boolean z, DiskCacheStrategy diskCacheStrategy, com.bumptech.glide.request.d dVar) {
        boolean z2 = z;
        com.bumptech.glide.request.d dVar2 = dVar;
        com.bumptech.glide.g.h.a();
        long a2 = com.bumptech.glide.g.d.a();
        e a3 = this.b.a(cVar.b(), bVar, i, i2, bVar2.a(), bVar2.b(), fVar, bVar2.d(), cVar2, bVar2.c());
        g b2 = b((com.bumptech.glide.load.b) a3, z2);
        if (b2 != null) {
            dVar2.a((i<?>) b2);
            if (Log.isLoggable("Engine", 2)) {
                a("Loaded resource from cache", a2, a3);
            }
            return null;
        }
        g a4 = a((com.bumptech.glide.load.b) a3, z2);
        if (a4 != null) {
            dVar2.a((i<?>) a4);
            if (Log.isLoggable("Engine", 2)) {
                a("Loaded resource from active resources", a2, a3);
            }
            return null;
        }
        c cVar3 = (c) this.a.get(a3);
        if (cVar3 != null) {
            cVar3.a(dVar2);
            if (Log.isLoggable("Engine", 2)) {
                a("Added to existing load", a2, a3);
            }
            return new c(dVar2, cVar3);
        }
        c a5 = this.d.a(a3, z2);
        a aVar = new a(a3, i, i2, cVar, bVar2, fVar, cVar2, this.g, diskCacheStrategy, priority);
        EngineRunnable engineRunnable = new EngineRunnable(a5, aVar, priority);
        this.a.put(a3, a5);
        a5.a(dVar2);
        a5.a(engineRunnable);
        if (Log.isLoggable("Engine", 2)) {
            a("Started new load", a2, a3);
        }
        return new c(dVar2, a5);
    }

    private static void a(String str, long j, com.bumptech.glide.load.b bVar) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" in ");
        sb.append(com.bumptech.glide.g.d.a(j));
        sb.append("ms, key: ");
        sb.append(bVar);
        Log.v("Engine", sb.toString());
    }

    private g<?> a(com.bumptech.glide.load.b bVar, boolean z) {
        g<?> gVar = null;
        if (!z) {
            return null;
        }
        WeakReference weakReference = (WeakReference) this.e.get(bVar);
        if (weakReference != null) {
            gVar = (g) weakReference.get();
            if (gVar != null) {
                gVar.e();
            } else {
                this.e.remove(bVar);
            }
        }
        return gVar;
    }

    private g<?> b(com.bumptech.glide.load.b bVar, boolean z) {
        if (!z) {
            return null;
        }
        g<?> a2 = a(bVar);
        if (a2 != null) {
            a2.e();
            this.e.put(bVar, new e(bVar, a2, a()));
        }
        return a2;
    }

    private g<?> a(com.bumptech.glide.load.b bVar) {
        i a2 = this.c.a(bVar);
        if (a2 == null) {
            return null;
        }
        if (a2 instanceof g) {
            return (g) a2;
        }
        return new g(a2, true);
    }

    public void a(i iVar) {
        com.bumptech.glide.g.h.a();
        if (iVar instanceof g) {
            ((g) iVar).f();
            return;
        }
        throw new IllegalArgumentException("Cannot release anything but an EngineResource");
    }

    public void a(com.bumptech.glide.load.b bVar, g<?> gVar) {
        com.bumptech.glide.g.h.a();
        if (gVar != null) {
            gVar.a(bVar, this);
            if (gVar.a()) {
                this.e.put(bVar, new e(bVar, gVar, a()));
            }
        }
        this.a.remove(bVar);
    }

    public void a(c cVar, com.bumptech.glide.load.b bVar) {
        com.bumptech.glide.g.h.a();
        if (cVar.equals((c) this.a.get(bVar))) {
            this.a.remove(bVar);
        }
    }

    public void b(i<?> iVar) {
        com.bumptech.glide.g.h.a();
        this.f.a(iVar);
    }

    public void b(com.bumptech.glide.load.b bVar, g gVar) {
        com.bumptech.glide.g.h.a();
        this.e.remove(bVar);
        if (gVar.a()) {
            this.c.b(bVar, gVar);
        } else {
            this.f.a(gVar);
        }
    }

    private ReferenceQueue<g<?>> a() {
        if (this.h == null) {
            this.h = new ReferenceQueue<>();
            Looper.myQueue().addIdleHandler(new d(this.e, this.h));
        }
        return this.h;
    }
}
