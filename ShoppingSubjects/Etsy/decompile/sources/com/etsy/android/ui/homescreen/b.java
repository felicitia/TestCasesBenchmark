package com.etsy.android.ui.homescreen;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.LruCache;
import android.widget.Toast;
import com.etsy.android.R;
import com.etsy.android.lib.config.g;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.a.C0065a;
import com.etsy.android.lib.core.http.request.d;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.requests.HttpUtil;
import java.util.List;
import java.util.Map;

/* compiled from: RecentlyViewedTracker */
public class b {
    private static b b;
    private final LruCache<String, String> a = new LruCache<>(25);

    /* compiled from: RecentlyViewedTracker */
    public interface a {
        void a();
    }

    private b() {
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (b == null) {
                b = new b();
            }
            bVar = b;
        }
        return bVar;
    }

    public void a(String str) {
        this.a.put(str, String.valueOf(System.currentTimeMillis()));
    }

    public void b() {
        this.a.evictAll();
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(EtsyApplication.get());
        Intent intent = new Intent();
        intent.setAction(EtsyAction.RECENTLY_VIEWED_CLEAR.getAction());
        instance.sendBroadcast(intent);
    }

    @NonNull
    public Map<String, String> c() {
        return HttpUtil.formatMapAsParams("listing_ids_to_timestamps", this.a.snapshot());
    }

    public boolean d() {
        return this.a.size() == 0;
    }

    public d<EmptyResult> a(final Activity activity, @Nullable final a aVar) {
        return (d) ((com.etsy.android.lib.core.http.request.d.a) com.etsy.android.lib.core.http.request.d.a.a((EtsyApiV3Request) ((com.etsy.android.lib.core.http.request.EtsyApiV3Request.a) ((com.etsy.android.lib.core.http.request.EtsyApiV3Request.a) com.etsy.android.lib.core.http.request.EtsyApiV3Request.a.a(EmptyResult.class, "/etsyapps/v3/member/orloj/recent-listings").a("uaid", g.a().b())).a(3)).d()).a((C0065a<ResultType>) new com.etsy.android.lib.core.http.request.d.b<EmptyResult>() {
            public void a(@NonNull List<EmptyResult> list, int i, @NonNull com.etsy.android.lib.core.a.a<EmptyResult> aVar) {
                b.a().b();
                if (aVar != null) {
                    aVar.a();
                }
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<EmptyResult> aVar) {
                Toast.makeText(activity, R.string.something_went_wrong, 1).show();
            }
        }, activity)).c();
    }
}
