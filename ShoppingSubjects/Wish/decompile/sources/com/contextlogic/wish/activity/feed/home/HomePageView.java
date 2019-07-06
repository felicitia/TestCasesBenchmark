package com.contextlogic.wish.activity.feed.home;

import android.content.Context;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.feed.BaseFeedHeaderView;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.pricechop.PriceChopHomePageView;
import com.contextlogic.wish.api.model.WishHomePageInfo;
import com.contextlogic.wish.api.model.WishHomePageInfo.HomePageProductListItemHolder;
import com.contextlogic.wish.api.model.WishHomePageInfo.RowType;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;
import java.util.Iterator;

public class HomePageView extends BaseFeedHeaderView {
    private LinearLayout mContainer;
    private final Context mContext;
    private ProductFeedFragment mFragment;
    private WishHomePageInfo mHomePageInfo = WishHomePageInfo.getInstance();
    private ArrayList<ImageHttpPrefetcher> mImageHttpPrefetchers;
    private ThemedTextView mProductFeedTitle;
    private int mTotalRows = 0;
    private int mTotalRowsFailed;
    private int mTotalRowsRequested;

    public HomePageView(Context context, ProductFeedFragment productFeedFragment) {
        super(context);
        this.mContext = context;
        this.mFragment = productFeedFragment;
        init();
    }

    public void updateView(int i, int i2, int i3) {
        RowType[] values;
        if (this.mHomePageInfo != null && this.mContainer != null && this.mContainer.getChildCount() != 1 && this.mContainer.getChildAt(i3) != null) {
            RowType rowType = null;
            for (RowType rowType2 : RowType.values()) {
                if (rowType2.getValue() == i) {
                    rowType = rowType2;
                }
            }
            if (rowType != null) {
                switch (rowType) {
                    case PRODUCT:
                        long j = (long) i2;
                        HomePageProductListItemHolder productListContentView = this.mHomePageInfo.getProductListContentView(j);
                        if (productListContentView != null && productListContentView.getProducts().size() > 0) {
                            ImageHttpPrefetcher imageHttpPrefetcher = new ImageHttpPrefetcher();
                            this.mImageHttpPrefetchers.add(imageHttpPrefetcher);
                            ((HomePageHorizontalProductListView) this.mContainer.getChildAt(i3)).setup(this.mHomePageInfo.getProductListContentView(j), imageHttpPrefetcher);
                            this.mTotalRows++;
                            break;
                        } else {
                            hideRow(i3);
                            return;
                        }
                    case DETAILED_PRODUCT:
                        long j2 = (long) i2;
                        HomePageProductListItemHolder productListContentView2 = this.mHomePageInfo.getProductListContentView(j2);
                        if (productListContentView2 != null && productListContentView2.getProducts().size() > 0) {
                            ImageHttpPrefetcher imageHttpPrefetcher2 = new ImageHttpPrefetcher();
                            this.mImageHttpPrefetchers.add(imageHttpPrefetcher2);
                            ((HomePageHorizontalProductListView) this.mContainer.getChildAt(i3)).setup(this.mHomePageInfo.getProductListContentView(j2), imageHttpPrefetcher2);
                            this.mTotalRows++;
                            break;
                        } else {
                            hideRow(i3);
                            return;
                        }
                    case NOTIFICATION:
                        if (this.mHomePageInfo.getNotifications().size() > 0) {
                            ImageHttpPrefetcher imageHttpPrefetcher3 = new ImageHttpPrefetcher();
                            this.mImageHttpPrefetchers.add(imageHttpPrefetcher3);
                            ((HomePageHorizontalNotificationListView) this.mContainer.getChildAt(i3)).setup(this.mHomePageInfo, imageHttpPrefetcher3);
                            this.mTotalRows++;
                            break;
                        } else {
                            hideRow(i3);
                            return;
                        }
                    case STATUS_UPDATE:
                        if (this.mHomePageInfo.getOrderStatuses().size() > 0) {
                            ImageHttpPrefetcher imageHttpPrefetcher4 = new ImageHttpPrefetcher();
                            this.mImageHttpPrefetchers.add(imageHttpPrefetcher4);
                            ((HomePageHorizontalOrderStatusView) this.mContainer.getChildAt(i3)).setup(this.mHomePageInfo, imageHttpPrefetcher4);
                            this.mTotalRows++;
                            break;
                        } else {
                            hideRow(i3);
                            return;
                        }
                    case COMMERCE_LOAN:
                        if (!this.mHomePageInfo.getCommerceLoans().isEmpty()) {
                            ((HomePageCommerceLoanCellView) this.mContainer.getChildAt(i3)).setup(this.mHomePageInfo);
                            this.mTotalRows++;
                            break;
                        } else {
                            hideRow(i3);
                            return;
                        }
                    case PRICE_CHOP:
                        if (this.mHomePageInfo.getPriceChop() != null) {
                            ((PriceChopHomePageView) this.mContainer.getChildAt(i3)).setup(this.mHomePageInfo.getPriceChop(), false);
                            this.mTotalRows++;
                            break;
                        } else {
                            hideRow(i3);
                            return;
                        }
                }
                refreshViewState();
            }
        }
    }

    private void init() {
        this.mContainer = new LinearLayout(getContext());
        this.mContainer.setOrientation(1);
        this.mContainer.setLayoutParams(new LayoutParams(-1, -2));
        this.mImageHttpPrefetchers = new ArrayList<>();
        addView(this.mContainer);
        this.mProductFeedTitle = new ThemedTextView(this.mContext);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        this.mProductFeedTitle.setTextSize(0, (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.text_size_subtitle));
        this.mProductFeedTitle.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.text_primary));
        this.mProductFeedTitle.setTypeface(1);
        layoutParams.gravity = 19;
        this.mProductFeedTitle.setLayoutParams(layoutParams);
        this.mProductFeedTitle.setPadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.twelve_padding), WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.sixteen_padding), 0, 0);
        this.mContainer.addView(this.mProductFeedTitle);
        setProductFeedTitle(WishApplication.getInstance().getString(R.string.latest));
        this.mTotalRowsRequested = -1;
        WishHomePageInfo instance = WishHomePageInfo.getInstance();
        Iterator it = this.mHomePageInfo.getRowOrdering().iterator();
        while (it.hasNext()) {
            RowType rowType = (RowType) it.next();
            this.mTotalRowsRequested++;
            long longValue = ((Long) this.mHomePageInfo.getRowIds().get(this.mTotalRowsRequested)).longValue();
            switch (rowType) {
                case PRODUCT:
                    HomePageHorizontalProductListView homePageHorizontalProductListView = new HomePageHorizontalProductListView(getContext(), this.mFragment, false);
                    homePageHorizontalProductListView.startLoading(this.mHomePageInfo.getProductListContentView(longValue));
                    this.mContainer.addView(homePageHorizontalProductListView, this.mTotalRowsRequested);
                    requestRow(rowType.getValue(), longValue, this.mTotalRowsRequested);
                    break;
                case DETAILED_PRODUCT:
                    HomePageHorizontalProductListView homePageHorizontalProductListView2 = new HomePageHorizontalProductListView(getContext(), this.mFragment, true);
                    homePageHorizontalProductListView2.startLoading(this.mHomePageInfo.getProductListContentView(longValue));
                    this.mContainer.addView(homePageHorizontalProductListView2, this.mTotalRowsRequested);
                    requestRow(rowType.getValue(), longValue, this.mTotalRowsRequested);
                    break;
                case NOTIFICATION:
                    HomePageHorizontalNotificationListView homePageHorizontalNotificationListView = new HomePageHorizontalNotificationListView(getContext(), this.mFragment);
                    homePageHorizontalNotificationListView.startLoading(instance);
                    this.mContainer.addView(homePageHorizontalNotificationListView, this.mTotalRowsRequested);
                    requestRow(rowType.getValue(), longValue, this.mTotalRowsRequested);
                    break;
                case STATUS_UPDATE:
                    HomePageHorizontalOrderStatusView homePageHorizontalOrderStatusView = new HomePageHorizontalOrderStatusView(getContext(), this.mFragment);
                    homePageHorizontalOrderStatusView.startLoading(instance);
                    this.mContainer.addView(homePageHorizontalOrderStatusView, this.mTotalRowsRequested);
                    requestRow(rowType.getValue(), longValue, this.mTotalRowsRequested);
                    break;
                case COMMERCE_LOAN:
                    HomePageCommerceLoanCellView homePageCommerceLoanCellView = new HomePageCommerceLoanCellView(getContext());
                    homePageCommerceLoanCellView.setup(instance);
                    this.mContainer.addView(homePageCommerceLoanCellView, this.mTotalRowsRequested);
                    requestRow(rowType.getValue(), longValue, this.mTotalRowsRequested);
                    break;
                case PRICE_CHOP:
                    if (instance.getPriceChop() == null) {
                        break;
                    } else {
                        PriceChopHomePageView priceChopHomePageView = new PriceChopHomePageView(getContext());
                        priceChopHomePageView.setup(instance.getPriceChop(), true);
                        this.mContainer.addView(priceChopHomePageView, this.mTotalRowsRequested);
                        requestRow(rowType.getValue(), longValue, this.mTotalRowsRequested);
                        break;
                    }
            }
        }
        this.mTotalRowsRequested++;
        refreshViewState();
    }

    private void hideAllUiElements() {
        this.mProductFeedTitle.setVisibility(8);
        setVisibility(8);
    }

    private void refreshViewState() {
        hideAllUiElements();
        if (this.mTotalRows >= 1) {
            setVisibility(0);
            setVisibility(0);
            this.mProductFeedTitle.setVisibility(0);
        } else if (this.mTotalRowsFailed != this.mTotalRowsRequested) {
            setVisibility(0);
        }
    }

    private void requestRow(int i, long j, int i2) {
        ProductFeedFragment productFeedFragment = this.mFragment;
        final int i3 = i;
        final long j2 = j;
        final int i4 = i2;
        AnonymousClass1 r1 = new ServiceTask<BaseActivity, BaseProductFeedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, BaseProductFeedServiceFragment baseProductFeedServiceFragment) {
                baseProductFeedServiceFragment.requestHomePageRow(i3, j2, i4);
            }
        };
        productFeedFragment.withServiceFragment(r1);
    }

    private void hideRow(int i) {
        if (this.mContainer.getChildAt(i) != null) {
            this.mContainer.getChildAt(i).setVisibility(8);
        }
        refreshViewState();
    }

    public void setProductFeedTitle(String str) {
        this.mProductFeedTitle.setText(str);
    }

    public void releaseImages() {
        if (this.mImageHttpPrefetchers != null) {
            Iterator it = this.mImageHttpPrefetchers.iterator();
            while (it.hasNext()) {
                ((ImageHttpPrefetcher) it.next()).cancelPrefetching();
            }
        }
        if (this.mContainer != null && this.mContainer.getChildCount() > 0) {
            for (int i = 0; i < this.mContainer.getChildCount(); i++) {
                if (this.mContainer.getChildAt(i) instanceof ImageRestorable) {
                    ((ImageRestorable) this.mContainer.getChildAt(i)).releaseImages();
                }
            }
        }
    }

    public void restoreImages() {
        if (this.mImageHttpPrefetchers != null) {
            Iterator it = this.mImageHttpPrefetchers.iterator();
            while (it.hasNext()) {
                ((ImageHttpPrefetcher) it.next()).resumePrefetching();
            }
        }
        if (this.mContainer != null && this.mContainer.getChildCount() > 0) {
            for (int i = 0; i < this.mContainer.getChildCount(); i++) {
                if (this.mContainer.getChildAt(i) instanceof ImageRestorable) {
                    ((ImageRestorable) this.mContainer.getChildAt(i)).restoreImages();
                }
            }
        }
    }

    public static int getRowTitleHeight() {
        return WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_row_title_height);
    }

    public void handleSuccess(int i, int i2, int i3) {
        updateView(i, i2, i3);
    }

    public void handleFailure(int i) {
        this.mTotalRowsFailed++;
        hideRow(i);
    }
}
