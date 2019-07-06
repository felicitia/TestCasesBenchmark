package com.contextlogic.wish.activity.orderconfirmed;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.LoadingUiFragment;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.activity.orderconfirmed.HorizontalProductScroller.Builder;
import com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedView.DataMode;
import com.contextlogic.wish.activity.pricewatch.PriceWatchHorizontalItemsAdapter;
import com.contextlogic.wish.activity.productdetails.DealDashSquareView;
import com.contextlogic.wish.analytics.FeedTileLogger;
import com.contextlogic.wish.analytics.FeedTileLogger.Action;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishPriceWatchSpec;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.addtocart.AddToCartDialogFragment;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.dialog.addtocart.sizecolorselector.SizeColorSelectorDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.social.facebook.FacebookManager;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.listview.HorizontalListView.OnScrollListener;
import com.contextlogic.wish.ui.listview.HorizontalListView.OnViewVisibleListener;
import com.contextlogic.wish.util.PreferenceUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class OrderConfirmedFragment extends LoadingUiFragment<OrderConfirmedActivity> {
    private boolean mAlsoBoughtProductsLoaded;
    private String mBoletoDueDate;
    private OrderConfirmedBoxView mBoxView;
    private String mCommerceLoanDueDate;
    private String mCommerceLoanOriginalTransactionId;
    private String mCommerceLoanProcessedDate;
    private boolean mDealDashResultPreloaded;
    /* access modifiers changed from: private */
    public boolean mDealDashResultReceived;
    private boolean mDealDashShouldShow;
    private OrderConfirmedView mDealDashView;
    /* access modifiers changed from: private */
    public ArrayList<GroupBuyInfo> mGroupBuyInfos;
    private ArrayList<GroupBuyConfirmedItemView> mGroupBuyItemViews;
    private boolean mGroupBuysLoaded;
    private boolean mGroupBuysLogged;
    private ImageHttpPrefetcher mImagePrefetcher;
    private HashMap<String, WishProduct> mLoadedPriceWatchProducts;
    private LinearLayout mOrderConfirmedContainer;
    private PaymentConfirmedBoxView mPaymentBoxView;
    private WishPriceWatchSpec mPriceWatchInfo;
    /* access modifiers changed from: private */
    public boolean mPriceWatchInfoReceived;
    /* access modifiers changed from: private */
    public boolean mPriceWatchScrollLogged;
    private OrderConfirmedView mPriceWatchView;
    /* access modifiers changed from: private */
    public ArrayList<ProductInfo> mProductInfos;
    private boolean mProductInfosLogged;
    private ArrayList<OrderConfirmedView> mProductViews;
    private boolean mSentAnalytics;
    private WishShippingInfo mShippingInfo;
    /* access modifiers changed from: private */
    public String mTransactionId;
    private boolean mValidationDialogShown;
    private ArrayList<OrderConfirmedView> mViews;
    /* access modifiers changed from: private */
    public HashSet<String> mVisibleProducts;
    private WishlistInfo mWishlistInfo;
    /* access modifiers changed from: private */
    public boolean mWishlistResultReceived;
    private OrderConfirmedView mWishlistView;

    private class ContinueShoppingOnClickListener implements OnClickListener {
        /* access modifiers changed from: private */
        public DataMode mDataMode;

        public ContinueShoppingOnClickListener(DataMode dataMode) {
            this.mDataMode = dataMode;
        }

        public void onClick(View view) {
            OrderConfirmedFragment.this.withActivity(new ActivityTask<OrderConfirmedActivity>() {
                public void performTask(OrderConfirmedActivity orderConfirmedActivity) {
                    Intent intent = new Intent();
                    intent.setClass(orderConfirmedActivity, BrowseActivity.class);
                    switch (ContinueShoppingOnClickListener.this.mDataMode) {
                        case ALSO_BOUGHT:
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ORDER_CONFIRMED_VIEW_ALSO_BOUGHT_BUTTON);
                            break;
                        case DEAL_DASH:
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ORDER_CONFIRMED_VIEW_DEAL_DASH_BUTTON);
                            intent.putExtra("ExtraCategoryId", "deal_dash__tab");
                            break;
                        case WISHLIST:
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ORDER_CONFIRMED_VIEW_WISHLIST_BUTTON);
                            break;
                    }
                    orderConfirmedActivity.startActivity(intent);
                }
            });
        }
    }

    public static class GroupBuyInfo implements Parcelable {
        public static final Creator<GroupBuyInfo> CREATOR = new Creator<GroupBuyInfo>() {
            public GroupBuyInfo createFromParcel(Parcel parcel) {
                return new GroupBuyInfo(parcel);
            }

            public GroupBuyInfo[] newArray(int i) {
                return new GroupBuyInfo[i];
            }
        };
        private Date mExpiry;
        private String mMessage;
        private WishLocalizedCurrencyValue mPrice;
        private WishImage mProductImage;
        private String mTitle;
        private WishImage mUserImage;
        private String mUserName;

        public int describeContents() {
            return 0;
        }

        public GroupBuyInfo(WishImage wishImage, WishImage wishImage2, Date date, String str, String str2, String str3, WishLocalizedCurrencyValue wishLocalizedCurrencyValue) {
            this.mProductImage = wishImage;
            this.mUserImage = wishImage2;
            this.mExpiry = date;
            this.mUserName = str;
            this.mMessage = str2;
            this.mTitle = str3;
            this.mPrice = wishLocalizedCurrencyValue;
        }

        public GroupBuyInfo(Parcel parcel) {
            this.mProductImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
            if (parcel.readByte() != 0) {
                this.mUserImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
            }
            if (parcel.readByte() != 0) {
                this.mExpiry = new Date(parcel.readLong());
            }
            this.mTitle = parcel.readString();
            this.mMessage = parcel.readString();
            this.mUserName = parcel.readString();
            this.mPrice = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mProductImage, i);
            int i2 = 1;
            parcel.writeByte((byte) (this.mUserImage != null ? 1 : 0));
            if (this.mUserImage != null) {
                parcel.writeParcelable(this.mUserImage, i);
            }
            if (this.mExpiry == null) {
                i2 = 0;
            }
            parcel.writeByte((byte) i2);
            if (this.mExpiry != null) {
                parcel.writeLong(this.mExpiry.getTime());
            }
            parcel.writeString(this.mTitle);
            parcel.writeString(this.mMessage);
            parcel.writeString(this.mUserName);
            parcel.writeParcelable(this.mPrice, 0);
        }

        public WishImage getProductImage() {
            return this.mProductImage;
        }

        public WishImage getUserImage() {
            return this.mUserImage;
        }

        public Date getExpiry() {
            return this.mExpiry;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getMessage() {
            return this.mMessage;
        }

        public String getUserName() {
            return this.mUserName;
        }

        public WishLocalizedCurrencyValue getPrice() {
            return this.mPrice;
        }
    }

    public static class ProductInfo implements Parcelable {
        public static final Creator<ProductInfo> CREATOR = new Creator<ProductInfo>() {
            public ProductInfo createFromParcel(Parcel parcel) {
                return new ProductInfo(parcel);
            }

            public ProductInfo[] newArray(int i) {
                return new ProductInfo[i];
            }
        };
        public WishImage mImage;
        public String mProductId;
        public String mProductName;
        public ArrayList<WishProduct> mProducts;
        public String mTitleText;

        public int describeContents() {
            return 0;
        }

        public ProductInfo(String str, String str2, WishImage wishImage, String str3, ArrayList<WishProduct> arrayList) {
            this.mTitleText = str;
            this.mProductName = str2;
            this.mImage = wishImage;
            this.mProductId = str3;
            this.mProducts = arrayList;
        }

        protected ProductInfo(Parcel parcel) {
            this.mTitleText = parcel.readString();
            this.mProductName = parcel.readString();
            this.mImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
            this.mProductId = parcel.readString();
            this.mProducts = parcel.createTypedArrayList(WishProduct.CREATOR);
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mTitleText);
            parcel.writeString(this.mProductName);
            parcel.writeParcelable(this.mImage, i);
            parcel.writeString(this.mProductId);
            parcel.writeTypedList(this.mProducts);
        }
    }

    public static class WishlistInfo implements Parcelable {
        public static final Creator<WishlistInfo> CREATOR = new Creator<WishlistInfo>() {
            public WishlistInfo createFromParcel(Parcel parcel) {
                return new WishlistInfo(parcel);
            }

            public WishlistInfo[] newArray(int i) {
                return new WishlistInfo[i];
            }
        };
        public ArrayList<WishProduct> mProducts;
        public String mTitleText;

        public int describeContents() {
            return 0;
        }

        public WishlistInfo(String str, ArrayList<WishProduct> arrayList) {
            this.mTitleText = str;
            this.mProducts = arrayList;
        }

        protected WishlistInfo(Parcel parcel) {
            this.mTitleText = parcel.readString();
            this.mProducts = parcel.createTypedArrayList(WishProduct.CREATOR);
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mTitleText);
            parcel.writeTypedList(this.mProducts);
        }
    }

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.order_confirmed_fragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mValidationDialogShown = false;
        this.mLoadedPriceWatchProducts = new HashMap<>();
        if (bundle != null) {
            this.mSentAnalytics = bundle.getBoolean("SavedStateSentAnalytics");
            this.mProductInfos = StateStoreCache.getInstance().getParcelableList(bundle, "SavedStateProductInfos", ProductInfo.class);
            this.mWishlistInfo = (WishlistInfo) StateStoreCache.getInstance().getParcelable(bundle, "SavedStateWishlistInfo", WishlistInfo.class);
            this.mDealDashResultPreloaded = bundle.getBoolean("SavedStateDealDashResultKnown");
            this.mDealDashShouldShow = bundle.getBoolean("SavedStateDealDashShouldShow");
            this.mProductInfosLogged = bundle.getBoolean("SavedStateLoggedProductInfos");
            this.mValidationDialogShown = bundle.getBoolean("SavedStateValidationDialogShown");
            this.mGroupBuyInfos = StateStoreCache.getInstance().getParcelableList(bundle, "SavedStateGroupBuyInfo", GroupBuyInfo.class);
            this.mGroupBuysLoaded = bundle.getBoolean("SavedStateGroupBuyLoaded");
            this.mGroupBuysLogged = bundle.getBoolean("SavedStateGroupBuyLogged");
            this.mShippingInfo = (WishShippingInfo) StateStoreCache.getInstance().getParcelable(bundle, "SavedStateShippingInfo", WishShippingInfo.class);
            this.mBoletoDueDate = bundle.getString("SavedStateBoletoDueDate");
            this.mCommerceLoanDueDate = bundle.getString("SavedStateLoanDate");
            this.mPriceWatchInfo = (WishPriceWatchSpec) StateStoreCache.getInstance().getParcelable(bundle, "SavedStatePriceWatchInfo", WishPriceWatchSpec.class);
            this.mPriceWatchScrollLogged = bundle.getBoolean("SavedStatePriceWatchScrollLogged");
        }
        this.mImagePrefetcher = new ImageHttpPrefetcher();
    }

    public void handleReload() {
        loadNecessaryInfo();
    }

    public void initializeLoadingContentView(View view) {
        this.mTransactionId = ((OrderConfirmedActivity) getBaseActivity()).getTransactionId();
        this.mViews = new ArrayList<>();
        this.mProductViews = new ArrayList<>();
        this.mGroupBuyItemViews = new ArrayList<>();
        this.mVisibleProducts = new HashSet<>();
        this.mOrderConfirmedContainer = (LinearLayout) view.findViewById(R.id.order_confirmed_fragment_container);
        refreshViewState();
    }

    public boolean hasItems() {
        return this.mAlsoBoughtProductsLoaded;
    }

    /* access modifiers changed from: protected */
    public void handleResume() {
        if (!this.mSentAnalytics) {
            this.mSentAnalytics = true;
            withActivity(new ActivityTask<OrderConfirmedActivity>() {
                public void performTask(OrderConfirmedActivity orderConfirmedActivity) {
                    FacebookManager.getInstance().logPurchase(orderConfirmedActivity.getTransactionCartAmount(), orderConfirmedActivity.getTransactionCurrencyCode(), orderConfirmedActivity.getTransactionCartItemIds());
                    GoogleAnalyticsLogger.getInstance().logPurchase(orderConfirmedActivity.getTransactionId(), orderConfirmedActivity.getTransactionCartAmount(), orderConfirmedActivity.getTransactionCurrencyCode());
                }
            });
        }
        if (!this.mProductViews.isEmpty() && !getLoadingPageView().isLoadingComplete()) {
            getLoadingPageView().markLoadingComplete();
        }
        if (this.mGroupBuyItemViews.isEmpty() && this.mGroupBuyInfos != null) {
            handleGroupBuysInfo(this.mGroupBuyInfos);
        }
        if (this.mProductViews.isEmpty() && this.mProductInfos != null) {
            handleAllProductInfo(this.mProductInfos, this.mShippingInfo, this.mBoletoDueDate, this.mCommerceLoanDueDate, this.mCommerceLoanProcessedDate, this.mCommerceLoanOriginalTransactionId);
        }
        if (this.mDealDashResultPreloaded && !this.mDealDashResultReceived) {
            handleDealDashStatus(this.mDealDashShouldShow);
        }
        if (!(this.mWishlistInfo == null || this.mWishlistInfo.mProducts == null || this.mWishlistResultReceived)) {
            handleWishlistHourlyDeals(this.mWishlistInfo);
        }
        if (this.mPriceWatchInfo != null && !this.mPriceWatchInfo.getItems().isEmpty() && !this.mPriceWatchInfoReceived) {
            handlePriceWatchInfoLoaded(this.mPriceWatchInfo);
        }
        loadNecessaryInfo();
    }

    private void loadNecessaryInfo() {
        if (hasItems()) {
            getLoadingPageView().clearError();
        }
        withServiceFragment(new ServiceTask<BaseActivity, OrderConfirmedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, OrderConfirmedServiceFragment orderConfirmedServiceFragment) {
                if (OrderConfirmedFragment.this.mGroupBuyInfos == null) {
                    orderConfirmedServiceFragment.loadGroupBuys(OrderConfirmedFragment.this.mTransactionId);
                }
                if (OrderConfirmedFragment.this.mProductInfos == null) {
                    orderConfirmedServiceFragment.loadAllProductInfo(OrderConfirmedFragment.this.mTransactionId);
                }
                if (!OrderConfirmedFragment.this.mDealDashResultReceived) {
                    orderConfirmedServiceFragment.loadDealDashStatus();
                }
                if (!OrderConfirmedFragment.this.mWishlistResultReceived) {
                    orderConfirmedServiceFragment.loadWishlistHourlyDeals();
                }
                if (!OrderConfirmedFragment.this.mPriceWatchInfoReceived && ExperimentDataCenter.getInstance().shouldSeePriceWatch()) {
                    orderConfirmedServiceFragment.loadPriceWatchInfo();
                }
            }
        });
    }

    private void hideAllViews() {
        Iterator it = this.mViews.iterator();
        while (it.hasNext()) {
            ((View) it.next()).setVisibility(8);
        }
    }

    private void showGroupBuyViews() {
        Iterator it = this.mGroupBuyItemViews.iterator();
        while (it.hasNext()) {
            ((View) it.next()).setVisibility(0);
        }
    }

    private void showProductViews() {
        Iterator it = this.mProductViews.iterator();
        while (it.hasNext()) {
            ((View) it.next()).setVisibility(0);
        }
    }

    private void refreshViewState() {
        hideAllViews();
        if (!this.mGroupBuyItemViews.isEmpty()) {
            showGroupBuyViews();
        } else if (!this.mGroupBuysLoaded) {
            return;
        }
        if (!this.mProductViews.isEmpty()) {
            showProductViews();
        } else if (!this.mAlsoBoughtProductsLoaded) {
            return;
        }
        if (this.mDealDashView != null) {
            this.mDealDashView.setVisibility(0);
        } else if (!this.mDealDashResultReceived) {
            return;
        }
        if (this.mWishlistView != null) {
            this.mWishlistView.setVisibility(0);
        }
        if (this.mPriceWatchView != null) {
            this.mPriceWatchView.setVisibility(0);
        }
    }

    private void finishAndShow() {
        this.mOrderConfirmedContainer.removeAllViews();
        if (this.mBoxView != null) {
            this.mOrderConfirmedContainer.addView(this.mBoxView);
        }
        if (this.mPaymentBoxView != null) {
            this.mOrderConfirmedContainer.addView(this.mPaymentBoxView);
        }
        Iterator it = this.mGroupBuyItemViews.iterator();
        while (it.hasNext()) {
            this.mOrderConfirmedContainer.addView((GroupBuyConfirmedItemView) it.next());
        }
        Iterator it2 = this.mProductViews.iterator();
        while (it2.hasNext()) {
            OrderConfirmedView orderConfirmedView = (OrderConfirmedView) it2.next();
            this.mViews.add(orderConfirmedView);
            this.mOrderConfirmedContainer.addView(orderConfirmedView);
        }
        if (this.mPriceWatchView != null) {
            this.mViews.add(this.mPriceWatchView);
            this.mOrderConfirmedContainer.addView(this.mPriceWatchView);
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_PRICE_WATCH_ORDER_CONFIRMED);
        }
        if (this.mDealDashView != null) {
            this.mViews.add(this.mDealDashView);
            this.mOrderConfirmedContainer.addView(this.mDealDashView);
        }
        if (this.mWishlistView != null) {
            this.mViews.add(this.mWishlistView);
            this.mOrderConfirmedContainer.addView(this.mWishlistView);
        }
        refreshViewState();
    }

    private void tryToMarkLoadingComplete() {
        if (this.mAlsoBoughtProductsLoaded && this.mDealDashResultReceived && this.mWishlistResultReceived && this.mGroupBuysLoaded) {
            finishAndShow();
            getLoadingPageView().markLoadingComplete();
        }
    }

    private void showInviteCouponDialog() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_INVITE_COUPON_POPUP);
        withServiceFragment(new ServiceTask<BaseActivity, OrderConfirmedServiceFragment>() {
            public void performTask(BaseActivity baseActivity, OrderConfirmedServiceFragment orderConfirmedServiceFragment) {
                orderConfirmedServiceFragment.showInviteCouponDialog();
            }
        });
    }

    public void handleGroupBuysInfo(ArrayList<GroupBuyInfo> arrayList) {
        this.mGroupBuyInfos = arrayList;
        this.mGroupBuysLoaded = true;
        if (!this.mGroupBuysLogged) {
            this.mGroupBuysLogged = true;
            for (int i = 0; i < this.mGroupBuyInfos.size(); i++) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_ORDER_CONFIRMED_GROUP_BUY);
            }
        }
        for (int i2 = 0; i2 < this.mGroupBuyInfos.size(); i2++) {
            GroupBuyInfo groupBuyInfo = (GroupBuyInfo) this.mGroupBuyInfos.get(i2);
            GroupBuyConfirmedItemView groupBuyConfirmedItemView = new GroupBuyConfirmedItemView(getContext());
            groupBuyConfirmedItemView.setup(groupBuyInfo);
            this.mGroupBuyItemViews.add(groupBuyConfirmedItemView);
        }
        tryToMarkLoadingComplete();
    }

    public void handleAllProductInfo(ArrayList<ProductInfo> arrayList, WishShippingInfo wishShippingInfo, String str, String str2, String str3, String str4) {
        this.mProductInfos = arrayList;
        this.mAlsoBoughtProductsLoaded = true;
        if (wishShippingInfo != null) {
            OrderConfirmedBoxView orderConfirmedBoxView = new OrderConfirmedBoxView(getContext(), wishShippingInfo.getFormattedString(), this.mTransactionId, str, str2, ((OrderConfirmedActivity) getBaseActivity()).hasUpfrontLoanPayment());
            this.mBoxView = orderConfirmedBoxView;
            this.mShippingInfo = wishShippingInfo;
            this.mBoletoDueDate = str;
            this.mCommerceLoanDueDate = str2;
        }
        if (!(str3 == null || str4 == null)) {
            this.mPaymentBoxView = new PaymentConfirmedBoxView(getContext(), str3, str4);
            this.mCommerceLoanProcessedDate = str3;
            this.mCommerceLoanOriginalTransactionId = str4;
            withActivity(new ActivityTask<OrderConfirmedActivity>() {
                public void performTask(OrderConfirmedActivity orderConfirmedActivity) {
                    orderConfirmedActivity.getActionBarManager().setTitle(OrderConfirmedFragment.this.getResources().getString(R.string.payment_confirmed));
                }
            });
        }
        if (!(str == null || getBaseActivity() == null || ((OrderConfirmedActivity) getBaseActivity()).getActionBarManager() == null)) {
            withActivity(new ActivityTask<OrderConfirmedActivity>() {
                public void performTask(OrderConfirmedActivity orderConfirmedActivity) {
                    orderConfirmedActivity.getActionBarManager().setTitle(OrderConfirmedFragment.this.getResources().getString(R.string.order_placed));
                }
            });
        }
        if (!this.mProductInfosLogged) {
            this.mProductInfosLogged = true;
            for (int i = 0; i < this.mProductInfos.size(); i++) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_ORDER_CONFIRMED_ALSO_BOUGHT);
            }
        }
        if (str3 == null) {
            for (int i2 = 0; i2 <= this.mProductInfos.size() - 1; i2++) {
                ProductInfo productInfo = (ProductInfo) this.mProductInfos.get(i2);
                Builder onClickListener = new Builder(getBaseActivity()).setTitle(productInfo.mTitleText).setWishImage(productInfo.mImage).setOnClickListener(new ContinueShoppingOnClickListener(DataMode.ALSO_BOUGHT));
                if (i2 == this.mProductInfos.size() - 1) {
                    onClickListener.setButtonText(getString(R.string.continue_shopping));
                }
                HorizontalProductScroller build = onClickListener.build();
                HorizontalListView listView = build.getListView();
                ProductHorizontalAdapter productHorizontalAdapter = new ProductHorizontalAdapter(getBaseActivity(), productInfo.mProducts, DataMode.ALSO_BOUGHT, productInfo.mProductId);
                listView.setAdapter(productHorizontalAdapter);
                final ArrayList<WishProduct> arrayList2 = productInfo.mProducts;
                listView.setOnViewVisibleListener(new OnViewVisibleListener() {
                    public void onViewVisible(int i, View view) {
                        WishProduct wishProduct = (WishProduct) arrayList2.get(i);
                        String productId = wishProduct.getProductId();
                        if (!OrderConfirmedFragment.this.mVisibleProducts.contains(productId)) {
                            FeedTileLogger.getInstance().addToQueue(wishProduct.getLoggingFields(), Action.IMPRESSION, i);
                            OrderConfirmedFragment.this.mVisibleProducts.add(productId);
                        }
                    }
                });
                productHorizontalAdapter.setImagePrefetcher(this.mImagePrefetcher);
                productHorizontalAdapter.attachItemClickListener(listView);
                this.mProductViews.add(build);
            }
        }
        tryToMarkLoadingComplete();
        if (ExperimentDataCenter.getInstance().canSeeInviteCouponDialog() && !PreferenceUtil.getBoolean("NeverShowInviteCouponPopup")) {
            showInviteCouponDialog();
        }
    }

    public void handleGroupBuyInfoFailed(String str) {
        this.mGroupBuysLoaded = true;
        tryToMarkLoadingComplete();
    }

    public void handleAllProductInfoFailed(String str) {
        this.mAlsoBoughtProductsLoaded = true;
        tryToMarkLoadingComplete();
    }

    public void handleDealDashStatus(boolean z) {
        this.mDealDashResultReceived = true;
        if (z) {
            this.mDealDashView = new SquareViewHolder.Builder(getBaseActivity()).setTitle(getString(R.string.dont_forget_to_spin_for_great_deals)).setButtonText(getString(R.string.go_exclamation_mark)).setBackgroundDrawableId(R.drawable.dealdash_background).setAdjustableSquareView(new DealDashSquareView(getBaseActivity())).setOnClickListener(new ContinueShoppingOnClickListener(DataMode.DEAL_DASH)).build();
        }
        tryToMarkLoadingComplete();
    }

    public void handleDealDashStatusFailed(String str) {
        getLoadingPageView().markLoadingErrored();
        showErrorDialog(str);
    }

    public void handleWishlistHourlyDeals(WishlistInfo wishlistInfo) {
        this.mWishlistResultReceived = true;
        this.mWishlistInfo = wishlistInfo;
        if (!wishlistInfo.mProducts.isEmpty()) {
            HorizontalProductScroller build = new Builder(getBaseActivity()).setTitle(wishlistInfo.mTitleText).setOnClickListener(new ContinueShoppingOnClickListener(DataMode.WISHLIST)).setButtonText(getString(R.string.continue_shopping)).setIsLastInlist(true).build();
            HorizontalListView listView = build.getListView();
            ProductHorizontalAdapter productHorizontalAdapter = new ProductHorizontalAdapter(getBaseActivity(), wishlistInfo.mProducts, DataMode.WISHLIST);
            listView.setAdapter(productHorizontalAdapter);
            productHorizontalAdapter.setImagePrefetcher(this.mImagePrefetcher);
            productHorizontalAdapter.attachItemClickListener(listView);
            this.mWishlistView = build;
        }
        tryToMarkLoadingComplete();
    }

    public void handleWishlistHourlyDealsFailed(String str) {
        getLoadingPageView().markLoadingErrored();
        showErrorDialog(str);
    }

    public void handlePriceWatchInfoLoaded(WishPriceWatchSpec wishPriceWatchSpec) {
        this.mPriceWatchInfoReceived = true;
        this.mPriceWatchInfo = wishPriceWatchSpec;
        if (!wishPriceWatchSpec.getItems().isEmpty()) {
            HorizontalProductScroller build = new Builder(getBaseActivity()).setTitle(this.mPriceWatchInfo.getTitleText()).setIsLastInlist(true).build();
            HorizontalListView listView = build.getListView();
            LayoutParams layoutParams = listView.getLayoutParams();
            layoutParams.height = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.price_watch_item_row_height);
            listView.setLayoutParams(layoutParams);
            PriceWatchHorizontalItemsAdapter priceWatchHorizontalItemsAdapter = new PriceWatchHorizontalItemsAdapter(wishPriceWatchSpec.getItems(), this);
            listView.setAdapter(priceWatchHorizontalItemsAdapter);
            listView.setOnScrollListener(new OnScrollListener() {
                public void onScrollChanged(int i, int i2, int i3, int i4) {
                    if (!OrderConfirmedFragment.this.mPriceWatchScrollLogged) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRICE_WATCH_ORDER_CONFIRMED_SCROLL);
                        OrderConfirmedFragment.this.mPriceWatchScrollLogged = true;
                    }
                }
            });
            priceWatchHorizontalItemsAdapter.setImagePrefetcher(this.mImagePrefetcher);
            this.mPriceWatchView = build;
        }
        tryToMarkLoadingComplete();
    }

    public void handlePriceWatchInfoFailed(String str) {
        getLoadingPageView().markLoadingErrored();
        showErrorDialog(str);
    }

    public void handleBuyClick(final String str) {
        if (this.mLoadedPriceWatchProducts.get(str) != null) {
            addProductToCart((WishProduct) this.mLoadedPriceWatchProducts.get(str));
        } else {
            withServiceFragment(new ServiceTask<BaseActivity, OrderConfirmedServiceFragment>() {
                public void performTask(BaseActivity baseActivity, OrderConfirmedServiceFragment orderConfirmedServiceFragment) {
                    orderConfirmedServiceFragment.getProduct(str);
                }
            });
        }
    }

    public void addProductToCart(final WishProduct wishProduct) {
        if (wishProduct != null) {
            if (!wishProduct.isInStock()) {
                withActivity(new ActivityTask<OrderConfirmedActivity>() {
                    public void performTask(OrderConfirmedActivity orderConfirmedActivity) {
                        orderConfirmedActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(orderConfirmedActivity.getString(R.string.this_item_is_out_of_stock)));
                    }
                });
            } else if (wishProduct.isCommerceProduct()) {
                final Source source = Source.DEFAULT;
                withActivity(new ActivityTask<OrderConfirmedActivity>() {
                    public void performTask(OrderConfirmedActivity orderConfirmedActivity) {
                        BaseDialogFragment baseDialogFragment;
                        if (ExperimentDataCenter.getInstance().shouldShowSizeColorSelector() || ExperimentDataCenter.getInstance().shouldShowSizeColorSelectorWithTextSwatchesOnly()) {
                            baseDialogFragment = SizeColorSelectorDialogFragment.createSizeColorSelectorDialogFragment(wishProduct, source, false);
                        } else {
                            baseDialogFragment = AddToCartDialogFragment.createAddToCartDialog(wishProduct, source, false);
                        }
                        if (baseDialogFragment != null) {
                            orderConfirmedActivity.startDialog(baseDialogFragment);
                        }
                    }
                });
            }
        }
    }

    public void handleProductLoadingSuccess(WishProduct wishProduct) {
        this.mLoadedPriceWatchProducts.put(wishProduct.getProductId(), wishProduct);
        addProductToCart(wishProduct);
    }

    public void handleLoadingFailure() {
        withActivity(new ActivityTask<OrderConfirmedActivity>() {
            public void performTask(OrderConfirmedActivity orderConfirmedActivity) {
                orderConfirmedActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(orderConfirmedActivity.getString(R.string.could_not_add_to_cart)));
            }
        });
    }

    private void showErrorDialog(final String str) {
        withActivity(new ActivityTask<OrderConfirmedActivity>() {
            public void performTask(OrderConfirmedActivity orderConfirmedActivity) {
                orderConfirmedActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
            }
        });
    }

    public void restoreImages() {
        if (this.mViews != null) {
            Iterator it = this.mViews.iterator();
            while (it.hasNext()) {
                ((OrderConfirmedView) it.next()).getListView().restoreImages();
            }
        }
        if (this.mGroupBuyItemViews != null) {
            Iterator it2 = this.mGroupBuyItemViews.iterator();
            while (it2.hasNext()) {
                ((GroupBuyConfirmedItemView) it2.next()).restoreImages();
            }
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.resumePrefetching();
        }
    }

    public void releaseImages() {
        if (this.mViews != null) {
            Iterator it = this.mViews.iterator();
            while (it.hasNext()) {
                ((OrderConfirmedView) it.next()).getListView().releaseImages();
            }
        }
        if (this.mGroupBuyItemViews != null) {
            Iterator it2 = this.mGroupBuyItemViews.iterator();
            while (it2.hasNext()) {
                ((GroupBuyConfirmedItemView) it2.next()).releaseImages();
            }
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.pausePrefetching();
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        super.handleSaveInstanceState(bundle);
        bundle.putBoolean("SavedStateSentAnalytics", this.mSentAnalytics);
        bundle.putBoolean("SavedStateDealDashResultKnown", this.mDealDashResultReceived);
        bundle.putBoolean("SavedStateDealDashShouldShow", this.mDealDashView != null);
        bundle.putBoolean("SavedStateLoggedProductInfos", this.mProductInfosLogged);
        bundle.putBoolean("SavedStateValidationDialogShown", this.mValidationDialogShown);
        bundle.putString("SavedStateProductInfos", StateStoreCache.getInstance().storeParcelableList(this.mProductInfos));
        bundle.putString("SavedStateWishlistInfo", StateStoreCache.getInstance().storeParcelable(this.mWishlistInfo));
        bundle.putString("SavedStateGroupBuyInfo", StateStoreCache.getInstance().storeParcelableList(this.mGroupBuyInfos));
        bundle.putString("SavedStateShippingInfo", StateStoreCache.getInstance().storeParcelable(this.mShippingInfo));
        bundle.putString("SavedStateBoletoDueDate", this.mBoletoDueDate);
        bundle.putString("SavedStateLoanDate", this.mCommerceLoanDueDate);
        bundle.putString("SavedStatePriceWatchInfo", StateStoreCache.getInstance().storeParcelable(this.mPriceWatchInfo));
        bundle.putBoolean("SavedStatePriceWatchScrollLogged", this.mPriceWatchScrollLogged);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mImagePrefetcher.cancelPrefetching();
    }

    public boolean isCommerceLoanRepayment() {
        return this.mCommerceLoanOriginalTransactionId != null;
    }
}
