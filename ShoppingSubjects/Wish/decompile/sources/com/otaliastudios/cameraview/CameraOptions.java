package com.otaliastudios.cameraview;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CameraOptions {
    private boolean autoFocusSupported;
    private float exposureCorrectionMaxValue;
    private float exposureCorrectionMinValue;
    private boolean exposureCorrectionSupported;
    private Set<Facing> supportedFacing = new HashSet(2);
    private Set<Flash> supportedFlash = new HashSet(4);
    private Set<Hdr> supportedHdr = new HashSet(2);
    private Set<AspectRatio> supportedPictureAspectRatio = new HashSet(4);
    private Set<Size> supportedPictureSizes = new HashSet(15);
    private Set<WhiteBalance> supportedWhiteBalance = new HashSet(5);
    private boolean videoSnapshotSupported;
    private boolean zoomSupported;

    CameraOptions(Parameters parameters, boolean z) {
        Mapper1 mapper1 = new Mapper1();
        CameraInfo cameraInfo = new CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        boolean z2 = false;
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            Facing unmapFacing = mapper1.unmapFacing(Integer.valueOf(cameraInfo.facing));
            if (unmapFacing != null) {
                this.supportedFacing.add(unmapFacing);
            }
        }
        List<String> supportedWhiteBalance2 = parameters.getSupportedWhiteBalance();
        if (supportedWhiteBalance2 != null) {
            for (String unmapWhiteBalance : supportedWhiteBalance2) {
                WhiteBalance unmapWhiteBalance2 = mapper1.unmapWhiteBalance(unmapWhiteBalance);
                if (unmapWhiteBalance2 != null) {
                    this.supportedWhiteBalance.add(unmapWhiteBalance2);
                }
            }
        }
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        if (supportedFlashModes != null) {
            for (String unmapFlash : supportedFlashModes) {
                Flash unmapFlash2 = mapper1.unmapFlash(unmapFlash);
                if (unmapFlash2 != null) {
                    this.supportedFlash.add(unmapFlash2);
                }
            }
        }
        List<String> supportedSceneModes = parameters.getSupportedSceneModes();
        if (supportedSceneModes != null) {
            for (String unmapHdr : supportedSceneModes) {
                Hdr unmapHdr2 = mapper1.unmapHdr(unmapHdr);
                if (unmapHdr2 != null) {
                    this.supportedHdr.add(unmapHdr2);
                }
            }
        }
        this.zoomSupported = parameters.isZoomSupported();
        this.videoSnapshotSupported = parameters.isVideoSnapshotSupported();
        this.autoFocusSupported = parameters.getSupportedFocusModes().contains("auto");
        float exposureCompensationStep = parameters.getExposureCompensationStep();
        this.exposureCorrectionMinValue = ((float) parameters.getMinExposureCompensation()) * exposureCompensationStep;
        this.exposureCorrectionMaxValue = ((float) parameters.getMaxExposureCompensation()) * exposureCompensationStep;
        if (!(parameters.getMinExposureCompensation() == 0 && parameters.getMaxExposureCompensation() == 0)) {
            z2 = true;
        }
        this.exposureCorrectionSupported = z2;
        for (Size size : parameters.getSupportedPictureSizes()) {
            int i2 = z ? size.height : size.width;
            int i3 = z ? size.width : size.height;
            this.supportedPictureSizes.add(new Size(i2, i3));
            this.supportedPictureAspectRatio.add(AspectRatio.of(i2, i3));
        }
    }

    public boolean supports(Control control) {
        return getSupportedControls(control.getClass()).contains(control);
    }

    public <T extends Control> Collection<T> getSupportedControls(Class<T> cls) {
        if (cls.equals(Audio.class)) {
            return Arrays.asList(Audio.values());
        }
        if (cls.equals(Facing.class)) {
            return getSupportedFacing();
        }
        if (cls.equals(Flash.class)) {
            return getSupportedFlash();
        }
        if (cls.equals(Grid.class)) {
            return Arrays.asList(Grid.values());
        }
        if (cls.equals(Hdr.class)) {
            return getSupportedHdr();
        }
        if (cls.equals(SessionType.class)) {
            return Arrays.asList(SessionType.values());
        }
        if (cls.equals(VideoQuality.class)) {
            return Arrays.asList(VideoQuality.values());
        }
        if (cls.equals(WhiteBalance.class)) {
            return getSupportedWhiteBalance();
        }
        return Collections.emptyList();
    }

    public Set<Size> getSupportedPictureSizes() {
        return Collections.unmodifiableSet(this.supportedPictureSizes);
    }

    public Set<Facing> getSupportedFacing() {
        return Collections.unmodifiableSet(this.supportedFacing);
    }

    public Set<Flash> getSupportedFlash() {
        return Collections.unmodifiableSet(this.supportedFlash);
    }

    public Set<WhiteBalance> getSupportedWhiteBalance() {
        return Collections.unmodifiableSet(this.supportedWhiteBalance);
    }

    public Set<Hdr> getSupportedHdr() {
        return Collections.unmodifiableSet(this.supportedHdr);
    }

    public boolean isZoomSupported() {
        return this.zoomSupported;
    }

    public boolean isVideoSnapshotSupported() {
        return this.videoSnapshotSupported;
    }

    public boolean isAutoFocusSupported() {
        return this.autoFocusSupported;
    }

    public boolean isExposureCorrectionSupported() {
        return this.exposureCorrectionSupported;
    }

    public float getExposureCorrectionMinValue() {
        return this.exposureCorrectionMinValue;
    }

    public float getExposureCorrectionMaxValue() {
        return this.exposureCorrectionMaxValue;
    }
}
