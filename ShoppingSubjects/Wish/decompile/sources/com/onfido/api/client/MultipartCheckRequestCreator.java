package com.onfido.api.client;

import okhttp3.MultipartBody.Builder;

class MultipartCheckRequestCreator {
    private Builder builder;

    public static MultipartCheckRequestCreator newInstance() {
        return new MultipartCheckRequestCreator(new Builder());
    }

    MultipartCheckRequestCreator(Builder builder2) {
        this.builder = builder2;
    }
}
