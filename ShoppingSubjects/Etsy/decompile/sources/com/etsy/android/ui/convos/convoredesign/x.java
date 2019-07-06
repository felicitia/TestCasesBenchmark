package com.etsy.android.ui.convos.convoredesign;

import android.content.Context;
import com.etsy.android.lib.c.b;
import com.etsy.android.lib.c.e;
import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.models.ConversationUser;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.CameraHelper;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import kotlin.jvm.internal.p;

/* compiled from: ConvoThreadFragmentModule.kt */
public final class x {
    public static final x a = new x();

    private x() {
    }

    public static final ae a(af afVar, ConvoThreadFragment2 convoThreadFragment2, CameraHelper cameraHelper, l lVar) {
        p.b(afVar, "presenter");
        p.b(convoThreadFragment2, "fragment");
        p.b(cameraHelper, "cameraHelper");
        p.b(lVar, "logCat");
        return new ae(afVar, convoThreadFragment2, cameraHelper, lVar);
    }

    public static final b a(e eVar) {
        p.b(eVar, "retrofit");
        Object a2 = eVar.a().a(b.class);
        p.a(a2, "retrofit.v3moshiRetrofit…tionEndpoint::class.java)");
        return (b) a2;
    }

    public static final a a(b bVar) {
        p.b(bVar, "retrofit");
        Object a2 = bVar.a().a(a.class);
        p.a(a2, "retrofit.v2moshiRetrofit…CartEndpoint::class.java)");
        return (a) a2;
    }

    public static final EtsyId a(ConvoThreadFragment2 convoThreadFragment2) {
        p.b(convoThreadFragment2, "convoThreadFragment");
        return new EtsyId(convoThreadFragment2.getArguments().getLong("convo_id", 0));
    }

    public static final ai b(ConvoThreadFragment2 convoThreadFragment2) {
        p.b(convoThreadFragment2, "convoThreadFragment");
        return convoThreadFragment2;
    }

    public static final ConversationUser a(ConvoActivity convoActivity) {
        p.b(convoActivity, "activity");
        ConversationUser conversationUser = new ConversationUser();
        Context context = convoActivity;
        conversationUser.setAvatarUrl(SharedPreferencesUtility.f(context));
        conversationUser.setUserId(SharedPreferencesUtility.c(context));
        conversationUser.setUserName(SharedPreferencesUtility.e(context));
        conversationUser.setDisplayName(SharedPreferencesUtility.d(context));
        return conversationUser;
    }
}
