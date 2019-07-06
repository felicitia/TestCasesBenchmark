package com.etsy.android.ui.convos.convoredesign;

import android.arch.persistence.room.RoomDatabase;

/* compiled from: ConvoDatabase.kt */
public abstract class ConvoDatabase extends RoomDatabase {
    public static final int ADD_CONVOS_TABLE = 2;
    public static final int ADD_CONVO_DRAFTS_TABLE = 3;
    public static final int ADD_USERID_CONVOS = 4;
    public static final a Companion = new a(null);
    /* access modifiers changed from: private */
    public static final android.arch.persistence.room.a.a MIGRATION_1_2_ADD_CONVOS_TABLE = new ConvoDatabase$Companion$MIGRATION_1_2_ADD_CONVOS_TABLE$1(1, 2);
    /* access modifiers changed from: private */
    public static final android.arch.persistence.room.a.a MIGRATION_2_3_ADD_CONVO_DRAFTS_TABLE = new ConvoDatabase$Companion$MIGRATION_2_3_ADD_CONVO_DRAFTS_TABLE$1(2, 3);
    /* access modifiers changed from: private */
    public static final android.arch.persistence.room.a.a MIGRATION_3_4_ADD_USERID_CONVOS = new ConvoDatabase$Companion$MIGRATION_3_4_ADD_USERID_CONVOS$1(3, 4);
    public static final int ONLY_SNIPPETS_TABLE = 1;

    /* compiled from: ConvoDatabase.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }

        public final android.arch.persistence.room.a.a a() {
            return ConvoDatabase.MIGRATION_1_2_ADD_CONVOS_TABLE;
        }

        public final android.arch.persistence.room.a.a b() {
            return ConvoDatabase.MIGRATION_2_3_ADD_CONVO_DRAFTS_TABLE;
        }

        public final android.arch.persistence.room.a.a c() {
            return ConvoDatabase.MIGRATION_3_4_ADD_USERID_CONVOS;
        }
    }

    public abstract f convoDao();

    public abstract l convoDraftDao();
}
