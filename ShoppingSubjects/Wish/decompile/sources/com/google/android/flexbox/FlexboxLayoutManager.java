package com.google.android.flexbox;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager.Properties;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.MeasureSpec;
import java.util.ArrayList;
import java.util.List;

public class FlexboxLayoutManager extends LayoutManager implements FlexContainer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Rect TEMP_RECT = new Rect();
    private int mAlignItems;
    private AnchorInfo mAnchorInfo;
    private final Context mContext;
    private int mDirtyPosition;
    /* access modifiers changed from: private */
    public int mFlexDirection;
    /* access modifiers changed from: private */
    public List<FlexLine> mFlexLines;
    private FlexLinesResult mFlexLinesResult;
    /* access modifiers changed from: private */
    public int mFlexWrap;
    /* access modifiers changed from: private */
    public final FlexboxHelper mFlexboxHelper;
    private boolean mFromBottomToTop;
    /* access modifiers changed from: private */
    public boolean mIsRtl;
    private int mJustifyContent;
    private int mLastHeight;
    private int mLastWidth;
    private LayoutState mLayoutState;
    /* access modifiers changed from: private */
    public OrientationHelper mOrientationHelper;
    private View mParent;
    private SavedState mPendingSavedState;
    private int mPendingScrollPosition;
    private int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private Recycler mRecycler;
    private State mState;
    private OrientationHelper mSubOrientationHelper;
    private SparseArray<View> mViewCache;

    private class AnchorInfo {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        /* access modifiers changed from: private */
        public boolean mAssignedFromSavedState;
        /* access modifiers changed from: private */
        public int mCoordinate;
        /* access modifiers changed from: private */
        public int mFlexLinePosition;
        /* access modifiers changed from: private */
        public boolean mLayoutFromEnd;
        /* access modifiers changed from: private */
        public int mPerpendicularCoordinate;
        /* access modifiers changed from: private */
        public int mPosition;
        /* access modifiers changed from: private */
        public boolean mValid;

        static {
            Class<FlexboxLayoutManager> cls = FlexboxLayoutManager.class;
        }

        private AnchorInfo() {
            this.mPerpendicularCoordinate = 0;
        }

        /* access modifiers changed from: private */
        public void reset() {
            this.mPosition = -1;
            this.mFlexLinePosition = -1;
            this.mCoordinate = Integer.MIN_VALUE;
            boolean z = false;
            this.mValid = false;
            this.mAssignedFromSavedState = false;
            if (FlexboxLayoutManager.this.isMainAxisDirectionHorizontal()) {
                if (FlexboxLayoutManager.this.mFlexWrap == 0) {
                    if (FlexboxLayoutManager.this.mFlexDirection == 1) {
                        z = true;
                    }
                    this.mLayoutFromEnd = z;
                    return;
                }
                if (FlexboxLayoutManager.this.mFlexWrap == 2) {
                    z = true;
                }
                this.mLayoutFromEnd = z;
            } else if (FlexboxLayoutManager.this.mFlexWrap == 0) {
                if (FlexboxLayoutManager.this.mFlexDirection == 3) {
                    z = true;
                }
                this.mLayoutFromEnd = z;
            } else {
                if (FlexboxLayoutManager.this.mFlexWrap == 2) {
                    z = true;
                }
                this.mLayoutFromEnd = z;
            }
        }

        /* access modifiers changed from: private */
        public void assignCoordinateFromPadding() {
            int i;
            int i2;
            if (FlexboxLayoutManager.this.isMainAxisDirectionHorizontal() || !FlexboxLayoutManager.this.mIsRtl) {
                if (this.mLayoutFromEnd) {
                    i = FlexboxLayoutManager.this.mOrientationHelper.getEndAfterPadding();
                } else {
                    i = FlexboxLayoutManager.this.mOrientationHelper.getStartAfterPadding();
                }
                this.mCoordinate = i;
                return;
            }
            if (this.mLayoutFromEnd) {
                i2 = FlexboxLayoutManager.this.mOrientationHelper.getEndAfterPadding();
            } else {
                i2 = FlexboxLayoutManager.this.getWidth() - FlexboxLayoutManager.this.mOrientationHelper.getStartAfterPadding();
            }
            this.mCoordinate = i2;
        }

        /* access modifiers changed from: private */
        public void assignFromView(View view) {
            if (FlexboxLayoutManager.this.isMainAxisDirectionHorizontal() || !FlexboxLayoutManager.this.mIsRtl) {
                if (this.mLayoutFromEnd) {
                    this.mCoordinate = FlexboxLayoutManager.this.mOrientationHelper.getDecoratedEnd(view) + FlexboxLayoutManager.this.mOrientationHelper.getTotalSpaceChange();
                } else {
                    this.mCoordinate = FlexboxLayoutManager.this.mOrientationHelper.getDecoratedStart(view);
                }
            } else if (this.mLayoutFromEnd) {
                this.mCoordinate = FlexboxLayoutManager.this.mOrientationHelper.getDecoratedStart(view) + FlexboxLayoutManager.this.mOrientationHelper.getTotalSpaceChange();
            } else {
                this.mCoordinate = FlexboxLayoutManager.this.mOrientationHelper.getDecoratedEnd(view);
            }
            this.mPosition = FlexboxLayoutManager.this.getPosition(view);
            int i = 0;
            this.mAssignedFromSavedState = false;
            int i2 = FlexboxLayoutManager.this.mFlexboxHelper.mIndexToFlexLine[this.mPosition];
            if (i2 != -1) {
                i = i2;
            }
            this.mFlexLinePosition = i;
            if (FlexboxLayoutManager.this.mFlexLines.size() > this.mFlexLinePosition) {
                this.mPosition = ((FlexLine) FlexboxLayoutManager.this.mFlexLines.get(this.mFlexLinePosition)).mFirstIndex;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("AnchorInfo{mPosition=");
            sb.append(this.mPosition);
            sb.append(", mFlexLinePosition=");
            sb.append(this.mFlexLinePosition);
            sb.append(", mCoordinate=");
            sb.append(this.mCoordinate);
            sb.append(", mPerpendicularCoordinate=");
            sb.append(this.mPerpendicularCoordinate);
            sb.append(", mLayoutFromEnd=");
            sb.append(this.mLayoutFromEnd);
            sb.append(", mValid=");
            sb.append(this.mValid);
            sb.append(", mAssignedFromSavedState=");
            sb.append(this.mAssignedFromSavedState);
            sb.append('}');
            return sb.toString();
        }
    }

    public static class LayoutParams extends android.support.v7.widget.RecyclerView.LayoutParams implements FlexItem {
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
        private boolean mWrapBefore;

        public int describeContents() {
            return 0;
        }

        public int getOrder() {
            return 1;
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
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

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public void writeToParcel(Parcel parcel, int i) {
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
            super(-2, -2);
            this.mFlexGrow = parcel.readFloat();
            this.mFlexShrink = parcel.readFloat();
            this.mAlignSelf = parcel.readInt();
            this.mFlexBasisPercent = parcel.readFloat();
            this.mMinWidth = parcel.readInt();
            this.mMinHeight = parcel.readInt();
            this.mMaxWidth = parcel.readInt();
            this.mMaxHeight = parcel.readInt();
            this.mWrapBefore = parcel.readByte() != 0;
            this.bottomMargin = parcel.readInt();
            this.leftMargin = parcel.readInt();
            this.rightMargin = parcel.readInt();
            this.topMargin = parcel.readInt();
            this.height = parcel.readInt();
            this.width = parcel.readInt();
        }
    }

    private static class LayoutState {
        /* access modifiers changed from: private */
        public int mAvailable;
        /* access modifiers changed from: private */
        public int mFlexLinePosition;
        /* access modifiers changed from: private */
        public boolean mInfinite;
        /* access modifiers changed from: private */
        public int mItemDirection;
        /* access modifiers changed from: private */
        public int mLastScrollDelta;
        /* access modifiers changed from: private */
        public int mLayoutDirection;
        /* access modifiers changed from: private */
        public int mOffset;
        /* access modifiers changed from: private */
        public int mPosition;
        /* access modifiers changed from: private */
        public int mScrollingOffset;
        /* access modifiers changed from: private */
        public boolean mShouldRecycle;

        private LayoutState() {
            this.mItemDirection = 1;
            this.mLayoutDirection = 1;
        }

        /* access modifiers changed from: private */
        public boolean hasMore(State state, List<FlexLine> list) {
            return this.mPosition >= 0 && this.mPosition < state.getItemCount() && this.mFlexLinePosition >= 0 && this.mFlexLinePosition < list.size();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("LayoutState{mAvailable=");
            sb.append(this.mAvailable);
            sb.append(", mFlexLinePosition=");
            sb.append(this.mFlexLinePosition);
            sb.append(", mPosition=");
            sb.append(this.mPosition);
            sb.append(", mOffset=");
            sb.append(this.mOffset);
            sb.append(", mScrollingOffset=");
            sb.append(this.mScrollingOffset);
            sb.append(", mLastScrollDelta=");
            sb.append(this.mLastScrollDelta);
            sb.append(", mItemDirection=");
            sb.append(this.mItemDirection);
            sb.append(", mLayoutDirection=");
            sb.append(this.mLayoutDirection);
            sb.append('}');
            return sb.toString();
        }
    }

    private static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        /* access modifiers changed from: private */
        public int mAnchorOffset;
        /* access modifiers changed from: private */
        public int mAnchorPosition;

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mAnchorOffset);
        }

        SavedState() {
        }

        private SavedState(Parcel parcel) {
            this.mAnchorPosition = parcel.readInt();
            this.mAnchorOffset = parcel.readInt();
        }

        private SavedState(SavedState savedState) {
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mAnchorOffset = savedState.mAnchorOffset;
        }

        /* access modifiers changed from: private */
        public void invalidateAnchor() {
            this.mAnchorPosition = -1;
        }

        /* access modifiers changed from: private */
        public boolean hasValidAnchor(int i) {
            return this.mAnchorPosition >= 0 && this.mAnchorPosition < i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("SavedState{mAnchorPosition=");
            sb.append(this.mAnchorPosition);
            sb.append(", mAnchorOffset=");
            sb.append(this.mAnchorOffset);
            sb.append('}');
            return sb.toString();
        }
    }

    public int getAlignContent() {
        return 5;
    }

    public void onNewFlexLineAdded(FlexLine flexLine) {
    }

    public FlexboxLayoutManager(Context context) {
        this(context, 0, 1);
    }

    public FlexboxLayoutManager(Context context, int i) {
        this(context, i, 1);
    }

    public FlexboxLayoutManager(Context context, int i, int i2) {
        this.mFlexLines = new ArrayList();
        this.mFlexboxHelper = new FlexboxHelper(this);
        this.mAnchorInfo = new AnchorInfo();
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLastWidth = Integer.MIN_VALUE;
        this.mLastHeight = Integer.MIN_VALUE;
        this.mViewCache = new SparseArray<>();
        this.mDirtyPosition = -1;
        this.mFlexLinesResult = new FlexLinesResult();
        setFlexDirection(i);
        setFlexWrap(i2);
        setAlignItems(4);
        setAutoMeasureEnabled(true);
        this.mContext = context;
    }

    public FlexboxLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mFlexLines = new ArrayList();
        this.mFlexboxHelper = new FlexboxHelper(this);
        this.mAnchorInfo = new AnchorInfo();
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLastWidth = Integer.MIN_VALUE;
        this.mLastHeight = Integer.MIN_VALUE;
        this.mViewCache = new SparseArray<>();
        this.mDirtyPosition = -1;
        this.mFlexLinesResult = new FlexLinesResult();
        Properties properties = getProperties(context, attributeSet, i, i2);
        switch (properties.orientation) {
            case 0:
                if (!properties.reverseLayout) {
                    setFlexDirection(0);
                    break;
                } else {
                    setFlexDirection(1);
                    break;
                }
            case 1:
                if (!properties.reverseLayout) {
                    setFlexDirection(2);
                    break;
                } else {
                    setFlexDirection(3);
                    break;
                }
        }
        setFlexWrap(1);
        setAlignItems(4);
        setAutoMeasureEnabled(true);
        this.mContext = context;
    }

    public int getFlexDirection() {
        return this.mFlexDirection;
    }

    public void setFlexDirection(int i) {
        if (this.mFlexDirection != i) {
            removeAllViews();
            this.mFlexDirection = i;
            this.mOrientationHelper = null;
            this.mSubOrientationHelper = null;
            clearFlexLines();
            requestLayout();
        }
    }

    public int getFlexWrap() {
        return this.mFlexWrap;
    }

    public void setFlexWrap(int i) {
        if (i == 2) {
            throw new UnsupportedOperationException("wrap_reverse is not supported in FlexboxLayoutManager");
        } else if (this.mFlexWrap != i) {
            if (this.mFlexWrap == 0 || i == 0) {
                removeAllViews();
                clearFlexLines();
            }
            this.mFlexWrap = i;
            this.mOrientationHelper = null;
            this.mSubOrientationHelper = null;
            requestLayout();
        }
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
            if (this.mAlignItems == 4 || i == 4) {
                removeAllViews();
                clearFlexLines();
            }
            this.mAlignItems = i;
            requestLayout();
        }
    }

    public int getDecorationLengthMainAxis(View view, int i, int i2) {
        if (isMainAxisDirectionHorizontal()) {
            return getLeftDecorationWidth(view) + getRightDecorationWidth(view);
        }
        return getTopDecorationHeight(view) + getBottomDecorationHeight(view);
    }

    public int getDecorationLengthCrossAxis(View view) {
        if (isMainAxisDirectionHorizontal()) {
            return getTopDecorationHeight(view) + getBottomDecorationHeight(view);
        }
        return getLeftDecorationWidth(view) + getRightDecorationWidth(view);
    }

    public void onNewFlexItemAdded(View view, int i, int i2, FlexLine flexLine) {
        calculateItemDecorationsForChild(view, TEMP_RECT);
        if (isMainAxisDirectionHorizontal()) {
            int leftDecorationWidth = getLeftDecorationWidth(view) + getRightDecorationWidth(view);
            flexLine.mMainSize += leftDecorationWidth;
            flexLine.mDividerLengthInMainSize += leftDecorationWidth;
            return;
        }
        int topDecorationHeight = getTopDecorationHeight(view) + getBottomDecorationHeight(view);
        flexLine.mMainSize += topDecorationHeight;
        flexLine.mDividerLengthInMainSize += topDecorationHeight;
    }

    public int getFlexItemCount() {
        return this.mState.getItemCount();
    }

    public View getFlexItemAt(int i) {
        View view = (View) this.mViewCache.get(i);
        if (view != null) {
            return view;
        }
        return this.mRecycler.getViewForPosition(i);
    }

    public View getReorderedFlexItemAt(int i) {
        return getFlexItemAt(i);
    }

    public int getChildWidthMeasureSpec(int i, int i2, int i3) {
        return getChildMeasureSpec(getWidth(), getWidthMode(), i2, i3, canScrollHorizontally());
    }

    public int getChildHeightMeasureSpec(int i, int i2, int i3) {
        return getChildMeasureSpec(getHeight(), getHeightMode(), i2, i3, canScrollVertically());
    }

    public int getLargestMainSize() {
        if (this.mFlexLines.size() == 0) {
            return 0;
        }
        int i = Integer.MIN_VALUE;
        int size = this.mFlexLines.size();
        for (int i2 = 0; i2 < size; i2++) {
            i = Math.max(i, ((FlexLine) this.mFlexLines.get(i2)).mMainSize);
        }
        return i;
    }

    public int getSumOfCrossSize() {
        int i = 0;
        for (int i2 = 0; i2 < this.mFlexLines.size(); i2++) {
            i += ((FlexLine) this.mFlexLines.get(i2)).mCrossSize;
        }
        return i;
    }

    public void setFlexLines(List<FlexLine> list) {
        this.mFlexLines = list;
    }

    public List<FlexLine> getFlexLinesInternal() {
        return this.mFlexLines;
    }

    public void updateViewCache(int i, View view) {
        this.mViewCache.put(i, view);
    }

    public android.support.v7.widget.RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public android.support.v7.widget.RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public boolean checkLayoutParams(android.support.v7.widget.RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void onAdapterChanged(Adapter adapter, Adapter adapter2) {
        removeAllViews();
    }

    public Parcelable onSaveInstanceState() {
        if (this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        SavedState savedState = new SavedState();
        if (getChildCount() > 0) {
            View childClosestToStart = getChildClosestToStart();
            savedState.mAnchorPosition = getPosition(childClosestToStart);
            savedState.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
        } else {
            savedState.invalidateAnchor();
        }
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState) parcelable;
            requestLayout();
        }
    }

    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        super.onItemsAdded(recyclerView, i, i2);
        updateDirtyPosition(i);
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
        super.onItemsUpdated(recyclerView, i, i2, obj);
        updateDirtyPosition(i);
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2) {
        super.onItemsUpdated(recyclerView, i, i2);
        updateDirtyPosition(i);
    }

    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        super.onItemsRemoved(recyclerView, i, i2);
        updateDirtyPosition(i);
    }

    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        super.onItemsMoved(recyclerView, i, i2, i3);
        updateDirtyPosition(Math.min(i, i2));
    }

    private void updateDirtyPosition(int i) {
        int findFirstVisibleItemPosition = findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = findLastVisibleItemPosition();
        if (i < findLastVisibleItemPosition) {
            int childCount = getChildCount();
            this.mFlexboxHelper.ensureMeasureSpecCache(childCount);
            this.mFlexboxHelper.ensureMeasuredSizeCache(childCount);
            this.mFlexboxHelper.ensureIndexToFlexLine(childCount);
            if (i < this.mFlexboxHelper.mIndexToFlexLine.length) {
                this.mDirtyPosition = i;
                View childClosestToStart = getChildClosestToStart();
                if (childClosestToStart != null) {
                    if (findFirstVisibleItemPosition > i || i > findLastVisibleItemPosition) {
                        this.mPendingScrollPosition = getPosition(childClosestToStart);
                        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
                            this.mPendingScrollPositionOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
                        } else {
                            this.mPendingScrollPositionOffset = this.mOrientationHelper.getDecoratedEnd(childClosestToStart) + this.mOrientationHelper.getEndPadding();
                        }
                    }
                }
            }
        }
    }

    public void onLayoutChildren(Recycler recycler, State state) {
        int i;
        int i2;
        this.mRecycler = recycler;
        this.mState = state;
        int itemCount = state.getItemCount();
        if (itemCount != 0 || !state.isPreLayout()) {
            resolveLayoutDirection();
            ensureOrientationHelper();
            ensureLayoutState();
            this.mFlexboxHelper.ensureMeasureSpecCache(itemCount);
            this.mFlexboxHelper.ensureMeasuredSizeCache(itemCount);
            this.mFlexboxHelper.ensureIndexToFlexLine(itemCount);
            this.mLayoutState.mShouldRecycle = false;
            if (this.mPendingSavedState != null && this.mPendingSavedState.hasValidAnchor(itemCount)) {
                this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
            }
            if (!(this.mAnchorInfo.mValid && this.mPendingScrollPosition == -1 && this.mPendingSavedState == null)) {
                this.mAnchorInfo.reset();
                updateAnchorInfoForLayout(state, this.mAnchorInfo);
                this.mAnchorInfo.mValid = true;
            }
            detachAndScrapAttachedViews(recycler);
            if (this.mAnchorInfo.mLayoutFromEnd) {
                updateLayoutStateToFillStart(this.mAnchorInfo, false, true);
            } else {
                updateLayoutStateToFillEnd(this.mAnchorInfo, false, true);
            }
            updateFlexLines(itemCount);
            if (this.mAnchorInfo.mLayoutFromEnd) {
                fill(recycler, state, this.mLayoutState);
                i2 = this.mLayoutState.mOffset;
                updateLayoutStateToFillEnd(this.mAnchorInfo, true, false);
                fill(recycler, state, this.mLayoutState);
                i = this.mLayoutState.mOffset;
            } else {
                fill(recycler, state, this.mLayoutState);
                i = this.mLayoutState.mOffset;
                updateLayoutStateToFillStart(this.mAnchorInfo, true, false);
                fill(recycler, state, this.mLayoutState);
                i2 = this.mLayoutState.mOffset;
            }
            if (getChildCount() > 0) {
                if (this.mAnchorInfo.mLayoutFromEnd) {
                    fixLayoutStartGap(i2 + fixLayoutEndGap(i, recycler, state, true), recycler, state, false);
                } else {
                    fixLayoutEndGap(i + fixLayoutStartGap(i2, recycler, state, true), recycler, state, false);
                }
            }
        }
    }

    private int fixLayoutStartGap(int i, Recycler recycler, State state, boolean z) {
        int i2;
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            int startAfterPadding = i - this.mOrientationHelper.getStartAfterPadding();
            if (startAfterPadding <= 0) {
                return 0;
            }
            i2 = -handleScrollingCrossAxis(startAfterPadding, recycler, state);
        } else {
            int endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i;
            if (endAfterPadding <= 0) {
                return 0;
            }
            i2 = handleScrollingCrossAxis(-endAfterPadding, recycler, state);
        }
        int i3 = i + i2;
        if (z) {
            int startAfterPadding2 = i3 - this.mOrientationHelper.getStartAfterPadding();
            if (startAfterPadding2 > 0) {
                this.mOrientationHelper.offsetChildren(-startAfterPadding2);
                return i2 - startAfterPadding2;
            }
        }
        return i2;
    }

    private int fixLayoutEndGap(int i, Recycler recycler, State state, boolean z) {
        int i2;
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            int startAfterPadding = i - this.mOrientationHelper.getStartAfterPadding();
            if (startAfterPadding <= 0) {
                return 0;
            }
            i2 = handleScrollingCrossAxis(startAfterPadding, recycler, state);
        } else {
            int endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i;
            if (endAfterPadding <= 0) {
                return 0;
            }
            i2 = -handleScrollingCrossAxis(-endAfterPadding, recycler, state);
        }
        int i3 = i + i2;
        if (z) {
            int endAfterPadding2 = this.mOrientationHelper.getEndAfterPadding() - i3;
            if (endAfterPadding2 > 0) {
                this.mOrientationHelper.offsetChildren(endAfterPadding2);
                return endAfterPadding2 + i2;
            }
        }
        return i2;
    }

    private void updateFlexLines(int i) {
        int access$1200;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        int width = getWidth();
        int height = getHeight();
        boolean z = false;
        if (isMainAxisDirectionHorizontal()) {
            if (!(this.mLastWidth == Integer.MIN_VALUE || this.mLastWidth == width)) {
                z = true;
            }
            if (this.mLayoutState.mInfinite) {
                access$1200 = this.mContext.getResources().getDisplayMetrics().heightPixels;
            } else {
                access$1200 = this.mLayoutState.mAvailable;
            }
        } else {
            if (!(this.mLastHeight == Integer.MIN_VALUE || this.mLastHeight == height)) {
                z = true;
            }
            if (this.mLayoutState.mInfinite) {
                access$1200 = this.mContext.getResources().getDisplayMetrics().widthPixels;
            } else {
                access$1200 = this.mLayoutState.mAvailable;
            }
        }
        int i2 = access$1200;
        this.mLastWidth = width;
        this.mLastHeight = height;
        if (this.mDirtyPosition != -1 || (this.mPendingScrollPosition == -1 && !z)) {
            int min = this.mDirtyPosition != -1 ? Math.min(this.mDirtyPosition, this.mAnchorInfo.mPosition) : this.mAnchorInfo.mPosition;
            this.mFlexLinesResult.reset();
            if (isMainAxisDirectionHorizontal()) {
                if (this.mFlexLines.size() > 0) {
                    this.mFlexboxHelper.clearFlexLines(this.mFlexLines, min);
                    this.mFlexboxHelper.calculateFlexLines(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i2, min, this.mAnchorInfo.mPosition, this.mFlexLines);
                } else {
                    this.mFlexboxHelper.ensureIndexToFlexLine(i);
                    this.mFlexboxHelper.calculateHorizontalFlexLines(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i2, 0, this.mFlexLines);
                }
            } else if (this.mFlexLines.size() > 0) {
                this.mFlexboxHelper.clearFlexLines(this.mFlexLines, min);
                this.mFlexboxHelper.calculateFlexLines(this.mFlexLinesResult, makeMeasureSpec2, makeMeasureSpec, i2, min, this.mAnchorInfo.mPosition, this.mFlexLines);
            } else {
                this.mFlexboxHelper.ensureIndexToFlexLine(i);
                this.mFlexboxHelper.calculateVerticalFlexLines(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i2, 0, this.mFlexLines);
            }
            this.mFlexLines = this.mFlexLinesResult.mFlexLines;
            this.mFlexboxHelper.determineMainSize(makeMeasureSpec, makeMeasureSpec2, min);
            this.mFlexboxHelper.stretchViews(min);
        } else if (!this.mAnchorInfo.mLayoutFromEnd) {
            this.mFlexLines.clear();
            this.mFlexLinesResult.reset();
            if (isMainAxisDirectionHorizontal()) {
                this.mFlexboxHelper.calculateHorizontalFlexLinesToIndex(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i2, this.mAnchorInfo.mPosition, this.mFlexLines);
            } else {
                this.mFlexboxHelper.calculateVerticalFlexLinesToIndex(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i2, this.mAnchorInfo.mPosition, this.mFlexLines);
            }
            this.mFlexLines = this.mFlexLinesResult.mFlexLines;
            this.mFlexboxHelper.determineMainSize(makeMeasureSpec, makeMeasureSpec2);
            this.mFlexboxHelper.stretchViews();
            this.mAnchorInfo.mFlexLinePosition = this.mFlexboxHelper.mIndexToFlexLine[this.mAnchorInfo.mPosition];
            this.mLayoutState.mFlexLinePosition = this.mAnchorInfo.mFlexLinePosition;
        }
    }

    public void onLayoutCompleted(State state) {
        super.onLayoutCompleted(state);
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mDirtyPosition = -1;
        this.mAnchorInfo.reset();
        this.mViewCache.clear();
    }

    private void resolveLayoutDirection() {
        int layoutDirection = getLayoutDirection();
        boolean z = false;
        switch (this.mFlexDirection) {
            case 0:
                this.mIsRtl = layoutDirection == 1;
                if (this.mFlexWrap == 2) {
                    z = true;
                }
                this.mFromBottomToTop = z;
                return;
            case 1:
                this.mIsRtl = layoutDirection != 1;
                if (this.mFlexWrap == 2) {
                    z = true;
                }
                this.mFromBottomToTop = z;
                return;
            case 2:
                this.mIsRtl = layoutDirection == 1;
                if (this.mFlexWrap == 2) {
                    this.mIsRtl = !this.mIsRtl;
                }
                this.mFromBottomToTop = false;
                return;
            case 3:
                if (layoutDirection == 1) {
                    z = true;
                }
                this.mIsRtl = z;
                if (this.mFlexWrap == 2) {
                    this.mIsRtl = !this.mIsRtl;
                }
                this.mFromBottomToTop = true;
                return;
            default:
                this.mIsRtl = false;
                this.mFromBottomToTop = false;
                return;
        }
    }

    private void updateAnchorInfoForLayout(State state, AnchorInfo anchorInfo) {
        if (!updateAnchorFromPendingState(state, anchorInfo, this.mPendingSavedState) && !updateAnchorFromChildren(state, anchorInfo)) {
            anchorInfo.assignCoordinateFromPadding();
            anchorInfo.mPosition = 0;
            anchorInfo.mFlexLinePosition = 0;
        }
    }

    private boolean updateAnchorFromPendingState(State state, AnchorInfo anchorInfo, SavedState savedState) {
        int i;
        boolean z = false;
        if (state.isPreLayout() || this.mPendingScrollPosition == -1) {
            return false;
        }
        if (this.mPendingScrollPosition < 0 || this.mPendingScrollPosition >= state.getItemCount()) {
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            return false;
        }
        anchorInfo.mPosition = this.mPendingScrollPosition;
        anchorInfo.mFlexLinePosition = this.mFlexboxHelper.mIndexToFlexLine[anchorInfo.mPosition];
        if (this.mPendingSavedState != null && this.mPendingSavedState.hasValidAnchor(state.getItemCount())) {
            anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + savedState.mAnchorOffset;
            anchorInfo.mAssignedFromSavedState = true;
            anchorInfo.mFlexLinePosition = -1;
            return true;
        } else if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
            View findViewByPosition = findViewByPosition(this.mPendingScrollPosition);
            if (findViewByPosition == null) {
                if (getChildCount() > 0) {
                    if (this.mPendingScrollPosition < getPosition(getChildAt(0))) {
                        z = true;
                    }
                    anchorInfo.mLayoutFromEnd = z;
                }
                anchorInfo.assignCoordinateFromPadding();
            } else if (this.mOrientationHelper.getDecoratedMeasurement(findViewByPosition) > this.mOrientationHelper.getTotalSpace()) {
                anchorInfo.assignCoordinateFromPadding();
                return true;
            } else if (this.mOrientationHelper.getDecoratedStart(findViewByPosition) - this.mOrientationHelper.getStartAfterPadding() < 0) {
                anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                anchorInfo.mLayoutFromEnd = false;
                return true;
            } else if (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(findViewByPosition) < 0) {
                anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                anchorInfo.mLayoutFromEnd = true;
                return true;
            } else {
                if (anchorInfo.mLayoutFromEnd) {
                    i = this.mOrientationHelper.getDecoratedEnd(findViewByPosition) + this.mOrientationHelper.getTotalSpaceChange();
                } else {
                    i = this.mOrientationHelper.getDecoratedStart(findViewByPosition);
                }
                anchorInfo.mCoordinate = i;
            }
            return true;
        } else {
            if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
                anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
            } else {
                anchorInfo.mCoordinate = this.mPendingScrollPositionOffset - this.mOrientationHelper.getEndPadding();
            }
            return true;
        }
    }

    private boolean updateAnchorFromChildren(State state, AnchorInfo anchorInfo) {
        View view;
        int i;
        boolean z = false;
        if (getChildCount() == 0) {
            return false;
        }
        if (anchorInfo.mLayoutFromEnd) {
            view = findLastReferenceChild(state.getItemCount());
        } else {
            view = findFirstReferenceChild(state.getItemCount());
        }
        if (view == null) {
            return false;
        }
        anchorInfo.assignFromView(view);
        if (!state.isPreLayout() && supportsPredictiveItemAnimations()) {
            if (this.mOrientationHelper.getDecoratedStart(view) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(view) < this.mOrientationHelper.getStartAfterPadding()) {
                z = true;
            }
            if (z) {
                if (anchorInfo.mLayoutFromEnd) {
                    i = this.mOrientationHelper.getEndAfterPadding();
                } else {
                    i = this.mOrientationHelper.getStartAfterPadding();
                }
                anchorInfo.mCoordinate = i;
            }
        }
        return true;
    }

    private View findFirstReferenceChild(int i) {
        View findReferenceChild = findReferenceChild(0, getChildCount(), i);
        if (findReferenceChild == null) {
            return null;
        }
        int i2 = this.mFlexboxHelper.mIndexToFlexLine[getPosition(findReferenceChild)];
        if (i2 == -1) {
            return null;
        }
        return findFirstReferenceViewInLine(findReferenceChild, (FlexLine) this.mFlexLines.get(i2));
    }

    private View findLastReferenceChild(int i) {
        View findReferenceChild = findReferenceChild(getChildCount() - 1, -1, i);
        if (findReferenceChild == null) {
            return null;
        }
        return findLastReferenceViewInLine(findReferenceChild, (FlexLine) this.mFlexLines.get(this.mFlexboxHelper.mIndexToFlexLine[getPosition(findReferenceChild)]));
    }

    private View findReferenceChild(int i, int i2, int i3) {
        ensureOrientationHelper();
        ensureLayoutState();
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            if (position >= 0 && position < i3) {
                if (((android.support.v7.widget.RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else if (this.mOrientationHelper.getDecoratedStart(childAt) >= startAfterPadding && this.mOrientationHelper.getDecoratedEnd(childAt) <= endAfterPadding) {
                    return childAt;
                } else {
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i += i4;
        }
        if (view == null) {
            view = view2;
        }
        return view;
    }

    private View getChildClosestToStart() {
        return getChildAt(0);
    }

    private int fill(Recycler recycler, State state, LayoutState layoutState) {
        if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
            if (layoutState.mAvailable < 0) {
                layoutState.mScrollingOffset = layoutState.mScrollingOffset + layoutState.mAvailable;
            }
            recycleByLayoutState(recycler, layoutState);
        }
        int access$1200 = layoutState.mAvailable;
        int access$12002 = layoutState.mAvailable;
        int i = 0;
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        while (true) {
            if ((access$12002 > 0 || this.mLayoutState.mInfinite) && layoutState.hasMore(state, this.mFlexLines)) {
                FlexLine flexLine = (FlexLine) this.mFlexLines.get(layoutState.mFlexLinePosition);
                layoutState.mPosition = flexLine.mFirstIndex;
                i += layoutFlexLine(flexLine, layoutState);
                if (isMainAxisDirectionHorizontal || !this.mIsRtl) {
                    layoutState.mOffset = layoutState.mOffset + (flexLine.getCrossSize() * layoutState.mLayoutDirection);
                } else {
                    layoutState.mOffset = layoutState.mOffset - (flexLine.getCrossSize() * layoutState.mLayoutDirection);
                }
                access$12002 -= flexLine.getCrossSize();
            }
        }
        layoutState.mAvailable = layoutState.mAvailable - i;
        if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
            layoutState.mScrollingOffset = layoutState.mScrollingOffset + i;
            if (layoutState.mAvailable < 0) {
                layoutState.mScrollingOffset = layoutState.mScrollingOffset + layoutState.mAvailable;
            }
            recycleByLayoutState(recycler, layoutState);
        }
        return access$1200 - layoutState.mAvailable;
    }

    private void recycleByLayoutState(Recycler recycler, LayoutState layoutState) {
        if (layoutState.mShouldRecycle) {
            if (layoutState.mLayoutDirection == -1) {
                recycleFlexLinesFromEnd(recycler, layoutState);
            } else {
                recycleFlexLinesFromStart(recycler, layoutState);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0060, code lost:
        r2 = r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void recycleFlexLinesFromStart(android.support.v7.widget.RecyclerView.Recycler r9, com.google.android.flexbox.FlexboxLayoutManager.LayoutState r10) {
        /*
            r8 = this;
            int r0 = r10.mScrollingOffset
            if (r0 >= 0) goto L_0x0007
            return
        L_0x0007:
            int r0 = r8.getChildCount()
            if (r0 != 0) goto L_0x000e
            return
        L_0x000e:
            r1 = 0
            android.view.View r2 = r8.getChildAt(r1)
            com.google.android.flexbox.FlexboxHelper r3 = r8.mFlexboxHelper
            int[] r3 = r3.mIndexToFlexLine
            int r2 = r8.getPosition(r2)
            r2 = r3[r2]
            r3 = -1
            if (r2 != r3) goto L_0x0021
            return
        L_0x0021:
            java.util.List<com.google.android.flexbox.FlexLine> r4 = r8.mFlexLines
            java.lang.Object r4 = r4.get(r2)
            com.google.android.flexbox.FlexLine r4 = (com.google.android.flexbox.FlexLine) r4
            r3 = r2
            r2 = 0
            r5 = -1
        L_0x002c:
            if (r2 >= r0) goto L_0x0060
            android.view.View r6 = r8.getChildAt(r2)
            int r7 = r10.mScrollingOffset
            boolean r7 = r8.canViewBeRecycledFromStart(r6, r7)
            if (r7 == 0) goto L_0x0060
            int r7 = r4.mLastIndex
            int r6 = r8.getPosition(r6)
            if (r7 != r6) goto L_0x005d
            java.util.List<com.google.android.flexbox.FlexLine> r4 = r8.mFlexLines
            int r4 = r4.size()
            int r4 = r4 + -1
            if (r3 < r4) goto L_0x004f
            goto L_0x0061
        L_0x004f:
            int r4 = r10.mLayoutDirection
            int r3 = r3 + r4
            java.util.List<com.google.android.flexbox.FlexLine> r4 = r8.mFlexLines
            java.lang.Object r4 = r4.get(r3)
            com.google.android.flexbox.FlexLine r4 = (com.google.android.flexbox.FlexLine) r4
            r5 = r2
        L_0x005d:
            int r2 = r2 + 1
            goto L_0x002c
        L_0x0060:
            r2 = r5
        L_0x0061:
            r8.recycleChildren(r9, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.recycleFlexLinesFromStart(android.support.v7.widget.RecyclerView$Recycler, com.google.android.flexbox.FlexboxLayoutManager$LayoutState):void");
    }

    private boolean canViewBeRecycledFromStart(View view, int i) {
        boolean z = false;
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            if (this.mOrientationHelper.getDecoratedEnd(view) <= i) {
                z = true;
            }
            return z;
        }
        if (this.mOrientationHelper.getEnd() - this.mOrientationHelper.getDecoratedStart(view) <= i) {
            z = true;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0060, code lost:
        r0 = r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void recycleFlexLinesFromEnd(android.support.v7.widget.RecyclerView.Recycler r8, com.google.android.flexbox.FlexboxLayoutManager.LayoutState r9) {
        /*
            r7 = this;
            int r0 = r9.mScrollingOffset
            if (r0 >= 0) goto L_0x0007
            return
        L_0x0007:
            android.support.v7.widget.OrientationHelper r0 = r7.mOrientationHelper
            r0.getEnd()
            r9.mScrollingOffset
            int r0 = r7.getChildCount()
            if (r0 != 0) goto L_0x0016
            return
        L_0x0016:
            int r1 = r0 + -1
            android.view.View r2 = r7.getChildAt(r1)
            com.google.android.flexbox.FlexboxHelper r3 = r7.mFlexboxHelper
            int[] r3 = r3.mIndexToFlexLine
            int r2 = r7.getPosition(r2)
            r2 = r3[r2]
            r3 = -1
            if (r2 != r3) goto L_0x002a
            return
        L_0x002a:
            java.util.List<com.google.android.flexbox.FlexLine> r3 = r7.mFlexLines
            java.lang.Object r3 = r3.get(r2)
            com.google.android.flexbox.FlexLine r3 = (com.google.android.flexbox.FlexLine) r3
            r4 = r0
            r0 = r1
        L_0x0034:
            if (r0 < 0) goto L_0x0060
            android.view.View r5 = r7.getChildAt(r0)
            int r6 = r9.mScrollingOffset
            boolean r6 = r7.canViewBeRecycledFromEnd(r5, r6)
            if (r6 == 0) goto L_0x0060
            int r6 = r3.mFirstIndex
            int r5 = r7.getPosition(r5)
            if (r6 != r5) goto L_0x005d
            if (r2 > 0) goto L_0x004f
            goto L_0x0061
        L_0x004f:
            int r3 = r9.mLayoutDirection
            int r2 = r2 + r3
            java.util.List<com.google.android.flexbox.FlexLine> r3 = r7.mFlexLines
            java.lang.Object r3 = r3.get(r2)
            com.google.android.flexbox.FlexLine r3 = (com.google.android.flexbox.FlexLine) r3
            r4 = r0
        L_0x005d:
            int r0 = r0 + -1
            goto L_0x0034
        L_0x0060:
            r0 = r4
        L_0x0061:
            r7.recycleChildren(r8, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.recycleFlexLinesFromEnd(android.support.v7.widget.RecyclerView$Recycler, com.google.android.flexbox.FlexboxLayoutManager$LayoutState):void");
    }

    private boolean canViewBeRecycledFromEnd(View view, int i) {
        boolean z = false;
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            if (this.mOrientationHelper.getDecoratedStart(view) >= this.mOrientationHelper.getEnd() - i) {
                z = true;
            }
            return z;
        }
        if (this.mOrientationHelper.getDecoratedEnd(view) <= i) {
            z = true;
        }
        return z;
    }

    private void recycleChildren(Recycler recycler, int i, int i2) {
        while (i2 >= i) {
            removeAndRecycleViewAt(i2, recycler);
            i2--;
        }
    }

    private int layoutFlexLine(FlexLine flexLine, LayoutState layoutState) {
        if (isMainAxisDirectionHorizontal()) {
            return layoutFlexLineMainAxisHorizontal(flexLine, layoutState);
        }
        return layoutFlexLineMainAxisVertical(flexLine, layoutState);
    }

    private int layoutFlexLineMainAxisHorizontal(FlexLine flexLine, LayoutState layoutState) {
        float f;
        float f2;
        float f3;
        int i;
        float f4;
        float f5;
        LayoutParams layoutParams;
        FlexLine flexLine2 = flexLine;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int width = getWidth();
        int access$1000 = layoutState.mOffset;
        if (layoutState.mLayoutDirection == -1) {
            access$1000 -= flexLine2.mCrossSize;
        }
        int i2 = access$1000;
        int access$2200 = layoutState.mPosition;
        int i3 = 1;
        switch (this.mJustifyContent) {
            case 0:
                f3 = (float) paddingLeft;
                f2 = (float) (width - paddingRight);
                break;
            case 1:
                f = 0.0f;
                float f6 = (float) ((width - flexLine2.mMainSize) + paddingRight);
                f2 = (float) (flexLine2.mMainSize - paddingLeft);
                f3 = f6;
                break;
            case 2:
                f3 = ((float) paddingLeft) + (((float) (width - flexLine2.mMainSize)) / 2.0f);
                f2 = ((float) (width - paddingRight)) - (((float) (width - flexLine2.mMainSize)) / 2.0f);
                break;
            case 3:
                f3 = (float) paddingLeft;
                f = ((float) (width - flexLine2.mMainSize)) / (flexLine2.mItemCount != 1 ? (float) (flexLine2.mItemCount - 1) : 1.0f);
                f2 = (float) (width - paddingRight);
                break;
            case 4:
                f = flexLine2.mItemCount != 0 ? ((float) (width - flexLine2.mMainSize)) / ((float) flexLine2.mItemCount) : 0.0f;
                float f7 = f / 2.0f;
                f3 = ((float) paddingLeft) + f7;
                f2 = ((float) (width - paddingRight)) - f7;
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid justifyContent is set: ");
                sb.append(this.mJustifyContent);
                throw new IllegalStateException(sb.toString());
        }
        f = 0.0f;
        float access$2400 = f3 - ((float) this.mAnchorInfo.mPerpendicularCoordinate);
        float access$24002 = f2 - ((float) this.mAnchorInfo.mPerpendicularCoordinate);
        float max = Math.max(f, 0.0f);
        int i4 = 0;
        int itemCount = flexLine.getItemCount();
        int i5 = access$2200;
        while (i5 < access$2200 + itemCount) {
            View flexItemAt = getFlexItemAt(i5);
            if (flexItemAt == null) {
                i = i2;
            } else {
                if (layoutState.mLayoutDirection == i3) {
                    calculateItemDecorationsForChild(flexItemAt, TEMP_RECT);
                    addView(flexItemAt);
                } else {
                    calculateItemDecorationsForChild(flexItemAt, TEMP_RECT);
                    addView(flexItemAt, i4);
                    i4++;
                }
                int i6 = i4;
                long j = this.mFlexboxHelper.mMeasureSpecCache[i5];
                int extractLowerInt = this.mFlexboxHelper.extractLowerInt(j);
                int extractHigherInt = this.mFlexboxHelper.extractHigherInt(j);
                LayoutParams layoutParams2 = (LayoutParams) flexItemAt.getLayoutParams();
                if (shouldMeasureChild(flexItemAt, extractLowerInt, extractHigherInt, layoutParams2)) {
                    flexItemAt.measure(extractLowerInt, extractHigherInt);
                }
                float leftDecorationWidth = access$2400 + ((float) (layoutParams2.leftMargin + getLeftDecorationWidth(flexItemAt)));
                float rightDecorationWidth = access$24002 - ((float) (layoutParams2.rightMargin + getRightDecorationWidth(flexItemAt)));
                int topDecorationHeight = i2 + getTopDecorationHeight(flexItemAt);
                if (this.mIsRtl) {
                    f4 = rightDecorationWidth;
                    f5 = leftDecorationWidth;
                    i = i2;
                    layoutParams = layoutParams2;
                    this.mFlexboxHelper.layoutSingleChildHorizontal(flexItemAt, flexLine2, Math.round(rightDecorationWidth) - flexItemAt.getMeasuredWidth(), topDecorationHeight, Math.round(rightDecorationWidth), topDecorationHeight + flexItemAt.getMeasuredHeight());
                } else {
                    f4 = rightDecorationWidth;
                    f5 = leftDecorationWidth;
                    i = i2;
                    layoutParams = layoutParams2;
                    this.mFlexboxHelper.layoutSingleChildHorizontal(flexItemAt, flexLine2, Math.round(f5), topDecorationHeight, Math.round(f5) + flexItemAt.getMeasuredWidth(), topDecorationHeight + flexItemAt.getMeasuredHeight());
                }
                access$24002 = f4 - (((float) ((flexItemAt.getMeasuredWidth() + layoutParams.leftMargin) + getLeftDecorationWidth(flexItemAt))) + max);
                access$2400 = f5 + ((float) (flexItemAt.getMeasuredWidth() + layoutParams.rightMargin + getRightDecorationWidth(flexItemAt))) + max;
                i4 = i6;
            }
            i5++;
            i2 = i;
            i3 = 1;
        }
        layoutState.mFlexLinePosition = layoutState.mFlexLinePosition + this.mLayoutState.mLayoutDirection;
        return flexLine.getCrossSize();
    }

    private int layoutFlexLineMainAxisVertical(FlexLine flexLine, LayoutState layoutState) {
        float f;
        float f2;
        float f3;
        int i;
        int i2;
        int i3;
        int i4;
        View view;
        float f4;
        float f5;
        FlexLine flexLine2 = flexLine;
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int access$1000 = layoutState.mOffset;
        int access$10002 = layoutState.mOffset;
        if (layoutState.mLayoutDirection == -1) {
            access$1000 -= flexLine2.mCrossSize;
            access$10002 += flexLine2.mCrossSize;
        }
        int i5 = access$1000;
        int i6 = access$10002;
        int access$2200 = layoutState.mPosition;
        switch (this.mJustifyContent) {
            case 0:
                f3 = (float) paddingTop;
                f2 = (float) (height - paddingBottom);
                break;
            case 1:
                f = 0.0f;
                float f6 = (float) ((height - flexLine2.mMainSize) + paddingBottom);
                f2 = (float) (flexLine2.mMainSize - paddingTop);
                f3 = f6;
                break;
            case 2:
                f3 = ((float) paddingTop) + (((float) (height - flexLine2.mMainSize)) / 2.0f);
                f2 = ((float) (height - paddingBottom)) - (((float) (height - flexLine2.mMainSize)) / 2.0f);
                break;
            case 3:
                f3 = (float) paddingTop;
                f = ((float) (height - flexLine2.mMainSize)) / (flexLine2.mItemCount != 1 ? (float) (flexLine2.mItemCount - 1) : 1.0f);
                f2 = (float) (height - paddingBottom);
                break;
            case 4:
                f = flexLine2.mItemCount != 0 ? ((float) (height - flexLine2.mMainSize)) / ((float) flexLine2.mItemCount) : 0.0f;
                float f7 = f / 2.0f;
                f3 = ((float) paddingTop) + f7;
                f2 = ((float) (height - paddingBottom)) - f7;
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid justifyContent is set: ");
                sb.append(this.mJustifyContent);
                throw new IllegalStateException(sb.toString());
        }
        f = 0.0f;
        float access$2400 = f3 - ((float) this.mAnchorInfo.mPerpendicularCoordinate);
        float access$24002 = f2 - ((float) this.mAnchorInfo.mPerpendicularCoordinate);
        float max = Math.max(f, 0.0f);
        int i7 = 0;
        int itemCount = flexLine.getItemCount();
        int i8 = access$2200;
        while (i8 < access$2200 + itemCount) {
            View flexItemAt = getFlexItemAt(i8);
            if (flexItemAt == null) {
                i4 = i8;
                i3 = i5;
                i2 = i6;
                i = access$2200;
            } else {
                long j = this.mFlexboxHelper.mMeasureSpecCache[i8];
                int extractLowerInt = this.mFlexboxHelper.extractLowerInt(j);
                int extractHigherInt = this.mFlexboxHelper.extractHigherInt(j);
                LayoutParams layoutParams = (LayoutParams) flexItemAt.getLayoutParams();
                if (shouldMeasureChild(flexItemAt, extractLowerInt, extractHigherInt, layoutParams)) {
                    flexItemAt.measure(extractLowerInt, extractHigherInt);
                }
                float topDecorationHeight = access$2400 + ((float) (layoutParams.topMargin + getTopDecorationHeight(flexItemAt)));
                float bottomDecorationHeight = access$24002 - ((float) (layoutParams.rightMargin + getBottomDecorationHeight(flexItemAt)));
                if (layoutState.mLayoutDirection == 1) {
                    calculateItemDecorationsForChild(flexItemAt, TEMP_RECT);
                    addView(flexItemAt);
                } else {
                    calculateItemDecorationsForChild(flexItemAt, TEMP_RECT);
                    addView(flexItemAt, i7);
                    i7++;
                }
                int i9 = i7;
                int leftDecorationWidth = i5 + getLeftDecorationWidth(flexItemAt);
                int rightDecorationWidth = i6 - getRightDecorationWidth(flexItemAt);
                if (!this.mIsRtl) {
                    i4 = i8;
                    i3 = i5;
                    i2 = i6;
                    i = access$2200;
                    f5 = bottomDecorationHeight;
                    f4 = topDecorationHeight;
                    view = flexItemAt;
                    if (this.mFromBottomToTop) {
                        this.mFlexboxHelper.layoutSingleChildVertical(view, flexLine2, this.mIsRtl, leftDecorationWidth, Math.round(f5) - view.getMeasuredHeight(), leftDecorationWidth + view.getMeasuredWidth(), Math.round(f5));
                    } else {
                        this.mFlexboxHelper.layoutSingleChildVertical(view, flexLine2, this.mIsRtl, leftDecorationWidth, Math.round(f4), leftDecorationWidth + view.getMeasuredWidth(), Math.round(f4) + view.getMeasuredHeight());
                    }
                } else if (this.mFromBottomToTop) {
                    i3 = i5;
                    f5 = bottomDecorationHeight;
                    i2 = i6;
                    f4 = topDecorationHeight;
                    i = access$2200;
                    view = flexItemAt;
                    i4 = i8;
                    this.mFlexboxHelper.layoutSingleChildVertical(flexItemAt, flexLine2, this.mIsRtl, rightDecorationWidth - flexItemAt.getMeasuredWidth(), Math.round(bottomDecorationHeight) - flexItemAt.getMeasuredHeight(), rightDecorationWidth, Math.round(bottomDecorationHeight));
                } else {
                    i4 = i8;
                    i3 = i5;
                    i2 = i6;
                    i = access$2200;
                    f5 = bottomDecorationHeight;
                    f4 = topDecorationHeight;
                    view = flexItemAt;
                    this.mFlexboxHelper.layoutSingleChildVertical(view, flexLine2, this.mIsRtl, rightDecorationWidth - view.getMeasuredWidth(), Math.round(f4), rightDecorationWidth, Math.round(f4) + view.getMeasuredHeight());
                }
                access$24002 = f5 - (((float) ((view.getMeasuredHeight() + layoutParams.bottomMargin) + getTopDecorationHeight(view))) + max);
                access$2400 = f4 + ((float) (view.getMeasuredHeight() + layoutParams.topMargin + getBottomDecorationHeight(view))) + max;
                i7 = i9;
            }
            i8 = i4 + 1;
            i5 = i3;
            i6 = i2;
            access$2200 = i;
        }
        layoutState.mFlexLinePosition = layoutState.mFlexLinePosition + this.mLayoutState.mLayoutDirection;
        return flexLine.getCrossSize();
    }

    public boolean isMainAxisDirectionHorizontal() {
        return this.mFlexDirection == 0 || this.mFlexDirection == 1;
    }

    private void updateLayoutStateToFillEnd(AnchorInfo anchorInfo, boolean z, boolean z2) {
        if (z2) {
            resolveInfiniteAmount();
        } else {
            this.mLayoutState.mInfinite = false;
        }
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - anchorInfo.mCoordinate;
        } else {
            this.mLayoutState.mAvailable = anchorInfo.mCoordinate - getPaddingRight();
        }
        this.mLayoutState.mPosition = anchorInfo.mPosition;
        this.mLayoutState.mItemDirection = 1;
        this.mLayoutState.mLayoutDirection = 1;
        this.mLayoutState.mOffset = anchorInfo.mCoordinate;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
        this.mLayoutState.mFlexLinePosition = anchorInfo.mFlexLinePosition;
        if (z && this.mFlexLines.size() > 1 && anchorInfo.mFlexLinePosition >= 0 && anchorInfo.mFlexLinePosition < this.mFlexLines.size() - 1) {
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(anchorInfo.mFlexLinePosition);
            this.mLayoutState.mFlexLinePosition = this.mLayoutState.mFlexLinePosition + 1;
            LayoutState layoutState = this.mLayoutState;
            layoutState.mPosition = layoutState.mPosition + flexLine.getItemCount();
        }
    }

    private void updateLayoutStateToFillStart(AnchorInfo anchorInfo, boolean z, boolean z2) {
        if (z2) {
            resolveInfiniteAmount();
        } else {
            this.mLayoutState.mInfinite = false;
        }
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            this.mLayoutState.mAvailable = anchorInfo.mCoordinate - this.mOrientationHelper.getStartAfterPadding();
        } else {
            this.mLayoutState.mAvailable = (this.mParent.getWidth() - anchorInfo.mCoordinate) - this.mOrientationHelper.getStartAfterPadding();
        }
        this.mLayoutState.mPosition = anchorInfo.mPosition;
        this.mLayoutState.mItemDirection = 1;
        this.mLayoutState.mLayoutDirection = -1;
        this.mLayoutState.mOffset = anchorInfo.mCoordinate;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
        this.mLayoutState.mFlexLinePosition = anchorInfo.mFlexLinePosition;
        if (z && anchorInfo.mFlexLinePosition > 0 && this.mFlexLines.size() > anchorInfo.mFlexLinePosition) {
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(anchorInfo.mFlexLinePosition);
            this.mLayoutState.mFlexLinePosition = this.mLayoutState.mFlexLinePosition - 1;
            LayoutState layoutState = this.mLayoutState;
            layoutState.mPosition = layoutState.mPosition - flexLine.getItemCount();
        }
    }

    private void resolveInfiniteAmount() {
        int i;
        if (isMainAxisDirectionHorizontal()) {
            i = getHeightMode();
        } else {
            i = getWidthMode();
        }
        this.mLayoutState.mInfinite = i == 0 || i == Integer.MIN_VALUE;
    }

    private void ensureOrientationHelper() {
        if (this.mOrientationHelper == null) {
            if (isMainAxisDirectionHorizontal()) {
                if (this.mFlexWrap == 0) {
                    this.mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
                    this.mSubOrientationHelper = OrientationHelper.createVerticalHelper(this);
                } else {
                    this.mOrientationHelper = OrientationHelper.createVerticalHelper(this);
                    this.mSubOrientationHelper = OrientationHelper.createHorizontalHelper(this);
                }
            } else if (this.mFlexWrap == 0) {
                this.mOrientationHelper = OrientationHelper.createVerticalHelper(this);
                this.mSubOrientationHelper = OrientationHelper.createHorizontalHelper(this);
            } else {
                this.mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
                this.mSubOrientationHelper = OrientationHelper.createVerticalHelper(this);
            }
        }
    }

    private void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = new LayoutState();
        }
    }

    public void scrollToPosition(int i) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        if (this.mPendingSavedState != null) {
            this.mPendingSavedState.invalidateAnchor();
        }
        requestLayout();
    }

    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.mParent = (View) recyclerView.getParent();
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    public boolean canScrollHorizontally() {
        return !isMainAxisDirectionHorizontal() || getWidth() > this.mParent.getWidth();
    }

    public boolean canScrollVertically() {
        return isMainAxisDirectionHorizontal() || getHeight() > this.mParent.getHeight();
    }

    public int scrollHorizontallyBy(int i, Recycler recycler, State state) {
        if (!isMainAxisDirectionHorizontal()) {
            int handleScrollingCrossAxis = handleScrollingCrossAxis(i, recycler, state);
            this.mViewCache.clear();
            return handleScrollingCrossAxis;
        }
        int handleScrollingMainAxis = handleScrollingMainAxis(i);
        AnchorInfo anchorInfo = this.mAnchorInfo;
        anchorInfo.mPerpendicularCoordinate = anchorInfo.mPerpendicularCoordinate + handleScrollingMainAxis;
        this.mSubOrientationHelper.offsetChildren(-handleScrollingMainAxis);
        return handleScrollingMainAxis;
    }

    public int scrollVerticallyBy(int i, Recycler recycler, State state) {
        if (isMainAxisDirectionHorizontal()) {
            int handleScrollingCrossAxis = handleScrollingCrossAxis(i, recycler, state);
            this.mViewCache.clear();
            return handleScrollingCrossAxis;
        }
        int handleScrollingMainAxis = handleScrollingMainAxis(i);
        AnchorInfo anchorInfo = this.mAnchorInfo;
        anchorInfo.mPerpendicularCoordinate = anchorInfo.mPerpendicularCoordinate + handleScrollingMainAxis;
        this.mSubOrientationHelper.offsetChildren(-handleScrollingMainAxis);
        return handleScrollingMainAxis;
    }

    private int handleScrollingCrossAxis(int i, Recycler recycler, State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        ensureOrientationHelper();
        int i2 = 1;
        this.mLayoutState.mShouldRecycle = true;
        boolean z = !isMainAxisDirectionHorizontal() && this.mIsRtl;
        if (!z ? i <= 0 : i >= 0) {
            i2 = -1;
        }
        int abs = Math.abs(i);
        updateLayoutState(i2, abs);
        int access$2000 = this.mLayoutState.mScrollingOffset + fill(recycler, state, this.mLayoutState);
        if (access$2000 < 0) {
            return 0;
        }
        if (z) {
            if (abs > access$2000) {
                i = (-i2) * access$2000;
            }
        } else if (abs > access$2000) {
            i = i2 * access$2000;
        }
        this.mOrientationHelper.offsetChildren(-i);
        this.mLayoutState.mLastScrollDelta = i;
        return i;
    }

    private int handleScrollingMainAxis(int i) {
        boolean z = false;
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        ensureOrientationHelper();
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int width = isMainAxisDirectionHorizontal ? this.mParent.getWidth() : this.mParent.getHeight();
        int width2 = isMainAxisDirectionHorizontal ? getWidth() : getHeight();
        if (getLayoutDirection() == 1) {
            z = true;
        }
        if (z) {
            int abs = Math.abs(i);
            if (i < 0) {
                i = -Math.min((width2 + this.mAnchorInfo.mPerpendicularCoordinate) - width, abs);
            } else if (this.mAnchorInfo.mPerpendicularCoordinate + i > 0) {
                i = -this.mAnchorInfo.mPerpendicularCoordinate;
            }
        } else if (i > 0) {
            i = Math.min((width2 - this.mAnchorInfo.mPerpendicularCoordinate) - width, i);
        } else if (this.mAnchorInfo.mPerpendicularCoordinate + i < 0) {
            i = -this.mAnchorInfo.mPerpendicularCoordinate;
        }
        return i;
    }

    private void updateLayoutState(int i, int i2) {
        this.mLayoutState.mLayoutDirection = i;
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        int i3 = 0;
        boolean z = !isMainAxisDirectionHorizontal && this.mIsRtl;
        if (i == 1) {
            View childAt = getChildAt(getChildCount() - 1);
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(childAt);
            int position = getPosition(childAt);
            View findLastReferenceViewInLine = findLastReferenceViewInLine(childAt, (FlexLine) this.mFlexLines.get(this.mFlexboxHelper.mIndexToFlexLine[position]));
            this.mLayoutState.mItemDirection = 1;
            this.mLayoutState.mPosition = position + this.mLayoutState.mItemDirection;
            if (this.mFlexboxHelper.mIndexToFlexLine.length <= this.mLayoutState.mPosition) {
                this.mLayoutState.mFlexLinePosition = -1;
            } else {
                this.mLayoutState.mFlexLinePosition = this.mFlexboxHelper.mIndexToFlexLine[this.mLayoutState.mPosition];
            }
            if (z) {
                this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(findLastReferenceViewInLine);
                this.mLayoutState.mScrollingOffset = (-this.mOrientationHelper.getDecoratedStart(findLastReferenceViewInLine)) + this.mOrientationHelper.getStartAfterPadding();
                LayoutState layoutState = this.mLayoutState;
                if (this.mLayoutState.mScrollingOffset >= 0) {
                    i3 = this.mLayoutState.mScrollingOffset;
                }
                layoutState.mScrollingOffset = i3;
            } else {
                this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(findLastReferenceViewInLine);
                this.mLayoutState.mScrollingOffset = this.mOrientationHelper.getDecoratedEnd(findLastReferenceViewInLine) - this.mOrientationHelper.getEndAfterPadding();
            }
            if ((this.mLayoutState.mFlexLinePosition == -1 || this.mLayoutState.mFlexLinePosition > this.mFlexLines.size() - 1) && this.mLayoutState.mPosition <= getFlexItemCount()) {
                int access$2000 = i2 - this.mLayoutState.mScrollingOffset;
                this.mFlexLinesResult.reset();
                if (access$2000 > 0) {
                    if (isMainAxisDirectionHorizontal) {
                        this.mFlexboxHelper.calculateHorizontalFlexLines(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, access$2000, this.mLayoutState.mPosition, this.mFlexLines);
                    } else {
                        this.mFlexboxHelper.calculateVerticalFlexLines(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, access$2000, this.mLayoutState.mPosition, this.mFlexLines);
                    }
                    this.mFlexboxHelper.determineMainSize(makeMeasureSpec, makeMeasureSpec2, this.mLayoutState.mPosition);
                    this.mFlexboxHelper.stretchViews(this.mLayoutState.mPosition);
                }
            }
        } else {
            View childAt2 = getChildAt(0);
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(childAt2);
            int position2 = getPosition(childAt2);
            View findFirstReferenceViewInLine = findFirstReferenceViewInLine(childAt2, (FlexLine) this.mFlexLines.get(this.mFlexboxHelper.mIndexToFlexLine[position2]));
            this.mLayoutState.mItemDirection = 1;
            int i4 = this.mFlexboxHelper.mIndexToFlexLine[position2];
            if (i4 == -1) {
                i4 = 0;
            }
            if (i4 > 0) {
                this.mLayoutState.mPosition = position2 - ((FlexLine) this.mFlexLines.get(i4 - 1)).getItemCount();
            } else {
                this.mLayoutState.mPosition = -1;
            }
            this.mLayoutState.mFlexLinePosition = i4 > 0 ? i4 - 1 : 0;
            if (z) {
                this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(findFirstReferenceViewInLine);
                this.mLayoutState.mScrollingOffset = this.mOrientationHelper.getDecoratedEnd(findFirstReferenceViewInLine) - this.mOrientationHelper.getEndAfterPadding();
                LayoutState layoutState2 = this.mLayoutState;
                if (this.mLayoutState.mScrollingOffset >= 0) {
                    i3 = this.mLayoutState.mScrollingOffset;
                }
                layoutState2.mScrollingOffset = i3;
            } else {
                this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(findFirstReferenceViewInLine);
                this.mLayoutState.mScrollingOffset = (-this.mOrientationHelper.getDecoratedStart(findFirstReferenceViewInLine)) + this.mOrientationHelper.getStartAfterPadding();
            }
        }
        this.mLayoutState.mAvailable = i2 - this.mLayoutState.mScrollingOffset;
    }

    private View findFirstReferenceViewInLine(View view, FlexLine flexLine) {
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int i = flexLine.mItemCount;
        for (int i2 = 1; i2 < i; i2++) {
            View childAt = getChildAt(i2);
            if (!(childAt == null || childAt.getVisibility() == 8)) {
                if (!this.mIsRtl || isMainAxisDirectionHorizontal) {
                    if (this.mOrientationHelper.getDecoratedStart(view) <= this.mOrientationHelper.getDecoratedStart(childAt)) {
                    }
                } else if (this.mOrientationHelper.getDecoratedEnd(view) >= this.mOrientationHelper.getDecoratedEnd(childAt)) {
                }
                view = childAt;
            }
        }
        return view;
    }

    private View findLastReferenceViewInLine(View view, FlexLine flexLine) {
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int childCount = (getChildCount() - flexLine.mItemCount) - 1;
        for (int childCount2 = getChildCount() - 2; childCount2 > childCount; childCount2--) {
            View childAt = getChildAt(childCount2);
            if (!(childAt == null || childAt.getVisibility() == 8)) {
                if (!this.mIsRtl || isMainAxisDirectionHorizontal) {
                    if (this.mOrientationHelper.getDecoratedEnd(view) >= this.mOrientationHelper.getDecoratedEnd(childAt)) {
                    }
                } else if (this.mOrientationHelper.getDecoratedStart(view) <= this.mOrientationHelper.getDecoratedStart(childAt)) {
                }
                view = childAt;
            }
        }
        return view;
    }

    public int computeHorizontalScrollExtent(State state) {
        return computeScrollExtent(state);
    }

    public int computeVerticalScrollExtent(State state) {
        return computeScrollExtent(state);
    }

    private int computeScrollExtent(State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        ensureOrientationHelper();
        View findFirstReferenceChild = findFirstReferenceChild(itemCount);
        View findLastReferenceChild = findLastReferenceChild(itemCount);
        if (state.getItemCount() == 0 || findFirstReferenceChild == null || findLastReferenceChild == null) {
            return 0;
        }
        return Math.min(this.mOrientationHelper.getTotalSpace(), this.mOrientationHelper.getDecoratedEnd(findLastReferenceChild) - this.mOrientationHelper.getDecoratedStart(findFirstReferenceChild));
    }

    public int computeHorizontalScrollOffset(State state) {
        computeScrollOffset(state);
        return computeScrollOffset(state);
    }

    public int computeVerticalScrollOffset(State state) {
        return computeScrollOffset(state);
    }

    private int computeScrollOffset(State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View findFirstReferenceChild = findFirstReferenceChild(itemCount);
        View findLastReferenceChild = findLastReferenceChild(itemCount);
        if (state.getItemCount() == 0 || findFirstReferenceChild == null || findLastReferenceChild == null) {
            return 0;
        }
        int position = getPosition(findFirstReferenceChild);
        int position2 = getPosition(findLastReferenceChild);
        int abs = Math.abs(this.mOrientationHelper.getDecoratedEnd(findLastReferenceChild) - this.mOrientationHelper.getDecoratedStart(findFirstReferenceChild));
        int i = this.mFlexboxHelper.mIndexToFlexLine[position];
        if (i == 0 || i == -1) {
            return 0;
        }
        return Math.round((((float) i) * (((float) abs) / ((float) ((this.mFlexboxHelper.mIndexToFlexLine[position2] - i) + 1)))) + ((float) (this.mOrientationHelper.getStartAfterPadding() - this.mOrientationHelper.getDecoratedStart(findFirstReferenceChild))));
    }

    public int computeHorizontalScrollRange(State state) {
        return computeScrollRange(state);
    }

    public int computeVerticalScrollRange(State state) {
        return computeScrollRange(state);
    }

    private int computeScrollRange(State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View findFirstReferenceChild = findFirstReferenceChild(itemCount);
        View findLastReferenceChild = findLastReferenceChild(itemCount);
        if (state.getItemCount() == 0 || findFirstReferenceChild == null || findLastReferenceChild == null) {
            return 0;
        }
        int findFirstVisibleItemPosition = findFirstVisibleItemPosition();
        return (int) ((((float) Math.abs(this.mOrientationHelper.getDecoratedEnd(findLastReferenceChild) - this.mOrientationHelper.getDecoratedStart(findFirstReferenceChild))) / ((float) ((findLastVisibleItemPosition() - findFirstVisibleItemPosition) + 1))) * ((float) state.getItemCount()));
    }

    private boolean shouldMeasureChild(View view, int i, int i2, android.support.v7.widget.RecyclerView.LayoutParams layoutParams) {
        return view.isLayoutRequested() || !isMeasurementCacheEnabled() || !isMeasurementUpToDate(view.getWidth(), i, layoutParams.width) || !isMeasurementUpToDate(view.getHeight(), i2, layoutParams.height);
    }

    private static boolean isMeasurementUpToDate(int i, int i2, int i3) {
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        boolean z = false;
        if (i3 > 0 && i != i3) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            if (size >= i) {
                z = true;
            }
            return z;
        } else if (mode == 0) {
            return true;
        } else {
            if (mode != 1073741824) {
                return false;
            }
            if (size == i) {
                z = true;
            }
            return z;
        }
    }

    private void clearFlexLines() {
        this.mFlexLines.clear();
        this.mAnchorInfo.reset();
        this.mAnchorInfo.mPerpendicularCoordinate = 0;
    }

    private int getChildLeft(View view) {
        return getDecoratedLeft(view) - ((android.support.v7.widget.RecyclerView.LayoutParams) view.getLayoutParams()).leftMargin;
    }

    private int getChildRight(View view) {
        return getDecoratedRight(view) + ((android.support.v7.widget.RecyclerView.LayoutParams) view.getLayoutParams()).rightMargin;
    }

    private int getChildTop(View view) {
        return getDecoratedTop(view) - ((android.support.v7.widget.RecyclerView.LayoutParams) view.getLayoutParams()).topMargin;
    }

    private int getChildBottom(View view) {
        return getDecoratedBottom(view) + ((android.support.v7.widget.RecyclerView.LayoutParams) view.getLayoutParams()).bottomMargin;
    }

    private boolean isViewVisible(View view, boolean z) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = getWidth() - getPaddingRight();
        int height = getHeight() - getPaddingBottom();
        int childLeft = getChildLeft(view);
        int childTop = getChildTop(view);
        int childRight = getChildRight(view);
        int childBottom = getChildBottom(view);
        boolean z2 = true;
        boolean z3 = paddingLeft <= childLeft && width >= childRight;
        boolean z4 = childLeft >= width || childRight >= paddingLeft;
        boolean z5 = paddingTop <= childTop && height >= childBottom;
        boolean z6 = childTop >= height || childBottom >= paddingTop;
        if (z) {
            if (!z3 || !z5) {
                z2 = false;
            }
            return z2;
        }
        if (!z4 || !z6) {
            z2 = false;
        }
        return z2;
    }

    public int findFirstVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(0, getChildCount(), false);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    public int findLastVisibleItemPosition() {
        View findOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, false);
        if (findOneVisibleChild == null) {
            return -1;
        }
        return getPosition(findOneVisibleChild);
    }

    private View findOneVisibleChild(int i, int i2, boolean z) {
        int i3 = i2 > i ? 1 : -1;
        while (i != i2) {
            View childAt = getChildAt(i);
            if (isViewVisible(childAt, z)) {
                return childAt;
            }
            i += i3;
        }
        return null;
    }
}
