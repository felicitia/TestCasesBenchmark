package com.etsy.android.iconsy.views;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.etsy.android.iconsy.f;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import java.util.HashMap;

public class IconDrawable extends Drawable {
    private static boolean sDebug = false;
    private final Rect mCopiedBounds;
    private int mGravity;
    private b mIconState;
    private boolean mMutated;
    private HashMap<String, SparseArray<SparseIntArray>> mSizeCache;
    private final Rect mTextMeas;
    private TextPaint mTextPaint;

    public static class a {
        private com.etsy.android.iconsy.a a;
        private int b;
        private float c = -1.0f;
        private int d = 17;
        private Resources e;
        private int f = -1;

        private a() {
        }

        public static a a(Resources resources) {
            return new a().b(resources);
        }

        private a b(Resources resources) {
            this.e = resources;
            return this;
        }

        public a a(com.etsy.android.iconsy.a aVar) {
            this.a = aVar;
            return this;
        }

        public a a(@ColorInt int i) {
            this.b = i;
            return this;
        }

        public a a(float f2) {
            this.c = f2;
            return this;
        }

        public a b(int i) {
            this.f = i;
            return this;
        }

        public a c(int i) {
            this.d = i;
            return this;
        }

        public IconDrawable a() {
            IconDrawable iconDrawable = new IconDrawable();
            iconDrawable.setColor(this.b);
            iconDrawable.setTextSize(this.c);
            iconDrawable.setIconFont(this.a);
            iconDrawable.setGravity(this.d);
            if (this.f > -1) {
                iconDrawable.setDefaultAlpha(this.f);
            }
            return iconDrawable;
        }
    }

    final class b extends ConstantState {
        com.etsy.android.iconsy.a a;
        TextPaint b;
        int c = 255;
        ColorFilter d;
        boolean e;

        public int getChangingConfigurations() {
            return 0;
        }

        b(b bVar) {
            if (bVar != null) {
                this.b = bVar.b;
                this.c = bVar.c;
                this.d = bVar.d;
                this.a = bVar.a;
                this.e = bVar.e;
                return;
            }
            this.b = new TextPaint(1);
            this.b.setTextAlign(Align.LEFT);
        }

        public String a() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.a.name());
            sb.append("_-_");
            sb.append(this.a.getClass());
            return sb.toString();
        }

        public float b() {
            return this.b.measureText(this.a.toString());
        }

        public FontMetrics c() {
            return this.b.getFontMetrics();
        }

        public String d() {
            return this.a.toString();
        }

        public Drawable newDrawable() {
            return new IconDrawable(this);
        }

        public Drawable newDrawable(Resources resources) {
            return new IconDrawable(this);
        }
    }

    private int modulateAlpha(int i, int i2) {
        return (i * (i2 + (i2 >>> 7))) >>> 8;
    }

    public int getOpacity() {
        return -3;
    }

    public static void setDebugMode(boolean z) {
        sDebug = z;
    }

    public IconDrawable() {
        this(null);
    }

    public IconDrawable(b bVar) {
        this.mSizeCache = new HashMap<>();
        this.mIconState = new b(bVar);
        this.mTextPaint = new TextPaint();
        this.mCopiedBounds = new Rect();
        this.mTextMeas = new Rect();
    }

    public ConstantState getConstantState() {
        return this.mIconState;
    }

    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            if (this.mIconState.b != null) {
                this.mIconState.b = new TextPaint(this.mIconState.b);
            } else {
                this.mIconState.b = new TextPaint(1);
                this.mIconState.b.setTextAlign(Align.LEFT);
            }
            this.mMutated = true;
        }
        return this;
    }

    public void draw(Canvas canvas) {
        int alpha = this.mIconState.b.getAlpha();
        this.mIconState.b.setAlpha(modulateAlpha(alpha, this.mIconState.c));
        this.mIconState.b.setColorFilter(this.mIconState.d);
        copyBounds(this.mCopiedBounds);
        float calculateHorizontalOffset = calculateHorizontalOffset(this.mCopiedBounds, this.mIconState.b());
        float calculateVerticalOffset = calculateVerticalOffset(this.mCopiedBounds);
        canvas.drawText(this.mIconState.d(), calculateHorizontalOffset, calculateVerticalOffset, this.mIconState.b);
        if (sDebug) {
            FontMetrics c = this.mIconState.c();
            Paint paint = new Paint();
            paint.setColor(-16776961);
            paint.setAntiAlias(true);
            Paint paint2 = paint;
            canvas.drawLine((float) this.mCopiedBounds.left, calculateVerticalOffset, (float) this.mCopiedBounds.right, calculateVerticalOffset, paint2);
            float textSize = this.mIconState.b.getTextSize() - c.descent;
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            float f = calculateVerticalOffset - textSize;
            canvas.drawLine((float) this.mCopiedBounds.left, f, (float) this.mCopiedBounds.right, f, paint2);
            float calculateXHeight = calculateXHeight();
            paint.setColor(SupportMenu.CATEGORY_MASK);
            float f2 = calculateVerticalOffset - calculateXHeight;
            canvas.drawLine((float) this.mCopiedBounds.left, f2, (float) this.mCopiedBounds.right, f2, paint2);
            paint.setColor(-16711936);
            canvas.drawLine((float) this.mCopiedBounds.left, calculateVerticalOffset + c.ascent, (float) this.mCopiedBounds.right, calculateVerticalOffset + c.ascent, paint2);
            paint.setColor(-65281);
            canvas.drawLine((float) this.mCopiedBounds.left, calculateVerticalOffset + c.descent, (float) this.mCopiedBounds.right, calculateVerticalOffset + c.descent, paint2);
            paint.setColor(InputDeviceCompat.SOURCE_ANY);
            Rect rect = new Rect();
            this.mIconState.b.getTextBounds(this.mIconState.d(), 0, 1, rect);
            canvas.drawLine((float) this.mCopiedBounds.left, calculateVerticalOffset - ((float) rect.height()), (float) this.mCopiedBounds.right, calculateVerticalOffset - ((float) rect.height()), paint2);
        }
        this.mIconState.b.setAlpha(alpha);
    }

    private float calculateXHeight() {
        Rect rect = new Rect();
        this.mIconState.b.getTextBounds(EtsyDialogFragment.OPT_X_BUTTON, 0, 1, rect);
        return (float) rect.height();
    }

    private float calculateHorizontalOffset(Rect rect, float f) {
        int i = this.mGravity & 7;
        if (i == 1) {
            return ((((float) rect.width()) - f) / 2.0f) + ((float) rect.left);
        }
        if (i != 5) {
            return (float) rect.left;
        }
        return (((float) rect.width()) - f) + ((float) rect.left);
    }

    private float calculateVerticalOffset(Rect rect) {
        this.mTextPaint.getTextBounds(this.mIconState.d(), 0, 1, this.mTextMeas);
        int i = this.mGravity & 112;
        if (i != 16) {
            return i != 80 ? (float) (rect.top - this.mTextMeas.top) : (float) (rect.bottom - this.mTextMeas.bottom);
        }
        return (((float) rect.centerY()) + calculateXHeight()) - 2.0f;
    }

    private int calculateSize(Rect rect) {
        int width = rect.width();
        int height = rect.height();
        int i = 0;
        if (width == 0 && height == 0) {
            return 0;
        }
        SparseArray sparseArray = (SparseArray) this.mSizeCache.get(this.mIconState.a());
        if (sparseArray == null) {
            sparseArray = new SparseArray();
            this.mSizeCache.put(this.mIconState.a(), sparseArray);
        }
        SparseIntArray sparseIntArray = (SparseIntArray) sparseArray.get(width);
        if (sparseIntArray != null) {
            int i2 = sparseIntArray.get(height, 0);
            if (i2 > 0) {
                return i2;
            }
        }
        this.mTextPaint.set(this.mIconState.b);
        Rect rect2 = new Rect(0, 0, width, height);
        Rect rect3 = new Rect();
        int min = Math.min(rect.height(), rect.width());
        int i3 = 0;
        while (i <= min) {
            int i4 = (i + min) >>> 1;
            int compareSize = compareSize(rect2, rect3, i4);
            if (compareSize < 0) {
                int i5 = i4 + 1;
                i3 = i;
                i = i5;
            } else if (compareSize <= 0) {
                return i4;
            } else {
                i3 = i4 - 1;
                min = i3;
            }
        }
        if (sparseIntArray == null) {
            sparseIntArray = new SparseIntArray();
            sparseArray.put(width, sparseIntArray);
        }
        sparseIntArray.put(height, i3);
        return i3;
    }

    private int compareSize(Rect rect, Rect rect2, int i) {
        this.mTextPaint.setTextSize((float) i);
        int i2 = 1;
        this.mTextPaint.getTextBounds(this.mIconState.d(), 0, 1, rect2);
        if (rect2.height() == rect.height() && rect2.width() == rect.width()) {
            return 0;
        }
        if (rect2.height() < rect.height() && rect2.width() < rect.width()) {
            i2 = -1;
        }
        return i2;
    }

    public int getIntrinsicWidth() {
        if (this.mIconState.e) {
            return -1;
        }
        return Math.round(this.mIconState.b());
    }

    public int getIntrinsicHeight() {
        if (this.mIconState.e) {
            return -1;
        }
        FontMetrics c = this.mIconState.c();
        return Math.round(c.bottom - c.top);
    }

    public void setAlpha(int i) {
        this.mIconState.c = i;
        invalidateSelf();
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        if (this.mIconState.e) {
            Rect rect = new Rect(i, i2, i3, i4);
            if (rect.height() > 0 && rect.width() > 0) {
                this.mIconState.b.setTextSize((float) calculateSize(rect));
            }
        }
    }

    public void setBounds(Rect rect) {
        super.setBounds(rect);
        if (this.mIconState.e && rect.height() > 0 && rect.width() > 0) {
            this.mIconState.b.setTextSize((float) calculateSize(rect));
        }
    }

    public void setDefaultAlpha(int i) {
        this.mIconState.b.setAlpha(i);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mIconState.b.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setDefaultColorFilter(ColorFilter colorFilter) {
        this.mIconState.d = colorFilter;
        invalidateSelf();
    }

    public void setIconFont(com.etsy.android.iconsy.a aVar) {
        this.mIconState.a = aVar;
        this.mIconState.b.setTypeface(f.a().a(aVar));
        invalidateSelf();
    }

    public void setTextSize(float f) {
        if (f < 1.0f) {
            this.mIconState.e = true;
        }
        this.mIconState.b.setTextSize(f);
        invalidateSelf();
    }

    public void setColorId(Resources resources, @ColorRes int i) {
        this.mIconState.b.setColor(resources.getColor(i));
        invalidateSelf();
    }

    public void setColor(@ColorInt int i) {
        this.mIconState.b.setColor(i);
        invalidateSelf();
    }

    public void setGravity(int i) {
        this.mGravity = i;
    }
}
