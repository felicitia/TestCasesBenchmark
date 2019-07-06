package com.etsy.android.ui.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.ui.core.TransparentActivity;

public class CollectionEditActivity extends TransparentActivity {
    private Collection mCollection;

    public void onCreate(Bundle bundle) {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Collection.TYPE_COLLECTION)) {
            this.mCollection = (Collection) getIntent().getExtras().getSerializable(Collection.TYPE_COLLECTION);
        }
        super.onCreate(bundle);
        if (bundle == null) {
            e.a((FragmentActivity) this).f().B().a(this.mCollection);
        }
    }
}
