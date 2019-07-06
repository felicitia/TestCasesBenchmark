package com.etsy.android.d;

import com.etsy.android.ui.convos.ManufacturerProjectActivity;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: ManufacturerProjectActivityModule_ProvidesManufacturerProjectActivityFactory */
public final class b implements c<TrackingBaseActivity> {
    static final /* synthetic */ boolean a = true;
    private final a b;
    private final a<ManufacturerProjectActivity> c;

    public b(a aVar, a<ManufacturerProjectActivity> aVar2) {
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
        return (TrackingBaseActivity) f.a(this.b.a((ManufacturerProjectActivity) this.c.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<TrackingBaseActivity> a(a aVar, a<ManufacturerProjectActivity> aVar2) {
        return new b(aVar, aVar2);
    }
}
