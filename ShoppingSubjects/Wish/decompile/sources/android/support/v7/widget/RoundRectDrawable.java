package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

class RoundRectDrawable extends Drawable {
    private final RectF mBoundsF;
    private final Rect mBoundsI;
    private boolean mInsetForPadding = false;
    private boolean mInsetForRadius = true;
    private float mPadding;
    private final Paint mPaint;
    private float mRadius;
    private ColorStateList mTint;
    private PorterDuffColorFilter mTintFilter;
    private Mode mTintMode = Mode.SRC_IN;

    public int getOpacity() {
        return -3;
    }

    public RoundRectDrawable(int i, float f) {
        this.mRadius = f;
        this.mPaint = new Paint(5);
        this.mPaint.setColor(i);
        this.mBoundsF = new RectF();
        this.mBoundsI = new Rect();
    }

    /* access modifiers changed from: 0000 */
    public void setPadding(float f, boolean z, boolean z2) {
        if (f != this.mPadding || this.mInsetForPadding != z || this.mInsetForRadius != z2) {
            this.mPadding = f;
            this.mInsetForPadding = z;
            this.mInsetForRadius = z2;
            updateBounds(null);
            invalidateSelf();
        }
    }

    /* access modifiers changed from: 0000 */
    public float getPadding() {
        return this.mPadding;
    }

    public void draw(Canvas canvas) {
        boolean z;
        Paint paint = this.mPaint;
        if (this.mTintFilter == null || paint.getColorFilter() != null) {
            z = false;
        } else {
            paint.setColorFilter(this.mTintFilter);
            z = true;
        }
        canvas.drawRoundRect(this.mBoundsF, this.mRadius, this.mRadius, paint);
        if (z) {
            paint.setColorFilter(null);
        }
    }

    private void updateBounds(Rect rect) {
        if (rect == null) {
            rect = getBounds();
        }
        this.mBoundsF.set((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom);
        this.mBoundsI.set(rect);
        if (this.mInsetForPadding) {
            float calculateVerticalPadding = RoundRectDrawableWithShadow.calculateVerticalPadding(this.mPadding, this.mRadius, this.mInsetForRadius);
            this.mBoundsI.inset((int) Math.ceil((double) RoundRectDrawableWithShadow.calculateHorizontalPadding(this.mPadding, this.mRadius, this.mInsetForRadius)), (int) Math.ceil((double) calculateVerticalPadding));
            this.mBoundsF.set(this.mBoundsI);
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        updateBounds(rect);
    }

    public void getOutline(Outline outline) {
        outline.setRoundRect(this.mBoundsI, this.mRadius);
    }

    /* access modifiers changed from: 0000 */
    public void setRadius(float f) {
        if (f != this.mRadius) {
            this.mRadius = f;
            updateBounds(null);
            invalidateSelf();
        }
    }

    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public float getRadius() {
        return this.mRadius;
    }

    public void setColor(int i) {
        this.mPaint.setColor(i);
        invalidateSelf();
    }

    public void setTintList(ColorStateList colorStateList) {
        this.mTint = colorStateList;
        this.mTintFilter = createTintFilter(this.mTint, this.mTintMode);
        invalidateSelf();
    }

    public void setTintMode(Mode mode) {
        this.mTintMode = mode;
        this.mTintFilter = createTintFilter(this.mTint, this.mTintMode);
        invalidateSelf();
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        if (this.mTint == null || this.mTintMode == null) {
            return false;
        }
        this.mTintFilter = createTintFilter(this.mTint, this.mTintMode);
        return true;
    }

    public boolean isStateful() {
        return (this.mTint != null && this.mTint.isStateful()) || super.isStateful();
    }

    private PorterDuffColorFilter createTintFilter(ColorStateList colorStateList, Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }
}
