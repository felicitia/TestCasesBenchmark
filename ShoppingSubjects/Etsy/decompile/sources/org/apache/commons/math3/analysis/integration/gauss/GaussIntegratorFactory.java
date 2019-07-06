package org.apache.commons.math3.analysis.integration.gauss;

import java.math.BigDecimal;
import org.apache.commons.math3.util.Pair;

public class GaussIntegratorFactory {
    private final BaseRuleFactory<Double> legendre = new LegendreRuleFactory();
    private final BaseRuleFactory<BigDecimal> legendreHighPrecision = new LegendreHighPrecisionRuleFactory();

    public GaussIntegrator legendre(int i) {
        return new GaussIntegrator(getRule(this.legendre, i));
    }

    public GaussIntegrator legendre(int i, double d, double d2) {
        return new GaussIntegrator(transform(getRule(this.legendre, i), d, d2));
    }

    public GaussIntegrator legendreHighPrecision(int i) {
        return new GaussIntegrator(getRule(this.legendreHighPrecision, i));
    }

    public GaussIntegrator legendreHighPrecision(int i, double d, double d2) {
        return new GaussIntegrator(transform(getRule(this.legendreHighPrecision, i), d, d2));
    }

    private static Pair<double[], double[]> getRule(BaseRuleFactory<? extends Number> baseRuleFactory, int i) {
        return baseRuleFactory.getRule(i);
    }

    private static Pair<double[], double[]> transform(Pair<double[], double[]> pair, double d, double d2) {
        double[] dArr = (double[]) pair.getFirst();
        double[] dArr2 = (double[]) pair.getSecond();
        double d3 = (d2 - d) / 2.0d;
        double d4 = d + d3;
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = (dArr[i] * d3) + d4;
            dArr2[i] = dArr2[i] * d3;
        }
        return new Pair<>(dArr, dArr2);
    }
}
