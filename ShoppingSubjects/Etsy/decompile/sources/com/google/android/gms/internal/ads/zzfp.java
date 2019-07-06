package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;

@bu
@TargetApi(14)
public final class zzfp implements ActivityLifecycleCallbacks, OnAttachStateChangeListener, OnGlobalLayoutListener, OnScrollChangedListener {
    private static final long zzagc = ((Long) ajh.f().a(akl.bn)).longValue();
    private je zzadz = new je(zzagc);
    private final Context zzaeo;
    private final WindowManager zzaeu;
    private final PowerManager zzaev;
    private final KeyguardManager zzaew;
    private boolean zzafd = false;
    @Nullable
    @VisibleForTesting
    private BroadcastReceiver zzafe;
    private final Rect zzafh;
    private Application zzagd;
    private WeakReference<ViewTreeObserver> zzage;
    private WeakReference<View> zzagf;
    private afo zzagg;
    private int zzagh = -1;
    private final HashSet<afn> zzagi = new HashSet<>();
    private final DisplayMetrics zzagj;

    public zzfp(Context context, View view) {
        this.zzaeo = context.getApplicationContext();
        this.zzaeu = (WindowManager) context.getSystemService("window");
        this.zzaev = (PowerManager) this.zzaeo.getSystemService("power");
        this.zzaew = (KeyguardManager) context.getSystemService("keyguard");
        if (this.zzaeo instanceof Application) {
            this.zzagd = (Application) this.zzaeo;
            this.zzagg = new afo((Application) this.zzaeo, this);
        }
        this.zzagj = context.getResources().getDisplayMetrics();
        this.zzafh = new Rect();
        this.zzafh.right = this.zzaeu.getDefaultDisplay().getWidth();
        this.zzafh.bottom = this.zzaeu.getDefaultDisplay().getHeight();
        View view2 = this.zzagf != null ? (View) this.zzagf.get() : null;
        if (view2 != null) {
            view2.removeOnAttachStateChangeListener(this);
            zzf(view2);
        }
        this.zzagf = new WeakReference<>(view);
        if (view != null) {
            if (ao.g().a(view)) {
                zze(view);
            }
            view.addOnAttachStateChangeListener(this);
        }
    }

    private final Rect zza(Rect rect) {
        return new Rect(zzn(rect.left), zzn(rect.top), zzn(rect.right), zzn(rect.bottom));
    }

    private final void zza(Activity activity, int i) {
        if (this.zzagf != null) {
            Window window = activity.getWindow();
            if (window != null) {
                View peekDecorView = window.peekDecorView();
                View view = (View) this.zzagf.get();
                if (!(view == null || peekDecorView == null || view.getRootView() != peekDecorView.getRootView())) {
                    this.zzagh = i;
                }
            }
        }
    }

    private final void zzao() {
        ao.e();
        hd.a.post(new afk(this));
    }

    private final void zze(View view) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zzage = new WeakReference<>(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener(this);
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        if (this.zzafe == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zzafe = new afl(this);
            ao.E().a(this.zzaeo, this.zzafe, intentFilter);
        }
        if (this.zzagd != null) {
            try {
                this.zzagd.registerActivityLifecycleCallbacks(this.zzagg);
            } catch (Exception e) {
                gv.b("Error registering activity lifecycle callbacks.", e);
            }
        }
    }

    private final void zzf(View view) {
        try {
            if (this.zzage != null) {
                ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzage.get();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnScrollChangedListener(this);
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                this.zzage = null;
            }
        } catch (Exception e) {
            gv.b("Error while unregistering listeners from the last ViewTreeObserver.", e);
        }
        try {
            ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
            if (viewTreeObserver2.isAlive()) {
                viewTreeObserver2.removeOnScrollChangedListener(this);
                viewTreeObserver2.removeGlobalOnLayoutListener(this);
            }
        } catch (Exception e2) {
            gv.b("Error while unregistering listeners from the ViewTreeObserver.", e2);
        }
        if (this.zzafe != null) {
            try {
                ao.E().a(this.zzaeo, this.zzafe);
            } catch (IllegalStateException e3) {
                gv.b("Failed trying to unregister the receiver", e3);
            } catch (Exception e4) {
                ao.i().a((Throwable) e4, "ActiveViewUnit.stopScreenStatusMonitoring");
            }
            this.zzafe = null;
        }
        if (this.zzagd != null) {
            try {
                this.zzagd.unregisterActivityLifecycleCallbacks(this.zzagg);
            } catch (Exception e5) {
                gv.b("Error registering activity lifecycle callbacks.", e5);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zzm(int i) {
        boolean z;
        boolean z2;
        int i2 = i;
        if (this.zzagi.size() != 0 && this.zzagf != null) {
            View view = (View) this.zzagf.get();
            boolean z3 = false;
            boolean z4 = i2 == 1;
            boolean z5 = view == null;
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            Rect rect3 = new Rect();
            Rect rect4 = new Rect();
            int[] iArr = new int[2];
            int[] iArr2 = new int[2];
            if (view != null) {
                boolean globalVisibleRect = view.getGlobalVisibleRect(rect2);
                boolean localVisibleRect = view.getLocalVisibleRect(rect3);
                view.getHitRect(rect4);
                try {
                    view.getLocationOnScreen(iArr);
                    view.getLocationInWindow(iArr2);
                } catch (Exception e) {
                    gv.b("Failure getting view location.", e);
                }
                rect.left = iArr[0];
                rect.top = iArr[1];
                rect.right = rect.left + view.getWidth();
                rect.bottom = rect.top + view.getHeight();
                z2 = globalVisibleRect;
                z = localVisibleRect;
            } else {
                z2 = false;
                z = false;
            }
            int windowVisibility = view != null ? view.getWindowVisibility() : 8;
            if (this.zzagh != -1) {
                windowVisibility = this.zzagh;
            }
            boolean z6 = !z5 && ao.e().a(view, this.zzaev, this.zzaew) && z2 && z && windowVisibility == 0;
            if (z4 && !this.zzadz.a() && z6 == this.zzafd) {
                return;
            }
            if (z6 || this.zzafd || i2 != 1) {
                long elapsedRealtime = ao.l().elapsedRealtime();
                boolean isScreenOn = this.zzaev.isScreenOn();
                if (view != null) {
                    z3 = ao.g().a(view);
                }
                afm afm = new afm(elapsedRealtime, isScreenOn, z3, view != null ? view.getWindowVisibility() : 8, zza(this.zzafh), zza(rect), zza(rect2), z2, zza(rect3), z, zza(rect4), this.zzagj.density, z6);
                Iterator it = this.zzagi.iterator();
                while (it.hasNext()) {
                    ((afn) it.next()).zza(afm);
                }
                this.zzafd = z6;
            }
        }
    }

    private final int zzn(int i) {
        return (int) (((float) i) / this.zzagj.density);
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(activity, 0);
        zzm(3);
        zzao();
    }

    public final void onActivityDestroyed(Activity activity) {
        zzm(3);
        zzao();
    }

    public final void onActivityPaused(Activity activity) {
        zza(activity, 4);
        zzm(3);
        zzao();
    }

    public final void onActivityResumed(Activity activity) {
        zza(activity, 0);
        zzm(3);
        zzao();
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzm(3);
        zzao();
    }

    public final void onActivityStarted(Activity activity) {
        zza(activity, 0);
        zzm(3);
        zzao();
    }

    public final void onActivityStopped(Activity activity) {
        zzm(3);
        zzao();
    }

    public final void onGlobalLayout() {
        zzm(2);
        zzao();
    }

    public final void onScrollChanged() {
        zzm(1);
    }

    public final void onViewAttachedToWindow(View view) {
        this.zzagh = -1;
        zze(view);
        zzm(3);
    }

    public final void onViewDetachedFromWindow(View view) {
        this.zzagh = -1;
        zzm(3);
        zzao();
        zzf(view);
    }

    public final void zza(afn afn) {
        this.zzagi.add(afn);
        zzm(3);
    }

    public final void zzb(afn afn) {
        this.zzagi.remove(afn);
    }

    public final void zzgm() {
        zzm(4);
    }
}
