package org.apache.commons.math3.ode;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;

class ParameterJacobianWrapper implements ParameterJacobianProvider {
    private final FirstOrderDifferentialEquations fode;
    private final Map<String, Double> hParam = new HashMap();
    private final ParameterizedODE pode;

    public ParameterJacobianWrapper(FirstOrderDifferentialEquations firstOrderDifferentialEquations, ParameterizedODE parameterizedODE, ParameterConfiguration[] parameterConfigurationArr) {
        this.fode = firstOrderDifferentialEquations;
        this.pode = parameterizedODE;
        for (ParameterConfiguration parameterConfiguration : parameterConfigurationArr) {
            String parameterName = parameterConfiguration.getParameterName();
            if (parameterizedODE.isSupported(parameterName)) {
                this.hParam.put(parameterName, Double.valueOf(parameterConfiguration.getHP()));
            }
        }
    }

    public Collection<String> getParametersNames() {
        return this.pode.getParametersNames();
    }

    public boolean isSupported(String str) {
        return this.pode.isSupported(str);
    }

    public void computeParameterJacobian(double d, double[] dArr, double[] dArr2, String str, double[] dArr3) throws DimensionMismatchException, MaxCountExceededException {
        String str2 = str;
        double[] dArr4 = dArr3;
        int dimension = this.fode.getDimension();
        if (this.pode.isSupported(str2)) {
            double[] dArr5 = new double[dimension];
            double parameter = this.pode.getParameter(str2);
            double doubleValue = ((Double) this.hParam.get(str2)).doubleValue();
            this.pode.setParameter(str2, parameter + doubleValue);
            this.fode.computeDerivatives(d, dArr, dArr5);
            for (int i = 0; i < dimension; i++) {
                dArr4[i] = (dArr5[i] - dArr2[i]) / doubleValue;
            }
            this.pode.setParameter(str2, parameter);
            return;
        }
        Arrays.fill(dArr4, 0, dimension, 0.0d);
    }
}
