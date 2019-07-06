package com.onfido.api.client;

import com.onfido.api.client.OnfidoAPI.Callback;
import com.onfido.api.client.OnfidoAPI.Listener;
import com.onfido.api.client.data.Applicant;
import com.onfido.api.client.data.Challenge;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.DocType;
import com.onfido.api.client.data.DocumentUpload;
import com.onfido.api.client.data.LivePhotoUpload;
import com.onfido.api.client.data.LiveVideoLanguage;
import com.onfido.api.client.data.LiveVideoUpload;
import io.reactivex.Observable;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

class OnfidoAPIImpl implements OnfidoAPI {
    private final ApplicantAPI applicantAPI;
    private final CheckAPI checkAPI;
    private final ErrorParser errorParser;
    private final UploadLivePhotoAPI livePhotoAPI;
    private final UploadLiveVideoAPI liveVideoAPI;
    private final UploadDocumentAPI uploadDocumentAPI;

    private String getSourceName(String str) {
        return str != null ? str : "Onfido Java Client";
    }

    OnfidoAPIImpl(UploadDocumentAPI uploadDocumentAPI2, UploadLivePhotoAPI uploadLivePhotoAPI, UploadLiveVideoAPI uploadLiveVideoAPI, CheckAPI checkAPI2, ApplicantAPI applicantAPI2, ErrorParser errorParser2) {
        this.uploadDocumentAPI = uploadDocumentAPI2;
        this.livePhotoAPI = uploadLivePhotoAPI;
        this.liveVideoAPI = uploadLiveVideoAPI;
        this.checkAPI = checkAPI2;
        this.applicantAPI = applicantAPI2;
        this.errorParser = errorParser2;
    }

    public void create(Applicant applicant, Listener<Applicant> listener) {
        this.applicantAPI.create(applicant).enqueue(new Callback(listener, this.errorParser));
    }

    public void upload(Applicant applicant, String str, DocType docType, String str2, byte[] bArr, Listener<DocumentUpload> listener, Map<ValidationType, ValidationLevel> map, DocSide docSide, String str3, String str4) {
        this.uploadDocumentAPI.upload(applicant, str, docType, str2, bArr, map, docSide, getSourceName(str3), getSourceVersion(str4)).enqueue(new Callback(listener, this.errorParser));
    }

    public void uploadLivePhoto(Applicant applicant, String str, String str2, byte[] bArr, boolean z, Listener<LivePhotoUpload> listener, String str3, String str4) {
        this.livePhotoAPI.upload(applicant, str, str2, z, bArr, getSourceName(str3), getSourceVersion(str4)).enqueue(new Callback(listener, this.errorParser));
    }

    public Observable<LiveVideoUpload> uploadLiveVideo(Applicant applicant, String str, String str2, byte[] bArr, String str3, String str4, Challenge[] challengeArr, Long l, LiveVideoLanguage[] liveVideoLanguageArr) {
        return this.liveVideoAPI.upload(applicant, str, str2, bArr, getSourceName(str3), getSourceVersion(str4), challengeArr, l.longValue(), liveVideoLanguageArr);
    }

    private String getSourceVersion(String str) {
        if (str != null) {
            return str;
        }
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/version.properties"));
            return properties.getProperty("version");
        } catch (IOException unused) {
            return "NA";
        }
    }
}
