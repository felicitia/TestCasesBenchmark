package com.etsy.android.uikit.ui.core;

import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import com.etsy.android.lib.core.http.a;
import com.etsy.android.lib.core.http.loader.NetworkLoader;
import com.etsy.android.lib.core.http.loader.NetworkLoaderCallbacks;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.logger.f;
import com.etsy.etsyapi.c;

/* compiled from: NetworkLoaderDelegate */
public class b {
    private static final String a = f.a(b.class);

    public static <ResultType extends a> void a(@NonNull LoaderManager loaderManager, int i, @NonNull BaseHttpRequest<?, ResultType, ?> baseHttpRequest, @NonNull com.etsy.android.lib.core.http.loader.NetworkLoader.b<ResultType> bVar) {
        Loader initLoader = loaderManager.initLoader(i, null, new NetworkLoaderCallbacks.a(i, loaderManager, baseHttpRequest).a(bVar).a());
        String str = a;
        String str2 = "loadDataFromNetwork: got loader (%d)";
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(initLoader == null ? 0 : initLoader.hashCode());
        f.c(str, str2, objArr);
    }

    public static <T> void a(@NonNull LoaderManager loaderManager, int i, @NonNull c<T> cVar, @NonNull NetworkLoader.a<T> aVar) {
        a(loaderManager, i, EtsyApiV3Request.a.a(cVar).d(), (com.etsy.android.lib.core.http.loader.NetworkLoader.b<ResultType>) aVar);
    }
}
