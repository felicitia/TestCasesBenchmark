package com.etsy.android.ui.shop;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.e.c;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.i;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.models.ReceiptReview;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Review;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.apiv3.TranslatedReview;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.ReceiptReviewsRequest;
import com.etsy.android.lib.requests.ShopsRequest;
import com.etsy.android.lib.requests.apiv3.TranslatedReviewRequest;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.l;
import com.etsy.android.lib.util.t;
import com.etsy.android.stylekit.CompoundVectorTextView;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;
import com.etsy.android.ui.util.f;
import com.etsy.android.uikit.EndlessRecyclerViewListFragment;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.adapter.ReviewAdapter;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.RatingIconView;
import java.io.Serializable;
import java.util.List;

public class FeedbackFragment extends EndlessRecyclerViewListFragment<ReceiptReview> implements com.etsy.android.uikit.viewholder.ReviewViewHolder.a {
    private static final String OFFSET = "offset";
    private static final String REVIEW = "review";
    private static final String REVIEW_COUNT = "review_count";
    /* access modifiers changed from: private */
    public Shop mShop;
    private f mShopHeaderHelper;
    /* access modifiers changed from: private */
    public EtsyId mShopIdentifier;

    private class a extends i<ReceiptReview> {
        private a() {
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<ReceiptReview> a() {
            if (!FeedbackFragment.this.mShopIdentifier.hasId()) {
                return null;
            }
            ReceiptReviewsRequest allReviews = ReceiptReviewsRequest.getAllReviews(FeedbackFragment.this.mShopIdentifier);
            ArrayMap arrayMap = new ArrayMap();
            arrayMap.put("limit", Integer.toString(24));
            arrayMap.put(FeedbackFragment.OFFSET, Integer.toString(FeedbackFragment.this.getApiOffset()));
            allReviews.addParams(arrayMap);
            return allReviews;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k<ReceiptReview> kVar) {
            if (kVar.a()) {
                FeedbackFragment.this.onLoadSuccess(kVar.g(), kVar.f());
            } else {
                FeedbackFragment.this.onLoadFailure();
            }
        }
    }

    private class b extends i<Shop> {
        private b() {
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<Shop> a() {
            if (!FeedbackFragment.this.mShopIdentifier.hasId()) {
                return null;
            }
            ShopsRequest shop = ShopsRequest.getShop(FeedbackFragment.this.mShopIdentifier);
            ArrayMap arrayMap = new ArrayMap();
            arrayMap.put("fields", "shop_id,shop_name,title,total_rating_count,average_rating,icon_url_fullxfull");
            arrayMap.put("includes", "User(user_id,login_name)/Profile(image_url_75x75)");
            shop.addParams(arrayMap);
            return shop;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k<Shop> kVar) {
            if (kVar != null && kVar.a() && kVar.i()) {
                FeedbackFragment.this.mShop = (Shop) kVar.g().get(0);
                if (FeedbackFragment.this.mShop != null) {
                    FeedbackFragment.this.mShopIdentifier = FeedbackFragment.this.mShop.getShopId();
                    FeedbackFragment.this.setShopInfo(FeedbackFragment.this.mShop);
                }
            }
        }
    }

    public int getLayoutId() {
        return R.layout.fragment_feedback;
    }

    @NonNull
    public String getTrackingName() {
        return "shop_reviews";
    }

    public void onContactBuyerClicked(int i) {
    }

    public void onUnhidePhotoClicked(int i) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments().containsKey(ResponseConstants.SHOP)) {
            this.mShop = (Shop) getArguments().getSerializable(ResponseConstants.SHOP);
            this.mShopIdentifier = this.mShop.getShopId();
        } else if (bundle == null || !bundle.containsKey(ResponseConstants.SHOP)) {
            this.mShopIdentifier = (EtsyId) getArguments().getSerializable("shop_id");
        } else {
            this.mShop = (Shop) bundle.getSerializable(ResponseConstants.SHOP);
            this.mShopIdentifier = this.mShop.getShopId();
        }
        this.mAdapter = new ReviewAdapter(getActivity(), getImageBatch());
        ReviewAdapter reviewAdapter = (ReviewAdapter) this.mAdapter;
        reviewAdapter.setOnClickListener(this);
        reviewAdapter.setScrollLoadTriggerListener(this);
        reviewAdapter.setMachineTranslationEnabled(t.e());
        if (bundle != null) {
            for (int i = 0; i < bundle.getInt("review_count"); i++) {
                BaseRecyclerViewAdapter baseRecyclerViewAdapter = this.mAdapter;
                StringBuilder sb = new StringBuilder();
                sb.append("review");
                sb.append(i);
                baseRecyclerViewAdapter.addItem((ReceiptReview) bundle.getSerializable(sb.toString()));
            }
            setApiOffset(bundle.getInt(OFFSET, 0));
        }
        this.mShopHeaderHelper = new f(getResources(), "shop_reviews", false);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (!l.d((Activity) getActivity())) {
            this.mShopHeaderHelper.a(onCreateView.findViewById(R.id.panel_shop_header), onCreateView.findViewById(R.id.shop_header_background));
        }
        this.mSwipeRefreshLayout.setColorSchemeResources(R.color.sk_orange_30);
        loadContent();
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (!l.d((Activity) getActivity())) {
            this.mShopHeaderHelper.a((Activity) getActivity(), getImageBatch(), getAnalyticsContext());
            ((BOENavDrawerActivity) getActivity()).getAppBarHelper().hideDivider();
        }
        if (this.mShop != null) {
            setShopInfo(this.mShop);
        } else {
            getRequestQueue().a((Object) this, (g<Result>) new b<Result>());
        }
    }

    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.item_reviews);
    }

    public void onDestroyView() {
        ((ReviewAdapter) this.mAdapter).setOnClickListener(null);
        super.onDestroyView();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mShop != null) {
            bundle.putSerializable(ResponseConstants.SHOP, this.mShop);
        }
        for (int i = 0; i < this.mAdapter.getDataItemCount(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("review");
            sb.append(i);
            bundle.putSerializable(sb.toString(), (Serializable) this.mAdapter.getItem(i));
        }
        bundle.putInt("review_count", this.mAdapter.getDataItemCount());
        bundle.putInt(OFFSET, getApiOffset());
    }

    /* access modifiers changed from: private */
    public void setShopInfo(@NonNull Shop shop) {
        ReviewAdapter reviewAdapter = (ReviewAdapter) this.mAdapter;
        reviewAdapter.setShopOwnerName(af.a(shop.getUser()));
        reviewAdapter.setSellerAvatarUrl(shop.getAvatarUrl());
        com.etsy.android.lib.toolbar.a.c(shop.getShopName());
        fillShopHeader(shop);
    }

    private void fillShopHeader(Shop shop) {
        Resources resources;
        int i;
        if (!l.d((Activity) getActivity())) {
            this.mShopHeaderHelper.a(shop.getUser(), shop);
        } else {
            View view = getView();
            if (view != null) {
                ImageView imageView = (ImageView) view.findViewById(R.id.shop_avatar);
                if (!(this.mShop.getUser() == null || this.mShop.getUser().getProfile() == null)) {
                    int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.gen_avatar_corners_large);
                    if (l.d((Activity) getActivity())) {
                        resources = getResources();
                        i = R.dimen.shop_home_main_avatar;
                    } else {
                        resources = getResources();
                        i = R.dimen.shop_listing_header_avatar;
                    }
                    int dimensionPixelOffset2 = resources.getDimensionPixelOffset(i);
                    getImageBatch().b(shop.getUser().getProfile().getImageUrl75x75(), imageView, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset2);
                }
                ((TextView) view.findViewById(R.id.shop_name)).setText(shop.getShopName());
                view.findViewById(R.id.shop_header).setVisibility(0);
                TextView textView = (TextView) view.findViewById(R.id.rating_count);
                StringBuilder sb = new StringBuilder();
                sb.append("(");
                sb.append(af.a((double) shop.getNumRatings()));
                sb.append(")");
                textView.setText(sb.toString());
                setUpShopLocationAndTagline(shop);
                View findViewById = view.findViewById(R.id.shop_header_click);
                RatingIconView ratingIconView = (RatingIconView) view.findViewById(R.id.shop_rating);
                if (shop.getAverageRating() > 0.0d) {
                    ratingIconView.setRating((float) shop.getAverageRating());
                } else {
                    view.findViewById(R.id.average_review_title).setVisibility(8);
                    ratingIconView.setVisibility(8);
                    textView.setText(getResources().getQuantityString(R.plurals.reviews_plurals_no_brackets, shop.getNumRatings(), new Object[]{af.a((double) shop.getNumRatings())}));
                }
                findViewById.setOnClickListener(new TrackingOnClickListener(this.mShop) {
                    public void onViewClick(View view) {
                        e.a(FeedbackFragment.this.getActivity()).a().a(FeedbackFragment.this.mShop.getShopId(), FeedbackFragment.this.mShop.getUserId());
                    }
                });
            }
        }
    }

    private void setUpShopLocationAndTagline(Shop shop) {
        View view = getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(R.id.tagline);
            if (af.a(shop.getTitle())) {
                textView.setText(shop.getTitle());
            } else {
                textView.setVisibility(8);
            }
            CompoundVectorTextView compoundVectorTextView = (CompoundVectorTextView) view.findViewById(R.id.shop_location);
            String a2 = af.a(shop.getUser().getProfile());
            if (af.a(a2)) {
                compoundVectorTextView.setText(a2);
            } else {
                compoundVectorTextView.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLoadContent() {
        getRequestQueue().a((Object) this, (g<Result>) new a<Result>());
    }

    public void onAppreciationPhotoClicked(int i) {
        ReceiptReview receiptReview = (ReceiptReview) this.mAdapter.getItem(i);
        if (receiptReview != null) {
            Review review = (Review) receiptReview.getReviews().get(0);
            if (review != null) {
                e.a(getActivity()).a().g(review.getTransactionId());
            }
        }
    }

    public void onListingClicked(int i) {
        ReceiptReview receiptReview = (ReceiptReview) this.mAdapter.getItem(i);
        if (receiptReview != null) {
            e.a((Activity) getActivity()).a(((Review) receiptReview.getReviews().get(0)).getListingId());
        }
    }

    public void onShopOwnerClicked(int i) {
        e.a((Activity) getActivity()).b(this.mShopIdentifier);
    }

    public void onUserClicked(int i) {
        ReceiptReview receiptReview = (ReceiptReview) this.mAdapter.getItem(i);
        if (receiptReview != null) {
            e.a((Activity) getActivity()).c(receiptReview.getUserId());
        }
    }

    public void onTranslateReviewClicked(final int i) {
        final Review review = (Review) ((ReceiptReview) this.mAdapter.getItem(i)).getReviews().get(0);
        getRequestQueue().a((Object) this, (g<Result>) com.etsy.android.lib.core.e.a((EtsyRequest<Result>) TranslatedReviewRequest.getTranslatedReview(this.mShop.getShopId(), review.getTransactionId(), t.d())).a((c) new c() {
            public void a() {
                ((ReviewAdapter) FeedbackFragment.this.mAdapter).onTranslationLoading(i);
                FeedbackFragment.this.mAdapter.notifyItemChanged(i);
            }
        }).a((com.etsy.android.lib.core.f.c<Result>) new com.etsy.android.lib.core.f.c<TranslatedReview>() {
            public void a(List<TranslatedReview> list, int i, k<TranslatedReview> kVar) {
                review.setTranslatedReviewMessage(((TranslatedReview) list.get(0)).getTranslatedReview());
                ((ReviewAdapter) FeedbackFragment.this.mAdapter).onTranslationSuccess(i);
                FeedbackFragment.this.mAdapter.notifyItemChanged(i);
            }
        }).a((com.etsy.android.lib.core.f.b) new com.etsy.android.lib.core.f.b() {
            public void a(int i, String str, k kVar) {
                ((ReviewAdapter) FeedbackFragment.this.mAdapter).onTranslationError(i);
                FeedbackFragment.this.mAdapter.notifyItemChanged(i);
            }
        }).a());
    }
}
