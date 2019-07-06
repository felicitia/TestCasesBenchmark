package com.etsy.android.ui.shophome;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.etsy.android.lib.core.http.request.EtsyApiV2Request;
import com.etsy.android.lib.core.http.request.EtsyApiV2Request.a;
import com.etsy.android.lib.core.http.request.a.C0065a;
import com.etsy.android.lib.core.http.request.c.b;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.models.ReceiptReview;
import com.etsy.android.lib.models.apiv3.ShopHomePage;
import com.etsy.android.lib.models.apiv3.ShopReviewsResult;
import com.etsy.android.vespa.b.c;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class ShopHomeReviewsFragment extends SectionedShopHomeFragment {
    c mPaginationForOffset = new c();

    public void onScrolledToLoadTrigger() {
        onLoadReviews();
    }

    public int getLoadTriggerPosition() {
        return this.mAdapter.getDataItemCount() / 2;
    }

    /* access modifiers changed from: protected */
    public EtsyApiV2Request<ReceiptReview> getReviewsRequest() {
        a a = a.a(ReceiptReview.class, String.format("/v2/apps/shops/%s/reviews", new Object[]{getShopId().toString()}));
        a.a((Map<String, String>) getPagination().getPaginationParams());
        return a.d();
    }

    public void onLoadContent() {
        if (getAdapter().getItemCount() > 0) {
            onLoadReviews();
        } else {
            super.onLoadContent();
        }
    }

    /* access modifiers changed from: protected */
    public void onLoadReviews() {
        getRequestQueue().a(((com.etsy.android.lib.core.http.request.c.a) ((com.etsy.android.lib.core.http.request.c.a) com.etsy.android.lib.core.http.request.c.a.a(getReviewsRequest()).a(true)).a((C0065a<ResultType>) new b<ReceiptReview>() {
            WeakReference<ShopHomeReviewsFragment> a = new WeakReference<>(ShopHomeReviewsFragment.this);

            public void a(int i, @Nullable String str, @NonNull k<ReceiptReview> kVar) {
            }

            public void a(@NonNull List<ReceiptReview> list, int i, @NonNull k<ReceiptReview> kVar) {
                ShopHomeReviewsFragment shopHomeReviewsFragment = (ShopHomeReviewsFragment) this.a.get();
                if (shopHomeReviewsFragment != null) {
                    shopHomeReviewsFragment.onAddReviews(kVar);
                }
            }
        }, (Fragment) this)).c());
    }

    /* access modifiers changed from: private */
    public void onAddReviews(k<ReceiptReview> kVar) {
        getShopHomeFactoryAdapter().addReviews(kVar.g());
        getPagination().onSuccess(kVar, kVar.g().size());
    }

    @NonNull
    public com.etsy.android.vespa.b.a getPagination() {
        return this.mPaginationForOffset;
    }

    /* access modifiers changed from: protected */
    public void onPageLoaded(@NonNull ShopHomePage shopHomePage) {
        super.onPageLoaded(shopHomePage);
        ShopReviewsResult shopReviews = shopHomePage.getShopReviews();
        getPagination().onSuccess(Integer.valueOf(shopReviews.getCount()), shopReviews.getReviews().size());
    }
}
