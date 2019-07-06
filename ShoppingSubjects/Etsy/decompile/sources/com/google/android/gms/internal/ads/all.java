package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public final class all {
    public final int a;
    public final byte[] b;
    public final Map<String, String> c;
    public final List<ajj> d;
    public final boolean e;
    private final long f;

    private all(int i, byte[] bArr, Map<String, String> map, List<ajj> list, boolean z, long j) {
        this.a = i;
        this.b = bArr;
        this.c = map;
        this.d = list == null ? null : Collections.unmodifiableList(list);
        this.e = z;
        this.f = j;
    }

    @Deprecated
    public all(int i, byte[] bArr, Map<String, String> map, boolean z, long j) {
        List arrayList;
        if (map == null) {
            arrayList = null;
        } else if (map.isEmpty()) {
            arrayList = Collections.emptyList();
        } else {
            arrayList = new ArrayList(map.size());
            for (Entry entry : map.entrySet()) {
                arrayList.add(new ajj((String) entry.getKey(), (String) entry.getValue()));
            }
        }
        List list = arrayList;
        this(i, bArr, map, list, z, j);
    }

    public all(int i, byte[] bArr, boolean z, long j, List<ajj> list) {
        Map treeMap;
        if (list == null) {
            treeMap = null;
        } else if (list.isEmpty()) {
            treeMap = Collections.emptyMap();
        } else {
            treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            for (ajj ajj : list) {
                treeMap.put(ajj.a(), ajj.b());
            }
        }
        Map map = treeMap;
        this(i, bArr, map, list, z, j);
    }

    @Deprecated
    public all(byte[] bArr, Map<String, String> map) {
        this(200, bArr, map, false, 0);
    }
}
