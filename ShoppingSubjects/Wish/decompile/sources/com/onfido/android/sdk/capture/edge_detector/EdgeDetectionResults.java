package com.onfido.android.sdk.capture.edge_detector;

public final class EdgeDetectionResults {
    private final boolean a;
    private final boolean b;
    private final boolean c;
    private final boolean d;

    public EdgeDetectionResults() {
        this(false, false, false, false, 15, null);
    }

    public EdgeDetectionResults(boolean z, boolean z2, boolean z3, boolean z4) {
        this.a = z;
        this.b = z2;
        this.c = z3;
        this.d = z4;
    }

    public /* synthetic */ EdgeDetectionResults(boolean z, boolean z2, boolean z3, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            z3 = false;
        }
        if ((i & 8) != 0) {
            z4 = false;
        }
        this(z, z2, z3, z4);
    }

    private final int a(boolean z) {
        return z ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EdgeDetectionResults)) {
            return false;
        }
        EdgeDetectionResults edgeDetectionResults = (EdgeDetectionResults) obj;
        return this.a == edgeDetectionResults.a && this.b == edgeDetectionResults.b && this.c == edgeDetectionResults.c && this.d == edgeDetectionResults.d;
    }

    public final boolean getBottomEdgeDetected() {
        return this.c;
    }

    public final boolean getHasAny() {
        return this.a || this.b || this.c || this.d;
    }

    public final boolean getHasMost() {
        return ((a(this.a) + a(this.b)) + a(this.c)) + a(this.d) >= 3;
    }

    public final boolean getLeftEdgeDetected() {
        return this.b;
    }

    public final boolean getRightEdgeDetected() {
        return this.d;
    }

    public final boolean getTopEdgeDetected() {
        return this.a;
    }

    public int hashCode() {
        return (((((Boolean.valueOf(this.a).hashCode() * 31) + Boolean.valueOf(this.b).hashCode()) * 31) + Boolean.valueOf(this.c).hashCode()) * 31) + Boolean.valueOf(this.d).hashCode();
    }
}
