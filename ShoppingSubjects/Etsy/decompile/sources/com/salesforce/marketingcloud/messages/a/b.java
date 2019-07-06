package com.salesforce.marketingcloud.messages.a;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.etsy.android.lib.models.datatypes.ShopHomeSortOption;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.messages.c.a.C0169a;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@Deprecated
public abstract class b implements com.salesforce.marketingcloud.messages.c.a, Cloneable {
    /* access modifiers changed from: private */
    public static final String a = j.a(b.class);
    private static final List<String> b;
    private boolean c;
    private boolean d;

    public static abstract class a implements C0169a {
        @RestrictTo({Scope.LIBRARY})
        public static a a(String str, String str2) {
            if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
                return new e(str, str2);
            }
            throw new IllegalStateException("Invalid media provided.");
        }

        @Nullable
        public abstract String a();

        @Nullable
        public abstract String b();
    }

    /* renamed from: com.salesforce.marketingcloud.messages.a.b$b reason: collision with other inner class name */
    public static abstract class C0168b {
        public abstract C0168b a(int i);

        public abstract C0168b a(a aVar);

        public abstract C0168b a(String str);

        public abstract C0168b a(Date date);

        public abstract C0168b a(Map<String, String> map);

        public abstract b a();

        public abstract C0168b b(int i);

        public abstract C0168b b(String str);

        public abstract C0168b b(Date date);

        public abstract C0168b c(String str);

        public abstract C0168b d(String str);

        public abstract C0168b e(String str);

        public abstract C0168b f(String str);

        public abstract C0168b g(String str);

        public abstract C0168b h(String str);

        public abstract C0168b i(String str);
    }

    @RestrictTo({Scope.LIBRARY})
    public static class c {
        public a a(JSONObject jSONObject, String str) {
            JSONObject optJSONObject = jSONObject.optJSONObject(str);
            if (optJSONObject != null) {
                try {
                    return a.a(optJSONObject.optString("androidUrl"), optJSONObject.optString("alt"));
                } catch (Exception e) {
                    j.c(b.a, e, "Unable to create media object from json: ", optJSONObject);
                }
            }
            return null;
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add(NotificationMessage.a);
        arrayList.add("title");
        arrayList.add("subtitle");
        arrayList.add("alert");
        arrayList.add("sound");
        arrayList.add("_mediaUrl");
        arrayList.add("_mediaAlt");
        arrayList.add("_x");
        arrayList.add("_od");
        arrayList.add("_mt");
        arrayList.add("_h");
        arrayList.add("_r");
        arrayList.add("_sid");
        arrayList.add("timestamp");
        arrayList.add(ShopHomeSortOption.SORT_CUSTOM);
        b = Collections.unmodifiableList(arrayList);
    }

    @RestrictTo({Scope.LIBRARY})
    public static b a(@NonNull Bundle bundle) {
        HashMap hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            if (!b.contains(str) && !str.startsWith("google.")) {
                hashMap.put(str, bundle.getString(str));
            }
        }
        a aVar = null;
        String string = bundle.getString("_mediaUrl");
        String string2 = bundle.getString("_mediaAlt");
        if (!TextUtils.isEmpty(string)) {
            aVar = a.a(string, string2);
        }
        C0168b i = q().h(bundle.getString(NotificationMessage.a)).e(bundle.getString("title")).f(bundle.getString("alert")).b(2).a(8).a((Map<String, String>) hashMap).d(bundle.getString(ShopHomeSortOption.SORT_CUSTOM)).g(bundle.getString("sound")).a(bundle.getString("_r")).b(bundle.getString("_h")).i(bundle.getString("_x"));
        if (aVar != null) {
            i.a(aVar);
        }
        return i.a();
    }

    @VisibleForTesting(otherwise = 3)
    @RestrictTo({Scope.LIBRARY})
    public static b a(JSONObject jSONObject) {
        return d.b(jSONObject);
    }

    @RestrictTo({Scope.LIBRARY})
    public static C0168b q() {
        return new C0167a();
    }

    @Nullable
    public abstract String a();

    public final void a(boolean z) {
        this.c = z;
    }

    @Nullable
    public abstract String b();

    public final void b(boolean z) {
        this.d = z;
    }

    @Nullable
    public abstract String c();

    @Nullable
    public abstract Map<String, String> d();

    @Nullable
    public abstract String e();

    @Nullable
    public abstract String f();

    @Nullable
    public abstract String g();

    @Nullable
    public abstract String h();

    @Nullable
    /* renamed from: i */
    public abstract a p();

    @NonNull
    public abstract String j();

    @Nullable
    public abstract Date k();

    @Nullable
    public abstract Date l();

    public abstract int m();

    public abstract int n();

    @NonNull
    public abstract String o();

    public final boolean r() {
        return this.c;
    }

    public final boolean s() {
        return this.d;
    }
}
