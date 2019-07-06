package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;

public class Barrier extends Helper {
    private int mBarrierType = 0;

    public void setBarrierType(int i) {
        this.mBarrierType = i;
    }

    public void addToSolver(LinearSystem linearSystem) {
        boolean z;
        this.mListAnchors[0] = this.mLeft;
        this.mListAnchors[2] = this.mTop;
        this.mListAnchors[1] = this.mRight;
        this.mListAnchors[3] = this.mBottom;
        for (int i = 0; i < this.mListAnchors.length; i++) {
            this.mListAnchors[i].mSolverVariable = linearSystem.createObjectVariable(this.mListAnchors[i]);
        }
        if (this.mBarrierType >= 0 && this.mBarrierType < 4) {
            ConstraintAnchor constraintAnchor = this.mListAnchors[this.mBarrierType];
            int i2 = 0;
            while (true) {
                if (i2 >= this.mWidgetsCount) {
                    z = false;
                    break;
                } else if (((this.mBarrierType == 0 || this.mBarrierType == 1) && this.mWidgets[i2].getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) || ((this.mBarrierType == 2 || this.mBarrierType == 3) && this.mWidgets[i2].getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT)) {
                    z = true;
                } else {
                    i2++;
                }
            }
            z = true;
            if (this.mBarrierType == 0 || this.mBarrierType == 1 ? getParent().getHorizontalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT : getParent().getVerticalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT) {
                z = false;
            }
            for (int i3 = 0; i3 < this.mWidgetsCount; i3++) {
                SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mWidgets[i3].mListAnchors[this.mBarrierType]);
                this.mWidgets[i3].mListAnchors[this.mBarrierType].mSolverVariable = createObjectVariable;
                if (this.mBarrierType == 0 || this.mBarrierType == 2) {
                    linearSystem.addLowerBarrier(constraintAnchor.mSolverVariable, createObjectVariable, z);
                } else {
                    linearSystem.addGreaterBarrier(constraintAnchor.mSolverVariable, createObjectVariable, z);
                }
            }
            if (this.mBarrierType == 0) {
                linearSystem.addEquality(this.mRight.mSolverVariable, this.mLeft.mSolverVariable, 0, 6);
                if (!z) {
                    linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 0);
                }
            } else if (this.mBarrierType == 1) {
                linearSystem.addEquality(this.mLeft.mSolverVariable, this.mRight.mSolverVariable, 0, 6);
                if (!z) {
                    linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 0);
                }
            } else if (this.mBarrierType == 2) {
                linearSystem.addEquality(this.mBottom.mSolverVariable, this.mTop.mSolverVariable, 0, 6);
                if (!z) {
                    linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 0);
                }
            } else if (this.mBarrierType == 3) {
                linearSystem.addEquality(this.mTop.mSolverVariable, this.mBottom.mSolverVariable, 0, 6);
                if (!z) {
                    linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 0);
                }
            }
        }
    }
}
