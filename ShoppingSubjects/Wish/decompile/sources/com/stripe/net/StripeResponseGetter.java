package com.stripe.net;

import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.net.APIResource.RequestMethod;
import com.stripe.net.APIResource.RequestType;
import java.util.Map;

public interface StripeResponseGetter {
    <T> T request(RequestMethod requestMethod, String str, Map<String, Object> map, Class<T> cls, RequestType requestType, RequestOptions requestOptions) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException;
}
