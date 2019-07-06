package com.etsy.android;

import android.app.Activity;
import android.app.Service;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import androidx.work.j;
import com.etsy.android.a.u;
import com.etsy.android.lib.config.c;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.core.x;
import com.etsy.android.lib.logger.a.a.C0074a;
import com.etsy.android.lib.logger.analytics.g;
import com.etsy.android.lib.logger.elk.e;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.logger.r;
import com.etsy.android.lib.util.CrashUtil;
import com.etsy.android.ui.EtsyWebActivity;
import com.etsy.android.ui.nav.NotificationActivity;
import com.etsy.android.ui.search.v2.impressions.SearchImpressionsUploadWorker.a;
import com.etsy.android.uikit.b;
import com.etsy.android.util.AppLifecycleObserver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.d;
import dagger.android.h;
import io.branch.referral.Branch;
import java.util.Timer;
import java.util.TimerTask;
import org.scribe.model.Token;

public class BOEApplication extends EtsyApplication implements a, b.a, d, h {
    private static final String BOE_LOG_PREFIX = "ETSY:";
    private static final String TAG = f.a(BOEApplication.class);
    com.etsy.android.lib.logger.analytics.f analyticsUpload;
    g analyticsUploader;
    AppLifecycleObserver appLifecycleObserver;
    com.etsy.android.deeplinking.a button;
    c configMap;
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
    DispatchingAndroidInjector<Service> dispatchingServiceInjector;
    com.etsy.android.lib.logger.elk.uploading.b elkEndpoint;
    com.etsy.android.lib.logger.elk.f elkLogger;
    FirebaseJobDispatcher jobDispatcher;
    com.etsy.android.lib.logger.legacy.b legacyLogger;
    l logCat;
    com.etsy.android.lib.logger.elk.b logDao;
    e logUploader;
    com.etsy.android.lib.push.f pushRegistration;
    com.etsy.android.messaging.salesforce.c salesforceNotificationInitializer;
    com.etsy.android.ui.search.v2.impressions.d searchImpressionRepository;
    r serverTimestampOffsetSynchronizer;
    v session;
    com.etsy.android.util.f sessionManager;
    j workManager;

    public String getConvoAuthority() {
        return "com.etsy.android.contentproviders.EtsyConvoProvider";
    }

    public String getFileProviderAuthority() {
        return "com.etsy.android.contentproviders.FileProvider";
    }

    public void onCreate() {
        super.onCreate();
        boolean b = com.etsy.android.util.d.b();
        u.a(this);
        if (!this.logUploader.a(this.workManager)) {
            this.logUploader.a(this.jobDispatcher);
        }
        if (this.configMap.c(com.etsy.android.lib.config.b.bY)) {
            this.elkLogger.b("WorkManager", "scheduling analytics upload with WorkManager");
            this.analyticsUploader.a(this.workManager);
        }
        this.session.a((v.b) new c(this));
        this.serverTimestampOffsetSynchronizer.a();
        b.a(getApplicationContext()).a(EtsyApplication.BOE_NAME).a((int) R.drawable.ic_stat_ic_notification).b(BOE_LOG_PREFIX).a(b).b(b).c(b).a(com.etsy.android.util.d.a).a((x) this.sessionManager).a((b.a) this).a(com.etsy.android.util.b.a, com.etsy.android.util.b.b).c("1431618").a(com.etsy.android.lib.config.b.aW, com.etsy.android.util.b.c).a((com.etsy.android.lib.qualtrics.a) new com.etsy.android.lib.qualtrics.a() {
            public void a(com.etsy.android.lib.qualtrics.b bVar, String str) {
                Intent intent = new Intent();
                intent.setClass(BOEApplication.this.getApplicationContext(), EtsyWebActivity.class);
                intent.putExtra("type", 12);
                intent.putExtra("url", str);
                intent.addFlags(ErrorDialogData.BINDER_CRASH);
                com.etsy.android.lib.util.r.a(BOEApplication.this.getApplicationContext(), intent);
            }
        }).a(com.etsy.android.lib.config.b.g.b, getAnalyticsTracker()).a(com.etsy.android.lib.config.b.g.c).a(this.pushRegistration).a(this.logCat).a(this.elkLogger).a();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this.appLifecycleObserver);
        Branch.k();
        Branch.c((Context) EtsyApplication.get());
        this.button.a();
        com.etsy.android.ui.a.a(getApplicationContext());
        com.etsy.android.localization.addresses.b.a(getApplicationContext());
        if (b && com.etsy.android.lib.config.a.e(this)) {
            com.etsy.android.lib.config.a a = com.etsy.android.lib.config.a.a();
            a.a((Context) this);
            this.session.c();
            a.d((Context) this);
        }
        final C0074a b2 = com.etsy.android.lib.logger.a.a.b("nulltest.timer", 0.009999999776482582d);
        new Timer().schedule(new TimerTask() {
            public void run() {
                b2.a();
            }
        }, 100);
        com.etsy.android.deeplinking.bitly.a.a(getApplicationContext());
        f.b(TAG, "onCreate");
        this.session.a((com.etsy.android.lib.core.g<?>) new d<Object>(this));
        if (this.googlePlayException != null) {
            CrashUtil.a().a((Throwable) this.googlePlayException);
        }
        this.salesforceNotificationInitializer.a(this);
    }

    public Class<? extends FragmentActivity> getDeepLinkRoutingActivity() {
        return NotificationActivity.class;
    }

    public boolean onAppUpgrade(int i) {
        boolean z;
        if (i <= 83) {
            handleTwoDotOhUpgrade();
            handleTwoDotThreeUpgrade();
            z = false;
        } else {
            if (i <= 2030006) {
                handleTwoDotThreeUpgrade();
            }
            z = true;
        }
        if (i > 0) {
            com.etsy.android.ui.util.c.a(this, i);
        }
        return z;
    }

    private void handleTwoDotOhUpgrade() {
        if (this.session.e()) {
            this.session.f();
        }
    }

    private void handleTwoDotThreeUpgrade() {
        com.etsy.android.util.h.b(this);
        com.etsy.android.util.h.a(this);
    }

    public dagger.android.b<Activity> activityInjector() {
        return this.dispatchingActivityInjector;
    }

    public dagger.android.b<Service> serviceInjector() {
        return this.dispatchingServiceInjector;
    }

    public l logCat() {
        return this.logCat;
    }

    public c configMap() {
        return this.configMap;
    }

    public com.etsy.android.lib.logger.elk.uploading.b elkLogsEndpoint() {
        return this.elkEndpoint;
    }

    public com.etsy.android.lib.logger.elk.b logDao() {
        return this.logDao;
    }

    @NonNull
    public com.etsy.android.lib.logger.analytics.f analyticsUpload() {
        return this.analyticsUpload;
    }

    public Token authToken() {
        return com.etsy.android.lib.core.b.c();
    }

    public com.etsy.android.ui.search.v2.impressions.d searchImpressionRepository() {
        return this.searchImpressionRepository;
    }

    public void initializeSalesforce() {
        this.salesforceNotificationInitializer.a(this);
    }
}
