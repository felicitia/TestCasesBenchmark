package okhttp3.internal.e;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;

/* compiled from: Jdk9Platform */
final class c extends f {
    final Method a;
    final Method b;

    c(Method method, Method method2) {
        this.a = method;
        this.b = method2;
    }

    public void a(SSLSocket sSLSocket, String str, List<Protocol> list) {
        try {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            List a2 = a(list);
            this.a.invoke(sSLParameters, new Object[]{a2.toArray(new String[a2.size()])});
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw okhttp3.internal.c.a("unable to set ssl parameters", (Exception) e);
        }
    }

    public String a(SSLSocket sSLSocket) {
        try {
            String str = (String) this.b.invoke(sSLSocket, new Object[0]);
            if (str == null || str.equals("")) {
                return null;
            }
            return str;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw okhttp3.internal.c.a("unable to get selected protocols", (Exception) e);
        }
    }

    public X509TrustManager a(SSLSocketFactory sSLSocketFactory) {
        throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on JDK 9+");
    }

    public static c b() {
        try {
            return new c(SSLParameters.class.getMethod("setApplicationProtocols", new Class[]{String[].class}), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }
}
