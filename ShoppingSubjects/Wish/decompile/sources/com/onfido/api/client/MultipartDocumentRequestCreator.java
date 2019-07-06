package com.onfido.api.client;

import com.google.gson.JsonObject;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.DocType;
import java.util.Map;
import okhttp3.MultipartBody.Builder;
import okhttp3.RequestBody;

class MultipartDocumentRequestCreator extends MultiPartRequestCreator {
    private Builder builder;

    public static MultipartDocumentRequestCreator newInstance() {
        return new MultipartDocumentRequestCreator(new Builder());
    }

    MultipartDocumentRequestCreator(Builder builder2) {
        this.builder = builder2;
    }

    public RequestBody createMultipartRequestBody(String str, DocType docType, String str2, byte[] bArr, Map<ValidationType, ValidationLevel> map, DocSide docSide, String str3, String str4) {
        setFormType(this.builder);
        setFile(this.builder, str, str2, bArr);
        setDocumentType(this.builder, docType);
        setValidate(this.builder, map);
        setSourceInfo(this.builder, str3);
        setSourceVersion(this.builder, str4);
        if (docSide != null) {
            setSide(this.builder, docSide);
        }
        return this.builder.build();
    }

    private static Builder setDocumentType(Builder builder2, DocType docType) {
        return builder2.addFormDataPart("type", docType.getId());
    }

    private static Builder setSide(Builder builder2, DocSide docSide) {
        return builder2.addFormDataPart("side", docSide.getId());
    }

    private Builder setValidate(Builder builder2, Map<ValidationType, ValidationLevel> map) {
        JsonObject jsonObject = new JsonObject();
        for (ValidationType validationType : map.keySet()) {
            jsonObject.addProperty(validationType.getId(), ((ValidationLevel) map.get(validationType)).getId());
        }
        return builder2.addFormDataPart("sdk_validations", jsonObject.toString());
    }
}
