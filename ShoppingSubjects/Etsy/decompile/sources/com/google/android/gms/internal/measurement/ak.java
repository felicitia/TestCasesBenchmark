package com.google.android.gms.internal.measurement;

import android.net.Uri;
import android.support.annotation.WorkerThread;
import com.etsy.android.lib.models.apiv3.editable.EditableInventoryValue;
import com.etsy.android.uikit.adapter.AbstractContextRecyclerViewAdapter;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;

@VisibleForTesting
public final class ak {
    public static a<Long> A = a.a("measurement.upload.window_interval", (long) DateUtils.MILLIS_PER_HOUR, (long) DateUtils.MILLIS_PER_HOUR);
    public static a<Long> B = a.a("measurement.upload.interval", (long) DateUtils.MILLIS_PER_HOUR, (long) DateUtils.MILLIS_PER_HOUR);
    public static a<Long> C = a.a("measurement.upload.realtime_upload_interval", 10000, 10000);
    public static a<Long> D = a.a("measurement.upload.debug_upload_interval", 1000, 1000);
    public static a<Long> E = a.a("measurement.upload.minimum_delay", 500, 500);
    public static a<Long> F = a.a("measurement.alarm_manager.minimum_interval", (long) DateUtils.MILLIS_PER_MINUTE, (long) DateUtils.MILLIS_PER_MINUTE);
    public static a<Long> G = a.a("measurement.upload.stale_data_deletion_interval", (long) DateUtils.MILLIS_PER_DAY, (long) DateUtils.MILLIS_PER_DAY);
    public static a<Long> H = a.a("measurement.upload.refresh_blacklisted_config_interval", 604800000, 604800000);
    public static a<Long> I = a.a("measurement.upload.initial_upload_delay_time", 15000, 15000);
    public static a<Long> J = a.a("measurement.upload.retry_time", 1800000, 1800000);
    public static a<Integer> K = a.a("measurement.upload.retry_count", 6, 6);
    public static a<Long> L = a.a("measurement.upload.max_queue_time", 2419200000L, 2419200000L);
    public static a<Integer> M = a.a("measurement.lifetimevalue.max_currency_tracked", 4, 4);
    public static a<Integer> N = a.a("measurement.audience.filter_result_max_count", 200, 200);
    public static a<Long> O = a.a("measurement.service_client.idle_disconnect_millis", 5000, 5000);
    public static a<Boolean> P = a.a("measurement.test.boolean_flag", false, false);
    public static a<String> Q;
    public static a<Long> R = a.a("measurement.test.long_flag", -1, -1);
    public static a<Integer> S = a.a("measurement.test.int_flag", -2, -2);
    public static a<Double> T = a.a("measurement.test.double_flag", -3.0d, -3.0d);
    public static a<Boolean> U = a.a("measurement.lifetimevalue.user_engagement_tracking_enabled", false, false);
    public static a<Boolean> V = a.a("measurement.audience.complex_param_evaluation", false, false);
    public static a<Boolean> W = a.a("measurement.validation.internal_limits_internal_event_params", false, false);
    public static a<Boolean> X = a.a("measurement.quality.unsuccessful_update_retry_counter", false, false);
    public static a<Boolean> Y = a.a("measurement.iid.disable_on_collection_disabled", true, true);
    public static a<Boolean> Z = a.a("measurement.app_launch.call_only_when_enabled", true, true);
    static v a;
    public static a<Boolean> aa = a.a("measurement.run_on_worker_inline", true, false);
    public static a<Boolean> ab = a.a("measurement.audience.dynamic_filters", false, false);
    public static a<Boolean> ac = a.a("measurement.reset_analytics.persist_time", false, false);
    /* access modifiers changed from: private */
    public static final gs ad;
    private static a<Boolean> ae = a.a("measurement.log_third_party_store_events_enabled", false, false);
    private static a<Boolean> af = a.a("measurement.log_installs_enabled", false, false);
    private static a<Boolean> ag = a.a("measurement.log_upgrades_enabled", false, false);
    private static a<Boolean> ah = a.a("measurement.validation.value_and_currency_params", false, false);
    static List<a<Integer>> b = new ArrayList();
    static List<a<Long>> c = new ArrayList();
    static List<a<Boolean>> d = new ArrayList();
    static List<a<String>> e = new ArrayList();
    static List<a<Double>> f = new ArrayList();
    public static a<Boolean> g = a.a("measurement.log_androidId_enabled", false, false);
    public static a<Boolean> h = a.a("measurement.upload_dsid_enabled", false, false);
    public static a<String> i = a.a("measurement.log_tag", "FA", "FA-SVC");
    public static a<Long> j = a.a("measurement.ad_id_cache_time", 10000, 10000);
    public static a<Long> k = a.a("measurement.monitoring.sample_period_millis", (long) DateUtils.MILLIS_PER_DAY, (long) DateUtils.MILLIS_PER_DAY);
    public static a<Long> l = a.a("measurement.config.cache_time", (long) DateUtils.MILLIS_PER_DAY, (long) DateUtils.MILLIS_PER_HOUR);
    public static a<String> m;
    public static a<String> n;
    public static a<Integer> o = a.a("measurement.upload.max_bundles", 100, 100);
    public static a<Integer> p = a.a("measurement.upload.max_batch_size", 65536, 65536);
    public static a<Integer> q = a.a("measurement.upload.max_bundle_size", 65536, 65536);
    public static a<Integer> r = a.a("measurement.upload.max_events_per_bundle", 1000, 1000);
    public static a<Integer> s = a.a("measurement.upload.max_events_per_day", (int) EditableInventoryValue.ROOT_ID, (int) EditableInventoryValue.ROOT_ID);
    public static a<Integer> t = a.a("measurement.upload.max_error_events_per_day", 1000, 1000);
    public static a<Integer> u = a.a("measurement.upload.max_public_events_per_day", 50000, 50000);
    public static a<Integer> v = a.a("measurement.upload.max_conversions_per_day", (int) AbstractContextRecyclerViewAdapter.VIEW_TYPE_HEADER, (int) AbstractContextRecyclerViewAdapter.VIEW_TYPE_HEADER);
    public static a<Integer> w = a.a("measurement.upload.max_realtime_events_per_day", 10, 10);
    public static a<Integer> x = a.a("measurement.store.max_stored_events_per_app", (int) EditableInventoryValue.ROOT_ID, (int) EditableInventoryValue.ROOT_ID);
    public static a<String> y;
    public static a<Long> z = a.a("measurement.upload.backoff_period", 43200000, 43200000);

    @VisibleForTesting
    public static final class a<V> {
        private gi<V> a;
        private final V b;
        private final V c;
        private volatile V d;
        private final String e;

        private a(String str, V v, V v2) {
            this.e = str;
            this.c = v;
            this.b = v2;
        }

        static a<Double> a(String str, double d2, double d3) {
            a<Double> aVar = new a<>(str, Double.valueOf(-3.0d), Double.valueOf(-3.0d));
            ak.f.add(aVar);
            return aVar;
        }

        static a<Integer> a(String str, int i, int i2) {
            a<Integer> aVar = new a<>(str, Integer.valueOf(i), Integer.valueOf(i2));
            ak.b.add(aVar);
            return aVar;
        }

        static a<Long> a(String str, long j, long j2) {
            a<Long> aVar = new a<>(str, Long.valueOf(j), Long.valueOf(j2));
            ak.c.add(aVar);
            return aVar;
        }

        static a<String> a(String str, String str2, String str3) {
            a<String> aVar = new a<>(str, str2, str3);
            ak.e.add(aVar);
            return aVar;
        }

        static a<Boolean> a(String str, boolean z, boolean z2) {
            a<Boolean> aVar = new a<>(str, Boolean.valueOf(z), Boolean.valueOf(z2));
            ak.d.add(aVar);
            return aVar;
        }

        /* access modifiers changed from: private */
        public static void d() {
            synchronized (a.class) {
                for (a aVar : ak.d) {
                    gs a2 = ak.ad;
                    String str = aVar.e;
                    v vVar = ak.a;
                    aVar.a = a2.a(str, ((Boolean) aVar.c).booleanValue());
                }
                for (a aVar2 : ak.e) {
                    gs a3 = ak.ad;
                    String str2 = aVar2.e;
                    v vVar2 = ak.a;
                    aVar2.a = a3.a(str2, (String) aVar2.c);
                }
                for (a aVar3 : ak.c) {
                    gs a4 = ak.ad;
                    String str3 = aVar3.e;
                    v vVar3 = ak.a;
                    aVar3.a = a4.a(str3, ((Long) aVar3.c).longValue());
                }
                for (a aVar4 : ak.b) {
                    gs a5 = ak.ad;
                    String str4 = aVar4.e;
                    v vVar4 = ak.a;
                    aVar4.a = a5.a(str4, ((Integer) aVar4.c).intValue());
                }
                for (a aVar5 : ak.f) {
                    gs a6 = ak.ad;
                    String str5 = aVar5.e;
                    v vVar5 = ak.a;
                    aVar5.a = a6.a(str5, ((Double) aVar5.c).doubleValue());
                }
            }
        }

        @WorkerThread
        private static void e() {
            synchronized (a.class) {
                if (!v.a()) {
                    v vVar = ak.a;
                    for (a aVar : ak.d) {
                        aVar.d = aVar.a.a();
                    }
                    for (a aVar2 : ak.e) {
                        aVar2.d = aVar2.a.a();
                    }
                    for (a aVar3 : ak.c) {
                        aVar3.d = aVar3.a.a();
                    }
                    for (a aVar4 : ak.b) {
                        aVar4.d = aVar4.a.a();
                    }
                    for (a aVar5 : ak.f) {
                        aVar5.d = aVar5.a.a();
                    }
                } else {
                    throw new IllegalStateException("Tried to refresh flag cache on main thread or on package side.");
                }
            }
        }

        public final V a(V v) {
            if (v != null) {
                return v;
            }
            if (ak.a == null) {
                return this.c;
            }
            v vVar = ak.a;
            if (v.a()) {
                return this.d == null ? this.c : this.d;
            }
            e();
            return this.a.a();
        }

        public final String a() {
            return this.e;
        }

        public final V b() {
            if (ak.a == null) {
                return this.c;
            }
            v vVar = ak.a;
            if (v.a()) {
                return this.d == null ? this.c : this.d;
            }
            e();
            return this.a.a();
        }
    }

    static {
        String str = "content://com.google.android.gms.phenotype/";
        String valueOf = String.valueOf(Uri.encode("com.google.android.gms.measurement"));
        ad = new gs(Uri.parse(valueOf.length() != 0 ? str.concat(valueOf) : new String(str)));
        String str2 = "https";
        m = a.a("measurement.config.url_scheme", str2, str2);
        String str3 = "app-measurement.com";
        n = a.a("measurement.config.url_authority", str3, str3);
        String str4 = "https://app-measurement.com/a";
        y = a.a("measurement.upload.url", str4, str4);
        String str5 = "---";
        Q = a.a("measurement.test.string_flag", str5, str5);
    }

    static void a(v vVar) {
        a = vVar;
        a.d();
    }
}
