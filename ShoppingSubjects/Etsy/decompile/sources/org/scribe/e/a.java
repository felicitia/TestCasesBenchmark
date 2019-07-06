package org.scribe.e;

import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: MapUtils */
public class a {
    public static <K, V> String a(Map<K, V> map) {
        if (map == null) {
            return "";
        }
        if (map.isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            sb.append(String.format(", %s -> %s ", new Object[]{entry.getKey().toString(), entry.getValue().toString()}));
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(VectorFormat.DEFAULT_PREFIX);
        sb2.append(sb.substring(1));
        sb2.append(VectorFormat.DEFAULT_SUFFIX);
        return sb2.toString();
    }
}
