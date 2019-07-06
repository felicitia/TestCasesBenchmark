package org.scribe.exceptions;

public class OAuthSignatureException extends OAuthException {
    private static final long serialVersionUID = 1;

    public OAuthSignatureException(String str, Exception exc) {
        super(String.format("Error while signing string: %s", new Object[]{str}), exc);
    }
}
