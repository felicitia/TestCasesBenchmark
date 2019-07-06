package com.etsy.android.util;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.Menu;
import android.view.View;
import com.etsy.android.R;
import com.etsy.android.lib.logger.f;
import com.etsy.android.uikit.util.b;
import com.etsy.android.uikit.util.b.C0114b;

public class BOEOptionsMenuItemHelper extends b {
    private static final String a = f.a(BOEOptionsMenuItemHelper.class);

    private enum OptionsMenuIcon implements C0114b {
        CART(R.id.menu_cart, R.drawable.sk_ic_cart),
        SEND(R.id.menu_send_reply, R.drawable.sk_ic_send),
        COMPOSE(R.id.menu_new_message, R.drawable.sk_ic_compose),
        REPLY(R.id.menu_reply, R.drawable.sk_ic_reply),
        SEARCH(R.id.menu_search, R.drawable.sk_ic_search),
        SHARE(R.id.menu_share, R.drawable.sk_ic_androidshare);
        
        @DrawableRes
        private int mIcon;
        private int mMenuId;

        private OptionsMenuIcon(int i, int i2) {
            this.mMenuId = i;
            this.mIcon = i2;
        }

        public int getMenuId() {
            return this.mMenuId;
        }

        @DrawableRes
        public int getIcon() {
            return this.mIcon;
        }
    }

    public static void a(Context context, Menu menu) {
        a(OptionsMenuIcon.values(), context, menu);
    }

    public static void a(Menu menu) {
        a(OptionsMenuIcon.values(), menu);
    }

    public static View a(Context context, int i, int i2) {
        return a(context, i, context.getString(i2), context.getResources().getDimension(R.dimen.actionbar_menu_text_size), null);
    }
}
