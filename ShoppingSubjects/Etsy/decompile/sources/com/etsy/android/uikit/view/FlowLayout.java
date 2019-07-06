package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.etsy.android.lib.a.q;

public class FlowLayout extends ViewGroup {
    private int mHorizontalSpacing;
    private int mVerticalSpacing;

    public static class LayoutParams extends android.view.ViewGroup.LayoutParams {
        public boolean breakLine;
        int x;
        int y;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, q.FlowLayout_LayoutParams);
            try {
                this.breakLine = obtainStyledAttributes.getBoolean(q.FlowLayout_LayoutParams_layout_breakLine, false);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, q.FlowLayout);
        try {
            this.mHorizontalSpacing = obtainStyledAttributes.getDimensionPixelSize(q.FlowLayout_horizontalSpacing, 0);
            this.mVerticalSpacing = obtainStyledAttributes.getDimensionPixelSize(q.FlowLayout_verticalSpacing, 0);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public FlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public FlowLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        boolean z;
        int i3 = i;
        int i4 = i2;
        int size = MeasureSpec.getSize(i) - getPaddingRight();
        boolean z2 = MeasureSpec.getMode(i) != 0;
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int childCount = getChildCount();
        int i5 = paddingTop;
        int i6 = paddingLeft;
        int i7 = 0;
        boolean z3 = false;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        boolean z4 = false;
        while (i7 < childCount) {
            View childAt = getChildAt(i7);
            int i11 = childCount;
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i3, i4);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int i12 = this.mHorizontalSpacing;
                if (!z2 || (!z4 && childAt.getMeasuredWidth() + i6 <= size)) {
                    z = false;
                } else {
                    i5 += i10 + this.mVerticalSpacing;
                    i8 = Math.max(i8, i6 - i12);
                    i6 = getPaddingLeft();
                    z = true;
                    i10 = 0;
                }
                layoutParams.x = i6;
                layoutParams.y = i5;
                i6 += childAt.getMeasuredWidth() + i12;
                i10 = Math.max(i10, childAt.getMeasuredHeight());
                z4 = layoutParams.breakLine;
                i9 = i12;
                z3 = z;
            }
            i7++;
            childCount = i11;
        }
        if (!z3) {
            i8 = Math.max(i8, i6 - i9);
        }
        setMeasuredDimension(resolveSize(i8 + getPaddingRight(), i3), resolveSize(i5 + i10 + getPaddingBottom(), i4));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                childAt.layout(layoutParams.x, layoutParams.y, layoutParams.x + childAt.getMeasuredWidth(), layoutParams.y + childAt.getMeasuredHeight());
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams.width, layoutParams.height);
    }
}
