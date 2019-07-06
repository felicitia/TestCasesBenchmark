package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.ArrayUtils;
import java.util.ArrayList;

@Class(creator = "LabelValueRowCreator")
@Reserved({1})
public final class LabelValueRow extends AbstractSafeParcelable {
    public static final Creator<LabelValueRow> CREATOR = new zze();
    @Field(id = 2)
    String zzgn;
    @Field(id = 3)
    String zzgo;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 4)
    ArrayList<LabelValue> zzgp;

    public final class a {
        private a() {
        }
    }

    LabelValueRow() {
        this.zzgp = ArrayUtils.newArrayList();
    }

    @Constructor
    LabelValueRow(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) ArrayList<LabelValue> arrayList) {
        this.zzgn = str;
        this.zzgo = str2;
        this.zzgp = arrayList;
    }

    public static a newBuilder() {
        return new a();
    }

    public final ArrayList<LabelValue> getColumns() {
        return this.zzgp;
    }

    public final String getHexBackgroundColor() {
        return this.zzgo;
    }

    public final String getHexFontColor() {
        return this.zzgn;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzgn, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzgo, false);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzgp, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
