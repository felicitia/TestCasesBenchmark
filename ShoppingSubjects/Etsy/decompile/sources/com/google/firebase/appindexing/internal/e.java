package com.google.firebase.appindexing.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.firebase.b;

final class e extends GoogleApi<NoOptions> {
    public e(Context context) {
        super(context, b.a, null, Looper.getMainLooper(), new b());
    }
}
