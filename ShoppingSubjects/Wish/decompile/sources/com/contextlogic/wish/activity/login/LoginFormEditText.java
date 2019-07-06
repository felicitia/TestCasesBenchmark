package com.contextlogic.wish.activity.login;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedEditText;
import com.contextlogic.wish.util.ViewUtil;

public class LoginFormEditText extends ThemedEditText {
    boolean errorState;

    public LoginFormEditText(Context context) {
        super(context);
        init();
    }

    public LoginFormEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public LoginFormEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                LoginFormEditText.this.handleTextChanged();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleTextChanged() {
        if (this.errorState && ViewUtil.extractEditTextValue(this) != null) {
            setBackground((StateListDrawable) WishApplication.getInstance().getResources().getDrawable(R.drawable.login_fragment_redesign_edit_text_background));
            this.errorState = false;
        }
    }

    public void setError() {
        setBackground((GradientDrawable) WishApplication.getInstance().getResources().getDrawable(R.drawable.login_fragment_redesign_edit_text_error_background));
        this.errorState = true;
    }
}
