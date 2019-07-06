package com.etsy.android.ui.cart.a;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.ui.cart.d;
import com.etsy.android.vespa.a.f;

/* compiled from: CartGroupActionClickHandler */
public class a extends f {
    protected final d a;

    public a(@NonNull d dVar, FragmentActivity fragmentActivity, @NonNull b bVar) {
        super(fragmentActivity, bVar, dVar);
        this.a = dVar;
    }

    public void a(View view, @NonNull ServerDrivenAction serverDrivenAction) {
        this.a.showVariationSelectDialog(a(view), serverDrivenAction);
    }
}
