package com.etsy.android.ui.search.v2.impressions;

import io.reactivex.v;
import java.util.HashMap;
import okhttp3.z;
import retrofit2.b.a;
import retrofit2.b.j;
import retrofit2.b.o;
import retrofit2.l;

/* compiled from: SearchImpressionsEndpoint.kt */
public interface f {
    @o(a = "/etsyapps/v3/public/shop-analytics/impression-log")
    v<l<Void>> a(@a z zVar, @j HashMap<String, String> hashMap);
}
