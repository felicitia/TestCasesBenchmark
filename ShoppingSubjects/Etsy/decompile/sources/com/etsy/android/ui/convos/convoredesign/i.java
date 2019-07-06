package com.etsy.android.ui.convos.convoredesign;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.c;
import android.content.Context;
import com.etsy.android.lib.models.ResponseConstants;
import kotlin.jvm.internal.p;

/* compiled from: ConvoDbModule.kt */
public final class i {
    public static final i a = new i();

    private i() {
    }

    public static final ConvoDatabase a(Context context) {
        p.b(context, ResponseConstants.CONTEXT);
        RoomDatabase c = c.a(context.getApplicationContext(), ConvoDatabase.class, "ConvoDB").a(ConvoDatabase.Companion.a()).a(ConvoDatabase.Companion.b()).a(ConvoDatabase.Companion.c()).c();
        p.a((Object) c, "Room.databaseBuilder(con…\n                .build()");
        return (ConvoDatabase) c;
    }

    public static final f a(ConvoDatabase convoDatabase) {
        p.b(convoDatabase, "db");
        return convoDatabase.convoDao();
    }
}
