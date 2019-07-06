package com.facebook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.internal.aa;
import com.facebook.internal.z;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.math3.geometry.VectorFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AccessToken implements Parcelable {
    public static final String ACCESS_TOKEN_KEY = "access_token";
    private static final String APPLICATION_ID_KEY = "application_id";
    public static final Creator<AccessToken> CREATOR = new Creator() {
        /* renamed from: a */
        public AccessToken createFromParcel(Parcel parcel) {
            return new AccessToken(parcel);
        }

        /* renamed from: a */
        public AccessToken[] newArray(int i) {
            return new AccessToken[i];
        }
    };
    private static final int CURRENT_JSON_FORMAT = 1;
    private static final String DECLINED_PERMISSIONS_KEY = "declined_permissions";
    private static final AccessTokenSource DEFAULT_ACCESS_TOKEN_SOURCE = AccessTokenSource.FACEBOOK_APPLICATION_WEB;
    private static final Date DEFAULT_EXPIRATION_TIME = MAX_DATE;
    private static final Date DEFAULT_LAST_REFRESH_TIME = new Date();
    private static final String EXPIRES_AT_KEY = "expires_at";
    public static final String EXPIRES_IN_KEY = "expires_in";
    private static final String LAST_REFRESH_KEY = "last_refresh";
    private static final Date MAX_DATE = new Date(Long.MAX_VALUE);
    private static final String PERMISSIONS_KEY = "permissions";
    private static final String SOURCE_KEY = "source";
    private static final String TOKEN_KEY = "token";
    public static final String USER_ID_KEY = "user_id";
    private static final String VERSION_KEY = "version";
    private final String applicationId;
    private final Set<String> declinedPermissions;
    private final Date expires;
    private final Date lastRefresh;
    private final Set<String> permissions;
    private final AccessTokenSource source;
    private final String token;
    private final String userId;

    public interface a {
        void a(AccessToken accessToken);

        void a(FacebookException facebookException);
    }

    public interface b {
        void a(AccessToken accessToken);

        void a(FacebookException facebookException);
    }

    public int describeContents() {
        return 0;
    }

    public AccessToken(String str, String str2, String str3, @Nullable Collection<String> collection, @Nullable Collection<String> collection2, @Nullable AccessTokenSource accessTokenSource, @Nullable Date date, @Nullable Date date2) {
        aa.a(str, "accessToken");
        aa.a(str2, "applicationId");
        aa.a(str3, "userId");
        if (date == null) {
            date = DEFAULT_EXPIRATION_TIME;
        }
        this.expires = date;
        this.permissions = Collections.unmodifiableSet(collection != null ? new HashSet(collection) : new HashSet());
        this.declinedPermissions = Collections.unmodifiableSet(collection2 != null ? new HashSet(collection2) : new HashSet());
        this.token = str;
        if (accessTokenSource == null) {
            accessTokenSource = DEFAULT_ACCESS_TOKEN_SOURCE;
        }
        this.source = accessTokenSource;
        if (date2 == null) {
            date2 = DEFAULT_LAST_REFRESH_TIME;
        }
        this.lastRefresh = date2;
        this.applicationId = str2;
        this.userId = str3;
    }

    public static AccessToken getCurrentAccessToken() {
        return b.a().b();
    }

    public static boolean isCurrentAccessTokenActive() {
        AccessToken b2 = b.a().b();
        return b2 != null && !b2.isExpired();
    }

    static void expireCurrentAccessToken() {
        AccessToken b2 = b.a().b();
        if (b2 != null) {
            setCurrentAccessToken(createExpired(b2));
        }
    }

    public static void setCurrentAccessToken(AccessToken accessToken) {
        b.a().a(accessToken);
    }

    public static void refreshCurrentAccessTokenAsync() {
        b.a().a((b) null);
    }

    public static void refreshCurrentAccessTokenAsync(b bVar) {
        b.a().a(bVar);
    }

    public String getToken() {
        return this.token;
    }

    public Date getExpires() {
        return this.expires;
    }

    public Set<String> getPermissions() {
        return this.permissions;
    }

    public Set<String> getDeclinedPermissions() {
        return this.declinedPermissions;
    }

    public AccessTokenSource getSource() {
        return this.source;
    }

    public Date getLastRefresh() {
        return this.lastRefresh;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public String getUserId() {
        return this.userId;
    }

    public static void createFromNativeLinkingIntent(Intent intent, final String str, final a aVar) {
        aa.a((Object) intent, "intent");
        if (intent.getExtras() == null) {
            aVar.a(new FacebookException("No extras found on intent"));
            return;
        }
        final Bundle bundle = new Bundle(intent.getExtras());
        String string = bundle.getString(ACCESS_TOKEN_KEY);
        if (string == null || string.isEmpty()) {
            aVar.a(new FacebookException("No access token found on intent"));
            return;
        }
        String string2 = bundle.getString("user_id");
        if (string2 == null || string2.isEmpty()) {
            z.a(string, (com.facebook.internal.z.a) new com.facebook.internal.z.a() {
                public void a(JSONObject jSONObject) {
                    try {
                        bundle.putString("user_id", jSONObject.getString("id"));
                        aVar.a(AccessToken.createFromBundle(null, bundle, AccessTokenSource.FACEBOOK_APPLICATION_WEB, new Date(), str));
                    } catch (JSONException unused) {
                        aVar.a(new FacebookException("Unable to generate access token due to missing user id"));
                    }
                }

                public void a(FacebookException facebookException) {
                    aVar.a(facebookException);
                }
            });
        } else {
            aVar.a(createFromBundle(null, bundle, AccessTokenSource.FACEBOOK_APPLICATION_WEB, new Date(), str));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{AccessToken");
        sb.append(" token:");
        sb.append(tokenToString());
        appendPermissions(sb);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccessToken)) {
            return false;
        }
        AccessToken accessToken = (AccessToken) obj;
        if (!this.expires.equals(accessToken.expires) || !this.permissions.equals(accessToken.permissions) || !this.declinedPermissions.equals(accessToken.declinedPermissions) || !this.token.equals(accessToken.token) || this.source != accessToken.source || !this.lastRefresh.equals(accessToken.lastRefresh) || (this.applicationId != null ? !this.applicationId.equals(accessToken.applicationId) : accessToken.applicationId != null) || !this.userId.equals(accessToken.userId)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((((((((((((527 + this.expires.hashCode()) * 31) + this.permissions.hashCode()) * 31) + this.declinedPermissions.hashCode()) * 31) + this.token.hashCode()) * 31) + this.source.hashCode()) * 31) + this.lastRefresh.hashCode()) * 31) + (this.applicationId == null ? 0 : this.applicationId.hashCode())) * 31) + this.userId.hashCode();
    }

    @SuppressLint({"FieldGetter"})
    static AccessToken createFromRefresh(AccessToken accessToken, Bundle bundle) {
        if (accessToken.source == AccessTokenSource.FACEBOOK_APPLICATION_WEB || accessToken.source == AccessTokenSource.FACEBOOK_APPLICATION_NATIVE || accessToken.source == AccessTokenSource.FACEBOOK_APPLICATION_SERVICE) {
            Date a2 = z.a(bundle, EXPIRES_IN_KEY, new Date(0));
            String string = bundle.getString(ACCESS_TOKEN_KEY);
            if (z.a(string)) {
                return null;
            }
            AccessToken accessToken2 = new AccessToken(string, accessToken.applicationId, accessToken.getUserId(), accessToken.getPermissions(), accessToken.getDeclinedPermissions(), accessToken.source, a2, new Date());
            return accessToken2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid token source: ");
        sb.append(accessToken.source);
        throw new FacebookException(sb.toString());
    }

    static AccessToken createExpired(AccessToken accessToken) {
        AccessToken accessToken2 = new AccessToken(accessToken.token, accessToken.applicationId, accessToken.getUserId(), accessToken.getPermissions(), accessToken.getDeclinedPermissions(), accessToken.source, new Date(), new Date());
        return accessToken2;
    }

    static AccessToken createFromLegacyCache(Bundle bundle) {
        List permissionsFromBundle = getPermissionsFromBundle(bundle, "com.facebook.TokenCachingStrategy.Permissions");
        List permissionsFromBundle2 = getPermissionsFromBundle(bundle, "com.facebook.TokenCachingStrategy.DeclinedPermissions");
        String d = h.d(bundle);
        if (z.a(d)) {
            d = f.j();
        }
        String str = d;
        String b2 = h.b(bundle);
        try {
            AccessToken accessToken = new AccessToken(b2, str, z.d(b2).getString("id"), permissionsFromBundle, permissionsFromBundle2, h.c(bundle), h.a(bundle, "com.facebook.TokenCachingStrategy.ExpirationDate"), h.a(bundle, "com.facebook.TokenCachingStrategy.LastRefreshDate"));
            return accessToken;
        } catch (JSONException unused) {
            return null;
        }
    }

    static List<String> getPermissionsFromBundle(Bundle bundle, String str) {
        ArrayList stringArrayList = bundle.getStringArrayList(str);
        if (stringArrayList == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList(stringArrayList));
    }

    public boolean isExpired() {
        return new Date().after(this.expires);
    }

    /* access modifiers changed from: 0000 */
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("version", 1);
        jSONObject.put(TOKEN_KEY, this.token);
        jSONObject.put(EXPIRES_AT_KEY, this.expires.getTime());
        jSONObject.put(PERMISSIONS_KEY, new JSONArray(this.permissions));
        jSONObject.put(DECLINED_PERMISSIONS_KEY, new JSONArray(this.declinedPermissions));
        jSONObject.put(LAST_REFRESH_KEY, this.lastRefresh.getTime());
        jSONObject.put(SOURCE_KEY, this.source.name());
        jSONObject.put(APPLICATION_ID_KEY, this.applicationId);
        jSONObject.put("user_id", this.userId);
        return jSONObject;
    }

    static AccessToken createFromJSONObject(JSONObject jSONObject) throws JSONException {
        if (jSONObject.getInt("version") > 1) {
            throw new FacebookException("Unknown AccessToken serialization format.");
        }
        String string = jSONObject.getString(TOKEN_KEY);
        Date date = new Date(jSONObject.getLong(EXPIRES_AT_KEY));
        JSONArray jSONArray = jSONObject.getJSONArray(PERMISSIONS_KEY);
        JSONArray jSONArray2 = jSONObject.getJSONArray(DECLINED_PERMISSIONS_KEY);
        Date date2 = new Date(jSONObject.getLong(LAST_REFRESH_KEY));
        AccessToken accessToken = new AccessToken(string, jSONObject.getString(APPLICATION_ID_KEY), jSONObject.getString("user_id"), z.a(jSONArray), z.a(jSONArray2), AccessTokenSource.valueOf(jSONObject.getString(SOURCE_KEY)), date, date2);
        return accessToken;
    }

    /* access modifiers changed from: private */
    public static AccessToken createFromBundle(List<String> list, Bundle bundle, AccessTokenSource accessTokenSource, Date date, String str) {
        String string = bundle.getString(ACCESS_TOKEN_KEY);
        Date a2 = z.a(bundle, EXPIRES_IN_KEY, date);
        String string2 = bundle.getString("user_id");
        if (z.a(string) || a2 == null) {
            return null;
        }
        AccessToken accessToken = new AccessToken(string, str, string2, list, null, accessTokenSource, a2, new Date());
        return accessToken;
    }

    private String tokenToString() {
        if (this.token == null) {
            return "null";
        }
        return f.a(LoggingBehavior.INCLUDE_ACCESS_TOKENS) ? this.token : "ACCESS_TOKEN_REMOVED";
    }

    private void appendPermissions(StringBuilder sb) {
        sb.append(" permissions:");
        if (this.permissions == null) {
            sb.append("null");
            return;
        }
        sb.append("[");
        sb.append(TextUtils.join(", ", this.permissions));
        sb.append("]");
    }

    AccessToken(Parcel parcel) {
        this.expires = new Date(parcel.readLong());
        ArrayList arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        this.permissions = Collections.unmodifiableSet(new HashSet(arrayList));
        arrayList.clear();
        parcel.readStringList(arrayList);
        this.declinedPermissions = Collections.unmodifiableSet(new HashSet(arrayList));
        this.token = parcel.readString();
        this.source = AccessTokenSource.valueOf(parcel.readString());
        this.lastRefresh = new Date(parcel.readLong());
        this.applicationId = parcel.readString();
        this.userId = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.expires.getTime());
        parcel.writeStringList(new ArrayList(this.permissions));
        parcel.writeStringList(new ArrayList(this.declinedPermissions));
        parcel.writeString(this.token);
        parcel.writeString(this.source.name());
        parcel.writeLong(this.lastRefresh.getTime());
        parcel.writeString(this.applicationId);
        parcel.writeString(this.userId);
    }
}
