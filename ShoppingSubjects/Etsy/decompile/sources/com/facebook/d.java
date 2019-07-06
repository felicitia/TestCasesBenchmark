package com.facebook;

import android.content.Intent;
import com.facebook.internal.CallbackManagerImpl;

/* compiled from: CallbackManager */
public interface d {

    /* compiled from: CallbackManager */
    public static class a {
        public static d a() {
            return new CallbackManagerImpl();
        }
    }

    boolean a(int i, int i2, Intent intent);
}
