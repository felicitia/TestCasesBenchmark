package android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DataBindingUtil {
    private static DataBindingComponent sDefaultComponent;
    private static DataBinderMapper sMapper = new DataBinderMapperImpl();

    public static DataBindingComponent getDefaultComponent() {
        return sDefaultComponent;
    }

    public static <T extends ViewDataBinding> T inflate(LayoutInflater layoutInflater, int i, ViewGroup viewGroup, boolean z, DataBindingComponent dataBindingComponent) {
        int i2 = 0;
        boolean z2 = viewGroup != null && z;
        if (z2) {
            i2 = viewGroup.getChildCount();
        }
        View inflate = layoutInflater.inflate(i, viewGroup, z);
        if (z2) {
            return bindToAddedViews(dataBindingComponent, viewGroup, i2, i);
        }
        return bind(dataBindingComponent, inflate, i);
    }

    public static <T extends ViewDataBinding> T bind(View view) {
        return bind(view, sDefaultComponent);
    }

    public static <T extends ViewDataBinding> T bind(View view, DataBindingComponent dataBindingComponent) {
        T binding = getBinding(view);
        if (binding != null) {
            return binding;
        }
        Object tag = view.getTag();
        if (!(tag instanceof String)) {
            throw new IllegalArgumentException("View is not a binding layout");
        }
        int layoutId = sMapper.getLayoutId((String) tag);
        if (layoutId != 0) {
            return sMapper.getDataBinder(dataBindingComponent, view, layoutId);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("View is not a binding layout. Tag: ");
        sb.append(tag);
        throw new IllegalArgumentException(sb.toString());
    }

    static <T extends ViewDataBinding> T bind(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        return sMapper.getDataBinder(dataBindingComponent, viewArr, i);
    }

    static <T extends ViewDataBinding> T bind(DataBindingComponent dataBindingComponent, View view, int i) {
        return sMapper.getDataBinder(dataBindingComponent, view, i);
    }

    public static <T extends ViewDataBinding> T getBinding(View view) {
        return ViewDataBinding.getBinding(view);
    }

    private static <T extends ViewDataBinding> T bindToAddedViews(DataBindingComponent dataBindingComponent, ViewGroup viewGroup, int i, int i2) {
        int childCount = viewGroup.getChildCount();
        int i3 = childCount - i;
        if (i3 == 1) {
            return bind(dataBindingComponent, viewGroup.getChildAt(childCount - 1), i2);
        }
        View[] viewArr = new View[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            viewArr[i4] = viewGroup.getChildAt(i4 + i);
        }
        return bind(dataBindingComponent, viewArr, i2);
    }
}
