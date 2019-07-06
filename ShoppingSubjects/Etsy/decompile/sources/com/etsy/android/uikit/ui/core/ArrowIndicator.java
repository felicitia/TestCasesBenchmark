package com.etsy.android.uikit.ui.core;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnChildAttachStateChangeListener;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.etsy.android.lib.a.f;
import com.etsy.android.uikit.util.AnimationUtil;
import com.etsy.android.uikit.util.j;

public class ArrowIndicator extends AppCompatImageView {
    /* access modifiers changed from: private */
    public View mAnimateTo;
    @Nullable
    private ViewPropertyAnimator mAnimator;
    @Nullable
    private OnChildAttachStateChangeListener mOnChildAttachStateChangeListener;
    /* access modifiers changed from: private */
    @Nullable
    public RecyclerView mRecyclerView;
    @Nullable
    private OnScrollListener mRecyclerViewScrollListener;
    /* access modifiers changed from: private */
    public int mSelectedPosition = -1;
    private int mTwoPaneArrowYOffset;

    public ArrowIndicator(Context context) {
        super(context);
        init();
    }

    public ArrowIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ArrowIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mTwoPaneArrowYOffset = getResources().getDimensionPixelSize(f.tpane_arrow_height);
    }

    public void moveArrowToView(View view) {
        if (view != null && ViewCompat.isLaidOut(view)) {
            setTranslationY((float) getNewY(view));
            setVisibility(0);
        }
    }

    public void animateArrowToView(final View view) {
        if (view == null) {
            return;
        }
        if (!ViewCompat.isLaidOut(view) || view.isDirty()) {
            this.mAnimateTo = view;
            j.a(view.getViewTreeObserver(), (OnGlobalLayoutListener) new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    j.b(view.getViewTreeObserver(), (OnGlobalLayoutListener) this);
                    if (ArrowIndicator.this.mAnimateTo == view) {
                        ArrowIndicator.this.animateArrowToView(view);
                    }
                }
            });
            return;
        }
        animateArrowToY(view);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAnimateTo = null;
        if (this.mAnimator != null) {
            this.mAnimator.cancel();
            this.mAnimator = null;
        }
        if (this.mRecyclerView != null) {
            this.mRecyclerView.removeOnScrollListener(this.mRecyclerViewScrollListener);
            this.mRecyclerView.removeOnChildAttachStateChangeListener(this.mOnChildAttachStateChangeListener);
            this.mRecyclerView = null;
        }
    }

    private int getNewY(View view) {
        View view2 = (View) view.getParent();
        int i = 0;
        while (view2 != null && view2 != getParent()) {
            i += view2.getTop();
            view2 = (View) view2.getParent();
        }
        return ((i + view.getTop()) + (view.getHeight() / 2)) - this.mTwoPaneArrowYOffset;
    }

    private void animateArrowToY(View view) {
        int newY = getNewY(view);
        if (getVisibility() == 8) {
            setTranslationY((float) newY);
            AnimationUtil.a((View) this, 200);
            return;
        }
        this.mAnimator = animate().translationY((float) newY).setDuration(200);
        this.mAnimator.start();
        setVisibility(0);
    }

    public void setSelectedPosition(int i) {
        if (this.mRecyclerView == null) {
            throw new RuntimeException("ArrowIndicator must be following a RecyclerView to set the selected position");
        }
        int i2 = this.mSelectedPosition;
        if (i != this.mSelectedPosition) {
            ViewHolder findViewHolderForAdapterPosition = this.mRecyclerView.findViewHolderForAdapterPosition(i2);
            ViewHolder findViewHolderForAdapterPosition2 = this.mRecyclerView.findViewHolderForAdapterPosition(i);
            if (!(i2 == -1 || findViewHolderForAdapterPosition == null)) {
                moveArrowToView(findViewHolderForAdapterPosition.itemView);
            }
            this.mSelectedPosition = i;
            if (findViewHolderForAdapterPosition2 != null) {
                animateArrowToView(findViewHolderForAdapterPosition2.itemView);
            }
        }
    }

    public void follow(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        this.mRecyclerView.removeOnScrollListener(this.mRecyclerViewScrollListener);
        this.mRecyclerViewScrollListener = new OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                ArrowIndicator.this.setTranslationY(ArrowIndicator.this.getTranslationY() - ((float) i2));
            }
        };
        recyclerView.addOnScrollListener(this.mRecyclerViewScrollListener);
        recyclerView.removeOnChildAttachStateChangeListener(this.mOnChildAttachStateChangeListener);
        this.mOnChildAttachStateChangeListener = new OnChildAttachStateChangeListener() {
            public void onChildViewAttachedToWindow(final View view) {
                if (ArrowIndicator.this.mSelectedPosition == ArrowIndicator.this.mRecyclerView.getLayoutManager().getPosition(view)) {
                    ArrowIndicator.this.mRecyclerView.getLayoutManager().postOnAnimation(new Runnable() {
                        public void run() {
                            ArrowIndicator.this.moveArrowToView(view);
                        }
                    });
                }
            }

            public void onChildViewDetachedFromWindow(View view) {
                if (ArrowIndicator.this.mSelectedPosition == ArrowIndicator.this.mRecyclerView.getLayoutManager().getPosition(view)) {
                    ArrowIndicator.this.setVisibility(8);
                }
            }
        };
        recyclerView.addOnChildAttachStateChangeListener(this.mOnChildAttachStateChangeListener);
    }
}
