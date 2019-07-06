package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class zzjz extends zzjs {
    zzjz(zzjt zzjt) {
        super(zzjt);
    }

    static zzks zza(zzkr zzkr, String str) {
        zzks[] zzksArr;
        for (zzks zzks : zzkr.zzava) {
            if (zzks.name.equals(str)) {
                return zzks;
            }
        }
        return null;
    }

    private static void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private final void zza(StringBuilder sb, int i, zzki zzki) {
        String[] strArr;
        if (zzki != null) {
            zza(sb, i);
            sb.append("filter {\n");
            zza(sb, i, "complement", (Object) zzki.zzats);
            zza(sb, i, "param_name", (Object) zzgf().zzbn(zzki.zzatt));
            int i2 = i + 1;
            String str = "string_filter";
            zzkl zzkl = zzki.zzatq;
            if (zzkl != null) {
                zza(sb, i2);
                sb.append(str);
                sb.append(" {\n");
                if (zzkl.zzaue != null) {
                    String str2 = "UNKNOWN_MATCH_TYPE";
                    switch (zzkl.zzaue.intValue()) {
                        case 1:
                            str2 = "REGEXP";
                            break;
                        case 2:
                            str2 = "BEGINS_WITH";
                            break;
                        case 3:
                            str2 = "ENDS_WITH";
                            break;
                        case 4:
                            str2 = "PARTIAL";
                            break;
                        case 5:
                            str2 = "EXACT";
                            break;
                        case 6:
                            str2 = "IN_LIST";
                            break;
                    }
                    zza(sb, i2, "match_type", (Object) str2);
                }
                zza(sb, i2, "expression", (Object) zzkl.zzauf);
                zza(sb, i2, "case_sensitive", (Object) zzkl.zzaug);
                if (zzkl.zzauh.length > 0) {
                    zza(sb, i2 + 1);
                    sb.append("expression_list {\n");
                    for (String str3 : zzkl.zzauh) {
                        zza(sb, i2 + 2);
                        sb.append(str3);
                        sb.append("\n");
                    }
                    sb.append("}\n");
                }
                zza(sb, i2);
                sb.append("}\n");
            }
            zza(sb, i2, "number_filter", zzki.zzatr);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i, String str, zzkj zzkj) {
        if (zzkj != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzkj.zzatw != null) {
                String str2 = "UNKNOWN_COMPARISON_TYPE";
                switch (zzkj.zzatw.intValue()) {
                    case 1:
                        str2 = "LESS_THAN";
                        break;
                    case 2:
                        str2 = "GREATER_THAN";
                        break;
                    case 3:
                        str2 = "EQUAL";
                        break;
                    case 4:
                        str2 = "BETWEEN";
                        break;
                }
                zza(sb, i, "comparison_type", (Object) str2);
            }
            zza(sb, i, "match_as_float", (Object) zzkj.zzatx);
            zza(sb, i, "comparison_value", (Object) zzkj.zzaty);
            zza(sb, i, "min_comparison_value", (Object) zzkj.zzatz);
            zza(sb, i, "max_comparison_value", (Object) zzkj.zzaua);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzkv zzkv) {
        if (zzkv != null) {
            zza(sb, 3);
            sb.append(str);
            sb.append(" {\n");
            int i2 = 0;
            if (zzkv.zzawm != null) {
                zza(sb, 4);
                sb.append("results: ");
                long[] jArr = zzkv.zzawm;
                int length = jArr.length;
                int i3 = 0;
                int i4 = 0;
                while (i3 < length) {
                    Long valueOf = Long.valueOf(jArr[i3]);
                    int i5 = i4 + 1;
                    if (i4 != 0) {
                        sb.append(", ");
                    }
                    sb.append(valueOf);
                    i3++;
                    i4 = i5;
                }
                sb.append(10);
            }
            if (zzkv.zzawl != null) {
                zza(sb, 4);
                sb.append("status: ");
                long[] jArr2 = zzkv.zzawl;
                int length2 = jArr2.length;
                int i6 = 0;
                while (i2 < length2) {
                    Long valueOf2 = Long.valueOf(jArr2[i2]);
                    int i7 = i6 + 1;
                    if (i6 != 0) {
                        sb.append(", ");
                    }
                    sb.append(valueOf2);
                    i2++;
                    i6 = i7;
                }
                sb.append(10);
            }
            zza(sb, 3);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            zza(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append(10);
        }
    }

    static boolean zza(long[] jArr, int i) {
        return i < (jArr.length << 6) && (jArr[i / 64] & (1 << (i % 64))) != 0;
    }

    static long[] zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            jArr[i] = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i << 6) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    jArr[i] = jArr[i] | (1 << i2);
                }
            }
        }
        return jArr;
    }

    static zzks[] zza(zzks[] zzksArr, String str, Object obj) {
        int length = zzksArr.length;
        int i = 0;
        while (i < length) {
            zzks zzks = zzksArr[i];
            if (str.equals(zzks.name)) {
                zzks.zzave = null;
                zzks.zzale = null;
                zzks.zzasw = null;
                if (obj instanceof Long) {
                    zzks.zzave = (Long) obj;
                    return zzksArr;
                } else if (obj instanceof String) {
                    zzks.zzale = (String) obj;
                    return zzksArr;
                } else {
                    if (obj instanceof Double) {
                        zzks.zzasw = (Double) obj;
                    }
                    return zzksArr;
                }
            } else {
                i++;
            }
        }
        zzks[] zzksArr2 = new zzks[(zzksArr.length + 1)];
        System.arraycopy(zzksArr, 0, zzksArr2, 0, zzksArr.length);
        zzks zzks2 = new zzks();
        zzks2.name = str;
        if (obj instanceof Long) {
            zzks2.zzave = (Long) obj;
        } else if (obj instanceof String) {
            zzks2.zzale = (String) obj;
        } else if (obj instanceof Double) {
            zzks2.zzasw = (Double) obj;
        }
        zzksArr2[zzksArr.length] = zzks2;
        return zzksArr2;
    }

    static Object zzb(zzkr zzkr, String str) {
        zzks zza = zza(zzkr, str);
        if (zza != null) {
            if (zza.zzale != null) {
                return zza.zzale;
            }
            if (zza.zzave != null) {
                return zza.zzave;
            }
            if (zza.zzasw != null) {
                return zza.zzasw;
            }
        }
        return null;
    }

    static boolean zzcf(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        zzgi().zziv().log("Failed to load parcelable from buffer");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r1.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T extends android.os.Parcelable> T zza(byte[] r5, android.os.Parcelable.Creator<T> r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            android.os.Parcel r1 = android.os.Parcel.obtain()
            int r2 = r5.length     // Catch:{ ParseException -> 0x001c }
            r3 = 0
            r1.unmarshall(r5, r3, r2)     // Catch:{ ParseException -> 0x001c }
            r1.setDataPosition(r3)     // Catch:{ ParseException -> 0x001c }
            java.lang.Object r5 = r6.createFromParcel(r1)     // Catch:{ ParseException -> 0x001c }
            android.os.Parcelable r5 = (android.os.Parcelable) r5     // Catch:{ ParseException -> 0x001c }
            r1.recycle()
            return r5
        L_0x001a:
            r5 = move-exception
            goto L_0x002d
        L_0x001c:
            com.google.android.gms.internal.measurement.zzfi r5 = r4.zzgi()     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziv()     // Catch:{ all -> 0x001a }
            java.lang.String r6 = "Failed to load parcelable from buffer"
            r5.log(r6)     // Catch:{ all -> 0x001a }
            r1.recycle()
            return r0
        L_0x002d:
            r1.recycle()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjz.zza(byte[], android.os.Parcelable$Creator):android.os.Parcelable");
    }

    /* access modifiers changed from: 0000 */
    public final String zza(zzkh zzkh) {
        if (zzkh == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        zza(sb, 0, "filter_id", (Object) zzkh.zzatk);
        zza(sb, 0, "event_name", (Object) zzgf().zzbm(zzkh.zzatl));
        zza(sb, 1, "event_count_filter", zzkh.zzato);
        sb.append("  filters {\n");
        for (zzki zza : zzkh.zzatm) {
            zza(sb, 2, zza);
        }
        zza(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public final String zza(zzkk zzkk) {
        if (zzkk == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        zza(sb, 0, "filter_id", (Object) zzkk.zzatk);
        zza(sb, 0, "property_name", (Object) zzgf().zzbo(zzkk.zzauc));
        zza(sb, 1, zzkk.zzaud);
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzks zzks, Object obj) {
        Preconditions.checkNotNull(obj);
        zzks.zzale = null;
        zzks.zzave = null;
        zzks.zzasw = null;
        if (obj instanceof String) {
            zzks.zzale = (String) obj;
        } else if (obj instanceof Long) {
            zzks.zzave = (Long) obj;
        } else if (obj instanceof Double) {
            zzks.zzasw = (Double) obj;
        } else {
            zzgi().zziv().zzg("Ignoring invalid (type) event param value", obj);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzkx zzkx, Object obj) {
        Preconditions.checkNotNull(obj);
        zzkx.zzale = null;
        zzkx.zzave = null;
        zzkx.zzasw = null;
        if (obj instanceof String) {
            zzkx.zzale = (String) obj;
        } else if (obj instanceof Long) {
            zzkx.zzave = (Long) obj;
        } else if (obj instanceof Double) {
            zzkx.zzasw = (Double) obj;
        } else {
            zzgi().zziv().zzg("Ignoring invalid (type) user attribute value", obj);
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zzbt().currentTimeMillis() - j) > j2;
    }

    /* access modifiers changed from: 0000 */
    public final byte[] zza(zzkt zzkt) {
        try {
            byte[] bArr = new byte[zzkt.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzkt.zza(zzb);
            zzb.zzvt();
            return bArr;
        } catch (IOException e) {
            zzgi().zziv().zzg("Data loss. Failed to serialize batch", e);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final byte[] zza(byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            zzgi().zziv().zzg("Failed to ungzip content", e);
            throw e;
        }
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    /* access modifiers changed from: 0000 */
    public final String zzb(zzkt zzkt) {
        zzku[] zzkuArr;
        zzkt zzkt2 = zzkt;
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        if (zzkt2.zzavf != null) {
            for (zzku zzku : zzkt2.zzavf) {
                if (!(zzku == null || zzku == null)) {
                    zza(sb, 1);
                    sb.append("bundle {\n");
                    zza(sb, 1, "protocol_version", (Object) zzku.zzavh);
                    zza(sb, 1, "platform", (Object) zzku.zzavp);
                    zza(sb, 1, "gmp_version", (Object) zzku.zzavt);
                    zza(sb, 1, "uploading_gmp_version", (Object) zzku.zzavu);
                    zza(sb, 1, "config_version", (Object) zzku.zzawf);
                    zza(sb, 1, "gmp_app_id", (Object) zzku.zzafa);
                    zza(sb, 1, "app_id", (Object) zzku.zzth);
                    zza(sb, 1, "app_version", (Object) zzku.zztg);
                    zza(sb, 1, "app_version_major", (Object) zzku.zzawb);
                    zza(sb, 1, "firebase_instance_id", (Object) zzku.zzafc);
                    zza(sb, 1, "dev_cert_hash", (Object) zzku.zzavx);
                    zza(sb, 1, "app_store", (Object) zzku.zzafh);
                    zza(sb, 1, "upload_timestamp_millis", (Object) zzku.zzavk);
                    zza(sb, 1, "start_timestamp_millis", (Object) zzku.zzavl);
                    zza(sb, 1, "end_timestamp_millis", (Object) zzku.zzavm);
                    zza(sb, 1, "previous_bundle_start_timestamp_millis", (Object) zzku.zzavn);
                    zza(sb, 1, "previous_bundle_end_timestamp_millis", (Object) zzku.zzavo);
                    zza(sb, 1, "app_instance_id", (Object) zzku.zzaez);
                    zza(sb, 1, "resettable_device_id", (Object) zzku.zzavv);
                    zza(sb, 1, "device_id", (Object) zzku.zzawe);
                    zza(sb, 1, "ds_id", (Object) zzku.zzawh);
                    zza(sb, 1, "limited_ad_tracking", (Object) zzku.zzavw);
                    zza(sb, 1, "os_version", (Object) zzku.zzavq);
                    zza(sb, 1, "device_model", (Object) zzku.zzavr);
                    zza(sb, 1, "user_default_language", (Object) zzku.zzahd);
                    zza(sb, 1, "time_zone_offset_minutes", (Object) zzku.zzavs);
                    zza(sb, 1, "bundle_sequential_index", (Object) zzku.zzavy);
                    zza(sb, 1, "service_upload", (Object) zzku.zzavz);
                    zza(sb, 1, "health_monitor", (Object) zzku.zzafy);
                    if (!(zzku.zzawg == null || zzku.zzawg.longValue() == 0)) {
                        zza(sb, 1, "android_id", (Object) zzku.zzawg);
                    }
                    if (zzku.zzawj != null) {
                        zza(sb, 1, "retry_counter", (Object) zzku.zzawj);
                    }
                    zzkx[] zzkxArr = zzku.zzavj;
                    if (zzkxArr != null) {
                        for (zzkx zzkx : zzkxArr) {
                            if (zzkx != null) {
                                zza(sb, 2);
                                sb.append("user_property {\n");
                                zza(sb, 2, "set_timestamp_millis", (Object) zzkx.zzaws);
                                zza(sb, 2, "name", (Object) zzgf().zzbo(zzkx.name));
                                zza(sb, 2, "string_value", (Object) zzkx.zzale);
                                zza(sb, 2, "int_value", (Object) zzkx.zzave);
                                zza(sb, 2, "double_value", (Object) zzkx.zzasw);
                                zza(sb, 2);
                                sb.append("}\n");
                            }
                        }
                    }
                    zzkp[] zzkpArr = zzku.zzawa;
                    if (zzkpArr != null) {
                        for (zzkp zzkp : zzkpArr) {
                            if (zzkp != null) {
                                zza(sb, 2);
                                sb.append("audience_membership {\n");
                                zza(sb, 2, "audience_id", (Object) zzkp.zzate);
                                zza(sb, 2, "new_audience", (Object) zzkp.zzauv);
                                zza(sb, 2, "current_data", zzkp.zzaut);
                                zza(sb, 2, "previous_data", zzkp.zzauu);
                                zza(sb, 2);
                                sb.append("}\n");
                            }
                        }
                    }
                    zzkr[] zzkrArr = zzku.zzavi;
                    if (zzkrArr != null) {
                        for (zzkr zzkr : zzkrArr) {
                            if (zzkr != null) {
                                zza(sb, 2);
                                sb.append("event {\n");
                                zza(sb, 2, "name", (Object) zzgf().zzbm(zzkr.name));
                                zza(sb, 2, "timestamp_millis", (Object) zzkr.zzavb);
                                zza(sb, 2, "previous_timestamp_millis", (Object) zzkr.zzavc);
                                zza(sb, 2, "count", (Object) zzkr.count);
                                zzks[] zzksArr = zzkr.zzava;
                                if (zzksArr != null) {
                                    for (zzks zzks : zzksArr) {
                                        if (zzks != null) {
                                            zza(sb, 3);
                                            sb.append("param {\n");
                                            zza(sb, 3, "name", (Object) zzgf().zzbn(zzks.name));
                                            zza(sb, 3, "string_value", (Object) zzks.zzale);
                                            zza(sb, 3, "int_value", (Object) zzks.zzave);
                                            zza(sb, 3, "double_value", (Object) zzks.zzasw);
                                            zza(sb, 3);
                                            sb.append("}\n");
                                        }
                                    }
                                }
                                zza(sb, 2);
                                sb.append("}\n");
                            }
                        }
                    }
                    zza(sb, 1);
                    sb.append("}\n");
                }
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public final byte[] zzb(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzgi().zziv().zzg("Failed to gzip content", e);
            throw e;
        }
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzd(zzex zzex, zzeb zzeb) {
        Preconditions.checkNotNull(zzex);
        Preconditions.checkNotNull(zzeb);
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            return true;
        }
        zzgl();
        return false;
    }

    public final /* bridge */ /* synthetic */ void zzfu() {
        super.zzfu();
    }

    public final /* bridge */ /* synthetic */ void zzfv() {
        super.zzfv();
    }

    public final /* bridge */ /* synthetic */ void zzfw() {
        super.zzfw();
    }

    public final /* bridge */ /* synthetic */ zzer zzge() {
        return super.zzge();
    }

    public final /* bridge */ /* synthetic */ zzfg zzgf() {
        return super.zzgf();
    }

    public final /* bridge */ /* synthetic */ zzkd zzgg() {
        return super.zzgg();
    }

    public final /* bridge */ /* synthetic */ zzgi zzgh() {
        return super.zzgh();
    }

    public final /* bridge */ /* synthetic */ zzfi zzgi() {
        return super.zzgi();
    }

    public final /* bridge */ /* synthetic */ zzft zzgj() {
        return super.zzgj();
    }

    public final /* bridge */ /* synthetic */ zzeh zzgk() {
        return super.zzgk();
    }

    public final /* bridge */ /* synthetic */ zzee zzgl() {
        return super.zzgl();
    }

    /* access modifiers changed from: protected */
    public final boolean zzgn() {
        return false;
    }

    public final /* bridge */ /* synthetic */ zzjz zzjf() {
        return super.zzjf();
    }

    public final /* bridge */ /* synthetic */ zzed zzjg() {
        return super.zzjg();
    }

    public final /* bridge */ /* synthetic */ zzek zzjh() {
        return super.zzjh();
    }
}
