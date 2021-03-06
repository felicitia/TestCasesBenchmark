package com.google.android.gms.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "PaymentMethodTokenizationParametersCreator")
@Reserved({1})
public final class PaymentMethodTokenizationParameters extends AbstractSafeParcelable {
    public static final Creator<PaymentMethodTokenizationParameters> CREATOR = new zzah();
    @Field(id = 2)
    int zzeb;
    @Field(id = 3)
    Bundle zzed = new Bundle();

    public final class a {
        private a() {
        }

        public final a a(int i) {
            PaymentMethodTokenizationParameters.this.zzeb = i;
            return this;
        }

        public final a a(@NonNull String str, @NonNull String str2) {
            Preconditions.checkNotEmpty(str, "Tokenization parameter name must not be empty");
            Preconditions.checkNotEmpty(str2, "Tokenization parameter value must not be empty");
            PaymentMethodTokenizationParameters.this.zzed.putString(str, str2);
            return this;
        }

        public final PaymentMethodTokenizationParameters a() {
            return PaymentMethodTokenizationParameters.this;
        }
    }

    private PaymentMethodTokenizationParameters() {
    }

    @Constructor
    PaymentMethodTokenizationParameters(@Param(id = 2) int i, @Param(id = 3) Bundle bundle) {
        this.zzeb = i;
        this.zzed = bundle;
    }

    public static a newBuilder() {
        return new a();
    }

    public final Bundle getParameters() {
        return new Bundle(this.zzed);
    }

    public final int getPaymentMethodTokenizationType() {
        return this.zzeb;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzeb);
        SafeParcelWriter.writeBundle(parcel, 3, this.zzed, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
