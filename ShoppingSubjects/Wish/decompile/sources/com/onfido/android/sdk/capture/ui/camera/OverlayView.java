package com.onfido.android.sdk.capture.ui.camera;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

public final class OverlayView extends RelativeLayout {
    public static final Companion Companion = new Companion(null);
    static final /* synthetic */ KProperty[] a = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(OverlayView.class), "aspectRatio", "getAspectRatio()F")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(OverlayView.class), "bigHorizontalWeight", "getBigHorizontalWeight()F")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(OverlayView.class), "smallHorizontalWeight", "getSmallHorizontalWeight()F")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(OverlayView.class), "visibleHorizontalWeight", "getVisibleHorizontalWeight()F")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(OverlayView.class), "type", "getType()I"))};
    private final Paint b = new Paint();
    private final Paint c = new Paint();
    private CaptureType d;
    private Listener e;
    private final TextPaint f;
    private final TextPaint g;
    private final Paint h;
    private final float i;
    private PorterDuffXfermode j;
    private int k;
    private final ReadWriteProperty l;
    private final ReadWriteProperty m;
    private final ReadWriteProperty n;
    private final ReadWriteProperty o;
    private final ReadWriteProperty p;
    /* access modifiers changed from: private */
    public int q;
    private float r;
    private Bitmap s;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public interface Listener {
        void onLayoutDrawn(RectF rectF, RectF rectF2);
    }

    static final class a implements AnimatorUpdateListener {
        final /* synthetic */ OverlayView a;
        final /* synthetic */ int b;
        final /* synthetic */ int c;

        a(OverlayView overlayView, int i, int i2) {
            this.a = overlayView;
            this.b = i;
            this.c = i2;
        }

        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            OverlayView overlayView = this.a;
            Object animatedValue = valueAnimator.getAnimatedValue();
            if (animatedValue == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
            overlayView.q = ((Integer) animatedValue).intValue();
            this.a.invalidate();
        }
    }

    public OverlayView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context);
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.onfido_white));
        textPaint.setTextSize((float) getContext().getResources().getDimensionPixelSize(R.dimen.onfido_fs_xxlarge));
        this.f = textPaint;
        TextPaint textPaint2 = new TextPaint();
        textPaint2.setColor(ContextCompat.getColor(getContext(), R.color.onfido_white));
        textPaint2.setTextSize((float) getContext().getResources().getDimensionPixelSize(R.dimen.onfido_fs_normal));
        this.g = textPaint2;
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.onfido_white));
        paint.setStyle(Style.STROKE);
        this.h = paint;
        this.i = (float) getResources().getDimensionPixelOffset(R.dimen.onfido_capture_rectangle_radius);
        this.j = new PorterDuffXfermode(Mode.CLEAR);
        this.l = Delegates.INSTANCE.notNull();
        this.m = Delegates.INSTANCE.notNull();
        this.n = Delegates.INSTANCE.notNull();
        this.o = Delegates.INSTANCE.notNull();
        this.p = Delegates.INSTANCE.notNull();
        throw new RuntimeException("Load OverlayView only from a XML layout.");
    }

    /* JADX INFO: finally extract failed */
    public OverlayView(Context context, AttributeSet attributeSet) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attrs");
        super(context, attributeSet, R.styleable.OnfidoOverlayViewStyle_onfidoFaceOverlayViewStyle);
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.onfido_white));
        textPaint.setTextSize((float) getContext().getResources().getDimensionPixelSize(R.dimen.onfido_fs_xxlarge));
        this.f = textPaint;
        TextPaint textPaint2 = new TextPaint();
        textPaint2.setColor(ContextCompat.getColor(getContext(), R.color.onfido_white));
        textPaint2.setTextSize((float) getContext().getResources().getDimensionPixelSize(R.dimen.onfido_fs_normal));
        this.g = textPaint2;
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.onfido_white));
        paint.setStyle(Style.STROKE);
        this.h = paint;
        this.i = (float) getResources().getDimensionPixelOffset(R.dimen.onfido_capture_rectangle_radius);
        this.j = new PorterDuffXfermode(Mode.CLEAR);
        this.l = Delegates.INSTANCE.notNull();
        this.m = Delegates.INSTANCE.notNull();
        this.n = Delegates.INSTANCE.notNull();
        this.o = Delegates.INSTANCE.notNull();
        this.p = Delegates.INSTANCE.notNull();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OnfidoOverlayView);
        try {
            setType(obtainStyledAttributes.getInt(R.styleable.OnfidoOverlayView_onfidoOverlayType, 0));
            setAspectRatio(obtainStyledAttributes.getFloat(R.styleable.OnfidoOverlayView_onfidoOverlayAspectRatio, 1.0f));
            setBigHorizontalWeight(obtainStyledAttributes.getFloat(R.styleable.OnfidoOverlayView_onfidoOverlayBigHorizontalWeight, 1.0f));
            setSmallHorizontalWeight(obtainStyledAttributes.getFloat(R.styleable.OnfidoOverlayView_onfidoOverlaySmallHorizontalWeight, obtainStyledAttributes.getFloat(R.styleable.OnfidoOverlayView_onfidoOverlayBigHorizontalWeight, 1.0f)));
            setVisibleHorizontalWeight(getSmallHorizontalWeight());
            this.r = obtainStyledAttributes.getDimension(R.styleable.OnfidoOverlayView_onfidoOverlayStrokeWidth, 1.0f);
            obtainStyledAttributes.recycle();
            setBackgroundColor(0);
            setLayerType(1, null);
            Drawable drawable = ContextCompat.getDrawable(context, R.drawable.onfido_ic_watermark_white);
            if (drawable != null) {
                this.s = ViewExtensionsKt.toBitmap(drawable);
            }
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public OverlayView(Context context, AttributeSet attributeSet, int i2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attrs");
        super(context, attributeSet, i2);
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.onfido_white));
        textPaint.setTextSize((float) getContext().getResources().getDimensionPixelSize(R.dimen.onfido_fs_xxlarge));
        this.f = textPaint;
        TextPaint textPaint2 = new TextPaint();
        textPaint2.setColor(ContextCompat.getColor(getContext(), R.color.onfido_white));
        textPaint2.setTextSize((float) getContext().getResources().getDimensionPixelSize(R.dimen.onfido_fs_normal));
        this.g = textPaint2;
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.onfido_white));
        paint.setStyle(Style.STROKE);
        this.h = paint;
        this.i = (float) getResources().getDimensionPixelOffset(R.dimen.onfido_capture_rectangle_radius);
        this.j = new PorterDuffXfermode(Mode.CLEAR);
        this.l = Delegates.INSTANCE.notNull();
        this.m = Delegates.INSTANCE.notNull();
        this.n = Delegates.INSTANCE.notNull();
        this.o = Delegates.INSTANCE.notNull();
        this.p = Delegates.INSTANCE.notNull();
        throw new RuntimeException("Style not supported here.");
    }

    private final RectF a(Canvas canvas, float f2) {
        Resources resources;
        int i2;
        int overlayWidth = getOverlayWidth(f2) / 2;
        int overlayHeight = getOverlayHeight(f2) / 2;
        int width = canvas.getWidth() / 2;
        if (Intrinsics.areEqual(this.d, CaptureType.DOCUMENT)) {
            resources = getContext().getResources();
            i2 = R.dimen.onfido_document_capture_rectangle_top_margin;
        } else {
            resources = getContext().getResources();
            i2 = R.dimen.onfido_face_capture_rectangle_top_margin;
        }
        int dimensionPixelOffset = resources.getDimensionPixelOffset(i2) + overlayHeight;
        int overlayHeight2 = f2 == getSmallHorizontalWeight() ? (getOverlayHeight(getBigHorizontalWeight()) - getOverlayHeight(getSmallHorizontalWeight())) / 2 : 0;
        return new RectF((float) (width - overlayWidth), (float) ((dimensionPixelOffset - overlayHeight) + overlayHeight2), (float) (width + overlayWidth), (float) (dimensionPixelOffset + overlayHeight + overlayHeight2));
    }

    private final void a(int i2, int i3) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(new int[]{i2, i3});
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new a(this, i2, i3));
        valueAnimator.setDuration(300);
        valueAnimator.start();
    }

    private final void a(Canvas canvas, RectF rectF) {
        if (getType() == 1) {
            canvas.drawRoundRect(rectF, this.i, this.i, this.b);
            return;
        }
        canvas.drawOval(rectF, this.b);
        canvas.drawOval(rectF, this.h);
    }

    private final float getAspectRatio() {
        return ((Number) this.l.getValue(this, a[0])).floatValue();
    }

    private final int getType() {
        return ((Number) this.p.getValue(this, a[4])).intValue();
    }

    private final void setAspectRatio(float f2) {
        this.l.setValue(this, a[0], Float.valueOf(f2));
    }

    private final void setType(int i2) {
        this.p.setValue(this, a[4], Integer.valueOf(i2));
    }

    public final void disableDynamicLayout() {
        setSmallHorizontalWeight(getBigHorizontalWeight());
        setVisibleHorizontalWeight(getBigHorizontalWeight());
    }

    public final float getBigHorizontalWeight() {
        return ((Number) this.m.getValue(this, a[1])).floatValue();
    }

    public final int getOverlayHeight(float f2) {
        return (int) (((float) getOverlayWidth(f2)) * getAspectRatio());
    }

    public final int getOverlayWidth(float f2) {
        int width = getWidth();
        if (width != 0) {
            return (int) (((float) width) * f2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("View is not initialized, width:");
        sb.append(width);
        throw new RuntimeException(sb.toString());
    }

    public final float getSmallHorizontalWeight() {
        return ((Number) this.n.getValue(this, a[2])).floatValue();
    }

    public final float getVerticalWeight() {
        int height = getHeight();
        if (height != 0) {
            return ((float) getOverlayHeight(getBigHorizontalWeight())) / ((float) height);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("View is not initialized, height:");
        sb.append(height);
        throw new RuntimeException(sb.toString());
    }

    public final float getVisibleHorizontalWeight() {
        return ((Number) this.o.getValue(this, a[3])).floatValue();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        super.onDraw(canvas);
        RectF a2 = a(canvas, getBigHorizontalWeight());
        RectF a3 = a(canvas, getVisibleHorizontalWeight());
        Listener listener = this.e;
        if (listener != null) {
            listener.onLayoutDrawn(a3, a2);
        }
        canvas.drawColor(this.q);
        this.b.setStyle(Style.FILL);
        this.b.setXfermode(this.j);
        this.b.setColor(this.k);
        this.h.setStrokeWidth(this.r);
        a(canvas, a3);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.onfido_capture_rectangle_icon_margin);
        Bitmap bitmap = this.s;
        if (bitmap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("watermarkBitmap");
        }
        float centerX = a2.centerX();
        Bitmap bitmap2 = this.s;
        if (bitmap2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("watermarkBitmap");
        }
        float width = centerX - ((float) (bitmap2.getWidth() / 2));
        float f2 = a2.top - ((float) dimensionPixelOffset);
        Bitmap bitmap3 = this.s;
        if (bitmap3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("watermarkBitmap");
        }
        canvas.drawBitmap(bitmap, width, f2 - ((float) (bitmap3.getHeight() / 2)), this.c);
    }

    public final void paintCaptureArea() {
        this.b.reset();
        this.k = this.q;
        this.j = new PorterDuffXfermode(Mode.SRC);
        this.b.setStyle(Style.FILL);
        this.h.setColor(ContextCompat.getColor(getContext(), R.color.onfido_white_90));
        invalidate();
    }

    public final void runOnMeasured(Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "runnable");
        post(runnable);
    }

    public final void setBigHorizontalWeight(float f2) {
        this.m.setValue(this, a[1], Float.valueOf(f2));
    }

    public final void setCaptureType(CaptureType captureType) {
        Intrinsics.checkParameterIsNotNull(captureType, "captureType");
        this.d = captureType;
    }

    public final void setColorOutsideOverlay(int i2) {
        a(this.q, i2);
    }

    public final void setDarkTheme() {
        this.f.setColor(ContextCompat.getColor(getContext(), R.color.onfido_white));
        this.g.setColor(ContextCompat.getColor(getContext(), R.color.onfido_white));
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.onfido_ic_watermark_white);
        if (drawable != null) {
            this.s = ViewExtensionsKt.toBitmap(drawable);
        }
    }

    public final void setLightTheme() {
        this.f.setColor(ContextCompat.getColor(getContext(), R.color.onfido_medium_500));
        this.g.setColor(ContextCompat.getColor(getContext(), R.color.onfido_medium_500));
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.onfido_ic_watermark_grey);
        if (drawable != null) {
            this.s = ViewExtensionsKt.toBitmap(drawable);
        }
    }

    public final void setListener(Listener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.e = listener;
    }

    public final void setSmallHorizontalWeight(float f2) {
        this.n.setValue(this, a[2], Float.valueOf(f2));
    }

    public final void setVisibleHorizontalWeight(float f2) {
        this.o.setValue(this, a[3], Float.valueOf(f2));
    }

    public final void switchConfirmationMode(boolean z) {
        setVisibleHorizontalWeight(z ? getBigHorizontalWeight() : getSmallHorizontalWeight());
    }
}
