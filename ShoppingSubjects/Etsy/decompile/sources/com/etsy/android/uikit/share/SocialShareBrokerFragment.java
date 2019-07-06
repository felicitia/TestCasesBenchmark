package com.etsy.android.uikit.share;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import com.etsy.android.lib.a.C0051a;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.a.a;
import com.etsy.android.lib.util.l;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.nav.b;
import com.etsy.android.uikit.util.SocialShareUtil;
import java.util.HashMap;

public abstract class SocialShareBrokerFragment extends ShareBrokerFragment {
    private static final int DEFAULT_SPAN_COUNT = 4;
    private View mHeaderView;

    public static class SocialShareGridLayoutManager extends GridLayoutManager {
        private int[] mMeasuredDimension = new int[2];

        public boolean canScrollVertically() {
            return false;
        }

        public SocialShareGridLayoutManager(Context context, int i) {
            super(context, i);
            setAutoMeasureEnabled(false);
        }

        public void onMeasure(Recycler recycler, State state, int i, int i2) {
            int mode = MeasureSpec.getMode(i);
            int mode2 = MeasureSpec.getMode(i2);
            int size = MeasureSpec.getSize(i);
            int size2 = MeasureSpec.getSize(i2);
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; ((double) i5) < Math.ceil(((double) getItemCount()) / ((double) getSpanCount())); i5++) {
                measureScrapChild(recycler, i5, MeasureSpec.makeMeasureSpec(i5, 0), MeasureSpec.makeMeasureSpec(i5, 0), this.mMeasuredDimension);
                if (getOrientation() == 0) {
                    i3 += this.mMeasuredDimension[0];
                    if (i5 == 0) {
                        i4 = this.mMeasuredDimension[1];
                    }
                } else {
                    i4 += this.mMeasuredDimension[1];
                    if (i5 == 0) {
                        i3 = this.mMeasuredDimension[0];
                    }
                }
            }
            if (mode == 1073741824) {
                i3 = size;
            }
            if (mode2 == 1073741824) {
                i4 = size2;
            }
            setMeasuredDimension(i3, i4);
        }

        private void measureScrapChild(Recycler recycler, int i, int i2, int i3, int[] iArr) {
            View viewForPosition = recycler.getViewForPosition(i);
            if (viewForPosition != null) {
                LayoutParams layoutParams = (LayoutParams) viewForPosition.getLayoutParams();
                viewForPosition.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight(), layoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom(), layoutParams.height));
                iArr[0] = viewForPosition.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                iArr[1] = viewForPosition.getMeasuredHeight() + layoutParams.bottomMargin + layoutParams.topMargin;
                recycler.recycleView(viewForPosition);
            }
        }
    }

    public abstract View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mUrl != null) {
            this.mUrl = SocialShareUtil.a(this.mUrl);
            shortenUrl();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        hideHeaderInLandscape();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        hideHeaderInLandscape();
    }

    private void hideHeaderInLandscape() {
        if (this.mHeaderView != null) {
            int i = 0;
            boolean z = !l.c((Activity) getActivity()) && l.e((Activity) getActivity());
            View view = this.mHeaderView;
            if (z) {
                i = 8;
            }
            view.setVisibility(i);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(k.fragment_social_share, viewGroup, false);
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(i.share_layout);
        ViewGroup viewGroup3 = (ViewGroup) inflate.findViewById(i.social_share_content);
        this.mHeaderView = onCreateHeaderView(layoutInflater, viewGroup2, bundle);
        if (this.mHeaderView != null) {
            viewGroup2.addView(this.mHeaderView, 0);
        }
        viewGroup2.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    SocialShareBrokerFragment.this.dismiss();
                }
                return true;
            }
        });
        viewGroup3.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        hideHeaderInLandscape();
        return inflate;
    }

    /* access modifiers changed from: protected */
    public LayoutManager createLayoutManager() {
        return new SocialShareGridLayoutManager(getActivity(), 4);
    }

    /* access modifiers changed from: protected */
    public BaseRecyclerViewAdapter<ResolveInfo> createAdapter() {
        return new ShareIntentListAdapter(getActivity(), k.standard_image_grid_item);
    }

    /* access modifiers changed from: protected */
    public void dismiss() {
        getAnalyticsContext().a("share_sheet_dismissed", null);
        a.a(SocialShareUtil.a("share_sheet_dismissed"));
        getFragmentManager().beginTransaction().setCustomAnimations(0, C0051a.nav_frag_bottom_pop_exit).hide(this).commit();
        if (getView() != null) {
            getView().postDelayed(new c(this), 200);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$dismiss$0$SocialShareBrokerFragment() {
        if (getActivity() != null) {
            b.b(getActivity()).h();
        }
    }

    public void onIntentItemClick(int i) {
        super.onIntentItemClick(i);
        a.a(SocialShareUtil.a("action_tapped", ((ResolveInfo) this.mAdapter.getItem(i)).activityInfo.packageName));
    }

    /* access modifiers changed from: protected */
    public void onShareError(final ResolveInfo resolveInfo) {
        super.onShareError(resolveInfo);
        getAnalyticsContext().a("action_failure", new HashMap<AnalyticsLogAttribute, Object>() {
            {
                put(AnalyticsLogAttribute.SI_CHANNEL, resolveInfo.activityInfo.packageName);
            }
        });
        a.a(SocialShareUtil.a("action_failure", resolveInfo.activityInfo.packageName));
    }
}
