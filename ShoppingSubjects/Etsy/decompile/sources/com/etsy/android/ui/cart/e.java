package com.etsy.android.ui.cart;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.cardview.clickhandlers.m;
import com.etsy.android.ui.cart.a.a;
import com.etsy.android.ui.cart.a.d;
import com.etsy.android.ui.cart.a.f;
import com.etsy.android.ui.cart.viewholders.AddPromotionViewHolder;
import com.etsy.android.ui.cart.viewholders.CartListingGiftCardViewHolder;
import com.etsy.android.ui.cart.viewholders.CartListingUnavailableViewHolder;
import com.etsy.android.ui.cart.viewholders.CartListingViewHolder;
import com.etsy.android.ui.cart.viewholders.CartReceiptViewHolder;
import com.etsy.android.ui.cart.viewholders.CartThankYouGroupViewHolder;
import com.etsy.android.ui.cart.viewholders.CheckoutSectionAndroidPayViewHolder;
import com.etsy.android.ui.cart.viewholders.CheckoutSectionViewHolder;
import com.etsy.android.ui.cart.viewholders.GiftOptionsViewHolder;
import com.etsy.android.ui.cart.viewholders.HorizontalCartGroupViewHolder;
import com.etsy.android.ui.cart.viewholders.MessageBannerViewHolder;
import com.etsy.android.ui.cart.viewholders.MessageBubbleViewHolder;
import com.etsy.android.ui.cart.viewholders.MessageToSellerViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentAddCouponViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentAppliedCouponViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentApplyGiftCardViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentHeaderViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentOptionsDividerViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentOptionsViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentTotalsLineItemViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentTotalsNoteViewHolder;
import com.etsy.android.ui.cart.viewholders.PaymentUpdateShippingCountryViewHolder;
import com.etsy.android.ui.cart.viewholders.PromotionViewHolder;
import com.etsy.android.ui.cart.viewholders.ShippingDetailsViewHolder;
import com.etsy.android.ui.cart.viewholders.ShopCartHeaderViewHolder;
import com.etsy.android.ui.cart.viewholders.VerticalCartGroupViewHolder;
import com.etsy.android.vespa.c;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import com.etsy.android.vespa.viewholders.HTMLTextViewHolder;

/* compiled from: MultiShopCartViewHolderFactory */
public class e extends c<FragmentActivity> {
    protected d a;

    public e(@NonNull FragmentActivity fragmentActivity, @NonNull b bVar, @NonNull d dVar) {
        super(fragmentActivity, bVar);
        this.a = dVar;
        this.e.put(R.id.view_type_multishop_shop_header, new m(fragmentActivity, this.f));
        com.etsy.android.ui.cart.a.b bVar2 = new com.etsy.android.ui.cart.a.b(this.a, fragmentActivity, this.f);
        this.e.put(R.id.view_type_multishop_cart_listing, bVar2);
        this.e.put(R.id.view_type_multishop_cart_listing_unavailable, bVar2);
        this.e.put(R.id.view_type_multishop_cart_listing_gift_card, bVar2);
        this.e.put(R.id.view_type_multishop_checkout_section, new d(this.a, fragmentActivity, this.f));
        this.e.put(R.id.view_type_multishop_shipping, new f(this.a, fragmentActivity, this.f));
        this.e.put(R.id.view_type_gift_options, new com.etsy.android.ui.cart.a.e(this.a, fragmentActivity, this.f));
        a aVar = new a(this.a, fragmentActivity, this.f);
        this.e.put(R.id.view_type_multishop_message_to_seller, aVar);
        this.e.put(R.id.view_type_multishop_payment_add_coupon, aVar);
        this.e.put(R.id.view_type_multishop_add_promotion, aVar);
        this.e.put(R.id.view_type_multishop_payment_applied_coupon, aVar);
        this.e.put(R.id.view_type_multishop_payment_apply_gift_card, aVar);
        this.e.put(R.id.view_type_multishop_payment_update_shipping_country, aVar);
        this.e.put(R.id.view_type_multishop_payment_options, aVar);
        this.e.put(R.id.view_type_multishop_message_bubble, aVar);
        this.e.put(R.id.view_type_multishop_message_banner, aVar);
        this.e.put(R.id.view_type_promotion, aVar);
        this.e.put(R.id.view_type_multishop_cart_receipt, new com.etsy.android.ui.cart.a.c(fragmentActivity, this.f));
    }

    public BaseViewHolder a(ViewGroup viewGroup, int i) {
        BaseViewHolder baseViewHolder;
        if (i != R.id.view_type_discount_total_line_item) {
            if (i == R.id.view_type_html_text) {
                baseViewHolder = new HTMLTextViewHolder(viewGroup, R.layout.list_item_msco_html_text, true);
            } else if (i == R.id.view_type_promotion) {
                return new PromotionViewHolder(viewGroup, (a) this.e.get(i));
            } else {
                switch (i) {
                    case R.id.view_type_gift_options /*2131363410*/:
                        return new GiftOptionsViewHolder(viewGroup, (com.etsy.android.ui.cart.a.e) this.e.get(i));
                    case R.id.view_type_grand_total_line_item /*2131363411*/:
                        break;
                    default:
                        switch (i) {
                            case R.id.view_type_multishop_add_promotion /*2131363433*/:
                                return new AddPromotionViewHolder(viewGroup, (a) this.e.get(i));
                            case R.id.view_type_multishop_cart_group /*2131363434*/:
                                if (!l.d((View) viewGroup)) {
                                    baseViewHolder = new VerticalCartGroupViewHolder(viewGroup, this);
                                    break;
                                } else {
                                    baseViewHolder = new HorizontalCartGroupViewHolder(viewGroup, this);
                                    break;
                                }
                            case R.id.view_type_multishop_cart_listing /*2131363435*/:
                                return new CartListingViewHolder(viewGroup, this.d, (com.etsy.android.ui.cart.a.b) this.e.get(i));
                            case R.id.view_type_multishop_cart_listing_gift_card /*2131363436*/:
                                return new CartListingGiftCardViewHolder(viewGroup, this.d, (com.etsy.android.ui.cart.a.b) this.e.get(i));
                            case R.id.view_type_multishop_cart_listing_unavailable /*2131363437*/:
                                return new CartListingUnavailableViewHolder(viewGroup, this.d, (com.etsy.android.ui.cart.a.b) this.e.get(i));
                            case R.id.view_type_multishop_cart_receipt /*2131363438*/:
                                return new CartReceiptViewHolder(viewGroup, this.d, (com.etsy.android.ui.cart.a.c) b(i));
                            case R.id.view_type_multishop_cart_thank_you_group /*2131363439*/:
                                baseViewHolder = new CartThankYouGroupViewHolder(viewGroup, this);
                                break;
                            case R.id.view_type_multishop_checkout_section /*2131363440*/:
                                return new CheckoutSectionViewHolder(viewGroup, (d) this.e.get(i));
                            case R.id.view_type_multishop_checkout_section_android_pay /*2131363441*/:
                                return new CheckoutSectionAndroidPayViewHolder(viewGroup, (d) this.e.get(i));
                            case R.id.view_type_multishop_message_banner /*2131363442*/:
                                return new MessageBannerViewHolder(viewGroup, this.d, (a) this.e.get(i));
                            case R.id.view_type_multishop_message_bubble /*2131363443*/:
                                return new MessageBubbleViewHolder(viewGroup, (a) this.e.get(i));
                            case R.id.view_type_multishop_message_to_seller /*2131363444*/:
                                return new MessageToSellerViewHolder(viewGroup, (a) this.e.get(i));
                            case R.id.view_type_multishop_payment_add_coupon /*2131363445*/:
                                return new PaymentAddCouponViewHolder(viewGroup, (a) this.e.get(i));
                            case R.id.view_type_multishop_payment_applied_coupon /*2131363446*/:
                                return new PaymentAppliedCouponViewHolder(viewGroup, (a) this.e.get(i));
                            case R.id.view_type_multishop_payment_apply_gift_card /*2131363447*/:
                                return new PaymentApplyGiftCardViewHolder(viewGroup, (a) this.e.get(i));
                            case R.id.view_type_multishop_payment_header /*2131363448*/:
                                baseViewHolder = new PaymentHeaderViewHolder(viewGroup);
                                break;
                            case R.id.view_type_multishop_payment_options /*2131363449*/:
                                return new PaymentOptionsViewHolder(viewGroup, (a) this.e.get(i));
                            case R.id.view_type_multishop_payment_options_divider /*2131363450*/:
                                baseViewHolder = new PaymentOptionsDividerViewHolder(viewGroup);
                                break;
                            case R.id.view_type_multishop_payment_update_shipping_country /*2131363451*/:
                                return new PaymentUpdateShippingCountryViewHolder(viewGroup, (a) this.e.get(i));
                            case R.id.view_type_multishop_shipping /*2131363452*/:
                                return new ShippingDetailsViewHolder(viewGroup, (f) this.e.get(i));
                            case R.id.view_type_multishop_shop_header /*2131363453*/:
                                return new ShopCartHeaderViewHolder(viewGroup, this.d, (m) this.e.get(i));
                            default:
                                switch (i) {
                                    case R.id.view_type_total_line_item /*2131363517*/:
                                        break;
                                    case R.id.view_type_totals_note /*2131363518*/:
                                        baseViewHolder = new PaymentTotalsNoteViewHolder(viewGroup);
                                        break;
                                    default:
                                        return null;
                                }
                        }
                }
            }
            return baseViewHolder;
        }
        return new PaymentTotalsLineItemViewHolder(viewGroup, (com.etsy.android.vespa.b) this.e.get(i), i);
    }
}
