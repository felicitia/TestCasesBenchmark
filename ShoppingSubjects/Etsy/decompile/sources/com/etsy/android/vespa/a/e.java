package com.etsy.android.vespa.a;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.models.homescreen.MessageCard;
import com.etsy.android.vespa.b;

/* compiled from: MessageCardClickHandler */
public class e extends b<MessageCard> {
    public e(FragmentActivity fragmentActivity, @NonNull com.etsy.android.lib.logger.b bVar) {
        super(fragmentActivity, bVar);
    }

    public void a(MessageCard messageCard) {
        b(messageCard);
    }

    public void b(MessageCard messageCard) {
        if (messageCard != null && !TextUtils.isEmpty(messageCard.getDeepLinkUrl())) {
            Intent intent = new Intent(d(), EtsyApplication.get().getDeepLinkRoutingActivity());
            intent.setData(Uri.parse(messageCard.getDeepLinkUrl()));
            d().startActivity(intent);
        }
    }
}
