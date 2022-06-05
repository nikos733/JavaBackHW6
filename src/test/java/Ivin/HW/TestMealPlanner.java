package Ivin.HW;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class TestMealPlanner {

    Properties properties = new Properties();

    long date = LocalDate.now().toEpochDay();

    String addID;
    String delID;

    @Test
    void MealTestDel() {
        try {
            addID = given()
                    .queryParam("hash", TestVariables.hash())
                    .queryParam("apiKey", TestVariables.apiKey())
                    .queryParam("username", TestVariables.username())
                    .body("{\n"
                            + " \"date\": " + date + ",\n"
                            + " \"slot\": 1,\n"
                            + " \"position\": 1,\n"
                            + " \"type\": \"RECIPE\",\n"
                            + " \"value\": {\n"
                            + " \"id\": 644953,\n"
                            + " \"servings\":2,\n"
                            + " \"title\": \"Goat Cheese Pesto Pizza\", \n"
                            + " \"imageType\": \"jpg\", \n "
                            + " }\n"
                            + "}")
                    .when()
                    .post("https://api.spoonacular.com/mealplanner/" + TestVariables.username() + "/items")
                    .then()
                    .statusCode(200)
                    .extract()
                    .jsonPath()
                    .get("id")
                    .toString();
        } finally {
            AddDown();
        }
    }

    @Test
    void ShoppingListTestDel() {

        try {
            delID = given()
                    .queryParam("hash", TestVariables.hash())
                    .queryParam("apiKey", TestVariables.apiKey())
                    .queryParam("username", TestVariables.username())
                    .body("{\n"
                            + " \"item\": \"garlic\",\n"
                            + "\"parse\": true,\n "
                            + "}")
                    .when()
                    .post("https://api.spoonacular.com/mealplanner/" + TestVariables.username() + "/shopping-list/items")
                    .then()
                    .statusCode(200)
                    .extract()
                    .jsonPath()
                    .get("id")
                    .toString();

        } finally {
            DelDown();
        }
    }


    void AddDown() {
        given()
                .queryParam("hash", TestVariables.hash())
                .queryParam("apiKey", TestVariables.apiKey())
                .queryParam("username", TestVariables.username())
                .delete("https://api.spoonacular.com/mealplanner/" + TestVariables.username() + "/items/" + addID)
                .then()
                .statusCode(200);
    }

    void DelDown() {
        given()
                .queryParam("hash", TestVariables.hash())
                .queryParam("username", TestVariables.username())
                .queryParam("apiKey", TestVariables.apiKey())
                .queryParam("id", delID)
                .delete("https://api.spoonacular.com/mealplanner/" + TestVariables.username() + "/shopping-list/items/" + delID)
                .then()
                .statusCode(200);
    }
}
