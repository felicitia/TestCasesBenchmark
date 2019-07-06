package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

final class zh extends zg<FieldDescriptorType, Object> {
    zh(int i) {
        super(i, null);
    }

    public final void a() {
        if (!b()) {
            for (int i = 0; i < c(); i++) {
                Entry b = b(i);
                if (((xd) b.getKey()).d()) {
                    b.setValue(Collections.unmodifiableList((List) b.getValue()));
                }
            }
            for (Entry entry : d()) {
                if (((xd) entry.getKey()).d()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.a();
    }
}
