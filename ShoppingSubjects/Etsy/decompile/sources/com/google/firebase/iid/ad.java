package com.google.firebase.iid;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.tasks.f;
import com.google.android.gms.tasks.g;
import com.google.android.gms.tasks.i;
import com.google.firebase.a;
import java.io.IOException;
import java.util.concurrent.Executor;

final class ad implements IRpc {
    private final a a;
    private final g b;
    @VisibleForTesting
    private final m c;
    private final Executor d;

    ad(a aVar, g gVar, Executor executor) {
        this(aVar, gVar, executor, new m(aVar.a(), gVar));
    }

    @VisibleForTesting
    private ad(a aVar, g gVar, Executor executor, m mVar) {
        this.a = aVar;
        this.b = gVar;
        this.c = mVar;
        this.d = executor;
    }

    private final <T> f<Void> a(f<T> fVar) {
        return fVar.a(this.d, (com.google.android.gms.tasks.a<TResult, TContinuationResult>) new af<TResult,TContinuationResult>(this));
    }

    private final f<Bundle> a(String str, String str2, String str3, Bundle bundle) {
        bundle.putString("scope", str3);
        bundle.putString("sender", str2);
        bundle.putString("subtype", str2);
        bundle.putString("appid", str);
        bundle.putString("gmp_app_id", this.a.c().a());
        bundle.putString("gmsv", Integer.toString(this.b.d()));
        bundle.putString("osv", Integer.toString(VERSION.SDK_INT));
        bundle.putString("app_ver", this.b.b());
        bundle.putString("app_ver_name", this.b.c());
        bundle.putString("cliv", "fiid-12451000");
        g gVar = new g();
        this.d.execute(new ae(this, bundle, gVar));
        return gVar.a();
    }

    /* access modifiers changed from: private */
    public static String a(Bundle bundle) throws IOException {
        if (bundle == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String string = bundle.getString("registration_id");
        if (string != null) {
            return string;
        }
        String string2 = bundle.getString("unregistered");
        if (string2 != null) {
            return string2;
        }
        String string3 = bundle.getString("error");
        if ("RST".equals(string3)) {
            throw new IOException("INSTANCE_ID_RESET");
        } else if (string3 != null) {
            throw new IOException(string3);
        } else {
            String valueOf = String.valueOf(bundle);
            StringBuilder sb = new StringBuilder(21 + String.valueOf(valueOf).length());
            sb.append("Unexpected response: ");
            sb.append(valueOf);
            Log.w("FirebaseInstanceId", sb.toString(), new Throwable());
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
    }

    private final f<String> b(f<Bundle> fVar) {
        return fVar.a(this.d, (com.google.android.gms.tasks.a<TResult, TContinuationResult>) new ag<TResult,TContinuationResult>(this));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(Bundle bundle, g gVar) {
        try {
            gVar.a(this.c.a(bundle));
        } catch (IOException e) {
            gVar.a((Exception) e);
        }
    }

    public final f<Void> ackMessage(String str) {
        return null;
    }

    public final f<String> buildChannel(String str) {
        return i.a("");
    }

    public final f<Void> deleteInstanceId(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("iid-operation", "delete");
        bundle.putString("delete", "1");
        return a(b(a(str, "*", "*", bundle)));
    }

    public final f<Void> deleteToken(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("delete", "1");
        return a(b(a(str, str2, str3, bundle)));
    }

    public final f<String> getToken(String str, String str2, String str3) {
        return b(a(str, str2, str3, new Bundle()));
    }

    public final f<Void> subscribeToTopic(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        String str4 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str3);
        bundle.putString(str4, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        String valueOf3 = String.valueOf("/topics/");
        String valueOf4 = String.valueOf(str3);
        return a(b(a(str, str2, valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), bundle)));
    }

    public final f<Void> unsubscribeFromTopic(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        String str4 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str3);
        bundle.putString(str4, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        bundle.putString("delete", "1");
        String valueOf3 = String.valueOf("/topics/");
        String valueOf4 = String.valueOf(str3);
        return a(b(a(str, str2, valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), bundle)));
    }
}
