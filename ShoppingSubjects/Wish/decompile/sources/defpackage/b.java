package defpackage;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

/* renamed from: b reason: default package */
/* compiled from: GA */
final class b implements Interceptor {
    b() {
    }

    public final Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response proceed = chain.proceed(request);
        if (proceed != null) {
            if (!request.isHttps()) {
                throw new IOException("M27");
            }
            al.a(proceed.handshake().peerCertificates(), request.url().host());
        }
        return proceed;
    }
}
