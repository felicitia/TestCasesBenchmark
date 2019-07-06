package org.apache.commons.math3.optimization;

import java.io.Serializable;
import org.apache.commons.math3.util.Pair;

@Deprecated
public class PointVectorValuePair extends Pair<double[], double[]> implements Serializable {
    private static final long serialVersionUID = 20120513;

    private static class DataTransferObject implements Serializable {
        private static final long serialVersionUID = 20120513;
        private final double[] point;
        private final double[] value;

        public DataTransferObject(double[] dArr, double[] dArr2) {
            this.point = (double[]) dArr.clone();
            this.value = (double[]) dArr2.clone();
        }

        private Object readResolve() {
            return new PointVectorValuePair(this.point, this.value, false);
        }
    }

    public PointVectorValuePair(double[] dArr, double[] dArr2) {
        this(dArr, dArr2, true);
    }

    public PointVectorValuePair(double[] dArr, double[] dArr2, boolean z) {
        if (z) {
            dArr = dArr == null ? null : (double[]) dArr.clone();
        }
        if (z) {
            dArr2 = dArr2 == null ? null : (double[]) dArr2.clone();
        }
        super(dArr, dArr2);
    }

    public double[] getPoint() {
        double[] dArr = (double[]) getKey();
        if (dArr == null) {
            return null;
        }
        return (double[]) dArr.clone();
    }

    public double[] getPointRef() {
        return (double[]) getKey();
    }

    public double[] getValue() {
        double[] dArr = (double[]) super.getValue();
        if (dArr == null) {
            return null;
        }
        return (double[]) dArr.clone();
    }

    public double[] getValueRef() {
        return (double[]) super.getValue();
    }

    private Object writeReplace() {
        return new DataTransferObject((double[]) getKey(), getValue());
    }
}
