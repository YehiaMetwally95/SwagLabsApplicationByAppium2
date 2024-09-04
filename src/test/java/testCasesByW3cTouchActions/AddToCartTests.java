package testCasesByW3cTouchActions;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pagesByW3cTouchActions.LoginPage;
import utils.JDBCManager;
import utils.JsonManager;

import static utils.W3CTouchActions.Direction.*;
import java.io.IOException;
import java.sql.SQLException;

@Epic("SwagLabs Android App")
@Feature("Cart")
@Story("Verify Add Products To Cart")
@Listeners(utils.TestNGListners.class)
public class AddToCartTests extends BaseTest {

    //Variables
    JsonManager json1,json2;

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

        json1 = new JsonManager(jsonFilePath);
        JSONObject obj1 = JDBCManager.setJsonObjectFromDBForNestedArrayOfJsonObjects(dbQuery1,jsonKeysForUsers,jsonMainKeyForUsers);
        JSONObject obj2 = JDBCManager.setJsonObjectFromDBForNestedArrayOfJsonObjects(dbQuery2,jsonKeysForMassages,jsonMainKeyForMassages);
        JSONObject[] obj = {obj1, obj2};
        JDBCManager.setJsonFileFromMultipleJsonObjects(obj,jsonFilePath);
    }

    @BeforeClass
    public void prepareTestDataForProducts() throws IOException, SQLException {

        String dbQuery = "SELECT Id,Name,Price,Description FROM yehiadb1.swaglabsproducts Order By Id Asc";
        String jsonFilePath = "src/test/resources/TestDataJsonFiles/Products.json";

        //JsonKeys shall be filled by the same order of table columns of database query
        String[] jsonKeys = {"Id","Name","Price","Description"};

        //In Case of writing one JsonMainKey for all records, the Records will represent an array of Json objects
        //In Case of writing JsonMainKey for every record, Each Record will represent an object value for the corresponding JsonMainKey,In this case JsonMainKeys shall be filled by the same order of table rows on database
        String jsonMainKey = "Products";

        json2 = new JsonManager(jsonFilePath);
        JDBCManager.setJsonFileFromDBForNestedArrayOfJsonObjects(dbQuery,jsonFilePath,jsonKeys,jsonMainKey);
    }

    /*@Test
    public void addProductToCartByButton() throws IOException, ParseException {
        new LoginPage(driver)
                .setUsername(json1.getValueFromArray("Users[0].Username"))
                .setPassword(json1.getValueFromArray("Users[0].Password"))
                .clickLoginButtonSuccess()
                .assertHomePageIsOpened()

                .scrollToProduct(json2.getValueFromArray("Products[1].Name"),DOWN)
                .addProductToCartByButton(json2.getValueFromArray("Products[1].Name"))

                .openCartPageFromHeader()
                .assertProductIsAddedToCart(json2.getValueFromArray("Products[1].Name"));
    }

    @Test
    public void addProductToCartByDragAndDrop() throws IOException, ParseException {
        new LoginPage(driver)
                .setUsername(json1.getValueFromArray("Users[0].Username"))
                .setPassword(json1.getValueFromArray("Users[0].Password"))
                .clickLoginButtonSuccess()
                .assertHomePageIsOpened()

                .scrollToProduct(json2.getValueFromArray("Products[2].Name"), DOWN)
                .addProductToCartByDragAndDrop(json2.getValueFromArray("Products[2].Name"))

                .openCartPageFromHeader()
                .assertProductIsAddedToCart(json2.getValueFromArray("Products[2].Name"));
    }*/

    @Test
    public void addMultipleProductsToCart() throws IOException, ParseException {
        new LoginPage(driver)
                .setUsername(json1.getValueFromArray("Users[0].Username"))
                .setPassword(json1.getValueFromArray("Users[0].Password"))
                .clickLoginButtonSuccess()
                .assertHomePageIsOpened()

                .addProductToCartByButton(json2.getValueFromArray("Products[0].Name"))

                .scrollToProduct(json2.getValueFromArray("Products[1].Name"))
                .addProductToCartByDragAndDrop(json2.getValueFromArray("Products[1].Name"))
                .scrollToProduct(json2.getValueFromArray("Products[2].Name"))
                .addProductToCartByDragAndDrop(json2.getValueFromArray("Products[2].Name"))

                .openCartPageFromHeader()
                .scrollToProduct(json2.getValueFromArray("Products[2].Name"),DOWN)
                .removeProductFromCartBySwipe(json2.getValueFromArray("Products[2].Name"))

                .assertProductIsAddedToCart(json2.getValueFromArray("Products[2].Name"));
    }

    /*@Test
    public void addProductToCartFromInsideProductDetailsPage() throws IOException, ParseException {
        new LoginPage(driver)
                .setUsername(json1.getValueFromArray("Users[0].Username"))
                .setPassword(json1.getValueFromArray("Users[0].Password"))
                .clickLoginButtonSuccess()
                .assertHomePageIsOpened()

                .scrollToProduct(json2.getValueFromArray("Products[1].Name"),DOWN)
                .openProductDetailsPage(json2.getValueFromArray("Products[1].Name"))
                .zoomInProductPicture(100)
                .zoomOutProductPicture(100)
                .addProductToCart()

                .openCartPageFromHeader()
                .assertProductIsAddedToCart(json2.getValueFromArray("Products[1].Name"));
    }*/
}
