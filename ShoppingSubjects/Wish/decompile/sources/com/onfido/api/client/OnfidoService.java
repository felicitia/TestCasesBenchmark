package com.onfido.api.client;

import com.onfido.api.client.data.Applicant;
import com.onfido.api.client.data.DocumentUpload;
import com.onfido.api.client.data.LivePhotoUpload;
import com.onfido.api.client.data.LiveVideoUpload;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface OnfidoService {
    @POST("applicants")
    Call<Applicant> create(@Body Applicant applicant);

    @POST("applicants/{applicant_id}/documents")
    Call<DocumentUpload> upload(@Path("applicant_id") String str, @Body RequestBody requestBody);

    @POST("live_photos")
    Call<LivePhotoUpload> uploadLivePhoto(@Body RequestBody requestBody);

    @POST("live_videos")
    Observable<LiveVideoUpload> uploadLiveVideo(@Body RequestBody requestBody);
}
