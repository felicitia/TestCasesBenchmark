package com.google.android.flexbox;

import android.support.v4.view.MarginLayoutParamsCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class FlexboxHelper {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean[] mChildrenFrozen;
    private final FlexContainer mFlexContainer;
    int[] mIndexToFlexLine;
    long[] mMeasureSpecCache;
    private long[] mMeasuredSizeCache;

    static class FlexLinesResult {
        int mChildState;
        List<FlexLine> mFlexLines;

        FlexLinesResult() {
        }

        /* access modifiers changed from: 0000 */
        public void reset() {
            this.mFlexLines = null;
            this.mChildState = 0;
        }
    }

    private static class Order implements Comparable<Order> {
        int index;
        int order;

        private Order() {
        }

        public int compareTo(Order order2) {
            if (this.order != order2.order) {
                return this.order - order2.order;
            }
            return this.index - order2.index;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Order{order=");
            sb.append(this.order);
            sb.append(", index=");
            sb.append(this.index);
            sb.append('}');
            return sb.toString();
        }
    }

    /* access modifiers changed from: 0000 */
    public int extractHigherInt(long j) {
        return (int) (j >> 32);
    }

    /* access modifiers changed from: 0000 */
    public int extractLowerInt(long j) {
        return (int) j;
    }

    /* access modifiers changed from: 0000 */
    public long makeCombinedLong(int i, int i2) {
        return (((long) i2) << 32) | (((long) i) & 4294967295L);
    }

    FlexboxHelper(FlexContainer flexContainer) {
        this.mFlexContainer = flexContainer;
    }

    /* access modifiers changed from: 0000 */
    public int[] createReorderedIndices(View view, int i, LayoutParams layoutParams, SparseIntArray sparseIntArray) {
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        List createOrders = createOrders(flexItemCount);
        Order order = new Order();
        if (view == null || !(layoutParams instanceof FlexItem)) {
            order.order = 1;
        } else {
            order.order = ((FlexItem) layoutParams).getOrder();
        }
        if (i == -1 || i == flexItemCount) {
            order.index = flexItemCount;
        } else if (i < this.mFlexContainer.getFlexItemCount()) {
            order.index = i;
            while (i < flexItemCount) {
                Order order2 = (Order) createOrders.get(i);
                order2.index++;
                i++;
            }
        } else {
            order.index = flexItemCount;
        }
        createOrders.add(order);
        return sortOrdersIntoReorderedIndices(flexItemCount + 1, createOrders, sparseIntArray);
    }

    /* access modifiers changed from: 0000 */
    public int[] createReorderedIndices(SparseIntArray sparseIntArray) {
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        return sortOrdersIntoReorderedIndices(flexItemCount, createOrders(flexItemCount), sparseIntArray);
    }

    private List<Order> createOrders(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            FlexItem flexItem = (FlexItem) this.mFlexContainer.getFlexItemAt(i2).getLayoutParams();
            Order order = new Order();
            order.order = flexItem.getOrder();
            order.index = i2;
            arrayList.add(order);
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public boolean isOrderChangedFromLastMeasurement(SparseIntArray sparseIntArray) {
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        if (sparseIntArray.size() != flexItemCount) {
            return true;
        }
        for (int i = 0; i < flexItemCount; i++) {
            View flexItemAt = this.mFlexContainer.getFlexItemAt(i);
            if (flexItemAt != null && ((FlexItem) flexItemAt.getLayoutParams()).getOrder() != sparseIntArray.get(i)) {
                return true;
            }
        }
        return false;
    }

    private int[] sortOrdersIntoReorderedIndices(int i, List<Order> list, SparseIntArray sparseIntArray) {
        Collections.sort(list);
        sparseIntArray.clear();
        int[] iArr = new int[i];
        int i2 = 0;
        for (Order order : list) {
            iArr[i2] = order.index;
            sparseIntArray.append(order.index, order.order);
            i2++;
        }
        return iArr;
    }

    /* access modifiers changed from: 0000 */
    public void calculateHorizontalFlexLines(FlexLinesResult flexLinesResult, int i, int i2) {
        calculateFlexLines(flexLinesResult, i, i2, Integer.MAX_VALUE, 0, -1, null);
    }

    /* access modifiers changed from: 0000 */
    public void calculateHorizontalFlexLines(FlexLinesResult flexLinesResult, int i, int i2, int i3, int i4, List<FlexLine> list) {
        calculateFlexLines(flexLinesResult, i, i2, i3, i4, -1, list);
    }

    /* access modifiers changed from: 0000 */
    public void calculateHorizontalFlexLinesToIndex(FlexLinesResult flexLinesResult, int i, int i2, int i3, int i4, List<FlexLine> list) {
        calculateFlexLines(flexLinesResult, i, i2, i3, 0, i4, list);
    }

    /* access modifiers changed from: 0000 */
    public void calculateVerticalFlexLines(FlexLinesResult flexLinesResult, int i, int i2) {
        calculateFlexLines(flexLinesResult, i2, i, Integer.MAX_VALUE, 0, -1, null);
    }

    /* access modifiers changed from: 0000 */
    public void calculateVerticalFlexLines(FlexLinesResult flexLinesResult, int i, int i2, int i3, int i4, List<FlexLine> list) {
        calculateFlexLines(flexLinesResult, i2, i, i3, i4, -1, list);
    }

    /* access modifiers changed from: 0000 */
    public void calculateVerticalFlexLinesToIndex(FlexLinesResult flexLinesResult, int i, int i2, int i3, int i4, List<FlexLine> list) {
        calculateFlexLines(flexLinesResult, i2, i, i3, 0, i4, list);
    }

    /* access modifiers changed from: 0000 */
    public void calculateFlexLines(FlexLinesResult flexLinesResult, int i, int i2, int i3, int i4, int i5, List<FlexLine> list) {
        int i6;
        int i7;
        FlexLinesResult flexLinesResult2;
        int i8;
        int i9;
        List<FlexLine> list2;
        int i10;
        int i11;
        int i12;
        int i13;
        List<FlexLine> list3;
        int i14;
        int i15;
        View view;
        int i16;
        int i17;
        int i18;
        int i19;
        FlexLinesResult flexLinesResult3 = flexLinesResult;
        int i20 = i;
        int i21 = i2;
        int i22 = i5;
        boolean isMainAxisDirectionHorizontal = this.mFlexContainer.isMainAxisDirectionHorizontal();
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        List<FlexLine> arrayList = list == null ? new ArrayList<>() : list;
        flexLinesResult3.mFlexLines = arrayList;
        boolean z = i22 == -1;
        int paddingStartMain = getPaddingStartMain(isMainAxisDirectionHorizontal);
        int paddingEndMain = getPaddingEndMain(isMainAxisDirectionHorizontal);
        int paddingStartCross = getPaddingStartCross(isMainAxisDirectionHorizontal);
        int paddingEndCross = getPaddingEndCross(isMainAxisDirectionHorizontal);
        FlexLine flexLine = new FlexLine();
        int i23 = i4;
        flexLine.mFirstIndex = i23;
        int i24 = paddingEndMain + paddingStartMain;
        flexLine.mMainSize = i24;
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        boolean z2 = z;
        int i25 = 0;
        int i26 = 0;
        int i27 = 0;
        int i28 = Integer.MIN_VALUE;
        while (true) {
            if (i6 >= flexItemCount) {
                i7 = i26;
                flexLinesResult2 = flexLinesResult3;
                break;
            }
            View reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i6);
            if (reorderedFlexItemAt != null) {
                if (reorderedFlexItemAt.getVisibility() != 8) {
                    FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                    int i29 = flexItemCount;
                    if (flexItem.getAlignSelf() == 4) {
                        flexLine.mIndicesAlignSelfStretch.add(Integer.valueOf(i6));
                    }
                    int flexItemSizeMain = getFlexItemSizeMain(flexItem, isMainAxisDirectionHorizontal);
                    if (flexItem.getFlexBasisPercent() != -1.0f && mode == 1073741824) {
                        flexItemSizeMain = Math.round(((float) size) * flexItem.getFlexBasisPercent());
                    }
                    if (isMainAxisDirectionHorizontal) {
                        list3 = arrayList;
                        int childWidthMeasureSpec = this.mFlexContainer.getChildWidthMeasureSpec(i20, i24 + getFlexItemMarginStartMain(flexItem, true) + getFlexItemMarginEndMain(flexItem, true), flexItemSizeMain);
                        i13 = size;
                        int childHeightMeasureSpec = this.mFlexContainer.getChildHeightMeasureSpec(i21, paddingStartCross + paddingEndCross + getFlexItemMarginStartCross(flexItem, true) + getFlexItemMarginEndCross(flexItem, true) + i25, getFlexItemSizeCross(flexItem, true));
                        reorderedFlexItemAt.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                        updateMeasureCache(i6, childWidthMeasureSpec, childHeightMeasureSpec, reorderedFlexItemAt);
                        i14 = childWidthMeasureSpec;
                    } else {
                        list3 = arrayList;
                        i13 = size;
                        int childWidthMeasureSpec2 = this.mFlexContainer.getChildWidthMeasureSpec(i21, paddingStartCross + paddingEndCross + getFlexItemMarginStartCross(flexItem, false) + getFlexItemMarginEndCross(flexItem, false) + i25, getFlexItemSizeCross(flexItem, false));
                        int childHeightMeasureSpec2 = this.mFlexContainer.getChildHeightMeasureSpec(i20, getFlexItemMarginStartMain(flexItem, false) + i24 + getFlexItemMarginEndMain(flexItem, false), flexItemSizeMain);
                        reorderedFlexItemAt.measure(childWidthMeasureSpec2, childHeightMeasureSpec2);
                        updateMeasureCache(i6, childWidthMeasureSpec2, childHeightMeasureSpec2, reorderedFlexItemAt);
                        i14 = childHeightMeasureSpec2;
                    }
                    this.mFlexContainer.updateViewCache(i6, reorderedFlexItemAt);
                    checkSizeConstraints(reorderedFlexItemAt, i6);
                    i26 = View.combineMeasuredStates(i26, reorderedFlexItemAt.getMeasuredState());
                    int i30 = i25;
                    int i31 = i29;
                    int i32 = i24;
                    int i33 = mode;
                    i8 = mode;
                    FlexLine flexLine2 = flexLine;
                    int i34 = i6;
                    int i35 = i14;
                    int i36 = i34;
                    list2 = list3;
                    View view2 = reorderedFlexItemAt;
                    i9 = i13;
                    if (isWrapRequired(reorderedFlexItemAt, i33, i13, flexLine.mMainSize, getFlexItemMarginEndMain(flexItem, isMainAxisDirectionHorizontal) + getViewMeasuredSizeMain(reorderedFlexItemAt, isMainAxisDirectionHorizontal) + getFlexItemMarginStartMain(flexItem, isMainAxisDirectionHorizontal), flexItem, i34, i27)) {
                        if (flexLine2.getItemCountNotGone() > 0) {
                            i6 = i36;
                            addFlexLine(list2, flexLine2, i6 > 0 ? i6 - 1 : 0, i30);
                            i19 = flexLine2.mCrossSize + i30;
                        } else {
                            i6 = i36;
                            i19 = i30;
                        }
                        if (!isMainAxisDirectionHorizontal) {
                            int i37 = i35;
                            view = view2;
                            if (flexItem.getWidth() == -1) {
                                view.measure(this.mFlexContainer.getChildWidthMeasureSpec(i21, this.mFlexContainer.getPaddingLeft() + this.mFlexContainer.getPaddingRight() + flexItem.getMarginLeft() + flexItem.getMarginRight() + i19, flexItem.getWidth()), i37);
                                checkSizeConstraints(view, i6);
                            }
                        } else if (flexItem.getHeight() == -1) {
                            view = view2;
                            view.measure(i35, this.mFlexContainer.getChildHeightMeasureSpec(i21, this.mFlexContainer.getPaddingTop() + this.mFlexContainer.getPaddingBottom() + flexItem.getMarginTop() + flexItem.getMarginBottom() + i19, flexItem.getHeight()));
                            checkSizeConstraints(view, i6);
                        } else {
                            view = view2;
                        }
                        FlexLine flexLine3 = new FlexLine();
                        i16 = 1;
                        flexLine3.mItemCount = 1;
                        i11 = i32;
                        flexLine3.mMainSize = i11;
                        flexLine3.mFirstIndex = i6;
                        i30 = i19;
                        flexLine2 = flexLine3;
                        i17 = 0;
                        i15 = Integer.MIN_VALUE;
                    } else {
                        i11 = i32;
                        i6 = i36;
                        view = view2;
                        i16 = 1;
                        flexLine2.mItemCount++;
                        i17 = i27 + 1;
                        i15 = i28;
                    }
                    if (this.mIndexToFlexLine != null) {
                        this.mIndexToFlexLine[i6] = list2.size();
                    }
                    flexLine2.mMainSize += getViewMeasuredSizeMain(view, isMainAxisDirectionHorizontal) + getFlexItemMarginStartMain(flexItem, isMainAxisDirectionHorizontal) + getFlexItemMarginEndMain(flexItem, isMainAxisDirectionHorizontal);
                    flexLine2.mTotalFlexGrow += flexItem.getFlexGrow();
                    flexLine2.mTotalFlexShrink += flexItem.getFlexShrink();
                    this.mFlexContainer.onNewFlexItemAdded(view, i6, i17, flexLine2);
                    int max = Math.max(i15, getViewMeasuredSizeCross(view, isMainAxisDirectionHorizontal) + getFlexItemMarginStartCross(flexItem, isMainAxisDirectionHorizontal) + getFlexItemMarginEndCross(flexItem, isMainAxisDirectionHorizontal) + this.mFlexContainer.getDecorationLengthCrossAxis(view));
                    flexLine2.mCrossSize = Math.max(flexLine2.mCrossSize, max);
                    if (isMainAxisDirectionHorizontal) {
                        if (this.mFlexContainer.getFlexWrap() != 2) {
                            flexLine2.mMaxBaseline = Math.max(flexLine2.mMaxBaseline, view.getBaseline() + flexItem.getMarginTop());
                        } else {
                            flexLine2.mMaxBaseline = Math.max(flexLine2.mMaxBaseline, (view.getMeasuredHeight() - view.getBaseline()) + flexItem.getMarginBottom());
                        }
                    }
                    i12 = i31;
                    if (isLastFlexItem(i6, i12, flexLine2)) {
                        addFlexLine(list2, flexLine2, i6, i30);
                        i30 += flexLine2.mCrossSize;
                    }
                    i10 = i5;
                    if (i10 == -1 || list2.size() <= 0 || ((FlexLine) list2.get(list2.size() - i16)).mLastIndex < i10 || i6 < i10 || z2) {
                        i18 = i3;
                    } else {
                        i30 = -flexLine2.getCrossSize();
                        i18 = i3;
                        z2 = true;
                    }
                    if (i30 > i18 && z2) {
                        i7 = i26;
                        flexLinesResult2 = flexLinesResult;
                        break;
                    }
                    i27 = i17;
                    i28 = max;
                    i25 = i30;
                    flexLine = flexLine2;
                    i23 = i6 + 1;
                    flexItemCount = i12;
                    i24 = i11;
                    size = i9;
                    mode = i8;
                    flexLinesResult3 = flexLinesResult;
                    i20 = i;
                    List<FlexLine> list4 = list2;
                    i22 = i10;
                    arrayList = list4;
                } else {
                    flexLine.mGoneItemCount++;
                    flexLine.mItemCount++;
                    if (isLastFlexItem(i6, flexItemCount, flexLine)) {
                        addFlexLine(arrayList, flexLine, i6, i25);
                    }
                }
            } else if (isLastFlexItem(i6, flexItemCount, flexLine)) {
                addFlexLine(arrayList, flexLine, i6, i25);
            }
            int i38 = i3;
            i11 = i24;
            i9 = size;
            i8 = mode;
            i12 = flexItemCount;
            int i39 = i22;
            list2 = arrayList;
            i10 = i39;
            i23 = i6 + 1;
            flexItemCount = i12;
            i24 = i11;
            size = i9;
            mode = i8;
            flexLinesResult3 = flexLinesResult;
            i20 = i;
            List<FlexLine> list42 = list2;
            i22 = i10;
            arrayList = list42;
        }
        flexLinesResult2.mChildState = i7;
    }

    private int getPaddingStartMain(boolean z) {
        if (z) {
            return this.mFlexContainer.getPaddingStart();
        }
        return this.mFlexContainer.getPaddingTop();
    }

    private int getPaddingEndMain(boolean z) {
        if (z) {
            return this.mFlexContainer.getPaddingEnd();
        }
        return this.mFlexContainer.getPaddingBottom();
    }

    private int getPaddingStartCross(boolean z) {
        if (z) {
            return this.mFlexContainer.getPaddingTop();
        }
        return this.mFlexContainer.getPaddingStart();
    }

    private int getPaddingEndCross(boolean z) {
        if (z) {
            return this.mFlexContainer.getPaddingBottom();
        }
        return this.mFlexContainer.getPaddingEnd();
    }

    private int getViewMeasuredSizeMain(View view, boolean z) {
        if (z) {
            return view.getMeasuredWidth();
        }
        return view.getMeasuredHeight();
    }

    private int getViewMeasuredSizeCross(View view, boolean z) {
        if (z) {
            return view.getMeasuredHeight();
        }
        return view.getMeasuredWidth();
    }

    private int getFlexItemSizeMain(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getWidth();
        }
        return flexItem.getHeight();
    }

    private int getFlexItemSizeCross(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getHeight();
        }
        return flexItem.getWidth();
    }

    private int getFlexItemMarginStartMain(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginLeft();
        }
        return flexItem.getMarginTop();
    }

    private int getFlexItemMarginEndMain(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginRight();
        }
        return flexItem.getMarginBottom();
    }

    private int getFlexItemMarginStartCross(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginTop();
        }
        return flexItem.getMarginLeft();
    }

    private int getFlexItemMarginEndCross(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getMarginBottom();
        }
        return flexItem.getMarginRight();
    }

    private boolean isWrapRequired(View view, int i, int i2, int i3, int i4, FlexItem flexItem, int i5, int i6) {
        if (this.mFlexContainer.getFlexWrap() == 0) {
            return false;
        }
        boolean z = true;
        if (flexItem.isWrapBefore()) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        int decorationLengthMainAxis = this.mFlexContainer.getDecorationLengthMainAxis(view, i5, i6);
        if (decorationLengthMainAxis > 0) {
            i4 += decorationLengthMainAxis;
        }
        if (i2 >= i3 + i4) {
            z = false;
        }
        return z;
    }

    private boolean isLastFlexItem(int i, int i2, FlexLine flexLine) {
        return i == i2 - 1 && flexLine.getItemCountNotGone() != 0;
    }

    private void addFlexLine(List<FlexLine> list, FlexLine flexLine, int i, int i2) {
        flexLine.mSumCrossSizeBefore = i2;
        this.mFlexContainer.onNewFlexLineAdded(flexLine);
        flexLine.mLastIndex = i;
        list.add(flexLine);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkSizeConstraints(android.view.View r7, int r8) {
        /*
            r6 = this;
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            com.google.android.flexbox.FlexItem r0 = (com.google.android.flexbox.FlexItem) r0
            int r1 = r7.getMeasuredWidth()
            int r2 = r7.getMeasuredHeight()
            int r3 = r0.getMinWidth()
            r4 = 1
            if (r1 >= r3) goto L_0x001b
            int r1 = r0.getMinWidth()
        L_0x0019:
            r3 = 1
            goto L_0x0027
        L_0x001b:
            int r3 = r0.getMaxWidth()
            if (r1 <= r3) goto L_0x0026
            int r1 = r0.getMaxWidth()
            goto L_0x0019
        L_0x0026:
            r3 = 0
        L_0x0027:
            int r5 = r0.getMinHeight()
            if (r2 >= r5) goto L_0x0032
            int r2 = r0.getMinHeight()
            goto L_0x003e
        L_0x0032:
            int r5 = r0.getMaxHeight()
            if (r2 <= r5) goto L_0x003d
            int r2 = r0.getMaxHeight()
            goto L_0x003e
        L_0x003d:
            r4 = r3
        L_0x003e:
            if (r4 == 0) goto L_0x0055
            r0 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r0)
            r7.measure(r1, r0)
            r6.updateMeasureCache(r8, r1, r0, r7)
            com.google.android.flexbox.FlexContainer r0 = r6.mFlexContainer
            r0.updateViewCache(r8, r7)
        L_0x0055:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxHelper.checkSizeConstraints(android.view.View, int):void");
    }

    /* access modifiers changed from: 0000 */
    public void determineMainSize(int i, int i2) {
        determineMainSize(i, i2, 0);
    }

    /* access modifiers changed from: 0000 */
    public void determineMainSize(int i, int i2, int i3) {
        int i4;
        int i5;
        ensureChildrenFrozen(this.mFlexContainer.getFlexItemCount());
        if (i3 < this.mFlexContainer.getFlexItemCount()) {
            int flexDirection = this.mFlexContainer.getFlexDirection();
            switch (this.mFlexContainer.getFlexDirection()) {
                case 0:
                case 1:
                    int mode = MeasureSpec.getMode(i);
                    int size = MeasureSpec.getSize(i);
                    if (mode != 1073741824) {
                        size = this.mFlexContainer.getLargestMainSize();
                    }
                    i5 = this.mFlexContainer.getPaddingLeft() + this.mFlexContainer.getPaddingRight();
                    break;
                case 2:
                case 3:
                    int mode2 = MeasureSpec.getMode(i2);
                    i4 = MeasureSpec.getSize(i2);
                    if (mode2 != 1073741824) {
                        i4 = this.mFlexContainer.getLargestMainSize();
                    }
                    i5 = this.mFlexContainer.getPaddingTop() + this.mFlexContainer.getPaddingBottom();
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid flex direction: ");
                    sb.append(flexDirection);
                    throw new IllegalArgumentException(sb.toString());
            }
            int i6 = 0;
            if (this.mIndexToFlexLine != null) {
                i6 = this.mIndexToFlexLine[i3];
            }
            List flexLinesInternal = this.mFlexContainer.getFlexLinesInternal();
            int size2 = flexLinesInternal.size();
            for (int i7 = i6; i7 < size2; i7++) {
                FlexLine flexLine = (FlexLine) flexLinesInternal.get(i7);
                if (flexLine.mMainSize < i4) {
                    expandFlexItems(i, i2, flexLine, i4, i5, false);
                } else {
                    shrinkFlexItems(i, i2, flexLine, i4, i5, false);
                }
            }
        }
    }

    private void ensureChildrenFrozen(int i) {
        if (this.mChildrenFrozen == null) {
            if (i < 10) {
                i = 10;
            }
            this.mChildrenFrozen = new boolean[i];
        } else if (this.mChildrenFrozen.length < i) {
            int length = this.mChildrenFrozen.length * 2;
            if (length >= i) {
                i = length;
            }
            this.mChildrenFrozen = new boolean[i];
        } else {
            Arrays.fill(this.mChildrenFrozen, false);
        }
    }

    private void expandFlexItems(int i, int i2, FlexLine flexLine, int i3, int i4, boolean z) {
        float f;
        boolean z2;
        float f2;
        int i5;
        int i6;
        int i7;
        int i8;
        float f3;
        int i9;
        int i10;
        float f4;
        boolean z3;
        FlexLine flexLine2 = flexLine;
        int i11 = i3;
        if (flexLine2.mTotalFlexGrow > 0.0f && i11 >= flexLine2.mMainSize) {
            int i12 = flexLine2.mMainSize;
            float f5 = ((float) (i11 - flexLine2.mMainSize)) / flexLine2.mTotalFlexGrow;
            flexLine2.mMainSize = i4 + flexLine2.mDividerLengthInMainSize;
            if (!z) {
                flexLine2.mCrossSize = Integer.MIN_VALUE;
            }
            int i13 = 0;
            boolean z4 = false;
            float f6 = 0.0f;
            int i14 = 0;
            while (i13 < flexLine2.mItemCount) {
                int i15 = flexLine2.mFirstIndex + i13;
                View reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i15);
                if (reorderedFlexItemAt == null || reorderedFlexItemAt.getVisibility() == 8) {
                    int i16 = i;
                    f2 = f5;
                    z2 = z4;
                    f = f6;
                    int i17 = i14;
                    int i18 = i2;
                    i5 = i17;
                } else {
                    FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                    int flexDirection = this.mFlexContainer.getFlexDirection();
                    if (flexDirection == 0 || flexDirection == 1) {
                        int i19 = i;
                        f2 = f5;
                        boolean z5 = z4;
                        float f7 = f6;
                        int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                        if (this.mMeasuredSizeCache != null) {
                            measuredWidth = extractLowerInt(this.mMeasuredSizeCache[i15]);
                        }
                        int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                        if (this.mMeasuredSizeCache != null) {
                            measuredHeight = extractHigherInt(this.mMeasuredSizeCache[i15]);
                        }
                        if (this.mChildrenFrozen[i15] || flexItem.getFlexGrow() <= 0.0f) {
                            i6 = i14;
                            int i20 = i2;
                            i8 = measuredWidth;
                            i7 = measuredHeight;
                        } else {
                            float flexGrow = ((float) measuredWidth) + (flexItem.getFlexGrow() * f2);
                            boolean z6 = true;
                            if (i13 == flexLine2.mItemCount - 1) {
                                flexGrow += f7;
                                f7 = 0.0f;
                            }
                            int round = Math.round(flexGrow);
                            if (round > flexItem.getMaxWidth()) {
                                round = flexItem.getMaxWidth();
                                this.mChildrenFrozen[i15] = true;
                                flexLine2.mTotalFlexGrow -= flexItem.getFlexGrow();
                                i6 = i14;
                                f3 = f7;
                            } else {
                                float f8 = f7 + (flexGrow - ((float) round));
                                i6 = i14;
                                double d = (double) f8;
                                if (d > 1.0d) {
                                    round++;
                                    f8 = (float) (d - 1.0d);
                                } else if (d < -1.0d) {
                                    round--;
                                    f8 = (float) (d + 1.0d);
                                }
                                f3 = f8;
                                z6 = z5;
                            }
                            int childHeightMeasureSpecInternal = getChildHeightMeasureSpecInternal(i2, flexItem, flexLine2.mSumCrossSizeBefore);
                            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(round, 1073741824);
                            reorderedFlexItemAt.measure(makeMeasureSpec, childHeightMeasureSpecInternal);
                            i8 = reorderedFlexItemAt.getMeasuredWidth();
                            i7 = reorderedFlexItemAt.getMeasuredHeight();
                            updateMeasureCache(i15, makeMeasureSpec, childHeightMeasureSpecInternal, reorderedFlexItemAt);
                            this.mFlexContainer.updateViewCache(i15, reorderedFlexItemAt);
                            z5 = z6;
                            f7 = f3;
                        }
                        i5 = Math.max(i6, i7 + flexItem.getMarginTop() + flexItem.getMarginBottom() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                        flexLine2.mMainSize += i8 + flexItem.getMarginLeft() + flexItem.getMarginRight();
                    } else {
                        int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                        if (this.mMeasuredSizeCache != null) {
                            f2 = f5;
                            measuredHeight2 = extractHigherInt(this.mMeasuredSizeCache[i15]);
                        } else {
                            f2 = f5;
                        }
                        int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                        if (this.mMeasuredSizeCache != null) {
                            z2 = z4;
                            f = f6;
                            measuredWidth2 = extractLowerInt(this.mMeasuredSizeCache[i15]);
                        } else {
                            z2 = z4;
                            f = f6;
                        }
                        if (this.mChildrenFrozen[i15] || flexItem.getFlexGrow() <= 0.0f) {
                            int i21 = i;
                            i9 = measuredHeight2;
                            i10 = measuredWidth2;
                        } else {
                            float flexGrow2 = ((float) measuredHeight2) + (flexItem.getFlexGrow() * f2);
                            if (i13 == flexLine2.mItemCount - 1) {
                                flexGrow2 += f;
                                f = 0.0f;
                            }
                            int round2 = Math.round(flexGrow2);
                            if (round2 > flexItem.getMaxHeight()) {
                                round2 = flexItem.getMaxHeight();
                                this.mChildrenFrozen[i15] = true;
                                flexLine2.mTotalFlexGrow -= flexItem.getFlexGrow();
                                f4 = f;
                                z3 = true;
                            } else {
                                float f9 = f + (flexGrow2 - ((float) round2));
                                double d2 = (double) f9;
                                if (d2 > 1.0d) {
                                    round2++;
                                    f9 = (float) (d2 - 1.0d);
                                } else if (d2 < -1.0d) {
                                    round2--;
                                    f9 = (float) (d2 + 1.0d);
                                }
                                f4 = f9;
                                z3 = z2;
                            }
                            int childWidthMeasureSpecInternal = getChildWidthMeasureSpecInternal(i, flexItem, flexLine2.mSumCrossSizeBefore);
                            int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(round2, 1073741824);
                            reorderedFlexItemAt.measure(childWidthMeasureSpecInternal, makeMeasureSpec2);
                            i10 = reorderedFlexItemAt.getMeasuredWidth();
                            i9 = reorderedFlexItemAt.getMeasuredHeight();
                            updateMeasureCache(i15, childWidthMeasureSpecInternal, makeMeasureSpec2, reorderedFlexItemAt);
                            this.mFlexContainer.updateViewCache(i15, reorderedFlexItemAt);
                            z2 = z3;
                            f = f4;
                        }
                        i5 = Math.max(i14, i10 + flexItem.getMarginLeft() + flexItem.getMarginRight() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                        flexLine2.mMainSize += i9 + flexItem.getMarginTop() + flexItem.getMarginBottom();
                        int i22 = i2;
                    }
                    flexLine2.mCrossSize = Math.max(flexLine2.mCrossSize, i5);
                }
                z4 = z2;
                f6 = f;
                i13++;
                i14 = i5;
                f5 = f2;
            }
            int i23 = i;
            int i24 = i2;
            if (z4 && i12 != flexLine2.mMainSize) {
                expandFlexItems(i23, i24, flexLine2, i11, i4, true);
            }
        }
    }

    private void shrinkFlexItems(int i, int i2, FlexLine flexLine, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        FlexLine flexLine2 = flexLine;
        int i10 = i3;
        int i11 = flexLine2.mMainSize;
        if (flexLine2.mTotalFlexShrink > 0.0f && i10 <= flexLine2.mMainSize) {
            float f = ((float) (flexLine2.mMainSize - i10)) / flexLine2.mTotalFlexShrink;
            flexLine2.mMainSize = i4 + flexLine2.mDividerLengthInMainSize;
            if (!z) {
                flexLine2.mCrossSize = Integer.MIN_VALUE;
            }
            int i12 = 0;
            boolean z2 = false;
            float f2 = 0.0f;
            int i13 = 0;
            while (i12 < flexLine2.mItemCount) {
                int i14 = flexLine2.mFirstIndex + i12;
                View reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i14);
                if (reorderedFlexItemAt == null || reorderedFlexItemAt.getVisibility() == 8) {
                    int i15 = i2;
                } else {
                    FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                    int flexDirection = this.mFlexContainer.getFlexDirection();
                    if (flexDirection == 0 || flexDirection == 1) {
                        int i16 = i;
                        int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                        if (this.mMeasuredSizeCache != null) {
                            measuredWidth = extractLowerInt(this.mMeasuredSizeCache[i14]);
                        }
                        int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                        if (this.mMeasuredSizeCache != null) {
                            measuredHeight = extractHigherInt(this.mMeasuredSizeCache[i14]);
                        }
                        if (this.mChildrenFrozen[i14] || flexItem.getFlexShrink() <= 0.0f) {
                            int i17 = i2;
                            i7 = measuredWidth;
                            i6 = measuredHeight;
                        } else {
                            float flexShrink = ((float) measuredWidth) - (flexItem.getFlexShrink() * f);
                            if (i12 == flexLine2.mItemCount - 1) {
                                flexShrink += f2;
                                f2 = 0.0f;
                            }
                            int round = Math.round(flexShrink);
                            if (round < flexItem.getMinWidth()) {
                                round = flexItem.getMinWidth();
                                this.mChildrenFrozen[i14] = true;
                                flexLine2.mTotalFlexShrink -= flexItem.getFlexShrink();
                                z2 = true;
                            } else {
                                f2 += flexShrink - ((float) round);
                                double d = (double) f2;
                                if (d > 1.0d) {
                                    round++;
                                    f2 -= 1.0f;
                                } else if (d < -1.0d) {
                                    round--;
                                    f2 += 1.0f;
                                }
                            }
                            int childHeightMeasureSpecInternal = getChildHeightMeasureSpecInternal(i2, flexItem, flexLine2.mSumCrossSizeBefore);
                            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(round, 1073741824);
                            reorderedFlexItemAt.measure(makeMeasureSpec, childHeightMeasureSpecInternal);
                            i7 = reorderedFlexItemAt.getMeasuredWidth();
                            i6 = reorderedFlexItemAt.getMeasuredHeight();
                            updateMeasureCache(i14, makeMeasureSpec, childHeightMeasureSpecInternal, reorderedFlexItemAt);
                            this.mFlexContainer.updateViewCache(i14, reorderedFlexItemAt);
                        }
                        i5 = Math.max(i13, i6 + flexItem.getMarginTop() + flexItem.getMarginBottom() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                        flexLine2.mMainSize += i7 + flexItem.getMarginLeft() + flexItem.getMarginRight();
                    } else {
                        int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                        if (this.mMeasuredSizeCache != null) {
                            measuredHeight2 = extractHigherInt(this.mMeasuredSizeCache[i14]);
                        }
                        int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                        if (this.mMeasuredSizeCache != null) {
                            measuredWidth2 = extractLowerInt(this.mMeasuredSizeCache[i14]);
                        }
                        if (this.mChildrenFrozen[i14] || flexItem.getFlexShrink() <= 0.0f) {
                            int i18 = i;
                            flexLine2 = flexLine;
                            i8 = measuredHeight2;
                            i9 = measuredWidth2;
                        } else {
                            float flexShrink2 = ((float) measuredHeight2) - (flexItem.getFlexShrink() * f);
                            flexLine2 = flexLine;
                            if (i12 == flexLine2.mItemCount - 1) {
                                flexShrink2 += f2;
                                f2 = 0.0f;
                            }
                            int round2 = Math.round(flexShrink2);
                            if (round2 < flexItem.getMinHeight()) {
                                round2 = flexItem.getMinHeight();
                                this.mChildrenFrozen[i14] = true;
                                flexLine2.mTotalFlexShrink -= flexItem.getFlexShrink();
                                z2 = true;
                            } else {
                                f2 += flexShrink2 - ((float) round2);
                                double d2 = (double) f2;
                                if (d2 > 1.0d) {
                                    round2++;
                                    f2 -= 1.0f;
                                } else if (d2 < -1.0d) {
                                    round2--;
                                    f2 += 1.0f;
                                }
                            }
                            int childWidthMeasureSpecInternal = getChildWidthMeasureSpecInternal(i, flexItem, flexLine2.mSumCrossSizeBefore);
                            int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(round2, 1073741824);
                            reorderedFlexItemAt.measure(childWidthMeasureSpecInternal, makeMeasureSpec2);
                            i9 = reorderedFlexItemAt.getMeasuredWidth();
                            i8 = reorderedFlexItemAt.getMeasuredHeight();
                            updateMeasureCache(i14, childWidthMeasureSpecInternal, makeMeasureSpec2, reorderedFlexItemAt);
                            this.mFlexContainer.updateViewCache(i14, reorderedFlexItemAt);
                        }
                        i5 = Math.max(i13, i9 + flexItem.getMarginLeft() + flexItem.getMarginRight() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                        flexLine2.mMainSize += i8 + flexItem.getMarginTop() + flexItem.getMarginBottom();
                        int i19 = i2;
                    }
                    flexLine2.mCrossSize = Math.max(flexLine2.mCrossSize, i5);
                    i13 = i5;
                }
                i12++;
                int i20 = i3;
            }
            int i21 = i2;
            if (z2 && i11 != flexLine2.mMainSize) {
                shrinkFlexItems(i, i21, flexLine2, i3, i4, true);
            }
        }
    }

    private int getChildWidthMeasureSpecInternal(int i, FlexItem flexItem, int i2) {
        int childWidthMeasureSpec = this.mFlexContainer.getChildWidthMeasureSpec(i, this.mFlexContainer.getPaddingLeft() + this.mFlexContainer.getPaddingRight() + flexItem.getMarginLeft() + flexItem.getMarginRight() + i2, flexItem.getWidth());
        int size = MeasureSpec.getSize(childWidthMeasureSpec);
        if (size > flexItem.getMaxWidth()) {
            return MeasureSpec.makeMeasureSpec(flexItem.getMaxWidth(), MeasureSpec.getMode(childWidthMeasureSpec));
        }
        return size < flexItem.getMinWidth() ? MeasureSpec.makeMeasureSpec(flexItem.getMinWidth(), MeasureSpec.getMode(childWidthMeasureSpec)) : childWidthMeasureSpec;
    }

    private int getChildHeightMeasureSpecInternal(int i, FlexItem flexItem, int i2) {
        int childHeightMeasureSpec = this.mFlexContainer.getChildHeightMeasureSpec(i, this.mFlexContainer.getPaddingTop() + this.mFlexContainer.getPaddingBottom() + flexItem.getMarginTop() + flexItem.getMarginBottom() + i2, flexItem.getHeight());
        int size = MeasureSpec.getSize(childHeightMeasureSpec);
        if (size > flexItem.getMaxHeight()) {
            return MeasureSpec.makeMeasureSpec(flexItem.getMaxHeight(), MeasureSpec.getMode(childHeightMeasureSpec));
        }
        return size < flexItem.getMinHeight() ? MeasureSpec.makeMeasureSpec(flexItem.getMinHeight(), MeasureSpec.getMode(childHeightMeasureSpec)) : childHeightMeasureSpec;
    }

    /* access modifiers changed from: 0000 */
    public void determineCrossSize(int i, int i2, int i3) {
        int i4;
        int i5;
        int flexDirection = this.mFlexContainer.getFlexDirection();
        switch (flexDirection) {
            case 0:
            case 1:
                i5 = MeasureSpec.getMode(i2);
                i4 = MeasureSpec.getSize(i2);
                break;
            case 2:
            case 3:
                int mode = MeasureSpec.getMode(i);
                i4 = MeasureSpec.getSize(i);
                i5 = mode;
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid flex direction: ");
                sb.append(flexDirection);
                throw new IllegalArgumentException(sb.toString());
        }
        List<FlexLine> flexLinesInternal = this.mFlexContainer.getFlexLinesInternal();
        if (i5 == 1073741824) {
            int sumOfCrossSize = this.mFlexContainer.getSumOfCrossSize() + i3;
            int i6 = 0;
            if (flexLinesInternal.size() == 1) {
                ((FlexLine) flexLinesInternal.get(0)).mCrossSize = i4 - i3;
            } else if (flexLinesInternal.size() >= 2) {
                switch (this.mFlexContainer.getAlignContent()) {
                    case 1:
                        int i7 = i4 - sumOfCrossSize;
                        FlexLine flexLine = new FlexLine();
                        flexLine.mCrossSize = i7;
                        flexLinesInternal.add(0, flexLine);
                        return;
                    case 2:
                        this.mFlexContainer.setFlexLines(constructFlexLinesForAlignContentCenter(flexLinesInternal, i4, sumOfCrossSize));
                        return;
                    case 3:
                        if (sumOfCrossSize < i4) {
                            float size = ((float) (i4 - sumOfCrossSize)) / ((float) (flexLinesInternal.size() - 1));
                            ArrayList arrayList = new ArrayList();
                            int size2 = flexLinesInternal.size();
                            float f = 0.0f;
                            while (i6 < size2) {
                                arrayList.add((FlexLine) flexLinesInternal.get(i6));
                                if (i6 != flexLinesInternal.size() - 1) {
                                    FlexLine flexLine2 = new FlexLine();
                                    if (i6 == flexLinesInternal.size() - 2) {
                                        flexLine2.mCrossSize = Math.round(f + size);
                                        f = 0.0f;
                                    } else {
                                        flexLine2.mCrossSize = Math.round(size);
                                    }
                                    f += size - ((float) flexLine2.mCrossSize);
                                    if (f > 1.0f) {
                                        flexLine2.mCrossSize++;
                                        f -= 1.0f;
                                    } else if (f < -1.0f) {
                                        flexLine2.mCrossSize--;
                                        f += 1.0f;
                                    }
                                    arrayList.add(flexLine2);
                                }
                                i6++;
                            }
                            this.mFlexContainer.setFlexLines(arrayList);
                            return;
                        }
                        return;
                    case 4:
                        if (sumOfCrossSize >= i4) {
                            this.mFlexContainer.setFlexLines(constructFlexLinesForAlignContentCenter(flexLinesInternal, i4, sumOfCrossSize));
                            return;
                        }
                        int size3 = (i4 - sumOfCrossSize) / (flexLinesInternal.size() * 2);
                        ArrayList arrayList2 = new ArrayList();
                        FlexLine flexLine3 = new FlexLine();
                        flexLine3.mCrossSize = size3;
                        for (FlexLine flexLine4 : flexLinesInternal) {
                            arrayList2.add(flexLine3);
                            arrayList2.add(flexLine4);
                            arrayList2.add(flexLine3);
                        }
                        this.mFlexContainer.setFlexLines(arrayList2);
                        return;
                    case 5:
                        if (sumOfCrossSize < i4) {
                            float size4 = ((float) (i4 - sumOfCrossSize)) / ((float) flexLinesInternal.size());
                            int size5 = flexLinesInternal.size();
                            float f2 = 0.0f;
                            while (i6 < size5) {
                                FlexLine flexLine5 = (FlexLine) flexLinesInternal.get(i6);
                                float f3 = ((float) flexLine5.mCrossSize) + size4;
                                if (i6 == flexLinesInternal.size() - 1) {
                                    f3 += f2;
                                    f2 = 0.0f;
                                }
                                int round = Math.round(f3);
                                f2 += f3 - ((float) round);
                                if (f2 > 1.0f) {
                                    round++;
                                    f2 -= 1.0f;
                                } else if (f2 < -1.0f) {
                                    round--;
                                    f2 += 1.0f;
                                }
                                flexLine5.mCrossSize = round;
                                i6++;
                            }
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private List<FlexLine> constructFlexLinesForAlignContentCenter(List<FlexLine> list, int i, int i2) {
        int i3 = (i - i2) / 2;
        ArrayList arrayList = new ArrayList();
        FlexLine flexLine = new FlexLine();
        flexLine.mCrossSize = i3;
        int size = list.size();
        for (int i4 = 0; i4 < size; i4++) {
            if (i4 == 0) {
                arrayList.add(flexLine);
            }
            arrayList.add((FlexLine) list.get(i4));
            if (i4 == list.size() - 1) {
                arrayList.add(flexLine);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public void stretchViews() {
        stretchViews(0);
    }

    /* access modifiers changed from: 0000 */
    public void stretchViews(int i) {
        if (i < this.mFlexContainer.getFlexItemCount()) {
            int flexDirection = this.mFlexContainer.getFlexDirection();
            if (this.mFlexContainer.getAlignItems() == 4) {
                List flexLinesInternal = this.mFlexContainer.getFlexLinesInternal();
                int size = flexLinesInternal.size();
                for (int i2 = this.mIndexToFlexLine != null ? this.mIndexToFlexLine[i] : 0; i2 < size; i2++) {
                    FlexLine flexLine = (FlexLine) flexLinesInternal.get(i2);
                    int i3 = flexLine.mItemCount;
                    for (int i4 = 0; i4 < i3; i4++) {
                        int i5 = flexLine.mFirstIndex + i4;
                        if (i4 < this.mFlexContainer.getFlexItemCount()) {
                            View reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i5);
                            if (!(reorderedFlexItemAt == null || reorderedFlexItemAt.getVisibility() == 8)) {
                                FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                                if (flexItem.getAlignSelf() == -1 || flexItem.getAlignSelf() == 4) {
                                    switch (flexDirection) {
                                        case 0:
                                        case 1:
                                            stretchViewVertically(reorderedFlexItemAt, flexLine.mCrossSize, i5);
                                            break;
                                        case 2:
                                        case 3:
                                            stretchViewHorizontally(reorderedFlexItemAt, flexLine.mCrossSize, i5);
                                            break;
                                        default:
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("Invalid flex direction: ");
                                            sb.append(flexDirection);
                                            throw new IllegalArgumentException(sb.toString());
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                for (FlexLine flexLine2 : this.mFlexContainer.getFlexLinesInternal()) {
                    Iterator it = flexLine2.mIndicesAlignSelfStretch.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            Integer num = (Integer) it.next();
                            View reorderedFlexItemAt2 = this.mFlexContainer.getReorderedFlexItemAt(num.intValue());
                            switch (flexDirection) {
                                case 0:
                                case 1:
                                    stretchViewVertically(reorderedFlexItemAt2, flexLine2.mCrossSize, num.intValue());
                                    break;
                                case 2:
                                case 3:
                                    stretchViewHorizontally(reorderedFlexItemAt2, flexLine2.mCrossSize, num.intValue());
                                    break;
                                default:
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("Invalid flex direction: ");
                                    sb2.append(flexDirection);
                                    throw new IllegalArgumentException(sb2.toString());
                            }
                        }
                    }
                }
            }
        }
    }

    private void stretchViewVertically(View view, int i, int i2) {
        int i3;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int min = Math.min(Math.max(((i - flexItem.getMarginTop()) - flexItem.getMarginBottom()) - this.mFlexContainer.getDecorationLengthCrossAxis(view), flexItem.getMinHeight()), flexItem.getMaxHeight());
        if (this.mMeasuredSizeCache != null) {
            i3 = extractLowerInt(this.mMeasuredSizeCache[i2]);
        } else {
            i3 = view.getMeasuredWidth();
        }
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(i3, 1073741824);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(makeMeasureSpec, makeMeasureSpec2);
        updateMeasureCache(i2, makeMeasureSpec, makeMeasureSpec2, view);
        this.mFlexContainer.updateViewCache(i2, view);
    }

    private void stretchViewHorizontally(View view, int i, int i2) {
        int i3;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int min = Math.min(Math.max(((i - flexItem.getMarginLeft()) - flexItem.getMarginRight()) - this.mFlexContainer.getDecorationLengthCrossAxis(view), flexItem.getMinWidth()), flexItem.getMaxWidth());
        if (this.mMeasuredSizeCache != null) {
            i3 = extractHigherInt(this.mMeasuredSizeCache[i2]);
        } else {
            i3 = view.getMeasuredHeight();
        }
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(i3, 1073741824);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(makeMeasureSpec2, makeMeasureSpec);
        updateMeasureCache(i2, makeMeasureSpec2, makeMeasureSpec, view);
        this.mFlexContainer.updateViewCache(i2, view);
    }

    /* access modifiers changed from: 0000 */
    public void layoutSingleChildHorizontal(View view, FlexLine flexLine, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.mFlexContainer.getAlignItems();
        if (flexItem.getAlignSelf() != -1) {
            alignItems = flexItem.getAlignSelf();
        }
        int i5 = flexLine.mCrossSize;
        switch (alignItems) {
            case 0:
            case 4:
                if (this.mFlexContainer.getFlexWrap() != 2) {
                    view.layout(i, i2 + flexItem.getMarginTop(), i3, i4 + flexItem.getMarginTop());
                    return;
                } else {
                    view.layout(i, i2 - flexItem.getMarginBottom(), i3, i4 - flexItem.getMarginBottom());
                    return;
                }
            case 1:
                if (this.mFlexContainer.getFlexWrap() != 2) {
                    int i6 = i2 + i5;
                    view.layout(i, (i6 - view.getMeasuredHeight()) - flexItem.getMarginBottom(), i3, i6 - flexItem.getMarginBottom());
                    return;
                }
                view.layout(i, (i2 - i5) + view.getMeasuredHeight() + flexItem.getMarginTop(), i3, (i4 - i5) + view.getMeasuredHeight() + flexItem.getMarginTop());
                return;
            case 2:
                int measuredHeight = (((i5 - view.getMeasuredHeight()) + flexItem.getMarginTop()) - flexItem.getMarginBottom()) / 2;
                if (this.mFlexContainer.getFlexWrap() != 2) {
                    int i7 = i2 + measuredHeight;
                    view.layout(i, i7, i3, view.getMeasuredHeight() + i7);
                    return;
                }
                int i8 = i2 - measuredHeight;
                view.layout(i, i8, i3, view.getMeasuredHeight() + i8);
                return;
            case 3:
                if (this.mFlexContainer.getFlexWrap() != 2) {
                    int max = Math.max(flexLine.mMaxBaseline - view.getBaseline(), flexItem.getMarginTop());
                    view.layout(i, i2 + max, i3, i4 + max);
                    return;
                }
                int max2 = Math.max((flexLine.mMaxBaseline - view.getMeasuredHeight()) + view.getBaseline(), flexItem.getMarginBottom());
                view.layout(i, i2 - max2, i3, i4 - max2);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: 0000 */
    public void layoutSingleChildVertical(View view, FlexLine flexLine, boolean z, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.mFlexContainer.getAlignItems();
        if (flexItem.getAlignSelf() != -1) {
            alignItems = flexItem.getAlignSelf();
        }
        int i5 = flexLine.mCrossSize;
        switch (alignItems) {
            case 0:
            case 3:
            case 4:
                if (!z) {
                    view.layout(i + flexItem.getMarginLeft(), i2, i3 + flexItem.getMarginLeft(), i4);
                    return;
                } else {
                    view.layout(i - flexItem.getMarginRight(), i2, i3 - flexItem.getMarginRight(), i4);
                    return;
                }
            case 1:
                if (!z) {
                    view.layout(((i + i5) - view.getMeasuredWidth()) - flexItem.getMarginRight(), i2, ((i3 + i5) - view.getMeasuredWidth()) - flexItem.getMarginRight(), i4);
                    return;
                } else {
                    view.layout((i - i5) + view.getMeasuredWidth() + flexItem.getMarginLeft(), i2, (i3 - i5) + view.getMeasuredWidth() + flexItem.getMarginLeft(), i4);
                    return;
                }
            case 2:
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
                int measuredWidth = (((i5 - view.getMeasuredWidth()) + MarginLayoutParamsCompat.getMarginStart(marginLayoutParams)) - MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams)) / 2;
                if (!z) {
                    view.layout(i + measuredWidth, i2, i3 + measuredWidth, i4);
                    return;
                } else {
                    view.layout(i - measuredWidth, i2, i3 - measuredWidth, i4);
                    return;
                }
            default:
                return;
        }
    }

    /* access modifiers changed from: 0000 */
    public void ensureMeasuredSizeCache(int i) {
        if (this.mMeasuredSizeCache == null) {
            if (i < 10) {
                i = 10;
            }
            this.mMeasuredSizeCache = new long[i];
        } else if (this.mMeasuredSizeCache.length < i) {
            int length = this.mMeasuredSizeCache.length * 2;
            if (length >= i) {
                i = length;
            }
            this.mMeasuredSizeCache = Arrays.copyOf(this.mMeasuredSizeCache, i);
        }
    }

    /* access modifiers changed from: 0000 */
    public void ensureMeasureSpecCache(int i) {
        if (this.mMeasureSpecCache == null) {
            if (i < 10) {
                i = 10;
            }
            this.mMeasureSpecCache = new long[i];
        } else if (this.mMeasureSpecCache.length < i) {
            int length = this.mMeasureSpecCache.length * 2;
            if (length >= i) {
                i = length;
            }
            this.mMeasureSpecCache = Arrays.copyOf(this.mMeasureSpecCache, i);
        }
    }

    private void updateMeasureCache(int i, int i2, int i3, View view) {
        if (this.mMeasureSpecCache != null) {
            this.mMeasureSpecCache[i] = makeCombinedLong(i2, i3);
        }
        if (this.mMeasuredSizeCache != null) {
            this.mMeasuredSizeCache[i] = makeCombinedLong(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    /* access modifiers changed from: 0000 */
    public void ensureIndexToFlexLine(int i) {
        if (this.mIndexToFlexLine == null) {
            if (i < 10) {
                i = 10;
            }
            this.mIndexToFlexLine = new int[i];
        } else if (this.mIndexToFlexLine.length < i) {
            int length = this.mIndexToFlexLine.length * 2;
            if (length >= i) {
                i = length;
            }
            this.mIndexToFlexLine = Arrays.copyOf(this.mIndexToFlexLine, i);
        }
    }

    /* access modifiers changed from: 0000 */
    public void clearFlexLines(List<FlexLine> list, int i) {
        int i2 = this.mIndexToFlexLine[i];
        if (i2 == -1) {
            i2 = 0;
        }
        for (int size = list.size() - 1; size >= i2; size--) {
            list.remove(size);
        }
        int length = this.mIndexToFlexLine.length - 1;
        if (i > length) {
            Arrays.fill(this.mIndexToFlexLine, -1);
        } else {
            Arrays.fill(this.mIndexToFlexLine, i, length, -1);
        }
        int length2 = this.mMeasureSpecCache.length - 1;
        if (i > length2) {
            Arrays.fill(this.mMeasureSpecCache, 0);
        } else {
            Arrays.fill(this.mMeasureSpecCache, i, length2, 0);
        }
    }
}
