package com.contextlogic.wish.activity.signup.SignupFreeGift;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishSignupFreeGifts;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.grid.StaggeredGridView;
import com.contextlogic.wish.ui.grid.StaggeredGridView.OnItemClickListener;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;

public class SignupFreeGiftView extends SignupFreeGiftBaseView {
    /* access modifiers changed from: private */
    public SignupFreeGiftAdapter mAdapter;
    /* access modifiers changed from: private */
    public ThemedTextView mAppreciationTextView;
    /* access modifiers changed from: private */
    public ThemedTextView mChooseGiftTextView;
    /* access modifiers changed from: private */
    public final SignupFreeGiftFragment mFragment;
    /* access modifiers changed from: private */
    public WishSignupFreeGifts mFreeGifts;
    /* access modifiers changed from: private */
    public ImageHttpPrefetcher mImagePrefetcher;
    private ThemedTextView mNoThanksTextView;
    /* access modifiers changed from: private */
    public StaggeredGridView mProductView;
    /* access modifiers changed from: private */
    public ThemedTextView mThanksTextView;

    public SignupFreeGiftView(SignupFreeGiftActivity signupFreeGiftActivity, SignupFreeGiftFragment signupFreeGiftFragment, Bundle bundle) {
        super(signupFreeGiftFragment, signupFreeGiftActivity, bundle);
        this.mFragment = signupFreeGiftFragment;
    }

    /* access modifiers changed from: protected */
    public void initializeUi(Bundle bundle) {
        View view;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        this.mProductView = (StaggeredGridView) layoutInflater.inflate(R.layout.signup_free_gift_view, this).findViewById(R.id.fragment_signup_free_gift_feed_gridview);
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(new LayoutParams(-1, (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics())));
        if (ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            view = layoutInflater.inflate(R.layout.signup_free_gift_view_header_redesign, this.mProductView, false);
        } else {
            view = layoutInflater.inflate(R.layout.signup_free_gift_view_header, this.mProductView, false);
        }
        this.mProductView.setHeaderView(view);
        this.mProductView.setFooterView(frameLayout);
        this.mProductView.notifyDataSetChanged();
        this.mThanksTextView = (ThemedTextView) view.findViewById(R.id.signup_free_gift_view_header_thanks_textview);
        this.mAppreciationTextView = (ThemedTextView) view.findViewById(R.id.signup_free_gift_view_header_appreciation_textview);
        this.mChooseGiftTextView = (ThemedTextView) view.findViewById(R.id.signup_free_gift_view_header_choose_gift_textview);
        this.mNoThanksTextView = (ThemedTextView) view.findViewById(R.id.signup_free_gift_view_header_no_thanks_textview);
        this.mNoThanksTextView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignupFreeGiftView.this.handleCancel();
            }
        });
        if (ExperimentDataCenter.getInstance().shouldSeeFreeGiftBanner()) {
            view.findViewById(R.id.choose_gift_header).setVisibility(0);
        }
        initializeValues(bundle);
    }

    private void initializeValues(Bundle bundle) {
        if (bundle != null) {
            this.mFreeGifts = (WishSignupFreeGifts) StateStoreCache.getInstance().getParcelable(bundle, "SavedStateData", WishSignupFreeGifts.class);
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        if (this.mFreeGifts != null) {
            bundle.putString("SavedStateData", StateStoreCache.getInstance().storeParcelable(this.mFreeGifts));
        }
    }

    public void setupFreeGifts(final WishSignupFreeGifts wishSignupFreeGifts) {
        this.mImagePrefetcher = new ImageHttpPrefetcher();
        this.mFragment.withActivity(new ActivityTask<SignupFreeGiftActivity>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                SignupFreeGiftView.this.mFreeGifts = wishSignupFreeGifts;
                SignupFreeGiftView.this.mAdapter = new SignupFreeGiftAdapter(signupFreeGiftActivity, SignupFreeGiftView.this, SignupFreeGiftView.this.mFragment);
                SignupFreeGiftView.this.mAdapter.setImagePrefetcher(SignupFreeGiftView.this.mImagePrefetcher);
                SignupFreeGiftView.this.mProductView.setAdapter(SignupFreeGiftView.this.mAdapter);
                SignupFreeGiftView.this.mProductView.setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(int i, View view) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_CLAIM_BUTTON);
                        final WishProduct wishProduct = (WishProduct) SignupFreeGiftView.this.mFreeGifts.getSignupGiftProducts().get(i);
                        SignupFreeGiftView.this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, SignupFreeGiftServiceFragment>() {
                            public void performTask(BaseActivity baseActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                                signupFreeGiftServiceFragment.onProductSelected(wishProduct);
                            }
                        });
                    }
                });
                SignupFreeGiftView.this.mProductView.notifyDataSetChanged();
                SignupFreeGiftView.this.mAdapter.notifyDataSetChanged();
                SignupFreeGiftView.this.mThanksTextView.setText(SignupFreeGiftView.this.mFreeGifts.getTitle());
                SignupFreeGiftView.this.mAppreciationTextView.setText(SignupFreeGiftView.this.mFreeGifts.getSubtitle());
                SignupFreeGiftView.this.mChooseGiftTextView.setText(SignupFreeGiftView.this.mFreeGifts.getMessage());
            }
        });
    }

    public void refreshUi() {
        if (this.mFragment.getFreeGifts() != null) {
            setupFreeGifts(this.mFragment.getFreeGifts());
        }
    }

    public void releaseImages() {
        if (this.mProductView != null) {
            this.mProductView.releaseImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.cancelPrefetching();
        }
    }

    public void restoreImages() {
        if (this.mProductView != null) {
            this.mProductView.restoreImages();
        }
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.resumePrefetching();
        }
    }

    public void recycle() {
        releaseImages();
    }

    /* access modifiers changed from: private */
    public void handleCancel() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_CLAIM_CANCEL_BUTTON);
        if (this.mFreeGifts == null || this.mFreeGifts.getAbandonInfo() == null) {
            handleClose();
            return;
        }
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_FREE_GIFTS_ABANDONMENT_MODAL);
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, SignupFreeGiftServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                signupFreeGiftServiceFragment.showFreeGiftAbandonDialog(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_ABANDONMENT_MODAL_RETURN, WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_ABANDONMENT_MODAL_PROCEED);
            }
        });
    }

    public boolean onBackPressed() {
        return !this.mFragment.startedFromNotification();
    }

    public ArrayList<WishProduct> getProducts() {
        return this.mFreeGifts.getSignupGiftProducts();
    }

    public void handleClose() {
        this.mFragment.withActivity(new ActivityTask<SignupFreeGiftActivity>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                SignupFreeGiftView.this.mFragment.handleClose();
            }
        });
    }
}
