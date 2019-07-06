package com.bumptech.glide.load.resource.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.view.Gravity;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;

public class GlideBitmapDrawable extends GlideDrawable {
    private boolean applyGravity;
    private final Rect destRect;
    private int height;
    private boolean mutated;
    private a state;
    private int width;

    static class a extends ConstantState {
        private static final Paint d = new Paint(6);
        final Bitmap a;
        int b;
        Paint c;

        public int getChangingConfigurations() {
            return 0;
        }

        public a(Bitmap bitmap) {
            this.c = d;
            this.a = bitmap;
        }

        a(a aVar) {
            this(aVar.a);
            this.b = aVar.b;
        }

        /* access modifiers changed from: 0000 */
        public void a(ColorFilter colorFilter) {
            a();
            this.c.setColorFilter(colorFilter);
        }

        /* access modifiers changed from: 0000 */
        public void a(int i) {
            a();
            this.c.setAlpha(i);
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            if (d == this.c) {
                this.c = new Paint(6);
            }
        }

        public Drawable newDrawable() {
            return new GlideBitmapDrawable((Resources) null, this);
        }

        public Drawable newDrawable(Resources resources) {
            return new GlideBitmapDrawable(resources, this);
        }
    }

    public boolean isAnimated() {
        return false;
    }

    public boolean isRunning() {
        return false;
    }

    public void setLoopCount(int i) {
    }

    public void start() {
    }

    public void stop() {
    }

    public GlideBitmapDrawable(Resources resources, Bitmap bitmap) {
        this(resources, new a(bitmap));
    }

    GlideBitmapDrawable(Resources resources, a aVar) {
        int i;
        this.destRect = new Rect();
        if (aVar == null) {
            throw new NullPointerException("BitmapState must not be null");
        }
        this.state = aVar;
        if (resources != null) {
            i = resources.getDisplayMetrics().densityDpi;
            if (i == 0) {
                i = 160;
            }
            aVar.b = i;
        } else {
            i = aVar.b;
        }
        this.width = aVar.a.getScaledWidth(i);
        this.height = aVar.a.getScaledHeight(i);
    }

    public int getIntrinsicWidth() {
        return this.width;
    }

    public int getIntrinsicHeight() {
        return this.height;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.applyGravity = true;
    }

    public ConstantState getConstantState() {
        return this.state;
    }

    public void draw(Canvas canvas) {
        if (this.applyGravity) {
            Gravity.apply(119, this.width, this.height, getBounds(), this.destRect);
            this.applyGravity = false;
        }
        canvas.drawBitmap(this.state.a, null, this.destRect, this.state.c);
    }

    public void setAlpha(int i) {
        if (this.state.c.getAlpha() != i) {
            this.state.a(i);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.state.a(colorFilter);
        invalidateSelf();
    }

    public int getOpacity() {
        Bitmap bitmap = this.state.a;
        return (bitmap == null || bitmap.hasAlpha() || this.state.c.getAlpha() < 255) ? -3 : -1;
    }

    public Drawable mutate() {
        if (!this.mutated && super.mutate() == this) {
            this.state = new a(this.state);
            this.mutated = true;
        }
        return this;
    }

    public Bitmap getBitmap() {
        return this.state.a;
    }
}
