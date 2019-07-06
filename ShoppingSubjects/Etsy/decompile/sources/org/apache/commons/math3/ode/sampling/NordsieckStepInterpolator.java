package org.apache.commons.math3.ode.sampling;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.util.FastMath;

public class NordsieckStepInterpolator extends AbstractStepInterpolator {
    private static final long serialVersionUID = -7179861704951334960L;
    private Array2DRowRealMatrix nordsieck;
    private double referenceTime;
    private double[] scaled;
    private double scalingH;
    protected double[] stateVariation;

    public NordsieckStepInterpolator() {
    }

    public NordsieckStepInterpolator(NordsieckStepInterpolator nordsieckStepInterpolator) {
        super(nordsieckStepInterpolator);
        this.scalingH = nordsieckStepInterpolator.scalingH;
        this.referenceTime = nordsieckStepInterpolator.referenceTime;
        if (nordsieckStepInterpolator.scaled != null) {
            this.scaled = (double[]) nordsieckStepInterpolator.scaled.clone();
        }
        if (nordsieckStepInterpolator.nordsieck != null) {
            this.nordsieck = new Array2DRowRealMatrix(nordsieckStepInterpolator.nordsieck.getDataRef(), true);
        }
        if (nordsieckStepInterpolator.stateVariation != null) {
            this.stateVariation = (double[]) nordsieckStepInterpolator.stateVariation.clone();
        }
    }

    /* access modifiers changed from: protected */
    public StepInterpolator doCopy() {
        return new NordsieckStepInterpolator(this);
    }

    public void reinitialize(double[] dArr, boolean z, EquationsMapper equationsMapper, EquationsMapper[] equationsMapperArr) {
        super.reinitialize(dArr, z, equationsMapper, equationsMapperArr);
        this.stateVariation = new double[dArr.length];
    }

    public void reinitialize(double d, double d2, double[] dArr, Array2DRowRealMatrix array2DRowRealMatrix) {
        this.referenceTime = d;
        this.scalingH = d2;
        this.scaled = dArr;
        this.nordsieck = array2DRowRealMatrix;
        setInterpolatedTime(getInterpolatedTime());
    }

    public void rescale(double d) {
        double d2 = d / this.scalingH;
        for (int i = 0; i < this.scaled.length; i++) {
            double[] dArr = this.scaled;
            dArr[i] = dArr[i] * d2;
        }
        double[][] dataRef = this.nordsieck.getDataRef();
        double d3 = d2;
        for (double[] dArr2 : dataRef) {
            d3 *= d2;
            for (int i2 = 0; i2 < dArr2.length; i2++) {
                dArr2[i2] = dArr2[i2] * d3;
            }
        }
        this.scalingH = d;
    }

    public double[] getInterpolatedStateVariation() throws MaxCountExceededException {
        getInterpolatedState();
        return this.stateVariation;
    }

    /* access modifiers changed from: protected */
    public void computeInterpolatedStateAndDerivatives(double d, double d2) {
        int i;
        double d3 = this.interpolatedTime - this.referenceTime;
        double d4 = d3 / this.scalingH;
        Arrays.fill(this.stateVariation, 0.0d);
        Arrays.fill(this.interpolatedDerivatives, 0.0d);
        double[][] dataRef = this.nordsieck.getDataRef();
        int length = dataRef.length;
        while (true) {
            length--;
            i = 0;
            if (length < 0) {
                break;
            }
            int i2 = length + 2;
            double[] dArr = dataRef[length];
            double pow = FastMath.pow(d4, i2);
            while (i < dArr.length) {
                double d5 = dArr[i] * pow;
                double[] dArr2 = this.stateVariation;
                dArr2[i] = dArr2[i] + d5;
                double[] dArr3 = this.interpolatedDerivatives;
                double[] dArr4 = dArr;
                double d6 = pow;
                dArr3[i] = dArr3[i] + (((double) i2) * d5);
                i++;
                dArr = dArr4;
                pow = d6;
            }
        }
        while (i < this.currentState.length) {
            double[] dArr5 = this.stateVariation;
            dArr5[i] = dArr5[i] + (this.scaled[i] * d4);
            this.interpolatedState[i] = this.currentState[i] + this.stateVariation[i];
            this.interpolatedDerivatives[i] = (this.interpolatedDerivatives[i] + (this.scaled[i] * d4)) / d3;
            i++;
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        writeBaseExternal(objectOutput);
        objectOutput.writeDouble(this.scalingH);
        objectOutput.writeDouble(this.referenceTime);
        int length = this.currentState == null ? -1 : this.currentState.length;
        if (this.scaled == null) {
            objectOutput.writeBoolean(false);
        } else {
            objectOutput.writeBoolean(true);
            for (int i = 0; i < length; i++) {
                objectOutput.writeDouble(this.scaled[i]);
            }
        }
        if (this.nordsieck == null) {
            objectOutput.writeBoolean(false);
            return;
        }
        objectOutput.writeBoolean(true);
        objectOutput.writeObject(this.nordsieck);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        double readBaseExternal = readBaseExternal(objectInput);
        this.scalingH = objectInput.readDouble();
        this.referenceTime = objectInput.readDouble();
        int length = this.currentState == null ? -1 : this.currentState.length;
        boolean readBoolean = objectInput.readBoolean();
        if (readBoolean) {
            this.scaled = new double[length];
            for (int i = 0; i < length; i++) {
                this.scaled[i] = objectInput.readDouble();
            }
        } else {
            this.scaled = null;
        }
        boolean readBoolean2 = objectInput.readBoolean();
        if (readBoolean2) {
            this.nordsieck = (Array2DRowRealMatrix) objectInput.readObject();
        } else {
            this.nordsieck = null;
        }
        if (!readBoolean || !readBoolean2) {
            this.stateVariation = null;
            return;
        }
        this.stateVariation = new double[length];
        setInterpolatedTime(readBaseExternal);
    }
}
