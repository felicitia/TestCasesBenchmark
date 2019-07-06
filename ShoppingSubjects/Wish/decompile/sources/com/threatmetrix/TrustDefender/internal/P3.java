package com.threatmetrix.TrustDefender.internal;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import com.threatmetrix.TrustDefender.StrongAuth.AuthenticationStatus;
import com.threatmetrix.TrustDefender.StrongAuth.StrongAuthCallback;

class P3 {

    /* renamed from: int reason: not valid java name */
    private static final String f488int = TL.m331if(P3.class);

    P3() {
    }

    @TargetApi(21)
    /* renamed from: for reason: not valid java name */
    static AuthenticationStatus m274for(Context context, String str, String str2, StrongAuthCallback strongAuthCallback) {
        if (strongAuthCallback == null) {
            return AuthenticationStatus.THM_STRONG_AUTH_NOT_POSSIBLE;
        }
        Object systemService = context.getSystemService("keyguard");
        if (systemService == null || !(systemService instanceof KeyguardManager)) {
            return AuthenticationStatus.THM_STRONG_AUTH_NOT_POSSIBLE;
        }
        Intent createConfirmDeviceCredentialIntent = ((KeyguardManager) systemService).createConfirmDeviceCredentialIntent(str, str2);
        if (createConfirmDeviceCredentialIntent == null) {
            return AuthenticationStatus.THM_STRONG_AUTH_NOT_POSSIBLE;
        }
        int callIntent = strongAuthCallback.callIntent(createConfirmDeviceCredentialIntent);
        if (callIntent == -1) {
            return AuthenticationStatus.THM_STRONG_AUTH_OK;
        }
        if (callIntent == 0) {
            return AuthenticationStatus.THM_STRONG_AUTH_CANCELLED;
        }
        return AuthenticationStatus.THM_STRONG_AUTH_FAILED;
    }
}
