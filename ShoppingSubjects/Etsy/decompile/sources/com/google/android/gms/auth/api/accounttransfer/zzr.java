package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.internal.auth.zzbs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Class(creator = "AccountTransferProgressCreator")
public class zzr extends zzbs {
    public static final Creator<zzr> CREATOR = new zzs();
    private static final ArrayMap<String, Field<?, ?>> zzbp;
    @SafeParcelable.Field(getter = "getRegisteredAccountTypes", id = 2)
    private List<String> zzbq;
    @SafeParcelable.Field(getter = "getInProgressAccountTypes", id = 3)
    private List<String> zzbr;
    @SafeParcelable.Field(getter = "getSuccessAccountTypes", id = 4)
    private List<String> zzbs;
    @SafeParcelable.Field(getter = "getFailedAccountTypes", id = 5)
    private List<String> zzbt;
    @SafeParcelable.Field(getter = "getEscrowedAccountTypes", id = 6)
    private List<String> zzbu;
    @VersionField(id = 1)
    private final int zzy;

    static {
        ArrayMap<String, Field<?, ?>> arrayMap = new ArrayMap<>();
        zzbp = arrayMap;
        arrayMap.put("registered", Field.forStrings("registered", 2));
        zzbp.put("in_progress", Field.forStrings("in_progress", 3));
        zzbp.put("success", Field.forStrings("success", 4));
        zzbp.put("failed", Field.forStrings("failed", 5));
        zzbp.put("escrowed", Field.forStrings("escrowed", 6));
    }

    public zzr() {
        this.zzy = 1;
    }

    @Constructor
    zzr(@Param(id = 1) int i, @Nullable @Param(id = 2) List<String> list, @Nullable @Param(id = 3) List<String> list2, @Nullable @Param(id = 4) List<String> list3, @Nullable @Param(id = 5) List<String> list4, @Nullable @Param(id = 6) List<String> list5) {
        this.zzy = i;
        this.zzbq = list;
        this.zzbr = list2;
        this.zzbs = list3;
        this.zzbt = list4;
        this.zzbu = list5;
    }

    public Map<String, Field<?, ?>> getFieldMappings() {
        return zzbp;
    }

    /* access modifiers changed from: protected */
    public Object getFieldValue(Field field) {
        switch (field.getSafeParcelableFieldId()) {
            case 1:
                return Integer.valueOf(this.zzy);
            case 2:
                return this.zzbq;
            case 3:
                return this.zzbr;
            case 4:
                return this.zzbs;
            case 5:
                return this.zzbt;
            case 6:
                return this.zzbu;
            default:
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                StringBuilder sb = new StringBuilder(37);
                sb.append("Unknown SafeParcelable id=");
                sb.append(safeParcelableFieldId);
                throw new IllegalStateException(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public boolean isFieldSet(Field field) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void setStringsInternal(Field<?, ?> field, String str, ArrayList<String> arrayList) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case 2:
                this.zzbq = arrayList;
                return;
            case 3:
                this.zzbr = arrayList;
                return;
            case 4:
                this.zzbs = arrayList;
                return;
            case 5:
                this.zzbt = arrayList;
                return;
            case 6:
                this.zzbu = arrayList;
                return;
            default:
                throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string list.", new Object[]{Integer.valueOf(safeParcelableFieldId)}));
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzy);
        SafeParcelWriter.writeStringList(parcel, 2, this.zzbq, false);
        SafeParcelWriter.writeStringList(parcel, 3, this.zzbr, false);
        SafeParcelWriter.writeStringList(parcel, 4, this.zzbs, false);
        SafeParcelWriter.writeStringList(parcel, 5, this.zzbt, false);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzbu, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
