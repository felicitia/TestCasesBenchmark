package org.apache.commons.math3.ode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class JacobianMatrices {
    /* access modifiers changed from: private */
    public boolean dirtyParameter;
    private ExpandableStatefulODE efode;
    private int index;
    /* access modifiers changed from: private */
    public List<ParameterJacobianProvider> jacobianProviders;
    /* access modifiers changed from: private */
    public MainStateJacobianProvider jode;
    private double[] matricesData;
    /* access modifiers changed from: private */
    public int paramDim;
    /* access modifiers changed from: private */
    public ParameterizedODE pode;
    /* access modifiers changed from: private */
    public ParameterConfiguration[] selectedParameters;
    /* access modifiers changed from: private */
    public int stateDim;

    private class JacobiansSecondaryEquations implements SecondaryEquations {
        private JacobiansSecondaryEquations() {
        }

        public int getDimension() {
            return JacobianMatrices.this.stateDim * (JacobianMatrices.this.stateDim + JacobianMatrices.this.paramDim);
        }

        public void computeDerivatives(double d, double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4) throws MaxCountExceededException, DimensionMismatchException {
            ParameterConfiguration parameterConfiguration;
            int i;
            int i2;
            int i3;
            ParameterConfiguration[] parameterConfigurationArr;
            double[] dArr5 = dArr4;
            int i4 = 0;
            if (JacobianMatrices.this.dirtyParameter && JacobianMatrices.this.paramDim != 0) {
                JacobianMatrices.this.jacobianProviders.add(new ParameterJacobianWrapper(JacobianMatrices.this.jode, JacobianMatrices.this.pode, JacobianMatrices.this.selectedParameters));
                JacobianMatrices.this.dirtyParameter = false;
            }
            double[][] dArr6 = (double[][]) Array.newInstance(double.class, new int[]{JacobianMatrices.this.stateDim, JacobianMatrices.this.stateDim});
            JacobianMatrices.this.jode.computeMainStateJacobian(d, dArr, dArr2, dArr6);
            for (int i5 = 0; i5 < JacobianMatrices.this.stateDim; i5++) {
                double[] dArr7 = dArr6[i5];
                for (int i6 = 0; i6 < JacobianMatrices.this.stateDim; i6++) {
                    int i7 = i6;
                    double d2 = 0.0d;
                    for (int i8 = 0; i8 < JacobianMatrices.this.stateDim; i8++) {
                        d2 += dArr7[i8] * dArr3[i7];
                        i7 += JacobianMatrices.this.stateDim;
                    }
                    dArr5[(JacobianMatrices.this.stateDim * i5) + i6] = d2;
                }
            }
            if (JacobianMatrices.this.paramDim != 0) {
                double[] dArr8 = new double[JacobianMatrices.this.stateDim];
                int access$200 = JacobianMatrices.this.stateDim * JacobianMatrices.this.stateDim;
                ParameterConfiguration[] access$700 = JacobianMatrices.this.selectedParameters;
                int length = access$700.length;
                int i9 = access$200;
                int i10 = 0;
                while (i10 < length) {
                    ParameterConfiguration parameterConfiguration2 = access$700[i10];
                    int i11 = i4;
                    int i12 = i11;
                    while (i11 == 0 && i12 < JacobianMatrices.this.jacobianProviders.size()) {
                        ParameterJacobianProvider parameterJacobianProvider = (ParameterJacobianProvider) JacobianMatrices.this.jacobianProviders.get(i12);
                        if (parameterJacobianProvider.isSupported(parameterConfiguration2.getParameterName())) {
                            i = i12;
                            parameterConfiguration = parameterConfiguration2;
                            i3 = i9;
                            i2 = length;
                            String parameterName = parameterConfiguration2.getParameterName();
                            parameterConfigurationArr = access$700;
                            parameterJacobianProvider.computeParameterJacobian(d, dArr, dArr2, parameterName, dArr8);
                            for (int i13 = 0; i13 < JacobianMatrices.this.stateDim; i13++) {
                                double[] dArr9 = dArr6[i13];
                                int i14 = i3;
                                double d3 = dArr8[i13];
                                for (int i15 = 0; i15 < JacobianMatrices.this.stateDim; i15++) {
                                    d3 += dArr9[i15] * dArr3[i14];
                                    i14++;
                                }
                                dArr5[i3 + i13] = d3;
                            }
                            i11 = 1;
                        } else {
                            i = i12;
                            parameterConfiguration = parameterConfiguration2;
                            i3 = i9;
                            i2 = length;
                            parameterConfigurationArr = access$700;
                        }
                        i12 = i + 1;
                        access$700 = parameterConfigurationArr;
                        i9 = i3;
                        length = i2;
                        parameterConfiguration2 = parameterConfiguration;
                    }
                    int i16 = i9;
                    int i17 = length;
                    ParameterConfiguration[] parameterConfigurationArr2 = access$700;
                    if (i11 == 0) {
                        Arrays.fill(dArr5, i16, i16 + JacobianMatrices.this.stateDim, 0.0d);
                    }
                    i9 = i16 + JacobianMatrices.this.stateDim;
                    i10++;
                    access$700 = parameterConfigurationArr2;
                    length = i17;
                    i4 = 0;
                }
            }
        }
    }

    private static class MainStateJacobianWrapper implements MainStateJacobianProvider {
        private final double[] hY;
        /* access modifiers changed from: private */
        public final FirstOrderDifferentialEquations ode;

        public MainStateJacobianWrapper(FirstOrderDifferentialEquations firstOrderDifferentialEquations, double[] dArr) throws DimensionMismatchException {
            this.ode = firstOrderDifferentialEquations;
            this.hY = (double[]) dArr.clone();
            if (dArr.length != firstOrderDifferentialEquations.getDimension()) {
                throw new DimensionMismatchException(firstOrderDifferentialEquations.getDimension(), dArr.length);
            }
        }

        public int getDimension() {
            return this.ode.getDimension();
        }

        public void computeDerivatives(double d, double[] dArr, double[] dArr2) throws MaxCountExceededException, DimensionMismatchException {
            this.ode.computeDerivatives(d, dArr, dArr2);
        }

        public void computeMainStateJacobian(double d, double[] dArr, double[] dArr2, double[][] dArr3) throws MaxCountExceededException, DimensionMismatchException {
            double[] dArr4 = dArr;
            int dimension = this.ode.getDimension();
            double[] dArr5 = new double[dimension];
            for (int i = 0; i < dimension; i++) {
                double d2 = dArr4[i];
                dArr4[i] = dArr4[i] + this.hY[i];
                this.ode.computeDerivatives(d, dArr4, dArr5);
                for (int i2 = 0; i2 < dimension; i2++) {
                    dArr3[i2][i] = (dArr5[i2] - dArr2[i2]) / this.hY[i];
                }
                dArr4[i] = d2;
            }
        }
    }

    public static class MismatchedEquations extends MathIllegalArgumentException {
        private static final long serialVersionUID = 20120902;

        public MismatchedEquations() {
            super(LocalizedFormats.UNMATCHED_ODE_IN_EXPANDED_SET, new Object[0]);
        }
    }

    public JacobianMatrices(FirstOrderDifferentialEquations firstOrderDifferentialEquations, double[] dArr, String... strArr) throws DimensionMismatchException {
        this(new MainStateJacobianWrapper(firstOrderDifferentialEquations, dArr), strArr);
    }

    public JacobianMatrices(MainStateJacobianProvider mainStateJacobianProvider, String... strArr) {
        this.efode = null;
        this.index = -1;
        this.jode = mainStateJacobianProvider;
        this.pode = null;
        this.stateDim = mainStateJacobianProvider.getDimension();
        if (strArr == null) {
            this.selectedParameters = null;
            this.paramDim = 0;
        } else {
            this.selectedParameters = new ParameterConfiguration[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                this.selectedParameters[i] = new ParameterConfiguration(strArr[i], Double.NaN);
            }
            this.paramDim = strArr.length;
        }
        this.dirtyParameter = false;
        this.jacobianProviders = new ArrayList();
        this.matricesData = new double[((this.stateDim + this.paramDim) * this.stateDim)];
        for (int i2 = 0; i2 < this.stateDim; i2++) {
            this.matricesData[(this.stateDim + 1) * i2] = 1.0d;
        }
    }

    public void registerVariationalEquations(ExpandableStatefulODE expandableStatefulODE) throws DimensionMismatchException, MismatchedEquations {
        if (expandableStatefulODE.getPrimary() != (this.jode instanceof MainStateJacobianWrapper ? ((MainStateJacobianWrapper) this.jode).ode : this.jode)) {
            throw new MismatchedEquations();
        }
        this.efode = expandableStatefulODE;
        this.index = this.efode.addSecondaryEquations(new JacobiansSecondaryEquations());
        this.efode.setSecondaryState(this.index, this.matricesData);
    }

    public void addParameterJacobianProvider(ParameterJacobianProvider parameterJacobianProvider) {
        this.jacobianProviders.add(parameterJacobianProvider);
    }

    public void setParameterizedODE(ParameterizedODE parameterizedODE) {
        this.pode = parameterizedODE;
        this.dirtyParameter = true;
    }

    public void setParameterStep(String str, double d) throws UnknownParameterException {
        ParameterConfiguration[] parameterConfigurationArr;
        for (ParameterConfiguration parameterConfiguration : this.selectedParameters) {
            if (str.equals(parameterConfiguration.getParameterName())) {
                parameterConfiguration.setHP(d);
                this.dirtyParameter = true;
                return;
            }
        }
        throw new UnknownParameterException(str);
    }

    public void setInitialMainStateJacobian(double[][] dArr) throws DimensionMismatchException {
        checkDimension(this.stateDim, dArr);
        checkDimension(this.stateDim, dArr[0]);
        int i = 0;
        for (double[] arraycopy : dArr) {
            System.arraycopy(arraycopy, 0, this.matricesData, i, this.stateDim);
            i += this.stateDim;
        }
        if (this.efode != null) {
            this.efode.setSecondaryState(this.index, this.matricesData);
        }
    }

    public void setInitialParameterJacobian(String str, double[] dArr) throws UnknownParameterException, DimensionMismatchException {
        checkDimension(this.stateDim, dArr);
        int i = this.stateDim * this.stateDim;
        int i2 = i;
        for (ParameterConfiguration parameterName : this.selectedParameters) {
            if (str.equals(parameterName.getParameterName())) {
                System.arraycopy(dArr, 0, this.matricesData, i2, this.stateDim);
                if (this.efode != null) {
                    this.efode.setSecondaryState(this.index, this.matricesData);
                    return;
                }
                return;
            }
            i2 += this.stateDim;
        }
        throw new UnknownParameterException(str);
    }

    public void getCurrentMainSetJacobian(double[][] dArr) {
        double[] secondaryState = this.efode.getSecondaryState(this.index);
        int i = 0;
        for (int i2 = 0; i2 < this.stateDim; i2++) {
            System.arraycopy(secondaryState, i, dArr[i2], 0, this.stateDim);
            i += this.stateDim;
        }
    }

    public void getCurrentParameterJacobian(String str, double[] dArr) {
        double[] secondaryState = this.efode.getSecondaryState(this.index);
        int i = this.stateDim * this.stateDim;
        ParameterConfiguration[] parameterConfigurationArr = this.selectedParameters;
        int length = parameterConfigurationArr.length;
        int i2 = i;
        int i3 = 0;
        while (i3 < length) {
            if (parameterConfigurationArr[i3].getParameterName().equals(str)) {
                System.arraycopy(secondaryState, i2, dArr, 0, this.stateDim);
                return;
            } else {
                i2 += this.stateDim;
                i3++;
            }
        }
    }

    private void checkDimension(int i, Object obj) throws DimensionMismatchException {
        int length = obj == null ? 0 : Array.getLength(obj);
        if (length != i) {
            throw new DimensionMismatchException(length, i);
        }
    }
}
