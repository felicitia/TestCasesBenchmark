package com.google.android.gms.auth.api.signin;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public class c implements Result {
    private Status a;
    private GoogleSignInAccount b;

    public c(@Nullable GoogleSignInAccount googleSignInAccount, @NonNull Status status) {
        this.b = googleSignInAccount;
        this.a = status;
    }

    @Nullable
    public GoogleSignInAccount a() {
        return this.b;
    }

    public boolean b() {
        return this.a.isSuccess();
    }

    @NonNull
    public Status getStatus() {
        return this.a;
    }
}
