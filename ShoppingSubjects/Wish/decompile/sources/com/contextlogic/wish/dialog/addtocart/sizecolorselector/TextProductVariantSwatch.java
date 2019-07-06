package com.contextlogic.wish.dialog.addtocart.sizecolorselector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class TextProductVariantSwatch extends BaseProductVariantSwatch {
    private View mBackground;
    private ThemedTextView mButtonText;
    private Paint mStrikeThroughPaint;

    public int getLayoutResourceId() {
        return R.layout.product_variant_text_swatch;
    }

    public TextProductVariantSwatch(Context context) {
        this(context, null);
    }

    public TextProductVariantSwatch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TextProductVariantSwatch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: 0000 */
    public void init() {
        super.init();
        this.mStrikeThroughPaint = new Paint();
        this.mStrikeThroughPaint.setColor(getResources().getColor(R.color.gray5));
        this.mStrikeThroughPaint.setAntiAlias(true);
        this.mStrikeThroughPaint.setStyle(Style.FILL);
        this.mStrikeThroughPaint.setStrokeWidth(getResources().getDimension(R.dimen.size_color_selector_dialog_fragment_text_swatch_disable_bar_thickness));
        this.mButtonText = (ThemedTextView) findViewById(R.id.text_swatch_button_text);
        this.mBackground = findViewById(R.id.text_swatch_selected_ring);
        this.mBackground.setBackgroundResource(R.drawable.size_color_picker_text_swatch);
        setSelected(false);
    }

    public void setEnabled(boolean z) {
        this.mBackground.setEnabled(z);
        if (z) {
            this.mButtonText.setTextColor(getResources().getColor(R.color.gray1));
        } else {
            this.mButtonText.setTextColor(getResources().getColor(R.color.gray4));
        }
        super.setEnabled(z);
    }

    public void setSelected(boolean z) {
        if (isEnabled()) {
            if (z) {
                this.mButtonText.setTypeface(1);
                this.mButtonText.setTextColor(getResources().getColor(R.color.wish_blue));
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRODUCT_DETAILS_TEXT_SWATCH);
            } else {
                this.mButtonText.setTypeface(0);
                this.mButtonText.setTextColor(getResources().getColor(R.color.gray1));
            }
            super.setSelected(z);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (!isEnabled()) {
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.size_color_selector_dialog_fragment_text_swatch_padding) + getResources().getDimensionPixelOffset(R.dimen.default_border_stroke);
            Canvas canvas2 = canvas;
            canvas2.drawLine((float) (((getWidth() + 0) - getResources().getDimensionPixelOffset(R.dimen.twelve_padding)) - getResources().getDimensionPixelOffset(R.dimen.size_color_selector_dialog_fragment_text_swatch_corner_radius)), (float) dimensionPixelOffset, (float) (getResources().getDimensionPixelOffset(R.dimen.size_color_selector_dialog_fragment_text_swatch_corner_radius) + 0), (float) ((getResources().getDimensionPixelOffset(R.dimen.size_color_selector_dialog_fragment_text_swatch_ring_size) + dimensionPixelOffset) - getResources().getDimensionPixelOffset(R.dimen.size_color_selector_dialog_fragment_swatch_outline_thickness)), this.mStrikeThroughPaint);
        }
    }

    public void setText(String str) {
        this.mButtonText.setText(str);
    }
}
