package com.onfido.api.client;

import com.onfido.api.client.data.Applicant;
import com.onfido.api.client.data.Challenge;
import com.onfido.api.client.data.LiveVideoLanguage;
import com.onfido.api.client.data.LiveVideoUpload;
import io.reactivex.Observable;

class UploadLiveVideoAPI {
    private final MultipartLiveVideoRequestCreator liveVideoRequestCreator;
    private final OnfidoService onfidoService;

    UploadLiveVideoAPI(OnfidoService onfidoService2, MultipartLiveVideoRequestCreator multipartLiveVideoRequestCreator) {
        this.onfidoService = onfidoService2;
        this.liveVideoRequestCreator = multipartLiveVideoRequestCreator;
    }

    /* access modifiers changed from: 0000 */
    public Observable<LiveVideoUpload> upload(Applicant applicant, String str, String str2, byte[] bArr, String str3, String str4, Challenge[] challengeArr, long j, LiveVideoLanguage[] liveVideoLanguageArr) {
        return this.onfidoService.uploadLiveVideo(this.liveVideoRequestCreator.createMultipartRequestBody(applicant.getId(), str, str2, bArr, str3, str4, challengeArr, Long.valueOf(j), liveVideoLanguageArr));
    }
}
