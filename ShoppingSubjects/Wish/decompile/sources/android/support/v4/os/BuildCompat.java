package android.support.v4.os;

import android.os.Build.VERSION;

public class BuildCompat {
    @Deprecated
    public static boolean isAtLeastOMR1() {
        return VERSION.SDK_INT >= 27;
    }

    public static boolean isAtLeastP() {
        return VERSION.CODENAME.equals("P");
    }
}
