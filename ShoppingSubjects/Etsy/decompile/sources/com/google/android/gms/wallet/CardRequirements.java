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
import java.util.ArrayList;

@Class(creator = "CardRequirementsCreator")
public final class CardRequirements extends AbstractSafeParcelable {
    public static final Creator<CardRequirements> CREATOR = new zze();
    @Field(id = 1)
    ArrayList<Integer> zzai;
    @Field(defaultValue = "true", id = 2)
    boolean zzaj;
    @Field(id = 3)
    boolean zzak;
    @Field(id = 4)
    int zzal;

    public final class a {
        private a() {
        }
    }

    private CardRequirements() {
        this.zzaj = true;
    }

    @Constructor
    CardRequirements(@Param(id = 1) ArrayList<Integer> arrayList, @Param(id = 2) boolean z, @Param(id = 3) boolean z2, @Param(id = 4) int i) {
        this.zzai = arrayList;
        this.zzaj = z;
        this.zzak = z2;
        this.zzal = i;
    }

    public static a newBuilder() {
        return new a();
    }

    public final boolean allowPrepaidCards() {
        return this.zzaj;
    }

    @Nullable
    public final ArrayList<Integer> getAllowedCardNetworks() {
        return this.zzai;
    }

    public final int getBillingAddressFormat() {
        return this.zzal;
    }

    public final boolean isBillingAddressRequired() {
        return this.zzak;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIntegerList(parcel, 1, this.zzai, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzaj);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzak);
        SafeParcelWriter.writeInt(parcel, 4, this.zzal);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
