package com.etsy.android.stylekit.views.dropdown;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import com.etsy.android.stylekit.a.b;
import com.etsy.android.stylekit.d;

public class SpinnerBackgroundDrawable extends Drawable {
    private boolean drawUnderline = false;
    private Drawable icon;
    private int spacing;
    private Paint underlinePaint = new Paint();
    private float underlineWidth;

    public SpinnerBackgroundDrawable(Context context, boolean z) {
        this.icon = d.a(context, b.token_dropdown__spinner_icon);
        DrawableCompat.setTintList(this.icon, d.b(context, b.token_dropdown__spinner_icon_color));
        this.spacing = d.c(context, b.token_dropdown__spinner_spacing);
        this.drawUnderline = z;
        this.underlineWidth = (float) d.c(context, b.token_dropdown__spinner_underline_width);
        this.underlinePaint.setColor(d.b(context, b.token_dropdown__spinner_underline_color).getDefaultColor());
        this.underlinePaint.setStrokeWidth(this.underlineWidth);
    }

    public void draw(@NonNull Canvas canvas) {
        if (this.drawUnderline) {
            canvas.drawLine(0.0f, ((float) canvas.getHeight()) - this.underlineWidth, (float) canvas.getWidth(), ((float) canvas.getHeight()) - this.underlineWidth, this.underlinePaint);
        }
        int max = Math.max(this.icon.getIntrinsicWidth(), this.icon.getMinimumWidth());
        int max2 = Math.max(this.icon.getIntrinsicHeight(), this.icon.getMinimumHeight());
        int width = (canvas.getWidth() - max) + (this.spacing / 2);
        int height = (canvas.getHeight() - max2) / 2;
        this.icon.setBounds(width, height, max + width, max2 + height);
        this.icon.draw(canvas);
    }

    public void setAlpha(int i) {
        this.icon.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.icon.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return this.icon.getOpacity();
    }

    public int getIntrinsicWidth() {
        int intrinsicWidth = this.icon.getIntrinsicWidth();
        if (intrinsicWidth < 0) {
            return -1;
        }
        return intrinsicWidth + this.spacing;
    }

    public int getIntrinsicHeight() {
        int intrinsicHeight = this.icon.getIntrinsicHeight();
        if (intrinsicHeight < 0) {
            return -1;
        }
        return intrinsicHeight + this.spacing;
    }

    public int getMinimumWidth() {
        return this.icon.getMinimumWidth() + this.spacing;
    }

    public int getMinimumHeight() {
        return this.icon.getMinimumHeight() + this.spacing;
    }
}
