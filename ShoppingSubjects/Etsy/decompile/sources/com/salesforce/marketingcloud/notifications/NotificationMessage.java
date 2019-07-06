package com.salesforce.marketingcloud.notifications;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.salesforce.marketingcloud.messages.c;
import com.salesforce.marketingcloud.messages.e;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class NotificationMessage implements Parcelable {
    @RestrictTo({Scope.LIBRARY})
    public static final String a = "_m";
    private static final String b = "title";
    private static final String c = "subtitle";
    private static final String d = "alert";
    private static final String e = "sound";
    private static final String f = "_mediaUrl";
    private static final String g = "_mediaAlt";
    private static final String h = "_x";
    private static final String i = "_od";
    private static final List<String> j;
    private static final String k = "default";
    private static final String l = "none";

    public enum Sound {
        CUSTOM,
        DEFAULT,
        NONE
    }

    public enum Trigger {
        PUSH,
        GEOFENCE,
        BEACON
    }

    public enum Type {
        OPEN_DIRECT,
        CLOUD_PAGE,
        OTHER
    }

    @RestrictTo({Scope.LIBRARY})
    public static abstract class a {
        /* access modifiers changed from: 0000 */
        public abstract Sound a();

        /* access modifiers changed from: 0000 */
        public abstract a a(int i);

        /* access modifiers changed from: 0000 */
        public abstract a a(Bundle bundle);

        /* access modifiers changed from: 0000 */
        public abstract a a(Sound sound);

        /* access modifiers changed from: 0000 */
        public abstract a a(Trigger trigger);

        /* access modifiers changed from: 0000 */
        public abstract a a(Type type);

        /* access modifiers changed from: 0000 */
        public abstract a a(String str);

        /* access modifiers changed from: 0000 */
        public abstract a a(Map<String, String> map);

        /* access modifiers changed from: 0000 */
        public abstract a b(int i);

        /* access modifiers changed from: 0000 */
        public abstract a b(String str);

        /* access modifiers changed from: 0000 */
        public abstract String b();

        /* access modifiers changed from: 0000 */
        public abstract a c(String str);

        /* access modifiers changed from: 0000 */
        public abstract NotificationMessage c();

        /* access modifiers changed from: 0000 */
        public abstract a d(String str);

        /* access modifiers changed from: 0000 */
        public NotificationMessage d() {
            if (a() == Sound.CUSTOM && b() == null) {
                a(Sound.DEFAULT);
            }
            return c();
        }

        /* access modifiers changed from: 0000 */
        public abstract a e(String str);

        /* access modifiers changed from: 0000 */
        public abstract a f(String str);

        /* access modifiers changed from: 0000 */
        public abstract a g(String str);

        /* access modifiers changed from: 0000 */
        public abstract a h(String str);

        /* access modifiers changed from: 0000 */
        public abstract a i(String str);

        /* access modifiers changed from: 0000 */
        public abstract a j(String str);
    }

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add(a);
        arrayList.add("title");
        arrayList.add("subtitle");
        arrayList.add(d);
        arrayList.add(e);
        arrayList.add(f);
        arrayList.add(g);
        arrayList.add(h);
        arrayList.add(i);
        arrayList.add("_mt");
        arrayList.add("_h");
        arrayList.add("_r");
        j = Collections.unmodifiableList(arrayList);
    }

    static a a() {
        return new a().a(-1).b(0);
    }

    private static a a(a aVar, String str) {
        Sound sound;
        if (str == null || "none".equalsIgnoreCase(str)) {
            sound = Sound.NONE;
        } else if (k.equalsIgnoreCase(str)) {
            sound = Sound.DEFAULT;
        } else {
            aVar.a(Sound.CUSTOM).d(str);
            return aVar;
        }
        aVar.a(sound);
        return aVar;
    }

    @RestrictTo({Scope.LIBRARY})
    public static NotificationMessage a(@NonNull d dVar, @NonNull Bundle bundle) {
        a g2;
        Type type;
        e a2 = dVar.a();
        ArrayMap arrayMap = new ArrayMap();
        for (String str : bundle.keySet()) {
            if (!j.contains(str) && !str.startsWith("google.")) {
                arrayMap.put(str, bundle.getString(str));
            }
        }
        a a3 = a(a().a(Trigger.PUSH).a(bundle.getString(a)).e(bundle.getString("title", a2.c())).f(bundle.getString("subtitle")).c(bundle.getString(d)).h(bundle.getString(f)).i(bundle.getString(g)).a(bundle).b(a2.b()).a((Map<String, String>) arrayMap), bundle.getString(e));
        if (bundle.containsKey(h)) {
            g2 = a3.g(bundle.getString(h));
            type = Type.CLOUD_PAGE;
        } else if (bundle.containsKey(i)) {
            g2 = a3.g(bundle.getString(i));
            type = Type.OPEN_DIRECT;
        } else {
            a3.a(Type.OTHER);
            return a3.d();
        }
        g2.a(type);
        return a3.d();
    }

    @RestrictTo({Scope.LIBRARY})
    public static NotificationMessage a(d dVar, c cVar, e eVar) {
        a g2;
        Type type;
        Bundle bundle = new Bundle();
        bundle.putString(i, cVar.q());
        bundle.putString(h, cVar.j());
        bundle.putString(e, cVar.d());
        bundle.putString(d, cVar.c());
        bundle.putString(a, cVar.a());
        bundle.putString("regionId", eVar.a());
        Map emptyMap = Collections.emptyMap();
        if (cVar.r() != null && !cVar.r().isEmpty()) {
            emptyMap = cVar.r();
            for (Entry entry : emptyMap.entrySet()) {
                bundle.putString((String) entry.getKey(), (String) entry.getValue());
            }
        }
        bundle.putString("title", cVar.b());
        if (cVar.e() != null) {
            bundle.putString(f, cVar.e().a());
            bundle.putString(g, cVar.e().b());
        }
        e a2 = dVar.a();
        a j2 = a().a(cVar.h() == 5 ? Trigger.BEACON : Trigger.GEOFENCE).a(cVar.a()).b(eVar.a()).c(cVar.c()).a(bundle).b(a2.b()).e(TextUtils.isEmpty(cVar.b()) ? a2.c() : cVar.b()).a(emptyMap).j(cVar.s());
        if (cVar.e() != null) {
            j2.h(cVar.e().a());
            j2.i(cVar.e().b());
        }
        a a3 = a(j2, cVar.d());
        if (cVar.j() != null) {
            g2 = a3.g(cVar.j());
            type = Type.CLOUD_PAGE;
        } else if (cVar.q() != null) {
            g2 = a3.g(cVar.q());
            type = Type.OPEN_DIRECT;
        } else {
            a3.a(Type.OTHER);
            return a3.d();
        }
        g2.a(type);
        return a3.d();
    }

    /* access modifiers changed from: 0000 */
    public abstract NotificationMessage a(int i2);

    @NonNull
    public abstract String alert();

    @Nullable
    public abstract String custom();

    @NonNull
    public abstract Map<String, String> customKeys();

    @NonNull
    public abstract String id();

    @Nullable
    public abstract String mediaAltText();

    @Nullable
    public abstract String mediaUrl();

    public abstract int notificationId();

    @Nullable
    public abstract Bundle payload();

    @Nullable
    public abstract String regionId();

    @DrawableRes
    public abstract int smallIconResId();

    @NonNull
    public abstract Sound sound();

    @Nullable
    public abstract String soundName();

    @Nullable
    public abstract String subTitle();

    @Nullable
    public abstract String title();

    @NonNull
    public abstract Trigger trigger();

    @NonNull
    public abstract Type type();

    @Nullable
    public abstract String url();
}
