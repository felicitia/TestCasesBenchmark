package org.m4m.domain.pipeline;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import org.m4m.domain.IInputRaw;
import org.m4m.domain.IOutputRaw;
import org.m4m.domain.IsConnectable;

class OneToManyConnectable implements IsConnectable {
    ManyTypes inTypes;
    Class outType;

    public OneToManyConnectable(Class cls, ManyTypes manyTypes) {
        this.outType = cls;
        this.inTypes = manyTypes;
    }

    public static OneToManyConnectable OneToManyConnection(Class cls, ManyTypes manyTypes) {
        return new OneToManyConnectable(cls, manyTypes);
    }

    public boolean isConnectable(IOutputRaw iOutputRaw, Collection<IInputRaw> collection) {
        boolean z;
        if (!this.outType.isInstance(iOutputRaw)) {
            return false;
        }
        Iterator it = collection.iterator();
        do {
            z = true;
            if (it.hasNext()) {
                IInputRaw iInputRaw = (IInputRaw) it.next();
                Class[] types = this.inTypes.getTypes();
                int length = types.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        z = false;
                        continue;
                        break;
                    } else if (types[i].isInstance(iInputRaw)) {
                        continue;
                        break;
                    } else {
                        i++;
                    }
                }
            } else {
                return true;
            }
        } while (z);
        return false;
    }

    public boolean isConnectable(Collection<IOutputRaw> collection, IInputRaw iInputRaw) {
        if (collection.size() != 1) {
            return false;
        }
        LinkedList linkedList = new LinkedList();
        linkedList.add(iInputRaw);
        return isConnectable((IOutputRaw) collection.iterator().next(), (Collection<IInputRaw>) linkedList);
    }
}
