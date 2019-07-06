package com.contextlogic.wish.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.text.ErrorableThemedEditText;
import com.contextlogic.wish.ui.text.ThemedEditText;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.FontUtil;
import com.contextlogic.wish.util.ViewUtil;

public class FormTextInputLayout extends FrameLayout {
    /* access modifiers changed from: private */
    public ErrorableThemedEditText mEditText;
    private ThemedTextView mErrorText;
    /* access modifiers changed from: private */
    public ThemedTextView mLabelText;
    /* access modifiers changed from: private */
    public OnFieldChangedListener mOnFieldChangedListener;
    /* access modifiers changed from: private */
    public OnFocusChangeListener mOnFocusChangeListener;
    /* access modifiers changed from: private */
    public OnVerifyFieldListener mOnVerifyFieldListener;

    public interface OnFieldChangedListener {
        void onFieldChanged(String str);
    }

    public interface OnVerifyFieldListener {
        String getErrorMessage(String str);
    }

    public FormTextInputLayout(Context context) {
        this(context, null);
    }

    public FormTextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FormTextInputLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.form_text_input_layout, this);
        this.mLabelText = (ThemedTextView) inflate.findViewById(R.id.form_text_input_label);
        this.mEditText = (ErrorableThemedEditText) inflate.findViewById(R.id.form_text_input_edit_text);
        this.mErrorText = (ThemedTextView) inflate.findViewById(R.id.form_text_input_error_message);
        this.mEditText.setOnFocusChangeListener(getFocusChangedListener());
        this.mEditText.addTextChangedListener(getTextWatcher());
        handleAttributeSet(context, attributeSet);
    }

    private void handleAttributeSet(Context context, AttributeSet attributeSet) {
        if (context != null && attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FormTextInputLayout);
            setHint(obtainStyledAttributes.getString(1));
            setLabel(obtainStyledAttributes.getString(5));
            setMaxLines(obtainStyledAttributes.getInt(2, -1));
            setNextFocusDown(obtainStyledAttributes.getInt(0, -1));
            setEditTextMinimumHeight(obtainStyledAttributes.getDimensionPixelOffset(4, -1));
            setInputType(obtainStyledAttributes.getInt(3, 1));
            obtainStyledAttributes.recycle();
        }
    }

    public void setErrored(String str) {
        if (this.mEditText != null && this.mLabelText != null && this.mErrorText != null) {
            boolean z = !TextUtils.isEmpty(str);
            if (z) {
                this.mLabelText.setTextColor(ContextCompat.getColor(getContext(), R.color.new_red));
                this.mErrorText.setVisibility(0);
                this.mErrorText.setText(str);
            } else {
                this.mErrorText.setVisibility(8);
                this.mLabelText.setTextColor(ContextCompat.getColor(getContext(), this.mEditText.hasFocus() ? R.color.main_primary : R.color.text_primary));
            }
            this.mEditText.setErrored(z);
            this.mEditText.refreshDrawableState();
        }
    }

    public void setOnVerifyFormListener(OnVerifyFieldListener onVerifyFieldListener) {
        this.mOnVerifyFieldListener = onVerifyFieldListener;
    }

    public void setOnFieldChangedListener(OnFieldChangedListener onFieldChangedListener) {
        this.mOnFieldChangedListener = onFieldChangedListener;
    }

    public void setOnFocusChangedListener(OnFocusChangeListener onFocusChangeListener) {
        this.mOnFocusChangeListener = onFocusChangeListener;
    }

    public ThemedEditText getEditText() {
        return this.mEditText;
    }

    public void setLabel(String str) {
        if (this.mLabelText != null && str != null) {
            this.mLabelText.setVisibility(0);
            this.mLabelText.setText(str);
        } else if (this.mLabelText != null) {
            this.mLabelText.setVisibility(8);
        }
    }

    public void setMaxLines(int i) {
        if (i > 0 && this.mEditText != null) {
            this.mEditText.setMaxLines(i);
        }
    }

    public void setNextFocusDown(int i) {
        if (i > 0 && this.mEditText != null) {
            this.mEditText.setNextFocusDownId(i);
        }
    }

    public void setText(String str) {
        if (this.mEditText != null) {
            this.mEditText.setText(str);
        }
    }

    public void setHint(String str) {
        if (this.mEditText != null) {
            this.mEditText.setHint(str);
        }
    }

    public void setInputType(int i) {
        if (this.mEditText != null) {
            this.mEditText.setInputType(i);
            reInitTypeface();
        }
    }

    public void setEditTextMinimumHeight(int i) {
        if (this.mEditText != null) {
            this.mEditText.setMinimumHeight(i);
        }
    }

    public void setTextCentered() {
        if (this.mEditText == null) {
            return;
        }
        if (VERSION.SDK_INT >= 17) {
            this.mEditText.setTextAlignment(4);
        } else {
            this.mEditText.setGravity(17);
        }
    }

    private void reInitTypeface() {
        if (this.mEditText != null) {
            Typeface typefaceForStyle = FontUtil.getTypefaceForStyle(this.mEditText.getTypeface() != null ? this.mEditText.getTypeface().getStyle() : 0);
            if (typefaceForStyle != null) {
                this.mEditText.setTypeface(typefaceForStyle);
            }
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (this.mEditText != null) {
            this.mEditText.setEnabled(z);
        }
    }

    public void clear() {
        OnVerifyFieldListener onVerifyFieldListener = this.mOnVerifyFieldListener;
        this.mOnVerifyFieldListener = null;
        setText(null);
        setErrored(null);
        this.mOnVerifyFieldListener = onVerifyFieldListener;
    }

    public void setDropdownClickListener(final OnClickListener onClickListener) {
        if (this.mEditText != null) {
            if (onClickListener != null) {
                this.mEditText.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(getContext(), R.drawable.dropdown_downarrow), null);
                this.mEditText.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.four_padding));
                this.mEditText.setFocusable(false);
                this.mEditText.setFocusableInTouchMode(false);
                this.mEditText.setCursorVisible(false);
                this.mEditText.setGravity(8388627);
                this.mEditText.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        FormTextInputLayout.this.requestFocus();
                        onClickListener.onClick(view);
                    }
                });
            } else {
                this.mEditText.setCompoundDrawables(null, null, null, null);
                this.mEditText.setFocusable(true);
                this.mEditText.setFocusableInTouchMode(true);
                this.mEditText.setCursorVisible(true);
                this.mEditText.setOnClickListener(null);
            }
        }
    }

    private OnFocusChangeListener getFocusChangedListener() {
        return new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (!(FormTextInputLayout.this.mLabelText == null || FormTextInputLayout.this.mEditText == null || FormTextInputLayout.this.mEditText.getErrored())) {
                    FormTextInputLayout.this.mLabelText.setTextColor(FormTextInputLayout.this.getResources().getColor(z ? R.color.main_primary : R.color.text_primary));
                }
                if (FormTextInputLayout.this.mOnFocusChangeListener != null) {
                    FormTextInputLayout.this.mOnFocusChangeListener.onFocusChange(view, z);
                }
            }
        };
    }

    private TextWatcher getTextWatcher() {
        return new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (FormTextInputLayout.this.mOnVerifyFieldListener != null) {
                    FormTextInputLayout.this.setErrored(FormTextInputLayout.this.mOnVerifyFieldListener.getErrorMessage(ViewUtil.extractEditTextValue(FormTextInputLayout.this.mEditText)));
                }
                if (FormTextInputLayout.this.mOnFieldChangedListener != null) {
                    FormTextInputLayout.this.mOnFieldChangedListener.onFieldChanged(charSequence == null ? null : charSequence.toString());
                }
            }
        };
    }
}
