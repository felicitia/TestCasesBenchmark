package com.google.android.gms.internal.ads;

import android.os.Environment;
import java.util.concurrent.Callable;

final class ajx implements Callable<Boolean> {
    ajx() {
    }

    public final /* synthetic */ Object call() throws Exception {
        return Boolean.valueOf("mounted".equals(Environment.getExternalStorageState()));
    }
}
