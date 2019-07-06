package com.etsy.android.messaging.salesforce;

import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.NotificationCompat.Builder;
import com.etsy.android.BOEApplication;
import com.etsy.android.R;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.GoogleApiAvailability;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.a.C0159a;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.notifications.c.C0173c;
import io.reactivex.g;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.p;

/* compiled from: SalesforceNotificationInitializer.kt */
public final class c {
    public static final a a = new a(null);
    private static final AtomicBoolean f = new AtomicBoolean();
    /* access modifiers changed from: private */
    public static final AtomicBoolean g = new AtomicBoolean();
    /* access modifiers changed from: private */
    public final l b;
    private final com.etsy.android.lib.util.sharedprefs.d c;
    /* access modifiers changed from: private */
    public final a d;
    private final GoogleApiAvailability e;

    /* compiled from: SalesforceNotificationInitializer.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }

        public final boolean a() {
            if (c.g.compareAndSet(false, b())) {
                EtsyApplication etsyApplication = EtsyApplication.get();
                if (!(etsyApplication instanceof BOEApplication)) {
                    etsyApplication = null;
                }
                BOEApplication bOEApplication = (BOEApplication) etsyApplication;
                if (bOEApplication != null) {
                    bOEApplication.initializeSalesforce();
                }
            }
            return c.g.get();
        }

        /* access modifiers changed from: private */
        public final boolean b() {
            EtsyApplication etsyApplication = BOEApplication.get();
            p.a((Object) etsyApplication, "BOEApplication.get()");
            com.etsy.android.lib.logger.b analyticsTracker = etsyApplication.getAnalyticsTracker();
            p.a((Object) analyticsTracker, "BOEApplication.get().analyticsTracker");
            return analyticsTracker.c().c(com.etsy.android.lib.config.b.aY);
        }
    }

    /* compiled from: SalesforceNotificationInitializer.kt */
    static final class b implements com.salesforce.marketingcloud.c.a {
        final /* synthetic */ c a;
        final /* synthetic */ BOEApplication b;

        b(c cVar, BOEApplication bOEApplication) {
            this.a = cVar;
            this.b = bOEApplication;
        }

        public final void a(InitializationStatus initializationStatus) {
            p.b(initializationStatus, "initializationStatus");
            this.a.a(initializationStatus, this.b);
        }
    }

    /* renamed from: com.etsy.android.messaging.salesforce.c$c reason: collision with other inner class name */
    /* compiled from: SalesforceNotificationInitializer.kt */
    static final class C0084c implements com.salesforce.marketingcloud.notifications.c.a {
        final /* synthetic */ c a;

        C0084c(c cVar) {
            this.a = cVar;
        }

        public final Builder a(Context context, NotificationMessage notificationMessage) {
            p.b(context, ResponseConstants.CONTEXT);
            p.b(notificationMessage, "message");
            Builder a2 = com.salesforce.marketingcloud.notifications.c.a(context, notificationMessage);
            a a3 = this.a.d;
            p.a((Object) a2, "notificationBuilder");
            a3.a(a2);
            return a2;
        }
    }

    /* compiled from: SalesforceNotificationInitializer.kt */
    static final class d implements C0173c {
        final /* synthetic */ c a;

        d(c cVar) {
            this.a = cVar;
        }

        public final PendingIntent a(Context context, NotificationMessage notificationMessage) {
            p.b(context, ResponseConstants.CONTEXT);
            p.b(notificationMessage, "notificationmessage");
            return this.a.d.a(notificationMessage.payload(), context);
        }
    }

    public static final boolean b() {
        return a.a();
    }

    public c(l lVar, com.etsy.android.lib.util.sharedprefs.d dVar, a aVar, GoogleApiAvailability googleApiAvailability) {
        p.b(lVar, "logCat");
        p.b(dVar, "userIdStream");
        p.b(aVar, "pushNotificationPopulator");
        p.b(googleApiAvailability, "googleApiAvailability");
        this.b = lVar;
        this.c = dVar;
        this.d = aVar;
        this.e = googleApiAvailability;
    }

    public final void a(BOEApplication bOEApplication) {
        p.b(bOEApplication, "application");
        if (!a.b()) {
            this.b.c("Salesforce config flag is not enabled");
        } else if (!f.compareAndSet(false, true)) {
            this.b.c("Salesforce setup already started");
        } else {
            C0159a aVar = new C0159a();
            com.salesforce.marketingcloud.b.a c2 = com.salesforce.marketingcloud.b.a().a(com.etsy.android.util.b.d).b(com.etsy.android.util.b.e).a(false).b(false).a((com.salesforce.marketingcloud.notifications.c.a) new C0084c(this)).a((C0173c) new d(this)).d(bOEApplication.getString(R.string.etsy)).c(bOEApplication.getString(R.string.gcm_defaultSenderId));
            com.salesforce.marketingcloud.c.a(6);
            com.salesforce.marketingcloud.c.a((com.salesforce.marketingcloud.a) aVar);
            com.salesforce.marketingcloud.c.a(bOEApplication, c2.b(), new b(this, bOEApplication));
            c();
        }
    }

    /* access modifiers changed from: private */
    public final void a(InitializationStatus initializationStatus, Context context) {
        int f2 = initializationStatus.f();
        boolean isUserResolvableError = this.e.isUserResolvableError(f2);
        if (!(f2 == 0) && isUserResolvableError) {
            this.e.showErrorNotification(context, f2);
        }
    }

    private final void c() {
        g b2 = this.c.a().b();
        p.a((Object) b2, "userIdStream.userIdFlowa…  .distinctUntilChanged()");
        io.reactivex.rxkotlin.c.a(b2, (kotlin.jvm.a.b) new SalesforceNotificationInitializer$subscribeToUserChanges$2(this), (kotlin.jvm.a.a) null, SalesforceNotificationInitializer$subscribeToUserChanges$1.INSTANCE, 2, (Object) null);
    }
}
