package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays.OrderDirection;

public class NonMonotonicSequenceException extends MathIllegalNumberException {
    private static final long serialVersionUID = 3596849179428944575L;
    private final OrderDirection direction;
    private final int index;
    private final Number previous;
    private final boolean strict;

    public NonMonotonicSequenceException(Number number, Number number2, int i) {
        this(number, number2, i, OrderDirection.INCREASING, true);
    }

    public NonMonotonicSequenceException(Number number, Number number2, int i, OrderDirection orderDirection, boolean z) {
        LocalizedFormats localizedFormats = orderDirection == OrderDirection.INCREASING ? z ? LocalizedFormats.NOT_STRICTLY_INCREASING_SEQUENCE : LocalizedFormats.NOT_INCREASING_SEQUENCE : z ? LocalizedFormats.NOT_STRICTLY_DECREASING_SEQUENCE : LocalizedFormats.NOT_DECREASING_SEQUENCE;
        super(localizedFormats, number, number2, Integer.valueOf(i), Integer.valueOf(i - 1));
        this.direction = orderDirection;
        this.strict = z;
        this.index = i;
        this.previous = number2;
    }

    public OrderDirection getDirection() {
        return this.direction;
    }

    public boolean getStrict() {
        return this.strict;
    }

    public int getIndex() {
        return this.index;
    }

    public Number getPrevious() {
        return this.previous;
    }
}
