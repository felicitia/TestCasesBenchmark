package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.q;

public class DiagonalStrikeImageView extends AppCompatImageView {
    @ColorInt
    private int mLineColor;
    private int mLineWidth;
    private Paint mPaint;

    public DiagonalStrikeImageView(Context context) {
        super(context);
        init(context, null);
    }

    public DiagonalStrikeImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public DiagonalStrikeImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        Resources resources = context.getResources();
        this.mLineColor = resources.getColor(e.red);
        this.mLineWidth = resources.getDimensionPixelSize(f.double_divider_height);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, q.DiagonalStrikeImageView, 0, 0);
            try {
                this.mLineColor = obtainStyledAttributes.getColor(q.DiagonalStrikeImageView_diagonalLineColor, this.mLineColor);
                this.mLineWidth = obtainStyledAttributes.getDimensionPixelSize(q.DiagonalStrikeImageView_diagonalLineWidth, this.mLineWidth);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(this.mLineColor);
        this.mPaint.setStrokeWidth((float) this.mLineWidth);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0.0f, (float) getHeight(), (float) getWidth(), 0.0f, this.mPaint);
    }
}
