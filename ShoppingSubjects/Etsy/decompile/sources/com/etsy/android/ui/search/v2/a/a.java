package com.etsy.android.ui.search.v2.a;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.c;
import android.content.Context;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.ui.search.v2.impressions.SearchImpressionsDatabase;
import kotlin.jvm.internal.p;

/* compiled from: SearchImpressionsDbModule.kt */
public final class a {
    public static final C0098a a = new C0098a(null);

    /* renamed from: com.etsy.android.ui.search.v2.a.a$a reason: collision with other inner class name */
    /* compiled from: SearchImpressionsDbModule.kt */
    public static final class C0098a {
        private C0098a() {
        }

        public /* synthetic */ C0098a(o oVar) {
            this();
        }
    }

    public final SearchImpressionsDatabase a(Context context) {
        p.b(context, ResponseConstants.CONTEXT);
        RoomDatabase c = c.a(context.getApplicationContext(), SearchImpressionsDatabase.class, "SearchImpressionsDB").c();
        p.a((Object) c, "Room.databaseBuilder(con…\n                .build()");
        return (SearchImpressionsDatabase) c;
    }

    public final com.etsy.android.ui.search.v2.impressions.a a(SearchImpressionsDatabase searchImpressionsDatabase) {
        p.b(searchImpressionsDatabase, "db");
        return searchImpressionsDatabase.searchImpressionDao();
    }
}
