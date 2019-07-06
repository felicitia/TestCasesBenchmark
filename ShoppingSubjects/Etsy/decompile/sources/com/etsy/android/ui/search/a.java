package com.etsy.android.ui.search;

import android.support.v4.util.ArrayMap;
import com.etsy.android.lib.core.i;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.ShopsRequest;

/* compiled from: SearchShopsJob */
public class a extends i<Shop> {
    private final String a;
    private final int c;
    private final int d;
    private final boolean e;

    public a(String str, int i, int i2, boolean z) {
        this.a = str;
        this.c = i;
        this.d = i2;
        this.e = z;
    }

    /* access modifiers changed from: protected */
    public EtsyRequest<Shop> a() {
        ShopsRequest findAllShops = ShopsRequest.findAllShops();
        ArrayMap arrayMap = new ArrayMap();
        if (this.a != null) {
            arrayMap.put(ResponseConstants.SHOP_NAME, this.a);
        }
        arrayMap.put("limit", String.valueOf(this.c));
        arrayMap.put("offset", String.valueOf(this.d));
        if (!this.e) {
            StringBuilder sb = new StringBuilder();
            sb.append("User(user_id)/Profile(image_url_75x75,city)/Country(name),");
            sb.append(com.etsy.android.ui.util.a.c(6));
            sb.append(",");
            sb.append(com.etsy.android.ui.util.a.b(6));
            arrayMap.put("includes", sb.toString());
            arrayMap.put("fields", "shop_id,shop_name,total_rating_count,average_rating,icon_url_fullxfull");
        }
        findAllShops.addParams(arrayMap);
        return findAllShops;
    }
}
