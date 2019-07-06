package com.salesforce.marketingcloud.analytics;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.salesforce.marketingcloud.j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PiOrder implements Parcelable {
    /* access modifiers changed from: private */
    public static final String a = j.a(PiOrder.class);

    static class a {
        a() {
        }

        public void a(JSONObject jSONObject, String str, PiCart piCart) {
            JSONArray jSONArray = new JSONArray();
            try {
                for (PiCartItem a : piCart.cartItems()) {
                    jSONArray.put(a.a());
                }
                jSONObject.put(str, jSONArray);
            } catch (JSONException e) {
                j.c(PiOrder.a, e, "Failed to add our PiCart to the Order.", new Object[0]);
            }
        }
    }

    @NonNull
    public static PiOrder create(@NonNull PiCart piCart, @NonNull String str, double d, double d2) {
        h hVar = new h(piCart, str, d, d2);
        return hVar;
    }

    public abstract JSONObject a();

    @NonNull
    public abstract PiCart cart();

    public abstract double discount();

    @NonNull
    public abstract String orderNumber();

    public abstract double shipping();
}
