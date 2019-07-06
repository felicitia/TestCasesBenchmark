package com.stripe.net;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.ChargeRefundCollection;
import com.stripe.model.ChargeRefundCollectionDeserializer;
import com.stripe.model.Dispute;
import com.stripe.model.DisputeDataDeserializer;
import com.stripe.model.EventData;
import com.stripe.model.EventDataDeserializer;
import com.stripe.model.ExternalAccountTypeAdapterFactory;
import com.stripe.model.FeeRefundCollection;
import com.stripe.model.FeeRefundCollectionDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.model.StripeRawJsonObject;
import com.stripe.model.StripeRawJsonObjectDeserializer;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public abstract class APIResource extends StripeObject {
    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).registerTypeAdapter(EventData.class, new EventDataDeserializer()).registerTypeAdapter(ChargeRefundCollection.class, new ChargeRefundCollectionDeserializer()).registerTypeAdapter(FeeRefundCollection.class, new FeeRefundCollectionDeserializer()).registerTypeAdapter(StripeRawJsonObject.class, new StripeRawJsonObjectDeserializer()).registerTypeAdapter(Dispute.class, new DisputeDataDeserializer()).registerTypeAdapterFactory(new ExternalAccountTypeAdapterFactory()).create();
    private static StripeResponseGetter stripeResponseGetter = new LiveStripeResponseGetter();

    public enum RequestMethod {
        GET,
        POST,
        DELETE
    }

    public enum RequestType {
        NORMAL,
        MULTIPART
    }

    private static String className(Class<?> cls) {
        String replace = cls.getSimpleName().toLowerCase().replace("$", " ");
        if (replace.equals("applicationfee")) {
            return "application_fee";
        }
        if (replace.equals("fileupload")) {
            return "file";
        }
        return replace.equals("bitcoinreceiver") ? "bitcoin_receiver" : replace;
    }

    protected static String singleClassURL(Class<?> cls, String str) {
        return String.format("%s/v1/%s", new Object[]{str, className(cls)});
    }

    protected static String classURL(Class<?> cls) {
        return classURL(cls, Stripe.getApiBase());
    }

    protected static String classURL(Class<?> cls, String str) {
        return String.format("%ss", new Object[]{singleClassURL(cls, str)});
    }

    public static String urlEncode(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return URLEncoder.encode(str, "UTF-8");
    }

    protected static <T> T request(RequestMethod requestMethod, String str, Map<String, Object> map, Class<T> cls, RequestOptions requestOptions) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        return stripeResponseGetter.request(requestMethod, str, map, cls, RequestType.NORMAL, requestOptions);
    }
}
