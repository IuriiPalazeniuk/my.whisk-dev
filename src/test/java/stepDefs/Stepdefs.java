package stepDefs;

import api.ApiOjects.DeletedShoppingListResponse;
import api.ApiOjects.ShoppingList;
import api.ApiOjects.ShoppingListResponse;
import api.RestApiShoppingList;
import api.ScenarioContext;
import api.ScenarioContext.ContextEnum;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static apiAssertions.ShoppingListApi.checkContentIsEmpty;
import static apiAssertions.ShoppingListApi.checkDeletedListResponseMessage;
import static apiAssertions.ShoppingListApi.checkPostedListID;
import static apiAssertions.ShoppingListApi.verifyStatusCode;

public class Stepdefs {

    private RestApiShoppingList rest = new RestApiShoppingList();
    private final String LIST_NAME = "My new List";

    @Given("post new Shopping list")
    public void postNewShoppingList() {
        ShoppingList list = new ShoppingList(LIST_NAME, false);
        rest.postNewShoppingListRequest(list);
        ShoppingListResponse response = ScenarioContext.getContext(ContextEnum.HTTP_RESPONSE);
        ScenarioContext.setContext(ScenarioContext.ContextEnum.POSTED_ID, response.getList().getId());
    }

    @When("get Shopping List by id")
    public void getShoppingListById() {
        String posted_id = ScenarioContext.getContext(ScenarioContext.ContextEnum.POSTED_ID);
        rest.getCreatedShoppingListById(posted_id, ShoppingListResponse.class);
    }

    @Then("verify that response contains necessary id")
    public void verifyThatResponseContainsNecessaryId() {
        checkPostedListID();
    }

    @And("Verify that Shopping list is empty")
    public void verifyThatShoppingListIsEmpty() {
        checkContentIsEmpty();
    }

    @When("delete Shopping list by id")
    public void deleteShoppingListById() {
        ShoppingListResponse response = ScenarioContext.getContext(ContextEnum.HTTP_RESPONSE);
        rest.deleteCreatedShoppingListById(response.getList().getId());
    }

    @Then("verify that code response {int}")
    public void verifyThatCodeResponse(int statusCode) {
        verifyStatusCode(statusCode);
    }

    @And("verify that response message is {string}")
    public void verifyThatResponseMessageIsShoppingListNotFound(String message) {
        checkDeletedListResponseMessage(message);
    }

    @And("get Shopping List by id after deleting Shopping list")
    public void getShoppingListByIdAfterDeletingShoppingList() {
        String posted_id = ScenarioContext.getContext(ScenarioContext.ContextEnum.POSTED_ID);
        rest.getCreatedShoppingListById(posted_id, DeletedShoppingListResponse.class);
    }
}
