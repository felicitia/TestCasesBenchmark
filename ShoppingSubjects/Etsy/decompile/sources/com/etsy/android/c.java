package com.etsy.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo.Builder;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build.VERSION;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.core.v.b;
import com.etsy.android.lib.logger.f;
import com.etsy.android.ui.cart.CartWithSavedActivity;
import com.etsy.android.ui.favorites.FavoritesActivity;
import com.etsy.android.ui.search.v2.SearchV2Activity;
import com.etsy.android.ui.user.PurchasesActivity;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: EtsyShortcutManager */
public class c implements b {
    public c(Context context) {
        a(context);
        v.a().a((b) this);
    }

    public void onSignedInChanged(Context context, boolean z) {
        a(context);
    }

    /* access modifiers changed from: protected */
    @TargetApi(25)
    public void a(Context context) {
        if (VERSION.SDK_INT >= 25) {
            ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(ShortcutManager.class);
            try {
                shortcutManager.removeAllDynamicShortcuts();
                ArrayList arrayList = new ArrayList();
                if (v.a().e()) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setClass(context, PurchasesActivity.class);
                    String string = context.getResources().getString(R.string.nav_purchases);
                    arrayList.add(new Builder(context, "shortcut_purchases").setShortLabel(string).setLongLabel(string).setIcon(Icon.createWithResource(context, R.drawable.ic_shortcut_purchases)).setIntent(intent).build());
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    intent2.setClass(context, FavoritesActivity.class);
                    String string2 = context.getResources().getString(R.string.nav_favorites);
                    arrayList.add(new Builder(context, "shortcut_fav").setShortLabel(string2).setLongLabel(string2).setIcon(Icon.createWithResource(context, R.drawable.ic_shortcut_favorites)).setIntent(intent2).build());
                } else {
                    shortcutManager.disableShortcuts(Arrays.asList(new String[]{"shortcut_fav", "shortcut_purchases"}), context.getString(R.string.error_shortcut_sign_in));
                }
                Intent intent3 = new Intent("android.intent.action.VIEW");
                intent3.setClass(context, CartWithSavedActivity.class);
                String string3 = context.getResources().getString(R.string.menu_cart);
                arrayList.add(new Builder(context, "shortcut_cart").setShortLabel(string3).setLongLabel(string3).setIcon(Icon.createWithResource(context, R.drawable.ic_shortcut_cart)).setIntent(intent3).build());
                Intent intent4 = new Intent("android.intent.action.VIEW");
                intent4.setClass(context, SearchV2Activity.class);
                String string4 = context.getResources().getString(R.string.menu_search);
                arrayList.add(new Builder(context, "shortcut_search").setShortLabel(string4).setLongLabel(string4).setIcon(Icon.createWithResource(context, R.drawable.ic_shortcut_search)).setIntent(intent4).build());
                shortcutManager.setDynamicShortcuts(arrayList);
            } catch (SecurityException e) {
                f.a((Throwable) e);
            }
        }
    }
}
