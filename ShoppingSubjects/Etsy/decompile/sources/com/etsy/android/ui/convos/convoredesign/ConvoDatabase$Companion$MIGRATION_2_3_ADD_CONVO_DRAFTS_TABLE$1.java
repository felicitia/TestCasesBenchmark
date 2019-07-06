package com.etsy.android.ui.convos.convoredesign;

import android.arch.persistence.room.a.a;
import kotlin.jvm.internal.p;

/* compiled from: ConvoDatabase.kt */
public final class ConvoDatabase$Companion$MIGRATION_2_3_ADD_CONVO_DRAFTS_TABLE$1 extends a {
    ConvoDatabase$Companion$MIGRATION_2_3_ADD_CONVO_DRAFTS_TABLE$1(int i, int i2) {
        super(i, i2);
    }

    public void migrate(android.arch.persistence.db.a aVar) {
        p.b(aVar, "database");
        aVar.c("CREATE TABLE IF NOT EXISTS `convo_drafts` (`id` INTEGER NOT NULL, `message` TEXT NOT NULL, `subject` TEXT NOT NULL, `userName` TEXT NOT NULL, `selectionStart` INTEGER NOT NULL, `selectionEnd` INTEGER NOT NULL, `imageFilePaths` TEXT NOT NULL, `status` INTEGER NOT NULL, PRIMARY KEY(`id`))");
    }
}
