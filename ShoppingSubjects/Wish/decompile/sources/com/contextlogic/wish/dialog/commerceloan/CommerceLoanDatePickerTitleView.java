package com.contextlogic.wish.dialog.commerceloan;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class CommerceLoanDatePickerTitleView extends LinearLayout {
    private ThemedTextView mSubTitle;
    private ThemedTextView mTitle;

    public CommerceLoanDatePickerTitleView(Context context) {
        this(context, null);
    }

    public CommerceLoanDatePickerTitleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) WishApplication.getInstance().getSystemService("layout_inflater")).inflate(R.layout.commerce_loan_date_picker_title, this);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.commerce_loan_date_picker_title_main);
        this.mSubTitle = (ThemedTextView) inflate.findViewById(R.id.commerce_loan_date_picker_title_sub);
    }

    public void setNumDays(int i) {
        this.mTitle.setText(WishApplication.getInstance().getString(R.string.choose_payment_date));
        this.mSubTitle.setText(WishApplication.getInstance().getString(R.string.commerce_loan_date_constraint, new Object[]{Integer.toString(i)}));
    }
}
