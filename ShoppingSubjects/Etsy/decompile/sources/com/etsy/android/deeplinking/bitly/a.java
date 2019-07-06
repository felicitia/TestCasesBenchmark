package com.etsy.android.deeplinking.bitly;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.a.a.C0006a;
import com.a.d;
import com.etsy.android.BOEApplication;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.legacy.b;
import com.etsy.android.lib.messaging.c;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: BitlyManager */
public class a {
    public static final String a = f.a(a.class);
    public static final AtomicBoolean b = new AtomicBoolean(false);

    public static void a(Context context) {
        if (b()) {
            com.a.a.a(context, "Ah6cjtTBAgK", Arrays.asList(new String[]{"etsy.me"}), Arrays.asList(new String[]{"etsy"}), new C0006a() {
                public void a(com.a.f fVar) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Bitly.Callback onResponse() ");
                    sb.append(fVar.a());
                    sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    sb.append(fVar.b());
                    sb.append(" App link: ");
                    sb.append(fVar.e());
                    sb.append(", URL: ");
                    sb.append(fVar.d());
                    sb.append(", Bitlink: ");
                    sb.append(fVar.c());
                    f.d(sb.toString());
                    int a = fVar.a();
                    if (a < 200 || a >= 300) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Bitly error ");
                        sb2.append(a);
                        sb2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                        sb2.append(fVar.b());
                        sb2.append(" for Bitlink ");
                        sb2.append(fVar.c());
                        b.a().b(a.a, sb2.toString());
                        a.b(a, fVar.b(), fVar.c());
                        return;
                    }
                    Context applicationContext = BOEApplication.get().getApplicationContext();
                    Intent intent = new Intent("android.intent.action.VIEW");
                    Uri parse = Uri.parse(fVar.d());
                    intent.setData(parse);
                    intent.setFlags(ErrorDialogData.BINDER_CRASH);
                    if (c.a(parse) != null) {
                        intent.setClass(applicationContext, EtsyApplication.get().getDeepLinkRoutingActivity());
                    }
                    applicationContext.startActivity(intent);
                    a.b(fVar.d());
                }

                public void a(d dVar) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Bitly.Callback onError() ");
                    sb.append(dVar.a());
                    f.f(sb.toString());
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Bitly error ");
                    sb2.append(dVar.a());
                    sb2.append(" for ");
                    sb2.append(dVar.b());
                    b.a().b(a.a, sb2.toString());
                    a.b(0, dVar.a(), dVar.b());
                }
            });
        }
    }

    public static boolean a() {
        if (b.compareAndSet(false, b())) {
            a((Context) EtsyApplication.get());
        }
        return b.get();
    }

    private static boolean b() {
        return BOEApplication.get().getAnalyticsTracker().c().c(com.etsy.android.lib.config.b.aZ);
    }

    /* access modifiers changed from: private */
    public static void b(String str) {
        BOEApplication.get().getAnalyticsTracker().a("opened_bitlink", new BitlyManager$2(str));
    }

    /* access modifiers changed from: private */
    public static void b(int i, String str, String str2) {
        BOEApplication.get().getAnalyticsTracker().a("bitlink_error", new BitlyManager$3(i, str, str2));
    }
}
