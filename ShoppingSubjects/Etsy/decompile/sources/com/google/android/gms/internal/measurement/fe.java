package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class fe extends ex {
    fe(ey eyVar) {
        super(eyVar);
    }

    static fv a(fu fuVar, String str) {
        fv[] fvVarArr;
        for (fv fvVar : fuVar.c) {
            if (fvVar.c.equals(str)) {
                return fvVar;
            }
        }
        return null;
    }

    private static void a(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private final void a(StringBuilder sb, int i, fl flVar) {
        String[] strArr;
        if (flVar != null) {
            a(sb, i);
            sb.append("filter {\n");
            a(sb, i, "complement", (Object) flVar.e);
            a(sb, i, "param_name", (Object) o().b(flVar.f));
            int i2 = i + 1;
            String str = "string_filter";
            fo foVar = flVar.c;
            if (foVar != null) {
                a(sb, i2);
                sb.append(str);
                sb.append(" {\n");
                if (foVar.c != null) {
                    String str2 = "UNKNOWN_MATCH_TYPE";
                    switch (foVar.c.intValue()) {
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
                    a(sb, i2, "match_type", (Object) str2);
                }
                a(sb, i2, "expression", (Object) foVar.d);
                a(sb, i2, "case_sensitive", (Object) foVar.e);
                if (foVar.f.length > 0) {
                    a(sb, i2 + 1);
                    sb.append("expression_list {\n");
                    for (String str3 : foVar.f) {
                        a(sb, i2 + 2);
                        sb.append(str3);
                        sb.append("\n");
                    }
                    sb.append("}\n");
                }
                a(sb, i2);
                sb.append("}\n");
            }
            a(sb, i2, "number_filter", flVar.d);
            a(sb, i);
            sb.append("}\n");
        }
    }

    private final void a(StringBuilder sb, int i, String str, fm fmVar) {
        if (fmVar != null) {
            a(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (fmVar.c != null) {
                String str2 = "UNKNOWN_COMPARISON_TYPE";
                switch (fmVar.c.intValue()) {
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
                a(sb, i, "comparison_type", (Object) str2);
            }
            a(sb, i, "match_as_float", (Object) fmVar.d);
            a(sb, i, "comparison_value", (Object) fmVar.e);
            a(sb, i, "min_comparison_value", (Object) fmVar.f);
            a(sb, i, "max_comparison_value", (Object) fmVar.g);
            a(sb, i);
            sb.append("}\n");
        }
    }

    private static void a(StringBuilder sb, int i, String str, fy fyVar) {
        if (fyVar != null) {
            a(sb, 3);
            sb.append(str);
            sb.append(" {\n");
            int i2 = 0;
            if (fyVar.d != null) {
                a(sb, 4);
                sb.append("results: ");
                long[] jArr = fyVar.d;
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
            if (fyVar.c != null) {
                a(sb, 4);
                sb.append("status: ");
                long[] jArr2 = fyVar.c;
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
            a(sb, 3);
            sb.append("}\n");
        }
    }

    private static void a(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            a(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append(10);
        }
    }

    static boolean a(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    static boolean a(long[] jArr, int i) {
        return i < (jArr.length << 6) && (jArr[i / 64] & (1 << (i % 64))) != 0;
    }

    static long[] a(BitSet bitSet) {
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

    static fv[] a(fv[] fvVarArr, String str, Object obj) {
        int length = fvVarArr.length;
        int i = 0;
        while (i < length) {
            fv fvVar = fvVarArr[i];
            if (str.equals(fvVar.c)) {
                fvVar.e = null;
                fvVar.d = null;
                fvVar.f = null;
                if (obj instanceof Long) {
                    fvVar.e = (Long) obj;
                    return fvVarArr;
                } else if (obj instanceof String) {
                    fvVar.d = (String) obj;
                    return fvVarArr;
                } else {
                    if (obj instanceof Double) {
                        fvVar.f = (Double) obj;
                    }
                    return fvVarArr;
                }
            } else {
                i++;
            }
        }
        fv[] fvVarArr2 = new fv[(fvVarArr.length + 1)];
        System.arraycopy(fvVarArr, 0, fvVarArr2, 0, fvVarArr.length);
        fv fvVar2 = new fv();
        fvVar2.c = str;
        if (obj instanceof Long) {
            fvVar2.e = (Long) obj;
        } else if (obj instanceof String) {
            fvVar2.d = (String) obj;
        } else if (obj instanceof Double) {
            fvVar2.f = (Double) obj;
        }
        fvVarArr2[fvVarArr.length] = fvVar2;
        return fvVarArr2;
    }

    static Object b(fu fuVar, String str) {
        fv a = a(fuVar, str);
        if (a != null) {
            if (a.d != null) {
                return a.d;
            }
            if (a.e != null) {
                return a.e;
            }
            if (a.f != null) {
                return a.f;
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r().h_().a("Failed to load parcelable from buffer");
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
    public final <T extends android.os.Parcelable> T a(byte[] r5, android.os.Parcelable.Creator<T> r6) {
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
            com.google.android.gms.internal.measurement.aq r5 = r4.r()     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.measurement.as r5 = r5.h_()     // Catch:{ all -> 0x001a }
            java.lang.String r6 = "Failed to load parcelable from buffer"
            r5.a(r6)     // Catch:{ all -> 0x001a }
            r1.recycle()
            return r0
        L_0x002d:
            r1.recycle()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.fe.a(byte[], android.os.Parcelable$Creator):android.os.Parcelable");
    }

    /* access modifiers changed from: 0000 */
    public final String a(fk fkVar) {
        if (fkVar == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        a(sb, 0, "filter_id", (Object) fkVar.c);
        a(sb, 0, ResponseConstants.EVENT_NAME, (Object) o().a(fkVar.d));
        a(sb, 1, "event_count_filter", fkVar.f);
        sb.append("  filters {\n");
        for (fl a : fkVar.e) {
            a(sb, 2, a);
        }
        a(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public final String a(fn fnVar) {
        if (fnVar == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        a(sb, 0, "filter_id", (Object) fnVar.c);
        a(sb, 0, ResponseConstants.PROPERTY_NAME, (Object) o().c(fnVar.d));
        a(sb, 1, fnVar.e);
        sb.append("}\n");
        return sb.toString();
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    /* access modifiers changed from: 0000 */
    public final void a(fv fvVar, Object obj) {
        Preconditions.checkNotNull(obj);
        fvVar.d = null;
        fvVar.e = null;
        fvVar.f = null;
        if (obj instanceof String) {
            fvVar.d = (String) obj;
        } else if (obj instanceof Long) {
            fvVar.e = (Long) obj;
        } else if (obj instanceof Double) {
            fvVar.f = (Double) obj;
        } else {
            r().h_().a("Ignoring invalid (type) event param value", obj);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(ga gaVar, Object obj) {
        Preconditions.checkNotNull(obj);
        gaVar.e = null;
        gaVar.f = null;
        gaVar.g = null;
        if (obj instanceof String) {
            gaVar.e = (String) obj;
        } else if (obj instanceof Long) {
            gaVar.f = (Long) obj;
        } else if (obj instanceof Double) {
            gaVar.g = (Double) obj;
        } else {
            r().h_().a("Ignoring invalid (type) user attribute value", obj);
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean a(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(m().currentTimeMillis() - j) > j2;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean a(zzex zzex, zzeb zzeb) {
        Preconditions.checkNotNull(zzex);
        Preconditions.checkNotNull(zzeb);
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            return true;
        }
        u();
        return false;
    }

    /* access modifiers changed from: 0000 */
    public final byte[] a(fw fwVar) {
        try {
            byte[] bArr = new byte[fwVar.d()];
            d a = d.a(bArr, 0, bArr.length);
            fwVar.a(a);
            a.a();
            return bArr;
        } catch (IOException e) {
            r().h_().a("Data loss. Failed to serialize batch", e);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final byte[] a(byte[] bArr) throws IOException {
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
            r().h_().a("Failed to ungzip content", e);
            throw e;
        }
    }

    /* access modifiers changed from: 0000 */
    public final String b(fw fwVar) {
        fx[] fxVarArr;
        fw fwVar2 = fwVar;
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        if (fwVar2.c != null) {
            for (fx fxVar : fwVar2.c) {
                if (!(fxVar == null || fxVar == null)) {
                    int i = 1;
                    a(sb, 1);
                    sb.append("bundle {\n");
                    a(sb, 1, "protocol_version", (Object) fxVar.c);
                    a(sb, 1, ResponseConstants.PLATFORM, (Object) fxVar.k);
                    a(sb, 1, "gmp_version", (Object) fxVar.s);
                    a(sb, 1, "uploading_gmp_version", (Object) fxVar.t);
                    a(sb, 1, "config_version", (Object) fxVar.G);
                    a(sb, 1, "gmp_app_id", (Object) fxVar.A);
                    a(sb, 1, "app_id", (Object) fxVar.q);
                    a(sb, 1, "app_version", (Object) fxVar.r);
                    a(sb, 1, "app_version_major", (Object) fxVar.E);
                    a(sb, 1, "firebase_instance_id", (Object) fxVar.D);
                    a(sb, 1, "dev_cert_hash", (Object) fxVar.x);
                    a(sb, 1, "app_store", (Object) fxVar.p);
                    a(sb, 1, "upload_timestamp_millis", (Object) fxVar.f);
                    a(sb, 1, "start_timestamp_millis", (Object) fxVar.g);
                    a(sb, 1, "end_timestamp_millis", (Object) fxVar.h);
                    a(sb, 1, "previous_bundle_start_timestamp_millis", (Object) fxVar.i);
                    a(sb, 1, "previous_bundle_end_timestamp_millis", (Object) fxVar.j);
                    a(sb, 1, "app_instance_id", (Object) fxVar.w);
                    a(sb, 1, "resettable_device_id", (Object) fxVar.u);
                    a(sb, 1, "device_id", (Object) fxVar.F);
                    a(sb, 1, "ds_id", (Object) fxVar.I);
                    a(sb, 1, "limited_ad_tracking", (Object) fxVar.v);
                    a(sb, 1, "os_version", (Object) fxVar.l);
                    a(sb, 1, "device_model", (Object) fxVar.m);
                    a(sb, 1, "user_default_language", (Object) fxVar.n);
                    a(sb, 1, "time_zone_offset_minutes", (Object) fxVar.o);
                    a(sb, 1, "bundle_sequential_index", (Object) fxVar.y);
                    a(sb, 1, "service_upload", (Object) fxVar.B);
                    a(sb, 1, "health_monitor", (Object) fxVar.z);
                    if (!(fxVar.H == null || fxVar.H.longValue() == 0)) {
                        a(sb, 1, "android_id", (Object) fxVar.H);
                    }
                    if (fxVar.J != null) {
                        a(sb, 1, "retry_counter", (Object) fxVar.J);
                    }
                    ga[] gaVarArr = fxVar.e;
                    if (gaVarArr != null) {
                        for (ga gaVar : gaVarArr) {
                            if (gaVar != null) {
                                a(sb, 2);
                                sb.append("user_property {\n");
                                a(sb, 2, "set_timestamp_millis", (Object) gaVar.c);
                                a(sb, 2, ResponseConstants.NAME, (Object) o().c(gaVar.d));
                                a(sb, 2, "string_value", (Object) gaVar.e);
                                a(sb, 2, "int_value", (Object) gaVar.f);
                                a(sb, 2, "double_value", (Object) gaVar.g);
                                a(sb, 2);
                                sb.append("}\n");
                            }
                        }
                    }
                    fs[] fsVarArr = fxVar.C;
                    if (fsVarArr != null) {
                        for (fs fsVar : fsVarArr) {
                            if (fsVar != null) {
                                a(sb, 2);
                                sb.append("audience_membership {\n");
                                a(sb, 2, "audience_id", (Object) fsVar.c);
                                a(sb, 2, "new_audience", (Object) fsVar.f);
                                a(sb, 2, "current_data", fsVar.d);
                                a(sb, 2, "previous_data", fsVar.e);
                                a(sb, 2);
                                sb.append("}\n");
                            }
                        }
                    }
                    fu[] fuVarArr = fxVar.d;
                    if (fuVarArr != null) {
                        int length = fuVarArr.length;
                        int i2 = 0;
                        while (i2 < length) {
                            fu fuVar = fuVarArr[i2];
                            if (fuVar != null) {
                                a(sb, 2);
                                sb.append("event {\n");
                                a(sb, 2, ResponseConstants.NAME, (Object) o().a(fuVar.d));
                                a(sb, 2, "timestamp_millis", (Object) fuVar.e);
                                a(sb, 2, "previous_timestamp_millis", (Object) fuVar.f);
                                a(sb, 2, ResponseConstants.COUNT, (Object) fuVar.g);
                                fv[] fvVarArr = fuVar.c;
                                if (fvVarArr != null) {
                                    for (fv fvVar : fvVarArr) {
                                        if (fvVar != null) {
                                            a(sb, 3);
                                            sb.append("param {\n");
                                            a(sb, 3, ResponseConstants.NAME, (Object) o().b(fvVar.c));
                                            a(sb, 3, "string_value", (Object) fvVar.d);
                                            a(sb, 3, "int_value", (Object) fvVar.e);
                                            a(sb, 3, "double_value", (Object) fvVar.f);
                                            a(sb, 3);
                                            sb.append("}\n");
                                        }
                                    }
                                }
                                a(sb, 2);
                                sb.append("}\n");
                            }
                            i2++;
                            i = 1;
                        }
                    }
                    a(sb, i);
                    sb.append("}\n");
                }
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    /* access modifiers changed from: 0000 */
    public final byte[] b(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            r().h_().a("Failed to gzip content", e);
            throw e;
        }
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public final /* bridge */ /* synthetic */ void d() {
        super.d();
    }

    public final /* bridge */ /* synthetic */ z d_() {
        return super.d_();
    }

    /* access modifiers changed from: protected */
    public final boolean e() {
        return false;
    }

    public final /* bridge */ /* synthetic */ u e_() {
        return super.e_();
    }

    public final /* bridge */ /* synthetic */ fe f_() {
        return super.f_();
    }

    public final /* bridge */ /* synthetic */ ag l() {
        return super.l();
    }

    public final /* bridge */ /* synthetic */ Clock m() {
        return super.m();
    }

    public final /* bridge */ /* synthetic */ Context n() {
        return super.n();
    }

    public final /* bridge */ /* synthetic */ ao o() {
        return super.o();
    }

    public final /* bridge */ /* synthetic */ fg p() {
        return super.p();
    }

    public final /* bridge */ /* synthetic */ bq q() {
        return super.q();
    }

    public final /* bridge */ /* synthetic */ aq r() {
        return super.r();
    }

    public final /* bridge */ /* synthetic */ bb s() {
        return super.s();
    }

    public final /* bridge */ /* synthetic */ w t() {
        return super.t();
    }

    public final /* bridge */ /* synthetic */ v u() {
        return super.u();
    }
}
