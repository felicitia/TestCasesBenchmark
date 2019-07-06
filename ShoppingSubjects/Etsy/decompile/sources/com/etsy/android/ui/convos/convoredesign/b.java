package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.models.ListingCard;
import com.etsy.android.lib.models.convo.ConversationThread2;
import io.reactivex.g;
import io.reactivex.v;
import java.util.List;
import okhttp3.z;
import retrofit2.b.f;
import retrofit2.b.l;
import retrofit2.b.o;
import retrofit2.b.q;
import retrofit2.b.s;

/* compiled from: ConversationEndpoint.kt */
public interface b {
    public static final a a = a.a;

    /* compiled from: ConversationEndpoint.kt */
    public static final class a {
        static final /* synthetic */ a a = new a();

        private a() {
        }
    }

    @l
    @o(a = "/etsyapps/v3/member/conversations/{convo_id}/messages")
    io.reactivex.a a(@s(a = "convo_id") long j, @q(a = "message") z zVar, @q List<okhttp3.v.b> list);

    @o(a = "/etsyapps/v3/member/conversations/change-tags")
    io.reactivex.a a(@retrofit2.b.a z zVar);

    @f(a = "/etsyapps/v3/bespoke/member/conversations/{convo_id}/thread?typed_context=true")
    g<ConversationThread2> a(@s(a = "convo_id") long j);

    @l
    @o(a = "/etsyapps/v3/public/listings/get-multi-cards")
    v<List<ListingCard>> b(@q(a = "listing_ids") z zVar);
}
