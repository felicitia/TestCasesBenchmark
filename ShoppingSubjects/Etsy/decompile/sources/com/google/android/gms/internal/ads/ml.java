package com.google.android.gms.internal.ads;

import android.os.Looper;

final class ml implements Runnable {
    ml(mk mkVar) {
    }

    public final void run() {
        Looper.myLooper().quit();
    }
}
