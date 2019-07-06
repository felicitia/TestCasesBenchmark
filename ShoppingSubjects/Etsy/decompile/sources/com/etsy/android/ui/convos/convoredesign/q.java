package com.etsy.android.ui.convos.convoredesign;

import kotlin.jvm.internal.p;

/* compiled from: ConvoMessageParserResult.kt */
public abstract class q {
    private final String a;

    /* compiled from: ConvoMessageParserResult.kt */
    public static final class a extends q {
        private final String a;

        public a(String str, String str2) {
            p.b(str, "url");
            p.b(str2, "listingId");
            super(str, null);
            this.a = str2;
        }

        public final String b() {
            return this.a;
        }
    }

    private q(String str) {
        this.a = str;
    }

    public /* synthetic */ q(String str, o oVar) {
        this(str);
    }

    public final String a() {
        return this.a;
    }
}
