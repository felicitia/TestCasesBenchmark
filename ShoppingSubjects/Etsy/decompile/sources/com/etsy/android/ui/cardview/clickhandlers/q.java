package com.etsy.android.ui.cardview.clickhandlers;

import com.etsy.android.lib.core.f.b;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.logger.s;

final /* synthetic */ class q implements b {
    private final String a;

    q(String str) {
        this.a = str;
    }

    public void a(int i, String str, k kVar) {
        s.a(String.format("feed.%s.%s", new Object[]{this.a, "failure"}));
    }
}
