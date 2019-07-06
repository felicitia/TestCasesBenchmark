package com.etsy.android.ui.nav;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.etsy.android.R;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.c;
import com.etsy.android.lib.messaging.g;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.ui.nav.BaseNotificationActivity;
import com.etsy.android.lib.util.CrashUtil;
import com.etsy.android.lib.util.NotificationType;
import com.etsy.android.lib.util.n;
import com.etsy.android.uikit.nav.ActivityNavigator.AnimationMode;
import com.etsy.android.util.d;
import com.google.android.gms.a.b;
import io.branch.referral.Branch;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.v;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class NotificationActivity extends BaseNotificationActivity implements a {
    private static final long BRANCH_FALLBACK_TIMEOUT = 3;
    private static final int BRANCH_RETRY_COUNT = 1;
    private static final int BRANCH_TIMEOUT = 3000;
    public static final String ETSY_DEEPLINK_PARAM = "deeplink";
    public static final String ETSY_DEFERRED_PARAM = "deferred";
    private static final String TAG = f.a(NotificationActivity.class);
    com.etsy.android.deeplinking.a button;
    @Nullable
    private Disposable fallbackRedirectTimer = null;
    l log;

    public NotificationActivity() {
        super(new HashMap<String, String>() {
            {
                put("android.intent.action.VIEW", "external_url");
                put("com.etsy.android.action.NOTIFICATION", "notifications");
            }
        });
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(R.color.background_main_v2);
        this.button.a(getIntent());
        trackDeeplink();
    }

    private b getActivityNavigator() {
        b a = e.a((FragmentActivity) this).a().a(AnimationMode.DEFAULT);
        boolean z = false;
        if (getIntent() != null && getIntent().getBooleanExtra("NAV_INTERNAL_LINK", false)) {
            z = true;
        }
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra("t");
            if (!TextUtils.isEmpty(stringExtra)) {
                a.a(stringExtra);
            }
        }
        if (z) {
            a.a(AnimationMode.SLIDE_RIGHT);
        }
        if (EtsyApplication.get().isAppInBackground() && !z) {
            a.g();
        }
        return a;
    }

    private Bundle getRouteBundle(c cVar) {
        Bundle bundle = new Bundle();
        if (cVar.b() != null) {
            bundle.putString(EtsyAction.ACTION_TYPE_NAME, cVar.b().getName());
        }
        HashMap f = cVar.f();
        if (f != null && f.size() > 0) {
            Bundle bundle2 = new Bundle();
            for (String str : f.keySet()) {
                bundle2.putString(str, (String) f.get(str));
            }
            bundle.putBundle("url_params", bundle2);
        }
        return bundle;
    }

    public void onStart() {
        super.onStart();
        this.fallbackRedirectTimer = v.a(3, TimeUnit.SECONDS).a(io.reactivex.a.b.a.a()).a((Consumer<? super T>) new f<Object>(this), (Consumer<? super Throwable>) new g<Object>(this));
        Branch b = Branch.b();
        b.b(3000);
        b.a(1);
        b.a((Branch.f) new h(this), getIntent().getData(), (Activity) this);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$onStart$0$NotificationActivity(Long l) throws Exception {
        dispatchRoute(null);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$onStart$1$NotificationActivity(Throwable th) throws Exception {
        dispatchRoute(null);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$onStart$2$NotificationActivity(JSONObject jSONObject, io.branch.referral.c cVar) {
        if (this.fallbackRedirectTimer != null) {
            this.fallbackRedirectTimer.dispose();
        }
        if (cVar == null) {
            dispatchRoute(jSONObject);
            return;
        }
        dispatchRoute(null);
        this.log.e(cVar.a());
    }

    public void onNewIntent(Intent intent) {
        setIntent(intent);
    }

    public void onStop() {
        super.onStop();
        if (this.fallbackRedirectTimer != null) {
            this.fallbackRedirectTimer.dispose();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x014e  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0167  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void dispatchRoute(org.json.JSONObject r12) {
        /*
            r11 = this;
            android.content.Intent r0 = r11.getIntent()
            java.lang.String r1 = "external"
            r2 = 0
            r3 = 0
            if (r12 == 0) goto L_0x001f
            java.lang.String r4 = "+clicked_branch_link"
            boolean r4 = r12.optBoolean(r4, r2)
            if (r4 == 0) goto L_0x001f
            java.lang.String r4 = "$canonical_url"
            java.lang.String r5 = "https://www.etsy.com/"
            java.lang.String r12 = r12.optString(r4, r5)
            android.net.Uri r12 = android.net.Uri.parse(r12)
            goto L_0x0031
        L_0x001f:
            if (r0 == 0) goto L_0x0030
            java.lang.String r12 = r0.getAction()
            java.lang.String r1 = "external"
            java.lang.String r1 = r11.determineSourceType(r12, r1)
            android.net.Uri r12 = r0.getData()
            goto L_0x0031
        L_0x0030:
            r12 = r3
        L_0x0031:
            if (r12 == 0) goto L_0x0038
            com.etsy.android.lib.messaging.c r4 = com.etsy.android.lib.messaging.c.a(r12)
            goto L_0x0039
        L_0x0038:
            r4 = r3
        L_0x0039:
            if (r4 == 0) goto L_0x04c2
            android.content.Context r5 = r11.getApplicationContext()
            com.google.ads.conversiontracking.a.a(r5, r12)
            android.os.Bundle r12 = r11.addReferrerData(r12)
            r11.trackIntent(r0, r1, r3)
            android.os.Bundle r10 = r11.getRouteBundle(r4)
            java.lang.String r5 = "referrer_bundle"
            r10.putBundle(r5, r12)
            java.lang.String r5 = "source_type"
            r10.putString(r5, r1)
            com.etsy.android.ui.nav.b r5 = r11.getActivityNavigator()
            com.etsy.android.ui.nav.b r5 = r5.a(r10)
            com.etsy.android.lib.models.datatypes.EtsyDeepLinkId r6 = new com.etsy.android.lib.models.datatypes.EtsyDeepLinkId
            r6.<init>()
            int[] r7 = com.etsy.android.ui.nav.NotificationActivity.AnonymousClass2.a
            com.etsy.android.lib.messaging.EtsyEntity r8 = r4.c()
            int r8 = r8.ordinal()
            r7 = r7[r8]
            r8 = 1
            switch(r7) {
                case 2: goto L_0x04bb;
                case 3: goto L_0x04b4;
                case 4: goto L_0x0432;
                case 5: goto L_0x0432;
                case 6: goto L_0x0424;
                case 7: goto L_0x041b;
                case 8: goto L_0x040c;
                case 9: goto L_0x03d4;
                case 10: goto L_0x038c;
                case 11: goto L_0x0384;
                case 12: goto L_0x0359;
                case 13: goto L_0x032e;
                case 14: goto L_0x031b;
                case 15: goto L_0x02cd;
                case 16: goto L_0x02cd;
                case 17: goto L_0x02c1;
                case 18: goto L_0x02b1;
                case 19: goto L_0x02aa;
                case 20: goto L_0x029c;
                case 21: goto L_0x029c;
                case 22: goto L_0x029c;
                case 23: goto L_0x0288;
                case 24: goto L_0x0202;
                case 25: goto L_0x0202;
                case 26: goto L_0x01f6;
                case 27: goto L_0x01ea;
                case 28: goto L_0x01b6;
                case 29: goto L_0x016c;
                case 30: goto L_0x00c8;
                case 31: goto L_0x00ae;
                case 32: goto L_0x00ae;
                case 33: goto L_0x00a9;
                case 34: goto L_0x0096;
                case 35: goto L_0x0079;
                case 36: goto L_0x0088;
                default: goto L_0x0074;
            }
        L_0x0074:
            r11.showHome(r0)
            goto L_0x04c5
        L_0x0079:
            boolean r12 = com.etsy.android.util.d.a()
            if (r12 == 0) goto L_0x0088
            java.lang.String r12 = r4.d()
            r5.i(r12)
            goto L_0x04c5
        L_0x0088:
            com.etsy.android.lib.models.datatypes.EtsyId r12 = new com.etsy.android.lib.models.datatypes.EtsyId
            java.lang.String r0 = r4.d()
            r12.<init>(r0)
            r5.e(r12)
            goto L_0x04c5
        L_0x0096:
            com.etsy.android.ui.nav.b r12 = r5.f()
            com.etsy.android.uikit.nav.ActivityNavigator$AnimationMode r0 = com.etsy.android.uikit.nav.ActivityNavigator.AnimationMode.POP
            com.etsy.android.ui.nav.b r12 = r12.a(r0)
            java.lang.String r0 = r4.d()
            r12.c(r0)
            goto L_0x04c5
        L_0x00a9:
            r5.n()
            goto L_0x04c5
        L_0x00ae:
            java.lang.String r0 = ".ref_package"
            java.lang.String r1 = ""
            java.lang.String r12 = r12.getString(r0, r1)
            android.content.Context r0 = r11.getApplicationContext()
            java.lang.String r0 = r0.getPackageName()
            boolean r12 = r12.equals(r0)
            r12 = r12 ^ r8
            r5.a(r12)
            goto L_0x04c5
        L_0x00c8:
            java.lang.String r12 = ""
            java.lang.String r1 = ""
            java.lang.String r2 = ""
            java.util.HashMap r3 = r4.f()     // Catch:{ UnsupportedEncodingException -> 0x013b }
            java.lang.String r6 = "api_path"
            boolean r3 = r3.containsKey(r6)     // Catch:{ UnsupportedEncodingException -> 0x013b }
            if (r3 == 0) goto L_0x0118
            java.util.HashMap r3 = r4.f()     // Catch:{ UnsupportedEncodingException -> 0x013b }
            java.lang.String r6 = "title"
            boolean r3 = r3.containsKey(r6)     // Catch:{ UnsupportedEncodingException -> 0x013b }
            if (r3 == 0) goto L_0x0118
            java.util.HashMap r3 = r4.f()     // Catch:{ UnsupportedEncodingException -> 0x013b }
            java.lang.String r6 = "event_name"
            boolean r3 = r3.containsKey(r6)     // Catch:{ UnsupportedEncodingException -> 0x013b }
            if (r3 == 0) goto L_0x0118
            java.lang.String r3 = "api_path"
            java.lang.String r3 = r4.a(r3)     // Catch:{ UnsupportedEncodingException -> 0x013b }
            java.lang.String r6 = "UTF-8"
            java.lang.String r3 = java.net.URLDecoder.decode(r3, r6)     // Catch:{ UnsupportedEncodingException -> 0x013b }
            java.lang.String r12 = "title"
            java.lang.String r12 = r4.a(r12)     // Catch:{ UnsupportedEncodingException -> 0x013c }
            java.lang.String r6 = "UTF-8"
            java.lang.String r12 = java.net.URLDecoder.decode(r12, r6)     // Catch:{ UnsupportedEncodingException -> 0x013c }
            java.lang.String r1 = "event_name"
            java.lang.String r1 = r4.a(r1)     // Catch:{ UnsupportedEncodingException -> 0x013d }
            java.lang.String r4 = "UTF-8"
            java.lang.String r1 = java.net.URLDecoder.decode(r1, r4)     // Catch:{ UnsupportedEncodingException -> 0x013d }
            r2 = r1
            goto L_0x0148
        L_0x0118:
            com.etsy.android.lib.logger.legacy.b r3 = com.etsy.android.lib.logger.legacy.b.a()     // Catch:{ UnsupportedEncodingException -> 0x013b }
            java.lang.String r4 = TAG     // Catch:{ UnsupportedEncodingException -> 0x013b }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x013b }
            r6.<init>()     // Catch:{ UnsupportedEncodingException -> 0x013b }
            java.lang.String r7 = "Missing parameter in "
            r6.append(r7)     // Catch:{ UnsupportedEncodingException -> 0x013b }
            com.etsy.android.lib.messaging.EtsyEntity r7 = com.etsy.android.lib.messaging.EtsyEntity.LISTING_LANDING_PAGE     // Catch:{ UnsupportedEncodingException -> 0x013b }
            java.lang.String r7 = r7.getName()     // Catch:{ UnsupportedEncodingException -> 0x013b }
            r6.append(r7)     // Catch:{ UnsupportedEncodingException -> 0x013b }
            java.lang.String r6 = r6.toString()     // Catch:{ UnsupportedEncodingException -> 0x013b }
            r3.a(r4, r6)     // Catch:{ UnsupportedEncodingException -> 0x013b }
            r3 = r12
            r12 = r1
            goto L_0x0148
        L_0x013b:
            r3 = r12
        L_0x013c:
            r12 = r1
        L_0x013d:
            com.etsy.android.lib.logger.legacy.b r1 = com.etsy.android.lib.logger.legacy.b.a()
            java.lang.String r4 = TAG
            java.lang.String r6 = "Couldn't decode listing landing page query parameter"
            r1.b(r4, r6)
        L_0x0148:
            boolean r1 = android.text.TextUtils.isEmpty(r3)
            if (r1 != 0) goto L_0x0167
            com.etsy.android.lib.models.homescreen.LandingPageLink r0 = new com.etsy.android.lib.models.homescreen.LandingPageLink
            r0.<init>()
            r0.setPageTitle(r12)
            r0.setApiPath(r3)
            boolean r12 = android.text.TextUtils.isEmpty(r2)
            if (r12 != 0) goto L_0x0162
            r0.setEventName(r2)
        L_0x0162:
            r5.a(r0)
            goto L_0x04c5
        L_0x0167:
            r11.showHome(r0)
            goto L_0x04c5
        L_0x016c:
            java.util.HashMap r12 = r4.f()
            java.lang.String r1 = "page_type"
            boolean r12 = r12.containsKey(r1)
            if (r12 == 0) goto L_0x0186
            java.lang.String r12 = "page_type"
            java.lang.String r12 = r4.a(r12)
            java.lang.String r1 = "explore"
            boolean r12 = r12.equals(r1)
            if (r12 != 0) goto L_0x0189
        L_0x0186:
            r11.showHome(r0)
        L_0x0189:
            java.lang.String r12 = "title"
            java.lang.String r12 = r4.a(r12)     // Catch:{ UnsupportedEncodingException -> 0x01a6 }
            java.lang.String r1 = "api_path"
            java.lang.String r1 = r4.a(r1)     // Catch:{ UnsupportedEncodingException -> 0x01a6 }
            java.lang.String r2 = "UTF-8"
            java.lang.String r1 = java.net.URLDecoder.decode(r1, r2)     // Catch:{ UnsupportedEncodingException -> 0x01a6 }
            java.lang.String r2 = "event_name"
            java.lang.String r2 = r4.a(r2)     // Catch:{ UnsupportedEncodingException -> 0x01a6 }
            r5.b(r12, r1, r2)     // Catch:{ UnsupportedEncodingException -> 0x01a6 }
            goto L_0x04c5
        L_0x01a6:
            com.etsy.android.lib.logger.legacy.b r12 = com.etsy.android.lib.logger.legacy.b.a()
            java.lang.String r1 = TAG
            java.lang.String r2 = "Couldn't decode listing landing page query parameter"
            r12.b(r1, r2)
            r11.showHome(r0)
            goto L_0x04c5
        L_0x01b6:
            java.lang.String r12 = r4.d()
            boolean r1 = android.text.TextUtils.isEmpty(r12)
            if (r1 != 0) goto L_0x01e5
            java.util.HashMap r0 = r4.f()
            java.lang.String r1 = "view_draft_content"
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x01da
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            boolean r2 = r0.booleanValue()
        L_0x01da:
            java.lang.String r0 = "anchor_listing_id"
            java.lang.String r0 = r4.a(r0)
            r5.a(r12, r0, r2)
            goto L_0x04c5
        L_0x01e5:
            r11.showHome(r0)
            goto L_0x04c5
        L_0x01ea:
            java.lang.String r12 = r4.d()
            r6.checkIdTypeAndSet(r12)
            r5.g(r6)
            goto L_0x04c5
        L_0x01f6:
            java.lang.String r12 = r4.d()
            r6.checkIdTypeAndSet(r12)
            r5.f(r6)
            goto L_0x04c5
        L_0x0202:
            com.etsy.android.ui.search.v2.SearchOptions$a r12 = com.etsy.android.ui.search.v2.SearchOptions.Companion
            com.etsy.android.lib.logger.l r0 = r11.log
            com.etsy.android.ui.search.v2.SearchOptions r7 = r12.a(r4, r0)
            com.etsy.android.lib.messaging.EtsyEntity r12 = r4.c()
            com.etsy.android.lib.messaging.EtsyEntity r0 = com.etsy.android.lib.messaging.EtsyEntity.SEARCH
            boolean r12 = r12.equals(r0)
            if (r12 == 0) goto L_0x024f
            java.lang.String r12 = "q"
            java.lang.String r3 = r4.a(r12)
            java.lang.String r12 = r4.d()
            java.lang.String r0 = "shops"
            boolean r0 = r12.startsWith(r0)
            if (r0 != 0) goto L_0x0247
            java.lang.String r0 = "people"
            boolean r0 = r12.startsWith(r0)
            if (r0 == 0) goto L_0x0231
            goto L_0x0247
        L_0x0231:
            int r0 = r12.length()
            if (r0 <= 0) goto L_0x025f
            java.lang.String r12 = com.etsy.android.lib.models.TaxonomyNode.pathFromWebUrlToAPIFormat(r12)
            java.util.HashMap r0 = r4.f()
            r5.a(r12, r0, r7)
            r11.finish()
            goto L_0x04c5
        L_0x0247:
            r5.q()
            r11.finish()
            goto L_0x04c5
        L_0x024f:
            com.etsy.android.lib.messaging.EtsyEntity r12 = r4.c()
            com.etsy.android.lib.messaging.EtsyEntity r0 = com.etsy.android.lib.messaging.EtsyEntity.MARKET
            boolean r12 = r12.equals(r0)
            if (r12 == 0) goto L_0x025f
            java.lang.String r3 = r4.d()
        L_0x025f:
            if (r3 != 0) goto L_0x0263
            java.lang.String r3 = ""
        L_0x0263:
            java.lang.String r12 = "-"
            java.lang.String r0 = " "
            java.lang.String r12 = r3.replace(r12, r0)
            java.lang.String r0 = "_"
            java.lang.String r1 = " "
            java.lang.String r12 = r12.replace(r0, r1)
            com.etsy.android.lib.logger.l r0 = r11.log
            java.lang.String r6 = com.etsy.android.extensions.f.a(r12, r0)
            java.lang.String r12 = "anchor_listing_id"
            java.lang.String r9 = r4.a(r12)
            com.etsy.android.lib.models.TaxonomyNode r8 = r11.getSearchTaxonomyNode(r4)
            r5.a(r6, r7, r8, r9, r10)
            goto L_0x04c5
        L_0x0288:
            java.lang.String r12 = r4.d()
            java.lang.String r12 = com.etsy.android.lib.models.TaxonomyNode.pathFromWebUrlToAPIFormat(r12)
            java.util.HashMap r0 = r4.f()
            r5.a(r12, r0, r3)
            r11.finish()
            goto L_0x04c5
        L_0x029c:
            com.etsy.android.lib.models.datatypes.EtsyId r12 = new com.etsy.android.lib.models.datatypes.EtsyId
            java.lang.String r0 = r4.d()
            r12.<init>(r0)
            r5.a(r12, r8)
            goto L_0x04c5
        L_0x02aa:
            java.lang.String r12 = "local"
            r5.c(r12)
            goto L_0x04c5
        L_0x02b1:
            r11.finish()
            com.etsy.android.ui.nav.b r12 = r5.g()
            java.lang.String r0 = r4.d()
            r12.b(r0)
            goto L_0x04c5
        L_0x02c1:
            com.etsy.android.ui.nav.b r12 = r5.g()
            r12.w()
            r11.finish()
            goto L_0x04c5
        L_0x02cd:
            com.etsy.android.lib.messaging.c r12 = r4.e()
            if (r12 == 0) goto L_0x0309
            int[] r12 = com.etsy.android.ui.nav.NotificationActivity.AnonymousClass2.a
            com.etsy.android.lib.messaging.c r0 = r4.e()
            com.etsy.android.lib.messaging.EtsyEntity r0 = r0.c()
            int r0 = r0.ordinal()
            r12 = r12[r0]
            if (r12 == r8) goto L_0x02e6
            goto L_0x0309
        L_0x02e6:
            com.etsy.android.lib.models.datatypes.EtsyId r12 = new com.etsy.android.lib.models.datatypes.EtsyId
            java.lang.String r0 = r4.d()
            r12.<init>(r0)
            com.etsy.android.lib.models.datatypes.EtsyId r0 = new com.etsy.android.lib.models.datatypes.EtsyId
            com.etsy.android.lib.messaging.c r1 = r4.e()
            java.lang.String r1 = r1.d()
            r0.<init>(r1)
            r5.b(r12, r0)
            com.etsy.android.lib.util.NotificationType r12 = com.etsy.android.lib.util.NotificationType.SHIPPING
            com.etsy.android.lib.messaging.g.b(r11, r12)
            r11.finish()
            goto L_0x04c5
        L_0x0309:
            com.etsy.android.ui.nav.b r12 = r5.g()
            com.etsy.android.lib.models.datatypes.EtsyId r0 = new com.etsy.android.lib.models.datatypes.EtsyId
            java.lang.String r1 = r4.d()
            r0.<init>(r1)
            r12.d(r0)
            goto L_0x04c5
        L_0x031b:
            java.lang.String r12 = r4.d()
            r6.checkIdTypeAndSet(r12)
            com.etsy.android.ui.nav.b r12 = r5.g()
            r12.e(r6, r10)
            r11.finish()
            goto L_0x04c5
        L_0x032e:
            boolean r12 = com.etsy.android.lib.shophome.c.a(r0, r1)
            if (r12 == 0) goto L_0x0346
            java.lang.String r12 = r4.d()
            com.etsy.android.lib.shophome.ShopHomeInitialLoadConfiguration r0 = new com.etsy.android.lib.shophome.ShopHomeInitialLoadConfiguration
            r1 = 2
            r0.<init>(r1)
            r5.a(r12, r10, r0)
            r11.finish()
            goto L_0x04c5
        L_0x0346:
            java.lang.String r12 = r4.d()
            r6.checkIdTypeAndSet(r12)
            com.etsy.android.ui.nav.b r12 = r5.g()
            r12.d(r6, r10)
            r11.finish()
            goto L_0x04c5
        L_0x0359:
            boolean r12 = com.etsy.android.lib.shophome.c.a(r0, r1)
            if (r12 == 0) goto L_0x0371
            java.lang.String r12 = r4.d()
            com.etsy.android.lib.shophome.ShopHomeInitialLoadConfiguration r0 = new com.etsy.android.lib.shophome.ShopHomeInitialLoadConfiguration
            r1 = 3
            r0.<init>(r1)
            r5.a(r12, r10, r0)
            r11.finish()
            goto L_0x04c5
        L_0x0371:
            java.lang.String r12 = r4.d()
            r6.checkIdTypeAndSet(r12)
            com.etsy.android.ui.nav.b r12 = r5.g()
            r12.c(r6, r10)
            r11.finish()
            goto L_0x04c5
        L_0x0384:
            r5.q()
            r11.finish()
            goto L_0x04c5
        L_0x038c:
            com.etsy.android.lib.messaging.c r12 = r4.e()
            if (r12 == 0) goto L_0x03c3
            int[] r12 = com.etsy.android.ui.nav.NotificationActivity.AnonymousClass2.a
            com.etsy.android.lib.messaging.c r0 = r4.e()
            com.etsy.android.lib.messaging.EtsyEntity r0 = r0.c()
            int r0 = r0.ordinal()
            r12 = r12[r0]
            if (r12 == r8) goto L_0x03a5
            goto L_0x03c3
        L_0x03a5:
            com.etsy.android.lib.models.datatypes.EtsyId r12 = new com.etsy.android.lib.models.datatypes.EtsyId
            java.lang.String r0 = r4.d()
            r12.<init>(r0)
            com.etsy.android.lib.models.datatypes.EtsyId r0 = new com.etsy.android.lib.models.datatypes.EtsyId
            com.etsy.android.lib.messaging.c r1 = r4.e()
            java.lang.String r1 = r1.d()
            r0.<init>(r1)
            r5.b(r12, r0)
            r11.finish()
            goto L_0x04c5
        L_0x03c3:
            com.etsy.android.lib.models.datatypes.EtsyId r12 = new com.etsy.android.lib.models.datatypes.EtsyId
            java.lang.String r0 = r4.d()
            r12.<init>(r0)
            r5.g(r12, r10)
            r11.finish()
            goto L_0x04c5
        L_0x03d4:
            boolean r12 = com.etsy.android.lib.shophome.c.a(r0, r1)
            if (r12 == 0) goto L_0x03fd
            boolean r12 = com.etsy.android.lib.shophome.c.a(r4)
            if (r12 == 0) goto L_0x03f1
            java.lang.String r12 = r4.d()
            com.etsy.android.lib.shophome.ShopHomeInitialLoadConfiguration r0 = new com.etsy.android.lib.shophome.ShopHomeInitialLoadConfiguration
            java.util.HashMap r1 = r4.f()
            r0.<init>(r8, r1)
            r5.a(r12, r10, r0)
            goto L_0x03f8
        L_0x03f1:
            java.lang.String r12 = r4.d()
            r5.a(r12, r10)
        L_0x03f8:
            r11.finish()
            goto L_0x04c5
        L_0x03fd:
            java.lang.String r12 = r4.d()
            r6.checkIdTypeAndSet(r12)
            r5.b(r6, r10)
            r11.finish()
            goto L_0x04c5
        L_0x040c:
            java.lang.String r12 = r4.d()
            r6.checkIdTypeAndSet(r12)
            r5.f(r6, r10)
            r11.finish()
            goto L_0x04c5
        L_0x041b:
            java.lang.String r12 = r4.d()
            r5.d(r12)
            goto L_0x04c5
        L_0x0424:
            com.etsy.android.lib.models.datatypes.EtsyId r12 = new com.etsy.android.lib.models.datatypes.EtsyId
            java.lang.String r0 = r4.d()
            r12.<init>(r0)
            r5.a(r12, r10)
            goto L_0x04c5
        L_0x0432:
            java.lang.String r12 = r4.d()
            java.lang.String r0 = "new"
            boolean r12 = r12.equals(r0)
            if (r12 == 0) goto L_0x046a
            com.etsy.android.lib.messaging.c r12 = r4.e()
            if (r12 == 0) goto L_0x046a
            com.etsy.android.lib.messaging.c r12 = r4.e()
            com.etsy.android.lib.messaging.EtsyEntity r12 = r12.c()
            com.etsy.android.lib.messaging.EtsyEntity r0 = com.etsy.android.lib.messaging.EtsyEntity.CONVO_COMPOSE
            if (r12 != r0) goto L_0x046a
            android.os.Bundle r12 = new android.os.Bundle
            r12.<init>()
            java.lang.String r0 = "user_id"
            com.etsy.android.lib.messaging.c r1 = r4.e()
            java.lang.String r1 = r1.d()
            long r1 = java.lang.Long.parseLong(r1)
            r12.putLong(r0, r1)
            r5.d(r12)
            goto L_0x04c5
        L_0x046a:
            java.lang.String r12 = r4.d()
            boolean r12 = android.text.TextUtils.isEmpty(r12)
            if (r12 == 0) goto L_0x0494
            java.util.HashMap r12 = r4.f()
            java.lang.String r0 = "username"
            boolean r12 = r12.containsKey(r0)
            if (r12 == 0) goto L_0x0494
            java.lang.String r12 = "username"
            java.lang.String r12 = r4.a(r12)
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r1 = "username"
            r0.putString(r1, r12)
            r5.d(r0)
            goto L_0x04c5
        L_0x0494:
            com.etsy.android.lib.config.a r12 = com.etsy.android.lib.config.a.a()
            com.etsy.android.lib.config.c r12 = r12.d()
            com.etsy.android.lib.config.EtsyConfigKey r0 = com.etsy.android.lib.config.b.ca
            boolean r12 = r12.c(r0)
            if (r12 == 0) goto L_0x04ac
            java.lang.String r12 = r4.d()
            r5.h(r12)
            goto L_0x04c5
        L_0x04ac:
            java.lang.String r12 = r4.d()
            r5.g(r12)
            goto L_0x04c5
        L_0x04b4:
            r5.i()
            r11.finish()
            goto L_0x04c5
        L_0x04bb:
            r5.j()
            r11.finish()
            goto L_0x04c5
        L_0x04c2:
            r11.showHome(r3)
        L_0x04c5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.nav.NotificationActivity.dispatchRoute(org.json.JSONObject):void");
    }

    private Bundle addReferrerData(@Nullable Uri uri) {
        Bundle bundle = new Bundle();
        if (uri != null && "1".equals(uri.getQueryParameter(ETSY_DEEPLINK_PARAM))) {
            if (uri.getQueryParameter("r") != null) {
                bundle.putString(".ref", uri.getQueryParameter("r"));
            }
            if (uri.getQueryParameter("g") != null) {
                bundle.putString("originating_page_guid", uri.getQueryParameter("g"));
            }
            if (uri.getQueryParameter("uaid") != null) {
                bundle.putString("originating_uaid", uri.getQueryParameter("uaid"));
            }
            for (String str : uri.getQueryParameterNames()) {
                if (!str.equals("r") && !str.equals("g") && !str.equals("uaid")) {
                    bundle.putString(str, uri.getQueryParameter(str));
                }
            }
        }
        if (!bundle.containsKey(".ref")) {
            Uri referrer = ActivityCompat.getReferrer(this);
            if (referrer != null) {
                if (referrer.getScheme().equals("http") || referrer.getScheme().equals("https")) {
                    bundle.putString(".ref", referrer.toString());
                } else if (referrer.getScheme().equals("android-app")) {
                    b a = b.a(referrer);
                    if (!"com.google.appcrawler".equals(a.b())) {
                        if (a.c() != null) {
                            bundle.putString(".ref", a.c().toString());
                        }
                        bundle.putString(".ref_package", a.b());
                    }
                }
            }
        }
        if (!bundle.containsKey(".ref")) {
            Intent findLastIntent = findLastIntent();
            if (!(findLastIntent == null || findLastIntent.getComponent() == null || findLastIntent.getComponent().equals(getComponentName()))) {
                bundle.putString(".ref", findLastIntent.getDataString());
                bundle.putString(".ref_package", findLastIntent.getComponent().getPackageName());
            }
        }
        if (uri != null) {
            bundle.putString(".loc", uri.toString());
        }
        return bundle;
    }

    private Intent findLastIntent() {
        List recentTasks = ((ActivityManager) getSystemService("activity")).getRecentTasks(5, 0);
        if (recentTasks.size() > 0) {
            return ((RecentTaskInfo) recentTasks.get(0)).baseIntent;
        }
        return null;
    }

    private void showHome(Intent intent) {
        Bundle bundle = new Bundle();
        if (intent != null) {
            bundle.putBundle("referrer_bundle", addReferrerData(intent.getData()));
        }
        getActivityNavigator().b(bundle);
        finish();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void trackIntent(android.content.Intent r2, java.lang.String r3, com.etsy.android.lib.messaging.c r4) {
        /*
            r1 = this;
            int r4 = r3.hashCode()
            r0 = -1385596165(0xffffffffad697afb, float:-1.3271824E-11)
            if (r4 == r0) goto L_0x0019
            r0 = 1272354024(0x4bd694e8, float:2.8125648E7)
            if (r4 == r0) goto L_0x000f
            goto L_0x0023
        L_0x000f:
            java.lang.String r4 = "notifications"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x0023
            r4 = 1
            goto L_0x0024
        L_0x0019:
            java.lang.String r4 = "external_url"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x0023
            r4 = 0
            goto L_0x0024
        L_0x0023:
            r4 = -1
        L_0x0024:
            switch(r4) {
                case 0: goto L_0x002c;
                case 1: goto L_0x0028;
                default: goto L_0x0027;
            }
        L_0x0027:
            goto L_0x002f
        L_0x0028:
            r1.trackPushNotificationDeeplinkWithGraphite(r2, r3)
            goto L_0x002f
        L_0x002c:
            r1.trackExternalUrlDeeplinkWithGraphite(r2)
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.nav.NotificationActivity.trackIntent(android.content.Intent, java.lang.String, com.etsy.android.lib.messaging.c):void");
    }

    private void trackDeeplink() {
        Uri data = getIntent().getData();
        if (data != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(AnalyticsLogAttribute.LOC, data.toString());
            ((EtsyApplication) getApplication()).getAnalyticsTracker().a("universal_app_link", hashMap);
        }
    }

    private void trackExternalUrlDeeplinkWithGraphite(Intent intent) {
        Uri data = intent.getData();
        if (data == null || data.getScheme() == null) {
            com.etsy.android.lib.logger.a.a.a("route.unknown");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("route.");
        sb.append(data.getScheme());
        com.etsy.android.lib.logger.a.a.a(sb.toString());
        if (!n.a(data.getScheme())) {
            return;
        }
        if ("1".equals(data.getQueryParameter(ETSY_DEEPLINK_PARAM))) {
            com.etsy.android.lib.logger.a.a.a("deeplink.custom");
        } else if ("1".equals(data.getQueryParameter(ETSY_DEFERRED_PARAM))) {
            com.etsy.android.lib.logger.a.a.a("deeplink.custom.deferred");
        }
    }

    private void trackPushNotificationDeeplinkWithGraphite(Intent intent, String str) {
        try {
            g.a(NotificationType.fromString(intent.getExtras().getString("t"))).g();
            com.etsy.android.lib.logger.a.a.a("route.notification");
        } catch (Exception e) {
            if (d.b()) {
                throw e;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Notification Intent extras are null. Source type: ");
            sb.append(str);
            sb.append(" Intent data: ");
            sb.append(intent.getDataString());
            CrashUtil.a().a(new Throwable(sb.toString()));
        }
    }

    private TaxonomyNode getSearchTaxonomyNode(c cVar) {
        if (!cVar.c().equals(EtsyEntity.SEARCH)) {
            return null;
        }
        String a = cVar.a(ResponseConstants.TAXONOMY_ID);
        if (!TextUtils.isEmpty(a)) {
            return new TaxonomyNode(a, "");
        }
        return null;
    }
}
