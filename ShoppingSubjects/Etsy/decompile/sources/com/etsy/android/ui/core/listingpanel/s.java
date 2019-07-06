package com.etsy.android.ui.core.listingpanel;

import com.etsy.android.ui.core.k;
import io.reactivex.functions.Consumer;

final /* synthetic */ class s implements Consumer {
    private final q a;

    s(q qVar) {
        this.a = qVar;
    }

    public void accept(Object obj) {
        this.a.b((k) obj);
    }
}
