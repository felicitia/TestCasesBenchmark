package com.etsy.android;

import android.app.Activity;
import android.app.Service;
import androidx.work.j;
import com.etsy.android.lib.config.c;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.analytics.g;
import com.etsy.android.lib.logger.elk.e;
import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.logger.legacy.b;
import com.etsy.android.lib.logger.r;
import com.etsy.android.ui.search.v2.impressions.d;
import com.etsy.android.util.AppLifecycleObserver;
import com.etsy.android.util.f;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import dagger.android.DispatchingAndroidInjector;

/* compiled from: BOEApplication_MembersInjector */
public final class a implements dagger.a<BOEApplication> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<DispatchingAndroidInjector<Activity>> b;
    private final javax.a.a<DispatchingAndroidInjector<Service>> c;
    private final javax.a.a<f> d;
    private final javax.a.a<com.etsy.android.lib.push.f> e;
    private final javax.a.a<l> f;
    private final javax.a.a<v> g;
    private final javax.a.a<b> h;
    private final javax.a.a<e> i;
    private final javax.a.a<com.etsy.android.lib.logger.elk.f> j;
    private final javax.a.a<com.etsy.android.lib.logger.elk.b> k;
    private final javax.a.a<com.etsy.android.lib.logger.elk.uploading.b> l;
    private final javax.a.a<com.etsy.android.lib.logger.analytics.f> m;
    private final javax.a.a<g> n;
    private final javax.a.a<c> o;
    private final javax.a.a<FirebaseJobDispatcher> p;
    private final javax.a.a<j> q;
    private final javax.a.a<com.etsy.android.deeplinking.a> r;
    private final javax.a.a<r> s;
    private final javax.a.a<com.etsy.android.messaging.salesforce.c> t;
    private final javax.a.a<AppLifecycleObserver> u;
    private final javax.a.a<d> v;

    public a(javax.a.a<DispatchingAndroidInjector<Activity>> aVar, javax.a.a<DispatchingAndroidInjector<Service>> aVar2, javax.a.a<f> aVar3, javax.a.a<com.etsy.android.lib.push.f> aVar4, javax.a.a<l> aVar5, javax.a.a<v> aVar6, javax.a.a<b> aVar7, javax.a.a<e> aVar8, javax.a.a<com.etsy.android.lib.logger.elk.f> aVar9, javax.a.a<com.etsy.android.lib.logger.elk.b> aVar10, javax.a.a<com.etsy.android.lib.logger.elk.uploading.b> aVar11, javax.a.a<com.etsy.android.lib.logger.analytics.f> aVar12, javax.a.a<g> aVar13, javax.a.a<c> aVar14, javax.a.a<FirebaseJobDispatcher> aVar15, javax.a.a<j> aVar16, javax.a.a<com.etsy.android.deeplinking.a> aVar17, javax.a.a<r> aVar18, javax.a.a<com.etsy.android.messaging.salesforce.c> aVar19, javax.a.a<AppLifecycleObserver> aVar20, javax.a.a<d> aVar21) {
        javax.a.a<j> aVar22;
        javax.a.a<com.etsy.android.deeplinking.a> aVar23;
        javax.a.a<r> aVar24;
        javax.a.a<com.etsy.android.messaging.salesforce.c> aVar25;
        javax.a.a<AppLifecycleObserver> aVar26;
        javax.a.a<d> aVar27;
        javax.a.a<DispatchingAndroidInjector<Activity>> aVar28 = aVar;
        javax.a.a<DispatchingAndroidInjector<Service>> aVar29 = aVar2;
        javax.a.a<f> aVar30 = aVar3;
        javax.a.a<com.etsy.android.lib.push.f> aVar31 = aVar4;
        javax.a.a<l> aVar32 = aVar5;
        javax.a.a<v> aVar33 = aVar6;
        javax.a.a<b> aVar34 = aVar7;
        javax.a.a<e> aVar35 = aVar8;
        javax.a.a<com.etsy.android.lib.logger.elk.f> aVar36 = aVar9;
        javax.a.a<com.etsy.android.lib.logger.elk.b> aVar37 = aVar10;
        javax.a.a<com.etsy.android.lib.logger.elk.uploading.b> aVar38 = aVar11;
        javax.a.a<com.etsy.android.lib.logger.analytics.f> aVar39 = aVar12;
        javax.a.a<g> aVar40 = aVar13;
        javax.a.a<c> aVar41 = aVar14;
        javax.a.a<FirebaseJobDispatcher> aVar42 = aVar15;
        if (a || aVar28 != null) {
            this.b = aVar28;
            if (a || aVar29 != null) {
                this.c = aVar29;
                if (a || aVar30 != null) {
                    this.d = aVar30;
                    if (a || aVar31 != null) {
                        this.e = aVar31;
                        if (a || aVar32 != null) {
                            this.f = aVar32;
                            if (a || aVar33 != null) {
                                this.g = aVar33;
                                if (a || aVar34 != null) {
                                    this.h = aVar34;
                                    if (a || aVar35 != null) {
                                        this.i = aVar35;
                                        if (a || aVar36 != null) {
                                            this.j = aVar36;
                                            if (a || aVar37 != null) {
                                                this.k = aVar37;
                                                if (a || aVar38 != null) {
                                                    this.l = aVar38;
                                                    if (a || aVar39 != null) {
                                                        this.m = aVar39;
                                                        if (a || aVar40 != null) {
                                                            this.n = aVar40;
                                                            if (a || aVar41 != null) {
                                                                this.o = aVar41;
                                                                if (a || aVar42 != null) {
                                                                    this.p = aVar42;
                                                                    if (!a) {
                                                                        aVar22 = aVar16;
                                                                        if (aVar22 == null) {
                                                                            throw new AssertionError();
                                                                        }
                                                                    } else {
                                                                        aVar22 = aVar16;
                                                                    }
                                                                    this.q = aVar22;
                                                                    if (!a) {
                                                                        aVar23 = aVar17;
                                                                        if (aVar23 == null) {
                                                                            throw new AssertionError();
                                                                        }
                                                                    } else {
                                                                        aVar23 = aVar17;
                                                                    }
                                                                    this.r = aVar23;
                                                                    if (!a) {
                                                                        aVar24 = aVar18;
                                                                        if (aVar24 == null) {
                                                                            throw new AssertionError();
                                                                        }
                                                                    } else {
                                                                        aVar24 = aVar18;
                                                                    }
                                                                    this.s = aVar24;
                                                                    if (!a) {
                                                                        aVar25 = aVar19;
                                                                        if (aVar25 == null) {
                                                                            throw new AssertionError();
                                                                        }
                                                                    } else {
                                                                        aVar25 = aVar19;
                                                                    }
                                                                    this.t = aVar25;
                                                                    if (!a) {
                                                                        aVar26 = aVar20;
                                                                        if (aVar26 == null) {
                                                                            throw new AssertionError();
                                                                        }
                                                                    } else {
                                                                        aVar26 = aVar20;
                                                                    }
                                                                    this.u = aVar26;
                                                                    if (!a) {
                                                                        aVar27 = aVar21;
                                                                        if (aVar27 == null) {
                                                                            throw new AssertionError();
                                                                        }
                                                                    } else {
                                                                        aVar27 = aVar21;
                                                                    }
                                                                    this.v = aVar27;
                                                                    return;
                                                                }
                                                                throw new AssertionError();
                                                            }
                                                            throw new AssertionError();
                                                        }
                                                        throw new AssertionError();
                                                    }
                                                    throw new AssertionError();
                                                }
                                                throw new AssertionError();
                                            }
                                            throw new AssertionError();
                                        }
                                        throw new AssertionError();
                                    }
                                    throw new AssertionError();
                                }
                                throw new AssertionError();
                            }
                            throw new AssertionError();
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static dagger.a<BOEApplication> a(javax.a.a<DispatchingAndroidInjector<Activity>> aVar, javax.a.a<DispatchingAndroidInjector<Service>> aVar2, javax.a.a<f> aVar3, javax.a.a<com.etsy.android.lib.push.f> aVar4, javax.a.a<l> aVar5, javax.a.a<v> aVar6, javax.a.a<b> aVar7, javax.a.a<e> aVar8, javax.a.a<com.etsy.android.lib.logger.elk.f> aVar9, javax.a.a<com.etsy.android.lib.logger.elk.b> aVar10, javax.a.a<com.etsy.android.lib.logger.elk.uploading.b> aVar11, javax.a.a<com.etsy.android.lib.logger.analytics.f> aVar12, javax.a.a<g> aVar13, javax.a.a<c> aVar14, javax.a.a<FirebaseJobDispatcher> aVar15, javax.a.a<j> aVar16, javax.a.a<com.etsy.android.deeplinking.a> aVar17, javax.a.a<r> aVar18, javax.a.a<com.etsy.android.messaging.salesforce.c> aVar19, javax.a.a<AppLifecycleObserver> aVar20, javax.a.a<d> aVar21) {
        a aVar22 = new a(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13, aVar14, aVar15, aVar16, aVar17, aVar18, aVar19, aVar20, aVar21);
        return aVar22;
    }

    /* renamed from: a */
    public void injectMembers(BOEApplication bOEApplication) {
        if (bOEApplication == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        bOEApplication.dispatchingActivityInjector = (DispatchingAndroidInjector) this.b.b();
        bOEApplication.dispatchingServiceInjector = (DispatchingAndroidInjector) this.c.b();
        bOEApplication.sessionManager = (f) this.d.b();
        bOEApplication.pushRegistration = (com.etsy.android.lib.push.f) this.e.b();
        bOEApplication.logCat = (l) this.f.b();
        bOEApplication.session = (v) this.g.b();
        bOEApplication.legacyLogger = (b) this.h.b();
        bOEApplication.logUploader = (e) this.i.b();
        bOEApplication.elkLogger = (com.etsy.android.lib.logger.elk.f) this.j.b();
        bOEApplication.logDao = (com.etsy.android.lib.logger.elk.b) this.k.b();
        bOEApplication.elkEndpoint = (com.etsy.android.lib.logger.elk.uploading.b) this.l.b();
        bOEApplication.analyticsUpload = (com.etsy.android.lib.logger.analytics.f) this.m.b();
        bOEApplication.analyticsUploader = (g) this.n.b();
        bOEApplication.configMap = (c) this.o.b();
        bOEApplication.jobDispatcher = (FirebaseJobDispatcher) this.p.b();
        bOEApplication.workManager = (j) this.q.b();
        bOEApplication.button = (com.etsy.android.deeplinking.a) this.r.b();
        bOEApplication.serverTimestampOffsetSynchronizer = (r) this.s.b();
        bOEApplication.salesforceNotificationInitializer = (com.etsy.android.messaging.salesforce.c) this.t.b();
        bOEApplication.appLifecycleObserver = (AppLifecycleObserver) this.u.b();
        bOEApplication.searchImpressionRepository = (d) this.v.b();
    }
}
