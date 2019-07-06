package com.etsy.android.a;

import com.etsy.android.messaging.h;
import dagger.internal.c;
import dagger.internal.f;

/* compiled from: AppModule_ProvideNotificationRepoFactory */
public final class ae implements c<h> {
    static final /* synthetic */ boolean a = true;
    private final v b;

    public ae(v vVar) {
        if (a || vVar != null) {
            this.b = vVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public h b() {
        return (h) f.a(this.b.d(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<h> a(v vVar) {
        return new ae(vVar);
    }
}
