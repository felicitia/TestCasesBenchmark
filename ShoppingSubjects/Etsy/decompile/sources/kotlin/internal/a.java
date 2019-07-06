package kotlin.internal;

/* compiled from: progressionUtil.kt */
public final class a {
    private static final int a(int i, int i2) {
        int i3 = i % i2;
        return i3 >= 0 ? i3 : i3 + i2;
    }

    private static final int b(int i, int i2, int i3) {
        return a(a(i, i3) - a(i2, i3), i3);
    }

    public static final int a(int i, int i2, int i3) {
        if (i3 > 0) {
            return i2 - b(i2, i, i3);
        }
        if (i3 < 0) {
            return i2 + b(i, i2, -i3);
        }
        throw new IllegalArgumentException("Step is zero.");
    }
}
