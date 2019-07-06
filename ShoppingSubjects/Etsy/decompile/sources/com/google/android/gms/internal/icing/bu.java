package com.google.android.gms.internal.icing;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class bu {
    public static final Api<NoOptions> a = new Api<>("AppDataSearch.LIGHTWEIGHT_API", c, b);
    private static final ClientKey<b> b = new ClientKey<>();
    private static final AbstractClientBuilder<b, NoOptions> c = new cu();
    private static final a d = new zzai();
}
