package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.CallAdapter.Factory;

public final class Retrofit {
    private final List<Factory> adapterFactories;
    private final HttpUrl baseUrl;
    private final Call.Factory callFactory;
    private final Executor callbackExecutor;
    private final List<Converter.Factory> converterFactories;
    private final Map<Method, ServiceMethod> serviceMethodCache = new LinkedHashMap();
    private final boolean validateEagerly;

    public static final class Builder {
        private List<Factory> adapterFactories;
        private HttpUrl baseUrl;
        private Call.Factory callFactory;
        private Executor callbackExecutor;
        private List<Converter.Factory> converterFactories;
        private Platform platform;
        private boolean validateEagerly;

        Builder(Platform platform2) {
            this.converterFactories = new ArrayList();
            this.adapterFactories = new ArrayList();
            this.platform = platform2;
            this.converterFactories.add(new BuiltInConverters());
        }

        public Builder() {
            this(Platform.get());
        }

        public Builder client(OkHttpClient okHttpClient) {
            return callFactory((Call.Factory) Utils.checkNotNull(okHttpClient, "client == null"));
        }

        public Builder callFactory(Call.Factory factory) {
            this.callFactory = (Call.Factory) Utils.checkNotNull(factory, "factory == null");
            return this;
        }

        public Builder baseUrl(HttpUrl httpUrl) {
            Utils.checkNotNull(httpUrl, "baseUrl == null");
            List pathSegments = httpUrl.pathSegments();
            if (!"".equals(pathSegments.get(pathSegments.size() - 1))) {
                StringBuilder sb = new StringBuilder();
                sb.append("baseUrl must end in /: ");
                sb.append(httpUrl);
                throw new IllegalArgumentException(sb.toString());
            }
            this.baseUrl = httpUrl;
            return this;
        }

        public Builder addConverterFactory(Converter.Factory factory) {
            this.converterFactories.add(Utils.checkNotNull(factory, "factory == null"));
            return this;
        }

        public Builder addCallAdapterFactory(Factory factory) {
            this.adapterFactories.add(Utils.checkNotNull(factory, "factory == null"));
            return this;
        }

        public Retrofit build() {
            if (this.baseUrl == null) {
                throw new IllegalStateException("Base URL required.");
            }
            Call.Factory factory = this.callFactory;
            if (factory == null) {
                factory = new OkHttpClient();
            }
            Call.Factory factory2 = factory;
            Executor executor = this.callbackExecutor;
            if (executor == null) {
                executor = this.platform.defaultCallbackExecutor();
            }
            Executor executor2 = executor;
            ArrayList arrayList = new ArrayList(this.adapterFactories);
            arrayList.add(this.platform.defaultCallAdapterFactory(executor2));
            Retrofit retrofit = new Retrofit(factory2, this.baseUrl, new ArrayList(this.converterFactories), arrayList, executor2, this.validateEagerly);
            return retrofit;
        }
    }

    Retrofit(Call.Factory factory, HttpUrl httpUrl, List<Converter.Factory> list, List<Factory> list2, Executor executor, boolean z) {
        this.callFactory = factory;
        this.baseUrl = httpUrl;
        this.converterFactories = Collections.unmodifiableList(list);
        this.adapterFactories = Collections.unmodifiableList(list2);
        this.callbackExecutor = executor;
        this.validateEagerly = z;
    }

    public <T> T create(final Class<T> cls) {
        Utils.validateServiceInterface(cls);
        if (this.validateEagerly) {
            eagerlyValidateMethods(cls);
        }
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
            private final Platform platform = Platform.get();

            public Object invoke(Object obj, Method method, Object... objArr) throws Throwable {
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, objArr);
                }
                if (this.platform.isDefaultMethod(method)) {
                    return this.platform.invokeDefaultMethod(method, cls, obj, objArr);
                }
                ServiceMethod loadServiceMethod = Retrofit.this.loadServiceMethod(method);
                return loadServiceMethod.callAdapter.adapt(new OkHttpCall(loadServiceMethod, objArr));
            }
        });
    }

    private void eagerlyValidateMethods(Class<?> cls) {
        Method[] declaredMethods;
        Platform platform = Platform.get();
        for (Method method : cls.getDeclaredMethods()) {
            if (!platform.isDefaultMethod(method)) {
                loadServiceMethod(method);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public ServiceMethod loadServiceMethod(Method method) {
        ServiceMethod serviceMethod;
        synchronized (this.serviceMethodCache) {
            serviceMethod = (ServiceMethod) this.serviceMethodCache.get(method);
            if (serviceMethod == null) {
                serviceMethod = new Builder(this, method).build();
                this.serviceMethodCache.put(method, serviceMethod);
            }
        }
        return serviceMethod;
    }

    public Call.Factory callFactory() {
        return this.callFactory;
    }

    public HttpUrl baseUrl() {
        return this.baseUrl;
    }

    public CallAdapter<?> callAdapter(Type type, Annotation[] annotationArr) {
        return nextCallAdapter(null, type, annotationArr);
    }

    public CallAdapter<?> nextCallAdapter(Factory factory, Type type, Annotation[] annotationArr) {
        Utils.checkNotNull(type, "returnType == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int indexOf = this.adapterFactories.indexOf(factory) + 1;
        int size = this.adapterFactories.size();
        for (int i = indexOf; i < size; i++) {
            CallAdapter<?> callAdapter = ((Factory) this.adapterFactories.get(i)).get(type, annotationArr, this);
            if (callAdapter != null) {
                return callAdapter;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate call adapter for ");
        sb.append(type);
        sb.append(".\n");
        if (factory != null) {
            sb.append("  Skipped:");
            for (int i2 = 0; i2 < indexOf; i2++) {
                sb.append("\n   * ");
                sb.append(((Factory) this.adapterFactories.get(i2)).getClass().getName());
            }
            sb.append(10);
        }
        sb.append("  Tried:");
        int size2 = this.adapterFactories.size();
        while (indexOf < size2) {
            sb.append("\n   * ");
            sb.append(((Factory) this.adapterFactories.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> Converter<T, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        return nextRequestBodyConverter(null, type, annotationArr, annotationArr2);
    }

    public <T> Converter<T, RequestBody> nextRequestBodyConverter(Converter.Factory factory, Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "parameterAnnotations == null");
        Utils.checkNotNull(annotationArr2, "methodAnnotations == null");
        int indexOf = this.converterFactories.indexOf(factory) + 1;
        int size = this.converterFactories.size();
        for (int i = indexOf; i < size; i++) {
            Converter<T, RequestBody> requestBodyConverter = ((Converter.Factory) this.converterFactories.get(i)).requestBodyConverter(type, annotationArr, annotationArr2, this);
            if (requestBodyConverter != null) {
                return requestBodyConverter;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate RequestBody converter for ");
        sb.append(type);
        sb.append(".\n");
        if (factory != null) {
            sb.append("  Skipped:");
            for (int i2 = 0; i2 < indexOf; i2++) {
                sb.append("\n   * ");
                sb.append(((Converter.Factory) this.converterFactories.get(i2)).getClass().getName());
            }
            sb.append(10);
        }
        sb.append("  Tried:");
        int size2 = this.converterFactories.size();
        while (indexOf < size2) {
            sb.append("\n   * ");
            sb.append(((Converter.Factory) this.converterFactories.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> Converter<ResponseBody, T> responseBodyConverter(Type type, Annotation[] annotationArr) {
        return nextResponseBodyConverter(null, type, annotationArr);
    }

    public <T> Converter<ResponseBody, T> nextResponseBodyConverter(Converter.Factory factory, Type type, Annotation[] annotationArr) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int indexOf = this.converterFactories.indexOf(factory) + 1;
        int size = this.converterFactories.size();
        for (int i = indexOf; i < size; i++) {
            Converter<ResponseBody, T> responseBodyConverter = ((Converter.Factory) this.converterFactories.get(i)).responseBodyConverter(type, annotationArr, this);
            if (responseBodyConverter != null) {
                return responseBodyConverter;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate ResponseBody converter for ");
        sb.append(type);
        sb.append(".\n");
        if (factory != null) {
            sb.append("  Skipped:");
            for (int i2 = 0; i2 < indexOf; i2++) {
                sb.append("\n   * ");
                sb.append(((Converter.Factory) this.converterFactories.get(i2)).getClass().getName());
            }
            sb.append(10);
        }
        sb.append("  Tried:");
        int size2 = this.converterFactories.size();
        while (indexOf < size2) {
            sb.append("\n   * ");
            sb.append(((Converter.Factory) this.converterFactories.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> Converter<T, String> stringConverter(Type type, Annotation[] annotationArr) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int size = this.converterFactories.size();
        for (int i = 0; i < size; i++) {
            Converter<T, String> stringConverter = ((Converter.Factory) this.converterFactories.get(i)).stringConverter(type, annotationArr, this);
            if (stringConverter != null) {
                return stringConverter;
            }
        }
        return ToStringConverter.INSTANCE;
    }
}
