package org.apache.commons.math3.optim.nonlinear.scalar;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.function.Logit;
import org.apache.commons.math3.analysis.function.Sigmoid;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class MultivariateFunctionMappingAdapter implements MultivariateFunction {
    private final MultivariateFunction bounded;
    private final Mapper[] mappers;

    private static class LowerBoundMapper implements Mapper {
        private final double lower;

        public LowerBoundMapper(double d) {
            this.lower = d;
        }

        public double unboundedToBounded(double d) {
            return this.lower + FastMath.exp(d);
        }

        public double boundedToUnbounded(double d) {
            return FastMath.log(d - this.lower);
        }
    }

    private static class LowerUpperBoundMapper implements Mapper {
        private final UnivariateFunction boundingFunction;
        private final UnivariateFunction unboundingFunction;

        public LowerUpperBoundMapper(double d, double d2) {
            this.boundingFunction = new Sigmoid(d, d2);
            this.unboundingFunction = new Logit(d, d2);
        }

        public double unboundedToBounded(double d) {
            return this.boundingFunction.value(d);
        }

        public double boundedToUnbounded(double d) {
            return this.unboundingFunction.value(d);
        }
    }

    private interface Mapper {
        double boundedToUnbounded(double d);

        double unboundedToBounded(double d);
    }

    private static class NoBoundsMapper implements Mapper {
        public double boundedToUnbounded(double d) {
            return d;
        }

        public double unboundedToBounded(double d) {
            return d;
        }

        private NoBoundsMapper() {
        }
    }

    private static class UpperBoundMapper implements Mapper {
        private final double upper;

        public UpperBoundMapper(double d) {
            this.upper = d;
        }

        public double unboundedToBounded(double d) {
            return this.upper - FastMath.exp(-d);
        }

        public double boundedToUnbounded(double d) {
            return -FastMath.log(this.upper - d);
        }
    }

    public MultivariateFunctionMappingAdapter(MultivariateFunction multivariateFunction, double[] dArr, double[] dArr2) {
        MathUtils.checkNotNull(dArr);
        MathUtils.checkNotNull(dArr2);
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        for (int i = 0; i < dArr.length; i++) {
            if (dArr2[i] < dArr[i]) {
                throw new NumberIsTooSmallException(Double.valueOf(dArr2[i]), Double.valueOf(dArr[i]), true);
            }
        }
        this.bounded = multivariateFunction;
        this.mappers = new Mapper[dArr.length];
        for (int i2 = 0; i2 < this.mappers.length; i2++) {
            if (Double.isInfinite(dArr[i2])) {
                if (Double.isInfinite(dArr2[i2])) {
                    this.mappers[i2] = new NoBoundsMapper();
                } else {
                    this.mappers[i2] = new UpperBoundMapper(dArr2[i2]);
                }
            } else if (Double.isInfinite(dArr2[i2])) {
                this.mappers[i2] = new LowerBoundMapper(dArr[i2]);
            } else {
                this.mappers[i2] = new LowerUpperBoundMapper(dArr[i2], dArr2[i2]);
            }
        }
    }

    public double[] unboundedToBounded(double[] dArr) {
        double[] dArr2 = new double[this.mappers.length];
        for (int i = 0; i < this.mappers.length; i++) {
            dArr2[i] = this.mappers[i].unboundedToBounded(dArr[i]);
        }
        return dArr2;
    }

    public double[] boundedToUnbounded(double[] dArr) {
        double[] dArr2 = new double[this.mappers.length];
        for (int i = 0; i < this.mappers.length; i++) {
            dArr2[i] = this.mappers[i].boundedToUnbounded(dArr[i]);
        }
        return dArr2;
    }

    public double value(double[] dArr) {
        return this.bounded.value(unboundedToBounded(dArr));
    }
}
