package tests.enum_deneme;

public enum Enum1 {

    MYHUWAEI("NNR6R20618007244","12","Huawei-ec","Android");

    String udid;
    String version;
    String deviceName;
    String platformName;

    Enum1(String udid, String version, String deviceName, String platformName) {
        this.udid = udid;
        this.version = version;
        this.deviceName = deviceName;
        this.platformName = platformName;
    }
}
