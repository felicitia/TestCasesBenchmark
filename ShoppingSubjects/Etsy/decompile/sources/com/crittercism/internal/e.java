package com.crittercism.internal;

import java.lang.reflect.Field;

public final class e {
    public static <C, F> F a(Field field, C c) {
        F f = null;
        if (field == null) {
            return null;
        }
        if (field != null) {
            field.setAccessible(true);
            try {
                f = field.get(c);
            } catch (ThreadDeath e) {
                throw e;
            } catch (Throwable th) {
                throw new bk("Unable to get value of field", th);
            }
        }
        return f;
    }

    public static Field a(Class<?> cls, Class<?> cls2, boolean z) {
        Field[] declaredFields = cls.getDeclaredFields();
        Field field = null;
        for (int i = 0; i < declaredFields.length; i++) {
            if (cls2.isAssignableFrom(declaredFields[i].getType())) {
                if (field != null) {
                    StringBuilder sb = new StringBuilder("Field is ambiguous: ");
                    sb.append(field.getName());
                    sb.append(", ");
                    sb.append(declaredFields[i].getName());
                    throw new bk(sb.toString());
                }
                field = declaredFields[i];
            }
        }
        if (field != null) {
            field.setAccessible(true);
        } else if (z) {
            StringBuilder sb2 = new StringBuilder("Could not find field matching type: ");
            sb2.append(cls2.getName());
            throw new bk(sb2.toString());
        }
        return field;
    }
}
