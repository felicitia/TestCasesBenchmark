package com.etsy.android.uikit.ui.core;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.http.a;
import com.etsy.android.lib.core.http.loader.NetworkLoader;
import com.etsy.android.lib.core.http.loader.NetworkLoader.b;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.etsyapi.c;

public abstract class NetworkLoaderFragment extends TrackingBaseFragment {
    public <ResultType extends a> void loadDataFromNetwork(int i, @NonNull BaseHttpRequest<?, ResultType, ?> baseHttpRequest, @NonNull b<ResultType> bVar) {
        b.a(getLoaderManager(), i, baseHttpRequest, bVar);
    }

    public <T> void loadDataFromNetwork(int i, c<T> cVar, @NonNull NetworkLoader.a<T> aVar) {
        b.a(getLoaderManager(), i, cVar, aVar);
    }
}
