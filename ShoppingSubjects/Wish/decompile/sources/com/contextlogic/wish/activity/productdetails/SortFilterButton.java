package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.CompoundButton;
import com.contextlogic.wish.R;
import java.util.Locale;

public class SortFilterButton extends CompoundButton {
    private long mCount = 0;
    private FilterType mFilterType;
    private boolean mHasDrawable = false;
    private String mText;

    public SortFilterButton(Context context) {
        super(context);
        setTextColor(ContextCompat.getColorStateList(context, R.color.filter_button_color_states));
        setBackgroundResource(R.drawable.filter_button_background_selector);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.eight_padding);
        setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        setChecked(false);
    }

    public void setFilterType(FilterType filterType) {
        this.mFilterType = filterType;
        Drawable displayDrawable = filterType.toDisplayDrawable(getContext());
        if (displayDrawable != null) {
            displayDrawable.setBounds(0, 0, displayDrawable.getIntrinsicWidth(), displayDrawable.getIntrinsicHeight());
            setCompoundDrawables(displayDrawable, null, null, null);
            this.mHasDrawable = true;
        }
        this.mText = filterType.toDisplayString(getContext());
        if (!this.mHasDrawable) {
            setText(this.mText);
        }
        setContentDescription(this.mText);
    }

    public FilterType getFilterType() {
        return this.mFilterType;
    }

    public void setCount(long j) {
        this.mCount = j;
        if (this.mCount == 0) {
            setAlpha(0.4f);
            setEnabled(false);
            return;
        }
        setAlpha(1.0f);
        setEnabled(true);
    }

    public void setShowCount(boolean z) {
        if (z) {
            if (this.mHasDrawable) {
                setText(String.format(Locale.getDefault(), " (%d)", new Object[]{Long.valueOf(this.mCount)}));
                return;
            }
            setText(String.format(Locale.getDefault(), "%s (%d)", new Object[]{this.mText, Long.valueOf(this.mCount)}));
        } else if (!this.mHasDrawable) {
            setText(this.mText);
        }
    }
}
