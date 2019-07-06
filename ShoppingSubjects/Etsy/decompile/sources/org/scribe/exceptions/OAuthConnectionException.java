package org.scribe.exceptions;

public class OAuthConnectionException extends OAuthException {
    public OAuthConnectionException(Exception exc) {
        super("There was a problem while creating a connection to the remote service.", exc);
    }
}
