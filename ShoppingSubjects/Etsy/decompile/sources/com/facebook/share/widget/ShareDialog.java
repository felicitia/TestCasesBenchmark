package com.facebook.share.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.os.EnvironmentCompat;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.AccessToken;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.internal.f;
import com.facebook.internal.g;
import com.facebook.internal.u;
import com.facebook.internal.z;
import com.facebook.share.internal.CameraEffectFeature;
import com.facebook.share.internal.OpenGraphActionDialogFeature;
import com.facebook.share.internal.ShareDialogFeature;
import com.facebook.share.internal.ShareFeedContent;
import com.facebook.share.internal.ShareStoryFeature;
import com.facebook.share.internal.j;
import com.facebook.share.internal.k;
import com.facebook.share.internal.m;
import com.facebook.share.model.ShareCameraEffectContent;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareStoryContent;
import com.facebook.share.model.ShareVideoContent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public final class ShareDialog extends g<ShareContent, com.facebook.share.c.a> implements com.facebook.share.c {
    private static final String b = "ShareDialog";
    private static final int c = RequestCodeOffset.Share.toRequestCode();
    private boolean d;
    private boolean e;

    public enum Mode {
        AUTOMATIC,
        NATIVE,
        WEB,
        FEED
    }

    private class a extends a {
        private a() {
            super();
        }

        public Object a() {
            return Mode.NATIVE;
        }

        public boolean a(ShareContent shareContent, boolean z) {
            return (shareContent instanceof ShareCameraEffectContent) && ShareDialog.c(shareContent.getClass());
        }

        public com.facebook.internal.a a(final ShareContent shareContent) {
            j.b(shareContent);
            final com.facebook.internal.a d = ShareDialog.this.d();
            final boolean e = ShareDialog.this.e();
            f.a(d, (com.facebook.internal.f.a) new com.facebook.internal.f.a() {
                public Bundle a() {
                    return com.facebook.share.internal.g.a(d.c(), shareContent, e);
                }

                public Bundle b() {
                    return com.facebook.share.internal.b.a(d.c(), shareContent, e);
                }
            }, ShareDialog.e(shareContent.getClass()));
            return d;
        }
    }

    private class b extends a {
        private b() {
            super();
        }

        public Object a() {
            return Mode.FEED;
        }

        public boolean a(ShareContent shareContent, boolean z) {
            return (shareContent instanceof ShareLinkContent) || (shareContent instanceof ShareFeedContent);
        }

        public com.facebook.internal.a a(ShareContent shareContent) {
            Bundle bundle;
            ShareDialog.this.a(ShareDialog.this.b(), shareContent, Mode.FEED);
            com.facebook.internal.a d = ShareDialog.this.d();
            if (shareContent instanceof ShareLinkContent) {
                ShareLinkContent shareLinkContent = (ShareLinkContent) shareContent;
                j.c(shareLinkContent);
                bundle = m.b(shareLinkContent);
            } else {
                bundle = m.a((ShareFeedContent) shareContent);
            }
            f.a(d, ResponseConstants.FEED, bundle);
            return d;
        }
    }

    private class c extends a {
        private c() {
            super();
        }

        public Object a() {
            return Mode.NATIVE;
        }

        public boolean a(ShareContent shareContent, boolean z) {
            boolean z2;
            boolean z3 = false;
            if (shareContent == null || (shareContent instanceof ShareCameraEffectContent) || (shareContent instanceof ShareStoryContent)) {
                return false;
            }
            if (!z) {
                z2 = shareContent.getShareHashtag() != null ? f.a((com.facebook.internal.e) ShareDialogFeature.HASHTAG) : true;
                if ((shareContent instanceof ShareLinkContent) && !z.a(((ShareLinkContent) shareContent).getQuote())) {
                    z2 &= f.a((com.facebook.internal.e) ShareDialogFeature.LINK_SHARE_QUOTES);
                }
            } else {
                z2 = true;
            }
            if (z2 && ShareDialog.c(shareContent.getClass())) {
                z3 = true;
            }
            return z3;
        }

        public com.facebook.internal.a a(final ShareContent shareContent) {
            ShareDialog.this.a(ShareDialog.this.b(), shareContent, Mode.NATIVE);
            j.b(shareContent);
            final com.facebook.internal.a d = ShareDialog.this.d();
            final boolean e = ShareDialog.this.e();
            f.a(d, (com.facebook.internal.f.a) new com.facebook.internal.f.a() {
                public Bundle a() {
                    return com.facebook.share.internal.g.a(d.c(), shareContent, e);
                }

                public Bundle b() {
                    return com.facebook.share.internal.b.a(d.c(), shareContent, e);
                }
            }, ShareDialog.e(shareContent.getClass()));
            return d;
        }
    }

    private class d extends a {
        private d() {
            super();
        }

        public Object a() {
            return Mode.NATIVE;
        }

        public boolean a(ShareContent shareContent, boolean z) {
            return (shareContent instanceof ShareStoryContent) && ShareDialog.c(shareContent.getClass());
        }

        public com.facebook.internal.a a(final ShareContent shareContent) {
            j.d(shareContent);
            final com.facebook.internal.a d = ShareDialog.this.d();
            final boolean e = ShareDialog.this.e();
            f.a(d, (com.facebook.internal.f.a) new com.facebook.internal.f.a() {
                public Bundle a() {
                    return com.facebook.share.internal.g.a(d.c(), shareContent, e);
                }

                public Bundle b() {
                    return com.facebook.share.internal.b.a(d.c(), shareContent, e);
                }
            }, ShareDialog.e(shareContent.getClass()));
            return d;
        }
    }

    private class e extends a {
        private e() {
            super();
        }

        public Object a() {
            return Mode.WEB;
        }

        public boolean a(ShareContent shareContent, boolean z) {
            return shareContent != null && ShareDialog.b(shareContent);
        }

        public com.facebook.internal.a a(ShareContent shareContent) {
            Bundle bundle;
            ShareDialog.this.a(ShareDialog.this.b(), shareContent, Mode.WEB);
            com.facebook.internal.a d = ShareDialog.this.d();
            j.c(shareContent);
            if (shareContent instanceof ShareLinkContent) {
                bundle = m.a((ShareLinkContent) shareContent);
            } else if (shareContent instanceof SharePhotoContent) {
                bundle = m.a(a((SharePhotoContent) shareContent, d.c()));
            } else {
                bundle = m.a((ShareOpenGraphContent) shareContent);
            }
            f.a(d, b(shareContent), bundle);
            return d;
        }

        private String b(ShareContent shareContent) {
            if ((shareContent instanceof ShareLinkContent) || (shareContent instanceof SharePhotoContent)) {
                return "share";
            }
            if (shareContent instanceof ShareOpenGraphContent) {
                return "share_open_graph";
            }
            return null;
        }

        private SharePhotoContent a(SharePhotoContent sharePhotoContent, UUID uuid) {
            com.facebook.share.model.SharePhotoContent.a a = new com.facebook.share.model.SharePhotoContent.a().a(sharePhotoContent);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < sharePhotoContent.getPhotos().size(); i++) {
                SharePhoto sharePhoto = (SharePhoto) sharePhotoContent.getPhotos().get(i);
                Bitmap bitmap = sharePhoto.getBitmap();
                if (bitmap != null) {
                    com.facebook.internal.u.a a2 = u.a(uuid, bitmap);
                    sharePhoto = new com.facebook.share.model.SharePhoto.a().a(sharePhoto).a(Uri.parse(a2.a())).a((Bitmap) null).c();
                    arrayList2.add(a2);
                }
                arrayList.add(sharePhoto);
            }
            a.c(arrayList);
            u.a((Collection<com.facebook.internal.u.a>) arrayList2);
            return a.a();
        }
    }

    /* access modifiers changed from: private */
    public static boolean c(Class<? extends ShareContent> cls) {
        com.facebook.internal.e e2 = e(cls);
        return e2 != null && f.a(e2);
    }

    private static boolean d(Class<? extends ShareContent> cls) {
        return ShareLinkContent.class.isAssignableFrom(cls) || ShareOpenGraphContent.class.isAssignableFrom(cls) || (SharePhotoContent.class.isAssignableFrom(cls) && AccessToken.isCurrentAccessTokenActive());
    }

    /* access modifiers changed from: private */
    public static boolean b(ShareContent shareContent) {
        if (!d(shareContent.getClass())) {
            return false;
        }
        if (shareContent instanceof ShareOpenGraphContent) {
            try {
                k.a((ShareOpenGraphContent) shareContent);
            } catch (Exception e2) {
                z.a(b, "canShow returned false because the content of the Opem Graph object can't be shared via the web dialog", (Throwable) e2);
                return false;
            }
        }
        return true;
    }

    public ShareDialog(Fragment fragment) {
        this(new com.facebook.internal.m(fragment));
    }

    private ShareDialog(com.facebook.internal.m mVar) {
        super(mVar, c);
        this.d = false;
        this.e = true;
        k.a(c);
    }

    ShareDialog(Activity activity, int i) {
        super(activity, i);
        this.d = false;
        this.e = true;
        k.a(i);
    }

    ShareDialog(Fragment fragment, int i) {
        this(new com.facebook.internal.m(fragment), i);
    }

    ShareDialog(android.app.Fragment fragment, int i) {
        this(new com.facebook.internal.m(fragment), i);
    }

    private ShareDialog(com.facebook.internal.m mVar, int i) {
        super(mVar, i);
        this.d = false;
        this.e = true;
        k.a(i);
    }

    /* access modifiers changed from: protected */
    public void a(CallbackManagerImpl callbackManagerImpl, com.facebook.e<com.facebook.share.c.a> eVar) {
        k.a(a(), (com.facebook.d) callbackManagerImpl, eVar);
    }

    public boolean e() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public com.facebook.internal.a d() {
        return new com.facebook.internal.a(a());
    }

    /* access modifiers changed from: protected */
    public List<a> c() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new c());
        arrayList.add(new b());
        arrayList.add(new e());
        arrayList.add(new a());
        arrayList.add(new d());
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static com.facebook.internal.e e(Class<? extends ShareContent> cls) {
        if (ShareLinkContent.class.isAssignableFrom(cls)) {
            return ShareDialogFeature.SHARE_DIALOG;
        }
        if (SharePhotoContent.class.isAssignableFrom(cls)) {
            return ShareDialogFeature.PHOTOS;
        }
        if (ShareVideoContent.class.isAssignableFrom(cls)) {
            return ShareDialogFeature.VIDEO;
        }
        if (ShareOpenGraphContent.class.isAssignableFrom(cls)) {
            return OpenGraphActionDialogFeature.OG_ACTION_DIALOG;
        }
        if (ShareMediaContent.class.isAssignableFrom(cls)) {
            return ShareDialogFeature.MULTIMEDIA;
        }
        if (ShareCameraEffectContent.class.isAssignableFrom(cls)) {
            return CameraEffectFeature.SHARE_CAMERA_EFFECT;
        }
        if (ShareStoryContent.class.isAssignableFrom(cls)) {
            return ShareStoryFeature.SHARE_STORY_ASSET;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void a(Context context, ShareContent shareContent, Mode mode) {
        String str;
        if (this.e) {
            mode = Mode.AUTOMATIC;
        }
        switch (mode) {
            case AUTOMATIC:
                str = "automatic";
                break;
            case WEB:
                str = "web";
                break;
            case NATIVE:
                str = "native";
                break;
            default:
                str = EnvironmentCompat.MEDIA_UNKNOWN;
                break;
        }
        com.facebook.internal.e e2 = e(shareContent.getClass());
        String str2 = e2 == ShareDialogFeature.SHARE_DIALOG ? "status" : e2 == ShareDialogFeature.PHOTOS ? "photo" : e2 == ShareDialogFeature.VIDEO ? "video" : e2 == OpenGraphActionDialogFeature.OG_ACTION_DIALOG ? "open_graph" : EnvironmentCompat.MEDIA_UNKNOWN;
        AppEventsLogger a2 = AppEventsLogger.a(context);
        Bundle bundle = new Bundle();
        bundle.putString("fb_share_dialog_show", str);
        bundle.putString("fb_share_dialog_content_type", str2);
        a2.a("fb_share_dialog_show", (Double) null, bundle);
    }
}
