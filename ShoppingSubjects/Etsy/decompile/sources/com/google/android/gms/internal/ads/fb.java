package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

@bu
public final class fb implements a {
    private final zzaic a;

    public fb(zzaic zzaic) {
        this.a = zzaic;
    }

    public final void a(Bundle bundle) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdMetadataChanged.");
        try {
            this.a.zzc(bundle);
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void a(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onInitializationSucceeded.");
        try {
            this.a.zzq(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void a(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdFailedToLoad.");
        try {
            this.a.zzd(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter), i);
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void a(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, com.google.android.gms.ads.reward.a aVar) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onRewarded.");
        if (aVar != null) {
            try {
                this.a.zza(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter), new zzaig(aVar));
            } catch (RemoteException e) {
                ka.d("#007 Could not call remote method.", e);
            }
        } else {
            this.a.zza(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter), new zzaig("", 1));
        }
    }

    public final void b(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdLoaded.");
        try {
            this.a.zzr(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void c(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdOpened.");
        try {
            this.a.zzs(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void d(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onVideoStarted.");
        try {
            this.a.zzt(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void e(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdClosed.");
        try {
            this.a.zzu(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void f(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onAdLeftApplication.");
        try {
            this.a.zzw(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void g(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        ka.b("Adapter called onVideoCompleted.");
        try {
            this.a.zzx(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }
}
