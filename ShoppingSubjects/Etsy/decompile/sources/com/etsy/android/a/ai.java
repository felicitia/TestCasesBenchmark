package com.etsy.android.a;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.BOEApplication;
import com.etsy.android.a.a.C0018a;
import com.etsy.android.a.a.C0018a.C0019a;
import com.etsy.android.a.b.a.C0021a;
import com.etsy.android.a.c.a.C0022a;
import com.etsy.android.a.d.a.C0023a;
import com.etsy.android.a.e.a.C0024a;
import com.etsy.android.a.f.a.C0025a;
import com.etsy.android.a.g.a.C0026a;
import com.etsy.android.a.h.a.C0027a;
import com.etsy.android.a.i.a.C0028a;
import com.etsy.android.a.j.a.C0029a;
import com.etsy.android.a.k.a.C0030a;
import com.etsy.android.a.l.a.C0031a;
import com.etsy.android.a.m.a.C0032a;
import com.etsy.android.a.n.a.C0033a;
import com.etsy.android.a.o.a.C0034a;
import com.etsy.android.a.p.a.C0035a;
import com.etsy.android.a.q.a.C0036a;
import com.etsy.android.a.r.a.C0037a;
import com.etsy.android.a.s.a.C0038a;
import com.etsy.android.c.a.C0039a;
import com.etsy.android.c.a.C0039a.C0040a;
import com.etsy.android.c.b.a.C0041a;
import com.etsy.android.d.c.a.C0045a;
import com.etsy.android.e.b.a.C0046a;
import com.etsy.android.f.a.C0047a;
import com.etsy.android.f.a.C0047a.C0048a;
import com.etsy.android.f.b.a.C0049a;
import com.etsy.android.f.c.a.C0050a;
import com.etsy.android.lib.a.p.a.C0052a;
import com.etsy.android.lib.auth.external.ExternalAccountDelegate;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.AnalyticsLogDatabaseHelper;
import com.etsy.android.lib.logger.elk.ElkLogDatabase;
import com.etsy.android.lib.messaging.InstanceIdService;
import com.etsy.android.lib.models.ConversationUser;
import com.etsy.android.lib.models.apiv3.Feedback;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.apiv3.ShippingDetailsEndpoint;
import com.etsy.android.lib.requests.apiv3.SuggestUsernameEndpoint;
import com.etsy.android.lib.requests.apiv3.UserEndpoint;
import com.etsy.android.lib.shophome.vertical.BaseShopHomeFragment;
import com.etsy.android.lib.util.CameraHelper;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.messaging.EtsyGcmListenerService;
import com.etsy.android.messaging.c.a.C0082a;
import com.etsy.android.ui.cart.CartWithSavedActivity;
import com.etsy.android.ui.cart.SavedCartItemsFragment;
import com.etsy.android.ui.convos.ConvoBaseActivity;
import com.etsy.android.ui.convos.ConvoComposeActivity;
import com.etsy.android.ui.convos.ConvoComposeDialogActivity;
import com.etsy.android.ui.convos.ConvoComposeFragment;
import com.etsy.android.ui.convos.ConvoThreadFragment;
import com.etsy.android.ui.convos.ConvoViewActivity;
import com.etsy.android.ui.convos.ManufacturerProjectActivity;
import com.etsy.android.ui.convos.ManufacturerProjectFragment;
import com.etsy.android.ui.convos.convolistredesign.ConvoListActivity;
import com.etsy.android.ui.convos.convolistredesign.ConvosListFragment;
import com.etsy.android.ui.convos.convolistredesign.f.a.C0090a;
import com.etsy.android.ui.convos.convoredesign.ConvoActivity;
import com.etsy.android.ui.convos.convoredesign.ConvoDatabase;
import com.etsy.android.ui.convos.convoredesign.ConvoThreadFragment2;
import com.etsy.android.ui.convos.convoredesign.w.a.C0092a;
import com.etsy.android.ui.convos.e.a.C0093a;
import com.etsy.android.ui.convos.k.a.C0095a;
import com.etsy.android.ui.core.CoreActivity;
import com.etsy.android.ui.core.DetailedImageActivity;
import com.etsy.android.ui.core.DetailedImageFragment;
import com.etsy.android.ui.core.ListingFragment;
import com.etsy.android.ui.feedback.FeedbackActivity;
import com.etsy.android.ui.giftcards.GiftCardCreateActivity;
import com.etsy.android.ui.homescreen.HomescreenTabsActivity;
import com.etsy.android.ui.nav.NotificationActivity;
import com.etsy.android.ui.search.v2.SearchResultsListingsFragment;
import com.etsy.android.ui.search.v2.SearchV2Activity;
import com.etsy.android.ui.search.v2.a.f.a.C0099a;
import com.etsy.android.ui.search.v2.impressions.SearchImpressionsDatabase;
import com.etsy.android.ui.shop.ShopHomeFragment;
import com.etsy.android.ui.shophome.SectionedShopHomeFragment;
import com.etsy.android.ui.shophome.ShopHomeActivity;
import com.etsy.android.ui.user.PhabletsActivity;
import com.etsy.android.ui.user.PhabletsFragment;
import com.etsy.android.ui.user.ReceiptFragment;
import com.etsy.android.ui.user.SettingsActivity;
import com.etsy.android.ui.user.UserSettingsFragment;
import com.etsy.android.ui.user.auth.RegisterFragment;
import com.etsy.android.ui.user.auth.SignInActivity;
import com.etsy.android.ui.user.auth.SignInFragment;
import com.etsy.android.ui.user.auth.SignInNagFragment;
import com.etsy.android.ui.user.auth.SignInTwoFactorFragment;
import com.etsy.android.ui.user.auth.r.a.C0101a;
import com.etsy.android.ui.user.auth.s.a.C0102a;
import com.etsy.android.ui.user.auth.t.a.C0103a;
import com.etsy.android.ui.user.auth.u.a.C0104a;
import com.etsy.android.ui.user.b.a.C0105a;
import com.etsy.android.ui.user.f.a.C0106a;
import com.etsy.android.ui.user.j.a.C0107a;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.util.AppLifecycleObserver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.google.android.gms.common.GoogleApiAvailability;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.b.C0117b;
import java.util.Map;
import okhttp3.logging.HttpLoggingInterceptor.Level;

/* compiled from: DaggerAppComponent */
public final class ai implements t {
    static final /* synthetic */ boolean a = true;
    private javax.a.a<C0117b<? extends Activity>> A;
    private javax.a.a<C0032a> B;
    private javax.a.a<C0117b<? extends Activity>> C;
    private javax.a.a<C0028a> D;
    private javax.a.a<C0117b<? extends Activity>> E;
    private javax.a.a<C0029a> F;
    private javax.a.a<C0117b<? extends Activity>> G;
    private javax.a.a<C0030a> H;
    private javax.a.a<C0117b<? extends Activity>> I;
    private javax.a.a<C0031a> J;
    private javax.a.a<C0117b<? extends Activity>> K;
    private javax.a.a<C0027a> L;
    private javax.a.a<C0117b<? extends Activity>> M;
    private javax.a.a<C0037a> N;
    private javax.a.a<C0117b<? extends Activity>> O;
    private javax.a.a<Map<Class<? extends Activity>, javax.a.a<C0117b<? extends Activity>>>> P;
    private javax.a.a<DispatchingAndroidInjector<Activity>> Q;
    private javax.a.a<C0052a> R;
    private javax.a.a<C0117b<? extends Service>> S;
    private javax.a.a<C0082a> T;
    private javax.a.a<C0117b<? extends Service>> U;
    private javax.a.a<Map<Class<? extends Service>, javax.a.a<C0117b<? extends Service>>>> V;
    private javax.a.a<DispatchingAndroidInjector<Service>> W;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.lib.logger.l> X;
    private javax.a.a<GoogleApiAvailability> Y;
    private javax.a.a<com.etsy.android.lib.e.a> Z;
    private javax.a.a<com.etsy.android.lib.logger.elk.e> aA;
    private javax.a.a<retrofit2.a.a.a> aB;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.lib.c.d> aC;
    private javax.a.a<com.etsy.android.lib.logger.elk.uploading.b> aD;
    private javax.a.a<AnalyticsLogDatabaseHelper> aE;
    private javax.a.a<com.etsy.android.lib.c.f> aF;
    private javax.a.a<com.etsy.android.lib.c.h> aG;
    private javax.a.a<com.etsy.android.lib.logger.analytics.a> aH;
    private javax.a.a<com.etsy.android.lib.logger.analytics.f> aI;
    private javax.a.a<com.etsy.android.lib.logger.analytics.g> aJ;
    private javax.a.a<FirebaseJobDispatcher> aK;
    /* access modifiers changed from: private */
    public javax.a.a<androidx.work.j> aL;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.deeplinking.a> aM;
    private javax.a.a<com.etsy.android.lib.logger.a.c> aN;
    private javax.a.a<com.etsy.android.lib.logger.r> aO;
    private javax.a.a<com.etsy.android.lib.util.sharedprefs.d> aP;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.lib.util.b.a> aQ;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.lib.util.sharedprefs.b> aR;
    private javax.a.a<com.etsy.android.messaging.salesforce.a> aS;
    private javax.a.a<com.etsy.android.messaging.salesforce.c> aT;
    private javax.a.a<AppLifecycleObserver> aU;
    private javax.a.a<SearchImpressionsDatabase> aV;
    private javax.a.a<com.etsy.android.ui.search.v2.impressions.a> aW;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.lib.c.e> aX;
    private javax.a.a<com.etsy.android.ui.search.v2.impressions.f> aY;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.ui.search.v2.impressions.d> aZ;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.lib.b.a> aa;
    private javax.a.a<com.etsy.android.lib.core.q> ab;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.lib.h.a> ac;
    private javax.a.a<EtsyApplication> ad;
    private javax.a.a<com.etsy.android.lib.util.ag> ae;
    private javax.a.a<com.etsy.android.lib.config.g> af;
    private javax.a.a<com.etsy.android.lib.config.a> ag;
    private javax.a.a<ElkLogDatabase> ah;
    private javax.a.a<com.etsy.android.lib.logger.elk.b> ai;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.lib.logger.elk.f> aj;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.lib.logger.elk.a.a> ak;
    private javax.a.a<Level> al;
    private javax.a.a<com.etsy.android.lib.c.j> am;
    private javax.a.a<com.etsy.android.lib.c.c> an;
    private javax.a.a<retrofit2.a.b.a> ao;
    private javax.a.a<com.etsy.android.lib.config.c> ap;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.lib.c.b> aq;
    private javax.a.a<com.etsy.android.lib.push.g> ar;
    private javax.a.a<com.etsy.android.lib.push.j> as;
    private javax.a.a<com.google.android.gms.iid.a> at;
    private javax.a.a<com.etsy.android.lib.push.b> au;
    private javax.a.a<com.etsy.android.lib.push.d> av;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.lib.push.f> aw;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.util.f> ax;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.lib.core.v> ay;
    private javax.a.a<com.etsy.android.lib.logger.legacy.b> az;
    private javax.a.a<BOEApplication> b;
    private dagger.a<BOEApplication> ba;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.lib.auth.b> bb;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.lib.c.g> bc;
    private javax.a.a<ConvoDatabase> bd;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.ui.convos.convoredesign.f> be;
    /* access modifiers changed from: private */
    public javax.a.a<com.etsy.android.messaging.h> bf;
    /* access modifiers changed from: private */
    public javax.a.a<NetworkUtils> bg;
    /* access modifiers changed from: private */
    public javax.a.a<CameraHelper> bh;
    /* access modifiers changed from: private */
    public javax.a.a<Context> c;
    private javax.a.a<C0026a> d;
    private javax.a.a<C0117b<? extends Activity>> e;
    private javax.a.a<C0021a> f;
    private javax.a.a<C0117b<? extends Activity>> g;
    private javax.a.a<C0024a> h;
    private javax.a.a<C0117b<? extends Activity>> i;
    private javax.a.a<C0019a> j;
    private javax.a.a<C0117b<? extends Activity>> k;
    private javax.a.a<C0022a> l;
    private javax.a.a<C0117b<? extends Activity>> m;
    private javax.a.a<C0025a> n;
    private javax.a.a<C0117b<? extends Activity>> o;
    private javax.a.a<C0023a> p;
    private javax.a.a<C0117b<? extends Activity>> q;
    private javax.a.a<C0033a> r;
    private javax.a.a<C0117b<? extends Activity>> s;
    private javax.a.a<C0036a> t;
    private javax.a.a<C0117b<? extends Activity>> u;
    private javax.a.a<C0034a> v;
    private javax.a.a<C0117b<? extends Activity>> w;
    private javax.a.a<C0038a> x;
    private javax.a.a<C0117b<? extends Activity>> y;
    private javax.a.a<C0035a> z;

    /* compiled from: DaggerAppComponent */
    private static final class a implements com.etsy.android.a.t.a {
        /* access modifiers changed from: private */
        public v a;
        /* access modifiers changed from: private */
        public com.etsy.android.lib.logger.elk.b.a b;
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.e c;
        /* access modifiers changed from: private */
        public com.etsy.android.lib.logger.analytics.b d;
        /* access modifiers changed from: private */
        public com.etsy.android.lib.logger.o e;
        /* access modifiers changed from: private */
        public com.etsy.android.ui.search.v2.a.a f;
        /* access modifiers changed from: private */
        public com.etsy.android.ui.search.v2.a.d g;
        /* access modifiers changed from: private */
        public com.etsy.android.lib.util.c h;
        /* access modifiers changed from: private */
        public BOEApplication i;

        private a() {
        }

        public t a() {
            if (this.a == null) {
                this.a = new v();
            }
            if (this.b == null) {
                this.b = new com.etsy.android.lib.logger.elk.b.a();
            }
            if (this.c == null) {
                this.c = new com.etsy.android.lib.a.e();
            }
            if (this.d == null) {
                this.d = new com.etsy.android.lib.logger.analytics.b();
            }
            if (this.e == null) {
                this.e = new com.etsy.android.lib.logger.o();
            }
            if (this.f == null) {
                this.f = new com.etsy.android.ui.search.v2.a.a();
            }
            if (this.g == null) {
                this.g = new com.etsy.android.ui.search.v2.a.d();
            }
            if (this.h == null) {
                this.h = new com.etsy.android.lib.util.c();
            }
            if (this.i != null) {
                return new ai(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(BOEApplication.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        /* renamed from: b */
        public a a(BOEApplication bOEApplication) {
            this.i = (BOEApplication) dagger.internal.f.a(bOEApplication);
            return this;
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class aa implements com.etsy.android.a.d.a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> c;
        private javax.a.a<HomescreenTabsActivity> d;
        private javax.a.a<TrackingBaseActivity> e;
        private javax.a.a<com.etsy.android.lib.logger.w> f;
        private dagger.a<HomescreenTabsActivity> g;

        static {
            Class<ai> cls = ai.class;
        }

        private aa(z zVar) {
            if (a || zVar != null) {
                a(zVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(z zVar) {
            this.c = dagger.android.c.a(dagger.internal.e.a());
            this.d = dagger.internal.d.a(zVar.c);
            this.e = this.d;
            this.f = com.etsy.android.lib.a.r.a(zVar.b, this.e);
            this.g = com.etsy.android.ui.homescreen.a.a(this.c, this.f, ai.this.ax, ai.this.ak, ai.this.ay, ai.this.aM);
        }

        public void a(HomescreenTabsActivity homescreenTabsActivity) {
            this.g.injectMembers(homescreenTabsActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class ab extends C0052a {
        private InstanceIdService b;

        private ab() {
        }

        /* renamed from: a */
        public com.etsy.android.lib.a.p.a b() {
            if (this.b != null) {
                return new ac(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(InstanceIdService.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(InstanceIdService instanceIdService) {
            this.b = (InstanceIdService) dagger.internal.f.a(instanceIdService);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class ac implements com.etsy.android.lib.a.p.a {
        static final /* synthetic */ boolean a = true;
        private dagger.a<InstanceIdService> c;

        static {
            Class<ai> cls = ai.class;
        }

        private ac(ab abVar) {
            if (a || abVar != null) {
                a(abVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(ab abVar) {
            this.c = com.etsy.android.lib.messaging.e.a(ai.this.aw, ai.this.ak, ai.this.aa);
        }

        public void a(InstanceIdService instanceIdService) {
            this.c.injectMembers(instanceIdService);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class ad extends C0035a {
        /* access modifiers changed from: private */
        public com.etsy.android.d.a b;
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q c;
        /* access modifiers changed from: private */
        public ManufacturerProjectActivity d;

        private ad() {
        }

        /* renamed from: a */
        public com.etsy.android.a.p.a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.d.a();
            }
            if (this.c == null) {
                this.c = new com.etsy.android.lib.a.q();
            }
            if (this.d != null) {
                return new ae(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(ManufacturerProjectActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(ManufacturerProjectActivity manufacturerProjectActivity) {
            this.d = (ManufacturerProjectActivity) dagger.internal.f.a(manufacturerProjectActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class ae implements com.etsy.android.a.p.a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<C0045a> c;
        private javax.a.a<C0117b<? extends Fragment>> d;
        private javax.a.a<Map<Class<? extends Fragment>, javax.a.a<C0117b<? extends Fragment>>>> e;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> f;
        private javax.a.a<ManufacturerProjectActivity> g;
        private javax.a.a<TrackingBaseActivity> h;
        private javax.a.a<com.etsy.android.lib.logger.w> i;
        private dagger.a<ManufacturerProjectActivity> j;

        /* compiled from: DaggerAppComponent */
        private final class a extends C0045a {
            private ManufacturerProjectFragment b;

            private a() {
            }

            /* renamed from: a */
            public com.etsy.android.d.c.a b() {
                if (this.b != null) {
                    return new b(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(ManufacturerProjectFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(ManufacturerProjectFragment manufacturerProjectFragment) {
                this.b = (ManufacturerProjectFragment) dagger.internal.f.a(manufacturerProjectFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class b implements com.etsy.android.d.c.a {
            static final /* synthetic */ boolean a = true;
            private dagger.a<ManufacturerProjectFragment> c;

            static {
                Class<ai> cls = ai.class;
            }

            private b(a aVar) {
                if (a || aVar != null) {
                    a(aVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(a aVar) {
                this.c = com.etsy.android.ui.convos.o.a(ai.this.aQ);
            }

            public void a(ManufacturerProjectFragment manufacturerProjectFragment) {
                this.c.injectMembers(manufacturerProjectFragment);
            }
        }

        static {
            Class<ai> cls = ai.class;
        }

        private ae(ad adVar) {
            if (a || adVar != null) {
                a(adVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(ad adVar) {
            this.c = new dagger.internal.c<C0045a>() {
                /* renamed from: a */
                public C0045a b() {
                    return new a();
                }
            };
            this.d = this.c;
            this.e = dagger.internal.e.a(1).a(ManufacturerProjectFragment.class, this.d).a();
            this.f = dagger.android.c.a(this.e);
            this.g = dagger.internal.d.a(adVar.d);
            this.h = com.etsy.android.d.b.a(adVar.b, this.g);
            this.i = com.etsy.android.lib.a.r.a(adVar.c, this.h);
            this.j = com.etsy.android.ui.convos.n.a(this.f, this.i);
        }

        public void a(ManufacturerProjectActivity manufacturerProjectActivity) {
            this.j.injectMembers(manufacturerProjectActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class af extends C0024a {
        private NotificationActivity b;

        private af() {
        }

        /* renamed from: a */
        public com.etsy.android.a.e.a b() {
            if (this.b != null) {
                return new ag(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(NotificationActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(NotificationActivity notificationActivity) {
            this.b = (NotificationActivity) dagger.internal.f.a(notificationActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class ag implements com.etsy.android.a.e.a {
        static final /* synthetic */ boolean a = true;
        private dagger.a<NotificationActivity> c;

        static {
            Class<ai> cls = ai.class;
        }

        private ag(af afVar) {
            if (a || afVar != null) {
                a(afVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(af afVar) {
            this.c = com.etsy.android.ui.nav.i.a(ai.this.X, ai.this.aM);
        }

        public void a(NotificationActivity notificationActivity) {
            this.c.injectMembers(notificationActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class ah extends C0036a {
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q b;
        /* access modifiers changed from: private */
        public PhabletsActivity c;

        private ah() {
        }

        /* renamed from: a */
        public com.etsy.android.a.q.a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.lib.a.q();
            }
            if (this.c != null) {
                return new C0020ai(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(PhabletsActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(PhabletsActivity phabletsActivity) {
            this.c = (PhabletsActivity) dagger.internal.f.a(phabletsActivity);
        }
    }

    /* renamed from: com.etsy.android.a.ai$ai reason: collision with other inner class name */
    /* compiled from: DaggerAppComponent */
    private final class C0020ai implements com.etsy.android.a.q.a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<C0105a> c;
        private javax.a.a<C0117b<? extends Fragment>> d;
        private javax.a.a<Map<Class<? extends Fragment>, javax.a.a<C0117b<? extends Fragment>>>> e;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> f;
        private javax.a.a<PhabletsActivity> g;
        private javax.a.a<TrackingBaseActivity> h;
        private javax.a.a<com.etsy.android.lib.logger.w> i;
        private dagger.a<PhabletsActivity> j;

        /* renamed from: com.etsy.android.a.ai$ai$a */
        /* compiled from: DaggerAppComponent */
        private final class a extends C0105a {
            private PhabletsFragment b;

            private a() {
            }

            /* renamed from: a */
            public com.etsy.android.ui.user.b.a b() {
                if (this.b != null) {
                    return new b(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(PhabletsFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(PhabletsFragment phabletsFragment) {
                this.b = (PhabletsFragment) dagger.internal.f.a(phabletsFragment);
            }
        }

        /* renamed from: com.etsy.android.a.ai$ai$b */
        /* compiled from: DaggerAppComponent */
        private final class b implements com.etsy.android.ui.user.b.a {
            static final /* synthetic */ boolean a = true;
            private dagger.a<PhabletsFragment> c;

            static {
                Class<ai> cls = ai.class;
            }

            private b(a aVar) {
                if (a || aVar != null) {
                    a(aVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(a aVar) {
                this.c = com.etsy.android.ui.user.c.a(ai.this.X);
            }

            public void a(PhabletsFragment phabletsFragment) {
                this.c.injectMembers(phabletsFragment);
            }
        }

        static {
            Class<ai> cls = ai.class;
        }

        private C0020ai(ah ahVar) {
            if (a || ahVar != null) {
                a(ahVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(ah ahVar) {
            this.c = new dagger.internal.c<C0105a>() {
                /* renamed from: a */
                public C0105a b() {
                    return new a();
                }
            };
            this.d = this.c;
            this.e = dagger.internal.e.a(1).a(PhabletsFragment.class, this.d).a();
            this.f = dagger.android.c.a(this.e);
            this.g = dagger.internal.d.a(ahVar.c);
            this.h = this.g;
            this.i = com.etsy.android.lib.a.r.a(ahVar.b, this.h);
            this.j = com.etsy.android.ui.user.a.a(this.f, this.i);
        }

        public void a(PhabletsActivity phabletsActivity) {
            this.j.injectMembers(phabletsActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class aj extends C0037a {
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q b;
        /* access modifiers changed from: private */
        public SearchV2Activity c;

        private aj() {
        }

        /* renamed from: a */
        public com.etsy.android.a.r.a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.lib.a.q();
            }
            if (this.c != null) {
                return new ak(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(SearchV2Activity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(SearchV2Activity searchV2Activity) {
            this.c = (SearchV2Activity) dagger.internal.f.a(searchV2Activity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class ak implements com.etsy.android.a.r.a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<C0099a> c;
        private javax.a.a<C0117b<? extends Fragment>> d;
        private javax.a.a<Map<Class<? extends Fragment>, javax.a.a<C0117b<? extends Fragment>>>> e;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> f;
        private javax.a.a<SearchV2Activity> g;
        private javax.a.a<TrackingBaseActivity> h;
        private javax.a.a<com.etsy.android.lib.logger.w> i;
        private dagger.a<SearchV2Activity> j;

        /* compiled from: DaggerAppComponent */
        private final class a extends C0099a {
            private SearchResultsListingsFragment b;

            private a() {
            }

            /* renamed from: a */
            public com.etsy.android.ui.search.v2.a.f.a b() {
                if (this.b != null) {
                    return new b(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(SearchResultsListingsFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(SearchResultsListingsFragment searchResultsListingsFragment) {
                this.b = (SearchResultsListingsFragment) dagger.internal.f.a(searchResultsListingsFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class b implements com.etsy.android.ui.search.v2.a.f.a {
            static final /* synthetic */ boolean a = true;
            private dagger.a<SearchResultsListingsFragment> c;

            static {
                Class<ai> cls = ai.class;
            }

            private b(a aVar) {
                if (a || aVar != null) {
                    a(aVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(a aVar) {
                this.c = com.etsy.android.ui.search.v2.k.a(ai.this.aZ, com.etsy.android.lib.f.b.c(), ai.this.X, ai.this.aL);
            }

            public void a(SearchResultsListingsFragment searchResultsListingsFragment) {
                this.c.injectMembers(searchResultsListingsFragment);
            }
        }

        static {
            Class<ai> cls = ai.class;
        }

        private ak(aj ajVar) {
            if (a || ajVar != null) {
                a(ajVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(aj ajVar) {
            this.c = new dagger.internal.c<C0099a>() {
                /* renamed from: a */
                public C0099a b() {
                    return new a();
                }
            };
            this.d = this.c;
            this.e = dagger.internal.e.a(1).a(SearchResultsListingsFragment.class, this.d).a();
            this.f = dagger.android.c.a(this.e);
            this.g = dagger.internal.d.a(ajVar.c);
            this.h = this.g;
            this.i = com.etsy.android.lib.a.r.a(ajVar.b, this.h);
            this.j = com.etsy.android.ui.search.v2.q.a(this.f, this.i);
        }

        public void a(SearchV2Activity searchV2Activity) {
            this.j.injectMembers(searchV2Activity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class al extends C0025a {
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q b;
        /* access modifiers changed from: private */
        public SettingsActivity c;

        private al() {
        }

        /* renamed from: a */
        public com.etsy.android.a.f.a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.lib.a.q();
            }
            if (this.c != null) {
                return new am(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(SettingsActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(SettingsActivity settingsActivity) {
            this.c = (SettingsActivity) dagger.internal.f.a(settingsActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class am implements com.etsy.android.a.f.a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<C0107a> c;
        private javax.a.a<C0117b<? extends Fragment>> d;
        private javax.a.a<Map<Class<? extends Fragment>, javax.a.a<C0117b<? extends Fragment>>>> e;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> f;
        private javax.a.a<SettingsActivity> g;
        private javax.a.a<TrackingBaseActivity> h;
        private javax.a.a<com.etsy.android.lib.logger.w> i;
        private dagger.a<SettingsActivity> j;

        /* compiled from: DaggerAppComponent */
        private final class a extends C0107a {
            private UserSettingsFragment b;

            private a() {
            }

            /* renamed from: a */
            public com.etsy.android.ui.user.j.a b() {
                if (this.b != null) {
                    return new b(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(UserSettingsFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(UserSettingsFragment userSettingsFragment) {
                this.b = (UserSettingsFragment) dagger.internal.f.a(userSettingsFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class b implements com.etsy.android.ui.user.j.a {
            static final /* synthetic */ boolean a = true;
            private dagger.a<UserSettingsFragment> c;

            static {
                Class<ai> cls = ai.class;
            }

            private b(a aVar) {
                if (a || aVar != null) {
                    a(aVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(a aVar) {
                this.c = com.etsy.android.ui.user.k.a(ai.this.aw);
            }

            public void a(UserSettingsFragment userSettingsFragment) {
                this.c.injectMembers(userSettingsFragment);
            }
        }

        static {
            Class<ai> cls = ai.class;
        }

        private am(al alVar) {
            if (a || alVar != null) {
                a(alVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(al alVar) {
            this.c = new dagger.internal.c<C0107a>() {
                /* renamed from: a */
                public C0107a b() {
                    return new a();
                }
            };
            this.d = this.c;
            this.e = dagger.internal.e.a(1).a(UserSettingsFragment.class, this.d).a();
            this.f = dagger.android.c.a(this.e);
            this.g = dagger.internal.d.a(alVar.c);
            this.h = this.g;
            this.i = com.etsy.android.lib.a.r.a(alVar.b, this.h);
            this.j = com.etsy.android.ui.user.h.a(this.f, this.i);
        }

        public void a(SettingsActivity settingsActivity) {
            this.j.injectMembers(settingsActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class an extends C0038a {
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q b;
        /* access modifiers changed from: private */
        public ShopHomeActivity c;

        private an() {
        }

        /* renamed from: a */
        public com.etsy.android.a.s.a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.lib.a.q();
            }
            if (this.c != null) {
                return new ao(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(ShopHomeActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(ShopHomeActivity shopHomeActivity) {
            this.c = (ShopHomeActivity) dagger.internal.f.a(shopHomeActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class ao implements com.etsy.android.a.s.a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<C0049a> c;
        private javax.a.a<C0117b<? extends Fragment>> d;
        private javax.a.a<Map<Class<? extends Fragment>, javax.a.a<C0117b<? extends Fragment>>>> e;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> f;
        private javax.a.a<ShopHomeActivity> g;
        private javax.a.a<TrackingBaseActivity> h;
        private javax.a.a<com.etsy.android.lib.logger.w> i;
        private dagger.a<ShopHomeActivity> j;

        /* compiled from: DaggerAppComponent */
        private final class a extends C0049a {
            private SectionedShopHomeFragment b;

            private a() {
            }

            /* renamed from: a */
            public com.etsy.android.f.b.a b() {
                if (this.b != null) {
                    return new b(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(SectionedShopHomeFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(SectionedShopHomeFragment sectionedShopHomeFragment) {
                this.b = (SectionedShopHomeFragment) dagger.internal.f.a(sectionedShopHomeFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class b implements com.etsy.android.f.b.a {
            static final /* synthetic */ boolean a = true;
            private dagger.a<SectionedShopHomeFragment> c;

            static {
                Class<ai> cls = ai.class;
            }

            private b(a aVar) {
                if (a || aVar != null) {
                    a(aVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(a aVar) {
                this.c = com.etsy.android.ui.shophome.a.a(ai.this.aQ);
            }

            public void a(SectionedShopHomeFragment sectionedShopHomeFragment) {
                this.c.injectMembers(sectionedShopHomeFragment);
            }
        }

        static {
            Class<ai> cls = ai.class;
        }

        private ao(an anVar) {
            if (a || anVar != null) {
                a(anVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(an anVar) {
            this.c = new dagger.internal.c<C0049a>() {
                /* renamed from: a */
                public C0049a b() {
                    return new a();
                }
            };
            this.d = this.c;
            this.e = dagger.internal.e.a(1).a(SectionedShopHomeFragment.class, this.d).a();
            this.f = dagger.android.c.a(this.e);
            this.g = dagger.internal.d.a(anVar.c);
            this.h = this.g;
            this.i = com.etsy.android.lib.a.r.a(anVar.b, this.h);
            this.j = com.etsy.android.ui.shophome.b.a(this.f, this.i);
        }

        public void a(ShopHomeActivity shopHomeActivity) {
            this.j.injectMembers(shopHomeActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class ap extends C0026a {
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q b;
        /* access modifiers changed from: private */
        public SignInActivity c;

        private ap() {
        }

        /* renamed from: a */
        public com.etsy.android.a.g.a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.lib.a.q();
            }
            if (this.c != null) {
                return new aq(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(SignInActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(SignInActivity signInActivity) {
            this.c = (SignInActivity) dagger.internal.f.a(signInActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class aq implements com.etsy.android.a.g.a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<C0101a> c;
        private javax.a.a<C0117b<? extends Fragment>> d;
        private javax.a.a<C0102a> e;
        private javax.a.a<C0117b<? extends Fragment>> f;
        private javax.a.a<C0103a> g;
        private javax.a.a<C0117b<? extends Fragment>> h;
        private javax.a.a<C0104a> i;
        private javax.a.a<C0117b<? extends Fragment>> j;
        private javax.a.a<Map<Class<? extends Fragment>, javax.a.a<C0117b<? extends Fragment>>>> k;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> l;
        private javax.a.a<SignInActivity> m;
        private javax.a.a<TrackingBaseActivity> n;
        private javax.a.a<com.etsy.android.lib.logger.w> o;
        private javax.a.a<FragmentActivity> p;
        private javax.a.a<ExternalAccountDelegate> q;
        private javax.a.a<com.etsy.android.lib.auth.m> r;
        /* access modifiers changed from: private */
        public javax.a.a<com.etsy.android.lib.auth.h> s;
        private dagger.a<SignInActivity> t;
        /* access modifiers changed from: private */
        public javax.a.a<com.etsy.android.ui.user.auth.a> u;
        /* access modifiers changed from: private */
        public javax.a.a<SuggestUsernameEndpoint> v;

        /* compiled from: DaggerAppComponent */
        private final class a extends C0101a {
            private RegisterFragment b;

            private a() {
            }

            /* renamed from: a */
            public com.etsy.android.ui.user.auth.r.a b() {
                if (this.b != null) {
                    return new b(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(RegisterFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(RegisterFragment registerFragment) {
                this.b = (RegisterFragment) dagger.internal.f.a(registerFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class b implements com.etsy.android.ui.user.auth.r.a {
            static final /* synthetic */ boolean a = true;
            private dagger.a<RegisterFragment> c;

            static {
                Class<ai> cls = ai.class;
            }

            private b(a aVar) {
                if (a || aVar != null) {
                    a(aVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(a aVar) {
                this.c = com.etsy.android.ui.user.auth.h.a(aq.this.u, aq.this.s, aq.this.v, com.etsy.android.lib.f.b.c(), ai.this.aj);
            }

            public void a(RegisterFragment registerFragment) {
                this.c.injectMembers(registerFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class c extends C0102a {
            private SignInFragment b;

            private c() {
            }

            /* renamed from: a */
            public com.etsy.android.ui.user.auth.s.a b() {
                if (this.b != null) {
                    return new d(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(SignInFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(SignInFragment signInFragment) {
                this.b = (SignInFragment) dagger.internal.f.a(signInFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class d implements com.etsy.android.ui.user.auth.s.a {
            static final /* synthetic */ boolean a = true;
            private dagger.a<SignInFragment> c;

            static {
                Class<ai> cls = ai.class;
            }

            private d(c cVar) {
                if (a || cVar != null) {
                    a(cVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(c cVar) {
                this.c = com.etsy.android.ui.user.auth.v.a(aq.this.u, aq.this.s);
            }

            public void a(SignInFragment signInFragment) {
                this.c.injectMembers(signInFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class e extends C0103a {
            private SignInNagFragment b;

            private e() {
            }

            /* renamed from: a */
            public com.etsy.android.ui.user.auth.t.a b() {
                if (this.b != null) {
                    return new f(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(SignInNagFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(SignInNagFragment signInNagFragment) {
                this.b = (SignInNagFragment) dagger.internal.f.a(signInNagFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class f implements com.etsy.android.ui.user.auth.t.a {
            static final /* synthetic */ boolean a = true;
            private dagger.a<SignInNagFragment> c;

            static {
                Class<ai> cls = ai.class;
            }

            private f(e eVar) {
                if (a || eVar != null) {
                    a(eVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(e eVar) {
                this.c = com.etsy.android.ui.user.auth.x.a(aq.this.u, aq.this.s);
            }

            public void a(SignInNagFragment signInNagFragment) {
                this.c.injectMembers(signInNagFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class g extends C0104a {
            private SignInTwoFactorFragment b;

            private g() {
            }

            /* renamed from: a */
            public com.etsy.android.ui.user.auth.u.a b() {
                if (this.b != null) {
                    return new h(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(SignInTwoFactorFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(SignInTwoFactorFragment signInTwoFactorFragment) {
                this.b = (SignInTwoFactorFragment) dagger.internal.f.a(signInTwoFactorFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class h implements com.etsy.android.ui.user.auth.u.a {
            static final /* synthetic */ boolean a = true;
            private dagger.a<SignInTwoFactorFragment> c;

            static {
                Class<ai> cls = ai.class;
            }

            private h(g gVar) {
                if (a || gVar != null) {
                    a(gVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(g gVar) {
                this.c = com.etsy.android.ui.user.auth.z.a(aq.this.u, aq.this.s);
            }

            public void a(SignInTwoFactorFragment signInTwoFactorFragment) {
                this.c.injectMembers(signInTwoFactorFragment);
            }
        }

        static {
            Class<ai> cls = ai.class;
        }

        private aq(ap apVar) {
            if (a || apVar != null) {
                a(apVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(ap apVar) {
            this.c = new dagger.internal.c<C0101a>() {
                /* renamed from: a */
                public C0101a b() {
                    return new a();
                }
            };
            this.d = this.c;
            this.e = new dagger.internal.c<C0102a>() {
                /* renamed from: a */
                public C0102a b() {
                    return new c();
                }
            };
            this.f = this.e;
            this.g = new dagger.internal.c<C0103a>() {
                /* renamed from: a */
                public C0103a b() {
                    return new e();
                }
            };
            this.h = this.g;
            this.i = new dagger.internal.c<C0104a>() {
                /* renamed from: a */
                public C0104a b() {
                    return new g();
                }
            };
            this.j = this.i;
            this.k = dagger.internal.e.a(4).a(RegisterFragment.class, this.d).a(SignInFragment.class, this.f).a(SignInNagFragment.class, this.h).a(SignInTwoFactorFragment.class, this.j).a();
            this.l = dagger.android.c.a(this.k);
            this.m = dagger.internal.d.a(apVar.c);
            this.n = this.m;
            this.o = com.etsy.android.lib.a.r.a(apVar.b, this.n);
            this.p = this.m;
            this.q = dagger.internal.b.a(com.etsy.android.lib.auth.external.b.a(this.p));
            this.r = com.etsy.android.ui.user.auth.l.a(this.m, ai.this.aw, ai.this.ac);
            this.s = com.etsy.android.lib.auth.i.a(ai.this.bb, this.o, this.q, this.r);
            this.t = com.etsy.android.ui.user.auth.n.a(this.l, this.o, this.s);
            this.u = com.etsy.android.ui.user.auth.k.a(this.m, ai.this.aw, ai.this.ac);
            this.v = com.etsy.android.ui.user.auth.m.a(ai.this.aX);
        }

        public void a(SignInActivity signInActivity) {
            this.t.injectMembers(signInActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class b extends C0019a {
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q b;
        /* access modifiers changed from: private */
        public CartWithSavedActivity c;

        private b() {
        }

        /* renamed from: a */
        public C0018a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.lib.a.q();
            }
            if (this.c != null) {
                return new c(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(CartWithSavedActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(CartWithSavedActivity cartWithSavedActivity) {
            this.c = (CartWithSavedActivity) dagger.internal.f.a(cartWithSavedActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class c implements C0018a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<C0046a> c;
        private javax.a.a<C0117b<? extends Fragment>> d;
        private javax.a.a<Map<Class<? extends Fragment>, javax.a.a<C0117b<? extends Fragment>>>> e;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> f;
        private javax.a.a<CartWithSavedActivity> g;
        private javax.a.a<TrackingBaseActivity> h;
        private javax.a.a<com.etsy.android.lib.logger.w> i;
        private dagger.a<CartWithSavedActivity> j;

        /* compiled from: DaggerAppComponent */
        private final class a extends C0046a {
            private SavedCartItemsFragment b;

            private a() {
            }

            /* renamed from: a */
            public com.etsy.android.e.b.a b() {
                if (this.b != null) {
                    return new b(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(SavedCartItemsFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(SavedCartItemsFragment savedCartItemsFragment) {
                this.b = (SavedCartItemsFragment) dagger.internal.f.a(savedCartItemsFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class b implements com.etsy.android.e.b.a {
            static final /* synthetic */ boolean a = true;
            private dagger.a<SavedCartItemsFragment> c;

            static {
                Class<ai> cls = ai.class;
            }

            private b(a aVar) {
                if (a || aVar != null) {
                    a(aVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(a aVar) {
                this.c = com.etsy.android.ui.cart.g.a(ai.this.bc);
            }

            public void a(SavedCartItemsFragment savedCartItemsFragment) {
                this.c.injectMembers(savedCartItemsFragment);
            }
        }

        static {
            Class<ai> cls = ai.class;
        }

        private c(b bVar) {
            if (a || bVar != null) {
                a(bVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(b bVar) {
            this.c = new dagger.internal.c<C0046a>() {
                /* renamed from: a */
                public C0046a b() {
                    return new a();
                }
            };
            this.d = this.c;
            this.e = dagger.internal.e.a(1).a(SavedCartItemsFragment.class, this.d).a();
            this.f = dagger.android.c.a(this.e);
            this.g = dagger.internal.d.a(bVar.c);
            this.h = this.g;
            this.i = com.etsy.android.lib.a.r.a(bVar.b, this.h);
            this.j = com.etsy.android.ui.cart.c.a(this.f, this.i);
        }

        public void a(CartWithSavedActivity cartWithSavedActivity) {
            this.j.injectMembers(cartWithSavedActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class d extends C0027a {
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q b;
        /* access modifiers changed from: private */
        public ConvoActivity c;

        private d() {
        }

        /* renamed from: a */
        public com.etsy.android.a.h.a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.lib.a.q();
            }
            if (this.c != null) {
                return new e(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(ConvoActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(ConvoActivity convoActivity) {
            this.c = (ConvoActivity) dagger.internal.f.a(convoActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class e implements com.etsy.android.a.h.a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<C0092a> c;
        private javax.a.a<C0117b<? extends Fragment>> d;
        private javax.a.a<Map<Class<? extends Fragment>, javax.a.a<C0117b<? extends Fragment>>>> e;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> f;
        /* access modifiers changed from: private */
        public javax.a.a<ConvoActivity> g;
        private javax.a.a<TrackingBaseActivity> h;
        private javax.a.a<com.etsy.android.lib.logger.w> i;
        private dagger.a<ConvoActivity> j;

        /* compiled from: DaggerAppComponent */
        private final class a extends C0092a {
            /* access modifiers changed from: private */
            public ConvoThreadFragment2 b;

            private a() {
            }

            /* renamed from: a */
            public com.etsy.android.ui.convos.convoredesign.w.a b() {
                if (this.b != null) {
                    return new b(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(ConvoThreadFragment2.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(ConvoThreadFragment2 convoThreadFragment2) {
                this.b = (ConvoThreadFragment2) dagger.internal.f.a(convoThreadFragment2);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class b implements com.etsy.android.ui.convos.convoredesign.w.a {
            static final /* synthetic */ boolean a = true;
            private javax.a.a<com.etsy.android.ui.convos.convoredesign.b> c;
            private javax.a.a<com.etsy.android.ui.convos.convoredesign.a> d;
            private javax.a.a<ConvoDatabase> e;
            private javax.a.a<com.etsy.android.ui.convos.convoredesign.f> f;
            private javax.a.a<com.etsy.android.ui.convos.convoredesign.c> g;
            private javax.a.a<ConvoThreadFragment2> h;
            private javax.a.a<com.etsy.android.ui.convos.convoredesign.ai> i;
            private javax.a.a<EtsyId> j;
            private javax.a.a<ConversationUser> k;
            private javax.a.a<com.etsy.android.ui.convos.convoredesign.t> l;
            private javax.a.a<com.etsy.android.ui.convos.convoredesign.af> m;
            private javax.a.a<com.etsy.android.ui.convos.convoredesign.ae> n;
            private dagger.a<ConvoThreadFragment2> o;

            static {
                Class<ai> cls = ai.class;
            }

            private b(a aVar) {
                if (a || aVar != null) {
                    a(aVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(a aVar) {
                this.c = com.etsy.android.ui.convos.convoredesign.z.a(ai.this.aX);
                this.d = com.etsy.android.ui.convos.convoredesign.y.a(ai.this.aq);
                this.e = com.etsy.android.ui.convos.convoredesign.k.a(ai.this.c);
                this.f = com.etsy.android.ui.convos.convoredesign.j.a(this.e);
                this.g = com.etsy.android.ui.convos.convoredesign.d.a(this.c, this.d, this.f);
                this.h = dagger.internal.d.a(aVar.b);
                this.i = com.etsy.android.ui.convos.convoredesign.ab.a(this.h);
                this.j = com.etsy.android.ui.convos.convoredesign.ad.a(this.h);
                this.k = com.etsy.android.ui.convos.convoredesign.ac.a(e.this.g);
                this.l = com.etsy.android.ui.convos.convoredesign.u.a(ai.this.bf);
                this.m = com.etsy.android.ui.convos.convoredesign.ah.a(this.g, this.i, com.etsy.android.lib.f.b.c(), this.j, this.k, ai.this.aQ, ai.this.bg, ai.this.aR, this.l);
                this.n = com.etsy.android.ui.convos.convoredesign.aa.a(this.m, this.h, ai.this.bh, ai.this.X);
                this.o = com.etsy.android.ui.convos.convoredesign.v.a(this.n, this.m, ai.this.X);
            }

            public void a(ConvoThreadFragment2 convoThreadFragment2) {
                this.o.injectMembers(convoThreadFragment2);
            }
        }

        static {
            Class<ai> cls = ai.class;
        }

        private e(d dVar) {
            if (a || dVar != null) {
                a(dVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(d dVar) {
            this.c = new dagger.internal.c<C0092a>() {
                /* renamed from: a */
                public C0092a b() {
                    return new a();
                }
            };
            this.d = this.c;
            this.e = dagger.internal.e.a(1).a(ConvoThreadFragment2.class, this.d).a();
            this.f = dagger.android.c.a(this.e);
            this.g = dagger.internal.d.a(dVar.c);
            this.h = this.g;
            this.i = com.etsy.android.lib.a.r.a(dVar.b, this.h);
            this.j = com.etsy.android.ui.convos.convoredesign.e.a(this.f, this.i);
        }

        public void a(ConvoActivity convoActivity) {
            this.j.injectMembers(convoActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class f extends C0028a {
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q b;
        /* access modifiers changed from: private */
        public ConvoBaseActivity c;

        private f() {
        }

        /* renamed from: a */
        public com.etsy.android.a.i.a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.lib.a.q();
            }
            if (this.c != null) {
                return new g(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(ConvoBaseActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(ConvoBaseActivity convoBaseActivity) {
            this.c = (ConvoBaseActivity) dagger.internal.f.a(convoBaseActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class g implements com.etsy.android.a.i.a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<C0095a> c;
        private javax.a.a<C0117b<? extends Fragment>> d;
        private javax.a.a<Map<Class<? extends Fragment>, javax.a.a<C0117b<? extends Fragment>>>> e;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> f;
        private javax.a.a<ConvoBaseActivity> g;
        private javax.a.a<TrackingBaseActivity> h;
        private javax.a.a<com.etsy.android.lib.logger.w> i;
        private dagger.a<ConvoBaseActivity> j;

        /* compiled from: DaggerAppComponent */
        private final class a extends C0095a {
            private ConvoThreadFragment b;

            private a() {
            }

            /* renamed from: a */
            public com.etsy.android.ui.convos.k.a b() {
                if (this.b != null) {
                    return new b(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(ConvoThreadFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(ConvoThreadFragment convoThreadFragment) {
                this.b = (ConvoThreadFragment) dagger.internal.f.a(convoThreadFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class b implements com.etsy.android.ui.convos.k.a {
            static final /* synthetic */ boolean a = true;
            private dagger.a<ConvoThreadFragment> c;

            static {
                Class<ai> cls = ai.class;
            }

            private b(a aVar) {
                if (a || aVar != null) {
                    a(aVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(a aVar) {
                this.c = com.etsy.android.ui.convos.l.a(ai.this.aQ);
            }

            public void a(ConvoThreadFragment convoThreadFragment) {
                this.c.injectMembers(convoThreadFragment);
            }
        }

        static {
            Class<ai> cls = ai.class;
        }

        private g(f fVar) {
            if (a || fVar != null) {
                a(fVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(f fVar) {
            this.c = new dagger.internal.c<C0095a>() {
                /* renamed from: a */
                public C0095a b() {
                    return new a();
                }
            };
            this.d = this.c;
            this.e = dagger.internal.e.a(1).a(ConvoThreadFragment.class, this.d).a();
            this.f = dagger.android.c.a(this.e);
            this.g = dagger.internal.d.a(fVar.c);
            this.h = this.g;
            this.i = com.etsy.android.lib.a.r.a(fVar.b, this.h);
            this.j = com.etsy.android.ui.convos.a.a(this.f, this.i);
        }

        public void a(ConvoBaseActivity convoBaseActivity) {
            this.j.injectMembers(convoBaseActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class h extends C0029a {
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q b;
        /* access modifiers changed from: private */
        public com.etsy.android.ui.convos.f c;
        /* access modifiers changed from: private */
        public ConvoComposeActivity d;

        private h() {
        }

        /* renamed from: a */
        public com.etsy.android.a.j.a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.lib.a.q();
            }
            if (this.c == null) {
                this.c = new com.etsy.android.ui.convos.f();
            }
            if (this.d != null) {
                return new i(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(ConvoComposeActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(ConvoComposeActivity convoComposeActivity) {
            this.d = (ConvoComposeActivity) dagger.internal.f.a(convoComposeActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class i implements com.etsy.android.a.j.a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<C0093a> c;
        private javax.a.a<C0117b<? extends Fragment>> d;
        private javax.a.a<Map<Class<? extends Fragment>, javax.a.a<C0117b<? extends Fragment>>>> e;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> f;
        private javax.a.a<ConvoComposeActivity> g;
        private javax.a.a<TrackingBaseActivity> h;
        private javax.a.a<com.etsy.android.lib.logger.w> i;
        private dagger.a<ConvoComposeActivity> j;
        /* access modifiers changed from: private */
        public final com.etsy.android.ui.convos.f k;

        /* compiled from: DaggerAppComponent */
        private final class a extends C0093a {
            private ConvoComposeFragment b;

            private a() {
            }

            /* renamed from: a */
            public com.etsy.android.ui.convos.e.a b() {
                if (this.b != null) {
                    return new b(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(ConvoComposeFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(ConvoComposeFragment convoComposeFragment) {
                this.b = (ConvoComposeFragment) dagger.internal.f.a(convoComposeFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class b implements com.etsy.android.ui.convos.e.a {
            static final /* synthetic */ boolean a = true;
            private javax.a.a<UserEndpoint> c;
            private javax.a.a<com.etsy.android.ui.convos.i> d;
            private dagger.a<ConvoComposeFragment> e;

            static {
                Class<ai> cls = ai.class;
            }

            private b(a aVar) {
                if (a || aVar != null) {
                    a(aVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(a aVar) {
                this.c = com.etsy.android.ui.convos.g.a(i.this.k, ai.this.aX);
                this.d = com.etsy.android.ui.convos.j.a(this.c);
                this.e = com.etsy.android.ui.convos.h.a(this.d, com.etsy.android.lib.f.b.c());
            }

            public void a(ConvoComposeFragment convoComposeFragment) {
                this.e.injectMembers(convoComposeFragment);
            }
        }

        static {
            Class<ai> cls = ai.class;
        }

        private i(h hVar) {
            if (a || hVar != null) {
                a(hVar);
                this.k = hVar.c;
                return;
            }
            throw new AssertionError();
        }

        private void a(h hVar) {
            this.c = new dagger.internal.c<C0093a>() {
                /* renamed from: a */
                public C0093a b() {
                    return new a();
                }
            };
            this.d = this.c;
            this.e = dagger.internal.e.a(1).a(ConvoComposeFragment.class, this.d).a();
            this.f = dagger.android.c.a(this.e);
            this.g = dagger.internal.d.a(hVar.d);
            this.h = this.g;
            this.i = com.etsy.android.lib.a.r.a(hVar.b, this.h);
            this.j = com.etsy.android.ui.convos.b.a(this.f, this.i);
        }

        public void a(ConvoComposeActivity convoComposeActivity) {
            this.j.injectMembers(convoComposeActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class j extends C0030a {
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q b;
        /* access modifiers changed from: private */
        public com.etsy.android.ui.convos.f c;
        /* access modifiers changed from: private */
        public ConvoComposeDialogActivity d;

        private j() {
        }

        /* renamed from: a */
        public com.etsy.android.a.k.a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.lib.a.q();
            }
            if (this.c == null) {
                this.c = new com.etsy.android.ui.convos.f();
            }
            if (this.d != null) {
                return new k(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(ConvoComposeDialogActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(ConvoComposeDialogActivity convoComposeDialogActivity) {
            this.d = (ConvoComposeDialogActivity) dagger.internal.f.a(convoComposeDialogActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class k implements com.etsy.android.a.k.a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<C0093a> c;
        private javax.a.a<C0117b<? extends Fragment>> d;
        private javax.a.a<Map<Class<? extends Fragment>, javax.a.a<C0117b<? extends Fragment>>>> e;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> f;
        private javax.a.a<ConvoComposeDialogActivity> g;
        private javax.a.a<TrackingBaseActivity> h;
        private javax.a.a<com.etsy.android.lib.logger.w> i;
        private dagger.a<ConvoComposeDialogActivity> j;
        /* access modifiers changed from: private */
        public final com.etsy.android.ui.convos.f k;

        /* compiled from: DaggerAppComponent */
        private final class a extends C0093a {
            private ConvoComposeFragment b;

            private a() {
            }

            /* renamed from: a */
            public com.etsy.android.ui.convos.e.a b() {
                if (this.b != null) {
                    return new b(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(ConvoComposeFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(ConvoComposeFragment convoComposeFragment) {
                this.b = (ConvoComposeFragment) dagger.internal.f.a(convoComposeFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class b implements com.etsy.android.ui.convos.e.a {
            static final /* synthetic */ boolean a = true;
            private javax.a.a<UserEndpoint> c;
            private javax.a.a<com.etsy.android.ui.convos.i> d;
            private dagger.a<ConvoComposeFragment> e;

            static {
                Class<ai> cls = ai.class;
            }

            private b(a aVar) {
                if (a || aVar != null) {
                    a(aVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(a aVar) {
                this.c = com.etsy.android.ui.convos.g.a(k.this.k, ai.this.aX);
                this.d = com.etsy.android.ui.convos.j.a(this.c);
                this.e = com.etsy.android.ui.convos.h.a(this.d, com.etsy.android.lib.f.b.c());
            }

            public void a(ConvoComposeFragment convoComposeFragment) {
                this.e.injectMembers(convoComposeFragment);
            }
        }

        static {
            Class<ai> cls = ai.class;
        }

        private k(j jVar) {
            if (a || jVar != null) {
                a(jVar);
                this.k = jVar.c;
                return;
            }
            throw new AssertionError();
        }

        private void a(j jVar) {
            this.c = new dagger.internal.c<C0093a>() {
                /* renamed from: a */
                public C0093a b() {
                    return new a();
                }
            };
            this.d = this.c;
            this.e = dagger.internal.e.a(1).a(ConvoComposeFragment.class, this.d).a();
            this.f = dagger.android.c.a(this.e);
            this.g = dagger.internal.d.a(jVar.d);
            this.h = this.g;
            this.i = com.etsy.android.lib.a.r.a(jVar.b, this.h);
            this.j = com.etsy.android.ui.convos.c.a(this.f, this.i);
        }

        public void a(ConvoComposeDialogActivity convoComposeDialogActivity) {
            this.j.injectMembers(convoComposeDialogActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class l extends C0031a {
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q b;
        /* access modifiers changed from: private */
        public ConvoListActivity c;

        private l() {
        }

        /* renamed from: a */
        public com.etsy.android.a.l.a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.lib.a.q();
            }
            if (this.c != null) {
                return new m(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(ConvoListActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(ConvoListActivity convoListActivity) {
            this.c = (ConvoListActivity) dagger.internal.f.a(convoListActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class m implements com.etsy.android.a.l.a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<C0090a> c;
        private javax.a.a<C0117b<? extends Fragment>> d;
        private javax.a.a<Map<Class<? extends Fragment>, javax.a.a<C0117b<? extends Fragment>>>> e;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> f;
        private javax.a.a<ConvoListActivity> g;
        private javax.a.a<TrackingBaseActivity> h;
        private javax.a.a<com.etsy.android.lib.logger.w> i;
        private dagger.a<ConvoListActivity> j;

        /* compiled from: DaggerAppComponent */
        private final class a extends C0090a {
            /* access modifiers changed from: private */
            public ConvosListFragment b;

            private a() {
            }

            /* renamed from: a */
            public com.etsy.android.ui.convos.convolistredesign.f.a b() {
                if (this.b != null) {
                    return new b(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(ConvosListFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(ConvosListFragment convosListFragment) {
                this.b = (ConvosListFragment) dagger.internal.f.a(convosListFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class b implements com.etsy.android.ui.convos.convolistredesign.f.a {
            static final /* synthetic */ boolean a = true;
            private javax.a.a<com.etsy.android.ui.convos.convolistredesign.b> c;
            private javax.a.a<ConvosListFragment> d;
            private javax.a.a<com.etsy.android.ui.convos.convolistredesign.o> e;
            private javax.a.a<com.etsy.android.ui.convos.convoredesign.t> f;
            private javax.a.a<com.etsy.android.ui.convos.convolistredesign.m> g;
            private dagger.a<ConvosListFragment> h;

            static {
                Class<ai> cls = ai.class;
            }

            private b(a aVar) {
                if (a || aVar != null) {
                    a(aVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(a aVar) {
                this.c = com.etsy.android.ui.convos.convolistredesign.h.a(ai.this.be, ai.this.aX, ai.this.aR);
                this.d = dagger.internal.d.a(aVar.b);
                this.e = com.etsy.android.ui.convos.convolistredesign.k.a(this.d);
                this.f = com.etsy.android.ui.convos.convolistredesign.i.a(ai.this.bf);
                this.g = dagger.internal.b.a(com.etsy.android.ui.convos.convolistredesign.j.a(this.c, this.e, com.etsy.android.lib.f.b.c(), this.f, ai.this.X));
                this.h = com.etsy.android.ui.convos.convolistredesign.l.a((javax.a.a) this.g);
            }

            public void a(ConvosListFragment convosListFragment) {
                this.h.injectMembers(convosListFragment);
            }
        }

        static {
            Class<ai> cls = ai.class;
        }

        private m(l lVar) {
            if (a || lVar != null) {
                a(lVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(l lVar) {
            this.c = new dagger.internal.c<C0090a>() {
                /* renamed from: a */
                public C0090a b() {
                    return new a();
                }
            };
            this.d = this.c;
            this.e = dagger.internal.e.a(1).a(ConvosListFragment.class, this.d).a();
            this.f = dagger.android.c.a(this.e);
            this.g = dagger.internal.d.a(lVar.c);
            this.h = this.g;
            this.i = com.etsy.android.lib.a.r.a(lVar.b, this.h);
            this.j = com.etsy.android.ui.convos.convolistredesign.d.a(this.f, this.i);
        }

        public void a(ConvoListActivity convoListActivity) {
            this.j.injectMembers(convoListActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class n extends C0032a {
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q b;
        /* access modifiers changed from: private */
        public ConvoViewActivity c;

        private n() {
        }

        /* renamed from: a */
        public com.etsy.android.a.m.a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.lib.a.q();
            }
            if (this.c != null) {
                return new o(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(ConvoViewActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(ConvoViewActivity convoViewActivity) {
            this.c = (ConvoViewActivity) dagger.internal.f.a(convoViewActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class o implements com.etsy.android.a.m.a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<C0095a> c;
        private javax.a.a<C0117b<? extends Fragment>> d;
        private javax.a.a<Map<Class<? extends Fragment>, javax.a.a<C0117b<? extends Fragment>>>> e;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> f;
        private javax.a.a<ConvoViewActivity> g;
        private javax.a.a<TrackingBaseActivity> h;
        private javax.a.a<com.etsy.android.lib.logger.w> i;
        private dagger.a<ConvoViewActivity> j;

        /* compiled from: DaggerAppComponent */
        private final class a extends C0095a {
            private ConvoThreadFragment b;

            private a() {
            }

            /* renamed from: a */
            public com.etsy.android.ui.convos.k.a b() {
                if (this.b != null) {
                    return new b(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(ConvoThreadFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(ConvoThreadFragment convoThreadFragment) {
                this.b = (ConvoThreadFragment) dagger.internal.f.a(convoThreadFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class b implements com.etsy.android.ui.convos.k.a {
            static final /* synthetic */ boolean a = true;
            private dagger.a<ConvoThreadFragment> c;

            static {
                Class<ai> cls = ai.class;
            }

            private b(a aVar) {
                if (a || aVar != null) {
                    a(aVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(a aVar) {
                this.c = com.etsy.android.ui.convos.l.a(ai.this.aQ);
            }

            public void a(ConvoThreadFragment convoThreadFragment) {
                this.c.injectMembers(convoThreadFragment);
            }
        }

        static {
            Class<ai> cls = ai.class;
        }

        private o(n nVar) {
            if (a || nVar != null) {
                a(nVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(n nVar) {
            this.c = new dagger.internal.c<C0095a>() {
                /* renamed from: a */
                public C0095a b() {
                    return new a();
                }
            };
            this.d = this.c;
            this.e = dagger.internal.e.a(1).a(ConvoThreadFragment.class, this.d).a();
            this.f = dagger.android.c.a(this.e);
            this.g = dagger.internal.d.a(nVar.c);
            this.h = this.g;
            this.i = com.etsy.android.lib.a.r.a(nVar.b, this.h);
            this.j = com.etsy.android.ui.convos.m.a(this.f, this.i);
        }

        public void a(ConvoViewActivity convoViewActivity) {
            this.j.injectMembers(convoViewActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class p extends C0033a {
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q b;
        /* access modifiers changed from: private */
        public CoreActivity c;

        private p() {
        }

        /* renamed from: a */
        public com.etsy.android.a.n.a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.lib.a.q();
            }
            if (this.c != null) {
                return new q(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(CoreActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(CoreActivity coreActivity) {
            this.c = (CoreActivity) dagger.internal.f.a(coreActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class q implements com.etsy.android.a.n.a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<C0041a> c;
        private javax.a.a<C0117b<? extends Fragment>> d;
        private javax.a.a<C0050a> e;
        private javax.a.a<C0117b<? extends Fragment>> f;
        private javax.a.a<C0048a> g;
        private javax.a.a<C0117b<? extends Fragment>> h;
        private javax.a.a<C0106a> i;
        private javax.a.a<C0117b<? extends Fragment>> j;
        private javax.a.a<Map<Class<? extends Fragment>, javax.a.a<C0117b<? extends Fragment>>>> k;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> l;
        private javax.a.a<CoreActivity> m;
        private javax.a.a<TrackingBaseActivity> n;
        private javax.a.a<com.etsy.android.lib.logger.w> o;
        private dagger.a<CoreActivity> p;

        /* compiled from: DaggerAppComponent */
        private final class a extends C0048a {
            private BaseShopHomeFragment b;

            private a() {
            }

            /* renamed from: a */
            public C0047a b() {
                if (this.b != null) {
                    return new b(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(BaseShopHomeFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(BaseShopHomeFragment baseShopHomeFragment) {
                this.b = (BaseShopHomeFragment) dagger.internal.f.a(baseShopHomeFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class b implements C0047a {
            static final /* synthetic */ boolean a = true;
            private dagger.a<BaseShopHomeFragment> c;

            static {
                Class<ai> cls = ai.class;
            }

            private b(a aVar) {
                if (a || aVar != null) {
                    a(aVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(a aVar) {
                this.c = com.etsy.android.lib.shophome.vertical.a.a(ai.this.aQ);
            }

            public void a(BaseShopHomeFragment baseShopHomeFragment) {
                this.c.injectMembers(baseShopHomeFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class c extends C0041a {
            /* access modifiers changed from: private */
            public com.etsy.android.c.c b;
            private ListingFragment c;

            private c() {
            }

            /* renamed from: a */
            public com.etsy.android.c.b.a b() {
                if (this.b == null) {
                    this.b = new com.etsy.android.c.c();
                }
                if (this.c != null) {
                    return new d(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(ListingFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(ListingFragment listingFragment) {
                this.c = (ListingFragment) dagger.internal.f.a(listingFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class d implements com.etsy.android.c.b.a {
            static final /* synthetic */ boolean a = true;
            private javax.a.a<ShippingDetailsEndpoint> c;
            private javax.a.a<com.etsy.android.ui.core.i> d;
            private dagger.a<ListingFragment> e;

            static {
                Class<ai> cls = ai.class;
            }

            private d(c cVar) {
                if (a || cVar != null) {
                    a(cVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(c cVar) {
                this.c = com.etsy.android.c.d.a(cVar.b, ai.this.aX);
                this.d = com.etsy.android.ui.core.j.a(this.c, ai.this.ay);
                this.e = com.etsy.android.ui.core.h.a(ai.this.aQ, this.d, ai.this.aR, com.etsy.android.lib.f.b.c());
            }

            public void a(ListingFragment listingFragment) {
                this.e.injectMembers(listingFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class e extends C0106a {
            private ReceiptFragment b;

            private e() {
            }

            /* renamed from: a */
            public com.etsy.android.ui.user.f.a b() {
                if (this.b != null) {
                    return new f(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(ReceiptFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(ReceiptFragment receiptFragment) {
                this.b = (ReceiptFragment) dagger.internal.f.a(receiptFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class f implements com.etsy.android.ui.user.f.a {
            static final /* synthetic */ boolean a = true;
            private dagger.a<ReceiptFragment> c;

            static {
                Class<ai> cls = ai.class;
            }

            private f(e eVar) {
                if (a || eVar != null) {
                    a(eVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(e eVar) {
                this.c = com.etsy.android.ui.user.g.a(ai.this.aX, com.etsy.android.lib.f.b.c(), ai.this.X);
            }

            public void a(ReceiptFragment receiptFragment) {
                this.c.injectMembers(receiptFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class g extends C0050a {
            private ShopHomeFragment b;

            private g() {
            }

            /* renamed from: a */
            public com.etsy.android.f.c.a b() {
                if (this.b != null) {
                    return new h(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(ShopHomeFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(ShopHomeFragment shopHomeFragment) {
                this.b = (ShopHomeFragment) dagger.internal.f.a(shopHomeFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class h implements com.etsy.android.f.c.a {
            static final /* synthetic */ boolean a = true;
            private dagger.a<ShopHomeFragment> c;

            static {
                Class<ai> cls = ai.class;
            }

            private h(g gVar) {
                if (a || gVar != null) {
                    a(gVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(g gVar) {
                this.c = com.etsy.android.ui.shop.c.a(ai.this.aQ);
            }

            public void a(ShopHomeFragment shopHomeFragment) {
                this.c.injectMembers(shopHomeFragment);
            }
        }

        static {
            Class<ai> cls = ai.class;
        }

        private q(p pVar) {
            if (a || pVar != null) {
                a(pVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(p pVar) {
            this.c = new dagger.internal.c<C0041a>() {
                /* renamed from: a */
                public C0041a b() {
                    return new c();
                }
            };
            this.d = this.c;
            this.e = new dagger.internal.c<C0050a>() {
                /* renamed from: a */
                public C0050a b() {
                    return new g();
                }
            };
            this.f = this.e;
            this.g = new dagger.internal.c<C0048a>() {
                /* renamed from: a */
                public C0048a b() {
                    return new a();
                }
            };
            this.h = this.g;
            this.i = new dagger.internal.c<C0106a>() {
                /* renamed from: a */
                public C0106a b() {
                    return new e();
                }
            };
            this.j = this.i;
            this.k = dagger.internal.e.a(4).a(ListingFragment.class, this.d).a(ShopHomeFragment.class, this.f).a(BaseShopHomeFragment.class, this.h).a(ReceiptFragment.class, this.j).a();
            this.l = dagger.android.c.a(this.k);
            this.m = dagger.internal.d.a(pVar.c);
            this.n = this.m;
            this.o = com.etsy.android.lib.a.r.a(pVar.b, this.n);
            this.p = com.etsy.android.ui.core.b.a(this.l, this.o, ai.this.X);
        }

        public void a(CoreActivity coreActivity) {
            this.p.injectMembers(coreActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class r extends C0034a {
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q b;
        /* access modifiers changed from: private */
        public DetailedImageActivity c;

        private r() {
        }

        /* renamed from: a */
        public com.etsy.android.a.o.a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.lib.a.q();
            }
            if (this.c != null) {
                return new s(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(DetailedImageActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(DetailedImageActivity detailedImageActivity) {
            this.c = (DetailedImageActivity) dagger.internal.f.a(detailedImageActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class s implements com.etsy.android.a.o.a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<C0040a> c;
        private javax.a.a<C0117b<? extends Fragment>> d;
        private javax.a.a<Map<Class<? extends Fragment>, javax.a.a<C0117b<? extends Fragment>>>> e;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> f;
        private javax.a.a<DetailedImageActivity> g;
        private javax.a.a<TrackingBaseActivity> h;
        private javax.a.a<com.etsy.android.lib.logger.w> i;
        private dagger.a<DetailedImageActivity> j;

        /* compiled from: DaggerAppComponent */
        private final class a extends C0040a {
            private DetailedImageFragment b;

            private a() {
            }

            /* renamed from: a */
            public C0039a b() {
                if (this.b != null) {
                    return new b(this);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(DetailedImageFragment.class.getCanonicalName());
                sb.append(" must be set");
                throw new IllegalStateException(sb.toString());
            }

            public void a(DetailedImageFragment detailedImageFragment) {
                this.b = (DetailedImageFragment) dagger.internal.f.a(detailedImageFragment);
            }
        }

        /* compiled from: DaggerAppComponent */
        private final class b implements C0039a {
            static final /* synthetic */ boolean a = true;
            private dagger.a<DetailedImageFragment> c;

            static {
                Class<ai> cls = ai.class;
            }

            private b(a aVar) {
                if (a || aVar != null) {
                    a(aVar);
                    return;
                }
                throw new AssertionError();
            }

            private void a(a aVar) {
                this.c = com.etsy.android.ui.core.d.a(ai.this.aQ);
            }

            public void a(DetailedImageFragment detailedImageFragment) {
                this.c.injectMembers(detailedImageFragment);
            }
        }

        static {
            Class<ai> cls = ai.class;
        }

        private s(r rVar) {
            if (a || rVar != null) {
                a(rVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(r rVar) {
            this.c = new dagger.internal.c<C0040a>() {
                /* renamed from: a */
                public C0040a b() {
                    return new a();
                }
            };
            this.d = this.c;
            this.e = dagger.internal.e.a(1).a(DetailedImageFragment.class, this.d).a();
            this.f = dagger.android.c.a(this.e);
            this.g = dagger.internal.d.a(rVar.c);
            this.h = this.g;
            this.i = com.etsy.android.lib.a.r.a(rVar.b, this.h);
            this.j = com.etsy.android.ui.core.c.a(this.f, this.i);
        }

        public void a(DetailedImageActivity detailedImageActivity) {
            this.j.injectMembers(detailedImageActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class t extends C0082a {
        private EtsyGcmListenerService b;

        private t() {
        }

        /* renamed from: a */
        public com.etsy.android.messaging.c.a b() {
            if (this.b != null) {
                return new u(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(EtsyGcmListenerService.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(EtsyGcmListenerService etsyGcmListenerService) {
            this.b = (EtsyGcmListenerService) dagger.internal.f.a(etsyGcmListenerService);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class u implements com.etsy.android.messaging.c.a {
        static final /* synthetic */ boolean a = true;
        private dagger.a<EtsyGcmListenerService> c;

        static {
            Class<ai> cls = ai.class;
        }

        private u(t tVar) {
            if (a || tVar != null) {
                a(tVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(t tVar) {
            this.c = com.etsy.android.messaging.d.a(ai.this.bf);
        }

        public void a(EtsyGcmListenerService etsyGcmListenerService) {
            this.c.injectMembers(etsyGcmListenerService);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class v extends C0021a {
        /* access modifiers changed from: private */
        public com.etsy.android.ui.feedback.a b;
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q c;
        /* access modifiers changed from: private */
        public FeedbackActivity d;

        private v() {
        }

        /* renamed from: a */
        public com.etsy.android.a.b.a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.ui.feedback.a();
            }
            if (this.c == null) {
                this.c = new com.etsy.android.lib.a.q();
            }
            if (this.d != null) {
                return new w(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(FeedbackActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(FeedbackActivity feedbackActivity) {
            this.d = (FeedbackActivity) dagger.internal.f.a(feedbackActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class w implements com.etsy.android.a.b.a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> c;
        private javax.a.a<FeedbackActivity> d;
        private javax.a.a<TrackingBaseActivity> e;
        private javax.a.a<com.etsy.android.lib.logger.w> f;
        private javax.a.a<com.etsy.android.lib.core.http.request.EtsyApiV3Request.a<Feedback>> g;
        private javax.a.a<com.etsy.android.lib.core.http.request.d.a<Feedback>> h;
        private javax.a.a<com.etsy.android.ui.feedback.i> i;
        private javax.a.a<com.etsy.android.ui.feedback.g> j;
        private dagger.a<FeedbackActivity> k;

        static {
            Class<ai> cls = ai.class;
        }

        private w(v vVar) {
            if (a || vVar != null) {
                a(vVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(v vVar) {
            this.c = dagger.android.c.a(dagger.internal.e.a());
            this.d = dagger.internal.d.a(vVar.d);
            this.e = com.etsy.android.ui.feedback.e.a(vVar.b, this.d);
            this.f = com.etsy.android.lib.a.r.a(vVar.c, this.e);
            this.g = com.etsy.android.ui.feedback.b.a(vVar.b);
            this.h = com.etsy.android.ui.feedback.c.a(vVar.b);
            this.i = com.etsy.android.ui.feedback.d.a(vVar.b, this.g, this.h, ai.this.ay);
            this.j = com.etsy.android.ui.feedback.h.a(this.i);
            this.k = com.etsy.android.ui.feedback.f.a(this.c, this.f, this.j);
        }

        public void a(FeedbackActivity feedbackActivity) {
            this.k.injectMembers(feedbackActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class x extends C0022a {
        /* access modifiers changed from: private */
        public com.etsy.android.b.a b;
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q c;
        /* access modifiers changed from: private */
        public GiftCardCreateActivity d;

        private x() {
        }

        /* renamed from: a */
        public com.etsy.android.a.c.a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.b.a();
            }
            if (this.c == null) {
                this.c = new com.etsy.android.lib.a.q();
            }
            if (this.d != null) {
                return new y(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(GiftCardCreateActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(GiftCardCreateActivity giftCardCreateActivity) {
            this.d = (GiftCardCreateActivity) dagger.internal.f.a(giftCardCreateActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class y implements com.etsy.android.a.c.a {
        static final /* synthetic */ boolean a = true;
        private javax.a.a<DispatchingAndroidInjector<Fragment>> c;
        private javax.a.a<GiftCardCreateActivity> d;
        private javax.a.a<TrackingBaseActivity> e;
        private javax.a.a<com.etsy.android.lib.logger.w> f;
        private javax.a.a<com.etsy.android.b.d> g;
        private dagger.a<GiftCardCreateActivity> h;

        static {
            Class<ai> cls = ai.class;
        }

        private y(x xVar) {
            if (a || xVar != null) {
                a(xVar);
                return;
            }
            throw new AssertionError();
        }

        private void a(x xVar) {
            this.c = dagger.android.c.a(dagger.internal.e.a());
            this.d = dagger.internal.d.a(xVar.d);
            this.e = com.etsy.android.b.b.a(xVar.b, this.d);
            this.f = com.etsy.android.lib.a.r.a(xVar.c, this.e);
            this.g = com.etsy.android.b.c.a(xVar.b, ai.this.aC);
            this.h = com.etsy.android.ui.giftcards.f.a(this.c, this.f, this.g);
        }

        public void a(GiftCardCreateActivity giftCardCreateActivity) {
            this.h.injectMembers(giftCardCreateActivity);
        }
    }

    /* compiled from: DaggerAppComponent */
    private final class z extends C0023a {
        /* access modifiers changed from: private */
        public com.etsy.android.lib.a.q b;
        /* access modifiers changed from: private */
        public HomescreenTabsActivity c;

        private z() {
        }

        /* renamed from: a */
        public com.etsy.android.a.d.a b() {
            if (this.b == null) {
                this.b = new com.etsy.android.lib.a.q();
            }
            if (this.c != null) {
                return new aa(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(HomescreenTabsActivity.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public void a(HomescreenTabsActivity homescreenTabsActivity) {
            this.c = (HomescreenTabsActivity) dagger.internal.f.a(homescreenTabsActivity);
        }
    }

    private ai(a aVar) {
        if (a || aVar != null) {
            a(aVar);
            b(aVar);
            return;
        }
        throw new AssertionError();
    }

    public static com.etsy.android.a.t.a a() {
        return new a();
    }

    private void a(a aVar) {
        this.b = dagger.internal.d.a(aVar.i);
        this.c = dagger.internal.b.a(w.a(aVar.a, this.b));
        this.d = new dagger.internal.c<C0026a>() {
            /* renamed from: a */
            public C0026a b() {
                return new ap();
            }
        };
        this.e = this.d;
        this.f = new dagger.internal.c<C0021a>() {
            /* renamed from: a */
            public C0021a b() {
                return new v();
            }
        };
        this.g = this.f;
        this.h = new dagger.internal.c<C0024a>() {
            /* renamed from: a */
            public C0024a b() {
                return new af();
            }
        };
        this.i = this.h;
        this.j = new dagger.internal.c<C0019a>() {
            /* renamed from: a */
            public C0019a b() {
                return new b();
            }
        };
        this.k = this.j;
        this.l = new dagger.internal.c<C0022a>() {
            /* renamed from: a */
            public C0022a b() {
                return new x();
            }
        };
        this.m = this.l;
        this.n = new dagger.internal.c<C0025a>() {
            /* renamed from: a */
            public C0025a b() {
                return new al();
            }
        };
        this.o = this.n;
        this.p = new dagger.internal.c<C0023a>() {
            /* renamed from: a */
            public C0023a b() {
                return new z();
            }
        };
        this.q = this.p;
        this.r = new dagger.internal.c<C0033a>() {
            /* renamed from: a */
            public C0033a b() {
                return new p();
            }
        };
        this.s = this.r;
        this.t = new dagger.internal.c<C0036a>() {
            /* renamed from: a */
            public C0036a b() {
                return new ah();
            }
        };
        this.u = this.t;
        this.v = new dagger.internal.c<C0034a>() {
            /* renamed from: a */
            public C0034a b() {
                return new r();
            }
        };
        this.w = this.v;
        this.x = new dagger.internal.c<C0038a>() {
            /* renamed from: a */
            public C0038a b() {
                return new an();
            }
        };
        this.y = this.x;
        this.z = new dagger.internal.c<C0035a>() {
            /* renamed from: a */
            public C0035a b() {
                return new ad();
            }
        };
        this.A = this.z;
        this.B = new dagger.internal.c<C0032a>() {
            /* renamed from: a */
            public C0032a b() {
                return new n();
            }
        };
        this.C = this.B;
        this.D = new dagger.internal.c<C0028a>() {
            /* renamed from: a */
            public C0028a b() {
                return new f();
            }
        };
        this.E = this.D;
        this.F = new dagger.internal.c<C0029a>() {
            /* renamed from: a */
            public C0029a b() {
                return new h();
            }
        };
        this.G = this.F;
        this.H = new dagger.internal.c<C0030a>() {
            /* renamed from: a */
            public C0030a b() {
                return new j();
            }
        };
        this.I = this.H;
        this.J = new dagger.internal.c<C0031a>() {
            /* renamed from: a */
            public C0031a b() {
                return new l();
            }
        };
        this.K = this.J;
        this.L = new dagger.internal.c<C0027a>() {
            /* renamed from: a */
            public C0027a b() {
                return new d();
            }
        };
        this.M = this.L;
        this.N = new dagger.internal.c<C0037a>() {
            /* renamed from: a */
            public C0037a b() {
                return new aj();
            }
        };
        this.O = this.N;
        this.P = dagger.internal.e.a(19).a(SignInActivity.class, this.e).a(FeedbackActivity.class, this.g).a(NotificationActivity.class, this.i).a(CartWithSavedActivity.class, this.k).a(GiftCardCreateActivity.class, this.m).a(SettingsActivity.class, this.o).a(HomescreenTabsActivity.class, this.q).a(CoreActivity.class, this.s).a(PhabletsActivity.class, this.u).a(DetailedImageActivity.class, this.w).a(ShopHomeActivity.class, this.y).a(ManufacturerProjectActivity.class, this.A).a(ConvoViewActivity.class, this.C).a(ConvoBaseActivity.class, this.E).a(ConvoComposeActivity.class, this.G).a(ConvoComposeDialogActivity.class, this.I).a(ConvoListActivity.class, this.K).a(ConvoActivity.class, this.M).a(SearchV2Activity.class, this.O).a();
        this.Q = dagger.android.c.a(this.P);
        this.R = new dagger.internal.c<C0052a>() {
            /* renamed from: a */
            public C0052a b() {
                return new ab();
            }
        };
        this.S = this.R;
        this.T = new dagger.internal.c<C0082a>() {
            /* renamed from: a */
            public C0082a b() {
                return new t();
            }
        };
        this.U = this.T;
        this.V = dagger.internal.e.a(2).a(InstanceIdService.class, this.S).a(EtsyGcmListenerService.class, this.U).a();
        this.W = dagger.android.c.a(this.V);
        this.X = dagger.internal.b.a(com.etsy.android.lib.logger.m.c());
        this.Y = dagger.internal.b.a(com.etsy.android.lib.a.b.c());
        this.Z = com.etsy.android.lib.e.b.a(this.c, this.X, this.Y);
        this.aa = com.etsy.android.lib.b.b.a(this.X);
        this.ab = dagger.internal.b.a(com.etsy.android.lib.a.d.a(this.c));
        this.ac = com.etsy.android.lib.h.b.a(this.c);
        this.ad = dagger.internal.b.a(x.a(aVar.a, this.b));
        this.ae = dagger.internal.b.a(ag.a(aVar.a));
        this.af = dagger.internal.b.a(aa.a(aVar.a, this.c));
        this.ag = dagger.internal.b.a(y.a(aVar.a, this.c, this.af));
        this.ah = dagger.internal.b.a(com.etsy.android.lib.logger.elk.b.g.a(aVar.b, this.c));
        this.ai = com.etsy.android.lib.logger.elk.b.f.a(aVar.b, this.ah);
        this.aj = dagger.internal.b.a(com.etsy.android.lib.logger.elk.b.c.a(aVar.b, this.ai, this.af, this.ae, this.X));
        this.ak = dagger.internal.b.a(com.etsy.android.lib.logger.elk.b.d.a(aVar.b, this.ad, this.ae, this.ag, this.aj, this.X));
        this.al = com.etsy.android.lib.a.o.a(aVar.c);
        this.am = com.etsy.android.lib.c.k.a(this.aj);
        this.an = dagger.internal.b.a(com.etsy.android.lib.a.h.a(aVar.c, this.al, this.am));
        this.ao = dagger.internal.b.a(com.etsy.android.lib.a.g.a(aVar.c, this.X));
        this.ap = dagger.internal.b.a(z.a(aVar.a, this.ag));
        this.aq = dagger.internal.b.a(com.etsy.android.lib.a.j.a(aVar.c, this.an, this.ao, this.ap));
        this.ar = com.etsy.android.lib.push.h.a(this.ap);
        this.as = com.etsy.android.lib.push.k.a(this.aq, this.ar, this.ak, this.X, this.af, com.etsy.android.lib.auth.d.c());
        this.at = dagger.internal.b.a(com.etsy.android.lib.a.c.a(this.c));
        this.au = com.etsy.android.lib.push.c.a(this.c, this.X, this.aa, this.at, this.ak);
        this.av = com.etsy.android.lib.push.e.a(this.ar, this.au, this.ak, this.X);
        this.aw = dagger.internal.b.a(com.etsy.android.lib.push.i.a(this.X, this.c, this.Z, this.aa, this.ab, com.etsy.android.lib.f.b.c(), this.ac, this.ak, this.as, this.av));
        this.ax = com.etsy.android.util.g.a(this.aw);
        this.ay = dagger.internal.b.a(af.a(this.c, this.ax, this.ac));
        this.az = dagger.internal.b.a(ab.a(this.c));
        this.aA = dagger.internal.b.a(com.etsy.android.lib.logger.elk.b.h.a(aVar.b, this.ap));
        this.aB = dagger.internal.b.a(com.etsy.android.lib.a.f.a(aVar.c));
        this.aC = dagger.internal.b.a(com.etsy.android.lib.a.i.a(aVar.c, this.an, this.aB, this.ap));
        this.aD = com.etsy.android.lib.logger.elk.b.b.a(aVar.b, this.aC);
        this.aE = dagger.internal.b.a(com.etsy.android.lib.logger.analytics.c.a(aVar.d));
        this.aF = dagger.internal.b.a(com.etsy.android.lib.a.k.a(aVar.c, this.al, this.am));
        this.aG = dagger.internal.b.a(com.etsy.android.lib.a.n.a(aVar.c, this.aF, this.ao, this.ap));
        this.aH = com.etsy.android.lib.logger.analytics.d.a(aVar.d, this.aG);
        this.aI = com.etsy.android.lib.logger.analytics.e.a(aVar.d, this.X, this.aj, this.ap, this.aE, this.aH, this.af);
        this.aJ = com.etsy.android.lib.logger.analytics.h.a(this.ap);
        this.aK = dagger.internal.b.a(com.etsy.android.lib.logger.elk.b.e.a(aVar.b, this.c));
        this.aL = dagger.internal.b.a(ah.a(aVar.a));
        this.aM = com.etsy.android.deeplinking.b.a(this.c);
        this.aN = dagger.internal.b.a(com.etsy.android.lib.logger.p.a(aVar.e, this.aq));
        this.aO = com.etsy.android.lib.logger.q.a(aVar.e, this.aN, this.ay, this.ae);
        this.aP = dagger.internal.b.a(com.etsy.android.lib.util.sharedprefs.e.a(this.c));
        this.aQ = dagger.internal.b.a(com.etsy.android.lib.util.b.b.c());
        this.aR = dagger.internal.b.a(com.etsy.android.lib.util.sharedprefs.c.a(this.c));
        this.aS = com.etsy.android.messaging.salesforce.b.a(this.c, this.aQ, this.aR);
        this.aT = com.etsy.android.messaging.salesforce.d.a(this.X, this.aP, this.aS, this.Y);
        this.aU = com.etsy.android.util.a.a(this.X);
        this.aV = dagger.internal.b.a(com.etsy.android.ui.search.v2.a.c.a(aVar.f, this.c));
        this.aW = com.etsy.android.ui.search.v2.a.b.a(aVar.f, this.aV);
    }

    private void b(a aVar) {
        this.aX = dagger.internal.b.a(com.etsy.android.lib.a.m.a(aVar.c, this.aF, this.ao, this.ap));
        this.aY = com.etsy.android.ui.search.v2.a.e.a(aVar.g, this.aX);
        this.aZ = com.etsy.android.ui.search.v2.impressions.e.a(this.ap, this.X, this.aW, this.aY);
        this.ba = com.etsy.android.a.a(this.Q, this.W, this.ax, this.aw, this.X, this.ay, this.az, this.aA, this.aj, this.ai, this.aD, this.aI, this.aJ, this.ap, this.aK, this.aL, this.aM, this.aO, this.aT, this.aU, this.aZ);
        this.bb = dagger.internal.b.a(ac.a(aVar.a));
        this.bc = dagger.internal.b.a(com.etsy.android.lib.a.l.a(aVar.c, this.aF, this.aB, this.ap));
        this.bd = com.etsy.android.ui.convos.convoredesign.k.a(this.c);
        this.be = com.etsy.android.ui.convos.convoredesign.j.a(this.bd);
        this.bf = dagger.internal.b.a(ae.a(aVar.a));
        this.bg = dagger.internal.b.a(ad.a(aVar.a, this.c));
        this.bh = dagger.internal.b.a(com.etsy.android.lib.util.d.a(aVar.h, this.c));
    }

    public void a(BOEApplication bOEApplication) {
        this.ba.injectMembers(bOEApplication);
    }
}
