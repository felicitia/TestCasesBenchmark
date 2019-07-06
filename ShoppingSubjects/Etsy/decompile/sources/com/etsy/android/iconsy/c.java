package com.etsy.android.iconsy;

import android.util.Pair;
import android.util.SparseArray;
import com.etsy.android.iconsy.e.a;
import com.etsy.android.iconsy.fonts.DemoFontIcon;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: FontSets */
public final class c {
    private static Map<Class<? extends a>, String> a = new HashMap();
    private static SparseArray<a> b = new SparseArray<>();

    static {
        b.put(a.ic_demo_example, DemoFontIcon.EXAMPLE);
    }

    public static Map<Class<? extends a>, String> a() {
        return a;
    }

    public static a a(int i) {
        return (a) b.get(i);
    }

    public static void a(Map<Class<? extends a>, String> map) {
        a.putAll(map);
    }

    public static void a(List<Pair<Integer, a>> list) {
        if (list != null) {
            for (Pair pair : list) {
                b.put(((Integer) pair.first).intValue(), pair.second);
            }
        }
    }
}
