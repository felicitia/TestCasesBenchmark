package com.contextlogic.wish.util;

import java.util.concurrent.atomic.AtomicBoolean;

public class TestUtil {
    private static AtomicBoolean mIsRunningTest;

    public static synchronized boolean isRunningTest() {
        boolean z;
        boolean z2;
        synchronized (TestUtil.class) {
            if (mIsRunningTest == null) {
                try {
                    Class.forName("android.support.test.espresso.Espresso");
                    z2 = true;
                } catch (ClassNotFoundException unused) {
                    z2 = false;
                }
                mIsRunningTest = new AtomicBoolean(z2);
            }
            z = mIsRunningTest.get();
        }
        return z;
    }
}
