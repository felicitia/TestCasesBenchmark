package com.etsy.android.ui.cart.viewholders;

import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.PaymentMethod;
import com.etsy.android.lib.models.apiv3.cart.PaymentOptions;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.lib.util.o;
import com.etsy.android.ui.cart.a.a;
import java.util.List;
import kotlin.h;
import kotlin.jvm.a.b;

public class PaymentOptionsViewHolder extends BaseCartGroupItemViewHolder {
    private final a mClickHandler;
    private final o mRadioGroupDelegate = new o();
    private final LinearLayout mRadioGroupPaymentMethods;

    public PaymentOptionsViewHolder(ViewGroup viewGroup, a aVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_payment_options, viewGroup, false));
        this.mClickHandler = aVar;
        this.mRadioGroupPaymentMethods = (LinearLayout) findViewById(R.id.group_payment_methods);
    }

    public void bindCartGroupItem(CartGroupItem cartGroupItem) {
        Drawable drawable;
        PaymentOptions paymentOptions = (PaymentOptions) cartGroupItem.getData();
        this.mRadioGroupPaymentMethods.removeAllViews();
        List paymentMethods = paymentOptions.getPaymentMethods();
        for (int i = 0; i < paymentMethods.size(); i++) {
            PaymentMethod paymentMethod = (PaymentMethod) paymentMethods.get(i);
            View inflate = LayoutInflater.from(this.itemView.getContext()).inflate(R.layout.list_item_payment_option, this.mRadioGroupPaymentMethods, false);
            RadioButton radioButton = (RadioButton) inflate.findViewById(R.id.radio_button);
            TextView textView = (TextView) inflate.findViewById(R.id.text);
            if (paymentMethod.isPayPal()) {
                drawable = this.itemView.getResources().getDrawable(R.drawable.cc_paypal);
            } else if (paymentMethod.isCreditCard()) {
                drawable = this.itemView.getResources().getDrawable(R.drawable.cc_all);
            } else if (paymentMethod.isAndroidPay()) {
                drawable = this.itemView.getResources().getDrawable(R.drawable.cc_android_pay);
            } else if (paymentMethod.isIdeal()) {
                drawable = this.itemView.getResources().getDrawable(R.drawable.cc_ideal);
            } else if (paymentMethod.isSofort()) {
                drawable = this.itemView.getResources().getDrawable(R.drawable.sofort);
            } else if (paymentMethod.isKlarna()) {
                Drawable drawable2 = this.itemView.getResources().getDrawable(R.drawable.klarna);
                String string = this.itemView.getResources().getString(R.string.klarna_invoice_url);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.itemView.getResources().getString(R.string.invoice_terms));
                spannableStringBuilder.setSpan(new URLSpan(string), 0, spannableStringBuilder.length(), 0);
                textView.setText(spannableStringBuilder);
                textView.setVisibility(0);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                drawable = drawable2;
            } else {
                drawable = null;
            }
            if (drawable == null) {
                radioButton.setText(paymentMethod.getDisplayValue());
            } else {
                radioButton.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
            }
            if (paymentMethod.isCreditCard()) {
                radioButton.setContentDescription(this.itemView.getResources().getString(R.string.payment_method_label_all_credit_card));
            } else {
                radioButton.setContentDescription(paymentMethod.getDisplayValue());
            }
            radioButton.setChecked(paymentMethod.isSelected());
            radioButton.setEnabled(paymentMethod.isEnabled());
            radioButton.setAlpha(paymentMethod.isEnabled() ? 1.0f : 0.5f);
            radioButton.setTag(paymentMethod.getValue());
            this.mRadioGroupPaymentMethods.addView(inflate);
            this.mRadioGroupDelegate.a(radioButton);
        }
        if (this.mRadioGroupPaymentMethods.getChildCount() > 0) {
            this.mRadioGroupDelegate.a((b<? super CompoundButton, h>) new p<Object,h>(this, cartGroupItem));
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ h lambda$bindCartGroupItem$0$PaymentOptionsViewHolder(CartGroupItem cartGroupItem, CompoundButton compoundButton) {
        if (this.mClickHandler != null) {
            ServerDrivenAction action = cartGroupItem.getAction(ServerDrivenAction.TYPE_SET_PAYMENT_METHOD);
            if (!(action == null || compoundButton == null)) {
                action.addParam("payment_method", (String) compoundButton.getTag());
                this.mClickHandler.c(getRootView(), action);
            }
        }
        return h.a;
    }
}
