package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;

public final class zzs extends AbstractSafeParcelable {
    public static final Creator<zzs> CREATOR = new zzu();
    private final String name;
    private final int weight;
    private final String zzaa;
    private final boolean zzab;
    private final boolean zzac;
    private final String zzad;
    private final zzn[] zzae;
    private final String zzaf;
    private final zzv zzag;

    zzs(String str, String str2, boolean z, int i, boolean z2, String str3, zzn[] zznArr, String str4, zzv zzv) {
        this.name = str;
        this.zzaa = str2;
        this.zzab = z;
        this.weight = i;
        this.zzac = z2;
        this.zzad = str3;
        this.zzae = zznArr;
        this.zzaf = str4;
        this.zzag = zzv;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzs)) {
            return false;
        }
        zzs zzs = (zzs) obj;
        return this.zzab == zzs.zzab && this.weight == zzs.weight && this.zzac == zzs.zzac && Objects.equal(this.name, zzs.name) && Objects.equal(this.zzaa, zzs.zzaa) && Objects.equal(this.zzad, zzs.zzad) && Objects.equal(this.zzaf, zzs.zzaf) && Objects.equal(this.zzag, zzs.zzag) && Arrays.equals(this.zzae, zzs.zzae);
    }

    public final int hashCode() {
        return Objects.hashCode(this.name, this.zzaa, Boolean.valueOf(this.zzab), Integer.valueOf(this.weight), Boolean.valueOf(this.zzac), this.zzad, Integer.valueOf(Arrays.hashCode(this.zzae)), this.zzaf, this.zzag);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.name, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzaa, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzab);
        SafeParcelWriter.writeInt(parcel, 4, this.weight);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzac);
        SafeParcelWriter.writeString(parcel, 6, this.zzad, false);
        SafeParcelWriter.writeTypedArray(parcel, 7, this.zzae, i, false);
        SafeParcelWriter.writeString(parcel, 11, this.zzaf, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzag, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
