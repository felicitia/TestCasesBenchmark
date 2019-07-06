package com.contextlogic.wish.ui.text;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.util.FontUtil;
import com.contextlogic.wish.util.ValueUtil;
import com.crashlytics.android.Crashlytics;

public class ThemedTextView extends TextView {
    private int mAllowedExtraMaxLines;
    private String mEllipsizedContent;
    private boolean mFontResizable;
    private float mIdealFontSize;
    private int mIdealMaxLines;
    private int mMaxLines;
    private int mMaxWidth;
    private float mMinFontSize;
    private boolean mProgrammaticallyChangingText;
    private boolean mProgrammaticallyChangingTextSize;
    private boolean mRedrawStale;
    private String mUnellipsizedContent;
    private int mWorkingMaxLines;

    public ThemedTextView(Context context) {
        this(context, null);
    }

    public ThemedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ThemedTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    public void setTypeface(int i) {
        super.setTypeface(FontUtil.getTypefaceForStyle(i), i);
    }

    private void init(Context context, AttributeSet attributeSet) {
        Typeface typefaceForStyle = FontUtil.getTypefaceForStyle(getTypeface() != null ? getTypeface().getStyle() : 0);
        if (typefaceForStyle != null) {
            setTypeface(typefaceForStyle);
        }
        this.mMaxLines = -1;
        this.mRedrawStale = false;
        this.mProgrammaticallyChangingText = false;
        this.mProgrammaticallyChangingTextSize = false;
        this.mIdealFontSize = getTextSize();
        if (!isInEditMode()) {
            this.mIdealFontSize += (float) ValueUtil.convertSpToPx(1.0f);
            setTextSize(0, this.mIdealFontSize);
            this.mMinFontSize = ValueUtil.convertDpToPx(5.0f);
        }
        this.mFontResizable = false;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16843405});
            this.mFontResizable = obtainStyledAttributes.getBoolean(0, false);
            obtainStyledAttributes.recycle();
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, new int[]{16843101});
            if (obtainStyledAttributes2.getBoolean(0, false)) {
                setMaxLines(1);
            }
            obtainStyledAttributes2.recycle();
            TypedArray obtainStyledAttributes3 = context.obtainStyledAttributes(attributeSet, new int[]{16843091});
            int i = obtainStyledAttributes3.getInt(0, -1);
            if (i != -1) {
                setMaxLines(i);
            }
            obtainStyledAttributes3.recycle();
            TypedArray obtainStyledAttributes4 = getContext().obtainStyledAttributes(attributeSet, R.styleable.ThemedTextView);
            float dimension = obtainStyledAttributes4.getDimension(2, 0.0f);
            if (dimension > 0.0f) {
                this.mMinFontSize = dimension;
            }
            this.mIdealMaxLines = obtainStyledAttributes4.getInteger(1, 0);
            this.mWorkingMaxLines = this.mIdealMaxLines;
            this.mAllowedExtraMaxLines = obtainStyledAttributes4.getInteger(0, 0);
            obtainStyledAttributes4.recycle();
        }
    }

    public void setFontResizable(boolean z) {
        this.mFontResizable = z;
    }

    public void setMaxLines(int i) {
        super.setMaxLines(i);
        this.mMaxLines = i;
        this.mRedrawStale = true;
        if (this.mEllipsizedContent != null && this.mUnellipsizedContent != null && !needsCustomEllipsize()) {
            setText(this.mUnellipsizedContent);
        }
    }

    private boolean needsCustomEllipsize() {
        return this.mMaxLines > 2;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mRedrawStale = true;
    }

    /* access modifiers changed from: protected */
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        if (!this.mProgrammaticallyChangingText) {
            this.mUnellipsizedContent = charSequence.toString();
            this.mEllipsizedContent = null;
            this.mRedrawStale = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        try {
            if (!this.mProgrammaticallyChangingTextSize) {
                if (this.mRedrawStale && this.mFontResizable) {
                    resetTextSize();
                } else if (this.mRedrawStale && needsCustomEllipsize()) {
                    super.setEllipsize(null);
                    resetEllipsizedText();
                }
                super.onDraw(canvas);
            }
        } catch (Throwable unused) {
            Crashlytics.logException(new Exception(getText().toString()));
        }
    }

    private void resetTextSize() {
        this.mProgrammaticallyChangingTextSize = true;
        this.mWorkingMaxLines = this.mIdealMaxLines;
        setTextSize(0, (float) getIdealFontSize());
        this.mProgrammaticallyChangingTextSize = false;
        this.mRedrawStale = false;
    }

    public void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        this.mIdealFontSize = getTextSize();
    }

    private int getIdealFontSize() {
        int i = (int) this.mMinFontSize;
        int i2 = (int) this.mIdealFontSize;
        int i3 = i;
        boolean z = false;
        while (i <= i2) {
            int i4 = (i2 + i) / 2;
            boolean testFontSize = testFontSize((float) i4);
            if (testFontSize) {
                i = i4 + 1;
                i3 = i4;
            } else {
                i2 = i4 - 1;
            }
            z = testFontSize;
        }
        if (i3 != ((int) this.mMinFontSize) || z || this.mIdealMaxLines <= 0 || this.mAllowedExtraMaxLines <= 0 || this.mWorkingMaxLines <= 0 || this.mWorkingMaxLines >= this.mIdealMaxLines + this.mAllowedExtraMaxLines) {
            return i3;
        }
        this.mWorkingMaxLines++;
        setMaxLines(this.mWorkingMaxLines);
        setTextSize(0, this.mIdealFontSize);
        return getIdealFontSize();
    }

    private boolean testFontSize(float f) {
        String charSequence = getText().toString();
        TextPaint textPaint = new TextPaint(getPaint());
        textPaint.setTextSize(f);
        try {
            Layout createWorkingLayout = createWorkingLayout(charSequence, textPaint);
            if (this.mMaxLines > 0 && createWorkingLayout.getLineCount() > this.mMaxLines) {
                return false;
            }
            if ((this.mWorkingMaxLines <= 0 || createWorkingLayout.getLineCount() <= this.mWorkingMaxLines) && createWorkingLayout.getHeight() <= getAvailableHeight()) {
                return true;
            }
            return false;
        } catch (Throwable unused) {
            Crashlytics.logException(new Exception(getText().toString()));
            return true;
        }
    }

    public void setMaxWidth(int i) {
        this.mMaxWidth = i;
        super.setMaxWidth(i);
    }

    private int getAvailableHeight() {
        return (getHeight() - getCompoundPaddingBottom()) - getCompoundPaddingTop();
    }

    private void resetEllipsizedText() {
        String str = this.mUnellipsizedContent;
        if (this.mMaxLines != -1) {
            Layout createWorkingLayout = createWorkingLayout(str);
            if (createWorkingLayout.getLineCount() > this.mMaxLines) {
                String trim = this.mUnellipsizedContent.substring(0, createWorkingLayout.getLineEnd(this.mMaxLines - 1)).trim();
                while (true) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(trim);
                    sb.append("...");
                    if (createWorkingLayout(sb.toString()).getLineCount() <= this.mMaxLines) {
                        break;
                    }
                    int lastIndexOf = trim.lastIndexOf(32);
                    if (lastIndexOf == -1) {
                        break;
                    }
                    trim = trim.substring(0, lastIndexOf);
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(trim);
                sb2.append("...");
                str = sb2.toString();
            }
        }
        if (!str.equals(getText())) {
            this.mProgrammaticallyChangingText = true;
            this.mEllipsizedContent = str;
            try {
                setText(str);
            } finally {
                this.mProgrammaticallyChangingText = false;
            }
        }
        this.mRedrawStale = false;
    }

    private Layout createWorkingLayout(String str) {
        return createWorkingLayout(str, null);
    }

    private Layout createWorkingLayout(String str, TextPaint textPaint) {
        if (textPaint == null) {
            textPaint = getPaint();
        }
        TextPaint textPaint2 = textPaint;
        int width = getWidth();
        if (getLayoutParams().width == -2 && this.mMaxWidth > 0) {
            width = this.mMaxWidth;
        }
        StaticLayout staticLayout = new StaticLayout(str, textPaint2, Math.max(0, (width - getPaddingLeft()) - getPaddingRight()), Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        return staticLayout;
    }

    public void changeTypefaceToNormal() {
        Typeface typefaceForStyle = FontUtil.getTypefaceForStyle(0);
        if (typefaceForStyle != null) {
            setTypeface(typefaceForStyle);
        }
    }
}
