package com.etsy.android.uikit.util;

import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;
import kotlin.text.j;
import kotlin.text.m;

/* compiled from: HashtagHelper.kt */
final class HashtagHelper$getHashTagsFromString$1 extends Lambda implements b<j, String> {
    public static final HashtagHelper$getHashTagsFromString$1 INSTANCE = new HashtagHelper$getHashTagsFromString$1();

    HashtagHelper$getHashTagsFromString$1() {
        super(1);
    }

    public final String invoke(j jVar) {
        p.b(jVar, "it");
        return m.a(jVar.b(), "#", "", false, 4, (Object) null);
    }
}
