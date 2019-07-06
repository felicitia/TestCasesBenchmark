package com.contextlogic.wish.activity.commerceloan;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.LoadingUiFragment;
import com.contextlogic.wish.api.model.WishCommerceLoanLearnMoreInfo;
import com.contextlogic.wish.api.model.WishCommerceLoanLearnMoreInfo.LearnMoreInfoParagraph;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;

public class CommerceLoanPayHalfLearnMoreFragment extends LoadingUiFragment<CommerceLoanLearnMoreActivity> {
    private LinearLayout mLearnMoreInfoContainer;
    private ThemedTextView mLearnMoreInfoTitle;

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.pay_half_learn_more_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public void loadLearnMoreInfo() {
        withServiceFragment(new ServiceTask<BaseActivity, CommerceLoanPayHalfLearnMoreServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CommerceLoanPayHalfLearnMoreServiceFragment commerceLoanPayHalfLearnMoreServiceFragment) {
                commerceLoanPayHalfLearnMoreServiceFragment.loadLearnMoreInfo();
            }
        });
    }

    public void handleLearnMoreInfoLoadingSuccess(WishCommerceLoanLearnMoreInfo wishCommerceLoanLearnMoreInfo) {
        this.mLearnMoreInfoTitle.setText(wishCommerceLoanLearnMoreInfo.getMainTitle());
        ArrayList paragraphs = wishCommerceLoanLearnMoreInfo.getParagraphs();
        for (int i = 0; i < paragraphs.size(); i++) {
            CommerceLoanLearnMoreInfoParagraphView commerceLoanLearnMoreInfoParagraphView = new CommerceLoanLearnMoreInfoParagraphView(getContext());
            commerceLoanLearnMoreInfoParagraphView.setup((LearnMoreInfoParagraph) paragraphs.get(i), i);
            this.mLearnMoreInfoContainer.addView(commerceLoanLearnMoreInfoParagraphView);
        }
        getLoadingPageView().markLoadingComplete();
    }

    public void handleReload() {
        loadLearnMoreInfo();
    }

    public void initializeLoadingContentView(View view) {
        this.mLearnMoreInfoTitle = (ThemedTextView) findViewById(R.id.pay_half_learn_more_fragment_main_title);
        this.mLearnMoreInfoContainer = (LinearLayout) findViewById(R.id.pay_half_learn_more_fragment_container);
        loadLearnMoreInfo();
    }

    public boolean hasItems() {
        return !TextUtils.isEmpty(this.mLearnMoreInfoTitle.getText());
    }
}
