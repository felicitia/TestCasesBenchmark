package com.contextlogic.wish.activity.cart.billing.creditcardform;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.util.AttributeSet;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.CreditCardUtil;
import com.contextlogic.wish.util.CreditCardUtil.CardType;

public class CreditCardFormCvvEditText extends CreditCardFormBaseEditText {
    private CardType mCardType;
    private CreditCardFormFieldsDelegate mDelegate;
    private int mLength;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public CreditCardFormCvvEditText(Context context) {
        super(context);
        init();
    }

    public CreditCardFormCvvEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CreditCardFormCvvEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void init() {
        super.init();
        setTextSize(0, (float) getResources().getDimensionPixelSize(R.dimen.text_size_form));
        setHint(getContext().getString(R.string.cvv));
        setTextColor(WishApplication.getInstance().getResources().getColor(R.color.text_primary));
        setHintTextColor(WishApplication.getInstance().getResources().getColor(R.color.text_hint));
        setBackgroundResource(R.drawable.edit_text_background);
        setGravity(19);
    }

    public void showRedesignedBackground() {
        setBackgroundResource(R.drawable.redesign_edit_text_normal);
        setHintTextColor(getResources().getColor(R.color.gray4));
        setHint(WishApplication.getInstance().getString(R.string.security_code_cvv));
    }

    public void afterTextChanged(Editable editable) {
        if (this.mCardType != null && editable.toString().length() == this.mLength) {
            onSecurityCodeValid();
        }
    }

    private void onSecurityCodeValid() {
        if (this.mDelegate != null) {
            this.mDelegate.onEntryComplete();
        }
    }

    public boolean isValid() {
        String obj = getText() != null ? getText().toString() : "";
        boolean z = false;
        if (this.mCardType != null) {
            return obj.length() == this.mLength;
        }
        if (obj.length() > 0) {
            z = true;
        }
        return z;
    }

    public CardType getCardType() {
        return this.mCardType;
    }

    public void setCardType(CardType cardType) {
        this.mCardType = cardType;
        this.mLength = CreditCardUtil.getValidSecurityCodeLength(cardType);
        setFilters(new InputFilter[]{new LengthFilter(this.mLength)});
    }

    public CreditCardFormFieldsDelegate getDelegate() {
        return this.mDelegate;
    }

    public void setDelegate(CreditCardFormFieldsDelegate creditCardFormFieldsDelegate) {
        this.mDelegate = creditCardFormFieldsDelegate;
    }
}
