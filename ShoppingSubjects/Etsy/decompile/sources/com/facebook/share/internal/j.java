package com.facebook.share.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import com.etsy.android.lib.convos.Draft;
import com.facebook.FacebookException;
import com.facebook.f;
import com.facebook.internal.aa;
import com.facebook.internal.z;
import com.facebook.share.model.ShareCameraEffectContent;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMedia;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareMessengerActionButton;
import com.facebook.share.model.ShareMessengerGenericTemplateContent;
import com.facebook.share.model.ShareMessengerMediaTemplateContent;
import com.facebook.share.model.ShareMessengerOpenGraphMusicTemplateContent;
import com.facebook.share.model.ShareMessengerURLActionButton;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.ShareOpenGraphValueContainer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareStoryContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import java.util.List;
import java.util.Locale;

/* compiled from: ShareContentValidation */
public class j {
    private static b a;
    private static b b;
    private static b c;

    /* compiled from: ShareContentValidation */
    private static class a extends b {
        private a() {
            super();
        }

        public void a(ShareStoryContent shareStoryContent) {
            j.b(shareStoryContent, (b) this);
        }
    }

    /* compiled from: ShareContentValidation */
    private static class b {
        private boolean a;

        private b() {
            this.a = false;
        }

        public void a(ShareLinkContent shareLinkContent) {
            j.b(shareLinkContent, this);
        }

        public void a(SharePhotoContent sharePhotoContent) {
            j.b(sharePhotoContent, this);
        }

        public void a(ShareVideoContent shareVideoContent) {
            j.b(shareVideoContent, this);
        }

        public void a(ShareMediaContent shareMediaContent) {
            j.b(shareMediaContent, this);
        }

        public void a(ShareCameraEffectContent shareCameraEffectContent) {
            j.b(shareCameraEffectContent, this);
        }

        public void a(ShareOpenGraphContent shareOpenGraphContent) {
            this.a = true;
            j.b(shareOpenGraphContent, this);
        }

        public void a(ShareOpenGraphAction shareOpenGraphAction) {
            j.b(shareOpenGraphAction, this);
        }

        public void a(ShareOpenGraphObject shareOpenGraphObject) {
            j.b(shareOpenGraphObject, this);
        }

        public void a(ShareOpenGraphValueContainer shareOpenGraphValueContainer, boolean z) {
            j.b(shareOpenGraphValueContainer, this, z);
        }

        public void a(SharePhoto sharePhoto) {
            j.d(sharePhoto, this);
        }

        public void a(ShareVideo shareVideo) {
            j.b(shareVideo, this);
        }

        public void a(ShareMedia shareMedia) {
            j.a(shareMedia, this);
        }

        public void a(ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent) {
            j.b(shareMessengerOpenGraphMusicTemplateContent);
        }

        public void a(ShareMessengerGenericTemplateContent shareMessengerGenericTemplateContent) {
            j.b(shareMessengerGenericTemplateContent);
        }

        public void a(ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent) {
            j.b(shareMessengerMediaTemplateContent);
        }

        public void a(ShareStoryContent shareStoryContent) {
            j.b(shareStoryContent, this);
        }

        public boolean a() {
            return this.a;
        }
    }

    /* compiled from: ShareContentValidation */
    private static class c extends b {
        private c() {
            super();
        }

        public void a(ShareVideoContent shareVideoContent) {
            throw new FacebookException("Cannot share ShareVideoContent via web sharing dialogs");
        }

        public void a(ShareMediaContent shareMediaContent) {
            throw new FacebookException("Cannot share ShareMediaContent via web sharing dialogs");
        }

        public void a(SharePhoto sharePhoto) {
            j.e(sharePhoto, this);
        }
    }

    public static void a(ShareContent shareContent) {
        a(shareContent, b());
    }

    public static void b(ShareContent shareContent) {
        a(shareContent, b());
    }

    public static void c(ShareContent shareContent) {
        a(shareContent, c());
    }

    public static void d(ShareContent shareContent) {
        a(shareContent, a());
    }

    private static b a() {
        if (c == null) {
            c = new a();
        }
        return c;
    }

    private static b b() {
        if (b == null) {
            b = new b();
        }
        return b;
    }

    private static b c() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    private static void a(ShareContent shareContent, b bVar) throws FacebookException {
        if (shareContent == null) {
            throw new FacebookException("Must provide non-null content to share");
        } else if (shareContent instanceof ShareLinkContent) {
            bVar.a((ShareLinkContent) shareContent);
        } else if (shareContent instanceof SharePhotoContent) {
            bVar.a((SharePhotoContent) shareContent);
        } else if (shareContent instanceof ShareVideoContent) {
            bVar.a((ShareVideoContent) shareContent);
        } else if (shareContent instanceof ShareOpenGraphContent) {
            bVar.a((ShareOpenGraphContent) shareContent);
        } else if (shareContent instanceof ShareMediaContent) {
            bVar.a((ShareMediaContent) shareContent);
        } else if (shareContent instanceof ShareCameraEffectContent) {
            bVar.a((ShareCameraEffectContent) shareContent);
        } else if (shareContent instanceof ShareMessengerOpenGraphMusicTemplateContent) {
            bVar.a((ShareMessengerOpenGraphMusicTemplateContent) shareContent);
        } else if (shareContent instanceof ShareMessengerMediaTemplateContent) {
            bVar.a((ShareMessengerMediaTemplateContent) shareContent);
        } else if (shareContent instanceof ShareMessengerGenericTemplateContent) {
            bVar.a((ShareMessengerGenericTemplateContent) shareContent);
        } else if (shareContent instanceof ShareStoryContent) {
            bVar.a((ShareStoryContent) shareContent);
        }
    }

    /* access modifiers changed from: private */
    public static void b(ShareStoryContent shareStoryContent, b bVar) {
        if (shareStoryContent == null || (shareStoryContent.getBackgroundAsset() == null && shareStoryContent.getStickerAsset() == null)) {
            throw new FacebookException("Must pass the Facebook app a background asset, a sticker asset, or both");
        }
        if (shareStoryContent.getBackgroundAsset() != null) {
            bVar.a(shareStoryContent.getBackgroundAsset());
        }
        if (shareStoryContent.getStickerAsset() != null) {
            bVar.a(shareStoryContent.getStickerAsset());
        }
    }

    /* access modifiers changed from: private */
    public static void b(ShareLinkContent shareLinkContent, b bVar) {
        Uri imageUrl = shareLinkContent.getImageUrl();
        if (imageUrl != null && !z.b(imageUrl)) {
            throw new FacebookException("Image Url must be an http:// or https:// url");
        }
    }

    /* access modifiers changed from: private */
    public static void b(SharePhotoContent sharePhotoContent, b bVar) {
        List<SharePhoto> photos = sharePhotoContent.getPhotos();
        if (photos == null || photos.isEmpty()) {
            throw new FacebookException("Must specify at least one Photo in SharePhotoContent.");
        } else if (photos.size() > 6) {
            throw new FacebookException(String.format(Locale.ROOT, "Cannot add more than %d photos.", new Object[]{Integer.valueOf(6)}));
        } else {
            for (SharePhoto a2 : photos) {
                bVar.a(a2);
            }
        }
    }

    private static void a(SharePhoto sharePhoto) {
        if (sharePhoto == null) {
            throw new FacebookException("Cannot share a null SharePhoto");
        }
        Bitmap bitmap = sharePhoto.getBitmap();
        Uri imageUrl = sharePhoto.getImageUrl();
        if (bitmap == null && imageUrl == null) {
            throw new FacebookException("SharePhoto does not have a Bitmap or ImageUrl specified");
        }
    }

    private static void c(SharePhoto sharePhoto, b bVar) {
        a(sharePhoto);
        Bitmap bitmap = sharePhoto.getBitmap();
        Uri imageUrl = sharePhoto.getImageUrl();
        if (bitmap == null && z.b(imageUrl) && !bVar.a()) {
            throw new FacebookException("Cannot set the ImageUrl of a SharePhoto to the Uri of an image on the web when sharing SharePhotoContent");
        }
    }

    /* access modifiers changed from: private */
    public static void d(SharePhoto sharePhoto, b bVar) {
        c(sharePhoto, bVar);
        if (sharePhoto.getBitmap() != null || !z.b(sharePhoto.getImageUrl())) {
            aa.e(f.f());
        }
    }

    /* access modifiers changed from: private */
    public static void e(SharePhoto sharePhoto, b bVar) {
        a(sharePhoto);
    }

    /* access modifiers changed from: private */
    public static void b(ShareVideoContent shareVideoContent, b bVar) {
        bVar.a(shareVideoContent.getVideo());
        SharePhoto previewPhoto = shareVideoContent.getPreviewPhoto();
        if (previewPhoto != null) {
            bVar.a(previewPhoto);
        }
    }

    /* access modifiers changed from: private */
    public static void b(ShareVideo shareVideo, b bVar) {
        if (shareVideo == null) {
            throw new FacebookException("Cannot share a null ShareVideo");
        }
        Uri localUrl = shareVideo.getLocalUrl();
        if (localUrl == null) {
            throw new FacebookException("ShareVideo does not have a LocalUrl specified");
        } else if (!z.c(localUrl) && !z.d(localUrl)) {
            throw new FacebookException("ShareVideo must reference a video that is on the device");
        }
    }

    /* access modifiers changed from: private */
    public static void b(ShareMediaContent shareMediaContent, b bVar) {
        List<ShareMedia> media = shareMediaContent.getMedia();
        if (media == null || media.isEmpty()) {
            throw new FacebookException("Must specify at least one medium in ShareMediaContent.");
        } else if (media.size() > 6) {
            throw new FacebookException(String.format(Locale.ROOT, "Cannot add more than %d media.", new Object[]{Integer.valueOf(6)}));
        } else {
            for (ShareMedia a2 : media) {
                bVar.a(a2);
            }
        }
    }

    public static void a(ShareMedia shareMedia, b bVar) {
        if (shareMedia instanceof SharePhoto) {
            bVar.a((SharePhoto) shareMedia);
        } else if (shareMedia instanceof ShareVideo) {
            bVar.a((ShareVideo) shareMedia);
        } else {
            throw new FacebookException(String.format(Locale.ROOT, "Invalid media type: %s", new Object[]{shareMedia.getClass().getSimpleName()}));
        }
    }

    /* access modifiers changed from: private */
    public static void b(ShareCameraEffectContent shareCameraEffectContent, b bVar) {
        if (z.a(shareCameraEffectContent.getEffectId())) {
            throw new FacebookException("Must specify a non-empty effectId");
        }
    }

    /* access modifiers changed from: private */
    public static void b(ShareOpenGraphContent shareOpenGraphContent, b bVar) {
        bVar.a(shareOpenGraphContent.getAction());
        String previewPropertyName = shareOpenGraphContent.getPreviewPropertyName();
        if (z.a(previewPropertyName)) {
            throw new FacebookException("Must specify a previewPropertyName.");
        } else if (shareOpenGraphContent.getAction().get(previewPropertyName) == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Property \"");
            sb.append(previewPropertyName);
            sb.append("\" was not found on the action. The name of the preview property must match the name of an action property.");
            throw new FacebookException(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    public static void b(ShareOpenGraphAction shareOpenGraphAction, b bVar) {
        if (shareOpenGraphAction == null) {
            throw new FacebookException("Must specify a non-null ShareOpenGraphAction");
        } else if (z.a(shareOpenGraphAction.getActionType())) {
            throw new FacebookException("ShareOpenGraphAction must have a non-empty actionType");
        } else {
            bVar.a(shareOpenGraphAction, false);
        }
    }

    /* access modifiers changed from: private */
    public static void b(ShareOpenGraphObject shareOpenGraphObject, b bVar) {
        if (shareOpenGraphObject == null) {
            throw new FacebookException("Cannot share a null ShareOpenGraphObject");
        }
        bVar.a(shareOpenGraphObject, true);
    }

    /* access modifiers changed from: private */
    public static void b(ShareOpenGraphValueContainer shareOpenGraphValueContainer, b bVar, boolean z) {
        for (String str : shareOpenGraphValueContainer.keySet()) {
            a(str, z);
            Object obj = shareOpenGraphValueContainer.get(str);
            if (obj instanceof List) {
                for (Object next : (List) obj) {
                    if (next == null) {
                        throw new FacebookException("Cannot put null objects in Lists in ShareOpenGraphObjects and ShareOpenGraphActions");
                    }
                    a(next, bVar);
                }
                continue;
            } else {
                a(obj, bVar);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void b(ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent) {
        if (z.a(shareMessengerOpenGraphMusicTemplateContent.getPageId())) {
            throw new FacebookException("Must specify Page Id for ShareMessengerOpenGraphMusicTemplateContent");
        } else if (shareMessengerOpenGraphMusicTemplateContent.getUrl() == null) {
            throw new FacebookException("Must specify url for ShareMessengerOpenGraphMusicTemplateContent");
        } else {
            a(shareMessengerOpenGraphMusicTemplateContent.getButton());
        }
    }

    /* access modifiers changed from: private */
    public static void b(ShareMessengerGenericTemplateContent shareMessengerGenericTemplateContent) {
        if (z.a(shareMessengerGenericTemplateContent.getPageId())) {
            throw new FacebookException("Must specify Page Id for ShareMessengerGenericTemplateContent");
        } else if (shareMessengerGenericTemplateContent.getGenericTemplateElement() == null) {
            throw new FacebookException("Must specify element for ShareMessengerGenericTemplateContent");
        } else if (z.a(shareMessengerGenericTemplateContent.getGenericTemplateElement().getTitle())) {
            throw new FacebookException("Must specify title for ShareMessengerGenericTemplateElement");
        } else {
            a(shareMessengerGenericTemplateContent.getGenericTemplateElement().getButton());
        }
    }

    /* access modifiers changed from: private */
    public static void b(ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent) {
        if (z.a(shareMessengerMediaTemplateContent.getPageId())) {
            throw new FacebookException("Must specify Page Id for ShareMessengerMediaTemplateContent");
        } else if (shareMessengerMediaTemplateContent.getMediaUrl() != null || !z.a(shareMessengerMediaTemplateContent.getAttachmentId())) {
            a(shareMessengerMediaTemplateContent.getButton());
        } else {
            throw new FacebookException("Must specify either attachmentId or mediaURL for ShareMessengerMediaTemplateContent");
        }
    }

    private static void a(ShareMessengerActionButton shareMessengerActionButton) {
        if (shareMessengerActionButton != null) {
            if (z.a(shareMessengerActionButton.getTitle())) {
                throw new FacebookException("Must specify title for ShareMessengerActionButton");
            }
            if (shareMessengerActionButton instanceof ShareMessengerURLActionButton) {
                a((ShareMessengerURLActionButton) shareMessengerActionButton);
            }
        }
    }

    private static void a(ShareMessengerURLActionButton shareMessengerURLActionButton) {
        if (shareMessengerURLActionButton.getUrl() == null) {
            throw new FacebookException("Must specify url for ShareMessengerURLActionButton");
        }
    }

    private static void a(String str, boolean z) {
        if (z) {
            String[] split = str.split(Draft.IMAGE_DELIMITER);
            if (split.length < 2) {
                throw new FacebookException("Open Graph keys must be namespaced: %s", str);
            }
            for (String isEmpty : split) {
                if (isEmpty.isEmpty()) {
                    throw new FacebookException("Invalid key found in Open Graph dictionary: %s", str);
                }
            }
        }
    }

    private static void a(Object obj, b bVar) {
        if (obj instanceof ShareOpenGraphObject) {
            bVar.a((ShareOpenGraphObject) obj);
        } else if (obj instanceof SharePhoto) {
            bVar.a((SharePhoto) obj);
        }
    }
}
