package com.google.android.gms.auth.api.accounttransfer;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Indicator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.internal.auth.zzbs;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Class(creator = "AuthenticatorTransferInfoCreator")
public class zzw extends zzbs {
    public static final Creator<zzw> CREATOR = new zzx();
    private static final HashMap<String, Field<?, ?>> zzbk;
    @Indicator
    private final Set<Integer> zzbl;
    @SafeParcelable.Field(getter = "getAccountType", id = 2)
    private String zzbx;
    @SafeParcelable.Field(getter = "getStatus", id = 3)
    private int zzby;
    @SafeParcelable.Field(getter = "getTransferBytes", id = 4)
    private byte[] zzbz;
    @SafeParcelable.Field(getter = "getPendingIntent", id = 5)
    private PendingIntent zzca;
    @SafeParcelable.Field(getter = "getDeviceMetaData", id = 6)
    private DeviceMetaData zzcb;
    @VersionField(id = 1)
    private final int zzy;

    static {
        HashMap<String, Field<?, ?>> hashMap = new HashMap<>();
        zzbk = hashMap;
        hashMap.put("accountType", Field.forString("accountType", 2));
        zzbk.put("status", Field.forInteger("status", 3));
        zzbk.put("transferBytes", Field.forBase64("transferBytes", 4));
    }

    public zzw() {
        this.zzbl = new ArraySet(3);
        this.zzy = 1;
    }

    @Constructor
    zzw(@Indicator Set<Integer> set, @Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) int i2, @Param(id = 4) byte[] bArr, @Param(id = 5) PendingIntent pendingIntent, @Param(id = 6) DeviceMetaData deviceMetaData) {
        this.zzbl = set;
        this.zzy = i;
        this.zzbx = str;
        this.zzby = i2;
        this.zzbz = bArr;
        this.zzca = pendingIntent;
        this.zzcb = deviceMetaData;
    }

    public /* synthetic */ Map getFieldMappings() {
        return zzbk;
    }

    /* access modifiers changed from: protected */
    public Object getFieldValue(Field field) {
        int i;
        switch (field.getSafeParcelableFieldId()) {
            case 1:
                i = this.zzy;
                break;
            case 2:
                return this.zzbx;
            case 3:
                i = this.zzby;
                break;
            case 4:
                return this.zzbz;
            default:
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                StringBuilder sb = new StringBuilder(37);
                sb.append("Unknown SafeParcelable id=");
                sb.append(safeParcelableFieldId);
                throw new IllegalStateException(sb.toString());
        }
        return Integer.valueOf(i);
    }

    /* access modifiers changed from: protected */
    public boolean isFieldSet(Field field) {
        return this.zzbl.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    /* access modifiers changed from: protected */
    public void setDecodedBytesInternal(Field<?, ?> field, String str, byte[] bArr) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId != 4) {
            StringBuilder sb = new StringBuilder(59);
            sb.append("Field with id=");
            sb.append(safeParcelableFieldId);
            sb.append(" is not known to be an byte array.");
            throw new IllegalArgumentException(sb.toString());
        }
        this.zzbz = bArr;
        this.zzbl.add(Integer.valueOf(safeParcelableFieldId));
    }

    /* access modifiers changed from: protected */
    public void setIntegerInternal(Field<?, ?> field, String str, int i) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId != 3) {
            StringBuilder sb = new StringBuilder(52);
            sb.append("Field with id=");
            sb.append(safeParcelableFieldId);
            sb.append(" is not known to be an int.");
            throw new IllegalArgumentException(sb.toString());
        }
        this.zzby = i;
        this.zzbl.add(Integer.valueOf(safeParcelableFieldId));
    }

    /* access modifiers changed from: protected */
    public void setStringInternal(Field<?, ?> field, String str, String str2) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId != 2) {
            throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string.", new Object[]{Integer.valueOf(safeParcelableFieldId)}));
        }
        this.zzbx = str2;
        this.zzbl.add(Integer.valueOf(safeParcelableFieldId));
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> set = this.zzbl;
        if (set.contains(Integer.valueOf(1))) {
            SafeParcelWriter.writeInt(parcel, 1, this.zzy);
        }
        if (set.contains(Integer.valueOf(2))) {
            SafeParcelWriter.writeString(parcel, 2, this.zzbx, true);
        }
        if (set.contains(Integer.valueOf(3))) {
            SafeParcelWriter.writeInt(parcel, 3, this.zzby);
        }
        if (set.contains(Integer.valueOf(4))) {
            SafeParcelWriter.writeByteArray(parcel, 4, this.zzbz, true);
        }
        if (set.contains(Integer.valueOf(5))) {
            SafeParcelWriter.writeParcelable(parcel, 5, this.zzca, i, true);
        }
        if (set.contains(Integer.valueOf(6))) {
            SafeParcelWriter.writeParcelable(parcel, 6, this.zzcb, i, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
