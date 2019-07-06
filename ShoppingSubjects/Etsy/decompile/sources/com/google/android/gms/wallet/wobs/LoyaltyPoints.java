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

@Class(creator = "LoyaltyPointsCreator")
@Reserved({1, 4})
public final class LoyaltyPoints extends AbstractSafeParcelable {
    public static final Creator<LoyaltyPoints> CREATOR = new zzi();
    @Field(id = 2)
    String label;
    @Field(id = 5)
    TimeInterval zzcp;
    @Field(id = 3)
    LoyaltyPointsBalance zzgr;

    public final class a {
        private a() {
        }
    }

    LoyaltyPoints() {
    }

    @Constructor
    LoyaltyPoints(@Param(id = 2) String str, @Param(id = 3) LoyaltyPointsBalance loyaltyPointsBalance, @Param(id = 5) TimeInterval timeInterval) {
        this.label = str;
        this.zzgr = loyaltyPointsBalance;
        this.zzcp = timeInterval;
    }

    public static a newBuilder() {
        return new a();
    }

    public final LoyaltyPointsBalance getBalance() {
        return this.zzgr;
    }

    public final String getLabel() {
        return this.label;
    }

    @Deprecated
    public final String getType() {
        return null;
    }

    public final TimeInterval getValidTimeInterval() {
        return this.zzcp;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.label, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzgr, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzcp, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
