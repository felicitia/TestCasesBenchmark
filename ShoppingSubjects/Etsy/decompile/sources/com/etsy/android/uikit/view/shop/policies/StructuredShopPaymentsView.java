package com.etsy.android.uikit.view.shop.policies;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.models.apiv3.StructuredShopPayments;
import com.etsy.android.lib.util.PaymentMethod;
import com.etsy.android.uikit.view.PaymentMethodsView;
import com.etsy.android.uikit.view.shop.policies.StructuredShopPoliciesView.a;

public class StructuredShopPaymentsView extends StructuredShopPoliciesView {
    private PaymentMethodsView mPaymentMethodsView;
    private TextView mTxtOtherOptionsInfo;
    private TextView mTxtSecureOptionsInfo;
    private TextView mTxtSubheadOtherOptions;
    private TextView mTxtSubheadPaymentMethods;
    private TextView mTxtSubheadSecureOptions;

    public StructuredShopPaymentsView(Context context) {
        super(context);
    }

    public StructuredShopPaymentsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StructuredShopPaymentsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public StructuredShopPaymentsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, LinearLayout linearLayout) {
        inflate(context, k.view_structured_shop_payments, linearLayout);
        this.mTxtSubheadSecureOptions = (TextView) findViewById(i.txt_subhead_secure_options);
        this.mTxtSecureOptionsInfo = (TextView) findViewById(i.txt_secure_options_info);
        this.mPaymentMethodsView = (PaymentMethodsView) findViewById(i.payment_methods);
        this.mTxtSubheadPaymentMethods = (TextView) findViewById(i.txt_subhead_payment_methods);
        this.mTxtSubheadOtherOptions = (TextView) findViewById(i.txt_subhead_other_options);
        this.mTxtOtherOptionsInfo = (TextView) findViewById(i.txt_other_options_info);
    }

    public void setStructuredShopPayments(@NonNull StructuredShopPayments structuredShopPayments, @Nullable a aVar) {
        Resources resources = getResources();
        boolean z = true;
        boolean z2 = !structuredShopPayments.getProtectedPaymentMethods().isEmpty();
        if (z2) {
            this.mTxtSubheadSecureOptions.setVisibility(0);
            this.mPaymentMethodsView.setVisibility(0);
            this.mTxtSecureOptionsInfo.setVisibility(0);
            this.mTxtSubheadOtherOptions.setVisibility(0);
            this.mTxtSubheadPaymentMethods.setVisibility(8);
            this.mPaymentMethodsView.setCreditCardsVisible(structuredShopPayments.acceptsDirectCheckout() || structuredShopPayments.acceptsPayPal());
            this.mPaymentMethodsView.setPayPalVisible(structuredShopPayments.acceptsDirectCheckout() || structuredShopPayments.acceptsPayPal());
            this.mPaymentMethodsView.setGiftCardsVisible(structuredShopPayments.acceptsDirectCheckout());
        } else {
            this.mTxtSubheadSecureOptions.setVisibility(8);
            this.mPaymentMethodsView.setVisibility(8);
            this.mTxtSecureOptionsInfo.setVisibility(8);
            this.mTxtSubheadOtherOptions.setVisibility(8);
            this.mTxtSubheadPaymentMethods.setVisibility(0);
        }
        boolean z3 = !structuredShopPayments.getManualPaymentMethods().isEmpty();
        if (z3) {
            this.mTxtOtherOptionsInfo.setVisibility(0);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(Html.fromHtml(resources.getString(o.structured_payment_other_options_info)));
            for (String paymentMethod : structuredShopPayments.getManualPaymentMethods()) {
                spannableStringBuilder.append("\n\n").append(Html.fromHtml("&#8226; ")).append(resources.getString(PaymentMethod.getPaymentMethod(paymentMethod).getDisplayLabel()));
            }
            this.mTxtOtherOptionsInfo.setText(spannableStringBuilder);
            linkifyContactShopUrlSpan(this.mTxtOtherOptionsInfo, aVar);
        } else {
            this.mTxtSubheadOtherOptions.setVisibility(8);
            this.mTxtOtherOptionsInfo.setVisibility(8);
        }
        if (!z2 || !z3) {
            z = false;
        }
        setContentCollapsible(z);
    }
}
