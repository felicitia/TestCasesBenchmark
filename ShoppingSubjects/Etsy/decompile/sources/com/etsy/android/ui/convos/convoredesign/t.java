package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.util.NotificationType;
import com.etsy.android.messaging.f;
import com.etsy.android.messaging.h;
import io.reactivex.functions.g;
import io.reactivex.functions.i;
import io.reactivex.q;
import kotlin.jvm.internal.p;

/* compiled from: ConvoNotificationRepo.kt */
public final class t {
    private final h a;

    /* compiled from: ConvoNotificationRepo.kt */
    static final class a<T> implements i<f> {
        public static final a a = new a();

        a() {
        }

        /* renamed from: a */
        public final boolean test(f fVar) {
            p.b(fVar, "data");
            return NotificationType.fromString(fVar.a()) == NotificationType.CONVO;
        }
    }

    /* compiled from: ConvoNotificationRepo.kt */
    static final class b<T, R> implements g<T, R> {
        public static final b a = new b();

        b() {
        }

        /* renamed from: a */
        public final s apply(f fVar) {
            p.b(fVar, "bundle");
            return new s(fVar.b());
        }
    }

    public t(h hVar) {
        p.b(hVar, "notificationRepo");
        this.a = hVar;
    }

    public final q<s> a() {
        q<s> b2 = this.a.a().a((i<? super T>) a.a).b((g<? super T, ? extends R>) b.a);
        p.a((Object) b2, "notificationRepo.observe…ername)\n                }");
        return b2;
    }
}
