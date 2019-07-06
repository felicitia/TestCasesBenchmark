package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

@bu
public final class o {
    private static final Object a = new Object();
    @VisibleForTesting
    private static boolean b = false;
    @VisibleForTesting
    private static boolean c = false;
    @VisibleForTesting
    private zzatn d;

    @VisibleForTesting
    private final void c(Context context) {
        synchronized (a) {
            if (((Boolean) ajh.f().a(akl.dg)).booleanValue() && !c) {
                try {
                    c = true;
                    this.d = zzato.zzab(DynamiteModule.a(context, DynamiteModule.a, ModuleDescriptor.MODULE_ID).a("com.google.android.gms.ads.omid.DynamiteOmid"));
                } catch (LoadingException e) {
                    ka.d("#007 Could not call remote method.", e);
                }
            }
        }
    }

    @Nullable
    public final IObjectWrapper a(String str, WebView webView, String str2, String str3, String str4) {
        synchronized (a) {
            if (((Boolean) ajh.f().a(akl.dg)).booleanValue()) {
                if (b) {
                    try {
                        return this.d.zza(str, ObjectWrapper.wrap(webView), str2, str3, str4);
                    } catch (RemoteException | NullPointerException e) {
                        ka.d("#007 Could not call remote method.", e);
                        return null;
                    }
                }
            }
            return null;
        }
    }

    public final void a(IObjectWrapper iObjectWrapper) {
        synchronized (a) {
            if (((Boolean) ajh.f().a(akl.dg)).booleanValue()) {
                if (b) {
                    try {
                        this.d.zzm(iObjectWrapper);
                    } catch (RemoteException | NullPointerException e) {
                        ka.d("#007 Could not call remote method.", e);
                    }
                }
            }
        }
    }

    public final void a(IObjectWrapper iObjectWrapper, View view) {
        synchronized (a) {
            if (((Boolean) ajh.f().a(akl.dg)).booleanValue()) {
                if (b) {
                    try {
                        this.d.zza(iObjectWrapper, ObjectWrapper.wrap(view));
                    } catch (RemoteException | NullPointerException e) {
                        ka.d("#007 Could not call remote method.", e);
                    }
                }
            }
        }
    }

    public final boolean a(Context context) {
        synchronized (a) {
            if (!((Boolean) ajh.f().a(akl.dg)).booleanValue()) {
                return false;
            }
            if (b) {
                return true;
            }
            try {
                c(context);
                boolean zzy = this.d.zzy(ObjectWrapper.wrap(context));
                b = zzy;
                return zzy;
            } catch (RemoteException | NullPointerException e) {
                ka.d("#007 Could not call remote method.", e);
                return false;
            }
        }
    }

    @Nullable
    public final String b(Context context) {
        if (!((Boolean) ajh.f().a(akl.dg)).booleanValue()) {
            return null;
        }
        try {
            c(context);
            String str = "a.";
            String valueOf = String.valueOf(this.d.getVersion());
            return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        } catch (RemoteException | NullPointerException e) {
            ka.d("#007 Could not call remote method.", e);
            return null;
        }
    }

    public final void b(IObjectWrapper iObjectWrapper) {
        synchronized (a) {
            if (((Boolean) ajh.f().a(akl.dg)).booleanValue()) {
                if (b) {
                    try {
                        this.d.zzn(iObjectWrapper);
                    } catch (RemoteException | NullPointerException e) {
                        ka.d("#007 Could not call remote method.", e);
                    }
                }
            }
        }
    }
}
