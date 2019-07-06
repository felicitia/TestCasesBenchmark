package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

public final class zzp implements Creator<zzo> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i2 = 0;
        ArrayList arrayList = null;
        zzr zzr = null;
        int i3 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    i = 1;
                    break;
                case 2:
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, zzu.CREATOR);
                    i = 2;
                    break;
                case 3:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
                    i = 3;
                    break;
                case 4:
                    zzr = (zzr) SafeParcelReader.createParcelable(parcel, readHeader, zzr.CREATOR);
                    i = 4;
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    continue;
            }
            hashSet.add(Integer.valueOf(i));
        }
        if (parcel.dataPosition() != validateObjectHeader) {
            StringBuilder sb = new StringBuilder(37);
            sb.append("Overread allowed size end=");
            sb.append(validateObjectHeader);
            throw new ParseException(sb.toString(), parcel);
        }
        zzo zzo = new zzo(hashSet, i2, arrayList, i3, zzr);
        return zzo;
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzo[i];
    }
}
