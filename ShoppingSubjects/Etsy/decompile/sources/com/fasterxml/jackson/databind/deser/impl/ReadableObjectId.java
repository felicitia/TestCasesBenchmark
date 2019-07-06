package com.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;

public class ReadableObjectId {
    public final Object id;
    public Object item;

    public ReadableObjectId(Object obj) {
        this.id = obj;
    }

    public void bindItem(Object obj) throws IOException {
        if (this.item != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Already had POJO for id (");
            sb.append(this.id.getClass().getName());
            sb.append(") [");
            sb.append(this.id);
            sb.append("]");
            throw new IllegalStateException(sb.toString());
        }
        this.item = obj;
    }
}
