package com.contextlogic.wish.activity.productdetails;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.Theme;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.IntentUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ProductDetailsActivity extends DrawerActivity {
    public String getMenuKey() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void initializeActionBarManager(ActionBarManager actionBarManager) {
        super.initializeActionBarManager(actionBarManager);
        if (getProductSource() == Source.BRANDED) {
            actionBarManager.setTheme(Theme.WHITE_BACKGROUND);
        }
        if (ExperimentDataCenter.getInstance().shouldShowProductDetailTransition()) {
            actionBarManager.setFadeToTheme(actionBarManager.getTheme(), 0);
            actionBarManager.setTheme(Theme.TRANSPARENT_BACKGROUND_LIGHT_BUTTONS);
            getActionBarManager().setHideStatusbar(true);
            updateToolBarPadding();
        }
    }

    /* access modifiers changed from: protected */
    public void initializeCoreUi(Bundle bundle) {
        super.initializeCoreUi(bundle);
        if (bundle == null && ExperimentDataCenter.getInstance().shouldShowFeedToProductDetailTransition() && getTransitionImageUrl() != null) {
            supportPostponeEnterTransition();
        }
        updateToolBarPadding();
    }

    private void updateToolBarPadding() {
        View toolbarContainer = getToolbarContainer();
        if (toolbarContainer != null) {
            toolbarContainer.setPadding(0, getActionBarManager().isStatusBarHidden() ? DisplayUtil.getStatusBarHeight() : 0, 0, 0);
        }
    }

    public void setThemeLoadSuccess() {
        getActionBarManager().interpolateBackground(0.0f);
        getActionBarManager().setHideStatusbar(true);
        updateToolBarPadding();
    }

    public void setThemeLoadFailure() {
        getActionBarManager().interpolateBackground(1.0f);
        getActionBarManager().setHideStatusbar(false);
        updateToolBarPadding();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new ProductDetailsServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new ProductDetailsFragment();
    }

    public WishProduct getInitialProduct() {
        return (WishProduct) IntentUtil.getParcelableExtra(getIntent(), "ArgProduct");
    }

    public String getTransitionImageUrl() {
        return getIntent().getStringExtra("ArgExtraLTransitionImageUrl");
    }

    public static void addInitialProduct(Intent intent, WishProduct wishProduct) {
        if (intent != null && wishProduct != null) {
            WishProduct wishProduct2 = new WishProduct(wishProduct.getProductId(), wishProduct.getImage(), wishProduct.getValue(), wishProduct.getAspectRatio());
            IntentUtil.putParcelableExtra(intent, "ArgProduct", wishProduct2);
        }
    }

    public HashMap<String, String> getExtraInfo() {
        return (HashMap) getIntent().getSerializableExtra("ArgExtraInfo");
    }

    public Source getProductSource() {
        Source source = (Source) getIntent().getSerializableExtra("ArgExtraSource");
        return source == null ? Source.DEFAULT : source;
    }

    public String getDealDashCouponCode() {
        return getIntent().getStringExtra("ArgDealDashCouponCode");
    }

    public String getDealDashPercentOff() {
        return getIntent().getStringExtra("ArgDealDashPercentOffString");
    }

    public Date getDealDashTargetDate() {
        return (Date) getIntent().getSerializableExtra("ArgExtraDealDashTime");
    }

    public boolean shouldSmoothScrollToPriceChop() {
        return getIntent().getBooleanExtra("ArgExtraScrollToPriceChop", false);
    }

    public int getAvailableRewardsPoints() {
        return getIntent().getIntExtra("ArgExtraAvailableRewardsPoints", -1);
    }

    public String getProductRatingId() {
        return getIntent().getStringExtra("ArgExtraProductRatingId");
    }

    public String getActionBarTitle() {
        return getString(R.string.details);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.PRODUCT_DETAILS;
    }

    /* access modifiers changed from: protected */
    public void showAuthenticatingView() {
        if (ExperimentDataCenter.getInstance().shouldShowFeedToProductDetailTransition()) {
            lockDrawers(true);
        } else {
            super.showAuthenticatingView();
        }
    }

    public void showShareDialog(String str, String str2) {
        try {
            Intent shareIntent = IntentUtil.getShareIntent(str, str2);
            if (shareIntent != null) {
                startActivity(shareIntent);
            }
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (IntentUtil.safeToUnparcel(intent) && intent.getBooleanExtra("ExtraRequiresReload", false)) {
            ((ProductDetailsFragment) getUiFragment("FragmentTagMainContent")).getLoadingPageView().reload();
        }
    }

    public static void startActivityWithTransition(BaseActivity baseActivity, NetworkImageView networkImageView, WishProduct wishProduct, Source source, ActivityResultCallback activityResultCallback) {
        Intent intent = new Intent();
        intent.setClass(baseActivity, ProductDetailsActivity.class);
        if (source != null) {
            intent.putExtra("ArgExtraSource", source);
        }
        addInitialProduct(intent, wishProduct);
        Bundle bundle = new Bundle();
        if (networkImageView != null && networkImageView.getDrawable() != null && VERSION.SDK_INT >= 22 && ExperimentDataCenter.getInstance().shouldShowFeedToProductDetailTransition()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new Pair(networkImageView, baseActivity.getString(R.string.shared_element_product_details_image)));
            View findViewById = baseActivity.getWindow().findViewById(16908336);
            if (findViewById != null) {
                arrayList.add(new Pair(findViewById, "android:navigation:background"));
            }
            intent.putExtra("ArgExtraLTransitionImageUrl", networkImageView.getLastFetchedUrl());
            bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(baseActivity, (Pair[]) arrayList.toArray(new Pair[arrayList.size()])).toBundle();
        }
        if (activityResultCallback != null) {
            baseActivity.startActivityForResult(intent, baseActivity.addResultCodeCallback(activityResultCallback), bundle);
        } else {
            baseActivity.startActivity(intent, bundle);
        }
    }

    /* access modifiers changed from: protected */
    public ActivityAnimationTypes getDefaultActivityAnimation() {
        return getProductSource() == Source.POINTS_REDEMPTION ? ActivityAnimationTypes.SLIDE_UP : super.getDefaultActivityAnimation();
    }
}
