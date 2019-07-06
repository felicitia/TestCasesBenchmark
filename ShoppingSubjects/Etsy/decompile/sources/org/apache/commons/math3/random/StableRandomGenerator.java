package org.apache.commons.math3.random;

import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

public class StableRandomGenerator implements NormalizedRandomGenerator {
    private final double alpha;
    private final double beta;
    private final RandomGenerator generator;
    private final double zeta;

    public StableRandomGenerator(RandomGenerator randomGenerator, double d, double d2) throws NullArgumentException, OutOfRangeException {
        if (randomGenerator == null) {
            throw new NullArgumentException();
        } else if (d <= 0.0d || d > 2.0d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_LEFT, Double.valueOf(d), Integer.valueOf(0), Integer.valueOf(2));
        } else if (d2 < -1.0d || d2 > 1.0d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_SIMPLE, Double.valueOf(d2), Integer.valueOf(-1), Integer.valueOf(1));
        } else {
            this.generator = randomGenerator;
            this.alpha = d;
            this.beta = d2;
            if (d >= 2.0d || d2 == 0.0d) {
                this.zeta = 0.0d;
            } else {
                this.zeta = d2 * FastMath.tan((3.141592653589793d * d) / 2.0d);
            }
        }
    }

    public double nextNormalizedDouble() {
        double d;
        double d2 = -FastMath.log(this.generator.nextDouble());
        double nextDouble = (this.generator.nextDouble() - 0.5d) * 3.141592653589793d;
        if (this.alpha == 2.0d) {
            return FastMath.sqrt(2.0d * d2) * FastMath.sin(nextDouble);
        }
        if (this.beta != 0.0d) {
            double cos = FastMath.cos(nextDouble);
            if (FastMath.abs(this.alpha - 1.0d) > 1.0E-8d) {
                double d3 = this.alpha * nextDouble;
                double d4 = nextDouble - d3;
                d = (((FastMath.sin(d3) + (this.zeta * FastMath.cos(d3))) / cos) * (FastMath.cos(d4) + (this.zeta * FastMath.sin(d4)))) / FastMath.pow(d2 * cos, (1.0d - this.alpha) / this.alpha);
            } else {
                double d5 = (this.beta * nextDouble) + 1.5707963267948966d;
                d = 0.6366197723675814d * ((FastMath.tan(nextDouble) * d5) - (this.beta * FastMath.log(((1.5707963267948966d * d2) * cos) / d5)));
                if (this.alpha != 1.0d) {
                    d += this.beta * FastMath.tan((3.141592653589793d * this.alpha) / 2.0d);
                }
            }
        } else if (this.alpha == 1.0d) {
            d = FastMath.tan(nextDouble);
        } else {
            d = (FastMath.pow(d2 * FastMath.cos((1.0d - this.alpha) * nextDouble), (1.0d / this.alpha) - 1.0d) * FastMath.sin(this.alpha * nextDouble)) / FastMath.pow(FastMath.cos(nextDouble), 1.0d / this.alpha);
        }
        return d;
    }
}
