package com.facebook;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.facebook.internal.z;
import java.net.HttpURLConnection;
import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.geometry.VectorFormat;

public class GraphRequestAsyncTask extends AsyncTask<Void, Void, List<GraphResponse>> {
    private static final String TAG = GraphRequestAsyncTask.class.getCanonicalName();
    private final HttpURLConnection connection;
    private Exception exception;
    private final g requests;

    public GraphRequestAsyncTask(GraphRequest... graphRequestArr) {
        this((HttpURLConnection) null, new g(graphRequestArr));
    }

    public GraphRequestAsyncTask(Collection<GraphRequest> collection) {
        this((HttpURLConnection) null, new g(collection));
    }

    public GraphRequestAsyncTask(g gVar) {
        this((HttpURLConnection) null, gVar);
    }

    public GraphRequestAsyncTask(HttpURLConnection httpURLConnection, GraphRequest... graphRequestArr) {
        this(httpURLConnection, new g(graphRequestArr));
    }

    public GraphRequestAsyncTask(HttpURLConnection httpURLConnection, Collection<GraphRequest> collection) {
        this(httpURLConnection, new g(collection));
    }

    public GraphRequestAsyncTask(HttpURLConnection httpURLConnection, g gVar) {
        this.requests = gVar;
        this.connection = httpURLConnection;
    }

    /* access modifiers changed from: protected */
    public final Exception getException() {
        return this.exception;
    }

    /* access modifiers changed from: protected */
    public final g getRequests() {
        return this.requests;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{RequestAsyncTask: ");
        sb.append(" connection: ");
        sb.append(this.connection);
        sb.append(", requests: ");
        sb.append(this.requests);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        Handler handler;
        super.onPreExecute();
        if (f.b()) {
            z.b(TAG, String.format("execute async task: %s", new Object[]{this}));
        }
        if (this.requests.c() == null) {
            if (Thread.currentThread() instanceof HandlerThread) {
                handler = new Handler();
            } else {
                handler = new Handler(Looper.getMainLooper());
            }
            this.requests.a(handler);
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(List<GraphResponse> list) {
        super.onPostExecute(list);
        if (this.exception != null) {
            z.b(TAG, String.format("onPostExecute: exception encountered during request: %s", new Object[]{this.exception.getMessage()}));
        }
    }

    /* access modifiers changed from: protected */
    public List<GraphResponse> doInBackground(Void... voidArr) {
        try {
            if (this.connection == null) {
                return this.requests.g();
            }
            return GraphRequest.a(this.connection, this.requests);
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }
}
