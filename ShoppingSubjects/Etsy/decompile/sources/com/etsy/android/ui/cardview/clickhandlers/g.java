package com.etsy.android.ui.cardview.clickhandlers;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.ui.nav.e;
import com.etsy.android.vespa.b;

/* compiled from: ListingCollectionClickHandler */
public class g extends b<Collection> {
    private Fragment a;

    public g(String str, FragmentActivity fragmentActivity, @NonNull com.etsy.android.lib.logger.b bVar, Fragment fragment) {
        super(fragmentActivity, bVar);
        this.a = fragment;
    }

    public void a(Collection collection) {
        e.a((Activity) d()).a(600, this.a).a(collection);
    }
}
