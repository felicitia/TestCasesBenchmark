package com.contextlogic.wish.activity.productreview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class ProductReviewSectionTitle extends LinearLayout {
    public ProductReviewSectionTitle(Context context) {
        super(context);
        init();
    }

    public ProductReviewSectionTitle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ProductReviewSectionTitle(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.product_review_section_header, this);
    }

    public void setup(String str) {
        ((ThemedTextView) findViewById(R.id.product_review_section_header_text)).setText(str);
    }
}
