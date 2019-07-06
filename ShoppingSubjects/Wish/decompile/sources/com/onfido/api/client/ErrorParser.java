package com.onfido.api.client;

import com.onfido.api.client.data.ErrorData;
import retrofit2.Response;

public interface ErrorParser {
    ErrorData parseErrorFrom(Response response);
}
