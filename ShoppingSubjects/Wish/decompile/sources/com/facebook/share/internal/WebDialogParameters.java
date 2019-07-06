package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.internal.Utility;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import org.json.JSONException;
import org.json.JSONObject;

public class WebDialogParameters {
    public static Bundle create(ShareLinkContent shareLinkContent) {
        Bundle createBaseParameters = createBaseParameters(shareLinkContent);
        Utility.putUri(createBaseParameters, "href", shareLinkContent.getContentUrl());
        Utility.putNonEmptyString(createBaseParameters, "quote", shareLinkContent.getQuote());
        return createBaseParameters;
    }

    public static Bundle create(ShareOpenGraphContent shareOpenGraphContent) {
        Bundle createBaseParameters = createBaseParameters(shareOpenGraphContent);
        Utility.putNonEmptyString(createBaseParameters, "action_type", shareOpenGraphContent.getAction().getActionType());
        try {
            JSONObject removeNamespacesFromOGJsonObject = ShareInternalUtility.removeNamespacesFromOGJsonObject(ShareInternalUtility.toJSONObjectForWeb(shareOpenGraphContent), false);
            if (removeNamespacesFromOGJsonObject != null) {
                Utility.putNonEmptyString(createBaseParameters, "action_properties", removeNamespacesFromOGJsonObject.toString());
            }
            return createBaseParameters;
        } catch (JSONException e) {
            throw new FacebookException("Unable to serialize the ShareOpenGraphContent to JSON", e);
        }
    }

    public static Bundle createBaseParameters(ShareContent shareContent) {
        Bundle bundle = new Bundle();
        ShareHashtag shareHashtag = shareContent.getShareHashtag();
        if (shareHashtag != null) {
            Utility.putNonEmptyString(bundle, "hashtag", shareHashtag.getHashtag());
        }
        return bundle;
    }
}
