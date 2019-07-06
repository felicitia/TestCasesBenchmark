package de.tavendo.autobahn;

public class WebSocketException extends Exception {
    private static final long serialVersionUID = 1;

    public WebSocketException(String str) {
        super(str);
    }

    public WebSocketException(String str, Throwable th) {
        super(str, th);
    }
}
