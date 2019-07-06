package com.contextlogic.wish.ui.listview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.view.Recyclable;
import com.contextlogic.wish.util.ValueUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class HorizontalListView extends HorizontalScrollView implements ImageRestorable {
    protected Adapter mAdapter;
    protected int mContentWidth;
    private FrameLayout mFooterViewContainer;
    private LinearLayout mHeaderFooterContentHolder;
    private OnGlobalLayoutListener mHeaderFooterViewTreeLayoutListener;
    private FrameLayout mHeaderViewContainer;
    protected boolean mInitialized;
    private Runnable mInvalidateListViewJob;
    /* access modifiers changed from: private */
    public boolean mInvalidateListViewJobPosted;
    protected SparseArray<Rect> mItemPositions;
    private int mLastDataCount;
    private Rect mLastLoadedRect;
    protected FrameLayout mListViewContentHolder;
    protected OnItemClickListener mOnItemClickListener;
    private OnScrollListener mOnScrollListener;
    private OnViewVisibleListener mOnViewVisibleListener;
    private SparseArray<ArrayList<View>> mRecycledViews;
    private HashSet<Integer> mSeenPositions;
    private OnGlobalLayoutListener mViewTreeLayoutListener;
    protected ArrayList<ItemWrapper> mVisibleItems;

    public static abstract class Adapter extends BaseAdapter {
        public int getGravity() {
            return 48;
        }

        public abstract int getItemHeight(int i);

        public long getItemId(int i) {
            return 0;
        }

        public abstract int getItemWidth(int i);

        public boolean includeLeadingMargin() {
            return true;
        }

        public boolean includeTrailingMargin() {
            return true;
        }

        public int getItemMargin() {
            return (int) ValueUtil.convertDpToPx(16.0f);
        }

        public int getLeadMargin() {
            return getItemMargin();
        }

        public int getTrailingMargin() {
            return getItemMargin();
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

    public interface VisibleItemTask {
        void performTask(View view);
    }

    public HorizontalListView(Context context) {
        this(context, null);
    }

    public HorizontalListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HorizontalListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mRecycledViews = new SparseArray<>();
        this.mItemPositions = new SparseArray<>();
        this.mVisibleItems = new ArrayList<>();
        this.mLastDataCount = 0;
        this.mInitialized = false;
        this.mLastLoadedRect = new Rect();
        this.mSeenPositions = new HashSet<>();
        this.mInvalidateListViewJobPosted = false;
        this.mInvalidateListViewJob = new Runnable() {
            public void run() {
                HorizontalListView.this.mInvalidateListViewJobPosted = false;
                HorizontalListView.this.invalidateListView();
            }
        };
        this.mHeaderFooterContentHolder = new LinearLayout(getContext());
        this.mHeaderFooterContentHolder.setLayoutParams(new LayoutParams(-2, -1));
        this.mHeaderFooterContentHolder.setOrientation(0);
        addView(this.mHeaderFooterContentHolder);
        this.mHeaderViewContainer = new FrameLayout(getContext());
        this.mHeaderViewContainer.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        this.mHeaderFooterContentHolder.addView(this.mHeaderViewContainer);
        this.mListViewContentHolder = new FrameLayout(getContext());
        this.mListViewContentHolder.setLayoutParams(new LinearLayout.LayoutParams(0, -1));
        this.mHeaderFooterContentHolder.addView(this.mListViewContentHolder);
        this.mFooterViewContainer = new FrameLayout(getContext());
        this.mFooterViewContainer.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        this.mHeaderFooterContentHolder.addView(this.mFooterViewContainer);
        this.mHeaderFooterViewTreeLayoutListener = new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                HorizontalListView.this.postInvalidateListView();
            }
        };
        this.mHeaderViewContainer.getViewTreeObserver().addOnGlobalLayoutListener(this.mHeaderFooterViewTreeLayoutListener);
        this.mFooterViewContainer.getViewTreeObserver().addOnGlobalLayoutListener(this.mHeaderFooterViewTreeLayoutListener);
        this.mViewTreeLayoutListener = new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (!HorizontalListView.this.mInitialized && HorizontalListView.this.getWidth() > 0 && HorizontalListView.this.getHeight() > 0) {
                    HorizontalListView.this.mInitialized = true;
                    HorizontalListView.this.notifyDataSetChanged(true);
                }
            }
        };
        getViewTreeObserver().addOnGlobalLayoutListener(this.mViewTreeLayoutListener);
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
        invalidateListView();
        if (this.mOnScrollListener != null) {
            this.mOnScrollListener.onScrollChanged(i, i - i3, getWidth(), this.mHeaderFooterContentHolder.getWidth());
        }
    }

    /* access modifiers changed from: protected */
    public void addListViewCells(int i) {
        int count = this.mAdapter.getCount();
        if (count > 0) {
            while (i < count) {
                int i2 = this.mContentWidth != 0 ? this.mContentWidth + this.mAdapter.getItemMargin() : this.mAdapter.includeLeadingMargin() ? this.mAdapter.getLeadMargin() : 0;
                Rect rect = new Rect(i2, 0, this.mAdapter.getItemWidth(i) + i2, this.mAdapter.getItemHeight(i) + 0);
                this.mItemPositions.put(i, rect);
                this.mContentWidth = rect.right;
                i++;
            }
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mListViewContentHolder.getLayoutParams();
        int i3 = this.mContentWidth;
        if (this.mAdapter.includeTrailingMargin()) {
            i3 += this.mAdapter.getTrailingMargin();
        }
        layoutParams.width = i3;
        this.mListViewContentHolder.setLayoutParams(layoutParams);
        postInvalidateListView();
    }

    public void notifyDataSetChanged() {
        notifyDataSetChanged(false);
    }

    public void notifyDataSetChanged(boolean z) {
        if (this.mAdapter != null && this.mInitialized) {
            int i = this.mLastDataCount;
            if (z) {
                Iterator it = this.mVisibleItems.iterator();
                while (it.hasNext()) {
                    recycleView((ItemWrapper) it.next());
                }
                this.mVisibleItems.clear();
                this.mItemPositions.clear();
                this.mSeenPositions.clear();
                this.mContentWidth = 0;
                i = 0;
            }
            addListViewCells(i);
            this.mLastDataCount = this.mAdapter.getCount();
        }
    }

    /* access modifiers changed from: protected */
    public void postInvalidateListView() {
        if (!this.mInvalidateListViewJobPosted) {
            this.mInvalidateListViewJobPosted = true;
            post(this.mInvalidateListViewJob);
        }
    }

    /* access modifiers changed from: protected */
    public boolean invalidateListView() {
        if (this.mAdapter == null || !this.mInitialized || this.mAdapter.getCount() == 0) {
            return false;
        }
        int scrollX = getScrollX() - this.mHeaderViewContainer.getWidth();
        int width = (int) (((double) getWidth()) * 1.0d);
        int max = Math.max(0, scrollX - ((int) (((double) getWidth()) * 1.0d)));
        int width2 = getWidth() + scrollX + width;
        Rect rect = new Rect(Math.max(0, scrollX), 0, scrollX + getWidth(), getHeight());
        this.mLastLoadedRect.set(max, 0, width2, getHeight());
        boolean z = false;
        for (int size = this.mVisibleItems.size() - 1; size >= 0; size--) {
            ItemWrapper itemWrapper = (ItemWrapper) this.mVisibleItems.get(size);
            int i = itemWrapper.index;
            if (!Rect.intersects(this.mLastLoadedRect, (Rect) this.mItemPositions.get(i))) {
                recycleView(itemWrapper);
                this.mVisibleItems.remove(size);
                z = true;
            }
            if (Rect.intersects(rect, (Rect) this.mItemPositions.get(i)) && !this.mSeenPositions.contains(Integer.valueOf(i)) && this.mOnViewVisibleListener != null) {
                this.mSeenPositions.add(Integer.valueOf(i));
                this.mOnViewVisibleListener.onViewVisible(i, itemWrapper.view);
            }
        }
        int i2 = -1;
        int i3 = this.mVisibleItems.size() > 0 ? ((ItemWrapper) this.mVisibleItems.get(0)).index : -1;
        if (this.mVisibleItems.size() > 0) {
            i2 = ((ItemWrapper) this.mVisibleItems.get(this.mVisibleItems.size() - 1)).index;
        }
        int i4 = i3 - 1;
        while (i4 >= 0) {
            ItemWrapper addPositionToListView = addPositionToListView(i4, this.mLastLoadedRect);
            if (addPositionToListView == null) {
                break;
            }
            this.mVisibleItems.add(0, addPositionToListView);
            i4--;
            z = true;
        }
        int count = this.mAdapter.getCount();
        for (int i5 = i2 + 1; i5 < count; i5++) {
            ItemWrapper addPositionToListView2 = addPositionToListView(i5, this.mLastLoadedRect);
            if (addPositionToListView2 != null) {
                this.mVisibleItems.add(addPositionToListView2);
                z = true;
            } else if (this.mVisibleItems.size() > 0) {
                break;
            }
        }
        return z;
    }

    private ItemWrapper addPositionToListView(final int i, Rect rect) {
        Rect rect2 = (Rect) this.mItemPositions.get(i);
        if (rect2 == null || rect == null || !Rect.intersects(rect2, rect)) {
            return null;
        }
        int itemViewType = this.mAdapter.getItemViewType(i);
        View view = this.mAdapter.getView(i, getRecycledView(itemViewType), this.mListViewContentHolder);
        ItemWrapper itemWrapper = new ItemWrapper();
        itemWrapper.view = view;
        itemWrapper.index = i;
        itemWrapper.viewType = itemViewType;
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (HorizontalListView.this.mOnItemClickListener != null) {
                    HorizontalListView.this.mOnItemClickListener.onItemClick(i, view);
                }
            }
        });
        LayoutParams layoutParams = new LayoutParams(rect2.right - rect2.left, rect2.bottom - rect2.top);
        layoutParams.gravity = this.mAdapter.getGravity();
        layoutParams.topMargin = rect2.top;
        layoutParams.leftMargin = rect2.left;
        if (view.getParent() != null) {
            view.setVisibility(0);
            view.setLayoutParams(layoutParams);
        } else {
            this.mListViewContentHolder.addView(view, layoutParams);
        }
        return itemWrapper;
    }

    /* access modifiers changed from: protected */
    public void recycleView(ItemWrapper itemWrapper) {
        View view = itemWrapper.view;
        if (view instanceof Recyclable) {
            ((Recyclable) view).recycle();
        }
        if (view.getParent() == this.mListViewContentHolder) {
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

    public void teardown() {
        releaseImages();
        if (!(this.mHeaderViewContainer == null || this.mHeaderViewContainer.getViewTreeObserver() == null)) {
            this.mHeaderViewContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this.mHeaderFooterViewTreeLayoutListener);
        }
        if (!(this.mFooterViewContainer == null || this.mFooterViewContainer.getViewTreeObserver() == null)) {
            this.mFooterViewContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this.mHeaderFooterViewTreeLayoutListener);
        }
        if (getViewTreeObserver() != null) {
            getViewTreeObserver().removeGlobalOnLayoutListener(this.mViewTreeLayoutListener);
        }
    }
}
