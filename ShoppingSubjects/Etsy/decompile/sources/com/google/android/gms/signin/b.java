package com.google.android.gms.signin;

import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.signin.internal.ISignInCallbacks;

public interface b extends Client {
    void a();

    void a(IAccountAccessor iAccountAccessor, boolean z);

    void a(ISignInCallbacks iSignInCallbacks);

    void b();
}
