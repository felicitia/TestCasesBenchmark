package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.a.q;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.util.u;
import com.etsy.android.uikit.adapter.AbstractContextRecyclerViewAdapter;
import java.util.ArrayList;

public class RangedProgressBar extends AppCompatImageView {
    private static final int DIVIDER_HEIGHT = 8;
    private static final int DIVIDER_PADDING = 4;
    private static final int DIVIDER_WIDTH = 1;
    private static final int NUMBERS_SIZE = 12;
    private static final int SLIDER_WIDTH = 2;
    private static final String TAG = f.a(RangedProgressBar.class);
    private static final int THUMB_AURORA = 20;
    private static final int THUMB_RADIUS_LARGE = 24;
    private static final int THUMB_RADIUS_SMALL = 6;
    /* access modifiers changed from: private */
    public final float mBigThumbRadius;
    private String[] mBucketLabels;
    private int[] mBuckets;
    private Paint mCirclePaint;
    private final float mDensity;
    private final float mDividerHeight;
    private final float mDividerPadding;
    private Paint mDividerPaint;
    private int mLevels;
    private Paint mLineBkgPaint;
    private Paint mLinePaint;
    /* access modifiers changed from: private */
    public float mMaxPixels;
    /* access modifiers changed from: private */
    public float mMinPixels;
    private final float mNumberTextHeight;
    private final float mScaledDensity;
    private a mSeekBarChangedListener;
    private int mSelectedThumb;
    private Paint mTextPainter;
    private boolean mThumb1Enabled;
    private b mThumb1X;
    private b mThumb2X;
    private final float mThumbAurora;
    /* access modifiers changed from: private */
    public final float mThumbRadius;
    private float mThumbYValue;
    /* access modifiers changed from: private */
    public float mWaypointDistance;
    /* access modifiers changed from: private */
    public ArrayList<Integer> mWaypoints;

    public interface a {
        void a(int i, int i2);
    }

    private class b {
        final Interpolator a = new AccelerateDecelerateInterpolator();
        final Interpolator b = new DecelerateInterpolator();
        long c;
        long d;
        long e = 77;
        long f = 0;
        float g = RangedProgressBar.this.mThumbRadius;
        float h = RangedProgressBar.this.mBigThumbRadius;
        float i = RangedProgressBar.this.mThumbRadius;
        boolean j = false;
        long k;
        private final Paint m = new Paint();
        private Interpolator n = new AccelerateDecelerateInterpolator();
        private int o;
        private float p;
        private boolean q;
        private int r;

        public b() {
            this.m.setColor(RangedProgressBar.this.getResources().getColor(e.slider_large_circle));
            this.m.setAntiAlias(true);
            this.m.setAlpha(0);
            this.r = -1;
        }

        public void a(int i2) {
            this.o = u.a(i2, 0, RangedProgressBar.this.mWaypoints.size() - 1);
            d();
        }

        public void a(float f2) {
            b(f2);
            d();
        }

        public int a() {
            return this.o;
        }

        public float b() {
            return this.p;
        }

        public int c() {
            return ((Integer) RangedProgressBar.this.mWaypoints.get(this.o)).intValue();
        }

        private void b(float f2) {
            this.o = u.a(Math.round((f2 - RangedProgressBar.this.mMinPixels) / RangedProgressBar.this.mWaypointDistance), 0, RangedProgressBar.this.mWaypoints.size() - 1);
        }

        public void d() {
            this.p = u.a((float) Math.floor((double) ((((float) this.o) * RangedProgressBar.this.mWaypointDistance) + RangedProgressBar.this.mMinPixels)), RangedProgressBar.this.mMinPixels, RangedProgressBar.this.mMaxPixels);
        }

        public void a(boolean z) {
            this.q = z;
        }

        public boolean e() {
            return this.q;
        }

        public void b(int i2) {
            if (i2 != this.r || i2 == -1) {
                this.j = false;
            }
            this.r = i2;
        }

        /* access modifiers changed from: private */
        public void a(Canvas canvas, float f2) {
            if (!this.j && this.r != -1) {
                this.c = System.currentTimeMillis();
                this.d = this.c;
                this.j = true;
                if (this.r == 1) {
                    this.e = 0;
                    this.f = (long) this.m.getAlpha();
                    this.g = this.i;
                    this.h = 0.0f;
                    this.k = 500;
                    this.n = this.b;
                } else if (this.r == 0) {
                    this.e = 77;
                    this.f = 0;
                    this.k = 300;
                    this.g = 0.0f;
                    this.h = RangedProgressBar.this.mBigThumbRadius;
                    this.i = this.g;
                    this.n = this.a;
                }
            }
            if (this.r != -1 && this.j) {
                if (this.c - this.d >= this.k) {
                    this.j = false;
                    this.r = -1;
                    this.m.setAlpha((int) this.e);
                    this.i = this.h;
                } else if (System.currentTimeMillis() >= this.c + 60) {
                    this.c = System.currentTimeMillis();
                    float a2 = u.a(this.n.getInterpolation(((float) (this.c - this.d)) / ((float) this.k)), 0.0f, 1.0f);
                    this.m.setAlpha(Math.round((((float) (this.e - this.f)) * a2) + ((float) this.f)));
                    if (this.r == 0) {
                        this.i = (a2 * (this.h - this.g)) + this.g;
                    }
                }
            }
            if (this.q || this.r == 1) {
                canvas.drawCircle(this.p, f2, this.i, this.m);
                RangedProgressBar.this.invalidate();
            }
        }
    }

    public RangedProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBuckets = new int[]{0, 25, 50, 100, 200, AbstractContextRecyclerViewAdapter.VIEW_TYPE_HEADER, 1000};
        this.mBucketLabels = new String[]{"0", "25", "50", "100", "200", "500", "∞"};
        this.mLevels = 5;
        this.mThumb1Enabled = true;
        this.mWaypoints = new ArrayList<>();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, q.RangedProgressBar);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == q.RangedProgressBar_waypointLevels) {
                    this.mLevels = obtainStyledAttributes.getInt(index, 5);
                } else if (index == q.RangedProgressBar_waypointLabels) {
                    this.mBucketLabels = getResources().getStringArray(obtainStyledAttributes.getResourceId(index, 0));
                } else if (index == q.RangedProgressBar_waypointValues) {
                    this.mBuckets = getResources().getIntArray(obtainStyledAttributes.getResourceId(index, 0));
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.mDensity = getResources().getDisplayMetrics().density;
        this.mScaledDensity = getResources().getDisplayMetrics().scaledDensity;
        this.mThumbRadius = this.mDensity * 6.0f;
        this.mBigThumbRadius = this.mDensity * 24.0f;
        this.mThumbAurora = this.mDensity * 20.0f;
        this.mDividerHeight = this.mDensity * 8.0f;
        this.mDividerPadding = this.mDensity * 4.0f;
        this.mNumberTextHeight = this.mScaledDensity * 12.0f;
        construct();
    }

    public RangedProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RangedProgressBar(Context context) {
        this(context, null, 0);
    }

    private void construct() {
        this.mThumb1X = new b();
        this.mThumb2X = new b();
        fillWaypoints();
        this.mLinePaint = new Paint();
        this.mLinePaint.setColor(getResources().getColor(e.blue));
        this.mLinePaint.setStrokeWidth(this.mDensity * 2.0f);
        this.mLineBkgPaint = new Paint();
        this.mLineBkgPaint.setColor(getResources().getColor(e.slider_bar_unfilled));
        this.mLineBkgPaint.setStrokeWidth(2.0f * this.mDensity);
        this.mTextPainter = new Paint();
        this.mTextPainter.setColor(getResources().getColor(e.slider_numbers));
        this.mTextPainter.setStyle(Style.STROKE);
        com.etsy.android.stylekit.e.a(getContext(), this.mTextPainter, o.sk_typeface_bold);
        this.mTextPainter.setAntiAlias(true);
        this.mTextPainter.setTextSize(12.0f * this.mScaledDensity);
        this.mCirclePaint = new Paint();
        this.mCirclePaint.setColor(getResources().getColor(e.blue));
        this.mCirclePaint.setAntiAlias(true);
        this.mDividerPaint = new Paint();
        this.mDividerPaint.setAntiAlias(true);
        this.mDividerPaint.setStrokeWidth(1.0f * this.mScaledDensity);
        this.mDividerPaint.setColor(getResources().getColor(e.slider_numbers));
    }

    private void fillWaypoints() {
        int i = 0;
        int i2 = 0;
        while (i < this.mBuckets.length - 1) {
            int i3 = i + 1;
            int i4 = (this.mBuckets[i3] - this.mBuckets[i]) / this.mLevels;
            int i5 = i2;
            for (int i6 = 0; i6 < this.mLevels; i6++) {
                this.mWaypoints.add(i5, Integer.valueOf(this.mBuckets[i] + (i6 * i4)));
                i5++;
            }
            i = i3;
            i2 = i5;
        }
        this.mWaypoints.add(i2, Integer.valueOf(this.mBuckets[this.mBuckets.length - 1]));
    }

    public int getMaxValue() {
        return this.mBuckets[this.mBuckets.length - 1];
    }

    public int getMinValue() {
        return this.mBuckets[0];
    }

    public void setWaypointParameters(int[] iArr, String[] strArr, int i) {
        this.mBuckets = iArr;
        this.mBucketLabels = strArr;
        this.mLevels = i;
        this.mWaypoints.clear();
        fillWaypoints();
    }

    public void setThumb1Enabled(boolean z) {
        this.mThumb1Enabled = z;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getHeight() > 0 && getWidth() != ((int) (this.mMaxPixels + this.mBigThumbRadius))) {
            remeasure();
        }
        setMeasuredDimension(MeasureSpec.getSize(i), resolveSize((int) Math.ceil((double) ((12.0f * this.mScaledDensity) + (this.mDensity * 4.0f) + (8.0f * this.mDensity) + (4.0f * this.mDensity) + (6.0f * this.mDensity) + (24.0f * this.mDensity))), i2));
        invalidate();
    }

    private void remeasure() {
        setDefaults();
        this.mThumb1X.d();
        this.mThumb2X.d();
    }

    private void setDefaults() {
        this.mMinPixels = this.mBigThumbRadius;
        this.mMaxPixels = ((float) getWidth()) - this.mBigThumbRadius;
        this.mWaypointDistance = ((this.mMaxPixels - this.mMinPixels) / ((float) (this.mLevels * (this.mBuckets.length - 1)))) * 1.0f;
        this.mThumbYValue = (float) ((int) Math.ceil((double) ((12.0f * this.mScaledDensity) + (this.mDensity * 4.0f) + (8.0f * this.mDensity) + (4.0f * this.mDensity) + (6.0f * this.mDensity))));
    }

    private int getWaypointFromValue(int i) {
        int size = this.mWaypoints.size() - 1;
        int i2 = 0;
        if (i > 0) {
            if (i >= this.mBuckets[this.mBuckets.length - 1]) {
                return this.mWaypoints.size() - 1;
            }
            while (i2 < this.mWaypoints.size()) {
                if (i > ((Integer) this.mWaypoints.get(i2)).intValue()) {
                    i2++;
                }
            }
            return size;
        }
        return i2;
    }

    public void setSeekBarChangeListener(a aVar) {
        this.mSeekBarChangedListener = aVar;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        remeasure();
        float f = this.mThumbYValue;
        float f2 = f;
        canvas.drawLine(this.mMinPixels, f, this.mMaxPixels, f2, this.mLineBkgPaint);
        canvas.drawLine(this.mThumb1X.b(), f, this.mThumb2X.b() - this.mThumbRadius, f2, this.mLinePaint);
        if (this.mThumb1Enabled) {
            canvas.drawCircle(this.mThumb1X.b(), f, this.mThumbRadius, this.mCirclePaint);
            this.mThumb1X.a(canvas, f);
        }
        canvas.drawCircle(this.mThumb2X.b(), f, this.mThumbRadius, this.mCirclePaint);
        this.mThumb2X.a(canvas, f);
        for (int i = 0; i < this.mBuckets.length; i++) {
            float f3 = this.mNumberTextHeight + this.mDividerPadding;
            float indexOf = (this.mWaypointDistance * ((float) this.mWaypoints.indexOf(Integer.valueOf(this.mBuckets[i])))) + this.mMinPixels;
            String str = this.mBucketLabels[i];
            canvas.drawText(str, indexOf - (this.mTextPainter.measureText(str) / 2.0f), this.mNumberTextHeight, this.mTextPainter);
            canvas.drawLine(indexOf, f3, indexOf, f3 + this.mDividerHeight, this.mDividerPaint);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int c = this.mThumb1X.c();
        int c2 = this.mThumb2X.c();
        float x = motionEvent.getX();
        int action = motionEvent.getAction();
        if (action == 0) {
            float b2 = this.mThumb1X.b();
            float b3 = this.mThumb2X.b();
            if (this.mThumb1Enabled && x >= b2 - this.mThumbRadius && x <= this.mThumbRadius + b2) {
                this.mSelectedThumb = 1;
            } else if (x >= b3 - this.mThumbRadius && x <= this.mThumbRadius + b3) {
                this.mSelectedThumb = 2;
            } else if (this.mThumb1Enabled && x >= b2 - this.mThumbAurora && x <= b2 + this.mThumbAurora) {
                this.mSelectedThumb = 1;
            } else if (x >= b3 - this.mThumbAurora && x <= b3 + this.mThumbAurora) {
                this.mSelectedThumb = 2;
            }
            switch (this.mSelectedThumb) {
                case 1:
                    this.mThumb1X.a(true);
                    this.mThumb2X.a(false);
                    this.mThumb1X.b(0);
                    break;
                case 2:
                    this.mThumb2X.a(true);
                    this.mThumb1X.a(false);
                    this.mThumb2X.b(0);
                    break;
            }
            if (getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        } else if (action != 2) {
            if (this.mThumb1X.e()) {
                this.mThumb1X.b(1);
            }
            if (this.mThumb2X.e()) {
                this.mThumb2X.b(1);
            }
            this.mSelectedThumb = 0;
            this.mThumb1X.a(false);
            this.mThumb2X.a(false);
            if (getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        } else if (this.mSelectedThumb == 1) {
            this.mThumb1X.a(u.a(x, this.mMinPixels, (float) Math.floor((double) (this.mThumb2X.b() - this.mWaypointDistance))));
            if (this.mThumb1X.a() == this.mThumb2X.a()) {
                this.mThumb1X.a(this.mThumb1X.a() - 1);
            }
        } else if (this.mSelectedThumb == 2) {
            this.mThumb2X.a(u.a(x, (float) Math.ceil((double) (this.mThumb1X.b() + this.mWaypointDistance)), this.mMaxPixels));
            if (this.mThumb2X.a() == this.mThumb1X.a()) {
                this.mThumb2X.a(this.mThumb2X.a() + 1);
            }
        }
        invalidate();
        if (!(this.mSeekBarChangedListener == null || (c == this.mThumb1X.c() && c2 == this.mThumb2X.c()))) {
            this.mSeekBarChangedListener.a(this.mThumb1X.c(), this.mThumb2X.c());
        }
        return true;
    }

    public void setProgress(int i, int i2) {
        setProgressLower(i);
        setProgressUpper(i2);
    }

    private void setProgressUpper(int i) {
        this.mThumb2X.a(getWaypointFromValue(i));
        invalidate();
    }

    private void setProgressLower(int i) {
        this.mThumb1X.a(getWaypointFromValue(i));
        invalidate();
    }
}
