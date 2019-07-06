package com.contextlogic.wish.dialog.clipboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.api.model.WishClipboardCouponPopupDialogSpec;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishTextViewSpec;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.ClipboardCouponPopupDialogClaimService.SuccessCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.Margin;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.DisplayUtil;
import java.util.List;

public class ClipboardCouponPopupDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    /* access modifiers changed from: private */
    public WishClipboardCouponPopupDialogSpec mDialogSpec;

    private static void populateText(List<WishTextViewSpec> list, LinearLayout linearLayout, int i) {
        for (WishTextViewSpec wishTextViewSpec : list) {
            if (wishTextViewSpec != null) {
                ThemedTextView themedTextView = new ThemedTextView(linearLayout.getContext());
                formatTextView(themedTextView, wishTextViewSpec, i);
                linearLayout.addView(themedTextView);
            }
        }
    }

    private static void formatTextView(TextView textView, WishTextViewSpec wishTextViewSpec, int i) {
        textView.setGravity(1);
        textView.setTextColor(i);
        textView.setPadding(0, 0, 0, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.eight_padding));
        textView.setTextSize(0, (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.text_size_body));
        textView.setLineSpacing((float) WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.clipboard_coupon_text_line_spacing_extra), 1.0f);
        WishTextViewSpec.applyTextViewSpec(textView, wishTextViewSpec);
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        View inflate = layoutInflater.inflate(R.layout.clipboard_coupon_popup_dialog_fragment, viewGroup, false);
        AutoReleasableImageView autoReleasableImageView = (AutoReleasableImageView) inflate.findViewById(R.id.clipboard_coupon_popup_dialog_fragment_x);
        FrameLayout frameLayout = (FrameLayout) inflate.findViewById(R.id.clipboard_coupon_popup_dialog_fragment_header_container);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.clipboard_coupon_popup_header_content_container);
        AutoReleasableImageView autoReleasableImageView2 = (AutoReleasableImageView) inflate.findViewById(R.id.clipboard_coupon_popup_dialog_fragment_header_background_image);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.clipboard_coupon_popup_dialog_fragment_header_text_container);
        LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.clipboard_coupon_popup_dialog_fragment_products);
        LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(R.id.clipboard_coupon_popup_dialog_fragment_body_text_container);
        ThemedButton themedButton = (ThemedButton) inflate.findViewById(R.id.clipboard_coupon_popup_dialog_fragment_body_action_button);
        LinearLayout linearLayout5 = (LinearLayout) inflate.findViewById(R.id.clipboard_coupon_popup_dialog_fragment_footer_text_container);
        if (this.mDialogSpec.shouldShowHeader()) {
            if (this.mDialogSpec.shouldShowXButton()) {
                autoReleasableImageView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        ClipboardCouponPopupDialogFragment.this.cancel();
                    }
                });
            } else {
                autoReleasableImageView.setVisibility(4);
            }
            if (this.mDialogSpec.getHeaderText() != null) {
                populateText(this.mDialogSpec.getHeaderText(), linearLayout2, getResources().getColor(R.color.white));
            }
            if (this.mDialogSpec.getProducts() == null || this.mDialogSpec.getProducts().size() <= 0) {
                linearLayout.measure(0, 0);
                autoReleasableImageView2.setLayoutParams(new LayoutParams(-1, linearLayout.getMeasuredHeight()));
            } else {
                if (this.mDialogSpec.getProducts().size() == 1) {
                    i = getResources().getDimensionPixelSize(R.dimen.clipboard_coupon_one_product_size);
                } else {
                    i = getResources().getDimensionPixelSize(R.dimen.clipboard_coupon_multiple_product_size);
                }
                for (WishProduct clipboardCouponPopupDialogFragmentProductTile : this.mDialogSpec.getProducts()) {
                    linearLayout3.addView(new ClipboardCouponPopupDialogFragmentProductTile(linearLayout3.getContext(), clipboardCouponPopupDialogFragmentProductTile, i));
                }
                linearLayout.measure(0, 0);
                autoReleasableImageView2.setLayoutParams(new LayoutParams(-1, linearLayout.getMeasuredHeight() - ((ClipboardCouponPopupDialogFragmentProductTile) linearLayout3.getChildAt(0)).getBackgroundPadding()));
            }
        } else {
            frameLayout.setVisibility(8);
            linearLayout4.setPadding(linearLayout4.getPaddingLeft(), WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.thirty_two_padding), linearLayout4.getPaddingRight(), linearLayout4.getPaddingBottom());
        }
        if (this.mDialogSpec.getBodyText() != null) {
            populateText(this.mDialogSpec.getBodyText(), linearLayout4, WishApplication.getInstance().getResources().getColor(R.color.text_primary));
        }
        themedButton.setText(this.mDialogSpec.getButtonText());
        themedButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ClipboardCouponPopupDialogFragment.this.onActionButtonClick();
            }
        });
        if (this.mDialogSpec.getFooterText() != null) {
            populateText(this.mDialogSpec.getFooterText(), linearLayout5, WishApplication.getInstance().getResources().getColor(R.color.main_primary));
            if (!this.mDialogSpec.getFooterDeeplink().equals("")) {
                linearLayout5.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        DeepLinkManager.processDeepLink(ClipboardCouponPopupDialogFragment.this.getBaseActivity(), new DeepLink(ClipboardCouponPopupDialogFragment.this.mDialogSpec.getFooterDeeplink()));
                    }
                });
            }
        } else {
            linearLayout5.setVisibility(8);
        }
        if (this.mDialogSpec.getDialogImpressionEventId() != -1) {
            WishAnalyticsLogger.trackEvent(this.mDialogSpec.getDialogImpressionEventId());
        }
        return inflate;
    }

    public Margin getDialogMargin() {
        Margin margin = new Margin(0, DisplayUtil.getStatusBarHeight(), 0, 0);
        return margin;
    }

    public int getDialogWidth() {
        int displayWidth = DisplayUtil.getDisplayWidth() - (getResources().getDimensionPixelSize(R.dimen.thirty_two_padding) * 2);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.bottom_dialog_fragment_max_width);
        return displayWidth > dimensionPixelSize ? dimensionPixelSize : displayWidth;
    }

    public void setSpecifications(WishClipboardCouponPopupDialogSpec wishClipboardCouponPopupDialogSpec) {
        this.mDialogSpec = wishClipboardCouponPopupDialogSpec;
    }

    public void onActionButtonClick() {
        showProgressSpinner();
        if (this.mDialogSpec.getActionButtonEventId() != -1) {
            WishAnalyticsLogger.trackEvent(this.mDialogSpec.getActionButtonEventId());
        }
        switch (this.mDialogSpec.getButtonActionType()) {
            case CLAIM_COUPON:
                withServiceFragment(new ServiceTask<BaseActivity, ServiceFragment>() {
                    public void performTask(final BaseActivity baseActivity, final ServiceFragment serviceFragment) {
                        serviceFragment.claimClipboardCouponCode(ClipboardCouponPopupDialogFragment.this.mDialogSpec.getCouponCode(), new SuccessCallback() {
                            public void onSuccessDoNothing() {
                                ClipboardCouponPopupDialogFragment.this.hideProgressSpinner();
                                ClipboardCouponPopupDialogFragment.this.cancel();
                            }

                            public void onSuccessShowDialog(WishClipboardCouponPopupDialogSpec wishClipboardCouponPopupDialogSpec) {
                                ClipboardCouponPopupDialogFragment.this.hideProgressSpinner();
                                serviceFragment.showClipboardCouponDialogFragment(wishClipboardCouponPopupDialogSpec);
                                ClipboardCouponPopupDialogFragment.this.cancel();
                            }

                            public void onSuccessResolveDeepLink(DeepLink deepLink) {
                                ClipboardCouponPopupDialogFragment.this.hideProgressSpinner();
                                ClipboardCouponPopupDialogFragment.this.cancel();
                                DeepLinkManager.processDeepLink(baseActivity, deepLink);
                            }
                        }, new DefaultFailureCallback() {
                            public void onFailure(String str) {
                                ClipboardCouponPopupDialogFragment.this.hideProgressSpinner();
                                baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                                ClipboardCouponPopupDialogFragment.this.cancel();
                            }
                        });
                    }
                });
                return;
            case DEEPLINK:
                withActivity(new ActivityTask<A>() {
                    public void performTask(A a) {
                        DeepLinkManager.processDeepLink(a, new DeepLink(ClipboardCouponPopupDialogFragment.this.mDialogSpec.getButtonDeeplink()));
                    }
                });
                return;
            default:
                hideProgressSpinner();
                cancel();
                return;
        }
    }
}
