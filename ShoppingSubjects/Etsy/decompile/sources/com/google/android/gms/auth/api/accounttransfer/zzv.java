package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import java.util.HashSet;

public final class zzv implements Creator<zzu> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i2 = 0;
        zzw zzw = null;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    i = 1;
                    break;
                case 2:
                    zzw = (zzw) SafeParcelReader.createParcelable(parcel, readHeader, zzw.CREATOR);
                    i = 2;
                    break;
                case 3:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    i = 3;
                    break;
                case 4:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
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
        zzu zzu = new zzu(hashSet, i2, zzw, str, str2);
        return zzu;
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzu[i];
    }
}
