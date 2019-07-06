package org.m4m.domain.pipeline;

class ManyTypes {
    private final Class[] types;

    public ManyTypes(Class... clsArr) {
        this.types = clsArr;
    }

    public Class[] getTypes() {
        return this.types;
    }
}
