package com.google.android.gms.ads.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

@bu
public final class ba {
    private static String a(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = new TreeSet(bundle.keySet()).iterator();
        while (it.hasNext()) {
            Object obj = bundle.get((String) it.next());
            String str = obj == null ? "null" : obj instanceof Bundle ? a((Bundle) obj) : obj.toString();
            sb.append(str);
        }
        return sb.toString();
    }

    public static Object[] a(String str, zzjj zzjj, String str2, int i, @Nullable zzjn zzjn) {
        HashSet hashSet = new HashSet(Arrays.asList(str.split(",")));
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(str2);
        if (hashSet.contains("formatString")) {
            arrayList.add(null);
        }
        if (hashSet.contains("networkType")) {
            arrayList.add(Integer.valueOf(i));
        }
        if (hashSet.contains("birthday")) {
            arrayList.add(Long.valueOf(zzjj.zzapw));
        }
        if (hashSet.contains("extras")) {
            arrayList.add(a(zzjj.extras));
        }
        if (hashSet.contains("gender")) {
            arrayList.add(Integer.valueOf(zzjj.zzapx));
        }
        if (hashSet.contains("keywords")) {
            if (zzjj.zzapy != null) {
                arrayList.add(zzjj.zzapy.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("isTestDevice")) {
            arrayList.add(Boolean.valueOf(zzjj.zzapz));
        }
        if (hashSet.contains("tagForChildDirectedTreatment")) {
            arrayList.add(Integer.valueOf(zzjj.zzaqa));
        }
        if (hashSet.contains("manualImpressionsEnabled")) {
            arrayList.add(Boolean.valueOf(zzjj.zzaqb));
        }
        if (hashSet.contains("publisherProvidedId")) {
            arrayList.add(zzjj.zzaqc);
        }
        if (hashSet.contains(ResponseConstants.LOCATION)) {
            if (zzjj.zzaqe != null) {
                arrayList.add(zzjj.zzaqe.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("contentUrl")) {
            arrayList.add(zzjj.zzaqf);
        }
        if (hashSet.contains("networkExtras")) {
            arrayList.add(a(zzjj.zzaqg));
        }
        if (hashSet.contains("customTargeting")) {
            arrayList.add(a(zzjj.zzaqh));
        }
        if (hashSet.contains("categoryExclusions")) {
            if (zzjj.zzaqi != null) {
                arrayList.add(zzjj.zzaqi.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("requestAgent")) {
            arrayList.add(zzjj.zzaqj);
        }
        if (hashSet.contains("requestPackage")) {
            arrayList.add(zzjj.zzaqk);
        }
        if (hashSet.contains("isDesignedForFamilies")) {
            arrayList.add(Boolean.valueOf(zzjj.zzaql));
        }
        return arrayList.toArray();
    }
}
