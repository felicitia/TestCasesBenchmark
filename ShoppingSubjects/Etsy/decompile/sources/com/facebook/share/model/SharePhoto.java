package com.facebook.share.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.share.model.ShareMedia.Type;
import java.util.ArrayList;
import java.util.List;

public final class SharePhoto extends ShareMedia {
    public static final Creator<SharePhoto> CREATOR = new Creator<SharePhoto>() {
        /* renamed from: a */
        public SharePhoto createFromParcel(Parcel parcel) {
            return new SharePhoto(parcel);
        }

        /* renamed from: a */
        public SharePhoto[] newArray(int i) {
            return new SharePhoto[i];
        }
    };
    private final Bitmap bitmap;
    private final String caption;
    private final Uri imageUrl;
    private final boolean userGenerated;

    public static final class a extends com.facebook.share.model.ShareMedia.a<SharePhoto, a> {
        /* access modifiers changed from: private */
        public Bitmap a;
        /* access modifiers changed from: private */
        public Uri b;
        /* access modifiers changed from: private */
        public boolean c;
        /* access modifiers changed from: private */
        public String d;

        public a a(@Nullable Bitmap bitmap) {
            this.a = bitmap;
            return this;
        }

        public a a(@Nullable Uri uri) {
            this.b = uri;
            return this;
        }

        public a a(boolean z) {
            this.c = z;
            return this;
        }

        public a a(@Nullable String str) {
            this.d = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Uri a() {
            return this.b;
        }

        /* access modifiers changed from: 0000 */
        public Bitmap b() {
            return this.a;
        }

        public SharePhoto c() {
            return new SharePhoto(this);
        }

        public a a(SharePhoto sharePhoto) {
            if (sharePhoto == null) {
                return this;
            }
            return ((a) super.a(sharePhoto)).a(sharePhoto.getBitmap()).a(sharePhoto.getImageUrl()).a(sharePhoto.getUserGenerated()).a(sharePhoto.getCaption());
        }

        /* access modifiers changed from: 0000 */
        public a b(Parcel parcel) {
            return a((SharePhoto) parcel.readParcelable(SharePhoto.class.getClassLoader()));
        }

        static void a(Parcel parcel, int i, List<SharePhoto> list) {
            ShareMedia[] shareMediaArr = new ShareMedia[list.size()];
            for (int i2 = 0; i2 < list.size(); i2++) {
                shareMediaArr[i2] = (ShareMedia) list.get(i2);
            }
            parcel.writeParcelableArray(shareMediaArr, i);
        }

        static List<SharePhoto> c(Parcel parcel) {
            List<ShareMedia> a2 = a(parcel);
            ArrayList arrayList = new ArrayList();
            for (ShareMedia shareMedia : a2) {
                if (shareMedia instanceof SharePhoto) {
                    arrayList.add((SharePhoto) shareMedia);
                }
            }
            return arrayList;
        }
    }

    public int describeContents() {
        return 0;
    }

    private SharePhoto(a aVar) {
        super((com.facebook.share.model.ShareMedia.a) aVar);
        this.bitmap = aVar.a;
        this.imageUrl = aVar.b;
        this.userGenerated = aVar.c;
        this.caption = aVar.d;
    }

    SharePhoto(Parcel parcel) {
        super(parcel);
        this.bitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.imageUrl = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.userGenerated = parcel.readByte() != 0;
        this.caption = parcel.readString();
    }

    @Nullable
    public Bitmap getBitmap() {
        return this.bitmap;
    }

    @Nullable
    public Uri getImageUrl() {
        return this.imageUrl;
    }

    public boolean getUserGenerated() {
        return this.userGenerated;
    }

    public String getCaption() {
        return this.caption;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.bitmap, 0);
        parcel.writeParcelable(this.imageUrl, 0);
        parcel.writeByte(this.userGenerated ? (byte) 1 : 0);
        parcel.writeString(this.caption);
    }

    public Type getMediaType() {
        return Type.PHOTO;
    }
}
