package com.bumptech.glide.load.resource.gif;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.view.Gravity;
import com.bumptech.glide.b.a.C0008a;
import com.bumptech.glide.b.c;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.e.b;

public class GifDrawable extends GlideDrawable implements b {
    private boolean applyGravity;
    private final com.bumptech.glide.b.a decoder;
    private final Rect destRect;
    private final e frameLoader;
    private boolean isRecycled;
    private boolean isRunning;
    private boolean isStarted;
    private boolean isVisible;
    private int loopCount;
    private int maxLoopCount;
    private final Paint paint;
    private final a state;

    static class a extends ConstantState {
        c a;
        byte[] b;
        Context c;
        f<Bitmap> d;
        int e;
        int f;
        C0008a g;
        com.bumptech.glide.load.engine.bitmap_recycle.c h;
        Bitmap i;

        public int getChangingConfigurations() {
            return 0;
        }

        public a(c cVar, byte[] bArr, Context context, f<Bitmap> fVar, int i2, int i3, C0008a aVar, com.bumptech.glide.load.engine.bitmap_recycle.c cVar2, Bitmap bitmap) {
            if (bitmap == null) {
                throw new NullPointerException("The first frame of the GIF must not be null");
            }
            this.a = cVar;
            this.b = bArr;
            this.h = cVar2;
            this.i = bitmap;
            this.c = context.getApplicationContext();
            this.d = fVar;
            this.e = i2;
            this.f = i3;
            this.g = aVar;
        }

        public a(a aVar) {
            if (aVar != null) {
                this.a = aVar.a;
                this.b = aVar.b;
                this.c = aVar.c;
                this.d = aVar.d;
                this.e = aVar.e;
                this.f = aVar.f;
                this.g = aVar.g;
                this.h = aVar.h;
                this.i = aVar.i;
            }
        }

        public Drawable newDrawable(Resources resources) {
            return newDrawable();
        }

        public Drawable newDrawable() {
            return new GifDrawable(this);
        }
    }

    public int getOpacity() {
        return -2;
    }

    public boolean isAnimated() {
        return true;
    }

    public GifDrawable(Context context, C0008a aVar, com.bumptech.glide.load.engine.bitmap_recycle.c cVar, f<Bitmap> fVar, int i, int i2, c cVar2, byte[] bArr, Bitmap bitmap) {
        a aVar2 = new a(cVar2, bArr, context, fVar, i, i2, aVar, cVar, bitmap);
        this(aVar2);
    }

    public GifDrawable(GifDrawable gifDrawable, Bitmap bitmap, f<Bitmap> fVar) {
        a aVar = new a(gifDrawable.state.a, gifDrawable.state.b, gifDrawable.state.c, fVar, gifDrawable.state.e, gifDrawable.state.f, gifDrawable.state.g, gifDrawable.state.h, bitmap);
        this(aVar);
    }

    GifDrawable(a aVar) {
        this.destRect = new Rect();
        this.isVisible = true;
        this.maxLoopCount = -1;
        if (aVar == null) {
            throw new NullPointerException("GifState must not be null");
        }
        this.state = aVar;
        this.decoder = new com.bumptech.glide.b.a(aVar.g);
        this.paint = new Paint();
        this.decoder.a(aVar.a, aVar.b);
        e eVar = new e(aVar.c, this, this.decoder, aVar.e, aVar.f);
        this.frameLoader = eVar;
        this.frameLoader.a(aVar.d);
    }

    GifDrawable(com.bumptech.glide.b.a aVar, e eVar, Bitmap bitmap, com.bumptech.glide.load.engine.bitmap_recycle.c cVar, Paint paint2) {
        this.destRect = new Rect();
        this.isVisible = true;
        this.maxLoopCount = -1;
        this.decoder = aVar;
        this.frameLoader = eVar;
        this.state = new a(null);
        this.paint = paint2;
        this.state.h = cVar;
        this.state.i = bitmap;
    }

    public Bitmap getFirstFrame() {
        return this.state.i;
    }

    public void setFrameTransformation(f<Bitmap> fVar, Bitmap bitmap) {
        if (bitmap == null) {
            throw new NullPointerException("The first frame of the GIF must not be null");
        } else if (fVar == null) {
            throw new NullPointerException("The frame transformation must not be null");
        } else {
            this.state.d = fVar;
            this.state.i = bitmap;
            this.frameLoader.a(fVar);
        }
    }

    public com.bumptech.glide.b.a getDecoder() {
        return this.decoder;
    }

    public f<Bitmap> getFrameTransformation() {
        return this.state.d;
    }

    public byte[] getData() {
        return this.state.b;
    }

    public int getFrameCount() {
        return this.decoder.c();
    }

    private void resetLoopCount() {
        this.loopCount = 0;
    }

    public void start() {
        this.isStarted = true;
        resetLoopCount();
        if (this.isVisible) {
            startRunning();
        }
    }

    public void stop() {
        this.isStarted = false;
        stopRunning();
        if (VERSION.SDK_INT < 11) {
            reset();
        }
    }

    private void reset() {
        this.frameLoader.c();
        invalidateSelf();
    }

    private void startRunning() {
        if (this.decoder.c() == 1) {
            invalidateSelf();
        } else if (!this.isRunning) {
            this.isRunning = true;
            this.frameLoader.a();
            invalidateSelf();
        }
    }

    private void stopRunning() {
        this.isRunning = false;
        this.frameLoader.b();
    }

    public boolean setVisible(boolean z, boolean z2) {
        this.isVisible = z;
        if (!z) {
            stopRunning();
        } else if (this.isStarted) {
            startRunning();
        }
        return super.setVisible(z, z2);
    }

    public int getIntrinsicWidth() {
        return this.state.i.getWidth();
    }

    public int getIntrinsicHeight() {
        return this.state.i.getHeight();
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    /* access modifiers changed from: 0000 */
    public void setIsRunning(boolean z) {
        this.isRunning = z;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.applyGravity = true;
    }

    public void draw(Canvas canvas) {
        if (!this.isRecycled) {
            if (this.applyGravity) {
                Gravity.apply(119, getIntrinsicWidth(), getIntrinsicHeight(), getBounds(), this.destRect);
                this.applyGravity = false;
            }
            Bitmap d = this.frameLoader.d();
            if (d == null) {
                d = this.state.i;
            }
            canvas.drawBitmap(d, null, this.destRect, this.paint);
        }
    }

    public void setAlpha(int i) {
        this.paint.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }

    @TargetApi(11)
    public void onFrameReady(int i) {
        if (VERSION.SDK_INT < 11 || getCallback() != null) {
            invalidateSelf();
            if (i == this.decoder.c() - 1) {
                this.loopCount++;
            }
            if (this.maxLoopCount != -1 && this.loopCount >= this.maxLoopCount) {
                stop();
            }
            return;
        }
        stop();
        reset();
    }

    public ConstantState getConstantState() {
        return this.state;
    }

    public void recycle() {
        this.isRecycled = true;
        this.state.h.a(this.state.i);
        this.frameLoader.c();
        this.frameLoader.b();
    }

    /* access modifiers changed from: 0000 */
    public boolean isRecycled() {
        return this.isRecycled;
    }

    public void setLoopCount(int i) {
        if (i <= 0 && i != -1 && i != 0) {
            throw new IllegalArgumentException("Loop count must be greater than 0, or equal to GlideDrawable.LOOP_FOREVER, or equal to GlideDrawable.LOOP_INTRINSIC");
        } else if (i == 0) {
            this.maxLoopCount = this.decoder.e();
        } else {
            this.maxLoopCount = i;
        }
    }
}
