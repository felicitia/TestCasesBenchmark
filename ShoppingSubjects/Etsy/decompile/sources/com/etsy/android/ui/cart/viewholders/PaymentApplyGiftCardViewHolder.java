package com.etsy.android.ui.cart.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.PaymentApplyGiftCard;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.ui.cart.a.a;
import com.etsy.android.uikit.util.TrackingOnCheckedChangeListener;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public class PaymentApplyGiftCardViewHolder extends BaseCartGroupItemViewHolder {
    /* access modifiers changed from: private */
    public final CheckBox mCheckboxGiftCardApplied = ((CheckBox) findViewById(R.id.checkbox_gift_card_applied));
    /* access modifiers changed from: private */
    public final a mClickHandler;
    private final View mIcon = findViewById(R.id.icon_giftcard);
    private final TextView mText = ((TextView) findViewById(R.id.txt_text));

    public PaymentApplyGiftCardViewHolder(ViewGroup viewGroup, a aVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_payment_apply_gift_card, viewGroup, false));
        this.mClickHandler = aVar;
    }

    public void bindCartGroupItem(final CartGroupItem cartGroupItem) {
        this.mCheckboxGiftCardApplied.setOnCheckedChangeListener(null);
        this.mIcon.setOnClickListener(null);
        this.mText.setOnClickListener(null);
        PaymentApplyGiftCard paymentApplyGiftCard = (PaymentApplyGiftCard) cartGroupItem.getData();
        if (paymentApplyGiftCard != null) {
            TextView textView = this.mText;
            StringBuilder sb = new StringBuilder();
            sb.append(paymentApplyGiftCard.getTitle());
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(paymentApplyGiftCard.getMoneyString());
            textView.setText(sb.toString());
            this.mCheckboxGiftCardApplied.setChecked(paymentApplyGiftCard.isApplied());
        }
        AnonymousClass1 r0 = new TrackingOnClickListener() {
            public void onViewClick(View view) {
                PaymentApplyGiftCardViewHolder.this.mCheckboxGiftCardApplied.setChecked(!PaymentApplyGiftCardViewHolder.this.mCheckboxGiftCardApplied.isChecked());
            }
        };
        this.mIcon.setOnClickListener(r0);
        this.mText.setOnClickListener(r0);
        this.mCheckboxGiftCardApplied.setOnCheckedChangeListener(new TrackingOnCheckedChangeListener() {
            public void onViewCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (PaymentApplyGiftCardViewHolder.this.mClickHandler != null) {
                    ServerDrivenAction action = cartGroupItem.getAction(ServerDrivenAction.TYPE_SHOULD_USE_GIFTCARD);
                    if (action != null) {
                        action.addParam("should_use_gift_card", String.valueOf(z));
                        PaymentApplyGiftCardViewHolder.this.mClickHandler.c(PaymentApplyGiftCardViewHolder.this.getRootView(), action);
                    }
                }
            }
        });
    }
}
