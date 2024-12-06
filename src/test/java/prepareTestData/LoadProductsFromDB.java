package prepareTestData;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import yehiaEngine.managers.JDBCManager;

import java.io.IOException;
import java.sql.SQLException;

public class LoadProductsFromDB {
    static String jsonFilePathForLogin = "src/test/resources/TestDataJsonFiles/LoginTestData.json";
    static String jsonFilePathForAddToCart = "src/test/resources/TestDataJsonFiles/AddToCartTestData.json";
    static String jsonFilePathForRemoveFromCart = "src/test/resources/TestDataJsonFiles/RemoveFromCartTestData.json";
    static String jsonFilePathForCheckout = "src/test/resources/TestDataJsonFiles/CheckOutCartTestData.json";

    @Description("Load The Latest Products from DB and Update them into Test Data Json Files")
    @Test
    @Step("Load Products from DB")
    public static void loadProductsFromDB() throws SQLException, IOException {
        String dbQuery = "SELECT Id,Name,Price,Quantity,Description FROM swaglabs.products Order by Id Asc;";

        //JsonKeys shall be filled by the same order of table columns of database query
        String[] jsonKeys = {"Id","Name","Price","Quantity","Description"};

        //In Case of writing one JsonMainKey for all records, the Records will represent an array of Json objects
        //In Case of writing JsonMainKey for every record, Each Record will represent an object value for the corresponding JsonMainKey,In this case JsonMainKeys shall be filled by the same order of table rows on database
        String jsonMainKey = "Products";

        JDBCManager.setJsonFileFromDBAsNestedArrayOfJsonObjects(dbQuery, jsonFilePathForLogin, jsonKeys, jsonMainKey);
        JDBCManager.setJsonFileFromDBAsNestedArrayOfJsonObjects(dbQuery, jsonFilePathForAddToCart, jsonKeys, jsonMainKey);
        JDBCManager.setJsonFileFromDBAsNestedArrayOfJsonObjects(dbQuery, jsonFilePathForRemoveFromCart, jsonKeys, jsonMainKey);
        JDBCManager.setJsonFileFromDBAsNestedArrayOfJsonObjects(dbQuery, jsonFilePathForCheckout, jsonKeys, jsonMainKey);
    }
}
