package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.models.datatypes.EtsyId;
import kotlin.jvm.internal.p;
import okhttp3.v;
import okhttp3.z;

/* compiled from: TagSpec.kt */
public abstract class al {
    private final String a;
    private final EtsyId b;
    private final String c;

    /* compiled from: TagSpec.kt */
    public static final class a extends al {
        public a(EtsyId etsyId) {
            p.b(etsyId, "id");
            super("system_tag.unread", etsyId, "removeTags", null);
        }
    }

    /* compiled from: TagSpec.kt */
    public static final class b extends al {
        public b(EtsyId etsyId) {
            p.b(etsyId, "id");
            super("system_tag.spam", etsyId, "addTags", null);
        }
    }

    /* compiled from: TagSpec.kt */
    public static final class c extends al {
        public c(EtsyId etsyId) {
            p.b(etsyId, "id");
            super("system_tag.trash", etsyId, "addTags", null);
        }
    }

    /* compiled from: TagSpec.kt */
    public static final class d extends al {
        public d(EtsyId etsyId) {
            p.b(etsyId, "id");
            super("system_tag.unread", etsyId, "addTags", null);
        }
    }

    private al(String str, EtsyId etsyId, String str2) {
        this.a = str;
        this.b = etsyId;
        this.c = str2;
    }

    public /* synthetic */ al(String str, EtsyId etsyId, String str2, o oVar) {
        this(str, etsyId, str2);
    }

    public final EtsyId b() {
        return this.b;
    }

    public final z a() {
        v a2 = new okhttp3.v.a().a(v.e).a("conversation_ids", this.b.getId()).a(this.c, this.a).a();
        p.a((Object) a2, "MultipartBody.Builder()\n…\n                .build()");
        return a2;
    }
}
