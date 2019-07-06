package org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EnumUtils {
    public static <E extends Enum<E>> Map<String, E> getEnumMap(Class<E> cls) {
        Enum[] enumArr;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
            linkedHashMap.put(enumR.name(), enumR);
        }
        return linkedHashMap;
    }

    public static <E extends Enum<E>> List<E> getEnumList(Class<E> cls) {
        return new ArrayList(Arrays.asList(cls.getEnumConstants()));
    }

    public static <E extends Enum<E>> boolean isValidEnum(Class<E> cls, String str) {
        if (str == null) {
            return false;
        }
        try {
            Enum.valueOf(cls, str);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static <E extends Enum<E>> E getEnum(Class<E> cls, String str) {
        if (str == null) {
            return null;
        }
        try {
            return Enum.valueOf(cls, str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static <E extends Enum<E>> long generateBitVector(Class<E> cls, Iterable<E> iterable) {
        checkBitVectorable(cls);
        Validate.notNull(iterable);
        long j = 0;
        for (E ordinal : iterable) {
            j |= (long) (1 << ordinal.ordinal());
        }
        return j;
    }

    public static <E extends Enum<E>> long generateBitVector(Class<E> cls, E... eArr) {
        Validate.noNullElements((T[]) eArr);
        return generateBitVector(cls, (Iterable<E>) Arrays.asList(eArr));
    }

    public static <E extends Enum<E>> EnumSet<E> processBitVector(Class<E> cls, long j) {
        Enum[] enumArr = (Enum[]) checkBitVectorable(cls).getEnumConstants();
        EnumSet<E> noneOf = EnumSet.noneOf(cls);
        for (Enum enumR : enumArr) {
            if ((j & ((long) (1 << enumR.ordinal()))) != 0) {
                noneOf.add(enumR);
            }
        }
        return noneOf;
    }

    private static <E extends Enum<E>> Class<E> checkBitVectorable(Class<E> cls) {
        Validate.notNull(cls, "EnumClass must be defined.", new Object[0]);
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        Validate.isTrue(enumArr != null, "%s does not seem to be an Enum type", cls);
        Validate.isTrue(enumArr.length <= 64, "Cannot store %s %s values in %s bits", Integer.valueOf(enumArr.length), cls.getSimpleName(), Integer.valueOf(64));
        return cls;
    }
}
