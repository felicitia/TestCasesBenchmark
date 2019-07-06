package org.apache.commons.math3.analysis.interpolation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.random.UnitSphereRandomVectorGenerator;
import org.apache.commons.math3.util.FastMath;

public class MicrosphereInterpolatingFunction implements MultivariateFunction {
    private final double brightnessExponent;
    private final int dimension;
    private final List<MicrosphereSurfaceElement> microsphere;
    private final Map<RealVector, Double> samples;

    private static class MicrosphereSurfaceElement {
        private double brightestIllumination;
        private Entry<RealVector, Double> brightestSample;
        private final RealVector normal;

        MicrosphereSurfaceElement(double[] dArr) {
            this.normal = new ArrayRealVector(dArr);
        }

        /* access modifiers changed from: 0000 */
        public RealVector normal() {
            return this.normal;
        }

        /* access modifiers changed from: 0000 */
        public void reset() {
            this.brightestIllumination = 0.0d;
            this.brightestSample = null;
        }

        /* access modifiers changed from: 0000 */
        public void store(double d, Entry<RealVector, Double> entry) {
            if (d > this.brightestIllumination) {
                this.brightestIllumination = d;
                this.brightestSample = entry;
            }
        }

        /* access modifiers changed from: 0000 */
        public double illumination() {
            return this.brightestIllumination;
        }

        /* access modifiers changed from: 0000 */
        public Entry<RealVector, Double> sample() {
            return this.brightestSample;
        }
    }

    public MicrosphereInterpolatingFunction(double[][] dArr, double[] dArr2, int i, int i2, UnitSphereRandomVectorGenerator unitSphereRandomVectorGenerator) throws DimensionMismatchException, NoDataException, NullArgumentException {
        if (dArr == null || dArr2 == null) {
            throw new NullArgumentException();
        } else if (dArr.length == 0) {
            throw new NoDataException();
        } else if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        } else {
            if (dArr[0] == null) {
                throw new NullArgumentException();
            }
            this.dimension = dArr[0].length;
            this.brightnessExponent = (double) i;
            this.samples = new HashMap(dArr2.length);
            int i3 = 0;
            while (i3 < dArr.length) {
                double[] dArr3 = dArr[i3];
                if (dArr3 == null) {
                    throw new NullArgumentException();
                } else if (dArr3.length != this.dimension) {
                    throw new DimensionMismatchException(dArr3.length, this.dimension);
                } else {
                    this.samples.put(new ArrayRealVector(dArr3), Double.valueOf(dArr2[i3]));
                    i3++;
                }
            }
            this.microsphere = new ArrayList(i2);
            for (int i4 = 0; i4 < i2; i4++) {
                this.microsphere.add(new MicrosphereSurfaceElement(unitSphereRandomVectorGenerator.nextVector()));
            }
        }
    }

    public double value(double[] dArr) {
        ArrayRealVector arrayRealVector = new ArrayRealVector(dArr);
        for (MicrosphereSurfaceElement reset : this.microsphere) {
            reset.reset();
        }
        for (Entry entry : this.samples.entrySet()) {
            RealVector subtract = ((RealVector) entry.getKey()).subtract(arrayRealVector);
            double norm = subtract.getNorm();
            if (FastMath.abs(norm) < FastMath.ulp(1.0d)) {
                return ((Double) entry.getValue()).doubleValue();
            }
            for (MicrosphereSurfaceElement microsphereSurfaceElement : this.microsphere) {
                microsphereSurfaceElement.store(cosAngle(subtract, microsphereSurfaceElement.normal()) * FastMath.pow(norm, -this.brightnessExponent), entry);
            }
        }
        double d = 0.0d;
        double d2 = 0.0d;
        for (MicrosphereSurfaceElement microsphereSurfaceElement2 : this.microsphere) {
            double illumination = microsphereSurfaceElement2.illumination();
            Entry sample = microsphereSurfaceElement2.sample();
            if (sample != null) {
                d += ((Double) sample.getValue()).doubleValue() * illumination;
                d2 += illumination;
            }
        }
        return d / d2;
    }

    private double cosAngle(RealVector realVector, RealVector realVector2) {
        return realVector.dotProduct(realVector2) / (realVector.getNorm() * realVector2.getNorm());
    }
}
