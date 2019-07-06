package com.etsy.android.uikit.ui.core;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.http.a;
import com.etsy.android.lib.core.http.loader.NetworkLoader.b;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.uikit.nav.TrackingBaseActivity;

public abstract class NetworkLoaderActivity extends TrackingBaseActivity {
    public <ResultType extends a> void loadDataFromNetwork(int i, @NonNull BaseHttpRequest<?, ResultType, ?> baseHttpRequest, @NonNull b<ResultType> bVar) {
        b.a(getSupportLoaderManager(), i, baseHttpRequest, bVar);
    }
}
