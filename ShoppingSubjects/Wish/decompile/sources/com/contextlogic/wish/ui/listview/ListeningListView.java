package com.contextlogic.wish.ui.listview;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class ListeningListView extends ListView {
    private SparseIntArray mChildrenHeights;
    private boolean mDragging;
    private boolean mFirstScroll;
    private boolean mIntercepted;
    private ScrollViewListener mListener;
    /* access modifiers changed from: private */
    public OnScrollListener mOriginalScrollListener;
    private int mPrevFirstVisibleChildHeight = -1;
    private int mPrevFirstVisiblePosition;
    private MotionEvent mPrevMoveEvent;
    private int mPrevScrollY;
    private int mPrevScrolledChildrenHeight;
    private OnScrollListener mScrollListener = new OnScrollListener() {
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (ListeningListView.this.mOriginalScrollListener != null) {
                ListeningListView.this.mOriginalScrollListener.onScrollStateChanged(absListView, i);
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (ListeningListView.this.mOriginalScrollListener != null) {
                ListeningListView.this.mOriginalScrollListener.onScroll(absListView, i, i2, i3);
            }
            ListeningListView.this.onScrollChanged();
        }
    };
    private ScrollState mScrollState;
    private int mScrollY;
    private ViewGroup mTouchInterceptionViewGroup;

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        SparseIntArray childrenHeights;
        int prevFirstVisibleChildHeight;
        int prevFirstVisiblePosition;
        int prevScrollY;
        int prevScrolledChildrenHeight;
        int scrollY;

        SavedState(Parcelable parcelable) {
            super(parcelable);
            this.prevFirstVisibleChildHeight = -1;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.prevFirstVisibleChildHeight = -1;
            this.prevFirstVisiblePosition = parcel.readInt();
            this.prevFirstVisibleChildHeight = parcel.readInt();
            this.prevScrolledChildrenHeight = parcel.readInt();
            this.prevScrollY = parcel.readInt();
            this.scrollY = parcel.readInt();
            this.childrenHeights = new SparseIntArray();
            int readInt = parcel.readInt();
            if (readInt > 0) {
                for (int i = 0; i < readInt; i++) {
                    this.childrenHeights.put(parcel.readInt(), parcel.readInt());
                }
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.prevFirstVisiblePosition);
            parcel.writeInt(this.prevFirstVisibleChildHeight);
            parcel.writeInt(this.prevScrolledChildrenHeight);
            parcel.writeInt(this.prevScrollY);
            parcel.writeInt(this.scrollY);
            int size = this.childrenHeights == null ? 0 : this.childrenHeights.size();
            parcel.writeInt(size);
            if (size > 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    parcel.writeInt(this.childrenHeights.keyAt(i2));
                    parcel.writeInt(this.childrenHeights.valueAt(i2));
                }
            }
        }
    }

    public enum ScrollState {
        STOP,
        UP,
        DOWN
    }

    public interface ScrollViewListener {
        void onScrollChanged(int i, int i2);
    }

    public ListeningListView(Context context) {
        super(context);
        init();
    }

    public ListeningListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ListeningListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        this.mPrevFirstVisiblePosition = savedState.prevFirstVisiblePosition;
        this.mPrevFirstVisibleChildHeight = savedState.prevFirstVisibleChildHeight;
        this.mPrevScrolledChildrenHeight = savedState.prevScrolledChildrenHeight;
        this.mPrevScrollY = savedState.prevScrollY;
        this.mScrollY = savedState.scrollY;
        this.mChildrenHeights = savedState.childrenHeights;
        super.onRestoreInstanceState(savedState.getSuperState());
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.prevFirstVisiblePosition = this.mPrevFirstVisiblePosition;
        savedState.prevFirstVisibleChildHeight = this.mPrevFirstVisibleChildHeight;
        savedState.prevScrolledChildrenHeight = this.mPrevScrolledChildrenHeight;
        savedState.prevScrollY = this.mPrevScrollY;
        savedState.scrollY = this.mScrollY;
        savedState.childrenHeights = this.mChildrenHeights;
        return savedState;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mListener != null && motionEvent.getActionMasked() == 0) {
            this.mDragging = true;
            this.mFirstScroll = true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        final ViewGroup viewGroup;
        if (this.mListener != null) {
            switch (motionEvent.getActionMasked()) {
                case 1:
                case 3:
                    this.mIntercepted = false;
                    this.mDragging = false;
                    break;
                case 2:
                    if (this.mPrevMoveEvent == null) {
                        this.mPrevMoveEvent = motionEvent;
                    }
                    float y = motionEvent.getY() - this.mPrevMoveEvent.getY();
                    this.mPrevMoveEvent = MotionEvent.obtainNoHistory(motionEvent);
                    if (((float) getCurrentScrollY()) - y <= 0.0f) {
                        if (this.mIntercepted) {
                            return false;
                        }
                        if (this.mTouchInterceptionViewGroup == null) {
                            viewGroup = (ViewGroup) getParent();
                        } else {
                            viewGroup = this.mTouchInterceptionViewGroup;
                        }
                        View view = this;
                        float f = 0.0f;
                        float f2 = 0.0f;
                        while (view != 0 && view != viewGroup) {
                            f += (float) (view.getLeft() - view.getScrollX());
                            f2 += (float) (view.getTop() - view.getScrollY());
                            view = (View) view.getParent();
                        }
                        final MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                        obtainNoHistory.offsetLocation(f, f2);
                        if (!viewGroup.onInterceptTouchEvent(obtainNoHistory)) {
                            return super.onTouchEvent(motionEvent);
                        }
                        this.mIntercepted = true;
                        obtainNoHistory.setAction(0);
                        post(new Runnable() {
                            public void run() {
                                viewGroup.dispatchTouchEvent(obtainNoHistory);
                            }
                        });
                        return false;
                    }
                    break;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOriginalScrollListener = onScrollListener;
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.mListener = scrollViewListener;
    }

    public void setTouchInterceptionViewGroup(ViewGroup viewGroup) {
        this.mTouchInterceptionViewGroup = viewGroup;
    }

    public int getCurrentScrollY() {
        return this.mScrollY;
    }

    private void init() {
        this.mChildrenHeights = new SparseIntArray();
        super.setOnScrollListener(this.mScrollListener);
    }

    /* access modifiers changed from: private */
    public void onScrollChanged() {
        int i;
        int i2;
        int i3;
        int i4;
        if (this.mListener != null && getChildCount() > 0) {
            int firstVisiblePosition = getFirstVisiblePosition();
            int firstVisiblePosition2 = getFirstVisiblePosition();
            int i5 = 0;
            while (firstVisiblePosition2 <= getLastVisiblePosition()) {
                if (this.mChildrenHeights.indexOfKey(firstVisiblePosition2) < 0 || getChildAt(i5).getHeight() != this.mChildrenHeights.get(firstVisiblePosition2)) {
                    this.mChildrenHeights.put(firstVisiblePosition2, getChildAt(i5).getHeight());
                }
                firstVisiblePosition2++;
                i5++;
            }
            View childAt = getChildAt(0);
            if (childAt != null) {
                if (this.mPrevFirstVisiblePosition < firstVisiblePosition) {
                    if (firstVisiblePosition - this.mPrevFirstVisiblePosition != 1) {
                        i3 = 0;
                        for (int i6 = firstVisiblePosition - 1; i6 > this.mPrevFirstVisiblePosition; i6--) {
                            if (this.mChildrenHeights.indexOfKey(i6) > 0) {
                                i4 = this.mChildrenHeights.get(i6);
                            } else {
                                i4 = childAt.getHeight();
                            }
                            i3 += i4;
                        }
                    } else {
                        i3 = 0;
                    }
                    this.mPrevScrolledChildrenHeight += this.mPrevFirstVisibleChildHeight + i3;
                    this.mPrevFirstVisibleChildHeight = childAt.getHeight();
                } else if (firstVisiblePosition < this.mPrevFirstVisiblePosition) {
                    if (this.mPrevFirstVisiblePosition - firstVisiblePosition != 1) {
                        i = 0;
                        for (int i7 = this.mPrevFirstVisiblePosition - 1; i7 > firstVisiblePosition; i7--) {
                            if (this.mChildrenHeights.indexOfKey(i7) > 0) {
                                i2 = this.mChildrenHeights.get(i7);
                            } else {
                                i2 = childAt.getHeight();
                            }
                            i += i2;
                        }
                    } else {
                        i = 0;
                    }
                    this.mPrevScrolledChildrenHeight -= childAt.getHeight() + i;
                    this.mPrevFirstVisibleChildHeight = childAt.getHeight();
                } else if (firstVisiblePosition == 0) {
                    this.mPrevFirstVisibleChildHeight = childAt.getHeight();
                }
                if (this.mPrevFirstVisibleChildHeight < 0) {
                    this.mPrevFirstVisibleChildHeight = 0;
                }
                this.mScrollY = this.mPrevScrolledChildrenHeight - childAt.getTop();
                this.mPrevFirstVisiblePosition = firstVisiblePosition;
                if (this.mFirstScroll) {
                    this.mFirstScroll = false;
                }
                if (this.mPrevScrollY < this.mScrollY) {
                    this.mScrollState = ScrollState.UP;
                } else if (this.mScrollY < this.mPrevScrollY) {
                    this.mScrollState = ScrollState.DOWN;
                } else {
                    this.mScrollState = ScrollState.STOP;
                }
                this.mListener.onScrollChanged(this.mScrollY, this.mScrollY - this.mPrevScrollY);
                this.mPrevScrollY = this.mScrollY;
            }
        }
    }
}
