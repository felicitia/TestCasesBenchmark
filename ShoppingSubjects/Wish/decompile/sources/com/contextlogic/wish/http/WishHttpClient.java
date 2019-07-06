package com.contextlogic.wish.http;

import android.os.StatFs;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class WishHttpClient {
    private static WishHttpClient sInstance = new WishHttpClient();
    private Executor mCancellationExecutor = this.mHttpClient.dispatcher().executorService();
    private OkHttpClient mHttpClient = createOkHttpClient();

    private WishHttpClient() {
    }

    private OkHttpClient createOkHttpClient() {
        Builder cookieJar = new Builder().connectTimeout(35000, TimeUnit.MILLISECONDS).readTimeout(35000, TimeUnit.MILLISECONDS).writeTimeout(35000, TimeUnit.MILLISECONDS).cookieJar(HttpCookieManager.getInstance());
        Tls12Helper.enableTls12(cookieJar);
        try {
            File file = new File(WishApplication.getInstance().getCacheDir().getAbsolutePath(), "http-cache");
            cookieJar.cache(new Cache(file, calculateOptimalCacheSize(file)));
        } catch (Throwable unused) {
        }
        cookieJar.authenticator(new Authenticator() {
            public Request authenticate(Route route, Response response) {
                String basic = response.request().url().host().equals(WishApplication.getInstance().getString(R.string.testing_server_host)) ? Credentials.basic(ServerConfig.getInstance().getApiUsername(), ServerConfig.getInstance().getApiPassword()) : null;
                if (basic != null) {
                    return response.request().newBuilder().header("Authorization", basic).build();
                }
                return null;
            }
        });
        return cookieJar.build();
    }

    public Call startRequest(Request request, Callback callback) {
        Call newCall = this.mHttpClient.newCall(request);
        FirebasePerfOkHttpClient.enqueue(newCall, callback);
        return newCall;
    }

    public static WishHttpClient getInstance() {
        return sInstance;
    }

    public void cancelRequest(final Call call) {
        this.mCancellationExecutor.execute(new Runnable() {
            public void run() {
                if (call != null) {
                    call.cancel();
                }
            }
        });
    }

    public static long calculateOptimalCacheSize(File file) {
        long j;
        try {
            StatFs statFs = new StatFs(file.getAbsolutePath());
            j = (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 50;
        } catch (IllegalArgumentException unused) {
            j = 5242880;
        }
        return Math.max(Math.min(j, 52428800), 5242880);
    }
}
