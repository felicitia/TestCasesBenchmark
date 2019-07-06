package com.google.firebase.a;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class a<T> {
    private final Class<T> a;
    private final T b;

    @KeepForSdk
    public Class<T> a() {
        return this.a;
    }

    public String toString() {
        return String.format("Event{type: %s, payload: %s}", new Object[]{this.a, this.b});
    }
}
