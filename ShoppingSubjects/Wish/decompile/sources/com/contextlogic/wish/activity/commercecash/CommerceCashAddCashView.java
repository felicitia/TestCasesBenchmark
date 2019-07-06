package com.contextlogic.wish.activity.commercecash;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCommerceCashSpecs;
import com.contextlogic.wish.api.model.WishCommerceCashSpecs.PurchaseOption;
import com.contextlogic.wish.api.model.WishCommerceCashUserInfo;
import com.contextlogic.wish.ui.scrollview.ObservableScrollView;
import com.contextlogic.wish.ui.scrollview.ObservableScrollView.ScrollViewListener;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.Iterator;

public class CommerceCashAddCashView extends CommerceCashPagerView {
    private LinearLayout mAddCashOptions;
    private ThemedTextView mAddCashTitleText;
    private ThemedTextView mBonusOfferDescription;
    private ThemedTextView mBonusOfferTitle;
    private CommerceCashFragment mFragment;
    private LinearLayout mOneTimeOfferBanner;
    private ThemedTextView mPayNearMeBonusOfferDescription;
    private ThemedTextView mPayNearMeBonusOfferTitle;
    private LinearLayout mPayNearMeOneTimeOfferBanner;
    private ObservableScrollView mScroller;
    private WishCommerceCashSpecs mSpecs;
    private WishCommerceCashUserInfo mUserInfo;

    public int getLoadingContentLayoutResourceId() {
        return R.layout.commerce_cash_fragment_add_cash;
    }

    public CommerceCashAddCashView(Context context) {
        super(context);
    }

    public boolean hasItems() {
        return isLoadingComplete();
    }

    public void handleReload() {
        super.handleReload();
        this.mSpecs = null;
        this.mUserInfo = null;
        this.mFragment.loadPages();
    }

    public void setup(int i, CommerceCashFragment commerceCashFragment) {
        super.setup(i, commerceCashFragment);
        this.mFragment = commerceCashFragment;
        this.mScroller = (ObservableScrollView) this.mRootLayout.findViewById(R.id.commerce_cash_fragment_add_cash_scroller);
        this.mScroller.setScrollViewListener(new ScrollViewListener() {
            public void onScrollChanged(int i, int i2) {
                CommerceCashAddCashView.this.handleScrollChanged(i, i2);
            }
        });
        setupScroller(this.mScroller);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.commerce_cash_fragment_add_cash_container);
        this.mAddCashTitleText = (ThemedTextView) findViewById(R.id.commerce_cash_fragment_add_cash_title);
        this.mOneTimeOfferBanner = (LinearLayout) findViewById(R.id.commerce_cash_fragment_one_time_offer_banner);
        this.mBonusOfferTitle = (ThemedTextView) findViewById(R.id.commerce_cash_fragment_bonus_offer_title);
        this.mBonusOfferDescription = (ThemedTextView) findViewById(R.id.commerce_cash_fragment_bonus_offer_description);
        this.mPayNearMeOneTimeOfferBanner = (LinearLayout) findViewById(R.id.commerce_cash_fragment_paynearme_one_time_offer_banner);
        this.mPayNearMeBonusOfferTitle = (ThemedTextView) findViewById(R.id.commerce_cash_fragment_paynearme_bonus_offer_title);
        this.mPayNearMeBonusOfferDescription = (ThemedTextView) findViewById(R.id.commerce_cash_fragment_paynearme_bonus_offer_description);
        this.mAddCashOptions = (LinearLayout) findViewById(R.id.commerce_cash_fragment_add_cash_options);
        if (ExperimentDataCenter.getInstance().shouldSeePayNearMe()) {
            findViewById(R.id.commerce_cash_fragment_store_logo_container).setVisibility(0);
        }
        linearLayout.setPadding(linearLayout.getPaddingLeft(), this.mFragment.getTabAreaSize(), linearLayout.getPaddingRight(), linearLayout.getPaddingLeft());
    }

    private void setupHeader() {
        if (this.mSpecs != null) {
            this.mAddCashTitleText.setText(this.mSpecs.getMainDescription());
            if (!this.mSpecs.hasBonus()) {
                this.mOneTimeOfferBanner.setVisibility(8);
                this.mPayNearMeOneTimeOfferBanner.setVisibility(8);
            } else if (ExperimentDataCenter.getInstance().shouldSeePayNearMe()) {
                this.mPayNearMeOneTimeOfferBanner.setVisibility(0);
                this.mPayNearMeBonusOfferTitle.setText(this.mSpecs.getBonusTitle());
                this.mPayNearMeBonusOfferDescription.setText(this.mSpecs.getBonusDescription());
            } else {
                this.mOneTimeOfferBanner.setVisibility(0);
                this.mBonusOfferTitle.setText(this.mSpecs.getBonusTitle());
                this.mBonusOfferDescription.setText(this.mSpecs.getBonusDescription());
            }
        }
    }

    private void setupOptions() {
        if (this.mSpecs != null) {
            this.mAddCashOptions.removeAllViews();
            Iterator it = this.mSpecs.getPurchaseOptions().iterator();
            while (it.hasNext()) {
                PurchaseOption purchaseOption = (PurchaseOption) it.next();
                AddCashOptionView addCashOptionView = new AddCashOptionView(getContext(), this.mFragment);
                addCashOptionView.setOption(purchaseOption, this.mUserInfo);
                this.mAddCashOptions.addView(addCashOptionView);
            }
        }
    }

    public void handleLoadingSuccess(WishCommerceCashSpecs wishCommerceCashSpecs, WishCommerceCashUserInfo wishCommerceCashUserInfo) {
        if (wishCommerceCashSpecs != null && wishCommerceCashUserInfo != null) {
            this.mSpecs = wishCommerceCashSpecs;
            this.mUserInfo = wishCommerceCashUserInfo;
            setupHeader();
            setupOptions();
            markLoadingComplete();
        }
    }

    public void handleLoadingFailure() {
        markLoadingErrored();
    }

    public int getCurrentScrollY() {
        if (this.mScroller != null) {
            return this.mScroller.getScrollY();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void setupScroller(View view) {
        this.mPagerHelper.setupScroller(view);
    }

    public void scrollPage(int i) {
        this.mScroller.scrollBy(0, -i);
    }
}
