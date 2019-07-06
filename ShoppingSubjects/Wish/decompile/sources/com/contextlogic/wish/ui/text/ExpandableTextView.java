package com.contextlogic.wish.ui.text;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView.BufferType;
import com.contextlogic.wish.R;

public class ExpandableTextView extends ThemedTextView {
    private BufferType mBufferType;
    private final String mEllipsis;
    private CharSequence mOriginalText;
    private boolean mTrim;
    private int mTrimLength;
    private boolean mTrimmable;
    private CharSequence mTrimmedText;

    public ExpandableTextView(Context context) {
        this(context, null);
    }

    public ExpandableTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTrim = true;
        this.mTrimmable = true;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ExpandableTextView);
        this.mTrimLength = obtainStyledAttributes.getInt(0, 140);
        this.mEllipsis = "...";
        obtainStyledAttributes.recycle();
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ExpandableTextView.this.cycleExpandedTextView(view);
            }
        });
    }

    public boolean isTrimmed() {
        return this.mTrim;
    }

    public void cycleExpandedTextView(View view) {
        this.mTrim = !this.mTrim;
        setText();
        requestFocusFromTouch();
    }

    private void setText() {
        super.setText(getDisplayableText(), this.mBufferType);
    }

    private CharSequence getDisplayableText() {
        return this.mTrim ? this.mTrimmedText : this.mOriginalText;
    }

    public void setText(CharSequence charSequence, BufferType bufferType) {
        this.mOriginalText = charSequence;
        this.mTrimmedText = getTrimmedText(charSequence);
        this.mBufferType = bufferType;
        setText();
    }

    private CharSequence getTrimmedText(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() <= this.mTrimLength || this.mTrimLength <= 0) {
            this.mTrimmable = false;
            return this.mOriginalText;
        }
        this.mTrimmable = true;
        return new SpannableString(new SpannableStringBuilder(charSequence, 0, this.mTrimLength).append(this.mEllipsis));
    }

    public void setTrim(boolean z) {
        this.mTrim = z;
        setText();
    }

    public boolean getTrim() {
        return this.mTrim;
    }

    public boolean isTrimmable() {
        return this.mTrimmable;
    }

    public void setTrimLength(int i) {
        this.mTrimLength = i;
        this.mTrimmedText = getTrimmedText(this.mOriginalText);
        setText();
    }
}
