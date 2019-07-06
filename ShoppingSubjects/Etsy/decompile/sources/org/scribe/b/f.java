package org.scribe.b;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.scribe.e.b;
import org.scribe.e.c;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.Token;

/* compiled from: TokenExtractorImpl */
public class f implements a {
    private static final Pattern a = Pattern.compile("oauth_token=([^&]+)");
    private static final Pattern b = Pattern.compile("oauth_token_secret=([^&]*)");

    public Token a(String str) {
        c.a(str, "Response body is incorrect. Can't extract a token from an empty string");
        return new Token(a(str, a), a(str, b), str);
    }

    private String a(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find() && matcher.groupCount() >= 1) {
            return b.b(matcher.group(1));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Response body is incorrect. Can't extract token and secret from this: '");
        sb.append(str);
        sb.append("'");
        throw new OAuthException(sb.toString(), null);
    }
}
