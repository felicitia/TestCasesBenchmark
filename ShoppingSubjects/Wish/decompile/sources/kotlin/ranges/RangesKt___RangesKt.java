package kotlin.ranges;

/* compiled from: _Ranges.kt */
class RangesKt___RangesKt extends RangesKt__RangesKt {
    public static final int coerceAtLeast(int i, int i2) {
        return i < i2 ? i2 : i;
    }

    public static final int coerceAtMost(int i, int i2) {
        return i > i2 ? i2 : i;
    }

    public static final IntProgression downTo(int i, int i2) {
        return IntProgression.Companion.fromClosedRange(i, i2, -1);
    }
}
