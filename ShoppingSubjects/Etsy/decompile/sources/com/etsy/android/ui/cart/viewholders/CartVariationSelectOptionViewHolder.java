package com.etsy.android.ui.cart.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.OfferingOption;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class CartVariationSelectOptionViewHolder extends ViewHolder {
    /* access modifiers changed from: private */
    public a mListener;
    private TextView mText = ((TextView) this.itemView.findViewById(R.id.text));

    public interface a {
        void onVariationOptionClicked(@NonNull OfferingOption offeringOption);
    }

    public CartVariationSelectOptionViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, a aVar) {
        super(layoutInflater.inflate(R.layout.list_item_cart_variation, viewGroup, false));
        this.mListener = aVar;
    }

    public void bind(final OfferingOption offeringOption) {
        this.mText.setText(offeringOption.getDisplayValue().toString());
        this.mText.setEnabled(offeringOption.isEnabled());
        this.itemView.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (CartVariationSelectOptionViewHolder.this.mListener != null && offeringOption.isEnabled()) {
                    CartVariationSelectOptionViewHolder.this.mListener.onVariationOptionClicked(offeringOption);
                }
            }
        });
    }
}
