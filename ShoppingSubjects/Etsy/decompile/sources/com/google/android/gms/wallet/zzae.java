package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zzae implements Creator<PaymentDataRequest> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        CardRequirements cardRequirements = null;
        ShippingAddressRequirements shippingAddressRequirements = null;
        ArrayList arrayList = null;
        PaymentMethodTokenizationParameters paymentMethodTokenizationParameters = null;
        TransactionInfo transactionInfo = null;
        String str = null;
        boolean z4 = true;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 2:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 3:
                    cardRequirements = (CardRequirements) SafeParcelReader.createParcelable(parcel2, readHeader, CardRequirements.CREATOR);
                    break;
                case 4:
                    z3 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 5:
                    shippingAddressRequirements = (ShippingAddressRequirements) SafeParcelReader.createParcelable(parcel2, readHeader, ShippingAddressRequirements.CREATOR);
                    break;
                case 6:
                    arrayList = SafeParcelReader.createIntegerList(parcel2, readHeader);
                    break;
                case 7:
                    paymentMethodTokenizationParameters = (PaymentMethodTokenizationParameters) SafeParcelReader.createParcelable(parcel2, readHeader, PaymentMethodTokenizationParameters.CREATOR);
                    break;
                case 8:
                    transactionInfo = (TransactionInfo) SafeParcelReader.createParcelable(parcel2, readHeader, TransactionInfo.CREATOR);
                    break;
                case 9:
                    z4 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 10:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        PaymentDataRequest paymentDataRequest = new PaymentDataRequest(z, z2, cardRequirements, z3, shippingAddressRequirements, arrayList, paymentMethodTokenizationParameters, transactionInfo, z4, str);
        return paymentDataRequest;
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PaymentDataRequest[i];
    }
}
