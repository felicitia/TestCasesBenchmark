package com.google.android.gms.internal.measurement;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.AppMeasurement.Event;
import com.google.android.gms.measurement.AppMeasurement.Param;
import com.google.android.gms.measurement.AppMeasurement.UserProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzed extends zzjs {
    zzed(zzjt zzjt) {
        super(zzjt);
    }

    private final Boolean zza(double d, zzkj zzkj) {
        try {
            return zza(new BigDecimal(d), zzkj, Math.ulp(d));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(long j, zzkj zzkj) {
        try {
            return zza(new BigDecimal(j), zzkj, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(zzkh zzkh, String str, zzks[] zzksArr, long j) {
        zzki[] zzkiArr;
        zzki[] zzkiArr2;
        Boolean bool;
        String str2;
        Object obj;
        if (zzkh.zzato != null) {
            Boolean zza = zza(j, zzkh.zzato);
            if (zza == null) {
                return null;
            }
            if (!zza.booleanValue()) {
                return Boolean.valueOf(false);
            }
        }
        HashSet hashSet = new HashSet();
        for (zzki zzki : zzkh.zzatm) {
            if (TextUtils.isEmpty(zzki.zzatt)) {
                zzgi().zziy().zzg("null or empty param name in filter. event", zzgf().zzbm(str));
                return null;
            }
            hashSet.add(zzki.zzatt);
        }
        ArrayMap arrayMap = new ArrayMap();
        for (zzks zzks : zzksArr) {
            if (hashSet.contains(zzks.name)) {
                if (zzks.zzave != null) {
                    str2 = zzks.name;
                    obj = zzks.zzave;
                } else if (zzks.zzasw != null) {
                    str2 = zzks.name;
                    obj = zzks.zzasw;
                } else if (zzks.zzale != null) {
                    str2 = zzks.name;
                    obj = zzks.zzale;
                } else {
                    zzgi().zziy().zze("Unknown value for param. event, param", zzgf().zzbm(str), zzgf().zzbn(zzks.name));
                    return null;
                }
                arrayMap.put(str2, obj);
            }
        }
        for (zzki zzki2 : zzkh.zzatm) {
            boolean equals = Boolean.TRUE.equals(zzki2.zzats);
            String str3 = zzki2.zzatt;
            if (TextUtils.isEmpty(str3)) {
                zzgi().zziy().zzg("Event has empty param name. event", zzgf().zzbm(str));
                return null;
            }
            Object obj2 = arrayMap.get(str3);
            if (obj2 instanceof Long) {
                if (zzki2.zzatr == null) {
                    zzgi().zziy().zze("No number filter for long param. event, param", zzgf().zzbm(str), zzgf().zzbn(str3));
                    return null;
                }
                Boolean zza2 = zza(((Long) obj2).longValue(), zzki2.zzatr);
                if (zza2 == null) {
                    return null;
                }
                if ((true ^ zza2.booleanValue()) ^ equals) {
                    return Boolean.valueOf(false);
                }
            } else if (obj2 instanceof Double) {
                if (zzki2.zzatr == null) {
                    zzgi().zziy().zze("No number filter for double param. event, param", zzgf().zzbm(str), zzgf().zzbn(str3));
                    return null;
                }
                Boolean zza3 = zza(((Double) obj2).doubleValue(), zzki2.zzatr);
                if (zza3 == null) {
                    return null;
                }
                if ((true ^ zza3.booleanValue()) ^ equals) {
                    return Boolean.valueOf(false);
                }
            } else if (obj2 instanceof String) {
                if (zzki2.zzatq != null) {
                    bool = zza((String) obj2, zzki2.zzatq);
                } else if (zzki2.zzatr != null) {
                    String str4 = (String) obj2;
                    if (zzjz.zzcf(str4)) {
                        bool = zza(str4, zzki2.zzatr);
                    } else {
                        zzgi().zziy().zze("Invalid param value for number filter. event, param", zzgf().zzbm(str), zzgf().zzbn(str3));
                        return null;
                    }
                } else {
                    zzgi().zziy().zze("No filter for String param. event, param", zzgf().zzbm(str), zzgf().zzbn(str3));
                    return null;
                }
                if (bool == null) {
                    return null;
                }
                if ((true ^ bool.booleanValue()) ^ equals) {
                    return Boolean.valueOf(false);
                }
            } else if (obj2 == null) {
                zzgi().zzjc().zze("Missing param for filter. event, param", zzgf().zzbm(str), zzgf().zzbn(str3));
                return Boolean.valueOf(false);
            } else {
                zzgi().zziy().zze("Unknown param type. event, param", zzgf().zzbm(str), zzgf().zzbn(str3));
                return null;
            }
        }
        return Boolean.valueOf(true);
    }

    private final Boolean zza(zzkk zzkk, zzkx zzkx) {
        zzfk zziy;
        String str;
        Boolean zza;
        zzki zzki = zzkk.zzaud;
        if (zzki == null) {
            zziy = zzgi().zziy();
            str = "Missing property filter. property";
        } else {
            boolean equals = Boolean.TRUE.equals(zzki.zzats);
            if (zzkx.zzave != null) {
                if (zzki.zzatr == null) {
                    zziy = zzgi().zziy();
                    str = "No number filter for long property. property";
                } else {
                    zza = zza(zzkx.zzave.longValue(), zzki.zzatr);
                }
            } else if (zzkx.zzasw != null) {
                if (zzki.zzatr == null) {
                    zziy = zzgi().zziy();
                    str = "No number filter for double property. property";
                } else {
                    zza = zza(zzkx.zzasw.doubleValue(), zzki.zzatr);
                }
            } else if (zzkx.zzale == null) {
                zziy = zzgi().zziy();
                str = "User property has no value, property";
            } else if (zzki.zzatq != null) {
                zza = zza(zzkx.zzale, zzki.zzatq);
            } else if (zzki.zzatr == null) {
                zzgi().zziy().zzg("No string or number filter defined. property", zzgf().zzbo(zzkx.name));
                return null;
            } else if (zzjz.zzcf(zzkx.zzale)) {
                zza = zza(zzkx.zzale, zzki.zzatr);
            } else {
                zzgi().zziy().zze("Invalid user property value for Numeric number filter. property, value", zzgf().zzbo(zzkx.name), zzkx.zzale);
                return null;
            }
            return zza(zza, equals);
        }
        zziy.zzg(str, zzgf().zzbo(zzkx.name));
        return null;
    }

    private static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() ^ z);
    }

    private final Boolean zza(String str, int i, boolean z, String str2, List<String> list, String str3) {
        boolean startsWith;
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 1) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException unused) {
                    zzgi().zziy().zzg("Invalid regular expression in REGEXP audience filter. expression", str3);
                    return null;
                }
            case 2:
                startsWith = str.startsWith(str2);
                break;
            case 3:
                startsWith = str.endsWith(str2);
                break;
            case 4:
                startsWith = str.contains(str2);
                break;
            case 5:
                startsWith = str.equals(str2);
                break;
            case 6:
                startsWith = list.contains(str);
                break;
            default:
                return null;
        }
        return Boolean.valueOf(startsWith);
    }

    private final Boolean zza(String str, zzkj zzkj) {
        if (!zzjz.zzcf(str)) {
            return null;
        }
        try {
            return zza(new BigDecimal(str), zzkj, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(String str, zzkl zzkl) {
        List list;
        Preconditions.checkNotNull(zzkl);
        if (str == null || zzkl.zzaue == null || zzkl.zzaue.intValue() == 0) {
            return null;
        }
        if (zzkl.zzaue.intValue() == 6) {
            if (zzkl.zzauh == null || zzkl.zzauh.length == 0) {
                return null;
            }
        } else if (zzkl.zzauf == null) {
            return null;
        }
        int intValue = zzkl.zzaue.intValue();
        boolean z = zzkl.zzaug != null && zzkl.zzaug.booleanValue();
        String upperCase = (z || intValue == 1 || intValue == 6) ? zzkl.zzauf : zzkl.zzauf.toUpperCase(Locale.ENGLISH);
        if (zzkl.zzauh == null) {
            list = null;
        } else {
            String[] strArr = zzkl.zzauh;
            if (z) {
                list = Arrays.asList(strArr);
            } else {
                ArrayList arrayList = new ArrayList();
                for (String upperCase2 : strArr) {
                    arrayList.add(upperCase2.toUpperCase(Locale.ENGLISH));
                }
                list = arrayList;
            }
        }
        return zza(str, intValue, z, upperCase, list, intValue == 1 ? upperCase : null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0070, code lost:
        if (r3 != null) goto L_0x0072;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Boolean zza(java.math.BigDecimal r7, com.google.android.gms.internal.measurement.zzkj r8, double r9) {
        /*
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)
            java.lang.Integer r0 = r8.zzatw
            r1 = 0
            if (r0 == 0) goto L_0x00ec
            java.lang.Integer r0 = r8.zzatw
            int r0 = r0.intValue()
            if (r0 != 0) goto L_0x0011
            return r1
        L_0x0011:
            java.lang.Integer r0 = r8.zzatw
            int r0 = r0.intValue()
            r2 = 4
            if (r0 != r2) goto L_0x0023
            java.lang.String r0 = r8.zzatz
            if (r0 == 0) goto L_0x0022
            java.lang.String r0 = r8.zzaua
            if (r0 != 0) goto L_0x0028
        L_0x0022:
            return r1
        L_0x0023:
            java.lang.String r0 = r8.zzaty
            if (r0 != 0) goto L_0x0028
            return r1
        L_0x0028:
            java.lang.Integer r0 = r8.zzatw
            int r0 = r0.intValue()
            java.lang.Integer r3 = r8.zzatw
            int r3 = r3.intValue()
            if (r3 != r2) goto L_0x0059
            java.lang.String r3 = r8.zzatz
            boolean r3 = com.google.android.gms.internal.measurement.zzjz.zzcf(r3)
            if (r3 == 0) goto L_0x0058
            java.lang.String r3 = r8.zzaua
            boolean r3 = com.google.android.gms.internal.measurement.zzjz.zzcf(r3)
            if (r3 != 0) goto L_0x0047
            return r1
        L_0x0047:
            java.math.BigDecimal r3 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0058 }
            java.lang.String r4 = r8.zzatz     // Catch:{ NumberFormatException -> 0x0058 }
            r3.<init>(r4)     // Catch:{ NumberFormatException -> 0x0058 }
            java.math.BigDecimal r4 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0058 }
            java.lang.String r8 = r8.zzaua     // Catch:{ NumberFormatException -> 0x0058 }
            r4.<init>(r8)     // Catch:{ NumberFormatException -> 0x0058 }
            r8 = r3
            r3 = r1
            goto L_0x006b
        L_0x0058:
            return r1
        L_0x0059:
            java.lang.String r3 = r8.zzaty
            boolean r3 = com.google.android.gms.internal.measurement.zzjz.zzcf(r3)
            if (r3 != 0) goto L_0x0062
            return r1
        L_0x0062:
            java.math.BigDecimal r3 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x00ec }
            java.lang.String r8 = r8.zzaty     // Catch:{ NumberFormatException -> 0x00ec }
            r3.<init>(r8)     // Catch:{ NumberFormatException -> 0x00ec }
            r8 = r1
            r4 = r8
        L_0x006b:
            if (r0 != r2) goto L_0x0070
            if (r8 != 0) goto L_0x0072
            return r1
        L_0x0070:
            if (r3 == 0) goto L_0x00ec
        L_0x0072:
            r2 = -1
            r5 = 0
            r6 = 1
            switch(r0) {
                case 1: goto L_0x00e0;
                case 2: goto L_0x00d4;
                case 3: goto L_0x008b;
                case 4: goto L_0x0079;
                default: goto L_0x0078;
            }
        L_0x0078:
            return r1
        L_0x0079:
            int r8 = r7.compareTo(r8)
            if (r8 == r2) goto L_0x0086
            int r7 = r7.compareTo(r4)
            if (r7 == r6) goto L_0x0086
            r5 = 1
        L_0x0086:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x008b:
            r0 = 0
            int r8 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r8 == 0) goto L_0x00c8
            java.math.BigDecimal r8 = new java.math.BigDecimal
            r8.<init>(r9)
            java.math.BigDecimal r0 = new java.math.BigDecimal
            r1 = 2
            r0.<init>(r1)
            java.math.BigDecimal r8 = r8.multiply(r0)
            java.math.BigDecimal r8 = r3.subtract(r8)
            int r8 = r7.compareTo(r8)
            if (r8 != r6) goto L_0x00c3
            java.math.BigDecimal r8 = new java.math.BigDecimal
            r8.<init>(r9)
            java.math.BigDecimal r9 = new java.math.BigDecimal
            r9.<init>(r1)
            java.math.BigDecimal r8 = r8.multiply(r9)
            java.math.BigDecimal r8 = r3.add(r8)
            int r7 = r7.compareTo(r8)
            if (r7 != r2) goto L_0x00c3
            r5 = 1
        L_0x00c3:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x00c8:
            int r7 = r7.compareTo(r3)
            if (r7 != 0) goto L_0x00cf
            r5 = 1
        L_0x00cf:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x00d4:
            int r7 = r7.compareTo(r3)
            if (r7 != r6) goto L_0x00db
            r5 = 1
        L_0x00db:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x00e0:
            int r7 = r7.compareTo(r3)
            if (r7 != r2) goto L_0x00e7
            r5 = 1
        L_0x00e7:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x00ec:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzed.zza(java.math.BigDecimal, com.google.android.gms.internal.measurement.zzkj, double):java.lang.Boolean");
    }

    private final void zza(Integer num, Integer num2, zzki zzki, Boolean bool, Boolean bool2) {
        if (zzki == null) {
            zzgi().zziy().zze("The leaf filter of event or user property filter is null. audience ID, filter ID", num, num2);
            return;
        }
        boolean z = false;
        zzki.zzatu = Boolean.valueOf((bool != null && bool.booleanValue()) || (bool2 != null && bool2.booleanValue()));
        if (bool2 != null && bool2.booleanValue()) {
            z = true;
        }
        zzki.zzatv = Boolean.valueOf(z);
    }

    private static void zza(Map<Integer, Long> map, int i, long j) {
        Long l = (Long) map.get(Integer.valueOf(i));
        long j2 = j / 1000;
        if (l == null || j2 > l.longValue()) {
            map.put(Integer.valueOf(i), Long.valueOf(j2));
        }
    }

    private static zzkq[] zzd(Map<Integer, Long> map) {
        if (map == null) {
            return null;
        }
        int i = 0;
        zzkq[] zzkqArr = new zzkq[map.size()];
        for (Integer num : map.keySet()) {
            zzkq zzkq = new zzkq();
            zzkq.zzaux = num;
            zzkq.zzauy = (Long) map.get(num);
            int i2 = i + 1;
            zzkqArr[i] = zzkq;
            i = i2;
        }
        return zzkqArr;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(String str, zzkg[] zzkgArr) {
        zzkh[] zzkhArr;
        zzkk[] zzkkArr;
        zzki[] zzkiArr;
        zzkg[] zzkgArr2 = zzkgArr;
        Preconditions.checkNotNull(zzkgArr);
        for (zzkg zzkg : zzkgArr2) {
            for (zzkh zzkh : zzkg.zzatg) {
                String zzal = Event.zzal(zzkh.zzatl);
                if (zzal != null) {
                    zzkh.zzatl = zzal;
                }
                for (zzki zzki : zzkh.zzatm) {
                    String zzal2 = Param.zzal(zzki.zzatt);
                    if (zzal2 != null) {
                        zzki.zzatt = zzal2;
                    }
                    zza(zzkg.zzate, zzkh.zzatk, zzki, zzkg.zzath, zzkg.zzati);
                }
            }
            for (zzkk zzkk : zzkg.zzatf) {
                String zzal3 = UserProperty.zzal(zzkk.zzauc);
                if (zzal3 != null) {
                    zzkk.zzauc = zzal3;
                }
                zza(zzkg.zzate, zzkk.zzatk, zzkk.zzaud, zzkg.zzath, zzkg.zzati);
            }
        }
        zzjh().zzb(str, zzkgArr2);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:293:0x0995, code lost:
        zzgi().zziy().zze("Invalid property filter ID. appId, id", com.google.android.gms.internal.measurement.zzfi.zzbp(r77), java.lang.String.valueOf(r9.zzatk));
        r15.add(java.lang.Integer.valueOf(r11));
        r2 = r61;
        r5 = r62;
        r10 = r66;
        r9 = r67;
        r4 = r69;
        r55 = r70;
        r59 = r71;
        r58 = r72;
        r6 = r73;
     */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x02d2  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0311  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0382  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x03d2  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x03fa  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x040f  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0420  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x05db  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x05de  */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x05e4  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x05ff  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01c2  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x029b  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x02b4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzkp[] zza(java.lang.String r77, com.google.android.gms.internal.measurement.zzkr[] r78, com.google.android.gms.internal.measurement.zzkx[] r79) {
        /*
            r76 = this;
            r7 = r76
            r15 = r77
            r13 = r78
            r14 = r79
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r77)
            java.util.HashSet r11 = new java.util.HashSet
            r11.<init>()
            android.support.v4.util.ArrayMap r12 = new android.support.v4.util.ArrayMap
            r12.<init>()
            android.support.v4.util.ArrayMap r10 = new android.support.v4.util.ArrayMap
            r10.<init>()
            android.support.v4.util.ArrayMap r9 = new android.support.v4.util.ArrayMap
            r9.<init>()
            android.support.v4.util.ArrayMap r8 = new android.support.v4.util.ArrayMap
            r8.<init>()
            com.google.android.gms.internal.measurement.zzeh r1 = r76.zzgk()
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.Boolean> r2 = com.google.android.gms.internal.measurement.zzez.zzajx
            boolean r22 = r1.zzd(r15, r2)
            com.google.android.gms.internal.measurement.zzek r1 = r76.zzjh()
            java.util.Map r1 = r1.zzbi(r15)
            if (r1 == 0) goto L_0x0176
            java.util.Set r2 = r1.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0040:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0176
            java.lang.Object r3 = r2.next()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)
            java.lang.Object r6 = r1.get(r6)
            com.google.android.gms.internal.measurement.zzkv r6 = (com.google.android.gms.internal.measurement.zzkv) r6
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            java.lang.Object r4 = r10.get(r4)
            java.util.BitSet r4 = (java.util.BitSet) r4
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)
            java.lang.Object r5 = r9.get(r5)
            java.util.BitSet r5 = (java.util.BitSet) r5
            if (r22 == 0) goto L_0x00b0
            r26 = r1
            android.support.v4.util.ArrayMap r1 = new android.support.v4.util.ArrayMap
            r1.<init>()
            if (r6 == 0) goto L_0x00a2
            r27 = r2
            com.google.android.gms.internal.measurement.zzkq[] r2 = r6.zzawn
            if (r2 != 0) goto L_0x0080
            goto L_0x00a4
        L_0x0080:
            com.google.android.gms.internal.measurement.zzkq[] r2 = r6.zzawn
            r28 = r5
            int r5 = r2.length
            r29 = r11
            r11 = 0
        L_0x0088:
            if (r11 >= r5) goto L_0x00a8
            r30 = r5
            r5 = r2[r11]
            r31 = r2
            java.lang.Integer r2 = r5.zzaux
            if (r2 == 0) goto L_0x009b
            java.lang.Integer r2 = r5.zzaux
            java.lang.Long r5 = r5.zzauy
            r1.put(r2, r5)
        L_0x009b:
            int r11 = r11 + 1
            r5 = r30
            r2 = r31
            goto L_0x0088
        L_0x00a2:
            r27 = r2
        L_0x00a4:
            r28 = r5
            r29 = r11
        L_0x00a8:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            r8.put(r2, r1)
            goto L_0x00b9
        L_0x00b0:
            r26 = r1
            r27 = r2
            r28 = r5
            r29 = r11
            r1 = 0
        L_0x00b9:
            if (r4 != 0) goto L_0x00d4
            java.util.BitSet r4 = new java.util.BitSet
            r4.<init>()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            r10.put(r2, r4)
            java.util.BitSet r5 = new java.util.BitSet
            r5.<init>()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            r9.put(r2, r5)
            goto L_0x00d6
        L_0x00d4:
            r5 = r28
        L_0x00d6:
            r2 = 0
        L_0x00d7:
            long[] r11 = r6.zzawl
            int r11 = r11.length
            int r11 = r11 << 6
            if (r2 >= r11) goto L_0x012c
            long[] r11 = r6.zzawl
            boolean r11 = com.google.android.gms.internal.measurement.zzjz.zza(r11, r2)
            if (r11 == 0) goto L_0x0111
            com.google.android.gms.internal.measurement.zzfi r11 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r11 = r11.zzjc()
            r32 = r8
            java.lang.String r8 = "Filter already evaluated. audience ID, filter ID"
            r33 = r9
            java.lang.Integer r9 = java.lang.Integer.valueOf(r3)
            r34 = r10
            java.lang.Integer r10 = java.lang.Integer.valueOf(r2)
            r11.zze(r8, r9, r10)
            r5.set(r2)
            long[] r8 = r6.zzawm
            boolean r8 = com.google.android.gms.internal.measurement.zzjz.zza(r8, r2)
            if (r8 == 0) goto L_0x0117
            r4.set(r2)
            r8 = 1
            goto L_0x0118
        L_0x0111:
            r32 = r8
            r33 = r9
            r34 = r10
        L_0x0117:
            r8 = 0
        L_0x0118:
            if (r1 == 0) goto L_0x0123
            if (r8 != 0) goto L_0x0123
            java.lang.Integer r8 = java.lang.Integer.valueOf(r2)
            r1.remove(r8)
        L_0x0123:
            int r2 = r2 + 1
            r8 = r32
            r9 = r33
            r10 = r34
            goto L_0x00d7
        L_0x012c:
            r32 = r8
            r33 = r9
            r34 = r10
            com.google.android.gms.internal.measurement.zzkp r2 = new com.google.android.gms.internal.measurement.zzkp
            r2.<init>()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r12.put(r3, r2)
            r3 = 0
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r3)
            r2.zzauv = r8
            r2.zzauu = r6
            com.google.android.gms.internal.measurement.zzkv r3 = new com.google.android.gms.internal.measurement.zzkv
            r3.<init>()
            r2.zzaut = r3
            com.google.android.gms.internal.measurement.zzkv r3 = r2.zzaut
            long[] r4 = com.google.android.gms.internal.measurement.zzjz.zza(r4)
            r3.zzawm = r4
            com.google.android.gms.internal.measurement.zzkv r3 = r2.zzaut
            long[] r4 = com.google.android.gms.internal.measurement.zzjz.zza(r5)
            r3.zzawl = r4
            if (r22 == 0) goto L_0x0168
            com.google.android.gms.internal.measurement.zzkv r2 = r2.zzaut
            com.google.android.gms.internal.measurement.zzkq[] r1 = zzd(r1)
            r2.zzawn = r1
        L_0x0168:
            r1 = r26
            r2 = r27
            r11 = r29
            r8 = r32
            r9 = r33
            r10 = r34
            goto L_0x0040
        L_0x0176:
            r32 = r8
            r33 = r9
            r34 = r10
            r29 = r11
            if (r13 == 0) goto L_0x0730
            android.support.v4.util.ArrayMap r9 = new android.support.v4.util.ArrayMap
            r9.<init>()
            int r8 = r13.length
            r1 = 0
            r2 = 0
            r6 = 0
            r16 = 0
        L_0x018b:
            if (r6 >= r8) goto L_0x0730
            r3 = r13[r6]
            java.lang.String r10 = r3.name
            com.google.android.gms.internal.measurement.zzks[] r11 = r3.zzava
            com.google.android.gms.internal.measurement.zzeh r4 = r76.zzgk()
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.Boolean> r5 = com.google.android.gms.internal.measurement.zzez.zzajr
            boolean r4 = r4.zzd(r15, r5)
            if (r4 == 0) goto L_0x0367
            r76.zzjf()
            java.lang.String r4 = "_eid"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzjz.zzb(r3, r4)
            java.lang.Long r4 = (java.lang.Long) r4
            if (r4 == 0) goto L_0x01ae
            r5 = 1
            goto L_0x01af
        L_0x01ae:
            r5 = 0
        L_0x01af:
            if (r5 == 0) goto L_0x01bd
            r35 = r6
            java.lang.String r6 = "_ep"
            boolean r6 = r10.equals(r6)
            if (r6 == 0) goto L_0x01bf
            r6 = 1
            goto L_0x01c0
        L_0x01bd:
            r35 = r6
        L_0x01bf:
            r6 = 0
        L_0x01c0:
            if (r6 == 0) goto L_0x0311
            r76.zzjf()
            java.lang.String r5 = "_en"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzjz.zzb(r3, r5)
            r10 = r5
            java.lang.String r10 = (java.lang.String) r10
            boolean r5 = android.text.TextUtils.isEmpty(r10)
            if (r5 == 0) goto L_0x01e5
            com.google.android.gms.internal.measurement.zzfi r3 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zziv()
            java.lang.String r5 = "Extra parameter without an event name. eventId"
            r3.zzg(r5, r4)
            r25 = r35
            goto L_0x02fd
        L_0x01e5:
            if (r1 == 0) goto L_0x01fa
            if (r2 == 0) goto L_0x01fa
            long r5 = r4.longValue()
            long r18 = r2.longValue()
            int r20 = (r5 > r18 ? 1 : (r5 == r18 ? 0 : -1))
            if (r20 == 0) goto L_0x01f6
            goto L_0x01fa
        L_0x01f6:
            r6 = r1
            r18 = r2
            goto L_0x0222
        L_0x01fa:
            com.google.android.gms.internal.measurement.zzek r5 = r76.zzjh()
            android.util.Pair r5 = r5.zza(r15, r4)
            if (r5 == 0) goto L_0x02ee
            java.lang.Object r6 = r5.first
            if (r6 != 0) goto L_0x020a
            goto L_0x02ee
        L_0x020a:
            java.lang.Object r1 = r5.first
            com.google.android.gms.internal.measurement.zzkr r1 = (com.google.android.gms.internal.measurement.zzkr) r1
            java.lang.Object r2 = r5.second
            java.lang.Long r2 = (java.lang.Long) r2
            long r16 = r2.longValue()
            r76.zzjf()
            java.lang.String r2 = "_eid"
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzjz.zzb(r1, r2)
            java.lang.Long r2 = (java.lang.Long) r2
            goto L_0x01f6
        L_0x0222:
            r1 = 1
            long r19 = r16 - r1
            r16 = 0
            int r1 = (r19 > r16 ? 1 : (r19 == r16 ? 0 : -1))
            if (r1 > 0) goto L_0x0273
            com.google.android.gms.internal.measurement.zzek r1 = r76.zzjh()
            r1.zzab()
            com.google.android.gms.internal.measurement.zzfi r2 = r1.zzgi()
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zzjc()
            java.lang.String r4 = "Clearing complex main event info. appId"
            r2.zzg(r4, r15)
            android.database.sqlite.SQLiteDatabase r2 = r1.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0257 }
            java.lang.String r4 = "delete from main_event_params where app_id=?"
            r36 = r3
            r5 = 1
            java.lang.String[] r3 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0255 }
            r21 = 0
            r3[r21] = r15     // Catch:{ SQLiteException -> 0x0253 }
            r2.execSQL(r4, r3)     // Catch:{ SQLiteException -> 0x0253 }
            goto L_0x026b
        L_0x0253:
            r0 = move-exception
            goto L_0x025d
        L_0x0255:
            r0 = move-exception
            goto L_0x025b
        L_0x0257:
            r0 = move-exception
            r36 = r3
            r5 = 1
        L_0x025b:
            r21 = 0
        L_0x025d:
            r2 = r0
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zziv()
            java.lang.String r3 = "Error clearing complex main event"
            r1.zzg(r3, r2)
        L_0x026b:
            r1 = r6
            r14 = r16
            r25 = r35
            r13 = r36
            goto L_0x028d
        L_0x0273:
            r36 = r3
            r5 = 1
            r21 = 0
            com.google.android.gms.internal.measurement.zzek r1 = r76.zzjh()
            r2 = r15
            r13 = r36
            r3 = r4
            r14 = r16
            r4 = r19
            r37 = r6
            r25 = r35
            r1.zza(r2, r3, r4, r6)
            r1 = r37
        L_0x028d:
            com.google.android.gms.internal.measurement.zzks[] r2 = r1.zzava
            int r2 = r2.length
            int r3 = r11.length
            int r2 = r2 + r3
            com.google.android.gms.internal.measurement.zzks[] r2 = new com.google.android.gms.internal.measurement.zzks[r2]
            com.google.android.gms.internal.measurement.zzks[] r3 = r1.zzava
            int r4 = r3.length
            r5 = 0
            r6 = 0
        L_0x0299:
            if (r5 >= r4) goto L_0x02b2
            r14 = r3[r5]
            r76.zzjf()
            java.lang.String r15 = r14.name
            com.google.android.gms.internal.measurement.zzks r15 = com.google.android.gms.internal.measurement.zzjz.zza(r13, r15)
            if (r15 != 0) goto L_0x02ad
            int r15 = r6 + 1
            r2[r6] = r14
            r6 = r15
        L_0x02ad:
            int r5 = r5 + 1
            r14 = 0
            goto L_0x0299
        L_0x02b2:
            if (r6 <= 0) goto L_0x02d2
            int r3 = r11.length
            r4 = 0
        L_0x02b6:
            if (r4 >= r3) goto L_0x02c2
            r5 = r11[r4]
            int r14 = r6 + 1
            r2[r6] = r5
            int r4 = r4 + 1
            r6 = r14
            goto L_0x02b6
        L_0x02c2:
            int r3 = r2.length
            if (r6 != r3) goto L_0x02c6
            goto L_0x02cc
        L_0x02c6:
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r6)
            com.google.android.gms.internal.measurement.zzks[] r2 = (com.google.android.gms.internal.measurement.zzks[]) r2
        L_0x02cc:
            r26 = r1
            r28 = r2
            r5 = r10
            goto L_0x02e4
        L_0x02d2:
            com.google.android.gms.internal.measurement.zzfi r2 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziy()
            java.lang.String r3 = "No unique parameters in main event. eventName"
            r2.zzg(r3, r10)
            r26 = r1
            r5 = r10
            r28 = r11
        L_0x02e4:
            r27 = r18
            r38 = r19
            r14 = r77
            r23 = 0
            goto L_0x0376
        L_0x02ee:
            r25 = r35
            com.google.android.gms.internal.measurement.zzfi r3 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zziv()
            java.lang.String r5 = "Extra parameter without existing main event. eventName, eventId"
            r3.zze(r5, r10, r4)
        L_0x02fd:
            r30 = r8
            r46 = r9
            r59 = r12
            r15 = r29
            r4 = r32
            r55 = r33
            r58 = r34
            r3 = r77
            r23 = 0
            goto L_0x0719
        L_0x0311:
            r13 = r3
            r25 = r35
            if (r5 == 0) goto L_0x0364
            r76.zzjf()
            java.lang.String r1 = "_epc"
            r2 = 0
            java.lang.Long r5 = java.lang.Long.valueOf(r2)
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzjz.zzb(r13, r1)
            if (r1 != 0) goto L_0x0328
            r1 = r5
        L_0x0328:
            java.lang.Long r1 = (java.lang.Long) r1
            long r14 = r1.longValue()
            int r1 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r1 > 0) goto L_0x0348
            com.google.android.gms.internal.measurement.zzfi r1 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zziy()
            java.lang.String r5 = "Complex event with zero extra param count. eventName"
            r1.zzg(r5, r10)
            r23 = r2
            r16 = r4
            r38 = r14
            r14 = r77
            goto L_0x035c
        L_0x0348:
            com.google.android.gms.internal.measurement.zzek r1 = r76.zzjh()
            r23 = r2
            r6 = r77
            r2 = r6
            r3 = r4
            r16 = r4
            r4 = r14
            r38 = r14
            r14 = r6
            r6 = r13
            r1.zza(r2, r3, r4, r6)
        L_0x035c:
            r5 = r10
            r28 = r11
            r26 = r13
            r27 = r16
            goto L_0x0376
        L_0x0364:
            r14 = r77
            goto L_0x036b
        L_0x0367:
            r13 = r3
            r25 = r6
            r14 = r15
        L_0x036b:
            r23 = 0
            r26 = r1
            r27 = r2
            r5 = r10
            r28 = r11
            r38 = r16
        L_0x0376:
            com.google.android.gms.internal.measurement.zzek r1 = r76.zzjh()
            java.lang.String r2 = r13.name
            com.google.android.gms.internal.measurement.zzet r1 = r1.zzf(r14, r2)
            if (r1 != 0) goto L_0x03d2
            com.google.android.gms.internal.measurement.zzfi r1 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zziy()
            java.lang.String r2 = "Event aggregate wasn't created during raw event logging. appId, event"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzfi.zzbp(r77)
            com.google.android.gms.internal.measurement.zzfg r4 = r76.zzgf()
            java.lang.String r4 = r4.zzbm(r5)
            r1.zze(r2, r3, r4)
            com.google.android.gms.internal.measurement.zzet r1 = new com.google.android.gms.internal.measurement.zzet
            java.lang.String r10 = r13.name
            r2 = 1
            r15 = 1
            java.lang.Long r4 = r13.zzavb
            long r17 = r4.longValue()
            r19 = 0
            r4 = 0
            r6 = 0
            r21 = 0
            r30 = r8
            r11 = r32
            r8 = r1
            r41 = r9
            r40 = r33
            r9 = r14
            r42 = r34
            r45 = r11
            r44 = r12
            r43 = r29
            r11 = r2
            r2 = r13
            r3 = r79
            r13 = r15
            r15 = r17
            r17 = r19
            r19 = r4
            r20 = r6
            r8.<init>(r9, r10, r11, r13, r15, r17, r19, r20, r21)
            goto L_0x03e7
        L_0x03d2:
            r30 = r8
            r41 = r9
            r44 = r12
            r2 = r13
            r43 = r29
            r45 = r32
            r40 = r33
            r42 = r34
            r3 = r79
            com.google.android.gms.internal.measurement.zzet r1 = r1.zzim()
        L_0x03e7:
            com.google.android.gms.internal.measurement.zzek r4 = r76.zzjh()
            r4.zza(r1)
            long r8 = r1.zzahh
            r10 = r41
            java.lang.Object r1 = r10.get(r5)
            java.util.Map r1 = (java.util.Map) r1
            if (r1 != 0) goto L_0x040f
            com.google.android.gms.internal.measurement.zzek r1 = r76.zzjh()
            r11 = r77
            java.util.Map r1 = r1.zzk(r11, r5)
            if (r1 != 0) goto L_0x040b
            android.support.v4.util.ArrayMap r1 = new android.support.v4.util.ArrayMap
            r1.<init>()
        L_0x040b:
            r10.put(r5, r1)
            goto L_0x0411
        L_0x040f:
            r11 = r77
        L_0x0411:
            r12 = r1
            java.util.Set r1 = r12.keySet()
            java.util.Iterator r13 = r1.iterator()
        L_0x041a:
            boolean r1 = r13.hasNext()
            if (r1 == 0) goto L_0x0706
            java.lang.Object r1 = r13.next()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r14 = r1.intValue()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r14)
            r15 = r43
            boolean r1 = r15.contains(r1)
            if (r1 == 0) goto L_0x044a
            com.google.android.gms.internal.measurement.zzfi r1 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zzjc()
            java.lang.String r4 = "Skipping failed audience ID"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r14)
            r1.zzg(r4, r6)
            r43 = r15
            goto L_0x041a
        L_0x044a:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r14)
            r6 = r44
            java.lang.Object r1 = r6.get(r1)
            com.google.android.gms.internal.measurement.zzkp r1 = (com.google.android.gms.internal.measurement.zzkp) r1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r14)
            r46 = r10
            r10 = r42
            java.lang.Object r4 = r10.get(r4)
            java.util.BitSet r4 = (java.util.BitSet) r4
            r47 = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r14)
            r48 = r13
            r13 = r40
            java.lang.Object r2 = r13.get(r2)
            java.util.BitSet r2 = (java.util.BitSet) r2
            if (r22 == 0) goto L_0x0485
            r49 = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r14)
            r11 = r45
            java.lang.Object r2 = r11.get(r2)
            java.util.Map r2 = (java.util.Map) r2
            goto L_0x048a
        L_0x0485:
            r49 = r2
            r11 = r45
            r2 = 0
        L_0x048a:
            if (r1 != 0) goto L_0x04d8
            com.google.android.gms.internal.measurement.zzkp r1 = new com.google.android.gms.internal.measurement.zzkp
            r1.<init>()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r14)
            r6.put(r4, r1)
            r50 = r2
            r4 = 1
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r4)
            r1.zzauv = r2
            java.util.BitSet r1 = new java.util.BitSet
            r1.<init>()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r14)
            r10.put(r2, r1)
            java.util.BitSet r2 = new java.util.BitSet
            r2.<init>()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r14)
            r13.put(r4, r2)
            if (r22 == 0) goto L_0x04ce
            android.support.v4.util.ArrayMap r4 = new android.support.v4.util.ArrayMap
            r4.<init>()
            r51 = r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r14)
            r11.put(r1, r4)
            r1 = r2
            r52 = r11
            r11 = r4
            goto L_0x04d5
        L_0x04ce:
            r51 = r1
            r1 = r2
            r52 = r11
            r11 = r50
        L_0x04d5:
            r4 = r51
            goto L_0x04e0
        L_0x04d8:
            r50 = r2
            r52 = r11
            r1 = r49
            r11 = r50
        L_0x04e0:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r14)
            java.lang.Object r2 = r12.get(r2)
            java.util.List r2 = (java.util.List) r2
            java.util.Iterator r2 = r2.iterator()
        L_0x04ee:
            boolean r16 = r2.hasNext()
            if (r16 == 0) goto L_0x06f0
            java.lang.Object r16 = r2.next()
            r53 = r12
            r12 = r16
            com.google.android.gms.internal.measurement.zzkh r12 = (com.google.android.gms.internal.measurement.zzkh) r12
            r54 = r1
            com.google.android.gms.internal.measurement.zzfi r1 = r76.zzgi()
            r55 = r13
            r13 = 2
            boolean r1 = r1.isLoggable(r13)
            if (r1 == 0) goto L_0x0546
            com.google.android.gms.internal.measurement.zzfi r1 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zzjc()
            java.lang.String r13 = "Evaluating filter. audience, filter, event"
            r56 = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r14)
            java.lang.Integer r3 = r12.zzatk
            r57 = r6
            com.google.android.gms.internal.measurement.zzfg r6 = r76.zzgf()
            r58 = r10
            java.lang.String r10 = r12.zzatl
            java.lang.String r6 = r6.zzbm(r10)
            r1.zzd(r13, r2, r3, r6)
            com.google.android.gms.internal.measurement.zzfi r1 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zzjc()
            java.lang.String r2 = "Filter definition"
            com.google.android.gms.internal.measurement.zzjz r3 = r76.zzjf()
            java.lang.String r3 = r3.zza(r12)
            r1.zzg(r2, r3)
            goto L_0x054c
        L_0x0546:
            r56 = r2
            r57 = r6
            r58 = r10
        L_0x054c:
            java.lang.Integer r1 = r12.zzatk
            if (r1 == 0) goto L_0x06b2
            java.lang.Integer r1 = r12.zzatk
            int r1 = r1.intValue()
            r10 = 256(0x100, float:3.59E-43)
            if (r1 <= r10) goto L_0x055c
            goto L_0x06b2
        L_0x055c:
            if (r22 == 0) goto L_0x062f
            if (r12 == 0) goto L_0x0581
            com.google.android.gms.internal.measurement.zzki[] r1 = r12.zzatm
            if (r1 == 0) goto L_0x0581
            com.google.android.gms.internal.measurement.zzki[] r1 = r12.zzatm
            int r1 = r1.length
            if (r1 <= 0) goto L_0x0581
            com.google.android.gms.internal.measurement.zzki[] r1 = r12.zzatm
            r13 = 0
            r1 = r1[r13]
            java.lang.Boolean r1 = r1.zzatu
            if (r1 == 0) goto L_0x0582
            com.google.android.gms.internal.measurement.zzki[] r1 = r12.zzatm
            r1 = r1[r13]
            java.lang.Boolean r1 = r1.zzatu
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0582
            r16 = 1
            goto L_0x0584
        L_0x0581:
            r13 = 0
        L_0x0582:
            r16 = 0
        L_0x0584:
            java.lang.Integer r1 = r12.zzatk
            int r1 = r1.intValue()
            boolean r1 = r4.get(r1)
            if (r1 == 0) goto L_0x05b3
            if (r16 != 0) goto L_0x05b3
            com.google.android.gms.internal.measurement.zzfi r1 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zzjc()
            java.lang.String r2 = "Event filter already evaluated true and it is not associated with a dynamic audience. audience ID, filter ID"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r14)
            java.lang.Integer r6 = r12.zzatk
            r1.zze(r2, r3, r6)
            r12 = r53
            r1 = r54
            r13 = r55
            r2 = r56
            r6 = r57
            r10 = r58
            goto L_0x06ec
        L_0x05b3:
            r6 = r54
            r1 = r7
            r3 = r47
            r17 = r56
            r13 = 1
            r2 = r12
            r13 = r3
            r10 = r79
            r3 = r5
            r10 = r4
            r4 = r28
            r18 = r5
            r60 = r11
            r59 = r57
            r11 = r6
            r5 = r8
            java.lang.Boolean r1 = r1.zza(r2, r3, r4, r5)
            com.google.android.gms.internal.measurement.zzfi r2 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zzjc()
            java.lang.String r3 = "Event filter result"
            if (r1 != 0) goto L_0x05de
            java.lang.String r4 = "null"
            goto L_0x05df
        L_0x05de:
            r4 = r1
        L_0x05df:
            r2.zzg(r3, r4)
            if (r1 != 0) goto L_0x05ff
            java.lang.Integer r1 = java.lang.Integer.valueOf(r14)
            r15.add(r1)
        L_0x05eb:
            r4 = r10
            r1 = r11
            r47 = r13
            r2 = r17
            r5 = r18
            r12 = r53
            r13 = r55
            r10 = r58
            r6 = r59
            r11 = r60
            goto L_0x06ec
        L_0x05ff:
            java.lang.Integer r2 = r12.zzatk
            int r2 = r2.intValue()
            r11.set(r2)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x05eb
            java.lang.Integer r1 = r12.zzatk
            int r1 = r1.intValue()
            r10.set(r1)
            if (r16 == 0) goto L_0x05eb
            java.lang.Long r1 = r13.zzavb
            if (r1 == 0) goto L_0x05eb
            java.lang.Integer r1 = r12.zzatk
            int r1 = r1.intValue()
            java.lang.Long r2 = r13.zzavb
            long r2 = r2.longValue()
            r5 = r60
            zza(r5, r1, r2)
            goto L_0x065a
        L_0x062f:
            r10 = r4
            r18 = r5
            r5 = r11
            r13 = r47
            r11 = r54
            r17 = r56
            r59 = r57
            java.lang.Integer r1 = r12.zzatk
            int r1 = r1.intValue()
            boolean r1 = r10.get(r1)
            if (r1 == 0) goto L_0x066f
            com.google.android.gms.internal.measurement.zzfi r1 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zzjc()
            java.lang.String r2 = "Event filter already evaluated true. audience ID, filter ID"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r14)
            java.lang.Integer r4 = r12.zzatk
            r1.zze(r2, r3, r4)
        L_0x065a:
            r4 = r10
            r1 = r11
            r47 = r13
            r2 = r17
            r12 = r53
            r13 = r55
            r10 = r58
            r6 = r59
            r3 = r79
            r11 = r5
            r5 = r18
            goto L_0x04ee
        L_0x066f:
            r1 = r7
            r2 = r12
            r3 = r18
            r4 = r28
            r50 = r5
            r5 = r8
            java.lang.Boolean r1 = r1.zza(r2, r3, r4, r5)
            com.google.android.gms.internal.measurement.zzfi r2 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zzjc()
            java.lang.String r3 = "Event filter result"
            if (r1 != 0) goto L_0x068b
            java.lang.String r4 = "null"
            goto L_0x068c
        L_0x068b:
            r4 = r1
        L_0x068c:
            r2.zzg(r3, r4)
            if (r1 != 0) goto L_0x0699
            java.lang.Integer r1 = java.lang.Integer.valueOf(r14)
            r15.add(r1)
            goto L_0x06da
        L_0x0699:
            java.lang.Integer r2 = r12.zzatk
            int r2 = r2.intValue()
            r11.set(r2)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x06da
            java.lang.Integer r1 = r12.zzatk
            int r1 = r1.intValue()
            r10.set(r1)
            goto L_0x06da
        L_0x06b2:
            r10 = r4
            r18 = r5
            r50 = r11
            r13 = r47
            r11 = r54
            r17 = r56
            r59 = r57
            com.google.android.gms.internal.measurement.zzfi r1 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zziy()
            java.lang.String r2 = "Invalid event filter ID. appId, id"
            r4 = r52
            r3 = r77
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzfi.zzbp(r77)
            java.lang.Integer r6 = r12.zzatk
            java.lang.String r6 = java.lang.String.valueOf(r6)
            r1.zze(r2, r5, r6)
        L_0x06da:
            r4 = r10
            r1 = r11
            r47 = r13
            r2 = r17
            r5 = r18
            r11 = r50
            r12 = r53
            r13 = r55
            r10 = r58
            r6 = r59
        L_0x06ec:
            r3 = r79
            goto L_0x04ee
        L_0x06f0:
            r55 = r13
            r44 = r6
            r42 = r10
            r43 = r15
            r10 = r46
            r2 = r47
            r13 = r48
            r45 = r52
            r40 = r55
            r11 = r77
            goto L_0x041a
        L_0x0706:
            r46 = r10
            r3 = r11
            r55 = r40
            r58 = r42
            r15 = r43
            r59 = r44
            r4 = r45
            r1 = r26
            r2 = r27
            r16 = r38
        L_0x0719:
            int r6 = r25 + 1
            r13 = r78
            r32 = r4
            r29 = r15
            r8 = r30
            r9 = r46
            r33 = r55
            r34 = r58
            r12 = r59
            r14 = r79
            r15 = r3
            goto L_0x018b
        L_0x0730:
            r59 = r12
            r3 = r15
            r15 = r29
            r4 = r32
            r55 = r33
            r58 = r34
            r1 = r14
            if (r1 == 0) goto L_0x09f0
            android.support.v4.util.ArrayMap r2 = new android.support.v4.util.ArrayMap
            r2.<init>()
            int r5 = r1.length
            r6 = 0
        L_0x0745:
            if (r6 >= r5) goto L_0x09f0
            r8 = r1[r6]
            java.lang.String r9 = r8.name
            java.lang.Object r9 = r2.get(r9)
            java.util.Map r9 = (java.util.Map) r9
            if (r9 != 0) goto L_0x0769
            com.google.android.gms.internal.measurement.zzek r9 = r76.zzjh()
            java.lang.String r10 = r8.name
            java.util.Map r9 = r9.zzl(r3, r10)
            if (r9 != 0) goto L_0x0764
            android.support.v4.util.ArrayMap r9 = new android.support.v4.util.ArrayMap
            r9.<init>()
        L_0x0764:
            java.lang.String r10 = r8.name
            r2.put(r10, r9)
        L_0x0769:
            java.util.Set r10 = r9.keySet()
            java.util.Iterator r10 = r10.iterator()
        L_0x0771:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x09d8
            java.lang.Object r11 = r10.next()
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            java.lang.Integer r12 = java.lang.Integer.valueOf(r11)
            boolean r12 = r15.contains(r12)
            if (r12 == 0) goto L_0x079d
            com.google.android.gms.internal.measurement.zzfi r12 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r12 = r12.zzjc()
            java.lang.String r13 = "Skipping failed audience ID"
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r12.zzg(r13, r11)
            goto L_0x0771
        L_0x079d:
            java.lang.Integer r12 = java.lang.Integer.valueOf(r11)
            r13 = r59
            java.lang.Object r12 = r13.get(r12)
            com.google.android.gms.internal.measurement.zzkp r12 = (com.google.android.gms.internal.measurement.zzkp) r12
            java.lang.Integer r14 = java.lang.Integer.valueOf(r11)
            r1 = r58
            java.lang.Object r14 = r1.get(r14)
            java.util.BitSet r14 = (java.util.BitSet) r14
            r61 = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r11)
            r62 = r5
            r5 = r55
            java.lang.Object r2 = r5.get(r2)
            java.util.BitSet r2 = (java.util.BitSet) r2
            if (r22 == 0) goto L_0x07d4
            r63 = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r11)
            java.lang.Object r2 = r4.get(r2)
            java.util.Map r2 = (java.util.Map) r2
            goto L_0x07d7
        L_0x07d4:
            r63 = r2
            r2 = 0
        L_0x07d7:
            if (r12 != 0) goto L_0x0823
            com.google.android.gms.internal.measurement.zzkp r12 = new com.google.android.gms.internal.measurement.zzkp
            r12.<init>()
            java.lang.Integer r14 = java.lang.Integer.valueOf(r11)
            r13.put(r14, r12)
            r64 = r2
            r14 = 1
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r14)
            r12.zzauv = r2
            java.util.BitSet r2 = new java.util.BitSet
            r2.<init>()
            java.lang.Integer r12 = java.lang.Integer.valueOf(r11)
            r1.put(r12, r2)
            java.util.BitSet r12 = new java.util.BitSet
            r12.<init>()
            java.lang.Integer r14 = java.lang.Integer.valueOf(r11)
            r5.put(r14, r12)
            if (r22 == 0) goto L_0x081a
            android.support.v4.util.ArrayMap r14 = new android.support.v4.util.ArrayMap
            r14.<init>()
            r65 = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r11)
            r4.put(r2, r14)
            r66 = r10
            r2 = r14
            goto L_0x0820
        L_0x081a:
            r65 = r2
            r66 = r10
            r2 = r64
        L_0x0820:
            r14 = r65
            goto L_0x0829
        L_0x0823:
            r64 = r2
            r66 = r10
            r12 = r63
        L_0x0829:
            java.lang.Integer r10 = java.lang.Integer.valueOf(r11)
            java.lang.Object r10 = r9.get(r10)
            java.util.List r10 = (java.util.List) r10
            java.util.Iterator r10 = r10.iterator()
        L_0x0837:
            boolean r16 = r10.hasNext()
            if (r16 == 0) goto L_0x09c6
            java.lang.Object r16 = r10.next()
            r67 = r9
            r9 = r16
            com.google.android.gms.internal.measurement.zzkk r9 = (com.google.android.gms.internal.measurement.zzkk) r9
            r68 = r10
            com.google.android.gms.internal.measurement.zzfi r10 = r76.zzgi()
            r69 = r4
            r4 = 2
            boolean r10 = r10.isLoggable(r4)
            if (r10 == 0) goto L_0x0891
            com.google.android.gms.internal.measurement.zzfi r10 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r10 = r10.zzjc()
            java.lang.String r4 = "Evaluating filter. audience, filter, property"
            r70 = r5
            java.lang.Integer r5 = java.lang.Integer.valueOf(r11)
            r71 = r13
            java.lang.Integer r13 = r9.zzatk
            r72 = r1
            com.google.android.gms.internal.measurement.zzfg r1 = r76.zzgf()
            r73 = r6
            java.lang.String r6 = r9.zzauc
            java.lang.String r1 = r1.zzbo(r6)
            r10.zzd(r4, r5, r13, r1)
            com.google.android.gms.internal.measurement.zzfi r1 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zzjc()
            java.lang.String r4 = "Filter definition"
            com.google.android.gms.internal.measurement.zzjz r5 = r76.zzjf()
            java.lang.String r5 = r5.zza(r9)
            r1.zzg(r4, r5)
            goto L_0x0899
        L_0x0891:
            r72 = r1
            r70 = r5
            r73 = r6
            r71 = r13
        L_0x0899:
            java.lang.Integer r1 = r9.zzatk
            if (r1 == 0) goto L_0x0993
            java.lang.Integer r1 = r9.zzatk
            int r1 = r1.intValue()
            r4 = 256(0x100, float:3.59E-43)
            if (r1 <= r4) goto L_0x08a9
            goto L_0x0995
        L_0x08a9:
            if (r22 == 0) goto L_0x092a
            if (r9 == 0) goto L_0x08c3
            com.google.android.gms.internal.measurement.zzki r1 = r9.zzaud
            if (r1 == 0) goto L_0x08c3
            com.google.android.gms.internal.measurement.zzki r1 = r9.zzaud
            java.lang.Boolean r1 = r1.zzatu
            if (r1 == 0) goto L_0x08c3
            com.google.android.gms.internal.measurement.zzki r1 = r9.zzaud
            java.lang.Boolean r1 = r1.zzatu
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x08c3
            r1 = 1
            goto L_0x08c4
        L_0x08c3:
            r1 = 0
        L_0x08c4:
            java.lang.Integer r5 = r9.zzatk
            int r5 = r5.intValue()
            boolean r5 = r14.get(r5)
            if (r5 == 0) goto L_0x08dd
            if (r1 != 0) goto L_0x08dd
            com.google.android.gms.internal.measurement.zzfi r1 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zzjc()
            java.lang.String r5 = "Property filter already evaluated true and it is not associated with a dynamic audience. audience ID, filter ID"
            goto L_0x0940
        L_0x08dd:
            java.lang.Boolean r5 = r7.zza(r9, r8)
            com.google.android.gms.internal.measurement.zzfi r6 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r6 = r6.zzjc()
            java.lang.String r10 = "Property filter result"
            if (r5 != 0) goto L_0x08f0
            java.lang.String r13 = "null"
            goto L_0x08f1
        L_0x08f0:
            r13 = r5
        L_0x08f1:
            r6.zzg(r10, r13)
            if (r5 != 0) goto L_0x08f8
            goto L_0x0972
        L_0x08f8:
            java.lang.Integer r6 = r9.zzatk
            int r6 = r6.intValue()
            r12.set(r6)
            java.lang.Integer r6 = r9.zzatk
            int r6 = r6.intValue()
            boolean r10 = r5.booleanValue()
            r14.set(r6, r10)
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x0949
            if (r1 == 0) goto L_0x0949
            java.lang.Long r1 = r8.zzaws
            if (r1 == 0) goto L_0x0949
            java.lang.Integer r1 = r9.zzatk
            int r1 = r1.intValue()
            java.lang.Long r5 = r8.zzaws
            long r5 = r5.longValue()
            zza(r2, r1, r5)
            goto L_0x0949
        L_0x092a:
            java.lang.Integer r1 = r9.zzatk
            int r1 = r1.intValue()
            boolean r1 = r14.get(r1)
            if (r1 == 0) goto L_0x0959
            com.google.android.gms.internal.measurement.zzfi r1 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zzjc()
            java.lang.String r5 = "Property filter already evaluated true. audience ID, filter ID"
        L_0x0940:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r11)
            java.lang.Integer r9 = r9.zzatk
            r1.zze(r5, r6, r9)
        L_0x0949:
            r9 = r67
            r10 = r68
            r4 = r69
            r5 = r70
            r13 = r71
            r1 = r72
            r6 = r73
            goto L_0x0837
        L_0x0959:
            java.lang.Boolean r1 = r7.zza(r9, r8)
            com.google.android.gms.internal.measurement.zzfi r5 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zzjc()
            java.lang.String r6 = "Property filter result"
            if (r1 != 0) goto L_0x096c
            java.lang.String r10 = "null"
            goto L_0x096d
        L_0x096c:
            r10 = r1
        L_0x096d:
            r5.zzg(r6, r10)
            if (r1 != 0) goto L_0x097a
        L_0x0972:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r11)
            r15.add(r1)
            goto L_0x0949
        L_0x097a:
            java.lang.Integer r5 = r9.zzatk
            int r5 = r5.intValue()
            r12.set(r5)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0949
            java.lang.Integer r1 = r9.zzatk
            int r1 = r1.intValue()
            r14.set(r1)
            goto L_0x0949
        L_0x0993:
            r4 = 256(0x100, float:3.59E-43)
        L_0x0995:
            com.google.android.gms.internal.measurement.zzfi r1 = r76.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zziy()
            java.lang.String r2 = "Invalid property filter ID. appId, id"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzfi.zzbp(r77)
            java.lang.Integer r6 = r9.zzatk
            java.lang.String r6 = java.lang.String.valueOf(r6)
            r1.zze(r2, r5, r6)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r11)
            r15.add(r1)
            r2 = r61
            r5 = r62
            r10 = r66
            r9 = r67
            r4 = r69
            r55 = r70
            r59 = r71
            r58 = r72
            r6 = r73
            goto L_0x09d4
        L_0x09c6:
            r69 = r4
            r58 = r1
            r55 = r5
            r59 = r13
            r2 = r61
            r5 = r62
            r10 = r66
        L_0x09d4:
            r1 = r79
            goto L_0x0771
        L_0x09d8:
            r61 = r2
            r69 = r4
            r62 = r5
            r73 = r6
            r70 = r55
            r72 = r58
            r71 = r59
            r4 = 256(0x100, float:3.59E-43)
            int r6 = r73 + 1
            r4 = r69
            r1 = r79
            goto L_0x0745
        L_0x09f0:
            r69 = r4
            r70 = r55
            r71 = r59
            r1 = r58
            int r2 = r1.size()
            com.google.android.gms.internal.measurement.zzkp[] r2 = new com.google.android.gms.internal.measurement.zzkp[r2]
            java.util.Set r4 = r1.keySet()
            java.util.Iterator r4 = r4.iterator()
            r5 = 0
        L_0x0a07:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x0b19
            java.lang.Object r6 = r4.next()
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r6)
            boolean r8 = r15.contains(r8)
            if (r8 != 0) goto L_0x0a07
            java.lang.Integer r8 = java.lang.Integer.valueOf(r6)
            r9 = r71
            java.lang.Object r8 = r9.get(r8)
            com.google.android.gms.internal.measurement.zzkp r8 = (com.google.android.gms.internal.measurement.zzkp) r8
            if (r8 != 0) goto L_0x0a34
            com.google.android.gms.internal.measurement.zzkp r8 = new com.google.android.gms.internal.measurement.zzkp
            r8.<init>()
        L_0x0a34:
            int r10 = r5 + 1
            r2[r5] = r8
            java.lang.Integer r5 = java.lang.Integer.valueOf(r6)
            r8.zzate = r5
            com.google.android.gms.internal.measurement.zzkv r5 = new com.google.android.gms.internal.measurement.zzkv
            r5.<init>()
            r8.zzaut = r5
            com.google.android.gms.internal.measurement.zzkv r5 = r8.zzaut
            java.lang.Integer r11 = java.lang.Integer.valueOf(r6)
            java.lang.Object r11 = r1.get(r11)
            java.util.BitSet r11 = (java.util.BitSet) r11
            long[] r11 = com.google.android.gms.internal.measurement.zzjz.zza(r11)
            r5.zzawm = r11
            com.google.android.gms.internal.measurement.zzkv r5 = r8.zzaut
            java.lang.Integer r11 = java.lang.Integer.valueOf(r6)
            r12 = r70
            java.lang.Object r11 = r12.get(r11)
            java.util.BitSet r11 = (java.util.BitSet) r11
            long[] r11 = com.google.android.gms.internal.measurement.zzjz.zza(r11)
            r5.zzawl = r11
            if (r22 == 0) goto L_0x0a82
            com.google.android.gms.internal.measurement.zzkv r5 = r8.zzaut
            java.lang.Integer r11 = java.lang.Integer.valueOf(r6)
            r13 = r69
            java.lang.Object r11 = r13.get(r11)
            java.util.Map r11 = (java.util.Map) r11
            com.google.android.gms.internal.measurement.zzkq[] r11 = zzd(r11)
            r5.zzawn = r11
            goto L_0x0a84
        L_0x0a82:
            r13 = r69
        L_0x0a84:
            com.google.android.gms.internal.measurement.zzek r5 = r76.zzjh()
            com.google.android.gms.internal.measurement.zzkv r8 = r8.zzaut
            r5.zzch()
            r5.zzab()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r77)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)
            int r11 = r8.zzwb()     // Catch:{ IOException -> 0x0af8 }
            byte[] r11 = new byte[r11]     // Catch:{ IOException -> 0x0af8 }
            int r14 = r11.length     // Catch:{ IOException -> 0x0af8 }
            r74 = r1
            r1 = 0
            com.google.android.gms.internal.measurement.zzacb r14 = com.google.android.gms.internal.measurement.zzacb.zzb(r11, r1, r14)     // Catch:{ IOException -> 0x0af6 }
            r8.zza(r14)     // Catch:{ IOException -> 0x0af6 }
            r14.zzvt()     // Catch:{ IOException -> 0x0af6 }
            android.content.ContentValues r8 = new android.content.ContentValues
            r8.<init>()
            java.lang.String r14 = "app_id"
            r8.put(r14, r3)
            java.lang.String r14 = "audience_id"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r8.put(r14, r6)
            java.lang.String r6 = "current_results"
            r8.put(r6, r11)
            android.database.sqlite.SQLiteDatabase r6 = r5.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0ae8 }
            java.lang.String r11 = "audience_filter_values"
            r14 = 5
            r1 = 0
            long r16 = r6.insertWithOnConflict(r11, r1, r8, r14)     // Catch:{ SQLiteException -> 0x0ae6 }
            r18 = -1
            int r6 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r6 != 0) goto L_0x0b0e
            com.google.android.gms.internal.measurement.zzfi r6 = r5.zzgi()     // Catch:{ SQLiteException -> 0x0ae6 }
            com.google.android.gms.internal.measurement.zzfk r6 = r6.zziv()     // Catch:{ SQLiteException -> 0x0ae6 }
            java.lang.String r8 = "Failed to insert filter results (got -1). appId"
            java.lang.Object r11 = com.google.android.gms.internal.measurement.zzfi.zzbp(r77)     // Catch:{ SQLiteException -> 0x0ae6 }
            r6.zzg(r8, r11)     // Catch:{ SQLiteException -> 0x0ae6 }
            goto L_0x0b0e
        L_0x0ae6:
            r0 = move-exception
            goto L_0x0aea
        L_0x0ae8:
            r0 = move-exception
            r1 = 0
        L_0x0aea:
            r6 = r0
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzgi()
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziv()
            java.lang.String r8 = "Error storing filter results. appId"
            goto L_0x0b07
        L_0x0af6:
            r0 = move-exception
            goto L_0x0afb
        L_0x0af8:
            r0 = move-exception
            r74 = r1
        L_0x0afb:
            r1 = 0
            r6 = r0
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzgi()
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziv()
            java.lang.String r8 = "Configuration loss. Failed to serialize filter results. appId"
        L_0x0b07:
            java.lang.Object r11 = com.google.android.gms.internal.measurement.zzfi.zzbp(r77)
            r5.zze(r8, r11, r6)
        L_0x0b0e:
            r71 = r9
            r5 = r10
            r70 = r12
            r69 = r13
            r1 = r74
            goto L_0x0a07
        L_0x0b19:
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r2, r5)
            com.google.android.gms.internal.measurement.zzkp[] r1 = (com.google.android.gms.internal.measurement.zzkp[]) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzed.zza(java.lang.String, com.google.android.gms.internal.measurement.zzkr[], com.google.android.gms.internal.measurement.zzkx[]):com.google.android.gms.internal.measurement.zzkp[]");
    }

    /* access modifiers changed from: protected */
    public final boolean zzgn() {
        return false;
    }
}
