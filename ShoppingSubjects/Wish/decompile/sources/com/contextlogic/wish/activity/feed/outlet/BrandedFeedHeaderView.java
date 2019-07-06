package com.contextlogic.wish.activity.feed.outlet;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.feed.BaseFeedHeaderView;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishBrandedFeedInfo;
import com.contextlogic.wish.api.model.WishCategory;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.listview.HorizontalListView.OnItemClickListener;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.crashlytics.android.Crashlytics;
import java.util.ArrayList;

public class BrandedFeedHeaderView extends BaseFeedHeaderView {
    private ImageView mBannerHideButton;
    private ThemedTextView mBannerText;
    private ThemedTextView mBannerTextOld;
    private ThemedTextView mBannerTextSubtitle;
    private View mBannerView;
    private WishBrandedFeedInfo mBrandedFeedInfo;
    /* access modifiers changed from: private */
    public ArrayList<WishCategory> mCategories;
    private BrandedCategoryAdapter mCategoryAdapter;
    private HorizontalListView mCategoryListView;
    private ThemedTextView mCategoryViewAllButton;
    private final Context mContext;
    private ProductFeedFragment mFragment;
    private ImageHttpPrefetcher mImagePrefetcher;

    public BrandedFeedHeaderView(Context context, ProductFeedFragment productFeedFragment) {
        super(context);
        this.mFragment = productFeedFragment;
        this.mContext = context;
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.branded_feed_view, this);
        this.mBannerView = inflate.findViewById(R.id.branded_feed_view_banner);
        this.mBannerHideButton = (ImageView) inflate.findViewById(R.id.branded_feed_view_banner_x_button);
        this.mBannerHideButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BrandedFeedHeaderView.this.handleBannerHide();
            }
        });
        this.mBannerText = (ThemedTextView) inflate.findViewById(R.id.branded_feed_view_banner_text);
        this.mBannerTextSubtitle = (ThemedTextView) inflate.findViewById(R.id.branded_feed_view_banner_text_subtitle);
        this.mBannerTextOld = (ThemedTextView) findViewById(R.id.branded_feed_view_banner_text_old);
        this.mCategoryViewAllButton = (ThemedTextView) inflate.findViewById(R.id.branded_feed_view_category_view_all);
        this.mCategoryViewAllButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BRANDED_PRODUCT_CATEGORY_VIEW_ALL);
                BrandedFeedHeaderView.this.handleCategoryViewAll();
            }
        });
        this.mCategoryListView = (HorizontalListView) inflate.findViewById(R.id.branded_feed_view_category_list_view);
        this.mCategoryListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(int i, View view) {
                if (i >= 0 && i < BrandedFeedHeaderView.this.mCategories.size()) {
                    BrandedFeedHeaderView.this.handleCategorySelected((WishCategory) BrandedFeedHeaderView.this.mCategories.get(i));
                }
            }
        });
        this.mImagePrefetcher = new ImageHttpPrefetcher();
        this.mCategoryAdapter = new BrandedCategoryAdapter(this.mFragment, this.mContext, this.mCategoryListView);
        this.mCategoryAdapter.setImagePrefetcher(this.mImagePrefetcher);
        this.mCategoryListView.setAdapter(this.mCategoryAdapter);
    }

    public void setBrandedFeedInfo(WishBrandedFeedInfo wishBrandedFeedInfo) {
        this.mBrandedFeedInfo = wishBrandedFeedInfo;
        refreshBrandedFeedInfo();
        setCategories(wishBrandedFeedInfo.getBrandedCategories());
        if (ExperimentDataCenter.getInstance().shouldSeeNewLayoutOutletBanner()) {
            this.mBannerText.setText(wishBrandedFeedInfo.getBannerText());
            this.mBannerTextSubtitle.setText(wishBrandedFeedInfo.getBannerTextSubtitle());
            this.mBannerTextOld.setVisibility(8);
            return;
        }
        String string = getContext().getResources().getString(R.string.brand_banner);
        SpannableString spannableString = new SpannableString(string);
        int indexOf = string.indexOf("\n");
        int lastIndexOf = string.lastIndexOf("\n");
        this.mBannerText.setVisibility(8);
        this.mBannerTextSubtitle.setVisibility(8);
        try {
            spannableString.setSpan(new RelativeSizeSpan(3.0f), indexOf + 1, lastIndexOf, 33);
        } catch (IndexOutOfBoundsException e) {
            Crashlytics.logException(e);
        }
        this.mBannerTextOld.setText(spannableString);
    }

    public void setCategories(ArrayList<WishCategory> arrayList) {
        this.mCategories = arrayList;
        this.mCategoryAdapter.setCategories(this.mCategories);
    }

    private void refreshBrandedFeedInfo() {
        if (this.mBrandedFeedInfo.shouldShowBanner()) {
            this.mBannerView.setVisibility(0);
        } else {
            this.mBannerView.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void handleBannerHide() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BRANDED_PRODUCT_HIDE_BANNER);
        this.mBannerView.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void handleCategoryViewAll() {
        if (this.mCategories != null) {
            Intent intent = new Intent();
            intent.setClass(this.mFragment.getBaseActivity(), BrandedCategoryListActivity.class);
            intent.putParcelableArrayListExtra("ExtraCategories", this.mCategories);
            this.mFragment.getBaseActivity().startActivity(intent);
        }
    }

    /* access modifiers changed from: private */
    public void handleCategorySelected(WishCategory wishCategory) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BRANDED_PRODUCT_SELECT_CATEGORY_ITEM);
        Intent intent = new Intent();
        intent.setClass(this.mFragment.getBaseActivity(), BrandedFeedActivity.class);
        intent.putExtra(BrandedFeedActivity.EXTRA_CATEGORY, wishCategory);
        this.mFragment.getBaseActivity().startActivity(intent);
    }

    public void releaseImages() {
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.cancelPrefetching();
        }
        if (this.mCategoryAdapter != null) {
            this.mCategoryAdapter.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.resumePrefetching();
        }
        if (this.mCategoryAdapter != null) {
            this.mCategoryAdapter.restoreImages();
        }
    }
}
