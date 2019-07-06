package com.salesforce.marketingcloud.analytics;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.salesforce.marketingcloud.j;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PiCart implements Parcelable {
    /* access modifiers changed from: private */
    public static final String a = j.a(PiCart.class);

    static class a {
        a() {
        }

        public void a(JSONObject jSONObject, String str, List<PiCartItem> list) {
            JSONArray jSONArray = new JSONArray();
            try {
                if (!list.isEmpty()) {
                    for (PiCartItem a : list) {
                        jSONArray.put(a.a());
                    }
                }
                jSONObject.put(str, jSONArray);
            } catch (JSONException e) {
                j.c(PiCart.a, e, "Failed to convert List<PiCartItem> into JSONArray for PiCart payload.", new Object[0]);
            }
        }
    }

    @NonNull
    public static PiCart create(@NonNull List<PiCartItem> list) {
        return new f(new ArrayList(list));
    }

    public abstract JSONObject a();

    @NonNull
    public abstract List<PiCartItem> cartItems();
}
