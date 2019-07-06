package com.etsy.android.ui.cardview.clickhandlers;

import com.etsy.android.lib.core.f.a;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.logger.s;

final /* synthetic */ class p implements a {
    private final String a;

    p(String str) {
        this.a = str;
    }

    public void a(k kVar) {
        s.a(String.format("feed.%s.%s", new Object[]{this.a, "success"}));
    }
}
