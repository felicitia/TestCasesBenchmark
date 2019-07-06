package kotlin.reflect;

import kotlin.a;

/* compiled from: KFunction.kt */
public interface e<R> extends a<R>, b<R> {
    boolean isExternal();

    boolean isInfix();

    boolean isInline();

    boolean isOperator();

    boolean isSuspend();
}
