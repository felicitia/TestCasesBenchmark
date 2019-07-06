package com.etsy.android.ui.user.auth;

import android.os.Bundle;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;

/* compiled from: SignInActivityCallback.kt */
public interface i {
    void onFetchedUser();

    void showLinkAccountSignInScreen(Bundle bundle);

    void showLinkRegisterScreen(Bundle bundle);

    void showTwoFactor(Bundle bundle, String str, String str2, SignInFlow signInFlow);
}
