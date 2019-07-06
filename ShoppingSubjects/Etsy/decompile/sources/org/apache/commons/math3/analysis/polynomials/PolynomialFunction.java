package org.apache.commons.math3.analysis.polynomials;

import com.etsy.android.ui.dialog.EtsyDialogFragment;
import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class PolynomialFunction implements Serializable, DifferentiableUnivariateFunction, UnivariateDifferentiableFunction {
    private static final long serialVersionUID = -7726511984200295583L;
    private final double[] coefficients;

    public static class Parametric implements ParametricUnivariateFunction {
        public double[] gradient(double d, double... dArr) {
            double d2 = 1.0d;
            double[] dArr2 = new double[dArr.length];
            for (int i = 0; i < dArr.length; i++) {
                dArr2[i] = d2;
                d2 *= d;
            }
            return dArr2;
        }

        public double value(double d, double... dArr) {
            return PolynomialFunction.evaluate(dArr, d);
        }
    }

    public PolynomialFunction(double[] dArr) throws NullArgumentException, NoDataException {
        MathUtils.checkNotNull(dArr);
        int length = dArr.length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        }
        while (length > 1 && dArr[length - 1] == 0.0d) {
            length--;
        }
        this.coefficients = new double[length];
        System.arraycopy(dArr, 0, this.coefficients, 0, length);
    }

    public double value(double d) {
        return evaluate(this.coefficients, d);
    }

    public int degree() {
        return this.coefficients.length - 1;
    }

    public double[] getCoefficients() {
        return (double[]) this.coefficients.clone();
    }

    protected static double evaluate(double[] dArr, double d) throws NullArgumentException, NoDataException {
        MathUtils.checkNotNull(dArr);
        int length = dArr.length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        }
        double d2 = dArr[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            d2 = (d2 * d) + dArr[i];
        }
        return d2;
    }

    public DerivativeStructure value(DerivativeStructure derivativeStructure) throws NullArgumentException, NoDataException {
        MathUtils.checkNotNull(this.coefficients);
        int length = this.coefficients.length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        }
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(derivativeStructure.getFreeParameters(), derivativeStructure.getOrder(), this.coefficients[length - 1]);
        for (int i = length - 2; i >= 0; i--) {
            derivativeStructure2 = derivativeStructure2.multiply(derivativeStructure).add(this.coefficients[i]);
        }
        return derivativeStructure2;
    }

    public PolynomialFunction add(PolynomialFunction polynomialFunction) {
        int min = FastMath.min(this.coefficients.length, polynomialFunction.coefficients.length);
        int max = FastMath.max(this.coefficients.length, polynomialFunction.coefficients.length);
        double[] dArr = new double[max];
        for (int i = 0; i < min; i++) {
            dArr[i] = this.coefficients[i] + polynomialFunction.coefficients[i];
        }
        System.arraycopy(this.coefficients.length < polynomialFunction.coefficients.length ? polynomialFunction.coefficients : this.coefficients, min, dArr, min, max - min);
        return new PolynomialFunction(dArr);
    }

    public PolynomialFunction subtract(PolynomialFunction polynomialFunction) {
        int min = FastMath.min(this.coefficients.length, polynomialFunction.coefficients.length);
        int max = FastMath.max(this.coefficients.length, polynomialFunction.coefficients.length);
        double[] dArr = new double[max];
        for (int i = 0; i < min; i++) {
            dArr[i] = this.coefficients[i] - polynomialFunction.coefficients[i];
        }
        if (this.coefficients.length < polynomialFunction.coefficients.length) {
            while (min < max) {
                dArr[min] = -polynomialFunction.coefficients[min];
                min++;
            }
        } else {
            System.arraycopy(this.coefficients, min, dArr, min, max - min);
        }
        return new PolynomialFunction(dArr);
    }

    public PolynomialFunction negate() {
        double[] dArr = new double[this.coefficients.length];
        for (int i = 0; i < this.coefficients.length; i++) {
            dArr[i] = -this.coefficients[i];
        }
        return new PolynomialFunction(dArr);
    }

    public PolynomialFunction multiply(PolynomialFunction polynomialFunction) {
        double[] dArr = new double[((this.coefficients.length + polynomialFunction.coefficients.length) - 1)];
        int i = 0;
        while (i < dArr.length) {
            dArr[i] = 0.0d;
            int i2 = i + 1;
            for (int max = FastMath.max(0, i2 - polynomialFunction.coefficients.length); max < FastMath.min(this.coefficients.length, i2); max++) {
                dArr[i] = dArr[i] + (this.coefficients[max] * polynomialFunction.coefficients[i - max]);
            }
            i = i2;
        }
        return new PolynomialFunction(dArr);
    }

    protected static double[] differentiate(double[] dArr) throws NullArgumentException, NoDataException {
        MathUtils.checkNotNull(dArr);
        int length = dArr.length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        } else if (length == 1) {
            return new double[]{0.0d};
        } else {
            int i = length - 1;
            double[] dArr2 = new double[i];
            while (i > 0) {
                dArr2[i - 1] = ((double) i) * dArr[i];
                i--;
            }
            return dArr2;
        }
    }

    public PolynomialFunction polynomialDerivative() {
        return new PolynomialFunction(differentiate(this.coefficients));
    }

    public UnivariateFunction derivative() {
        return polynomialDerivative();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.coefficients[0] != 0.0d) {
            sb.append(toString(this.coefficients[0]));
        } else if (this.coefficients.length == 1) {
            return "0";
        }
        for (int i = 1; i < this.coefficients.length; i++) {
            if (this.coefficients[i] != 0.0d) {
                if (sb.length() > 0) {
                    if (this.coefficients[i] < 0.0d) {
                        sb.append(" - ");
                    } else {
                        sb.append(" + ");
                    }
                } else if (this.coefficients[i] < 0.0d) {
                    sb.append("-");
                }
                double abs = FastMath.abs(this.coefficients[i]);
                if (abs - 1.0d != 0.0d) {
                    sb.append(toString(abs));
                    sb.append(' ');
                }
                sb.append(EtsyDialogFragment.OPT_X_BUTTON);
                if (i > 1) {
                    sb.append('^');
                    sb.append(Integer.toString(i));
                }
            }
        }
        return sb.toString();
    }

    private static String toString(double d) {
        String d2 = Double.toString(d);
        return d2.endsWith(".0") ? d2.substring(0, d2.length() - 2) : d2;
    }

    public int hashCode() {
        return 31 + Arrays.hashCode(this.coefficients);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PolynomialFunction)) {
            return false;
        }
        return Arrays.equals(this.coefficients, ((PolynomialFunction) obj).coefficients);
    }
}
