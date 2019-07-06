package com.etsy.android.ui.cart.viewholders;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.cart.CartErrorResolution;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.CartMessageBubble;
import com.etsy.android.lib.models.apiv3.cart.PaymentUpdateShippingCountry;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.lib.util.CountryUtil;
import com.etsy.android.ui.cart.a.a;
import com.etsy.android.ui.cart.view.CartMessageBubbleView;
import com.etsy.android.uikit.EtsySpinnerArrayAdapterWithClickListener;
import com.etsy.android.uikit.view.ZeroSpinner;
import java.util.ArrayList;

public class MessageBubbleViewHolder extends BaseMessageViewHolder<CartGroupItem> {
    /* access modifiers changed from: private */
    public final a mClickHandler;
    private final CartMessageBubbleView mMessageBubble = ((CartMessageBubbleView) this.itemView);
    private final int mPaddingLeftIndented = this.mMessageBubble.getPaddingLeft();
    private final int mPaddingLeftNotIndented = this.itemView.getContext().getTheme().obtainStyledAttributes(R.style.MultiShopCartGroupItem, new int[]{16842966}).getDimensionPixelSize(0, this.mPaddingLeftIndented);

    public MessageBubbleViewHolder(ViewGroup viewGroup, a aVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_message_bubble, viewGroup, false));
        this.mClickHandler = aVar;
    }

    public void bind(CartGroupItem cartGroupItem) {
        CartMessageBubble cartMessageBubble = (CartMessageBubble) cartGroupItem.getData();
        this.mMessageBubble.setPadding(cartMessageBubble.isIndented() ? this.mPaddingLeftIndented : this.mPaddingLeftNotIndented, this.mMessageBubble.getPaddingTop(), this.mMessageBubble.getPaddingRight(), this.mMessageBubble.getPaddingBottom());
        this.mMessageBubble.bindMessageBubble(cartMessageBubble);
        if (cartMessageBubble.hasErrorDropdown()) {
            CartErrorResolution cartErrorDropDown = cartMessageBubble.getCartErrorDropDown();
            switch (cartErrorDropDown.getType()) {
                case 1:
                    bindVariation(cartGroupItem.getAction(ServerDrivenAction.TYPE_RESOLVE_CUSTOMIZATION), cartErrorDropDown);
                    return;
                case 2:
                    bindShipping(cartGroupItem.getAction(ServerDrivenAction.TYPE_UPDATE_SHIPPING_DESTINATION), cartErrorDropDown);
                    return;
                case 3:
                    bindQuantity(cartGroupItem.getAction(ServerDrivenAction.TYPE_UPDATE_QUANTITY), cartErrorDropDown);
                    return;
                default:
                    return;
            }
        } else {
            this.mMessageBubble.getErrorSpinner().setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void bindQuantity(final ServerDrivenAction serverDrivenAction, CartErrorResolution cartErrorResolution) {
        ZeroSpinner errorSpinner = this.mMessageBubble.getErrorSpinner();
        errorSpinner.setVisibility(0);
        errorSpinner.setEnabled(true);
        errorSpinner.setOnTouchListener(null);
        errorSpinner.setPrompt(cartErrorResolution.getPrompt());
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i <= cartErrorResolution.getQuantity(); i++) {
            arrayList.add(Integer.valueOf(i));
        }
        new EtsySpinnerArrayAdapterWithClickListener(this.itemView.getContext(), errorSpinner, arrayList).setOnItemClickListener(new EtsySpinnerArrayAdapterWithClickListener.a<Integer>() {
            public boolean b(@NonNull Integer num) {
                return true;
            }

            public void a(Integer num) {
                serverDrivenAction.addParam("quantity", num.toString());
                MessageBubbleViewHolder.this.mClickHandler.c(MessageBubbleViewHolder.this.itemView, serverDrivenAction);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void bindShipping(final ServerDrivenAction serverDrivenAction, CartErrorResolution cartErrorResolution) {
        ZeroSpinner errorSpinner = this.mMessageBubble.getErrorSpinner();
        errorSpinner.setVisibility(0);
        errorSpinner.setEnabled(true);
        errorSpinner.setOnTouchListener(null);
        errorSpinner.setPrompt(cartErrorResolution.getPrompt());
        PaymentUpdateShippingCountry shippingCountries = cartErrorResolution.getShippingCountries();
        ArrayList arrayList = new ArrayList();
        CountryUtil.a(arrayList, shippingCountries.getPreferredCountries(), shippingCountries.getAllCountryIds(), shippingCountries.getDestinationCountryId());
        new EtsySpinnerArrayAdapterWithClickListener(this.itemView.getContext(), errorSpinner, arrayList).setOnItemClickListener(new EtsySpinnerArrayAdapterWithClickListener.a<Country>() {
            public boolean b(Country country) {
                return true;
            }

            public void a(Country country) {
                if (MessageBubbleViewHolder.this.mClickHandler != null && country != null && country.getCountryId() != -1 && serverDrivenAction != null) {
                    serverDrivenAction.addParam(ResponseConstants.COUNTRY_ID, String.valueOf(country.getCountryId()));
                    MessageBubbleViewHolder.this.mClickHandler.c(MessageBubbleViewHolder.this.itemView, serverDrivenAction);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void bindVariation(final ServerDrivenAction serverDrivenAction, CartErrorResolution cartErrorResolution) {
        ZeroSpinner errorSpinner = this.mMessageBubble.getErrorSpinner();
        errorSpinner.setVisibility(0);
        errorSpinner.setPrompt(cartErrorResolution.getPrompt());
        ArrayList arrayList = new ArrayList();
        arrayList.add(cartErrorResolution.getPrompt());
        new EtsySpinnerArrayAdapterWithClickListener(this.itemView.getContext(), errorSpinner, arrayList);
        errorSpinner.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MessageBubbleViewHolder.this.mClickHandler == null || motionEvent.getAction() != 1 || serverDrivenAction == null) {
                    return true;
                }
                MessageBubbleViewHolder.this.mClickHandler.a(MessageBubbleViewHolder.this.itemView, serverDrivenAction);
                return true;
            }
        });
    }
}
