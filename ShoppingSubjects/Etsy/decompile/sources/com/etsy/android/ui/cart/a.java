package com.etsy.android.ui.cart;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract;
import java.util.HashMap;
import java.util.List;

/* compiled from: CartEventTracker */
public class a {
    public static void a(@Nullable b bVar, @NonNull List<String> list) {
        HashMap hashMap = new HashMap();
        if (!list.isEmpty()) {
            StringBuilder sb = new StringBuilder("[");
            for (String str : list) {
                if (sb.length() > 1) {
                    sb.append(",");
                }
                sb.append("[");
                sb.append(str);
                sb.append("]");
            }
            sb.append("]");
            hashMap.put(AnalyticsLogAttribute.GROUPED_CART_IDS, sb.toString());
        }
        if (bVar != null) {
            bVar.a("cart_view", hashMap);
        }
    }

    public static void a(@NonNull b bVar, AndroidPayDataContract androidPayDataContract) {
        if (androidPayDataContract != null) {
            String type = androidPayDataContract.getLastPaymentMethod() != null ? androidPayDataContract.getLastPaymentMethod().getType() : null;
            HashMap trackingParameters = ((i) androidPayDataContract).getTrackingParameters();
            if (trackingParameters == null) {
                trackingParameters = new HashMap();
            }
            trackingParameters.put(AnalyticsLogAttribute.PAYMENT_METHOD, type);
            bVar.a("enter_checkout", trackingParameters);
        }
    }

    public static void a(@Nullable b bVar, @Nullable String str, boolean z) {
        if (bVar != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(AnalyticsLogAttribute.IS_PAYPAL, Boolean.valueOf(z));
            if (!TextUtils.isEmpty(str)) {
                hashMap.put(AnalyticsLogAttribute.CART_ID, str);
            }
            bVar.a("canceled_checkout_webview", hashMap);
        }
    }

    public static void a(AndroidPayDataContract androidPayDataContract) {
        if (androidPayDataContract != null) {
            EtsyApplication.get().getAnalyticsTracker().a("load_full_wallet_start", ((i) androidPayDataContract).getTrackingParameters());
        }
    }

    public static void a(@Nullable b bVar, AndroidPayDataContract androidPayDataContract, int i) {
        if (bVar != null && androidPayDataContract != null) {
            bVar.a("load_full_wallet_complete", new CartEventTracker$1(((i) androidPayDataContract).getTrackingParameters(), i));
        }
    }

    public static void b(AndroidPayDataContract androidPayDataContract) {
        if (androidPayDataContract != null) {
            EtsyApplication.get().getAnalyticsTracker().a("load_masked_wallet_start", ((i) androidPayDataContract).getTrackingParameters());
        }
    }

    public static void b(@Nullable b bVar, AndroidPayDataContract androidPayDataContract, int i) {
        if (bVar != null && androidPayDataContract != null) {
            bVar.a("load_masked_wallet_complete", new CartEventTracker$2(((i) androidPayDataContract).getTrackingParameters(), i));
        }
    }
}
