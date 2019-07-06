package kotlin.text;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.b.d;
import kotlin.collections.af;
import kotlin.jvm.a.a;
import kotlin.jvm.internal.Lambda;

/* compiled from: CharCategory.kt */
final class CharCategory$Companion$categoryMap$2 extends Lambda implements a<Map<Integer, ? extends CharCategory>> {
    public static final CharCategory$Companion$categoryMap$2 INSTANCE = new CharCategory$Companion$categoryMap$2();

    CharCategory$Companion$categoryMap$2() {
        super(0);
    }

    public final Map<Integer, CharCategory> invoke() {
        CharCategory[] values = CharCategory.values();
        Map<Integer, CharCategory> linkedHashMap = new LinkedHashMap<>(d.c(af.a(values.length), 16));
        for (CharCategory charCategory : values) {
            linkedHashMap.put(Integer.valueOf(charCategory.getValue()), charCategory);
        }
        return linkedHashMap;
    }
}
