package com.google.firebase.appindexing.internal;

import android.content.Context;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.internal.icing.bu;
import com.google.firebase.b;

final class m extends GoogleApi<NoOptions> {
    m(Context context) {
        super(context, bu.a, null, (StatusExceptionMapper) new b());
    }
}
