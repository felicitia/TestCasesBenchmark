package android.support.v4.media;

import android.media.browse.MediaBrowser.MediaItem;
import android.support.annotation.RequiresApi;
import com.google.a.a.a.a.a.a;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RequiresApi(21)
class ParceledListSliceAdapterApi21 {
    private static Constructor sConstructor;

    ParceledListSliceAdapterApi21() {
    }

    static {
        try {
            sConstructor = Class.forName("android.content.pm.ParceledListSlice").getConstructor(new Class[]{List.class});
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            a.a(e);
        }
    }

    static Object newInstance(List<MediaItem> list) {
        try {
            return sConstructor.newInstance(new Object[]{list});
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            a.a(e);
            return null;
        }
    }
}
