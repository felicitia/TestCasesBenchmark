package com.google.android.gms.internal.icing;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

final class ce extends cd<FieldDescriptorType, Object> {
    ce(int i) {
        super(i, null);
    }

    public final void a() {
        if (!b()) {
            for (int i = 0; i < c(); i++) {
                Entry b = b(i);
                if (((ae) b.getKey()).d()) {
                    b.setValue(Collections.unmodifiableList((List) b.getValue()));
                }
            }
            for (Entry entry : d()) {
                if (((ae) entry.getKey()).d()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.a();
    }
}
