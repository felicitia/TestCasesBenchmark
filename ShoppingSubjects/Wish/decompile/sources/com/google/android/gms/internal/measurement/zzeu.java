package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Iterator;

public final class zzeu extends AbstractSafeParcelable implements Iterable<String> {
    public static final Creator<zzeu> CREATOR = new zzew();
    /* access modifiers changed from: private */
    public final Bundle zzaho;

    zzeu(Bundle bundle) {
        this.zzaho = bundle;
    }

    /* access modifiers changed from: 0000 */
    public final Object get(String str) {
        return this.zzaho.get(str);
    }

    /* access modifiers changed from: 0000 */
    public final Long getLong(String str) {
        return Long.valueOf(this.zzaho.getLong(str));
    }

    /* access modifiers changed from: 0000 */
    public final String getString(String str) {
        return this.zzaho.getString(str);
    }

    public final Iterator<String> iterator() {
        return new zzev(this);
    }

    public final int size() {
        return this.zzaho.size();
    }

    public final String toString() {
        return this.zzaho.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, zzin(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* access modifiers changed from: 0000 */
    public final Double zzbk(String str) {
        return Double.valueOf(this.zzaho.getDouble(str));
    }

    public final Bundle zzin() {
        return new Bundle(this.zzaho);
    }
}
