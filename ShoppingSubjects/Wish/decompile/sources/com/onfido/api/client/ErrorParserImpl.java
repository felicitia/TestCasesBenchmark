package com.onfido.api.client;

import com.google.gson.Gson;
import com.onfido.api.client.data.ErrorData;
import retrofit2.Response;

class ErrorParserImpl implements ErrorParser {
    private Gson gson;

    public static ErrorParser newInstance() {
        return new ErrorParserImpl(new Gson());
    }

    ErrorParserImpl(Gson gson2) {
        this.gson = gson2;
    }

    public ErrorData parseErrorFrom(Response response) {
        try {
            return (ErrorData) this.gson.fromJson(response.errorBody().string(), ErrorData.class);
        } catch (Exception unused) {
            return null;
        }
    }
}
