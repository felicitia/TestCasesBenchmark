package com.etsy.android;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.etsy.android.lib.core.f.a;
import com.etsy.android.lib.core.f.b;
import com.etsy.android.lib.core.f.c;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.h;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.l;
import com.etsy.android.lib.core.m;
import com.etsy.android.lib.core.t;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.EtsyArray;
import com.etsy.android.lib.models.EtsyLocale;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequestBatch;
import com.etsy.android.lib.requests.LocaleRequest;
import com.etsy.android.lib.requests.UsersRequest;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.ui.user.i;
import java.util.List;

/* compiled from: GetUserInfoAndCurrencyBatchJob */
public class d extends h {
    /* access modifiers changed from: private */
    public static final String a = f.a(d.class);
    /* access modifiers changed from: private */
    public Context c;

    public d(Context context) {
        this.c = context;
    }

    /* access modifiers changed from: protected */
    public EtsyRequestBatch a_() {
        EtsyRequestBatch etsyRequestBatch = new EtsyRequestBatch();
        etsyRequestBatch.addRequest("user", i());
        etsyRequestBatch.addRequest("locale_prefs", LocaleRequest.getLocale());
        return etsyRequestBatch;
    }

    private EtsyRequest<User> i() {
        UsersRequest self = UsersRequest.getSelf();
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put("fields", "user_id,login_name,awaiting_feedback_count,primary_email,user_pub_key");
        arrayMap.put("includes", "Profile(image_url_75x75,city,region,is_seller,first_name,last_name,login_name,join_tsz,birth_year,birth_month,birth_day)/Country(country_id,name),Shops(shop_id,shop_name,currency_code,icon_url_fullxfull),Addresses(zip,country_id,is_default_shipping)");
        self.addParams(arrayMap);
        return self;
    }

    @Nullable
    public static User a(@Nullable k<EmptyResult> kVar) {
        if (kVar != null && kVar.a()) {
            l lVar = (l) kVar;
            if (lVar.p() > 0) {
                k b = lVar.b("user");
                if (b != null && b.a() && b.i()) {
                    return (User) b.g().get(0);
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void a(k<EmptyResult> kVar) {
        if (kVar == null || !kVar.a()) {
            v.a().a(new EtsyId());
            return;
        }
        l lVar = (l) kVar;
        if (lVar.p() > 0) {
            c(lVar.b("user"));
            d(lVar.b("locale_prefs"));
            return;
        }
        v.a().a(new EtsyId());
    }

    private void c(k<User> kVar) {
        if (kVar == null || !kVar.a() || !kVar.i()) {
            v.a().a(new EtsyId());
            return;
        }
        User user = (User) kVar.g().get(0);
        if (user != null) {
            SharedPreferencesUtility.a(this.c, user);
            v.a().a(user.getUserId());
            if (user.getPublicKey() == null) {
                g().a((g<Result>) new t().a(getClass().toString()));
            }
            j();
        }
    }

    private void d(k<EtsyLocale> kVar) {
        if (kVar != null && kVar.a() && kVar.i()) {
            EtsyLocale etsyLocale = (EtsyLocale) kVar.g().get(0);
            if (etsyLocale != null && etsyLocale.getCurrency() != null) {
                CurrencyUtil.a(this.c, etsyLocale.getCurrency());
            }
        }
    }

    private void j() {
        g().a((Object) this, (g<Result>) m.a(EtsyArray.class, "/etsyapps/v3/bespoke/member/menucounts").a(false).a((c<Result>) new c<EtsyArray>() {
            public void a(List<EtsyArray> list, int i, k<EtsyArray> kVar) {
                if (list.size() > 0) {
                    EtsyArray etsyArray = (EtsyArray) list.get(0);
                    i.a(etsyArray.getData().optInt("open_reviews", 0));
                    i.b(etsyArray.getData().optInt("new_convo_count", 0));
                    i.c(d.this.c);
                }
            }
        }).a((a) new a() {
            public void a(k kVar) {
            }
        }).a((b) new b() {
            public void a(int i, String str, k kVar) {
                f.e(d.a, "Error retrieving the badge counts");
            }
        }).a());
    }
}
