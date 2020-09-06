package apiAssertions;

import api.ApiOjects.DeletedShoppingListResponse;
import api.ApiOjects.ShoppingListResponse;
import api.ScenarioContext;
import org.junit.Assert;

import static api.ScenarioContext.ContextEnum.HTTP_RESPONSE_STATUS_CODE;
import static org.junit.Assert.assertEquals;

public class ShoppingListApi {

    public static void checkPostedListID() {
        ShoppingListResponse list = ScenarioContext.getContext(ScenarioContext.ContextEnum.HTTP_RESPONSE);
        String post_id = ScenarioContext.getContext(ScenarioContext.ContextEnum.POSTED_ID);
        String get_id = list.getList().getId();
        Assert.assertEquals("Get request returned wrong id number", post_id, get_id);
    }

    public static void checkContentIsEmpty(){
        ShoppingListResponse list = ScenarioContext.getContext(ScenarioContext.ContextEnum.HTTP_RESPONSE);
        Assert.assertTrue("Content is not null", list.getContent().getItems() == null);
    }

    public static void verifyStatusCode(int statusCode){
        int code = ScenarioContext.getContext(HTTP_RESPONSE_STATUS_CODE);
        assertEquals(String.format("Wrong status code! Current code is: %s", code), code, statusCode);
    }

    public static void checkDeletedListResponseMessage(String expectedMessage){
        DeletedShoppingListResponse messageR = ScenarioContext.getContext(ScenarioContext.ContextEnum.HTTP_RESPONSE);
        String actualMessage = messageR.getCode();
        Assert.assertEquals("Response message code is wrong!", actualMessage, expectedMessage);
    }
}
