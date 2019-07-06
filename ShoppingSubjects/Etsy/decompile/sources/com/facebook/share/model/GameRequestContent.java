package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.util.List;

public final class GameRequestContent implements ShareModel {
    public static final Creator<GameRequestContent> CREATOR = new Creator<GameRequestContent>() {
        /* renamed from: a */
        public GameRequestContent createFromParcel(Parcel parcel) {
            return new GameRequestContent(parcel);
        }

        /* renamed from: a */
        public GameRequestContent[] newArray(int i) {
            return new GameRequestContent[i];
        }
    };
    private final ActionType actionType;
    private final String data;
    private final Filters filters;
    private final String message;
    private final String objectId;
    private final List<String> recipients;
    private final List<String> suggestions;
    private final String title;

    public enum ActionType {
        SEND,
        ASKFOR,
        TURN
    }

    public enum Filters {
        APP_USERS,
        APP_NON_USERS
    }

    public static class a {
        /* access modifiers changed from: private */
        public String a;
        /* access modifiers changed from: private */
        public List<String> b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d;
        /* access modifiers changed from: private */
        public ActionType e;
        /* access modifiers changed from: private */
        public String f;
        /* access modifiers changed from: private */
        public Filters g;
        /* access modifiers changed from: private */
        public List<String> h;
    }

    public int describeContents() {
        return 0;
    }

    private GameRequestContent(a aVar) {
        this.message = aVar.a;
        this.recipients = aVar.b;
        this.title = aVar.d;
        this.data = aVar.c;
        this.actionType = aVar.e;
        this.objectId = aVar.f;
        this.filters = aVar.g;
        this.suggestions = aVar.h;
    }

    GameRequestContent(Parcel parcel) {
        this.message = parcel.readString();
        this.recipients = parcel.createStringArrayList();
        this.title = parcel.readString();
        this.data = parcel.readString();
        this.actionType = (ActionType) parcel.readSerializable();
        this.objectId = parcel.readString();
        this.filters = (Filters) parcel.readSerializable();
        this.suggestions = parcel.createStringArrayList();
        parcel.readStringList(this.suggestions);
    }

    public String getMessage() {
        return this.message;
    }

    public String getTo() {
        if (getRecipients() != null) {
            return TextUtils.join(",", getRecipients());
        }
        return null;
    }

    public List<String> getRecipients() {
        return this.recipients;
    }

    public String getTitle() {
        return this.title;
    }

    public String getData() {
        return this.data;
    }

    public ActionType getActionType() {
        return this.actionType;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public Filters getFilters() {
        return this.filters;
    }

    public List<String> getSuggestions() {
        return this.suggestions;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.message);
        parcel.writeStringList(this.recipients);
        parcel.writeString(this.title);
        parcel.writeString(this.data);
        parcel.writeSerializable(this.actionType);
        parcel.writeString(this.objectId);
        parcel.writeSerializable(this.filters);
        parcel.writeStringList(this.suggestions);
    }
}
