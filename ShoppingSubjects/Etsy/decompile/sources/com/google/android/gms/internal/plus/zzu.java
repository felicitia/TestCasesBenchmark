package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.internal.plus.zzr.zzb;
import com.google.android.gms.internal.plus.zzr.zzb.C0140zzb;
import com.google.android.gms.internal.plus.zzr.zzb.zza;
import java.util.HashSet;

public final class zzu implements Creator<zzb> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i2 = 0;
        zza zza = null;
        C0140zzb zzb = null;
        int i3 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    i = 1;
                    break;
                case 2:
                    zza = (zza) SafeParcelReader.createParcelable(parcel, readHeader, zza.CREATOR);
                    i = 2;
                    break;
                case 3:
                    zzb = (C0140zzb) SafeParcelReader.createParcelable(parcel, readHeader, C0140zzb.CREATOR);
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
        zzb zzb2 = new zzb(hashSet, i2, zza, zzb, i3);
        return zzb2;
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzb[i];
    }
}
