package com.etsy.android.ui.cardview.clickhandlers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.core.f;
import com.etsy.android.lib.core.f.c;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.m;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.s;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.shopshare.ShareAnnotation;
import com.etsy.android.lib.models.shopshare.ShopShareCard;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.adapter.AnnotationAdapter.a;
import com.etsy.android.uikit.view.TaggableImageView;
import com.etsy.android.vespa.b;
import com.etsy.android.vespa.d;

/* compiled from: ShopShareCardClickHandler */
public class n extends b<ShopShareCard> {
    private d a;

    public void a(ShopShareCard shopShareCard) {
    }

    public n(FragmentActivity fragmentActivity, d dVar, @NonNull com.etsy.android.lib.logger.b bVar) {
        super(fragmentActivity, bVar);
        this.a = dVar;
    }

    public void a(TaggableImageView.d dVar, ShopShareCard shopShareCard) {
        ShareAnnotation d = ((a) dVar).d();
        s.a("tag.tapped", b(), s.a(shopShareCard.getShareItem()));
        e.a(d()).a().a(d.getObjectId(), a());
    }

    public void b(ShopShareCard shopShareCard) {
        s.a("photo.tapped", b(), s.a(shopShareCard.getShareItem()));
        if (b().equals("homescreen_recommended")) {
            e.a(d()).a().c(shopShareCard.getLandingPage());
            return;
        }
        e.a(d()).a().a(shopShareCard.getShareItem().getPrimaryAnnotation().getObjectId(), a());
    }

    public void c(ShopShareCard shopShareCard) {
        com.etsy.android.util.e.a(d(), c(), shopShareCard.getShareItem().getShareId());
    }

    public void a(int i, ShopShareCard shopShareCard) {
        a(i, shopShareCard, ResponseConstants.OBJECT);
        this.a.onRemoveItem(i);
    }

    public void b(int i, ShopShareCard shopShareCard) {
        a(i, shopShareCard, ResponseConstants.SUBJECT);
        this.a.onRemoveItem(i);
    }

    public void d(ShopShareCard shopShareCard) {
        s.a("shop.tapped", b());
        EtsyId ownerId = shopShareCard.getOwnerId();
        if (ownerId == null) {
            ownerId = ((ListingCard) shopShareCard.getShareItem().getPrimaryAnnotation().getObject()).getShopId();
        }
        e.a(d()).a().b(ownerId, a());
    }

    private Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("shop_stats_referrer", "shop_updates");
        Bundle bundle2 = new Bundle();
        bundle2.putBundle("referrer_bundle", bundle);
        return bundle2;
    }

    private void a(int i, ShopShareCard shopShareCard, String str) {
        String format = String.format("/etsyapps/v3/member/shop/shares/activity/%s/%s/%s/block", new Object[]{shopShareCard.getActivityId(), Integer.valueOf(shopShareCard.getOwnerType()), shopShareCard.getOwnerId()});
        String str2 = str.equals(ResponseConstants.OBJECT) ? "block_this_post" : "block_seller_posts";
        v.a().j().a((g<Result>) m.a(EmptyResult.class, format, 1).b("type", str).a((c<Result>) new o<Result>(str2)).a((f.a) new p(str2)).a((f.b) new q(str2)).a());
    }
}
