package com.etsy.android.ui.cardview.clickhandlers;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import com.etsy.android.R;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.BasicShopLike;
import com.etsy.android.lib.models.interfaces.ShopLike;
import com.etsy.android.ui.util.e;
import com.etsy.android.vespa.b;
import java.util.HashMap;

/* compiled from: ShopCardClickHandler */
public class m extends b<ShopLike> {
    private e a;

    public m(FragmentActivity fragmentActivity, @NonNull com.etsy.android.lib.logger.b bVar) {
        super(fragmentActivity, bVar);
        this.a = new e(d(), null, bVar);
    }

    public void a(ShopLike shopLike, @Nullable String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(AnalyticsLogAttribute.SHOP_ID, shopLike.getShopId().getId());
        if (str != null) {
            hashMap.put(AnalyticsLogAttribute.CONTENT_SOURCE, str);
        }
        com.etsy.android.lib.logger.b c = c();
        StringBuilder sb = new StringBuilder();
        sb.append(c().b());
        sb.append("_tapped_shop");
        c.a(sb.toString(), hashMap);
        com.etsy.android.ui.nav.e.a(d()).a().b(shopLike.getShopId());
    }

    public void a(ShopLike shopLike) {
        a(shopLike, null);
    }

    public void a(ShopCard shopCard, ImageView imageView, boolean z) {
        EtsyId shopId = shopCard.getShopId();
        if (!v.a().e()) {
            ((com.etsy.android.ui.nav.b) com.etsy.android.ui.nav.e.a((Activity) d()).a((View) imageView)).a(EtsyAction.FAVORITE, shopId.getId());
            return;
        }
        this.a.a(imageView, R.drawable.ic_favorite_selector, R.drawable.ic_favorited_selector, z);
        this.a.a((BasicShopLike) shopCard, (e.b) null, z);
        shopCard.setIsFavorite(!z);
    }

    public void a(String str) {
        if (!v.a().e()) {
            com.etsy.android.ui.nav.e.a((Activity) d()).a(EtsyAction.CONTACT_USER, str);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(ResponseConstants.USERNAME, str);
        com.etsy.android.ui.nav.e.a((Activity) d()).e(bundle);
    }

    public void a(EtsyId etsyId) {
        com.etsy.android.ui.nav.e.a(d()).a().c(etsyId, null);
    }
}
