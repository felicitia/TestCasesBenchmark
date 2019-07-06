package com.etsy.android.uikit.view;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView.ScaleType;
import com.etsy.android.lib.logger.f;

public class TouchImageView extends AppCompatImageView {
    static final int CLICK = 3;
    static final int DRAG = 1;
    static final int NONE = 0;
    static final int ZOOM = 2;
    Context context;
    PointF last = new PointF();
    float[] m;
    GestureDetector mDoubleTapDetector;
    ScaleGestureDetector mScaleDetector;
    Matrix matrix;
    float maxScale = 3.0f;
    float minScale = 1.0f;
    int mode = 0;
    int oldMeasuredHeight;
    int oldMeasuredWidth;
    protected float origHeight;
    protected float origWidth;
    float saveScale = 1.0f;
    PointF start = new PointF();
    int viewHeight;
    int viewWidth;

    private class a extends SimpleOnGestureListener {
        private a() {
        }

        public boolean onDoubleTap(final MotionEvent motionEvent) {
            float f = TouchImageView.this.saveScale;
            TouchImageView.this.saveScale *= 1.5f;
            if (f > 1.0f) {
                ScaleAnimation scaleAnimation = new ScaleAnimation(f, 1.0f, f, 1.0f, motionEvent.getX(), motionEvent.getY());
                scaleAnimation.setDuration(250);
                TouchImageView.this.startAnimation(scaleAnimation);
                TouchImageView.this.resetZoom();
            } else {
                float fixTrans = TouchImageView.this.getFixTrans(motionEvent.getX(), (float) TouchImageView.this.viewWidth, TouchImageView.this.origWidth * TouchImageView.this.saveScale);
                float fixTrans2 = TouchImageView.this.getFixTrans(motionEvent.getY(), (float) TouchImageView.this.viewHeight, TouchImageView.this.origHeight * TouchImageView.this.saveScale);
                if (fixTrans == 0.0f && fixTrans2 == 0.0f) {
                    new ScaleAnimation(f, 1.5f, f, 1.5f, motionEvent.getX(), motionEvent.getY());
                } else {
                    new ScaleAnimation(f, 1.5f, f, 1.5f, fixTrans, fixTrans2);
                }
                ScaleAnimation scaleAnimation2 = new ScaleAnimation(f, 1.5f, f, 1.5f, motionEvent.getX(), motionEvent.getY());
                scaleAnimation2.setDuration(250);
                scaleAnimation2.setAnimationListener(new AnimationListener() {
                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        TouchImageView.this.clearAnimation();
                        TouchImageView.this.matrix.postScale(1.5f, 1.5f, motionEvent.getX(), motionEvent.getY());
                        TouchImageView.this.fixTrans();
                        TouchImageView.this.setImageMatrix(TouchImageView.this.matrix);
                    }
                });
                TouchImageView.this.startAnimation(scaleAnimation2);
            }
            return true;
        }
    }

    private class b extends SimpleOnScaleGestureListener {
        private b() {
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            TouchImageView.this.mode = 2;
            return true;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            float f = TouchImageView.this.saveScale;
            TouchImageView.this.saveScale *= scaleFactor;
            if (TouchImageView.this.saveScale > TouchImageView.this.maxScale) {
                TouchImageView.this.saveScale = TouchImageView.this.maxScale;
                scaleFactor = TouchImageView.this.maxScale / f;
            } else if (TouchImageView.this.saveScale < TouchImageView.this.minScale) {
                TouchImageView.this.saveScale = TouchImageView.this.minScale;
                scaleFactor = TouchImageView.this.minScale / f;
            }
            if (TouchImageView.this.origWidth * TouchImageView.this.saveScale <= ((float) TouchImageView.this.viewWidth) || TouchImageView.this.origHeight * TouchImageView.this.saveScale <= ((float) TouchImageView.this.viewHeight)) {
                TouchImageView.this.matrix.postScale(scaleFactor, scaleFactor, (float) (TouchImageView.this.viewWidth / 2), (float) (TouchImageView.this.viewHeight / 2));
            } else {
                TouchImageView.this.matrix.postScale(scaleFactor, scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            }
            TouchImageView.this.fixTrans();
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public float getFixDragTrans(float f, float f2, float f3) {
        if (f3 <= f2) {
            return 0.0f;
        }
        return f;
    }

    /* access modifiers changed from: 0000 */
    public float getFixTrans(float f, float f2, float f3) {
        float f4;
        float f5;
        if (f3 <= f2) {
            f4 = f2 - f3;
            f5 = 0.0f;
        } else {
            f5 = f2 - f3;
            f4 = 0.0f;
        }
        if (f < f5) {
            return (-f) + f5;
        }
        if (f > f4) {
            return (-f) + f4;
        }
        return 0.0f;
    }

    public TouchImageView(Context context2) {
        super(context2);
        sharedConstructing(context2);
    }

    public TouchImageView(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        sharedConstructing(context2);
    }

    private void sharedConstructing(Context context2) {
        super.setClickable(true);
        this.context = context2;
        this.mScaleDetector = new ScaleGestureDetector(context2, new b());
        this.mDoubleTapDetector = new GestureDetector(context2, new a());
        this.matrix = new Matrix();
        this.m = new float[9];
        setImageMatrix(this.matrix);
        setScaleType(ScaleType.MATRIX);
        setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                TouchImageView.this.mScaleDetector.onTouchEvent(motionEvent);
                TouchImageView.this.mDoubleTapDetector.onTouchEvent(motionEvent);
                PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
                int action = motionEvent.getAction();
                if (action != 6) {
                    switch (action) {
                        case 0:
                            TouchImageView.this.last.set(pointF);
                            TouchImageView.this.start.set(TouchImageView.this.last);
                            TouchImageView.this.mode = 1;
                            break;
                        case 1:
                            TouchImageView.this.mode = 0;
                            int abs = (int) Math.abs(pointF.x - TouchImageView.this.start.x);
                            int abs2 = (int) Math.abs(pointF.y - TouchImageView.this.start.y);
                            if (abs < 3 && abs2 < 3) {
                                TouchImageView.this.performClick();
                                break;
                            }
                        case 2:
                            if (TouchImageView.this.mode == 1) {
                                float f = pointF.y - TouchImageView.this.last.y;
                                TouchImageView.this.matrix.postTranslate(TouchImageView.this.getFixDragTrans(pointF.x - TouchImageView.this.last.x, (float) TouchImageView.this.viewWidth, TouchImageView.this.origWidth * TouchImageView.this.saveScale), TouchImageView.this.getFixDragTrans(f, (float) TouchImageView.this.viewHeight, TouchImageView.this.origHeight * TouchImageView.this.saveScale));
                                TouchImageView.this.fixTrans();
                                TouchImageView.this.last.set(pointF.x, pointF.y);
                                break;
                            }
                            break;
                    }
                } else {
                    TouchImageView.this.mode = 0;
                }
                TouchImageView.this.setImageMatrix(TouchImageView.this.matrix);
                TouchImageView.this.invalidate();
                return true;
            }
        });
    }

    public void setMaxZoom(float f) {
        this.maxScale = f;
    }

    /* access modifiers changed from: 0000 */
    public void fixTrans() {
        this.matrix.getValues(this.m);
        float f = this.m[2];
        float f2 = this.m[5];
        float fixTrans = getFixTrans(f, (float) this.viewWidth, this.origWidth * this.saveScale);
        float fixTrans2 = getFixTrans(f2, (float) this.viewHeight, this.origHeight * this.saveScale);
        if (fixTrans != 0.0f || fixTrans2 != 0.0f) {
            this.matrix.postTranslate(fixTrans, fixTrans2);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.viewWidth = MeasureSpec.getSize(i);
        this.viewHeight = MeasureSpec.getSize(i2);
        if ((this.oldMeasuredHeight != this.viewWidth || this.oldMeasuredHeight != this.viewHeight) && this.viewWidth != 0 && this.viewHeight != 0) {
            this.oldMeasuredHeight = this.viewHeight;
            this.oldMeasuredWidth = this.viewWidth;
            if (this.saveScale == 1.0f) {
                Drawable drawable = getDrawable();
                if (drawable != null && drawable.getIntrinsicWidth() != 0 && drawable.getIntrinsicHeight() != 0) {
                    int intrinsicWidth = drawable.getIntrinsicWidth();
                    int intrinsicHeight = drawable.getIntrinsicHeight();
                    StringBuilder sb = new StringBuilder();
                    sb.append("bmWidth: ");
                    sb.append(intrinsicWidth);
                    sb.append(" bmHeight : ");
                    sb.append(intrinsicHeight);
                    f.c("bmSize", sb.toString());
                    float f = (float) intrinsicWidth;
                    float f2 = (float) intrinsicHeight;
                    float min = Math.min(((float) this.viewWidth) / f, ((float) this.viewHeight) / f2);
                    this.matrix.setScale(min, min);
                    float f3 = (((float) this.viewHeight) - (f2 * min)) / 2.0f;
                    float f4 = (((float) this.viewWidth) - (min * f)) / 2.0f;
                    this.matrix.postTranslate(f4, f3);
                    this.origWidth = ((float) this.viewWidth) - (f4 * 2.0f);
                    this.origHeight = ((float) this.viewHeight) - (2.0f * f3);
                    setImageMatrix(this.matrix);
                } else {
                    return;
                }
            }
            fixTrans();
        }
    }

    public void resetZoom() {
        if ((this.oldMeasuredHeight != this.viewWidth || this.oldMeasuredHeight != this.viewHeight) && this.viewWidth != 0 && this.viewHeight != 0) {
            this.oldMeasuredHeight = this.viewHeight;
            this.oldMeasuredWidth = this.viewWidth;
            this.saveScale = 1.0f;
            Drawable drawable = getDrawable();
            if (drawable != null && drawable.getIntrinsicWidth() != 0 && drawable.getIntrinsicHeight() != 0) {
                int intrinsicWidth = drawable.getIntrinsicWidth();
                float f = (float) intrinsicWidth;
                float intrinsicHeight = (float) drawable.getIntrinsicHeight();
                float min = Math.min(((float) this.viewWidth) / f, ((float) this.viewHeight) / intrinsicHeight);
                this.matrix.setScale(min, min);
                float f2 = (((float) this.viewHeight) - (intrinsicHeight * min)) / 2.0f;
                float f3 = (((float) this.viewWidth) - (min * f)) / 2.0f;
                this.matrix.postTranslate(f3, f2);
                this.origWidth = ((float) this.viewWidth) - (f3 * 2.0f);
                this.origHeight = ((float) this.viewHeight) - (2.0f * f2);
                setImageMatrix(this.matrix);
                fixTrans();
            }
        }
    }
}
