package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.internal.aa;
import com.facebook.internal.z;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LegacyNativeDialogParameters */
public class b {
    private static Bundle a(ShareVideoContent shareVideoContent, boolean z) {
        return null;
    }

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
            return a((ShareVideoContent) shareContent, z);
        } else {
            if (!(shareContent instanceof ShareOpenGraphContent)) {
                return null;
            }
            ShareOpenGraphContent shareOpenGraphContent = (ShareOpenGraphContent) shareContent;
            try {
                return a(shareOpenGraphContent, k.a(uuid, shareOpenGraphContent), z);
            } catch (JSONException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to create a JSON Object from the provided ShareOpenGraphContent: ");
                sb.append(e.getMessage());
                throw new FacebookException(sb.toString());
            }
        }
    }

    private static Bundle a(ShareLinkContent shareLinkContent, boolean z) {
        Bundle a = a((ShareContent) shareLinkContent, z);
        z.a(a, "com.facebook.platform.extra.TITLE", shareLinkContent.getContentTitle());
        z.a(a, "com.facebook.platform.extra.DESCRIPTION", shareLinkContent.getContentDescription());
        z.a(a, "com.facebook.platform.extra.IMAGE", shareLinkContent.getImageUrl());
        return a;
    }

    private static Bundle a(SharePhotoContent sharePhotoContent, List<String> list, boolean z) {
        Bundle a = a((ShareContent) sharePhotoContent, z);
        a.putStringArrayList("com.facebook.platform.extra.PHOTOS", new ArrayList(list));
        return a;
    }

    private static Bundle a(ShareOpenGraphContent shareOpenGraphContent, JSONObject jSONObject, boolean z) {
        Bundle a = a((ShareContent) shareOpenGraphContent, z);
        z.a(a, "com.facebook.platform.extra.PREVIEW_PROPERTY_NAME", shareOpenGraphContent.getPreviewPropertyName());
        z.a(a, "com.facebook.platform.extra.ACTION_TYPE", shareOpenGraphContent.getAction().getActionType());
        z.a(a, "com.facebook.platform.extra.ACTION", jSONObject.toString());
        return a;
    }

    private static Bundle a(ShareContent shareContent, boolean z) {
        Bundle bundle = new Bundle();
        z.a(bundle, "com.facebook.platform.extra.LINK", shareContent.getContentUrl());
        z.a(bundle, "com.facebook.platform.extra.PLACE", shareContent.getPlaceId());
        z.a(bundle, "com.facebook.platform.extra.REF", shareContent.getRef());
        bundle.putBoolean("com.facebook.platform.extra.DATA_FAILURES_FATAL", z);
        List peopleIds = shareContent.getPeopleIds();
        if (!z.a((Collection<T>) peopleIds)) {
            bundle.putStringArrayList("com.facebook.platform.extra.FRIENDS", new ArrayList(peopleIds));
        }
        return bundle;
    }
}
