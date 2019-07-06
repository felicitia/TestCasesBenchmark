package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.support.annotation.Nullable;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareContent.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ShareContent<P extends ShareContent, E extends a> implements ShareModel {
    private final Uri contentUrl;
    private final ShareHashtag hashtag;
    private final String pageId;
    private final List<String> peopleIds;
    private final String placeId;
    private final String ref;

    public static abstract class a<P extends ShareContent, E extends a> {
        /* access modifiers changed from: private */
        public Uri a;
        /* access modifiers changed from: private */
        public List<String> b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d;
        /* access modifiers changed from: private */
        public String e;
        /* access modifiers changed from: private */
        public ShareHashtag f;

        public E a(@Nullable Uri uri) {
            this.a = uri;
            return this;
        }

        public E a(@Nullable List<String> list) {
            this.b = list == null ? null : Collections.unmodifiableList(list);
            return this;
        }

        public E h(@Nullable String str) {
            this.c = str;
            return this;
        }

        public E i(@Nullable String str) {
            this.d = str;
            return this;
        }

        public E j(@Nullable String str) {
            this.e = str;
            return this;
        }

        public E a(P p) {
            if (p == null) {
                return this;
            }
            return a(p.getContentUrl()).a(p.getPeopleIds()).h(p.getPlaceId()).i(p.getPageId()).j(p.getRef());
        }
    }

    public int describeContents() {
        return 0;
    }

    protected ShareContent(a aVar) {
        this.contentUrl = aVar.a;
        this.peopleIds = aVar.b;
        this.placeId = aVar.c;
        this.pageId = aVar.d;
        this.ref = aVar.e;
        this.hashtag = aVar.f;
    }

    protected ShareContent(Parcel parcel) {
        this.contentUrl = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.peopleIds = readUnmodifiableStringList(parcel);
        this.placeId = parcel.readString();
        this.pageId = parcel.readString();
        this.ref = parcel.readString();
        this.hashtag = new com.facebook.share.model.ShareHashtag.a().a(parcel).a();
    }

    @Nullable
    public Uri getContentUrl() {
        return this.contentUrl;
    }

    @Nullable
    public List<String> getPeopleIds() {
        return this.peopleIds;
    }

    @Nullable
    public String getPlaceId() {
        return this.placeId;
    }

    @Nullable
    public String getPageId() {
        return this.pageId;
    }

    @Nullable
    public String getRef() {
        return this.ref;
    }

    @Nullable
    public ShareHashtag getShareHashtag() {
        return this.hashtag;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.contentUrl, 0);
        parcel.writeStringList(this.peopleIds);
        parcel.writeString(this.placeId);
        parcel.writeString(this.pageId);
        parcel.writeString(this.ref);
        parcel.writeParcelable(this.hashtag, 0);
    }

    private List<String> readUnmodifiableStringList(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        if (arrayList.size() == 0) {
            return null;
        }
        return Collections.unmodifiableList(arrayList);
    }
}
