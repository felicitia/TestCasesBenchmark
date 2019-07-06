package com.bumptech.glide;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.manager.g;
import com.bumptech.glide.manager.j;
import com.bumptech.glide.manager.k;

/* compiled from: RequestManager */
public class h implements com.bumptech.glide.manager.h {
    /* access modifiers changed from: private */
    public final Context a;
    /* access modifiers changed from: private */
    public final g b;
    private final j c;
    /* access modifiers changed from: private */
    public final k d;
    /* access modifiers changed from: private */
    public final Glide e;
    /* access modifiers changed from: private */
    public final c f;
    /* access modifiers changed from: private */
    public a g;

    /* compiled from: RequestManager */
    public interface a {
        <T> void a(e<T, ?, ?, ?> eVar);
    }

    /* compiled from: RequestManager */
    public final class b<A, T> {
        /* access modifiers changed from: private */
        public final l<A, T> b;
        /* access modifiers changed from: private */
        public final Class<T> c;

        /* compiled from: RequestManager */
        public final class a {
            private final A b;
            private final Class<A> c;
            private final boolean d = true;

            a(A a2) {
                this.b = a2;
                this.c = h.c(a2);
            }

            public <Z> f<A, T, Z> a(Class<Z> cls) {
                c e = h.this.f;
                f fVar = new f(h.this.a, h.this.e, this.c, b.this.b, b.this.c, cls, h.this.d, h.this.b, h.this.f);
                f<A, T, Z> fVar2 = (f) e.a(fVar);
                if (this.d) {
                    fVar2.b(this.b);
                }
                return fVar2;
            }
        }

        b(l<A, T> lVar, Class<T> cls) {
            this.b = lVar;
            this.c = cls;
        }

        public a a(A a2) {
            return new a<>(a2);
        }
    }

    /* compiled from: RequestManager */
    class c {
        c() {
        }

        public <A, X extends e<A, ?, ?, ?>> X a(X x) {
            if (h.this.g != null) {
                h.this.g.a(x);
            }
            return x;
        }
    }

    /* compiled from: RequestManager */
    private static class d implements com.bumptech.glide.manager.c.a {
        private final k a;

        public d(k kVar) {
            this.a = kVar;
        }

        public void a(boolean z) {
            if (z) {
                this.a.d();
            }
        }
    }

    public h(Context context, g gVar, j jVar) {
        this(context, gVar, jVar, new k(), new com.bumptech.glide.manager.d());
    }

    h(Context context, final g gVar, j jVar, k kVar, com.bumptech.glide.manager.d dVar) {
        this.a = context.getApplicationContext();
        this.b = gVar;
        this.c = jVar;
        this.d = kVar;
        this.e = Glide.a(context);
        this.f = new c();
        com.bumptech.glide.manager.c a2 = dVar.a(context, new d(kVar));
        if (com.bumptech.glide.g.h.c()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    gVar.a(h.this);
                }
            });
        } else {
            gVar.a(this);
        }
        gVar.a(a2);
    }

    public void a(int i) {
        this.e.a(i);
    }

    public void a() {
        this.e.h();
    }

    public void b() {
        com.bumptech.glide.g.h.a();
        this.d.a();
    }

    public void c() {
        com.bumptech.glide.g.h.a();
        this.d.b();
    }

    public void d() {
        c();
    }

    public void e() {
        b();
    }

    public void f() {
        this.d.c();
    }

    public <A, T> b<A, T> a(l<A, T> lVar, Class<T> cls) {
        return new b<>(lVar, cls);
    }

    public d<String> a(String str) {
        return (d) g().b(str);
    }

    public d<String> g() {
        return a(String.class);
    }

    public <T> d<T> a(T t) {
        return (d) a(c(t)).b(t);
    }

    private <T> d<T> a(Class<T> cls) {
        l a2 = Glide.a(cls, this.a);
        l b2 = Glide.b(cls, this.a);
        if (cls != null && a2 == null && b2 == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown type ");
            sb.append(cls);
            sb.append(". You must provide a Model of a type for");
            sb.append(" which there is a registered ModelLoader, if you are using a custom model, you must first call");
            sb.append(" Glide#register with a ModelLoaderFactory for your custom model class");
            throw new IllegalArgumentException(sb.toString());
        }
        c cVar = this.f;
        d dVar = new d(cls, a2, b2, this.a, this.e, this.d, this.b, this.f);
        return (d) cVar.a(dVar);
    }

    /* access modifiers changed from: private */
    public static <T> Class<T> c(T t) {
        if (t != null) {
            return t.getClass();
        }
        return null;
    }
}
