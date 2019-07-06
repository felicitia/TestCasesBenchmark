package com.onfido.api.client;

import com.onfido.api.client.data.Applicant;
import com.onfido.api.client.data.Challenge;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.DocType;
import com.onfido.api.client.data.DocumentUpload;
import com.onfido.api.client.data.ErrorData;
import com.onfido.api.client.data.LivePhotoUpload;
import com.onfido.api.client.data.LiveVideoLanguage;
import com.onfido.api.client.data.LiveVideoUpload;
import io.reactivex.Observable;
import java.util.Map;
import okhttp3.logging.HttpLoggingInterceptor.Logger;
import retrofit2.Call;
import retrofit2.Response;

public interface OnfidoAPI {

    public static class Callback<T> implements retrofit2.Callback<T> {
        static final String TAG;
        private final ErrorParser errorParser;
        private final Listener<T> listener;

        static {
            StringBuilder sb = new StringBuilder();
            sb.append(OnfidoAPI.class.getSimpleName());
            sb.append(".");
            sb.append(Callback.class.getSimpleName());
            TAG = sb.toString();
        }

        public Callback(Listener<T> listener2, ErrorParser errorParser2) {
            this.listener = listener2;
            this.errorParser = errorParser2;
        }

        public void onFailure(Call<T> call, Throwable th) {
            this.listener.onFailure();
            Logger logger = Logger.DEFAULT;
            StringBuilder sb = new StringBuilder();
            sb.append(TAG);
            sb.append("/onFailure:");
            sb.append(th);
            logger.log(sb.toString());
        }

        public void onResponse(Call<T> call, Response<T> response) {
            if (!response.isSuccessful()) {
                this.listener.onError(response.code(), response.message(), this.errorParser.parseErrorFrom(response));
                return;
            }
            this.listener.onSuccess(response.body());
        }
    }

    public interface Listener<T> {
        void onError(int i, String str, ErrorData errorData);

        void onFailure();

        void onSuccess(T t);
    }

    void create(Applicant applicant, Listener<Applicant> listener);

    void upload(Applicant applicant, String str, DocType docType, String str2, byte[] bArr, Listener<DocumentUpload> listener, Map<ValidationType, ValidationLevel> map, DocSide docSide, String str3, String str4);

    void uploadLivePhoto(Applicant applicant, String str, String str2, byte[] bArr, boolean z, Listener<LivePhotoUpload> listener, String str3, String str4);

    Observable<LiveVideoUpload> uploadLiveVideo(Applicant applicant, String str, String str2, byte[] bArr, String str3, String str4, Challenge[] challengeArr, Long l, LiveVideoLanguage[] liveVideoLanguageArr);
}
