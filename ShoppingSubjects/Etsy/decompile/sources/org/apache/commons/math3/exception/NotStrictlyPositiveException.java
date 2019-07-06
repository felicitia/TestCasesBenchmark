package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.Localizable;

public class NotStrictlyPositiveException extends NumberIsTooSmallException {
    private static final long serialVersionUID = -7824848630829852237L;

    public NotStrictlyPositiveException(Number number) {
        super(number, Integer.valueOf(0), false);
    }

    public NotStrictlyPositiveException(Localizable localizable, Number number) {
        super(localizable, number, Integer.valueOf(0), false);
    }
}
