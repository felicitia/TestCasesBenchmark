package android.arch.persistence.room;

public class RoomMasterTable {
    public static String createInsertQuery(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"");
        sb.append(str);
        sb.append("\")");
        return sb.toString();
    }
}
