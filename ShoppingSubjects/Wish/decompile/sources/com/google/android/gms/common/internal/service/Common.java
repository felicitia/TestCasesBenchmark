package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ClientKey;

public final class Common {
    public static final Api<Object> API = new Api<>("Common.API", CLIENT_BUILDER, CLIENT_KEY);
    private static final AbstractClientBuilder<CommonClient, Object> CLIENT_BUILDER = new zza();
    public static final ClientKey<CommonClient> CLIENT_KEY = new ClientKey<>();
    public static final CommonApi CommonApi = new CommonApiImpl();
}
