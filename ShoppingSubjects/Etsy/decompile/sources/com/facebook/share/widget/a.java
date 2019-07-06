package com.facebook.share.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.os.EnvironmentCompat;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.d;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.internal.e;
import com.facebook.internal.f;
import com.facebook.internal.g;
import com.facebook.internal.m;
import com.facebook.share.c;
import com.facebook.share.internal.MessageDialogFeature;
import com.facebook.share.internal.OpenGraphMessageDialogFeature;
import com.facebook.share.internal.b;
import com.facebook.share.internal.j;
import com.facebook.share.internal.k;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMessengerGenericTemplateContent;
import com.facebook.share.model.ShareMessengerMediaTemplateContent;
import com.facebook.share.model.ShareMessengerOpenGraphMusicTemplateContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MessageDialog */
public final class a extends g<ShareContent, com.facebook.share.c.a> implements c {
    private static final int b = RequestCodeOffset.Message.toRequestCode();
    private boolean c;

    /* renamed from: com.facebook.share.widget.a$a reason: collision with other inner class name */
    /* compiled from: MessageDialog */
    private class C0126a extends a {
        private C0126a() {
            super();
        }

        public boolean a(ShareContent shareContent, boolean z) {
            return shareContent != null && a.a(shareContent.getClass());
        }

        public com.facebook.internal.a a(final ShareContent shareContent) {
            j.a(shareContent);
            final com.facebook.internal.a d = a.this.d();
            final boolean e = a.this.e();
            a.b(a.this.b(), shareContent, d);
            f.a(d, (com.facebook.internal.f.a) new com.facebook.internal.f.a() {
                public Bundle a() {
                    return com.facebook.share.internal.g.a(d.c(), shareContent, e);
                }

                public Bundle b() {
                    return b.a(d.c(), shareContent, e);
                }
            }, a.c(shareContent.getClass()));
            return d;
        }
    }

    public static boolean a(Class<? extends ShareContent> cls) {
        e c2 = c(cls);
        return c2 != null && f.a(c2);
    }

    a(Activity activity, int i) {
        super(activity, i);
        this.c = false;
        k.a(i);
    }

    a(Fragment fragment, int i) {
        this(new m(fragment), i);
    }

    a(android.app.Fragment fragment, int i) {
        this(new m(fragment), i);
    }

    private a(m mVar, int i) {
        super(mVar, i);
        this.c = false;
        k.a(i);
    }

    /* access modifiers changed from: protected */
    public void a(CallbackManagerImpl callbackManagerImpl, com.facebook.e<com.facebook.share.c.a> eVar) {
        k.a(a(), (d) callbackManagerImpl, eVar);
    }

    public boolean e() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public com.facebook.internal.a d() {
        return new com.facebook.internal.a(a());
    }

    /* access modifiers changed from: protected */
    public List<a> c() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C0126a());
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static e c(Class<? extends ShareContent> cls) {
        if (ShareLinkContent.class.isAssignableFrom(cls)) {
            return MessageDialogFeature.MESSAGE_DIALOG;
        }
        if (SharePhotoContent.class.isAssignableFrom(cls)) {
            return MessageDialogFeature.PHOTOS;
        }
        if (ShareVideoContent.class.isAssignableFrom(cls)) {
            return MessageDialogFeature.VIDEO;
        }
        if (ShareOpenGraphContent.class.isAssignableFrom(cls)) {
            return OpenGraphMessageDialogFeature.OG_MESSAGE_DIALOG;
        }
        if (ShareMessengerGenericTemplateContent.class.isAssignableFrom(cls)) {
            return MessageDialogFeature.MESSENGER_GENERIC_TEMPLATE;
        }
        if (ShareMessengerOpenGraphMusicTemplateContent.class.isAssignableFrom(cls)) {
            return MessageDialogFeature.MESSENGER_OPEN_GRAPH_MUSIC_TEMPLATE;
        }
        if (ShareMessengerMediaTemplateContent.class.isAssignableFrom(cls)) {
            return MessageDialogFeature.MESSENGER_MEDIA_TEMPLATE;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static void b(Context context, ShareContent shareContent, com.facebook.internal.a aVar) {
        e c2 = c(shareContent.getClass());
        String str = c2 == MessageDialogFeature.MESSAGE_DIALOG ? "status" : c2 == MessageDialogFeature.PHOTOS ? "photo" : c2 == MessageDialogFeature.VIDEO ? "video" : c2 == OpenGraphMessageDialogFeature.OG_MESSAGE_DIALOG ? "open_graph" : c2 == MessageDialogFeature.MESSENGER_GENERIC_TEMPLATE ? "GenericTemplate" : c2 == MessageDialogFeature.MESSENGER_MEDIA_TEMPLATE ? "MediaTemplate" : c2 == MessageDialogFeature.MESSENGER_OPEN_GRAPH_MUSIC_TEMPLATE ? "OpenGraphMusicTemplate" : EnvironmentCompat.MEDIA_UNKNOWN;
        AppEventsLogger a = AppEventsLogger.a(context);
        Bundle bundle = new Bundle();
        bundle.putString("fb_share_dialog_content_type", str);
        bundle.putString("fb_share_dialog_content_uuid", aVar.c().toString());
        bundle.putString("fb_share_dialog_content_page_id", shareContent.getPageId());
        a.a("fb_messenger_share_dialog_show", (Double) null, bundle);
    }
}
