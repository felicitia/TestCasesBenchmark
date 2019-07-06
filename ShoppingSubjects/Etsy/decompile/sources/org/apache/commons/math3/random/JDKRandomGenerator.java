package org.apache.commons.math3.random;

import java.util.Random;

public class JDKRandomGenerator extends Random implements RandomGenerator {
    private static final long serialVersionUID = -7745277476784028798L;

    public void setSeed(int i) {
        setSeed((long) i);
    }

    public void setSeed(int[] iArr) {
        long j = 0;
        int i = 0;
        while (i < iArr.length) {
            i++;
            j = (j * 4294967291L) + ((long) iArr[i]);
        }
        setSeed(j);
    }
}
