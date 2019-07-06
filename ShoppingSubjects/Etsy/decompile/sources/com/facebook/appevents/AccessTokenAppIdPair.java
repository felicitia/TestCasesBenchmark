package com.facebook.appevents;

import com.facebook.AccessToken;
import com.facebook.f;
import com.facebook.internal.z;
import java.io.Serializable;

class AccessTokenAppIdPair implements Serializable {
    private static final long serialVersionUID = 1;
    private final String accessTokenString;
    private final String applicationId;

    static class SerializationProxyV1 implements Serializable {
        private static final long serialVersionUID = -2488473066578201069L;
        private final String accessTokenString;
        private final String appId;

        private SerializationProxyV1(String str, String str2) {
            this.accessTokenString = str;
            this.appId = str2;
        }

        private Object readResolve() {
            return new AccessTokenAppIdPair(this.accessTokenString, this.appId);
        }
    }

    public AccessTokenAppIdPair(AccessToken accessToken) {
        this(accessToken.getToken(), f.j());
    }

    public AccessTokenAppIdPair(String str, String str2) {
        if (z.a(str)) {
            str = null;
        }
        this.accessTokenString = str;
        this.applicationId = str2;
    }

    public String getAccessTokenString() {
        return this.accessTokenString;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = this.accessTokenString == null ? 0 : this.accessTokenString.hashCode();
        if (this.applicationId != null) {
            i = this.applicationId.hashCode();
        }
        return hashCode ^ i;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof AccessTokenAppIdPair)) {
            return false;
        }
        AccessTokenAppIdPair accessTokenAppIdPair = (AccessTokenAppIdPair) obj;
        if (z.a(accessTokenAppIdPair.accessTokenString, this.accessTokenString) && z.a(accessTokenAppIdPair.applicationId, this.applicationId)) {
            z = true;
        }
        return z;
    }

    private Object writeReplace() {
        return new SerializationProxyV1(this.accessTokenString, this.applicationId);
    }
}
