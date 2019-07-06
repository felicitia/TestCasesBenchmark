package org.apache.commons.lang3.mutable;

import java.io.Serializable;

public class MutableBoolean implements Serializable, Comparable<MutableBoolean>, Mutable<Boolean> {
    private static final long serialVersionUID = -4830728138360036487L;
    private boolean value;

    public MutableBoolean() {
    }

    public MutableBoolean(boolean z) {
        this.value = z;
    }

    public MutableBoolean(Boolean bool) {
        this.value = bool.booleanValue();
    }

    public Boolean getValue() {
        return Boolean.valueOf(this.value);
    }

    public void setValue(boolean z) {
        this.value = z;
    }

    public void setValue(Boolean bool) {
        this.value = bool.booleanValue();
    }

    public boolean isTrue() {
        return this.value;
    }

    public boolean isFalse() {
        return !this.value;
    }

    public boolean booleanValue() {
        return this.value;
    }

    public Boolean toBoolean() {
        return Boolean.valueOf(booleanValue());
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof MutableBoolean)) {
            return false;
        }
        if (this.value == ((MutableBoolean) obj).booleanValue()) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return (this.value ? Boolean.TRUE : Boolean.FALSE).hashCode();
    }

    public int compareTo(MutableBoolean mutableBoolean) {
        if (this.value == mutableBoolean.value) {
            return 0;
        }
        return this.value ? 1 : -1;
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
