package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WishNotificationPreference extends BaseModel implements Parcelable {
    public static final Creator<WishNotificationPreference> CREATOR = new Creator<WishNotificationPreference>() {
        public WishNotificationPreference createFromParcel(Parcel parcel) {
            return new WishNotificationPreference(parcel);
        }

        public WishNotificationPreference[] newArray(int i) {
            return new WishNotificationPreference[i];
        }
    };
    private String mDescription;
    private int mIndex;
    private String mName;
    private ArrayList<Boolean> mPreferenceTypesSupported;
    private ArrayList<Boolean> mPreferencesSelected;

    public enum PreferenceType {
        EMAIL(0),
        PUSH(1);
        
        private int mValue;

        private PreferenceType(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }

        public static PreferenceType fromInteger(int i) {
            switch (i) {
                case 0:
                    return EMAIL;
                case 1:
                    return PUSH;
                default:
                    return null;
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishNotificationPreference(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        int i;
        this.mPreferencesSelected = new ArrayList<>();
        this.mName = jSONObject.getString("name");
        this.mDescription = jSONObject.getString("description");
        JSONArray jSONArray = jSONObject.getJSONArray("value");
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            this.mPreferencesSelected.add(Boolean.valueOf(jSONArray.getBoolean(i2)));
        }
        this.mIndex = jSONObject.getInt("idx");
        this.mPreferenceTypesSupported = new ArrayList<>();
        if (jSONObject.has("supports")) {
            JSONArray jSONArray2 = jSONObject.getJSONArray("supports");
            int i3 = 0;
            while (true) {
                if (jSONArray2 == null) {
                    i = 0;
                } else {
                    i = jSONArray2.length();
                }
                if (i3 < i) {
                    this.mPreferenceTypesSupported.add(Boolean.valueOf(jSONArray2.getBoolean(i3)));
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    protected WishNotificationPreference(Parcel parcel) {
        this.mPreferencesSelected = parcel.readArrayList(Boolean.class.getClassLoader());
        this.mDescription = parcel.readString();
        this.mIndex = parcel.readInt();
        this.mName = parcel.readString();
    }

    public Boolean isPreferenceSelected(int i) {
        return (Boolean) this.mPreferencesSelected.get(i);
    }

    public boolean isPreferenceSupported(PreferenceType preferenceType) {
        boolean z = false;
        if (preferenceType == null || this.mPreferenceTypesSupported == null) {
            return false;
        }
        int ordinal = preferenceType.ordinal();
        if (ordinal < this.mPreferenceTypesSupported.size()) {
            z = ((Boolean) this.mPreferenceTypesSupported.get(ordinal)).booleanValue();
        }
        return z;
    }

    public boolean isPreferenceSelected(PreferenceType preferenceType) {
        return ((Boolean) this.mPreferencesSelected.get(preferenceType.ordinal())).booleanValue();
    }

    public ArrayList<Boolean> getPreferencesSelected() {
        return this.mPreferencesSelected;
    }

    public void setPreferencesSelected(ArrayList<Boolean> arrayList) {
        this.mPreferencesSelected = arrayList;
    }

    public String getName() {
        return this.mName;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.mPreferencesSelected);
        parcel.writeInt(this.mIndex);
        parcel.writeString(this.mName);
        parcel.writeString(this.mDescription);
    }
}
