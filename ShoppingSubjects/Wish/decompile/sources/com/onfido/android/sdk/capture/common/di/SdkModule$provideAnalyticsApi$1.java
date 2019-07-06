package com.onfido.android.sdk.capture.common.di;

import android.net.Uri;
import com.onfido.c.a.f;
import java.net.HttpURLConnection;
import kotlin.jvm.internal.Intrinsics;

public final class SdkModule$provideAnalyticsApi$1 extends f {
    SdkModule$provideAnalyticsApi$1() {
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection openConnection(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://192.168.80.110:8080");
        sb.append(Uri.parse(str).getPath());
        HttpURLConnection openConnection = super.openConnection(sb.toString());
        Intrinsics.checkExpressionValueIsNotNull(openConnection, "super.openConnection(Bui…RL + Uri.parse(url).path)");
        return openConnection;
    }
}
