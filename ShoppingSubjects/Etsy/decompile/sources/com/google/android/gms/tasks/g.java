package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public class g<TResult> {
    private final y<TResult> a = new y<>();

    @NonNull
    public f<TResult> a() {
        return this.a;
    }

    public void a(@NonNull Exception exc) {
        this.a.a(exc);
    }

    public void a(TResult tresult) {
        this.a.a(tresult);
    }

    public boolean b(@NonNull Exception exc) {
        return this.a.b(exc);
    }

    public boolean b(TResult tresult) {
        return this.a.b(tresult);
    }
}
