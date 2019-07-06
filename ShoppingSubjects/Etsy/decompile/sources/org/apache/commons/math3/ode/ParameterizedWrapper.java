package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;

class ParameterizedWrapper implements ParameterizedODE {
    private final FirstOrderDifferentialEquations fode;

    public boolean isSupported(String str) {
        return false;
    }

    public void setParameter(String str, double d) {
    }

    public ParameterizedWrapper(FirstOrderDifferentialEquations firstOrderDifferentialEquations) {
        this.fode = firstOrderDifferentialEquations;
    }

    public int getDimension() {
        return this.fode.getDimension();
    }

    public void computeDerivatives(double d, double[] dArr, double[] dArr2) throws MaxCountExceededException, DimensionMismatchException {
        this.fode.computeDerivatives(d, dArr, dArr2);
    }

    public Collection<String> getParametersNames() {
        return new ArrayList();
    }

    public double getParameter(String str) throws UnknownParameterException {
        if (isSupported(str)) {
            return Double.NaN;
        }
        throw new UnknownParameterException(str);
    }
}
