package com.google.android.gms.plus.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzo implements Creator<zzn> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        String[] strArr = null;
        String[] strArr2 = null;
        String[] strArr3 = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        PlusCommonExtras plusCommonExtras = null;
        int i = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId != 1000) {
                switch (fieldId) {
                    case 1:
                        str = SafeParcelReader.createString(parcel, readHeader);
                        break;
                    case 2:
                        strArr = SafeParcelReader.createStringArray(parcel, readHeader);
                        break;
                    case 3:
                        strArr2 = SafeParcelReader.createStringArray(parcel, readHeader);
                        break;
                    case 4:
                        strArr3 = SafeParcelReader.createStringArray(parcel, readHeader);
                        break;
                    case 5:
                        str2 = SafeParcelReader.createString(parcel, readHeader);
                        break;
                    case 6:
                        str3 = SafeParcelReader.createString(parcel, readHeader);
                        break;
                    case 7:
                        str4 = SafeParcelReader.createString(parcel, readHeader);
                        break;
                    case 8:
                        str5 = SafeParcelReader.createString(parcel, readHeader);
                        break;
                    case 9:
                        plusCommonExtras = (PlusCommonExtras) SafeParcelReader.createParcelable(parcel, readHeader, PlusCommonExtras.CREATOR);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, readHeader);
                        break;
                }
            } else {
                i = SafeParcelReader.readInt(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        zzn zzn = new zzn(i, str, strArr, strArr2, strArr3, str2, str3, str4, str5, plusCommonExtras);
        return zzn;
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzn[i];
    }
}
