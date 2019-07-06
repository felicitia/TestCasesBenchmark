package com.onfido.api.client;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.onfido.api.client.OnfidoAPI.Listener;
import com.onfido.api.client.data.Applicant;
import com.onfido.api.client.data.Challenge;
import com.onfido.api.client.data.Check;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.DocType;
import com.onfido.api.client.data.DocumentUpload;
import com.onfido.api.client.data.LivePhotoUpload;
import com.onfido.api.client.data.LiveVideoLanguage;
import com.onfido.api.client.data.LiveVideoUpload;
import io.reactivex.Observable;
import java.util.Map;

public class OnfidoDemoAPIImpl implements OnfidoAPI {
    private Check check = Check.builder().build();
    private Gson gson = new Gson();
    private LivePhotoUpload livePhotoUpload = new LivePhotoUpload();
    private LiveVideoUpload liveVideoUpload = new LiveVideoUpload();

    public void create(Applicant applicant, Listener<Applicant> listener) {
        listener.onSuccess(applicant);
    }

    public void upload(Applicant applicant, String str, DocType docType, String str2, byte[] bArr, Listener<DocumentUpload> listener, Map<ValidationType, ValidationLevel> map, DocSide docSide, String str3, String str4) {
        listener.onSuccess((DocumentUpload) this.gson.fromJson((JsonElement) serializeDocumentUpload(docType, docSide), DocumentUpload.class));
    }

    public void uploadLivePhoto(Applicant applicant, String str, String str2, byte[] bArr, boolean z, Listener<LivePhotoUpload> listener, String str3, String str4) {
        listener.onSuccess(this.livePhotoUpload);
    }

    public Observable<LiveVideoUpload> uploadLiveVideo(Applicant applicant, String str, String str2, byte[] bArr, String str3, String str4, Challenge[] challengeArr, Long l, LiveVideoLanguage[] liveVideoLanguageArr) {
        return Observable.just(this.liveVideoUpload);
    }

    private JsonObject serializeDocumentUpload(DocType docType, DocSide docSide) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", "ANY_ID");
        jsonObject.addProperty("type", docType.getId());
        String str = "side";
        if (docSide == null) {
            docSide = DocSide.FRONT;
        }
        jsonObject.addProperty(str, docSide.getId());
        return jsonObject;
    }
}
