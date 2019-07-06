package org.apache.commons.math3.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.math3.exception.MathIllegalArgumentException;

public class TransformerMap implements Serializable, NumberTransformer {
    private static final long serialVersionUID = 4605318041528645258L;
    private NumberTransformer defaultTransformer;
    private Map<Class<?>, NumberTransformer> map;

    public TransformerMap() {
        this.defaultTransformer = null;
        this.map = null;
        this.map = new HashMap();
        this.defaultTransformer = new DefaultTransformer();
    }

    public boolean containsClass(Class<?> cls) {
        return this.map.containsKey(cls);
    }

    public boolean containsTransformer(NumberTransformer numberTransformer) {
        return this.map.containsValue(numberTransformer);
    }

    public NumberTransformer getTransformer(Class<?> cls) {
        return (NumberTransformer) this.map.get(cls);
    }

    public NumberTransformer putTransformer(Class<?> cls, NumberTransformer numberTransformer) {
        return (NumberTransformer) this.map.put(cls, numberTransformer);
    }

    public NumberTransformer removeTransformer(Class<?> cls) {
        return (NumberTransformer) this.map.remove(cls);
    }

    public void clear() {
        this.map.clear();
    }

    public Set<Class<?>> classes() {
        return this.map.keySet();
    }

    public Collection<NumberTransformer> transformers() {
        return this.map.values();
    }

    public double transform(Object obj) throws MathIllegalArgumentException {
        if ((obj instanceof Number) || (obj instanceof String)) {
            return this.defaultTransformer.transform(obj);
        }
        NumberTransformer transformer = getTransformer(obj.getClass());
        if (transformer != null) {
            return transformer.transform(obj);
        }
        return Double.NaN;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransformerMap)) {
            return false;
        }
        TransformerMap transformerMap = (TransformerMap) obj;
        if (!this.defaultTransformer.equals(transformerMap.defaultTransformer) || this.map.size() != transformerMap.map.size()) {
            return false;
        }
        for (Entry entry : this.map.entrySet()) {
            if (!((NumberTransformer) entry.getValue()).equals(transformerMap.map.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int hashCode = this.defaultTransformer.hashCode();
        for (NumberTransformer hashCode2 : this.map.values()) {
            hashCode = (hashCode * 31) + hashCode2.hashCode();
        }
        return hashCode;
    }
}
