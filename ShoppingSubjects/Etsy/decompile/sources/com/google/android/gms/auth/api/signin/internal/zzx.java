package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.Binder;
import com.google.android.gms.auth.api.a;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;

public final class zzx extends zzs {
    private final Context mContext;

    public zzx(Context context) {
        this.mContext = context;
    }

    private final void zzs() {
        if (!GooglePlayServicesUtil.isGooglePlayServicesUid(this.mContext, Binder.getCallingUid())) {
            int callingUid = Binder.getCallingUid();
            StringBuilder sb = new StringBuilder(52);
            sb.append("Calling UID ");
            sb.append(callingUid);
            sb.append(" is not Google Play services.");
            throw new SecurityException(sb.toString());
        }
    }

    public final void zzq() {
        zzs();
        b a = b.a(this.mContext);
        GoogleSignInAccount a2 = a.a();
        GoogleSignInOptions googleSignInOptions = GoogleSignInOptions.DEFAULT_SIGN_IN;
        if (a2 != null) {
            googleSignInOptions = a.b();
        }
        GoogleApiClient build = new Builder(this.mContext).addApi(a.e, googleSignInOptions).build();
        try {
            if (build.blockingConnect().isSuccess()) {
                if (a2 != null) {
                    a.h.c(build);
                } else {
                    build.clearDefaultAccountAndReconnect();
                }
            }
        } finally {
            build.disconnect();
        }
    }

    public final void zzr() {
        zzs();
        l.a(this.mContext).a();
    }
}
