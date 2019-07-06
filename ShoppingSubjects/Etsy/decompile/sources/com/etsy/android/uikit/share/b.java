package com.etsy.android.uikit.share;

import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;

final /* synthetic */ class b implements io.reactivex.functions.b {
    private final ShareBrokerFragment a;
    private final ResolveInfo b;
    private final b c;

    b(ShareBrokerFragment shareBrokerFragment, ResolveInfo resolveInfo, b bVar) {
        this.a = shareBrokerFragment;
        this.b = resolveInfo;
        this.c = bVar;
    }

    public void a(Object obj, Object obj2) {
        this.a.lambda$onIntentItemClick$0$ShareBrokerFragment(this.b, this.c, (Bitmap) obj, (Throwable) obj2);
    }
}
