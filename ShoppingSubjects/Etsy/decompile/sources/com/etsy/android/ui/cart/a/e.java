package com.etsy.android.ui.cart.a;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.ui.cart.d;

/* compiled from: GiftOptionsClickHandler */
public class e extends a {
    public e(@NonNull d dVar, FragmentActivity fragmentActivity, @NonNull b bVar) {
        super(dVar, fragmentActivity, bVar);
    }

    public void a(@NonNull String str, @NonNull String str2, Image image) {
        com.etsy.android.ui.nav.e.a(d()).a().a(str, str2, image);
    }
}
