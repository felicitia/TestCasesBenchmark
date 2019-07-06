package com.crittercism.internal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

@SuppressLint({"NewApi"})
public abstract class br {
    private static final int[] g = {32, 544, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 8224};
    boolean a = false;
    /* access modifiers changed from: private */
    public int b = 0;
    /* access modifiers changed from: private */
    public boolean c = false;
    /* access modifiers changed from: private */
    public boolean d = false;
    private Application e = null;
    private a f = null;

    class a implements ActivityLifecycleCallbacks {
        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public final void onActivityDestroyed(Activity activity) {
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public final void onActivityStarted(Activity activity) {
        }

        private a() {
        }

        /* synthetic */ a(br brVar, byte b) {
            this();
        }

        public final void onActivityResumed(Activity activity) {
            if (activity != null) {
                try {
                    new Date();
                    br.this.b(activity);
                    br.this.g();
                    if (!br.this.d) {
                        br.this.d = true;
                    }
                    if (br.this.c) {
                        cm.d("not a foreground. rotation event.");
                        br.this.c = false;
                    } else {
                        if (br.this.b == 0) {
                            br.this.b();
                        }
                        br.this.a(activity);
                    }
                    br.this.b = br.this.b + 1;
                } catch (ThreadDeath e) {
                    throw e;
                } catch (Throwable th) {
                    cm.b(th);
                }
            }
        }

        public final void onActivityPaused(Activity activity) {
            if (activity != null) {
                try {
                    br.this.c(activity);
                    if (!br.this.d) {
                        br.this.b;
                        br.this.b = 1;
                        br.this.d = true;
                    }
                    br.this.g();
                } catch (ThreadDeath e) {
                    throw e;
                } catch (Throwable th) {
                    cm.b(th);
                }
            }
        }

        public final void onActivityStopped(Activity activity) {
            if (activity != null) {
                try {
                    br.this.e();
                    if (!br.this.d) {
                        br.this.b;
                        br.this.b = 1;
                        br.this.d = true;
                    }
                    br.this.b = br.this.b - 1;
                    if (activity.isChangingConfigurations()) {
                        cm.d("not a background. rotation event.");
                        br.this.c = true;
                    } else if (br.this.b == 0) {
                        br.this.c();
                    }
                } catch (ThreadDeath e) {
                    throw e;
                } catch (Throwable th) {
                    cm.b(th);
                }
            }
        }
    }

    public void a(Activity activity) {
    }

    public void b() {
    }

    public void b(Activity activity) {
    }

    public void c() {
    }

    public void c(Activity activity) {
    }

    public void d() {
    }

    public void e() {
    }

    public br(Application application) {
        if (VERSION.SDK_INT < 14) {
            throw new IllegalStateException("App lifecycle monitoring is only supported on API 14 and later");
        }
        this.e = application;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        if (this.f == null) {
            this.f = new a(this, 0);
            this.e.registerActivityLifecycleCallbacks(this.f);
            if (a((Context) this.e)) {
                g();
            }
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        if (!this.a) {
            this.a = true;
            d();
        }
    }

    private static boolean a(Context context) {
        if (context instanceof Activity) {
            return true;
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            String str = "currentActivityThread";
            Method method = cls.getMethod(str, new Class[0]);
            method.setAccessible(true);
            if (!cls.isAssignableFrom(method.getReturnType())) {
                throw new NoSuchMethodException(str);
            }
            Object invoke = method.invoke(null, new Object[0]);
            String str2 = "mNumVisibleActivities";
            Class cls2 = Integer.TYPE;
            Field declaredField = cls.getDeclaredField(str2);
            declaredField.setAccessible(true);
            if (cls2.isAssignableFrom(declaredField.getType())) {
                return ((Integer) declaredField.get(invoke)).intValue() > 0;
            }
            throw new NoSuchFieldException(str2);
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable th) {
            cm.a("Unable to detect if app has finished launching", th);
            return false;
        }
    }

    public static long f() {
        long[] jArr = new long[1];
        int myPid = Process.myPid();
        StringBuilder sb = new StringBuilder("/proc/");
        sb.append(myPid);
        sb.append("/stat");
        String sb2 = sb.toString();
        Boolean valueOf = Boolean.valueOf(false);
        Throwable th = null;
        try {
            valueOf = (Boolean) Process.class.getDeclaredMethod("readProcFile", new Class[]{String.class, int[].class, String[].class, long[].class, float[].class}).invoke(null, new Object[]{sb2, g, 0, jArr, 0});
        } catch (NoSuchMethodException e2) {
            th = e2;
        } catch (IllegalAccessException e3) {
            th = e3;
        } catch (InvocationTargetException e4) {
            th = e4;
        }
        if (!valueOf.booleanValue()) {
            throw new IOException("Unable to determine process start time", th);
        }
        return System.currentTimeMillis() - (SystemClock.elapsedRealtime() - (jArr[0] * 10));
    }
}
