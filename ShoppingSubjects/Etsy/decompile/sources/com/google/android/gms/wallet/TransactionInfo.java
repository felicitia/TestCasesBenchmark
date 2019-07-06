package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "TransactionInfoCreator")
public final class TransactionInfo extends AbstractSafeParcelable {
    public static final Creator<TransactionInfo> CREATOR = new zzao();
    @Field(id = 2)
    String zzan;
    @Field(id = 3)
    String zzao;
    @Field(id = 1)
    int zzen;

    public final class a {
        private a() {
        }
    }

    private TransactionInfo() {
    }

    @Constructor
    public TransactionInfo(@Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) String str2) {
        this.zzen = i;
        this.zzan = str;
        this.zzao = str2;
    }

    public static a newBuilder() {
        return new a();
    }

    public final String getCurrencyCode() {
        return this.zzao;
    }

    @Nullable
    public final String getTotalPrice() {
        return this.zzan;
    }

    public final int getTotalPriceStatus() {
        return this.zzen;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzen);
        SafeParcelWriter.writeString(parcel, 2, this.zzan, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzao, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
