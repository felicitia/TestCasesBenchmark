package com.onfido.api.client;

class CheckAPI {
    private final MultipartCheckRequestCreator checkRequestCreator;
    private final OnfidoService onfidoService;

    CheckAPI(OnfidoService onfidoService2, MultipartCheckRequestCreator multipartCheckRequestCreator) {
        this.onfidoService = onfidoService2;
        this.checkRequestCreator = multipartCheckRequestCreator;
    }
}
