package okhttp3.internal.e;

import com.etsy.android.lib.models.ResponseConstants;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;
import okhttp3.internal.c;

/* compiled from: JdkWithJettyBootPlatform */
class d extends f {
    private final Method a;
    private final Method b;
    private final Method c;
    private final Class<?> d;
    private final Class<?> e;

    /* compiled from: JdkWithJettyBootPlatform */
    private static class a implements InvocationHandler {
        boolean a;
        String b;
        private final List<String> c;

        a(List<String> list) {
            this.c = list;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            Class<String> returnType = method.getReturnType();
            if (objArr == null) {
                objArr = c.b;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return Boolean.valueOf(true);
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.a = true;
                return null;
            } else if (name.equals("protocols") && objArr.length == 0) {
                return this.c;
            } else {
                if ((name.equals("selectProtocol") || name.equals("select")) && String.class == returnType && objArr.length == 1 && (objArr[0] instanceof List)) {
                    List list = (List) objArr[0];
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        if (this.c.contains(list.get(i))) {
                            String str = (String) list.get(i);
                            this.b = str;
                            return str;
                        }
                    }
                    String str2 = (String) this.c.get(0);
                    this.b = str2;
                    return str2;
                } else if ((!name.equals("protocolSelected") && !name.equals(ResponseConstants.SELECTED)) || objArr.length != 1) {
                    return method.invoke(this, objArr);
                } else {
                    this.b = (String) objArr[0];
                    return null;
                }
            }
        }
    }

    d(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
        this.a = method;
        this.b = method2;
        this.c = method3;
        this.d = cls;
        this.e = cls2;
    }

    public void a(SSLSocket sSLSocket, String str, List<Protocol> list) {
        List a2 = a(list);
        try {
            Object newProxyInstance = Proxy.newProxyInstance(f.class.getClassLoader(), new Class[]{this.d, this.e}, new a(a2));
            this.a.invoke(null, new Object[]{sSLSocket, newProxyInstance});
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw c.a("unable to set alpn", (Exception) e2);
        }
    }

    public void b(SSLSocket sSLSocket) {
        try {
            this.c.invoke(null, new Object[]{sSLSocket});
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw c.a("unable to remove alpn", (Exception) e2);
        }
    }

    public String a(SSLSocket sSLSocket) {
        try {
            Object[] objArr = {sSLSocket};
            String str = null;
            a aVar = (a) Proxy.getInvocationHandler(this.b.invoke(null, objArr));
            if (aVar.a || aVar.b != null) {
                if (!aVar.a) {
                    str = aVar.b;
                }
                return str;
            }
            f.c().a(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", (Throwable) null);
            return null;
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw c.a("unable to get selected protocol", (Exception) e2);
        }
    }

    public static f b() {
        String str = "org.eclipse.jetty.alpn.ALPN";
        try {
            Class cls = Class.forName(str);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("$Provider");
            Class cls2 = Class.forName(sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append("$ClientProvider");
            Class cls3 = Class.forName(sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append("$ServerProvider");
            Class cls4 = Class.forName(sb3.toString());
            Method method = cls.getMethod("put", new Class[]{SSLSocket.class, cls2});
            Method method2 = method;
            d dVar = new d(method2, cls.getMethod("get", new Class[]{SSLSocket.class}), cls.getMethod("remove", new Class[]{SSLSocket.class}), cls3, cls4);
            return dVar;
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return null;
        }
    }
}
