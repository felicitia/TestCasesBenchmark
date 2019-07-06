package org.m4m.domain.pipeline;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import org.m4m.domain.IInputRaw;
import org.m4m.domain.IOutputRaw;
import org.m4m.domain.IsConnectable;

class ManyToOneConnectable implements IsConnectable {
    Class mInType;
    ManyTypes mOutTypes;

    ManyToOneConnectable(ManyTypes manyTypes, Class cls) {
        this.mOutTypes = manyTypes;
        this.mInType = cls;
    }

    public static ManyToOneConnectable ManyToOneConnections(ManyTypes manyTypes, Class cls) {
        return new ManyToOneConnectable(manyTypes, cls);
    }

    public boolean isConnectable(IOutputRaw iOutputRaw, Collection<IInputRaw> collection) {
        if (collection.size() != 1) {
            return false;
        }
        LinkedList linkedList = new LinkedList();
        linkedList.add(iOutputRaw);
        return isConnectable((Collection<IOutputRaw>) linkedList, (IInputRaw) collection.iterator().next());
    }

    public boolean isConnectable(Collection<IOutputRaw> collection, IInputRaw iInputRaw) {
        boolean z;
        if (!this.mInType.isInstance(iInputRaw)) {
            return false;
        }
        Iterator it = collection.iterator();
        do {
            z = true;
            if (it.hasNext()) {
                IOutputRaw iOutputRaw = (IOutputRaw) it.next();
                Class[] types = this.mOutTypes.getTypes();
                int length = types.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        z = false;
                        continue;
                        break;
                    } else if (types[i].isInstance(iOutputRaw)) {
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
}
