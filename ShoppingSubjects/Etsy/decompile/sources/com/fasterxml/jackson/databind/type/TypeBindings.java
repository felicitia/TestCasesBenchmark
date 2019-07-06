package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class TypeBindings {
    private static final JavaType[] NO_TYPES = new JavaType[0];
    public static final JavaType UNBOUND = new SimpleType(Object.class);
    protected Map<String, JavaType> _bindings;
    protected final Class<?> _contextClass;
    protected final JavaType _contextType;
    private final TypeBindings _parentBindings;
    protected HashSet<String> _placeholders;
    protected final TypeFactory _typeFactory;

    public TypeBindings(TypeFactory typeFactory, Class<?> cls) {
        this(typeFactory, null, cls, null);
    }

    public TypeBindings(TypeFactory typeFactory, JavaType javaType) {
        this(typeFactory, null, javaType.getRawClass(), javaType);
    }

    public TypeBindings childInstance() {
        return new TypeBindings(this._typeFactory, this, this._contextClass, this._contextType);
    }

    private TypeBindings(TypeFactory typeFactory, TypeBindings typeBindings, Class<?> cls, JavaType javaType) {
        this._typeFactory = typeFactory;
        this._parentBindings = typeBindings;
        this._contextClass = cls;
        this._contextType = javaType;
    }

    public JavaType resolveType(Class<?> cls) {
        return this._typeFactory._constructType(cls, this);
    }

    public JavaType resolveType(Type type) {
        return this._typeFactory._constructType(type, this);
    }

    public int getBindingCount() {
        if (this._bindings == null) {
            _resolve();
        }
        return this._bindings.size();
    }

    public JavaType findType(String str) {
        if (this._bindings == null) {
            _resolve();
        }
        JavaType javaType = (JavaType) this._bindings.get(str);
        if (javaType != null) {
            return javaType;
        }
        if (this._placeholders != null && this._placeholders.contains(str)) {
            return UNBOUND;
        }
        if (this._parentBindings != null) {
            return this._parentBindings.findType(str);
        }
        if (this._contextClass != null && this._contextClass.getEnclosingClass() != null && !Modifier.isStatic(this._contextClass.getModifiers())) {
            return UNBOUND;
        }
        String str2 = this._contextClass != null ? this._contextClass.getName() : this._contextType != null ? this._contextType.toString() : "UNKNOWN";
        StringBuilder sb = new StringBuilder();
        sb.append("Type variable '");
        sb.append(str);
        sb.append("' can not be resolved (with context of class ");
        sb.append(str2);
        sb.append(")");
        throw new IllegalArgumentException(sb.toString());
    }

    public void addBinding(String str, JavaType javaType) {
        if (this._bindings == null || this._bindings.size() == 0) {
            this._bindings = new LinkedHashMap();
        }
        this._bindings.put(str, javaType);
    }

    public JavaType[] typesAsArray() {
        if (this._bindings == null) {
            _resolve();
        }
        if (this._bindings.size() == 0) {
            return NO_TYPES;
        }
        return (JavaType[]) this._bindings.values().toArray(new JavaType[this._bindings.size()]);
    }

    /* access modifiers changed from: protected */
    public void _resolve() {
        _resolveBindings(this._contextClass);
        if (this._contextType != null) {
            int containedTypeCount = this._contextType.containedTypeCount();
            if (containedTypeCount > 0) {
                for (int i = 0; i < containedTypeCount; i++) {
                    addBinding(this._contextType.containedTypeName(i), this._contextType.containedType(i));
                }
            }
        }
        if (this._bindings == null) {
            this._bindings = Collections.emptyMap();
        }
    }

    public void _addPlaceholder(String str) {
        if (this._placeholders == null) {
            this._placeholders = new HashSet<>();
        }
        this._placeholders.add(str);
    }

    /* access modifiers changed from: protected */
    public void _resolveBindings(Type type) {
        Class cls;
        if (type != null) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                    Class cls2 = (Class) parameterizedType.getRawType();
                    TypeVariable[] typeParameters = cls2.getTypeParameters();
                    if (typeParameters.length != actualTypeArguments.length) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Strange parametrized type (in class ");
                        sb.append(cls2.getName());
                        sb.append("): number of type arguments != number of type parameters (");
                        sb.append(actualTypeArguments.length);
                        sb.append(" vs ");
                        sb.append(typeParameters.length);
                        sb.append(")");
                        throw new IllegalArgumentException(sb.toString());
                    }
                    int length = actualTypeArguments.length;
                    for (int i = 0; i < length; i++) {
                        String name = typeParameters[i].getName();
                        if (this._bindings == null) {
                            this._bindings = new LinkedHashMap();
                        } else if (this._bindings.containsKey(name)) {
                        }
                        _addPlaceholder(name);
                        this._bindings.put(name, this._typeFactory._constructType(actualTypeArguments[i], this));
                    }
                }
                cls = (Class) parameterizedType.getRawType();
            } else if (type instanceof Class) {
                cls = (Class) type;
                Class declaringClass = cls.getDeclaringClass();
                if (declaringClass != null && !declaringClass.isAssignableFrom(cls)) {
                    _resolveBindings(cls.getDeclaringClass());
                }
                TypeVariable[] typeParameters2 = cls.getTypeParameters();
                if (typeParameters2 != null && typeParameters2.length > 0) {
                    JavaType[] javaTypeArr = null;
                    if (this._contextType != null && cls.isAssignableFrom(this._contextType.getRawClass())) {
                        javaTypeArr = this._typeFactory.findTypeParameters(this._contextType, cls);
                    }
                    for (int i2 = 0; i2 < typeParameters2.length; i2++) {
                        TypeVariable typeVariable = typeParameters2[i2];
                        String name2 = typeVariable.getName();
                        Type type2 = typeVariable.getBounds()[0];
                        if (type2 != null) {
                            if (this._bindings == null) {
                                this._bindings = new LinkedHashMap();
                            } else if (this._bindings.containsKey(name2)) {
                            }
                            _addPlaceholder(name2);
                            if (javaTypeArr != null) {
                                this._bindings.put(name2, javaTypeArr[i2]);
                            } else {
                                this._bindings.put(name2, this._typeFactory._constructType(type2, this));
                            }
                        }
                    }
                }
            } else {
                return;
            }
            _resolveBindings(cls.getGenericSuperclass());
            for (Type _resolveBindings : cls.getGenericInterfaces()) {
                _resolveBindings(_resolveBindings);
            }
        }
    }

    public String toString() {
        if (this._bindings == null) {
            _resolve();
        }
        StringBuilder sb = new StringBuilder("[TypeBindings for ");
        if (this._contextType != null) {
            sb.append(this._contextType.toString());
        } else {
            sb.append(this._contextClass.getName());
        }
        sb.append(": ");
        sb.append(this._bindings);
        sb.append("]");
        return sb.toString();
    }
}
