package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedView.AdjustableSquareView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class DealDashSquareView extends AdjustableSquareView {
    private View mButton;
    private View mButtonContainer;
    private View mSpinner;
    private ThemedTextView mTextView;

    public int getLayoutId() {
        return R.layout.deal_dash_square_view;
    }

    public DealDashSquareView(Context context) {
        super(context);
    }

    public void setup() {
        this.mSpinner = this.mView.findViewById(R.id.deal_dash_spinner);
        this.mButtonContainer = this.mView.findViewById(R.id.deal_dash_spin_button_container);
        this.mButton = this.mView.findViewById(R.id.deal_dash_spin_button);
        this.mTextView = (ThemedTextView) this.mView.findViewById(R.id.deal_dash_spin_text_view);
    }

    public void adjust(int i) {
        LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i;
        setLayoutParams(layoutParams);
        LayoutParams layoutParams2 = this.mSpinner.getLayoutParams();
        LayoutParams layoutParams3 = this.mButtonContainer.getLayoutParams();
        LayoutParams layoutParams4 = this.mButton.getLayoutParams();
        LayoutParams layoutParams5 = this.mTextView.getLayoutParams();
        double d = ((double) i) / ((double) layoutParams2.width);
        layoutParams2.width = (int) (((double) layoutParams2.width) * d);
        layoutParams2.height = (int) (((double) layoutParams2.height) * d);
        this.mSpinner.setLayoutParams(layoutParams2);
        layoutParams3.width = (int) (((double) layoutParams3.width) * d);
        layoutParams3.height = (int) (((double) layoutParams3.height) * d);
        this.mButtonContainer.setLayoutParams(layoutParams3);
        layoutParams4.width = (int) (((double) layoutParams4.width) * d);
        layoutParams4.height = (int) (((double) layoutParams4.height) * d);
        this.mButton.setLayoutParams(layoutParams4);
        layoutParams5.width = (int) (((double) layoutParams5.width) * d);
        layoutParams5.height = (int) (((double) layoutParams5.height) * d);
        this.mTextView.setLayoutParams(layoutParams5);
    }
}
