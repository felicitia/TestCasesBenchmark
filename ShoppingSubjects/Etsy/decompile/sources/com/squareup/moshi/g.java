package com.squareup.moshi;

import org.apache.commons.lang3.ClassUtils;

/* compiled from: JsonScope */
final class g {
    static String a(int i, int[] iArr, String[] strArr, int[] iArr2) {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        for (int i2 = 0; i2 < i; i2++) {
            switch (iArr[i2]) {
                case 1:
                case 2:
                    sb.append('[');
                    sb.append(iArr2[i2]);
                    sb.append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                    if (strArr[i2] == null) {
                        break;
                    } else {
                        sb.append(strArr[i2]);
                        break;
                    }
            }
        }
        return sb.toString();
    }
}
