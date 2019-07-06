package com.contextlogic.wish.ui.image;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView.ScaleType;
import android.widget.OverScroller;
import android.widget.Scroller;

public class ZoomingImageView extends SafeImageView {
    /* access modifiers changed from: private */
    public Context mContext;
    private ZoomVariables mDelayedZoomVariables;
    /* access modifiers changed from: private */
    public OnDoubleTapListener mDoubleTapListener;
    /* access modifiers changed from: private */
    public Fling mFling;
    /* access modifiers changed from: private */
    public GestureDetector mGestureDetector;
    private boolean mImageRenderedAtLeastOnce;
    private float mMatchViewHeight;
    private float mMatchViewWidth;
    /* access modifiers changed from: private */
    public Matrix mMatrix;
    /* access modifiers changed from: private */
    public float[] mMatrixValues;
    /* access modifiers changed from: private */
    public float mMaxScale;
    /* access modifiers changed from: private */
    public float mMinScale;
    /* access modifiers changed from: private */
    public float mNormalizedScale;
    private OnClickListener mOnClickListener;
    private boolean mOnDrawReady;
    private float mPrevMatchViewHeight;
    private float mPrevMatchViewWidth;
    private Matrix mPrevMatrix;
    private int mPrevViewHeight;
    private int mPrevViewWidth;
    /* access modifiers changed from: private */
    public ScaleGestureDetector mScaleDetector;
    private ScaleType mScaleType;
    /* access modifiers changed from: private */
    public State mState;
    private float mSuperMaxScale;
    private float mSuperMinScale;
    /* access modifiers changed from: private */
    public OnTouchImageViewListener mTouchImageViewListener;
    /* access modifiers changed from: private */
    public OnTouchListener mUserTouchListener;
    /* access modifiers changed from: private */
    public int mViewHeight;
    /* access modifiers changed from: private */
    public int mViewWidth;
    private boolean mZoomingEnabled;

    /* renamed from: com.contextlogic.wish.ui.image.ZoomingImageView$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ScaleType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                android.widget.ImageView$ScaleType[] r0 = android.widget.ImageView.ScaleType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$android$widget$ImageView$ScaleType = r0
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x001f }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_CROP     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x002a }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_INSIDE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0035 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_CENTER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0040 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_XY     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.ui.image.ZoomingImageView.AnonymousClass1.<clinit>():void");
        }
    }

    @TargetApi(9)
    private class CompatScroller {
        boolean isPreGingerbread;
        OverScroller overScroller;
        Scroller scroller;

        public CompatScroller(Context context) {
            if (VERSION.SDK_INT < 9) {
                this.isPreGingerbread = true;
                this.scroller = new Scroller(context);
                return;
            }
            this.isPreGingerbread = false;
            this.overScroller = new OverScroller(context);
        }

        public void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (this.isPreGingerbread) {
                this.scroller.fling(i, i2, i3, i4, i5, i6, i7, i8);
            } else {
                this.overScroller.fling(i, i2, i3, i4, i5, i6, i7, i8);
            }
        }

        public void forceFinished(boolean z) {
            if (this.isPreGingerbread) {
                this.scroller.forceFinished(z);
            } else {
                this.overScroller.forceFinished(z);
            }
        }

        public boolean isFinished() {
            if (this.isPreGingerbread) {
                return this.scroller.isFinished();
            }
            return this.overScroller.isFinished();
        }

        public boolean computeScrollOffset() {
            if (this.isPreGingerbread) {
                return this.scroller.computeScrollOffset();
            }
            this.overScroller.computeScrollOffset();
            return this.overScroller.computeScrollOffset();
        }

        public int getCurrX() {
            if (this.isPreGingerbread) {
                return this.scroller.getCurrX();
            }
            return this.overScroller.getCurrX();
        }

        public int getCurrY() {
            if (this.isPreGingerbread) {
                return this.scroller.getCurrY();
            }
            return this.overScroller.getCurrY();
        }
    }

    private class DoubleTapZoom implements Runnable {
        private float mBitmapX;
        private float mBitmapY;
        private PointF mEndTouch;
        private AccelerateDecelerateInterpolator mInterpolator = new AccelerateDecelerateInterpolator();
        private long mStartTime;
        private PointF mStartTouch;
        private float mStartZoom;
        private boolean mStretchImageToSuper;
        private float mTargetZoom;

        DoubleTapZoom(float f, float f2, float f3, boolean z) {
            ZoomingImageView.this.setState(State.ANIMATE_ZOOM);
            this.mStartTime = System.currentTimeMillis();
            this.mStartZoom = ZoomingImageView.this.mNormalizedScale;
            this.mTargetZoom = f;
            this.mStretchImageToSuper = z;
            PointF access$2300 = ZoomingImageView.this.transformCoordTouchToBitmap(f2, f3, false);
            this.mBitmapX = access$2300.x;
            this.mBitmapY = access$2300.y;
            this.mStartTouch = ZoomingImageView.this.transformCoordBitmapToTouch(this.mBitmapX, this.mBitmapY);
            this.mEndTouch = new PointF((float) (ZoomingImageView.this.mViewWidth / 2), (float) (ZoomingImageView.this.mViewHeight / 2));
        }

        public void run() {
            float interpolate = interpolate();
            ZoomingImageView.this.scaleImage(calculateDeltaScale(interpolate), this.mBitmapX, this.mBitmapY, this.mStretchImageToSuper);
            translateImageToCenterTouchPosition(interpolate);
            ZoomingImageView.this.fixScaleTrans();
            ZoomingImageView.this.setImageMatrix(ZoomingImageView.this.mMatrix);
            if (ZoomingImageView.this.mTouchImageViewListener != null) {
                ZoomingImageView.this.mTouchImageViewListener.onMove();
            }
            if (interpolate < 1.0f) {
                ZoomingImageView.this.compatPostOnAnimation(this);
            } else {
                ZoomingImageView.this.setState(State.NONE);
            }
        }

        private void translateImageToCenterTouchPosition(float f) {
            float f2 = this.mStartTouch.x + ((this.mEndTouch.x - this.mStartTouch.x) * f);
            float f3 = this.mStartTouch.y + (f * (this.mEndTouch.y - this.mStartTouch.y));
            PointF access$2400 = ZoomingImageView.this.transformCoordBitmapToTouch(this.mBitmapX, this.mBitmapY);
            ZoomingImageView.this.mMatrix.postTranslate(f2 - access$2400.x, f3 - access$2400.y);
        }

        private float interpolate() {
            return this.mInterpolator.getInterpolation(Math.min(1.0f, ((float) (System.currentTimeMillis() - this.mStartTime)) / 500.0f));
        }

        private double calculateDeltaScale(float f) {
            return ((double) (this.mStartZoom + (f * (this.mTargetZoom - this.mStartZoom)))) / ((double) ZoomingImageView.this.mNormalizedScale);
        }
    }

    private class Fling implements Runnable {
        int mCurrX;
        int mCurrY;
        CompatScroller mScroller;

        Fling(int i, int i2) {
            int i3;
            int i4;
            int i5;
            int i6;
            ZoomingImageView.this.setState(State.FLING);
            this.mScroller = new CompatScroller(ZoomingImageView.this.mContext);
            ZoomingImageView.this.mMatrix.getValues(ZoomingImageView.this.mMatrixValues);
            int i7 = (int) ZoomingImageView.this.mMatrixValues[2];
            int i8 = (int) ZoomingImageView.this.mMatrixValues[5];
            if (ZoomingImageView.this.getImageWidth() > ((float) ZoomingImageView.this.mViewWidth)) {
                i4 = ZoomingImageView.this.mViewWidth - ((int) ZoomingImageView.this.getImageWidth());
                i3 = 0;
            } else {
                i4 = i7;
                i3 = i4;
            }
            if (ZoomingImageView.this.getImageHeight() > ((float) ZoomingImageView.this.mViewHeight)) {
                i6 = ZoomingImageView.this.mViewHeight - ((int) ZoomingImageView.this.getImageHeight());
                i5 = 0;
            } else {
                i6 = i8;
                i5 = i6;
            }
            this.mScroller.fling(i7, i8, i, i2, i4, i3, i6, i5);
            this.mCurrX = i7;
            this.mCurrY = i8;
        }

        public void cancelFling() {
            if (this.mScroller != null) {
                ZoomingImageView.this.setState(State.NONE);
                this.mScroller.forceFinished(true);
            }
        }

        public void run() {
            if (ZoomingImageView.this.mTouchImageViewListener != null) {
                ZoomingImageView.this.mTouchImageViewListener.onMove();
            }
            if (this.mScroller.isFinished()) {
                this.mScroller = null;
                return;
            }
            if (this.mScroller.computeScrollOffset()) {
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                int i = currX - this.mCurrX;
                int i2 = currY - this.mCurrY;
                this.mCurrX = currX;
                this.mCurrY = currY;
                ZoomingImageView.this.mMatrix.postTranslate((float) i, (float) i2);
                ZoomingImageView.this.fixTrans();
                ZoomingImageView.this.setImageMatrix(ZoomingImageView.this.mMatrix);
                ZoomingImageView.this.compatPostOnAnimation(this);
            }
        }
    }

    private class GestureListener extends SimpleOnGestureListener {
        private GestureListener() {
        }

        /* synthetic */ GestureListener(ZoomingImageView zoomingImageView, AnonymousClass1 r2) {
            this();
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (ZoomingImageView.this.mDoubleTapListener != null) {
                return ZoomingImageView.this.mDoubleTapListener.onSingleTapConfirmed(motionEvent);
            }
            return ZoomingImageView.this.performClick();
        }

        public void onLongPress(MotionEvent motionEvent) {
            try {
                ZoomingImageView.this.performLongClick();
            } catch (Exception unused) {
            }
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (ZoomingImageView.this.mFling != null) {
                ZoomingImageView.this.mFling.cancelFling();
            }
            ZoomingImageView.this.mFling = new Fling((int) f, (int) f2);
            ZoomingImageView.this.compatPostOnAnimation(ZoomingImageView.this.mFling);
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            boolean onDoubleTap = ZoomingImageView.this.mDoubleTapListener != null ? ZoomingImageView.this.mDoubleTapListener.onDoubleTap(motionEvent) : false;
            if (ZoomingImageView.this.mState != State.NONE) {
                return onDoubleTap;
            }
            DoubleTapZoom doubleTapZoom = new DoubleTapZoom(ZoomingImageView.this.mNormalizedScale == ZoomingImageView.this.mMinScale ? ZoomingImageView.this.mMaxScale : ZoomingImageView.this.mMinScale, motionEvent.getX(), motionEvent.getY(), false);
            ZoomingImageView.this.compatPostOnAnimation(doubleTapZoom);
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            if (ZoomingImageView.this.mDoubleTapListener != null) {
                return ZoomingImageView.this.mDoubleTapListener.onDoubleTapEvent(motionEvent);
            }
            return false;
        }
    }

    public interface OnTouchImageViewListener {
        void onMove();
    }

    private class PrivateOnTouchListener implements OnTouchListener {
        private PointF last;

        private PrivateOnTouchListener() {
            this.last = new PointF();
        }

        /* synthetic */ PrivateOnTouchListener(ZoomingImageView zoomingImageView, AnonymousClass1 r2) {
            this();
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            ZoomingImageView.this.mScaleDetector.onTouchEvent(motionEvent);
            ZoomingImageView.this.mGestureDetector.onTouchEvent(motionEvent);
            PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
            if (ZoomingImageView.this.mState == State.NONE || ZoomingImageView.this.mState == State.DRAG || ZoomingImageView.this.mState == State.FLING) {
                int action = motionEvent.getAction();
                if (action != 6) {
                    switch (action) {
                        case 0:
                            this.last.set(pointF);
                            if (ZoomingImageView.this.mFling != null) {
                                ZoomingImageView.this.mFling.cancelFling();
                            }
                            ZoomingImageView.this.setState(State.DRAG);
                            break;
                        case 1:
                            break;
                        case 2:
                            if (ZoomingImageView.this.mState == State.DRAG) {
                                float f = pointF.y - this.last.y;
                                ZoomingImageView.this.mMatrix.postTranslate(ZoomingImageView.this.getFixDragTrans(pointF.x - this.last.x, (float) ZoomingImageView.this.mViewWidth, ZoomingImageView.this.getImageWidth()), ZoomingImageView.this.getFixDragTrans(f, (float) ZoomingImageView.this.mViewHeight, ZoomingImageView.this.getImageHeight()));
                                ZoomingImageView.this.fixTrans();
                                this.last.set(pointF.x, pointF.y);
                                break;
                            }
                            break;
                    }
                }
                ZoomingImageView.this.setState(State.NONE);
            }
            ZoomingImageView.this.setImageMatrix(ZoomingImageView.this.mMatrix);
            if (ZoomingImageView.this.mUserTouchListener != null) {
                ZoomingImageView.this.mUserTouchListener.onTouch(view, motionEvent);
            }
            if (ZoomingImageView.this.mTouchImageViewListener != null) {
                ZoomingImageView.this.mTouchImageViewListener.onMove();
            }
            return true;
        }
    }

    private class ScaleListener extends SimpleOnScaleGestureListener {
        private ScaleListener() {
        }

        /* synthetic */ ScaleListener(ZoomingImageView zoomingImageView, AnonymousClass1 r2) {
            this();
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            ZoomingImageView.this.setState(State.ZOOM);
            return true;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            ZoomingImageView.this.scaleImage((double) scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), true);
            if (ZoomingImageView.this.mTouchImageViewListener != null) {
                ZoomingImageView.this.mTouchImageViewListener.onMove();
            }
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            super.onScaleEnd(scaleGestureDetector);
            ZoomingImageView.this.setState(State.NONE);
            float access$700 = ZoomingImageView.this.mNormalizedScale;
            boolean z = true;
            if (ZoomingImageView.this.mNormalizedScale > ZoomingImageView.this.mMaxScale) {
                access$700 = ZoomingImageView.this.mMaxScale;
            } else if (ZoomingImageView.this.mNormalizedScale < ZoomingImageView.this.mMinScale) {
                access$700 = ZoomingImageView.this.mMinScale;
            } else {
                z = false;
            }
            float f = access$700;
            if (z) {
                DoubleTapZoom doubleTapZoom = new DoubleTapZoom(f, (float) (ZoomingImageView.this.mViewWidth / 2), (float) (ZoomingImageView.this.mViewHeight / 2), true);
                ZoomingImageView.this.compatPostOnAnimation(doubleTapZoom);
            }
        }
    }

    private enum State {
        NONE,
        DRAG,
        ZOOM,
        FLING,
        ANIMATE_ZOOM
    }

    private class ZoomVariables {
        public float focusX;
        public float focusY;
        public float scale;
        public ScaleType scaleType;

        public ZoomVariables(float f, float f2, float f3, ScaleType scaleType2) {
            this.scale = f;
            this.focusX = f2;
            this.focusY = f3;
            this.scaleType = scaleType2;
        }
    }

    /* access modifiers changed from: private */
    public float getFixDragTrans(float f, float f2, float f3) {
        if (f3 <= f2) {
            return 0.0f;
        }
        return f;
    }

    private float getFixTrans(float f, float f2, float f3) {
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

    public ZoomingImageView(Context context) {
        super(context);
        sharedConstructing(context);
    }

    public ZoomingImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        sharedConstructing(context);
    }

    public ZoomingImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        sharedConstructing(context);
    }

    private void sharedConstructing(Context context) {
        this.mContext = context;
        this.mScaleDetector = new ScaleGestureDetector(context, new ScaleListener(this, null));
        this.mGestureDetector = new GestureDetector(context, new GestureListener(this, null));
        this.mMatrix = new Matrix();
        this.mPrevMatrix = new Matrix();
        this.mMatrixValues = new float[9];
        this.mNormalizedScale = 1.0f;
        if (this.mScaleType == null) {
            this.mScaleType = ScaleType.FIT_CENTER;
        }
        this.mMinScale = 1.0f;
        this.mMaxScale = 3.0f;
        this.mSuperMinScale = this.mMinScale * 0.75f;
        this.mSuperMaxScale = this.mMaxScale * 1.25f;
        setImageMatrix(this.mMatrix);
        setScaleType(ScaleType.MATRIX);
        setState(State.NONE);
        this.mOnDrawReady = false;
    }

    public void disableTouchEvents() {
        super.setOnTouchListener(null);
        if (this.mOnClickListener == null) {
            super.setClickable(false);
        }
    }

    public void enableTouchEvents() {
        super.setOnTouchListener(new PrivateOnTouchListener(this, null));
        super.setClickable(true);
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.mUserTouchListener = onTouchListener;
    }

    public void setOnTouchImageViewListener(OnTouchImageViewListener onTouchImageViewListener) {
        this.mTouchImageViewListener = onTouchImageViewListener;
    }

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
        this.mDoubleTapListener = onDoubleTapListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        super.setClickable(onClickListener != null);
        this.mOnClickListener = onClickListener;
        super.setOnClickListener(onClickListener);
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        savePreviousImageValues();
        fitImageToView();
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        savePreviousImageValues();
        fitImageToView();
    }

    public void setImageDrawable(Drawable drawable) {
        onImageDrawableLoaded(drawable);
    }

    public void onImageDrawableLoaded(Drawable drawable) {
        super.onImageDrawableLoaded(drawable);
        savePreviousImageValues();
        fitImageToView();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        savePreviousImageValues();
        fitImageToView();
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType == ScaleType.FIT_START || scaleType == ScaleType.FIT_END) {
            throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
        } else if (scaleType == ScaleType.MATRIX) {
            super.setScaleType(ScaleType.MATRIX);
        } else if (scaleType == ScaleType.FIT_CENTER) {
            super.setScaleType(ScaleType.FIT_CENTER);
        } else if (scaleType == ScaleType.CENTER_CROP) {
            super.setScaleType(ScaleType.CENTER_CROP);
        } else {
            this.mScaleType = scaleType;
            if (this.mOnDrawReady) {
                setZoom(this);
            }
        }
    }

    public ScaleType getScaleType() {
        return this.mScaleType;
    }

    public boolean isZoomed() {
        return this.mNormalizedScale != 1.0f;
    }

    public RectF getZoomedRect() {
        if (this.mScaleType == ScaleType.FIT_XY) {
            throw new UnsupportedOperationException("getZoomedRect() not supported with FIT_XY");
        }
        PointF transformCoordTouchToBitmap = transformCoordTouchToBitmap(0.0f, 0.0f, true);
        PointF transformCoordTouchToBitmap2 = transformCoordTouchToBitmap((float) this.mViewWidth, (float) this.mViewHeight, true);
        float intrinsicWidth = (float) getDrawable().getIntrinsicWidth();
        float intrinsicHeight = (float) getDrawable().getIntrinsicHeight();
        return new RectF(transformCoordTouchToBitmap.x / intrinsicWidth, transformCoordTouchToBitmap.y / intrinsicHeight, transformCoordTouchToBitmap2.x / intrinsicWidth, transformCoordTouchToBitmap2.y / intrinsicHeight);
    }

    private void savePreviousImageValues() {
        if (this.mMatrix != null && this.mViewHeight != 0 && this.mViewWidth != 0) {
            this.mMatrix.getValues(this.mMatrixValues);
            this.mPrevMatrix.setValues(this.mMatrixValues);
            this.mPrevMatchViewHeight = this.mMatchViewHeight;
            this.mPrevMatchViewWidth = this.mMatchViewWidth;
            this.mPrevViewHeight = this.mViewHeight;
            this.mPrevViewWidth = this.mViewWidth;
        }
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putFloat("saveScale", this.mNormalizedScale);
        bundle.putFloat("matchViewHeight", this.mMatchViewHeight);
        bundle.putFloat("matchViewWidth", this.mMatchViewWidth);
        bundle.putInt("viewWidth", this.mViewWidth);
        bundle.putInt("viewHeight", this.mViewHeight);
        this.mMatrix.getValues(this.mMatrixValues);
        bundle.putFloatArray("matrix", this.mMatrixValues);
        bundle.putBoolean("imageRendered", this.mImageRenderedAtLeastOnce);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.mNormalizedScale = bundle.getFloat("saveScale");
            this.mMatrixValues = bundle.getFloatArray("matrix");
            this.mPrevMatrix.setValues(this.mMatrixValues);
            this.mPrevMatchViewHeight = bundle.getFloat("matchViewHeight");
            this.mPrevMatchViewWidth = bundle.getFloat("matchViewWidth");
            this.mPrevViewHeight = bundle.getInt("viewHeight");
            this.mPrevViewWidth = bundle.getInt("viewWidth");
            this.mImageRenderedAtLeastOnce = bundle.getBoolean("imageRendered");
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.mOnDrawReady = true;
        this.mImageRenderedAtLeastOnce = true;
        if (this.mDelayedZoomVariables != null) {
            setZoom(this.mDelayedZoomVariables.scale, this.mDelayedZoomVariables.focusX, this.mDelayedZoomVariables.focusY, this.mDelayedZoomVariables.scaleType);
            this.mDelayedZoomVariables = null;
        }
        super.onDraw(canvas);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        savePreviousImageValues();
    }

    public float getMaxZoom() {
        return this.mMaxScale;
    }

    public void setMaxZoom(float f) {
        this.mMaxScale = f;
        this.mSuperMaxScale = this.mMaxScale * 1.25f;
    }

    public float getMinZoom() {
        return this.mMinScale;
    }

    public float getCurrentZoom() {
        return this.mNormalizedScale;
    }

    public void setMinZoom(float f) {
        this.mMinScale = f;
        this.mSuperMinScale = this.mMinScale * 0.75f;
    }

    /* access modifiers changed from: protected */
    public void disableZooming() {
        this.mZoomingEnabled = false;
        this.mMinScale = 1.0f;
        this.mSuperMinScale = 1.0f;
        this.mMaxScale = 1.0f;
        this.mSuperMaxScale = 1.0f;
        disableTouchEvents();
    }

    /* access modifiers changed from: protected */
    public void enableZooming() {
        this.mZoomingEnabled = true;
        setMinZoom(1.0f);
        setMaxZoom(3.0f);
        enableTouchEvents();
    }

    public void resetZoom() {
        this.mNormalizedScale = 1.0f;
        fitImageToView();
    }

    public void setZoom(float f) {
        setZoom(f, 0.5f, 0.5f);
    }

    public void setZoom(float f, float f2, float f3) {
        setZoom(f, f2, f3, this.mScaleType);
    }

    public void setZoom(float f, float f2, float f3, ScaleType scaleType) {
        if (!this.mOnDrawReady) {
            ZoomVariables zoomVariables = new ZoomVariables(f, f2, f3, scaleType);
            this.mDelayedZoomVariables = zoomVariables;
            return;
        }
        if (scaleType != this.mScaleType) {
            setScaleType(scaleType);
        }
        resetZoom();
        scaleImage((double) f, (float) (this.mViewWidth / 2), (float) (this.mViewHeight / 2), true);
        this.mMatrix.getValues(this.mMatrixValues);
        this.mMatrixValues[2] = -((f2 * getImageWidth()) - (((float) this.mViewWidth) * 0.5f));
        this.mMatrixValues[5] = -((f3 * getImageHeight()) - (((float) this.mViewHeight) * 0.5f));
        this.mMatrix.setValues(this.mMatrixValues);
        fixTrans();
        setImageMatrix(this.mMatrix);
    }

    public void setZoom(ZoomingImageView zoomingImageView) {
        PointF scrollPosition = zoomingImageView.getScrollPosition();
        if (scrollPosition != null) {
            setZoom(zoomingImageView.getCurrentZoom(), scrollPosition.x, scrollPosition.y, zoomingImageView.getScaleType());
        }
    }

    public PointF getScrollPosition() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        PointF transformCoordTouchToBitmap = transformCoordTouchToBitmap((float) (this.mViewWidth / 2), (float) (this.mViewHeight / 2), true);
        transformCoordTouchToBitmap.x /= (float) intrinsicWidth;
        transformCoordTouchToBitmap.y /= (float) intrinsicHeight;
        return transformCoordTouchToBitmap;
    }

    /* access modifiers changed from: private */
    public void fixTrans() {
        this.mMatrix.getValues(this.mMatrixValues);
        float f = this.mMatrixValues[2];
        float f2 = this.mMatrixValues[5];
        float fixTrans = getFixTrans(f, (float) this.mViewWidth, getImageWidth());
        float fixTrans2 = getFixTrans(f2, (float) this.mViewHeight, getImageHeight());
        if (fixTrans != 0.0f || fixTrans2 != 0.0f) {
            this.mMatrix.postTranslate(fixTrans, fixTrans2);
        }
    }

    /* access modifiers changed from: private */
    public void fixScaleTrans() {
        fixTrans();
        this.mMatrix.getValues(this.mMatrixValues);
        if (getImageWidth() < ((float) this.mViewWidth)) {
            this.mMatrixValues[2] = (((float) this.mViewWidth) - getImageWidth()) / 2.0f;
        }
        if (getImageHeight() < ((float) this.mViewHeight)) {
            this.mMatrixValues[5] = (((float) this.mViewHeight) - getImageHeight()) / 2.0f;
        }
        this.mMatrix.setValues(this.mMatrixValues);
    }

    /* access modifiers changed from: private */
    public float getImageWidth() {
        return this.mMatchViewWidth * this.mNormalizedScale;
    }

    /* access modifiers changed from: private */
    public float getImageHeight() {
        return this.mMatchViewHeight * this.mNormalizedScale;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0 || this.mSuperMaxScale == this.mSuperMinScale) {
            super.onMeasure(i, i2);
            int i3 = this.mViewWidth;
            int i4 = this.mViewHeight;
            this.mViewWidth = getWidth();
            this.mViewHeight = getHeight();
            if (!(this.mViewWidth == i3 && this.mViewHeight == i4)) {
                fitImageToView();
            }
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i2);
        this.mViewWidth = setViewSize(mode, size, intrinsicWidth);
        this.mViewHeight = setViewSize(mode2, size2, intrinsicHeight);
        setMeasuredDimension(this.mViewWidth, this.mViewHeight);
        fitImageToView();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0059, code lost:
        r1 = java.lang.Math.min(r1, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0062, code lost:
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0068, code lost:
        r5 = ((float) r11.mViewWidth) - (r1 * r2);
        r7 = ((float) r11.mViewHeight) - (r3 * r4);
        r11.mMatchViewWidth = ((float) r11.mViewWidth) - r5;
        r11.mMatchViewHeight = ((float) r11.mViewHeight) - r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0084, code lost:
        if (isZoomed() != false) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0088, code lost:
        if (r11.mImageRenderedAtLeastOnce != false) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x008a, code lost:
        r11.mMatrix.setScale(r1, r3);
        r11.mMatrix.postTranslate(r5 / 2.0f, r7 / 2.0f);
        r11.mNormalizedScale = 1.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a0, code lost:
        if (r11.mPrevMatchViewWidth == 0.0f) goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a6, code lost:
        if (r11.mPrevMatchViewHeight != 0.0f) goto L_0x00ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a8, code lost:
        savePreviousImageValues();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ab, code lost:
        r11.mPrevMatrix.getValues(r11.mMatrixValues);
        r11.mMatrixValues[0] = (r11.mMatchViewWidth / r2) * r11.mNormalizedScale;
        r11.mMatrixValues[4] = (r11.mMatchViewHeight / r4) * r11.mNormalizedScale;
        r4 = r11.mMatrixValues[2];
        r10 = r11.mMatrixValues[5];
        translateMatrixAfterRotate(2, r4, r11.mPrevMatchViewWidth * r11.mNormalizedScale, getImageWidth(), r11.mPrevViewWidth, r11.mViewWidth, r9);
        translateMatrixAfterRotate(5, r10, r11.mPrevMatchViewHeight * r11.mNormalizedScale, getImageHeight(), r11.mPrevViewHeight, r11.mViewHeight, r0);
        r11.mMatrix.setValues(r11.mMatrixValues);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0103, code lost:
        fixTrans();
        setImageMatrix(r11.mMatrix);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x010b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fitImageToView() {
        /*
            r11 = this;
            int r0 = r11.mViewWidth
            if (r0 != 0) goto L_0x0009
            int r0 = r11.mViewHeight
            if (r0 != 0) goto L_0x0009
            return
        L_0x0009:
            android.graphics.drawable.Drawable r0 = r11.getDrawable()
            if (r0 == 0) goto L_0x010d
            int r1 = r0.getIntrinsicWidth()
            if (r1 == 0) goto L_0x010d
            int r1 = r0.getIntrinsicHeight()
            if (r1 != 0) goto L_0x001d
            goto L_0x010d
        L_0x001d:
            android.graphics.Matrix r1 = r11.mMatrix
            if (r1 == 0) goto L_0x010c
            android.graphics.Matrix r1 = r11.mPrevMatrix
            if (r1 != 0) goto L_0x0027
            goto L_0x010c
        L_0x0027:
            int r9 = r0.getIntrinsicWidth()
            int r0 = r0.getIntrinsicHeight()
            int r1 = r11.mViewWidth
            float r1 = (float) r1
            float r2 = (float) r9
            float r1 = r1 / r2
            int r3 = r11.mViewHeight
            float r3 = (float) r3
            float r4 = (float) r0
            float r3 = r3 / r4
            int[] r5 = com.contextlogic.wish.ui.image.ZoomingImageView.AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType
            android.widget.ImageView$ScaleType r6 = r11.mScaleType
            int r6 = r6.ordinal()
            r5 = r5[r6]
            r6 = 1065353216(0x3f800000, float:1.0)
            switch(r5) {
                case 1: goto L_0x0064;
                case 2: goto L_0x005e;
                case 3: goto L_0x0050;
                case 4: goto L_0x0059;
                case 5: goto L_0x0068;
                default: goto L_0x0048;
            }
        L_0x0048:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "TouchImageView does not support FIT_START or FIT_END"
            r0.<init>(r1)
            throw r0
        L_0x0050:
            float r1 = java.lang.Math.min(r1, r3)
            float r1 = java.lang.Math.min(r6, r1)
            r3 = r1
        L_0x0059:
            float r1 = java.lang.Math.min(r1, r3)
            goto L_0x0062
        L_0x005e:
            float r1 = java.lang.Math.max(r1, r3)
        L_0x0062:
            r3 = r1
            goto L_0x0068
        L_0x0064:
            r1 = 1065353216(0x3f800000, float:1.0)
            r3 = 1065353216(0x3f800000, float:1.0)
        L_0x0068:
            int r5 = r11.mViewWidth
            float r5 = (float) r5
            float r7 = r1 * r2
            float r5 = r5 - r7
            int r7 = r11.mViewHeight
            float r7 = (float) r7
            float r8 = r3 * r4
            float r7 = r7 - r8
            int r8 = r11.mViewWidth
            float r8 = (float) r8
            float r8 = r8 - r5
            r11.mMatchViewWidth = r8
            int r8 = r11.mViewHeight
            float r8 = (float) r8
            float r8 = r8 - r7
            r11.mMatchViewHeight = r8
            boolean r8 = r11.isZoomed()
            if (r8 != 0) goto L_0x009b
            boolean r8 = r11.mImageRenderedAtLeastOnce
            if (r8 != 0) goto L_0x009b
            android.graphics.Matrix r0 = r11.mMatrix
            r0.setScale(r1, r3)
            android.graphics.Matrix r0 = r11.mMatrix
            r1 = 1073741824(0x40000000, float:2.0)
            float r5 = r5 / r1
            float r7 = r7 / r1
            r0.postTranslate(r5, r7)
            r11.mNormalizedScale = r6
            goto L_0x0103
        L_0x009b:
            float r1 = r11.mPrevMatchViewWidth
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x00a8
            float r1 = r11.mPrevMatchViewHeight
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x00ab
        L_0x00a8:
            r11.savePreviousImageValues()
        L_0x00ab:
            android.graphics.Matrix r1 = r11.mPrevMatrix
            float[] r3 = r11.mMatrixValues
            r1.getValues(r3)
            float[] r1 = r11.mMatrixValues
            r3 = 0
            float r5 = r11.mMatchViewWidth
            float r5 = r5 / r2
            float r2 = r11.mNormalizedScale
            float r5 = r5 * r2
            r1[r3] = r5
            float[] r1 = r11.mMatrixValues
            r2 = 4
            float r3 = r11.mMatchViewHeight
            float r3 = r3 / r4
            float r4 = r11.mNormalizedScale
            float r3 = r3 * r4
            r1[r2] = r3
            float[] r1 = r11.mMatrixValues
            r2 = 2
            r4 = r1[r2]
            float[] r1 = r11.mMatrixValues
            r2 = 5
            r10 = r1[r2]
            float r1 = r11.mPrevMatchViewWidth
            float r2 = r11.mNormalizedScale
            float r5 = r1 * r2
            float r6 = r11.getImageWidth()
            r3 = 2
            int r7 = r11.mPrevViewWidth
            int r8 = r11.mViewWidth
            r2 = r11
            r2.translateMatrixAfterRotate(r3, r4, r5, r6, r7, r8, r9)
            float r1 = r11.mPrevMatchViewHeight
            float r2 = r11.mNormalizedScale
            float r4 = r1 * r2
            float r5 = r11.getImageHeight()
            r2 = 5
            int r6 = r11.mPrevViewHeight
            int r7 = r11.mViewHeight
            r1 = r11
            r3 = r10
            r8 = r0
            r1.translateMatrixAfterRotate(r2, r3, r4, r5, r6, r7, r8)
            android.graphics.Matrix r0 = r11.mMatrix
            float[] r1 = r11.mMatrixValues
            r0.setValues(r1)
        L_0x0103:
            r11.fixTrans()
            android.graphics.Matrix r0 = r11.mMatrix
            r11.setImageMatrix(r0)
            return
        L_0x010c:
            return
        L_0x010d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.ui.image.ZoomingImageView.fitImageToView():void");
    }

    private int setViewSize(int i, int i2, int i3) {
        if (i != Integer.MIN_VALUE) {
            return i != 0 ? i2 : i3;
        }
        return Math.min(i3, i2);
    }

    private void translateMatrixAfterRotate(int i, float f, float f2, float f3, int i2, int i3, int i4) {
        float f4 = (float) i3;
        if (f3 < f4) {
            this.mMatrixValues[i] = (f4 - (((float) i4) * this.mMatrixValues[0])) * 0.5f;
        } else if (f > 0.0f) {
            this.mMatrixValues[i] = -((f3 - f4) * 0.5f);
        } else {
            this.mMatrixValues[i] = -((((Math.abs(f) + (((float) i2) * 0.5f)) / f2) * f3) - (f4 * 0.5f));
        }
    }

    /* access modifiers changed from: private */
    public void setState(State state) {
        this.mState = state;
    }

    public boolean canScrollHorizontallyFroyo(int i) {
        return canScrollHorizontally(i);
    }

    public boolean canScrollHorizontally(int i) {
        if (!this.mZoomingEnabled) {
            return false;
        }
        this.mMatrix.getValues(this.mMatrixValues);
        float f = this.mMatrixValues[2];
        if (getImageWidth() < ((float) this.mViewWidth)) {
            return false;
        }
        if (f >= -1.0f && i < 0) {
            return false;
        }
        if (Math.abs(f) + ((float) this.mViewWidth) + 1.0f < getImageWidth() || i <= 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void scaleImage(double d, float f, float f2, boolean z) {
        float f3;
        float f4;
        if (z) {
            f3 = this.mSuperMinScale;
            f4 = this.mSuperMaxScale;
        } else {
            f3 = this.mMinScale;
            f4 = this.mMaxScale;
        }
        float f5 = this.mNormalizedScale;
        this.mNormalizedScale = (float) (((double) this.mNormalizedScale) * d);
        if (this.mNormalizedScale > f4) {
            this.mNormalizedScale = f4;
            d = (double) (f4 / f5);
        } else if (this.mNormalizedScale < f3) {
            this.mNormalizedScale = f3;
            d = (double) (f3 / f5);
        }
        float f6 = (float) d;
        this.mMatrix.postScale(f6, f6, f, f2);
        fixScaleTrans();
    }

    /* access modifiers changed from: private */
    public PointF transformCoordTouchToBitmap(float f, float f2, boolean z) {
        this.mMatrix.getValues(this.mMatrixValues);
        float intrinsicWidth = getDrawable() == null ? 0.0f : (float) getDrawable().getIntrinsicWidth();
        float intrinsicHeight = getDrawable() == null ? 0.0f : (float) getDrawable().getIntrinsicHeight();
        float imageWidth = ((f - this.mMatrixValues[2]) * intrinsicWidth) / getImageWidth();
        float imageHeight = ((f2 - this.mMatrixValues[5]) * intrinsicHeight) / getImageHeight();
        if (z) {
            imageWidth = Math.min(Math.max(imageWidth, 0.0f), intrinsicWidth);
            imageHeight = Math.min(Math.max(imageHeight, 0.0f), intrinsicHeight);
        }
        return new PointF(imageWidth, imageHeight);
    }

    /* access modifiers changed from: private */
    public PointF transformCoordBitmapToTouch(float f, float f2) {
        this.mMatrix.getValues(this.mMatrixValues);
        float f3 = 0.0f;
        float intrinsicWidth = getDrawable() == null ? 0.0f : (float) getDrawable().getIntrinsicWidth();
        if (getDrawable() != null) {
            f3 = (float) getDrawable().getIntrinsicHeight();
        }
        return new PointF(this.mMatrixValues[2] + (getImageWidth() * (f / intrinsicWidth)), this.mMatrixValues[5] + (getImageHeight() * (f2 / f3)));
    }

    /* access modifiers changed from: private */
    @TargetApi(16)
    public void compatPostOnAnimation(Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            postOnAnimation(runnable);
        } else {
            postDelayed(runnable, 16);
        }
    }
}
