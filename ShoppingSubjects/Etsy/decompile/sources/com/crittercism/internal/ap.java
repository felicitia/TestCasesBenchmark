package com.crittercism.internal;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.commons.lang3.time.DateUtils;

public final class ap {
    public static final b A = new b("data.ndk.rate", Float.valueOf(1.0f));
    public static final a B = new a("data.metadata.enabled", Boolean.valueOf(true));
    public static final a C = new a("reporter.metadata.enabled", Boolean.valueOf(true));
    public static final d D = new d("reporter.metadata.last", Long.valueOf(0));
    public static final d E = new d("reporter.metadata.frequency", Long.valueOf(10000));
    public static final b F = new b("data.metadata.rate", Float.valueOf(1.0f));
    public static final a G = new a("data.config.enabled", Boolean.valueOf(true));
    public static final a H = new a("reporter.config.enabled", Boolean.valueOf(true));
    public static final d I = new d("reporter.config.last", Long.valueOf(0));
    public static final d J = new d("reporter.config.frequency", Long.valueOf(DateUtils.MILLIS_PER_DAY));
    public static final a K = new a("data.dh.config.enabled", Boolean.valueOf(true));
    public static final a L = new a("reporter.dh.config.enabled", Boolean.valueOf(at));
    public static final d M = new d("reporter.dh.config.last", Long.valueOf(0));
    public static final d N = new d("reporter.dh.config.frequency", Long.valueOf(DateUtils.MILLIS_PER_DAY));
    public static final a O = new a("data.dh.region.enabled", Boolean.valueOf(true));
    public static final a P = new a("reporter.dh.region.enabled", Boolean.valueOf(true));
    public static final d Q = new d("reporter.dh.region.last", Long.valueOf(0));
    public static final d R = new d("reporter.dh.region.frequency", Long.valueOf(DateUtils.MILLIS_PER_DAY));
    public static final f S = new f("reporter.dh.region.endpoint.config", "");
    public static final f T = new f("reporter.dh.region.endpoint.events", "");
    public static final a U = new a("data.appload.enabled", Boolean.valueOf(true));
    public static final a V = new a("reporter.appload.enabled", Boolean.valueOf(true));
    public static final d W = new d("reporter.appload.last", Long.valueOf(0));
    public static final d X = new d("reporter.appload.frequency", Long.valueOf(10000));
    public static final b Y = new b("data.appload.rate", Float.valueOf(1.0f));
    public static final a Z = new a("reporter.lumos.appload.enabled", Boolean.valueOf(true));
    public static final a a = new a("instrumentation.apm.enabled", Boolean.valueOf(true));
    public static final d aa = new d("reporter.lumos.appload.last", Long.valueOf(0));
    public static final d ab = new d("reporter.lumos.appload.frequency", Long.valueOf(10000));
    public static final a ac = new a("data.dh.appload.enabled", Boolean.valueOf(true));
    public static final a ad = new a("reporter.dh.appload.enabled", Boolean.valueOf(false));
    public static final d ae = new d("reporter.dh.appload.last", Long.valueOf(0));
    public static final d af = new d("reporter.dh.appload.frequency", Long.valueOf(10000));
    public static final b ag = new b("data.dh.appload.rate", Float.valueOf(1.0f));
    public static final a ah = new a("data.userflow.enabled", Boolean.valueOf(true));
    public static final a ai = new a("reporter.userflow.enabled", Boolean.valueOf(false));
    public static final d aj = new d("reporter.userflow.last", Long.valueOf(0));
    public static final d ak = new d("reporter.userflow.frequency", Long.valueOf(20000));
    public static final b al = new b("data.userflow.rate", Float.valueOf(1.0f));
    public static final a am = new a("data.breadcrumb.foreground.enabled", Boolean.valueOf(true));
    public static final a an = new a("data.breadcrumb.activity.enabled", Boolean.valueOf(true));
    public static final a ao = new a("data.breadcrumb.networkstate.enabled", Boolean.valueOf(true));
    public static final a ap = new a("data.breadcrumb.networkstats.enabled", Boolean.valueOf(true));
    public static final a aq = new a("data.breadcrumb.exception.enabled", Boolean.valueOf(true));
    public static final d ar = new d("userflow.defaultTimeoutMs", Long.valueOf(DateUtils.MILLIS_PER_HOUR));
    private static final boolean at = "true".equals(System.getProperty("com.crittercism.dhubConfigReporterEnabled", "false"));
    public static final a b = new a("instrumentation.apm.nougat.enabled", Boolean.valueOf(true));
    public static final a c = new a("data.apm.enabled", Boolean.valueOf(true));
    public static final a d = new a("reporter.apm.enabled", Boolean.valueOf(false));
    public static final d e = new d("reporter.apm.last", Long.valueOf(0));
    public static final d f = new d("reporter.apm.frequency", Long.valueOf(20000));
    public static final b g = new b("data.apm.rate", Float.valueOf(1.0f));
    public static final a h = new a("data.dh.apm.enabled", Boolean.valueOf(true));
    public static final a i = new a("reporter.dh.apm.enabled", Boolean.valueOf(false));
    public static final d j = new d("reporter.dh.apm.last", Long.valueOf(0));
    public static final d k = new d("reporter.dh.apm.frequency", Long.valueOf(20000));
    public static final b l = new b("data.dh.apm.rate", Float.valueOf(1.0f));
    public static final a m = new a("data.crash.enabled", Boolean.valueOf(true));
    public static final a n = new a("reporter.crash.enabled", Boolean.valueOf(true));
    public static final d o = new d("reporter.crash.last", Long.valueOf(0));
    public static final d p = new d("reporter.crash.frequency", Long.valueOf(0));
    public static final b q = new b("data.crash.rate", Float.valueOf(1.0f));
    public static final a r = new a("data.he.enabled", Boolean.valueOf(true));
    public static final a s = new a("reporter.he.enabled", Boolean.valueOf(true));
    public static final d t = new d("reporter.he.last", Long.valueOf(0));
    public static final d u = new d("reporter.he.frequency", Long.valueOf(DateUtils.MILLIS_PER_MINUTE));
    public static final b v = new b("data.he.rate", Float.valueOf(1.0f));
    public static final a w = new a("data.ndk.enabled", Boolean.valueOf(true));
    public static final a x = new a("reporter.ndk.enabled", Boolean.valueOf(true));
    public static final d y = new d("reporter.ndk.last", Long.valueOf(0));
    public static final d z = new d("reporter.ndk.frequency", Long.valueOf(10000));
    public final LinkedList<c> as = new LinkedList<>();
    private String au;
    private Context av;
    private SharedPreferences aw;

    public static class a extends e<Boolean> {
        public final /* synthetic */ void a(SharedPreferences sharedPreferences, Object obj) {
            sharedPreferences.edit().putBoolean(this.a, ((Boolean) obj).booleanValue()).commit();
        }

        public a(String str, Boolean bool) {
            super(str, bool);
        }

        public final /* synthetic */ Object a(SharedPreferences sharedPreferences) {
            return Boolean.valueOf(sharedPreferences.getBoolean(this.a, ((Boolean) this.b).booleanValue()));
        }
    }

    public static class b extends e<Float> {
        public final /* synthetic */ void a(SharedPreferences sharedPreferences, Object obj) {
            sharedPreferences.edit().putFloat(this.a, ((Float) obj).floatValue()).commit();
        }

        public b(String str, Float f) {
            super(str, f);
        }

        public final /* synthetic */ Object a(SharedPreferences sharedPreferences) {
            return Float.valueOf(sharedPreferences.getFloat(this.a, ((Float) this.b).floatValue()));
        }
    }

    public interface c {
        void a(ap apVar, String str);
    }

    public static class d extends e<Long> {
        public final /* synthetic */ void a(SharedPreferences sharedPreferences, Object obj) {
            sharedPreferences.edit().putLong(this.a, ((Long) obj).longValue()).commit();
        }

        public d(String str, Long l) {
            super(str, l);
        }

        public final /* synthetic */ Object a(SharedPreferences sharedPreferences) {
            return Long.valueOf(sharedPreferences.getLong(this.a, ((Long) this.b).longValue()));
        }
    }

    public static abstract class e<T> {
        protected String a;
        protected T b;

        public abstract T a(SharedPreferences sharedPreferences);

        public abstract void a(SharedPreferences sharedPreferences, T t);

        public e(String str, T t) {
            this.a = str;
            this.b = t;
        }

        public final String a() {
            return this.a;
        }

        public final T b() {
            return this.b;
        }
    }

    public static class f extends e<String> {
        public final /* synthetic */ void a(SharedPreferences sharedPreferences, Object obj) {
            sharedPreferences.edit().putString(this.a, (String) obj).commit();
        }

        public f(String str, String str2) {
            super(str, str2);
        }

        public final /* synthetic */ Object a(SharedPreferences sharedPreferences) {
            return sharedPreferences.getString(this.a, (String) this.b);
        }
    }

    public static d a(String str, long j2) {
        StringBuilder sb = new StringBuilder("userflow.timeouts.");
        sb.append(str);
        return new d(sb.toString(), Long.valueOf(j2));
    }

    public ap(Context context, String str) {
        this.au = str;
        this.av = context;
    }

    private SharedPreferences a() {
        if (this.aw == null) {
            Context context = this.av;
            StringBuilder sb = new StringBuilder("com.crittercism.settings.");
            sb.append(this.au);
            this.aw = context.getSharedPreferences(sb.toString(), 0);
        }
        return this.aw;
    }

    public final <T> T a(e<T> eVar) {
        return eVar.a(a());
    }

    public final <T> void a(e<T> eVar, T t2) {
        eVar.a(a(), t2);
        Iterator it = this.as.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(this, eVar.a);
        }
    }

    public final void b(String str, long j2) {
        a((e<T>) a(str, j2), (T) Long.valueOf(j2));
    }
}
