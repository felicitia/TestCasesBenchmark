package kotlin.text;

import kotlin.jvm.a.b;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.s;
import kotlin.reflect.d;

/* compiled from: Regex.kt */
final class Regex$findAll$2 extends FunctionReference implements b<j, j> {
    public static final Regex$findAll$2 INSTANCE = new Regex$findAll$2();

    Regex$findAll$2() {
        super(1);
    }

    public final String getName() {
        return "next";
    }

    public final d getOwner() {
        return s.a(j.class);
    }

    public final String getSignature() {
        return "next()Lkotlin/text/MatchResult;";
    }

    public final j invoke(j jVar) {
        p.b(jVar, "p1");
        return jVar.d();
    }
}
