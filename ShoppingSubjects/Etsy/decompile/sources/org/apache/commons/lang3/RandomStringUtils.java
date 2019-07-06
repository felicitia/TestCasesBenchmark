package org.apache.commons.lang3;

import java.util.Random;

public class RandomStringUtils {
    private static final Random RANDOM = new Random();

    public static String random(int i) {
        return random(i, false, false);
    }

    public static String randomAscii(int i) {
        return random(i, 32, 127, false, false);
    }

    public static String randomAlphabetic(int i) {
        return random(i, true, false);
    }

    public static String randomAlphanumeric(int i) {
        return random(i, true, true);
    }

    public static String randomNumeric(int i) {
        return random(i, false, true);
    }

    public static String random(int i, boolean z, boolean z2) {
        return random(i, 0, 0, z, z2);
    }

    public static String random(int i, int i2, int i3, boolean z, boolean z2) {
        return random(i, i2, i3, z, z2, null, RANDOM);
    }

    public static String random(int i, int i2, int i3, boolean z, boolean z2, char... cArr) {
        return random(i, i2, i3, z, z2, cArr, RANDOM);
    }

    public static String random(int i, int i2, int i3, boolean z, boolean z2, char[] cArr, Random random) {
        char c;
        if (i == 0) {
            return "";
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Requested random string length ");
            sb.append(i);
            sb.append(" is less than 0.");
            throw new IllegalArgumentException(sb.toString());
        }
        if (i2 == 0 && i3 == 0) {
            i3 = 123;
            i2 = 32;
            if (!z && !z2) {
                i2 = 0;
                i3 = Integer.MAX_VALUE;
            }
        }
        char[] cArr2 = new char[i];
        int i4 = i3 - i2;
        while (true) {
            int i5 = i - 1;
            if (i == 0) {
                return new String(cArr2);
            }
            if (cArr == null) {
                c = (char) (random.nextInt(i4) + i2);
            } else {
                c = cArr[random.nextInt(i4) + i2];
            }
            if ((!z || !Character.isLetter(c)) && ((!z2 || !Character.isDigit(c)) && (z || z2))) {
                i5++;
            } else if (c < 56320 || c > 57343) {
                if (c < 55296 || c > 56191) {
                    if (c < 56192 || c > 56319) {
                        cArr2[i5] = c;
                    } else {
                        i5++;
                    }
                } else if (i5 == 0) {
                    i5++;
                } else {
                    cArr2[i5] = (char) (56320 + random.nextInt(128));
                    i5--;
                    cArr2[i5] = c;
                }
            } else if (i5 == 0) {
                i5++;
            } else {
                cArr2[i5] = c;
                i5--;
                cArr2[i5] = (char) (55296 + random.nextInt(128));
            }
            i = i5;
        }
    }

    public static String random(int i, String str) {
        if (str != null) {
            return random(i, str.toCharArray());
        }
        return random(i, 0, 0, false, false, null, RANDOM);
    }

    public static String random(int i, char... cArr) {
        if (cArr == null) {
            return random(i, 0, 0, false, false, null, RANDOM);
        }
        return random(i, 0, cArr.length, false, false, cArr, RANDOM);
    }
}
