package baseTest;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import prepareTestData.LoadProductsFromDB;
import prepareTestData.LoadUsersFromDB;
import yehiaEngine.driverManager.AppiumFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import static yehiaEngine.driverManager.AppiumFactory.getDriver;

public class BaseTest {

    public ThreadLocal<AppiumDriver> isolatedDriver;

    @BeforeTest
    // Sync with Database to Load Latest Products and Users and Update Test Data Json Files
    public void prepareTestData() throws SQLException, IOException {
        if (System.getProperty("syncWithDB").equalsIgnoreCase("true")) {
            LoadProductsFromDB.loadProductsFromDB();
            LoadUsersFromDB.loadUsersFromDB();
        }
    }

    @BeforeMethod
    public void setUpAndOpenApp() throws MalformedURLException {
        //Open App
        isolatedDriver = AppiumFactory.openApp();
    }

    @AfterMethod
    public void closeApp(){
        //Close App after every test
        AppiumFactory.closeApp(getDriver(isolatedDriver));

        //Remove the Isolated Driver from Memory
        AppiumFactory.removeIsolatedDriver(isolatedDriver);
    }
}
