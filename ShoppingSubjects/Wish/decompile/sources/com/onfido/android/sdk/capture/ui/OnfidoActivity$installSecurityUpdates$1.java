package com.onfido.android.sdk.capture.ui;

import android.content.Intent;
import com.google.android.gms.security.ProviderInstaller.ProviderInstallListener;
import kotlin.jvm.functions.Function0;

public final class OnfidoActivity$installSecurityUpdates$1 implements ProviderInstallListener {
    final /* synthetic */ Function0 a;
    final /* synthetic */ Function0 b;

    OnfidoActivity$installSecurityUpdates$1(Function0 function0, Function0 function02) {
        this.a = function0;
        this.b = function02;
    }

    public void onProviderInstallFailed(int i, Intent intent) {
        this.a.invoke();
    }

    public void onProviderInstalled() {
        this.b.invoke();
    }
}
