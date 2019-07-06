package com.google.android.flexbox;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class FlexLine {
    int mBottom = Integer.MIN_VALUE;
    int mCrossSize;
    int mDividerLengthInMainSize;
    int mFirstIndex;
    int mGoneItemCount;
    List<Integer> mIndicesAlignSelfStretch = new ArrayList();
    int mItemCount;
    int mLastIndex;
    int mLeft = Integer.MAX_VALUE;
    int mMainSize;
    int mMaxBaseline;
    int mRight = Integer.MIN_VALUE;
    int mSumCrossSizeBefore;
    int mTop = Integer.MAX_VALUE;
    float mTotalFlexGrow;
    float mTotalFlexShrink;

    FlexLine() {
    }

    public int getCrossSize() {
        return this.mCrossSize;
    }

    public int getItemCount() {
        return this.mItemCount;
    }

    public int getItemCountNotGone() {
        return this.mItemCount - this.mGoneItemCount;
    }

    /* access modifiers changed from: 0000 */
    public void updatePositionFromView(View view, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        this.mLeft = Math.min(this.mLeft, (view.getLeft() - flexItem.getMarginLeft()) - i);
        this.mTop = Math.min(this.mTop, (view.getTop() - flexItem.getMarginTop()) - i2);
        this.mRight = Math.max(this.mRight, view.getRight() + flexItem.getMarginRight() + i3);
        this.mBottom = Math.max(this.mBottom, view.getBottom() + flexItem.getMarginBottom() + i4);
    }
}
