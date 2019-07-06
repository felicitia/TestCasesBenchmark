package com.onfido.d.b.a;

import com.onfido.d.b.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

final class b {
    private final Map<Integer, Integer> a = new HashMap();

    b() {
    }

    /* access modifiers changed from: 0000 */
    public void a(int i) {
        Integer num = (Integer) this.a.get(Integer.valueOf(i));
        if (num == null) {
            num = Integer.valueOf(0);
        }
        this.a.put(Integer.valueOf(i), Integer.valueOf(num.intValue() + 1));
    }

    /* access modifiers changed from: 0000 */
    public int[] a() {
        ArrayList arrayList = new ArrayList();
        int i = -1;
        for (Entry entry : this.a.entrySet()) {
            if (((Integer) entry.getValue()).intValue() > i) {
                i = ((Integer) entry.getValue()).intValue();
                arrayList.clear();
            } else if (((Integer) entry.getValue()).intValue() != i) {
            }
            arrayList.add(entry.getKey());
        }
        return a.a((Collection<Integer>) arrayList);
    }
}
