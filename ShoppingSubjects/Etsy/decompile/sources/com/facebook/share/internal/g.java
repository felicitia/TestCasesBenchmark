package com.facebook.share.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.FacebookException;
import com.facebook.internal.aa;
import com.facebook.internal.z;
import com.facebook.share.model.ShareCameraEffectContent;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareMessengerGenericTemplateContent;
import com.facebook.share.model.ShareMessengerMediaTemplateContent;
import com.facebook.share.model.ShareMessengerOpenGraphMusicTemplateContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareStoryContent;
import com.facebook.share.model.ShareVideoContent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeDialogParameters */
public class g {
    public static Bundle a(UUID uuid, ShareContent shareContent, boolean z) {
        aa.a((Object) shareContent, "shareContent");
        aa.a((Object) uuid, "callId");
        if (shareContent instanceof ShareLinkContent) {
            return a((ShareLinkContent) shareContent, z);
        }
        if (shareContent instanceof SharePhotoContent) {
            SharePhotoContent sharePhotoContent = (SharePhotoContent) shareContent;
            return a(sharePhotoContent, k.a(sharePhotoContent, uuid), z);
        } else if (shareContent instanceof ShareVideoContent) {
            ShareVideoContent shareVideoContent = (ShareVideoContent) shareContent;
            return a(shareVideoContent, k.a(shareVideoContent, uuid), z);
        } else if (shareContent instanceof ShareOpenGraphContent) {
            ShareOpenGraphContent shareOpenGraphContent = (ShareOpenGraphContent) shareContent;
            try {
                return a(shareOpenGraphContent, k.a(k.a(uuid, shareOpenGraphContent), false), z);
            } catch (JSONException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to create a JSON Object from the provided ShareOpenGraphContent: ");
                sb.append(e.getMessage());
                throw new FacebookException(sb.toString());
            }
        } else if (shareContent instanceof ShareMediaContent) {
            ShareMediaContent shareMediaContent = (ShareMediaContent) shareContent;
            return a(shareMediaContent, k.a(shareMediaContent, uuid), z);
        } else if (shareContent instanceof ShareCameraEffectContent) {
            ShareCameraEffectContent shareCameraEffectContent = (ShareCameraEffectContent) shareContent;
            return a(shareCameraEffectContent, k.a(shareCameraEffectContent, uuid), z);
        } else if (shareContent instanceof ShareMessengerGenericTemplateContent) {
            return a((ShareMessengerGenericTemplateContent) shareContent, z);
        } else {
            if (shareContent instanceof ShareMessengerOpenGraphMusicTemplateContent) {
                return a((ShareMessengerOpenGraphMusicTemplateContent) shareContent, z);
            }
            if (shareContent instanceof ShareMessengerMediaTemplateContent) {
                return a((ShareMessengerMediaTemplateContent) shareContent, z);
            }
            if (!(shareContent instanceof ShareStoryContent)) {
                return null;
            }
            ShareStoryContent shareStoryContent = (ShareStoryContent) shareContent;
            return a(shareStoryContent, k.b(shareStoryContent, uuid), k.a(shareStoryContent, uuid), z);
        }
    }

    private static Bundle a(ShareCameraEffectContent shareCameraEffectContent, Bundle bundle, boolean z) {
        Bundle a = a((ShareContent) shareCameraEffectContent, z);
        z.a(a, "effect_id", shareCameraEffectContent.getEffectId());
        if (bundle != null) {
            a.putBundle("effect_textures", bundle);
        }
        try {
            JSONObject a2 = a.a(shareCameraEffectContent.getArguments());
            if (a2 != null) {
                z.a(a, "effect_arguments", a2.toString());
            }
            return a;
        } catch (JSONException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to create a JSON Object from the provided CameraEffectArguments: ");
            sb.append(e.getMessage());
            throw new FacebookException(sb.toString());
        }
    }

    private static Bundle a(ShareLinkContent shareLinkContent, boolean z) {
        Bundle a = a((ShareContent) shareLinkContent, z);
        z.a(a, "TITLE", shareLinkContent.getContentTitle());
        z.a(a, "DESCRIPTION", shareLinkContent.getContentDescription());
        z.a(a, "IMAGE", shareLinkContent.getImageUrl());
        z.a(a, "QUOTE", shareLinkContent.getQuote());
        z.a(a, "MESSENGER_LINK", shareLinkContent.getContentUrl());
        z.a(a, "TARGET_DISPLAY", shareLinkContent.getContentUrl());
        return a;
    }

    private static Bundle a(SharePhotoContent sharePhotoContent, List<String> list, boolean z) {
        Bundle a = a((ShareContent) sharePhotoContent, z);
        a.putStringArrayList("PHOTOS", new ArrayList(list));
        return a;
    }

    private static Bundle a(ShareVideoContent shareVideoContent, String str, boolean z) {
        Bundle a = a((ShareContent) shareVideoContent, z);
        z.a(a, "TITLE", shareVideoContent.getContentTitle());
        z.a(a, "DESCRIPTION", shareVideoContent.getContentDescription());
        z.a(a, "VIDEO", str);
        return a;
    }

    private static Bundle a(ShareMediaContent shareMediaContent, List<Bundle> list, boolean z) {
        Bundle a = a((ShareContent) shareMediaContent, z);
        a.putParcelableArrayList("MEDIA", new ArrayList(list));
        return a;
    }

    private static Bundle a(ShareOpenGraphContent shareOpenGraphContent, JSONObject jSONObject, boolean z) {
        Bundle a = a((ShareContent) shareOpenGraphContent, z);
        z.a(a, "PREVIEW_PROPERTY_NAME", (String) k.a(shareOpenGraphContent.getPreviewPropertyName()).second);
        z.a(a, "ACTION_TYPE", shareOpenGraphContent.getAction().getActionType());
        z.a(a, "ACTION", jSONObject.toString());
        return a;
    }

    private static Bundle a(ShareMessengerGenericTemplateContent shareMessengerGenericTemplateContent, boolean z) {
        Bundle a = a((ShareContent) shareMessengerGenericTemplateContent, z);
        try {
            f.a(a, shareMessengerGenericTemplateContent);
            return a;
        } catch (JSONException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to create a JSON Object from the provided ShareMessengerGenericTemplateContent: ");
            sb.append(e.getMessage());
            throw new FacebookException(sb.toString());
        }
    }

    private static Bundle a(ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent, boolean z) {
        Bundle a = a((ShareContent) shareMessengerOpenGraphMusicTemplateContent, z);
        try {
            f.a(a, shareMessengerOpenGraphMusicTemplateContent);
            return a;
        } catch (JSONException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to create a JSON Object from the provided ShareMessengerOpenGraphMusicTemplateContent: ");
            sb.append(e.getMessage());
            throw new FacebookException(sb.toString());
        }
    }

    private static Bundle a(ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent, boolean z) {
        Bundle a = a((ShareContent) shareMessengerMediaTemplateContent, z);
        try {
            f.a(a, shareMessengerMediaTemplateContent);
            return a;
        } catch (JSONException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to create a JSON Object from the provided ShareMessengerMediaTemplateContent: ");
            sb.append(e.getMessage());
            throw new FacebookException(sb.toString());
        }
    }

    private static Bundle a(ShareStoryContent shareStoryContent, @Nullable Bundle bundle, @Nullable Bundle bundle2, boolean z) {
        Bundle a = a((ShareContent) shareStoryContent, z);
        if (bundle != null) {
            a.putParcelable("bg_asset", bundle);
        }
        if (bundle2 != null) {
            a.putParcelable("interactive_asset_uri", bundle2);
        }
        List backgroundColorList = shareStoryContent.getBackgroundColorList();
        if (!z.a((Collection<T>) backgroundColorList)) {
            a.putStringArrayList("top_background_color_list", new ArrayList(backgroundColorList));
        }
        z.a(a, "content_url", shareStoryContent.getAttributionLink());
        return a;
    }

    private static Bundle a(ShareContent shareContent, boolean z) {
        Bundle bundle = new Bundle();
        z.a(bundle, "LINK", shareContent.getContentUrl());
        z.a(bundle, "PLACE", shareContent.getPlaceId());
        z.a(bundle, "PAGE", shareContent.getPageId());
        z.a(bundle, "REF", shareContent.getRef());
        bundle.putBoolean("DATA_FAILURES_FATAL", z);
        List peopleIds = shareContent.getPeopleIds();
        if (!z.a((Collection<T>) peopleIds)) {
            bundle.putStringArrayList("FRIENDS", new ArrayList(peopleIds));
        }
        ShareHashtag shareHashtag = shareContent.getShareHashtag();
        if (shareHashtag != null) {
            z.a(bundle, "HASHTAG", shareHashtag.getHashtag());
        }
        return bundle;
    }
}
