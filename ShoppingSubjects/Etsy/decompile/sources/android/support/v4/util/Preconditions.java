package android.support.v4.util;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import java.util.Collection;
import java.util.Locale;

@RestrictTo({Scope.LIBRARY_GROUP})
public class Preconditions {
    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    @NonNull
    public static <T extends CharSequence> T checkStringNotEmpty(T t) {
        if (!TextUtils.isEmpty(t)) {
            return t;
        }
        throw new IllegalArgumentException();
    }

    @NonNull
    public static <T extends CharSequence> T checkStringNotEmpty(T t, Object obj) {
        if (!TextUtils.isEmpty(t)) {
            return t;
        }
        throw new IllegalArgumentException(String.valueOf(obj));
    }

    @NonNull
    public static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    @NonNull
    public static <T> T checkNotNull(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static void checkState(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }

    public static void checkState(boolean z) {
        checkState(z, null);
    }

    public static int checkFlagsArgument(int i, int i2) {
        if ((i & i2) == i) {
            return i;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Requested flags 0x");
        sb.append(Integer.toHexString(i));
        sb.append(", but only 0x");
        sb.append(Integer.toHexString(i2));
        sb.append(" are allowed");
        throw new IllegalArgumentException(sb.toString());
    }

    @IntRange(from = 0)
    public static int checkArgumentNonnegative(int i, String str) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException(str);
    }

    @IntRange(from = 0)
    public static int checkArgumentNonnegative(int i) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException();
    }

    public static long checkArgumentNonnegative(long j) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException();
    }

    public static long checkArgumentNonnegative(long j, String str) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException(str);
    }

    public static int checkArgumentPositive(int i, String str) {
        if (i > 0) {
            return i;
        }
        throw new IllegalArgumentException(str);
    }

    public static float checkArgumentFinite(float f, String str) {
        if (Float.isNaN(f)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" must not be NaN");
            throw new IllegalArgumentException(sb.toString());
        } else if (!Float.isInfinite(f)) {
            return f;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(" must not be infinite");
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public static float checkArgumentInRange(float f, float f2, float f3, String str) {
        if (Float.isNaN(f)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" must not be NaN");
            throw new IllegalArgumentException(sb.toString());
        } else if (f < f2) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too low)", new Object[]{str, Float.valueOf(f2), Float.valueOf(f3)}));
        } else if (f <= f3) {
            return f;
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too high)", new Object[]{str, Float.valueOf(f2), Float.valueOf(f3)}));
        }
    }

    public static int checkArgumentInRange(int i, int i2, int i3, String str) {
        if (i < i2) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i3)}));
        } else if (i <= i3) {
            return i;
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i3)}));
        }
    }

    public static long checkArgumentInRange(long j, long j2, long j3, String str) {
        if (j < j2) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", new Object[]{str, Long.valueOf(j2), Long.valueOf(j3)}));
        } else if (j <= j3) {
            return j;
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", new Object[]{str, Long.valueOf(j2), Long.valueOf(j3)}));
        }
    }

    public static <T> T[] checkArrayElementsNotNull(T[] tArr, String str) {
        if (tArr == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" must not be null");
            throw new NullPointerException(sb.toString());
        }
        for (int i = 0; i < tArr.length; i++) {
            if (tArr[i] == null) {
                throw new NullPointerException(String.format(Locale.US, "%s[%d] must not be null", new Object[]{str, Integer.valueOf(i)}));
            }
        }
        return tArr;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=C, code=C<java.lang.Object>, for r7v0, types: [C, C<java.lang.Object>, java.util.Collection] */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <C extends java.util.Collection<T>, T> C checkCollectionElementsNotNull(C<java.lang.Object> r7, java.lang.String r8) {
        /*
            if (r7 != 0) goto L_0x0019
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r8)
            java.lang.String r8 = " must not be null"
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r7.<init>(r8)
            throw r7
        L_0x0019:
            r0 = 0
            java.util.Iterator r2 = r7.iterator()
        L_0x001f:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x004c
            java.lang.Object r3 = r2.next()
            if (r3 != 0) goto L_0x0046
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            java.util.Locale r2 = java.util.Locale.US
            java.lang.String r3 = "%s[%d] must not be null"
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r5 = 0
            r4[r5] = r8
            r8 = 1
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r4[r8] = r0
            java.lang.String r8 = java.lang.String.format(r2, r3, r4)
            r7.<init>(r8)
            throw r7
        L_0x0046:
            r3 = 1
            long r5 = r0 + r3
            r0 = r5
            goto L_0x001f
        L_0x004c:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.Preconditions.checkCollectionElementsNotNull(java.util.Collection, java.lang.String):java.util.Collection");
    }

    public static <T> Collection<T> checkCollectionNotEmpty(Collection<T> collection, String str) {
        if (collection == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" must not be null");
            throw new NullPointerException(sb.toString());
        } else if (!collection.isEmpty()) {
            return collection;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(" is empty");
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public static float[] checkArrayElementsInRange(float[] fArr, float f, float f2, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" must not be null");
        checkNotNull(fArr, sb.toString());
        int i = 0;
        while (i < fArr.length) {
            float f3 = fArr[i];
            if (Float.isNaN(f3)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append("[");
                sb2.append(i);
                sb2.append("] must not be NaN");
                throw new IllegalArgumentException(sb2.toString());
            } else if (f3 < f) {
                throw new IllegalArgumentException(String.format(Locale.US, "%s[%d] is out of range of [%f, %f] (too low)", new Object[]{str, Integer.valueOf(i), Float.valueOf(f), Float.valueOf(f2)}));
            } else if (f3 > f2) {
                throw new IllegalArgumentException(String.format(Locale.US, "%s[%d] is out of range of [%f, %f] (too high)", new Object[]{str, Integer.valueOf(i), Float.valueOf(f), Float.valueOf(f2)}));
            } else {
                i++;
            }
        }
        return fArr;
    }
}
