package com.contextlogic.wish.activity.profile.wishlist;

import android.content.Context;
import android.content.Intent;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishWishlist;

public class WishlistHelper {
    public static void triggerShareIntent(Context context, WishWishlist wishWishlist) {
        String string = context.getString(R.string.wishlist_share_intent_message, new Object[]{wishWishlist.getName(), wishWishlist.getPermalink()});
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", context.getString(R.string.wishlist_share_intent_title));
        intent.putExtra("android.intent.extra.TEXT", string);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.wishlist_share_intent_title)));
    }
}
