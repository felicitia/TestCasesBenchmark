package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class LineItem extends AbstractSafeParcelable {
    public static final Creator<LineItem> CREATOR = new zzt();
    String description;
    String zzan;
    String zzao;
    String zzca;
    String zzcb;
    int zzcc;

    LineItem() {
        this.zzcc = 0;
    }

    LineItem(String str, String str2, String str3, String str4, int i, String str5) {
        this.description = str;
        this.zzca = str2;
        this.zzcb = str3;
        this.zzan = str4;
        this.zzcc = i;
        this.zzao = str5;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.description, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzca, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzcb, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzan, false);
        SafeParcelWriter.writeInt(parcel, 6, this.zzcc);
        SafeParcelWriter.writeString(parcel, 7, this.zzao, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
