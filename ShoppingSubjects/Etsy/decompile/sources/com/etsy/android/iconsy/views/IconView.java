package com.etsy.android.iconsy.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.etsy.android.iconsy.a;
import com.etsy.android.iconsy.c;
import com.etsy.android.iconsy.e;
import com.etsy.android.iconsy.e.b;

public class IconView extends ImageView {
    private static final int NONE = -1;
    int mAlpha;
    int mColor;
    boolean mCreateSelector;
    int mGravity;
    a mIcon;

    public void setScaleType(ScaleType scaleType) {
    }

    public IconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
        setupDrawable();
    }

    public IconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
        setupDrawable();
    }

    /* JADX INFO: finally extract failed */
    private void init(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, b.IconView);
        try {
            int resourceId = obtainStyledAttributes.getResourceId(b.IconView_iconChar, 0);
            if (!isInEditMode()) {
                if (resourceId != 0) {
                    this.mIcon = c.a(resourceId);
                    this.mCreateSelector = obtainStyledAttributes.getBoolean(b.IconView_hasSelector, false);
                    this.mAlpha = obtainStyledAttributes.getInteger(b.IconView_iconAlpha, -1);
                    this.mColor = obtainStyledAttributes.getColor(b.IconView_iconColor, ViewCompat.MEASURED_STATE_MASK);
                    this.mGravity = obtainStyledAttributes.getInt(b.IconView_gravity, -1);
                    obtainStyledAttributes.recycle();
                    setScaleType(ScaleType.FIT_XY);
                }
            }
            this.mIcon = c.a(e.a.ic_demo_example);
            this.mCreateSelector = obtainStyledAttributes.getBoolean(b.IconView_hasSelector, false);
            this.mAlpha = obtainStyledAttributes.getInteger(b.IconView_iconAlpha, -1);
            this.mColor = obtainStyledAttributes.getColor(b.IconView_iconColor, ViewCompat.MEASURED_STATE_MASK);
            this.mGravity = obtainStyledAttributes.getInt(b.IconView_gravity, -1);
            obtainStyledAttributes.recycle();
            setScaleType(ScaleType.FIT_XY);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private void setupDrawable() {
        if (isInEditMode()) {
            setImageResource(17301555);
            return;
        }
        IconDrawable.a b = IconDrawable.a.a(getResources()).a(this.mColor).a(this.mIcon).c(this.mGravity).b(this.mAlpha);
        if (this.mCreateSelector) {
            setImageDrawable(IconSelectorDrawable.create(b));
        } else {
            setImageDrawable(b.a());
        }
    }

    public void setIcon(a aVar) {
        this.mIcon = aVar;
        setupDrawable();
    }

    public void setGravity(int i) {
        this.mGravity = i;
        setupDrawable();
    }

    public void setColor(@ColorInt int i) {
        this.mColor = i;
        setupDrawable();
    }
}
