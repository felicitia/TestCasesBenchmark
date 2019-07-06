package com.salesforce.marketingcloud.messages.b;

import android.support.annotation.NonNull;
import com.salesforce.marketingcloud.messages.d;
import com.salesforce.marketingcloud.messages.e;
import java.util.List;
import org.json.JSONObject;

public abstract class b extends d {
    static b a(JSONObject jSONObject) {
        return c.b(jSONObject);
    }

    @NonNull
    public abstract List<e> c();
}
