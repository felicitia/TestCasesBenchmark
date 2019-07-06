package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.ListingCard;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.convo.ConversationThread2;
import com.etsy.android.ui.convos.convoredesign.al.d;
import io.reactivex.g;
import io.reactivex.v;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.o;
import kotlin.jvm.internal.p;
import okhttp3.u;
import okhttp3.v.b;
import okhttp3.z;

/* compiled from: ConversationRepository.kt */
public final class c {
    private final b a;
    private final a b;
    /* access modifiers changed from: private */
    public final f c;

    /* compiled from: ConversationRepository.kt */
    static final class a implements io.reactivex.functions.a {
        final /* synthetic */ c a;
        final /* synthetic */ al b;

        a(c cVar, al alVar) {
            this.a = cVar;
            this.b = alVar;
        }

        public final void a() {
            al alVar = this.b;
            if (alVar instanceof com.etsy.android.ui.convos.convoredesign.al.a) {
                this.a.c.a(this.b.b().getIdAsLong(), true);
            } else if (alVar instanceof d) {
                this.a.c.a(this.b.b().getIdAsLong(), false);
            }
        }
    }

    public c(b bVar, a aVar, f fVar) {
        p.b(bVar, "conversationEndpoint");
        p.b(aVar, "cartEndpoint");
        p.b(fVar, "convoDao");
        this.a = bVar;
        this.b = aVar;
        this.c = fVar;
    }

    public final g<ConversationThread2> a(long j) {
        return this.a.a(j);
    }

    public final io.reactivex.a a(DraftMessage draftMessage) {
        p.b(draftMessage, Listing.DRAFT_STATE);
        Iterable<File> e = draftMessage.e();
        Collection arrayList = new ArrayList(o.a(e, 10));
        int i = 0;
        for (File a2 : e) {
            int i2 = i + 1;
            z a3 = z.a(u.a("image/jpeg"), a2);
            StringBuilder sb = new StringBuilder();
            sb.append(ResponseConstants.IMAGE);
            sb.append(i == 0 ? "" : String.valueOf(i2));
            String sb2 = sb.toString();
            arrayList.add(b.a(sb2, sb2, a3));
            i = i2;
        }
        List list = (List) arrayList;
        b bVar = this.a;
        long c2 = draftMessage.c();
        z a4 = z.a(u.a("text/plain"), draftMessage.d());
        p.a((Object) a4, "RequestBody.create(Media…t/plain\"), draft.message)");
        return bVar.a(c2, a4, list);
    }

    public final io.reactivex.a a(al alVar) {
        p.b(alVar, "spec");
        io.reactivex.a a2 = this.a.a(alVar.a()).a((io.reactivex.functions.a) new a(this, alVar));
        p.a((Object) a2, "conversationEndpoint.upd…      }\n                }");
        return a2;
    }

    public final v<List<ListingCard>> a(String str) {
        p.b(str, "listingId");
        b bVar = this.a;
        z a2 = z.a(u.a("text/plain"), str);
        p.a((Object) a2, "RequestBody.create(Media…\"text/plain\"), listingId)");
        return bVar.b(a2);
    }

    public final io.reactivex.a b(String str) {
        p.b(str, "listingId");
        return this.b.a(str);
    }
}
