package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.Localizable;

public class MathIllegalNumberException extends MathIllegalArgumentException {
    private static final long serialVersionUID = -7447085893598031110L;
    private final Number argument;

    protected MathIllegalNumberException(Localizable localizable, Number number, Object... objArr) {
        super(localizable, number, objArr);
        this.argument = number;
    }

    public Number getArgument() {
        return this.argument;
    }
}
