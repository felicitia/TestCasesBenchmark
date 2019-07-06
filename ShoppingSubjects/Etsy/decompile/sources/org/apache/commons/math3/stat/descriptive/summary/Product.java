package org.apache.commons.math3.stat.descriptive.summary;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.WeightedEvaluation;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class Product extends AbstractStorelessUnivariateStatistic implements Serializable, WeightedEvaluation {
    private static final long serialVersionUID = 2824226005990582538L;
    private long n;
    private double value;

    public Product() {
        this.n = 0;
        this.value = 1.0d;
    }

    public Product(Product product) throws NullArgumentException {
        copy(product, this);
    }

    public void increment(double d) {
        this.value *= d;
        this.n++;
    }

    public double getResult() {
        return this.value;
    }

    public long getN() {
        return this.n;
    }

    public void clear() {
        this.value = 1.0d;
        this.n = 0;
    }

    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        if (!test(dArr, i, i2, true)) {
            return Double.NaN;
        }
        double d = 1.0d;
        for (int i3 = i; i3 < i + i2; i3++) {
            d *= dArr[i3];
        }
        return d;
    }

    public double evaluate(double[] dArr, double[] dArr2, int i, int i2) throws MathIllegalArgumentException {
        if (!test(dArr, dArr2, i, i2, true)) {
            return Double.NaN;
        }
        double d = 1.0d;
        for (int i3 = i; i3 < i + i2; i3++) {
            d *= FastMath.pow(dArr[i3], dArr2[i3]);
        }
        return d;
    }

    public double evaluate(double[] dArr, double[] dArr2) throws MathIllegalArgumentException {
        return evaluate(dArr, dArr2, 0, dArr.length);
    }

    public Product copy() {
        Product product = new Product();
        copy(this, product);
        return product;
    }

    public static void copy(Product product, Product product2) throws NullArgumentException {
        MathUtils.checkNotNull(product);
        MathUtils.checkNotNull(product2);
        product2.setData(product.getDataRef());
        product2.n = product.n;
        product2.value = product.value;
    }
}
