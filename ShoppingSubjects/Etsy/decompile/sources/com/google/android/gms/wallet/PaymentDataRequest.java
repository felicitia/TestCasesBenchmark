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

@Class(creator = "PaymentDataRequestCreator")
public final class PaymentDataRequest extends AbstractSafeParcelable {
    public static final Creator<PaymentDataRequest> CREATOR = new zzae();
    @Field(id = 6)
    ArrayList<Integer> zzbw;
    @Field(id = 10)
    String zzby;
    @Field(id = 2)
    boolean zzdd;
    @Field(id = 4)
    boolean zzde;
    @Field(id = 7)
    PaymentMethodTokenizationParameters zzdo;
    @Field(id = 1)
    boolean zzdv;
    @Field(id = 3)
    CardRequirements zzdw;
    @Field(id = 5)
    ShippingAddressRequirements zzdx;
    @Field(id = 8)
    TransactionInfo zzdy;
    @Field(defaultValue = "true", id = 9)
    boolean zzdz;

    public final class a {
        private a() {
        }
    }

    private PaymentDataRequest() {
        this.zzdz = true;
    }

    @Constructor
    PaymentDataRequest(@Param(id = 1) boolean z, @Param(id = 2) boolean z2, @Param(id = 3) CardRequirements cardRequirements, @Param(id = 4) boolean z3, @Param(id = 5) ShippingAddressRequirements shippingAddressRequirements, @Param(id = 6) ArrayList<Integer> arrayList, @Param(id = 7) PaymentMethodTokenizationParameters paymentMethodTokenizationParameters, @Param(id = 8) TransactionInfo transactionInfo, @Param(id = 9) boolean z4, @Param(id = 10) String str) {
        this.zzdv = z;
        this.zzdd = z2;
        this.zzdw = cardRequirements;
        this.zzde = z3;
        this.zzdx = shippingAddressRequirements;
        this.zzbw = arrayList;
        this.zzdo = paymentMethodTokenizationParameters;
        this.zzdy = transactionInfo;
        this.zzdz = z4;
        this.zzby = str;
    }

    public static a newBuilder() {
        return new a();
    }

    public final ArrayList<Integer> getAllowedPaymentMethods() {
        return this.zzbw;
    }

    @Nullable
    public final CardRequirements getCardRequirements() {
        return this.zzdw;
    }

    public final PaymentMethodTokenizationParameters getPaymentMethodTokenizationParameters() {
        return this.zzdo;
    }

    @Nullable
    public final ShippingAddressRequirements getShippingAddressRequirements() {
        return this.zzdx;
    }

    public final TransactionInfo getTransactionInfo() {
        return this.zzdy;
    }

    public final boolean isEmailRequired() {
        return this.zzdv;
    }

    public final boolean isPhoneNumberRequired() {
        return this.zzdd;
    }

    public final boolean isShippingAddressRequired() {
        return this.zzde;
    }

    public final boolean isUiRequired() {
        return this.zzdz;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zzdv);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzdd);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzdw, i, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzde);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzdx, i, false);
        SafeParcelWriter.writeIntegerList(parcel, 6, this.zzbw, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzdo, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzdy, i, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzdz);
        SafeParcelWriter.writeString(parcel, 10, this.zzby, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
