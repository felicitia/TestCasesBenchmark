package com.google.android.gms.analytics;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzaa;
import com.google.android.gms.internal.measurement.zzab;
import com.google.android.gms.internal.measurement.zzac;
import com.google.android.gms.internal.measurement.zzad;
import com.google.android.gms.internal.measurement.zzae;
import com.google.android.gms.internal.measurement.zzaf;
import com.google.android.gms.internal.measurement.zzag;
import com.google.android.gms.internal.measurement.zzaq;
import com.google.android.gms.internal.measurement.zzas;
import com.google.android.gms.internal.measurement.zzat;
import com.google.android.gms.internal.measurement.zzaw;
import com.google.android.gms.internal.measurement.zzch;
import com.google.android.gms.internal.measurement.zzdd;
import com.google.android.gms.internal.measurement.zzu;
import com.google.android.gms.internal.measurement.zzv;
import com.google.android.gms.internal.measurement.zzw;
import com.google.android.gms.internal.measurement.zzx;
import com.google.android.gms.internal.measurement.zzy;
import com.google.android.gms.internal.measurement.zzz;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzb extends zzaq implements zzo {
    private static DecimalFormat zzqq;
    private final zzat zzqm;
    private final String zzqr;
    private final Uri zzqs;

    public zzb(zzat zzat, String str) {
        this(zzat, str, true, false);
    }

    private zzb(zzat zzat, String str, boolean z, boolean z2) {
        super(zzat);
        Preconditions.checkNotEmpty(str);
        this.zzqm = zzat;
        this.zzqr = str;
        this.zzqs = zzb(this.zzqr);
    }

    private static String zza(double d) {
        if (zzqq == null) {
            zzqq = new DecimalFormat("0.######");
        }
        return zzqq.format(d);
    }

    private static void zza(Map<String, String> map, String str, double d) {
        if (d != 0.0d) {
            map.put(str, zza(d));
        }
    }

    private static void zza(Map<String, String> map, String str, int i, int i2) {
        if (i > 0 && i2 > 0) {
            StringBuilder sb = new StringBuilder(23);
            sb.append(i);
            sb.append("x");
            sb.append(i2);
            map.put(str, sb.toString());
        }
    }

    private static void zza(Map<String, String> map, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            map.put(str, str2);
        }
    }

    private static void zza(Map<String, String> map, String str, boolean z) {
        if (z) {
            map.put(str, "1");
        }
    }

    static Uri zzb(String str) {
        Preconditions.checkNotEmpty(str);
        Builder builder = new Builder();
        builder.scheme("uri");
        builder.authority("google-analytics.com");
        builder.path(str);
        return builder.build();
    }

    private static Map<String, String> zzc(zzg zzg) {
        HashMap hashMap = new HashMap();
        zzy zzy = (zzy) zzg.zza(zzy.class);
        if (zzy != null) {
            for (Entry entry : zzy.zzas().entrySet()) {
                Object value = entry.getValue();
                String str = null;
                if (value != null) {
                    if (value instanceof String) {
                        String str2 = (String) value;
                        if (!TextUtils.isEmpty(str2)) {
                            str = str2;
                        }
                    } else if (value instanceof Double) {
                        Double d = (Double) value;
                        if (d.doubleValue() != 0.0d) {
                            str = zza(d.doubleValue());
                        }
                    } else if (!(value instanceof Boolean)) {
                        str = String.valueOf(value);
                    } else if (value != Boolean.FALSE) {
                        str = "1";
                    }
                }
                if (str != null) {
                    hashMap.put((String) entry.getKey(), str);
                }
            }
        }
        zzad zzad = (zzad) zzg.zza(zzad.class);
        if (zzad != null) {
            zza((Map<String, String>) hashMap, "t", zzad.zzay());
            zza((Map<String, String>) hashMap, "cid", zzad.zzaz());
            zza((Map<String, String>) hashMap, "uid", zzad.zzba());
            zza((Map<String, String>) hashMap, "sc", zzad.zzbd());
            zza((Map<String, String>) hashMap, "sf", zzad.zzbf());
            zza((Map<String, String>) hashMap, "ni", zzad.zzbe());
            zza((Map<String, String>) hashMap, "adid", zzad.zzbb());
            zza((Map<String, String>) hashMap, "ate", zzad.zzbc());
        }
        zzae zzae = (zzae) zzg.zza(zzae.class);
        if (zzae != null) {
            zza((Map<String, String>) hashMap, "cd", zzae.zzbg());
            zza((Map<String, String>) hashMap, "a", (double) zzae.zzbh());
            zza((Map<String, String>) hashMap, "dr", zzae.zzbi());
        }
        zzab zzab = (zzab) zzg.zza(zzab.class);
        if (zzab != null) {
            zza((Map<String, String>) hashMap, "ec", zzab.zzax());
            zza((Map<String, String>) hashMap, "ea", zzab.getAction());
            zza((Map<String, String>) hashMap, "el", zzab.getLabel());
            zza((Map<String, String>) hashMap, "ev", (double) zzab.getValue());
        }
        zzv zzv = (zzv) zzg.zza(zzv.class);
        if (zzv != null) {
            zza((Map<String, String>) hashMap, "cn", zzv.getName());
            zza((Map<String, String>) hashMap, "cs", zzv.getSource());
            zza((Map<String, String>) hashMap, "cm", zzv.zzaj());
            zza((Map<String, String>) hashMap, "ck", zzv.zzak());
            zza((Map<String, String>) hashMap, "cc", zzv.zzal());
            zza((Map<String, String>) hashMap, "ci", zzv.getId());
            zza((Map<String, String>) hashMap, "anid", zzv.zzam());
            zza((Map<String, String>) hashMap, "gclid", zzv.zzan());
            zza((Map<String, String>) hashMap, "dclid", zzv.zzao());
            zza((Map<String, String>) hashMap, "aclid", zzv.zzap());
        }
        zzac zzac = (zzac) zzg.zza(zzac.class);
        if (zzac != null) {
            zza((Map<String, String>) hashMap, "exd", zzac.zzua);
            zza((Map<String, String>) hashMap, "exf", zzac.zzub);
        }
        zzaf zzaf = (zzaf) zzg.zza(zzaf.class);
        if (zzaf != null) {
            zza((Map<String, String>) hashMap, "sn", zzaf.zzur);
            zza((Map<String, String>) hashMap, "sa", zzaf.zzus);
            zza((Map<String, String>) hashMap, "st", zzaf.zzut);
        }
        zzag zzag = (zzag) zzg.zza(zzag.class);
        if (zzag != null) {
            zza((Map<String, String>) hashMap, "utv", zzag.zzuu);
            zza((Map<String, String>) hashMap, "utt", (double) zzag.zzuv);
            zza((Map<String, String>) hashMap, "utc", zzag.mCategory);
            zza((Map<String, String>) hashMap, "utl", zzag.zzuw);
        }
        zzw zzw = (zzw) zzg.zza(zzw.class);
        if (zzw != null) {
            for (Entry entry2 : zzw.zzaq().entrySet()) {
                String zzc = zzd.zzc(((Integer) entry2.getKey()).intValue());
                if (!TextUtils.isEmpty(zzc)) {
                    hashMap.put(zzc, (String) entry2.getValue());
                }
            }
        }
        zzx zzx = (zzx) zzg.zza(zzx.class);
        if (zzx != null) {
            for (Entry entry3 : zzx.zzar().entrySet()) {
                String zze = zzd.zze(((Integer) entry3.getKey()).intValue());
                if (!TextUtils.isEmpty(zze)) {
                    hashMap.put(zze, zza(((Double) entry3.getValue()).doubleValue()));
                }
            }
        }
        zzaa zzaa = (zzaa) zzg.zza(zzaa.class);
        if (zzaa != null) {
            ProductAction zzat = zzaa.zzat();
            if (zzat != null) {
                for (Entry entry4 : zzat.build().entrySet()) {
                    hashMap.put(((String) entry4.getKey()).startsWith("&") ? ((String) entry4.getKey()).substring(1) : (String) entry4.getKey(), (String) entry4.getValue());
                }
            }
            int i = 1;
            for (Promotion zzn : zzaa.zzaw()) {
                hashMap.putAll(zzn.zzn(zzd.zzi(i)));
                i++;
            }
            int i2 = 1;
            for (Product zzn2 : zzaa.zzau()) {
                hashMap.putAll(zzn2.zzn(zzd.zzg(i2)));
                i2++;
            }
            int i3 = 1;
            for (Entry entry5 : zzaa.zzav().entrySet()) {
                List<Product> list = (List) entry5.getValue();
                String zzl = zzd.zzl(i3);
                int i4 = 1;
                for (Product product : list) {
                    String valueOf = String.valueOf(zzl);
                    String valueOf2 = String.valueOf(zzd.zzj(i4));
                    hashMap.putAll(product.zzn(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
                    i4++;
                }
                if (!TextUtils.isEmpty((CharSequence) entry5.getKey())) {
                    String valueOf3 = String.valueOf(zzl);
                    String valueOf4 = String.valueOf("nm");
                    hashMap.put(valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), (String) entry5.getKey());
                }
                i3++;
            }
        }
        zzz zzz = (zzz) zzg.zza(zzz.class);
        if (zzz != null) {
            zza((Map<String, String>) hashMap, "ul", zzz.getLanguage());
            zza((Map<String, String>) hashMap, "sd", (double) zzz.zztu);
            zza(hashMap, "sr", zzz.zztv, zzz.zztw);
            zza(hashMap, "vp", zzz.zztx, zzz.zzty);
        }
        zzu zzu = (zzu) zzg.zza(zzu.class);
        if (zzu != null) {
            zza((Map<String, String>) hashMap, "an", zzu.zzaf());
            zza((Map<String, String>) hashMap, "aid", zzu.zzah());
            zza((Map<String, String>) hashMap, "aiid", zzu.zzai());
            zza((Map<String, String>) hashMap, "av", zzu.zzag());
        }
        return hashMap;
    }

    public final void zzb(zzg zzg) {
        Preconditions.checkNotNull(zzg);
        Preconditions.checkArgument(zzg.zzt(), "Can't deliver not submitted measurement");
        Preconditions.checkNotMainThread("deliver should be called on worker thread");
        zzg zzo = zzg.zzo();
        zzad zzad = (zzad) zzo.zzb(zzad.class);
        if (TextUtils.isEmpty(zzad.zzay())) {
            zzbu().zza(zzc(zzo), "Ignoring measurement without type");
        } else if (TextUtils.isEmpty(zzad.zzaz())) {
            zzbu().zza(zzc(zzo), "Ignoring measurement without client id");
        } else if (!this.zzqm.zzck().getAppOptOut()) {
            double zzbf = zzad.zzbf();
            if (zzdd.zza(zzbf, zzad.zzaz())) {
                zzb("Sampling enabled. Hit sampled out. sampling rate", Double.valueOf(zzbf));
                return;
            }
            Map zzc = zzc(zzo);
            zzc.put("v", "1");
            zzc.put("_v", zzas.zzvo);
            zzc.put("tid", this.zzqr);
            if (this.zzqm.zzck().isDryRunEnabled()) {
                StringBuilder sb = new StringBuilder();
                for (Entry entry : zzc.entrySet()) {
                    if (sb.length() != 0) {
                        sb.append(", ");
                    }
                    sb.append((String) entry.getKey());
                    sb.append("=");
                    sb.append((String) entry.getValue());
                }
                zzc("Dry run is enabled. GoogleAnalytics would have sent", sb.toString());
                return;
            }
            HashMap hashMap = new HashMap();
            zzdd.zzb((Map<String, String>) hashMap, "uid", zzad.zzba());
            zzu zzu = (zzu) zzg.zza(zzu.class);
            if (zzu != null) {
                zzdd.zzb((Map<String, String>) hashMap, "an", zzu.zzaf());
                zzdd.zzb((Map<String, String>) hashMap, "aid", zzu.zzah());
                zzdd.zzb((Map<String, String>) hashMap, "av", zzu.zzag());
                zzdd.zzb((Map<String, String>) hashMap, "aiid", zzu.zzai());
            }
            zzaw zzaw = new zzaw(0, zzad.zzaz(), this.zzqr, !TextUtils.isEmpty(zzad.zzbb()), 0, hashMap);
            zzc.put("_s", String.valueOf(zzby().zza(zzaw)));
            zzch zzch = new zzch(zzbu(), zzc, zzg.zzr(), true);
            zzby().zza(zzch);
        }
    }

    public final Uri zzk() {
        return this.zzqs;
    }
}
