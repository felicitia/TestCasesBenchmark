package com.facebook.share.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.e;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.internal.f;
import com.facebook.internal.g;
import com.facebook.internal.m;
import java.util.ArrayList;
import java.util.List;

@Deprecated
/* compiled from: LikeDialog */
public class d extends g<LikeContent, b> {
    private static final int b = RequestCodeOffset.Like.toRequestCode();

    /* compiled from: LikeDialog */
    private class a extends a {
        public boolean a(LikeContent likeContent, boolean z) {
            return false;
        }

        private a() {
            super();
        }

        public com.facebook.internal.a a(final LikeContent likeContent) {
            com.facebook.internal.a d = d.this.d();
            f.a(d, (com.facebook.internal.f.a) new com.facebook.internal.f.a() {
                public Bundle a() {
                    return d.c(likeContent);
                }

                public Bundle b() {
                    Log.e("LikeDialog", "Attempting to present the Like Dialog with an outdated Facebook app on the device");
                    return new Bundle();
                }
            }, d.h());
            return d;
        }
    }

    @Deprecated
    /* compiled from: LikeDialog */
    public static final class b {
        private final Bundle a;

        public b(Bundle bundle) {
            this.a = bundle;
        }
    }

    /* compiled from: LikeDialog */
    private class c extends a {
        public boolean a(LikeContent likeContent, boolean z) {
            return false;
        }

        private c() {
            super();
        }

        public com.facebook.internal.a a(LikeContent likeContent) {
            com.facebook.internal.a d = d.this.d();
            f.a(d, d.c(likeContent), d.h());
            return d;
        }
    }

    @Deprecated
    public static boolean e() {
        return false;
    }

    @Deprecated
    public static boolean f() {
        return false;
    }

    @Deprecated
    /* renamed from: a */
    public void b(LikeContent likeContent) {
    }

    @Deprecated
    public d(Activity activity) {
        super(activity, b);
    }

    @Deprecated
    public d(m mVar) {
        super(mVar, b);
    }

    /* access modifiers changed from: protected */
    public com.facebook.internal.a d() {
        return new com.facebook.internal.a(a());
    }

    /* access modifiers changed from: protected */
    public List<a> c() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new a());
        arrayList.add(new c());
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void a(CallbackManagerImpl callbackManagerImpl, final e<b> eVar) {
        final i r3 = eVar == null ? null : new i(eVar) {
            public void a(com.facebook.internal.a aVar, Bundle bundle) {
                eVar.a(new b(bundle));
            }
        };
        callbackManagerImpl.b(a(), new com.facebook.internal.CallbackManagerImpl.a() {
            public boolean a(int i, Intent intent) {
                return k.a(d.this.a(), i, intent, r3);
            }
        });
    }

    /* access modifiers changed from: private */
    public static com.facebook.internal.e h() {
        return LikeDialogFeature.LIKE_DIALOG;
    }

    /* access modifiers changed from: private */
    public static Bundle c(LikeContent likeContent) {
        Bundle bundle = new Bundle();
        bundle.putString(ResponseConstants.OBJECT_ID, likeContent.getObjectId());
        bundle.putString(ResponseConstants.OBJECT_TYPE, likeContent.getObjectType());
        return bundle;
    }
}
