package kotlin.b;

import org.apache.commons.lang3.ClassUtils;

/* compiled from: _Ranges.kt */
class g extends f {
    public static final int c(int i, int i2) {
        return i < i2 ? i2 : i;
    }

    public static final int d(int i, int i2) {
        return i > i2 ? i2 : i;
    }

    public static final a a(int i, int i2) {
        return a.a.a(i, i2, -1);
    }

    public static final c b(int i, int i2) {
        if (i2 <= Integer.MIN_VALUE) {
            return c.b.a();
        }
        return new c(i, i2 - 1);
    }

    public static final int a(int i, int i2, int i3) {
        if (i2 > i3) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot coerce value to an empty range: maximum ");
            sb.append(i3);
            sb.append(" is less than minimum ");
            sb.append(i2);
            sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
            throw new IllegalArgumentException(sb.toString());
        } else if (i < i2) {
            return i2;
        } else {
            return i > i3 ? i3 : i;
        }
    }
}
