package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.internal.plus.zzr.zzg;
import java.util.HashSet;

public final class zzab implements Creator<zzg> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Integer num;
        int i;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i2 = 0;
        String str = null;
        String str2 = null;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId != 1) {
                switch (fieldId) {
                    case 3:
                        i4 = SafeParcelReader.readInt(parcel, readHeader);
                        i = 3;
                        break;
                    case 4:
                        str2 = SafeParcelReader.createString(parcel, readHeader);
                        i = 4;
                        break;
                    case 5:
                        str = SafeParcelReader.createString(parcel, readHeader);
                        i = 5;
                        break;
                    case 6:
                        i3 = SafeParcelReader.readInt(parcel, readHeader);
                        i = 6;
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, readHeader);
                        continue;
                }
                num = Integer.valueOf(i);
            } else {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
                num = Integer.valueOf(1);
            }
            hashSet.add(num);
        }
        if (parcel.dataPosition() != validateObjectHeader) {
            StringBuilder sb = new StringBuilder(37);
            sb.append("Overread allowed size end=");
            sb.append(validateObjectHeader);
            throw new ParseException(sb.toString(), parcel);
        }
        zzg zzg = new zzg(hashSet, i2, str, i3, str2, i4);
        return zzg;
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzg[i];
    }
}
