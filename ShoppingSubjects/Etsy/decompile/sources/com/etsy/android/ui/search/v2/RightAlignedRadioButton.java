package com.etsy.android.ui.search.v2;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import com.etsy.android.lib.util.k;

public class RightAlignedRadioButton extends AppCompatRadioButton {
    private a mButtonDrawable;

    private static class a extends LayerDrawable {
        private Drawable a;
        private boolean b = false;

        public int getIntrinsicHeight() {
            return 0;
        }

        public int getIntrinsicWidth() {
            return 0;
        }

        public a(Drawable drawable) {
            super(new Drawable[]{drawable});
            this.a = drawable;
        }

        public void draw(Canvas canvas) {
            if (this.b) {
                this.a.draw(canvas);
            }
        }

        public void jumpToCurrentState() {
            super.jumpToCurrentState();
            this.a.jumpToCurrentState();
            invalidateSelf();
        }

        public Rect getDirtyBounds() {
            return this.a.getBounds();
        }

        public void a(Canvas canvas, int i, int i2, int i3, int i4) {
            if (this.a != null) {
                this.b = true;
                this.a.setBounds(i, i2, i3, i4);
                draw(canvas);
                this.b = false;
            }
        }

        public int a() {
            return this.a.getIntrinsicWidth();
        }

        public int b() {
            return this.a.getIntrinsicHeight();
        }
    }

    public RightAlignedRadioButton(Context context) {
        super(context);
    }

    public RightAlignedRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RightAlignedRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    @TargetApi(21)
    public void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        int a2 = this.mButtonDrawable.a();
        int b = this.mButtonDrawable.b();
        int width = (getWidth() - a2) - getPaddingRight();
        int height = (getHeight() - b) / 2;
        int width2 = getWidth() - getPaddingRight();
        int i2 = b + height;
        if (!k.a() || getLayoutDirection() != 1) {
            i = width2;
        } else {
            width = getPaddingStart();
            i = a2 + getPaddingStart();
        }
        this.mButtonDrawable.a(canvas, width, height, i, i2);
        if (k.c()) {
            Drawable background = getBackground();
            if (background != null) {
                background.setHotspotBounds(width, height, i, i2);
            }
        }
    }

    public void setButtonDrawable(Drawable drawable) {
        this.mButtonDrawable = new a(drawable);
        super.setButtonDrawable((Drawable) this.mButtonDrawable);
    }
}
