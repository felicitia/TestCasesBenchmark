package org.scribe.exceptions;

import org.scribe.model.c;

public class OAuthParametersMissingException extends OAuthException {
    private static final long serialVersionUID = 1745308760111976671L;

    public OAuthParametersMissingException(c cVar) {
        super(String.format("Could not find oauth parameters in request: %s. OAuth parameters must be specified with the addOAuthParameter() method", new Object[]{cVar}));
    }
}
