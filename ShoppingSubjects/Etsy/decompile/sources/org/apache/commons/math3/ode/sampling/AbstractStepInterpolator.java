package org.apache.commons.math3.ode.sampling;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.EquationsMapper;

public abstract class AbstractStepInterpolator implements StepInterpolator {
    protected double[] currentState;
    private boolean dirtyState;
    private boolean finalized;
    private boolean forward;
    private double globalCurrentTime;
    private double globalPreviousTime;
    protected double h;
    protected double[] interpolatedDerivatives;
    protected double[] interpolatedPrimaryDerivatives;
    protected double[] interpolatedPrimaryState;
    protected double[][] interpolatedSecondaryDerivatives;
    protected double[][] interpolatedSecondaryState;
    protected double[] interpolatedState;
    protected double interpolatedTime;
    private EquationsMapper primaryMapper;
    private EquationsMapper[] secondaryMappers;
    private double softCurrentTime;
    private double softPreviousTime;

    /* access modifiers changed from: protected */
    public abstract void computeInterpolatedStateAndDerivatives(double d, double d2) throws MaxCountExceededException;

    /* access modifiers changed from: protected */
    public abstract StepInterpolator doCopy();

    /* access modifiers changed from: protected */
    public void doFinalize() throws MaxCountExceededException {
    }

    public abstract void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException;

    public abstract void writeExternal(ObjectOutput objectOutput) throws IOException;

    protected AbstractStepInterpolator() {
        this.globalPreviousTime = Double.NaN;
        this.globalCurrentTime = Double.NaN;
        this.softPreviousTime = Double.NaN;
        this.softCurrentTime = Double.NaN;
        this.h = Double.NaN;
        this.interpolatedTime = Double.NaN;
        this.currentState = null;
        this.finalized = false;
        this.forward = true;
        this.dirtyState = true;
        this.primaryMapper = null;
        this.secondaryMappers = null;
        allocateInterpolatedArrays(-1);
    }

    protected AbstractStepInterpolator(double[] dArr, boolean z, EquationsMapper equationsMapper, EquationsMapper[] equationsMapperArr) {
        EquationsMapper[] equationsMapperArr2;
        this.globalPreviousTime = Double.NaN;
        this.globalCurrentTime = Double.NaN;
        this.softPreviousTime = Double.NaN;
        this.softCurrentTime = Double.NaN;
        this.h = Double.NaN;
        this.interpolatedTime = Double.NaN;
        this.currentState = dArr;
        this.finalized = false;
        this.forward = z;
        this.dirtyState = true;
        this.primaryMapper = equationsMapper;
        if (equationsMapperArr == null) {
            equationsMapperArr2 = null;
        } else {
            equationsMapperArr2 = (EquationsMapper[]) equationsMapperArr.clone();
        }
        this.secondaryMappers = equationsMapperArr2;
        allocateInterpolatedArrays(dArr.length);
    }

    protected AbstractStepInterpolator(AbstractStepInterpolator abstractStepInterpolator) {
        this.globalPreviousTime = abstractStepInterpolator.globalPreviousTime;
        this.globalCurrentTime = abstractStepInterpolator.globalCurrentTime;
        this.softPreviousTime = abstractStepInterpolator.softPreviousTime;
        this.softCurrentTime = abstractStepInterpolator.softCurrentTime;
        this.h = abstractStepInterpolator.h;
        this.interpolatedTime = abstractStepInterpolator.interpolatedTime;
        EquationsMapper[] equationsMapperArr = null;
        if (abstractStepInterpolator.currentState == null) {
            this.currentState = null;
            this.primaryMapper = null;
            this.secondaryMappers = null;
            allocateInterpolatedArrays(-1);
        } else {
            this.currentState = (double[]) abstractStepInterpolator.currentState.clone();
            this.interpolatedState = (double[]) abstractStepInterpolator.interpolatedState.clone();
            this.interpolatedDerivatives = (double[]) abstractStepInterpolator.interpolatedDerivatives.clone();
            this.interpolatedPrimaryState = (double[]) abstractStepInterpolator.interpolatedPrimaryState.clone();
            this.interpolatedPrimaryDerivatives = (double[]) abstractStepInterpolator.interpolatedPrimaryDerivatives.clone();
            this.interpolatedSecondaryState = new double[abstractStepInterpolator.interpolatedSecondaryState.length][];
            this.interpolatedSecondaryDerivatives = new double[abstractStepInterpolator.interpolatedSecondaryDerivatives.length][];
            for (int i = 0; i < this.interpolatedSecondaryState.length; i++) {
                this.interpolatedSecondaryState[i] = (double[]) abstractStepInterpolator.interpolatedSecondaryState[i].clone();
                this.interpolatedSecondaryDerivatives[i] = (double[]) abstractStepInterpolator.interpolatedSecondaryDerivatives[i].clone();
            }
        }
        this.finalized = abstractStepInterpolator.finalized;
        this.forward = abstractStepInterpolator.forward;
        this.dirtyState = abstractStepInterpolator.dirtyState;
        this.primaryMapper = abstractStepInterpolator.primaryMapper;
        if (abstractStepInterpolator.secondaryMappers != null) {
            equationsMapperArr = (EquationsMapper[]) abstractStepInterpolator.secondaryMappers.clone();
        }
        this.secondaryMappers = equationsMapperArr;
    }

    private void allocateInterpolatedArrays(int i) {
        if (i < 0) {
            this.interpolatedState = null;
            this.interpolatedDerivatives = null;
            this.interpolatedPrimaryState = null;
            this.interpolatedPrimaryDerivatives = null;
            double[][] dArr = null;
            this.interpolatedSecondaryState = dArr;
            this.interpolatedSecondaryDerivatives = dArr;
            return;
        }
        this.interpolatedState = new double[i];
        this.interpolatedDerivatives = new double[i];
        this.interpolatedPrimaryState = new double[this.primaryMapper.getDimension()];
        this.interpolatedPrimaryDerivatives = new double[this.primaryMapper.getDimension()];
        if (this.secondaryMappers == null) {
            double[][] dArr2 = null;
            this.interpolatedSecondaryState = dArr2;
            this.interpolatedSecondaryDerivatives = dArr2;
            return;
        }
        this.interpolatedSecondaryState = new double[this.secondaryMappers.length][];
        this.interpolatedSecondaryDerivatives = new double[this.secondaryMappers.length][];
        for (int i2 = 0; i2 < this.secondaryMappers.length; i2++) {
            this.interpolatedSecondaryState[i2] = new double[this.secondaryMappers[i2].getDimension()];
            this.interpolatedSecondaryDerivatives[i2] = new double[this.secondaryMappers[i2].getDimension()];
        }
    }

    /* access modifiers changed from: protected */
    public void reinitialize(double[] dArr, boolean z, EquationsMapper equationsMapper, EquationsMapper[] equationsMapperArr) {
        this.globalPreviousTime = Double.NaN;
        this.globalCurrentTime = Double.NaN;
        this.softPreviousTime = Double.NaN;
        this.softCurrentTime = Double.NaN;
        this.h = Double.NaN;
        this.interpolatedTime = Double.NaN;
        this.currentState = dArr;
        this.finalized = false;
        this.forward = z;
        this.dirtyState = true;
        this.primaryMapper = equationsMapper;
        this.secondaryMappers = (EquationsMapper[]) equationsMapperArr.clone();
        allocateInterpolatedArrays(dArr.length);
    }

    public StepInterpolator copy() throws MaxCountExceededException {
        finalizeStep();
        return doCopy();
    }

    public void shift() {
        this.globalPreviousTime = this.globalCurrentTime;
        this.softPreviousTime = this.globalPreviousTime;
        this.softCurrentTime = this.globalCurrentTime;
    }

    public void storeTime(double d) {
        this.globalCurrentTime = d;
        this.softCurrentTime = this.globalCurrentTime;
        this.h = this.globalCurrentTime - this.globalPreviousTime;
        setInterpolatedTime(d);
        this.finalized = false;
    }

    public void setSoftPreviousTime(double d) {
        this.softPreviousTime = d;
    }

    public void setSoftCurrentTime(double d) {
        this.softCurrentTime = d;
    }

    public double getGlobalPreviousTime() {
        return this.globalPreviousTime;
    }

    public double getGlobalCurrentTime() {
        return this.globalCurrentTime;
    }

    public double getPreviousTime() {
        return this.softPreviousTime;
    }

    public double getCurrentTime() {
        return this.softCurrentTime;
    }

    public double getInterpolatedTime() {
        return this.interpolatedTime;
    }

    public void setInterpolatedTime(double d) {
        this.interpolatedTime = d;
        this.dirtyState = true;
    }

    public boolean isForward() {
        return this.forward;
    }

    private void evaluateCompleteInterpolatedState() throws MaxCountExceededException {
        if (this.dirtyState) {
            double d = this.globalCurrentTime - this.interpolatedTime;
            double d2 = 0.0d;
            if (this.h != 0.0d) {
                d2 = (this.h - d) / this.h;
            }
            computeInterpolatedStateAndDerivatives(d2, d);
            this.dirtyState = false;
        }
    }

    public double[] getInterpolatedState() throws MaxCountExceededException {
        evaluateCompleteInterpolatedState();
        this.primaryMapper.extractEquationData(this.interpolatedState, this.interpolatedPrimaryState);
        return this.interpolatedPrimaryState;
    }

    public double[] getInterpolatedDerivatives() throws MaxCountExceededException {
        evaluateCompleteInterpolatedState();
        this.primaryMapper.extractEquationData(this.interpolatedDerivatives, this.interpolatedPrimaryDerivatives);
        return this.interpolatedPrimaryDerivatives;
    }

    public double[] getInterpolatedSecondaryState(int i) throws MaxCountExceededException {
        evaluateCompleteInterpolatedState();
        this.secondaryMappers[i].extractEquationData(this.interpolatedState, this.interpolatedSecondaryState[i]);
        return this.interpolatedSecondaryState[i];
    }

    public double[] getInterpolatedSecondaryDerivatives(int i) throws MaxCountExceededException {
        evaluateCompleteInterpolatedState();
        this.secondaryMappers[i].extractEquationData(this.interpolatedDerivatives, this.interpolatedSecondaryDerivatives[i]);
        return this.interpolatedSecondaryDerivatives[i];
    }

    public final void finalizeStep() throws MaxCountExceededException {
        if (!this.finalized) {
            doFinalize();
            this.finalized = true;
        }
    }

    /* access modifiers changed from: protected */
    public void writeBaseExternal(ObjectOutput objectOutput) throws IOException {
        if (this.currentState == null) {
            objectOutput.writeInt(-1);
        } else {
            objectOutput.writeInt(this.currentState.length);
        }
        objectOutput.writeDouble(this.globalPreviousTime);
        objectOutput.writeDouble(this.globalCurrentTime);
        objectOutput.writeDouble(this.softPreviousTime);
        objectOutput.writeDouble(this.softCurrentTime);
        objectOutput.writeDouble(this.h);
        objectOutput.writeBoolean(this.forward);
        objectOutput.writeObject(this.primaryMapper);
        objectOutput.write(this.secondaryMappers.length);
        for (EquationsMapper writeObject : this.secondaryMappers) {
            objectOutput.writeObject(writeObject);
        }
        if (this.currentState != null) {
            for (double writeDouble : this.currentState) {
                objectOutput.writeDouble(writeDouble);
            }
        }
        objectOutput.writeDouble(this.interpolatedTime);
        try {
            finalizeStep();
        } catch (MaxCountExceededException e) {
            IOException iOException = new IOException(e.getLocalizedMessage());
            iOException.initCause(e);
            throw iOException;
        }
    }

    /* access modifiers changed from: protected */
    public double readBaseExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        int readInt = objectInput.readInt();
        this.globalPreviousTime = objectInput.readDouble();
        this.globalCurrentTime = objectInput.readDouble();
        this.softPreviousTime = objectInput.readDouble();
        this.softCurrentTime = objectInput.readDouble();
        this.h = objectInput.readDouble();
        this.forward = objectInput.readBoolean();
        this.primaryMapper = (EquationsMapper) objectInput.readObject();
        this.secondaryMappers = new EquationsMapper[objectInput.read()];
        for (int i = 0; i < this.secondaryMappers.length; i++) {
            this.secondaryMappers[i] = (EquationsMapper) objectInput.readObject();
        }
        this.dirtyState = true;
        if (readInt < 0) {
            this.currentState = null;
        } else {
            this.currentState = new double[readInt];
            for (int i2 = 0; i2 < this.currentState.length; i2++) {
                this.currentState[i2] = objectInput.readDouble();
            }
        }
        this.interpolatedTime = Double.NaN;
        allocateInterpolatedArrays(readInt);
        this.finalized = true;
        return objectInput.readDouble();
    }
}
