package rx_activity_result2;

import android.content.Intent;
import android.support.annotation.Nullable;
import java.io.Serializable;

interface OnResult extends Serializable {
    void error(Throwable th);

    void response(int i, int i2, @Nullable Intent intent);
}
