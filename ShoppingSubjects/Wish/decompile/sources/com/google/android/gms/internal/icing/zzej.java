package com.google.android.gms.internal.icing;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

final class zzej extends zzei<FieldDescriptorType, Object> {
    zzej(int i) {
        super(i, null);
    }

    public final void zzp() {
        if (!isImmutable()) {
            for (int i = 0; i < zzci(); i++) {
                Entry zzaf = zzaf(i);
                if (((zzcf) zzaf.getKey()).zzas()) {
                    zzaf.setValue(Collections.unmodifiableList((List) zzaf.getValue()));
                }
            }
            for (Entry entry : zzcj()) {
                if (((zzcf) entry.getKey()).zzas()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzp();
    }
}
