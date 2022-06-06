package Ivin.HW;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ShoppingListTest extends MainTest {

    @Test
    @Tag("Positive")
    @DisplayName("POST. Add to Shopping List")
    void ShoppingListTest() throws IOException {
        RequestShoppingList requestShoppingList = new RequestShoppingList();
        requestShoppingList.setItem("1 package baking powder");
        requestShoppingList.setAisle("Baking");
        requestShoppingList.setParse(true);
        ResponseShoppingList response = given()
                .queryParam("hash", getApiHash())
                .spec(requestSpecification)
                .body(requestShoppingList)
                .when()
                .post(getURL() + "/mealplanner/" + getUserName() + "/shopping-list/items")
                .then()
                .spec(responseSpecification)
                .extract()
                .body()
                .as(ResponseShoppingList.class);
        System.out.println(response.getCost());
        assertThat(response.getCost(), notNullValue());
        assertThat(response.getCost().doubleValue(), greaterThan(0.00));
        tearDown(getURL() + "/mealplanner/" + getUserName() + "/shopping-list/items/" + response.getId());
    }
}
