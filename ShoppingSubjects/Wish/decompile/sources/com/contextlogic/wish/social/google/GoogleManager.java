package com.contextlogic.wish.social.google;

import com.contextlogic.wish.application.WishApplication;
import com.google.android.gms.common.GoogleApiAvailability;

public class GoogleManager {
    private static GoogleManager sInstance = new GoogleManager();
    private GoogleLoginSession mGoogleLoginSession = new GoogleLoginSession();
    private boolean mIsPlayServicesAvailable;

    public enum ProfileImageSize {
        SMALL,
        MEDIUM,
        LARGE
    }

    private GoogleManager() {
        this.mIsPlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(WishApplication.getInstance()) == 0;
    }

    public static GoogleManager getInstance() {
        return sInstance;
    }

    public GoogleLoginSession getLoginSession() {
        return this.mGoogleLoginSession;
    }

    public boolean isPlayServicesAvailable() {
        return this.mIsPlayServicesAvailable;
    }

    public static String getProfileImageUrlString(String str, ProfileImageSize profileImageSize) {
        if (str == null) {
            str = "";
        }
        int indexOf = str.indexOf(63);
        int i = 0;
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
        }
        switch (profileImageSize) {
            case SMALL:
                i = 50;
                break;
            case MEDIUM:
                i = 100;
                break;
            case LARGE:
                i = 200;
                break;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("?sz=");
        sb.append(i);
        return sb.toString();
    }
}
