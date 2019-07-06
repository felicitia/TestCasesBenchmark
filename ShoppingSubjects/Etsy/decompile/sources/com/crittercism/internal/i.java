package com.crittercism.internal;

import android.os.Build.VERSION;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Provider.Service;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLContextSpi;

public final class i extends Service {
    public static final String[] a = {"Default", "SSL", "TLSv1.1", "TLSv1.2", "SSLv3", "TLSv1", "TLS"};
    private d b;
    private c c;
    private Service d;

    private i(Service service, d dVar, c cVar) {
        super(service.getProvider(), service.getType(), service.getAlgorithm(), service.getClassName(), null, null);
        this.b = dVar;
        this.c = cVar;
        this.d = service;
    }

    private static i a(Service service, d dVar, c cVar) {
        i iVar = new i(service, dVar, cVar);
        try {
            Field[] fields = Service.class.getFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                fields[i].set(iVar, fields[i].get(service));
            }
            return iVar;
        } catch (Exception unused) {
            return null;
        }
    }

    private static Provider a() {
        Provider provider = null;
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            if (instance != null) {
                provider = instance.getProvider();
            }
            return provider;
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public static boolean a(d dVar, c cVar) {
        if (VERSION.SDK_INT < 17 || !j.a()) {
            return false;
        }
        Provider a2 = a();
        if (a2 == null) {
            return false;
        }
        boolean z = false;
        for (String service : a) {
            Service service2 = a2.getService("SSLContext", service);
            if (service2 != null && !(service2 instanceof i)) {
                i a3 = a(service2, dVar, cVar);
                if (a3 != null) {
                    z |= a3.b();
                }
            }
        }
        return z;
    }

    private boolean b() {
        Provider provider = getProvider();
        if (provider == null) {
            return false;
        }
        try {
            Method declaredMethod = Provider.class.getDeclaredMethod("putService", new Class[]{Service.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(provider, new Object[]{this});
            String str = "SSLContext.DummySSLAlgorithm";
            provider.put(str, getClassName());
            StringBuilder sb = new StringBuilder();
            sb.append(getType());
            sb.append(".");
            sb.append(getAlgorithm());
            provider.remove(sb.toString());
            provider.remove(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public final Object newInstance(Object obj) {
        Object newInstance = super.newInstance(obj);
        try {
            if (!(newInstance instanceof SSLContextSpi)) {
                return newInstance;
            }
            j a2 = j.a((SSLContextSpi) newInstance, this.b, this.c);
            return a2 != null ? a2 : newInstance;
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
            return newInstance;
        }
    }
}
