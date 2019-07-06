package com.onfido.c.a;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class k {
    private final Map<String, Object> a = new ConcurrentHashMap();

    public Map<String, Object> a() {
        return new LinkedHashMap(this.a);
    }
}
