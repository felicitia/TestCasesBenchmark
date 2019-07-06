package android.arch.persistence.room;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: RoomMasterTable */
public class d {
    public static String a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"");
        sb.append(str);
        sb.append("\")");
        return sb.toString();
    }
}
