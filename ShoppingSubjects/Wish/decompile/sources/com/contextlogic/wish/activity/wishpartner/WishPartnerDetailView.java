package com.contextlogic.wish.activity.wishpartner;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.wishpartner.info.WishPartnerInfoActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishPartnerProductDetailInfo;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;

public class WishPartnerDetailView extends LinearLayout {
    private TextView mActionButton;
    private TextView mBodyText;
    private TextView mLegalText;
    private TextView mTitleText;

    public WishPartnerDetailView(Context context) {
        super(context);
        init();
    }

    public WishPartnerDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public WishPartnerDetailView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.wish_partner_detail_view, this, true);
        this.mTitleText = (TextView) findViewById(R.id.wish_partner_detail_title);
        this.mBodyText = (TextView) findViewById(R.id.wish_partner_detail_body);
        this.mActionButton = (TextView) findViewById(R.id.wish_partner_detail_action_button);
        this.mLegalText = (TextView) findViewById(R.id.wish_partner_detail_legal_text);
    }

    public void setup(final WishProduct wishProduct, final BaseFragment baseFragment) {
        WishPartnerProductDetailInfo wishPartnerInfo = wishProduct.getWishPartnerInfo();
        if (wishPartnerInfo != null) {
            this.mTitleText.setText(wishPartnerInfo.getTitle());
            this.mBodyText.setText(wishPartnerInfo.getBody());
            this.mActionButton.setText(wishPartnerInfo.getActionText());
            this.mActionButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    baseFragment.withActivity(new ActivityTask<BaseActivity>() {
                        public void performTask(BaseActivity baseActivity) {
                            String str;
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_GENERATE_CODE_PRODUCT_DETAILS, wishProduct.getProductId());
                            Intent intent = new Intent();
                            intent.setClass(baseActivity, WishPartnerInfoActivity.class);
                            intent.putExtra("ExtraProductId", wishProduct.getProductId());
                            intent.putExtra("ExtraProductImage", wishProduct.getImage().getBaseUrlString());
                            if (wishProduct.getDefaultVariationPriceBeforeDiscount().getValue() == 0.0d) {
                                str = WishPartnerDetailView.this.getResources().getString(R.string.free);
                            } else {
                                str = wishProduct.getDefaultVariationPriceBeforeDiscount().toFormattedString();
                            }
                            intent.putExtra("ExtraProductPrice", str);
                            baseActivity.startActivity(intent);
                        }
                    });
                }
            });
            if (wishPartnerInfo.getLegalText() != null) {
                this.mLegalText.setText(processParagraph(wishPartnerInfo.getLegalText(), wishPartnerInfo.getLegalHighlightText(), wishPartnerInfo.getLegalTextLink(), baseFragment));
                this.mLegalText.setMovementMethod(LinkMovementMethod.getInstance());
                this.mLegalText.setLinkTextColor(getResources().getColor(R.color.main_primary));
                this.mLegalText.setVisibility(0);
            } else {
                this.mLegalText.setVisibility(8);
            }
        }
    }

    private SpannableString processParagraph(String str, String str2, final String str3, final BaseFragment baseFragment) {
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return new SpannableString(str);
        }
        int length = str2.length() + indexOf;
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_TERMS);
                DeepLinkManager.processDeepLink(baseFragment.getBaseActivity(), new DeepLink(str3));
            }
        }, indexOf, length, 33);
        return spannableString;
    }
}
