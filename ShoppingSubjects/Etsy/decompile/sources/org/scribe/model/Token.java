package org.scribe.model;

import java.io.Serializable;
import org.scribe.e.c;

public class Token implements Serializable {
    private static final long serialVersionUID = 715000866082812683L;
    private final String rawResponse;
    private final String secret;
    private final String token;

    public Token(String str, String str2) {
        this(str, str2, null);
    }

    public Token(String str, String str2, String str3) {
        c.a((Object) str, "Token can't be null");
        c.a((Object) str2, "Secret can't be null");
        this.token = str;
        this.secret = str2;
        this.rawResponse = str3;
    }

    public String getToken() {
        return this.token;
    }

    public String getSecret() {
        return this.secret;
    }

    public String getRawResponse() {
        if (this.rawResponse != null) {
            return this.rawResponse;
        }
        throw new IllegalStateException("This token object was not constructed by scribe and does not have a rawResponse");
    }

    public String toString() {
        return String.format("Token[%s , %s]", new Object[]{this.token, this.secret});
    }

    public boolean isEmpty() {
        return "".equals(this.token) && "".equals(this.secret);
    }

    public static Token empty() {
        return new Token("", "");
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Token token2 = (Token) obj;
        if (!this.token.equals(token2.token) || !this.secret.equals(token2.secret)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (31 * this.token.hashCode()) + this.secret.hashCode();
    }
}
