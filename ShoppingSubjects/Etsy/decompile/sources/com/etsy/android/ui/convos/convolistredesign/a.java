package com.etsy.android.ui.convos.convolistredesign;

import com.etsy.android.lib.models.Conversation3;
import io.reactivex.v;
import java.util.List;
import retrofit2.adapter.rxjava2.d;
import retrofit2.b.f;
import retrofit2.b.t;

/* compiled from: ConversationListEndpoint.kt */
public interface a {

    /* renamed from: com.etsy.android.ui.convos.convolistredesign.a$a reason: collision with other inner class name */
    /* compiled from: ConversationListEndpoint.kt */
    public static final class C0088a {
        @f(a = "/etsyapps/v3/member/conversations")
        public static /* synthetic */ v a(a aVar, int i, int i2, boolean z, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getConvoList");
            }
            if ((i3 & 4) != 0) {
                z = true;
            }
            return aVar.a(i, i2, z);
        }
    }

    @f(a = "/etsyapps/v3/member/conversations")
    v<d<List<Conversation3>>> a(@t(a = "limit") int i, @t(a = "offset") int i2, @t(a = "typed_context") boolean z);
}
