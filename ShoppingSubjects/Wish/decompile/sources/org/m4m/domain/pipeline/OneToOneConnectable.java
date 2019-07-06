package org.m4m.domain.pipeline;

import java.util.Collection;
import org.m4m.domain.IInputRaw;
import org.m4m.domain.IOutputRaw;
import org.m4m.domain.IsConnectable;

class OneToOneConnectable<TLeft, TRight> implements IsConnectable {
    private Class<TLeft> leftClass;
    private Class<TRight> rightClass;

    /* access modifiers changed from: protected */
    public boolean additionalCheck(IOutputRaw iOutputRaw, IInputRaw iInputRaw) {
        return true;
    }

    public static <T1, T2> OneToOneConnectable<T1, T2> OneToOneConnection(Class<T1> cls, Class<T2> cls2) {
        return new OneToOneConnectable<>(cls, cls2);
    }

    public OneToOneConnectable(Class<TLeft> cls, Class<TRight> cls2) {
        this.leftClass = cls;
        this.rightClass = cls2;
    }

    public boolean isConnectable(IOutputRaw iOutputRaw, Collection<IInputRaw> collection) {
        if (collection.size() != 1) {
            return false;
        }
        return isConnectable(iOutputRaw, (IInputRaw) collection.iterator().next());
    }

    public boolean isConnectable(Collection<IOutputRaw> collection, IInputRaw iInputRaw) {
        if (collection.size() != 1) {
            return false;
        }
        return isConnectable((IOutputRaw) collection.iterator().next(), iInputRaw);
    }

    private boolean isConnectable(IOutputRaw iOutputRaw, IInputRaw iInputRaw) {
        if (!this.leftClass.isInstance(iOutputRaw) || !this.rightClass.isInstance(iInputRaw)) {
            return false;
        }
        return additionalCheck(iOutputRaw, iInputRaw);
    }
}
