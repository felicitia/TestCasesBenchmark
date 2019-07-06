package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.internal.plus.zzr.zzb.C0140zzb;
import java.util.HashSet;

public final class zzw implements Creator<C0140zzb> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i2 = 0;
        int i3 = 0;
        String str = null;
        int i4 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i4 = SafeParcelReader.readInt(parcel, readHeader);
                    i = 1;
                    break;
                case 2:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    i = 2;
                    break;
                case 3:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    i = 3;
                    break;
                case 4:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
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
        C0140zzb zzb = new C0140zzb(hashSet, i4, i2, str, i3);
        return zzb;
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new C0140zzb[i];
    }
}
