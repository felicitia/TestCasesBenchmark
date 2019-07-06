package com.facebook;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import org.apache.commons.math3.geometry.VectorFormat;

public class FacebookGraphResponseException extends FacebookException {
    private final GraphResponse graphResponse;

    public FacebookGraphResponseException(GraphResponse graphResponse2, String str) {
        super(str);
        this.graphResponse = graphResponse2;
    }

    public final GraphResponse getGraphResponse() {
        return this.graphResponse;
    }

    public final String toString() {
        FacebookRequestError a = this.graphResponse != null ? this.graphResponse.a() : null;
        StringBuilder sb = new StringBuilder();
        sb.append("{FacebookGraphResponseException: ");
        String message = getMessage();
        if (message != null) {
            sb.append(message);
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
        if (a != null) {
            sb.append("httpResponseCode: ");
            sb.append(a.getRequestStatusCode());
            sb.append(", facebookErrorCode: ");
            sb.append(a.getErrorCode());
            sb.append(", facebookErrorType: ");
            sb.append(a.getErrorType());
            sb.append(", message: ");
            sb.append(a.getErrorMessage());
            sb.append(VectorFormat.DEFAULT_SUFFIX);
        }
        return sb.toString();
    }
}
