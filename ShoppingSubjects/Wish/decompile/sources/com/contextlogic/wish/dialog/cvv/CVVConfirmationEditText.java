package com.contextlogic.wish.dialog.cvv;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.cart.billing.creditcardform.CreditCardFormFieldsDelegate;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedEditText;

public class CVVConfirmationEditText extends ThemedEditText implements TextWatcher {
    private CreditCardFormFieldsDelegate mDelegate;
    private Character mPrevText;

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public CVVConfirmationEditText(Context context) {
        super(context);
        init();
    }

    public CVVConfirmationEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CVVConfirmationEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void init() {
        addTextChangedListener(this);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.length() > 0) {
            this.mPrevText = Character.valueOf(charSequence.charAt(0));
        } else {
            this.mPrevText = null;
        }
    }

    public void afterTextChanged(Editable editable) {
        removeTextChangedListener(this);
        if (editable.length() > 0) {
            if (editable.length() > 1) {
                for (int i = 0; i < editable.length(); i++) {
                    if (editable.charAt(i) == this.mPrevText.charValue()) {
                        editable.delete(i, i + 1);
                    }
                }
            }
            if (this.mDelegate != null) {
                this.mDelegate.onEntryComplete();
            }
        }
        addTextChangedListener(this);
    }

    public void setDelegate(CreditCardFormFieldsDelegate creditCardFormFieldsDelegate) {
        this.mDelegate = creditCardFormFieldsDelegate;
    }

    public void onBadInput() {
        startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
        setTextColor(-65536);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                CVVConfirmationEditText.this.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.text_primary));
            }
        }, 300);
    }
}
