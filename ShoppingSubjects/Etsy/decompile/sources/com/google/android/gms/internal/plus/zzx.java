package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.internal.plus.zzr.zzc;
import java.util.HashSet;

public final class zzx implements Creator<zzc> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i2 = 0;
        String str = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    i = 1;
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    i = 2;
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    continue;
            }
            hashSet.add(Integer.valueOf(i));
        }
        if (parcel.dataPosition() == validateObjectHeader) {
            return new zzc(hashSet, i2, str);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(validateObjectHeader);
        throw new ParseException(sb.toString(), parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzc[i];
    }
}
