package org.apache.commons.math3.optimization.linear;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealVector;

@Deprecated
public class LinearConstraint implements Serializable {
    private static final long serialVersionUID = -764632794033034092L;
    private final transient RealVector coefficients;
    private final Relationship relationship;
    private final double value;

    public LinearConstraint(double[] dArr, Relationship relationship2, double d) {
        this((RealVector) new ArrayRealVector(dArr), relationship2, d);
    }

    public LinearConstraint(RealVector realVector, Relationship relationship2, double d) {
        this.coefficients = realVector;
        this.relationship = relationship2;
        this.value = d;
    }

    public LinearConstraint(double[] dArr, double d, Relationship relationship2, double[] dArr2, double d2) {
        double[] dArr3 = new double[dArr.length];
        for (int i = 0; i < dArr3.length; i++) {
            dArr3[i] = dArr[i] - dArr2[i];
        }
        this.coefficients = new ArrayRealVector(dArr3, false);
        this.relationship = relationship2;
        this.value = d2 - d;
    }

    public LinearConstraint(RealVector realVector, double d, Relationship relationship2, RealVector realVector2, double d2) {
        this.coefficients = realVector.subtract(realVector2);
        this.relationship = relationship2;
        this.value = d2 - d;
    }

    public RealVector getCoefficients() {
        return this.coefficients;
    }

    public Relationship getRelationship() {
        return this.relationship;
    }

    public double getValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LinearConstraint)) {
            return false;
        }
        LinearConstraint linearConstraint = (LinearConstraint) obj;
        if (!(this.relationship == linearConstraint.relationship && this.value == linearConstraint.value && this.coefficients.equals(linearConstraint.coefficients))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (this.relationship.hashCode() ^ Double.valueOf(this.value).hashCode()) ^ this.coefficients.hashCode();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        MatrixUtils.serializeRealVector(this.coefficients, objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        MatrixUtils.deserializeRealVector(this, "coefficients", objectInputStream);
    }
}
