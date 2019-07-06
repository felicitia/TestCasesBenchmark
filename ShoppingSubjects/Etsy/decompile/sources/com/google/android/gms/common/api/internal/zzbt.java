package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.f;
import com.google.android.gms.tasks.g;
import java.util.concurrent.CancellationException;

public class zzbt extends zzk {
    private g<Void> zzln = new g<>();

    private zzbt(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.mLifecycleFragment.addCallback("GmsAvailabilityHelper", this);
    }

    public static zzbt zzd(Activity activity) {
        LifecycleFragment fragment = getFragment(activity);
        zzbt zzbt = (zzbt) fragment.getCallbackOrNull("GmsAvailabilityHelper", zzbt.class);
        if (zzbt == null) {
            return new zzbt(fragment);
        }
        if (zzbt.zzln.a().a()) {
            zzbt.zzln = new g<>();
        }
        return zzbt;
    }

    public final f<Void> getTask() {
        return this.zzln.a();
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzln.b((Exception) new CancellationException("Host activity was destroyed before Google Play services could be made available."));
    }

    /* access modifiers changed from: protected */
    public final void zza(ConnectionResult connectionResult, int i) {
        this.zzln.a((Exception) ApiExceptionUtil.fromConnectionResult(connectionResult));
    }

    /* access modifiers changed from: protected */
    public final void zzr() {
        int isGooglePlayServicesAvailable = this.zzdg.isGooglePlayServicesAvailable(this.mLifecycleFragment.getLifecycleActivity());
        if (isGooglePlayServicesAvailable == 0) {
            this.zzln.a(null);
            return;
        }
        if (!this.zzln.a().a()) {
            zzb(new ConnectionResult(isGooglePlayServicesAvailable, null), 0);
        }
    }
}
