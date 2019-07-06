package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.Localizable;

public class NotPositiveException extends NumberIsTooSmallException {
    private static final long serialVersionUID = -2250556892093726375L;

    public NotPositiveException(Number number) {
        super(number, Integer.valueOf(0), true);
    }

    public NotPositiveException(Localizable localizable, Number number) {
        super(localizable, number, Integer.valueOf(0), true);
    }
}
