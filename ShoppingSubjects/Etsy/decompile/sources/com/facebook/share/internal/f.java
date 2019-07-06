package com.facebook.share.internal;

import android.net.Uri;
import android.os.Bundle;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.internal.z;
import com.facebook.share.model.ShareMessengerActionButton;
import com.facebook.share.model.ShareMessengerGenericTemplateContent;
import com.facebook.share.model.ShareMessengerGenericTemplateContent.ImageAspectRatio;
import com.facebook.share.model.ShareMessengerGenericTemplateElement;
import com.facebook.share.model.ShareMessengerMediaTemplateContent;
import com.facebook.share.model.ShareMessengerMediaTemplateContent.MediaType;
import com.facebook.share.model.ShareMessengerOpenGraphMusicTemplateContent;
import com.facebook.share.model.ShareMessengerURLActionButton;
import com.facebook.share.model.ShareMessengerURLActionButton.WebviewHeightRatio;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: MessengerShareContentUtility */
public class f {
    public static final Pattern a = Pattern.compile("^(.+)\\.(facebook\\.com)$");

    /* renamed from: com.facebook.share.internal.f$1 reason: invalid class name */
    /* compiled from: MessengerShareContentUtility */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b = new int[ImageAspectRatio.values().length];
        static final /* synthetic */ int[] c = new int[MediaType.values().length];

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003a */
        static {
            /*
                com.facebook.share.model.ShareMessengerMediaTemplateContent$MediaType[] r0 = com.facebook.share.model.ShareMessengerMediaTemplateContent.MediaType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                c = r0
                r0 = 1
                int[] r1 = c     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.facebook.share.model.ShareMessengerMediaTemplateContent$MediaType r2 = com.facebook.share.model.ShareMessengerMediaTemplateContent.MediaType.VIDEO     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                com.facebook.share.model.ShareMessengerGenericTemplateContent$ImageAspectRatio[] r1 = com.facebook.share.model.ShareMessengerGenericTemplateContent.ImageAspectRatio.values()
                int r1 = r1.length
                int[] r1 = new int[r1]
                b = r1
                int[] r1 = b     // Catch:{ NoSuchFieldError -> 0x0027 }
                com.facebook.share.model.ShareMessengerGenericTemplateContent$ImageAspectRatio r2 = com.facebook.share.model.ShareMessengerGenericTemplateContent.ImageAspectRatio.SQUARE     // Catch:{ NoSuchFieldError -> 0x0027 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0027 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0027 }
            L_0x0027:
                com.facebook.share.model.ShareMessengerURLActionButton$WebviewHeightRatio[] r1 = com.facebook.share.model.ShareMessengerURLActionButton.WebviewHeightRatio.values()
                int r1 = r1.length
                int[] r1 = new int[r1]
                a = r1
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x003a }
                com.facebook.share.model.ShareMessengerURLActionButton$WebviewHeightRatio r2 = com.facebook.share.model.ShareMessengerURLActionButton.WebviewHeightRatio.WebviewHeightRatioCompact     // Catch:{ NoSuchFieldError -> 0x003a }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x003a }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x003a }
            L_0x003a:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0045 }
                com.facebook.share.model.ShareMessengerURLActionButton$WebviewHeightRatio r1 = com.facebook.share.model.ShareMessengerURLActionButton.WebviewHeightRatio.WebviewHeightRatioTall     // Catch:{ NoSuchFieldError -> 0x0045 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0045 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0045 }
            L_0x0045:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.share.internal.f.AnonymousClass1.<clinit>():void");
        }
    }

    public static void a(Bundle bundle, ShareMessengerGenericTemplateContent shareMessengerGenericTemplateContent) throws JSONException {
        a(bundle, shareMessengerGenericTemplateContent.getGenericTemplateElement());
        z.a(bundle, "MESSENGER_PLATFORM_CONTENT", (Object) a(shareMessengerGenericTemplateContent));
    }

    public static void a(Bundle bundle, ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent) throws JSONException {
        b(bundle, shareMessengerOpenGraphMusicTemplateContent);
        z.a(bundle, "MESSENGER_PLATFORM_CONTENT", (Object) a(shareMessengerOpenGraphMusicTemplateContent));
    }

    public static void a(Bundle bundle, ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent) throws JSONException {
        b(bundle, shareMessengerMediaTemplateContent);
        z.a(bundle, "MESSENGER_PLATFORM_CONTENT", (Object) a(shareMessengerMediaTemplateContent));
    }

    private static void a(Bundle bundle, ShareMessengerGenericTemplateElement shareMessengerGenericTemplateElement) throws JSONException {
        if (shareMessengerGenericTemplateElement.getButton() != null) {
            a(bundle, shareMessengerGenericTemplateElement.getButton(), false);
        } else if (shareMessengerGenericTemplateElement.getDefaultAction() != null) {
            a(bundle, shareMessengerGenericTemplateElement.getDefaultAction(), true);
        }
        z.a(bundle, "IMAGE", shareMessengerGenericTemplateElement.getImageUrl());
        z.a(bundle, "PREVIEW_TYPE", "DEFAULT");
        z.a(bundle, "TITLE", shareMessengerGenericTemplateElement.getTitle());
        z.a(bundle, "SUBTITLE", shareMessengerGenericTemplateElement.getSubtitle());
    }

    private static void b(Bundle bundle, ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent) throws JSONException {
        a(bundle, shareMessengerOpenGraphMusicTemplateContent.getButton(), false);
        z.a(bundle, "PREVIEW_TYPE", "OPEN_GRAPH");
        z.a(bundle, "OPEN_GRAPH_URL", shareMessengerOpenGraphMusicTemplateContent.getUrl());
    }

    private static void b(Bundle bundle, ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent) throws JSONException {
        a(bundle, shareMessengerMediaTemplateContent.getButton(), false);
        z.a(bundle, "PREVIEW_TYPE", "DEFAULT");
        z.a(bundle, "ATTACHMENT_ID", shareMessengerMediaTemplateContent.getAttachmentId());
        if (shareMessengerMediaTemplateContent.getMediaUrl() != null) {
            z.a(bundle, a(shareMessengerMediaTemplateContent.getMediaUrl()), shareMessengerMediaTemplateContent.getMediaUrl());
        }
        z.a(bundle, "type", a(shareMessengerMediaTemplateContent.getMediaType()));
    }

    private static void a(Bundle bundle, ShareMessengerActionButton shareMessengerActionButton, boolean z) throws JSONException {
        if (shareMessengerActionButton != null && (shareMessengerActionButton instanceof ShareMessengerURLActionButton)) {
            a(bundle, (ShareMessengerURLActionButton) shareMessengerActionButton, z);
        }
    }

    private static void a(Bundle bundle, ShareMessengerURLActionButton shareMessengerURLActionButton, boolean z) throws JSONException {
        String str;
        if (z) {
            str = z.a(shareMessengerURLActionButton.getUrl());
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(shareMessengerURLActionButton.getTitle());
            sb.append(" - ");
            sb.append(z.a(shareMessengerURLActionButton.getUrl()));
            str = sb.toString();
        }
        z.a(bundle, "TARGET_DISPLAY", str);
        z.a(bundle, "ITEM_URL", shareMessengerURLActionButton.getUrl());
    }

    private static JSONObject a(ShareMessengerGenericTemplateContent shareMessengerGenericTemplateContent) throws JSONException {
        return new JSONObject().put("attachment", new JSONObject().put("type", "template").put("payload", new JSONObject().put("template_type", "generic").put("sharable", shareMessengerGenericTemplateContent.getIsSharable()).put("image_aspect_ratio", a(shareMessengerGenericTemplateContent.getImageAspectRatio())).put("elements", new JSONArray().put(a(shareMessengerGenericTemplateContent.getGenericTemplateElement())))));
    }

    private static JSONObject a(ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent) throws JSONException {
        return new JSONObject().put("attachment", new JSONObject().put("type", "template").put("payload", new JSONObject().put("template_type", "open_graph").put("elements", new JSONArray().put(b(shareMessengerOpenGraphMusicTemplateContent)))));
    }

    private static JSONObject a(ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent) throws JSONException {
        return new JSONObject().put("attachment", new JSONObject().put("type", "template").put("payload", new JSONObject().put("template_type", ResponseConstants.MEDIA).put("elements", new JSONArray().put(b(shareMessengerMediaTemplateContent)))));
    }

    private static JSONObject a(ShareMessengerGenericTemplateElement shareMessengerGenericTemplateElement) throws JSONException {
        JSONObject put = new JSONObject().put("title", shareMessengerGenericTemplateElement.getTitle()).put("subtitle", shareMessengerGenericTemplateElement.getSubtitle()).put(ResponseConstants.IMAGE_URL, z.a(shareMessengerGenericTemplateElement.getImageUrl()));
        if (shareMessengerGenericTemplateElement.getButton() != null) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(a(shareMessengerGenericTemplateElement.getButton()));
            put.put("buttons", jSONArray);
        }
        if (shareMessengerGenericTemplateElement.getDefaultAction() != null) {
            put.put("default_action", a(shareMessengerGenericTemplateElement.getDefaultAction(), true));
        }
        return put;
    }

    private static JSONObject b(ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent) throws JSONException {
        JSONObject put = new JSONObject().put("url", z.a(shareMessengerOpenGraphMusicTemplateContent.getUrl()));
        if (shareMessengerOpenGraphMusicTemplateContent.getButton() != null) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(a(shareMessengerOpenGraphMusicTemplateContent.getButton()));
            put.put("buttons", jSONArray);
        }
        return put;
    }

    private static JSONObject b(ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent) throws JSONException {
        JSONObject put = new JSONObject().put("attachment_id", shareMessengerMediaTemplateContent.getAttachmentId()).put("url", z.a(shareMessengerMediaTemplateContent.getMediaUrl())).put(ResponseConstants.MEDIA_TYPE, a(shareMessengerMediaTemplateContent.getMediaType()));
        if (shareMessengerMediaTemplateContent.getButton() != null) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(a(shareMessengerMediaTemplateContent.getButton()));
            put.put("buttons", jSONArray);
        }
        return put;
    }

    private static JSONObject a(ShareMessengerActionButton shareMessengerActionButton) throws JSONException {
        return a(shareMessengerActionButton, false);
    }

    private static JSONObject a(ShareMessengerActionButton shareMessengerActionButton, boolean z) throws JSONException {
        if (shareMessengerActionButton instanceof ShareMessengerURLActionButton) {
            return a((ShareMessengerURLActionButton) shareMessengerActionButton, z);
        }
        return null;
    }

    private static JSONObject a(ShareMessengerURLActionButton shareMessengerURLActionButton, boolean z) throws JSONException {
        String str;
        JSONObject put = new JSONObject().put("type", "web_url");
        String str2 = "title";
        if (z) {
            str = null;
        } else {
            str = shareMessengerURLActionButton.getTitle();
        }
        return put.put(str2, str).put("url", z.a(shareMessengerURLActionButton.getUrl())).put("webview_height_ratio", a(shareMessengerURLActionButton.getWebviewHeightRatio())).put("messenger_extensions", shareMessengerURLActionButton.getIsMessengerExtensionURL()).put("fallback_url", z.a(shareMessengerURLActionButton.getFallbackUrl())).put("webview_share_button", a(shareMessengerURLActionButton));
    }

    private static String a(Uri uri) {
        String host = uri.getHost();
        return (z.a(host) || !a.matcher(host).matches()) ? "IMAGE" : "uri";
    }

    private static String a(WebviewHeightRatio webviewHeightRatio) {
        if (webviewHeightRatio == null) {
            return "full";
        }
        switch (webviewHeightRatio) {
            case WebviewHeightRatioCompact:
                return "compact";
            case WebviewHeightRatioTall:
                return "tall";
            default:
                return "full";
        }
    }

    private static String a(ImageAspectRatio imageAspectRatio) {
        if (imageAspectRatio == null) {
            return ResponseConstants.HORIZONTAL;
        }
        return AnonymousClass1.b[imageAspectRatio.ordinal()] != 1 ? ResponseConstants.HORIZONTAL : "square";
    }

    private static String a(MediaType mediaType) {
        if (mediaType == null) {
            return ResponseConstants.IMAGE;
        }
        return AnonymousClass1.c[mediaType.ordinal()] != 1 ? ResponseConstants.IMAGE : "video";
    }

    private static String a(ShareMessengerURLActionButton shareMessengerURLActionButton) {
        if (shareMessengerURLActionButton.getShouldHideWebviewShareButton()) {
            return "hide";
        }
        return null;
    }
}
