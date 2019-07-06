package com.facebook.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.FacebookActivity;
import com.facebook.FacebookException;

/* compiled from: DialogPresenter */
public class f {

    /* compiled from: DialogPresenter */
    public interface a {
        Bundle a();

        Bundle b();
    }

    public static void a(a aVar) {
        a(aVar, new FacebookException("Unable to show the provided content via the web or the installed version of the Facebook app. Some dialogs are only supported starting API 14."));
    }

    public static void a(a aVar, FacebookException facebookException) {
        b(aVar, facebookException);
    }

    public static void a(a aVar, Activity activity) {
        activity.startActivityForResult(aVar.b(), aVar.d());
        aVar.e();
    }

    public static void a(a aVar, m mVar) {
        mVar.a(aVar.b(), aVar.d());
        aVar.e();
    }

    public static boolean a(e eVar) {
        return b(eVar).b() != -1;
    }

    public static void b(a aVar, FacebookException facebookException) {
        if (facebookException != null) {
            aa.c(com.facebook.f.f());
            Intent intent = new Intent();
            intent.setClass(com.facebook.f.f(), FacebookActivity.class);
            intent.setAction(FacebookActivity.PASS_THROUGH_CANCEL_ACTION);
            v.a(intent, aVar.c().toString(), (String) null, v.a(), v.a(facebookException));
            aVar.a(intent);
        }
    }

    public static void a(a aVar, String str, Bundle bundle) {
        aa.c(com.facebook.f.f());
        aa.a(com.facebook.f.f());
        Bundle bundle2 = new Bundle();
        bundle2.putString(ResponseConstants.ACTION, str);
        bundle2.putBundle(ResponseConstants.PARAMS, bundle);
        Intent intent = new Intent();
        v.a(intent, aVar.c().toString(), str, v.a(), bundle2);
        intent.setClass(com.facebook.f.f(), FacebookActivity.class);
        intent.setAction(FacebookDialogFragment.TAG);
        aVar.a(intent);
    }

    public static void a(a aVar, Bundle bundle, e eVar) {
        Uri uri;
        aa.c(com.facebook.f.f());
        aa.a(com.facebook.f.f());
        String name = eVar.name();
        Uri c = c(eVar);
        if (c == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to fetch the Url for the DialogFeature : '");
            sb.append(name);
            sb.append("'");
            throw new FacebookException(sb.toString());
        }
        Bundle a2 = x.a(aVar.c().toString(), v.a(), bundle);
        if (a2 == null) {
            throw new FacebookException("Unable to fetch the app's key-hash");
        }
        if (c.isRelative()) {
            uri = z.a(x.a(), c.toString(), a2);
        } else {
            uri = z.a(c.getAuthority(), c.getPath(), a2);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("url", uri.toString());
        bundle2.putBoolean("is_fallback", true);
        Intent intent = new Intent();
        v.a(intent, aVar.c().toString(), eVar.getAction(), v.a(), bundle2);
        intent.setClass(com.facebook.f.f(), FacebookActivity.class);
        intent.setAction(FacebookDialogFragment.TAG);
        aVar.a(intent);
    }

    public static void a(a aVar, a aVar2, e eVar) {
        Bundle bundle;
        Context f = com.facebook.f.f();
        String action = eVar.getAction();
        com.facebook.internal.v.f b = b(eVar);
        int b2 = b.b();
        if (b2 == -1) {
            throw new FacebookException("Cannot present this dialog. This likely means that the Facebook app is not installed.");
        }
        if (v.a(b2)) {
            bundle = aVar2.a();
        } else {
            bundle = aVar2.b();
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        Intent a2 = v.a(f, aVar.c().toString(), action, b, bundle);
        if (a2 == null) {
            throw new FacebookException("Unable to create Intent; this likely means theFacebook app is not installed.");
        }
        aVar.a(a2);
    }

    private static Uri c(e eVar) {
        String name = eVar.name();
        com.facebook.internal.j.a a2 = j.a(com.facebook.f.j(), eVar.getAction(), name);
        if (a2 != null) {
            return a2.c();
        }
        return null;
    }

    public static com.facebook.internal.v.f b(e eVar) {
        String j = com.facebook.f.j();
        String action = eVar.getAction();
        return v.a(action, a(j, action, eVar));
    }

    private static int[] a(String str, String str2, e eVar) {
        com.facebook.internal.j.a a2 = j.a(str, str2, eVar.name());
        if (a2 != null) {
            return a2.d();
        }
        return new int[]{eVar.getMinVersion()};
    }
}
