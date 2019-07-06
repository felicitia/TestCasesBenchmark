package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;

class Chain {
    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i) {
        int i2;
        ConstraintWidget[] constraintWidgetArr;
        int i3;
        if (i == 0) {
            int i4 = constraintWidgetContainer.mHorizontalChainsSize;
            constraintWidgetArr = constraintWidgetContainer.mHorizontalChainsArray;
            i2 = i4;
            i3 = 0;
        } else {
            i3 = 2;
            int i5 = constraintWidgetContainer.mVerticalChainsSize;
            i2 = i5;
            constraintWidgetArr = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i6 = 0; i6 < i2; i6++) {
            applyChainConstraints(constraintWidgetContainer, linearSystem, i, i3, constraintWidgetArr[i6]);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003b, code lost:
        if (r4.mListAnchors[r36].mTarget.mOwner == r3) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0059, code lost:
        if (r3.mHorizontalChainStyle == 2) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x005d, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x007e, code lost:
        if (r3.mVerticalChainStyle == 2) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0144, code lost:
        if (r2.mListAnchors[r36].mTarget.mOwner == r8) goto L_0x0147;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer r33, android.support.constraint.solver.LinearSystem r34, int r35, int r36, android.support.constraint.solver.widgets.ConstraintWidget r37) {
        /*
            r0 = r33
            r9 = r34
            r12 = r37
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r0.mListDimensionBehaviors
            r1 = r1[r35]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r14 = 1
            if (r1 != r2) goto L_0x0011
            r1 = 1
            goto L_0x0012
        L_0x0011:
            r1 = 0
        L_0x0012:
            r15 = 0
            if (r35 != 0) goto L_0x0044
            boolean r2 = r33.isRtl()
            if (r2 == 0) goto L_0x0044
            r3 = r12
            r2 = 0
        L_0x001d:
            if (r2 != 0) goto L_0x0045
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r3.mListAnchors
            int r5 = r36 + 1
            r4 = r4[r5]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x003d
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r4.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r4.mListAnchors
            r5 = r5[r36]
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x003d
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r4.mListAnchors
            r5 = r5[r36]
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r5 = r5.mOwner
            if (r5 == r3) goto L_0x003e
        L_0x003d:
            r4 = r15
        L_0x003e:
            if (r4 == 0) goto L_0x0042
            r3 = r4
            goto L_0x001d
        L_0x0042:
            r2 = 1
            goto L_0x001d
        L_0x0044:
            r3 = r12
        L_0x0045:
            r2 = 2
            r4 = 0
            if (r35 != 0) goto L_0x006e
            int r5 = r3.mHorizontalChainStyle
            if (r5 != 0) goto L_0x004f
            r5 = 1
            goto L_0x0050
        L_0x004f:
            r5 = 0
        L_0x0050:
            int r6 = r3.mHorizontalChainStyle
            if (r6 != r14) goto L_0x0056
            r6 = 1
            goto L_0x0057
        L_0x0056:
            r6 = 0
        L_0x0057:
            int r7 = r3.mHorizontalChainStyle
            if (r7 != r2) goto L_0x005d
        L_0x005b:
            r2 = 1
            goto L_0x005e
        L_0x005d:
            r2 = 0
        L_0x005e:
            r26 = r5
            r27 = r6
            r8 = r12
            r6 = r15
            r7 = r6
            r14 = r7
            r16 = r14
            r4 = 0
            r25 = 0
            r5 = r2
            r2 = 0
            goto L_0x0081
        L_0x006e:
            int r5 = r3.mVerticalChainStyle
            if (r5 != 0) goto L_0x0074
            r5 = 1
            goto L_0x0075
        L_0x0074:
            r5 = 0
        L_0x0075:
            int r6 = r3.mVerticalChainStyle
            if (r6 != r14) goto L_0x007b
            r6 = 1
            goto L_0x007c
        L_0x007b:
            r6 = 0
        L_0x007c:
            int r7 = r3.mVerticalChainStyle
            if (r7 != r2) goto L_0x005d
            goto L_0x005b
        L_0x0081:
            if (r2 != 0) goto L_0x0157
            android.support.constraint.solver.widgets.ConstraintWidget[] r13 = r8.mListNextVisibleWidget
            r13[r35] = r15
            int r13 = r8.getVisibility()
            r15 = 8
            if (r13 == r15) goto L_0x0099
            if (r7 == 0) goto L_0x0095
            android.support.constraint.solver.widgets.ConstraintWidget[] r7 = r7.mListNextVisibleWidget
            r7[r35] = r8
        L_0x0095:
            if (r6 != 0) goto L_0x0098
            r6 = r8
        L_0x0098:
            r7 = r8
        L_0x0099:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r13 = r8.mListAnchors
            r13 = r13[r36]
            int r17 = r13.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r15 = r13.mTarget
            if (r15 == 0) goto L_0x00ad
            android.support.constraint.solver.widgets.ConstraintAnchor r15 = r13.mTarget
            int r15 = r15.getMargin()
            int r17 = r17 + r15
        L_0x00ad:
            r15 = r17
            if (r5 == 0) goto L_0x00bb
            if (r8 == r12) goto L_0x00bb
            if (r8 == r6) goto L_0x00bb
            r28 = r2
            r29 = r6
            r2 = 6
            goto L_0x00c0
        L_0x00bb:
            r28 = r2
            r29 = r6
            r2 = 1
        L_0x00c0:
            android.support.constraint.solver.SolverVariable r6 = r13.mSolverVariable
            r30 = r7
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r13.mTarget
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            r31 = r3
            r3 = 6
            r9.addGreaterThan(r6, r7, r15, r3)
            android.support.constraint.solver.SolverVariable r3 = r13.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r13.mTarget
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
            r9.addEquality(r3, r6, r15, r2)
            android.support.constraint.solver.widgets.ConstraintWidget[] r2 = r8.mListNextMatchConstraintsWidget
            r13 = 0
            r2[r35] = r13
            int r2 = r8.getVisibility()
            r3 = 8
            if (r2 == r3) goto L_0x0113
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r8.mListDimensionBehaviors
            r2 = r2[r35]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r3) goto L_0x0113
            int r4 = r4 + 1
            float[] r2 = r8.mWeight
            r2 = r2[r35]
            float r25 = r25 + r2
            if (r16 != 0) goto L_0x00f9
            r16 = r8
            goto L_0x00fd
        L_0x00f9:
            android.support.constraint.solver.widgets.ConstraintWidget[] r2 = r14.mListNextMatchConstraintsWidget
            r2[r35] = r8
        L_0x00fd:
            if (r1 == 0) goto L_0x0112
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            int r3 = r36 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r8.mListAnchors
            r3 = r3[r36]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            r6 = 0
            r7 = 6
            r9.addGreaterThan(r2, r3, r6, r7)
        L_0x0112:
            r14 = r8
        L_0x0113:
            if (r1 == 0) goto L_0x0127
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            r2 = r2[r36]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r0.mListAnchors
            r3 = r3[r36]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            r6 = 6
            r15 = 0
            r9.addGreaterThan(r2, r3, r15, r6)
            goto L_0x0128
        L_0x0127:
            r15 = 0
        L_0x0128:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            int r3 = r36 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0146
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r2.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r36]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x0146
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r36]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            if (r3 == r8) goto L_0x0147
        L_0x0146:
            r2 = r13
        L_0x0147:
            if (r2 == 0) goto L_0x014d
            r8 = r2
            r2 = r28
            goto L_0x014e
        L_0x014d:
            r2 = 1
        L_0x014e:
            r15 = r13
            r6 = r29
            r7 = r30
            r3 = r31
            goto L_0x0081
        L_0x0157:
            r31 = r3
            r13 = r15
            r15 = 0
            if (r7 == 0) goto L_0x017e
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            int r3 = r36 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x017e
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r7.mListAnchors
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r14 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r13 = r8.mListAnchors
            r3 = r13[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            int r2 = r2.getMargin()
            int r2 = -r2
            r13 = 6
            r9.addLowerThan(r14, r3, r2, r13)
        L_0x017e:
            if (r1 == 0) goto L_0x019a
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r0.mListAnchors
            int r1 = r36 + 1
            r0 = r0[r1]
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            r2 = r2[r1]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r8.mListAnchors
            r1 = r3[r1]
            int r1 = r1.getMargin()
            r3 = 6
            r9.addGreaterThan(r0, r2, r1, r3)
        L_0x019a:
            if (r4 <= 0) goto L_0x01fb
            r0 = r16
        L_0x019e:
            if (r0 == 0) goto L_0x01fb
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r0.mListNextMatchConstraintsWidget
            r1 = r1[r35]
            if (r1 == 0) goto L_0x01f8
            float[] r2 = r0.mWeight
            r18 = r2[r35]
            float[] r2 = r1.mWeight
            r20 = r2[r35]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r0.mListAnchors
            r2 = r2[r36]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r0.mListAnchors
            int r4 = r36 + 1
            r3 = r3[r4]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r13 = r1.mListAnchors
            r13 = r13[r36]
            android.support.constraint.solver.SolverVariable r13 = r13.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r14 = r1.mListAnchors
            r4 = r14[r4]
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            if (r35 != 0) goto L_0x01cf
            int r0 = r0.mMatchConstraintDefaultWidth
            int r14 = r1.mMatchConstraintDefaultWidth
            goto L_0x01d3
        L_0x01cf:
            int r0 = r0.mMatchConstraintDefaultHeight
            int r14 = r1.mMatchConstraintDefaultHeight
        L_0x01d3:
            r15 = 3
            if (r0 == 0) goto L_0x01d8
            if (r0 != r15) goto L_0x01dd
        L_0x01d8:
            if (r14 == 0) goto L_0x01df
            if (r14 != r15) goto L_0x01dd
            goto L_0x01df
        L_0x01dd:
            r0 = 0
            goto L_0x01e0
        L_0x01df:
            r0 = 1
        L_0x01e0:
            if (r0 == 0) goto L_0x01f8
            android.support.constraint.solver.ArrayRow r0 = r34.createRow()
            r17 = r0
            r19 = r25
            r21 = r2
            r22 = r3
            r23 = r13
            r24 = r4
            r17.createRowEqualMatchDimensions(r18, r19, r20, r21, r22, r23, r24)
            r9.addConstraint(r0)
        L_0x01f8:
            r0 = r1
            r15 = 0
            goto L_0x019e
        L_0x01fb:
            if (r6 == 0) goto L_0x0268
            if (r6 == r7) goto L_0x0201
            if (r5 == 0) goto L_0x0268
        L_0x0201:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r36]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r8.mListAnchors
            int r2 = r36 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r12.mListAnchors
            r3 = r3[r36]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x021c
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r12.mListAnchors
            r3 = r3[r36]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x021d
        L_0x021c:
            r3 = 0
        L_0x021d:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r8.mListAnchors
            r4 = r4[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x022f
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r8.mListAnchors
            r4 = r4[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            r5 = r4
            goto L_0x0230
        L_0x022f:
            r5 = 0
        L_0x0230:
            if (r6 != r7) goto L_0x023a
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r6.mListAnchors
            r0 = r0[r36]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r6.mListAnchors
            r1 = r1[r2]
        L_0x023a:
            if (r3 == 0) goto L_0x03f3
            if (r5 == 0) goto L_0x03f3
            if (r35 != 0) goto L_0x0245
            r12 = r31
            float r4 = r12.mHorizontalBiasPercent
            goto L_0x0249
        L_0x0245:
            r12 = r31
            float r4 = r12.mVerticalBiasPercent
        L_0x0249:
            int r6 = r0.getMargin()
            if (r7 != 0) goto L_0x0250
            r7 = r8
        L_0x0250:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r7.mListAnchors
            r2 = r7[r2]
            int r7 = r2.getMargin()
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r8 = r1.mSolverVariable
            r10 = 5
            r0 = r9
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = r8
            r8 = r10
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x03f3
        L_0x0268:
            if (r26 == 0) goto L_0x030e
            if (r6 == 0) goto L_0x030e
            r0 = r6
            r12 = r0
        L_0x026e:
            if (r12 == 0) goto L_0x03f3
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r12.mListNextVisibleWidget
            r13 = r1[r35]
            if (r13 != 0) goto L_0x0280
            if (r12 != r7) goto L_0x0279
            goto L_0x0280
        L_0x0279:
            r15 = r6
            r14 = r7
            r32 = r13
            r13 = r8
            goto L_0x0306
        L_0x0280:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r36]
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            if (r3 == 0) goto L_0x028f
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            android.support.constraint.solver.SolverVariable r15 = r3.mSolverVariable
            goto L_0x0290
        L_0x028f:
            r15 = 0
        L_0x0290:
            if (r0 == r12) goto L_0x029b
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r0.mListAnchors
            int r3 = r36 + 1
            r0 = r0[r3]
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            r15 = r0
        L_0x029b:
            r1.getMargin()
            if (r13 == 0) goto L_0x02b6
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r36]
            android.support.constraint.solver.SolverVariable r1 = r0.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mTarget
            if (r3 == 0) goto L_0x02af
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x02b0
        L_0x02af:
            r3 = 0
        L_0x02b0:
            r0.getMargin()
            r5 = r1
            r14 = r3
            goto L_0x02cc
        L_0x02b6:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r8.mListAnchors
            int r1 = r36 + 1
            r0 = r0[r1]
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            if (r0 == 0) goto L_0x02c3
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            goto L_0x02c4
        L_0x02c3:
            r0 = 0
        L_0x02c4:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r12.mListAnchors
            r1 = r3[r1]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            r5 = r0
            r14 = r1
        L_0x02cc:
            if (r2 == 0) goto L_0x0279
            if (r15 == 0) goto L_0x0279
            if (r5 == 0) goto L_0x0279
            if (r14 == 0) goto L_0x0279
            if (r12 != r6) goto L_0x02e0
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r6.mListAnchors
            r0 = r0[r36]
            int r0 = r0.getMargin()
            r3 = r0
            goto L_0x02e1
        L_0x02e0:
            r3 = 0
        L_0x02e1:
            if (r12 != r7) goto L_0x02f0
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r7.mListAnchors
            int r1 = r36 + 1
            r0 = r0[r1]
            int r0 = r0.getMargin()
            r16 = r0
            goto L_0x02f2
        L_0x02f0:
            r16 = 0
        L_0x02f2:
            r4 = 1056964608(0x3f000000, float:0.5)
            r17 = 4
            r0 = r9
            r1 = r2
            r2 = r15
            r15 = r6
            r6 = r14
            r14 = r7
            r7 = r16
            r32 = r13
            r13 = r8
            r8 = r17
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x0306:
            r0 = r12
            r8 = r13
            r7 = r14
            r6 = r15
            r12 = r32
            goto L_0x026e
        L_0x030e:
            r15 = r6
            r14 = r7
            r13 = r8
            if (r27 == 0) goto L_0x03f3
            if (r15 == 0) goto L_0x03f3
            r0 = r15
            r8 = r0
        L_0x0317:
            if (r8 == 0) goto L_0x0399
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r8.mListNextVisibleWidget
            r1 = r1[r35]
            if (r8 == r15) goto L_0x0392
            if (r1 == 0) goto L_0x0392
            if (r1 != r14) goto L_0x0325
            r7 = 0
            goto L_0x0326
        L_0x0325:
            r7 = r1
        L_0x0326:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r8.mListAnchors
            r1 = r1[r36]
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            if (r3 == 0) goto L_0x0334
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
        L_0x0334:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r0.mListAnchors
            int r3 = r36 + 1
            r0 = r0[r3]
            android.support.constraint.solver.SolverVariable r4 = r0.mSolverVariable
            r1.getMargin()
            if (r7 == 0) goto L_0x0357
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r7.mListAnchors
            r0 = r0[r36]
            android.support.constraint.solver.SolverVariable r1 = r0.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mTarget
            if (r3 == 0) goto L_0x0350
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x0351
        L_0x0350:
            r3 = 0
        L_0x0351:
            r0.getMargin()
            r5 = r1
            r6 = r3
            goto L_0x036b
        L_0x0357:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r8.mListAnchors
            r0 = r0[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            if (r0 == 0) goto L_0x0362
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            goto L_0x0363
        L_0x0362:
            r0 = 0
        L_0x0363:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r8.mListAnchors
            r1 = r1[r3]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            r5 = r0
            r6 = r1
        L_0x036b:
            if (r2 == 0) goto L_0x038b
            if (r4 == 0) goto L_0x038b
            if (r5 == 0) goto L_0x038b
            if (r6 == 0) goto L_0x038b
            r3 = 0
            r16 = 1056964608(0x3f000000, float:0.5)
            r17 = 0
            r18 = 4
            r0 = r9
            r1 = r2
            r2 = r4
            r4 = r16
            r16 = r7
            r7 = r17
            r17 = r8
            r8 = r18
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x038f
        L_0x038b:
            r16 = r7
            r17 = r8
        L_0x038f:
            r8 = r16
            goto L_0x0395
        L_0x0392:
            r17 = r8
            r8 = r1
        L_0x0395:
            r0 = r17
            goto L_0x0317
        L_0x0399:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r15.mListAnchors
            r0 = r0[r36]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r36]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            r3 = 1
            int r3 = r36 + 1
            r10 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r2.mTarget
            if (r1 == 0) goto L_0x03e2
            if (r15 == r14) goto L_0x03c1
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            int r0 = r0.getMargin()
            r3 = 6
            r9.addEquality(r2, r1, r0, r3)
            goto L_0x03e2
        L_0x03c1:
            if (r11 == 0) goto L_0x03e2
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r3 = r1.mSolverVariable
            int r4 = r0.getMargin()
            r5 = 1056964608(0x3f000000, float:0.5)
            android.support.constraint.solver.SolverVariable r6 = r10.mSolverVariable
            android.support.constraint.solver.SolverVariable r7 = r11.mSolverVariable
            int r8 = r10.getMargin()
            r12 = 6
            r0 = r9
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r12
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x03e2:
            if (r11 == 0) goto L_0x03f3
            if (r15 == r14) goto L_0x03f3
            android.support.constraint.solver.SolverVariable r0 = r10.mSolverVariable
            android.support.constraint.solver.SolverVariable r1 = r11.mSolverVariable
            int r2 = r10.getMargin()
            int r2 = -r2
            r3 = 6
            r9.addEquality(r0, r1, r2, r3)
        L_0x03f3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Chain.applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ConstraintWidget):void");
    }
}
