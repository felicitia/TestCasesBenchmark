package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.etsy.android.lib.models.editable.EditableListing;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.VisibleForTesting;

@Class(creator = "DocumentSectionCreator")
@Reserved({1000})
public final class zzl extends AbstractSafeParcelable {
    public static final Creator<zzl> CREATOR = new zzm();
    private static final int zzo = Integer.parseInt(EditableListing.LISTING_ID_DEVICE_DRAFT);
    private static final zzs zzp = new ds("SsbContext").a(true).a("blob").a();
    @Field(id = 1)
    private final String zzq;
    @Field(id = 3)
    private final zzs zzr;
    @Field(defaultValue = "-1", id = 4)
    public final int zzs;
    @Field(id = 5)
    private final byte[] zzt;

    public zzl(String str, zzs zzs2) {
        this(str, zzs2, zzo, null);
    }

    @Constructor
    zzl(@Param(id = 1) String str, @Param(id = 3) zzs zzs2, @Param(id = 4) int i, @Param(id = 5) byte[] bArr) {
        String str2;
        boolean z = i == zzo || dr.a(i) != null;
        StringBuilder sb = new StringBuilder(32);
        sb.append("Invalid section type ");
        sb.append(i);
        Preconditions.checkArgument(z, sb.toString());
        this.zzq = str;
        this.zzr = zzs2;
        this.zzs = i;
        this.zzt = bArr;
        if (this.zzs == zzo || dr.a(this.zzs) != null) {
            str2 = (this.zzq == null || this.zzt == null) ? null : "Both content and blobContent set";
        } else {
            int i2 = this.zzs;
            StringBuilder sb2 = new StringBuilder(32);
            sb2.append("Invalid section type ");
            sb2.append(i2);
            str2 = sb2.toString();
        }
        if (str2 != null) {
            throw new IllegalArgumentException(str2);
        }
    }

    @VisibleForTesting
    public zzl(String str, zzs zzs2, String str2) {
        this(str, zzs2, dr.a(str2), null);
    }

    public zzl(byte[] bArr, zzs zzs2) {
        this(null, zzs2, zzo, bArr);
    }

    public static zzl zza(byte[] bArr) {
        return new zzl(bArr, zzp);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzq, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzr, i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzs);
        SafeParcelWriter.writeByteArray(parcel, 5, this.zzt, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
