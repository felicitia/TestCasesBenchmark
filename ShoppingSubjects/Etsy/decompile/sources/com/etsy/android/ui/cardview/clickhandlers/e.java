package com.etsy.android.ui.cardview.clickhandlers;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.etsy.android.lib.models.apiv3.FindsCard;
import com.etsy.android.vespa.b;

/* compiled from: FindsClickHandler */
public class e extends b<FindsCard> {
    public e(FragmentActivity fragmentActivity, @NonNull com.etsy.android.lib.logger.b bVar) {
        super(fragmentActivity, bVar);
    }

    public void a(FindsCard findsCard) {
        if (findsCard != null && !TextUtils.isEmpty(findsCard.getSlug())) {
            com.etsy.android.ui.nav.e.a((Activity) d()).a(findsCard.getSlug(), (String) null, !findsCard.isPublic());
        }
    }
}
