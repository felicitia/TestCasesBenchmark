package com.contextlogic.wish.activity.rewards.redesign;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.api.model.WishRewardsHelpInfo;
import com.contextlogic.wish.api.model.WishRewardsHelpInfo.Chart;
import com.contextlogic.wish.api.model.WishRewardsHelpInfo.RowType;
import com.contextlogic.wish.api.model.WishRewardsReturnPolicyInformation;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.scrollview.ObservableScrollView;
import com.contextlogic.wish.ui.scrollview.ObservableScrollView.ScrollViewListener;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.Iterator;

public class RewardsHelpView extends RewardsPagerView implements ScrollViewListener {
    private LinearLayout mContainer;
    private RewardsFragment mFragment;
    private ObservableScrollView mScroller;

    public int getLoadingContentLayoutResourceId() {
        return R.layout.rewards_fragment_information;
    }

    public void onScrollChanged(int i, int i2) {
    }

    public void releaseImages() {
    }

    public RewardsHelpView(Context context) {
        super(context);
    }

    public void setup(int i, RewardsFragment rewardsFragment) {
        super.setup(i, rewardsFragment);
        this.mContainer = (LinearLayout) this.mRootLayout.findViewById(R.id.rewards_fragment_information_container);
        this.mFragment = rewardsFragment;
        this.mScroller = (ObservableScrollView) this.mRootLayout.findViewById(R.id.rewards_fragment_information_scroller);
        this.mScroller.setScrollViewListener(new ScrollViewListener() {
            public void onScrollChanged(int i, int i2) {
                RewardsHelpView.this.handleScrollChanged(i, i2);
            }
        });
        loadHelpInfo();
    }

    private void loadHelpInfo() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, RewardsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, RewardsServiceFragment rewardsServiceFragment) {
                rewardsServiceFragment.getRedeemableRewardsHelpInfoService();
            }
        });
    }

    public int getCurrentScrollY() {
        if (this.mScroller != null) {
            return this.mScroller.getScrollY();
        }
        return 0;
    }

    public int getFirstItemPosition() {
        if (this.mScroller.getChildCount() > 0) {
            LinearLayout linearLayout = (LinearLayout) this.mScroller.getChildAt(0);
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                if (linearLayout.getChildAt(i).getBottom() > this.mScroller.getScrollY()) {
                    return i;
                }
            }
        }
        return 0;
    }

    public boolean hasItems() {
        return isLoadingComplete();
    }

    public void handleReload() {
        loadHelpInfo();
    }

    /* access modifiers changed from: protected */
    public void cancelNetworkRequest() {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, RewardsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, RewardsServiceFragment rewardsServiceFragment) {
                rewardsServiceFragment.cancelLoadingHelpInfo();
            }
        });
    }

    public void cleanup() {
        releaseImages();
        cancelNetworkRequest();
    }

    public void handleLoadingSuccess(WishRewardsHelpInfo wishRewardsHelpInfo) {
        markLoadingComplete();
        ThemedTextView themedTextView = (ThemedTextView) this.mRootLayout.findViewById(R.id.rewards_fragment_information_title);
        themedTextView.setText(wishRewardsHelpInfo.getTitle());
        int i = 0;
        ((LayoutParams) themedTextView.getLayoutParams()).setMargins(0, this.mFragment.getTabAreaSize() + WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.twenty_four_padding), 0, 0);
        Iterator it = wishRewardsHelpInfo.getRowTypes().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            RowType rowType = (RowType) it.next();
            if (rowType == RowType.INFORMATION) {
                RewardsHelpInformationView rewardsHelpInformationView = new RewardsHelpInformationView(getContext());
                rewardsHelpInformationView.setup((WishRewardsReturnPolicyInformation) wishRewardsHelpInfo.getInformationRows().get(i));
                this.mContainer.addView(rewardsHelpInformationView);
                i++;
            } else if (rowType == RowType.CHART) {
                RewardsHelpChartView rewardsHelpChartView = new RewardsHelpChartView(getContext());
                rewardsHelpChartView.setup((Chart) wishRewardsHelpInfo.getCharts().get(i2));
                this.mContainer.addView(rewardsHelpChartView);
                i2++;
            }
        }
    }

    public void onFailure() {
        markLoadingErrored();
    }
}
