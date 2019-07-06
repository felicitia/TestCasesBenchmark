package com.contextlogic.wish.activity.cart.billing.creditcardform;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.view.animation.AnimationUtils;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.CreditCardUtil;
import com.contextlogic.wish.util.CreditCardUtil.CardType;

public class CreditCardFormCreditCardEditText extends CreditCardFormBaseEditText {
    private CardType mCardType;
    private CreditCardFormCreditCardDelegate mDelegate;
    private String mPreviousNumber;

    public CreditCardFormCreditCardEditText(Context context) {
        super(context);
        init();
    }

    public void init() {
        super.init();
        setGravity(19);
        if (ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            setHint(R.string.credit_slash_debit_card);
        } else {
            setHint(R.string.card_number);
        }
        setTextSize(0, (float) getResources().getDimensionPixelSize(R.dimen.text_size_form));
        setTextColor(WishApplication.getInstance().getResources().getColor(R.color.text_primary));
        setHintTextColor(WishApplication.getInstance().getResources().getColor(R.color.text_hint));
        setPadding(0, 0, 0, 0);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.mPreviousNumber = charSequence.toString();
    }

    public void afterTextChanged(Editable editable) {
        String obj = editable.toString();
        if (obj.length() >= 4) {
            CardType cardTypeFromNumber = CreditCardUtil.getCardTypeFromNumber(obj);
            if (cardTypeFromNumber.equals(CardType.Invalid)) {
                removeTextChangedListener(this);
                setText(this.mPreviousNumber);
                setSelection(Math.min(3, getText().length()));
                addTextChangedListener(this);
                onBadInput();
                setValid(false);
                return;
            }
            if (!(this.mCardType == cardTypeFromNumber || this.mDelegate == null)) {
                this.mDelegate.onCardTypeChange(cardTypeFromNumber);
            }
            this.mCardType = cardTypeFromNumber;
            String formattedCardNumber = CreditCardUtil.getFormattedCardNumber(obj, cardTypeFromNumber);
            if (!obj.equalsIgnoreCase(formattedCardNumber)) {
                removeTextChangedListener(this);
                setText(formattedCardNumber);
                setSelection(formattedCardNumber.length());
                addTextChangedListener(this);
            }
            if (cardTypeFromNumber != CardType.HiperCard && formattedCardNumber.length() >= CreditCardUtil.getLengthOfFormattedString(cardTypeFromNumber, formattedCardNumber)) {
                if (!CreditCardUtil.isValidCardNumber(formattedCardNumber)) {
                    onBadInput();
                } else if (this.mDelegate != null) {
                    this.mDelegate.onCreditCardNumberValid();
                }
            }
        } else if (this.mCardType != null) {
            this.mCardType = null;
            if (this.mDelegate != null) {
                this.mDelegate.onCardTypeChange(CardType.Invalid);
            }
        }
    }

    public boolean isValid() {
        return CreditCardUtil.isValidCardNumber(getText().toString());
    }

    private void onBadInput() {
        startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
        setTextColor(-65536);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                CreditCardFormCreditCardEditText.this.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.text_primary));
            }
        }, 300);
    }

    public CardType getCardType() {
        return this.mCardType;
    }

    public CreditCardFormCreditCardDelegate getDelegate() {
        return this.mDelegate;
    }

    public void setDelegate(CreditCardFormCreditCardDelegate creditCardFormCreditCardDelegate) {
        this.mDelegate = creditCardFormCreditCardDelegate;
    }
}
