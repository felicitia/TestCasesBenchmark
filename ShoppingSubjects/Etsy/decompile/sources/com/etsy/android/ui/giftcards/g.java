package com.etsy.android.ui.giftcards;

import android.text.TextUtils;
import com.etsy.android.lib.core.http.request.EtsyApiV2Request;
import com.etsy.android.lib.core.http.request.EtsyApiV2Request.a;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.ResponseConstants;

/* compiled from: GiftCardRequestFactory */
public class g {
    public EtsyApiV2Request<EmptyResult> a(int i, String str, String str2, String str3, int i2, String str4) {
        String str5;
        if (v.a().e()) {
            str5 = "/users/__SELF__/carts/giftcards";
        } else {
            str5 = String.format("/guests/%s/carts/giftcards", new Object[]{com.etsy.android.lib.config.g.a().e()});
        }
        boolean z = !TextUtils.isEmpty(str);
        a a = a.a(EmptyResult.class, str5);
        ((a) ((a) ((a) ((a) ((a) ((a) a.a(1)).a("recipient_name", str2)).a("sender_name", str3)).a(ResponseConstants.AMOUNT, Integer.toString(i))).a("message", str4)).a("is_email", Boolean.toString(z))).a("design_id", Integer.toString(i2));
        if (z) {
            a.a("recipient_email", str);
        }
        return a.d();
    }
}
