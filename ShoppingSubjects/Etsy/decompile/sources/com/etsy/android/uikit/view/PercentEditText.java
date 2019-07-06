package com.etsy.android.uikit.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import com.etsy.android.uikit.util.input.PercentageKeyListener;
import com.etsy.android.uikit.view.ManagedSelectionEditText.a;

public class PercentEditText extends ManagedSelectionEditText {
    private PercentageKeyListener mPercentKeyListener;

    public PercentEditText(Context context) {
        super(context);
        init();
    }

    public PercentEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PercentEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mPercentKeyListener = new PercentageKeyListener();
        setKeyListener(this.mPercentKeyListener);
        setOnSelectionChangedListener(new a() {
            public void a(int i, int i2) {
                int length = PercentEditText.this.getText().length();
                if (i2 > 1 && i2 == length) {
                    PercentEditText.this.setSelection(length - 1);
                }
            }
        });
        addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("%")) {
                    PercentEditText.this.setText("");
                }
            }
        });
    }

    public void setNoDecimal(boolean z) {
        this.mPercentKeyListener.setNoDecimal(z);
    }

    public double getPercentDouble() throws NumberFormatException {
        String replace = getText().toString().replace("%", "");
        if (replace.length() > 0) {
            return Double.parseDouble(replace);
        }
        return 0.0d;
    }

    public int getPercentInt() throws NumberFormatException {
        String replace = getText().toString().replace("%", "");
        if (replace.length() > 0) {
            return Integer.parseInt(replace);
        }
        return 0;
    }
}
