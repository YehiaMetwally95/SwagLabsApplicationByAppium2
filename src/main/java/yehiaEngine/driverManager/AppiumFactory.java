package yehiaEngine.driverManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import yehiaEngine.loggers.LogHelper;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import static yehiaEngine.loggers.LogHelper.*;

public class AppiumFactory {
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static AppiumDriverLocalService service;

    private static final String executionType = System.getProperty("appiumExecutionType");
    private static final String appType = System.getProperty("appType");
    private static final String browserName = System.getProperty("browserName");
    private static final String browserVersion = System.getProperty("browserVersion");
    private static final String deviceName = System.getProperty("deviceName");
    private static final String deviceUdid = System.getProperty("deviceUdid");
    private static final String platformVersion = System.getProperty("platformVersion");
    private static final String nativeAutomationDriver = System.getProperty("nativeAutomationDriver");
    private static final String appName = System.getProperty("appName");
    private static final String appPackageName = System.getProperty("appPackageName");
    private static final String appActivity = System.getProperty("appActivity");
    private static final String bundleId = System.getProperty(("bundleId"));
    private static final String appiumURL = System.getProperty("AppiumServerURL");
    private static final String username = System.getProperty("username");
    private static final String accessKey = System.getProperty("accessKey");
    private static final String build = System.getProperty("build");
    private static final String testName = System.getProperty("testName");
    private static final String deviceOrientation= System.getProperty("deviceOrientation");

    /**
     * *********************************  Open / Close App  *************************************
     */
    public static ThreadLocal<AppiumDriver> openApp() {
        try {
            ITestResult result = Reporter.getCurrentTestResult();
            ITestContext context = result.getTestContext();

            if (appType.equalsIgnoreCase("NativeAndroid") || appType.equalsIgnoreCase("WebAppAndroid")) {
                driver.set(new AndroidDriver(getAppiumServerURL(), getAndroidCapabilities()));
                if (!appName.isEmpty())
                    logInfoStep("Starting [" + appName + "] ............");
                else if (!appPackageName.isEmpty())
                    logInfoStep("Starting [" + appPackageName + "] ............");

            } else if (appType.equalsIgnoreCase("NativeIOS") || appType.equalsIgnoreCase("WebAppIOS")) {
                driver.set(new IOSDriver(getAppiumServerURL(), getIOSCapabilities()));
                logInfoStep("Starting [" + appName + "] ............");
            } else {
                logErrorStep("Failed to Start the Application, The Input App Type is Incorrect");
            }

            //Set the Logger Classes with the driver
            context.setAttribute("isolatedAppiumDriver", driver);
        }catch (Exception e){
            if (!appName.isEmpty())
                logErrorStep("Failed to Start [" + appName + "]",e);
            else if (!appPackageName.isEmpty())
                logErrorStep("Failed to Start [" + appPackageName + "]",e);
        }

        return driver;
    }

    public static void closeApp(AppiumDriver driver)
    {
        if (driver instanceof AndroidDriver mydriver){
            String appID = (String)mydriver.getCapabilities().getCapability("appium:appPackage");
            ((InteractsWithApps)driver).terminateApp(appID);
            mydriver.quit();
        }
        else if (driver instanceof IOSDriver mydriver){
            mydriver.quit();
        }

        if (!appName.isEmpty())
        logInfoStep("Terminating [" + appName + "] ............");
        else if (!appPackageName.isEmpty())
        logInfoStep("Terminating [" + appPackageName + "] ............");
    }

    /**
     * *********************************  Switch Between Web/Native View  *************************************
     */
    public static Set<String> getAllAvailableContexts (AppiumDriver driver) {
        // Switch to WebView
        logInfoStep("Available Contexts are: ");
        if (driver instanceof IOSDriver myDriver)
        {
            try {
                Set<String> contexts = myDriver.getContextHandles();
                contexts.forEach(LogHelper::logInfoStep);
                return contexts;

            }catch (Exception e){
                logErrorStep("Failed to Get All Available Contexts",e);
                return null;
            }
        }

        else if (driver instanceof AndroidDriver myDriver)
        {
            try {
                Set<String> contexts = myDriver.getContextHandles();
                contexts.forEach(LogHelper::logInfoStep);
                return contexts;

            }catch (Exception e){
                logErrorStep("Failed to Get All Available Contexts",e);
                return null;
            }
        }
        else
            return null;
    }

    public static void switchToWebView (AppiumDriver driver, String webViewName) {
        if (driver instanceof IOSDriver myDriver)
        {
            try {
                myDriver.context(webViewName);
                logWarningStep("Switched to WebView");
            }catch (Exception e){
                logErrorStep("Failed to switch to Web View",e);
            }
        }

        else if (driver instanceof AndroidDriver myDriver)
        {
            try {
                myDriver.context(webViewName);
                logWarningStep("Switched to WebView");
            }catch (Exception e){
                logErrorStep("Failed to switch to Web View",e);
            }
        }
    }

    public static void switchToNativeView (AppiumDriver driver, String nativeViewName) {
        if (driver instanceof IOSDriver myDriver)
        {
            try {
                myDriver.context(nativeViewName);
                logWarningStep("Switched to Native View");
            }catch (Exception e){
                logErrorStep("Failed to switch to Native View",e);
            }
        }

        else if (driver instanceof AndroidDriver myDriver)
        {
            try {
                myDriver.context(nativeViewName);
                logWarningStep("Switched to Native View");
            }catch (Exception e){
                logErrorStep("Failed to switch to Native View",e);
            }
        }
    }

    /**
     * *********************************  Start / Stop Appium Server *************************************
     */
    public static void startAppiumServerOnWindows () {
        String withoutHttp = appiumURL.split("://",2)[1];

        try{
            service = new AppiumServiceBuilder()
                    .withAppiumJS(new File(System.getenv("APPDATA")+"/npm/node_modules/appium/build/lib/main.js"))
                    .withIPAddress(withoutHttp.split(":",2)[0])
                    .usingPort(Integer.parseInt(withoutHttp.split(":",2)[1]))
                    .withArgument(()->"--allow-cors")
                    .withArgument(()->"--use-drivers",nativeAutomationDriver.toLowerCase())
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
                    //        .withArgument(()->"--use-plugins","relaxed-caps")
                    .withLogFile(new File("AppiumServerLogs"+File.separator+"Server.log"))
                    .build();

            service.start();

            if (service.isRunning())
                logInfoStep("Starting Appium Server.....................");
            else
                throw new Exception();

        }catch (Exception e){
            logErrorStep("Failed to Start Appium Server",e);
        }
    }

    public static void startAppiumServerOnMac () {
        String withoutHttp = appiumURL.split("://",2)[1];

        try {
            service = new AppiumServiceBuilder()
                    .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                    .withIPAddress(withoutHttp.split(":",2)[0])
                    .usingPort(Integer.parseInt(withoutHttp.split(":",2)[1]))
                    .withArgument(()->"--allow-cors")
                    .withArgument(()->"--use-drivers",nativeAutomationDriver.toLowerCase())
                    .withLogFile(new File("AppiumServerLogs"+File.separator+"Server.log"))
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
                    //    .withArgument(()->"--use-plugins","relaxed-caps")
                    .build();

            service.start();
            logInfoStep("Starting Appium Server.....................");

        }catch (Exception e){
            logErrorStep("Failed to Start Appium Server",e);
        }
    }

    public static void stopAppiumServer () {
        try{
            if(service.isRunning())
            {
                service.stop();
                logInfoStep("Stopping Appium Server..................");
            }
        }catch (Exception e){
            logErrorStep("Failed to Stop Appium Server");
        }
    }

    /**
     * *********************************  Switch between Portrait / Landscape  *************************************
     */
    public static void switchToLandscape(AppiumDriver driver){
        DeviceRotation landscape = new DeviceRotation(0,0,90);

        if (driver instanceof IOSDriver myDriver)
        {
            try {
                myDriver.rotate(landscape);
                logWarningStep("Switch to Landscape");
            }catch (Exception e){
                logErrorStep("Failed to switch to Landscape",e);
            }
        }

        else if (driver instanceof AndroidDriver myDriver)
        {
            try {
                myDriver.rotate(landscape);
                logWarningStep("Switched to Landscape");
            }catch (Exception e){
                logErrorStep("Failed to switch to Landscape",e);
            }
        }
    }

    public static void switchToPortrait(AppiumDriver driver){
        DeviceRotation portrait = new DeviceRotation(0,0,0);

        if (driver instanceof IOSDriver myDriver)
        {
            try {
                myDriver.rotate(portrait);
                logWarningStep("Switch to Portrait");
            }catch (Exception e){
                logErrorStep("Failed to switch to Portrait",e);
            }
        }

        else if (driver instanceof AndroidDriver myDriver)
        {
            try {
                myDriver.rotate(portrait);
                logWarningStep("Switched to Portrait");
            }catch (Exception e){
                logErrorStep("Failed to switch to Portrait",e);
            }
        }
    }

    /**
     * *********************************  Set Appium Capabilities  *************************************
     */
    private static UiAutomator2Options getAndroidCapabilities()
    {
        UiAutomator2Options options = new UiAutomator2Options();
        //Generic Capabilities
        options.fullReset();
        options.autoGrantPermissions();
        options.setNewCommandTimeout(Duration.ofSeconds(60));

        //Device Capabilities
        options.setDeviceName(deviceName);
        options.setUdid(deviceUdid);

        //Driver Capabilities
        options.setAutomationName(nativeAutomationDriver);
        options.setCapability("appium:chromedriverAutodownload","true");

        if (executionType.equalsIgnoreCase("Local"))
        {
            //Platform Capabilities
            options.setPlatformName("Android");
            options.setPlatformVersion(platformVersion+".0");

            //Application Capabilities for Native App
            if (appType.equalsIgnoreCase("NativeAndroid"))
            {
                if (!appName.isEmpty() && !appActivity.isEmpty()) {
                    options.setApp(System.getProperty("user.dir")+"/src/main/resources/apps/"+appName);
                    options.setAppActivity(appActivity);
                }

                else if (!appPackageName.isEmpty() && !appActivity.isEmpty()) {
                    options.setAppPackage(appPackageName);
                    options.setAppActivity(appActivity);
                }
            }

            //Browser Capabilities for Web-Based App
            else if (appType.equalsIgnoreCase("WebAppAndroid"))
            {
                options.setAutoWebview(false);
                options.withBrowserName(browserName);
                options.setCapability(CapabilityType.BROWSER_VERSION,browserVersion);
            }
        }

        else if (executionType.equalsIgnoreCase("SauceLabs"))
        {
            //Platform Capabilities
            options.setPlatformName("Android");
            options.setPlatformVersion(platformVersion);

            //Application Capabilities for Native App
            if (appType.equalsIgnoreCase("NativeAndroid"))
            {
                options.setApp("storage:filename="+appName);
                options.setCapability("sauce:options",getSauceLabsCapabilities());
            }

            //Browser Capabilities for Web-Based App
            else if (appType.equalsIgnoreCase("WebAppAndroid"))
            {
                options.setAutoWebview(false);
                options.withBrowserName(browserName);
                options.setCapability(CapabilityType.BROWSER_VERSION,browserVersion);
                options.setCapability("sauce:options",getSauceLabsCapabilities());
            }
        }

        else if (executionType.equalsIgnoreCase("BrowserStack"))
        {
            //Platform Capabilities
            options.setPlatformName("Android");
            options.setPlatformVersion(platformVersion+".0");

            //Application Capabilities for Native App
            if (appType.equalsIgnoreCase("NativeAndroid"))
            {
                options.setApp("bs://"+appName);
                options.setCapability("bstack:options",getBrowserStackCapabilities());
            }

            //Browser Capabilities for Web-Based App
            else if (appType.equalsIgnoreCase("WebAppAndroid"))
            {
                options.setAutoWebview(false);
                options.withBrowserName(browserName);
                options.setCapability(CapabilityType.BROWSER_VERSION,browserVersion);
                options.setCapability("bstack:options",getBrowserStackCapabilities());
            }
        }

        return options;
    }

    private static XCUITestOptions getIOSCapabilities()
    {
        XCUITestOptions options = new XCUITestOptions();
        //Generic Capabilities
   //     options.noReset();
        options.autoAcceptAlerts();
        options.setNewCommandTimeout(Duration.ofSeconds(60));

        //Device Capabilities
        options.setDeviceName(deviceName);
        options.setUdid(deviceUdid);

        //Driver Capabilities
        options.setAutomationName(nativeAutomationDriver);
        options.setCapability("appium:chromedriverAutodownload","true");

        if (executionType.equalsIgnoreCase("Local"))
        {
            //Platform Capabilities
            options.setPlatformName("Ios");
            options.setPlatformVersion(platformVersion+".0");

            //Application Capabilities for Native App
            if (appType.equalsIgnoreCase("NativeIOS"))
            {
                if (!appName.isEmpty()) {
                    options.setApp(System.getProperty("user.dir")+"/src/main/resources/apps/"+appName);
                }

                else if (!bundleId.isEmpty()) {
                    options.setBundleId(bundleId);
                }
            }

            //Browser Capabilities for Web-Based App
            else if (appType.equalsIgnoreCase("WebAppIOS"))
            {
                options.setAutoWebview(false);
                options.withBrowserName(browserName);
                options.setCapability(CapabilityType.BROWSER_VERSION,browserVersion);
            }
        }

        else if (executionType.equalsIgnoreCase("SauceLabs"))
        {
            //Platform Capabilities
            options.setPlatformName("Ios");
            options.setPlatformVersion(platformVersion);

            //Application Capabilities for Native App
            if (appType.equalsIgnoreCase("NativeIOS"))
            {
                options.setApp("storage:filename="+appName);
                options.setCapability("sauce:options",getSauceLabsCapabilities());
            }

            //Browser Capabilities for Web-Based App
            else if (appType.equalsIgnoreCase("WebAppIOS"))
            {
                options.setAutoWebview(false);
                options.withBrowserName(browserName);
                options.setCapability(CapabilityType.BROWSER_VERSION,browserVersion);
                options.setCapability("sauce:options",getSauceLabsCapabilities());
            }
        }

        else if (executionType.equalsIgnoreCase("BrowserStack"))
        {
            //Platform Capabilities
            options.setPlatformName("Ios");
            options.setPlatformVersion(platformVersion+".0");

            //Application Capabilities for Native App
            if (appType.equalsIgnoreCase("NativeIOS"))
            {
                options.setApp("bs://"+appName);
                options.setCapability("bstack:options",getBrowserStackCapabilities());
            }

            //Browser Capabilities for Web-Based App
            else if (appType.equalsIgnoreCase("WebAppIOS"))
            {
                options.setAutoWebview(false);
                options.withBrowserName(browserName);
                options.setCapability(CapabilityType.BROWSER_VERSION,browserVersion);
                options.setCapability("bstack:options",getBrowserStackCapabilities());
            }
        }

        return options;
    }

    private static DesiredCapabilities getSauceLabsCapabilities()
    {
        DesiredCapabilities sauceOptions;
        sauceOptions = new DesiredCapabilities();
        sauceOptions.setCapability("appiumVersion", "latest");
        sauceOptions.setCapability("username", username);
        sauceOptions.setCapability("accessKey", accessKey);
        sauceOptions.setCapability("build", build);
        sauceOptions.setCapability("name", testName);
        sauceOptions.setCapability("deviceOrientation", deviceOrientation);
        return sauceOptions;
    }

    private static DesiredCapabilities getBrowserStackCapabilities()
    {
        DesiredCapabilities browserStackOptions;
        browserStackOptions = new DesiredCapabilities();
        //      browserStackOptions.setCapability("appiumVersion", "2.6.0");
        browserStackOptions.setCapability("userName", username);
        browserStackOptions.setCapability("accessKey", accessKey);
        browserStackOptions.setCapability("buildName", build);
        browserStackOptions.setCapability("sessionName", testName);
        browserStackOptions.setCapability("deviceOrientation", deviceOrientation);
        browserStackOptions.setCapability("debug", "true");
        browserStackOptions.setCapability("networkLogs", "true");

        return browserStackOptions;
    }

    private static URL getAppiumServerURL () throws MalformedURLException, URISyntaxException {
        return new URI(appiumURL).toURL();
    }


    /**
     * *********************************  Thread Local Driver  *************************************
     */
    public static AppiumDriver getDriver(ThreadLocal<AppiumDriver> isolatedDriver)
    {
        return isolatedDriver.get();
    }

    public static void isolateWebDriver(AppiumDriver driver , ThreadLocal<AppiumDriver> isolatedDriver)
    {
        isolatedDriver.set(driver);
    }

    public static void removeIsolatedDriver (ThreadLocal<AppiumDriver> isolatedDriver)
    {
        isolatedDriver.remove();
    }

}
