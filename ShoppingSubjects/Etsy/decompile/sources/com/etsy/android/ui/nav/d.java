package com.etsy.android.ui.nav;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.logger.g;
import com.etsy.android.lib.logger.legacy.a;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.PaymentMethod;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayLineItemContract;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;
import java.util.HashMap;
import java.util.Locale;

@Deprecated
/* compiled from: LegacyEtsyNavTracker */
public class d extends a {
    public void b(@NonNull b bVar, String str) {
        bVar.a("searched", new LegacyEtsyNavTracker$1(this, str));
    }

    public void c(@NonNull b bVar, String str) {
        bVar.a("searched", new LegacyEtsyNavTracker$2(this, str));
    }

    public void a(String str) {
        this.a.d("country_tapped", str);
    }

    public void g() {
        this.a.a("listing_image_zoom");
    }

    public void h() {
        Locale locale = Locale.getDefault();
        HashMap hashMap = new HashMap();
        hashMap.put("language", locale.getLanguage());
        hashMap.put("region", locale.getCountry());
        hashMap.put("currency", CurrencyUtil.c());
        this.a.a("view_locale_preferences", hashMap);
    }

    public void i() {
        this.a.a("settings_legal");
    }

    public void j() {
        this.a.a("terms_of_use");
    }

    public void k() {
        this.a.a("privacy_policy");
    }

    public void l() {
        this.a.a("electronic_communications_policy");
    }

    public void m() {
        this.a.a("your_purchases");
    }

    public void n() {
        this.a.a("listing_variation_options");
    }

    public void a(AndroidPayDataContract androidPayDataContract) {
        PaymentMethod lastPaymentMethod = androidPayDataContract.getLastPaymentMethod();
        String type = lastPaymentMethod != null ? lastPaymentMethod.getType() : "";
        StringBuilder sb = new StringBuilder();
        for (AndroidPayLineItemContract listingId : androidPayDataContract.getLineItems()) {
            sb.append(listingId.getListingId().getId());
            sb.append(",");
        }
        LegacyEtsyNavTracker$3 legacyEtsyNavTracker$3 = new LegacyEtsyNavTracker$3(this, androidPayDataContract, sb, type);
        if (androidPayDataContract instanceof Cart) {
            Cart cart = (Cart) androidPayDataContract;
            legacyEtsyNavTracker$3.put("coupon_code", cart.getCouponCode());
            legacyEtsyNavTracker$3.put("subtotal", cart.getSubtotal());
            legacyEtsyNavTracker$3.put("ship_total", cart.getShippingCost());
            legacyEtsyNavTracker$3.put(ResponseConstants.DISCOUNT_AMOUNT, cart.getDiscountAmount());
        }
        this.a.a("cart_checkout", (HashMap<String, Object>) legacyEtsyNavTracker$3);
    }

    public void a(boolean z, EtsyId etsyId) {
        String str = (!etsyId.hasId() || v.a().m().equals(etsyId)) ? z ? "your_circles_following" : "your_circles_followers" : z ? "people_circles_following" : "people_circles_followers";
        this.a.a(str);
    }

    public void a(boolean z) {
        this.a.a(z ? "your_favorites" : "profile_favorites");
    }

    public void a(int i, boolean z) {
        String str;
        String str2 = "";
        switch (i) {
            case 0:
                if (!z) {
                    str = "profile_favorite_listings";
                    break;
                } else {
                    str = "your_favorite_items";
                    break;
                }
            case 1:
                if (!z) {
                    str = "profile_favorite_shops";
                    break;
                } else {
                    str = "your_favorite_shops";
                    break;
                }
            default:
                this.a.a(str2);
        }
        str2 = str;
        this.a.a(str2);
    }

    public void o() {
        this.a.a("list_open");
    }

    public void p() {
        this.a.a("list_edit_overlay_open");
    }

    public void q() {
        this.a.a("list_create_open");
    }

    public void r() {
        this.a.c("your_account_settings", v.a().e() ? "logged_in_user" : "logged_out_user");
    }

    public void s() {
        this.a.a("login_nag");
    }

    public void c(SignInFlow signInFlow) {
        a("login_2_factor", signInFlow);
    }

    public void d(SignInFlow signInFlow) {
        a("forgot_password_dialog", signInFlow);
    }

    public void a(Bundle bundle) {
        if (bundle.containsKey("convo_id")) {
            c();
        } else {
            b();
        }
    }

    public void a(EtsyId etsyId) {
        this.a.a("local_event_view", (HashMap<String, Object>) new LegacyEtsyNavTracker$4<String,Object>(this, etsyId));
    }

    public void a(@NonNull b bVar, EtsyId etsyId, Bundle bundle) {
        if (etsyId != null) {
            bVar.a("listing", new LegacyEtsyNavTracker$5(this, etsyId));
            HashMap c = c(bundle);
            c.put("listing_id", etsyId.getId());
            this.a.a("view_listing", c);
            g.c(etsyId);
        }
    }

    public void a(@NonNull b bVar, @Nullable EtsyId etsyId, @Nullable EtsyId etsyId2, Bundle bundle) {
        if (etsyId != null) {
            bVar.a(ResponseConstants.SHOP, new LegacyEtsyNavTracker$6(this, etsyId));
            HashMap c = c(bundle);
            c.put("shop_shop_id", etsyId.getId());
            if (etsyId2 != null && etsyId2.hasId()) {
                c.put("user_id", etsyId2.getId());
            }
            com.etsy.android.lib.logger.legacy.b.a().a("shop_home", c);
        }
    }

    public void a(EtsyId etsyId, Bundle bundle) {
        if (etsyId != null) {
            HashMap c = c(bundle);
            c.put("shop_shop_id", etsyId.getId());
            com.etsy.android.lib.logger.legacy.b.a().a("shop_reviews", c);
        }
    }

    public void b(@NonNull b bVar, EtsyId etsyId, Bundle bundle) {
        if (etsyId != null) {
            bVar.a("receipt", new LegacyEtsyNavTracker$7(this, etsyId));
            HashMap c = c(bundle);
            c.put(ResponseConstants.RECEIPT_ID, etsyId.getId());
            this.a.a("view_receipt", c);
        }
    }

    public void b(Bundle bundle) {
        com.etsy.android.lib.logger.legacy.b.a().a("search", c(bundle));
    }

    private HashMap<String, Object> c(Bundle bundle) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (!(bundle == null || bundle.getBundle("referrer_bundle") == null)) {
            Bundle bundle2 = bundle.getBundle("referrer_bundle");
            for (String str : bundle2.keySet()) {
                if (!TextUtils.isEmpty(bundle2.getString(str))) {
                    hashMap.put(str, bundle2.getString(str));
                }
            }
        }
        return hashMap;
    }
}
