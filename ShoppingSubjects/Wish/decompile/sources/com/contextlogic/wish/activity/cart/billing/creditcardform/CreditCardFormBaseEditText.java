package com.contextlogic.wish.activity.cart.billing.creditcardform;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedEditText;
import com.contextlogic.wish.util.KeyboardUtil;

public abstract class CreditCardFormBaseEditText extends ThemedEditText implements TextWatcher, OnClickListener {
    private boolean valid = false;

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public CreditCardFormBaseEditText(Context context) {
        super(context);
        init();
    }

    public CreditCardFormBaseEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CreditCardFormBaseEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void init() {
        setImeOptions(268435456);
        setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.transparent));
        setHintTextColor(WishApplication.getInstance().getResources().getColor(R.color.text_hint));
        setTextColor(WishApplication.getInstance().getResources().getColor(R.color.text_hint));
        setInputType(2);
        addTextChangedListener(this);
        setOnClickListener(this);
    }

    public void onClick(View view) {
        KeyboardUtil.requestKeyboardFocus(this);
        setSelection(getText().toString().length());
    }

    public boolean isValid() {
        return this.valid;
    }

    public void setValid(boolean z) {
        this.valid = z;
    }
}
