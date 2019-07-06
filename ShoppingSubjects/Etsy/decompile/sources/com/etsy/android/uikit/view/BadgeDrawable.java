package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.o;
import com.etsy.android.stylekit.e;

public class BadgeDrawable extends Drawable {
    private ColorStateList mBadgeColorStateList;
    private Paint mBadgePaint;
    private String mBadgeText = "";
    private CircleType mCircleType = CircleType.CIRCLE;
    private int mHeight;
    private int mPadding;
    private CirclePosition mPosition = CirclePosition.FULL;
    private RectF mRoundedRect;
    private Paint mStrokePaint;
    private ColorStateList mTextColorStateList;
    private Paint mTextPaint;
    private float mTextSize;
    private Rect mTxtRect = new Rect();
    private int mWidth;
    private boolean mWillDraw = false;

    public enum CirclePosition {
        FULL,
        TOP_RIGHT
    }

    public enum CircleType {
        CIRCLE,
        ROUNDED_RECT
    }

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public BadgeDrawable(Context context, int i, int i2) {
        Resources resources = context.getResources();
        this.mTextSize = resources.getDimension(f.default_badge_text_size);
        this.mBadgeColorStateList = resources.getColorStateList(i);
        this.mTextColorStateList = resources.getColorStateList(i2);
        this.mBadgePaint = new Paint();
        this.mBadgePaint.setColor(this.mBadgeColorStateList.getDefaultColor());
        this.mBadgePaint.setAntiAlias(true);
        this.mBadgePaint.setStyle(Style.FILL);
        this.mTextPaint = new Paint();
        this.mTextPaint.setColor(this.mTextColorStateList.getDefaultColor());
        e.a(context, this.mTextPaint, o.sk_typeface_bold);
        this.mTextPaint.setTextSize(this.mTextSize);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextAlign(Align.CENTER);
        this.mRoundedRect = new RectF();
    }

    public void draw(Canvas canvas) {
        float f;
        float f2;
        float f3;
        if (this.mWillDraw) {
            Rect bounds = getBounds();
            float f4 = (float) (bounds.right - bounds.left);
            float f5 = (float) (bounds.bottom - bounds.top);
            if (this.mPosition == CirclePosition.TOP_RIGHT) {
                f3 = ((Math.min(f4, f5) / 2.0f) - 1.0f) / 2.0f;
                f2 = (f4 - f3) - 1.0f;
                f = 1.0f + f3;
            } else {
                f3 = Math.min(f4, f5) / 2.0f;
                f2 = f3;
                f = f2;
            }
            if (this.mPadding > 0) {
                f3 -= (float) this.mPadding;
            }
            this.mTextPaint.getTextBounds(this.mBadgeText, 0, this.mBadgeText.length(), this.mTxtRect);
            float f6 = (float) (this.mTxtRect.bottom - this.mTxtRect.top);
            float f7 = ((f6 / 2.0f) - ((float) this.mTxtRect.bottom)) + f;
            if (this.mCircleType == CircleType.CIRCLE) {
                canvas.drawCircle(f2, f, f3, this.mBadgePaint);
                if (this.mStrokePaint != null) {
                    canvas.drawCircle(f2, f, f3, this.mStrokePaint);
                }
            } else if (this.mCircleType == CircleType.ROUNDED_RECT) {
                RectF rectF = this.mRoundedRect;
                rectF.set(this.mTxtRect);
                float f8 = 1.5f * f6;
                rectF.top = f - f8;
                rectF.bottom = f + f8;
                rectF.left -= f6;
                rectF.right += f6 - 2.0f;
                rectF.top -= (float) this.mPadding;
                rectF.bottom += (float) this.mPadding;
                rectF.left -= (float) this.mPadding;
                rectF.right += (float) this.mPadding;
                canvas.drawRoundRect(rectF, f3, f3, this.mBadgePaint);
                if (this.mStrokePaint != null) {
                    canvas.drawRoundRect(rectF, f3, f3, this.mStrokePaint);
                }
            }
            canvas.drawText(this.mBadgeText, f2, f7, this.mTextPaint);
        }
    }

    public void setCount(int i) {
        this.mBadgeText = Integer.toString(i);
        this.mWillDraw = i > 0;
        invalidateSelf();
    }

    public void setBadgeText(String str) {
        this.mBadgeText = str;
        this.mWillDraw = true;
        invalidateSelf();
    }

    public String getBadgeText() {
        return this.mBadgeText;
    }

    public int getIntrinsicWidth() {
        return this.mWidth > 0 ? this.mWidth : super.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        return this.mHeight > 0 ? this.mHeight : super.getIntrinsicHeight();
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        this.mHeight = rect.height();
        this.mWidth = rect.width();
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        this.mTextPaint.setColor(this.mTextColorStateList.getColorForState(iArr, this.mTextColorStateList.getDefaultColor()));
        this.mBadgePaint.setColor(this.mBadgeColorStateList.getColorForState(iArr, this.mBadgeColorStateList.getDefaultColor()));
        return true;
    }

    public void setPadding(int i) {
        this.mPadding = i;
        invalidateSelf();
    }

    public void setBadgeTextSize(int i) {
        this.mTextPaint.setTextSize((float) i);
        invalidateSelf();
    }

    public void setStroke(int i, int i2) {
        this.mStrokePaint = new Paint();
        this.mStrokePaint.setColor(i2);
        this.mStrokePaint.setStrokeWidth((float) i);
        this.mStrokePaint.setStyle(Style.STROKE);
        this.mStrokePaint.setAntiAlias(true);
        invalidateSelf();
    }

    public CircleType getCircleType() {
        return this.mCircleType;
    }

    public void setCircleType(CircleType circleType) {
        this.mCircleType = circleType;
    }
}
