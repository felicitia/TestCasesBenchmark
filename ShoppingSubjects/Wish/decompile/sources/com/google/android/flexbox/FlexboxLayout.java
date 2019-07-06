package com.google.android.flexbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.ArrayList;
import java.util.List;

public class FlexboxLayout extends ViewGroup implements FlexContainer {
    private int mAlignContent;
    private int mAlignItems;
    private Drawable mDividerDrawableHorizontal;
    private Drawable mDividerDrawableVertical;
    private int mDividerHorizontalHeight;
    private int mDividerVerticalWidth;
    private int mFlexDirection;
    private List<FlexLine> mFlexLines;
    private FlexLinesResult mFlexLinesResult;
    private int mFlexWrap;
    private FlexboxHelper mFlexboxHelper;
    private int mJustifyContent;
    private SparseIntArray mOrderCache;
    private int[] mReorderedIndices;
    private int mShowDividerHorizontal;
    private int mShowDividerVertical;

    public static class LayoutParams extends MarginLayoutParams implements FlexItem {
        public static final Creator<LayoutParams> CREATOR = new Creator<LayoutParams>() {
            public LayoutParams createFromParcel(Parcel parcel) {
                return new LayoutParams(parcel);
            }

            public LayoutParams[] newArray(int i) {
                return new LayoutParams[i];
            }
        };
        private int mAlignSelf = -1;
        private float mFlexBasisPercent = -1.0f;
        private float mFlexGrow = 0.0f;
        private float mFlexShrink = 1.0f;
        private int mMaxHeight = 16777215;
        private int mMaxWidth = 16777215;
        private int mMinHeight;
        private int mMinWidth;
        private int mOrder = 1;
        private boolean mWrapBefore;

        public int describeContents() {
            return 0;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FlexboxLayout_Layout);
            this.mOrder = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_Layout_layout_order, 1);
            this.mFlexGrow = obtainStyledAttributes.getFloat(R.styleable.FlexboxLayout_Layout_layout_flexGrow, 0.0f);
            this.mFlexShrink = obtainStyledAttributes.getFloat(R.styleable.FlexboxLayout_Layout_layout_flexShrink, 1.0f);
            this.mAlignSelf = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_Layout_layout_alignSelf, -1);
            this.mFlexBasisPercent = obtainStyledAttributes.getFraction(R.styleable.FlexboxLayout_Layout_layout_flexBasisPercent, 1, 1, -1.0f);
            this.mMinWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_minWidth, 0);
            this.mMinHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_minHeight, 0);
            this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_maxWidth, 16777215);
            this.mMaxHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_maxHeight, 16777215);
            this.mWrapBefore = obtainStyledAttributes.getBoolean(R.styleable.FlexboxLayout_Layout_layout_wrapBefore, false);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.mOrder = layoutParams.mOrder;
            this.mFlexGrow = layoutParams.mFlexGrow;
            this.mFlexShrink = layoutParams.mFlexShrink;
            this.mAlignSelf = layoutParams.mAlignSelf;
            this.mFlexBasisPercent = layoutParams.mFlexBasisPercent;
            this.mMinWidth = layoutParams.mMinWidth;
            this.mMinHeight = layoutParams.mMinHeight;
            this.mMaxWidth = layoutParams.mMaxWidth;
            this.mMaxHeight = layoutParams.mMaxHeight;
            this.mWrapBefore = layoutParams.mWrapBefore;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(int i, int i2) {
            super(new android.view.ViewGroup.LayoutParams(i, i2));
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }

        public int getOrder() {
            return this.mOrder;
        }

        public float getFlexGrow() {
            return this.mFlexGrow;
        }

        public float getFlexShrink() {
            return this.mFlexShrink;
        }

        public int getAlignSelf() {
            return this.mAlignSelf;
        }

        public int getMinWidth() {
            return this.mMinWidth;
        }

        public int getMinHeight() {
            return this.mMinHeight;
        }

        public int getMaxWidth() {
            return this.mMaxWidth;
        }

        public int getMaxHeight() {
            return this.mMaxHeight;
        }

        public boolean isWrapBefore() {
            return this.mWrapBefore;
        }

        public float getFlexBasisPercent() {
            return this.mFlexBasisPercent;
        }

        public int getMarginLeft() {
            return this.leftMargin;
        }

        public int getMarginTop() {
            return this.topMargin;
        }

        public int getMarginRight() {
            return this.rightMargin;
        }

        public int getMarginBottom() {
            return this.bottomMargin;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mOrder);
            parcel.writeFloat(this.mFlexGrow);
            parcel.writeFloat(this.mFlexShrink);
            parcel.writeInt(this.mAlignSelf);
            parcel.writeFloat(this.mFlexBasisPercent);
            parcel.writeInt(this.mMinWidth);
            parcel.writeInt(this.mMinHeight);
            parcel.writeInt(this.mMaxWidth);
            parcel.writeInt(this.mMaxHeight);
            parcel.writeByte(this.mWrapBefore ? (byte) 1 : 0);
            parcel.writeInt(this.bottomMargin);
            parcel.writeInt(this.leftMargin);
            parcel.writeInt(this.rightMargin);
            parcel.writeInt(this.topMargin);
            parcel.writeInt(this.height);
            parcel.writeInt(this.width);
        }

        protected LayoutParams(Parcel parcel) {
            boolean z = false;
            super(0, 0);
            this.mOrder = parcel.readInt();
            this.mFlexGrow = parcel.readFloat();
            this.mFlexShrink = parcel.readFloat();
            this.mAlignSelf = parcel.readInt();
            this.mFlexBasisPercent = parcel.readFloat();
            this.mMinWidth = parcel.readInt();
            this.mMinHeight = parcel.readInt();
            this.mMaxWidth = parcel.readInt();
            this.mMaxHeight = parcel.readInt();
            if (parcel.readByte() != 0) {
                z = true;
            }
            this.mWrapBefore = z;
            this.bottomMargin = parcel.readInt();
            this.leftMargin = parcel.readInt();
            this.rightMargin = parcel.readInt();
            this.topMargin = parcel.readInt();
            this.height = parcel.readInt();
            this.width = parcel.readInt();
        }
    }

    public int getDecorationLengthCrossAxis(View view) {
        return 0;
    }

    public void updateViewCache(int i, View view) {
    }

    public FlexboxLayout(Context context) {
        this(context, null);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mFlexboxHelper = new FlexboxHelper(this);
        this.mFlexLines = new ArrayList();
        this.mFlexLinesResult = new FlexLinesResult();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FlexboxLayout, i, 0);
        this.mFlexDirection = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_flexDirection, 0);
        this.mFlexWrap = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_flexWrap, 0);
        this.mJustifyContent = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_justifyContent, 0);
        this.mAlignItems = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_alignItems, 4);
        this.mAlignContent = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_alignContent, 5);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.FlexboxLayout_dividerDrawable);
        if (drawable != null) {
            setDividerDrawableHorizontal(drawable);
            setDividerDrawableVertical(drawable);
        }
        Drawable drawable2 = obtainStyledAttributes.getDrawable(R.styleable.FlexboxLayout_dividerDrawableHorizontal);
        if (drawable2 != null) {
            setDividerDrawableHorizontal(drawable2);
        }
        Drawable drawable3 = obtainStyledAttributes.getDrawable(R.styleable.FlexboxLayout_dividerDrawableVertical);
        if (drawable3 != null) {
            setDividerDrawableVertical(drawable3);
        }
        int i2 = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_showDivider, 0);
        if (i2 != 0) {
            this.mShowDividerVertical = i2;
            this.mShowDividerHorizontal = i2;
        }
        int i3 = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_showDividerVertical, 0);
        if (i3 != 0) {
            this.mShowDividerVertical = i3;
        }
        int i4 = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_showDividerHorizontal, 0);
        if (i4 != 0) {
            this.mShowDividerHorizontal = i4;
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mOrderCache == null) {
            this.mOrderCache = new SparseIntArray(getChildCount());
        }
        if (this.mFlexboxHelper.isOrderChangedFromLastMeasurement(this.mOrderCache)) {
            this.mReorderedIndices = this.mFlexboxHelper.createReorderedIndices(this.mOrderCache);
        }
        switch (this.mFlexDirection) {
            case 0:
            case 1:
                measureHorizontal(i, i2);
                return;
            case 2:
            case 3:
                measureVertical(i, i2);
                return;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid value for the flex direction is set: ");
                sb.append(this.mFlexDirection);
                throw new IllegalStateException(sb.toString());
        }
    }

    public int getFlexItemCount() {
        return getChildCount();
    }

    public View getFlexItemAt(int i) {
        return getChildAt(i);
    }

    public View getReorderedChildAt(int i) {
        if (i < 0 || i >= this.mReorderedIndices.length) {
            return null;
        }
        return getChildAt(this.mReorderedIndices[i]);
    }

    public View getReorderedFlexItemAt(int i) {
        return getReorderedChildAt(i);
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
        if (this.mOrderCache == null) {
            this.mOrderCache = new SparseIntArray(getChildCount());
        }
        this.mReorderedIndices = this.mFlexboxHelper.createReorderedIndices(view, i, layoutParams, this.mOrderCache);
        super.addView(view, i, layoutParams);
    }

    private void measureHorizontal(int i, int i2) {
        this.mFlexLines.clear();
        this.mFlexLinesResult.reset();
        this.mFlexboxHelper.calculateHorizontalFlexLines(this.mFlexLinesResult, i, i2);
        this.mFlexLines = this.mFlexLinesResult.mFlexLines;
        this.mFlexboxHelper.determineMainSize(i, i2);
        if (this.mAlignItems == 3) {
            for (FlexLine flexLine : this.mFlexLines) {
                int i3 = Integer.MIN_VALUE;
                for (int i4 = 0; i4 < flexLine.mItemCount; i4++) {
                    View reorderedChildAt = getReorderedChildAt(flexLine.mFirstIndex + i4);
                    if (!(reorderedChildAt == null || reorderedChildAt.getVisibility() == 8)) {
                        LayoutParams layoutParams = (LayoutParams) reorderedChildAt.getLayoutParams();
                        if (this.mFlexWrap != 2) {
                            i3 = Math.max(i3, reorderedChildAt.getMeasuredHeight() + Math.max(flexLine.mMaxBaseline - reorderedChildAt.getBaseline(), layoutParams.topMargin) + layoutParams.bottomMargin);
                        } else {
                            i3 = Math.max(i3, reorderedChildAt.getMeasuredHeight() + layoutParams.topMargin + Math.max((flexLine.mMaxBaseline - reorderedChildAt.getMeasuredHeight()) + reorderedChildAt.getBaseline(), layoutParams.bottomMargin));
                        }
                    }
                }
                flexLine.mCrossSize = i3;
            }
        }
        this.mFlexboxHelper.determineCrossSize(i, i2, getPaddingTop() + getPaddingBottom());
        this.mFlexboxHelper.stretchViews();
        setMeasuredDimensionForFlex(this.mFlexDirection, i, i2, this.mFlexLinesResult.mChildState);
    }

    private void measureVertical(int i, int i2) {
        this.mFlexLines.clear();
        this.mFlexLinesResult.reset();
        this.mFlexboxHelper.calculateVerticalFlexLines(this.mFlexLinesResult, i, i2);
        this.mFlexLines = this.mFlexLinesResult.mFlexLines;
        this.mFlexboxHelper.determineMainSize(i, i2);
        this.mFlexboxHelper.determineCrossSize(i, i2, getPaddingLeft() + getPaddingRight());
        this.mFlexboxHelper.stretchViews();
        setMeasuredDimensionForFlex(this.mFlexDirection, i, i2, this.mFlexLinesResult.mChildState);
    }

    private void setMeasuredDimensionForFlex(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i3);
        int size2 = MeasureSpec.getSize(i3);
        switch (i) {
            case 0:
            case 1:
                i5 = getSumOfCrossSize() + getPaddingTop() + getPaddingBottom();
                i6 = getLargestMainSize();
                break;
            case 2:
            case 3:
                i5 = getLargestMainSize();
                i6 = getSumOfCrossSize() + getPaddingLeft() + getPaddingRight();
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid flex direction: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
        if (mode == Integer.MIN_VALUE) {
            if (size < i6) {
                i4 = View.combineMeasuredStates(i4, 16777216);
            } else {
                size = i6;
            }
            i7 = View.resolveSizeAndState(size, i2, i4);
        } else if (mode == 0) {
            i7 = View.resolveSizeAndState(i6, i2, i4);
        } else if (mode != 1073741824) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unknown width mode is set: ");
            sb2.append(mode);
            throw new IllegalStateException(sb2.toString());
        } else {
            if (size < i6) {
                i4 = View.combineMeasuredStates(i4, 16777216);
            }
            i7 = View.resolveSizeAndState(size, i2, i4);
        }
        if (mode2 == Integer.MIN_VALUE) {
            if (size2 < i5) {
                i4 = View.combineMeasuredStates(i4, 256);
                i5 = size2;
            }
            i8 = View.resolveSizeAndState(i5, i3, i4);
        } else if (mode2 == 0) {
            i8 = View.resolveSizeAndState(i5, i3, i4);
        } else if (mode2 != 1073741824) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Unknown height mode is set: ");
            sb3.append(mode2);
            throw new IllegalStateException(sb3.toString());
        } else {
            if (size2 < i5) {
                i4 = View.combineMeasuredStates(i4, 256);
            }
            i8 = View.resolveSizeAndState(size2, i3, i4);
        }
        setMeasuredDimension(i7, i8);
    }

    public int getLargestMainSize() {
        int i = Integer.MIN_VALUE;
        for (FlexLine flexLine : this.mFlexLines) {
            i = Math.max(i, flexLine.mMainSize);
        }
        return i;
    }

    public int getSumOfCrossSize() {
        int size = this.mFlexLines.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(i2);
            if (hasDividerBeforeFlexLine(i2)) {
                if (isMainAxisDirectionHorizontal()) {
                    i += this.mDividerHorizontalHeight;
                } else {
                    i += this.mDividerVerticalWidth;
                }
            }
            if (hasEndDividerAfterFlexLine(i2)) {
                if (isMainAxisDirectionHorizontal()) {
                    i += this.mDividerHorizontalHeight;
                } else {
                    i += this.mDividerVerticalWidth;
                }
            }
            i += flexLine.mCrossSize;
        }
        return i;
    }

    public boolean isMainAxisDirectionHorizontal() {
        return this.mFlexDirection == 0 || this.mFlexDirection == 1;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        boolean z2 = false;
        switch (this.mFlexDirection) {
            case 0:
                layoutHorizontal(layoutDirection == 1, i, i2, i3, i4);
                return;
            case 1:
                layoutHorizontal(layoutDirection != 1, i, i2, i3, i4);
                return;
            case 2:
                if (layoutDirection == 1) {
                    z2 = true;
                }
                layoutVertical(this.mFlexWrap == 2 ? !z2 : z2, false, i, i2, i3, i4);
                return;
            case 3:
                if (layoutDirection == 1) {
                    z2 = true;
                }
                layoutVertical(this.mFlexWrap == 2 ? !z2 : z2, true, i, i2, i3, i4);
                return;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid flex direction is set: ");
                sb.append(this.mFlexDirection);
                throw new IllegalStateException(sb.toString());
        }
    }

    private void layoutHorizontal(boolean z, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        float f4;
        float f5;
        View view;
        float f6;
        float f7;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int i11 = i3 - i;
        int paddingBottom = (i4 - i2) - getPaddingBottom();
        int paddingTop = getPaddingTop();
        int size = this.mFlexLines.size();
        int i12 = paddingTop;
        int i13 = paddingBottom;
        int i14 = 0;
        while (i14 < size) {
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(i14);
            if (hasDividerBeforeFlexLine(i14)) {
                i13 -= this.mDividerHorizontalHeight;
                i12 += this.mDividerHorizontalHeight;
            }
            switch (this.mJustifyContent) {
                case 0:
                    f = (float) paddingLeft;
                    f7 = (float) (i11 - paddingRight);
                    break;
                case 1:
                    f = (float) ((i11 - flexLine.mMainSize) + paddingRight);
                    f7 = (float) (flexLine.mMainSize - paddingLeft);
                    break;
                case 2:
                    f = ((float) paddingLeft) + (((float) (i11 - flexLine.mMainSize)) / 2.0f);
                    f7 = ((float) (i11 - paddingRight)) - (((float) (i11 - flexLine.mMainSize)) / 2.0f);
                    break;
                case 3:
                    f = (float) paddingLeft;
                    int itemCountNotGone = flexLine.getItemCountNotGone();
                    f3 = ((float) (i11 - flexLine.mMainSize)) / (itemCountNotGone != 1 ? (float) (itemCountNotGone - 1) : 1.0f);
                    f2 = (float) (i11 - paddingRight);
                    break;
                case 4:
                    int itemCountNotGone2 = flexLine.getItemCountNotGone();
                    float f8 = itemCountNotGone2 != 0 ? ((float) (i11 - flexLine.mMainSize)) / ((float) itemCountNotGone2) : 0.0f;
                    float f9 = f8 / 2.0f;
                    f = ((float) paddingLeft) + f9;
                    float f10 = f8;
                    f2 = ((float) (i11 - paddingRight)) - f9;
                    f3 = f10;
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid justifyContent is set: ");
                    sb.append(this.mJustifyContent);
                    throw new IllegalStateException(sb.toString());
            }
            f2 = f7;
            f3 = 0.0f;
            float max = Math.max(f3, 0.0f);
            float f11 = f2;
            float f12 = f;
            int i15 = 0;
            while (i15 < flexLine.mItemCount) {
                int i16 = flexLine.mFirstIndex + i15;
                View reorderedChildAt = getReorderedChildAt(i16);
                if (reorderedChildAt != null) {
                    i9 = paddingLeft;
                    i8 = paddingRight;
                    if (reorderedChildAt.getVisibility() != 8) {
                        LayoutParams layoutParams = (LayoutParams) reorderedChildAt.getLayoutParams();
                        float f13 = f12 + ((float) layoutParams.leftMargin);
                        float f14 = f11 - ((float) layoutParams.rightMargin);
                        if (hasDividerBeforeChildAtAlongMainAxis(i16, i15)) {
                            int i17 = this.mDividerVerticalWidth;
                            float f15 = (float) i17;
                            float f16 = f14 - f15;
                            i10 = i17;
                            f4 = f13 + f15;
                            f5 = f16;
                        } else {
                            f4 = f13;
                            f5 = f14;
                            i10 = 0;
                        }
                        int i18 = (i15 != flexLine.mItemCount + -1 || (this.mShowDividerVertical & 4) <= 0) ? 0 : this.mDividerVerticalWidth;
                        if (this.mFlexWrap != 2) {
                            i7 = i11;
                            i5 = size;
                            f6 = f4;
                            i6 = i15;
                            view = reorderedChildAt;
                            if (z) {
                                this.mFlexboxHelper.layoutSingleChildHorizontal(view, flexLine, Math.round(f5) - view.getMeasuredWidth(), i12, Math.round(f5), i12 + view.getMeasuredHeight());
                            } else {
                                this.mFlexboxHelper.layoutSingleChildHorizontal(view, flexLine, Math.round(f6), i12, Math.round(f6) + view.getMeasuredWidth(), i12 + view.getMeasuredHeight());
                            }
                        } else if (z) {
                            i7 = i11;
                            f6 = f4;
                            i6 = i15;
                            i5 = size;
                            view = reorderedChildAt;
                            this.mFlexboxHelper.layoutSingleChildHorizontal(reorderedChildAt, flexLine, Math.round(f5) - reorderedChildAt.getMeasuredWidth(), i13 - reorderedChildAt.getMeasuredHeight(), Math.round(f5), i13);
                        } else {
                            i7 = i11;
                            i5 = size;
                            f6 = f4;
                            i6 = i15;
                            view = reorderedChildAt;
                            this.mFlexboxHelper.layoutSingleChildHorizontal(view, flexLine, Math.round(f6), i13 - view.getMeasuredHeight(), Math.round(f6) + view.getMeasuredWidth(), i13);
                        }
                        float measuredWidth = f6 + ((float) view.getMeasuredWidth()) + max + ((float) layoutParams.rightMargin);
                        float measuredWidth2 = f5 - ((((float) view.getMeasuredWidth()) + max) + ((float) layoutParams.leftMargin));
                        if (z) {
                            flexLine.updatePositionFromView(view, i18, 0, i10, 0);
                        } else {
                            flexLine.updatePositionFromView(view, i10, 0, i18, 0);
                        }
                        f11 = measuredWidth2;
                        f12 = measuredWidth;
                        i15 = i6 + 1;
                        paddingLeft = i9;
                        paddingRight = i8;
                        i11 = i7;
                        size = i5;
                    }
                } else {
                    i9 = paddingLeft;
                    i8 = paddingRight;
                }
                i7 = i11;
                i5 = size;
                i6 = i15;
                i15 = i6 + 1;
                paddingLeft = i9;
                paddingRight = i8;
                i11 = i7;
                size = i5;
            }
            int i19 = paddingRight;
            int i20 = i11;
            int i21 = size;
            i12 += flexLine.mCrossSize;
            i13 -= flexLine.mCrossSize;
            i14++;
            paddingLeft = paddingLeft;
        }
    }

    private void layoutVertical(boolean z, boolean z2, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        float f4;
        float f5;
        View view;
        float f6;
        float f7;
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingRight = getPaddingRight();
        int paddingLeft = getPaddingLeft();
        int i10 = i4 - i2;
        int i11 = (i3 - i) - paddingRight;
        int size = this.mFlexLines.size();
        int i12 = i11;
        int i13 = paddingLeft;
        int i14 = 0;
        while (i14 < size) {
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(i14);
            if (hasDividerBeforeFlexLine(i14)) {
                i13 += this.mDividerVerticalWidth;
                i12 -= this.mDividerVerticalWidth;
            }
            switch (this.mJustifyContent) {
                case 0:
                    f = (float) paddingTop;
                    f7 = (float) (i10 - paddingBottom);
                    break;
                case 1:
                    f = (float) ((i10 - flexLine.mMainSize) + paddingBottom);
                    f7 = (float) (flexLine.mMainSize - paddingTop);
                    break;
                case 2:
                    f = ((float) paddingTop) + (((float) (i10 - flexLine.mMainSize)) / 2.0f);
                    f7 = ((float) (i10 - paddingBottom)) - (((float) (i10 - flexLine.mMainSize)) / 2.0f);
                    break;
                case 3:
                    f = (float) paddingTop;
                    int itemCountNotGone = flexLine.getItemCountNotGone();
                    f3 = ((float) (i10 - flexLine.mMainSize)) / (itemCountNotGone != 1 ? (float) (itemCountNotGone - 1) : 1.0f);
                    f2 = (float) (i10 - paddingBottom);
                    break;
                case 4:
                    int itemCountNotGone2 = flexLine.getItemCountNotGone();
                    float f8 = itemCountNotGone2 != 0 ? ((float) (i10 - flexLine.mMainSize)) / ((float) itemCountNotGone2) : 0.0f;
                    float f9 = f8 / 2.0f;
                    f = ((float) paddingTop) + f9;
                    float f10 = f8;
                    f2 = ((float) (i10 - paddingBottom)) - f9;
                    f3 = f10;
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid justifyContent is set: ");
                    sb.append(this.mJustifyContent);
                    throw new IllegalStateException(sb.toString());
            }
            f2 = f7;
            f3 = 0.0f;
            float max = Math.max(f3, 0.0f);
            float f11 = f2;
            float f12 = f;
            int i15 = 0;
            while (i15 < flexLine.mItemCount) {
                int i16 = flexLine.mFirstIndex + i15;
                View reorderedChildAt = getReorderedChildAt(i16);
                if (reorderedChildAt != null) {
                    i8 = paddingTop;
                    i7 = paddingBottom;
                    if (reorderedChildAt.getVisibility() != 8) {
                        LayoutParams layoutParams = (LayoutParams) reorderedChildAt.getLayoutParams();
                        float f13 = f12 + ((float) layoutParams.topMargin);
                        float f14 = f11 - ((float) layoutParams.bottomMargin);
                        if (hasDividerBeforeChildAtAlongMainAxis(i16, i15)) {
                            int i17 = this.mDividerHorizontalHeight;
                            float f15 = (float) i17;
                            float f16 = f14 - f15;
                            i9 = i17;
                            f4 = f13 + f15;
                            f5 = f16;
                        } else {
                            f4 = f13;
                            f5 = f14;
                            i9 = 0;
                        }
                        int i18 = (i15 != flexLine.mItemCount + -1 || (this.mShowDividerHorizontal & 4) <= 0) ? 0 : this.mDividerHorizontalHeight;
                        if (!z) {
                            i6 = size;
                            f6 = f4;
                            i5 = i15;
                            View view2 = reorderedChildAt;
                            if (z2) {
                                View view3 = view2;
                                View view4 = view3;
                                this.mFlexboxHelper.layoutSingleChildVertical(view3, flexLine, false, i13, Math.round(f5) - view3.getMeasuredHeight(), i13 + view3.getMeasuredWidth(), Math.round(f5));
                                view = view4;
                            } else {
                                View view5 = view2;
                                View view6 = view5;
                                this.mFlexboxHelper.layoutSingleChildVertical(view5, flexLine, false, i13, Math.round(f6), i13 + view5.getMeasuredWidth(), Math.round(f6) + view5.getMeasuredHeight());
                                view = view6;
                            }
                        } else if (z2) {
                            i6 = size;
                            f6 = f4;
                            i5 = i15;
                            View view7 = reorderedChildAt;
                            this.mFlexboxHelper.layoutSingleChildVertical(reorderedChildAt, flexLine, true, i12 - reorderedChildAt.getMeasuredWidth(), Math.round(f5) - reorderedChildAt.getMeasuredHeight(), i12, Math.round(f5));
                            view = view7;
                        } else {
                            i6 = size;
                            f6 = f4;
                            i5 = i15;
                            View view8 = reorderedChildAt;
                            View view9 = reorderedChildAt;
                            this.mFlexboxHelper.layoutSingleChildVertical(reorderedChildAt, flexLine, true, i12 - reorderedChildAt.getMeasuredWidth(), Math.round(f6), i12, Math.round(f6) + reorderedChildAt.getMeasuredHeight());
                            view = view9;
                        }
                        float measuredHeight = f6 + ((float) view.getMeasuredHeight()) + max + ((float) layoutParams.bottomMargin);
                        float measuredHeight2 = f5 - ((((float) view.getMeasuredHeight()) + max) + ((float) layoutParams.topMargin));
                        if (z2) {
                            flexLine.updatePositionFromView(view, 0, i18, 0, i9);
                        } else {
                            flexLine.updatePositionFromView(view, 0, i9, 0, i18);
                        }
                        f11 = measuredHeight2;
                        f12 = measuredHeight;
                        i15 = i5 + 1;
                        paddingTop = i8;
                        paddingBottom = i7;
                        size = i6;
                    }
                } else {
                    i8 = paddingTop;
                    i7 = paddingBottom;
                }
                i6 = size;
                i5 = i15;
                i15 = i5 + 1;
                paddingTop = i8;
                paddingBottom = i7;
                size = i6;
            }
            int i19 = paddingBottom;
            int i20 = size;
            i13 += flexLine.mCrossSize;
            i12 -= flexLine.mCrossSize;
            i14++;
            paddingTop = paddingTop;
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mDividerDrawableVertical != null || this.mDividerDrawableHorizontal != null) {
            if (this.mShowDividerHorizontal != 0 || this.mShowDividerVertical != 0) {
                int layoutDirection = ViewCompat.getLayoutDirection(this);
                boolean z = false;
                boolean z2 = true;
                switch (this.mFlexDirection) {
                    case 0:
                        boolean z3 = layoutDirection == 1;
                        if (this.mFlexWrap == 2) {
                            z = true;
                        }
                        drawDividersHorizontal(canvas, z3, z);
                        break;
                    case 1:
                        boolean z4 = layoutDirection != 1;
                        if (this.mFlexWrap == 2) {
                            z = true;
                        }
                        drawDividersHorizontal(canvas, z4, z);
                        break;
                    case 2:
                        if (layoutDirection != 1) {
                            z2 = false;
                        }
                        if (this.mFlexWrap == 2) {
                            z2 = !z2;
                        }
                        drawDividersVertical(canvas, z2, false);
                        break;
                    case 3:
                        if (layoutDirection == 1) {
                            z = true;
                        }
                        if (this.mFlexWrap == 2) {
                            z = !z;
                        }
                        drawDividersVertical(canvas, z, true);
                        break;
                }
            }
        }
    }

    private void drawDividersHorizontal(Canvas canvas, boolean z, boolean z2) {
        int i;
        int i2;
        int i3;
        int i4;
        int paddingLeft = getPaddingLeft();
        int max = Math.max(0, (getWidth() - getPaddingRight()) - paddingLeft);
        int size = this.mFlexLines.size();
        for (int i5 = 0; i5 < size; i5++) {
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(i5);
            for (int i6 = 0; i6 < flexLine.mItemCount; i6++) {
                int i7 = flexLine.mFirstIndex + i6;
                View reorderedChildAt = getReorderedChildAt(i7);
                if (!(reorderedChildAt == null || reorderedChildAt.getVisibility() == 8)) {
                    LayoutParams layoutParams = (LayoutParams) reorderedChildAt.getLayoutParams();
                    if (hasDividerBeforeChildAtAlongMainAxis(i7, i6)) {
                        if (z) {
                            i4 = reorderedChildAt.getRight() + layoutParams.rightMargin;
                        } else {
                            i4 = (reorderedChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerVerticalWidth;
                        }
                        drawVerticalDivider(canvas, i4, flexLine.mTop, flexLine.mCrossSize);
                    }
                    if (i6 == flexLine.mItemCount - 1 && (this.mShowDividerVertical & 4) > 0) {
                        if (z) {
                            i3 = (reorderedChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerVerticalWidth;
                        } else {
                            i3 = reorderedChildAt.getRight() + layoutParams.rightMargin;
                        }
                        drawVerticalDivider(canvas, i3, flexLine.mTop, flexLine.mCrossSize);
                    }
                }
            }
            if (hasDividerBeforeFlexLine(i5)) {
                if (z2) {
                    i2 = flexLine.mBottom;
                } else {
                    i2 = flexLine.mTop - this.mDividerHorizontalHeight;
                }
                drawHorizontalDivider(canvas, paddingLeft, i2, max);
            }
            if (hasEndDividerAfterFlexLine(i5) && (this.mShowDividerHorizontal & 4) > 0) {
                if (z2) {
                    i = flexLine.mTop - this.mDividerHorizontalHeight;
                } else {
                    i = flexLine.mBottom;
                }
                drawHorizontalDivider(canvas, paddingLeft, i, max);
            }
        }
    }

    private void drawDividersVertical(Canvas canvas, boolean z, boolean z2) {
        int i;
        int i2;
        int i3;
        int i4;
        int paddingTop = getPaddingTop();
        int max = Math.max(0, (getHeight() - getPaddingBottom()) - paddingTop);
        int size = this.mFlexLines.size();
        for (int i5 = 0; i5 < size; i5++) {
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(i5);
            for (int i6 = 0; i6 < flexLine.mItemCount; i6++) {
                int i7 = flexLine.mFirstIndex + i6;
                View reorderedChildAt = getReorderedChildAt(i7);
                if (!(reorderedChildAt == null || reorderedChildAt.getVisibility() == 8)) {
                    LayoutParams layoutParams = (LayoutParams) reorderedChildAt.getLayoutParams();
                    if (hasDividerBeforeChildAtAlongMainAxis(i7, i6)) {
                        if (z2) {
                            i4 = reorderedChildAt.getBottom() + layoutParams.bottomMargin;
                        } else {
                            i4 = (reorderedChildAt.getTop() - layoutParams.topMargin) - this.mDividerHorizontalHeight;
                        }
                        drawHorizontalDivider(canvas, flexLine.mLeft, i4, flexLine.mCrossSize);
                    }
                    if (i6 == flexLine.mItemCount - 1 && (this.mShowDividerHorizontal & 4) > 0) {
                        if (z2) {
                            i3 = (reorderedChildAt.getTop() - layoutParams.topMargin) - this.mDividerHorizontalHeight;
                        } else {
                            i3 = reorderedChildAt.getBottom() + layoutParams.bottomMargin;
                        }
                        drawHorizontalDivider(canvas, flexLine.mLeft, i3, flexLine.mCrossSize);
                    }
                }
            }
            if (hasDividerBeforeFlexLine(i5)) {
                if (z) {
                    i2 = flexLine.mRight;
                } else {
                    i2 = flexLine.mLeft - this.mDividerVerticalWidth;
                }
                drawVerticalDivider(canvas, i2, paddingTop, max);
            }
            if (hasEndDividerAfterFlexLine(i5) && (this.mShowDividerVertical & 4) > 0) {
                if (z) {
                    i = flexLine.mLeft - this.mDividerVerticalWidth;
                } else {
                    i = flexLine.mRight;
                }
                drawVerticalDivider(canvas, i, paddingTop, max);
            }
        }
    }

    private void drawVerticalDivider(Canvas canvas, int i, int i2, int i3) {
        if (this.mDividerDrawableVertical != null) {
            this.mDividerDrawableVertical.setBounds(i, i2, this.mDividerVerticalWidth + i, i3 + i2);
            this.mDividerDrawableVertical.draw(canvas);
        }
    }

    private void drawHorizontalDivider(Canvas canvas, int i, int i2, int i3) {
        if (this.mDividerDrawableHorizontal != null) {
            this.mDividerDrawableHorizontal.setBounds(i, i2, i3 + i, this.mDividerHorizontalHeight + i2);
            this.mDividerDrawableHorizontal.draw(canvas);
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public int getFlexDirection() {
        return this.mFlexDirection;
    }

    public void setFlexDirection(int i) {
        if (this.mFlexDirection != i) {
            this.mFlexDirection = i;
            requestLayout();
        }
    }

    public int getFlexWrap() {
        return this.mFlexWrap;
    }

    public void setFlexWrap(int i) {
        if (this.mFlexWrap != i) {
            this.mFlexWrap = i;
            requestLayout();
        }
    }

    public int getJustifyContent() {
        return this.mJustifyContent;
    }

    public void setJustifyContent(int i) {
        if (this.mJustifyContent != i) {
            this.mJustifyContent = i;
            requestLayout();
        }
    }

    public int getAlignItems() {
        return this.mAlignItems;
    }

    public void setAlignItems(int i) {
        if (this.mAlignItems != i) {
            this.mAlignItems = i;
            requestLayout();
        }
    }

    public int getAlignContent() {
        return this.mAlignContent;
    }

    public void setAlignContent(int i) {
        if (this.mAlignContent != i) {
            this.mAlignContent = i;
            requestLayout();
        }
    }

    public List<FlexLine> getFlexLines() {
        ArrayList arrayList = new ArrayList(this.mFlexLines.size());
        for (FlexLine flexLine : this.mFlexLines) {
            if (flexLine.getItemCountNotGone() != 0) {
                arrayList.add(flexLine);
            }
        }
        return arrayList;
    }

    public int getDecorationLengthMainAxis(View view, int i, int i2) {
        int i3 = 0;
        if (isMainAxisDirectionHorizontal()) {
            if (hasDividerBeforeChildAtAlongMainAxis(i, i2)) {
                i3 = 0 + this.mDividerVerticalWidth;
            }
            if ((this.mShowDividerVertical & 4) > 0) {
                return i3 + this.mDividerVerticalWidth;
            }
            return i3;
        }
        if (hasDividerBeforeChildAtAlongMainAxis(i, i2)) {
            i3 = 0 + this.mDividerHorizontalHeight;
        }
        return (this.mShowDividerHorizontal & 4) > 0 ? i3 + this.mDividerHorizontalHeight : i3;
    }

    public void onNewFlexLineAdded(FlexLine flexLine) {
        if (isMainAxisDirectionHorizontal()) {
            if ((this.mShowDividerVertical & 4) > 0) {
                flexLine.mMainSize += this.mDividerVerticalWidth;
                flexLine.mDividerLengthInMainSize += this.mDividerVerticalWidth;
            }
        } else if ((this.mShowDividerHorizontal & 4) > 0) {
            flexLine.mMainSize += this.mDividerHorizontalHeight;
            flexLine.mDividerLengthInMainSize += this.mDividerHorizontalHeight;
        }
    }

    public int getChildWidthMeasureSpec(int i, int i2, int i3) {
        return getChildMeasureSpec(i, i2, i3);
    }

    public int getChildHeightMeasureSpec(int i, int i2, int i3) {
        return getChildMeasureSpec(i, i2, i3);
    }

    public void onNewFlexItemAdded(View view, int i, int i2, FlexLine flexLine) {
        if (!hasDividerBeforeChildAtAlongMainAxis(i, i2)) {
            return;
        }
        if (isMainAxisDirectionHorizontal()) {
            flexLine.mMainSize += this.mDividerVerticalWidth;
            flexLine.mDividerLengthInMainSize += this.mDividerVerticalWidth;
            return;
        }
        flexLine.mMainSize += this.mDividerHorizontalHeight;
        flexLine.mDividerLengthInMainSize += this.mDividerHorizontalHeight;
    }

    public void setFlexLines(List<FlexLine> list) {
        this.mFlexLines = list;
    }

    public List<FlexLine> getFlexLinesInternal() {
        return this.mFlexLines;
    }

    public Drawable getDividerDrawableHorizontal() {
        return this.mDividerDrawableHorizontal;
    }

    public Drawable getDividerDrawableVertical() {
        return this.mDividerDrawableVertical;
    }

    public void setDividerDrawable(Drawable drawable) {
        setDividerDrawableHorizontal(drawable);
        setDividerDrawableVertical(drawable);
    }

    public void setDividerDrawableHorizontal(Drawable drawable) {
        if (drawable != this.mDividerDrawableHorizontal) {
            this.mDividerDrawableHorizontal = drawable;
            if (drawable != null) {
                this.mDividerHorizontalHeight = drawable.getIntrinsicHeight();
            } else {
                this.mDividerHorizontalHeight = 0;
            }
            setWillNotDrawFlag();
            requestLayout();
        }
    }

    public void setDividerDrawableVertical(Drawable drawable) {
        if (drawable != this.mDividerDrawableVertical) {
            this.mDividerDrawableVertical = drawable;
            if (drawable != null) {
                this.mDividerVerticalWidth = drawable.getIntrinsicWidth();
            } else {
                this.mDividerVerticalWidth = 0;
            }
            setWillNotDrawFlag();
            requestLayout();
        }
    }

    public int getShowDividerVertical() {
        return this.mShowDividerVertical;
    }

    public int getShowDividerHorizontal() {
        return this.mShowDividerHorizontal;
    }

    public void setShowDivider(int i) {
        setShowDividerVertical(i);
        setShowDividerHorizontal(i);
    }

    public void setShowDividerVertical(int i) {
        if (i != this.mShowDividerVertical) {
            this.mShowDividerVertical = i;
            requestLayout();
        }
    }

    public void setShowDividerHorizontal(int i) {
        if (i != this.mShowDividerHorizontal) {
            this.mShowDividerHorizontal = i;
            requestLayout();
        }
    }

    private void setWillNotDrawFlag() {
        if (this.mDividerDrawableHorizontal == null && this.mDividerDrawableVertical == null) {
            setWillNotDraw(true);
        } else {
            setWillNotDraw(false);
        }
    }

    private boolean hasDividerBeforeChildAtAlongMainAxis(int i, int i2) {
        boolean allViewsAreGoneBefore = allViewsAreGoneBefore(i, i2);
        boolean z = false;
        if (allViewsAreGoneBefore) {
            if (isMainAxisDirectionHorizontal()) {
                if ((this.mShowDividerVertical & 1) != 0) {
                    z = true;
                }
                return z;
            }
            if ((this.mShowDividerHorizontal & 1) != 0) {
                z = true;
            }
            return z;
        } else if (isMainAxisDirectionHorizontal()) {
            if ((this.mShowDividerVertical & 2) != 0) {
                z = true;
            }
            return z;
        } else {
            if ((this.mShowDividerHorizontal & 2) != 0) {
                z = true;
            }
            return z;
        }
    }

    private boolean allViewsAreGoneBefore(int i, int i2) {
        for (int i3 = 1; i3 <= i2; i3++) {
            View reorderedChildAt = getReorderedChildAt(i - i3);
            if (reorderedChildAt != null && reorderedChildAt.getVisibility() != 8) {
                return false;
            }
        }
        return true;
    }

    private boolean hasDividerBeforeFlexLine(int i) {
        boolean z = false;
        if (i < 0 || i >= this.mFlexLines.size()) {
            return false;
        }
        if (allFlexLinesAreDummyBefore(i)) {
            if (isMainAxisDirectionHorizontal()) {
                if ((this.mShowDividerHorizontal & 1) != 0) {
                    z = true;
                }
                return z;
            }
            if ((this.mShowDividerVertical & 1) != 0) {
                z = true;
            }
            return z;
        } else if (isMainAxisDirectionHorizontal()) {
            if ((this.mShowDividerHorizontal & 2) != 0) {
                z = true;
            }
            return z;
        } else {
            if ((this.mShowDividerVertical & 2) != 0) {
                z = true;
            }
            return z;
        }
    }

    private boolean allFlexLinesAreDummyBefore(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (((FlexLine) this.mFlexLines.get(i2)).getItemCountNotGone() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean hasEndDividerAfterFlexLine(int i) {
        boolean z = false;
        if (i < 0 || i >= this.mFlexLines.size()) {
            return false;
        }
        for (int i2 = i + 1; i2 < this.mFlexLines.size(); i2++) {
            if (((FlexLine) this.mFlexLines.get(i2)).getItemCountNotGone() > 0) {
                return false;
            }
        }
        if (isMainAxisDirectionHorizontal()) {
            if ((this.mShowDividerHorizontal & 4) != 0) {
                z = true;
            }
            return z;
        }
        if ((this.mShowDividerVertical & 4) != 0) {
            z = true;
        }
        return z;
    }
}
