package com.etsy.android.ui.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.R;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.apiv3.ListingCollection;
import com.etsy.android.lib.models.interfaces.BasicListingLike;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.models.interfaces.ShopLike;
import com.etsy.android.lib.models.interfaces.ShopShareable;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.uikit.share.e;
import com.etsy.android.uikit.share.e.a;
import com.etsy.android.uikit.util.SocialShareUtil;
import com.etsy.android.uikit.util.SocialShareUtil.ShareType;

/* compiled from: EtsySocialShareUtil */
public class d extends SocialShareUtil {
    private static void a(final FragmentActivity fragmentActivity, @NonNull b bVar, final ShopLike shopLike) {
        final ShareType shareType = ShareType.FAVORITE_SHOP;
        if (!a((Context) fragmentActivity, bVar, shareType)) {
            a(fragmentActivity.getLocalClassName(), shareType, shopLike.getShopId().getId());
            e eVar = new e((Activity) fragmentActivity);
            eVar.a((int) R.string.social_share_shop_prompt);
            eVar.a((a) new a() {
                public void a() {
                    SharedPreferencesUtility.j(fragmentActivity, shareType.getName());
                    com.etsy.android.ui.nav.e.a(fragmentActivity).a().a(shopLike);
                }
            });
            eVar.a();
        }
    }

    private static void a(final FragmentActivity fragmentActivity, @NonNull b bVar, final ShopShareable shopShareable) {
        final ShareType shareType = ShareType.FAVORITE_SHOP;
        if (!a((Context) fragmentActivity, bVar, shareType)) {
            a(fragmentActivity.getLocalClassName(), shareType, shopShareable.getShopId().getId());
            e eVar = new e((Activity) fragmentActivity);
            eVar.a((int) R.string.social_share_shop_prompt);
            eVar.a((a) new a() {
                public void a() {
                    SharedPreferencesUtility.j(fragmentActivity, shareType.getName());
                    com.etsy.android.ui.nav.e.a(fragmentActivity).a().a(shopShareable);
                }
            });
            eVar.a();
        }
    }

    private static void a(final FragmentActivity fragmentActivity, @NonNull b bVar, final BasicListingLike basicListingLike, final boolean z) {
        final ShareType shareType;
        if (z) {
            shareType = ShareType.POST_PURCHASE;
        } else {
            shareType = ShareType.FAVORITE_ITEM;
        }
        if (z || !a((Context) fragmentActivity, bVar, shareType)) {
            a(fragmentActivity.getLocalClassName(), shareType, basicListingLike.getListingId().getId());
            e eVar = new e((Activity) fragmentActivity);
            eVar.a(z ? R.string.social_share_pp_prompt : R.string.social_share_listing_prompt);
            eVar.a((a) new a() {
                public void a() {
                    if (!z) {
                        SharedPreferencesUtility.j(fragmentActivity, shareType.getName());
                    }
                    com.etsy.android.ui.nav.e.a(fragmentActivity).a().a(basicListingLike);
                }
            });
            eVar.a();
        }
    }

    public static void a(final FragmentActivity fragmentActivity, @NonNull b bVar, final ListingCollection listingCollection, final ListingLike listingLike) {
        if (!a((Context) fragmentActivity, bVar, ShareType.ADD_TO_LIST)) {
            a(fragmentActivity.getLocalClassName(), ShareType.ADD_TO_LIST, listingCollection.getKey());
            e eVar = new e((Activity) fragmentActivity);
            eVar.a((int) R.string.social_share_collection_prompt);
            eVar.a((a) new a() {
                public void a() {
                    SharedPreferencesUtility.j(fragmentActivity, ShareType.ADD_TO_LIST.getName());
                    com.etsy.android.ui.nav.e.a(fragmentActivity).a().a(listingCollection, listingLike);
                }
            });
            eVar.a();
        }
    }

    private static boolean a(Context context, @NonNull b bVar, ShareType shareType) {
        boolean z = false;
        if (bVar.c().c(com.etsy.android.lib.config.b.aU)) {
            return false;
        }
        if (SharedPreferencesUtility.h(context, shareType.getName()) + 604800000 > System.currentTimeMillis()) {
            z = true;
        }
        if (!z) {
            SharedPreferencesUtility.i(context, shareType.getName());
        }
        return z;
    }

    public static void a(FragmentActivity fragmentActivity, @NonNull b bVar, BaseModel baseModel, boolean z) {
        if (baseModel instanceof ShopLike) {
            a(fragmentActivity, bVar, (ShopLike) baseModel);
        } else if (baseModel instanceof BasicListingLike) {
            a(fragmentActivity, bVar, (BasicListingLike) baseModel, z);
        } else if (baseModel instanceof ShopShareable) {
            a(fragmentActivity, bVar, (ShopShareable) baseModel);
        }
    }
}
