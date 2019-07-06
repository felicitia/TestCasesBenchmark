package com.contextlogic.wish.http;

import android.os.Build.VERSION;
import com.crashlytics.android.Crashlytics;
import java.util.ArrayList;
import javax.net.ssl.SSLContext;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient.Builder;
import okhttp3.TlsVersion;

public class Tls12Helper {
    public static void enableTls12(Builder builder) {
        if (VERSION.SDK_INT < 22) {
            try {
                SSLContext instance = SSLContext.getInstance("TLSv1.2");
                instance.init(null, null, null);
                builder.sslSocketFactory(new Tls12SocketFactory(instance.getSocketFactory()));
                ConnectionSpec build = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_2).build();
                ArrayList arrayList = new ArrayList();
                arrayList.add(build);
                arrayList.add(ConnectionSpec.COMPATIBLE_TLS);
                arrayList.add(ConnectionSpec.CLEARTEXT);
                builder.connectionSpecs(arrayList);
            } catch (Exception e) {
                Crashlytics.logException(e);
            }
        }
    }
}
