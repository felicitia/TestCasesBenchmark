package com.stripe.model;

import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.net.APIResource;
import com.stripe.net.APIResource.RequestMethod;
import com.stripe.net.RequestOptions;
import java.util.Map;

public class Token extends APIResource {
    Card card;
    Long created;
    String id;
    Boolean livemode;
    Boolean used;

    public Long getCreated() {
        return this.created;
    }

    public String getId() {
        return this.id;
    }

    public Boolean getLivemode() {
        return this.livemode;
    }

    public Boolean getUsed() {
        return this.used;
    }

    public Card getCard() {
        return this.card;
    }

    public static Token create(Map<String, Object> map, RequestOptions requestOptions) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        return (Token) request(RequestMethod.POST, classURL(Token.class), map, Token.class, requestOptions);
    }
}
