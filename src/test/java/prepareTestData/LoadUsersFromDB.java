package prepareTestData;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import yehiaEngine.managers.JDBCManager;

import java.io.IOException;
import java.sql.SQLException;

public class LoadUsersFromDB {
    static String jsonFilePathForLogin = "src/test/resources/TestDataJsonFiles/LoginTestData.json";
    static String jsonFilePathForAddToCart = "src/test/resources/TestDataJsonFiles/AddToCartTestData.json";
    static String jsonFilePathForRemoveFromCart = "src/test/resources/TestDataJsonFiles/RemoveFromCartTestData.json";
    static String jsonFilePathForCheckout = "src/test/resources/TestDataJsonFiles/CheckoutCartTestData.json";

    @Description("Load The Latest User from DB and Update them into Test Data Json Files")
    @Test
    @Step("Load Users from DB")
    public static void loadUsersFromDB() throws SQLException, IOException {
        String dbQuery = "SELECT Id,Username,Password,Status FROM swaglabs.users Order By Id Asc;";

        //JsonKeys shall be filled by the same order of table columns of database query
        String[] jsonKeys = {"Id","Username","Password","Status"};


        //In Case of writing one JsonMainKey for all records, the Records will represent an array of Json objects
        //In Case of writing JsonMainKey for every record, Each Record will represent an object value for the corresponding JsonMainKey,In this case JsonMainKeys shall be filled by the same order of table rows on database
        String jsonMainKey = "Users";

        JDBCManager.setJsonFileFromDBAsNestedArrayOfJsonObjects(dbQuery, jsonFilePathForLogin, jsonKeys, jsonMainKey);
        JDBCManager.setJsonFileFromDBAsNestedArrayOfJsonObjects(dbQuery, jsonFilePathForAddToCart, jsonKeys, jsonMainKey);
        JDBCManager.setJsonFileFromDBAsNestedArrayOfJsonObjects(dbQuery, jsonFilePathForRemoveFromCart, jsonKeys, jsonMainKey);
        JDBCManager.setJsonFileFromDBAsNestedArrayOfJsonObjects(dbQuery, jsonFilePathForCheckout, jsonKeys, jsonMainKey);
    }
}
