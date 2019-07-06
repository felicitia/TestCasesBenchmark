package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzka extends AbstractSafeParcelable {
    public static final Creator<zzka> CREATOR = new zzkb();
    public final String name;
    public final String origin;
    private final int versionCode;
    private final String zzale;
    public final long zzast;
    private final Long zzasu;
    private final Float zzasv;
    private final Double zzasw;

    zzka(int i, String str, long j, Long l, Float f, String str2, String str3, Double d) {
        this.versionCode = i;
        this.name = str;
        this.zzast = j;
        this.zzasu = l;
        Double d2 = null;
        this.zzasv = null;
        if (i == 1) {
            if (f != null) {
                d2 = Double.valueOf(f.doubleValue());
            }
            this.zzasw = d2;
        } else {
            this.zzasw = d;
        }
        this.zzale = str2;
        this.origin = str3;
    }

    zzka(zzkc zzkc) {
        this(zzkc.name, zzkc.zzast, zzkc.value, zzkc.origin);
    }

    zzka(String str, long j, Object obj, String str2) {
        Preconditions.checkNotEmpty(str);
        this.versionCode = 2;
        this.name = str;
        this.zzast = j;
        this.origin = str2;
        if (obj == null) {
            this.zzasu = null;
            this.zzasv = null;
            this.zzasw = null;
            this.zzale = null;
        } else if (obj instanceof Long) {
            this.zzasu = (Long) obj;
            this.zzasv = null;
            this.zzasw = null;
            this.zzale = null;
        } else if (obj instanceof String) {
            this.zzasu = null;
            this.zzasv = null;
            this.zzasw = null;
            this.zzale = (String) obj;
        } else if (obj instanceof Double) {
            this.zzasu = null;
            this.zzasv = null;
            this.zzasw = (Double) obj;
            this.zzale = null;
        } else {
            throw new IllegalArgumentException("User attribute given of un-supported type");
        }
    }

    public final Object getValue() {
        if (this.zzasu != null) {
            return this.zzasu;
        }
        if (this.zzasw != null) {
            return this.zzasw;
        }
        if (this.zzale != null) {
            return this.zzale;
        }
        return null;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.name, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzast);
        SafeParcelWriter.writeLongObject(parcel, 4, this.zzasu, false);
        SafeParcelWriter.writeFloatObject(parcel, 5, null, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzale, false);
        SafeParcelWriter.writeString(parcel, 7, this.origin, false);
        SafeParcelWriter.writeDoubleObject(parcel, 8, this.zzasw, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
