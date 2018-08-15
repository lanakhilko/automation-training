package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;

    private Platform(){}

    public static Platform getInstance(){
        if (instance == null){
            instance = new Platform();
        }
        return instance;
    }

    public Boolean isAndroid(){

        return isPlatform(PLATFORM_ANDROID);
    }

    public Boolean isIOS(){

        return isPlatform(PLATFORM_IOS);
    }

    public AppiumDriver getDriver() throws Exception{

        URL URL = new URL(APPIUM_URL);
        if(this.isAndroid()){
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        } else if(this.isIOS()){
            return new IOSDriver(URL, this.getIosDesiredCapabilities());
        } else{
            throw new Exception("Can't detect driver type. Platform value: " + this.getPlatformVar());
        }
    }

    private DesiredCapabilities getAndroidDesiredCapabilities(){

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("paltformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/skhilko/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIosDesiredCapabilities(){

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("paltformVersion", "11.3");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("app", "/Users/skhilko/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        return capabilities;
    }

    private Boolean isPlatform(String my_platform){

        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    private String getPlatformVar(){

        return System.getenv("PLATFORM");
    }
}
