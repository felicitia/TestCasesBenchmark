package com.facebook;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.internal.aa;
import com.facebook.internal.o;
import com.facebook.internal.z;
import com.facebook.internal.z.a;
import org.json.JSONException;
import org.json.JSONObject;

public final class Profile implements Parcelable {
    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        /* renamed from: a */
        public Profile createFromParcel(Parcel parcel) {
            return new Profile(parcel);
        }

        /* renamed from: a */
        public Profile[] newArray(int i) {
            return new Profile[i];
        }
    };
    private static final String FIRST_NAME_KEY = "first_name";
    private static final String ID_KEY = "id";
    private static final String LAST_NAME_KEY = "last_name";
    private static final String LINK_URI_KEY = "link_uri";
    private static final String MIDDLE_NAME_KEY = "middle_name";
    private static final String NAME_KEY = "name";
    /* access modifiers changed from: private */
    public static final String TAG = "Profile";
    @Nullable
    private final String firstName;
    @Nullable
    private final String id;
    @Nullable
    private final String lastName;
    @Nullable
    private final Uri linkUri;
    @Nullable
    private final String middleName;
    @Nullable
    private final String name;

    public int describeContents() {
        return 0;
    }

    public static Profile getCurrentProfile() {
        return j.a().b();
    }

    public static void setCurrentProfile(@Nullable Profile profile) {
        j.a().a(profile);
    }

    public static void fetchProfileForCurrentAccessToken() {
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        if (!AccessToken.isCurrentAccessTokenActive()) {
            setCurrentProfile(null);
        } else {
            z.a(currentAccessToken.getToken(), (a) new a() {
                public void a(JSONObject jSONObject) {
                    String optString = jSONObject.optString("id");
                    if (optString != null) {
                        String optString2 = jSONObject.optString("link");
                        Profile profile = new Profile(optString, jSONObject.optString("first_name"), jSONObject.optString(Profile.MIDDLE_NAME_KEY), jSONObject.optString("last_name"), jSONObject.optString("name"), optString2 != null ? Uri.parse(optString2) : null);
                        Profile.setCurrentProfile(profile);
                    }
                }

                public void a(FacebookException facebookException) {
                    String access$000 = Profile.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Got unexpected exception: ");
                    sb.append(facebookException);
                    Log.e(access$000, sb.toString());
                }
            });
        }
    }

    public Profile(String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Uri uri) {
        aa.a(str, "id");
        this.id = str;
        this.firstName = str2;
        this.middleName = str3;
        this.lastName = str4;
        this.name = str5;
        this.linkUri = uri;
    }

    public Uri getProfilePictureUri(int i, int i2) {
        return o.a(this.id, i, i2);
    }

    public String getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getName() {
        return this.name;
    }

    public Uri getLinkUri() {
        return this.linkUri;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r5.firstName == null) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0031, code lost:
        if (r5.middleName == null) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0044, code lost:
        if (r5.lastName == null) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0057, code lost:
        if (r5.name == null) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x006a, code lost:
        if (r5.linkUri == null) goto L_0x0075;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r4 != r5) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.facebook.Profile
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            com.facebook.Profile r5 = (com.facebook.Profile) r5
            java.lang.String r1 = r4.id
            java.lang.String r3 = r5.id
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0021
            java.lang.String r1 = r4.firstName
            if (r1 != 0) goto L_0x0021
            java.lang.String r5 = r5.firstName
            if (r5 != 0) goto L_0x001f
            goto L_0x0075
        L_0x001f:
            r0 = r2
            goto L_0x0075
        L_0x0021:
            java.lang.String r1 = r4.firstName
            java.lang.String r3 = r5.firstName
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0034
            java.lang.String r1 = r4.middleName
            if (r1 != 0) goto L_0x0034
            java.lang.String r5 = r5.middleName
            if (r5 != 0) goto L_0x001f
            goto L_0x0075
        L_0x0034:
            java.lang.String r1 = r4.middleName
            java.lang.String r3 = r5.middleName
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0047
            java.lang.String r1 = r4.lastName
            if (r1 != 0) goto L_0x0047
            java.lang.String r5 = r5.lastName
            if (r5 != 0) goto L_0x001f
            goto L_0x0075
        L_0x0047:
            java.lang.String r1 = r4.lastName
            java.lang.String r3 = r5.lastName
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x005a
            java.lang.String r1 = r4.name
            if (r1 != 0) goto L_0x005a
            java.lang.String r5 = r5.name
            if (r5 != 0) goto L_0x001f
            goto L_0x0075
        L_0x005a:
            java.lang.String r1 = r4.name
            java.lang.String r3 = r5.name
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x006d
            android.net.Uri r1 = r4.linkUri
            if (r1 != 0) goto L_0x006d
            android.net.Uri r5 = r5.linkUri
            if (r5 != 0) goto L_0x001f
            goto L_0x0075
        L_0x006d:
            android.net.Uri r0 = r4.linkUri
            android.net.Uri r5 = r5.linkUri
            boolean r0 = r0.equals(r5)
        L_0x0075:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.Profile.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int hashCode = 527 + this.id.hashCode();
        if (this.firstName != null) {
            hashCode = (hashCode * 31) + this.firstName.hashCode();
        }
        if (this.middleName != null) {
            hashCode = (hashCode * 31) + this.middleName.hashCode();
        }
        if (this.lastName != null) {
            hashCode = (hashCode * 31) + this.lastName.hashCode();
        }
        if (this.name != null) {
            hashCode = (hashCode * 31) + this.name.hashCode();
        }
        return this.linkUri != null ? (hashCode * 31) + this.linkUri.hashCode() : hashCode;
    }

    /* access modifiers changed from: 0000 */
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.id);
            jSONObject.put("first_name", this.firstName);
            jSONObject.put(MIDDLE_NAME_KEY, this.middleName);
            jSONObject.put("last_name", this.lastName);
            jSONObject.put("name", this.name);
            if (this.linkUri == null) {
                return jSONObject;
            }
            jSONObject.put(LINK_URI_KEY, this.linkUri.toString());
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    Profile(JSONObject jSONObject) {
        Uri uri = null;
        this.id = jSONObject.optString("id", null);
        this.firstName = jSONObject.optString("first_name", null);
        this.middleName = jSONObject.optString(MIDDLE_NAME_KEY, null);
        this.lastName = jSONObject.optString("last_name", null);
        this.name = jSONObject.optString("name", null);
        String optString = jSONObject.optString(LINK_URI_KEY, null);
        if (optString != null) {
            uri = Uri.parse(optString);
        }
        this.linkUri = uri;
    }

    private Profile(Parcel parcel) {
        Uri uri;
        this.id = parcel.readString();
        this.firstName = parcel.readString();
        this.middleName = parcel.readString();
        this.lastName = parcel.readString();
        this.name = parcel.readString();
        String readString = parcel.readString();
        if (readString == null) {
            uri = null;
        } else {
            uri = Uri.parse(readString);
        }
        this.linkUri = uri;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.firstName);
        parcel.writeString(this.middleName);
        parcel.writeString(this.lastName);
        parcel.writeString(this.name);
        parcel.writeString(this.linkUri == null ? null : this.linkUri.toString());
    }
}