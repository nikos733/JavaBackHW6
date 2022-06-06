package Ivin.HW ;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ComSearchTestGET extends MainTest {

    @Test
    @Tag("Positive")
    @DisplayName("GET. Recipe search with param: number, cuisine, addRecipeInformation," +
            " excludeIngredients")
    void GETRecipeSearchWith4ParamsTestV1() throws IOException {
        ResponseComSearch response = given()
                .spec(requestSpecification)
                .queryParam("number", "5")
                .queryParam("cuisine", "European")
                .queryParam("addRecipeInformation", "true")
                .queryParam("excludeIngredients", "cheese")
                .when()
                .get(getURL() + "/recipes/complexSearch")
                .then()
                .spec(responseSpecification)
                .extract()
                .body()
                .as(ResponseComSearch.class);
        assertThat(response.getNumber(), equalTo(5));
        for (ResponseComSearch.Result res : response.getResults()) {
            assertThat( new ArrayList<>(res.getCuisines()), hasItem("European"));
        }
    }

    @Test
    @Tag("Positive")
    @DisplayName("GET. Recipe search with param: addRecipeInformation, offset, number," +
            " equipment")
    void getRecipeSearchWith4ParamsTestV2() throws IOException {
        ResponseComSearch response = given()
                .spec(requestSpecification)
                .queryParam("addRecipeInformation", "true")
                .queryParam("offset", "2")
                .queryParam("number", "5")
                .queryParam("equipment", "pot")
                .when()
                .get(getURL() + "/recipes/complexSearch")
                .then()
                .spec(responseSpecification)
                .extract()
                .body()
                .as(ResponseComSearch.class);
        assertThat(response.getNumber(), equalTo(5));
        assertThat(response.getOffset(), equalTo(2));
    }

    @Test
    @Tag("Positive")
    @DisplayName("GET. Recipe search with param: addRecipeInformation, offset, number," +
            " maxReadyTime")
    void GETRecipeSearchWith4ParamsTestV3() throws IOException {
        ResponseComSearch response = given()
                .spec(requestSpecification)
                .queryParam("addRecipeInformation", "true")
                .queryParam("offset", "3")
                .queryParam("number", "10")
                .queryParam("maxReadyTime", "10")
                .when()
                .get(getURL() + "/recipes/complexSearch")
                .then()
                .spec(responseSpecification)
                .extract()
                .body()
                .as(ResponseComSearch.class);
        response.getResults().forEach(recipes ->
                assertThat(recipes.getReadyInMinutes(), lessThanOrEqualTo(10)));
    }

    @Test
    @Tag("Positive")
    @DisplayName("GET. Recipe search with number and addRecipeInformation")
    void getRecipeSearchWith2ParamsTest() throws IOException {
        ResponseComSearch response = given()
                .spec(requestSpecification)
                .queryParam("number", "2")
                .queryParam("addRecipeInformation", "true")
                .when()
                .get(getURL() + "/recipes/complexSearch")
                .then()
                .spec(responseSpecification)
                .extract()
                .body()
                .as(ResponseComSearch.class);
        assertThat(response.getNumber(), equalTo(2));
        response.getResults().forEach(recipes ->
                assertThat(recipes.getReadyInMinutes(), notNullValue()));
    }

    @Test
    @Tag("Positive")
    @DisplayName("GET. Recipe search with param: number, addRecipeInformation, instructionsRequired," +
                 " sort, sortDirection, maxProtein, limitLicense ")
    void getRecipeSearchWith8ParamsTest() throws IOException {
        ResponseComSearch response = given()
                .spec(requestSpecification)
                .queryParam("number", "5")
                .queryParam("addRecipeInformation", "true")
                .queryParam("instructionsRequired", "true")
                .queryParam("includeIngredients", "tomato")
                .queryParam("sort", "calories")
                .queryParam("sortDirection", "asc")
                .queryParam("maxProtein", "100")
                .queryParam("limitLicense", "true")
                .when()
                .get(getURL() + "/recipes/complexSearch")
                .then()
                .spec(responseSpecification)
                .extract()
                .body()
                .as(ResponseComSearch.class);
        assertThat(response.getNumber(), equalTo(5));
        response.getResults().toArray();
        for (ResponseComSearch.Result res : response.getResults()) {
            for (ResponseComSearch.Nutrient nutr : res.nutrition.getNutrients()) {
                if(nutr.getName().equalsIgnoreCase("calories" ))
                assertThat(nutr.getAmount(), lessThanOrEqualTo(100f));
            }
        }
 }
}
