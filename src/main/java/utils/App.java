package utils;

public enum App {

    APIDEMO("com.touchboarder.android.api.demos","com.touchboarder.androidapidemos.MainActivity", ""),
    // 5. satır bilgileri benim bilgiler
    CALCULATOR("com.sec.android.app.popupcalculator","Calculator", "") //hocanın verileri
    ;

    public String appPackage;
    public String appActivity;
    public String apk;


    App(String appPackage, String appActivity, String apk) {
        this.appPackage = appPackage;
        this.appActivity = appActivity;
        this.apk = apk;
    }


}
