package com.etsy.android.ui.shop;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.j;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShopAboutVideo;
import com.etsy.android.lib.models.apiv3.ShopHomePage;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.shophome.ShopHomeAdapter;
import com.etsy.android.ui.cardview.clickhandlers.f;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.nav.ActivityNavigator.AnimationMode;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.uikit.viewholder.a.a;
import com.etsy.android.vespa.d;

/* compiled from: BOEShopHomeRouter */
public abstract class b implements com.etsy.android.lib.shophome.b {
    private TrackingBaseActivity a;
    private d b;

    public b(@NonNull TrackingBaseActivity trackingBaseActivity, @NonNull d dVar, @NonNull EtsyId etsyId) {
        this.a = trackingBaseActivity;
        this.b = dVar;
    }

    public void a(@Nullable String str) {
        e.a((Activity) this.a).k(str);
    }

    public a b() {
        return new f(f(), this.b, g());
    }

    public com.etsy.android.uikit.viewholder.a.a.a c() {
        return new com.etsy.android.uikit.viewholder.a.a.a(f(), g(), this);
    }

    public void a(@NonNull LocalMarket localMarket) {
        ((com.etsy.android.ui.nav.b) e.a((FragmentActivity) f()).a().a((j) f())).a(localMarket, true);
    }

    public void a(@NonNull ShopAboutVideo shopAboutVideo) {
        ((com.etsy.android.ui.nav.b) e.a((Activity) f()).a(AnimationMode.FADE_IN_OUT).a((j) f())).a(shopAboutVideo.getShopId(), shopAboutVideo.getVideoUrl());
    }

    public void a(@NonNull EtsyId etsyId) {
        ((com.etsy.android.ui.nav.b) e.a((Activity) f()).a((j) f())).c(etsyId);
    }

    public void b(@NonNull EtsyId etsyId) {
        ((com.etsy.android.ui.nav.b) e.a((Activity) f()).a((j) f())).g(etsyId);
    }

    public void e() {
        ShopHomePage pageData = ((ShopHomeAdapter) this.b).getPageData();
        if (pageData != null) {
            ((com.etsy.android.ui.nav.b) e.a((Activity) f()).a((j) f())).e(pageData.getShop().getShopId(), null);
        }
    }

    /* access modifiers changed from: 0000 */
    public TrackingBaseActivity f() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public w g() {
        return f().getAnalyticsContext();
    }

    public void a() {
        String loginName = ((ShopHomeAdapter) this.b).getPageData().getShop().getOwner().getLoginName();
        TrackingBaseActivity f = f();
        if (f != null) {
            Bundle bundle = new Bundle();
            if (v.a().e()) {
                bundle.putString(ResponseConstants.USERNAME, loginName);
                ((com.etsy.android.ui.nav.b) e.a((FragmentActivity) f).a().a((j) f())).e(bundle);
            } else {
                bundle.putString(ResponseConstants.USERNAME, loginName);
                ((com.etsy.android.ui.nav.b) e.a((FragmentActivity) f).a().a((j) f())).a(EtsyAction.CONTACT_USER, bundle);
            }
        }
    }
}
