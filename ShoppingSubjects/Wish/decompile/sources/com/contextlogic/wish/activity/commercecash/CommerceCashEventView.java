package com.contextlogic.wish.activity.commercecash;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishCommerceCashEvent;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.Recyclable;

public class CommerceCashEventView extends LinearLayout implements ImageRestorable, Recyclable {
    private ThemedTextView mAmountText;
    private View mBottomRowSeparator;
    private AutoReleasableImageView mChevron;
    private ConstraintLayout mContainer;
    private ThemedTextView mDateText;
    private NetworkImageView mImageView;
    private AutoReleasableImageView mImageViewDrawable;
    private ThemedTextView mMainText;
    private ThemedTextView mSubTextView;
    private AutoReleasableImageView mTagIcon;
    private String mTransactionId;

    public CommerceCashEventView(Context context) {
        this(context, null, 0);
    }

    public CommerceCashEventView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setOrientation(1);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.commerce_cash_fragment_event, this, true);
        this.mContainer = (ConstraintLayout) inflate.findViewById(R.id.commerce_cash_event_main_container);
        this.mImageViewDrawable = (AutoReleasableImageView) inflate.findViewById(R.id.commerce_cash_event_image_drawable);
        this.mImageView = (NetworkImageView) inflate.findViewById(R.id.commerce_cash_event_image);
        this.mMainText = (ThemedTextView) inflate.findViewById(R.id.commerce_cash_event_main_text);
        this.mDateText = (ThemedTextView) inflate.findViewById(R.id.commerce_cash_event_date_text);
        this.mBottomRowSeparator = inflate.findViewById(R.id.commerce_cash_bottom_row_separator);
        this.mSubTextView = (ThemedTextView) inflate.findViewById(R.id.commerce_cash_event_sub_text);
        this.mAmountText = (ThemedTextView) inflate.findViewById(R.id.commerce_cash_event_amount);
        this.mChevron = (AutoReleasableImageView) inflate.findViewById(R.id.commerce_cash_event_transaction_chevron);
        this.mTagIcon = (AutoReleasableImageView) inflate.findViewById(R.id.commerce_cash_event_referral_tag_icon);
    }

    public void setupEvent(WishCommerceCashEvent wishCommerceCashEvent) {
        this.mMainText.setText(wishCommerceCashEvent.getMainText());
        this.mDateText.setText(wishCommerceCashEvent.getDateText());
        this.mSubTextView.setText(wishCommerceCashEvent.getSubText());
        this.mAmountText.setText(wishCommerceCashEvent.getAmountText());
        this.mAmountText.setTextColor(getResources().getColor(wishCommerceCashEvent.isNegativeAmount() ? R.color.black : R.color.green));
        int i = 0;
        int i2 = TextUtils.isEmpty(this.mSubTextView.getText()) ? 8 : 0;
        this.mSubTextView.setVisibility(i2);
        this.mBottomRowSeparator.setVisibility(i2);
        WishImage image = wishCommerceCashEvent.getImage();
        boolean z = wishCommerceCashEvent.getType() == 8;
        this.mTagIcon.setVisibility(z ? 0 : 8);
        if (image != null) {
            this.mImageView.setImage(image);
            this.mImageView.setBackground(getResources().getDrawable(R.drawable.commerce_cash_logo_background));
            this.mImageViewDrawable.setVisibility(8);
            this.mImageView.setVisibility(0);
        } else if (z) {
            this.mImageViewDrawable.setImageDrawable(getResources().getDrawable(R.drawable.referral_icon));
            this.mImageViewDrawable.setBackground(getResources().getDrawable(R.drawable.commerce_cash_logo_background));
            this.mImageViewDrawable.setVisibility(0);
            this.mImageView.setVisibility(8);
        } else {
            this.mImageViewDrawable.setImageDrawable(getResources().getDrawable(R.drawable.dollar_icon));
            this.mImageViewDrawable.setBackground(getResources().getDrawable(R.drawable.commerce_cash_logo_background));
            this.mImageViewDrawable.setVisibility(0);
            this.mImageView.setVisibility(8);
        }
        this.mTransactionId = wishCommerceCashEvent.getTransactionId();
        AutoReleasableImageView autoReleasableImageView = this.mChevron;
        if (this.mTransactionId == null) {
            i = 8;
        }
        autoReleasableImageView.setVisibility(i);
    }

    public String getTransactionId() {
        return this.mTransactionId;
    }

    public void releaseImages() {
        if (this.mImageView != null) {
            this.mImageView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mImageView != null) {
            this.mImageView.restoreImages();
        }
    }

    public void recycle() {
        releaseImages();
        this.mImageView.setImage(null);
    }
}
