package dagger.internal;

import java.util.LinkedHashMap;

/* compiled from: DaggerCollections */
public final class a {
    private static int b(int i) {
        if (i < 3) {
            return i + 1;
        }
        if (i < 1073741824) {
            return (int) ((((float) i) / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    static <K, V> LinkedHashMap<K, V> a(int i) {
        return new LinkedHashMap<>(b(i));
    }
}
