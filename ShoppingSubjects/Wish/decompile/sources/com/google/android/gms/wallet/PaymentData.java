package com.google.android.gms.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class PaymentData extends AbstractSafeParcelable implements AutoResolvableResult {
    public static final Creator<PaymentData> CREATOR = new zzac();
    private String zzaw;
    private String zzaz;
    private PaymentMethodToken zzbg;
    private String zzby;
    private CardInfo zzds;
    private UserAddress zzdt;
    private Bundle zzdu;

    private PaymentData() {
    }

    PaymentData(String str, CardInfo cardInfo, UserAddress userAddress, PaymentMethodToken paymentMethodToken, String str2, Bundle bundle, String str3) {
        this.zzaz = str;
        this.zzds = cardInfo;
        this.zzdt = userAddress;
        this.zzbg = paymentMethodToken;
        this.zzaw = str2;
        this.zzdu = bundle;
        this.zzby = str3;
    }

    public static PaymentData getFromIntent(Intent intent) {
        return (PaymentData) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.gms.wallet.PaymentData", CREATOR);
    }

    public final CardInfo getCardInfo() {
        return this.zzds;
    }

    public final String getEmail() {
        return this.zzaz;
    }

    public final PaymentMethodToken getPaymentMethodToken() {
        return this.zzbg;
    }

    public final UserAddress getShippingAddress() {
        return this.zzdt;
    }

    public final void putIntoIntent(Intent intent) {
        SafeParcelableSerializer.serializeToIntentExtra(this, intent, "com.google.android.gms.wallet.PaymentData");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzaz, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzds, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzdt, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbg, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzaw, false);
        SafeParcelWriter.writeBundle(parcel, 6, this.zzdu, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzby, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
