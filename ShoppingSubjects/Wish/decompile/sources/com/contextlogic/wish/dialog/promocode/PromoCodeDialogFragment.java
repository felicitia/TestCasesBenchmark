package com.contextlogic.wish.dialog.promocode;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.cart.CartServiceFragment;
import com.contextlogic.wish.activity.login.LoginFormEditText;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.KeyboardUtil;

public class PromoCodeDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> implements PromoCodeDialog {
    /* access modifiers changed from: private */
    public ThemedButton mApplyCouponButton;
    /* access modifiers changed from: private */
    public LoginFormEditText mCouponCodeEditText;
    /* access modifiers changed from: private */
    public boolean mError;
    /* access modifiers changed from: private */
    public View mLoadingSpinnerContainer;
    /* access modifiers changed from: private */
    public ThemedTextView mMessage;

    public int getGravity() {
        return 80;
    }

    /* access modifiers changed from: protected */
    public boolean requiresKeyboard() {
        return true;
    }

    public static PromoCodeDialogFragment createPromoCodeDialogFragment() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_PROMO_CODE_DIALOG);
        return new PromoCodeDialogFragment();
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.cart_fragment_cart_items_footer_promo_code, viewGroup, false);
        this.mCouponCodeEditText = (LoginFormEditText) inflate.findViewById(R.id.promo_code_input);
        this.mMessage = (ThemedTextView) inflate.findViewById(R.id.promo_code_message);
        this.mLoadingSpinnerContainer = inflate.findViewById(R.id.loading_spinner_container);
        this.mApplyCouponButton = (ThemedButton) inflate.findViewById(R.id.promo_code_apply_button);
        if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
            View findViewById = this.mLoadingSpinnerContainer.findViewById(R.id.webview_fragment_primary_progress_bar);
            View findViewById2 = this.mLoadingSpinnerContainer.findViewById(R.id.webview_fragment_three_dot_progress_bar);
            if (!(findViewById2 == null || findViewById == null)) {
                findViewById.setVisibility(8);
                findViewById2.setVisibility(0);
            }
        }
        this.mApplyCouponButton = (ThemedButton) inflate.findViewById(R.id.promo_code_apply_button);
        setupApplyCouponCodeButton();
        setupCouponEditTextChangeListener();
        return inflate;
    }

    public void onResume() {
        super.onResume();
        this.mCouponCodeEditText.post(new Runnable() {
            public void run() {
                KeyboardUtil.requestKeyboardFocus(PromoCodeDialogFragment.this.mCouponCodeEditText);
            }
        });
    }

    private void setupApplyCouponCodeButton() {
        this.mApplyCouponButton.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action != 3) {
                    switch (action) {
                        case 0:
                            PromoCodeDialogFragment.this.mApplyCouponButton.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.white));
                            break;
                        case 1:
                            PromoCodeDialogFragment.this.mApplyCouponButton.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
                            break;
                    }
                } else {
                    PromoCodeDialogFragment.this.mApplyCouponButton.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
                }
                return false;
            }
        });
        this.mApplyCouponButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PromoCodeDialogFragment.this.mCouponCodeEditText.getText() != null && PromoCodeDialogFragment.this.mCouponCodeEditText.getText().length() > 0) {
                    PromoCodeDialogFragment.this.withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
                        public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                            PromoCodeDialogFragment.this.withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
                                public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                                    PromoCodeDialogFragment.this.mLoadingSpinnerContainer.setVisibility(0);
                                    cartServiceFragment.applyPromoCodeService(PromoCodeDialogFragment.this.mCouponCodeEditText.getText().toString());
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    public void handleApplyPromoCodeFailure(String str) {
        updateMessage(str, true);
        this.mError = true;
    }

    public void handleApplyPromoCodeSuccess(String str) {
        updateMessage(str, false);
    }

    public boolean isCancelable() {
        return this.mLoadingSpinnerContainer == null || this.mLoadingSpinnerContainer.getVisibility() != 0;
    }

    private void updateMessage(String str, boolean z) {
        this.mLoadingSpinnerContainer.setVisibility(8);
        if (this.mMessage != null) {
            if (z) {
                this.mMessage.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.red));
                this.mCouponCodeEditText.setError();
            } else {
                this.mMessage.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.green));
            }
            this.mMessage.setText(str);
            this.mMessage.setVisibility(0);
        }
    }

    public void setupCouponEditTextChangeListener() {
        this.mCouponCodeEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (PromoCodeDialogFragment.this.mError) {
                    PromoCodeDialogFragment.this.mError = false;
                    PromoCodeDialogFragment.this.mMessage.setVisibility(8);
                }
            }
        });
    }

    public int getDialogWidth() {
        return DisplayUtil.getDisplayWidth();
    }
}
