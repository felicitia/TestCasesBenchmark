package kotlin.reflect;

import java.util.List;
import java.util.Map;

/* compiled from: KCallable.kt */
public interface b<R> extends a {
    R call(Object... objArr);

    R callBy(Map<KParameter, ? extends Object> map);

    List<KParameter> getParameters();

    n getReturnType();

    List<Object> getTypeParameters();

    KVisibility getVisibility();

    boolean isAbstract();

    boolean isFinal();

    boolean isOpen();
}
