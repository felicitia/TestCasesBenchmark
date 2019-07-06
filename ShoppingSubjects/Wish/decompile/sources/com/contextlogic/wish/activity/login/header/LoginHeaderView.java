package com.contextlogic.wish.activity.login.header;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.login.LoginDynamicProductAdapter;
import com.contextlogic.wish.activity.login.LoginProductAdapter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.grid.StaggeredGridView;
import com.contextlogic.wish.util.DisplayUtil;
import java.util.ArrayList;

public class LoginHeaderView extends FrameLayout {
    /* access modifiers changed from: private */
    public boolean mCanScroll;
    private View mHeaderBackground;
    private StaggeredGridView mProductGrid;
    /* access modifiers changed from: private */
    public Runnable mScrollRunnable;

    public LoginHeaderView(Context context) {
        this(context, null);
    }

    public LoginHeaderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoginHeaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCanScroll = true;
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.login_fragment_header, this);
        this.mHeaderBackground = inflate.findViewById(R.id.login_fragment_redesign_background);
        TextView textView = (TextView) findViewById(R.id.login_fragment_tagline_text);
        Resources resources = getResources();
        StringBuilder sb = new StringBuilder();
        sb.append("tagline_");
        WishApplication.getInstance();
        sb.append(WishApplication.getAppType());
        int identifier = resources.getIdentifier(sb.toString(), "string", getContext().getPackageName());
        if (!(getContext() == null || identifier == 0)) {
            textView.setText(getContext().getString(identifier));
        }
        this.mProductGrid = (StaggeredGridView) inflate.findViewById(R.id.login_fragment_product_grid);
        this.mProductGrid.disableInteraction();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        startProductGridScroll();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopProductGridScroll();
    }

    public StaggeredGridView getProductGrid() {
        return this.mProductGrid;
    }

    public void setUpSignInAdapterImages(ArrayList<WishImage> arrayList) {
        if (ExperimentDataCenter.getInstance().shouldShowLoginRedesignProductFeed()) {
            if (arrayList == null || arrayList.size() <= 10) {
                this.mProductGrid.setAdapter(new LoginProductAdapter(getContext()));
            } else {
                this.mProductGrid.setAdapter(new LoginDynamicProductAdapter(getContext(), arrayList));
            }
            this.mProductGrid.notifyDataSetChanged(true);
            this.mHeaderBackground.setAlpha(0.9f);
        }
    }

    /* access modifiers changed from: private */
    public void scrollProductGrid() {
        if (this.mProductGrid != null) {
            int displayHeight = DisplayUtil.getDisplayHeight();
            int max = Math.max(DisplayUtil.getDisplayWidth(), displayHeight);
            int scrollY = this.mProductGrid.getScrollY();
            int contentHeight = this.mProductGrid.getContentHeight();
            if (contentHeight > 0) {
                this.mCanScroll = scrollY < contentHeight - (displayHeight * 2);
                int max2 = Math.max(1, (int) ((((double) max) / 30.0d) / 33.333333333333336d));
                if (this.mCanScroll) {
                    this.mProductGrid.scrollBy(0, max2);
                }
            }
        }
    }

    public synchronized void startProductGridScroll() {
        final Handler handler = getHandler();
        if (this.mScrollRunnable == null && this.mCanScroll && handler != null) {
            this.mScrollRunnable = new Runnable() {
                public void run() {
                    LoginHeaderView.this.scrollProductGrid();
                    if (!LoginHeaderView.this.mCanScroll) {
                        LoginHeaderView.this.stopProductGridScroll();
                    } else {
                        handler.postDelayed(LoginHeaderView.this.mScrollRunnable, 30);
                    }
                }
            };
            handler.post(this.mScrollRunnable);
        }
    }

    public synchronized void stopProductGridScroll() {
        Handler handler = getHandler();
        if (!(this.mScrollRunnable == null || handler == null)) {
            getHandler().removeCallbacks(this.mScrollRunnable);
            this.mScrollRunnable = null;
        }
    }
}
