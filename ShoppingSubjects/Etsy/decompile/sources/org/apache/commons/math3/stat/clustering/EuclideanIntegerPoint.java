package org.apache.commons.math3.stat.clustering;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.math3.util.MathArrays;

public class EuclideanIntegerPoint implements Serializable, Clusterable<EuclideanIntegerPoint> {
    private static final long serialVersionUID = 3946024775784901369L;
    private final int[] point;

    public EuclideanIntegerPoint(int[] iArr) {
        this.point = iArr;
    }

    public int[] getPoint() {
        return this.point;
    }

    public double distanceFrom(EuclideanIntegerPoint euclideanIntegerPoint) {
        return MathArrays.distance(this.point, euclideanIntegerPoint.getPoint());
    }

    public EuclideanIntegerPoint centroidOf(Collection<EuclideanIntegerPoint> collection) {
        int i;
        int[] iArr = new int[getPoint().length];
        Iterator it = collection.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            EuclideanIntegerPoint euclideanIntegerPoint = (EuclideanIntegerPoint) it.next();
            while (i < iArr.length) {
                iArr[i] = iArr[i] + euclideanIntegerPoint.getPoint()[i];
                i++;
            }
        }
        while (i < iArr.length) {
            iArr[i] = iArr[i] / collection.size();
            i++;
        }
        return new EuclideanIntegerPoint(iArr);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EuclideanIntegerPoint)) {
            return false;
        }
        return Arrays.equals(this.point, ((EuclideanIntegerPoint) obj).point);
    }

    public int hashCode() {
        return Arrays.hashCode(this.point);
    }

    public String toString() {
        return Arrays.toString(this.point);
    }
}
