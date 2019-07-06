package com.contextlogic.wish.dialog.cvv;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.cart.billing.creditcardform.CreditCardFormFieldsDelegate;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCreditCardInfo;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.Margin;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.CreditCardUtil;
import com.contextlogic.wish.util.CreditCardUtil.CardType;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.KeyboardUtil;
import com.contextlogic.wish.util.ViewUtil;
import java.util.Arrays;
import java.util.List;

public class CVVConfirmationDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    /* access modifiers changed from: private */
    public List<CVVConfirmationEditText> mCVVEditTexts;
    private int mCVVLength;
    private WishCreditCardInfo mCreditCardBillingInfo;

    public int getGravity() {
        return 49;
    }

    public boolean isCancelable() {
        return false;
    }

    public static CVVConfirmationDialogFragment<BaseActivity> createCVVConfirmationDialog(CartContext cartContext) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_CVV_CONFIRMATION_DIALOG);
        WishCreditCardInfo defaultCreditCardInfo = cartContext.getUserBillingInfo().getDefaultCreditCardInfo(cartContext.getPaymentProcessor());
        Bundle bundle = new Bundle();
        bundle.putParcelable("ArgumentCCBillingInfo", defaultCreditCardInfo);
        CVVConfirmationDialogFragment<BaseActivity> cVVConfirmationDialogFragment = new CVVConfirmationDialogFragment<>();
        cVVConfirmationDialogFragment.setArguments(bundle);
        return cVVConfirmationDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mCreditCardBillingInfo = (WishCreditCardInfo) getArguments().getParcelable("ArgumentCCBillingInfo");
        View inflate = layoutInflater.inflate(R.layout.cvv_confirmation_dialog_fragment, viewGroup, false);
        ThemedTextView themedTextView = (ThemedTextView) inflate.findViewById(R.id.cvv_confirmation_dialog_message);
        String creditCardTypeDisplayString = CreditCardUtil.getCreditCardTypeDisplayString(this.mCreditCardBillingInfo.getCardType());
        StringBuilder sb = new StringBuilder();
        sb.append(creditCardTypeDisplayString);
        sb.append(" ");
        sb.append(String.format("- %s", new Object[]{this.mCreditCardBillingInfo.getLastFourDigits()}));
        themedTextView.setText(sb.toString());
        this.mCVVLength = CreditCardUtil.getValidSecurityCodeLength(this.mCreditCardBillingInfo.getCardType());
        ThemedTextView themedTextView2 = (ThemedTextView) inflate.findViewById(R.id.cvv_confirmation_dialog_message_2);
        if (this.mCVVLength == 4) {
            themedTextView2.setText(R.string.the_four_digit_security_code);
        } else {
            themedTextView2.setText(R.string.the_three_digit_security_code);
        }
        this.mCVVEditTexts = Arrays.asList(new CVVConfirmationEditText[]{(CVVConfirmationEditText) inflate.findViewById(R.id.cvv_confirmation_dialog_cvv_text_1), (CVVConfirmationEditText) inflate.findViewById(R.id.cvv_confirmation_dialog_cvv_text_2), (CVVConfirmationEditText) inflate.findViewById(R.id.cvv_confirmation_dialog_cvv_text_3), (CVVConfirmationEditText) inflate.findViewById(R.id.cvv_confirmation_dialog_cvv_text_4)});
        int i = 0;
        while (i < this.mCVVLength) {
            CVVConfirmationEditText cVVConfirmationEditText = (CVVConfirmationEditText) this.mCVVEditTexts.get(i);
            final CVVConfirmationEditText cVVConfirmationEditText2 = i == this.mCVVLength - 1 ? null : (CVVConfirmationEditText) this.mCVVEditTexts.get(i + 1);
            cVVConfirmationEditText.setVisibility(0);
            if (cVVConfirmationEditText2 != null) {
                cVVConfirmationEditText.setDelegate(new CreditCardFormFieldsDelegate() {
                    public void onCardTypeChanged(CardType cardType) {
                    }

                    public void onEntryComplete() {
                        KeyboardUtil.requestKeyboardFocus(cVVConfirmationEditText2);
                    }
                });
                cVVConfirmationEditText.setOnEditorActionListener(new OnEditorActionListener() {
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (i != 6) {
                            return false;
                        }
                        KeyboardUtil.requestKeyboardFocus(cVVConfirmationEditText2);
                        return true;
                    }
                });
            } else {
                cVVConfirmationEditText.setOnEditorActionListener(new OnEditorActionListener() {
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (i != 6) {
                            return false;
                        }
                        CVVConfirmationDialogFragment.this.handleConfirm();
                        return true;
                    }
                });
            }
            i++;
        }
        ThemedTextView themedTextView3 = (ThemedTextView) inflate.findViewById(R.id.cvv_confirmation_dialog_cancel_button);
        ((ThemedTextView) inflate.findViewById(R.id.cvv_confirmation_dialog_confirm_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CVVConfirmationDialogFragment.this.handleConfirm();
            }
        });
        themedTextView3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CVVConfirmationDialogFragment.this.handleCancel();
            }
        });
        return inflate;
    }

    public int getDialogWidth() {
        return (int) (getResources().getFraction(R.fraction.dialog_min_width_major, 1, 1) * ((float) DisplayUtil.getDisplayWidth()));
    }

    public Margin getDialogMargin() {
        Margin margin = new Margin(0, getResources().getDimensionPixelSize(R.dimen.cvv_confirmation_dialog_margin_top), 0, 0);
        return margin;
    }

    public void onResume() {
        super.onResume();
        ((CVVConfirmationEditText) this.mCVVEditTexts.get(0)).post(new Runnable() {
            public void run() {
                KeyboardUtil.requestKeyboardFocus((View) CVVConfirmationDialogFragment.this.mCVVEditTexts.get(0));
            }
        });
    }

    private String getCVV() {
        StringBuilder sb = new StringBuilder();
        for (CVVConfirmationEditText extractEditTextValue : this.mCVVEditTexts) {
            String extractEditTextValue2 = ViewUtil.extractEditTextValue(extractEditTextValue);
            if (extractEditTextValue2 != null) {
                sb.append(extractEditTextValue2);
            }
        }
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public void handleConfirm() {
        String cvv = getCVV();
        if (cvv.length() != this.mCVVLength) {
            onBadInput();
            return;
        }
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CVV_CONFIRMATION_DIALOG_CONFIRM);
        Bundle bundle = new Bundle();
        bundle.putString("ResultCVV", cvv);
        makeSelection(bundle);
    }

    /* access modifiers changed from: private */
    public void handleCancel() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CVV_CONFIRMATION_DIALOG_CANCEL);
        cancel();
    }

    private void onBadInput() {
        for (CVVConfirmationEditText onBadInput : this.mCVVEditTexts) {
            onBadInput.onBadInput();
        }
    }
}
