package com.paypal.android.sdk.onetouch.core.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OAuth2Recipe extends Recipe<OAuth2Recipe> {
    private final Map<String, ConfigEndpoint> mEndpoints = new HashMap();
    private boolean mIsValidForAllScopes;
    private final Collection<String> mScope = new HashSet();

    public OAuth2Recipe getThis() {
        return this;
    }

    public void validForScope(String str) {
        this.mScope.add(str);
    }

    public void withEndpoint(String str, ConfigEndpoint configEndpoint) {
        this.mEndpoints.put(str, configEndpoint);
    }

    public boolean isValidForScopes(Set<String> set) {
        if (this.mIsValidForAllScopes) {
            return true;
        }
        return set.containsAll(set);
    }

    public void validForAllScopes() {
        this.mIsValidForAllScopes = true;
    }

    public ConfigEndpoint getEndpoint(String str) {
        if (this.mEndpoints.containsKey(str)) {
            return (ConfigEndpoint) this.mEndpoints.get(str);
        }
        if (this.mEndpoints.containsKey("develop")) {
            return (ConfigEndpoint) this.mEndpoints.get("develop");
        }
        return (ConfigEndpoint) this.mEndpoints.get("live");
    }
}
