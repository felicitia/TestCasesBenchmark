package com.etsy.android.ui.convos.convoredesign;

import android.arch.persistence.room.a.a;
import kotlin.jvm.internal.p;

/* compiled from: ConvoDatabase.kt */
public final class ConvoDatabase$Companion$MIGRATION_1_2_ADD_CONVOS_TABLE$1 extends a {
    ConvoDatabase$Companion$MIGRATION_1_2_ADD_CONVOS_TABLE$1(int i, int i2) {
        super(i, i2);
    }

    public void migrate(android.arch.persistence.db.a aVar) {
        p.b(aVar, "database");
        aVar.c("CREATE TABLE IF NOT EXISTS `convos` (`conversationId` INTEGER NOT NULL, `messageCount` INTEGER NOT NULL, `isRead` INTEGER NOT NULL, `hasAttachment` INTEGER NOT NULL, `title` TEXT NOT NULL, `lastMessage` TEXT NOT NULL, `lastUpdated` INTEGER NOT NULL, `otherUserId` INTEGER NOT NULL, `otherUserNameUser` TEXT NOT NULL, `otherUserNameFull` TEXT NOT NULL, `otherUserAvatarUrl` TEXT NOT NULL, `otherUserIsGuest` INTEGER NOT NULL, `isCustomShop` INTEGER NOT NULL, PRIMARY KEY(`conversationId`))");
    }
}
