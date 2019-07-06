package com.google.android.gms.internal.ads;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdRequest.Gender;
import com.google.ads.mediation.a;
import java.util.Date;
import java.util.HashSet;

@bu
public final class arv {
    public static int a(ErrorCode errorCode) {
        switch (arw.a[errorCode.ordinal()]) {
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            default:
                return 0;
        }
    }

    public static a a(zzjj zzjj, boolean z) {
        Gender gender;
        HashSet hashSet = zzjj.zzapy != null ? new HashSet(zzjj.zzapy) : null;
        Date date = new Date(zzjj.zzapw);
        switch (zzjj.zzapx) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            default:
                gender = Gender.UNKNOWN;
                break;
        }
        a aVar = new a(date, gender, hashSet, z, zzjj.zzaqe);
        return aVar;
    }
}
