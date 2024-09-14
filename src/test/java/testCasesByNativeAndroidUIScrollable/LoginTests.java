package testCasesByNativeAndroidUIScrollable;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pagesByNativeAndroidUIScrollable.LoginPage;
import utils.JDBCManager;
import utils.JsonManager;
import utils.RandomDataGenerator;

import java.io.IOException;
import java.sql.SQLException;

@Epic("SwagLabs Android App")
@Feature("User Login")
@Story("Verify User Login")
@Listeners(utils.TestNGListners.class)
public class LoginTests extends BaseTest {

    //Variables
    JsonManager json;

    @BeforeClass
    public void prepareTestDataForLogin() throws IOException, SQLException {

        String dbQuery1 = "SELECT Id,Username,Password,Status FROM yehiadb1.swaglabsusers Order by Id Asc";
        String dbQuery2 = "SELECT Id,Name,Content FROM yehiadb1.swaglabsmassages Order by Id Asc";
        String jsonFilePath = "src/test/resources/TestDataJsonFiles/LoginData.json";

        //JsonKeys shall be filled by the same order of table columns of database query
           String[] jsonKeysForUsers = {"Id","Username","Password","Status"};
           String[] jsonKeysForMassages = {"Id","Name","Content"};

        //In Case of writing one JsonMainKey for all records, the Records will represent an array of Json objects
        //In Case of writing JsonMainKey for every record, Each Record will represent an object value for the corresponding JsonMainKey,In this case JsonMainKeys shall be filled by the same order of table rows on database
           String jsonMainKeyForUsers = "Users";
           String jsonMainKeyForMassages = "Massages";

        json = new JsonManager(jsonFilePath);
        JSONObject obj1 = JDBCManager.setJsonObjectFromDBForNestedArrayOfJsonObjects(dbQuery1,jsonKeysForUsers,jsonMainKeyForUsers);
        JSONObject obj2 = JDBCManager.setJsonObjectFromDBForNestedArrayOfJsonObjects(dbQuery2,jsonKeysForMassages,jsonMainKeyForMassages);
        JSONObject[] obj = {obj1, obj2};
        JDBCManager.setJsonFileFromMultipleJsonObjects(obj,jsonFilePath);
    }

    @Test
    public void successfulLogin() throws IOException, ParseException {
        new LoginPage(driver)
                .setUsername(json.getValueFromArray("Users[0].Username"))
                .setPassword(json.getValueFromArray("Users[0].Password"))
                .clickLoginButtonSuccess()
                .assertHomePageIsOpened();
    }

    @Test
    public void invalidUserLogin() throws IOException, ParseException {
        new LoginPage(driver)
                .setUsername(RandomDataGenerator.generateName())
                .setPassword(RandomDataGenerator.generateStrongPassword())
                .clickLoginButtonFailure()
                .assertErrorMassageForInvalidCredentials(json.getValueFromArray("Massages[0].Content"));
    }

    @Test
    public void lockedUserLogin() throws IOException, ParseException {
        new LoginPage(driver)
                .setUsername(json.getValueFromArray("Users[2].Username"))
                .setPassword(json.getValueFromArray("Users[2].Password"))
                .clickLoginButtonFailure()
                .assertErrorMassageForLockedUser(json.getValueFromArray("Massages[1].Content"));
    }
}
