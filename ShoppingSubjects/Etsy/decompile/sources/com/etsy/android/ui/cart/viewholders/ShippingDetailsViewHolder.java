package com.etsy.android.ui.cart.viewholders;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.messaging.c;
import com.etsy.android.lib.models.apiv3.FormattedMoney;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.Promotion;
import com.etsy.android.lib.models.apiv3.cart.ShippingDetails;
import com.etsy.android.lib.models.apiv3.cart.ShippingOption;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.ui.cart.a.f;
import com.etsy.android.ui.cart.view.PromotionView;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.List;

public class ShippingDetailsViewHolder extends BaseCartGroupItemViewHolder {
    private final ArrayAdapter<ShippingOption> mAdapter;
    /* access modifiers changed from: private */
    public final f mClickHandler;
    private final TextView mEstimatedDeliveryLabel;
    private final View mLayoutShippingOption;
    private final PromotionView mPromotionView;
    private final TextView mShippingOptionLabel;
    private final Spinner mShippingOptions;
    private final TextView mTxtFreeShipping;
    private final TextView mTxtProcessingTime;
    private final boolean showEstimatedDeliveryDate = com.etsy.android.lib.config.a.a().d().c(b.cy);

    private class a extends ArrayAdapter<ShippingOption> {
        private final boolean b;

        a(Context context, @NonNull int i, boolean z) {
            super(context, i);
            this.b = z;
        }

        public View getDropDownView(int i, @Nullable View view, @NonNull ViewGroup viewGroup) {
            int i2 = 0;
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shipping_options_spinner_item, viewGroup, false);
            }
            TextView textView = (TextView) view.findViewById(R.id.secondary_text);
            ShippingOption shippingOption = (ShippingOption) getItem(i);
            ((TextView) view.findViewById(R.id.primary_text)).setText(shippingOption.getTitle());
            textView.setText(shippingOption.getEstimatedDeliveryDate());
            if (!this.b) {
                i2 = 8;
            }
            textView.setVisibility(i2);
            return view;
        }
    }

    public ShippingDetailsViewHolder(ViewGroup viewGroup, f fVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_shipping_details, viewGroup, false));
        this.mClickHandler = fVar;
        this.mLayoutShippingOption = findViewById(R.id.layout_shipping_option);
        this.mShippingOptions = (Spinner) findViewById(R.id.spinner_shipping_options);
        this.mShippingOptionLabel = (TextView) findViewById(R.id.txt_shipping_option_label);
        this.mAdapter = new a(this.itemView.getContext(), R.layout.spinner_list_item, this.showEstimatedDeliveryDate);
        this.mAdapter.setDropDownViewResource(17367050);
        this.mShippingOptions.setAdapter(this.mAdapter);
        this.mTxtProcessingTime = (TextView) findViewById(R.id.txt_processing_time);
        this.mTxtFreeShipping = (TextView) findViewById(R.id.txt_free_shipping);
        this.mPromotionView = (PromotionView) findViewById(R.id.promotion);
        this.mEstimatedDeliveryLabel = (TextView) findViewById(R.id.txt_estimated_delivery);
    }

    public void bindCartGroupItem(final CartGroupItem cartGroupItem) {
        final ShippingDetails shippingDetails = (ShippingDetails) cartGroupItem.getData();
        String processingTimeText = shippingDetails.getProcessingTimeText();
        if (this.showEstimatedDeliveryDate) {
            if (!TextUtils.isEmpty(shippingDetails.getEstimatedDeliveryDateSecondaryText())) {
                processingTimeText = shippingDetails.getEstimatedDeliveryDateSecondaryText();
            } else {
                this.mTxtProcessingTime.setVisibility(8);
            }
        }
        this.mTxtProcessingTime.setText(processingTimeText);
        if (!TextUtils.isEmpty(shippingDetails.getEstimatedDeliveryDatePrimaryText()) && this.showEstimatedDeliveryDate) {
            this.mEstimatedDeliveryLabel.setVisibility(0);
            this.mEstimatedDeliveryLabel.setText(Html.fromHtml(shippingDetails.getEstimatedDeliveryDatePrimaryText()));
            EtsyLinkify.a(this.mEstimatedDeliveryLabel, true, (OnClickListener) new q(this));
        }
        FormattedMoney freeShippingText = shippingDetails.getFreeShippingText();
        if (!com.etsy.android.lib.config.a.a().d().c(b.bC) || freeShippingText == null) {
            this.mTxtFreeShipping.setVisibility(8);
        } else {
            Spanned fromHtml = Html.fromHtml(freeShippingText.toString());
            this.mTxtFreeShipping.setText(fromHtml);
            addCustomFreeShippingShopLinks(fromHtml);
            this.mTxtFreeShipping.setVisibility(0);
        }
        final List shippingOptions = shippingDetails.getShippingOptions();
        this.mAdapter.clear();
        Promotion promotion = shippingDetails.getPromotion();
        this.mPromotionView.setVisibility(promotion == null ? 8 : 0);
        if (promotion != null) {
            AnonymousClass1 r6 = null;
            if (cartGroupItem.getAction(ServerDrivenAction.TYPE_DELETE_COUPON) != null) {
                r6 = new TrackingOnClickListener() {
                    public void onViewClick(View view) {
                        if (ShippingDetailsViewHolder.this.mClickHandler != null) {
                            ServerDrivenAction action = cartGroupItem.getAction(ServerDrivenAction.TYPE_DELETE_COUPON);
                            if (action != null) {
                                ShippingDetailsViewHolder.this.mClickHandler.c(ShippingDetailsViewHolder.this.getRootView(), action);
                            }
                        }
                    }
                };
            }
            this.mPromotionView.bind(promotion, r6);
        }
        if (shippingOptions.size() == 0) {
            this.mLayoutShippingOption.setVisibility(8);
            return;
        }
        this.mLayoutShippingOption.setVisibility(0);
        if (shippingOptions.size() == 1) {
            this.mShippingOptions.setVisibility(8);
            this.mShippingOptionLabel.setText(((ShippingOption) shippingOptions.get(0)).toString());
            return;
        }
        this.mShippingOptionLabel.setText(R.string.shipping);
        this.mShippingOptions.setVisibility(0);
        this.mAdapter.addAll(shippingOptions);
        int i = 0;
        while (true) {
            if (i >= this.mAdapter.getCount()) {
                break;
            } else if (((ShippingOption) this.mAdapter.getItem(i)).getOptionId().equals(shippingDetails.getSelectedOptionId())) {
                this.mShippingOptions.setSelection(i, false);
                break;
            } else {
                i++;
            }
        }
        this.mShippingOptions.setOnItemSelectedListener(new OnItemSelectedListener() {
            private String e = shippingDetails.getSelectedOptionId();

            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                String optionId = ((ShippingOption) shippingOptions.get(i)).getOptionId();
                if (optionId != null && !optionId.equals(this.e)) {
                    if (!(ShippingDetailsViewHolder.this.mClickHandler == null || adapterView.getItemAtPosition(i) == null)) {
                        ServerDrivenAction action = cartGroupItem.getAction(ServerDrivenAction.TYPE_SHIPPING_OPTION);
                        if (action != null) {
                            action.addParam("shipping_option_tuple", optionId);
                            ShippingDetailsViewHolder.this.mClickHandler.c(ShippingDetailsViewHolder.this.getRootView(), action);
                        }
                    }
                    this.e = optionId;
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$bindCartGroupItem$0$ShippingDetailsViewHolder(View view) {
        this.mClickHandler.a();
    }

    private void addCustomFreeShippingShopLinks(Spanned spanned) {
        URLSpan[] uRLSpanArr = (URLSpan[]) spanned.getSpans(0, spanned.length(), URLSpan.class);
        if (uRLSpanArr.length > 0) {
            final c a2 = c.a(Uri.parse(uRLSpanArr[0].getURL()));
            EtsyLinkify.a(this.mTxtFreeShipping, false, false, (OnClickListener) new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    if (a2 != null && !TextUtils.isEmpty(a2.d())) {
                        ShippingDetailsViewHolder.this.mClickHandler.a(a2.d());
                    }
                }
            });
            this.mTxtFreeShipping.setLinkTextColor(this.mTxtFreeShipping.getResources().getColor(R.color.sk_text_gray_darker));
        }
    }
}
