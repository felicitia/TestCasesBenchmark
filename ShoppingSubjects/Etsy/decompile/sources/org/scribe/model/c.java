package org.scribe.model;

import java.util.HashMap;
import java.util.Map;

/* compiled from: OAuthRequest */
public class c extends f {
    private Map<String, String> a = new HashMap();

    public c(Verb verb, String str) {
        super(verb, str);
    }

    public void a(String str, String str2) {
        this.a.put(a(str), str2);
    }

    private String a(String str) {
        if (str.startsWith("oauth_") || str.equals("scope")) {
            return str;
        }
        throw new IllegalArgumentException(String.format("OAuth parameters must either be '%s' or start with '%s'", new Object[]{"scope", "oauth_"}));
    }

    public Map<String, String> a() {
        return this.a;
    }

    public String toString() {
        return String.format("@OAuthRequest(%s, %s)", new Object[]{i(), f()});
    }
}
