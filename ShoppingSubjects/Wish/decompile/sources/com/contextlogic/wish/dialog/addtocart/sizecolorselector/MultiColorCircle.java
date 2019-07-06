package com.contextlogic.wish.dialog.addtocart.sizecolorselector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.contextlogic.wish.R;
import java.util.ArrayList;
import java.util.List;

public class MultiColorCircle extends View {
    private List<Paint> mColorPaints;
    private Paint mGrayStrokePaint;
    private Paint mOutlinePaint;
    private RectF mRect;
    private Paint mWhiteStrokePaint;

    public MultiColorCircle(Context context) {
        this(context, null);
    }

    public MultiColorCircle(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MultiColorCircle(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mRect = new RectF();
        this.mWhiteStrokePaint = new Paint();
        this.mWhiteStrokePaint.setColor(getResources().getColor(R.color.white));
        this.mWhiteStrokePaint.setAntiAlias(true);
        this.mWhiteStrokePaint.setStyle(Style.FILL);
        this.mWhiteStrokePaint.setStrokeWidth(getResources().getDimension(R.dimen.size_color_selector_dialog_fragment_color_swatch_disable_bar_thickness));
        this.mGrayStrokePaint = new Paint();
        this.mGrayStrokePaint.setColor(getResources().getColor(R.color.gray5));
        this.mGrayStrokePaint.setAntiAlias(true);
        this.mGrayStrokePaint.setStyle(Style.FILL);
        this.mGrayStrokePaint.setStrokeWidth(getResources().getDimension(R.dimen.size_color_selector_dialog_fragment_color_swatch_disable_bar_thickness));
        this.mOutlinePaint = new Paint();
        this.mOutlinePaint.setColor(getResources().getColor(R.color.gray5));
        this.mOutlinePaint.setAntiAlias(true);
        this.mOutlinePaint.setStyle(Style.FILL);
        this.mOutlinePaint.setStrokeWidth((float) getResources().getDimensionPixelSize(R.dimen.size_color_selector_dialog_fragment_swatch_outline_thickness));
    }

    public void setColors(List<Integer> list) {
        this.mColorPaints = new ArrayList();
        for (Integer intValue : list) {
            int intValue2 = intValue.intValue();
            Paint paint = new Paint();
            paint.setColor(intValue2);
            paint.setAntiAlias(true);
            paint.setStyle(Style.FILL);
            this.mColorPaints.add(paint);
        }
    }

    private void setAlphas(boolean z) {
        for (Paint paint : this.mColorPaints) {
            if (z) {
                paint.setAlpha(255);
            } else {
                paint.setAlpha(25);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mColorPaints != null && this.mColorPaints.size() > 0) {
            int width = getWidth() + 0;
            int height = getHeight() + 0;
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.size_color_selector_dialog_fragment_swatch_outline_thickness);
            int size = (360 / this.mColorPaints.size()) + 1;
            int i = (this.mColorPaints.size() % 2 == 0 ? 135 : 0) - 1;
            setAlphas(isEnabled());
            for (Paint paint : this.mColorPaints) {
                if (paint.getColor() == -1 || paint.getColor() == getResources().getColor(R.color.gray5)) {
                    this.mRect.set(0.0f, 0.0f, (float) width, (float) height);
                    canvas.drawArc(this.mRect, (float) i, (float) size, true, this.mOutlinePaint);
                    float f = (float) (dimensionPixelSize + 0);
                    this.mRect.set(f, f, (float) (width - dimensionPixelSize), (float) (height - dimensionPixelSize));
                } else {
                    this.mRect.set(0.0f, 0.0f, (float) width, (float) height);
                }
                canvas.drawArc(this.mRect, (float) i, (float) size, true, paint);
                i += size;
            }
            if (isEnabled()) {
                return;
            }
            if (this.mColorPaints.size() == 1 && ((Paint) this.mColorPaints.get(0)).getColor() == -1) {
                canvas.drawLine((float) width, 0.0f, 0.0f, (float) height, this.mGrayStrokePaint);
            } else {
                canvas.drawLine((float) width, 0.0f, 0.0f, (float) height, this.mWhiteStrokePaint);
            }
        }
    }
}
