package com.etsy.android.ui.shop;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.cardview.clickhandlers.f;
import com.etsy.android.ui.nav.e;
import com.etsy.android.vespa.d;

/* compiled from: AppreciationPhotoFeatureClickHandler */
public class a extends f {
    public a(FragmentActivity fragmentActivity, d dVar, @NonNull b bVar) {
        super(fragmentActivity, dVar, bVar);
    }

    public void a(EtsyId etsyId) {
        e.a(d()).a().c(etsyId);
    }
}
