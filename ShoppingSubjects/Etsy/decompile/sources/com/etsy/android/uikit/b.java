package com.etsy.android.uikit;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.webkit.CookieSyncManager;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.Volley;
import com.etsy.android.iconsy.d;
import com.etsy.android.lib.config.EtsyBuild;
import com.etsy.android.lib.config.EtsyConfigKey;
import com.etsy.android.lib.config.g;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.core.u;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.core.x;
import com.etsy.android.lib.eventhorizon.EventHorizonService;
import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.push.f;
import com.etsy.android.lib.qualtrics.QualtricsController;
import com.etsy.android.lib.requests.LocaleRequest;
import com.etsy.android.lib.util.CrashUtil;
import com.etsy.android.lib.util.CrashUtil.CrashProvider;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.ab;
import com.etsy.android.lib.util.ae;
import com.etsy.android.lib.util.fonts.FontMaps;
import com.pinterest.pinit.PinItButton;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: EtsyAppBuilder */
public class b {
    @Nullable
    com.etsy.android.lib.util.sharedprefs.a a;
    private String b;
    private String c;
    private a d;
    private boolean e;
    private String f;
    private int g;
    private String h;
    private Context i;
    private x j;
    private EtsyBuild k;
    private boolean l;
    private boolean m;
    private String n;
    private String o = null;
    private EtsyConfigKey p = null;
    private List<com.etsy.android.lib.core.v.b> q = new ArrayList();
    private f r;
    private l s;
    private com.etsy.android.lib.logger.elk.f t;
    private boolean u = false;
    @Nullable
    private EtsyConfigKey v = null;
    @Nullable
    private com.etsy.android.lib.logger.b w = null;
    @Nullable
    private com.etsy.android.lib.qualtrics.a x = null;
    @Nullable
    private EtsyConfigKey y = null;

    /* compiled from: EtsyAppBuilder */
    public interface a {
        boolean onAppUpgrade(int i);
    }

    public static b a(Context context) {
        return new b(context);
    }

    private b(Context context) {
        this.i = context;
    }

    public b a(f fVar) {
        this.r = fVar;
        return this;
    }

    public b a(l lVar) {
        this.s = lVar;
        return this;
    }

    public b a(com.etsy.android.lib.logger.elk.f fVar) {
        this.t = fVar;
        return this;
    }

    public b a(String str) {
        this.f = str;
        return this;
    }

    public b a(int i2) {
        this.g = i2;
        return this;
    }

    public b b(String str) {
        this.h = str;
        return this;
    }

    public b a(boolean z) {
        this.e = z;
        return this;
    }

    public b a(EtsyBuild etsyBuild) {
        this.k = etsyBuild;
        return this;
    }

    public b a(String str, String str2) {
        this.b = str;
        this.c = str2;
        return this;
    }

    public b b(boolean z) {
        this.l = z;
        return this;
    }

    public b c(boolean z) {
        this.m = z;
        return this;
    }

    public b a(a aVar) {
        this.d = aVar;
        return this;
    }

    public b a(x xVar) {
        this.j = xVar;
        return this;
    }

    public b c(String str) {
        this.n = str;
        return this;
    }

    public b a(EtsyConfigKey etsyConfigKey, String str) {
        this.p = etsyConfigKey;
        this.o = str;
        return this;
    }

    public b a(@Nullable EtsyConfigKey etsyConfigKey, @Nullable com.etsy.android.lib.logger.b bVar) {
        this.u = true;
        this.w = bVar;
        this.v = etsyConfigKey;
        return this;
    }

    public b a(@Nullable com.etsy.android.lib.qualtrics.a aVar) {
        this.x = aVar;
        return this;
    }

    public b a(@Nullable EtsyConfigKey etsyConfigKey) {
        this.y = etsyConfigKey;
        return this;
    }

    public void a() {
        boolean z;
        if (this.h != null) {
            com.etsy.android.lib.logger.f.a(this.h);
        }
        com.etsy.android.lib.logger.f.a(this.e);
        g.a(this.i, this.f, this.g, this.k, this.e);
        if (this.b == null || this.c == null) {
            throw new IllegalStateException("EtsyAppBuilder must have setEtsyApiInfo() set");
        }
        CrashUtil.a().a(CrashProvider.CRITTERCISM, this.p, this.o);
        CrashUtil.a().a(this.i);
        if (this.a != null) {
            this.a.a();
        }
        if (com.etsy.android.lib.config.a.a().d().c(com.etsy.android.lib.config.b.bV)) {
            EtsyApplication.get().initAppStateListener();
        }
        if (!(!this.u || this.w == null || this.v == null)) {
            QualtricsController.a().a(this.i, this.v, this.w.c(), this.y);
            QualtricsController.a().a(this.x);
        }
        CookieSyncManager.createInstance(this.i);
        NetworkUtils.a(this.i);
        for (com.etsy.android.lib.core.v.b a2 : this.q) {
            v.a().a(a2);
        }
        com.etsy.android.lib.toolbar.a.a(this.i, this.l);
        com.etsy.android.lib.eventhorizon.a.a(this.i, this.m);
        if (this.m && com.etsy.android.lib.eventhorizon.a.a()) {
            this.i.startService(new Intent(this.i, EventHorizonService.class));
        }
        int o2 = SharedPreferencesUtility.o(this.i);
        boolean z2 = true;
        if (this.d != null) {
            z = this.d.onAppUpgrade(o2);
            v a3 = v.a();
            if (o2 != 0) {
                z2 = false;
            }
            a3.c(z2);
        } else {
            z = true;
        }
        if (z) {
            v.a().a(v.a().e());
        }
        SharedPreferencesUtility.a(this.i, g.a().m());
        CurrencyUtil.a(this.i, com.etsy.android.lib.config.a.a().d().b(com.etsy.android.lib.config.b.M));
        EtsyMoney.setConditionalCurrencyCodesHidden(com.etsy.android.lib.config.a.a().d().c(com.etsy.android.lib.config.b.N));
        d.a(this.i, FontMaps.FONT_MAPS);
        com.etsy.android.lib.config.a.a().d(this.i);
        com.etsy.android.lib.core.img.f.a().a(this.i, this.t);
        ae.a(this.e);
        v.a().b();
        com.etsy.android.stylekit.b.a(this.i);
        if (this.n != null) {
            PinItButton.setPartnerId(this.n);
            PinItButton.setDebugMode(this.e);
        }
        if (!u.b(this.i).equals(Volley.DEFAULT_CACHE_DIR)) {
            File file = new File(Volley.DEFAULT_CACHE_DIR);
            if (file.exists() && file.isDirectory()) {
                new DiskBasedCache(new File(Volley.DEFAULT_CACHE_DIR)).clear();
            }
        }
        if (v.a().e() && ab.b(this.i)) {
            LocaleRequest.setUserLocale();
        }
        rx_activity_result2.f.a((Application) EtsyApplication.get());
        EtsyApplication.get().registerTooLargeToolLoggingCallbacksIfNecessary(EtsyApplication.get().getAnalyticsTracker().c());
    }
}
