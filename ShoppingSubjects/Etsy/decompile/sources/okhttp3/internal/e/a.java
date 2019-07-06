package okhttp3.internal.e;

import android.os.Build.VERSION;
import android.util.Log;
import com.etsy.android.lib.requests.ReceiptsRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import okhttp3.internal.g.e;

/* compiled from: AndroidPlatform */
class a extends f {
    private final Class<?> a;
    private final e<Socket> b;
    private final e<Socket> c;
    private final e<Socket> d;
    private final e<Socket> e;
    private final c f = c.a();

    /* renamed from: okhttp3.internal.e.a$a reason: collision with other inner class name */
    /* compiled from: AndroidPlatform */
    static final class C0195a extends okhttp3.internal.g.c {
        private final Object a;
        private final Method b;

        public int hashCode() {
            return 0;
        }

        C0195a(Object obj, Method method) {
            this.a = obj;
            this.b = method;
        }

        public List<Certificate> a(List<Certificate> list, String str) throws SSLPeerUnverifiedException {
            try {
                X509Certificate[] x509CertificateArr = (X509Certificate[]) list.toArray(new X509Certificate[list.size()]);
                return (List) this.b.invoke(this.a, new Object[]{x509CertificateArr, "RSA", str});
            } catch (InvocationTargetException e) {
                SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e.getMessage());
                sSLPeerUnverifiedException.initCause(e);
                throw sSLPeerUnverifiedException;
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public boolean equals(Object obj) {
            return obj instanceof C0195a;
        }
    }

    /* compiled from: AndroidPlatform */
    static final class b implements e {
        private final X509TrustManager a;
        private final Method b;

        b(X509TrustManager x509TrustManager, Method method) {
            this.b = method;
            this.a = x509TrustManager;
        }

        public X509Certificate a(X509Certificate x509Certificate) {
            try {
                TrustAnchor trustAnchor = (TrustAnchor) this.b.invoke(this.a, new Object[]{x509Certificate});
                return trustAnchor != null ? trustAnchor.getTrustedCert() : null;
            } catch (IllegalAccessException e) {
                throw okhttp3.internal.c.a("unable to get issues and signature", (Exception) e);
            } catch (InvocationTargetException unused) {
                return null;
            }
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            if (!this.a.equals(bVar.a) || !this.b.equals(bVar.b)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return this.a.hashCode() + (31 * this.b.hashCode());
        }
    }

    /* compiled from: AndroidPlatform */
    static final class c {
        private final Method a;
        private final Method b;
        private final Method c;

        c(Method method, Method method2, Method method3) {
            this.a = method;
            this.b = method2;
            this.c = method3;
        }

        /* access modifiers changed from: 0000 */
        public Object a(String str) {
            if (this.a != null) {
                try {
                    Object invoke = this.a.invoke(null, new Object[0]);
                    this.b.invoke(invoke, new Object[]{str});
                    return invoke;
                } catch (Exception unused) {
                }
            }
            return null;
        }

        /* access modifiers changed from: 0000 */
        public boolean a(Object obj) {
            if (obj == null) {
                return false;
            }
            try {
                this.c.invoke(obj, new Object[0]);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }

        static c a() {
            Method method;
            Method method2;
            Method method3 = null;
            try {
                Class cls = Class.forName("dalvik.system.CloseGuard");
                Method method4 = cls.getMethod("get", new Class[0]);
                method = cls.getMethod(ReceiptsRequest.STATUS_OPEN, new Class[]{String.class});
                method2 = cls.getMethod("warnIfOpen", new Class[0]);
                method3 = method4;
            } catch (Exception unused) {
                method2 = null;
                method = null;
            }
            return new c(method3, method, method2);
        }
    }

    a(Class<?> cls, e<Socket> eVar, e<Socket> eVar2, e<Socket> eVar3, e<Socket> eVar4) {
        this.a = cls;
        this.b = eVar;
        this.c = eVar2;
        this.d = eVar3;
        this.e = eVar4;
    }

    public void a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        try {
            socket.connect(inetSocketAddress, i);
        } catch (AssertionError e2) {
            if (okhttp3.internal.c.a(e2)) {
                throw new IOException(e2);
            }
            throw e2;
        } catch (SecurityException e3) {
            IOException iOException = new IOException("Exception in connect");
            iOException.initCause(e3);
            throw iOException;
        } catch (ClassCastException e4) {
            if (VERSION.SDK_INT == 26) {
                IOException iOException2 = new IOException("Exception in connect");
                iOException2.initCause(e4);
                throw iOException2;
            }
            throw e4;
        }
    }

    /* access modifiers changed from: protected */
    public X509TrustManager a(SSLSocketFactory sSLSocketFactory) {
        Object a2 = a((Object) sSLSocketFactory, this.a, "sslParameters");
        if (a2 == null) {
            try {
                a2 = a((Object) sSLSocketFactory, Class.forName("com.google.android.gms.org.conscrypt.SSLParametersImpl", false, sSLSocketFactory.getClass().getClassLoader()), "sslParameters");
            } catch (ClassNotFoundException unused) {
                return super.a(sSLSocketFactory);
            }
        }
        X509TrustManager x509TrustManager = (X509TrustManager) a(a2, X509TrustManager.class, "x509TrustManager");
        if (x509TrustManager != null) {
            return x509TrustManager;
        }
        return (X509TrustManager) a(a2, X509TrustManager.class, "trustManager");
    }

    public void a(SSLSocket sSLSocket, String str, List<Protocol> list) {
        if (str != null) {
            this.b.b(sSLSocket, Boolean.valueOf(true));
            this.c.b(sSLSocket, str);
        }
        if (this.e != null && this.e.a(sSLSocket)) {
            this.e.d(sSLSocket, b(list));
        }
    }

    public String a(SSLSocket sSLSocket) {
        String str = null;
        if (this.d == null || !this.d.a(sSLSocket)) {
            return null;
        }
        byte[] bArr = (byte[]) this.d.d(sSLSocket, new Object[0]);
        if (bArr != null) {
            str = new String(bArr, okhttp3.internal.c.e);
        }
        return str;
    }

    public void a(int i, String str, Throwable th) {
        int min;
        int i2 = 5;
        if (i != 5) {
            i2 = 3;
        }
        if (th != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(10);
            sb.append(Log.getStackTraceString(th));
            str = sb.toString();
        }
        int i3 = 0;
        int length = str.length();
        while (i3 < length) {
            int indexOf = str.indexOf(10, i3);
            if (indexOf == -1) {
                indexOf = length;
            }
            while (true) {
                min = Math.min(indexOf, i3 + 4000);
                Log.println(i2, "OkHttp", str.substring(i3, min));
                if (min >= indexOf) {
                    break;
                }
                i3 = min;
            }
            i3 = min + 1;
        }
    }

    public Object a(String str) {
        return this.f.a(str);
    }

    public void a(String str, Object obj) {
        if (!this.f.a(obj)) {
            a(5, str, (Throwable) null);
        }
    }

    public boolean b(String str) {
        try {
            Class cls = Class.forName("android.security.NetworkSecurityPolicy");
            return a(str, cls, cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return super.b(str);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
            throw okhttp3.internal.c.a("unable to determine cleartext support", e2);
        }
    }

    private boolean a(String str, Class<?> cls, Object obj) throws InvocationTargetException, IllegalAccessException {
        try {
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", new Class[]{String.class}).invoke(obj, new Object[]{str})).booleanValue();
        } catch (NoSuchMethodException unused) {
            return b(str, cls, obj);
        }
    }

    private boolean b(String str, Class<?> cls, Object obj) throws InvocationTargetException, IllegalAccessException {
        try {
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", new Class[0]).invoke(obj, new Object[0])).booleanValue();
        } catch (NoSuchMethodException unused) {
            return super.b(str);
        }
    }

    private static boolean b() {
        if (Security.getProvider("GMSCore_OpenSSL") != null) {
            return true;
        }
        try {
            Class.forName("android.net.Network");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public okhttp3.internal.g.c a(X509TrustManager x509TrustManager) {
        try {
            Class cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new C0195a(cls.getConstructor(new Class[]{X509TrustManager.class}).newInstance(new Object[]{x509TrustManager}), cls.getMethod("checkServerTrusted", new Class[]{X509Certificate[].class, String.class, String.class}));
        } catch (Exception unused) {
            return super.a(x509TrustManager);
        }
    }

    public static f a() {
        Class cls;
        e eVar;
        e eVar2;
        try {
            cls = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
        } catch (ClassNotFoundException unused) {
            try {
                cls = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
            } catch (ClassNotFoundException unused2) {
                return null;
            }
        }
        Class cls2 = cls;
        e eVar3 = new e(null, "setUseSessionTickets", Boolean.TYPE);
        e eVar4 = new e(null, "setHostname", String.class);
        if (b()) {
            e eVar5 = new e(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
            eVar = new e(null, "setAlpnProtocols", byte[].class);
            eVar2 = eVar5;
        } else {
            eVar2 = null;
            eVar = null;
        }
        a aVar = new a(cls2, eVar3, eVar4, eVar2, eVar);
        return aVar;
    }

    public e b(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", new Class[]{X509Certificate.class});
            declaredMethod.setAccessible(true);
            return new b(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return super.b(x509TrustManager);
        }
    }
}
