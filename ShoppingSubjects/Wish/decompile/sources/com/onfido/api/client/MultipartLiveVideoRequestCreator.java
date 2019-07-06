package com.onfido.api.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.onfido.api.client.data.Challenge;
import com.onfido.api.client.data.JsonSerializable;
import com.onfido.api.client.data.LiveVideoLanguage;
import okhttp3.MultipartBody.Builder;
import okhttp3.RequestBody;

class MultipartLiveVideoRequestCreator extends MultiPartRequestCreator {
    private Builder builder;

    public static MultipartLiveVideoRequestCreator newInstance() {
        return new MultipartLiveVideoRequestCreator(new Builder());
    }

    MultipartLiveVideoRequestCreator(Builder builder2) {
        this.builder = builder2;
    }

    public RequestBody createMultipartRequestBody(String str, String str2, String str3, byte[] bArr, String str4, String str5, Challenge[] challengeArr, Long l, LiveVideoLanguage[] liveVideoLanguageArr) {
        setFormType(this.builder);
        setApplicantId(this.builder, str);
        setChallenges(this.builder, serialise(challengeArr));
        setFile(this.builder, str2, str3, bArr);
        setSourceInfo(this.builder, str4);
        setSourceVersion(this.builder, str5);
        setChallengeSwitch(this.builder, l);
        setLanguages(this.builder, serialise(liveVideoLanguageArr));
        return this.builder.build();
    }

    private <T extends JsonSerializable> String serialise(T[] tArr) {
        JsonArray jsonArray = new JsonArray();
        for (T serialise : tArr) {
            jsonArray.add((JsonElement) serialise.serialise());
        }
        return jsonArray.toString();
    }

    private static Builder setChallenges(Builder builder2, String str) {
        return builder2.addFormDataPart("challenge", str);
    }

    private static Builder setChallengeSwitch(Builder builder2, Long l) {
        return builder2.addFormDataPart("challenge_switch_at", l.toString());
    }

    private static Builder setLanguages(Builder builder2, String str) {
        return builder2.addFormDataPart("languages", str);
    }
}
