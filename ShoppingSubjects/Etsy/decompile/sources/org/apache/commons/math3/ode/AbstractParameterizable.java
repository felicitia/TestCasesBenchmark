package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractParameterizable implements Parameterizable {
    private final Collection<String> parametersNames = new ArrayList();

    protected AbstractParameterizable(String... strArr) {
        for (String add : strArr) {
            this.parametersNames.add(add);
        }
    }

    protected AbstractParameterizable(Collection<String> collection) {
        this.parametersNames.addAll(collection);
    }

    public Collection<String> getParametersNames() {
        return this.parametersNames;
    }

    public boolean isSupported(String str) {
        for (String equals : this.parametersNames) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void complainIfNotSupported(String str) throws UnknownParameterException {
        if (!isSupported(str)) {
            throw new UnknownParameterException(str);
        }
    }
}
