package com.etsy.android.uikit.ui.core;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.http.a;
import com.etsy.android.lib.core.http.loader.NetworkLoader.b;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;

public abstract class NetworkBaseDialogFragment extends TrackingBaseDialogFragment {
    public <ResultType extends a> void loadDataFromNetwork(int i, @NonNull BaseHttpRequest<?, ResultType, ?> baseHttpRequest, @NonNull b<ResultType> bVar) {
        b.a(getLoaderManager(), i, baseHttpRequest, bVar);
    }
}
