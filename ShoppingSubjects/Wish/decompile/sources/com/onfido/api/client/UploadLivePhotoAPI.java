package com.onfido.api.client;

import com.onfido.api.client.data.Applicant;
import com.onfido.api.client.data.LivePhotoUpload;
import retrofit2.Call;

class UploadLivePhotoAPI {
    private final MultipartLivePhotoRequestCreator livePhotoRequestCreator;
    private final OnfidoService onfidoService;

    UploadLivePhotoAPI(OnfidoService onfidoService2, MultipartLivePhotoRequestCreator multipartLivePhotoRequestCreator) {
        this.onfidoService = onfidoService2;
        this.livePhotoRequestCreator = multipartLivePhotoRequestCreator;
    }

    /* access modifiers changed from: 0000 */
    public Call<LivePhotoUpload> upload(Applicant applicant, String str, String str2, boolean z, byte[] bArr, String str3, String str4) {
        return this.onfidoService.uploadLivePhoto(this.livePhotoRequestCreator.createMultipartRequestBody(applicant.getId(), str, str2, z, bArr, str3, str4));
    }
}
