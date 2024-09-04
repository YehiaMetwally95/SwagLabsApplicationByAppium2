package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AndroidDriverManager {
    static AppiumDriver driver;

    public static AppiumDriver openApp() throws MalformedURLException {
        return driver = new AndroidDriver(getAppiumServerURL(),getAppiumCapabilities());
    }

    public static DesiredCapabilities getAppiumCapabilities()
    {
        DesiredCapabilities cap = new DesiredCapabilities();
            //Device Capabilities
        cap.setCapability("appium:deviceName","SM-M515F/DSN");
        cap.setCapability("appium:udid","RF8NA1WZTCE");

            //Platform Capabilities
        cap.setCapability("appium:platformName","Android");
        cap.setCapability("appium:platformVersion","12");

            //Native Driver Capabilities
        cap.setCapability("appium:automationName","UiAutomator2");

            //Application Capabilities for Native App
        //cap.setCapability("appium:appPackage","com.swaglabsmobileapp");
        cap.setCapability("appium:app", System.getProperty("user.dir")+"\\src\\test\\resources\\Apps\\ApiDemos-debug.apk");
        //cap.setCapability("appium:appActivity","com.swaglabsmobileapp.MainActivity");
            //Browser Capabilities for Web-Based App
        //cap.setCapability(CapabilityType.BROWSER_NAME,"Chrome");
        return cap;
    }

    public static URL getAppiumServerURL () throws MalformedURLException {
        URL url = new URL("http://127.0.0.1:4723");
        return url;
    }

    public static void closeApp()
    {
        String appID =
                (String)driver.getCapabilities().getCapability("appium:appPackage");
        ((InteractsWithApps)driver).terminateApp(appID);
    }


}
