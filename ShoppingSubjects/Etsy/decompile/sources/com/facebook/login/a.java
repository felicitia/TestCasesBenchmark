package com.facebook.login;

import android.net.Uri;
import com.facebook.login.LoginClient.Request;
import java.util.Collection;

/* compiled from: DeviceLoginManager */
public class a extends d {
    private static volatile a b;
    private Uri a;

    public static a a() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    public void a(Uri uri) {
        this.a = uri;
    }

    public Uri b() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public Request a(Collection<String> collection) {
        Request a2 = super.a(collection);
        Uri b2 = b();
        if (b2 != null) {
            a2.setDeviceRedirectUriString(b2.toString());
        }
        return a2;
    }
}
