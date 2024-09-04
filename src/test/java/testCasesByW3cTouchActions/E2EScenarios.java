package testCasesByW3cTouchActions;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Epic("SwagLabs Android App")
@Feature("AddToCart")
@Story("Verify AddToCart")
@Listeners(utils.TestNGListners.class)
public class E2EScenarios extends BaseTest {

    @Test
    public void test1() throws IOException, ParseException {

    }
}
