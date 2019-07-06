package com.facebook.share.internal;

import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.PlatformServiceClient;

@Deprecated
/* compiled from: LikeStatusClient */
final class e extends PlatformServiceClient {
    private String a;

    e(Context context, String str, String str2) {
        super(context, 65542, 65543, 20141001, str);
        this.a = str2;
    }

    /* access modifiers changed from: protected */
    public void populateRequestBundle(Bundle bundle) {
        bundle.putString("com.facebook.platform.extra.OBJECT_ID", this.a);
    }
}
