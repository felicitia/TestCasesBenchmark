package com.contextlogic.wish.activity.wishpartner.cashout;

import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.wishpartner.cashout.WishPartnerChangeEmailDialogFragment.ChangeCashOutOptionsListener;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishPartnerSummary.CashOutOption;
import com.contextlogic.wish.api.model.WishPartnerSummary.WishPartnerCashOutInfo;
import com.contextlogic.wish.api.model.WishPartnerSummary.WishPartnerCashOutOption;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.bottomsheet.SuccessBottomSheetDialog;
import com.contextlogic.wish.dialog.bottomsheet.SuccessBottomSheetDialog.SuccessBottomSheetDialogDismissCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import com.contextlogic.wish.ui.text.ThemedEditText;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;
import java.util.Iterator;

public class WishPartnerCashOutFragment extends UiFragment<WishPartnerCashOutActivity> implements ChangeCashOutOptionsListener {
    private ThemedTextView mAmountAvailableText;
    /* access modifiers changed from: private */
    public ThemedEditText mAmountEditText;
    private ThemedTextView mCashOutOptionsHeader;
    /* access modifiers changed from: private */
    public CashOutOption mChoice;
    /* access modifiers changed from: private */
    public String mPaypalId;
    /* access modifiers changed from: private */
    public AppCompatRadioButton mPaypalRadio;
    private ThemedTextView mTitleText;
    /* access modifiers changed from: private */
    public WishPartnerCashOutInfo mWishCashOutInfo;
    /* access modifiers changed from: private */
    public AppCompatRadioButton mWishCashRadio;
    private ThemedTextView mWithdrawButton;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.wish_partner_cash_out_info_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mChoice = CashOutOption.WISH_CASH;
        withActivity(new ActivityTask<WishPartnerCashOutActivity>() {
            public void performTask(WishPartnerCashOutActivity wishPartnerCashOutActivity) {
                WishPartnerCashOutFragment.this.mWishCashOutInfo = wishPartnerCashOutActivity.getPartnerCashOutInfo();
            }
        });
        this.mTitleText = (ThemedTextView) findViewById(R.id.wish_partner_cash_out_title_text);
        this.mAmountAvailableText = (ThemedTextView) findViewById(R.id.wish_partner_cash_out_amount_available);
        this.mCashOutOptionsHeader = (ThemedTextView) findViewById(R.id.wish_partner_cash_out_options_header);
        this.mPaypalId = null;
        loadData();
    }

    private void loadData() {
        this.mTitleText.setText(this.mWishCashOutInfo.getCashOutAmountText());
        this.mAmountAvailableText.setText(this.mWishCashOutInfo.getCashOutRemainingText());
        this.mCashOutOptionsHeader.setText(this.mWishCashOutInfo.getCashoutOptionsHeader());
        this.mAmountEditText = (ThemedEditText) findViewById(R.id.wish_partner_cash_out_amount_input);
        this.mWishCashRadio = (AppCompatRadioButton) findViewById(R.id.wish_partner_cash_out_radio_button_wish_cash);
        this.mPaypalRadio = (AppCompatRadioButton) findViewById(R.id.wish_partner_cash_out_radio_button_paypal);
        Iterator it = this.mWishCashOutInfo.getCashOutOptions().iterator();
        while (it.hasNext()) {
            WishPartnerCashOutOption wishPartnerCashOutOption = (WishPartnerCashOutOption) it.next();
            if (wishPartnerCashOutOption.getCashOutOption() == CashOutOption.PAYPAL) {
                ((ThemedTextView) findViewById(R.id.wish_partner_cash_out_paypal_name)).setText(wishPartnerCashOutOption.getName());
                if (wishPartnerCashOutOption.getBonusText() != null) {
                    ThemedTextView themedTextView = (ThemedTextView) findViewById(R.id.wish_partner_cash_out_bonus_text_paypal);
                    themedTextView.setVisibility(0);
                    themedTextView.setText(wishPartnerCashOutOption.getBonusText());
                }
                this.mPaypalRadio.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (WishPartnerCashOutFragment.this.mPaypalRadio.isChecked()) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_CASH_OUT_PAYPAL_RADIO);
                            WishPartnerCashOutFragment.this.mChoice = CashOutOption.PAYPAL;
                            WishPartnerCashOutFragment.this.mWishCashRadio.setChecked(false);
                        }
                    }
                });
                ThemedTextView themedTextView2 = (ThemedTextView) findViewById(R.id.wish_partner_cash_out_change_text);
                ThemedTextView themedTextView3 = (ThemedTextView) findViewById(R.id.wish_partner_cash_out_display_text);
                if (wishPartnerCashOutOption.getDefaultPaymentAccount() != null) {
                    themedTextView3.setText(wishPartnerCashOutOption.getDefaultPaymentAccount().getDisplayText());
                    themedTextView2.setText(R.string.change);
                    themedTextView2.setVisibility(0);
                    themedTextView3.setTextColor(getResources().getColor(R.color.text_primary));
                    this.mPaypalId = wishPartnerCashOutOption.getDefaultPaymentAccount().getId();
                } else {
                    themedTextView3.setText(R.string.no_email);
                    themedTextView3.setTextColor(getResources().getColor(R.color.gray3));
                    themedTextView2.setVisibility(0);
                    themedTextView2.setText(R.string.add_email);
                }
                themedTextView2.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_CASH_OUT_PAYPAL_SETUP_ACCOUNT);
                        WishPartnerCashOutFragment.this.openChangeEmailDialogFragment();
                    }
                });
                ThemedTextView themedTextView4 = (ThemedTextView) findViewById(R.id.wish_partner_cash_out_right_text);
                if (themedTextView4 != null && !TextUtils.isEmpty(wishPartnerCashOutOption.getRightText())) {
                    themedTextView4.setText(wishPartnerCashOutOption.getRightText());
                }
            }
            if (wishPartnerCashOutOption.getCashOutOption() == CashOutOption.WISH_CASH) {
                ((ThemedTextView) findViewById(R.id.wish_partner_cash_out_wish_cash_name)).setText(wishPartnerCashOutOption.getName());
                if (wishPartnerCashOutOption.getBonusText() != null) {
                    ThemedTextView themedTextView5 = (ThemedTextView) findViewById(R.id.wish_partner_cash_out_bonus_text_wish_cash);
                    themedTextView5.setVisibility(0);
                    themedTextView5.setText(wishPartnerCashOutOption.getBonusText());
                }
                this.mWishCashRadio.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (WishPartnerCashOutFragment.this.mWishCashRadio.isChecked()) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_CASH_OUT_WISH_CASH_RADIO);
                            WishPartnerCashOutFragment.this.mChoice = CashOutOption.WISH_CASH;
                            WishPartnerCashOutFragment.this.mPaypalRadio.setChecked(false);
                        }
                    }
                });
            }
        }
        this.mWithdrawButton = (ThemedTextView) findViewById(R.id.wish_partner_cash_out_withdraw_cash_button);
        this.mWithdrawButton.setText(this.mWishCashOutInfo.getCashoutWithdrawButtonText());
        this.mWithdrawButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_CASH_OUT_WITHDRAW_BUTTON);
                if (WishPartnerCashOutFragment.this.mChoice != CashOutOption.PAYPAL) {
                    WishPartnerCashOutFragment.this.showConfirmDialog(WishPartnerCashOutFragment.this.mChoice);
                } else if (WishPartnerCashOutFragment.this.mPaypalId == null) {
                    WishPartnerCashOutFragment.this.handleLoadingFailed(WishPartnerCashOutFragment.this.getString(R.string.set_up_paypal_warning));
                } else {
                    WishPartnerCashOutFragment.this.showConfirmDialog(WishPartnerCashOutFragment.this.mChoice);
                }
            }
        });
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_WISH_PARTNER_CASH_OUT_PAGE);
    }

    /* access modifiers changed from: private */
    public void showConfirmDialog(final CashOutOption cashOutOption) {
        withActivity(new ActivityTask<WishPartnerCashOutActivity>() {
            public void performTask(WishPartnerCashOutActivity wishPartnerCashOutActivity) {
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(0, WishPartnerCashOutFragment.this.mWishCashOutInfo.getCashOutConfirmModalButtonConfirm(), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(1, WishPartnerCashOutFragment.this.mWishCashOutInfo.getCashOutConfirmModalButtonCancel(), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                ArrayList arrayList = new ArrayList();
                arrayList.add(multiButtonDialogChoice);
                arrayList.add(multiButtonDialogChoice2);
                final MultiButtonDialogFragment build = new MultiButtonDialogFragmentBuilder().setTitle(WishPartnerCashOutFragment.this.mWishCashOutInfo.getCashOutConfirmModalTitle()).setSubTitle(WishPartnerCashOutFragment.this.mWishCashOutInfo.getCashOutConfirmModalMessage()).hideXButton().setButtons(arrayList).build();
                wishPartnerCashOutActivity.startDialog(build, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 1) {
                            build.cancel();
                        } else if (i != 0) {
                        } else {
                            if (cashOutOption == CashOutOption.PAYPAL) {
                                WishPartnerCashOutFragment.this.withServiceFragment(new ServiceTask<WishPartnerCashOutActivity, WishPartnerCashOutServiceFragment>() {
                                    public void performTask(WishPartnerCashOutActivity wishPartnerCashOutActivity, WishPartnerCashOutServiceFragment wishPartnerCashOutServiceFragment) {
                                        String obj = WishPartnerCashOutFragment.this.mAmountEditText.getText().toString();
                                        if (TextUtils.isEmpty(obj)) {
                                            WishPartnerCashOutFragment.this.handleLoadingFailed(WishPartnerCashOutFragment.this.getString(R.string.please_enter_an_amount));
                                        } else {
                                            wishPartnerCashOutServiceFragment.cashOutWithPaypal(obj, WishPartnerCashOutFragment.this.mPaypalId);
                                        }
                                    }
                                });
                            } else {
                                WishPartnerCashOutFragment.this.withServiceFragment(new ServiceTask<WishPartnerCashOutActivity, WishPartnerCashOutServiceFragment>() {
                                    public void performTask(WishPartnerCashOutActivity wishPartnerCashOutActivity, WishPartnerCashOutServiceFragment wishPartnerCashOutServiceFragment) {
                                        String obj = WishPartnerCashOutFragment.this.mAmountEditText.getText().toString();
                                        if (TextUtils.isEmpty(obj)) {
                                            WishPartnerCashOutFragment.this.handleLoadingFailed(WishPartnerCashOutFragment.this.getString(R.string.please_enter_an_amount));
                                        } else {
                                            wishPartnerCashOutServiceFragment.cashOutWithWishCash(obj);
                                        }
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void openChangeEmailDialogFragment() {
        final WishPartnerChangeEmailDialogFragment wishPartnerChangeEmailDialogFragment = new WishPartnerChangeEmailDialogFragment();
        wishPartnerChangeEmailDialogFragment.setListener(this);
        withActivity(new ActivityTask<WishPartnerCashOutActivity>() {
            public void performTask(WishPartnerCashOutActivity wishPartnerCashOutActivity) {
                wishPartnerCashOutActivity.startDialog(wishPartnerChangeEmailDialogFragment);
            }
        });
    }

    public void handleLoadingSuccess(final String str, final String str2) {
        withActivity(new ActivityTask<WishPartnerCashOutActivity>() {
            public void performTask(final WishPartnerCashOutActivity wishPartnerCashOutActivity) {
                SuccessBottomSheetDialog.create(wishPartnerCashOutActivity).setTitle(str).setMessage(str2).setDismissCallback(new SuccessBottomSheetDialogDismissCallback() {
                    public void onDismiss() {
                        wishPartnerCashOutActivity.finish();
                    }
                }).show();
            }
        });
    }

    public void handleLoadingFailed(final String str) {
        withActivity(new ActivityTask<WishPartnerCashOutActivity>() {
            public void performTask(WishPartnerCashOutActivity wishPartnerCashOutActivity) {
                wishPartnerCashOutActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
            }
        });
    }

    public void onChangedCashOutOptions(ArrayList<WishPartnerCashOutOption> arrayList, final String str, final String str2) {
        withActivity(new ActivityTask<WishPartnerCashOutActivity>() {
            public void performTask(WishPartnerCashOutActivity wishPartnerCashOutActivity) {
                SuccessBottomSheetDialog.create(wishPartnerCashOutActivity).setTitle(str).setMessage(str2).show();
            }
        });
        this.mWishCashOutInfo.setCashOutOptions(arrayList);
        loadData();
    }
}
