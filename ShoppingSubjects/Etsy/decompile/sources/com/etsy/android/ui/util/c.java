package com.etsy.android.ui.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import com.etsy.android.R;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.aa;
import com.etsy.android.ui.nav.e;
import com.etsy.android.ui.promos.VersionPromo;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.util.d;
import java.util.ArrayList;
import java.util.List;

/* compiled from: EtsyPromoUtil */
public class c extends aa {
    private static final List<VersionPromo> a = new ArrayList();

    public static void a(@Nullable b bVar, Context context, @NonNull VersionPromo versionPromo) {
        if (bVar != null) {
            bVar.a("update_beta_dismissed", new EtsyPromoUtil$1(versionPromo));
        }
        SharedPreferencesUtility.c(context, versionPromo.getUniqueName());
    }

    public static void a(Context context, int i) {
        for (VersionPromo versionPromo : a) {
            if (i < versionPromo.getMinVersion()) {
                SharedPreferencesUtility.d(context, versionPromo.getUniqueName());
            }
        }
    }

    public static VersionPromo b(Context context) {
        for (VersionPromo versionPromo : a) {
            if (SharedPreferencesUtility.b(context, versionPromo.getUniqueName())) {
                return versionPromo;
            }
        }
        return null;
    }

    public static boolean c(Context context) {
        return aa.a(context, com.etsy.android.lib.config.b.cv);
    }

    public static void a(final TrackingBaseActivity trackingBaseActivity) {
        e.a((FragmentActivity) trackingBaseActivity).d().a((int) R.layout.promo_new_upgrade_play, aa.a(aa.a(com.etsy.android.lib.config.b.cv)), (OnClickListener) new TrackingOnClickListener() {
            public void onViewClick(View view) {
                view.getContext().startActivity(c.a(trackingBaseActivity.getAnalyticsContext()));
            }
        });
    }

    public static Intent a(@NonNull w wVar) {
        return new Intent("android.intent.action.VIEW", Uri.parse(wVar.c().b(com.etsy.android.lib.config.b.ct)));
    }

    public static boolean b() {
        return d.a();
    }

    public static void a(FragmentActivity fragmentActivity, OnClickListener onClickListener, String str) {
        e.a(fragmentActivity).d().a((int) R.layout.promo_beta_upgrade, aa.a(str), onClickListener);
    }
}
