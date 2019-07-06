package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.app.NotificationCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Indicator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.internal.auth.zzbs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Class(creator = "AccountTransferMsgCreator")
public final class zzo extends zzbs {
    public static final Creator<zzo> CREATOR = new zzp();
    private static final HashMap<String, Field<?, ?>> zzbk;
    @Indicator
    private final Set<Integer> zzbl;
    @SafeParcelable.Field(getter = "getAuthenticatorDatas", id = 2)
    private ArrayList<zzu> zzbm;
    @SafeParcelable.Field(getter = "getRequestType", id = 3)
    private int zzbn;
    @SafeParcelable.Field(getter = "getProgress", id = 4)
    private zzr zzbo;
    @VersionField(id = 1)
    private final int zzy;

    static {
        HashMap<String, Field<?, ?>> hashMap = new HashMap<>();
        zzbk = hashMap;
        hashMap.put("authenticatorData", Field.forConcreteTypeArray("authenticatorData", 2, zzu.class));
        zzbk.put(NotificationCompat.CATEGORY_PROGRESS, Field.forConcreteType(NotificationCompat.CATEGORY_PROGRESS, 4, zzr.class));
    }

    public zzo() {
        this.zzbl = new HashSet(1);
        this.zzy = 1;
    }

    @Constructor
    zzo(@Indicator Set<Integer> set, @Param(id = 1) int i, @Param(id = 2) ArrayList<zzu> arrayList, @Param(id = 3) int i2, @Param(id = 4) zzr zzr) {
        this.zzbl = set;
        this.zzy = i;
        this.zzbm = arrayList;
        this.zzbn = i2;
        this.zzbo = zzr;
    }

    public final <T extends FastJsonResponse> void addConcreteTypeArrayInternal(Field<?, ?> field, String str, ArrayList<T> arrayList) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId != 2) {
            throw new IllegalArgumentException(String.format("Field with id=%d is not a known ConcreteTypeArray type. Found %s", new Object[]{Integer.valueOf(safeParcelableFieldId), arrayList.getClass().getCanonicalName()}));
        }
        this.zzbm = arrayList;
        this.zzbl.add(Integer.valueOf(safeParcelableFieldId));
    }

    public final <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String str, T t) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId != 4) {
            throw new IllegalArgumentException(String.format("Field with id=%d is not a known custom type. Found %s", new Object[]{Integer.valueOf(safeParcelableFieldId), t.getClass().getCanonicalName()}));
        }
        this.zzbo = (zzr) t;
        this.zzbl.add(Integer.valueOf(safeParcelableFieldId));
    }

    public final /* synthetic */ Map getFieldMappings() {
        return zzbk;
    }

    /* access modifiers changed from: protected */
    public final Object getFieldValue(Field field) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 4) {
            return this.zzbo;
        }
        switch (safeParcelableFieldId) {
            case 1:
                return Integer.valueOf(this.zzy);
            case 2:
                return this.zzbm;
            default:
                int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
                StringBuilder sb = new StringBuilder(37);
                sb.append("Unknown SafeParcelable id=");
                sb.append(safeParcelableFieldId2);
                throw new IllegalStateException(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public final boolean isFieldSet(Field field) {
        return this.zzbl.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> set = this.zzbl;
        if (set.contains(Integer.valueOf(1))) {
            SafeParcelWriter.writeInt(parcel, 1, this.zzy);
        }
        if (set.contains(Integer.valueOf(2))) {
            SafeParcelWriter.writeTypedList(parcel, 2, this.zzbm, true);
        }
        if (set.contains(Integer.valueOf(3))) {
            SafeParcelWriter.writeInt(parcel, 3, this.zzbn);
        }
        if (set.contains(Integer.valueOf(4))) {
            SafeParcelWriter.writeParcelable(parcel, 4, this.zzbo, i, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
