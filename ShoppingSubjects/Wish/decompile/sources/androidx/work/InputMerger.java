package androidx.work;

import android.util.Log;
import java.util.List;

public abstract class InputMerger {
    public abstract Data merge(List<Data> list);

    public static InputMerger fromClassName(String str) {
        try {
            return (InputMerger) Class.forName(str).newInstance();
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Trouble instantiating + ");
            sb.append(str);
            Log.e("InputMerger", sb.toString(), e);
            return null;
        }
    }
}
