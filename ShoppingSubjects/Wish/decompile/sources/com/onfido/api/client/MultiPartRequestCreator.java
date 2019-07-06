package com.onfido.api.client;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Builder;
import okhttp3.RequestBody;

public abstract class MultiPartRequestCreator {
    /* access modifiers changed from: 0000 */
    public Builder setFormType(Builder builder) {
        return builder.setType(MultipartBody.FORM);
    }

    /* access modifiers changed from: 0000 */
    public Builder setApplicantId(Builder builder, String str) {
        return builder.addFormDataPart("applicant_id", str);
    }

    /* access modifiers changed from: 0000 */
    public Builder setFile(Builder builder, String str, String str2, byte[] bArr) {
        return builder.addFormDataPart("name", str).addFormDataPart("file", str, RequestBody.create(MediaType.parse(str2), bArr));
    }

    /* access modifiers changed from: 0000 */
    public Builder setSourceInfo(Builder builder, String str) {
        return builder.addFormDataPart("sdk_source", str);
    }

    /* access modifiers changed from: 0000 */
    public Builder setSourceVersion(Builder builder, String str) {
        return builder.addFormDataPart("sdk_version", str);
    }
}
