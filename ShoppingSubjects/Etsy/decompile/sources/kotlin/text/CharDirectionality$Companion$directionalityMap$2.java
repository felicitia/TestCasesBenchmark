package kotlin.text;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.b.d;
import kotlin.collections.af;
import kotlin.jvm.a.a;
import kotlin.jvm.internal.Lambda;

/* compiled from: CharDirectionality.kt */
final class CharDirectionality$Companion$directionalityMap$2 extends Lambda implements a<Map<Integer, ? extends CharDirectionality>> {
    public static final CharDirectionality$Companion$directionalityMap$2 INSTANCE = new CharDirectionality$Companion$directionalityMap$2();

    CharDirectionality$Companion$directionalityMap$2() {
        super(0);
    }

    public final Map<Integer, CharDirectionality> invoke() {
        CharDirectionality[] values = CharDirectionality.values();
        Map<Integer, CharDirectionality> linkedHashMap = new LinkedHashMap<>(d.c(af.a(values.length), 16));
        for (CharDirectionality charDirectionality : values) {
            linkedHashMap.put(Integer.valueOf(charDirectionality.getValue()), charDirectionality);
        }
        return linkedHashMap;
    }
}
