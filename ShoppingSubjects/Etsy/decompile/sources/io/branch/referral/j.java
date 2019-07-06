package io.branch.referral;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* compiled from: DeferredAppLinkDataHandler */
class j {

    /* compiled from: DeferredAppLinkDataHandler */
    public interface a {
        void a(String str);
    }

    public static Boolean a(Context context, final a aVar) {
        boolean z = false;
        try {
            Class.forName("com.facebook.f").getMethod("sdkInitialize", new Class[]{Context.class}).invoke(null, new Object[]{context});
            final Class cls = Class.forName("com.facebook.applinks.a");
            Class cls2 = Class.forName("com.facebook.applinks.a$a");
            Method method = cls.getMethod("fetchDeferredAppLinkData", new Class[]{Context.class, String.class, cls2});
            AnonymousClass1 r7 = new InvocationHandler() {
                public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    if (method.getName().equals("onDeferredAppLinkDataFetched") && objArr[0] != null) {
                        Bundle bundle = (Bundle) Bundle.class.cast(cls.getMethod("getArgumentBundle", new Class[0]).invoke(cls.cast(objArr[0]), new Object[0]));
                        String string = bundle != null ? bundle.getString("com.facebook.platform.APPLINK_NATIVE_URL") : null;
                        if (aVar != null) {
                            aVar.a(string);
                        }
                    } else if (aVar != null) {
                        aVar.a(null);
                    }
                    return null;
                }
            };
            Object newProxyInstance = Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, r7);
            String string = context.getString(context.getResources().getIdentifier("facebook_app_id", "string", context.getPackageName()));
            if (!TextUtils.isEmpty(string)) {
                method.invoke(null, new Object[]{context, string, newProxyInstance});
                z = true;
            }
        } catch (Throwable unused) {
        }
        return Boolean.valueOf(z);
    }
}
