package com.google.android.gms.internal.icing;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;

public final class zzn extends AbstractSafeParcelable {
    public static final Creator<zzn> CREATOR = new zzo();
    private final int id;
    private final Bundle zzu;

    zzn(int i, Bundle bundle) {
        this.id = i;
        this.zzu = bundle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzn)) {
            return false;
        }
        zzn zzn = (zzn) obj;
        if (this.id != zzn.id) {
            return false;
        }
        if (this.zzu == null) {
            return zzn.zzu == null;
        }
        if (zzn.zzu == null || this.zzu.size() != zzn.zzu.size()) {
            return false;
        }
        for (String str : this.zzu.keySet()) {
            if (zzn.zzu.containsKey(str)) {
                if (!Objects.equal(this.zzu.getString(str), zzn.zzu.getString(str))) {
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(this.id));
        if (this.zzu != null) {
            for (String str : this.zzu.keySet()) {
                arrayList.add(str);
                arrayList.add(this.zzu.getString(str));
            }
        }
        return Objects.hashCode(arrayList.toArray(new Object[0]));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.id);
        SafeParcelWriter.writeBundle(parcel, 2, this.zzu, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
