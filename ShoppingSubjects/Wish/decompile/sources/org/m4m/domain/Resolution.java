package org.m4m.domain;

import java.io.Serializable;

public class Resolution implements Serializable {
    private final int height;
    private final int width;

    public Resolution(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Resolution resolution = (Resolution) obj;
        return this.height == resolution.height && this.width == resolution.width;
    }

    public int hashCode() {
        return (this.width * 31) + this.height;
    }
}
