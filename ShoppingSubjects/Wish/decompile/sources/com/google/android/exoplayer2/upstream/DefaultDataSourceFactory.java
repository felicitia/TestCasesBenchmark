package com.google.android.exoplayer2.upstream;

import android.content.Context;
import com.google.android.exoplayer2.upstream.DataSource.Factory;

public final class DefaultDataSourceFactory implements Factory {
    private final Factory baseDataSourceFactory;
    private final Context context;
    private final TransferListener<? super DataSource> listener;

    public DefaultDataSourceFactory(Context context2, String str) {
        this(context2, str, null);
    }

    public DefaultDataSourceFactory(Context context2, String str, TransferListener<? super DataSource> transferListener) {
        this(context2, transferListener, (Factory) new DefaultHttpDataSourceFactory(str, transferListener));
    }

    public DefaultDataSourceFactory(Context context2, TransferListener<? super DataSource> transferListener, Factory factory) {
        this.context = context2.getApplicationContext();
        this.listener = transferListener;
        this.baseDataSourceFactory = factory;
    }

    public DefaultDataSource createDataSource() {
        return new DefaultDataSource(this.context, this.listener, this.baseDataSourceFactory.createDataSource());
    }
}
