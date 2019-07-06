package org.apache.commons.math3.ode;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class UnknownParameterException extends MathIllegalArgumentException {
    private static final long serialVersionUID = 20120902;
    private final String name;

    public UnknownParameterException(String str) {
        super(LocalizedFormats.UNKNOWN_PARAMETER, new Object[0]);
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
