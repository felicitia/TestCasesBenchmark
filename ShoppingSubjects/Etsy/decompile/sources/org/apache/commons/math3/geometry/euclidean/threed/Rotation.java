package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import java.lang.reflect.Array;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class Rotation implements Serializable {
    public static final Rotation IDENTITY;
    private static final long serialVersionUID = -2153622329907944313L;
    private final double q0;
    private final double q1;
    private final double q2;
    private final double q3;

    static {
        Rotation rotation = new Rotation(1.0d, 0.0d, 0.0d, 0.0d, false);
        IDENTITY = rotation;
    }

    public Rotation(double d, double d2, double d3, double d4, boolean z) {
        if (z) {
            double sqrt = 1.0d / FastMath.sqrt((((d * d) + (d2 * d2)) + (d3 * d3)) + (d4 * d4));
            d *= sqrt;
            d2 *= sqrt;
            d3 *= sqrt;
            d4 *= sqrt;
        }
        this.q0 = d;
        this.q1 = d2;
        this.q2 = d3;
        this.q3 = d4;
    }

    public Rotation(Vector3D vector3D, double d) throws MathIllegalArgumentException {
        double norm = vector3D.getNorm();
        if (norm == 0.0d) {
            throw new MathIllegalArgumentException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_AXIS, new Object[0]);
        }
        double d2 = -0.5d * d;
        double sin = FastMath.sin(d2) / norm;
        this.q0 = FastMath.cos(d2);
        this.q1 = vector3D.getX() * sin;
        this.q2 = vector3D.getY() * sin;
        this.q3 = sin * vector3D.getZ();
    }

    public Rotation(double[][] dArr, double d) throws NotARotationMatrixException {
        double[][] dArr2 = dArr;
        if (dArr2.length == 3 && dArr2[0].length == 3 && dArr2[1].length == 3 && dArr2[2].length == 3) {
            double[][] orthogonalizeMatrix = orthogonalizeMatrix(dArr, d);
            double d2 = ((orthogonalizeMatrix[0][0] * ((orthogonalizeMatrix[1][1] * orthogonalizeMatrix[2][2]) - (orthogonalizeMatrix[2][1] * orthogonalizeMatrix[1][2]))) - (orthogonalizeMatrix[1][0] * ((orthogonalizeMatrix[0][1] * orthogonalizeMatrix[2][2]) - (orthogonalizeMatrix[2][1] * orthogonalizeMatrix[0][2])))) + (orthogonalizeMatrix[2][0] * ((orthogonalizeMatrix[0][1] * orthogonalizeMatrix[1][2]) - (orthogonalizeMatrix[1][1] * orthogonalizeMatrix[0][2])));
            if (d2 < 0.0d) {
                throw new NotARotationMatrixException(LocalizedFormats.CLOSEST_ORTHOGONAL_MATRIX_HAS_NEGATIVE_DETERMINANT, Double.valueOf(d2));
            }
            double[] mat2quat = mat2quat(orthogonalizeMatrix);
            this.q0 = mat2quat[0];
            this.q1 = mat2quat[1];
            this.q2 = mat2quat[2];
            this.q3 = mat2quat[3];
            return;
        }
        throw new NotARotationMatrixException(LocalizedFormats.ROTATION_MATRIX_DIMENSIONS, Integer.valueOf(dArr2.length), Integer.valueOf(dArr2[0].length));
    }

    public Rotation(Vector3D vector3D, Vector3D vector3D2, Vector3D vector3D3, Vector3D vector3D4) throws MathArithmeticException {
        Vector3D normalize = vector3D.crossProduct(vector3D2).normalize();
        Vector3D normalize2 = normalize.crossProduct(vector3D).normalize();
        Vector3D normalize3 = vector3D.normalize();
        Vector3D normalize4 = vector3D3.crossProduct(vector3D4).normalize();
        Vector3D normalize5 = normalize4.crossProduct(vector3D3).normalize();
        Vector3D normalize6 = vector3D3.normalize();
        double[] mat2quat = mat2quat(new double[][]{new double[]{MathArrays.linearCombination(normalize3.getX(), normalize6.getX(), normalize2.getX(), normalize5.getX(), normalize.getX(), normalize4.getX()), MathArrays.linearCombination(normalize3.getY(), normalize6.getX(), normalize2.getY(), normalize5.getX(), normalize.getY(), normalize4.getX()), MathArrays.linearCombination(normalize3.getZ(), normalize6.getX(), normalize2.getZ(), normalize5.getX(), normalize.getZ(), normalize4.getX())}, new double[]{MathArrays.linearCombination(normalize3.getX(), normalize6.getY(), normalize2.getX(), normalize5.getY(), normalize.getX(), normalize4.getY()), MathArrays.linearCombination(normalize3.getY(), normalize6.getY(), normalize2.getY(), normalize5.getY(), normalize.getY(), normalize4.getY()), MathArrays.linearCombination(normalize3.getZ(), normalize6.getY(), normalize2.getZ(), normalize5.getY(), normalize.getZ(), normalize4.getY())}, new double[]{MathArrays.linearCombination(normalize3.getX(), normalize6.getZ(), normalize2.getX(), normalize5.getZ(), normalize.getX(), normalize4.getZ()), MathArrays.linearCombination(normalize3.getY(), normalize6.getZ(), normalize2.getY(), normalize5.getZ(), normalize.getY(), normalize4.getZ()), MathArrays.linearCombination(normalize3.getZ(), normalize6.getZ(), normalize2.getZ(), normalize5.getZ(), normalize.getZ(), normalize4.getZ())}});
        this.q0 = mat2quat[0];
        this.q1 = mat2quat[1];
        this.q2 = mat2quat[2];
        this.q3 = mat2quat[3];
    }

    public Rotation(Vector3D vector3D, Vector3D vector3D2) throws MathArithmeticException {
        double norm = vector3D.getNorm() * vector3D2.getNorm();
        if (norm == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_DEFINING_VECTOR, new Object[0]);
        }
        double dotProduct = vector3D.dotProduct(vector3D2);
        if (dotProduct < -0.999999999999998d * norm) {
            Vector3D orthogonal = vector3D.orthogonal();
            this.q0 = 0.0d;
            this.q1 = -orthogonal.getX();
            this.q2 = -orthogonal.getY();
            this.q3 = -orthogonal.getZ();
            return;
        }
        this.q0 = FastMath.sqrt(0.5d * ((dotProduct / norm) + 1.0d));
        double d = 1.0d / ((2.0d * this.q0) * norm);
        Vector3D crossProduct = vector3D2.crossProduct(vector3D);
        this.q1 = crossProduct.getX() * d;
        this.q2 = crossProduct.getY() * d;
        this.q3 = d * crossProduct.getZ();
    }

    public Rotation(RotationOrder rotationOrder, double d, double d2, double d3) {
        Rotation applyTo = new Rotation(rotationOrder.getA1(), d).applyTo(new Rotation(rotationOrder.getA2(), d2).applyTo(new Rotation(rotationOrder.getA3(), d3)));
        this.q0 = applyTo.q0;
        this.q1 = applyTo.q1;
        this.q2 = applyTo.q2;
        this.q3 = applyTo.q3;
    }

    private static double[] mat2quat(double[][] dArr) {
        double[] dArr2 = new double[4];
        double d = dArr[0][0] + dArr[1][1] + dArr[2][2];
        if (d > -0.19d) {
            dArr2[0] = 0.5d * FastMath.sqrt(d + 1.0d);
            double d2 = 0.25d / dArr2[0];
            dArr2[1] = (dArr[1][2] - dArr[2][1]) * d2;
            dArr2[2] = (dArr[2][0] - dArr[0][2]) * d2;
            dArr2[3] = d2 * (dArr[0][1] - dArr[1][0]);
        } else {
            double d3 = (dArr[0][0] - dArr[1][1]) - dArr[2][2];
            if (d3 > -0.19d) {
                dArr2[1] = 0.5d * FastMath.sqrt(d3 + 1.0d);
                double d4 = 0.25d / dArr2[1];
                dArr2[0] = (dArr[1][2] - dArr[2][1]) * d4;
                dArr2[2] = (dArr[0][1] + dArr[1][0]) * d4;
                dArr2[3] = d4 * (dArr[0][2] + dArr[2][0]);
            } else {
                double d5 = (dArr[1][1] - dArr[0][0]) - dArr[2][2];
                if (d5 > -0.19d) {
                    dArr2[2] = 0.5d * FastMath.sqrt(d5 + 1.0d);
                    double d6 = 0.25d / dArr2[2];
                    dArr2[0] = (dArr[2][0] - dArr[0][2]) * d6;
                    dArr2[1] = (dArr[0][1] + dArr[1][0]) * d6;
                    dArr2[3] = d6 * (dArr[2][1] + dArr[1][2]);
                } else {
                    dArr2[3] = 0.5d * FastMath.sqrt(((dArr[2][2] - dArr[0][0]) - dArr[1][1]) + 1.0d);
                    double d7 = 0.25d / dArr2[3];
                    dArr2[0] = (dArr[0][1] - dArr[1][0]) * d7;
                    dArr2[1] = (dArr[0][2] + dArr[2][0]) * d7;
                    dArr2[2] = d7 * (dArr[2][1] + dArr[1][2]);
                }
            }
        }
        return dArr2;
    }

    public Rotation revert() {
        Rotation rotation = new Rotation(-this.q0, this.q1, this.q2, this.q3, false);
        return rotation;
    }

    public double getQ0() {
        return this.q0;
    }

    public double getQ1() {
        return this.q1;
    }

    public double getQ2() {
        return this.q2;
    }

    public double getQ3() {
        return this.q3;
    }

    public Vector3D getAxis() {
        double d = (this.q1 * this.q1) + (this.q2 * this.q2) + (this.q3 * this.q3);
        if (d == 0.0d) {
            Vector3D vector3D = new Vector3D(1.0d, 0.0d, 0.0d);
            return vector3D;
        } else if (this.q0 < 0.0d) {
            double sqrt = 1.0d / FastMath.sqrt(d);
            Vector3D vector3D2 = new Vector3D(this.q1 * sqrt, this.q2 * sqrt, this.q3 * sqrt);
            return vector3D2;
        } else {
            double sqrt2 = -1.0d / FastMath.sqrt(d);
            Vector3D vector3D3 = new Vector3D(this.q1 * sqrt2, this.q2 * sqrt2, this.q3 * sqrt2);
            return vector3D3;
        }
    }

    public double getAngle() {
        if (this.q0 < -0.1d || this.q0 > 0.1d) {
            return 2.0d * FastMath.asin(FastMath.sqrt((this.q1 * this.q1) + (this.q2 * this.q2) + (this.q3 * this.q3)));
        }
        if (this.q0 < 0.0d) {
            return 2.0d * FastMath.acos(-this.q0);
        }
        return 2.0d * FastMath.acos(this.q0);
    }

    public double[] getAngles(RotationOrder rotationOrder) throws CardanEulerSingularityException {
        if (rotationOrder == RotationOrder.XYZ) {
            Vector3D applyTo = applyTo(Vector3D.PLUS_K);
            Vector3D applyInverseTo = applyInverseTo(Vector3D.PLUS_I);
            if (applyInverseTo.getZ() < -0.9999999999d || applyInverseTo.getZ() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(-applyTo.getY(), applyTo.getZ()), FastMath.asin(applyInverseTo.getZ()), FastMath.atan2(-applyInverseTo.getY(), applyInverseTo.getX())};
        } else if (rotationOrder == RotationOrder.XZY) {
            Vector3D applyTo2 = applyTo(Vector3D.PLUS_J);
            Vector3D applyInverseTo2 = applyInverseTo(Vector3D.PLUS_I);
            if (applyInverseTo2.getY() < -0.9999999999d || applyInverseTo2.getY() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(applyTo2.getZ(), applyTo2.getY()), -FastMath.asin(applyInverseTo2.getY()), FastMath.atan2(applyInverseTo2.getZ(), applyInverseTo2.getX())};
        } else if (rotationOrder == RotationOrder.YXZ) {
            Vector3D applyTo3 = applyTo(Vector3D.PLUS_K);
            Vector3D applyInverseTo3 = applyInverseTo(Vector3D.PLUS_J);
            if (applyInverseTo3.getZ() < -0.9999999999d || applyInverseTo3.getZ() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(applyTo3.getX(), applyTo3.getZ()), -FastMath.asin(applyInverseTo3.getZ()), FastMath.atan2(applyInverseTo3.getX(), applyInverseTo3.getY())};
        } else if (rotationOrder == RotationOrder.YZX) {
            Vector3D applyTo4 = applyTo(Vector3D.PLUS_I);
            Vector3D applyInverseTo4 = applyInverseTo(Vector3D.PLUS_J);
            if (applyInverseTo4.getX() < -0.9999999999d || applyInverseTo4.getX() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(-applyTo4.getZ(), applyTo4.getX()), FastMath.asin(applyInverseTo4.getX()), FastMath.atan2(-applyInverseTo4.getZ(), applyInverseTo4.getY())};
        } else if (rotationOrder == RotationOrder.ZXY) {
            Vector3D applyTo5 = applyTo(Vector3D.PLUS_J);
            Vector3D applyInverseTo5 = applyInverseTo(Vector3D.PLUS_K);
            if (applyInverseTo5.getY() < -0.9999999999d || applyInverseTo5.getY() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(-applyTo5.getX(), applyTo5.getY()), FastMath.asin(applyInverseTo5.getY()), FastMath.atan2(-applyInverseTo5.getX(), applyInverseTo5.getZ())};
        } else if (rotationOrder == RotationOrder.ZYX) {
            Vector3D applyTo6 = applyTo(Vector3D.PLUS_I);
            Vector3D applyInverseTo6 = applyInverseTo(Vector3D.PLUS_K);
            if (applyInverseTo6.getX() < -0.9999999999d || applyInverseTo6.getX() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(applyTo6.getY(), applyTo6.getX()), -FastMath.asin(applyInverseTo6.getX()), FastMath.atan2(applyInverseTo6.getY(), applyInverseTo6.getZ())};
        } else if (rotationOrder == RotationOrder.XYX) {
            Vector3D applyTo7 = applyTo(Vector3D.PLUS_I);
            Vector3D applyInverseTo7 = applyInverseTo(Vector3D.PLUS_I);
            if (applyInverseTo7.getX() < -0.9999999999d || applyInverseTo7.getX() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(applyTo7.getY(), -applyTo7.getZ()), FastMath.acos(applyInverseTo7.getX()), FastMath.atan2(applyInverseTo7.getY(), applyInverseTo7.getZ())};
        } else if (rotationOrder == RotationOrder.XZX) {
            Vector3D applyTo8 = applyTo(Vector3D.PLUS_I);
            Vector3D applyInverseTo8 = applyInverseTo(Vector3D.PLUS_I);
            if (applyInverseTo8.getX() < -0.9999999999d || applyInverseTo8.getX() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(applyTo8.getZ(), applyTo8.getY()), FastMath.acos(applyInverseTo8.getX()), FastMath.atan2(applyInverseTo8.getZ(), -applyInverseTo8.getY())};
        } else if (rotationOrder == RotationOrder.YXY) {
            Vector3D applyTo9 = applyTo(Vector3D.PLUS_J);
            Vector3D applyInverseTo9 = applyInverseTo(Vector3D.PLUS_J);
            if (applyInverseTo9.getY() < -0.9999999999d || applyInverseTo9.getY() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(applyTo9.getX(), applyTo9.getZ()), FastMath.acos(applyInverseTo9.getY()), FastMath.atan2(applyInverseTo9.getX(), -applyInverseTo9.getZ())};
        } else if (rotationOrder == RotationOrder.YZY) {
            Vector3D applyTo10 = applyTo(Vector3D.PLUS_J);
            Vector3D applyInverseTo10 = applyInverseTo(Vector3D.PLUS_J);
            if (applyInverseTo10.getY() < -0.9999999999d || applyInverseTo10.getY() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(applyTo10.getZ(), -applyTo10.getX()), FastMath.acos(applyInverseTo10.getY()), FastMath.atan2(applyInverseTo10.getZ(), applyInverseTo10.getX())};
        } else if (rotationOrder == RotationOrder.ZXZ) {
            Vector3D applyTo11 = applyTo(Vector3D.PLUS_K);
            Vector3D applyInverseTo11 = applyInverseTo(Vector3D.PLUS_K);
            if (applyInverseTo11.getZ() < -0.9999999999d || applyInverseTo11.getZ() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(applyTo11.getX(), -applyTo11.getY()), FastMath.acos(applyInverseTo11.getZ()), FastMath.atan2(applyInverseTo11.getX(), applyInverseTo11.getY())};
        } else {
            Vector3D applyTo12 = applyTo(Vector3D.PLUS_K);
            Vector3D applyInverseTo12 = applyInverseTo(Vector3D.PLUS_K);
            if (applyInverseTo12.getZ() < -0.9999999999d || applyInverseTo12.getZ() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(applyTo12.getY(), applyTo12.getX()), FastMath.acos(applyInverseTo12.getZ()), FastMath.atan2(applyInverseTo12.getY(), -applyInverseTo12.getX())};
        }
    }

    public double[][] getMatrix() {
        double d = this.q0 * this.q0;
        double d2 = this.q0 * this.q3;
        double d3 = this.q1 * this.q2;
        double d4 = this.q0 * this.q1;
        double d5 = this.q1 * this.q3;
        double d6 = this.q0 * this.q2;
        double d7 = this.q2 * this.q2;
        double d8 = this.q2 * this.q3;
        double d9 = this.q3 * this.q3;
        double[][] dArr = {new double[3], new double[3], new double[3]};
        dArr[0][0] = (((this.q1 * this.q1) + d) * 2.0d) - 1.0d;
        dArr[1][0] = (d3 - d2) * 2.0d;
        dArr[2][0] = (d5 + d6) * 2.0d;
        dArr[0][1] = (d3 + d2) * 2.0d;
        dArr[1][1] = ((d + d7) * 2.0d) - 1.0d;
        dArr[2][1] = (d8 - d4) * 2.0d;
        dArr[0][2] = (d5 - d6) * 2.0d;
        dArr[1][2] = (d8 + d4) * 2.0d;
        dArr[2][2] = (2.0d * (d + d9)) - 1.0d;
        return dArr;
    }

    public Vector3D applyTo(Vector3D vector3D) {
        double x = vector3D.getX();
        double y = vector3D.getY();
        double z = vector3D.getZ();
        double d = (this.q1 * x) + (this.q2 * y) + (this.q3 * z);
        double d2 = z;
        Vector3D vector3D2 = new Vector3D((((this.q0 * ((this.q0 * x) - ((this.q2 * z) - (this.q3 * y)))) + (this.q1 * d)) * 2.0d) - x, (2.0d * ((this.q0 * ((this.q0 * y) - ((this.q3 * x) - (this.q1 * d2)))) + (this.q2 * d))) - y, (((this.q0 * ((this.q0 * d2) - ((this.q1 * y) - (this.q2 * x)))) + (d * this.q3)) * 2.0d) - d2);
        return vector3D2;
    }

    public void applyTo(double[] dArr, double[] dArr2) {
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = dArr[2];
        double d4 = (this.q1 * d) + (this.q2 * d2) + (this.q3 * d3);
        double d5 = d2;
        double d6 = d;
        double d7 = d3;
        dArr2[0] = (((this.q0 * ((this.q0 * d) - ((this.q2 * d3) - (this.q3 * d5)))) + (this.q1 * d4)) * 2.0d) - d6;
        dArr2[1] = (((this.q0 * ((this.q0 * d5) - ((this.q3 * d6) - (this.q1 * d7)))) + (this.q2 * d4)) * 2.0d) - d5;
        dArr2[2] = (2.0d * ((this.q0 * ((d7 * this.q0) - ((this.q1 * d5) - (this.q2 * d6)))) + (d4 * this.q3))) - d7;
    }

    public Vector3D applyInverseTo(Vector3D vector3D) {
        double x = vector3D.getX();
        double y = vector3D.getY();
        double z = vector3D.getZ();
        double d = (this.q1 * x) + (this.q2 * y) + (this.q3 * z);
        double d2 = -this.q0;
        double d3 = z;
        Vector3D vector3D2 = new Vector3D((((((x * d2) - ((this.q2 * z) - (this.q3 * y))) * d2) + (this.q1 * d)) * 2.0d) - x, (2.0d * ((((y * d2) - ((this.q3 * x) - (this.q1 * d3))) * d2) + (this.q2 * d))) - y, (2.0d * ((d2 * ((d3 * d2) - ((this.q1 * y) - (this.q2 * x)))) + (d * this.q3))) - d3);
        return vector3D2;
    }

    public void applyInverseTo(double[] dArr, double[] dArr2) {
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = dArr[2];
        double d4 = (this.q1 * d) + (this.q2 * d2) + (this.q3 * d3);
        double d5 = -this.q0;
        double d6 = d;
        double d7 = d3;
        dArr2[0] = (((((d * d5) - ((this.q2 * d3) - (this.q3 * d2))) * d5) + (this.q1 * d4)) * 2.0d) - d6;
        double d8 = d2;
        dArr2[1] = (2.0d * ((((d2 * d5) - ((this.q3 * d6) - (this.q1 * d7))) * d5) + (this.q2 * d4))) - d8;
        dArr2[2] = (2.0d * ((d5 * ((d7 * d5) - ((this.q1 * d8) - (this.q2 * d6)))) + (d4 * this.q3))) - d7;
    }

    public Rotation applyTo(Rotation rotation) {
        Rotation rotation2 = rotation;
        Rotation rotation3 = new Rotation((rotation2.q0 * this.q0) - (((rotation2.q1 * this.q1) + (rotation2.q2 * this.q2)) + (rotation2.q3 * this.q3)), (rotation2.q1 * this.q0) + (rotation2.q0 * this.q1) + ((rotation2.q2 * this.q3) - (rotation2.q3 * this.q2)), (rotation2.q2 * this.q0) + (rotation2.q0 * this.q2) + ((rotation2.q3 * this.q1) - (rotation2.q1 * this.q3)), (rotation2.q3 * this.q0) + (rotation2.q0 * this.q3) + ((rotation2.q1 * this.q2) - (rotation2.q2 * this.q1)), false);
        return rotation3;
    }

    public Rotation applyInverseTo(Rotation rotation) {
        Rotation rotation2 = rotation;
        Rotation rotation3 = new Rotation(((-rotation2.q0) * this.q0) - (((rotation2.q1 * this.q1) + (rotation2.q2 * this.q2)) + (rotation2.q3 * this.q3)), ((-rotation2.q1) * this.q0) + (rotation2.q0 * this.q1) + ((rotation2.q2 * this.q3) - (rotation2.q3 * this.q2)), ((-rotation2.q2) * this.q0) + (rotation2.q0 * this.q2) + ((rotation2.q3 * this.q1) - (rotation2.q1 * this.q3)), ((-rotation2.q3) * this.q0) + (rotation2.q0 * this.q3) + ((rotation2.q1 * this.q2) - (rotation2.q2 * this.q1)), false);
        return rotation3;
    }

    private double[][] orthogonalizeMatrix(double[][] dArr, double d) throws NotARotationMatrixException {
        double[] dArr2 = dArr[0];
        double[] dArr3 = dArr[1];
        double[] dArr4 = dArr[2];
        double d2 = dArr2[0];
        double d3 = dArr2[1];
        double d4 = dArr2[2];
        double d5 = dArr3[0];
        double d6 = dArr3[1];
        double d7 = dArr3[2];
        double d8 = dArr4[0];
        double d9 = dArr4[1];
        double d10 = dArr4[2];
        double[][] dArr5 = (double[][]) Array.newInstance(double.class, new int[]{3, 3});
        double[] dArr6 = dArr5[0];
        int i = 1;
        double[] dArr7 = dArr5[1];
        double[] dArr8 = dArr5[2];
        double d11 = 0.0d;
        double d12 = d10;
        double d13 = d7;
        double d14 = d4;
        double d15 = d3;
        double d16 = d2;
        int i2 = 0;
        while (true) {
            i2++;
            if (i2 < 11) {
                double d17 = (dArr2[0] * d16) + (dArr3[0] * d5) + (dArr4[0] * d8);
                double d18 = (dArr2[i] * d16) + (dArr3[i] * d5) + (dArr4[i] * d8);
                double d19 = (dArr2[2] * d16) + (dArr3[2] * d5) + (dArr4[2] * d8);
                double d20 = (dArr2[0] * d15) + (dArr3[0] * d6) + (dArr4[0] * d9);
                double d21 = (dArr2[1] * d15) + (dArr3[1] * d6) + (dArr4[1] * d9);
                double d22 = (dArr2[2] * d15) + (dArr3[2] * d6) + (dArr4[2] * d9);
                double d23 = (dArr2[0] * d14) + (dArr3[0] * d13) + (dArr4[0] * d12);
                double d24 = (dArr2[1] * d14) + (dArr3[1] * d13) + (dArr4[1] * d12);
                double d25 = (dArr2[2] * d14) + (dArr3[2] * d13) + (dArr4[2] * d12);
                dArr6[0] = d16 - (((((d16 * d17) + (d15 * d18)) + (d14 * d19)) - dArr2[0]) * 0.5d);
                dArr6[1] = d15 - (((((d16 * d20) + (d15 * d21)) + (d14 * d22)) - dArr2[1]) * 0.5d);
                dArr6[2] = d14 - (((((d16 * d23) + (d15 * d24)) + (d14 * d25)) - dArr2[2]) * 0.5d);
                dArr7[0] = d5 - (((((d5 * d17) + (d6 * d18)) + (d13 * d19)) - dArr3[0]) * 0.5d);
                dArr7[1] = d6 - (((((d5 * d20) + (d6 * d21)) + (d13 * d22)) - dArr3[1]) * 0.5d);
                dArr7[2] = d13 - (((((d5 * d23) + (d6 * d24)) + (d13 * d25)) - dArr3[2]) * 0.5d);
                dArr8[0] = d8 - (((((d17 * d8) + (d18 * d9)) + (d19 * d12)) - dArr4[0]) * 0.5d);
                dArr8[1] = d9 - (((((d20 * d8) + (d21 * d9)) + (d22 * d12)) - dArr4[1]) * 0.5d);
                dArr8[2] = d12 - (0.5d * ((((d8 * d23) + (d9 * d24)) + (d25 * d12)) - dArr4[2]));
                double d26 = dArr6[0] - dArr2[0];
                double d27 = dArr6[1] - dArr2[1];
                double d28 = dArr6[2] - dArr2[2];
                double d29 = dArr7[0] - dArr3[0];
                double d30 = dArr7[1] - dArr3[1];
                double d31 = dArr7[2] - dArr3[2];
                double d32 = dArr8[0] - dArr4[0];
                double d33 = dArr8[1] - dArr4[1];
                double d34 = dArr8[2] - dArr4[2];
                double d35 = (d26 * d26) + (d27 * d27) + (d28 * d28) + (d29 * d29) + (d30 * d30) + (d31 * d31) + (d32 * d32) + (d33 * d33) + (d34 * d34);
                if (FastMath.abs(d35 - d11) <= d) {
                    return dArr5;
                }
                double d36 = dArr6[0];
                double d37 = dArr6[1];
                double d38 = dArr6[2];
                double d39 = dArr7[0];
                double d40 = dArr7[1];
                double d41 = dArr7[2];
                double d42 = dArr8[0];
                double d43 = dArr8[1];
                i = 1;
                d14 = d38;
                d5 = d39;
                d8 = d42;
                d12 = dArr8[2];
                d9 = d43;
                d11 = d35;
                d16 = d36;
                d15 = d37;
                d6 = d40;
                d13 = d41;
            } else {
                int i3 = i;
                LocalizedFormats localizedFormats = LocalizedFormats.UNABLE_TO_ORTHOGONOLIZE_MATRIX;
                Object[] objArr = new Object[i3];
                objArr[0] = Integer.valueOf(i2 - i3);
                throw new NotARotationMatrixException(localizedFormats, objArr);
            }
        }
    }

    public static double distance(Rotation rotation, Rotation rotation2) {
        return rotation.applyInverseTo(rotation2).getAngle();
    }
}
