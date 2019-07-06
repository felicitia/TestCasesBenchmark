package com.contextlogic.wish.activity.cart.billing.creditcardform;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.util.CreditCardUtil;
import com.contextlogic.wish.util.CreditCardUtil.CardType;
import com.contextlogic.wish.util.ViewUtil;

public class CreditCardFormCreditCardField extends LinearLayout implements CreditCardFormCreditCardDelegate {
    private ImageView mCardImageView;
    private CreditCardFormCreditCardEditText mCreditCardEditText;
    private CreditCardFormFieldsDelegate mDelegate;

    public CreditCardFormCreditCardField(Context context) {
        super(context);
        init();
    }

    public CreditCardFormCreditCardField(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CreditCardFormCreditCardField(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void init() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.screen_padding);
        this.mCreditCardEditText = new CreditCardFormCreditCardEditText(getContext());
        LayoutParams layoutParams = new LayoutParams(0, -2, 1.0f);
        layoutParams.gravity = 17;
        layoutParams.rightMargin = dimensionPixelSize;
        this.mCreditCardEditText.setLayoutParams(layoutParams);
        this.mCreditCardEditText.setDelegate(this);
        addView(this.mCreditCardEditText);
        this.mCardImageView = new AutoReleasableImageView(getContext());
        LayoutParams layoutParams2 = new LayoutParams(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.cart_fragment_credit_card_form_card_image_width), getResources().getDimensionPixelSize(R.dimen.cart_fragment_credit_card_form_card_image_height));
        layoutParams2.gravity = 17;
        this.mCardImageView.setLayoutParams(layoutParams2);
        this.mCardImageView.setImageResource(R.drawable.credit_card_default);
        addView(this.mCardImageView);
        setBackgroundResource(R.drawable.edit_text_background);
        setOrientation(0);
        setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        this.mCreditCardEditText.setTextSize(0, (float) getResources().getDimensionPixelSize(R.dimen.text_size_form));
    }

    public void showRedesignedBackground() {
        this.mCardImageView.setImageResource(R.drawable.creditcard_default_minimal);
        setBackgroundResource(R.drawable.redesign_edit_text_normal);
        this.mCreditCardEditText.setHintTextColor(getResources().getColor(R.color.gray4));
    }

    public void onCardTypeChange(CardType cardType) {
        this.mCardImageView.setImageResource(CreditCardUtil.cardImageForCardType(cardType));
        if (this.mDelegate != null) {
            this.mDelegate.onCardTypeChanged(cardType);
        }
    }

    public void onCreditCardNumberValid() {
        if (this.mDelegate != null) {
            this.mDelegate.onEntryComplete();
        }
    }

    public CardType getCardType() {
        return this.mCreditCardEditText.getCardType();
    }

    public void setText(String str) {
        this.mCreditCardEditText.setText(str);
    }

    public String getText() {
        return ViewUtil.extractEditTextValue(this.mCreditCardEditText);
    }

    public CreditCardFormFieldsDelegate getDelegate() {
        return this.mDelegate;
    }

    public void setDelegate(CreditCardFormFieldsDelegate creditCardFormFieldsDelegate) {
        this.mDelegate = creditCardFormFieldsDelegate;
    }

    public boolean isValid() {
        return this.mCreditCardEditText.isValid();
    }

    public CreditCardFormCreditCardEditText getEditText() {
        return this.mCreditCardEditText;
    }
}
