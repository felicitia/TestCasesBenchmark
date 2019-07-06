package com.contextlogic.wish.ui.text;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AutoCompleteTextView;
import com.contextlogic.wish.util.FontUtil;

public class ThemedAutoCompleteTextView extends AutoCompleteTextView {
    /* access modifiers changed from: private */
    public Drawable mClearButton;
    private KeyboardHideListener mKeyboardHideListener;

    public interface KeyboardHideListener {
        boolean onKeyboardHide();
    }

    public ThemedAutoCompleteTextView(Context context) {
        super(context);
        init();
    }

    public ThemedAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ThemedAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        Typeface typefaceForStyle = FontUtil.getTypefaceForStyle(getTypeface() != null ? getTypeface().getStyle() : 0);
        if (typefaceForStyle != null) {
            setTypeface(typefaceForStyle);
        }
        setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (ThemedAutoCompleteTextView.this.mClearButton != null && ThemedAutoCompleteTextView.this.getCompoundDrawables()[2] != null && motionEvent.getAction() == 1 && motionEvent.getX() > ((float) ((ThemedAutoCompleteTextView.this.getWidth() - ThemedAutoCompleteTextView.this.getPaddingRight()) - ThemedAutoCompleteTextView.this.mClearButton.getIntrinsicWidth()))) {
                    ThemedAutoCompleteTextView.this.setText("");
                }
                return false;
            }
        });
    }

    public void setKeyboardHideListener(KeyboardHideListener keyboardHideListener) {
        this.mKeyboardHideListener = keyboardHideListener;
    }

    public void setClearButton(Drawable drawable) {
        this.mClearButton = drawable;
        if (this.mClearButton == null || getText().length() <= 0) {
            hideClearButton();
        } else {
            showClearButton();
        }
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 4 || isPopupShowing() || this.mKeyboardHideListener == null || !this.mKeyboardHideListener.onKeyboardHide()) {
            return super.onKeyPreIme(i, keyEvent);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (i == 0 && i2 == 0 && i3 > 0) {
            showClearButton();
        } else if (i == 0 && i2 > 0 && i3 == 0) {
            hideClearButton();
        }
        super.onTextChanged(charSequence, i, i2, i3);
    }

    private void showClearButton() {
        if (this.mClearButton != null) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, this.mClearButton, null);
        }
    }

    private void hideClearButton() {
        if (this.mClearButton != null) {
            setCompoundDrawables(null, null, null, null);
        }
    }
}
