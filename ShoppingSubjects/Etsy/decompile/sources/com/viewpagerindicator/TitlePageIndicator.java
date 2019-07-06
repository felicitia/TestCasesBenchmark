package com.viewpagerindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import java.util.ArrayList;

public class TitlePageIndicator extends View implements PageIndicator {
    private static final float BOLD_FADE_PERCENTAGE = 0.05f;
    private static final String EMPTY_TITLE = "";
    private static final int INVALID_POINTER = -1;
    private static final float SELECTION_FADE_PERCENTAGE = 0.25f;
    private int mActivePointerId;
    private boolean mBoldText;
    private final Rect mBounds;
    private OnCenterItemClickListener mCenterItemClickListener;
    private float mClipPadding;
    private int mColorSelected;
    private int mColorText;
    private int mCurrentPage;
    private float mFooterIndicatorHeight;
    private IndicatorStyle mFooterIndicatorStyle;
    private float mFooterIndicatorUnderlinePadding;
    private float mFooterLineHeight;
    private float mFooterPadding;
    private boolean mIsDragging;
    private float mLastMotionX;
    private LinePosition mLinePosition;
    private OnPageChangeListener mListener;
    private float mPageOffset;
    private final Paint mPaintFooterIndicator;
    private final Paint mPaintFooterLine;
    private final Paint mPaintText;
    private Path mPath;
    private int mScrollState;
    private float mTitlePadding;
    private float mTopPadding;
    private int mTouchSlop;
    private ViewPager mViewPager;

    public enum IndicatorStyle {
        None(0),
        Triangle(1),
        Underline(2);
        
        public final int value;

        private IndicatorStyle(int i) {
            this.value = i;
        }

        public static IndicatorStyle fromValue(int i) {
            IndicatorStyle[] values;
            for (IndicatorStyle indicatorStyle : values()) {
                if (indicatorStyle.value == i) {
                    return indicatorStyle;
                }
            }
            return null;
        }
    }

    public enum LinePosition {
        Bottom(0),
        Top(1);
        
        public final int value;

        private LinePosition(int i) {
            this.value = i;
        }

        public static LinePosition fromValue(int i) {
            LinePosition[] values;
            for (LinePosition linePosition : values()) {
                if (linePosition.value == i) {
                    return linePosition;
                }
            }
            return null;
        }
    }

    public interface OnCenterItemClickListener {
        void onCenterItemClick(int i);
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int currentPage;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.currentPage = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.currentPage);
        }
    }

    public TitlePageIndicator(Context context) {
        this(context, null);
    }

    public TitlePageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.vpiTitlePageIndicatorStyle);
    }

    public TitlePageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentPage = -1;
        this.mPaintText = new Paint();
        this.mPath = new Path();
        this.mBounds = new Rect();
        this.mPaintFooterLine = new Paint();
        this.mPaintFooterIndicator = new Paint();
        this.mLastMotionX = -1.0f;
        this.mActivePointerId = -1;
        if (!isInEditMode()) {
            Resources resources = getResources();
            int color = resources.getColor(R.color.default_title_indicator_footer_color);
            float dimension = resources.getDimension(R.dimen.default_title_indicator_footer_line_height);
            int integer = resources.getInteger(R.integer.default_title_indicator_footer_indicator_style);
            float dimension2 = resources.getDimension(R.dimen.default_title_indicator_footer_indicator_height);
            float dimension3 = resources.getDimension(R.dimen.default_title_indicator_footer_indicator_underline_padding);
            float dimension4 = resources.getDimension(R.dimen.default_title_indicator_footer_padding);
            int integer2 = resources.getInteger(R.integer.default_title_indicator_line_position);
            int color2 = resources.getColor(R.color.default_title_indicator_selected_color);
            boolean z = resources.getBoolean(R.bool.default_title_indicator_selected_bold);
            int color3 = resources.getColor(R.color.default_title_indicator_text_color);
            float dimension5 = resources.getDimension(R.dimen.default_title_indicator_text_size);
            float dimension6 = resources.getDimension(R.dimen.default_title_indicator_title_padding);
            float dimension7 = resources.getDimension(R.dimen.default_title_indicator_clip_padding);
            float dimension8 = resources.getDimension(R.dimen.default_title_indicator_top_padding);
            int i2 = color;
            boolean z2 = z;
            int i3 = color3;
            float f = dimension5;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TitlePageIndicator, i, 0);
            this.mFooterLineHeight = obtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_footerLineHeight, dimension);
            this.mFooterIndicatorStyle = IndicatorStyle.fromValue(obtainStyledAttributes.getInteger(R.styleable.TitlePageIndicator_footerIndicatorStyle, integer));
            this.mFooterIndicatorHeight = obtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_footerIndicatorHeight, dimension2);
            this.mFooterIndicatorUnderlinePadding = obtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_footerIndicatorUnderlinePadding, dimension3);
            this.mFooterPadding = obtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_footerPadding, dimension4);
            this.mLinePosition = LinePosition.fromValue(obtainStyledAttributes.getInteger(R.styleable.TitlePageIndicator_linePosition, integer2));
            this.mTopPadding = obtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_topPadding, dimension8);
            this.mTitlePadding = obtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_titlePadding, dimension6);
            this.mClipPadding = obtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_clipPadding, dimension7);
            this.mColorSelected = obtainStyledAttributes.getColor(R.styleable.TitlePageIndicator_selectedColor, color2);
            this.mColorText = obtainStyledAttributes.getColor(R.styleable.TitlePageIndicator_android_textColor, i3);
            this.mBoldText = obtainStyledAttributes.getBoolean(R.styleable.TitlePageIndicator_selectedBold, z2);
            float dimension9 = obtainStyledAttributes.getDimension(R.styleable.TitlePageIndicator_android_textSize, f);
            int color4 = obtainStyledAttributes.getColor(R.styleable.TitlePageIndicator_footerColor, i2);
            this.mPaintText.setTextSize(dimension9);
            this.mPaintText.setAntiAlias(true);
            this.mPaintFooterLine.setStyle(Style.FILL_AND_STROKE);
            this.mPaintFooterLine.setStrokeWidth(this.mFooterLineHeight);
            this.mPaintFooterLine.setColor(color4);
            this.mPaintFooterIndicator.setStyle(Style.FILL_AND_STROKE);
            this.mPaintFooterIndicator.setColor(color4);
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.TitlePageIndicator_android_background);
            if (drawable != null) {
                setBackgroundDrawable(drawable);
            }
            obtainStyledAttributes.recycle();
            this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
        }
    }

    public int getFooterColor() {
        return this.mPaintFooterLine.getColor();
    }

    public void setFooterColor(int i) {
        this.mPaintFooterLine.setColor(i);
        this.mPaintFooterIndicator.setColor(i);
        invalidate();
    }

    public float getFooterLineHeight() {
        return this.mFooterLineHeight;
    }

    public void setFooterLineHeight(float f) {
        this.mFooterLineHeight = f;
        this.mPaintFooterLine.setStrokeWidth(this.mFooterLineHeight);
        invalidate();
    }

    public float getFooterIndicatorHeight() {
        return this.mFooterIndicatorHeight;
    }

    public void setFooterIndicatorHeight(float f) {
        this.mFooterIndicatorHeight = f;
        invalidate();
    }

    public float getFooterIndicatorPadding() {
        return this.mFooterPadding;
    }

    public void setFooterIndicatorPadding(float f) {
        this.mFooterPadding = f;
        invalidate();
    }

    public IndicatorStyle getFooterIndicatorStyle() {
        return this.mFooterIndicatorStyle;
    }

    public void setFooterIndicatorStyle(IndicatorStyle indicatorStyle) {
        this.mFooterIndicatorStyle = indicatorStyle;
        invalidate();
    }

    public LinePosition getLinePosition() {
        return this.mLinePosition;
    }

    public void setLinePosition(LinePosition linePosition) {
        this.mLinePosition = linePosition;
        invalidate();
    }

    public int getSelectedColor() {
        return this.mColorSelected;
    }

    public void setSelectedColor(int i) {
        this.mColorSelected = i;
        invalidate();
    }

    public boolean isSelectedBold() {
        return this.mBoldText;
    }

    public void setSelectedBold(boolean z) {
        this.mBoldText = z;
        invalidate();
    }

    public int getTextColor() {
        return this.mColorText;
    }

    public void setTextColor(int i) {
        this.mPaintText.setColor(i);
        this.mColorText = i;
        invalidate();
    }

    public float getTextSize() {
        return this.mPaintText.getTextSize();
    }

    public void setTextSize(float f) {
        this.mPaintText.setTextSize(f);
        invalidate();
    }

    public float getTitlePadding() {
        return this.mTitlePadding;
    }

    public void setTitlePadding(float f) {
        this.mTitlePadding = f;
        invalidate();
    }

    public float getTopPadding() {
        return this.mTopPadding;
    }

    public void setTopPadding(float f) {
        this.mTopPadding = f;
        invalidate();
    }

    public float getClipPadding() {
        return this.mClipPadding;
    }

    public void setClipPadding(float f) {
        this.mClipPadding = f;
        invalidate();
    }

    public void setTypeface(Typeface typeface) {
        this.mPaintText.setTypeface(typeface);
        invalidate();
    }

    public Typeface getTypeface() {
        return this.mPaintText.getTypeface();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f;
        boolean z;
        float f2;
        float f3;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        float f4;
        Canvas canvas2 = canvas;
        super.onDraw(canvas);
        if (this.mViewPager != null) {
            int count = this.mViewPager.getAdapter().getCount();
            if (count != 0) {
                if (this.mCurrentPage == -1 && this.mViewPager != null) {
                    this.mCurrentPage = this.mViewPager.getCurrentItem();
                }
                ArrayList calculateAllBounds = calculateAllBounds(this.mPaintText);
                int size = calculateAllBounds.size();
                if (this.mCurrentPage >= size) {
                    setCurrentItem(size - 1);
                    return;
                }
                int i6 = count - 1;
                float width = ((float) getWidth()) / 2.0f;
                int left = getLeft();
                float f5 = ((float) left) + this.mClipPadding;
                int width2 = getWidth();
                int height = getHeight();
                int i7 = left + width2;
                float f6 = ((float) i7) - this.mClipPadding;
                int i8 = this.mCurrentPage;
                float f7 = width;
                if (((double) this.mPageOffset) <= 0.5d) {
                    f = this.mPageOffset;
                } else {
                    i8++;
                    f = 1.0f - this.mPageOffset;
                }
                int i9 = i8;
                boolean z2 = f <= SELECTION_FADE_PERCENTAGE;
                boolean z3 = f <= BOLD_FADE_PERCENTAGE;
                float f8 = (SELECTION_FADE_PERCENTAGE - f) / SELECTION_FADE_PERCENTAGE;
                Rect rect = (Rect) calculateAllBounds.get(this.mCurrentPage);
                float f9 = (float) (rect.right - rect.left);
                if (((float) rect.left) < f5) {
                    clipViewOnTheLeft(rect, f9, left);
                }
                if (((float) rect.right) > f6) {
                    clipViewOnTheRight(rect, f9, i7);
                }
                if (this.mCurrentPage > 0) {
                    int i10 = this.mCurrentPage - 1;
                    while (i10 >= 0) {
                        Rect rect2 = (Rect) calculateAllBounds.get(i10);
                        if (((float) rect2.left) < f5) {
                            f4 = f5;
                            int i11 = rect2.right - rect2.left;
                            clipViewOnTheLeft(rect2, (float) i11, left);
                            Rect rect3 = (Rect) calculateAllBounds.get(i10 + 1);
                            i5 = width2;
                            if (((float) rect2.right) + this.mTitlePadding > ((float) rect3.left)) {
                                rect2.left = (int) (((float) (rect3.left - i11)) - this.mTitlePadding);
                                rect2.right = rect2.left + i11;
                            }
                        } else {
                            f4 = f5;
                            i5 = width2;
                        }
                        i10--;
                        f5 = f4;
                        width2 = i5;
                        Canvas canvas3 = canvas;
                    }
                }
                int i12 = width2;
                if (this.mCurrentPage < i6) {
                    z = true;
                    for (int i13 = this.mCurrentPage + 1; i13 < count; i13++) {
                        Rect rect4 = (Rect) calculateAllBounds.get(i13);
                        if (((float) rect4.right) > f6) {
                            int i14 = rect4.right - rect4.left;
                            clipViewOnTheRight(rect4, (float) i14, i7);
                            Rect rect5 = (Rect) calculateAllBounds.get(i13 - 1);
                            if (((float) rect4.left) - this.mTitlePadding < ((float) rect5.right)) {
                                rect4.left = (int) (((float) rect5.right) + this.mTitlePadding);
                                rect4.right = rect4.left + i14;
                            }
                        }
                    }
                } else {
                    z = true;
                }
                int i15 = this.mColorText >>> 24;
                int i16 = 0;
                while (i16 < count) {
                    Rect rect6 = (Rect) calculateAllBounds.get(i16);
                    if ((rect6.left <= left || rect6.left >= i7) && (rect6.right <= left || rect6.right >= i7)) {
                        i3 = i7;
                        i4 = i12;
                        i2 = i16;
                    } else {
                        boolean z4 = i16 == i9 ? z : false;
                        CharSequence title = getTitle(i16);
                        this.mPaintText.setFakeBoldText((!z4 || !z3 || !this.mBoldText) ? false : z);
                        this.mPaintText.setColor(this.mColorText);
                        if (z4 && z2) {
                            this.mPaintText.setAlpha(i15 - ((int) (((float) i15) * f8)));
                        }
                        if (i16 < size - 1) {
                            Rect rect7 = (Rect) calculateAllBounds.get(i16 + 1);
                            if (((float) rect6.right) + this.mTitlePadding > ((float) rect7.left)) {
                                int i17 = rect6.right - rect6.left;
                                rect6.left = (int) (((float) (rect7.left - i17)) - this.mTitlePadding);
                                rect6.right = rect6.left + i17;
                            }
                        }
                        CharSequence charSequence = title;
                        Rect rect8 = rect6;
                        i3 = i7;
                        i4 = i12;
                        i2 = i16;
                        canvas.drawText(title, 0, title.length(), (float) rect6.left, this.mTopPadding + ((float) rect6.bottom), this.mPaintText);
                        if (z4 && z2) {
                            this.mPaintText.setColor(this.mColorSelected);
                            this.mPaintText.setAlpha((int) (((float) (this.mColorSelected >>> 24)) * f8));
                            CharSequence charSequence2 = charSequence;
                            Rect rect9 = rect8;
                            canvas.drawText(charSequence2, 0, charSequence2.length(), (float) rect9.left, this.mTopPadding + ((float) rect9.bottom), this.mPaintText);
                        }
                    }
                    i16 = i2 + 1;
                    i12 = i4;
                    i7 = i3;
                    z = true;
                }
                int i18 = i12;
                float f10 = this.mFooterLineHeight;
                float f11 = this.mFooterIndicatorHeight;
                if (this.mLinePosition == LinePosition.Top) {
                    f2 = -f11;
                    f3 = -f10;
                    i = 0;
                } else {
                    f2 = f11;
                    f3 = f10;
                    i = height;
                }
                this.mPath.reset();
                float f12 = (float) i;
                float f13 = f12 - (f3 / 2.0f);
                this.mPath.moveTo(0.0f, f13);
                this.mPath.lineTo((float) i18, f13);
                this.mPath.close();
                Canvas canvas4 = canvas;
                canvas4.drawPath(this.mPath, this.mPaintFooterLine);
                float f14 = f12 - f3;
                switch (this.mFooterIndicatorStyle) {
                    case Triangle:
                        this.mPath.reset();
                        float f15 = f7;
                        this.mPath.moveTo(f15, f14 - f2);
                        this.mPath.lineTo(f15 + f2, f14);
                        this.mPath.lineTo(f15 - f2, f14);
                        this.mPath.close();
                        canvas4.drawPath(this.mPath, this.mPaintFooterIndicator);
                        break;
                    case Underline:
                        if (z2 && i9 < size) {
                            Rect rect10 = (Rect) calculateAllBounds.get(i9);
                            float f16 = ((float) rect10.right) + this.mFooterIndicatorUnderlinePadding;
                            float f17 = ((float) rect10.left) - this.mFooterIndicatorUnderlinePadding;
                            float f18 = f14 - f2;
                            this.mPath.reset();
                            this.mPath.moveTo(f17, f14);
                            this.mPath.lineTo(f16, f14);
                            this.mPath.lineTo(f16, f18);
                            this.mPath.lineTo(f17, f18);
                            this.mPath.close();
                            this.mPaintFooterIndicator.setAlpha((int) (255.0f * f8));
                            canvas4.drawPath(this.mPath, this.mPaintFooterIndicator);
                            this.mPaintFooterIndicator.setAlpha(255);
                            break;
                        }
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (super.onTouchEvent(motionEvent)) {
            return true;
        }
        int i = 0;
        if (this.mViewPager == null || this.mViewPager.getAdapter().getCount() == 0) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        switch (action) {
            case 0:
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                this.mLastMotionX = motionEvent.getX();
                break;
            case 1:
            case 3:
                if (!this.mIsDragging) {
                    int count = this.mViewPager.getAdapter().getCount();
                    float width = (float) getWidth();
                    float f = width / 2.0f;
                    float f2 = width / 6.0f;
                    float f3 = f - f2;
                    float f4 = f + f2;
                    float x = motionEvent.getX();
                    if (x < f3) {
                        if (this.mCurrentPage > 0) {
                            if (action != 3) {
                                this.mViewPager.setCurrentItem(this.mCurrentPage - 1);
                            }
                            return true;
                        }
                    } else if (x > f4) {
                        if (this.mCurrentPage < count - 1) {
                            if (action != 3) {
                                this.mViewPager.setCurrentItem(this.mCurrentPage + 1);
                            }
                            return true;
                        }
                    } else if (!(this.mCenterItemClickListener == null || action == 3)) {
                        this.mCenterItemClickListener.onCenterItemClick(this.mCurrentPage);
                    }
                }
                this.mIsDragging = false;
                this.mActivePointerId = -1;
                if (this.mViewPager.isFakeDragging()) {
                    this.mViewPager.endFakeDrag();
                    break;
                }
                break;
            case 2:
                float x2 = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId));
                float f5 = x2 - this.mLastMotionX;
                if (!this.mIsDragging && Math.abs(f5) > ((float) this.mTouchSlop)) {
                    this.mIsDragging = true;
                }
                if (this.mIsDragging) {
                    this.mLastMotionX = x2;
                    if (this.mViewPager.isFakeDragging() || this.mViewPager.beginFakeDrag()) {
                        this.mViewPager.fakeDragBy(f5);
                        break;
                    }
                }
                break;
            case 5:
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                this.mLastMotionX = MotionEventCompat.getX(motionEvent, actionIndex);
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                break;
            case 6:
                int actionIndex2 = MotionEventCompat.getActionIndex(motionEvent);
                if (MotionEventCompat.getPointerId(motionEvent, actionIndex2) == this.mActivePointerId) {
                    if (actionIndex2 == 0) {
                        i = 1;
                    }
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, i);
                }
                this.mLastMotionX = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId));
                break;
        }
        return true;
    }

    private void clipViewOnTheRight(Rect rect, float f, int i) {
        rect.right = (int) (((float) i) - this.mClipPadding);
        rect.left = (int) (((float) rect.right) - f);
    }

    private void clipViewOnTheLeft(Rect rect, float f, int i) {
        rect.left = (int) (((float) i) + this.mClipPadding);
        rect.right = (int) (this.mClipPadding + f);
    }

    private ArrayList<Rect> calculateAllBounds(Paint paint) {
        ArrayList<Rect> arrayList = new ArrayList<>();
        int count = this.mViewPager.getAdapter().getCount();
        int width = getWidth();
        int i = width / 2;
        for (int i2 = 0; i2 < count; i2++) {
            Rect calcBounds = calcBounds(i2, paint);
            int i3 = calcBounds.right - calcBounds.left;
            int i4 = calcBounds.bottom - calcBounds.top;
            calcBounds.left = (int) ((((float) i) - (((float) i3) / 2.0f)) + ((((float) (i2 - this.mCurrentPage)) - this.mPageOffset) * ((float) width)));
            calcBounds.right = calcBounds.left + i3;
            calcBounds.top = 0;
            calcBounds.bottom = i4;
            arrayList.add(calcBounds);
        }
        return arrayList;
    }

    private Rect calcBounds(int i, Paint paint) {
        Rect rect = new Rect();
        CharSequence title = getTitle(i);
        rect.right = (int) paint.measureText(title, 0, title.length());
        rect.bottom = (int) (paint.descent() - paint.ascent());
        return rect;
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.mViewPager != viewPager) {
            if (this.mViewPager != null) {
                this.mViewPager.setOnPageChangeListener(null);
            }
            if (viewPager.getAdapter() == null) {
                throw new IllegalStateException("ViewPager does not have adapter instance.");
            }
            this.mViewPager = viewPager;
            this.mViewPager.setOnPageChangeListener(this);
            invalidate();
        }
    }

    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }

    public void notifyDataSetChanged() {
        invalidate();
    }

    public void setOnCenterItemClickListener(OnCenterItemClickListener onCenterItemClickListener) {
        this.mCenterItemClickListener = onCenterItemClickListener;
    }

    public void setCurrentItem(int i) {
        if (this.mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.mViewPager.setCurrentItem(i);
        this.mCurrentPage = i;
        invalidate();
    }

    public void onPageScrollStateChanged(int i) {
        this.mScrollState = i;
        if (this.mListener != null) {
            this.mListener.onPageScrollStateChanged(i);
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        this.mCurrentPage = i;
        this.mPageOffset = f;
        invalidate();
        if (this.mListener != null) {
            this.mListener.onPageScrolled(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        if (this.mScrollState == 0) {
            this.mCurrentPage = i;
            invalidate();
        }
        if (this.mListener != null) {
            this.mListener.onPageSelected(i);
        }
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.mListener = onPageChangeListener;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        float f;
        int size = MeasureSpec.getSize(i);
        if (MeasureSpec.getMode(i2) == 1073741824) {
            f = (float) MeasureSpec.getSize(i2);
        } else {
            this.mBounds.setEmpty();
            this.mBounds.bottom = (int) (this.mPaintText.descent() - this.mPaintText.ascent());
            f = ((float) (this.mBounds.bottom - this.mBounds.top)) + this.mFooterLineHeight + this.mFooterPadding + this.mTopPadding;
            if (this.mFooterIndicatorStyle != IndicatorStyle.None) {
                f += this.mFooterIndicatorHeight;
            }
        }
        setMeasuredDimension(size, (int) f);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mCurrentPage = savedState.currentPage;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.currentPage = this.mCurrentPage;
        return savedState;
    }

    private CharSequence getTitle(int i) {
        CharSequence pageTitle = this.mViewPager.getAdapter().getPageTitle(i);
        return pageTitle == null ? "" : pageTitle;
    }
}
