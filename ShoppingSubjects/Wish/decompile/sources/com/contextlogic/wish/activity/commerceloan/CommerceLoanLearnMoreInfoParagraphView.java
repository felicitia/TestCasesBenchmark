package com.contextlogic.wish.activity.commerceloan;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishCommerceLoanLearnMoreInfo.LearnMoreInfoParagraph;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class CommerceLoanLearnMoreInfoParagraphView extends LinearLayout {
    private ThemedTextView mLearnMoreInfoDescriptionText;
    private ThemedTextView mLearnMoreInfoTitleText;

    public CommerceLoanLearnMoreInfoParagraphView(Context context) {
        this(context, null);
    }

    public CommerceLoanLearnMoreInfoParagraphView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        ((LayoutInflater) WishApplication.getInstance().getSystemService("layout_inflater")).inflate(R.layout.commerce_loan_learn_more_info_item_view, this, true);
        this.mLearnMoreInfoTitleText = (ThemedTextView) findViewById(R.id.commerce_loan_learn_more_fragment_info_title);
        this.mLearnMoreInfoDescriptionText = (ThemedTextView) findViewById(R.id.commerce_loan_learn_more_fragment_info_description);
    }

    public void setup(LearnMoreInfoParagraph learnMoreInfoParagraph, int i) {
        int dimensionPixelSize = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.commerce_loan_learn_more_title_padding);
        if (i == 0) {
            dimensionPixelSize = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
        }
        LayoutParams layoutParams = (LayoutParams) this.mLearnMoreInfoTitleText.getLayoutParams();
        layoutParams.setMargins(0, dimensionPixelSize, 0, 0);
        this.mLearnMoreInfoTitleText.setLayoutParams(layoutParams);
        this.mLearnMoreInfoTitleText.setText(learnMoreInfoParagraph.getTitle());
        this.mLearnMoreInfoDescriptionText.setText(learnMoreInfoParagraph.getDescription());
    }
}
