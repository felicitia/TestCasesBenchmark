package com.etsy.android.vespa.a;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.b;
import com.etsy.android.vespa.d;
import com.etsy.android.vespa.f;
import kotlin.jvm.internal.p;

/* compiled from: BannerClickHandler.kt */
public final class a extends f {
    private final d a;

    /* renamed from: com.etsy.android.vespa.a.a$a reason: collision with other inner class name */
    /* compiled from: BannerClickHandler.kt */
    static final class C0116a implements Runnable {
        final /* synthetic */ a a;
        final /* synthetic */ int b;

        C0116a(a aVar, int i) {
            this.a = aVar;
            this.b = i;
        }

        public final void run() {
            this.a.a().onRemoveItem(this.b);
        }
    }

    public a(FragmentActivity fragmentActivity, b bVar, f fVar, d dVar) {
        p.b(fragmentActivity, "fragmentActivity");
        p.b(bVar, "viewTracker");
        p.b(fVar, "actionDelegate");
        p.b(dVar, "adapter");
        super(fragmentActivity, bVar, fVar);
        this.a = dVar;
    }

    public final d a() {
        return this.a;
    }

    public static /* bridge */ /* synthetic */ void a(a aVar, String str, boolean z, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            num = null;
        }
        aVar.a(str, z, num);
    }

    public final void a(String str, boolean z, Integer num) {
        p.b(str, "deepLinkUrl");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        d().startActivity(intent);
        if (z && num != null) {
            new Handler().post(new C0116a(this, num.intValue()));
        }
    }
}
