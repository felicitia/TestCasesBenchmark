package com.facebook;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.GraphMeRequestWithCacheCallback;
import com.facebook.internal.Validate;
import org.json.JSONException;
import org.json.JSONObject;

public final class Profile implements Parcelable {
    public static final Creator<Profile> CREATOR = new Creator() {
        public Profile createFromParcel(Parcel parcel) {
            return new Profile(parcel);
        }

        public Profile[] newArray(int i) {
            return new Profile[i];
        }
    };
    private final String firstName;
    private final String id;
    private final String lastName;
    private final Uri linkUri;
    private final String middleName;
    private final String name;

    public int describeContents() {
        return 0;
    }

    public static Profile getCurrentProfile() {
        return ProfileManager.getInstance().getCurrentProfile();
    }

    public static void setCurrentProfile(Profile profile) {
        ProfileManager.getInstance().setCurrentProfile(profile);
    }

    public static void fetchProfileForCurrentAccessToken() {
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        if (currentAccessToken == null) {
            setCurrentProfile(null);
        } else {
            Utility.getGraphMeRequestWithCacheAsync(currentAccessToken.getToken(), new GraphMeRequestWithCacheCallback() {
                public void onFailure(FacebookException facebookException) {
                }

                public void onSuccess(JSONObject jSONObject) {
                    String optString = jSONObject.optString("id");
                    if (optString != null) {
                        String optString2 = jSONObject.optString("link");
                        Profile profile = new Profile(optString, jSONObject.optString("first_name"), jSONObject.optString("middle_name"), jSONObject.optString("last_name"), jSONObject.optString("name"), optString2 != null ? Uri.parse(optString2) : null);
                        Profile.setCurrentProfile(profile);
                    }
                }
            });
        }
    }

    public Profile(String str, String str2, String str3, String str4, String str5, Uri uri) {
        Validate.notNullOrEmpty(str, "id");
        this.id = str;
        this.firstName = str2;
        this.middleName = str3;
        this.lastName = str4;
        this.name = str5;
        this.linkUri = uri;
    }

    public String getId() {
        return this.id;
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
            r0 = 0
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
            jSONObject.put("middle_name", this.middleName);
            jSONObject.put("last_name", this.lastName);
            jSONObject.put("name", this.name);
            if (this.linkUri == null) {
                return jSONObject;
            }
            jSONObject.put("link_uri", this.linkUri.toString());
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    Profile(JSONObject jSONObject) {
        Uri uri = null;
        this.id = jSONObject.optString("id", null);
        this.firstName = jSONObject.optString("first_name", null);
        this.middleName = jSONObject.optString("middle_name", null);
        this.lastName = jSONObject.optString("last_name", null);
        this.name = jSONObject.optString("name", null);
        String optString = jSONObject.optString("link_uri", null);
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
