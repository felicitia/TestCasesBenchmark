package com.etsy.android.ui.cardview.clickhandlers;

import com.etsy.android.lib.core.f.c;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.logger.s;
import java.util.List;

final /* synthetic */ class o implements c {
    private final String a;

    o(String str) {
        this.a = str;
    }

    public void a(List list, int i, k kVar) {
        s.a(String.format("feed.%s.%s", new Object[]{this.a, "success"}));
    }
}
