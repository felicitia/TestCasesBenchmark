package com.salesforce.marketingcloud.notifications;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.salesforce.marketingcloud.notifications.NotificationMessage.Sound;
import com.salesforce.marketingcloud.notifications.NotificationMessage.Trigger;
import com.salesforce.marketingcloud.notifications.NotificationMessage.Type;
import java.util.Map;

final class b extends a {
    public static final Creator<b> CREATOR = new Creator<b>() {
        /* renamed from: a */
        public b createFromParcel(Parcel parcel) {
            Parcel parcel2 = parcel;
            b bVar = new b(parcel.readString(), parcel.readInt() == 0 ? parcel.readString() : null, parcel.readInt(), parcel.readString(), Sound.valueOf(parcel.readString()), parcel.readInt() == 0 ? parcel.readString() : null, parcel.readInt(), parcel.readInt() == 0 ? parcel.readString() : null, parcel.readInt() == 0 ? parcel.readString() : null, Type.valueOf(parcel.readString()), Trigger.valueOf(parcel.readString()), parcel.readInt() == 0 ? parcel.readString() : null, parcel.readInt() == 0 ? parcel.readString() : null, parcel.readInt() == 0 ? parcel.readString() : null, parcel2.readHashMap(String.class.getClassLoader()), parcel.readInt() == 0 ? parcel.readString() : null, parcel2.readBundle(Bundle.class.getClassLoader()));
            return bVar;
        }

        /* renamed from: a */
        public b[] newArray(int i) {
            return new b[i];
        }
    };

    b(String str, String str2, int i, String str3, Sound sound, String str4, int i2, String str5, String str6, Type type, Trigger trigger, String str7, String str8, String str9, Map<String, String> map, String str10, Bundle bundle) {
        super(str, str2, i, str3, sound, str4, i2, str5, str6, type, trigger, str7, str8, str9, map, str10, bundle);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id());
        if (regionId() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(regionId());
        }
        parcel.writeInt(notificationId());
        parcel.writeString(alert());
        parcel.writeString(sound().name());
        if (soundName() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(soundName());
        }
        parcel.writeInt(smallIconResId());
        if (title() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(title());
        }
        if (subTitle() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(subTitle());
        }
        parcel.writeString(type().name());
        parcel.writeString(trigger().name());
        if (url() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(url());
        }
        if (mediaUrl() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(mediaUrl());
        }
        if (mediaAltText() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(mediaAltText());
        }
        parcel.writeMap(customKeys());
        if (custom() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(custom());
        }
        parcel.writeBundle(payload());
    }
}
