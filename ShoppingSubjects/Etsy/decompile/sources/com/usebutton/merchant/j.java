package com.usebutton.merchant;

import android.support.annotation.Nullable;

/* compiled from: GetPendingLinkTask */
final class j extends p<o> {
    private final a a;
    private final String b;
    private final h c;

    j(a<o> aVar, a aVar2, String str, h hVar) {
        super(aVar);
        this.a = aVar2;
        this.b = str;
        this.c = hVar;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    /* renamed from: a */
    public o b() throws Exception {
        return this.a.a(this.b, this.c.a(), this.c.b(), this.c.c());
    }
}
