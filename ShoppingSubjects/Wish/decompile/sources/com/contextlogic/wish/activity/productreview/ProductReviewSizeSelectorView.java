package com.contextlogic.wish.activity.productreview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import com.contextlogic.wish.R;

public class ProductReviewSizeSelectorView extends RadioGroup {
    /* access modifiers changed from: private */
    public SizeChoice mSelection;
    private LinearLayout mSizeSelectorGroup;

    public enum SizeChoice {
        DEFAULT(0),
        JUST_RIGHT(1),
        TOO_SMALL(2),
        TOO_LARGE(3);
        
        private int mChoice;

        private SizeChoice(int i) {
            this.mChoice = i;
        }

        public int getChoice() {
            return this.mChoice;
        }
    }

    public interface SizeSelectionListener {
        void onSelection(SizeChoice sizeChoice);
    }

    public ProductReviewSizeSelectorView(Context context) {
        super(context);
        init();
    }

    public ProductReviewSizeSelectorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.mSelection = SizeChoice.DEFAULT;
        this.mSizeSelectorGroup = (LinearLayout) ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.product_review_size_selector_view, this).findViewById(R.id.size_selector_radio_group);
    }

    public void setup(final SizeSelectionListener sizeSelectionListener) {
        for (int i = 0; i < this.mSizeSelectorGroup.getChildCount(); i++) {
            ((Button) this.mSizeSelectorGroup.getChildAt(i)).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.size_just_right /*2131298516*/:
                            ProductReviewSizeSelectorView.this.mSelection = SizeChoice.JUST_RIGHT;
                            break;
                        case R.id.size_too_large /*2131298518*/:
                            ProductReviewSizeSelectorView.this.mSelection = SizeChoice.TOO_LARGE;
                            break;
                        case R.id.size_too_small /*2131298519*/:
                            ProductReviewSizeSelectorView.this.mSelection = SizeChoice.TOO_SMALL;
                            break;
                        default:
                            ProductReviewSizeSelectorView.this.mSelection = SizeChoice.DEFAULT;
                            break;
                    }
                    ProductReviewSizeSelectorView.this.applySelection(view.getId());
                    if (sizeSelectionListener != null) {
                        sizeSelectionListener.onSelection(ProductReviewSizeSelectorView.this.mSelection);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void applySelection(int i) {
        for (int i2 = 0; i2 < this.mSizeSelectorGroup.getChildCount(); i2++) {
            Button button = (Button) this.mSizeSelectorGroup.getChildAt(i2);
            if (button.getId() == i) {
                button.setSelected(true);
                button.setTypeface(null, 1);
            } else {
                button.setSelected(false);
                button.setTypeface(null, 0);
            }
        }
    }

    public SizeChoice getSelection() {
        return this.mSelection;
    }
}
