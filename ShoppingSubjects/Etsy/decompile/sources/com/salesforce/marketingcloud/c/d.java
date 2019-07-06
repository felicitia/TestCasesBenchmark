package com.salesforce.marketingcloud.c;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.requests.HttpUtil;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.c.e.a;
import com.salesforce.marketingcloud.e.h;
import com.salesforce.marketingcloud.j;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.time.DateUtils;

public enum d {
    ET_ANALYTICS(BaseHttpRequest.POST, "https://consumer.exacttargetapis.com/", "device/v1/event/analytic", HttpUtil.JSON_CONTENT_TYPE, HttpUtil.JSON_CONTENT_TYPE, "et_etanalytic_route_retry_after_time_in_millis"),
    PI_ANALYTICS(BaseHttpRequest.POST, "https://{0}app.igodigital.com/", "api/v1/collect{0}process_batch", HttpUtil.JSON_CONTENT_TYPE, HttpUtil.JSON_CONTENT_TYPE, "et_piwama_route_retry_after_time_in_millis"),
    INBOX_MESSAGE(BaseHttpRequest.GET, "https://consumer.exacttargetapis.com/", "device/v1/{0}/message/?deviceid={1}&messagetype=8&contenttype=2", HttpUtil.JSON_CONTENT_TYPE, HttpUtil.JSON_CONTENT_TYPE, "et_cp_route_retry_after_time_in_millis"),
    INBOX_STATUS("PATCH", "https://consumer.exacttargetapis.com/", "device/v1/{0}/message", HttpUtil.JSON_CONTENT_TYPE, HttpUtil.JSON_CONTENT_TYPE, "et_ims_route_retry_after_time_in_millis"),
    GEOFENCE_MESSAGE(BaseHttpRequest.GET, "https://consumer.exacttargetapis.com/", "device/v1/location/{0}/fence/?latitude={1,number,#.########}&longitude={2,number,#.########}&deviceid={3}", HttpUtil.JSON_CONTENT_TYPE, HttpUtil.JSON_CONTENT_TYPE, "et_geofence_route_retry_after_time_in_millis"),
    PROXIMITY_MESSAGES(BaseHttpRequest.GET, "https://consumer.exacttargetapis.com/", "device/v1/location/{0}/proximity/?latitude={1,number,#.########}&longitude={2,number,#.########}&deviceid={3}", HttpUtil.JSON_CONTENT_TYPE, HttpUtil.JSON_CONTENT_TYPE, "et_proximity_route_retry_after_time_in_millis"),
    REGISTRATION(BaseHttpRequest.POST, "https://consumer.exacttargetapis.com/", "device/v1/registration", HttpUtil.JSON_CONTENT_TYPE, HttpUtil.JSON_CONTENT_TYPE, "et_registration_route_retry_after_time_in_millis"),
    SYNC(BaseHttpRequest.POST, "https://consumer.exacttargetapis.com/", "device/v1/{0}/sync/{1}", HttpUtil.JSON_CONTENT_TYPE, HttpUtil.JSON_CONTENT_TYPE, "et_sync_retry_after");
    
    @RestrictTo({Scope.LIBRARY})
    public static final String i = null;
    @RestrictTo({Scope.LIBRARY})
    public static final String j = "Bearer %s";
    public final String k;
    public final String l;
    public final String m;
    public final String n;
    public final String o;
    public final String p;

    static {
        i = String.format(Locale.ENGLISH, "ETPushSDK/%s (Android)", new Object[]{h.a()});
    }

    private d(String str, String str2, String str3, String str4, String str5, String str6) {
        this.p = str;
        this.k = str2;
        this.l = str3;
        this.n = str4;
        this.o = str5;
        this.m = str6;
    }

    private e a(@NonNull b bVar, @NonNull String str, @NonNull String str2, @Nullable String str3, @Nullable Map<String, String> map) {
        try {
            String c = c(str, str2);
            j.a("MCRequest", "Executing %s request ...", c);
            a d = e.i().a(this.p).a(this).c(this.n).b(str3).d(c);
            d.a("User-Agent", i);
            d.a("Authorization", String.format(Locale.ENGLISH, j, new Object[]{bVar.c()}));
            d.a("Accept", this.o);
            if (map != null && !map.isEmpty()) {
                for (Entry entry : map.entrySet()) {
                    d.a((String) entry.getKey(), (String) entry.getValue());
                }
            }
            return d.c();
        } catch (Exception e) {
            j.c("MCRequest", e, "Failed to execute request.", new Object[0]);
            return null;
        }
    }

    public static Object[] a(String str) {
        return new Object[]{str};
    }

    public static Object[] a(String str, String str2) {
        return new Object[]{str, str2};
    }

    public static Object[] a(String str, String str2, com.salesforce.marketingcloud.location.b bVar) {
        return new Object[]{str, Double.valueOf(bVar.a()), Double.valueOf(bVar.b()), str2};
    }

    public static Object[] b(String str, String str2) {
        return new Object[]{str, str2};
    }

    private String c(String str, String str2) {
        if (str.endsWith("/")) {
            str = str.substring(0, str.length() - 1);
        }
        if (str2.charAt(0) == '/') {
            str2 = str2.replaceFirst("/", "");
        }
        return new URL(String.format(Locale.ENGLISH, "%s/%s", new Object[]{str, str2})).toString();
    }

    /* access modifiers changed from: 0000 */
    public long a(SharedPreferences sharedPreferences) {
        return sharedPreferences.getLong(this.m, 0);
    }

    public e a(@NonNull b bVar, String str) {
        return a(bVar, this.k, this.l, str, null);
    }

    public e a(@NonNull b bVar, @NonNull Object[] objArr) {
        return a(bVar, this.k, MessageFormat.format(this.l, objArr), (String) null, null);
    }

    public e a(@NonNull b bVar, Object[] objArr, String str) {
        return a(bVar, this.k, MessageFormat.format(this.l, objArr), str, null);
    }

    public e a(@NonNull b bVar, Object[] objArr, Object[] objArr2, String str, Map<String, String> map) {
        return a(bVar, MessageFormat.format(this.k, objArr), MessageFormat.format(this.l, objArr2), str, map);
    }

    /* access modifiers changed from: 0000 */
    public void a(@NonNull SharedPreferences sharedPreferences, @NonNull g gVar) {
        if (sharedPreferences != null && gVar != null && gVar.f() != null) {
            List list = (List) gVar.f().get("Retry-After");
            if (list != null && !list.isEmpty()) {
                String str = (String) list.get(0);
                if (TextUtils.isEmpty(str)) {
                    j.b("MCRequest", "Expected a digits-only value for %s", str);
                    return;
                }
                try {
                    long parseLong = Long.parseLong(str) * 1000;
                    if (parseLong > DateUtils.MILLIS_PER_DAY) {
                        parseLong = 86400000;
                    }
                    sharedPreferences.edit().putLong(this.m, parseLong + gVar.d()).apply();
                } catch (Exception e) {
                    j.a("MCRequest", e, "Unable to get retry after value.", new Object[0]);
                }
            }
        }
    }
}
