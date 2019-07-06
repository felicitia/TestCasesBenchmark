package com.etsy.android.util;

import android.content.Context;
import android.text.TextUtils;
import com.etsy.android.lib.config.g;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.posts.PersistentRequest;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.requests.GuestRequest;
import com.etsy.android.util.posts.MergeGuestsPost;
import java.util.Arrays;
import java.util.HashSet;

/* compiled from: UpgradeUtil */
public class h {
    protected static final HashSet<String> a = new HashSet<>(Arrays.asList(new String[]{"9774d56d682e549c"}));

    public static void a(Context context) {
        if (!a.contains(g.a().d())) {
            GuestRequest mergeFromGuest = GuestRequest.mergeFromGuest(g.a().d());
            mergeFromGuest.setRetryCount(5);
            mergeFromGuest.setRetryBackOffMultiplier(1.5f);
            v.a().k().a((PersistentRequest<Request, Result>) new MergeGuestsPost<Request,Result>(mergeFromGuest));
            EtsyApplication.get().getAnalyticsTracker().a("merged_guest_cart_to_uuid", null);
        }
    }

    public static void b(Context context) {
        if (TextUtils.equals(g.a().b(), g.a().d())) {
            g.a().a(context);
        }
    }
}
