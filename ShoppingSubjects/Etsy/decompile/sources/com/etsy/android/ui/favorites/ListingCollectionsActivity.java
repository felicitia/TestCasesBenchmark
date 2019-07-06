package com.etsy.android.ui.favorites;

import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.ui.nav.a;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.ui.dialog.DialogActivity;

public class ListingCollectionsActivity extends DialogActivity {
    private ListingLike mListing;
    private EtsyId mListingId;
    private String mListingImageUrl;

    public void onCreate(Bundle bundle) {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = getIntent().getExtras();
            if (intent.hasExtra("listing")) {
                this.mListing = (ListingLike) extras.getSerializable("listing");
            } else if (intent.hasExtra("listing_id") && intent.hasExtra(ResponseConstants.LISTING_IMAGE_URL)) {
                this.mListingId = (EtsyId) extras.getSerializable("listing_id");
                this.mListingImageUrl = extras.getString(ResponseConstants.LISTING_IMAGE_URL);
            }
        }
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void onShowDialog(OnDismissListener onDismissListener) {
        a a = e.a((FragmentActivity) this).d().a(onDismissListener);
        if (this.mListing != null) {
            a.a(this.mListing);
        } else if (this.mListingId != null && this.mListingImageUrl != null) {
            a.a(this.mListingId, this.mListingImageUrl);
        }
    }
}
