package com.google.android.gms.internal.firebase-perf;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

final class zzen extends zzem<FieldDescriptorType, Object> {
    zzen(int i) {
        super(i, null);
    }

    public final void zzbi() {
        if (!isImmutable()) {
            for (int i = 0; i < zzfj(); i++) {
                Entry zzat = zzat(i);
                if (((zzcf) zzat.getKey()).zzdl()) {
                    zzat.setValue(Collections.unmodifiableList((List) zzat.getValue()));
                }
            }
            for (Entry entry : zzfk()) {
                if (((zzcf) entry.getKey()).zzdl()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzbi();
    }
}
