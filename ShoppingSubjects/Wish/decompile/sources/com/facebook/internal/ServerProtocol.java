package com.facebook.internal;

import com.facebook.FacebookSdk;
import java.util.Collection;

public final class ServerProtocol {
    private static final String TAG = "com.facebook.internal.ServerProtocol";
    public static final Collection<String> errorsProxyAuthDisabled = Utility.unmodifiableCollection("service_disabled", "AndroidAuthKillSwitchException");
    public static final Collection<String> errorsUserCanceled = Utility.unmodifiableCollection("access_denied", "OAuthAccessDeniedException");

    public static final String getAPIVersion() {
        return "v2.7";
    }

    public static final String getDialogAuthority() {
        return String.format("m.%s", new Object[]{FacebookSdk.getFacebookDomain()});
    }

    public static final String getGraphUrlBase() {
        return String.format("https://graph.%s", new Object[]{FacebookSdk.getFacebookDomain()});
    }

    public static final String getGraphVideoUrlBase() {
        return String.format("https://graph-video.%s", new Object[]{FacebookSdk.getFacebookDomain()});
    }
}
