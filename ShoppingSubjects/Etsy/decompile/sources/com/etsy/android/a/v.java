package com.etsy.android.a;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.work.j;
import com.etsy.android.BOEApplication;
import com.etsy.android.R;
import com.etsy.android.lib.config.a;
import com.etsy.android.lib.config.c;
import com.etsy.android.lib.config.g;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.legacy.LegacyEtsyLoggerDatabaseHelper;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.lib.util.ag;
import com.etsy.android.messaging.h;
import com.etsy.android.util.b;
import com.etsy.android.util.d;
import com.etsy.android.util.f;

/* compiled from: AppModule */
class v {
    /* access modifiers changed from: 0000 */
    public EtsyApplication a(BOEApplication bOEApplication) {
        return bOEApplication;
    }

    v() {
    }

    /* access modifiers changed from: 0000 */
    public Context b(BOEApplication bOEApplication) {
        return bOEApplication.getApplicationContext();
    }

    /* access modifiers changed from: 0000 */
    public a a(Context context, g gVar) {
        a.a(context, b.a, b.b);
        return a.a();
    }

    /* access modifiers changed from: 0000 */
    public c a(a aVar) {
        return aVar.d();
    }

    /* access modifiers changed from: 0000 */
    public g a(Context context) {
        g.a(context, EtsyApplication.BOE_NAME, R.drawable.ic_stat_ic_notification, com.etsy.android.b.a, d.b());
        return g.a();
    }

    public static com.etsy.android.lib.core.v a(Context context, f fVar, com.etsy.android.lib.h.a aVar) {
        com.etsy.android.lib.core.v.a(context, fVar, false, aVar);
        return com.etsy.android.lib.core.v.a();
    }

    /* access modifiers changed from: 0000 */
    public com.etsy.android.lib.auth.b a() {
        return new com.etsy.android.lib.auth.f();
    }

    public static com.etsy.android.lib.logger.legacy.b b(Context context) {
        com.etsy.android.lib.logger.legacy.b.a(context, com.etsy.android.lib.logger.legacy.c.a(context), (SQLiteOpenHelper) LegacyEtsyLoggerDatabaseHelper.getInstance(context), com.etsy.android.b.a);
        return com.etsy.android.lib.logger.legacy.b.a();
    }

    /* access modifiers changed from: 0000 */
    public ag b() {
        return new ag();
    }

    /* access modifiers changed from: 0000 */
    public j c() {
        return j.a();
    }

    /* access modifiers changed from: 0000 */
    public NetworkUtils c(Context context) {
        NetworkUtils.a(context);
        return NetworkUtils.a();
    }

    /* access modifiers changed from: 0000 */
    public h d() {
        return new h();
    }
}
