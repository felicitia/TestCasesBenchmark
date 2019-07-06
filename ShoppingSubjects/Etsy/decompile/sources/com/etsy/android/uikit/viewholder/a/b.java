package com.etsy.android.uikit.viewholder.a;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.shophome.model.ShopHomeLocalMarketViewModel;
import com.etsy.android.lib.shophome.model.ShopHomeReviewResponseViewData;
import com.etsy.android.lib.shophome.model.ShopHomeReviewViewModel;
import com.etsy.android.lib.shophome.model.ShopOwnerViewModel;

/* compiled from: HorizontalImageAndHeadingClickHandler */
public class b extends com.etsy.android.vespa.b {
    private final com.etsy.android.lib.shophome.b a;

    public b(@NonNull FragmentActivity fragmentActivity, @NonNull com.etsy.android.lib.logger.b bVar, com.etsy.android.lib.shophome.b bVar2) {
        super(fragmentActivity, bVar);
        this.a = bVar2;
    }

    public void a(Object obj) {
        if (obj instanceof ShopHomeLocalMarketViewModel) {
            this.a.a(((ShopHomeLocalMarketViewModel) obj).getLocalMarket());
        } else if (obj instanceof ShopOwnerViewModel) {
            this.a.a(((ShopOwnerViewModel) obj).getShop().getUserId());
        } else if (obj instanceof ShopHomeReviewResponseViewData) {
            this.a.a(((ShopHomeReviewResponseViewData) obj).getShop().getUserId());
        } else if (obj instanceof ShopHomeReviewViewModel) {
            this.a.a(((ShopHomeReviewViewModel) obj).getBuyerUserId());
        }
    }
}
