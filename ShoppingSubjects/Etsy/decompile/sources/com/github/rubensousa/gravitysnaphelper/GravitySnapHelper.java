package com.github.rubensousa.gravitysnaphelper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;
import com.github.rubensousa.gravitysnaphelper.a.C0127a;

public class GravitySnapHelper extends LinearSnapHelper {
    private int mGravity;
    private OrientationHelper mHorizontalHelper;
    private boolean mIsRtlHorizontal;
    private OnScrollListener mScrollListener;
    private boolean mSnapLastItemEnabled;
    a mSnapListener;
    boolean mSnapping;
    private OrientationHelper mVerticalHelper;

    public interface a {
        void a(int i);
    }

    public GravitySnapHelper(int i) {
        this(i, false, null);
    }

    public GravitySnapHelper(int i, boolean z) {
        this(i, z, null);
    }

    public GravitySnapHelper(int i, boolean z, a aVar) {
        this.mScrollListener = new OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 2) {
                    GravitySnapHelper.this.mSnapping = false;
                }
                if (i == 0 && GravitySnapHelper.this.mSnapping && GravitySnapHelper.this.mSnapListener != null) {
                    int snappedPosition = GravitySnapHelper.this.getSnappedPosition(recyclerView);
                    if (snappedPosition != -1) {
                        GravitySnapHelper.this.mSnapListener.a(snappedPosition);
                    }
                    GravitySnapHelper.this.mSnapping = false;
                }
            }
        };
        if (i == 8388611 || i == 8388613 || i == 80 || i == 48) {
            this.mSnapListener = aVar;
            this.mGravity = i;
            this.mSnapLastItemEnabled = z;
            return;
        }
        throw new IllegalArgumentException("Invalid gravity value. Use START | END | BOTTOM | TOP constants");
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        if (recyclerView != null) {
            if (this.mGravity == 8388611 || this.mGravity == 8388613) {
                this.mIsRtlHorizontal = recyclerView.getContext().getResources().getBoolean(C0127a.is_rtl);
            }
            if (this.mSnapListener != null) {
                recyclerView.addOnScrollListener(this.mScrollListener);
            }
        }
        super.attachToRecyclerView(recyclerView);
    }

    public int[] calculateDistanceToFinalSnap(@NonNull LayoutManager layoutManager, @NonNull View view) {
        int[] iArr = new int[2];
        if (!layoutManager.canScrollHorizontally()) {
            iArr[0] = 0;
        } else if (this.mGravity == 8388611) {
            iArr[0] = distanceToStart(view, getHorizontalHelper(layoutManager), false);
        } else {
            iArr[0] = distanceToEnd(view, getHorizontalHelper(layoutManager), false);
        }
        if (!layoutManager.canScrollVertically()) {
            iArr[1] = 0;
        } else if (this.mGravity == 48) {
            iArr[1] = distanceToStart(view, getVerticalHelper(layoutManager), false);
        } else {
            iArr[1] = distanceToEnd(view, getVerticalHelper(layoutManager), false);
        }
        return iArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0042  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View findSnapView(android.support.v7.widget.RecyclerView.LayoutManager r3) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof android.support.v7.widget.LinearLayoutManager
            if (r0 == 0) goto L_0x003d
            int r0 = r2.mGravity
            r1 = 48
            if (r0 == r1) goto L_0x0034
            r1 = 80
            if (r0 == r1) goto L_0x002b
            r1 = 8388611(0x800003, float:1.1754948E-38)
            if (r0 == r1) goto L_0x0022
            r1 = 8388613(0x800005, float:1.175495E-38)
            if (r0 == r1) goto L_0x0019
            goto L_0x003d
        L_0x0019:
            android.support.v7.widget.OrientationHelper r0 = r2.getHorizontalHelper(r3)
            android.view.View r3 = r2.findEndView(r3, r0)
            goto L_0x003e
        L_0x0022:
            android.support.v7.widget.OrientationHelper r0 = r2.getHorizontalHelper(r3)
            android.view.View r3 = r2.findStartView(r3, r0)
            goto L_0x003e
        L_0x002b:
            android.support.v7.widget.OrientationHelper r0 = r2.getVerticalHelper(r3)
            android.view.View r3 = r2.findEndView(r3, r0)
            goto L_0x003e
        L_0x0034:
            android.support.v7.widget.OrientationHelper r0 = r2.getVerticalHelper(r3)
            android.view.View r3 = r2.findStartView(r3, r0)
            goto L_0x003e
        L_0x003d:
            r3 = 0
        L_0x003e:
            if (r3 == 0) goto L_0x0042
            r0 = 1
            goto L_0x0043
        L_0x0042:
            r0 = 0
        L_0x0043:
            r2.mSnapping = r0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.rubensousa.gravitysnaphelper.GravitySnapHelper.findSnapView(android.support.v7.widget.RecyclerView$LayoutManager):android.view.View");
    }

    public void enableLastItemSnap(boolean z) {
        this.mSnapLastItemEnabled = z;
    }

    private int distanceToStart(View view, OrientationHelper orientationHelper, boolean z) {
        if (!this.mIsRtlHorizontal || z) {
            return orientationHelper.getDecoratedStart(view) - orientationHelper.getStartAfterPadding();
        }
        return distanceToEnd(view, orientationHelper, true);
    }

    private int distanceToEnd(View view, OrientationHelper orientationHelper, boolean z) {
        if (!this.mIsRtlHorizontal || z) {
            return orientationHelper.getDecoratedEnd(view) - orientationHelper.getEndAfterPadding();
        }
        return distanceToStart(view, orientationHelper, true);
    }

    private View findStartView(LayoutManager layoutManager, OrientationHelper orientationHelper) {
        float f;
        if (!(layoutManager instanceof LinearLayoutManager)) {
            return null;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        if (findFirstVisibleItemPosition == -1) {
            return null;
        }
        View findViewByPosition = layoutManager.findViewByPosition(findFirstVisibleItemPosition);
        if (this.mIsRtlHorizontal) {
            f = ((float) (orientationHelper.getTotalSpace() - orientationHelper.getDecoratedStart(findViewByPosition))) / ((float) orientationHelper.getDecoratedMeasurement(findViewByPosition));
        } else {
            f = ((float) orientationHelper.getDecoratedEnd(findViewByPosition)) / ((float) orientationHelper.getDecoratedMeasurement(findViewByPosition));
        }
        boolean z = linearLayoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.getItemCount() - 1;
        if (f > 0.5f && !z) {
            return findViewByPosition;
        }
        if (this.mSnapLastItemEnabled && z) {
            return findViewByPosition;
        }
        if (z) {
            return null;
        }
        return layoutManager.findViewByPosition(findFirstVisibleItemPosition + 1);
    }

    private View findEndView(LayoutManager layoutManager, OrientationHelper orientationHelper) {
        float f;
        if (!(layoutManager instanceof LinearLayoutManager)) {
            return null;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        if (findLastVisibleItemPosition == -1) {
            return null;
        }
        View findViewByPosition = layoutManager.findViewByPosition(findLastVisibleItemPosition);
        if (this.mIsRtlHorizontal) {
            f = ((float) orientationHelper.getDecoratedEnd(findViewByPosition)) / ((float) orientationHelper.getDecoratedMeasurement(findViewByPosition));
        } else {
            f = ((float) (orientationHelper.getTotalSpace() - orientationHelper.getDecoratedStart(findViewByPosition))) / ((float) orientationHelper.getDecoratedMeasurement(findViewByPosition));
        }
        boolean z = linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0;
        if (f > 0.5f && !z) {
            return findViewByPosition;
        }
        if (this.mSnapLastItemEnabled && z) {
            return findViewByPosition;
        }
        if (z) {
            return null;
        }
        return layoutManager.findViewByPosition(findLastVisibleItemPosition - 1);
    }

    /* access modifiers changed from: 0000 */
    public int getSnappedPosition(RecyclerView recyclerView) {
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            if (this.mGravity == 8388611 || this.mGravity == 48) {
                return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
            }
            if (this.mGravity == 8388613 || this.mGravity == 80) {
                return ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
            }
        }
        return -1;
    }

    private OrientationHelper getVerticalHelper(LayoutManager layoutManager) {
        if (this.mVerticalHelper == null) {
            this.mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.mVerticalHelper;
    }

    private OrientationHelper getHorizontalHelper(LayoutManager layoutManager) {
        if (this.mHorizontalHelper == null) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.mHorizontalHelper;
    }
}
