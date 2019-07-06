package com.etsy.android.uikit.view.shop.policies;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.models.apiv3.PrivacyFlag;
import com.etsy.android.lib.models.apiv3.StructuredShopPrivacy;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.shop.policies.StructuredShopPoliciesView.a;

public class StructuredShopPrivacyView extends StructuredShopPoliciesView {
    private static final int FIELD_OTHER_VISIBILITY_LIMIT = 250;
    /* access modifiers changed from: private */
    public View mBtnExpandPrivacyOther;
    private StructuredShopPrivacy mPrivacyPolicy;
    private TextView mTxtPrivacyInfo;
    private TextView mTxtPrivacyItems;

    public StructuredShopPrivacyView(Context context) {
        super(context);
    }

    public StructuredShopPrivacyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StructuredShopPrivacyView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public StructuredShopPrivacyView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, LinearLayout linearLayout) {
        inflate(context, k.view_structured_shop_privacy, linearLayout);
        this.mBtnExpandPrivacyOther = findViewById(i.btn_privacy_other_expand);
        this.mTxtPrivacyInfo = (TextView) findViewById(i.txt_privacy_info);
        this.mTxtPrivacyItems = (TextView) findViewById(i.txt_privacy_items);
    }

    public void setStructuredShopPrivacy(@NonNull StructuredShopPrivacy structuredShopPrivacy, @Nullable a aVar, b bVar) {
        this.mPrivacyPolicy = structuredShopPrivacy;
        if (!structuredShopPrivacy.hasAnyEnabledFlags()) {
            if (isSellerMode()) {
                this.mTxtPrivacyInfo.setText(o.structured_privacy_add_privacy_info);
            } else {
                this.mTxtPrivacyInfo.setText(Html.fromHtml(getResources().getString(o.structured_privacy_no_info_alternate_text)));
                linkifyContactShopUrlSpan(this.mTxtPrivacyInfo, aVar);
            }
            this.mTxtPrivacyItems.setVisibility(8);
        } else if (bVar.c().c(com.etsy.android.lib.config.b.cd)) {
            setPrivacyContentGDPR(false);
        } else {
            setPrivacyContent(false);
            this.mTxtPrivacyItems.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void setPrivacyContent(boolean z) {
        this.mTxtPrivacyInfo.setText(getResources().getString(o.structured_privacy_message));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        PrivacyFlag communication = this.mPrivacyPolicy.getCommunication();
        if (communication.isEnabled()) {
            spannableStringBuilder.append(Html.fromHtml("&#8226; ")).append(communication.getLabel()).append("\n");
        }
        PrivacyFlag fulfillment = this.mPrivacyPolicy.getFulfillment();
        if (fulfillment.isEnabled()) {
            spannableStringBuilder.append(Html.fromHtml("&#8226; ")).append(fulfillment.getLabel()).append("\n");
        }
        PrivacyFlag legal = this.mPrivacyPolicy.getLegal();
        if (legal.isEnabled()) {
            spannableStringBuilder.append(Html.fromHtml("&#8226; ")).append(legal.getLabel()).append("\n");
        }
        PrivacyFlag other = this.mPrivacyPolicy.getOther();
        if (other.isEnabled()) {
            String label = other.getLabel();
            if (other.getLabel().length() <= 250 || z) {
                this.mBtnExpandPrivacyOther.setVisibility(8);
            } else {
                this.mBtnExpandPrivacyOther.setVisibility(0);
                this.mBtnExpandPrivacyOther.setOnClickListener(new TrackingOnClickListener() {
                    public void onViewClick(View view) {
                        StructuredShopPrivacyView.this.mBtnExpandPrivacyOther.setVisibility(8);
                        StructuredShopPrivacyView.this.setPrivacyContent(true);
                    }
                });
                StringBuilder sb = new StringBuilder();
                sb.append(label.substring(0, 250).trim());
                sb.append("…");
                label = sb.toString();
            }
            spannableStringBuilder.append(Html.fromHtml("&#8226; ")).append(label).append("\n");
        }
        this.mTxtPrivacyItems.setText(spannableStringBuilder.toString().trim());
    }

    /* access modifiers changed from: private */
    public void setPrivacyContentGDPR(boolean z) {
        if (this.mPrivacyPolicy.getFlags().getCommunication().isEnabled() || this.mPrivacyPolicy.getFlags().getFulfillment().isEnabled() || this.mPrivacyPolicy.getFlags().getLegal().isEnabled()) {
            setPrivacyContent(z);
            return;
        }
        this.mTxtPrivacyItems.setVisibility(8);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        PrivacyFlag other = this.mPrivacyPolicy.getOther();
        if (other.isEnabled()) {
            String label = other.getLabel();
            if (other.getLabel().length() <= 250 || z) {
                this.mBtnExpandPrivacyOther.setVisibility(8);
            } else {
                this.mBtnExpandPrivacyOther.setVisibility(0);
                this.mBtnExpandPrivacyOther.setOnClickListener(new TrackingOnClickListener() {
                    public void onViewClick(View view) {
                        StructuredShopPrivacyView.this.mBtnExpandPrivacyOther.setVisibility(8);
                        StructuredShopPrivacyView.this.setPrivacyContentGDPR(true);
                    }
                });
                StringBuilder sb = new StringBuilder();
                sb.append(label.substring(0, 250).trim());
                sb.append("…");
                label = sb.toString();
            }
            spannableStringBuilder.append(label).append("\n");
        }
        this.mTxtPrivacyInfo.setText(spannableStringBuilder.toString().trim());
    }
}
