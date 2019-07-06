package com.otaliastudios.cameraview;

import android.os.Build.VERSION;
import java.util.HashMap;

abstract class Mapper {

    static class Mapper1 extends Mapper {
        private static final HashMap<Facing, Integer> FACING = new HashMap<>();
        private static final HashMap<Flash, String> FLASH = new HashMap<>();
        private static final HashMap<Hdr, String> HDR = new HashMap<>();
        private static final HashMap<WhiteBalance, String> WB = new HashMap<>();

        Mapper1() {
        }

        static {
            FLASH.put(Flash.OFF, "off");
            FLASH.put(Flash.ON, "on");
            FLASH.put(Flash.AUTO, "auto");
            FLASH.put(Flash.TORCH, "torch");
            FACING.put(Facing.BACK, Integer.valueOf(0));
            FACING.put(Facing.FRONT, Integer.valueOf(1));
            WB.put(WhiteBalance.AUTO, "auto");
            WB.put(WhiteBalance.INCANDESCENT, "incandescent");
            WB.put(WhiteBalance.FLUORESCENT, "fluorescent");
            WB.put(WhiteBalance.DAYLIGHT, "daylight");
            WB.put(WhiteBalance.CLOUDY, "cloudy-daylight");
            HDR.put(Hdr.OFF, "auto");
            if (VERSION.SDK_INT >= 17) {
                HDR.put(Hdr.ON, "hdr");
            } else {
                HDR.put(Hdr.ON, "hdr");
            }
        }

        /* access modifiers changed from: 0000 */
        public <T> T map(Flash flash) {
            return FLASH.get(flash);
        }

        /* access modifiers changed from: 0000 */
        public <T> T map(Facing facing) {
            return FACING.get(facing);
        }

        /* access modifiers changed from: 0000 */
        public <T> T map(WhiteBalance whiteBalance) {
            return WB.get(whiteBalance);
        }

        /* access modifiers changed from: 0000 */
        public <T> T map(Hdr hdr) {
            return HDR.get(hdr);
        }

        private <T> T reverseLookup(HashMap<T, ?> hashMap, Object obj) {
            for (T next : hashMap.keySet()) {
                if (hashMap.get(next).equals(obj)) {
                    return next;
                }
            }
            return null;
        }

        /* access modifiers changed from: 0000 */
        public <T> Flash unmapFlash(T t) {
            return (Flash) reverseLookup(FLASH, t);
        }

        /* access modifiers changed from: 0000 */
        public <T> Facing unmapFacing(T t) {
            return (Facing) reverseLookup(FACING, t);
        }

        /* access modifiers changed from: 0000 */
        public <T> WhiteBalance unmapWhiteBalance(T t) {
            return (WhiteBalance) reverseLookup(WB, t);
        }

        /* access modifiers changed from: 0000 */
        public <T> Hdr unmapHdr(T t) {
            return (Hdr) reverseLookup(HDR, t);
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract <T> T map(Facing facing);

    /* access modifiers changed from: 0000 */
    public abstract <T> T map(Flash flash);

    /* access modifiers changed from: 0000 */
    public abstract <T> T map(Hdr hdr);

    /* access modifiers changed from: 0000 */
    public abstract <T> T map(WhiteBalance whiteBalance);

    /* access modifiers changed from: 0000 */
    public abstract <T> Facing unmapFacing(T t);

    /* access modifiers changed from: 0000 */
    public abstract <T> Flash unmapFlash(T t);

    /* access modifiers changed from: 0000 */
    public abstract <T> Hdr unmapHdr(T t);

    /* access modifiers changed from: 0000 */
    public abstract <T> WhiteBalance unmapWhiteBalance(T t);

    Mapper() {
    }
}
