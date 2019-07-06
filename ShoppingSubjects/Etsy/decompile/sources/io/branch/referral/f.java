package io.branch.referral;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.customtabs.CustomTabsService;
import android.text.TextUtils;
import android.util.Log;
import io.branch.referral.Defines.Jsonkey;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/* compiled from: BranchStrongMatchHelper */
class f {
    private static f h = null;
    private static int j = 750;
    Object a = null;
    boolean b = false;
    Class<?> c;
    Class<?> d;
    Class<?> e;
    Class<?> f;
    Class<?> g;
    private final Handler i;
    private boolean k = true;

    /* compiled from: BranchStrongMatchHelper */
    private abstract class a implements ServiceConnection {
        public abstract void a(ComponentName componentName, Object obj);

        public a() {
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                Constructor declaredConstructor = f.this.c.getDeclaredConstructor(new Class[]{f.this.g, ComponentName.class});
                declaredConstructor.setAccessible(true);
                a(componentName, declaredConstructor.newInstance(new Object[]{Class.forName("android.support.customtabs.ICustomTabsService$Stub").getMethod("asInterface", new Class[]{IBinder.class}).invoke(null, new Object[]{iBinder}), componentName}));
            } catch (Throwable unused) {
                a(null, null);
            }
        }
    }

    /* compiled from: BranchStrongMatchHelper */
    interface b {
        void a();
    }

    private f() {
        try {
            this.c = Class.forName("android.support.customtabs.CustomTabsClient");
            this.d = Class.forName("android.support.customtabs.CustomTabsServiceConnection");
            this.e = Class.forName("android.support.customtabs.CustomTabsCallback");
            this.f = Class.forName("android.support.customtabs.CustomTabsSession");
            this.g = Class.forName("android.support.customtabs.ICustomTabsService");
        } catch (Throwable unused) {
            this.k = false;
        }
        this.i = new Handler();
    }

    public static f a() {
        if (h == null) {
            h = new f();
        }
        return h;
    }

    public void a(Context context, String str, k kVar, m mVar, aa aaVar, b bVar) {
        final b bVar2 = bVar;
        this.b = false;
        if (System.currentTimeMillis() - mVar.E() < 2592000000L) {
            a(bVar2, this.b);
        } else if (!this.k) {
            a(bVar2, this.b);
        } else {
            try {
                if (kVar.d() != null) {
                    final Uri a2 = a(str, kVar, mVar, aaVar, context);
                    if (a2 != null) {
                        this.i.postDelayed(new Runnable() {
                            public void run() {
                                f.this.a(bVar2, f.this.b);
                            }
                        }, 500);
                        this.c.getMethod("bindCustomTabsService", new Class[]{Context.class, String.class, this.d});
                        Method method = this.c.getMethod("warmup", new Class[]{Long.TYPE});
                        Method method2 = this.c.getMethod("newSession", new Class[]{this.e});
                        Method method3 = this.f.getMethod("mayLaunchUrl", new Class[]{Uri.class, Bundle.class, List.class});
                        Intent intent = new Intent(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
                        intent.setPackage("com.android.chrome");
                        final Method method4 = method;
                        final Method method5 = method2;
                        final Method method6 = method3;
                        final m mVar2 = mVar;
                        final b bVar3 = bVar2;
                        AnonymousClass2 r0 = new a() {
                            public void a(ComponentName componentName, Object obj) {
                                f.this.a = f.this.c.cast(obj);
                                if (f.this.a != null) {
                                    try {
                                        method4.invoke(f.this.a, new Object[]{Integer.valueOf(0)});
                                        Object invoke = method5.invoke(f.this.a, new Object[]{null});
                                        if (invoke != null) {
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("Strong match request ");
                                            sb.append(a2);
                                            m.b("BranchSDK", sb.toString());
                                            method6.invoke(invoke, new Object[]{a2, null, null});
                                            mVar2.a(System.currentTimeMillis());
                                            f.this.b = true;
                                        }
                                    } catch (Throwable unused) {
                                        f.this.a = null;
                                        f.this.a(bVar3, f.this.b);
                                    }
                                }
                            }

                            public void onServiceDisconnected(ComponentName componentName) {
                                f.this.a = null;
                                f.this.a(bVar3, f.this.b);
                            }
                        };
                        context.bindService(intent, r0, 33);
                        return;
                    }
                    a(bVar2, this.b);
                    return;
                }
                a(bVar2, this.b);
                Log.d("BranchSDK", "Cannot use cookie-based matching since device id is not available");
            } catch (Throwable unused) {
                a(bVar2, this.b);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(final b bVar, boolean z) {
        if (bVar == null) {
            return;
        }
        if (z) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    bVar.a();
                }
            }, (long) j);
        } else {
            bVar.a();
        }
    }

    private Uri a(String str, k kVar, m mVar, aa aaVar, Context context) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("https://");
        sb.append(str);
        sb.append("/_strong_match?os=");
        sb.append(kVar.e());
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        sb3.append("&");
        sb3.append(Jsonkey.HardwareID.getKey());
        sb3.append("=");
        sb3.append(kVar.d());
        String sb4 = sb3.toString();
        String key = (kVar.c() ? Jsonkey.HardwareIDTypeVendor : Jsonkey.HardwareIDTypeRandom).getKey();
        StringBuilder sb5 = new StringBuilder();
        sb5.append(sb4);
        sb5.append("&");
        sb5.append(Jsonkey.HardwareIDType.getKey());
        sb5.append("=");
        sb5.append(key);
        String sb6 = sb5.toString();
        if (aa.a != null && !h.a(context)) {
            StringBuilder sb7 = new StringBuilder();
            sb7.append(sb6);
            sb7.append("&");
            sb7.append(Jsonkey.GoogleAdvertisingID.getKey());
            sb7.append("=");
            sb7.append(aa.a);
            sb6 = sb7.toString();
        }
        if (!mVar.g().equals("bnc_no_value")) {
            StringBuilder sb8 = new StringBuilder();
            sb8.append(sb6);
            sb8.append("&");
            sb8.append(Jsonkey.DeviceFingerprintID.getKey());
            sb8.append("=");
            sb8.append(mVar.g());
            sb6 = sb8.toString();
        }
        if (!kVar.b().equals("bnc_no_value")) {
            StringBuilder sb9 = new StringBuilder();
            sb9.append(sb6);
            sb9.append("&");
            sb9.append(Jsonkey.AppVersion.getKey());
            sb9.append("=");
            sb9.append(kVar.b());
            sb6 = sb9.toString();
        }
        if (!mVar.f().equals("bnc_no_value")) {
            StringBuilder sb10 = new StringBuilder();
            sb10.append(sb6);
            sb10.append("&");
            sb10.append(Jsonkey.BranchKey.getKey());
            sb10.append("=");
            sb10.append(mVar.f());
            sb6 = sb10.toString();
        }
        StringBuilder sb11 = new StringBuilder();
        sb11.append(sb6);
        sb11.append("&sdk=android2.18.1");
        return Uri.parse(sb11.toString());
    }
}
