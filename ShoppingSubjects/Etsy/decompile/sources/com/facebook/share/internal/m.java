package com.facebook.share.internal;

import android.os.Bundle;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.FacebookException;
import com.facebook.internal.z;
import com.facebook.internal.z.b;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: WebDialogParameters */
public class m {
    public static Bundle a(ShareLinkContent shareLinkContent) {
        Bundle a = a((ShareContent) shareLinkContent);
        z.a(a, "href", shareLinkContent.getContentUrl());
        z.a(a, "quote", shareLinkContent.getQuote());
        return a;
    }

    public static Bundle a(ShareOpenGraphContent shareOpenGraphContent) {
        Bundle a = a((ShareContent) shareOpenGraphContent);
        z.a(a, "action_type", shareOpenGraphContent.getAction().getActionType());
        try {
            JSONObject a2 = k.a(k.a(shareOpenGraphContent), false);
            if (a2 != null) {
                z.a(a, "action_properties", a2.toString());
            }
            return a;
        } catch (JSONException e) {
            throw new FacebookException("Unable to serialize the ShareOpenGraphContent to JSON", (Throwable) e);
        }
    }

    public static Bundle a(SharePhotoContent sharePhotoContent) {
        Bundle a = a((ShareContent) sharePhotoContent);
        String[] strArr = new String[sharePhotoContent.getPhotos().size()];
        z.a(sharePhotoContent.getPhotos(), (b<T, K>) new b<SharePhoto, String>() {
            public String a(SharePhoto sharePhoto) {
                return sharePhoto.getImageUrl().toString();
            }
        }).toArray(strArr);
        a.putStringArray(ResponseConstants.MEDIA, strArr);
        return a;
    }

    public static Bundle a(ShareContent shareContent) {
        Bundle bundle = new Bundle();
        ShareHashtag shareHashtag = shareContent.getShareHashtag();
        if (shareHashtag != null) {
            z.a(bundle, "hashtag", shareHashtag.getHashtag());
        }
        return bundle;
    }

    public static Bundle b(ShareLinkContent shareLinkContent) {
        Bundle bundle = new Bundle();
        z.a(bundle, ResponseConstants.NAME, shareLinkContent.getContentTitle());
        z.a(bundle, "description", shareLinkContent.getContentDescription());
        z.a(bundle, "link", z.a(shareLinkContent.getContentUrl()));
        z.a(bundle, "picture", z.a(shareLinkContent.getImageUrl()));
        z.a(bundle, "quote", shareLinkContent.getQuote());
        if (shareLinkContent.getShareHashtag() != null) {
            z.a(bundle, "hashtag", shareLinkContent.getShareHashtag().getHashtag());
        }
        return bundle;
    }

    public static Bundle a(ShareFeedContent shareFeedContent) {
        Bundle bundle = new Bundle();
        z.a(bundle, ResponseConstants.TO, shareFeedContent.getToId());
        z.a(bundle, "link", shareFeedContent.getLink());
        z.a(bundle, "picture", shareFeedContent.getPicture());
        z.a(bundle, "source", shareFeedContent.getMediaSource());
        z.a(bundle, ResponseConstants.NAME, shareFeedContent.getLinkName());
        z.a(bundle, ResponseConstants.CAPTION, shareFeedContent.getLinkCaption());
        z.a(bundle, "description", shareFeedContent.getLinkDescription());
        return bundle;
    }
}
