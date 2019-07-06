package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.internal.plus.zzr.zze;
import java.util.HashSet;

public final class zzz implements Creator<zze> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        boolean z = false;
        int i2 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        int i3 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
                    i = 1;
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    i = 2;
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    i = 3;
                    break;
                case 4:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    i = 4;
                    break;
                case 5:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    i = 5;
                    break;
                case 6:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    i = 6;
                    break;
                case 7:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    i = 7;
                    break;
                case 8:
                    str6 = SafeParcelReader.createString(parcel, readHeader);
                    i = 8;
                    break;
                case 9:
                    str7 = SafeParcelReader.createString(parcel, readHeader);
                    i = 9;
                    break;
                case 10:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    i = 10;
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
        zze zze = new zze(hashSet, i3, str, str2, str3, str4, str5, z, str6, str7, i2);
        return zze;
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zze[i];
    }
}
