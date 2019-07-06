package com.google.android.gms.internal.measurement;

import android.net.Uri;
import java.util.ArrayList;
import java.util.List;

public final class zzez {
    static zzee zzahs;
    static List<zza<Integer>> zzaht = new ArrayList();
    static List<zza<Long>> zzahu = new ArrayList();
    static List<zza<Boolean>> zzahv = new ArrayList();
    static List<zza<String>> zzahw = new ArrayList();
    static List<zza<Double>> zzahx = new ArrayList();
    /* access modifiers changed from: private */
    public static final zzxh zzahy;
    private static zza<Boolean> zzahz = zza.zzb("measurement.log_third_party_store_events_enabled", false, false);
    private static zza<Boolean> zzaia = zza.zzb("measurement.log_installs_enabled", false, false);
    private static zza<Boolean> zzaib = zza.zzb("measurement.log_upgrades_enabled", false, false);
    public static zza<Boolean> zzaic = zza.zzb("measurement.log_androidId_enabled", false, false);
    public static zza<Boolean> zzaid = zza.zzb("measurement.upload_dsid_enabled", false, false);
    public static zza<String> zzaie = zza.zzd("measurement.log_tag", "FA", "FA-SVC");
    public static zza<Long> zzaif = zza.zzb("measurement.ad_id_cache_time", 10000, 10000);
    public static zza<Long> zzaig = zza.zzb("measurement.monitoring.sample_period_millis", 86400000, 86400000);
    public static zza<Long> zzaih = zza.zzb("measurement.config.cache_time", 86400000, 3600000);
    public static zza<String> zzaii;
    public static zza<String> zzaij;
    public static zza<Integer> zzaik = zza.zzc("measurement.upload.max_bundles", 100, 100);
    public static zza<Integer> zzail = zza.zzc("measurement.upload.max_batch_size", 65536, 65536);
    public static zza<Integer> zzaim = zza.zzc("measurement.upload.max_bundle_size", 65536, 65536);
    public static zza<Integer> zzain = zza.zzc("measurement.upload.max_events_per_bundle", 1000, 1000);
    public static zza<Integer> zzaio = zza.zzc("measurement.upload.max_events_per_day", 100000, 100000);
    public static zza<Integer> zzaip = zza.zzc("measurement.upload.max_error_events_per_day", 1000, 1000);
    public static zza<Integer> zzaiq = zza.zzc("measurement.upload.max_public_events_per_day", 50000, 50000);
    public static zza<Integer> zzair = zza.zzc("measurement.upload.max_conversions_per_day", 500, 500);
    public static zza<Integer> zzais = zza.zzc("measurement.upload.max_realtime_events_per_day", 10, 10);
    public static zza<Integer> zzait = zza.zzc("measurement.store.max_stored_events_per_app", 100000, 100000);
    public static zza<String> zzaiu;
    public static zza<Long> zzaiv = zza.zzb("measurement.upload.backoff_period", 43200000, 43200000);
    public static zza<Long> zzaiw = zza.zzb("measurement.upload.window_interval", 3600000, 3600000);
    public static zza<Long> zzaix = zza.zzb("measurement.upload.interval", 3600000, 3600000);
    public static zza<Long> zzaiy = zza.zzb("measurement.upload.realtime_upload_interval", 10000, 10000);
    public static zza<Long> zzaiz = zza.zzb("measurement.upload.debug_upload_interval", 1000, 1000);
    public static zza<Long> zzaja = zza.zzb("measurement.upload.minimum_delay", 500, 500);
    public static zza<Long> zzajb = zza.zzb("measurement.alarm_manager.minimum_interval", 60000, 60000);
    public static zza<Long> zzajc = zza.zzb("measurement.upload.stale_data_deletion_interval", 86400000, 86400000);
    public static zza<Long> zzajd = zza.zzb("measurement.upload.refresh_blacklisted_config_interval", 604800000, 604800000);
    public static zza<Long> zzaje = zza.zzb("measurement.upload.initial_upload_delay_time", 15000, 15000);
    public static zza<Long> zzajf = zza.zzb("measurement.upload.retry_time", 1800000, 1800000);
    public static zza<Integer> zzajg = zza.zzc("measurement.upload.retry_count", 6, 6);
    public static zza<Long> zzajh = zza.zzb("measurement.upload.max_queue_time", 2419200000L, 2419200000L);
    public static zza<Integer> zzaji = zza.zzc("measurement.lifetimevalue.max_currency_tracked", 4, 4);
    public static zza<Integer> zzajj = zza.zzc("measurement.audience.filter_result_max_count", 200, 200);
    public static zza<Long> zzajk = zza.zzb("measurement.service_client.idle_disconnect_millis", 5000, 5000);
    public static zza<Boolean> zzajl = zza.zzb("measurement.test.boolean_flag", false, false);
    public static zza<String> zzajm;
    public static zza<Long> zzajn = zza.zzb("measurement.test.long_flag", -1, -1);
    public static zza<Integer> zzajo = zza.zzc("measurement.test.int_flag", -2, -2);
    public static zza<Double> zzajp = zza.zza("measurement.test.double_flag", -3.0d, -3.0d);
    public static zza<Boolean> zzajq = zza.zzb("measurement.lifetimevalue.user_engagement_tracking_enabled", false, false);
    public static zza<Boolean> zzajr = zza.zzb("measurement.audience.complex_param_evaluation", false, false);
    public static zza<Boolean> zzajs = zza.zzb("measurement.validation.internal_limits_internal_event_params", false, false);
    public static zza<Boolean> zzajt = zza.zzb("measurement.quality.unsuccessful_update_retry_counter", false, false);
    public static zza<Boolean> zzaju = zza.zzb("measurement.iid.disable_on_collection_disabled", true, true);
    public static zza<Boolean> zzajv = zza.zzb("measurement.app_launch.call_only_when_enabled", true, true);
    public static zza<Boolean> zzajw = zza.zzb("measurement.run_on_worker_inline", true, false);
    public static zza<Boolean> zzajx = zza.zzb("measurement.audience.dynamic_filters", false, false);
    public static zza<Boolean> zzajy = zza.zzb("measurement.reset_analytics.persist_time", false, false);
    private static zza<Boolean> zzajz = zza.zzb("measurement.validation.value_and_currency_params", false, false);

    public static final class zza<V> {
        private final V zzaaa;
        private zzwx<V> zzaka;
        private final V zzakb;
        private volatile V zzakc;
        private final String zzny;

        private zza(String str, V v, V v2) {
            this.zzny = str;
            this.zzaaa = v;
            this.zzakb = v2;
        }

        static zza<Double> zza(String str, double d, double d2) {
            zza<Double> zza = new zza<>(str, Double.valueOf(-3.0d), Double.valueOf(-3.0d));
            zzez.zzahx.add(zza);
            return zza;
        }

        static zza<Long> zzb(String str, long j, long j2) {
            zza<Long> zza = new zza<>(str, Long.valueOf(j), Long.valueOf(j2));
            zzez.zzahu.add(zza);
            return zza;
        }

        static zza<Boolean> zzb(String str, boolean z, boolean z2) {
            zza<Boolean> zza = new zza<>(str, Boolean.valueOf(z), Boolean.valueOf(z2));
            zzez.zzahv.add(zza);
            return zza;
        }

        static zza<Integer> zzc(String str, int i, int i2) {
            zza<Integer> zza = new zza<>(str, Integer.valueOf(i), Integer.valueOf(i2));
            zzez.zzaht.add(zza);
            return zza;
        }

        static zza<String> zzd(String str, String str2, String str3) {
            zza<String> zza = new zza<>(str, str2, str3);
            zzez.zzahw.add(zza);
            return zza;
        }

        private static void zzip() {
            synchronized (zza.class) {
                if (!zzee.isMainThread()) {
                    zzee zzee = zzez.zzahs;
                    for (zza zza : zzez.zzahv) {
                        zza.zzakc = zza.zzaka.get();
                    }
                    for (zza zza2 : zzez.zzahw) {
                        zza2.zzakc = zza2.zzaka.get();
                    }
                    for (zza zza3 : zzez.zzahu) {
                        zza3.zzakc = zza3.zzaka.get();
                    }
                    for (zza zza4 : zzez.zzaht) {
                        zza4.zzakc = zza4.zzaka.get();
                    }
                    for (zza zza5 : zzez.zzahx) {
                        zza5.zzakc = zza5.zzaka.get();
                    }
                } else {
                    throw new IllegalStateException("Tried to refresh flag cache on main thread or on package side.");
                }
            }
        }

        /* access modifiers changed from: private */
        public static void zzm() {
            synchronized (zza.class) {
                for (zza zza : zzez.zzahv) {
                    zzxh zzio = zzez.zzahy;
                    String str = zza.zzny;
                    zzee zzee = zzez.zzahs;
                    zza.zzaka = zzio.zzf(str, ((Boolean) zza.zzaaa).booleanValue());
                }
                for (zza zza2 : zzez.zzahw) {
                    zzxh zzio2 = zzez.zzahy;
                    String str2 = zza2.zzny;
                    zzee zzee2 = zzez.zzahs;
                    zza2.zzaka = zzio2.zzv(str2, (String) zza2.zzaaa);
                }
                for (zza zza3 : zzez.zzahu) {
                    zzxh zzio3 = zzez.zzahy;
                    String str3 = zza3.zzny;
                    zzee zzee3 = zzez.zzahs;
                    zza3.zzaka = zzio3.zze(str3, ((Long) zza3.zzaaa).longValue());
                }
                for (zza zza4 : zzez.zzaht) {
                    zzxh zzio4 = zzez.zzahy;
                    String str4 = zza4.zzny;
                    zzee zzee4 = zzez.zzahs;
                    zza4.zzaka = zzio4.zzd(str4, ((Integer) zza4.zzaaa).intValue());
                }
                for (zza zza5 : zzez.zzahx) {
                    zzxh zzio5 = zzez.zzahy;
                    String str5 = zza5.zzny;
                    zzee zzee5 = zzez.zzahs;
                    zza5.zzaka = zzio5.zzb(str5, ((Double) zza5.zzaaa).doubleValue());
                }
            }
        }

        public final V get() {
            if (zzez.zzahs == null) {
                return this.zzaaa;
            }
            zzee zzee = zzez.zzahs;
            if (zzee.isMainThread()) {
                return this.zzakc == null ? this.zzaaa : this.zzakc;
            }
            zzip();
            return this.zzaka.get();
        }

        public final V get(V v) {
            if (v != null) {
                return v;
            }
            if (zzez.zzahs == null) {
                return this.zzaaa;
            }
            zzee zzee = zzez.zzahs;
            if (zzee.isMainThread()) {
                return this.zzakc == null ? this.zzaaa : this.zzakc;
            }
            zzip();
            return this.zzaka.get();
        }

        public final String getKey() {
            return this.zzny;
        }
    }

    static {
        String str = "content://com.google.android.gms.phenotype/";
        String valueOf = String.valueOf(Uri.encode("com.google.android.gms.measurement"));
        zzahy = new zzxh(Uri.parse(valueOf.length() != 0 ? str.concat(valueOf) : new String(str)));
        String str2 = "https";
        zzaii = zza.zzd("measurement.config.url_scheme", str2, str2);
        String str3 = "app-measurement.com";
        zzaij = zza.zzd("measurement.config.url_authority", str3, str3);
        String str4 = "https://app-measurement.com/a";
        zzaiu = zza.zzd("measurement.upload.url", str4, str4);
        String str5 = "---";
        zzajm = zza.zzd("measurement.test.string_flag", str5, str5);
    }

    static void zza(zzee zzee) {
        zzahs = zzee;
        zza.zzm();
    }
}
