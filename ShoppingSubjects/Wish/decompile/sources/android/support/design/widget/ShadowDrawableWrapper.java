package android.support.design.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.support.design.R;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.drawable.DrawableWrapper;

class ShadowDrawableWrapper extends DrawableWrapper {
    static final double COS_45 = Math.cos(Math.toRadians(45.0d));
    private boolean mAddPaddingForCorners = true;
    final RectF mContentBounds;
    float mCornerRadius;
    final Paint mCornerShadowPaint;
    Path mCornerShadowPath;
    private boolean mDirty = true;
    final Paint mEdgeShadowPaint;
    float mMaxShadowSize;
    private boolean mPrintedShadowClipWarning = false;
    float mRawMaxShadowSize;
    float mRawShadowSize;
    private float mRotation;
    private final int mShadowEndColor;
    private final int mShadowMiddleColor;
    float mShadowSize;
    private final int mShadowStartColor;

    public int getOpacity() {
        return -3;
    }

    public ShadowDrawableWrapper(Context context, Drawable drawable, float f, float f2, float f3) {
        super(drawable);
        this.mShadowStartColor = ContextCompat.getColor(context, R.color.design_fab_shadow_start_color);
        this.mShadowMiddleColor = ContextCompat.getColor(context, R.color.design_fab_shadow_mid_color);
        this.mShadowEndColor = ContextCompat.getColor(context, R.color.design_fab_shadow_end_color);
        this.mCornerShadowPaint = new Paint(5);
        this.mCornerShadowPaint.setStyle(Style.FILL);
        this.mCornerRadius = (float) Math.round(f);
        this.mContentBounds = new RectF();
        this.mEdgeShadowPaint = new Paint(this.mCornerShadowPaint);
        this.mEdgeShadowPaint.setAntiAlias(false);
        setShadowSize(f2, f3);
    }

    private static int toEven(float f) {
        int round = Math.round(f);
        return round % 2 == 1 ? round - 1 : round;
    }

    public void setAddPaddingForCorners(boolean z) {
        this.mAddPaddingForCorners = z;
        invalidateSelf();
    }

    public void setAlpha(int i) {
        super.setAlpha(i);
        this.mCornerShadowPaint.setAlpha(i);
        this.mEdgeShadowPaint.setAlpha(i);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        this.mDirty = true;
    }

    /* access modifiers changed from: 0000 */
    public void setShadowSize(float f, float f2) {
        if (f < 0.0f || f2 < 0.0f) {
            throw new IllegalArgumentException("invalid shadow size");
        }
        float even = (float) toEven(f);
        float even2 = (float) toEven(f2);
        if (even > even2) {
            if (!this.mPrintedShadowClipWarning) {
                this.mPrintedShadowClipWarning = true;
            }
            even = even2;
        }
        if (this.mRawShadowSize != even || this.mRawMaxShadowSize != even2) {
            this.mRawShadowSize = even;
            this.mRawMaxShadowSize = even2;
            this.mShadowSize = (float) Math.round(even * 1.5f);
            this.mMaxShadowSize = even2;
            this.mDirty = true;
            invalidateSelf();
        }
    }

    public boolean getPadding(Rect rect) {
        int ceil = (int) Math.ceil((double) calculateVerticalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
        int ceil2 = (int) Math.ceil((double) calculateHorizontalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
        rect.set(ceil2, ceil, ceil2, ceil);
        return true;
    }

    public static float calculateVerticalPadding(float f, float f2, boolean z) {
        return z ? (float) (((double) (f * 1.5f)) + ((1.0d - COS_45) * ((double) f2))) : f * 1.5f;
    }

    public static float calculateHorizontalPadding(float f, float f2, boolean z) {
        return z ? (float) (((double) f) + ((1.0d - COS_45) * ((double) f2))) : f;
    }

    public void draw(Canvas canvas) {
        if (this.mDirty) {
            buildComponents(getBounds());
            this.mDirty = false;
        }
        drawShadow(canvas);
        super.draw(canvas);
    }

    /* access modifiers changed from: 0000 */
    public final void setRotation(float f) {
        if (this.mRotation != f) {
            this.mRotation = f;
            invalidateSelf();
        }
    }

    private void drawShadow(Canvas canvas) {
        int i;
        float f;
        int i2;
        float f2;
        float f3;
        float f4;
        Canvas canvas2 = canvas;
        int save = canvas.save();
        canvas2.rotate(this.mRotation, this.mContentBounds.centerX(), this.mContentBounds.centerY());
        float f5 = (-this.mCornerRadius) - this.mShadowSize;
        float f6 = this.mCornerRadius;
        float f7 = f6 * 2.0f;
        boolean z = this.mContentBounds.width() - f7 > 0.0f;
        boolean z2 = this.mContentBounds.height() - f7 > 0.0f;
        float f8 = f6 / ((this.mRawShadowSize - (this.mRawShadowSize * 0.5f)) + f6);
        float f9 = f6 / ((this.mRawShadowSize - (this.mRawShadowSize * 0.25f)) + f6);
        float f10 = f6 / ((this.mRawShadowSize - (this.mRawShadowSize * 1.0f)) + f6);
        int save2 = canvas.save();
        canvas2.translate(this.mContentBounds.left + f6, this.mContentBounds.top + f6);
        canvas2.scale(f8, f9);
        canvas2.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (z) {
            canvas2.scale(1.0f / f8, 1.0f);
            i2 = save2;
            f = f10;
            i = save;
            f2 = f9;
            canvas2.drawRect(0.0f, f5, this.mContentBounds.width() - f7, -this.mCornerRadius, this.mEdgeShadowPaint);
        } else {
            i2 = save2;
            f = f10;
            i = save;
            f2 = f9;
        }
        canvas2.restoreToCount(i2);
        int save3 = canvas.save();
        canvas2.translate(this.mContentBounds.right - f6, this.mContentBounds.bottom - f6);
        float f11 = f;
        canvas2.scale(f8, f11);
        canvas2.rotate(180.0f);
        canvas2.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (z) {
            canvas2.scale(1.0f / f8, 1.0f);
            f3 = f2;
            f4 = f11;
            canvas2.drawRect(0.0f, f5, this.mContentBounds.width() - f7, (-this.mCornerRadius) + this.mShadowSize, this.mEdgeShadowPaint);
        } else {
            f3 = f2;
            f4 = f11;
        }
        canvas2.restoreToCount(save3);
        int save4 = canvas.save();
        canvas2.translate(this.mContentBounds.left + f6, this.mContentBounds.bottom - f6);
        canvas2.scale(f8, f4);
        canvas2.rotate(270.0f);
        canvas2.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (z2) {
            canvas2.scale(1.0f / f4, 1.0f);
            canvas2.drawRect(0.0f, f5, this.mContentBounds.height() - f7, -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas2.restoreToCount(save4);
        int save5 = canvas.save();
        canvas2.translate(this.mContentBounds.right - f6, this.mContentBounds.top + f6);
        float f12 = f3;
        canvas2.scale(f8, f12);
        canvas2.rotate(90.0f);
        canvas2.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (z2) {
            canvas2.scale(1.0f / f12, 1.0f);
            canvas2.drawRect(0.0f, f5, this.mContentBounds.height() - f7, -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas2.restoreToCount(save5);
        canvas2.restoreToCount(i);
    }

    private void buildShadowCorners() {
        RectF rectF = new RectF(-this.mCornerRadius, -this.mCornerRadius, this.mCornerRadius, this.mCornerRadius);
        RectF rectF2 = new RectF(rectF);
        rectF2.inset(-this.mShadowSize, -this.mShadowSize);
        if (this.mCornerShadowPath == null) {
            this.mCornerShadowPath = new Path();
        } else {
            this.mCornerShadowPath.reset();
        }
        this.mCornerShadowPath.setFillType(FillType.EVEN_ODD);
        this.mCornerShadowPath.moveTo(-this.mCornerRadius, 0.0f);
        this.mCornerShadowPath.rLineTo(-this.mShadowSize, 0.0f);
        this.mCornerShadowPath.arcTo(rectF2, 180.0f, 90.0f, false);
        this.mCornerShadowPath.arcTo(rectF, 270.0f, -90.0f, false);
        this.mCornerShadowPath.close();
        float f = -rectF2.top;
        if (f > 0.0f) {
            float f2 = this.mCornerRadius / f;
            float f3 = ((1.0f - f2) / 2.0f) + f2;
            RadialGradient radialGradient = r8;
            float[] fArr = {0.0f, f2, f3, 1.0f};
            Paint paint = this.mCornerShadowPaint;
            RadialGradient radialGradient2 = new RadialGradient(0.0f, 0.0f, f, new int[]{0, this.mShadowStartColor, this.mShadowMiddleColor, this.mShadowEndColor}, fArr, TileMode.CLAMP);
            paint.setShader(radialGradient);
        }
        Paint paint2 = this.mEdgeShadowPaint;
        float f4 = rectF.top;
        LinearGradient linearGradient = new LinearGradient(0.0f, f4, 0.0f, rectF2.top, new int[]{this.mShadowStartColor, this.mShadowMiddleColor, this.mShadowEndColor}, new float[]{0.0f, 0.5f, 1.0f}, TileMode.CLAMP);
        paint2.setShader(linearGradient);
        this.mEdgeShadowPaint.setAntiAlias(false);
    }

    private void buildComponents(Rect rect) {
        float f = this.mRawMaxShadowSize * 1.5f;
        this.mContentBounds.set(((float) rect.left) + this.mRawMaxShadowSize, ((float) rect.top) + f, ((float) rect.right) - this.mRawMaxShadowSize, ((float) rect.bottom) - f);
        getWrappedDrawable().setBounds((int) this.mContentBounds.left, (int) this.mContentBounds.top, (int) this.mContentBounds.right, (int) this.mContentBounds.bottom);
        buildShadowCorners();
    }

    public void setShadowSize(float f) {
        setShadowSize(f, this.mRawMaxShadowSize);
    }

    public float getShadowSize() {
        return this.mRawShadowSize;
    }
}
