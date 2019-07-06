package com.contextlogic.wish.ui.grid;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import com.contextlogic.wish.activity.feed.ProductFeedTileView;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.scrollview.ScrollRestorable;
import com.contextlogic.wish.ui.view.Destroyable;
import com.contextlogic.wish.ui.view.Recyclable;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.TabletUtil;
import com.contextlogic.wish.util.ValueUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class StaggeredGridView extends NestedScrollView implements ImageRestorable, ScrollRestorable {
    private Adapter mAdapter;
    private int[] mColumnHeights;
    private int mColumnWidth;
    private FrameLayout mFooterViewContainer;
    private FrameLayout mGridContentHolder;
    private LinearLayout mHeaderFooterContentHolder;
    private OnGlobalLayoutListener mHeaderFooterViewTreeLayoutListener;
    private LinearLayout mHeaderViewContainer;
    private boolean mInEditMode;
    /* access modifiers changed from: private */
    public boolean mInitialized;
    private Runnable mInvalidateGridJob;
    /* access modifiers changed from: private */
    public boolean mInvalidateGridJobPosted;
    /* access modifiers changed from: private */
    public SparseArray<Rect> mItemPositions;
    private int mLastDataCount;
    private Rect mLastLoadedRect;
    private long mLastScrollEventTime;
    /* access modifiers changed from: private */
    public OnItemClickListener mOnItemClickListener;
    /* access modifiers changed from: private */
    public OnLongClickListener mOnLongClickListener;
    private OnScrollListener mOnScrollListener;
    private OnViewVisibleListener mOnViewVisibleListener;
    private SparseArray<ArrayList<View>> mRecycledViews;
    private ScrollDirection mScrollDirection;
    private float mScrollSpeedPxPerSec;
    private HashSet<Integer> mSeenPositions;
    private OnGlobalLayoutListener mViewTreeLayoutListener;
    private ArrayList<ItemWrapper> mVisibleItems;

    public static abstract class Adapter extends BaseAdapter {
        public abstract int getItemHeight(int i, int i2);

        public long getItemId(int i) {
            return 0;
        }

        public int getItemMargin() {
            return (int) ValueUtil.convertDpToPx(10.0f);
        }

        public int getColumnCount() {
            if (TabletUtil.isTablet()) {
                if (DisplayUtil.isLandscape()) {
                    return 4;
                }
                return 3;
            } else if (DisplayUtil.isLandscape()) {
                return 3;
            } else {
                return 2;
            }
        }
    }

    public static class ItemWrapper {
        public int index;
        public View view;
        public int viewType;
    }

    public interface OnItemClickListener {
        void onItemClick(int i, View view);
    }

    public interface OnScrollListener {
        void onScrollChanged(int i, int i2, int i3, int i4);
    }

    public interface OnViewVisibleListener {
        void onViewVisible(int i, View view);
    }

    public enum ScrollDirection {
        UP,
        DOWN,
        UNKNOWN
    }

    public interface StaggeredGridViewSizedCell {
        void onCellSizeChanged(int i, int i2);
    }

    public interface VisibleItemTask {
        void performTask(View view);
    }

    public StaggeredGridView(Context context) {
        this(context, null);
    }

    public StaggeredGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StaggeredGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mRecycledViews = new SparseArray<>();
        this.mItemPositions = new SparseArray<>();
        this.mVisibleItems = new ArrayList<>();
        this.mLastDataCount = 0;
        this.mColumnWidth = 0;
        this.mInitialized = false;
        this.mLastLoadedRect = new Rect();
        this.mSeenPositions = new HashSet<>();
        this.mInvalidateGridJobPosted = false;
        this.mInvalidateGridJob = new Runnable() {
            public void run() {
                StaggeredGridView.this.mInvalidateGridJobPosted = false;
                StaggeredGridView.this.invalidateGrid();
            }
        };
        this.mHeaderFooterContentHolder = new LinearLayout(getContext());
        this.mHeaderFooterContentHolder.setLayoutParams(new LayoutParams(-1, -2));
        this.mHeaderFooterContentHolder.setOrientation(1);
        addView(this.mHeaderFooterContentHolder);
        this.mHeaderViewContainer = new LinearLayout(getContext());
        this.mHeaderViewContainer.setOrientation(1);
        this.mHeaderViewContainer.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.mHeaderFooterContentHolder.addView(this.mHeaderViewContainer);
        this.mGridContentHolder = new FrameLayout(getContext());
        this.mGridContentHolder.setLayoutParams(new LinearLayout.LayoutParams(-1, 0));
        this.mHeaderFooterContentHolder.addView(this.mGridContentHolder);
        this.mFooterViewContainer = new FrameLayout(getContext());
        this.mFooterViewContainer.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.mHeaderFooterContentHolder.addView(this.mFooterViewContainer);
        this.mHeaderFooterViewTreeLayoutListener = new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                StaggeredGridView.this.postInvalidateGrid();
            }
        };
        this.mHeaderViewContainer.getViewTreeObserver().addOnGlobalLayoutListener(this.mHeaderFooterViewTreeLayoutListener);
        this.mFooterViewContainer.getViewTreeObserver().addOnGlobalLayoutListener(this.mHeaderFooterViewTreeLayoutListener);
        this.mViewTreeLayoutListener = new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (!StaggeredGridView.this.mInitialized && StaggeredGridView.this.getWidth() > 0 && StaggeredGridView.this.getHeight() > 0) {
                    StaggeredGridView.this.mInitialized = true;
                    StaggeredGridView.this.notifyDataSetChanged(true);
                }
            }
        };
        getViewTreeObserver().addOnGlobalLayoutListener(this.mViewTreeLayoutListener);
    }

    public void setHeaderView(View view, ArrayList<View> arrayList) {
        this.mHeaderViewContainer.removeAllViews();
        this.mHeaderViewContainer.addView(view);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.mHeaderViewContainer.addView((View) it.next());
        }
    }

    public void setHeaderView(View view, View view2) {
        this.mHeaderViewContainer.removeAllViews();
        this.mHeaderViewContainer.addView(view);
        this.mHeaderViewContainer.addView(view2);
    }

    public void setHeaderView(View view) {
        this.mHeaderViewContainer.removeAllViews();
        this.mHeaderViewContainer.addView(view);
    }

    public View getHeaderView() {
        if (this.mHeaderViewContainer.getChildCount() > 0) {
            return this.mHeaderViewContainer.getChildAt(0);
        }
        return null;
    }

    public void setFooterView(View view) {
        this.mFooterViewContainer.removeAllViews();
        this.mFooterViewContainer.addView(view);
    }

    public View getFooterView() {
        if (this.mFooterViewContainer.getChildCount() > 0) {
            return this.mFooterViewContainer.getChildAt(0);
        }
        return null;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.mOnLongClickListener = onLongClickListener;
    }

    public void setOnViewVisibleListener(OnViewVisibleListener onViewVisibleListener) {
        this.mOnViewVisibleListener = onViewVisibleListener;
    }

    public void setAdapter(Adapter adapter) {
        this.mAdapter = adapter;
        notifyDataSetChanged(true);
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (i2 < i4) {
            this.mScrollDirection = ScrollDirection.UP;
        } else {
            this.mScrollDirection = ScrollDirection.DOWN;
        }
        invalidateGrid();
        long currentTimeMillis = System.currentTimeMillis() - this.mLastScrollEventTime;
        this.mLastScrollEventTime = System.currentTimeMillis();
        if (currentTimeMillis > 0) {
            this.mScrollSpeedPxPerSec = (float) ((int) (((double) Math.abs(i4 - i2)) / (((double) currentTimeMillis) / 1000.0d)));
        }
        if (this.mOnScrollListener != null) {
            this.mOnScrollListener.onScrollChanged(i2, i2 - i4, getHeight(), this.mHeaderFooterContentHolder.getHeight());
        }
    }

    public int getContentHeight() {
        return getMaxColumnHeight();
    }

    private int getMaxColumnHeight() {
        int[] iArr;
        if (this.mColumnHeights == null) {
            return 0;
        }
        int i = 0;
        for (int i2 : this.mColumnHeights) {
            if (i2 > i) {
                i = i2;
            }
        }
        return i;
    }

    private void addGridCells(int i) {
        int count = this.mAdapter.getCount();
        if (count > 0) {
            while (i < count) {
                int i2 = 0;
                int i3 = this.mColumnHeights[0];
                for (int i4 = 1; i4 < this.mColumnHeights.length; i4++) {
                    int i5 = this.mColumnHeights[i4];
                    if (i5 < i3) {
                        i2 = i4;
                        i3 = i5;
                    }
                }
                int itemMargin = (this.mColumnWidth * i2) + ((i2 + 1) * this.mAdapter.getItemMargin());
                int max = Math.max(i3, this.mAdapter.getItemMargin());
                Rect rect = new Rect(itemMargin, max, this.mColumnWidth + itemMargin, this.mAdapter.getItemHeight(i, this.mColumnWidth) + max);
                this.mItemPositions.put(i, rect);
                this.mColumnHeights[i2] = rect.bottom + this.mAdapter.getItemMargin();
                i++;
            }
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mGridContentHolder.getLayoutParams();
        layoutParams.height = getMaxColumnHeight();
        this.mGridContentHolder.setLayoutParams(layoutParams);
        postInvalidateGrid();
    }

    public void notifyDataSetChanged() {
        notifyDataSetChanged(false);
    }

    public void notifyDataSetChanged(boolean z) {
        if (this.mAdapter != null && this.mInitialized) {
            if (this.mColumnHeights == null || this.mColumnHeights.length != this.mAdapter.getColumnCount()) {
                this.mColumnHeights = new int[this.mAdapter.getColumnCount()];
                this.mColumnWidth = (getWidth() - ((this.mColumnHeights.length + 1) * this.mAdapter.getItemMargin())) / this.mColumnHeights.length;
                z = true;
            }
            int i = this.mLastDataCount;
            if (z) {
                Iterator it = this.mVisibleItems.iterator();
                while (it.hasNext()) {
                    recycleView((ItemWrapper) it.next());
                }
                this.mVisibleItems.clear();
                this.mItemPositions.clear();
                this.mSeenPositions.clear();
                for (int i2 = 0; i2 < this.mColumnHeights.length; i2++) {
                    this.mColumnHeights[i2] = 0;
                }
                i = 0;
            }
            addGridCells(i);
            this.mLastDataCount = this.mAdapter.getCount();
            if (z) {
                executeInvalidateGrid();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void executeInvalidateGrid() {
        if (this.mInvalidateGridJobPosted) {
            removeCallbacks(this.mInvalidateGridJob);
        }
        this.mInvalidateGridJob.run();
    }

    /* access modifiers changed from: protected */
    public void postInvalidateGrid() {
        if (!this.mInvalidateGridJobPosted) {
            this.mInvalidateGridJobPosted = true;
            post(this.mInvalidateGridJob);
        }
    }

    /* access modifiers changed from: protected */
    public boolean invalidateGrid() {
        if (this.mAdapter == null || !this.mInitialized || this.mAdapter.getCount() == 0) {
            return false;
        }
        int scrollY = getScrollY() - this.mHeaderViewContainer.getHeight();
        int height = (int) (((double) getHeight()) * 0.5d);
        int height2 = (int) (((double) getHeight()) * 1.5d);
        if (this.mScrollDirection != ScrollDirection.DOWN) {
            if (this.mScrollDirection == ScrollDirection.UP) {
                int i = height2;
                height2 = height;
                height = i;
            } else if (this.mScrollDirection == null) {
                height2 = height;
            } else {
                height = 0;
                height2 = 0;
            }
        }
        int max = Math.max(0, scrollY - height);
        int height3 = getHeight() + scrollY + height2;
        if (this.mFooterViewContainer != null) {
            this.mFooterViewContainer.setVisibility(height3 > this.mFooterViewContainer.getTop() ? 0 : 4);
        }
        this.mLastLoadedRect.set(0, max, getWidth(), height3);
        Rect rect = new Rect(0, Math.max(0, scrollY), getWidth(), scrollY + getHeight());
        boolean z = false;
        for (int size = this.mVisibleItems.size() - 1; size >= 0; size--) {
            ItemWrapper itemWrapper = (ItemWrapper) this.mVisibleItems.get(size);
            int i2 = itemWrapper.index;
            if (!Rect.intersects(this.mLastLoadedRect, (Rect) this.mItemPositions.get(i2))) {
                recycleView(itemWrapper);
                this.mVisibleItems.remove(size);
                z = true;
            }
            if (Rect.intersects(rect, (Rect) this.mItemPositions.get(i2)) && !this.mSeenPositions.contains(Integer.valueOf(i2)) && this.mOnViewVisibleListener != null) {
                this.mSeenPositions.add(Integer.valueOf(i2));
                this.mOnViewVisibleListener.onViewVisible(i2, itemWrapper.view);
            }
        }
        int i3 = -1;
        int i4 = this.mVisibleItems.size() > 0 ? ((ItemWrapper) this.mVisibleItems.get(0)).index : -1;
        if (this.mVisibleItems.size() > 0) {
            i3 = ((ItemWrapper) this.mVisibleItems.get(this.mVisibleItems.size() - 1)).index;
        }
        int i5 = i4 - 1;
        while (i5 >= 0) {
            ItemWrapper addPositionToGrid = addPositionToGrid(i5, this.mLastLoadedRect);
            if (addPositionToGrid == null) {
                break;
            }
            this.mVisibleItems.add(0, addPositionToGrid);
            i5--;
            z = true;
        }
        int count = this.mAdapter.getCount();
        for (int i6 = i3 + 1; i6 < count; i6++) {
            ItemWrapper addPositionToGrid2 = addPositionToGrid(i6, this.mLastLoadedRect);
            if (addPositionToGrid2 != null) {
                this.mVisibleItems.add(addPositionToGrid2);
                z = true;
            } else if (this.mVisibleItems.size() > 0) {
                break;
            }
        }
        return z;
    }

    private ItemWrapper addPositionToGrid(final int i, Rect rect) {
        Rect rect2 = (Rect) this.mItemPositions.get(i);
        if (!Rect.intersects(rect2, rect)) {
            return null;
        }
        int itemViewType = this.mAdapter.getItemViewType(i);
        View view = this.mAdapter.getView(i, getRecycledView(itemViewType), this.mGridContentHolder);
        ItemWrapper itemWrapper = new ItemWrapper();
        itemWrapper.view = view;
        itemWrapper.index = i;
        itemWrapper.viewType = itemViewType;
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (StaggeredGridView.this.mOnItemClickListener != null) {
                    StaggeredGridView.this.mOnItemClickListener.onItemClick(i, view);
                }
            }
        });
        view.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (StaggeredGridView.this.mOnLongClickListener != null) {
                    return StaggeredGridView.this.mOnLongClickListener.onLongClick(view);
                }
                return false;
            }
        });
        int i2 = rect2.right - rect2.left;
        int i3 = rect2.bottom - rect2.top;
        if (view instanceof StaggeredGridViewSizedCell) {
            ((StaggeredGridViewSizedCell) view).onCellSizeChanged(i2, i3);
        }
        LayoutParams layoutParams = new LayoutParams(i2, i3);
        layoutParams.gravity = 48;
        layoutParams.topMargin = rect2.top;
        layoutParams.leftMargin = rect2.left;
        if (view.getParent() != null) {
            view.setVisibility(0);
            view.setLayoutParams(layoutParams);
        } else {
            this.mGridContentHolder.addView(view, layoutParams);
        }
        return itemWrapper;
    }

    /* access modifiers changed from: protected */
    public void recycleView(ItemWrapper itemWrapper) {
        View view = itemWrapper.view;
        if (view instanceof Recyclable) {
            ((Recyclable) view).recycle();
        }
        if (view.getParent() == this.mGridContentHolder) {
            view.setVisibility(4);
        }
        ArrayList arrayList = (ArrayList) this.mRecycledViews.get(itemWrapper.viewType);
        if (arrayList != null) {
            arrayList.add(view);
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(view);
        this.mRecycledViews.put(itemWrapper.viewType, arrayList2);
    }

    /* access modifiers changed from: protected */
    public View getRecycledView(int i) {
        ArrayList arrayList = (ArrayList) this.mRecycledViews.get(i);
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        return (View) arrayList.remove(0);
    }

    public void disableInteraction() {
        setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    public void withVisibleItems(VisibleItemTask visibleItemTask) {
        Iterator it = this.mVisibleItems.iterator();
        while (it.hasNext()) {
            visibleItemTask.performTask(((ItemWrapper) it.next()).view);
        }
    }

    public void releaseImages() {
        withVisibleItems(new VisibleItemTask() {
            public void performTask(View view) {
                if (view instanceof ImageRestorable) {
                    ((ImageRestorable) view).releaseImages();
                }
            }
        });
    }

    public void restoreImages() {
        withVisibleItems(new VisibleItemTask() {
            public void performTask(View view) {
                if (view instanceof ImageRestorable) {
                    ((ImageRestorable) view).restoreImages();
                }
            }
        });
    }

    public void setEditModeEnabled(boolean z) {
        this.mInEditMode = z;
        int size = this.mVisibleItems.size();
        for (int i = 0; i < size; i++) {
            View view = ((ItemWrapper) this.mVisibleItems.get(i)).view;
            if (view instanceof ProductFeedTileView) {
                ((ProductFeedTileView) view).setEditModeEnabled(z);
            }
        }
    }

    private void teardownItems() {
        withVisibleItems(new VisibleItemTask() {
            public void performTask(View view) {
                if (view instanceof ImageRestorable) {
                    ((ImageRestorable) view).releaseImages();
                }
                if (view instanceof Recyclable) {
                    ((Recyclable) view).recycle();
                }
                if (view instanceof Destroyable) {
                    ((Destroyable) view).destroy();
                }
            }
        });
        int size = this.mRecycledViews.size();
        for (int i = 0; i < size; i++) {
            Iterator it = ((ArrayList) this.mRecycledViews.valueAt(i)).iterator();
            while (it.hasNext()) {
                View view = (View) it.next();
                if (view instanceof Destroyable) {
                    ((Destroyable) view).destroy();
                }
            }
        }
    }

    public boolean isInEditMode() {
        return this.mInEditMode;
    }

    public void teardown() {
        teardownItems();
        if (!(this.mHeaderViewContainer == null || this.mHeaderViewContainer.getViewTreeObserver() == null)) {
            this.mHeaderViewContainer.removeAllViews();
            this.mHeaderViewContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this.mHeaderFooterViewTreeLayoutListener);
        }
        if (!(this.mFooterViewContainer == null || this.mFooterViewContainer.getViewTreeObserver() == null)) {
            this.mFooterViewContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this.mHeaderFooterViewTreeLayoutListener);
        }
        if (getViewTreeObserver() != null) {
            getViewTreeObserver().removeGlobalOnLayoutListener(this.mViewTreeLayoutListener);
        }
    }

    public int getFirstItemPosition() {
        Iterator it = this.mVisibleItems.iterator();
        while (it.hasNext()) {
            ItemWrapper itemWrapper = (ItemWrapper) it.next();
            if (((Rect) this.mItemPositions.get(itemWrapper.index)).bottom > getScrollY()) {
                return itemWrapper.index;
            }
        }
        return 0;
    }

    public void restorePosition(final int i) {
        post(new Runnable() {
            public void run() {
                if (i < StaggeredGridView.this.mItemPositions.size() && StaggeredGridView.this.mItemPositions.get(i) != null) {
                    StaggeredGridView.this.scrollTo(0, ((Rect) StaggeredGridView.this.mItemPositions.get(i)).top);
                }
            }
        });
    }

    public void removeHeaderViews() {
        this.mHeaderViewContainer.removeAllViews();
    }
}
