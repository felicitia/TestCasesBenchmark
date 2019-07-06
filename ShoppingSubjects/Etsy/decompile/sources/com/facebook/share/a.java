package com.facebook.share;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.FacebookActivity;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.e;
import com.facebook.f;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.internal.g;
import com.facebook.internal.m;
import com.facebook.share.internal.DeviceShareDialogFragment;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import java.util.List;

/* compiled from: DeviceShareDialog */
public class a extends g<ShareContent, C0123a> {
    private static final int b = RequestCodeOffset.DeviceShare.toRequestCode();

    /* renamed from: com.facebook.share.a$a reason: collision with other inner class name */
    /* compiled from: DeviceShareDialog */
    public static class C0123a {
    }

    /* access modifiers changed from: protected */
    public List<a> c() {
        return null;
    }

    /* access modifiers changed from: protected */
    public com.facebook.internal.a d() {
        return null;
    }

    public a(Activity activity) {
        super(activity, b);
    }

    public a(Fragment fragment) {
        super(new m(fragment), b);
    }

    public a(android.support.v4.app.Fragment fragment) {
        super(new m(fragment), b);
    }

    /* access modifiers changed from: protected */
    public boolean a(ShareContent shareContent, Object obj) {
        return (shareContent instanceof ShareLinkContent) || (shareContent instanceof ShareOpenGraphContent);
    }

    /* access modifiers changed from: protected */
    public void b(ShareContent shareContent, Object obj) {
        if (shareContent == null) {
            throw new FacebookException("Must provide non-null content to share");
        } else if ((shareContent instanceof ShareLinkContent) || (shareContent instanceof ShareOpenGraphContent)) {
            Intent intent = new Intent();
            intent.setClass(f.f(), FacebookActivity.class);
            intent.setAction(DeviceShareDialogFragment.TAG);
            intent.putExtra(ResponseConstants.CONTENT, shareContent);
            a(intent, a());
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(getClass().getSimpleName());
            sb.append(" only supports ShareLinkContent or ShareOpenGraphContent");
            throw new FacebookException(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void a(CallbackManagerImpl callbackManagerImpl, final e<C0123a> eVar) {
        callbackManagerImpl.b(a(), new com.facebook.internal.CallbackManagerImpl.a() {
            public boolean a(int i, Intent intent) {
                if (intent.hasExtra("error")) {
                    eVar.a(((FacebookRequestError) intent.getParcelableExtra("error")).getException());
                    return true;
                }
                eVar.a(new C0123a());
                return true;
            }
        });
    }
}
