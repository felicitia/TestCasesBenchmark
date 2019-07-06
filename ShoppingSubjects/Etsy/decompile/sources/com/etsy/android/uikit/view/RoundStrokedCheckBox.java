package com.etsy.android.uikit.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;
import android.util.StateSet;
import com.etsy.android.iconsy.views.IconDrawable.a;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.q;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.util.fonts.StandardFontIcon;
import com.etsy.android.lib.util.k;

public class RoundStrokedCheckBox extends AppCompatCheckBox {
    @ColorInt
    private int backgroundColor;
    @ColorInt
    private int drawableColor;
    @DimenRes
    private int drawableInset;
    @ColorInt
    private int strokeColor;
    /* access modifiers changed from: private */
    @DimenRes
    public int strokeWidth;

    public RoundStrokedCheckBox(Context context) {
        super(context);
        init(null, 0);
    }

    public RoundStrokedCheckBox(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0);
    }

    public RoundStrokedCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i);
    }

    @SuppressLint({"ResourceAsColor"})
    private void init(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, q.RoundStrokedCheckBox);
        this.strokeWidth = obtainStyledAttributes.getDimensionPixelSize(q.RoundStrokedCheckBox_unselectedStrokeWidth, 2);
        this.strokeColor = obtainStyledAttributes.getColor(q.RoundStrokedCheckBox_unselectedStrokeColor, getResources().getColor(e.sk_white));
        this.backgroundColor = obtainStyledAttributes.getColor(q.RoundStrokedCheckBox_selectedBackgroundColor, getResources().getColor(17170444));
        this.drawableColor = obtainStyledAttributes.getColor(q.RoundStrokedCheckBox_selectedDrawableColor, getResources().getColor(e.sk_white));
        this.drawableInset = obtainStyledAttributes.getDimensionPixelSize(q.RoundStrokedCheckBox_selectedDrawableInset, 0);
        obtainStyledAttributes.recycle();
        Drawable backgroundDrawable = getBackgroundDrawable();
        backgroundDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        setBackgroundDrawable(backgroundDrawable);
        setButtonDrawable((Drawable) null);
    }

    private Drawable getBackgroundDrawable() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16843518}, getCheckedDrawable());
        stateListDrawable.addState(StateSet.WILD_CARD, getDefaultDrawable());
        return stateListDrawable;
    }

    private Drawable getDefaultDrawable() {
        AnonymousClass1 r0 = new ShapeDrawable(new OvalShape()) {
            public void setBounds(int i, int i2, int i3, int i4) {
                Rect rect = new Rect(i, i2, i3, i4);
                rect.inset(RoundStrokedCheckBox.this.strokeWidth / 2, RoundStrokedCheckBox.this.strokeWidth / 2);
                super.setBounds(rect.left, rect.top, rect.right, rect.bottom);
            }

            @TargetApi(21)
            public void getOutline(Outline outline) {
                if (k.c()) {
                    outline.setOval(getBounds());
                }
            }
        };
        r0.getPaint().setColor(this.strokeColor);
        r0.getPaint().setStrokeWidth((float) this.strokeWidth);
        r0.getPaint().setStyle(Style.STROKE);
        r0.getPaint().setAntiAlias(true);
        return r0;
    }

    private Drawable getCheckedDrawable() {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(this.backgroundColor);
        shapeDrawable.getPaint().setStyle(Style.FILL);
        shapeDrawable.getPaint().setAntiAlias(true);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{shapeDrawable, a.a(EtsyApplication.get().getResources()).a((com.etsy.android.iconsy.a) StandardFontIcon.CHECK).a(this.drawableColor).c(17).a()});
        layerDrawable.setLayerInset(1, this.drawableInset + (this.strokeWidth / 2), this.drawableInset + this.strokeWidth, this.drawableInset, this.drawableInset);
        return layerDrawable;
    }
}
