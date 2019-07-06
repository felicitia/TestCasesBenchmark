package okhttp3.internal.e;

import com.etsy.android.lib.models.ResponseConstants;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import okhttp3.internal.g.a;
import okhttp3.internal.g.b;
import okhttp3.internal.g.c;
import okhttp3.internal.g.e;
import okhttp3.w;

/* compiled from: Platform */
public class f {
    private static final f a = b();
    private static final Logger b = Logger.getLogger(w.class.getName());

    public String a(SSLSocket sSLSocket) {
        return null;
    }

    public void a(SSLSocket sSLSocket, String str, List<Protocol> list) {
    }

    public void b(SSLSocket sSLSocket) {
    }

    public boolean b(String str) {
        return true;
    }

    public static f c() {
        return a;
    }

    /* access modifiers changed from: protected */
    public X509TrustManager a(SSLSocketFactory sSLSocketFactory) {
        try {
            Object a2 = a((Object) sSLSocketFactory, Class.forName("sun.security.ssl.SSLContextImpl"), ResponseConstants.CONTEXT);
            if (a2 == null) {
                return null;
            }
            return (X509TrustManager) a(a2, X509TrustManager.class, "trustManager");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public void a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }

    public void a(int i, String str, Throwable th) {
        b.log(i == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    public Object a(String str) {
        if (b.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    public void a(String str, Object obj) {
        if (obj == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);");
            str = sb.toString();
        }
        a(5, str, (Throwable) obj);
    }

    public static List<String> a(List<Protocol> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = (Protocol) list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                arrayList.add(protocol.toString());
            }
        }
        return arrayList;
    }

    public c a(X509TrustManager x509TrustManager) {
        return new a(b(x509TrustManager));
    }

    public c b(SSLSocketFactory sSLSocketFactory) {
        X509TrustManager a2 = a(sSLSocketFactory);
        if (a2 != null) {
            return a(a2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to extract the trust manager on ");
        sb.append(c());
        sb.append(", sslSocketFactory is ");
        sb.append(sSLSocketFactory.getClass());
        throw new IllegalStateException(sb.toString());
    }

    public static boolean d() {
        if ("conscrypt".equals(System.getProperty("okhttp.platform"))) {
            return true;
        }
        return "Conscrypt".equals(Security.getProviders()[0].getName());
    }

    private static f b() {
        f a2 = a.a();
        if (a2 != null) {
            return a2;
        }
        if (d()) {
            f b2 = b.b();
            if (b2 != null) {
                return b2;
            }
        }
        c b3 = c.b();
        if (b3 != null) {
            return b3;
        }
        f b4 = d.b();
        if (b4 != null) {
            return b4;
        }
        return new f();
    }

    static byte[] b(List<Protocol> list) {
        okio.c cVar = new okio.c();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = (Protocol) list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                cVar.k(protocol.toString().length());
                cVar.b(protocol.toString());
            }
        }
        return cVar.s();
    }

    static <T> T a(Object obj, Class<T> cls, String str) {
        Class<Object> cls2 = obj.getClass();
        while (cls2 != Object.class) {
            try {
                Field declaredField = cls2.getDeclaredField(str);
                declaredField.setAccessible(true);
                Object obj2 = declaredField.get(obj);
                if (obj2 != null) {
                    if (cls.isInstance(obj2)) {
                        return cls.cast(obj2);
                    }
                }
                return null;
            } catch (NoSuchFieldException unused) {
                cls2 = cls2.getSuperclass();
            } catch (IllegalAccessException unused2) {
                throw new AssertionError();
            }
        }
        if (!str.equals("delegate")) {
            Object a2 = a(obj, Object.class, "delegate");
            if (a2 != null) {
                return a(a2, cls, str);
            }
        }
        return null;
    }

    public SSLContext m_() {
        try {
            return SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No TLS provider", e);
        }
    }

    public e b(X509TrustManager x509TrustManager) {
        return new b(x509TrustManager.getAcceptedIssuers());
    }
}
