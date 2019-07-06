package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;
import java.util.Arrays;

public class ConstraintWidgetContainer extends WidgetContainer {
    int mDebugSolverPassCount = 0;
    private boolean mHeightMeasuredTooSmall = false;
    ConstraintWidget[] mHorizontalChainsArray = new ConstraintWidget[4];
    int mHorizontalChainsSize = 0;
    private boolean mIsRtl = false;
    private int mOptimizationLevel = 2;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    private Snapshot mSnapshot;
    protected LinearSystem mSystem = new LinearSystem();
    ConstraintWidget[] mVerticalChainsArray = new ConstraintWidget[4];
    int mVerticalChainsSize = 0;
    private boolean mWidthMeasuredTooSmall = false;

    public boolean handlesInternalConstraints() {
        return false;
    }

    public void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
    }

    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        super.reset();
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem) {
        addToSolver(linearSystem);
        int size = this.mChildren.size();
        if ((this.mOptimizationLevel == 2 || this.mOptimizationLevel == 4) && Optimizer.optimize(linearSystem, this)) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof ConstraintWidgetContainer) {
                DimensionBehaviour dimensionBehaviour = constraintWidget.mListDimensionBehaviors[0];
                DimensionBehaviour dimensionBehaviour2 = constraintWidget.mListDimensionBehaviors[1];
                if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
                if (dimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
                constraintWidget.addToSolver(linearSystem);
                if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour);
                }
                if (dimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour2);
                }
            } else {
                Optimizer.checkMatchParent(this, linearSystem, constraintWidget);
                constraintWidget.addToSolver(linearSystem);
            }
        }
        if (this.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, 0);
        }
        if (this.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, 1);
        }
        return true;
    }

    public void updateChildrenFromSolver(LinearSystem linearSystem, boolean[] zArr) {
        zArr[2] = false;
        updateFromSolver(linearSystem);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            constraintWidget.updateFromSolver(linearSystem);
            if (constraintWidget.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getWidth() < constraintWidget.getWrapWidth()) {
                zArr[2] = true;
            }
            if (constraintWidget.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getHeight() < constraintWidget.getWrapHeight()) {
                zArr[2] = true;
            }
        }
    }

    public void setRtl(boolean z) {
        this.mIsRtl = z;
    }

    public boolean isRtl() {
        return this.mIsRtl;
    }

    /* JADX WARNING: type inference failed for: r11v9, types: [boolean] */
    /* JADX WARNING: type inference failed for: r11v13 */
    /* JADX WARNING: type inference failed for: r11v14 */
    /* JADX WARNING: type inference failed for: r11v36 */
    /* JADX WARNING: type inference failed for: r11v37 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r11v9, types: [boolean]
      assigns: []
      uses: [?[int, short, byte, char], boolean]
      mth insns count: 245
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/871566395.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$34/1534130292.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$69/1017384824.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1037163664.run(Unknown Source)
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x017d  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01a6  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01a9  */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void layout() {
        /*
            r17 = this;
            r1 = r17
            int r2 = r1.mX
            int r3 = r1.mY
            int r4 = r17.getWidth()
            r5 = 0
            int r4 = java.lang.Math.max(r5, r4)
            int r6 = r17.getHeight()
            int r6 = java.lang.Math.max(r5, r6)
            r1.mWidthMeasuredTooSmall = r5
            r1.mHeightMeasuredTooSmall = r5
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r1.mParent
            if (r7 == 0) goto L_0x0046
            android.support.constraint.solver.widgets.Snapshot r7 = r1.mSnapshot
            if (r7 != 0) goto L_0x002a
            android.support.constraint.solver.widgets.Snapshot r7 = new android.support.constraint.solver.widgets.Snapshot
            r7.<init>(r1)
            r1.mSnapshot = r7
        L_0x002a:
            android.support.constraint.solver.widgets.Snapshot r7 = r1.mSnapshot
            r7.updateFrom(r1)
            int r7 = r1.mPaddingLeft
            r1.setX(r7)
            int r7 = r1.mPaddingTop
            r1.setY(r7)
            r17.resetAnchors()
            android.support.constraint.solver.LinearSystem r7 = r1.mSystem
            android.support.constraint.solver.Cache r7 = r7.getCache()
            r1.resetSolverVariables(r7)
            goto L_0x004a
        L_0x0046:
            r1.mX = r5
            r1.mY = r5
        L_0x004a:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r7 = r1.mListDimensionBehaviors
            r8 = 1
            r7 = r7[r8]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r9 = r1.mListDimensionBehaviors
            r9 = r9[r5]
            r17.resetChains()
            java.util.ArrayList r10 = r1.mChildren
            int r10 = r10.size()
            r11 = 0
        L_0x005d:
            if (r11 >= r10) goto L_0x0073
            java.util.ArrayList r12 = r1.mChildren
            java.lang.Object r12 = r12.get(r11)
            android.support.constraint.solver.widgets.ConstraintWidget r12 = (android.support.constraint.solver.widgets.ConstraintWidget) r12
            boolean r13 = r12 instanceof android.support.constraint.solver.widgets.WidgetContainer
            if (r13 == 0) goto L_0x0070
            android.support.constraint.solver.widgets.WidgetContainer r12 = (android.support.constraint.solver.widgets.WidgetContainer) r12
            r12.layout()
        L_0x0070:
            int r11 = r11 + 1
            goto L_0x005d
        L_0x0073:
            r11 = 1
            r12 = 0
            r13 = 0
        L_0x0076:
            if (r11 == 0) goto L_0x01eb
            int r12 = r12 + r8
            android.support.constraint.solver.LinearSystem r14 = r1.mSystem     // Catch:{ Exception -> 0x008e }
            r14.reset()     // Catch:{ Exception -> 0x008e }
            android.support.constraint.solver.LinearSystem r14 = r1.mSystem     // Catch:{ Exception -> 0x008e }
            boolean r14 = r1.addChildrenToSolver(r14)     // Catch:{ Exception -> 0x008e }
            if (r14 == 0) goto L_0x00aa
            android.support.constraint.solver.LinearSystem r11 = r1.mSystem     // Catch:{ Exception -> 0x008c }
            r11.minimize()     // Catch:{ Exception -> 0x008c }
            goto L_0x00aa
        L_0x008c:
            r0 = move-exception
            goto L_0x0090
        L_0x008e:
            r0 = move-exception
            r14 = r11
        L_0x0090:
            r11 = r0
            r11.printStackTrace()
            java.io.PrintStream r15 = java.lang.System.out
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r5 = "EXCEPTION : "
            r8.append(r5)
            r8.append(r11)
            java.lang.String r5 = r8.toString()
            r15.println(r5)
        L_0x00aa:
            r5 = 2
            if (r14 == 0) goto L_0x00b6
            android.support.constraint.solver.LinearSystem r8 = r1.mSystem
            boolean[] r11 = android.support.constraint.solver.widgets.Optimizer.flags
            r1.updateChildrenFromSolver(r8, r11)
        L_0x00b4:
            r11 = 2
            goto L_0x00fd
        L_0x00b6:
            android.support.constraint.solver.LinearSystem r8 = r1.mSystem
            r1.updateFromSolver(r8)
            r8 = 0
        L_0x00bc:
            if (r8 >= r10) goto L_0x00b4
            java.util.ArrayList r11 = r1.mChildren
            java.lang.Object r11 = r11.get(r8)
            android.support.constraint.solver.widgets.ConstraintWidget r11 = (android.support.constraint.solver.widgets.ConstraintWidget) r11
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r14 = r11.mListDimensionBehaviors
            r15 = 0
            r14 = r14[r15]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r15 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r14 != r15) goto L_0x00df
            int r14 = r11.getWidth()
            int r15 = r11.getWrapWidth()
            if (r14 >= r15) goto L_0x00df
            boolean[] r8 = android.support.constraint.solver.widgets.Optimizer.flags
            r14 = 1
            r8[r5] = r14
            goto L_0x00b4
        L_0x00df:
            r14 = 1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r15 = r11.mListDimensionBehaviors
            r15 = r15[r14]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r15 != r5) goto L_0x00f8
            int r5 = r11.getHeight()
            int r11 = r11.getWrapHeight()
            if (r5 >= r11) goto L_0x00f8
            boolean[] r5 = android.support.constraint.solver.widgets.Optimizer.flags
            r11 = 2
            r5[r11] = r14
            goto L_0x00fd
        L_0x00f8:
            r11 = 2
            int r8 = r8 + 1
            r5 = 2
            goto L_0x00bc
        L_0x00fd:
            r5 = 8
            if (r12 >= r5) goto L_0x016b
            boolean[] r5 = android.support.constraint.solver.widgets.Optimizer.flags
            boolean r5 = r5[r11]
            if (r5 == 0) goto L_0x016b
            r5 = 0
            r8 = 0
            r11 = 0
        L_0x010a:
            if (r5 >= r10) goto L_0x012e
            java.util.ArrayList r14 = r1.mChildren
            java.lang.Object r14 = r14.get(r5)
            android.support.constraint.solver.widgets.ConstraintWidget r14 = (android.support.constraint.solver.widgets.ConstraintWidget) r14
            int r15 = r14.mX
            int r16 = r14.getWidth()
            int r15 = r15 + r16
            int r8 = java.lang.Math.max(r8, r15)
            int r15 = r14.mY
            int r14 = r14.getHeight()
            int r15 = r15 + r14
            int r11 = java.lang.Math.max(r11, r15)
            int r5 = r5 + 1
            goto L_0x010a
        L_0x012e:
            int r5 = r1.mMinWidth
            int r5 = java.lang.Math.max(r5, r8)
            int r8 = r1.mMinHeight
            int r8 = java.lang.Math.max(r8, r11)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r9 != r11) goto L_0x0151
            int r11 = r17.getWidth()
            if (r11 >= r5) goto L_0x0151
            r1.setWidth(r5)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r13 = 0
            r5[r13] = r11
            r5 = 1
            r13 = 1
            goto L_0x0152
        L_0x0151:
            r5 = 0
        L_0x0152:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r11 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r7 != r11) goto L_0x0169
            int r11 = r17.getHeight()
            if (r11 >= r8) goto L_0x0169
            r1.setHeight(r8)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r11 = 1
            r5[r11] = r8
            r5 = 1
            r8 = 1
            goto L_0x016d
        L_0x0169:
            r8 = r13
            goto L_0x016d
        L_0x016b:
            r8 = r13
            r5 = 0
        L_0x016d:
            int r11 = r1.mMinWidth
            int r13 = r17.getWidth()
            int r11 = java.lang.Math.max(r11, r13)
            int r13 = r17.getWidth()
            if (r11 <= r13) goto L_0x0189
            r1.setWidth(r11)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r11 = 0
            r5[r11] = r8
            r5 = 1
            r8 = 1
        L_0x0189:
            int r11 = r1.mMinHeight
            int r13 = r17.getHeight()
            int r11 = java.lang.Math.max(r11, r13)
            int r13 = r17.getHeight()
            if (r11 <= r13) goto L_0x01a6
            r1.setHeight(r11)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r11 = 1
            r5[r11] = r8
            r5 = 1
            r8 = 1
            goto L_0x01a7
        L_0x01a6:
            r11 = 1
        L_0x01a7:
            if (r8 != 0) goto L_0x01e5
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r13 = r1.mListDimensionBehaviors
            r14 = 0
            r13 = r13[r14]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r15 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r13 != r15) goto L_0x01c7
            if (r4 <= 0) goto L_0x01c7
            int r13 = r17.getWidth()
            if (r13 <= r4) goto L_0x01c7
            r1.mWidthMeasuredTooSmall = r11
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r5[r14] = r8
            r1.setWidth(r4)
            r5 = 1
            r8 = 1
        L_0x01c7:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r13 = r1.mListDimensionBehaviors
            r13 = r13[r11]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r14 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r13 != r14) goto L_0x01e5
            if (r6 <= 0) goto L_0x01e5
            int r13 = r17.getHeight()
            if (r13 <= r6) goto L_0x01e5
            r1.mHeightMeasuredTooSmall = r11
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r1.mListDimensionBehaviors
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r5[r11] = r8
            r1.setHeight(r6)
            r11 = 1
            r13 = 1
            goto L_0x01e7
        L_0x01e5:
            r11 = r5
            r13 = r8
        L_0x01e7:
            r5 = 0
            r8 = 1
            goto L_0x0076
        L_0x01eb:
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r1.mParent
            if (r4 == 0) goto L_0x021b
            int r2 = r1.mMinWidth
            int r3 = r17.getWidth()
            int r2 = java.lang.Math.max(r2, r3)
            int r3 = r1.mMinHeight
            int r4 = r17.getHeight()
            int r3 = java.lang.Math.max(r3, r4)
            android.support.constraint.solver.widgets.Snapshot r4 = r1.mSnapshot
            r4.applyTo(r1)
            int r4 = r1.mPaddingLeft
            int r2 = r2 + r4
            int r4 = r1.mPaddingRight
            int r2 = r2 + r4
            r1.setWidth(r2)
            int r2 = r1.mPaddingTop
            int r3 = r3 + r2
            int r2 = r1.mPaddingBottom
            int r3 = r3 + r2
            r1.setHeight(r3)
            goto L_0x021f
        L_0x021b:
            r1.mX = r2
            r1.mY = r3
        L_0x021f:
            if (r13 == 0) goto L_0x022b
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r1.mListDimensionBehaviors
            r3 = 0
            r2[r3] = r9
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r1.mListDimensionBehaviors
            r3 = 1
            r2[r3] = r7
        L_0x022b:
            android.support.constraint.solver.LinearSystem r2 = r1.mSystem
            android.support.constraint.solver.Cache r2 = r2.getCache()
            r1.resetSolverVariables(r2)
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r2 = r17.getRootConstraintContainer()
            if (r1 != r2) goto L_0x023d
            r17.updateDrawPosition()
        L_0x023d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.layout():void");
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    /* access modifiers changed from: 0000 */
    public void addChain(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            while (constraintWidget.mLeft.mTarget != null && constraintWidget.mLeft.mTarget.mOwner.mRight.mTarget != null && constraintWidget.mLeft.mTarget.mOwner.mRight.mTarget == constraintWidget.mLeft && constraintWidget.mLeft.mTarget.mOwner != constraintWidget) {
                constraintWidget = constraintWidget.mLeft.mTarget.mOwner;
            }
            addHorizontalChain(constraintWidget);
        } else if (i == 1) {
            while (constraintWidget.mTop.mTarget != null && constraintWidget.mTop.mTarget.mOwner.mBottom.mTarget != null && constraintWidget.mTop.mTarget.mOwner.mBottom.mTarget == constraintWidget.mTop && constraintWidget.mTop.mTarget.mOwner != constraintWidget) {
                constraintWidget = constraintWidget.mTop.mTarget.mOwner;
            }
            addVerticalChain(constraintWidget);
        }
    }

    private void addHorizontalChain(ConstraintWidget constraintWidget) {
        int i = 0;
        while (i < this.mHorizontalChainsSize) {
            if (this.mHorizontalChainsArray[i] != constraintWidget) {
                i++;
            } else {
                return;
            }
        }
        if (this.mHorizontalChainsSize + 1 >= this.mHorizontalChainsArray.length) {
            this.mHorizontalChainsArray = (ConstraintWidget[]) Arrays.copyOf(this.mHorizontalChainsArray, this.mHorizontalChainsArray.length * 2);
        }
        this.mHorizontalChainsArray[this.mHorizontalChainsSize] = constraintWidget;
        this.mHorizontalChainsSize++;
    }

    private void addVerticalChain(ConstraintWidget constraintWidget) {
        int i = 0;
        while (i < this.mVerticalChainsSize) {
            if (this.mVerticalChainsArray[i] != constraintWidget) {
                i++;
            } else {
                return;
            }
        }
        if (this.mVerticalChainsSize + 1 >= this.mVerticalChainsArray.length) {
            this.mVerticalChainsArray = (ConstraintWidget[]) Arrays.copyOf(this.mVerticalChainsArray, this.mVerticalChainsArray.length * 2);
        }
        this.mVerticalChainsArray[this.mVerticalChainsSize] = constraintWidget;
        this.mVerticalChainsSize++;
    }
}
