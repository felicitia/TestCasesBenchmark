package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.etsy.android.iconsy.a;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.a.g;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.a.q;
import com.etsy.android.stylekit.e;
import com.etsy.android.uikit.c;

public class ProgressButton extends FrameLayout {
    private View mActivityIndicator;
    private LoadingIndicatorView mActivityIndicatorView;
    protected TextView mText;

    public ProgressButton(Context context) {
        super(context);
        setUpView(context);
    }

    public ProgressButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setUpView(context);
        setAttributes(context, attributeSet);
    }

    public ProgressButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setUpView(context);
        setAttributes(context, attributeSet);
    }

    @TargetApi(21)
    public ProgressButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setUpView(context);
        setAttributes(context, attributeSet);
    }

    private void setUpView(Context context) {
        if (!isInEditMode()) {
            ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(k.view_progress_button, this, true);
            this.mText = (TextView) findViewById(i.text);
            this.mActivityIndicator = findViewById(i.activity_indicator);
            this.mActivityIndicatorView = (LoadingIndicatorView) findViewById(i.activity_indicator_view);
        }
    }

    private void setAttributes(Context context, AttributeSet attributeSet) {
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, q.ProgressButton, 0, 0);
            try {
                setText(obtainStyledAttributes.getString(q.ProgressButton_text));
                int resourceId = obtainStyledAttributes.getResourceId(q.ProgressButton_drawableLeftProgButton, -1);
                if (resourceId != -1) {
                    setDrawableLeft(resourceId);
                }
                this.mText.setTextAppearance(context, obtainStyledAttributes.getResourceId(q.ProgressButton_textStyleResource, 0));
                this.mActivityIndicatorView.setImageResource(obtainStyledAttributes.getResourceId(q.ProgressButton_activityIndicatorDrawable, g.progress_spinner_etsy));
                int color = obtainStyledAttributes.getColor(q.ProgressButton_drawableTintProgButton, -1);
                if (color != -1) {
                    setDrawableTint(color);
                }
                setDrawablePadding(obtainStyledAttributes.getDimensionPixelSize(q.ProgressButton_drawablePaddingProgButton, 0));
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    public void setText(String str) {
        this.mText.setText(str);
    }

    public void setText(int i) {
        this.mText.setText(i);
    }

    public void setTextIsBold(boolean z) {
        e.a(this.mText, z ? o.sk_typeface_bold : o.sk_typeface_normal);
    }

    public void setDrawableLeft(int i) {
        if (com.etsy.android.lib.util.k.c()) {
            setDrawableLeft(c.a(getContext(), i));
        } else {
            this.mText.setCompoundDrawablesWithIntrinsicBounds(i, 0, 0, 0);
        }
    }

    public void setDrawableLeft(Drawable drawable) {
        this.mText.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }

    public void setDrawableTint(int i) {
        Drawable[] compoundDrawables;
        for (Drawable drawable : this.mText.getCompoundDrawables()) {
            if (drawable != null) {
                DrawableCompat.setTint(drawable, i);
            }
        }
    }

    public void setDrawableLeft(a aVar, int i, float f) {
        setDrawableLeft((Drawable) IconDrawable.a.a(getResources()).a(aVar).a(i).a(f).a());
    }

    public void clearDrawables() {
        this.mText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    public void setDrawablePadding(int i) {
        this.mText.setCompoundDrawablePadding(i);
    }

    public void setTextColor(int i) {
        this.mText.setTextColor(i);
    }

    public void showLoading() {
        setEnabled(false);
        this.mText.setVisibility(8);
        this.mActivityIndicator.setVisibility(0);
    }

    public void hideLoading() {
        setEnabled(true);
        this.mText.setVisibility(0);
        this.mActivityIndicator.setVisibility(8);
    }
}
