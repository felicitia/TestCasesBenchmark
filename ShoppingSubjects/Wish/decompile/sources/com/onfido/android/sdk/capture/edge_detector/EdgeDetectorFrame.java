package com.onfido.android.sdk.capture.edge_detector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.onfido.android.sdk.capture.R;
import kotlin.jvm.internal.Intrinsics;

public final class EdgeDetectorFrame extends RelativeLayout {
    private final float a = ((float) getContext().getResources().getDimensionPixelOffset(R.dimen.onfido_capture_frame_stroke_width));
    private final float b = ((float) getContext().getResources().getDimensionPixelOffset(R.dimen.onfido_capture_frame_stroke_height));
    private final Paint c;
    private final Paint d;
    private final Paint e;
    private final Paint f;
    private EdgeDetectionResults g;

    public EdgeDetectorFrame(Context context, AttributeSet attributeSet) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attrs");
        super(context, attributeSet, 0);
        Paint paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.onfido_light_300_o_30));
        paint.setStrokeWidth(this.a);
        this.c = paint;
        Paint paint2 = new Paint();
        paint2.setStyle(Style.STROKE);
        paint2.setColor(ContextCompat.getColor(getContext(), R.color.onfido_white));
        paint2.setStrokeWidth(this.a);
        this.d = paint2;
        Paint paint3 = new Paint();
        paint3.setStyle(Style.STROKE);
        paint3.setColor(ContextCompat.getColor(getContext(), R.color.onfido_success_200));
        paint3.setStrokeWidth(this.a);
        this.e = paint3;
        Paint paint4 = new Paint();
        paint4.setStyle(Style.FILL);
        paint4.setColor(ContextCompat.getColor(getContext(), R.color.onfido_success_200));
        this.f = paint4;
        this.g = new EdgeDetectionResults(false, false, false, false);
        a();
    }

    public EdgeDetectorFrame(Context context, AttributeSet attributeSet, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attrs");
        super(context, attributeSet, i);
        Paint paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.onfido_light_300_o_30));
        paint.setStrokeWidth(this.a);
        this.c = paint;
        Paint paint2 = new Paint();
        paint2.setStyle(Style.STROKE);
        paint2.setColor(ContextCompat.getColor(getContext(), R.color.onfido_white));
        paint2.setStrokeWidth(this.a);
        this.d = paint2;
        Paint paint3 = new Paint();
        paint3.setStyle(Style.STROKE);
        paint3.setColor(ContextCompat.getColor(getContext(), R.color.onfido_success_200));
        paint3.setStrokeWidth(this.a);
        this.e = paint3;
        Paint paint4 = new Paint();
        paint4.setStyle(Style.FILL);
        paint4.setColor(ContextCompat.getColor(getContext(), R.color.onfido_success_200));
        this.f = paint4;
        this.g = new EdgeDetectionResults(false, false, false, false);
        a();
    }

    private final void a() {
        setWillNotDraw(false);
    }

    public final Paint getCirclePaint() {
        return this.f;
    }

    public final Paint getDetectedEdgesPaint() {
        return this.e;
    }

    public final EdgeDetectionResults getEdgeDetectionState() {
        return this.g;
    }

    public final float getLineHeight() {
        return this.b;
    }

    public final float getLineWidth() {
        return this.a;
    }

    public final Paint getPlaceholderPaint() {
        return this.c;
    }

    public final Paint getWhitePaint() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        super.onDraw(canvas);
        float f2 = (float) 2;
        canvas.drawLine(0.0f, this.a / f2, (float) getWidth(), this.a / f2, this.c);
        canvas.drawLine(this.a / f2, 0.0f, this.a / f2, (float) getHeight(), this.c);
        canvas.drawLine(0.0f, ((float) getHeight()) - (this.a / f2), (float) getWidth(), ((float) getHeight()) - (this.a / f2), this.c);
        canvas.drawLine(((float) getWidth()) - (this.a / f2), 0.0f, ((float) getWidth()) - (this.a / f2), (float) getHeight(), this.c);
        canvas.drawLine(this.a / f2, ((float) getHeight()) - this.b, this.a / f2, (float) getHeight(), this.d);
        canvas.drawLine(((float) getWidth()) - (this.a / f2), ((float) getHeight()) - this.b, ((float) getWidth()) - (this.a / f2), (float) getHeight(), this.d);
        canvas.drawLine(this.a / f2, this.a / f2, this.a / f2, this.b, this.d);
        canvas.drawLine(((float) getWidth()) - (this.a / f2), 0.0f, ((float) getWidth()) - (this.a / f2), this.b, this.d);
        canvas.drawLine(0.0f, this.a / f2, this.b, this.a / f2, this.d);
        canvas.drawLine(0.0f, ((float) getHeight()) - (this.a / f2), this.b, ((float) getHeight()) - (this.a / f2), this.d);
        canvas.drawLine(((float) getWidth()) - this.b, ((float) getHeight()) - (this.a / f2), (float) getWidth(), ((float) getHeight()) - (this.a / f2), this.d);
        canvas.drawLine(((float) getWidth()) - this.b, this.a / f2, (float) getWidth(), this.a / f2, this.d);
        if (this.g.getLeftEdgeDetected()) {
            canvas.drawCircle(0.0f, 0.0f, this.b, this.f);
            canvas.drawCircle((float) getWidth(), 0.0f, this.b, this.f);
            canvas.drawLine(this.a / f2, this.a / f2, this.a / f2, this.b, this.e);
            canvas.drawLine(0.0f, this.a / f2, (float) getWidth(), this.a / f2, this.e);
            canvas.drawLine(((float) getWidth()) - (this.a / f2), 0.0f, ((float) getWidth()) - (this.a / f2), this.b, this.e);
        }
        if (this.g.getBottomEdgeDetected()) {
            canvas.drawCircle(0.0f, 0.0f, this.b, this.f);
            canvas.drawCircle(0.0f, (float) getHeight(), this.b, this.f);
            canvas.drawLine(0.0f, this.a / f2, this.b, this.a / f2, this.e);
            canvas.drawLine(this.a / f2, 0.0f, this.a / f2, (float) getHeight(), this.e);
            canvas.drawLine(0.0f, ((float) getHeight()) - (this.a / f2), this.b, ((float) getHeight()) - (this.a / f2), this.e);
        }
        if (this.g.getRightEdgeDetected()) {
            canvas.drawCircle(0.0f, (float) getHeight(), this.b, this.f);
            canvas.drawCircle((float) getWidth(), (float) getHeight(), this.b, this.f);
            canvas.drawLine(this.a / f2, ((float) getHeight()) - this.b, this.a / f2, (float) getHeight(), this.e);
            canvas.drawLine(0.0f, ((float) getHeight()) - (this.a / f2), (float) getWidth(), ((float) getHeight()) - (this.a / f2), this.e);
            canvas.drawLine(((float) getWidth()) - (this.a / f2), ((float) getHeight()) - this.b, ((float) getWidth()) - (this.a / f2), (float) getHeight(), this.e);
        }
        if (this.g.getTopEdgeDetected()) {
            canvas.drawCircle((float) getWidth(), 0.0f, this.b, this.f);
            canvas.drawCircle((float) getWidth(), (float) getHeight(), this.b, this.f);
            canvas.drawLine(((float) getWidth()) - this.b, ((float) getHeight()) - (this.a / f2), (float) getWidth(), ((float) getHeight()) - (this.a / f2), this.e);
            canvas.drawLine(((float) getWidth()) - (this.a / f2), 0.0f, ((float) getWidth()) - (this.a / f2), (float) getHeight(), this.e);
            canvas.drawLine(((float) getWidth()) - this.b, this.a / f2, (float) getWidth(), this.a / f2, this.e);
        }
    }

    public final void setEdgeDetectionState(EdgeDetectionResults edgeDetectionResults) {
        Intrinsics.checkParameterIsNotNull(edgeDetectionResults, "<set-?>");
        this.g = edgeDetectionResults;
    }

    public final void update(EdgeDetectionResults edgeDetectionResults) {
        Intrinsics.checkParameterIsNotNull(edgeDetectionResults, "results");
        this.g = edgeDetectionResults;
        invalidate();
    }
}
