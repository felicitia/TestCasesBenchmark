package com.etsy.android.util.posts;

import android.content.Context;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.posts.EtsyRequestPost;
import com.etsy.android.lib.core.posts.PostInfo.a;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.requests.EtsyRequest;

public class MergeGuestsPost extends EtsyRequestPost {
    private static final long serialVersionUID = 191689716725671219L;

    public int getVersionCode() {
        return 1;
    }

    public MergeGuestsPost() {
    }

    public MergeGuestsPost(EtsyRequest etsyRequest) {
        super(etsyRequest, new a().a(5).a());
    }

    public boolean onError(Context context, k kVar) {
        String a = f.a(MergeGuestsPost.class);
        String str = "Error Merging old guest cart:%s ";
        Object[] objArr = new Object[1];
        objArr[0] = kVar != null ? kVar.c() : "null";
        f.d(a, str, objArr);
        return false;
    }
}
