package com.google.firebase.appindexing.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApi;
import com.google.firebase.FirebaseExceptionMapper;

final class zzi extends GoogleApi<Object> {
    public zzi(Context context) {
        super(context, zzd.API, null, Looper.getMainLooper(), new FirebaseExceptionMapper());
    }
}
