package com.contextlogic.wish.ui.timer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.timer.BaseCountdownTimerView.DoneCallback;
import com.contextlogic.wish.util.ValueUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CountdownTimerView extends BaseCountdownTimerView {
    public static int NO_BACKGROUND = -1;
    private int mBackgroundColor;
    private int mBackgroundDrawable;
    private int mColonPadding;
    private List<TextView> mDigitElements;
    private int mDigitPadding;
    private boolean mDisableExpiredText;
    private TextView mExpiredText;
    private List<TextView> mHourElements;
    private boolean mIsBold;
    private List<TextView> mMinuteElements;
    private int mPadding;
    private String mPreText;
    private List<TextView> mSecondElements;
    private boolean mShowHour;

    public CountdownTimerView(Context context) {
        this(context, null);
    }

    public CountdownTimerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.mDigitPadding = -1;
        this.mColonPadding = -1;
        this.mPadding = -1;
        this.mDisableExpiredText = false;
        this.mShowHour = true;
        setOrientation(0);
    }

    public CountdownTimerView setDigitPadding(int i) {
        this.mDigitPadding = i;
        return this;
    }

    public CountdownTimerView setColonPadding(int i) {
        this.mColonPadding = i;
        return this;
    }

    public CountdownTimerView setPadding(int i) {
        this.mPadding = i;
        return this;
    }

    public CountdownTimerView setPreText(String str) {
        this.mPreText = str;
        return this;
    }

    public CountdownTimerView disableExpiredText() {
        this.mDisableExpiredText = true;
        return this;
    }

    public void setup(Date date, int i, int i2, int i3) {
        setup(date, i, i2, i3, i2);
    }

    public void setup(Date date, int i, int i2, int i3, int i4) {
        setup(date, i, i2, i3, i4, NO_BACKGROUND);
    }

    public void setup(Date date, int i, int i2, int i3, DoneCallback doneCallback) {
        setup(date, i, i2, i3, i2, NO_BACKGROUND, false, true, doneCallback);
    }

    public void setup(Date date, int i, int i2, int i3, int i4, int i5) {
        setup(date, i, i2, i3, i4, i5, false);
    }

    public void setup(Date date, int i, int i2, int i3, int i4, int i5, boolean z) {
        setup(date, i, i2, i3, i4, i5, z, true, null);
    }

    public void setup(Date date, int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, DoneCallback doneCallback) {
        int i6 = i;
        int i7 = i2;
        pauseTimer();
        removeAllViews();
        setup(date, doneCallback);
        this.mBackgroundColor = i7;
        this.mBackgroundDrawable = i5;
        this.mIsBold = z;
        this.mShowHour = z2;
        int i8 = this.mPadding == -1 ? (int) ((((double) i6) * 0.25d) / 2.0d) : this.mPadding;
        int i9 = i6 - (i8 * 2);
        int convertDpToPx = (int) ValueUtil.convertDpToPx(2.0f);
        int i10 = this.mDigitPadding == -1 ? i8 : this.mDigitPadding;
        int i11 = this.mColonPadding == -1 ? convertDpToPx : this.mColonPadding;
        int max = Math.max(2, this.mTimeParts.hours > 0 ? (int) Math.ceil(Math.log10((double) this.mTimeParts.hours)) : 1);
        this.mDigitElements = new ArrayList();
        if (this.mPreText != null && !this.mPreText.isEmpty()) {
            addPreText(this.mPreText, i9, i11, convertDpToPx, i4);
        }
        if (this.mTimeParts.hours > 0 && this.mShowHour) {
            this.mHourElements = new ArrayList();
            addDigits(this.mDigitElements, max, i9, i10, convertDpToPx, i3, this.mHourElements);
            addColon(this.mDigitElements, i9, i11, convertDpToPx, i4);
        }
        this.mMinuteElements = new ArrayList();
        int i12 = i3;
        addDigits(this.mDigitElements, 2, i9, i10, convertDpToPx, i12, this.mMinuteElements);
        addColon(this.mDigitElements, i9, i11, convertDpToPx, i4);
        this.mSecondElements = new ArrayList();
        addDigits(this.mDigitElements, 2, i9, i10, convertDpToPx, i12, this.mSecondElements);
        this.mExpiredText = new ThemedTextView(getContext());
        this.mExpiredText.setTextColor(i3);
        this.mExpiredText.setBackgroundColor(i7);
        this.mExpiredText.setText(R.string.expired);
        int i13 = (int) (((double) i8) * 1.25d);
        this.mExpiredText.setPadding(i8, i13, i8, i13);
        this.mExpiredText.setGravity(17);
        this.mExpiredText.setTextSize(0, (float) ((int) (((double) i9) * 0.8d)));
        this.mExpiredText.setVisibility(8);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.leftMargin = i8;
        this.mExpiredText.setLayoutParams(layoutParams);
        addView(this.mExpiredText);
    }

    private void addColon(List<TextView> list, int i, int i2, int i3, int i4) {
        ThemedTextView themedTextView = new ThemedTextView(getContext());
        themedTextView.setTextColor(i4);
        themedTextView.setText(":");
        themedTextView.setPadding(i3, i2, i3, i2);
        themedTextView.setTextSize(0, (float) i);
        if (this.mIsBold) {
            themedTextView.setTypeface(Typeface.DEFAULT_BOLD);
        }
        themedTextView.setGravity(17);
        addView(themedTextView);
        list.add(themedTextView);
    }

    private void addPreText(String str, int i, int i2, int i3, int i4) {
        ThemedTextView themedTextView = new ThemedTextView(getContext());
        themedTextView.setTextColor(i4);
        themedTextView.setText(str);
        themedTextView.setPadding(i3, i2, i3, i2);
        themedTextView.setTextSize(0, (float) i);
        if (this.mIsBold) {
            themedTextView.setTypeface(Typeface.DEFAULT_BOLD);
        }
        addView(themedTextView);
    }

    private void addDigits(List<TextView> list, int i, int i2, int i3, int i4, int i5, List<TextView> list2) {
        for (int i6 = 0; i6 < i; i6++) {
            ThemedTextView themedTextView = new ThemedTextView(getContext());
            themedTextView.setTextColor(i5);
            themedTextView.setMaxEms(1);
            themedTextView.setMinEms(1);
            if (this.mBackgroundDrawable != NO_BACKGROUND) {
                themedTextView.setBackgroundResource(this.mBackgroundDrawable);
            } else if (this.mBackgroundColor != 0) {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setColor(this.mBackgroundColor);
                gradientDrawable.setCornerRadius((float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.corner_radius));
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setColor(WishApplication.getInstance().getResources().getColor(R.color.transparent));
                themedTextView.setBackground(new LayerDrawable(new Drawable[]{gradientDrawable2, gradientDrawable}));
            }
            themedTextView.setText("0");
            int i7 = (int) (((double) i3) * 1.75d);
            themedTextView.setPadding(i7, i3, i7, i3);
            themedTextView.setTextSize(0, (float) i2);
            if (this.mIsBold) {
                themedTextView.setTypeface(Typeface.DEFAULT_BOLD);
            }
            themedTextView.setGravity(17);
            addView(themedTextView);
            list.add(themedTextView);
            list2.add(themedTextView);
            if (i6 > 0) {
                LayoutParams layoutParams = new LayoutParams(-2, -2);
                layoutParams.leftMargin = i4;
                themedTextView.setLayoutParams(layoutParams);
            }
        }
    }

    private void setVisibility(View view, int i) {
        if (view.getVisibility() != i) {
            view.setVisibility(i);
        }
    }

    /* access modifiers changed from: protected */
    public void updateTimeUi(boolean z) {
        if (z) {
            pauseTimer();
        }
        int i = 0;
        boolean z2 = z && !this.mDisableExpiredText;
        Iterator it = this.mDigitElements.iterator();
        while (true) {
            int i2 = 8;
            if (!it.hasNext()) {
                break;
            }
            View view = (View) it.next();
            if (!z2) {
                i2 = 0;
            }
            setVisibility(view, i2);
        }
        TextView textView = this.mExpiredText;
        if (!z2) {
            i = 8;
        }
        setVisibility(textView, i);
        if (!this.mShowHour) {
            updateDigits(this.mMinuteElements, this.mTimeParts.minutes + (this.mTimeParts.hours * 60));
        } else {
            updateDigits(this.mHourElements, this.mTimeParts.hours);
            updateDigits(this.mMinuteElements, this.mTimeParts.minutes);
        }
        updateDigits(this.mSecondElements, this.mTimeParts.seconds);
    }

    public void setBlankBackgroundColorResId(int i) {
        if (this.mDigitElements != null) {
            for (TextView textView : this.mDigitElements) {
                if (!textView.getText().toString().contentEquals(":")) {
                    textView.setBackgroundColor(getResources().getColor(i));
                }
                textView.setText("");
            }
        }
    }

    @SuppressLint({"SetTextI18n"})
    private void updateDigits(List<TextView> list, long j) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                int i = ((int) j) % 10;
                j /= 10;
                ((TextView) list.get(size)).setText(Integer.toString(i));
            }
        }
    }
}
