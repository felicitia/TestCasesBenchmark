package org.apache.commons.math3.exception.util;

import java.util.ArrayList;

public class ArgUtils {
    private ArgUtils() {
    }

    public static Object[] flatten(Object[] objArr) {
        ArrayList arrayList = new ArrayList();
        if (objArr != null) {
            for (Object[] objArr2 : objArr) {
                if (objArr2 instanceof Object[]) {
                    for (Object add : flatten(objArr2)) {
                        arrayList.add(add);
                    }
                } else {
                    arrayList.add(objArr2);
                }
            }
        }
        return arrayList.toArray();
    }
}
