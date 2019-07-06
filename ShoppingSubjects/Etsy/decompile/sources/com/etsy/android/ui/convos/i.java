package com.etsy.android.ui.convos;

import com.etsy.android.lib.models.apiv3.User;
import com.etsy.android.lib.requests.apiv3.UserEndpoint;
import io.reactivex.functions.g;
import io.reactivex.v;
import kotlin.jvm.internal.p;
import retrofit2.adapter.rxjava2.d;
import retrofit2.l;

/* compiled from: ConvoRepository.kt */
public final class i {
    private final UserEndpoint a;

    /* compiled from: ConvoRepository.kt */
    public static abstract class a {

        /* renamed from: com.etsy.android.ui.convos.i$a$a reason: collision with other inner class name */
        /* compiled from: ConvoRepository.kt */
        public static final class C0094a extends a {
            public C0094a() {
                super(null);
            }
        }

        /* compiled from: ConvoRepository.kt */
        public static final class b extends a {
            private final User a;

            public b(User user) {
                p.b(user, "user");
                super(null);
                this.a = user;
            }

            public final User a() {
                return this.a;
            }
        }

        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }
    }

    /* compiled from: ConvoRepository.kt */
    static final class b<T, R> implements g<T, R> {
        public static final b a = new b();

        b() {
        }

        /* renamed from: a */
        public final a apply(d<User> dVar) {
            p.b(dVar, "it");
            l a2 = dVar.a();
            User user = a2 != null ? (User) a2.e() : null;
            if (user != null) {
                return new b(user);
            }
            return new C0094a();
        }
    }

    /* compiled from: ConvoRepository.kt */
    static final class c<T, R> implements g<Throwable, a> {
        public static final c a = new c();

        c() {
        }

        /* renamed from: a */
        public final C0094a apply(Throwable th) {
            p.b(th, "it");
            return new C0094a();
        }
    }

    public i(UserEndpoint userEndpoint) {
        p.b(userEndpoint, "userEndpoint");
        this.a = userEndpoint;
    }

    public final v<a> a(long j) {
        v<a> c2 = this.a.getPublicUserById(j).b((g<? super T, ? extends R>) b.a).c(c.a);
        p.a((Object) c2, "userEndpoint.getPublicUs…sult.RecipientFailure() }");
        return c2;
    }
}
