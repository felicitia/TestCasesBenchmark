package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzex extends AbstractSafeParcelable {
    public static final Creator<zzex> CREATOR = new zzey();
    public final String name;
    public final String origin;
    public final zzeu zzahg;
    public final long zzahr;

    zzex(zzex zzex, long j) {
        Preconditions.checkNotNull(zzex);
        this.name = zzex.name;
        this.zzahg = zzex.zzahg;
        this.origin = zzex.origin;
        this.zzahr = j;
    }

    public zzex(String str, zzeu zzeu, String str2, long j) {
        this.name = str;
        this.zzahg = zzeu;
        this.origin = str2;
        this.zzahr = j;
    }

    public final String toString() {
        String str = this.origin;
        String str2 = this.name;
        String valueOf = String.valueOf(this.zzahg);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(str2).length() + String.valueOf(valueOf).length());
        sb.append("origin=");
        sb.append(str);
        sb.append(",name=");
        sb.append(str2);
        sb.append(",params=");
        sb.append(valueOf);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.name, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzahg, i, false);
        SafeParcelWriter.writeString(parcel, 4, this.origin, false);
        SafeParcelWriter.writeLong(parcel, 5, this.zzahr);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
