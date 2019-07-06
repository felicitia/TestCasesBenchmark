package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.q;
import java.util.Locale;

@Deprecated
public class CharacterCounterView extends AppCompatTextView implements TextWatcher {
    private static final String DEFAULT_FORMAT = "%d/%d";
    private static final int DEFAULT_MAX_CHARS = 100;
    private int mErrorColor;
    private int mMaxChars;
    private TextView mObservable;
    private int mOriginalColor;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public CharacterCounterView(Context context) {
        super(context);
        init(null);
    }

    public CharacterCounterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    /* JADX INFO: finally extract failed */
    private void init(AttributeSet attributeSet) {
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, q.CharacterCounterView, 0, 0);
            try {
                this.mMaxChars = obtainStyledAttributes.getInteger(q.CharacterCounterView_maxChars, 100);
                obtainStyledAttributes.recycle();
                this.mOriginalColor = getCurrentTextColor();
                this.mErrorColor = getResources().getColor(e.text_warn);
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        }
    }

    public void setObservable(TextView textView) {
        if (this.mObservable != null) {
            this.mObservable.removeTextChangedListener(this);
        }
        this.mObservable = textView;
        this.mObservable.addTextChangedListener(this);
    }

    public void setMaxChars(int i) {
        this.mMaxChars = i;
    }

    public boolean isTooLong() {
        return this.mObservable != null && this.mObservable.length() > this.mMaxChars;
    }

    public void afterTextChanged(Editable editable) {
        int i = isTooLong() ? this.mErrorColor : this.mOriginalColor;
        int length = this.mMaxChars - editable.length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(String.format(Locale.ROOT, DEFAULT_FORMAT, new Object[]{Integer.valueOf(length), Integer.valueOf(this.mMaxChars)}));
        spannableStringBuilder.setSpan(new ForegroundColorSpan(i), 0, String.valueOf(length).length(), 17);
        setText(spannableStringBuilder);
    }
}
