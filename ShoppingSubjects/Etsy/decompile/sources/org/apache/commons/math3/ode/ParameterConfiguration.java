package org.apache.commons.math3.ode;

import java.io.Serializable;

class ParameterConfiguration implements Serializable {
    private static final long serialVersionUID = 2247518849090889379L;
    private double hP;
    private String parameterName;

    public ParameterConfiguration(String str, double d) {
        this.parameterName = str;
        this.hP = d;
    }

    public String getParameterName() {
        return this.parameterName;
    }

    public double getHP() {
        return this.hP;
    }

    public void setHP(double d) {
        this.hP = d;
    }
}
