package org.apache.commons.math3.ode.nonstiff;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;

abstract class RungeKuttaStepInterpolator extends AbstractStepInterpolator {
    protected AbstractIntegrator integrator;
    protected double[] previousState;
    protected double[][] yDotK;

    protected RungeKuttaStepInterpolator() {
        this.previousState = null;
        this.yDotK = null;
        this.integrator = null;
    }

    public RungeKuttaStepInterpolator(RungeKuttaStepInterpolator rungeKuttaStepInterpolator) {
        super(rungeKuttaStepInterpolator);
        if (rungeKuttaStepInterpolator.currentState != null) {
            this.previousState = (double[]) rungeKuttaStepInterpolator.previousState.clone();
            this.yDotK = new double[rungeKuttaStepInterpolator.yDotK.length][];
            for (int i = 0; i < rungeKuttaStepInterpolator.yDotK.length; i++) {
                this.yDotK[i] = (double[]) rungeKuttaStepInterpolator.yDotK[i].clone();
            }
        } else {
            this.previousState = null;
            this.yDotK = null;
        }
        this.integrator = null;
    }

    public void reinitialize(AbstractIntegrator abstractIntegrator, double[] dArr, double[][] dArr2, boolean z, EquationsMapper equationsMapper, EquationsMapper[] equationsMapperArr) {
        reinitialize(dArr, z, equationsMapper, equationsMapperArr);
        this.previousState = null;
        this.yDotK = dArr2;
        this.integrator = abstractIntegrator;
    }

    public void shift() {
        this.previousState = (double[]) this.currentState.clone();
        super.shift();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        writeBaseExternal(objectOutput);
        int i = -1;
        int length = this.currentState == null ? -1 : this.currentState.length;
        for (int i2 = 0; i2 < length; i2++) {
            objectOutput.writeDouble(this.previousState[i2]);
        }
        if (this.yDotK != null) {
            i = this.yDotK.length;
        }
        objectOutput.writeInt(i);
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < length; i4++) {
                objectOutput.writeDouble(this.yDotK[i3][i4]);
            }
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        double readBaseExternal = readBaseExternal(objectInput);
        int length = this.currentState == null ? -1 : this.currentState.length;
        if (length < 0) {
            this.previousState = null;
        } else {
            this.previousState = new double[length];
            for (int i = 0; i < length; i++) {
                this.previousState[i] = objectInput.readDouble();
            }
        }
        int readInt = objectInput.readInt();
        this.yDotK = readInt < 0 ? null : new double[readInt][];
        for (int i2 = 0; i2 < readInt; i2++) {
            this.yDotK[i2] = length < 0 ? null : new double[length];
            for (int i3 = 0; i3 < length; i3++) {
                this.yDotK[i2][i3] = objectInput.readDouble();
            }
        }
        this.integrator = null;
        if (this.currentState != null) {
            setInterpolatedTime(readBaseExternal);
        } else {
            this.interpolatedTime = readBaseExternal;
        }
    }
}
