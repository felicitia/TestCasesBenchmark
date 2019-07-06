package com.etsy.android.ui.search.v2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.core.i;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.m;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.SearchSimplifiedQueries;
import com.etsy.android.lib.models.apiv3.SearchWithAds;
import java.util.List;

/* compiled from: SearchRequests */
public class e {
    public static final a a = new a(false, false, false, true);

    /* compiled from: SearchRequests */
    public static final class a {
        public final boolean a;
        public final boolean b;
        public final boolean c;
        public final boolean d;

        public a(boolean z, boolean z2, boolean z3, boolean z4) {
            this.a = z;
            this.b = z2;
            this.c = z3;
            this.d = z4;
        }

        /* access modifiers changed from: 0000 */
        public void a(m<SearchWithAds> mVar) {
            if (this.a) {
                mVar.a("with_shop_suggestion", "1");
            }
            if (this.b) {
                mVar.a("with_shop_search_count", "1");
            }
            if (this.c) {
                mVar.a("with_user_search_count", "1");
            }
            if (this.d) {
                mVar.a("with_deep_facets", "1");
            }
        }
    }

    /* compiled from: SearchRequests */
    public interface b {
        void a();

        void a(List<String> list);
    }

    /* compiled from: SearchRequests */
    public interface c {
        void a();

        void a(SearchWithAds searchWithAds, @Nullable String str);
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }

    public static i<SearchWithAds> a(String str, @Nullable String str2, SearchOptions searchOptions, a aVar, c cVar, Bundle bundle, @Nullable String str3) {
        m a2 = m.a(SearchWithAds.class, !TextUtils.isEmpty(str3) ? str3 : "/etsyapps/v3/bespoke/member/search-with-ads/mmx").a((com.etsy.android.lib.core.f.c<Result>) new f<Result>(cVar)).a((com.etsy.android.lib.core.f.b) new g(cVar));
        boolean isEmpty = TextUtils.isEmpty(str3);
        if (isEmpty) {
            a2.a("keywords", a(str));
        }
        if (str2 != null && isEmpty) {
            a2.a(ResponseConstants.ANCHOR_LISTING_ID, str2);
        }
        if (bundle != null && !bundle.isEmpty()) {
            for (String str4 : bundle.keySet()) {
                a2.a(str4, bundle.getString(str4));
            }
        }
        searchOptions.apply(a2);
        aVar.a(a2);
        return a2.a();
    }

    @Nullable
    private static String a(k<SearchWithAds> kVar) {
        String c2 = kVar.c(ResponseConstants.HEADER_LINK);
        if (TextUtils.isEmpty(c2) || !c2.contains("rel=\"next\"")) {
            return null;
        }
        try {
            return c2.substring(c2.indexOf("<") + 1, c2.lastIndexOf(">"));
        } catch (IndexOutOfBoundsException unused) {
            return null;
        }
    }

    public static i<SearchSimplifiedQueries> a(String str, final b bVar) {
        return m.a(SearchSimplifiedQueries.class, "/etsyapps/v3/public/search/simplify").a(ResponseConstants.QUERY, a(str)).a((com.etsy.android.lib.core.f.c<Result>) new com.etsy.android.lib.core.f.c<SearchSimplifiedQueries>() {
            public void a(List<SearchSimplifiedQueries> list, int i, k<SearchSimplifiedQueries> kVar) {
                bVar.a(((SearchSimplifiedQueries) list.get(0)).getQueries());
            }
        }).a((com.etsy.android.lib.core.f.b) new com.etsy.android.lib.core.f.b() {
            public void a(int i, String str, k kVar) {
                bVar.a();
            }
        }).a();
    }
}
