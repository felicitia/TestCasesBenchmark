package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.firebase.a.c;
import com.google.firebase.components.h;
import com.google.firebase.components.i;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class a {
    static final Map<String, a> a = new ArrayMap();
    private static final List<String> b = Arrays.asList(new String[]{"com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId"});
    private static final List<String> c = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
    private static final List<String> d = Arrays.asList(new String[]{"com.google.android.gms.measurement.AppMeasurement"});
    private static final List<String> e = Arrays.asList(new String[0]);
    private static final Set<String> f = Collections.emptySet();
    /* access modifiers changed from: private */
    public static final Object g = new Object();
    private static final Executor h = new c(0);
    private final Context i;
    private final String j;
    private final c k;
    private final i l;
    private final SharedPreferences m;
    private final c n;
    private final AtomicBoolean o = new AtomicBoolean(false);
    private final AtomicBoolean p = new AtomicBoolean();
    private final AtomicBoolean q;
    private final List<Object> r = new CopyOnWriteArrayList();
    private final List<C0149a> s = new CopyOnWriteArrayList();
    private final List<Object> t = new CopyOnWriteArrayList();
    private b u;

    @KeepForSdk
    /* renamed from: com.google.firebase.a$a reason: collision with other inner class name */
    public interface C0149a {
        void a(boolean z);
    }

    @KeepForSdk
    public interface b {
    }

    static class c implements Executor {
        private static final Handler a = new Handler(Looper.getMainLooper());

        private c() {
        }

        /* synthetic */ c(byte b) {
            this();
        }

        public final void execute(@NonNull Runnable runnable) {
            a.post(runnable);
        }
    }

    @TargetApi(24)
    static class d extends BroadcastReceiver {
        private static AtomicReference<d> a = new AtomicReference<>();
        private final Context b;

        private d(Context context) {
            this.b = context;
        }

        public final void onReceive(Context context, Intent intent) {
            synchronized (a.g) {
                for (a a2 : a.a.values()) {
                    a2.i();
                }
            }
            this.b.unregisterReceiver(this);
        }

        static /* synthetic */ void a(Context context) {
            if (a.get() == null) {
                d dVar = new d(context);
                if (a.compareAndSet(null, dVar)) {
                    context.registerReceiver(dVar, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }
    }

    @NonNull
    public Context a() {
        h();
        return this.i;
    }

    @NonNull
    public String b() {
        h();
        return this.j;
    }

    @NonNull
    public c c() {
        h();
        return this.k;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        return this.j.equals(((a) obj).b());
    }

    public int hashCode() {
        return this.j.hashCode();
    }

    public String toString() {
        return Objects.toStringHelper(this).add(ResponseConstants.NAME, this.j).add(ResponseConstants.OPTIONS, this.k).toString();
    }

    @Nullable
    public static a d() {
        a aVar;
        synchronized (g) {
            aVar = (a) a.get("[DEFAULT]");
            if (aVar == null) {
                StringBuilder sb = new StringBuilder("Default FirebaseApp is not initialized in this process ");
                sb.append(ProcessUtils.getMyProcessName());
                sb.append(". Make sure to call FirebaseApp.initializeApp(Context) first.");
                throw new IllegalStateException(sb.toString());
            }
        }
        return aVar;
    }

    @Nullable
    public static a a(Context context) {
        synchronized (g) {
            if (a.containsKey("[DEFAULT]")) {
                a d2 = d();
                return d2;
            }
            c a2 = c.a(context);
            if (a2 == null) {
                return null;
            }
            a a3 = a(context, a2);
            return a3;
        }
    }

    public static a a(Context context, c cVar) {
        return a(context, cVar, "[DEFAULT]");
    }

    public static void a(boolean z) {
        synchronized (g) {
            Iterator it = new ArrayList(a.values()).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar.o.get()) {
                    aVar.b(z);
                }
            }
        }
    }

    public <T> T a(Class<T> cls) {
        h();
        return this.l.a((Class) cls);
    }

    protected a(Context context, String str, c cVar) {
        this.i = (Context) Preconditions.checkNotNull(context);
        this.j = Preconditions.checkNotEmpty(str);
        this.k = (c) Preconditions.checkNotNull(cVar);
        this.u = new com.google.firebase.internal.a();
        this.m = context.getSharedPreferences("com.google.firebase.common.prefs", 0);
        this.q = new AtomicBoolean(g());
        List a2 = new h(context).a();
        this.l = new i(h, a2, com.google.firebase.components.a.a(context, Context.class, new Class[0]), com.google.firebase.components.a.a(this, a.class, new Class[0]), com.google.firebase.components.a.a(cVar, c.class, new Class[0]));
        this.n = (c) this.l.a(c.class);
    }

    private boolean g() {
        if (this.m.contains("firebase_automatic_data_collection_enabled")) {
            return this.m.getBoolean("firebase_automatic_data_collection_enabled", true);
        }
        try {
            PackageManager packageManager = this.i.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(this.i.getPackageName(), 128);
                if (!(applicationInfo == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey("firebase_automatic_data_collection_enabled"))) {
                    return applicationInfo.metaData.getBoolean("firebase_automatic_data_collection_enabled");
                }
            }
        } catch (NameNotFoundException unused) {
        }
        return true;
    }

    private void h() {
        Preconditions.checkState(!this.p.get(), "FirebaseApp was deleted");
    }

    @VisibleForTesting
    public boolean e() {
        return "[DEFAULT]".equals(b());
    }

    private void b(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        for (C0149a a2 : this.s) {
            a2.a(z);
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        boolean isDeviceProtectedStorage = ContextCompat.isDeviceProtectedStorage(this.i);
        if (isDeviceProtectedStorage) {
            d.a(this.i);
        } else {
            this.l.a(e());
        }
        a(a.class, this, b, isDeviceProtectedStorage);
        if (e()) {
            a(a.class, this, c, isDeviceProtectedStorage);
            a(Context.class, this.i, d, isDeviceProtectedStorage);
        }
    }

    private static <T> void a(Class<T> cls, T t2, Iterable<String> iterable, boolean z) {
        for (String str : iterable) {
            if (z) {
                try {
                    if (e.contains(str)) {
                    }
                } catch (ClassNotFoundException unused) {
                    if (f.contains(str)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        sb.append(" is missing, but is required. Check if it has been removed by Proguard.");
                        throw new IllegalStateException(sb.toString());
                    }
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str);
                    sb2.append(" is not linked. Skipping initialization.");
                    Log.d("FirebaseApp", sb2.toString());
                } catch (NoSuchMethodException unused2) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str);
                    sb3.append("#getInstance has been removed by Proguard. Add keep rule to prevent it.");
                    throw new IllegalStateException(sb3.toString());
                } catch (InvocationTargetException e2) {
                    Log.wtf("FirebaseApp", "Firebase API initialization failure.", e2);
                } catch (IllegalAccessException e3) {
                    StringBuilder sb4 = new StringBuilder("Failed to initialize ");
                    sb4.append(str);
                    Log.wtf("FirebaseApp", sb4.toString(), e3);
                }
            }
            Method method = Class.forName(str).getMethod("getInstance", new Class[]{cls});
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                method.invoke(null, new Object[]{t2});
            }
        }
    }

    public static a a(Context context, c cVar, String str) {
        a aVar;
        if (PlatformVersion.isAtLeastIceCreamSandwich() && (context.getApplicationContext() instanceof Application)) {
            BackgroundDetector.initialize((Application) context.getApplicationContext());
            BackgroundDetector.getInstance().addListener(new BackgroundStateChangeListener() {
                public final void onBackgroundStateChanged(boolean z) {
                    a.a(z);
                }
            });
        }
        String trim = str.trim();
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        synchronized (g) {
            boolean z = !a.containsKey(trim);
            StringBuilder sb = new StringBuilder("FirebaseApp name ");
            sb.append(trim);
            sb.append(" already exists!");
            Preconditions.checkState(z, sb.toString());
            Preconditions.checkNotNull(context, "Application context cannot be null.");
            aVar = new a(context, trim, cVar);
            a.put(trim, aVar);
        }
        aVar.i();
        return aVar;
    }
}
