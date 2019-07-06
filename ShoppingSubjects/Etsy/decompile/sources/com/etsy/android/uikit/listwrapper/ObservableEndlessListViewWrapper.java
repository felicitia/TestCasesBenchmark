package com.etsy.android.uikit.listwrapper;

import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.logger.f;
import java.util.ArrayList;
import java.util.Iterator;

@Deprecated
public abstract class ObservableEndlessListViewWrapper extends ListViewEndlessWrapper {
    private static final boolean DBG = false;
    private static final String TAG = f.a(ObservableEndlessListViewWrapper.class);
    private int mCachedVerticalScrollRange;
    private ArrayList<a> mCallbacks = new ArrayList<>();
    private int mHeight;
    private boolean mIsScrollComputed = false;
    private int[] mItemOffsetY;
    private int mLastTopY;

    public enum ScrollDirection {
        UP,
        DOWN
    }

    public interface a {
        void a(int i, int i2, ScrollDirection scrollDirection);
    }

    public ObservableEndlessListViewWrapper(ListView listView) {
        super(listView, k.endless_footer, k.endless_error, i.btn_retry_endless);
    }

    public void addCallbacks(a aVar) {
        this.mCallbacks.add(aVar);
    }

    public void reset() {
        this.mCachedVerticalScrollRange = 0;
        this.mLastTopY = 0;
    }

    /* access modifiers changed from: protected */
    public void computeScrollY() {
        ListAdapter adapter = this.mListView.getAdapter();
        this.mHeight = 0;
        int count = adapter.getCount();
        if (this.mItemOffsetY == null || this.mItemOffsetY.length != count) {
            this.mItemOffsetY = new int[count];
        }
        int viewTypeCount = adapter.getViewTypeCount();
        int[] iArr = new int[viewTypeCount];
        for (int i = 0; i < count; i++) {
            this.mItemOffsetY[i] = this.mHeight;
            int itemViewType = adapter.getItemViewType(i);
            if (itemViewType < 0 || itemViewType >= viewTypeCount) {
                this.mHeight += getAdapterItemHeight(i);
            } else {
                if (iArr[itemViewType] == 0) {
                    iArr[itemViewType] = getAdapterItemHeight(i);
                }
                this.mHeight += iArr[itemViewType];
            }
        }
        this.mCachedVerticalScrollRange = this.mHeight;
        this.mIsScrollComputed = true;
    }

    /* access modifiers changed from: protected */
    public int calculateMeasuredHeight(View view) {
        try {
            view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            return view.getMeasuredHeight();
        } catch (NullPointerException e) {
            f.e(TAG, "Error measuring view height", e);
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    public int getHeight() {
        return this.mHeight;
    }

    /* access modifiers changed from: protected */
    public int getCachedVerticalScrollRange() {
        return this.mCachedVerticalScrollRange;
    }

    /* access modifiers changed from: protected */
    public void setCachedVerticalScrollRangeToCalculatedHeight() {
        this.mCachedVerticalScrollRange = this.mHeight;
    }

    private int getAdapterItemHeight(int i) {
        return calculateMeasuredHeight(this.mListView.getAdapter().getView(i, null, this.mListView));
    }

    private boolean scrollYIsComputed() {
        return this.mIsScrollComputed;
    }

    private int getComputedTopScrollY() {
        int firstVisiblePosition = this.mListView.getFirstVisiblePosition();
        View childAt = this.mListView.getChildAt(0);
        if (childAt == null || firstVisiblePosition >= this.mItemOffsetY.length) {
            return 0;
        }
        return this.mItemOffsetY[firstVisiblePosition] - childAt.getTop();
    }

    private int getComputedBottomScrollY() {
        int i;
        if (isEndlessLoading()) {
            i = this.mCachedVerticalScrollRange;
        } else {
            int lastVisiblePosition = this.mListView.getLastVisiblePosition();
            View childAt = this.mListView.getChildAt(lastVisiblePosition - this.mListView.getFirstVisiblePosition());
            if (childAt == null || lastVisiblePosition >= this.mItemOffsetY.length) {
                i = 0;
            } else {
                int top = childAt.getTop();
                i = (this.mCachedVerticalScrollRange - this.mItemOffsetY[lastVisiblePosition]) - (this.mListView.getBottom() - top);
            }
        }
        if (i >= 0) {
            return i;
        }
        return 0;
    }

    private ScrollDirection getComputedScrollDirection(int i) {
        ScrollDirection scrollDirection = ScrollDirection.DOWN;
        if (i < this.mLastTopY) {
            scrollDirection = ScrollDirection.UP;
        }
        this.mLastTopY = i;
        return scrollDirection;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        int i4;
        super.onScroll(absListView, i, i2, i3);
        int i5 = 0;
        if (scrollYIsComputed()) {
            i5 = getComputedTopScrollY();
            i4 = getComputedBottomScrollY();
        } else {
            i4 = 0;
        }
        ScrollDirection computedScrollDirection = getComputedScrollDirection(i5);
        if (this.mCallbacks != null) {
            Iterator it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((a) it.next()).a(i5, i4, computedScrollDirection);
            }
        }
    }
}
