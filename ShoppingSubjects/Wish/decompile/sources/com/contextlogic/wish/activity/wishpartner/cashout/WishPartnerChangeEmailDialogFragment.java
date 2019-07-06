package com.contextlogic.wish.activity.wishpartner.cashout;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishPartnerSummary.WishPartnerCashOutOption;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.text.ThemedEditText;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;

public class WishPartnerChangeEmailDialogFragment extends BaseDialogFragment {
    private ThemedTextView mActionButton;
    private ImageView mCancelButton;
    /* access modifiers changed from: private */
    public Step mCurrentStep;
    /* access modifiers changed from: private */
    public String mEmail;
    /* access modifiers changed from: private */
    public ThemedTextView mErrorMessage;
    /* access modifiers changed from: private */
    public ThemedEditText mInput;
    /* access modifiers changed from: private */
    public ThemedEditText mInput2;
    private ChangeCashOutOptionsListener mListener;
    private ThemedTextView mSubActionButton;
    private ThemedTextView mSubtitle;
    private ThemedTextView mSubtitle2;
    private ThemedTextView mTitle;

    public interface ChangeCashOutOptionsListener {
        void onChangedCashOutOptions(ArrayList<WishPartnerCashOutOption> arrayList, String str, String str2);
    }

    private enum Step {
        ADD_EMAIL,
        VERIFY,
        ALREADY_HAVE_VERIFY
    }

    public boolean isCancelable() {
        return false;
    }

    public void setListener(ChangeCashOutOptionsListener changeCashOutOptionsListener) {
        this.mListener = changeCashOutOptionsListener;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.wish_partner_change_email_dialog_fragment, viewGroup, false);
        this.mCurrentStep = Step.ADD_EMAIL;
        this.mCancelButton = (ImageView) inflate.findViewById(R.id.wish_partner_dialog_fragment_x_button);
        this.mCancelButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_CASH_OUT_PAYPAL_SETUP_ACCOUNT_X);
                WishPartnerChangeEmailDialogFragment.this.cancel();
            }
        });
        this.mActionButton = (ThemedTextView) inflate.findViewById(R.id.wish_partner_dialog_fragment_action_button);
        this.mSubActionButton = (ThemedTextView) inflate.findViewById(R.id.wish_partner_dialog_fragment_sub_action);
        this.mInput = (ThemedEditText) inflate.findViewById(R.id.wish_partner_dialog_fragment_input);
        this.mInput2 = (ThemedEditText) inflate.findViewById(R.id.wish_partner_dialog_fragment_input_2);
        this.mSubtitle = (ThemedTextView) inflate.findViewById(R.id.wish_partner_dialog_fragment_subtitle);
        this.mSubtitle2 = (ThemedTextView) inflate.findViewById(R.id.wish_partner_dialog_fragment_subtitle_2);
        this.mErrorMessage = (ThemedTextView) inflate.findViewById(R.id.wish_partner_dialog_fragment_error_message);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.wish_partner_dialog_fragment_title);
        loadStep();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_WISH_PARTNER_CASH_OUT_PAYPAL_SETUP_MODULE);
        return inflate;
    }

    public void loadStep() {
        this.mErrorMessage.setVisibility(8);
        if (this.mCurrentStep == Step.ADD_EMAIL) {
            this.mTitle.setText(R.string.set_up_account);
            this.mSubtitle.setText(R.string.please_enter_your_email);
            this.mInput.setHint(R.string.wish_partner_enter_your_email_address);
            this.mActionButton.setText(R.string.next);
            this.mActionButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_CASH_OUT_PAYPAL_SETUP_ACCOUNT_NEXT);
                    String obj = WishPartnerChangeEmailDialogFragment.this.mInput.getText().toString();
                    if (TextUtils.isEmpty(obj)) {
                        WishPartnerChangeEmailDialogFragment.this.mErrorMessage.setText(R.string.please_enter_email);
                        WishPartnerChangeEmailDialogFragment.this.mErrorMessage.setTextColor(WishPartnerChangeEmailDialogFragment.this.getResources().getColor(R.color.red));
                        WishPartnerChangeEmailDialogFragment.this.mErrorMessage.setVisibility(0);
                        return;
                    }
                    WishPartnerChangeEmailDialogFragment.this.mEmail = obj;
                    WishPartnerChangeEmailDialogFragment.this.verifyEmail(obj);
                }
            });
            this.mSubActionButton.setText(R.string.have_verification_code);
            this.mSubActionButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_ALREADY_HAVE_VERIFICATION);
                    WishPartnerChangeEmailDialogFragment.this.mCurrentStep = Step.ALREADY_HAVE_VERIFY;
                    WishPartnerChangeEmailDialogFragment.this.loadStep();
                }
            });
            this.mSubActionButton.setVisibility(0);
        } else if (this.mCurrentStep == Step.VERIFY) {
            this.mInput.setText(null);
            this.mInput.setHint(R.string.enter_verification_code);
            this.mActionButton.setText(R.string.verify);
            this.mActionButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    String obj = WishPartnerChangeEmailDialogFragment.this.mInput.getText().toString();
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_CASH_OUT_PAYPAL_SETUP_ACCOUNT_VERIFY);
                    if (TextUtils.isEmpty(obj)) {
                        WishPartnerChangeEmailDialogFragment.this.mErrorMessage.setText(WishPartnerChangeEmailDialogFragment.this.getString(R.string.please_enter_verification_code));
                        WishPartnerChangeEmailDialogFragment.this.mErrorMessage.setTextColor(WishPartnerChangeEmailDialogFragment.this.getResources().getColor(R.color.red));
                        WishPartnerChangeEmailDialogFragment.this.mErrorMessage.setVisibility(0);
                        return;
                    }
                    WishPartnerChangeEmailDialogFragment.this.storePaypalEmail(WishPartnerChangeEmailDialogFragment.this.mEmail, obj);
                }
            });
            this.mSubActionButton.setText(R.string.resend_email);
            this.mSubActionButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_CASH_OUT_PAYPAL_SETUP_ACCOUNT_RESEND_EMAIL);
                    WishPartnerChangeEmailDialogFragment.this.verifyEmail(WishPartnerChangeEmailDialogFragment.this.mEmail);
                }
            });
            this.mSubActionButton.setVisibility(0);
        } else {
            this.mInput.setText(null);
            this.mInput.setHint(R.string.wish_partner_enter_your_email_address);
            this.mSubtitle.setText(R.string.please_enter_your_email);
            this.mInput2.setVisibility(0);
            this.mInput2.setHint(R.string.enter_verification_code);
            this.mSubtitle2.setVisibility(0);
            this.mSubtitle2.setText(R.string.please_enter_your_verification_code);
            this.mActionButton.setText(R.string.verify);
            this.mActionButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    String obj = WishPartnerChangeEmailDialogFragment.this.mInput.getText().toString();
                    String obj2 = WishPartnerChangeEmailDialogFragment.this.mInput2.getText().toString();
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_CASH_OUT_PAYPAL_SETUP_ACCOUNT_VERIFY);
                    if (TextUtils.isEmpty(obj)) {
                        WishPartnerChangeEmailDialogFragment.this.mErrorMessage.setText(WishPartnerChangeEmailDialogFragment.this.getString(R.string.please_enter_email));
                        WishPartnerChangeEmailDialogFragment.this.mErrorMessage.setTextColor(WishPartnerChangeEmailDialogFragment.this.getResources().getColor(R.color.red));
                        WishPartnerChangeEmailDialogFragment.this.mErrorMessage.setVisibility(0);
                    } else if (TextUtils.isEmpty(obj2)) {
                        WishPartnerChangeEmailDialogFragment.this.mErrorMessage.setText(WishPartnerChangeEmailDialogFragment.this.getString(R.string.please_enter_verification_code));
                        WishPartnerChangeEmailDialogFragment.this.mErrorMessage.setTextColor(WishPartnerChangeEmailDialogFragment.this.getResources().getColor(R.color.red));
                        WishPartnerChangeEmailDialogFragment.this.mErrorMessage.setVisibility(0);
                    } else {
                        WishPartnerChangeEmailDialogFragment.this.storePaypalEmail(obj, obj2);
                    }
                }
            });
            this.mSubActionButton.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void storePaypalEmail(final String str, final String str2) {
        withServiceFragment(new ServiceTask<WishPartnerCashOutActivity, WishPartnerCashOutServiceFragment>() {
            public void performTask(WishPartnerCashOutActivity wishPartnerCashOutActivity, WishPartnerCashOutServiceFragment wishPartnerCashOutServiceFragment) {
                wishPartnerCashOutServiceFragment.storePaypalEmail(str, str2);
            }
        });
    }

    /* access modifiers changed from: private */
    public void verifyEmail(final String str) {
        withServiceFragment(new ServiceTask<WishPartnerCashOutActivity, WishPartnerCashOutServiceFragment>() {
            public void performTask(WishPartnerCashOutActivity wishPartnerCashOutActivity, WishPartnerCashOutServiceFragment wishPartnerCashOutServiceFragment) {
                wishPartnerCashOutServiceFragment.preverifyEmailAddress(str);
            }
        });
    }

    public void handleEmailVerified(String str, String str2) {
        if (this.mCurrentStep == Step.ADD_EMAIL) {
            this.mCurrentStep = Step.VERIFY;
            this.mTitle.setText(str);
            this.mSubtitle.setText(str2);
            loadStep();
            return;
        }
        this.mErrorMessage.setTextColor(getResources().getColor(R.color.green));
        this.mErrorMessage.setText(getString(R.string.email_sent));
        this.mErrorMessage.setVisibility(0);
    }

    public void handlePaypalEmailStored(ArrayList<WishPartnerCashOutOption> arrayList, String str, String str2) {
        if (!(this.mListener == null || arrayList == null)) {
            this.mListener.onChangedCashOutOptions(arrayList, str, str2);
        }
        cancel();
    }

    public void handleFailure(final String str) {
        withActivity(new ActivityTask<WishPartnerCashOutActivity>() {
            public void performTask(WishPartnerCashOutActivity wishPartnerCashOutActivity) {
                wishPartnerCashOutActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        WishPartnerChangeEmailDialogFragment.this.mErrorMessage.setTextColor(WishPartnerChangeEmailDialogFragment.this.getResources().getColor(R.color.red));
                        WishPartnerChangeEmailDialogFragment.this.mErrorMessage.setText(str);
                        WishPartnerChangeEmailDialogFragment.this.mErrorMessage.setVisibility(0);
                    }
                });
            }
        });
    }
}
