package com.contextlogic.wish.api.service;

import android.os.AsyncTask;
import okhttp3.Call;

public abstract class LocalApiService<S, T> extends ApiService {
    /* access modifiers changed from: private */
    public AsyncTask<S, Void, T> mRequestTask;

    protected interface LocalApiCallback<S, T> {
        T processRequest(S... sArr);

        void processResult(T t);
    }

    public void cancelAllRequests() {
        if (this.mRequestTask != null) {
            this.mRequestTask.cancel(true);
            this.mRequestTask = null;
        }
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
        }
    }

    /* access modifiers changed from: protected */
    public Call startService(final LocalApiCallback<S, T> localApiCallback, S... sArr) {
        cancelAllRequests();
        this.mRequestTask = new AsyncTask<S, Void, T>() {
            /* access modifiers changed from: protected */
            public T doInBackground(S... sArr) {
                if (LocalApiService.this.mRequestTask == this) {
                    return localApiCallback.processRequest(sArr);
                }
                return null;
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(T t) {
                if (LocalApiService.this.mRequestTask == this) {
                    localApiCallback.processResult(t);
                }
            }
        };
        this.mRequestTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, sArr);
        return null;
    }
}
