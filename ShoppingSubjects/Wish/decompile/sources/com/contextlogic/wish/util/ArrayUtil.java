package com.contextlogic.wish.util;

public class ArrayUtil {
    public static boolean any(boolean[] zArr) {
        if (zArr == null) {
            return false;
        }
        for (boolean z : zArr) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    public static boolean all(boolean[] zArr) {
        if (zArr == null || zArr.length == 0) {
            return false;
        }
        for (boolean z : zArr) {
            if (!z) {
                return false;
            }
        }
        return true;
    }
}
