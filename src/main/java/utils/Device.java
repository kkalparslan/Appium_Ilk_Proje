package utils;

public enum Device {

    PIXEL2("emulator-5554",
            "11",
            "Pixel2",
            "Android"),
    HUAWEI(
            "NNR6R20618007244",
            "12",
            "Huawei-ec",
            "Android");

    public String udid;
    public String version;
    public String deviceName;
    public String platformName;

    Device(String udid, String version, String deviceName, String platformName) {
        this.udid = udid;
        this.version = version;
        this.deviceName = deviceName;
        this.platformName = platformName;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
