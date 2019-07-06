package com.onfido.android.sdk.capture.analytics;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import kotlin.jvm.internal.Intrinsics;

public class IdentityInteractor {
    public static final Companion Companion = new Companion(null);
    private final Context a;
    private final NativeDetector b;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public IdentityInteractor(Context context, NativeDetector nativeDetector) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(nativeDetector, "nativeDetector");
        this.a = context;
        this.b = nativeDetector;
    }

    public String getAppPackageName() {
        String packageName = this.a.getPackageName();
        return packageName != null ? packageName : "N/A";
    }

    public String getCurrentLocale() {
        String country;
        String str;
        if (VERSION.SDK_INT >= 24) {
            country = this.a.getResources().getConfiguration().getLocales().get(0).getCountry();
            str = "context.resources.config…on.locales.get(0).country";
        } else {
            country = this.a.getResources().getConfiguration().locale.getCountry();
            str = "context.resources.configuration.locale.country";
        }
        Intrinsics.checkExpressionValueIsNotNull(country, str);
        return country;
    }

    public int getGooglePlayServicesVersion() {
        try {
            return this.a.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (NameNotFoundException unused) {
            return -1;
        }
    }

    public String getSdkLocaleCode() {
        return this.a.getString(R.string.onfido_locale);
    }

    public String getSdkSource() {
        return "onfido-android-sdk";
    }

    public String getSdkVersion() {
        String str = this.b.hasNativeLibrary() ? "" : "_core";
        StringBuilder sb = new StringBuilder();
        sb.append("4.3.0");
        sb.append(str);
        return sb.toString();
    }
}
