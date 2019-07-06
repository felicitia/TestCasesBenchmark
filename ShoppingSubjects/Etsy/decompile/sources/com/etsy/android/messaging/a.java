package com.etsy.android.messaging;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.config.g;
import com.etsy.android.lib.core.e;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.a.C0065a;
import com.etsy.android.lib.core.http.request.d;
import com.etsy.android.lib.core.http.url.a.b.c;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.NotificationOptOutBannerSetting;
import com.etsy.android.lib.requests.DeviceRequest;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.uikit.ui.toast.a.C0113a;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;

/* compiled from: EasyOptOutDelegate */
public class a {
    /* access modifiers changed from: private */
    public static final String a = f.a(a.class);
    /* access modifiers changed from: private */
    public Reference<BOENavDrawerActivity> b;
    /* access modifiers changed from: private */
    public String c;

    public a(BOENavDrawerActivity bOENavDrawerActivity) {
        this.b = new WeakReference(bOENavDrawerActivity);
        if (bOENavDrawerActivity.getConfigMap().c(b.by)) {
            this.c = b();
            if (!TextUtils.isEmpty(this.c)) {
                a(this.c);
            }
        }
    }

    @Nullable
    private String b() {
        String str = null;
        if (this.b.get() == null) {
            return null;
        }
        Intent intent = ((BOENavDrawerActivity) this.b.get()).getIntent();
        if (intent != null) {
            str = intent.getStringExtra("opened_notification_type");
        }
        return str;
    }

    private void a(final String str) {
        if (this.b.get() != null) {
            v.a().j().a(this.b.get(), ((com.etsy.android.lib.core.http.request.d.a) com.etsy.android.lib.core.http.request.d.a.a((EtsyApiV3Request) NotificationOptOutBannerSetting.createRequestBuilder(g.a().b(), str).d()).a((C0065a<ResultType>) new d.b<NotificationOptOutBannerSetting>() {
                public void a(@NonNull List<NotificationOptOutBannerSetting> list, int i, @NonNull com.etsy.android.lib.core.a.a<NotificationOptOutBannerSetting> aVar) {
                    if (!list.isEmpty()) {
                        NotificationOptOutBannerSetting notificationOptOutBannerSetting = (NotificationOptOutBannerSetting) list.get(0);
                        String a2 = a.a;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Fetched opt out banner setting for notification type ");
                        sb.append(str);
                        f.c(a2, sb.toString());
                        a.this.a(notificationOptOutBannerSetting);
                    }
                }

                public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<NotificationOptOutBannerSetting> aVar) {
                    String a2 = a.a;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Error fetching notification easy opt out banner setting: ");
                    sb.append(str);
                    f.e(a2, sb.toString());
                }
            }, (Activity) this.b.get())).c());
        }
    }

    /* access modifiers changed from: private */
    public void a(final String str, boolean z) {
        com.etsy.android.lib.core.http.request.EtsyApiV3Request.a aVar = new com.etsy.android.lib.core.http.request.EtsyApiV3Request.a(EmptyResult.class, c.b(g.a().b(), str));
        aVar.a(2);
        aVar.a(ResponseConstants.BANNER_ENABLED, String.valueOf(z));
        v.a().j().a((Object) null, ((com.etsy.android.lib.core.http.request.d.a) com.etsy.android.lib.core.http.request.d.a.a((EtsyApiV3Request) aVar.d()).a((C0065a<ResultType>) new d.b<EmptyResult>() {
            public void a(@NonNull List<EmptyResult> list, int i, @NonNull com.etsy.android.lib.core.a.a<EmptyResult> aVar) {
                String a2 = a.a;
                StringBuilder sb = new StringBuilder();
                sb.append("Updated opt out setting for notification type ");
                sb.append(str);
                f.c(a2, sb.toString());
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<EmptyResult> aVar) {
                String a2 = a.a;
                StringBuilder sb = new StringBuilder();
                sb.append("Error updating notification easy opt out banner setting for notification type ");
                sb.append(str);
                sb.append(": ");
                sb.append(str);
                f.e(a2, sb.toString());
            }
        })).c());
    }

    /* access modifiers changed from: private */
    public void b(final String str, boolean z) {
        e a2 = e.a((EtsyRequest<Result>) DeviceRequest.updateNotificationSetting(g.a().b(), str, z));
        a2.a((com.etsy.android.lib.core.f.c<Result>) new com.etsy.android.lib.core.f.c<BaseModel>() {
            public void a(List<BaseModel> list, int i, k<BaseModel> kVar) {
                String a2 = a.a;
                StringBuilder sb = new StringBuilder();
                sb.append("Successfully updated notification setting for ");
                sb.append(str);
                f.c(a2, sb.toString());
            }
        });
        a2.a((com.etsy.android.lib.core.f.b) new com.etsy.android.lib.core.f.b() {
            public void a(int i, String str, k kVar) {
                String a2 = a.a;
                StringBuilder sb = new StringBuilder();
                sb.append("Error updating notification setting for ");
                sb.append(str);
                f.c(a2, sb.toString());
            }
        });
        v.a().j().a((Object) null, (com.etsy.android.lib.core.g<Result>) a2.a());
    }

    /* access modifiers changed from: private */
    public void a(@NonNull NotificationOptOutBannerSetting notificationOptOutBannerSetting) {
        if (notificationOptOutBannerSetting.getBannerShouldShow() && this.b.get() != null) {
            com.etsy.android.uikit.messaging.a aVar = new com.etsy.android.uikit.messaging.a((Activity) this.b.get());
            aVar.a((CharSequence) notificationOptOutBannerSetting.getBannerText());
            aVar.b(notificationOptOutBannerSetting.getBannerDisableLink());
            aVar.a((C0113a) new C0113a() {
                public void a() {
                    a.this.b(a.this.c, false);
                    a.this.a(a.this.c, false);
                    ((BOENavDrawerActivity) a.this.b.get()).getAnalyticsContext().a("opt_out_push", new EasyOptOutDelegate$5$1(this));
                }

                public void b() {
                    a.this.a(a.this.c, false);
                    ((BOENavDrawerActivity) a.this.b.get()).getAnalyticsContext().a("dismiss_opt_out_banner", new EasyOptOutDelegate$5$2(this));
                }
            });
            aVar.b();
        }
    }
}
