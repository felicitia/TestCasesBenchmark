package com.contextlogic.wish.activity.commerceloan;

import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.api.model.WishCommerceLoanLearnMoreInfo;
import com.contextlogic.wish.api.model.WishCommerceLoanLearnMoreInfo.LearnMoreInfoParagraph;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;

public class CommerceLoanLearnMoreFragment extends UiFragment<CommerceLoanLearnMoreActivity> {
    private WishCommerceLoanLearnMoreInfo mLearnMoreInfo;
    private LinearLayout mLearnMoreInfoContainer;
    private ThemedTextView mLearnMoreInfoTitle;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.commerce_loan_learn_more_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mLearnMoreInfoTitle = (ThemedTextView) findViewById(R.id.commerce_loan_learn_more_fragment_main_title);
        this.mLearnMoreInfoContainer = (LinearLayout) findViewById(R.id.commerce_loan_learn_more_fragment_container);
        loadLearnMoreInfo();
    }

    public void loadLearnMoreInfo() {
        withServiceFragment(new ServiceTask<BaseActivity, CommerceLoanLearnMoreServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CommerceLoanLearnMoreServiceFragment commerceLoanLearnMoreServiceFragment) {
                commerceLoanLearnMoreServiceFragment.loadLearnMoreInfo();
            }
        });
    }

    public void handleLearnMoreInfoLoadingSuccess(WishCommerceLoanLearnMoreInfo wishCommerceLoanLearnMoreInfo) {
        if (wishCommerceLoanLearnMoreInfo != null) {
            this.mLearnMoreInfo = wishCommerceLoanLearnMoreInfo;
            this.mLearnMoreInfoTitle.setText(wishCommerceLoanLearnMoreInfo.getMainTitle());
            ArrayList paragraphs = wishCommerceLoanLearnMoreInfo.getParagraphs();
            for (int i = 0; i < paragraphs.size(); i++) {
                CommerceLoanLearnMoreInfoParagraphView commerceLoanLearnMoreInfoParagraphView = new CommerceLoanLearnMoreInfoParagraphView(getContext());
                commerceLoanLearnMoreInfoParagraphView.setup((LearnMoreInfoParagraph) paragraphs.get(i), i);
                this.mLearnMoreInfoContainer.addView(commerceLoanLearnMoreInfoParagraphView);
            }
        }
    }
}
