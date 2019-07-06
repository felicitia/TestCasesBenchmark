package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.q;
import com.etsy.android.uikit.c;

public class IconButton extends AppCompatButton {
    private final int DEFAULT_ICON_COLOR = e.sk_gray_70;
    private Drawable mIcon;
    private int mIconAltColor;
    private Drawable mIconAlternate;
    private int mIconColor;
    private int mIconPadding;
    private int mIconSize;
    private boolean mShowAlt = false;

    public IconButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    public IconButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public IconButton(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i = (this.mIconSize + this.mIconPadding) / 2;
        canvas.save();
        canvas.translate((float) i, 0.0f);
        super.onDraw(canvas);
        Drawable drawable = this.mIcon;
        int i2 = this.mIconColor;
        if (this.mShowAlt && this.mIconAlternate != null) {
            drawable = this.mIconAlternate;
            i2 = this.mIconAltColor;
        }
        if (drawable != null) {
            int width = (int) ((((((float) getWidth()) / 2.0f) - (getPaint().measureText((String) getText()) / 2.0f)) - ((float) this.mIconSize)) - ((float) this.mIconPadding));
            int height = (getHeight() / 2) - (this.mIconSize / 2);
            drawable.setBounds(width, height, this.mIconSize + width, this.mIconSize + height);
            DrawableCompat.setTint(drawable, i2);
            drawable.draw(canvas);
        }
        canvas.restore();
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, q.IconButton);
        this.mIconSize = getResources().getDimensionPixelSize(f.icon_button_size);
        this.mIconPadding = getResources().getDimensionPixelSize(f.icon_button_padding);
        for (int i = 0; i < obtainStyledAttributes.getIndexCount(); i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == q.IconButton_iconSrc) {
                this.mIcon = c.a(getContext(), obtainStyledAttributes.getResourceId(index, 0));
            } else if (index == q.IconButton_iconAltSrc) {
                this.mIconAlternate = c.a(getContext(), obtainStyledAttributes.getResourceId(index, 0));
            } else if (index == q.IconButton_iconPadding) {
                this.mIconPadding = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == q.IconButton_iconSize) {
                this.mIconSize = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == q.IconButton_showAlt) {
                this.mShowAlt = obtainStyledAttributes.getBoolean(index, false);
            } else if (index == q.IconButton_iconTint) {
                if (VERSION.SDK_INT >= 23) {
                    this.mIconColor = obtainStyledAttributes.getColor(index, getResources().getColor(this.DEFAULT_ICON_COLOR, getContext().getTheme()));
                } else {
                    this.mIconColor = obtainStyledAttributes.getColor(index, getResources().getColor(this.DEFAULT_ICON_COLOR));
                }
            } else if (index == q.IconButton_iconAltTint) {
                if (VERSION.SDK_INT >= 23) {
                    this.mIconAltColor = obtainStyledAttributes.getColor(index, getResources().getColor(this.DEFAULT_ICON_COLOR, getContext().getTheme()));
                } else {
                    this.mIconAltColor = obtainStyledAttributes.getColor(index, getResources().getColor(this.DEFAULT_ICON_COLOR));
                }
            }
        }
        obtainStyledAttributes.recycle();
    }

    public void setShowAlt(boolean z) {
        this.mShowAlt = z;
        invalidate();
    }
}
