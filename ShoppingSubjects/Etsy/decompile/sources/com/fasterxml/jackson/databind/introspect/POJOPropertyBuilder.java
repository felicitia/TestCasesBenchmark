package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.PropertyName;

public class POJOPropertyBuilder extends BeanPropertyDefinition implements Comparable<POJOPropertyBuilder> {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected a<AnnotatedParameter> _ctorParameters;
    protected a<AnnotatedField> _fields;
    protected final boolean _forSerialization;
    protected a<AnnotatedMethod> _getters;
    protected final String _internalName;
    protected final String _name;
    protected a<AnnotatedMethod> _setters;

    private static final class a<T> {
        public final T a;
        public final a<T> b;
        public final String c;
        public final boolean d;
        public final boolean e;

        public a(T t, a<T> aVar, String str, boolean z, boolean z2) {
            this.a = t;
            this.b = aVar;
            String str2 = null;
            if (str == null) {
                this.c = null;
            } else {
                if (str.length() != 0) {
                    str2 = str;
                }
                this.c = str2;
            }
            this.d = z;
            this.e = z2;
        }

        public a<T> a(T t) {
            if (t == this.a) {
                return this;
            }
            a aVar = new a(t, this.b, this.c, this.d, this.e);
            return aVar;
        }

        public a<T> a(a<T> aVar) {
            if (aVar == this.b) {
                return this;
            }
            a aVar2 = new a(this.a, aVar, this.c, this.d, this.e);
            return aVar2;
        }

        public a<T> a() {
            if (this.e) {
                return this.b == null ? null : this.b.a();
            }
            if (this.b != null) {
                a<T> a2 = this.b.a();
                if (a2 != this.b) {
                    return a(a2);
                }
            }
            return this;
        }

        public a<T> b() {
            a<T> b2 = this.b == null ? null : this.b.b();
            return this.d ? a(b2) : b2;
        }

        /* access modifiers changed from: private */
        public a<T> b(a<T> aVar) {
            if (this.b == null) {
                return a(aVar);
            }
            return a(this.b.b(aVar));
        }

        public a<T> c() {
            if (this.b == null) {
                return this;
            }
            a<T> c2 = this.b.c();
            if (this.c != null) {
                if (c2.c == null) {
                    return a(null);
                }
                return a(c2);
            } else if (c2.c != null) {
                return c2;
            } else {
                if (this.d == c2.d) {
                    return a(c2);
                }
                if (this.d) {
                    c2 = a(null);
                }
                return c2;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.a.toString());
            sb.append("[visible=");
            sb.append(this.d);
            sb.append("]");
            String sb2 = sb.toString();
            if (this.b == null) {
                return sb2;
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2);
            sb3.append(", ");
            sb3.append(this.b.toString());
            return sb3.toString();
        }
    }

    private interface b<T> {
        T b(AnnotatedMember annotatedMember);
    }

    public POJOPropertyBuilder(String str, AnnotationIntrospector annotationIntrospector, boolean z) {
        this._internalName = str;
        this._name = str;
        this._annotationIntrospector = annotationIntrospector;
        this._forSerialization = z;
    }

    public POJOPropertyBuilder(POJOPropertyBuilder pOJOPropertyBuilder, String str) {
        this._internalName = pOJOPropertyBuilder._internalName;
        this._name = str;
        this._annotationIntrospector = pOJOPropertyBuilder._annotationIntrospector;
        this._fields = pOJOPropertyBuilder._fields;
        this._ctorParameters = pOJOPropertyBuilder._ctorParameters;
        this._getters = pOJOPropertyBuilder._getters;
        this._setters = pOJOPropertyBuilder._setters;
        this._forSerialization = pOJOPropertyBuilder._forSerialization;
    }

    public POJOPropertyBuilder withName(String str) {
        return new POJOPropertyBuilder(this, str);
    }

    public int compareTo(POJOPropertyBuilder pOJOPropertyBuilder) {
        if (this._ctorParameters != null) {
            if (pOJOPropertyBuilder._ctorParameters == null) {
                return -1;
            }
        } else if (pOJOPropertyBuilder._ctorParameters != null) {
            return 1;
        }
        return getName().compareTo(pOJOPropertyBuilder.getName());
    }

    public String getName() {
        return this._name;
    }

    public String getInternalName() {
        return this._internalName;
    }

    public PropertyName getWrapperName() {
        AnnotatedMember primaryMember = getPrimaryMember();
        if (primaryMember == null || this._annotationIntrospector == null) {
            return null;
        }
        return this._annotationIntrospector.findWrapperName(primaryMember);
    }

    public boolean isExplicitlyIncluded() {
        return _anyExplicitNames(this._fields) || _anyExplicitNames(this._getters) || _anyExplicitNames(this._setters) || _anyExplicitNames(this._ctorParameters);
    }

    public boolean hasGetter() {
        return this._getters != null;
    }

    public boolean hasSetter() {
        return this._setters != null;
    }

    public boolean hasField() {
        return this._fields != null;
    }

    public boolean hasConstructorParameter() {
        return this._ctorParameters != null;
    }

    public boolean couldSerialize() {
        return (this._getters == null && this._fields == null) ? false : true;
    }

    public AnnotatedMethod getGetter() {
        if (this._getters == null) {
            return null;
        }
        AnnotatedMethod annotatedMethod = (AnnotatedMethod) this._getters.a;
        a<T> aVar = this._getters.b;
        while (aVar != null) {
            AnnotatedMethod annotatedMethod2 = (AnnotatedMethod) aVar.a;
            Class declaringClass = annotatedMethod.getDeclaringClass();
            Class declaringClass2 = annotatedMethod2.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (declaringClass.isAssignableFrom(declaringClass2)) {
                    annotatedMethod = annotatedMethod2;
                } else if (declaringClass2.isAssignableFrom(declaringClass)) {
                }
                aVar = aVar.b;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Conflicting getter definitions for property \"");
            sb.append(getName());
            sb.append("\": ");
            sb.append(annotatedMethod.getFullName());
            sb.append(" vs ");
            sb.append(annotatedMethod2.getFullName());
            throw new IllegalArgumentException(sb.toString());
        }
        return annotatedMethod;
    }

    public AnnotatedMethod getSetter() {
        if (this._setters == null) {
            return null;
        }
        AnnotatedMethod annotatedMethod = (AnnotatedMethod) this._setters.a;
        a<T> aVar = this._setters.b;
        while (aVar != null) {
            AnnotatedMethod annotatedMethod2 = (AnnotatedMethod) aVar.a;
            Class declaringClass = annotatedMethod.getDeclaringClass();
            Class declaringClass2 = annotatedMethod2.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (declaringClass.isAssignableFrom(declaringClass2)) {
                    annotatedMethod = annotatedMethod2;
                } else if (declaringClass2.isAssignableFrom(declaringClass)) {
                }
                aVar = aVar.b;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Conflicting setter definitions for property \"");
            sb.append(getName());
            sb.append("\": ");
            sb.append(annotatedMethod.getFullName());
            sb.append(" vs ");
            sb.append(annotatedMethod2.getFullName());
            throw new IllegalArgumentException(sb.toString());
        }
        return annotatedMethod;
    }

    public AnnotatedField getField() {
        if (this._fields == null) {
            return null;
        }
        AnnotatedField annotatedField = (AnnotatedField) this._fields.a;
        a<T> aVar = this._fields.b;
        while (aVar != null) {
            AnnotatedField annotatedField2 = (AnnotatedField) aVar.a;
            Class declaringClass = annotatedField.getDeclaringClass();
            Class declaringClass2 = annotatedField2.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (declaringClass.isAssignableFrom(declaringClass2)) {
                    annotatedField = annotatedField2;
                } else if (declaringClass2.isAssignableFrom(declaringClass)) {
                }
                aVar = aVar.b;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Multiple fields representing property \"");
            sb.append(getName());
            sb.append("\": ");
            sb.append(annotatedField.getFullName());
            sb.append(" vs ");
            sb.append(annotatedField2.getFullName());
            throw new IllegalArgumentException(sb.toString());
        }
        return annotatedField;
    }

    public AnnotatedParameter getConstructorParameter() {
        if (this._ctorParameters == null) {
            return null;
        }
        a aVar = this._ctorParameters;
        while (!(((AnnotatedParameter) aVar.a).getOwner() instanceof AnnotatedConstructor)) {
            aVar = aVar.b;
            if (aVar == null) {
                return (AnnotatedParameter) this._ctorParameters.a;
            }
        }
        return (AnnotatedParameter) aVar.a;
    }

    public AnnotatedMember getAccessor() {
        AnnotatedMethod getter = getGetter();
        return getter == null ? getField() : getter;
    }

    public AnnotatedMember getMutator() {
        AnnotatedParameter constructorParameter = getConstructorParameter();
        if (constructorParameter != null) {
            return constructorParameter;
        }
        AnnotatedMethod setter = getSetter();
        return setter == null ? getField() : setter;
    }

    public AnnotatedMember getPrimaryMember() {
        if (this._forSerialization) {
            return getAccessor();
        }
        return getMutator();
    }

    public Class<?>[] findViews() {
        return (Class[]) fromMemberAnnotations(new b<Class<?>[]>() {
            /* renamed from: a */
            public Class<?>[] b(AnnotatedMember annotatedMember) {
                return POJOPropertyBuilder.this._annotationIntrospector.findViews(annotatedMember);
            }
        });
    }

    public ReferenceProperty findReferenceType() {
        return (ReferenceProperty) fromMemberAnnotations(new b<ReferenceProperty>() {
            /* renamed from: a */
            public ReferenceProperty b(AnnotatedMember annotatedMember) {
                return POJOPropertyBuilder.this._annotationIntrospector.findReferenceType(annotatedMember);
            }
        });
    }

    public boolean isTypeId() {
        Boolean bool = (Boolean) fromMemberAnnotations(new b<Boolean>() {
            /* renamed from: a */
            public Boolean b(AnnotatedMember annotatedMember) {
                return POJOPropertyBuilder.this._annotationIntrospector.isTypeId(annotatedMember);
            }
        });
        return bool != null && bool.booleanValue();
    }

    public boolean isRequired() {
        Boolean bool = (Boolean) fromMemberAnnotations(new b<Boolean>() {
            /* renamed from: a */
            public Boolean b(AnnotatedMember annotatedMember) {
                return POJOPropertyBuilder.this._annotationIntrospector.hasRequiredMarker(annotatedMember);
            }
        });
        return bool != null && bool.booleanValue();
    }

    public ObjectIdInfo findObjectIdInfo() {
        return (ObjectIdInfo) fromMemberAnnotations(new b<ObjectIdInfo>() {
            /* renamed from: a */
            public ObjectIdInfo b(AnnotatedMember annotatedMember) {
                ObjectIdInfo findObjectIdInfo = POJOPropertyBuilder.this._annotationIntrospector.findObjectIdInfo(annotatedMember);
                return findObjectIdInfo != null ? POJOPropertyBuilder.this._annotationIntrospector.findObjectReferenceInfo(annotatedMember, findObjectIdInfo) : findObjectIdInfo;
            }
        });
    }

    public void addField(AnnotatedField annotatedField, String str, boolean z, boolean z2) {
        a aVar = new a(annotatedField, this._fields, str, z, z2);
        this._fields = aVar;
    }

    public void addCtor(AnnotatedParameter annotatedParameter, String str, boolean z, boolean z2) {
        a aVar = new a(annotatedParameter, this._ctorParameters, str, z, z2);
        this._ctorParameters = aVar;
    }

    public void addGetter(AnnotatedMethod annotatedMethod, String str, boolean z, boolean z2) {
        a aVar = new a(annotatedMethod, this._getters, str, z, z2);
        this._getters = aVar;
    }

    public void addSetter(AnnotatedMethod annotatedMethod, String str, boolean z, boolean z2) {
        a aVar = new a(annotatedMethod, this._setters, str, z, z2);
        this._setters = aVar;
    }

    public void addAll(POJOPropertyBuilder pOJOPropertyBuilder) {
        this._fields = merge(this._fields, pOJOPropertyBuilder._fields);
        this._ctorParameters = merge(this._ctorParameters, pOJOPropertyBuilder._ctorParameters);
        this._getters = merge(this._getters, pOJOPropertyBuilder._getters);
        this._setters = merge(this._setters, pOJOPropertyBuilder._setters);
    }

    private static <T> a<T> merge(a<T> aVar, a<T> aVar2) {
        if (aVar == null) {
            return aVar2;
        }
        return aVar2 == null ? aVar : aVar.b(aVar2);
    }

    public void removeIgnored() {
        this._fields = _removeIgnored(this._fields);
        this._getters = _removeIgnored(this._getters);
        this._setters = _removeIgnored(this._setters);
        this._ctorParameters = _removeIgnored(this._ctorParameters);
    }

    @Deprecated
    public void removeNonVisible() {
        removeNonVisible(false);
    }

    public void removeNonVisible(boolean z) {
        this._getters = _removeNonVisible(this._getters);
        this._ctorParameters = _removeNonVisible(this._ctorParameters);
        if (z || this._getters == null) {
            this._fields = _removeNonVisible(this._fields);
            this._setters = _removeNonVisible(this._setters);
        }
    }

    public void trimByVisibility() {
        this._fields = _trimByVisibility(this._fields);
        this._getters = _trimByVisibility(this._getters);
        this._setters = _trimByVisibility(this._setters);
        this._ctorParameters = _trimByVisibility(this._ctorParameters);
    }

    public void mergeAnnotations(boolean z) {
        if (z) {
            if (this._getters != null) {
                this._getters = this._getters.a(((AnnotatedMethod) this._getters.a).withAnnotations(_mergeAnnotations(0, this._getters, this._fields, this._ctorParameters, this._setters)));
            } else if (this._fields != null) {
                this._fields = this._fields.a(((AnnotatedField) this._fields.a).withAnnotations(_mergeAnnotations(0, this._fields, this._ctorParameters, this._setters)));
            }
        } else if (this._ctorParameters != null) {
            this._ctorParameters = this._ctorParameters.a(((AnnotatedParameter) this._ctorParameters.a).withAnnotations(_mergeAnnotations(0, this._ctorParameters, this._setters, this._fields, this._getters)));
        } else if (this._setters != null) {
            this._setters = this._setters.a(((AnnotatedMethod) this._setters.a).withAnnotations(_mergeAnnotations(0, this._setters, this._fields, this._getters)));
        } else if (this._fields != null) {
            this._fields = this._fields.a(((AnnotatedField) this._fields.a).withAnnotations(_mergeAnnotations(0, this._fields, this._getters)));
        }
    }

    private AnnotationMap _mergeAnnotations(int i, a<? extends AnnotatedMember>... aVarArr) {
        AnnotationMap allAnnotations = ((AnnotatedMember) aVarArr[i].a).getAllAnnotations();
        while (true) {
            i++;
            if (i >= aVarArr.length) {
                return allAnnotations;
            }
            if (aVarArr[i] != null) {
                return AnnotationMap.merge(allAnnotations, _mergeAnnotations(i, aVarArr));
            }
        }
    }

    private <T> a<T> _removeIgnored(a<T> aVar) {
        return aVar == null ? aVar : aVar.a();
    }

    private <T> a<T> _removeNonVisible(a<T> aVar) {
        return aVar == null ? aVar : aVar.b();
    }

    private <T> a<T> _trimByVisibility(a<T> aVar) {
        return aVar == null ? aVar : aVar.c();
    }

    private <T> boolean _anyExplicitNames(a<T> aVar) {
        while (aVar != null) {
            if (aVar.c != null && aVar.c.length() > 0) {
                return true;
            }
            aVar = aVar.b;
        }
        return false;
    }

    public boolean anyVisible() {
        return _anyVisible(this._fields) || _anyVisible(this._getters) || _anyVisible(this._setters) || _anyVisible(this._ctorParameters);
    }

    private <T> boolean _anyVisible(a<T> aVar) {
        while (aVar != null) {
            if (aVar.d) {
                return true;
            }
            aVar = aVar.b;
        }
        return false;
    }

    public boolean anyIgnorals() {
        return _anyIgnorals(this._fields) || _anyIgnorals(this._getters) || _anyIgnorals(this._setters) || _anyIgnorals(this._ctorParameters);
    }

    private <T> boolean _anyIgnorals(a<T> aVar) {
        while (aVar != null) {
            if (aVar.e) {
                return true;
            }
            aVar = aVar.b;
        }
        return false;
    }

    public String findNewName() {
        a findRenamed = findRenamed(this._ctorParameters, findRenamed(this._setters, findRenamed(this._getters, findRenamed(this._fields, null))));
        if (findRenamed == null) {
            return null;
        }
        return findRenamed.c;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a<? extends com.fasterxml.jackson.databind.introspect.AnnotatedMember>, code=com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a, for r4v0, types: [com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a, com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a<? extends com.fasterxml.jackson.databind.introspect.AnnotatedMember>] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a<? extends com.fasterxml.jackson.databind.introspect.AnnotatedMember>, code=com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a, for r5v0, types: [com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a, com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a<? extends com.fasterxml.jackson.databind.introspect.AnnotatedMember>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.a<? extends com.fasterxml.jackson.databind.introspect.AnnotatedMember> findRenamed(com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.a r4, com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.a r5) {
        /*
            r3 = this;
        L_0x0000:
            if (r4 == 0) goto L_0x005b
            java.lang.String r0 = r4.c
            if (r0 != 0) goto L_0x0007
            goto L_0x0058
        L_0x0007:
            java.lang.String r1 = r3._name
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0010
            goto L_0x0058
        L_0x0010:
            if (r5 != 0) goto L_0x0014
            r5 = r4
            goto L_0x0058
        L_0x0014:
            java.lang.String r1 = r5.c
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0058
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Conflicting property name definitions: '"
            r1.append(r2)
            java.lang.String r2 = r5.c
            r1.append(r2)
            java.lang.String r2 = "' (for "
            r1.append(r2)
            T r5 = r5.a
            r1.append(r5)
            java.lang.String r5 = ") vs '"
            r1.append(r5)
            java.lang.String r5 = r4.c
            r1.append(r5)
            java.lang.String r5 = "' (for "
            r1.append(r5)
            T r4 = r4.a
            r1.append(r4)
            java.lang.String r4 = ")"
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r0.<init>(r4)
            throw r0
        L_0x0058:
            com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a<T> r4 = r4.b
            goto L_0x0000
        L_0x005b:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.findRenamed(com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a, com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a):com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Property '");
        sb.append(this._name);
        sb.append("'; ctors: ");
        sb.append(this._ctorParameters);
        sb.append(", field(s): ");
        sb.append(this._fields);
        sb.append(", getter(s): ");
        sb.append(this._getters);
        sb.append(", setter(s): ");
        sb.append(this._setters);
        sb.append("]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T fromMemberAnnotations(com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.b<T> r3) {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._annotationIntrospector
            r1 = 0
            if (r0 == 0) goto L_0x0048
            boolean r0 = r2._forSerialization
            if (r0 == 0) goto L_0x0019
            com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a<com.fasterxml.jackson.databind.introspect.AnnotatedMethod> r0 = r2._getters
            if (r0 == 0) goto L_0x0038
            com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a<com.fasterxml.jackson.databind.introspect.AnnotatedMethod> r0 = r2._getters
            T r0 = r0.a
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = (com.fasterxml.jackson.databind.introspect.AnnotatedMember) r0
            java.lang.Object r0 = r3.b(r0)
        L_0x0017:
            r1 = r0
            goto L_0x0038
        L_0x0019:
            com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a<com.fasterxml.jackson.databind.introspect.AnnotatedParameter> r0 = r2._ctorParameters
            if (r0 == 0) goto L_0x0027
            com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a<com.fasterxml.jackson.databind.introspect.AnnotatedParameter> r0 = r2._ctorParameters
            T r0 = r0.a
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = (com.fasterxml.jackson.databind.introspect.AnnotatedMember) r0
            java.lang.Object r1 = r3.b(r0)
        L_0x0027:
            if (r1 != 0) goto L_0x0038
            com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a<com.fasterxml.jackson.databind.introspect.AnnotatedMethod> r0 = r2._setters
            if (r0 == 0) goto L_0x0038
            com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a<com.fasterxml.jackson.databind.introspect.AnnotatedMethod> r0 = r2._setters
            T r0 = r0.a
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = (com.fasterxml.jackson.databind.introspect.AnnotatedMember) r0
            java.lang.Object r0 = r3.b(r0)
            goto L_0x0017
        L_0x0038:
            if (r1 != 0) goto L_0x0048
            com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a<com.fasterxml.jackson.databind.introspect.AnnotatedField> r0 = r2._fields
            if (r0 == 0) goto L_0x0048
            com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$a<com.fasterxml.jackson.databind.introspect.AnnotatedField> r0 = r2._fields
            T r0 = r0.a
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = (com.fasterxml.jackson.databind.introspect.AnnotatedMember) r0
            java.lang.Object r1 = r3.b(r0)
        L_0x0048:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.fromMemberAnnotations(com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$b):java.lang.Object");
    }
}
