package baseTest;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import yehiaEngine.elementActions.NativeAndroidActions;
import yehiaEngine.elementActions.W3CFingerActions;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import static yehiaEngine.driverManager.AppiumFactory.getDriver;

public class Test extends BaseTest {

    @org.testng.annotations.Test
    public void test1() throws URISyntaxException, MalformedURLException {

        By pic = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.sec.android.gallery3d:id/deco_view_layout\").instance(0)");
        By pic2 = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.sec.android.gallery3d:id/photo_view\")");

        NativeAndroidActions nativeAction = new NativeAndroidActions(getDriver(isolatedDriver));
        W3CFingerActions fingerAction = new W3CFingerActions(getDriver(isolatedDriver));

        nativeAction.longTap(pic);
        nativeAction.pressOnAndroidKey(AndroidKey.BACK);
        nativeAction.tap(pic);
        nativeAction.doubleTap(pic2);
        nativeAction.doubleTap(pic2);

        for (int i=0;i<5;i++){
            nativeAction.singleSwipe(pic2, NativeAndroidActions.Direction.LEFT);
        }

        for (int i=0;i<5;i++){
            nativeAction.singleSwipe(pic2, NativeAndroidActions.Direction.RIGHT);
        }
    }
}
