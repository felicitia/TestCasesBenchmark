package com.onfido.api.client;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.onfido.api.client.Utils.Log;
import com.onfido.api.client.adapters.LocaleConverter;
import com.onfido.api.client.common.NetworkConstants;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.CertificatePinner;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class OnfidoFetcher {
    private static final String TAG = "OnfidoFetcher";
    private final Retrofit retrofit;

    private static class AuthTokenInterceptor implements Interceptor {
        private final String token;

        private AuthTokenInterceptor(String str) {
            this.token = str;
        }

        public Response intercept(Chain chain) throws IOException {
            StringBuilder sb = new StringBuilder();
            sb.append("Token token=");
            sb.append(this.token);
            return chain.proceed(chain.request().newBuilder().addHeader("Authorization", sb.toString()).build());
        }
    }

    private OnfidoFetcher(String str, String str2, Fingerprint fingerprint) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(Level.BASIC);
        Builder retryOnConnectionFailure = new Builder().addInterceptor(new AuthTokenInterceptor(str)).addInterceptor(httpLoggingInterceptor).connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).retryOnConnectionFailure(true);
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init(null);
            TrustManager[] trustManagers = instance.getTrustManagers();
            if (trustManagers.length == 1) {
                if (trustManagers[0] instanceof X509TrustManager) {
                    retryOnConnectionFailure.sslSocketFactory(new OnfidoTLSSocketFactory(), (X509TrustManager) trustManagers[0]);
                    if (fingerprint != null) {
                        retryOnConnectionFailure.certificatePinner(new CertificatePinner.Builder().add(NetworkConstants.ONFIDO_DOMAIN_PREFIX, fingerprint.getString()).build());
                    }
                    this.retrofit = new Retrofit.Builder().client(retryOnConnectionFailure.build()).addConverterFactory(buildGsonConverter()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(HttpUrl.parse(str2)).build();
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected default trust managers:");
            sb.append(Arrays.toString(trustManagers));
            throw new IllegalStateException(sb.toString());
        } catch (KeyManagementException | KeyStoreException | NoSuchAlgorithmException unused) {
            Log.e(TAG, "Exception thrown while setting SSL socket factory");
        }
    }

    private static GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Locale.class, new LocaleConverter());
        return GsonConverterFactory.create(gsonBuilder.create());
    }

    public static OnfidoFetcher create(String str, String str2, Fingerprint fingerprint) {
        return new OnfidoFetcher(str, str2, fingerprint);
    }

    private <T> T api(Class<T> cls) {
        return this.retrofit.create(cls);
    }

    public OnfidoService applicants() {
        return (OnfidoService) api(OnfidoService.class);
    }
}
