package com.contextlogic.wish.activity.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.viewpager.BaseTabStripInterface;

public abstract class CollapsableFeedHeaderView extends BaseFeedHeaderView {
    private View mBottomBorder;
    private FrameLayout mCollapsedLayoutHolder;
    private View mCollapsedView;
    private FrameLayout mExpandedLayoutHolder;
    private View mExpandedView;
    private Interpolator mInterpolator;
    protected long mLastClicked = 0;
    protected BaseTabStripInterface mTabStripInterface;

    public interface Interpolator {
        float interpolate(float f);
    }

    public class LinearInterpolator implements Interpolator {
        public float interpolate(float f) {
            return f;
        }

        public LinearInterpolator() {
        }
    }

    public class QuadraticClipInterpolator implements Interpolator {
        private float mClipValue;

        public QuadraticClipInterpolator(float f) {
            this.mClipValue = f;
        }

        public float interpolate(float f) {
            return Math.max(0.0f, (1.0f / (1.0f - this.mClipValue)) * ((f * f) - this.mClipValue));
        }
    }

    public boolean addViewsToParent() {
        return true;
    }

    public abstract View getCollapsedView();

    public abstract View getExpandedView();

    public CollapsableFeedHeaderView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        if (addViewsToParent()) {
            View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.collapsible_feed_header, this);
            this.mExpandedLayoutHolder = (FrameLayout) inflate.findViewById(R.id.collapsible_header_expanded_layout_holder);
            this.mCollapsedLayoutHolder = (FrameLayout) inflate.findViewById(R.id.collapsible_header_collapsed_layout_holder);
            this.mBottomBorder = inflate.findViewById(R.id.collapsible_header_bottom_border);
        }
        this.mInterpolator = new LinearInterpolator();
        setOnClickListener(null);
    }

    public void setTabStripInterface(BaseTabStripInterface baseTabStripInterface) {
        this.mTabStripInterface = baseTabStripInterface;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    /* access modifiers changed from: protected */
    public final void updateUI(boolean z) {
        this.mExpandedView = getExpandedView();
        this.mCollapsedView = getCollapsedView();
        if (!(!addViewsToParent() || this.mExpandedLayoutHolder == null || this.mCollapsedLayoutHolder == null)) {
            this.mExpandedLayoutHolder.removeAllViews();
            this.mCollapsedLayoutHolder.removeAllViews();
            if (z) {
                this.mBottomBorder.setVisibility(0);
            }
            this.mExpandedLayoutHolder.addView(this.mExpandedView);
            this.mCollapsedLayoutHolder.addView(this.mCollapsedView);
        }
        interpolate(0.0f);
    }

    public int getCollapsedHeight() {
        return getHeight() - this.mCollapsedLayoutHolder.getHeight();
    }

    public final void interpolate(float f) {
        if (this.mCollapsedView != null) {
            this.mCollapsedView.setAlpha(this.mInterpolator.interpolate(f));
        }
    }

    public void setOnClickListener(final OnClickListener onClickListener) {
        if (!addViewsToParent()) {
            super.setOnClickListener(onClickListener);
        } else {
            super.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    CollapsableFeedHeaderView.this.mLastClicked = System.currentTimeMillis();
                    CollapsableFeedHeaderView.this.interpolate(0.0f);
                    if (CollapsableFeedHeaderView.this.mTabStripInterface != null) {
                        CollapsableFeedHeaderView.this.mTabStripInterface.setTabAreaOffset(0);
                    }
                    if (onClickListener != null) {
                        onClickListener.onClick(view);
                    }
                }
            });
        }
    }

    public long getLastClickedTime() {
        return this.mLastClicked;
    }
}
