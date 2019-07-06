package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.i;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.c;
import com.google.android.gms.ads.mediation.d;
import com.google.android.gms.ads.mediation.e;
import com.google.android.gms.ads.mediation.f;
import com.google.android.gms.ads.mediation.l;
import com.google.android.gms.common.internal.Preconditions;

@bu
public final class arp implements c, d, e {
    private final zzxt a;
    private f b;
    private l c;
    private com.google.android.gms.ads.formats.f d;

    public arp(zzxt zzxt) {
        this.a = zzxt;
    }

    private static void a(MediationNativeAdapter mediationNativeAdapter, @Nullable l lVar, @Nullable f fVar) {
        if (!(mediationNativeAdapter instanceof AdMobAdapter)) {
            i iVar = new i();
            iVar.a((zzlo) new zzyi());
            if (lVar != null && lVar.k()) {
                lVar.a(iVar);
            }
            if (fVar != null && fVar.h()) {
                fVar.a(iVar);
            }
        }
    }

    public final f a() {
        return this.b;
    }

    public final void a(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdLoaded.");
        try {
            this.a.onAdLoaded();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void a(MediationBannerAdapter mediationBannerAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        StringBuilder sb = new StringBuilder(55);
        sb.append("Adapter called onAdFailedToLoad with error. ");
        sb.append(i);
        ka.b(sb.toString());
        try {
            this.a.onAdFailedToLoad(i);
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void a(MediationBannerAdapter mediationBannerAdapter, String str, String str2) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAppEvent.");
        try {
            this.a.onAppEvent(str, str2);
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void a(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdLoaded.");
        try {
            this.a.onAdLoaded();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void a(MediationInterstitialAdapter mediationInterstitialAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        StringBuilder sb = new StringBuilder(55);
        sb.append("Adapter called onAdFailedToLoad with error ");
        sb.append(i);
        sb.append(".");
        ka.b(sb.toString());
        try {
            this.a.onAdFailedToLoad(i);
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void a(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdOpened.");
        try {
            this.a.onAdOpened();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void a(MediationNativeAdapter mediationNativeAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        StringBuilder sb = new StringBuilder(55);
        sb.append("Adapter called onAdFailedToLoad with error ");
        sb.append(i);
        sb.append(".");
        ka.b(sb.toString());
        try {
            this.a.onAdFailedToLoad(i);
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void a(MediationNativeAdapter mediationNativeAdapter, com.google.android.gms.ads.formats.f fVar) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        String str = "Adapter called onAdLoaded with template id ";
        String valueOf = String.valueOf(fVar.a());
        ka.b(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        this.d = fVar;
        try {
            this.a.onAdLoaded();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void a(MediationNativeAdapter mediationNativeAdapter, com.google.android.gms.ads.formats.f fVar, String str) {
        if (fVar instanceof ame) {
            try {
                this.a.zzb(((ame) fVar).b(), str);
            } catch (RemoteException e) {
                ka.d("#007 Could not call remote method.", e);
            }
        } else {
            ka.e("Unexpected native custom template ad type.");
        }
    }

    public final void a(MediationNativeAdapter mediationNativeAdapter, f fVar) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdLoaded.");
        this.b = fVar;
        this.c = null;
        a(mediationNativeAdapter, this.c, this.b);
        try {
            this.a.onAdLoaded();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void a(MediationNativeAdapter mediationNativeAdapter, l lVar) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdLoaded.");
        this.c = lVar;
        this.b = null;
        a(mediationNativeAdapter, this.c, this.b);
        try {
            this.a.onAdLoaded();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final l b() {
        return this.c;
    }

    public final void b(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdOpened.");
        try {
            this.a.onAdOpened();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void b(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdOpened.");
        try {
            this.a.onAdOpened();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void b(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdClosed.");
        try {
            this.a.onAdClosed();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final com.google.android.gms.ads.formats.f c() {
        return this.d;
    }

    public final void c(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdClosed.");
        try {
            this.a.onAdClosed();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void c(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdClosed.");
        try {
            this.a.onAdClosed();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void c(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdLeftApplication.");
        try {
            this.a.onAdLeftApplication();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void d(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdLeftApplication.");
        try {
            this.a.onAdLeftApplication();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void d(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdLeftApplication.");
        try {
            this.a.onAdLeftApplication();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void d(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        f fVar = this.b;
        l lVar = this.c;
        if (this.d == null) {
            if (fVar == null && lVar == null) {
                ka.d("#007 Could not call remote method.", null);
                return;
            } else if (lVar != null && !lVar.q()) {
                ka.b("Could not call onAdClicked since setOverrideClickHandling is not set to true");
                return;
            } else if (fVar != null && !fVar.b()) {
                ka.b("Could not call onAdClicked since setOverrideClickHandling is not set to true");
                return;
            }
        }
        ka.b("Adapter called onAdClicked.");
        try {
            this.a.onAdClicked();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void e(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdClicked.");
        try {
            this.a.onAdClicked();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void e(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdClicked.");
        try {
            this.a.onAdClicked();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void e(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        f fVar = this.b;
        l lVar = this.c;
        if (this.d == null) {
            if (fVar == null && lVar == null) {
                ka.d("#007 Could not call remote method.", null);
                return;
            } else if (lVar != null && !lVar.p()) {
                ka.b("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
                return;
            } else if (fVar != null && !fVar.a()) {
                ka.b("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
                return;
            }
        }
        ka.b("Adapter called onAdImpression.");
        try {
            this.a.onAdImpression();
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }
}
