package com.fasterxml.jackson.core.io;

import com.etsy.android.lib.models.editable.EditableListing;

public final class NumberOutput {
    private static int BILLION = 1000000000;
    static final char[] FULL_TRIPLETS = new char[4000];
    static final byte[] FULL_TRIPLETS_B = new byte[4000];
    static final char[] LEADING_TRIPLETS = new char[4000];
    private static long MAX_INT_AS_LONG = 2147483647L;
    private static int MILLION = 1000000;
    private static long MIN_INT_AS_LONG = -2147483648L;
    private static final char NULL_CHAR = '\u0000';
    static final String SMALLEST_LONG = String.valueOf(Long.MIN_VALUE);
    private static long TEN_BILLION_L = 10000000000L;
    private static long THOUSAND_L = 1000;
    static final String[] sSmallIntStrs = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static final String[] sSmallIntStrs2 = {EditableListing.LISTING_ID_DEVICE_DRAFT, "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"};

    static {
        int i = 0;
        int i2 = 0;
        while (i < 10) {
            char c = (char) (48 + i);
            char c2 = i == 0 ? 0 : c;
            int i3 = i2;
            int i4 = 0;
            while (i4 < 10) {
                char c3 = (char) (48 + i4);
                char c4 = (i == 0 && i4 == 0) ? 0 : c3;
                int i5 = i3;
                for (int i6 = 0; i6 < 10; i6++) {
                    char c5 = (char) (48 + i6);
                    LEADING_TRIPLETS[i5] = c2;
                    int i7 = i5 + 1;
                    LEADING_TRIPLETS[i7] = c4;
                    int i8 = i5 + 2;
                    LEADING_TRIPLETS[i8] = c5;
                    FULL_TRIPLETS[i5] = c;
                    FULL_TRIPLETS[i7] = c3;
                    FULL_TRIPLETS[i8] = c5;
                    i5 += 4;
                }
                i4++;
                i3 = i5;
            }
            i++;
            i2 = i3;
        }
        for (int i9 = 0; i9 < 4000; i9++) {
            FULL_TRIPLETS_B[i9] = (byte) FULL_TRIPLETS[i9];
        }
    }

    public static int outputInt(int i, char[] cArr, int i2) {
        int i3;
        int i4;
        int i5;
        if (i < 0) {
            if (i == Integer.MIN_VALUE) {
                return outputLong((long) i, cArr, i2);
            }
            int i6 = i2 + 1;
            cArr[i2] = '-';
            i = -i;
            i2 = i6;
        }
        if (i < MILLION) {
            if (i >= 1000) {
                int i7 = i / 1000;
                i5 = outputFullTriplet(i - (i7 * 1000), cArr, outputLeadingTriplet(i7, cArr, i2));
            } else if (i < 10) {
                i5 = i2 + 1;
                cArr[i2] = (char) (48 + i);
            } else {
                i5 = outputLeadingTriplet(i, cArr, i2);
            }
            return i5;
        }
        boolean z = i >= BILLION;
        if (z) {
            i -= BILLION;
            if (i >= BILLION) {
                i -= BILLION;
                i3 = i2 + 1;
                cArr[i2] = '2';
            } else {
                i3 = i2 + 1;
                cArr[i2] = '1';
            }
        } else {
            i3 = i2;
        }
        int i8 = i / 1000;
        int i9 = i - (i8 * 1000);
        int i10 = i8 / 1000;
        int i11 = i8 - (i10 * 1000);
        if (z) {
            i4 = outputFullTriplet(i10, cArr, i3);
        } else {
            i4 = outputLeadingTriplet(i10, cArr, i3);
        }
        return outputFullTriplet(i9, cArr, outputFullTriplet(i11, cArr, i4));
    }

    public static int outputInt(int i, byte[] bArr, int i2) {
        int i3;
        int i4;
        int i5;
        if (i < 0) {
            if (i == Integer.MIN_VALUE) {
                return outputLong((long) i, bArr, i2);
            }
            int i6 = i2 + 1;
            bArr[i2] = 45;
            i = -i;
            i2 = i6;
        }
        if (i < MILLION) {
            if (i >= 1000) {
                int i7 = i / 1000;
                i5 = outputFullTriplet(i - (i7 * 1000), bArr, outputLeadingTriplet(i7, bArr, i2));
            } else if (i < 10) {
                i5 = i2 + 1;
                bArr[i2] = (byte) (48 + i);
            } else {
                i5 = outputLeadingTriplet(i, bArr, i2);
            }
            return i5;
        }
        boolean z = i >= BILLION;
        if (z) {
            i -= BILLION;
            if (i >= BILLION) {
                i -= BILLION;
                i3 = i2 + 1;
                bArr[i2] = 50;
            } else {
                i3 = i2 + 1;
                bArr[i2] = 49;
            }
        } else {
            i3 = i2;
        }
        int i8 = i / 1000;
        int i9 = i - (i8 * 1000);
        int i10 = i8 / 1000;
        int i11 = i8 - (i10 * 1000);
        if (z) {
            i4 = outputFullTriplet(i10, bArr, i3);
        } else {
            i4 = outputLeadingTriplet(i10, bArr, i3);
        }
        return outputFullTriplet(i9, bArr, outputFullTriplet(i11, bArr, i4));
    }

    public static int outputLong(long j, char[] cArr, int i) {
        if (j < 0) {
            if (j > MIN_INT_AS_LONG) {
                return outputInt((int) j, cArr, i);
            }
            if (j == Long.MIN_VALUE) {
                int length = SMALLEST_LONG.length();
                SMALLEST_LONG.getChars(0, length, cArr, i);
                return i + length;
            }
            int i2 = i + 1;
            cArr[i] = '-';
            j = -j;
            i = i2;
        } else if (j <= MAX_INT_AS_LONG) {
            return outputInt((int) j, cArr, i);
        }
        int calcLongStrLength = calcLongStrLength(j) + i;
        int i3 = calcLongStrLength;
        while (j > MAX_INT_AS_LONG) {
            i3 -= 3;
            long j2 = j / THOUSAND_L;
            outputFullTriplet((int) (j - (THOUSAND_L * j2)), cArr, i3);
            j = j2;
        }
        int i4 = (int) j;
        while (i4 >= 1000) {
            i3 -= 3;
            int i5 = i4 / 1000;
            outputFullTriplet(i4 - (i5 * 1000), cArr, i3);
            i4 = i5;
        }
        outputLeadingTriplet(i4, cArr, i);
        return calcLongStrLength;
    }

    public static int outputLong(long j, byte[] bArr, int i) {
        if (j < 0) {
            if (j > MIN_INT_AS_LONG) {
                return outputInt((int) j, bArr, i);
            }
            if (j == Long.MIN_VALUE) {
                int length = SMALLEST_LONG.length();
                int i2 = 0;
                while (i2 < length) {
                    int i3 = i + 1;
                    bArr[i] = (byte) SMALLEST_LONG.charAt(i2);
                    i2++;
                    i = i3;
                }
                return i;
            }
            int i4 = i + 1;
            bArr[i] = 45;
            j = -j;
            i = i4;
        } else if (j <= MAX_INT_AS_LONG) {
            return outputInt((int) j, bArr, i);
        }
        int calcLongStrLength = calcLongStrLength(j) + i;
        int i5 = calcLongStrLength;
        while (j > MAX_INT_AS_LONG) {
            i5 -= 3;
            long j2 = j / THOUSAND_L;
            outputFullTriplet((int) (j - (THOUSAND_L * j2)), bArr, i5);
            j = j2;
        }
        int i6 = (int) j;
        while (i6 >= 1000) {
            i5 -= 3;
            int i7 = i6 / 1000;
            outputFullTriplet(i6 - (i7 * 1000), bArr, i5);
            i6 = i7;
        }
        outputLeadingTriplet(i6, bArr, i);
        return calcLongStrLength;
    }

    public static String toString(int i) {
        if (i < sSmallIntStrs.length) {
            if (i >= 0) {
                return sSmallIntStrs[i];
            }
            int i2 = (-i) - 1;
            if (i2 < sSmallIntStrs2.length) {
                return sSmallIntStrs2[i2];
            }
        }
        return Integer.toString(i);
    }

    public static String toString(long j) {
        if (j > 2147483647L || j < -2147483648L) {
            return Long.toString(j);
        }
        return toString((int) j);
    }

    public static String toString(double d) {
        return Double.toString(d);
    }

    private static int outputLeadingTriplet(int i, char[] cArr, int i2) {
        int i3 = i << 2;
        int i4 = i3 + 1;
        char c = LEADING_TRIPLETS[i3];
        if (c != 0) {
            int i5 = i2 + 1;
            cArr[i2] = c;
            i2 = i5;
        }
        int i6 = i4 + 1;
        char c2 = LEADING_TRIPLETS[i4];
        if (c2 != 0) {
            int i7 = i2 + 1;
            cArr[i2] = c2;
            i2 = i7;
        }
        int i8 = i2 + 1;
        cArr[i2] = LEADING_TRIPLETS[i6];
        return i8;
    }

    private static int outputLeadingTriplet(int i, byte[] bArr, int i2) {
        int i3 = i << 2;
        int i4 = i3 + 1;
        char c = LEADING_TRIPLETS[i3];
        if (c != 0) {
            int i5 = i2 + 1;
            bArr[i2] = (byte) c;
            i2 = i5;
        }
        int i6 = i4 + 1;
        char c2 = LEADING_TRIPLETS[i4];
        if (c2 != 0) {
            int i7 = i2 + 1;
            bArr[i2] = (byte) c2;
            i2 = i7;
        }
        int i8 = i2 + 1;
        bArr[i2] = (byte) LEADING_TRIPLETS[i6];
        return i8;
    }

    private static int outputFullTriplet(int i, char[] cArr, int i2) {
        int i3 = i << 2;
        int i4 = i2 + 1;
        int i5 = i3 + 1;
        cArr[i2] = FULL_TRIPLETS[i3];
        int i6 = i4 + 1;
        int i7 = i5 + 1;
        cArr[i4] = FULL_TRIPLETS[i5];
        int i8 = i6 + 1;
        cArr[i6] = FULL_TRIPLETS[i7];
        return i8;
    }

    private static int outputFullTriplet(int i, byte[] bArr, int i2) {
        int i3 = i << 2;
        int i4 = i2 + 1;
        int i5 = i3 + 1;
        bArr[i2] = FULL_TRIPLETS_B[i3];
        int i6 = i4 + 1;
        int i7 = i5 + 1;
        bArr[i4] = FULL_TRIPLETS_B[i5];
        int i8 = i6 + 1;
        bArr[i6] = FULL_TRIPLETS_B[i7];
        return i8;
    }

    private static int calcLongStrLength(long j) {
        int i = 10;
        for (long j2 = TEN_BILLION_L; j >= j2 && i != 19; j2 = (j2 << 3) + (j2 << 1)) {
            i++;
        }
        return i;
    }
}
