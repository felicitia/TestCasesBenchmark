package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class UnrecognizedPropertyException extends JsonMappingException {
    private static final int MAX_DESC_LENGTH = 200;
    private static final long serialVersionUID = 1;
    protected transient String _propertiesAsString;
    protected final Collection<Object> _propertyIds;
    protected final Class<?> _referringClass;
    protected final String _unrecognizedPropertyName;

    public UnrecognizedPropertyException(String str, JsonLocation jsonLocation, Class<?> cls, String str2, Collection<Object> collection) {
        super(str, jsonLocation);
        this._referringClass = cls;
        this._unrecognizedPropertyName = str2;
        this._propertyIds = collection;
    }

    public static UnrecognizedPropertyException from(JsonParser jsonParser, Object obj, String str, Collection<Object> collection) {
        Class cls;
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        if (obj instanceof Class) {
            cls = (Class) obj;
        } else {
            cls = obj.getClass();
        }
        Class cls2 = cls;
        StringBuilder sb = new StringBuilder();
        sb.append("Unrecognized field \"");
        sb.append(str);
        sb.append("\" (class ");
        sb.append(cls2.getName());
        sb.append("), not marked as ignorable");
        UnrecognizedPropertyException unrecognizedPropertyException = new UnrecognizedPropertyException(sb.toString(), jsonParser.getCurrentLocation(), cls2, str, collection);
        unrecognizedPropertyException.prependPath(obj, str);
        return unrecognizedPropertyException;
    }

    public String getMessageSuffix() {
        String str = this._propertiesAsString;
        if (str != null || this._propertyIds == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder(100);
        int size = this._propertyIds.size();
        if (size != 1) {
            sb.append(" (");
            sb.append(size);
            sb.append(" known properties: ");
            Iterator it = this._propertyIds.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                sb.append(", \"");
                sb.append(String.valueOf(it.next()));
                sb.append('\"');
                if (sb.length() > 200) {
                    sb.append(" [truncated]");
                    break;
                }
            }
        } else {
            sb.append(" (one known property: \"");
            sb.append(String.valueOf(this._propertyIds.iterator().next()));
            sb.append('\"');
        }
        sb.append("])");
        String sb2 = sb.toString();
        this._propertiesAsString = sb2;
        return sb2;
    }

    public Class<?> getReferringClass() {
        return this._referringClass;
    }

    public String getUnrecognizedPropertyName() {
        return this._unrecognizedPropertyName;
    }

    public Collection<Object> getKnownPropertyIds() {
        if (this._propertyIds == null) {
            return null;
        }
        return Collections.unmodifiableCollection(this._propertyIds);
    }
}
