package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.etsy.android.lib.models.ResponseConstants;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Class(creator = "AuthenticatorAnnotatedDataCreator")
public class zzu extends zzbs {
    public static final Creator<zzu> CREATOR = new zzv();
    private static final HashMap<String, Field<?, ?>> zzbk;
    @SafeParcelable.Field(getter = "getPackageName", id = 4)
    private String mPackageName;
    @Indicator
    private final Set<Integer> zzbl;
    @SafeParcelable.Field(getter = "getInfo", id = 2)
    private zzw zzbv;
    @SafeParcelable.Field(getter = "getSignature", id = 3)
    private String zzbw;
    @VersionField(id = 1)
    private final int zzy;

    static {
        HashMap<String, Field<?, ?>> hashMap = new HashMap<>();
        zzbk = hashMap;
        hashMap.put("authenticatorInfo", Field.forConcreteType("authenticatorInfo", 2, zzw.class));
        zzbk.put(ResponseConstants.SIGNATURE, Field.forString(ResponseConstants.SIGNATURE, 3));
        zzbk.put("package", Field.forString("package", 4));
    }

    public zzu() {
        this.zzbl = new HashSet(3);
        this.zzy = 1;
    }

    @Constructor
    zzu(@Indicator Set<Integer> set, @Param(id = 1) int i, @Param(id = 2) zzw zzw, @Param(id = 3) String str, @Param(id = 4) String str2) {
        this.zzbl = set;
        this.zzy = i;
        this.zzbv = zzw;
        this.zzbw = str;
        this.mPackageName = str2;
    }

    public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String str, T t) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId != 2) {
            throw new IllegalArgumentException(String.format("Field with id=%d is not a known custom type. Found %s", new Object[]{Integer.valueOf(safeParcelableFieldId), t.getClass().getCanonicalName()}));
        }
        this.zzbv = (zzw) t;
        this.zzbl.add(Integer.valueOf(safeParcelableFieldId));
    }

    public /* synthetic */ Map getFieldMappings() {
        return zzbk;
    }

    /* access modifiers changed from: protected */
    public Object getFieldValue(Field field) {
        switch (field.getSafeParcelableFieldId()) {
            case 1:
                return Integer.valueOf(this.zzy);
            case 2:
                return this.zzbv;
            case 3:
                return this.zzbw;
            case 4:
                return this.mPackageName;
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
        return this.zzbl.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    /* access modifiers changed from: protected */
    public void setStringInternal(Field<?, ?> field, String str, String str2) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case 3:
                this.zzbw = str2;
                break;
            case 4:
                this.mPackageName = str2;
                break;
            default:
                throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string.", new Object[]{Integer.valueOf(safeParcelableFieldId)}));
        }
        this.zzbl.add(Integer.valueOf(safeParcelableFieldId));
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> set = this.zzbl;
        if (set.contains(Integer.valueOf(1))) {
            SafeParcelWriter.writeInt(parcel, 1, this.zzy);
        }
        if (set.contains(Integer.valueOf(2))) {
            SafeParcelWriter.writeParcelable(parcel, 2, this.zzbv, i, true);
        }
        if (set.contains(Integer.valueOf(3))) {
            SafeParcelWriter.writeString(parcel, 3, this.zzbw, true);
        }
        if (set.contains(Integer.valueOf(4))) {
            SafeParcelWriter.writeString(parcel, 4, this.mPackageName, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
