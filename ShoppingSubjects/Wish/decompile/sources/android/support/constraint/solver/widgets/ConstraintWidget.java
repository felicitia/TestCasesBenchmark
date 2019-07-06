package android.support.constraint.solver.widgets;

import android.support.constraint.solver.Cache;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintAnchor.Strength;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;
import java.util.ArrayList;

public class ConstraintWidget {
    public static float DEFAULT_BIAS = 0.5f;
    protected ArrayList<ConstraintAnchor> mAnchors = new ArrayList<>();
    ConstraintAnchor mBaseline = new ConstraintAnchor(this, Type.BASELINE);
    int mBaselineDistance = 0;
    ConstraintAnchor mBottom = new ConstraintAnchor(this, Type.BOTTOM);
    ConstraintAnchor mCenter = new ConstraintAnchor(this, Type.CENTER);
    ConstraintAnchor mCenterX = new ConstraintAnchor(this, Type.CENTER_X);
    ConstraintAnchor mCenterY = new ConstraintAnchor(this, Type.CENTER_Y);
    private float mCircleConstraintAngle = 0.0f;
    private Object mCompanionWidget;
    private int mContainerItemSkip = 0;
    private String mDebugName = null;
    protected float mDimensionRatio = 0.0f;
    protected int mDimensionRatioSide = -1;
    private int mDrawHeight = 0;
    private int mDrawWidth = 0;
    private int mDrawX = 0;
    private int mDrawY = 0;
    int mHeight = 0;
    float mHorizontalBiasPercent = DEFAULT_BIAS;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle = 0;
    ConstraintWidget mHorizontalNextWidget = null;
    public int mHorizontalResolution = -1;
    boolean mHorizontalWrapVisited;
    boolean mIsHeightWrapContent;
    boolean mIsWidthWrapContent;
    ConstraintAnchor mLeft = new ConstraintAnchor(this, Type.LEFT);
    protected ConstraintAnchor[] mListAnchors = {this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter};
    protected DimensionBehaviour[] mListDimensionBehaviors = {DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
    protected ConstraintWidget[] mListNextMatchConstraintsWidget = {null, null};
    protected ConstraintWidget[] mListNextVisibleWidget = {null, null};
    int mMatchConstraintDefaultHeight = 0;
    int mMatchConstraintDefaultWidth = 0;
    int mMatchConstraintMaxHeight = 0;
    int mMatchConstraintMaxWidth = 0;
    int mMatchConstraintMinHeight = 0;
    int mMatchConstraintMinWidth = 0;
    float mMatchConstraintPercentHeight = 1.0f;
    float mMatchConstraintPercentWidth = 1.0f;
    private int[] mMaxDimension = {Integer.MAX_VALUE, Integer.MAX_VALUE};
    protected int mMinHeight;
    protected int mMinWidth;
    protected int mOffsetX = 0;
    protected int mOffsetY = 0;
    ConstraintWidget mParent = null;
    float mResolvedDimensionRatio = 1.0f;
    int mResolvedDimensionRatioSide = -1;
    ConstraintAnchor mRight = new ConstraintAnchor(this, Type.RIGHT);
    ConstraintAnchor mTop = new ConstraintAnchor(this, Type.TOP);
    private String mType = null;
    float mVerticalBiasPercent = DEFAULT_BIAS;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle = 0;
    ConstraintWidget mVerticalNextWidget = null;
    public int mVerticalResolution = -1;
    boolean mVerticalWrapVisited;
    private int mVisibility = 0;
    float[] mWeight = {0.0f, 0.0f};
    int mWidth = 0;
    private int mWrapHeight;
    private int mWrapWidth;
    protected int mX = 0;
    protected int mY = 0;

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public void setMaxWidth(int i) {
        this.mMaxDimension[0] = i;
    }

    public void setMaxHeight(int i) {
        this.mMaxDimension[1] = i;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mWrapWidth = 0;
        this.mWrapHeight = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        this.mWeight[0] = 0.0f;
        this.mWeight[1] = 0.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMaxDimension[0] = Integer.MAX_VALUE;
        this.mMaxDimension[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
    }

    public ConstraintWidget() {
        addAnchors();
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public void setWidthWrapContent(boolean z) {
        this.mIsWidthWrapContent = z;
    }

    public void setHeightWrapContent(boolean z) {
        this.mIsHeightWrapContent = z;
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget, float f, int i) {
        immediateConnect(Type.CENTER, constraintWidget, Type.CENTER, i, 0);
        this.mCircleConstraintAngle = f;
    }

    public void setVisibility(int i) {
        this.mVisibility = i;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    public String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder();
        if (this.mType != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("type: ");
            sb2.append(this.mType);
            sb2.append(" ");
            str = sb2.toString();
        } else {
            str = "";
        }
        sb.append(str);
        if (this.mDebugName != null) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("id: ");
            sb3.append(this.mDebugName);
            sb3.append(" ");
            str2 = sb3.toString();
        } else {
            str2 = "";
        }
        sb.append(str2);
        sb.append("(");
        sb.append(this.mX);
        sb.append(", ");
        sb.append(this.mY);
        sb.append(") - (");
        sb.append(this.mWidth);
        sb.append(" x ");
        sb.append(this.mHeight);
        sb.append(") wrap: (");
        sb.append(this.mWrapWidth);
        sb.append(" x ");
        sb.append(this.mWrapHeight);
        sb.append(")");
        return sb.toString();
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getWrapWidth() {
        return this.mWrapWidth;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getWrapHeight() {
        return this.mWrapHeight;
    }

    public int getDrawX() {
        return this.mDrawX + this.mOffsetX;
    }

    public int getDrawY() {
        return this.mDrawY + this.mOffsetY;
    }

    /* access modifiers changed from: protected */
    public int getRootX() {
        return this.mX + this.mOffsetX;
    }

    /* access modifiers changed from: protected */
    public int getRootY() {
        return this.mY + this.mOffsetY;
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public boolean hasBaseline() {
        return this.mBaselineDistance > 0;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int i) {
        this.mX = i;
    }

    public void setY(int i) {
        this.mY = i;
    }

    public void setOrigin(int i, int i2) {
        this.mX = i;
        this.mY = i2;
    }

    public void setOffset(int i, int i2) {
        this.mOffsetX = i;
        this.mOffsetY = i2;
    }

    public void updateDrawPosition() {
        int i = this.mX;
        int i2 = this.mY;
        int i3 = this.mX + this.mWidth;
        int i4 = this.mY + this.mHeight;
        this.mDrawX = i;
        this.mDrawY = i2;
        this.mDrawWidth = i3 - i;
        this.mDrawHeight = i4 - i2;
    }

    public void setWidth(int i) {
        this.mWidth = i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHeight(int i) {
        this.mHeight = i;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setHorizontalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultWidth = i;
        this.mMatchConstraintMinWidth = i2;
        this.mMatchConstraintMaxWidth = i3;
        this.mMatchConstraintPercentWidth = f;
        if (f < 1.0f && this.mMatchConstraintDefaultWidth == 0) {
            this.mMatchConstraintDefaultWidth = 2;
        }
    }

    public void setVerticalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultHeight = i;
        this.mMatchConstraintMinHeight = i2;
        this.mMatchConstraintMaxHeight = i3;
        this.mMatchConstraintPercentHeight = f;
        if (f < 1.0f && this.mMatchConstraintDefaultHeight == 0) {
            this.mMatchConstraintDefaultHeight = 2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0089  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDimensionRatio(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto L_0x008e
            int r1 = r9.length()
            if (r1 != 0) goto L_0x000b
            goto L_0x008e
        L_0x000b:
            r1 = -1
            int r2 = r9.length()
            r3 = 44
            int r3 = r9.indexOf(r3)
            r4 = 0
            r5 = 1
            if (r3 <= 0) goto L_0x0037
            int r6 = r2 + -1
            if (r3 >= r6) goto L_0x0037
            java.lang.String r6 = r9.substring(r4, r3)
            java.lang.String r7 = "W"
            boolean r7 = r6.equalsIgnoreCase(r7)
            if (r7 == 0) goto L_0x002c
            r1 = 0
            goto L_0x0035
        L_0x002c:
            java.lang.String r4 = "H"
            boolean r4 = r6.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x0035
            r1 = 1
        L_0x0035:
            int r4 = r3 + 1
        L_0x0037:
            r3 = 58
            int r3 = r9.indexOf(r3)
            if (r3 < 0) goto L_0x0075
            int r2 = r2 - r5
            if (r3 >= r2) goto L_0x0075
            java.lang.String r2 = r9.substring(r4, r3)
            int r3 = r3 + r5
            java.lang.String r9 = r9.substring(r3)
            int r3 = r2.length()
            if (r3 <= 0) goto L_0x0084
            int r3 = r9.length()
            if (r3 <= 0) goto L_0x0084
            float r2 = java.lang.Float.parseFloat(r2)     // Catch:{ NumberFormatException -> 0x0084 }
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            int r3 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x0084
            int r3 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x0084
            if (r1 != r5) goto L_0x006f
            float r9 = r9 / r2
            float r9 = java.lang.Math.abs(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x006f:
            float r2 = r2 / r9
            float r9 = java.lang.Math.abs(r2)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x0075:
            java.lang.String r9 = r9.substring(r4)
            int r2 = r9.length()
            if (r2 <= 0) goto L_0x0084
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0084 }
            goto L_0x0085
        L_0x0084:
            r9 = 0
        L_0x0085:
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x008d
            r8.mDimensionRatio = r9
            r8.mDimensionRatioSide = r1
        L_0x008d:
            return
        L_0x008e:
            r8.mDimensionRatio = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidget.setDimensionRatio(java.lang.String):void");
    }

    public void setHorizontalBiasPercent(float f) {
        this.mHorizontalBiasPercent = f;
    }

    public void setVerticalBiasPercent(float f) {
        this.mVerticalBiasPercent = f;
    }

    public void setMinWidth(int i) {
        if (i < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i;
        }
    }

    public void setMinHeight(int i) {
        if (i < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i;
        }
    }

    public void setWrapWidth(int i) {
        this.mWrapWidth = i;
    }

    public void setWrapHeight(int i) {
        this.mWrapHeight = i;
    }

    public void setFrame(int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        this.mX = i;
        this.mY = i2;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i5 < this.mWidth) {
            i5 = this.mWidth;
        }
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i6 < this.mHeight) {
            i6 = this.mHeight;
        }
        this.mWidth = i5;
        this.mHeight = i6;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHorizontalDimension(int i, int i2) {
        this.mX = i;
        this.mWidth = i2 - i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setVerticalDimension(int i, int i2) {
        this.mY = i;
        this.mHeight = i2 - i;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setBaselineDistance(int i) {
        this.mBaselineDistance = i;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setHorizontalWeight(float f) {
        this.mWeight[0] = f;
    }

    public void setVerticalWeight(float f) {
        this.mWeight[1] = f;
    }

    public void setHorizontalChainStyle(int i) {
        this.mHorizontalChainStyle = i;
    }

    public void setVerticalChainStyle(int i) {
        this.mVerticalChainStyle = i;
    }

    public void immediateConnect(Type type, ConstraintWidget constraintWidget, Type type2, int i, int i2) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i, i2, Strength.STRONG, 0, true);
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i = 0; i < size; i++) {
                ((ConstraintAnchor) this.mAnchors.get(i)).reset();
            }
        }
    }

    public ConstraintAnchor getAnchor(Type type) {
        switch (type) {
            case LEFT:
                return this.mLeft;
            case TOP:
                return this.mTop;
            case RIGHT:
                return this.mRight;
            case BOTTOM:
                return this.mBottom;
            case BASELINE:
                return this.mBaseline;
            case CENTER:
                return this.mCenter;
            case CENTER_X:
                return this.mCenterX;
            case CENTER_Y:
                return this.mCenterY;
            case NONE:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[0] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setWidth(this.mWrapWidth);
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[1] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setHeight(this.mWrapHeight);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:139:0x028c  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x0296  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x029c  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x02a6  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x02e7  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x0310  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x031a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addToSolver(android.support.constraint.solver.LinearSystem r42) {
        /*
            r41 = this;
            r15 = r41
            r14 = r42
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mLeft
            android.support.constraint.solver.SolverVariable r21 = r14.createObjectVariable(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mRight
            android.support.constraint.solver.SolverVariable r13 = r14.createObjectVariable(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mTop
            android.support.constraint.solver.SolverVariable r12 = r14.createObjectVariable(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mBottom
            android.support.constraint.solver.SolverVariable r11 = r14.createObjectVariable(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mBaseline
            android.support.constraint.solver.SolverVariable r10 = r14.createObjectVariable(r0)
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            r1 = 8
            r9 = 1
            r8 = 0
            if (r0 == 0) goto L_0x00e5
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x003a
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r0 = r0[r8]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r2) goto L_0x003a
            r0 = 1
            goto L_0x003b
        L_0x003a:
            r0 = 0
        L_0x003b:
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r15.mParent
            if (r2 == 0) goto L_0x004b
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r2.mListDimensionBehaviors
            r2 = r2[r9]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r2 != r3) goto L_0x004b
            r2 = 1
            goto L_0x004c
        L_0x004b:
            r2 = 0
        L_0x004c:
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x005c
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mLeft
            if (r3 == r4) goto L_0x006c
        L_0x005c:
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x0075
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mRight
            if (r3 != r4) goto L_0x0075
        L_0x006c:
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r3 = (android.support.constraint.solver.widgets.ConstraintWidgetContainer) r3
            r3.addChain(r15, r8)
            r3 = 1
            goto L_0x0076
        L_0x0075:
            r3 = 0
        L_0x0076:
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x0086
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r15.mTop
            if (r4 == r5) goto L_0x0096
        L_0x0086:
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x009f
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r15.mBottom
            if (r4 != r5) goto L_0x009f
        L_0x0096:
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r4 = (android.support.constraint.solver.widgets.ConstraintWidgetContainer) r4
            r4.addChain(r15, r9)
            r4 = 1
            goto L_0x00a0
        L_0x009f:
            r4 = 0
        L_0x00a0:
            if (r0 == 0) goto L_0x00bd
            int r5 = r15.mVisibility
            if (r5 == r1) goto L_0x00bd
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 != 0) goto L_0x00bd
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r15.mRight
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 != 0) goto L_0x00bd
            android.support.constraint.solver.widgets.ConstraintWidget r5 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mRight
            android.support.constraint.solver.SolverVariable r5 = r14.createObjectVariable(r5)
            r14.addGreaterThan(r5, r13, r8, r9)
        L_0x00bd:
            if (r2 == 0) goto L_0x00de
            int r5 = r15.mVisibility
            if (r5 == r1) goto L_0x00de
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 != 0) goto L_0x00de
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r15.mBottom
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 != 0) goto L_0x00de
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r15.mBaseline
            if (r5 != 0) goto L_0x00de
            android.support.constraint.solver.widgets.ConstraintWidget r5 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mBottom
            android.support.constraint.solver.SolverVariable r5 = r14.createObjectVariable(r5)
            r14.addGreaterThan(r5, r11, r8, r9)
        L_0x00de:
            r7 = r2
            r16 = r3
            r22 = r4
            r2 = r0
            goto L_0x00eb
        L_0x00e5:
            r2 = 0
            r7 = 0
            r16 = 0
            r22 = 0
        L_0x00eb:
            int r0 = r15.mWidth
            int r3 = r15.mMinWidth
            if (r0 >= r3) goto L_0x00f3
            int r0 = r15.mMinWidth
        L_0x00f3:
            int r3 = r15.mHeight
            int r4 = r15.mMinHeight
            if (r3 >= r4) goto L_0x00fb
            int r3 = r15.mMinHeight
        L_0x00fb:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r15.mListDimensionBehaviors
            r4 = r4[r8]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 == r5) goto L_0x0105
            r4 = 1
            goto L_0x0106
        L_0x0105:
            r4 = 0
        L_0x0106:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r15.mListDimensionBehaviors
            r5 = r5[r9]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r5 == r6) goto L_0x0110
            r5 = 1
            goto L_0x0111
        L_0x0110:
            r5 = 0
        L_0x0111:
            int r6 = r15.mDimensionRatioSide
            r15.mResolvedDimensionRatioSide = r6
            float r6 = r15.mDimensionRatio
            r15.mResolvedDimensionRatio = r6
            float r6 = r15.mDimensionRatio
            r17 = 0
            int r6 = (r6 > r17 ? 1 : (r6 == r17 ? 0 : -1))
            r9 = -1
            if (r6 <= 0) goto L_0x0178
            int r6 = r15.mVisibility
            if (r6 == r1) goto L_0x0178
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.mListDimensionBehaviors
            r1 = r1[r8]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r6) goto L_0x013b
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.mListDimensionBehaviors
            r6 = 1
            r1 = r1[r6]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r6) goto L_0x013b
            r15.setupDimensionRatio(r2, r7, r4, r5)
            goto L_0x0171
        L_0x013b:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.mListDimensionBehaviors
            r1 = r1[r8]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r4) goto L_0x014e
            r15.mResolvedDimensionRatioSide = r8
            float r0 = r15.mResolvedDimensionRatio
            int r1 = r15.mHeight
            float r1 = (float) r1
            float r0 = r0 * r1
            int r0 = (int) r0
            goto L_0x0171
        L_0x014e:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r15.mListDimensionBehaviors
            r4 = 1
            r1 = r1[r4]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r5) goto L_0x0171
            r15.mResolvedDimensionRatioSide = r4
            int r1 = r15.mDimensionRatioSide
            if (r1 != r9) goto L_0x0164
            r1 = 1065353216(0x3f800000, float:1.0)
            float r3 = r15.mResolvedDimensionRatio
            float r1 = r1 / r3
            r15.mResolvedDimensionRatio = r1
        L_0x0164:
            float r1 = r15.mResolvedDimensionRatio
            int r3 = r15.mWidth
            float r3 = (float) r3
            float r1 = r1 * r3
            int r1 = (int) r1
            r17 = r0
            r25 = r1
            goto L_0x0175
        L_0x0171:
            r17 = r0
            r25 = r3
        L_0x0175:
            r24 = 1
            goto L_0x017e
        L_0x0178:
            r17 = r0
            r25 = r3
            r24 = 0
        L_0x017e:
            if (r24 == 0) goto L_0x018b
            int r0 = r15.mResolvedDimensionRatioSide
            if (r0 == 0) goto L_0x0188
            int r0 = r15.mResolvedDimensionRatioSide
            if (r0 != r9) goto L_0x018b
        L_0x0188:
            r18 = 1
            goto L_0x018d
        L_0x018b:
            r18 = 0
        L_0x018d:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r15.mListDimensionBehaviors
            r0 = r0[r8]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r1) goto L_0x019b
            boolean r0 = r15 instanceof android.support.constraint.solver.widgets.ConstraintWidgetContainer
            if (r0 == 0) goto L_0x019b
            r6 = 1
            goto L_0x019c
        L_0x019b:
            r6 = 0
        L_0x019c:
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mCenter
            boolean r0 = r0.isConnected()
            r19 = 1
            r23 = r0 ^ 1
            int r0 = r15.mHorizontalResolution
            r5 = 2
            r26 = 0
            if (r0 == r5) goto L_0x022b
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x01bb
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mRight
            android.support.constraint.solver.SolverVariable r0 = r14.createObjectVariable(r0)
            r4 = r0
            goto L_0x01bd
        L_0x01bb:
            r4 = r26
        L_0x01bd:
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x01cb
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mLeft
            android.support.constraint.solver.SolverVariable r0 = r14.createObjectVariable(r0)
            r3 = r0
            goto L_0x01cd
        L_0x01cb:
            r3 = r26
        L_0x01cd:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r15.mListDimensionBehaviors
            r20 = r0[r8]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r15.mLeft
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mRight
            int r9 = r15.mX
            r28 = r11
            int r11 = r15.mMinWidth
            int[] r5 = r15.mMaxDimension
            r30 = r5[r8]
            float r5 = r15.mHorizontalBiasPercent
            r31 = r13
            int r13 = r15.mMatchConstraintDefaultWidth
            r32 = r13
            int r13 = r15.mMatchConstraintMinWidth
            r33 = r13
            int r13 = r15.mMatchConstraintMaxWidth
            r34 = r13
            float r13 = r15.mMatchConstraintPercentWidth
            r35 = r0
            r0 = r15
            r36 = r1
            r1 = r14
            r29 = r5
            r5 = r20
            r37 = r7
            r7 = r36
            r8 = r35
            r38 = r10
            r10 = r17
            r27 = r28
            r39 = r12
            r12 = r30
            r30 = r13
            r28 = r31
            r17 = r32
            r19 = r33
            r20 = r34
            r13 = r29
            r14 = r18
            r15 = r16
            r16 = r17
            r17 = r19
            r18 = r20
            r19 = r30
            r20 = r23
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            r15 = r41
            goto L_0x0235
        L_0x022b:
            r37 = r7
            r38 = r10
            r27 = r11
            r39 = r12
            r28 = r13
        L_0x0235:
            int r0 = r15.mVerticalResolution
            r1 = 2
            if (r0 != r1) goto L_0x023b
            return
        L_0x023b:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r15.mListDimensionBehaviors
            r14 = 1
            r0 = r0[r14]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r1) goto L_0x024a
            boolean r0 = r15 instanceof android.support.constraint.solver.widgets.ConstraintWidgetContainer
            if (r0 == 0) goto L_0x024a
            r6 = 1
            goto L_0x024b
        L_0x024a:
            r6 = 0
        L_0x024b:
            if (r24 == 0) goto L_0x0259
            int r0 = r15.mResolvedDimensionRatioSide
            if (r0 == r14) goto L_0x0256
            int r0 = r15.mResolvedDimensionRatioSide
            r1 = -1
            if (r0 != r1) goto L_0x0259
        L_0x0256:
            r16 = 1
            goto L_0x025b
        L_0x0259:
            r16 = 0
        L_0x025b:
            int r0 = r15.mBaselineDistance
            if (r0 <= 0) goto L_0x0282
            int r0 = r41.getBaselineDistance()
            r1 = 6
            r2 = r38
            r12 = r39
            r13 = r42
            r13.addEquality(r2, r12, r0, r1)
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mBaseline
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            if (r0 == 0) goto L_0x0286
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r15.mBaseline
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTarget
            android.support.constraint.solver.SolverVariable r0 = r13.createObjectVariable(r0)
            r3 = 0
            r13.addEquality(r2, r0, r3, r1)
            r20 = 0
            goto L_0x0288
        L_0x0282:
            r12 = r39
            r13 = r42
        L_0x0286:
            r20 = r23
        L_0x0288:
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x0296
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mBottom
            android.support.constraint.solver.SolverVariable r0 = r13.createObjectVariable(r0)
            r4 = r0
            goto L_0x0298
        L_0x0296:
            r4 = r26
        L_0x0298:
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x02a6
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r15.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.mTop
            android.support.constraint.solver.SolverVariable r0 = r13.createObjectVariable(r0)
            r3 = r0
            goto L_0x02a8
        L_0x02a6:
            r3 = r26
        L_0x02a8:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r15.mListDimensionBehaviors
            r5 = r0[r14]
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r15.mTop
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r15.mBottom
            int r9 = r15.mY
            int r11 = r15.mMinHeight
            int[] r0 = r15.mMaxDimension
            r17 = r0[r14]
            float r10 = r15.mVerticalBiasPercent
            int r2 = r15.mMatchConstraintDefaultHeight
            int r1 = r15.mMatchConstraintMinHeight
            int r0 = r15.mMatchConstraintMaxHeight
            float r14 = r15.mMatchConstraintPercentHeight
            r18 = r0
            r0 = r15
            r19 = r1
            r1 = r13
            r23 = r2
            r2 = r37
            r26 = r10
            r10 = r25
            r25 = r12
            r12 = r17
            r13 = r26
            r26 = r14
            r14 = r16
            r15 = r22
            r16 = r23
            r17 = r19
            r19 = r26
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            if (r24 == 0) goto L_0x0310
            r6 = 6
            r7 = r41
            int r0 = r7.mResolvedDimensionRatioSide
            r1 = 1
            if (r0 != r1) goto L_0x0300
            float r5 = r7.mResolvedDimensionRatio
            r6 = 6
            r0 = r42
            r1 = r27
            r2 = r25
            r3 = r28
            r4 = r21
            r0.addRatio(r1, r2, r3, r4, r5, r6)
            goto L_0x0312
        L_0x0300:
            float r5 = r7.mResolvedDimensionRatio
            r0 = r42
            r1 = r28
            r2 = r21
            r3 = r27
            r4 = r25
            r0.addRatio(r1, r2, r3, r4, r5, r6)
            goto L_0x0312
        L_0x0310:
            r7 = r41
        L_0x0312:
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r7.mCenter
            boolean r0 = r0.isConnected()
            if (r0 == 0) goto L_0x033a
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r7.mCenter
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r0.getTarget()
            android.support.constraint.solver.widgets.ConstraintWidget r0 = r0.getOwner()
            float r1 = r7.mCircleConstraintAngle
            r2 = 1119092736(0x42b40000, float:90.0)
            float r1 = r1 + r2
            double r1 = (double) r1
            double r1 = java.lang.Math.toRadians(r1)
            float r1 = (float) r1
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r7.mCenter
            int r2 = r2.getMargin()
            r3 = r42
            r3.addCenterPoint(r7, r0, r1, r2)
        L_0x033a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidget.addToSolver(android.support.constraint.solver.LinearSystem):void");
    }

    public void setupDimensionRatio(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.mMatchConstraintDefaultWidth == 0) {
            this.mMatchConstraintDefaultWidth = 3;
        }
        if (this.mMatchConstraintDefaultHeight == 0) {
            this.mMatchConstraintDefaultHeight = 3;
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z3 && !z4) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z3 && z4) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z && !z2) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z && z2) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide != -1) {
            return;
        }
        if (this.mMatchConstraintMinWidth > 0 && this.mMatchConstraintMinHeight == 0) {
            this.mResolvedDimensionRatioSide = 0;
        } else if (this.mMatchConstraintMinWidth != 0 || this.mMatchConstraintMinHeight <= 0) {
            this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
            this.mResolvedDimensionRatioSide = 1;
        } else {
            this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
            this.mResolvedDimensionRatioSide = 1;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0228, code lost:
        if (r9 == 1) goto L_0x022c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x02ce  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyConstraints(android.support.constraint.solver.LinearSystem r30, boolean r31, android.support.constraint.solver.SolverVariable r32, android.support.constraint.solver.SolverVariable r33, android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour r34, boolean r35, android.support.constraint.solver.widgets.ConstraintAnchor r36, android.support.constraint.solver.widgets.ConstraintAnchor r37, int r38, int r39, int r40, int r41, float r42, boolean r43, boolean r44, int r45, int r46, int r47, float r48, boolean r49) {
        /*
            r29 = this;
            r0 = r29
            r10 = r30
            r1 = r32
            r11 = r33
            r2 = r36
            r3 = r37
            r4 = r40
            r5 = r41
            int r8 = r2.resolutionStatus
            r9 = 1
            if (r8 != r9) goto L_0x0020
            int r8 = r3.resolutionStatus
            if (r8 != r9) goto L_0x0020
            r2.addResolvedValue(r10)
            r3.addResolvedValue(r10)
            return
        L_0x0020:
            android.support.constraint.solver.SolverVariable r8 = r10.createObjectVariable(r2)
            android.support.constraint.solver.SolverVariable r14 = r10.createObjectVariable(r3)
            android.support.constraint.solver.widgets.ConstraintAnchor r12 = r36.getTarget()
            android.support.constraint.solver.SolverVariable r13 = r10.createObjectVariable(r12)
            android.support.constraint.solver.widgets.ConstraintAnchor r12 = r37.getTarget()
            android.support.constraint.solver.SolverVariable r12 = r10.createObjectVariable(r12)
            boolean r18 = r36.isConnected()
            boolean r19 = r37.isConnected()
            android.support.constraint.solver.widgets.ConstraintAnchor r9 = r0.mCenter
            boolean r9 = r9.isConnected()
            if (r43 == 0) goto L_0x004b
            r20 = 3
            goto L_0x004d
        L_0x004b:
            r20 = r45
        L_0x004d:
            int[] r15 = android.support.constraint.solver.widgets.ConstraintWidget.AnonymousClass1.$SwitchMap$android$support$constraint$solver$widgets$ConstraintWidget$DimensionBehaviour
            int r16 = r34.ordinal()
            r15 = r15[r16]
            switch(r15) {
                case 1: goto L_0x0058;
                case 2: goto L_0x0058;
                case 3: goto L_0x0058;
                case 4: goto L_0x005a;
                default: goto L_0x0058;
            }
        L_0x0058:
            r15 = 0
            goto L_0x005b
        L_0x005a:
            r15 = 1
        L_0x005b:
            int r1 = r0.mVisibility
            r21 = r12
            r12 = 8
            if (r1 != r12) goto L_0x0066
            r1 = 0
            r12 = 0
            goto L_0x0069
        L_0x0066:
            r12 = r39
            r1 = r15
        L_0x0069:
            if (r1 != 0) goto L_0x0098
            if (r35 == 0) goto L_0x0085
            r22 = r1
            r1 = 3
            r3 = 0
            r10.addEquality(r14, r8, r3, r1)
            if (r4 <= 0) goto L_0x007b
            r3 = 6
            r10.addGreaterThan(r14, r8, r4, r3)
            goto L_0x007c
        L_0x007b:
            r3 = 6
        L_0x007c:
            r1 = 2147483647(0x7fffffff, float:NaN)
            if (r5 >= r1) goto L_0x008b
            r10.addLowerThan(r14, r8, r5, r3)
            goto L_0x008b
        L_0x0085:
            r22 = r1
            r3 = 6
            r10.addEquality(r14, r8, r12, r3)
        L_0x008b:
            r0 = r13
            r3 = r14
            r1 = r20
            r5 = r21
            r14 = r47
            r13 = r12
            r12 = r46
            goto L_0x0161
        L_0x0098:
            r22 = r1
            r1 = -2
            r3 = r46
            if (r3 != r1) goto L_0x00a3
            r3 = r47
            r5 = r12
            goto L_0x00a6
        L_0x00a3:
            r5 = r3
            r3 = r47
        L_0x00a6:
            if (r3 != r1) goto L_0x00a9
            r3 = r12
        L_0x00a9:
            if (r5 <= 0) goto L_0x00bb
            if (r31 == 0) goto L_0x00b2
            r1 = 6
            r10.addGreaterThan(r14, r8, r5, r1)
            goto L_0x00b6
        L_0x00b2:
            r1 = 6
            r10.addGreaterThan(r14, r8, r5, r1)
        L_0x00b6:
            int r12 = java.lang.Math.max(r12, r5)
            goto L_0x00bc
        L_0x00bb:
            r1 = 6
        L_0x00bc:
            if (r3 <= 0) goto L_0x00cd
            if (r31 == 0) goto L_0x00c5
            r1 = 1
            r10.addLowerThan(r14, r8, r3, r1)
            goto L_0x00c8
        L_0x00c5:
            r10.addLowerThan(r14, r8, r3, r1)
        L_0x00c8:
            int r1 = java.lang.Math.min(r12, r3)
            r12 = r1
        L_0x00cd:
            r23 = r3
            r1 = r20
            r3 = 1
            if (r1 != r3) goto L_0x00e4
            if (r31 != 0) goto L_0x00de
            if (r44 == 0) goto L_0x00d9
            goto L_0x00de
        L_0x00d9:
            r10.addEquality(r14, r8, r12, r3)
            goto L_0x0153
        L_0x00de:
            r3 = 5
            r10.addEquality(r14, r8, r12, r3)
            goto L_0x0153
        L_0x00e4:
            r3 = 2
            if (r1 != r3) goto L_0x0153
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r3 = r36.getType()
            r24 = r5
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r5 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP
            if (r3 == r5) goto L_0x0118
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r3 = r36.getType()
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r5 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM
            if (r3 != r5) goto L_0x00fa
            goto L_0x0118
        L_0x00fa:
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r0.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r5 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.getAnchor(r5)
            android.support.constraint.solver.SolverVariable r3 = r10.createObjectVariable(r3)
            android.support.constraint.solver.widgets.ConstraintWidget r5 = r0.mParent
            r25 = r3
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r5.getAnchor(r3)
            android.support.constraint.solver.SolverVariable r3 = r10.createObjectVariable(r3)
            r15 = r3
            r16 = r25
            goto L_0x0133
        L_0x0118:
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r0.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r5 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.getAnchor(r5)
            android.support.constraint.solver.SolverVariable r3 = r10.createObjectVariable(r3)
            android.support.constraint.solver.widgets.ConstraintWidget r5 = r0.mParent
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r0 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM
            android.support.constraint.solver.widgets.ConstraintAnchor r0 = r5.getAnchor(r0)
            android.support.constraint.solver.SolverVariable r0 = r10.createObjectVariable(r0)
            r15 = r0
            r16 = r3
        L_0x0133:
            android.support.constraint.solver.ArrayRow r0 = r30.createRow()
            r3 = r12
            r5 = r21
            r12 = r0
            r0 = r13
            r13 = r14
            r26 = r3
            r3 = r14
            r14 = r8
            r17 = r48
            android.support.constraint.solver.ArrayRow r12 = r12.createRowDimensionRatio(r13, r14, r15, r16, r17)
            r10.addConstraint(r12)
            r14 = r23
            r12 = r24
            r13 = r26
            r22 = 0
            goto L_0x0161
        L_0x0153:
            r24 = r5
            r26 = r12
            r0 = r13
            r3 = r14
            r5 = r21
            r14 = r23
            r12 = r24
            r13 = r26
        L_0x0161:
            if (r18 == 0) goto L_0x0165
            r15 = 1
            goto L_0x0166
        L_0x0165:
            r15 = 0
        L_0x0166:
            if (r19 == 0) goto L_0x016a
            int r15 = r15 + 1
        L_0x016a:
            if (r9 == 0) goto L_0x016e
            int r15 = r15 + 1
        L_0x016e:
            r27 = r1
            r1 = r15
            if (r22 == 0) goto L_0x0188
            r4 = 2
            if (r1 == r4) goto L_0x0188
            if (r43 != 0) goto L_0x0188
            int r13 = java.lang.Math.max(r12, r13)
            if (r14 <= 0) goto L_0x0182
            int r13 = java.lang.Math.min(r14, r13)
        L_0x0182:
            r4 = 6
            r10.addEquality(r3, r8, r13, r4)
            r22 = 0
        L_0x0188:
            if (r49 == 0) goto L_0x02d2
            if (r44 == 0) goto L_0x018e
            goto L_0x02d2
        L_0x018e:
            if (r18 != 0) goto L_0x01a5
            if (r19 != 0) goto L_0x01a5
            if (r9 != 0) goto L_0x01a5
            r1 = r38
            r10.addEquality(r8, r1)
            if (r31 == 0) goto L_0x01a0
            r1 = 0
            r4 = 5
            r10.addGreaterThan(r11, r3, r1, r4)
        L_0x01a0:
            r12 = r3
            r13 = 0
        L_0x01a2:
            r14 = 6
            goto L_0x02cc
        L_0x01a5:
            r1 = 0
            r4 = 5
            if (r18 == 0) goto L_0x01b9
            if (r19 != 0) goto L_0x01b9
            int r2 = r36.getMargin()
            r9 = 6
            r10.addEquality(r8, r0, r2, r9)
            if (r31 == 0) goto L_0x01a0
            r10.addGreaterThan(r11, r3, r1, r4)
            goto L_0x01a0
        L_0x01b9:
            r9 = 6
            if (r18 != 0) goto L_0x01ce
            if (r19 == 0) goto L_0x01ce
            int r0 = r37.getMargin()
            int r0 = -r0
            r10.addEquality(r3, r5, r0, r9)
            if (r31 == 0) goto L_0x01a0
            r0 = r32
            r10.addGreaterThan(r8, r0, r1, r4)
            goto L_0x01a0
        L_0x01ce:
            r4 = 3
            if (r18 == 0) goto L_0x01a0
            if (r19 == 0) goto L_0x01a0
            if (r44 != 0) goto L_0x01a0
            if (r22 == 0) goto L_0x0220
            if (r31 == 0) goto L_0x01e2
            r7 = r40
            if (r7 != 0) goto L_0x01e2
            r7 = 6
            r10.addGreaterThan(r3, r8, r1, r7)
            goto L_0x01e3
        L_0x01e2:
            r7 = 6
        L_0x01e3:
            int r9 = r36.getMargin()
            r10.addGreaterThan(r8, r0, r9, r7)
            int r9 = r37.getMargin()
            int r9 = -r9
            r10.addLowerThan(r3, r5, r9, r7)
            if (r27 != 0) goto L_0x0207
            int r6 = r36.getMargin()
            r9 = 4
            r10.addEquality(r8, r0, r6, r9)
            int r6 = r37.getMargin()
            int r6 = -r6
            r10.addEquality(r3, r5, r6, r9)
            r9 = r27
            goto L_0x0223
        L_0x0207:
            r9 = r27
            if (r9 != r4) goto L_0x0223
            if (r43 != 0) goto L_0x020f
            r6 = 5
            goto L_0x0210
        L_0x020f:
            r6 = 3
        L_0x0210:
            int r13 = r36.getMargin()
            r10.addEquality(r8, r0, r13, r6)
            int r13 = r37.getMargin()
            int r13 = -r13
            r10.addEquality(r3, r5, r13, r6)
            goto L_0x0223
        L_0x0220:
            r9 = r27
            r7 = 6
        L_0x0223:
            if (r22 == 0) goto L_0x0274
            if (r9 == 0) goto L_0x022b
            r6 = 1
            if (r9 != r6) goto L_0x0274
            goto L_0x022c
        L_0x022b:
            r6 = 1
        L_0x022c:
            if (r9 != r6) goto L_0x024a
            int r4 = r36.getMargin()
            int r9 = r37.getMargin()
            r12 = 6
            r13 = 0
            r1 = r10
            r2 = r8
            r14 = r3
            r8 = 6
            r3 = r0
            r6 = r5
            r5 = r42
            r7 = r14
            r0 = 6
            r8 = r9
            r9 = r12
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            r12 = r14
            goto L_0x01a2
        L_0x024a:
            r7 = r3
            r6 = r5
            r5 = 6
            r13 = 0
            if (r9 != 0) goto L_0x0270
            if (r14 > 0) goto L_0x0258
            if (r12 <= 0) goto L_0x0255
            goto L_0x0258
        L_0x0255:
            r12 = r7
            goto L_0x01a2
        L_0x0258:
            int r4 = r36.getMargin()
            int r9 = r37.getMargin()
            r12 = 5
            r1 = r10
            r2 = r8
            r3 = r0
            r14 = 6
            r5 = r42
            r0 = r7
            r8 = r9
            r9 = r12
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            r12 = r0
            goto L_0x02cc
        L_0x0270:
            r14 = 6
            r12 = r7
            goto L_0x02cc
        L_0x0274:
            r12 = r3
            r6 = r5
            r13 = 0
            r14 = 6
            if (r22 == 0) goto L_0x0291
            if (r9 != r4) goto L_0x0291
            int r4 = r36.getMargin()
            int r9 = r37.getMargin()
            r15 = 5
            r1 = r10
            r2 = r8
            r3 = r0
            r5 = r42
            r7 = r12
            r8 = r9
            r9 = r15
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x02cc
        L_0x0291:
            if (r31 == 0) goto L_0x02b8
            int r1 = r36.getMargin()
            r3 = 5
            r10.addGreaterThan(r8, r0, r1, r3)
            int r1 = r37.getMargin()
            int r1 = -r1
            r10.addLowerThan(r12, r6, r1, r3)
            int r4 = r36.getMargin()
            int r9 = r37.getMargin()
            r15 = 5
            r1 = r10
            r2 = r8
            r3 = r0
            r5 = r42
            r7 = r12
            r8 = r9
            r9 = r15
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x02cc
        L_0x02b8:
            int r4 = r36.getMargin()
            int r9 = r37.getMargin()
            r15 = 5
            r1 = r10
            r2 = r8
            r3 = r0
            r5 = r42
            r7 = r12
            r8 = r9
            r9 = r15
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
        L_0x02cc:
            if (r31 == 0) goto L_0x02d1
            r10.addGreaterThan(r11, r12, r13, r14)
        L_0x02d1:
            return
        L_0x02d2:
            r12 = r3
            r0 = r32
            r2 = 2
            r13 = 0
            r14 = 6
            if (r1 >= r2) goto L_0x02e2
            if (r31 == 0) goto L_0x02e2
            r10.addGreaterThan(r8, r0, r13, r14)
            r10.addGreaterThan(r11, r12, r13, r14)
        L_0x02e2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidget.applyConstraints(android.support.constraint.solver.LinearSystem, boolean, android.support.constraint.solver.SolverVariable, android.support.constraint.solver.SolverVariable, android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour, boolean, android.support.constraint.solver.widgets.ConstraintAnchor, android.support.constraint.solver.widgets.ConstraintAnchor, int, int, int, int, float, boolean, boolean, int, int, int, float, boolean):void");
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        setFrame(linearSystem.getObjectVariableValue(this.mLeft), linearSystem.getObjectVariableValue(this.mTop), linearSystem.getObjectVariableValue(this.mRight), linearSystem.getObjectVariableValue(this.mBottom));
    }
}
