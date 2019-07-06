package com.etsy.android.ui.feedback;

import com.etsy.android.uikit.nav.TrackingBaseActivity;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: FeedbackActivityModule_ProvidesTrackingBaseActivityFactory */
public final class e implements c<TrackingBaseActivity> {
    static final /* synthetic */ boolean a = true;
    private final a b;
    private final a<FeedbackActivity> c;

    public e(a aVar, a<FeedbackActivity> aVar2) {
        if (a || aVar != null) {
            this.b = aVar;
            if (a || aVar2 != null) {
                this.c = aVar2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public TrackingBaseActivity b() {
        return (TrackingBaseActivity) f.a(this.b.a((FeedbackActivity) this.c.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<TrackingBaseActivity> a(a aVar, a<FeedbackActivity> aVar2) {
        return new e(aVar, aVar2);
    }
}
