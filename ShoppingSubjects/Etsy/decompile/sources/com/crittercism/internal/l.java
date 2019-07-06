package com.crittercism.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.harmony.xnet.provider.jsse.SSLParametersImpl;

public final class l extends k {
    private static boolean a = false;
    private static SSLSocketFactory b;
    private SSLParametersImpl c;
    private d d;
    private SSLSocketFactory delegate;
    private c e;

    public static boolean a(d dVar, c cVar) {
        if (a) {
            return a;
        }
        SSLSocketFactory defaultSSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
        try {
            l lVar = new l(defaultSSLSocketFactory, dVar, cVar);
            try {
                lVar.createSocket(lVar.createSocket(), "localhost", 6895, true);
            } catch (SocketException unused) {
            }
            HttpsURLConnection.setDefaultSSLSocketFactory(lVar);
            b = defaultSSLSocketFactory;
            a = true;
            return true;
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable th) {
            cm.a("Unable to instrument https connections.", th);
            return false;
        }
    }

    public l(SSLSocketFactory sSLSocketFactory, d dVar, c cVar) {
        this.delegate = sSLSocketFactory;
        this.d = dVar;
        this.e = cVar;
        this.c = a(sSLSocketFactory);
    }

    public final String[] getDefaultCipherSuites() {
        return this.delegate.getDefaultCipherSuites();
    }

    public final String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }

    public final Socket createSocket(Socket socket, String str, int i, boolean z) {
        p pVar = new p(this.d, this.e, socket, str, i, z, a(this.c));
        return pVar;
    }

    public final Socket createSocket(String str, int i) {
        n nVar = new n(this.d, this.e, str, i, a(this.c));
        return nVar;
    }

    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        n nVar = new n(this.d, this.e, str, i, inetAddress, i2, a(this.c));
        return nVar;
    }

    public final Socket createSocket(InetAddress inetAddress, int i) {
        n nVar = new n(this.d, this.e, inetAddress, i, a(this.c));
        return nVar;
    }

    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        n nVar = new n(this.d, this.e, inetAddress, i, inetAddress2, i2, a(this.c));
        return nVar;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.net.Socket, com.crittercism.internal.n] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [java.net.Socket, com.crittercism.internal.n]
      assigns: [com.crittercism.internal.n]
      uses: [java.net.Socket]
      mth insns count: 6
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/183835416.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/170174037.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$38/2083670723.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.net.Socket createSocket() {
        /*
            r4 = this;
            com.crittercism.internal.n r0 = new com.crittercism.internal.n
            com.crittercism.internal.d r1 = r4.d
            com.crittercism.internal.c r2 = r4.e
            org.apache.harmony.xnet.provider.jsse.SSLParametersImpl r3 = r4.c
            org.apache.harmony.xnet.provider.jsse.SSLParametersImpl r3 = a(r3)
            r0.<init>(r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crittercism.internal.l.createSocket():java.net.Socket");
    }

    private static SSLParametersImpl a(SSLSocketFactory sSLSocketFactory) {
        SSLParametersImpl sSLParametersImpl;
        try {
            sSLParametersImpl = (SSLParametersImpl) e.a(e.a(sSLSocketFactory.getClass(), SSLParametersImpl.class, false), sSLSocketFactory);
        } catch (bk e2) {
            cm.b((Throwable) e2);
            sSLParametersImpl = null;
        }
        return a(sSLParametersImpl);
    }

    private static SSLParametersImpl a(SSLParametersImpl sSLParametersImpl) {
        try {
            return b(sSLParametersImpl);
        } catch (bk unused) {
            return null;
        }
    }

    private static SSLParametersImpl b(SSLParametersImpl sSLParametersImpl) {
        try {
            Method declaredMethod = SSLParametersImpl.class.getDeclaredMethod("clone", new Class[0]);
            declaredMethod.setAccessible(true);
            return (SSLParametersImpl) declaredMethod.invoke(sSLParametersImpl, new Object[0]);
        } catch (NoSuchMethodException e2) {
            throw new bk((Throwable) e2);
        } catch (IllegalArgumentException e3) {
            throw new bk((Throwable) e3);
        } catch (IllegalAccessException e4) {
            throw new bk((Throwable) e4);
        } catch (InvocationTargetException e5) {
            throw new bk((Throwable) e5);
        }
    }

    public final SSLSocketFactory a() {
        return this.delegate;
    }
}
