package com.lyft.android.scissors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.lyft.android.scissors.c.b;

public class CropView extends ImageView {
    private static final int MAX_TOUCH_POINTS = 2;
    private Paint antialiasStroke;
    private Bitmap bitmap;
    private Paint bitmapPaint = new Paint();
    private a extensions;
    private Path ovalViewportPath;
    private i touchManager;
    private Matrix transform = new Matrix();
    private boolean useOvalViewport = false;
    private RectF viewportCoordinates;
    @ColorInt
    private int viewportOverlayColor;

    public static class a {
        private final CropView a;

        a(CropView cropView) {
            this.a = cropView;
        }

        public void a(@Nullable Object obj) {
            new b(this.a).a(obj);
        }

        public com.lyft.android.scissors.c.a a() {
            return new com.lyft.android.scissors.c.a(this.a);
        }
    }

    public CropView(Context context) {
        super(context);
        initCropView(context, null);
    }

    public CropView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initCropView(context, attributeSet);
    }

    /* access modifiers changed from: 0000 */
    public void initCropView(Context context, AttributeSet attributeSet) {
        b a2 = b.a(context, attributeSet);
        this.touchManager = new i(2, a2);
        this.useOvalViewport = a2.f();
        this.bitmapPaint.setFilterBitmap(true);
        this.viewportOverlayColor = a2.a();
        this.viewportCoordinates = new RectF();
        this.ovalViewportPath = new Path();
        this.antialiasStroke = new Paint(1);
        this.antialiasStroke.setStyle(Style.STROKE);
        this.antialiasStroke.setColor(this.viewportOverlayColor);
        this.antialiasStroke.setStrokeWidth(1.0f);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.bitmap != null) {
            drawBitmap(canvas);
            drawOverlay(canvas);
        }
    }

    private void drawBitmap(Canvas canvas) {
        this.transform.reset();
        this.touchManager.a(this.transform);
        canvas.drawBitmap(this.bitmap, this.transform, this.bitmapPaint);
    }

    private void drawOverlay(Canvas canvas) {
        canvas.save();
        int a2 = this.touchManager.a();
        int b = this.touchManager.b();
        float width = ((float) (getWidth() - a2)) / 2.0f;
        float height = ((float) (getHeight() - b)) / 2.0f;
        float f = width + ((float) a2);
        float f2 = height + ((float) b);
        this.viewportCoordinates.set(width, height, f, f2);
        if (this.useOvalViewport) {
            this.ovalViewportPath.reset();
            this.ovalViewportPath.addOval(this.viewportCoordinates, Direction.CCW);
            canvas.clipPath(this.ovalViewportPath, Op.DIFFERENCE);
        } else {
            canvas.clipRect(width, height, f, f2, Op.DIFFERENCE);
        }
        canvas.drawColor(this.viewportOverlayColor);
        canvas.restore();
        if (this.useOvalViewport) {
            canvas.drawOval(this.viewportCoordinates, this.antialiasStroke);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        resetTouchManager();
    }

    public float getImageRatio() {
        Bitmap imageBitmap = getImageBitmap();
        if (imageBitmap != null) {
            return ((float) imageBitmap.getWidth()) / ((float) imageBitmap.getHeight());
        }
        return 0.0f;
    }

    public float getViewportRatio() {
        return this.touchManager.c();
    }

    public void setViewportRatio(float f) {
        if (Float.compare(f, 0.0f) == 0) {
            f = getImageRatio();
        }
        this.touchManager.a(f);
        resetTouchManager();
        invalidate();
    }

    public void setUseOvalViewport(boolean z) {
        this.useOvalViewport = z;
        invalidate();
    }

    public void setImageResource(@DrawableRes int i) {
        setImageBitmap(i > 0 ? BitmapFactory.decodeResource(getResources(), i) : null);
    }

    public void setImageDrawable(@Nullable Drawable drawable) {
        Bitmap bitmap2 = drawable instanceof BitmapDrawable ? ((BitmapDrawable) drawable).getBitmap() : drawable != null ? m.a(drawable, getWidth(), getHeight()) : null;
        setImageBitmap(bitmap2);
    }

    public void setImageURI(@Nullable Uri uri) {
        extensions().a(uri);
    }

    public void setImageBitmap(@Nullable Bitmap bitmap2) {
        this.bitmap = bitmap2;
        resetTouchManager();
        invalidate();
    }

    @Nullable
    public Bitmap getImageBitmap() {
        return this.bitmap;
    }

    private void resetTouchManager() {
        int i;
        int i2 = 0;
        boolean z = this.bitmap == null;
        if (z) {
            i = 0;
        } else {
            i = this.bitmap.getWidth();
        }
        if (!z) {
            i2 = this.bitmap.getHeight();
        }
        this.touchManager.a(i, i2, getWidth(), getHeight());
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        super.dispatchTouchEvent(motionEvent);
        this.touchManager.a(motionEvent);
        invalidate();
        return true;
    }

    @Nullable
    public Bitmap crop() {
        if (this.bitmap == null) {
            return null;
        }
        Rect d = this.touchManager.d();
        return Bitmap.createBitmap(this.bitmap, d.left, d.top, d.right - d.left, d.bottom - d.top);
    }

    public int getViewportWidth() {
        return this.touchManager.a();
    }

    public int getViewportHeight() {
        return this.touchManager.b();
    }

    public a extensions() {
        if (this.extensions == null) {
            this.extensions = new a(this);
        }
        return this.extensions;
    }
}
