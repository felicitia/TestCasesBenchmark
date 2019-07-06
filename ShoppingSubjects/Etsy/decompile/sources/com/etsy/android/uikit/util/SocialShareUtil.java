package com.etsy.android.uikit.util;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.SharedPreferencesUtility;

public class SocialShareUtil {

    public enum ShareType {
        POST_PURCHASE("pp"),
        FAVORITE_ITEM("fi"),
        ADD_TO_LIST("atl"),
        FAVORITE_SHOP("fs"),
        APPRECIATION_PHOTO("ap"),
        SHOP_SHARE("ss"),
        LOCAL_SHARE("lcl"),
        SHOP_HOME("sh");
        
        private final String name;

        private ShareType(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    public static void a(String str, ShareType shareType, String str2) {
        SharedPreferencesUtility.a(EtsyApplication.get(), str, shareType, str2);
    }

    public static String a(String... strArr) {
        return String.format("share_sheet.%s.%s", new Object[]{SharedPreferencesUtility.a((Context) EtsyApplication.get(), "social_invites_trigger", ""), TextUtils.join(".", strArr)});
    }

    public static String a(String str) {
        EtsyApplication etsyApplication = EtsyApplication.get();
        String a = SharedPreferencesUtility.a((Context) etsyApplication, "social_invites_uuid", "");
        long a2 = SharedPreferencesUtility.a((Context) etsyApplication, "social_invites_time", 0);
        String a3 = SharedPreferencesUtility.a((Context) etsyApplication, "social_invites_page", "");
        String a4 = SharedPreferencesUtility.a((Context) etsyApplication, "social_invites_trigger", "");
        return Uri.parse(str).buildUpon().appendQueryParameter("social_invites", "1").appendQueryParameter("si_uuid", a).appendQueryParameter("user_id", v.a().l().getId()).appendQueryParameter("si_time", String.valueOf(a2)).appendQueryParameter("si_page", a3).appendQueryParameter("si_trigger", a4).appendQueryParameter("si_object_id", SharedPreferencesUtility.a((Context) etsyApplication, "social_invites_object_id", "")).appendQueryParameter(ResponseConstants.PLATFORM, AppBuild.ANDROID_PLATFORM).build().toString();
    }
}
