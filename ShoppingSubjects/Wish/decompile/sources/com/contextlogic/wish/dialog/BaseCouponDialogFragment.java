package com.contextlogic.wish.dialog;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.util.ClipboardUtil;

public abstract class BaseCouponDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    private TextView mCopyCouponCodeButton;
    private TextView mCouponCode;
    private TextView mCouponDesc1;
    private TextView mCouponDesc2;
    private TextView mCouponDiscountText;
    private TextView mCouponExpiryDateText;
    private TextView mDiscountTitleText;
    private TextView mMaxDiscountText;
    private TextView mStartShoppingButton;
    private AutoReleasableImageView mTicketImage;
    private AutoReleasableImageView mTicketShadow;

    public abstract void logCopiedToClipboardEvent();

    public abstract void logStartShoppingButtonOnClickEvent();

    public abstract void setupTicketImage();

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.login_action_coupon_dialog, viewGroup);
        this.mCouponDiscountText = (TextView) inflate.findViewById(R.id.coupon_dialog_discount_text);
        this.mCouponExpiryDateText = (TextView) inflate.findViewById(R.id.coupon_dialog_discount_deadline_text);
        this.mDiscountTitleText = (TextView) inflate.findViewById(R.id.coupon_dialog_discount_title_text);
        this.mCouponCode = (TextView) inflate.findViewById(R.id.coupon_dialog_coupon_code);
        this.mCopyCouponCodeButton = (TextView) inflate.findViewById(R.id.coupon_dialog_copy_code_button);
        this.mStartShoppingButton = (TextView) inflate.findViewById(R.id.coupon_dialog_action_button);
        this.mCouponDesc1 = (TextView) inflate.findViewById(R.id.coupon_dialog_desc_1);
        this.mCouponDesc2 = (TextView) inflate.findViewById(R.id.coupon_dialog_desc_2);
        this.mMaxDiscountText = (TextView) inflate.findViewById(R.id.coupon_dialog_max_discount_text);
        this.mTicketImage = (AutoReleasableImageView) inflate.findViewById(R.id.coupon_dialog_ticket_image);
        this.mTicketShadow = (AutoReleasableImageView) inflate.findViewById(R.id.coupon_dialog_ticket_shadow);
        return inflate;
    }

    public void setup(String str, String str2, String str3, final String str4, String str5, String str6, String str7) {
        this.mCouponDiscountText.setText(str);
        this.mCouponExpiryDateText.setText(str2);
        this.mDiscountTitleText.setText(str3);
        this.mCouponCode.setText(str4.replace("", " ").trim());
        this.mCouponDesc1.setText(str5);
        this.mCouponDesc2.setText(str6);
        if (!TextUtils.isEmpty(str7)) {
            this.mMaxDiscountText.setText(getString(R.string.copy_coupon_max_discount, str7));
        } else {
            this.mMaxDiscountText.setVisibility(8);
        }
        this.mCopyCouponCodeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseCouponDialogFragment.this.logCopiedToClipboardEvent();
                ClipboardUtil.copyToClipboard(str4, str4);
                ToastManager.getInstance().showToast(BaseCouponDialogFragment.this.getBaseActivity(), BaseCouponDialogFragment.this.getString(R.string.copied_to_clipboard));
            }
        });
        this.mStartShoppingButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseCouponDialogFragment.this.logStartShoppingButtonOnClickEvent();
                BaseCouponDialogFragment.this.cancel();
            }
        });
        setupTicketImage();
    }

    public void setTicketImageDrawable(Drawable drawable) {
        this.mTicketImage.setImageDrawable(drawable);
    }

    public void setTicketImageShadow(Drawable drawable) {
        this.mTicketShadow.setImageDrawable(drawable);
    }
}
