package com.google.android.gms.internal.location;

import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;

public final class s {
    public static Looper a() {
        Preconditions.checkState(Looper.myLooper() != null, "Can't create handler inside thread that has not called Looper.prepare()");
        return Looper.myLooper();
    }
}
