package com.contextlogic.wish.activity.cart.billing.commerceloanform;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class CommerceLoanOptionView extends FrameLayout {
    private AutoReleasableImageView mChevron;
    private boolean mEnabled;
    private AutoReleasableImageView mIcon;
    private ThemedTextView mSelectText;
    private ThemedTextView mSubtitle;
    private ThemedTextView mTitle;

    public CommerceLoanOptionView(Context context) {
        this(context, null);
    }

    public CommerceLoanOptionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        ((LayoutInflater) WishApplication.getInstance().getSystemService("layout_inflater")).inflate(R.layout.cart_fragment_payment_form_commerce_loan_option_view, this, true);
        this.mIcon = (AutoReleasableImageView) findViewById(R.id.cart_fragment_payment_form_commerce_loan_option_icon);
        this.mChevron = (AutoReleasableImageView) findViewById(R.id.cart_fragment_payment_form_commerce_loan_option_chevron);
        this.mTitle = (ThemedTextView) findViewById(R.id.cart_fragment_payment_form_commerce_loan_option_title);
        this.mSubtitle = (ThemedTextView) findViewById(R.id.cart_fragment_payment_form_commerce_loan_option_subtitle);
        this.mSelectText = (ThemedTextView) findViewById(R.id.cart_fragment_payment_form_commerce_loan_option_select_text);
        this.mEnabled = false;
    }

    public void setTitle(String str) {
        this.mTitle.setText(str);
    }

    public void setSubtitle(String str) {
        this.mSubtitle.setVisibility(0);
        this.mSubtitle.setText(str);
    }

    public void setSelectText(String str) {
        this.mEnabled = true;
        this.mChevron.setVisibility(0);
        this.mSelectText.setText(str);
    }

    public void setIcon(Drawable drawable) {
        this.mIcon.setImageDrawable(drawable);
    }

    public void setDisabled() {
        this.mEnabled = false;
        this.mIcon.setAlpha(0.2f);
        this.mChevron.setAlpha(0.2f);
        this.mTitle.setAlpha(0.2f);
        this.mSubtitle.setAlpha(0.2f);
        this.mSelectText.setAlpha(0.2f);
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }
}
