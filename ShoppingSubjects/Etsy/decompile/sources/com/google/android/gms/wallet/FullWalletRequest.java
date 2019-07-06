package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "FullWalletRequestCreator")
@Reserved({1})
public final class FullWalletRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<FullWalletRequest> CREATOR = new zzm();
    @Field(id = 2)
    String zzaw;
    @Field(id = 3)
    String zzax;
    @Field(id = 4)
    Cart zzbh;

    public final class a {
        private a() {
        }

        public final a a(Cart cart) {
            FullWalletRequest.this.zzbh = cart;
            return this;
        }

        public final a a(String str) {
            FullWalletRequest.this.zzaw = str;
            return this;
        }

        public final FullWalletRequest a() {
            return FullWalletRequest.this;
        }
    }

    FullWalletRequest() {
    }

    @Constructor
    FullWalletRequest(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) Cart cart) {
        this.zzaw = str;
        this.zzax = str2;
        this.zzbh = cart;
    }

    public static a newBuilder() {
        return new a();
    }

    public final Cart getCart() {
        return this.zzbh;
    }

    public final String getGoogleTransactionId() {
        return this.zzaw;
    }

    public final String getMerchantTransactionId() {
        return this.zzax;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzaw, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzax, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbh, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
