package com.onfido.api.client;

import okhttp3.MultipartBody.Builder;
import okhttp3.RequestBody;

class MultipartLivePhotoRequestCreator extends MultiPartRequestCreator {
    private Builder builder;

    public static MultipartLivePhotoRequestCreator newInstance() {
        return new MultipartLivePhotoRequestCreator(new Builder());
    }

    MultipartLivePhotoRequestCreator(Builder builder2) {
        this.builder = builder2;
    }

    public RequestBody createMultipartRequestBody(String str, String str2, String str3, boolean z, byte[] bArr, String str4, String str5) {
        setFormType(this.builder);
        setApplicantId(this.builder, str);
        setFile(this.builder, str2, str3, bArr);
        setValidate(this.builder, z);
        setSourceInfo(this.builder, str4);
        setSourceVersion(this.builder, str5);
        return this.builder.build();
    }

    private Builder setValidate(Builder builder2, boolean z) {
        return builder2.addFormDataPart("advanced_validation", String.valueOf(z));
    }
}
