package Ivin.HW;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class CuisineTestPOST extends MainTest {

    @Test
    @Tag("Positive")
    @DisplayName("POST. Classify Cuisine")
    void POSTClassifyCuisineSchemaIsValidTest() throws IOException {
        ResponseCuisine response = given()
                .spec(requestSpecification)
                .queryParam("title", "Slow Cooker Sausage Gumbo")
                .when()
                .post(getURL() + "/recipes/cuisine")
                .then()
                .spec(responseSpecification)
                .extract()
                .body()
                .as(ResponseCuisine.class);
        assertThat(response.getCuisine(), notNullValue() );
    }

    @Test
    @Tag("Positive")
    @DisplayName("POST. Check that confidence is not null")
    void POSTClassifyCuisineConfidenceNotNullTest() throws IOException {
        ResponseCuisine response = given()
                .spec(requestSpecification)
                .queryParam("title", "Louisiana Style Gumbo")
                .when()
                .post(getURL() + "/recipes/cuisine")
                .then()
                .spec(responseSpecification)
                .extract()
                .body()
                .as(ResponseCuisine.class);
        assertThat(response.getCuisine() instanceof String, is(true));
        assertThat(response.getConfidence(), not(equalTo(0f)));
    }

    @Test
    @Tag("Positive")
    @DisplayName("POST. Classify Cuisine (European)")
    void POSTClassifyCuisineMediterraneanTest() throws IOException {
        ResponseCuisine response = given()
                .spec(requestSpecification)
                .queryParam("title", "Spanish Tortilla")
                .when()
                .post(getURL() + "/recipes/cuisine")
                .then()
                .spec(responseSpecification)
                .extract()
                .body()
                .as(ResponseCuisine.class);
        assertThat(response.getCuisine(), containsString("European"));
    }

    @Test
    @Tag("Positive")
    @DisplayName("POST. Classify Cuisine")
    void POSTClassifyCuisineCuisinesTypeTest() throws IOException {
        ResponseCuisine response = given()
                .spec(requestSpecification)
                .queryParam("title", "Chicken Gumbo Luisiana Style")
                .when()
                .post(getURL() + "/recipes/cuisine")
                .then()
                .spec(responseSpecification)
                .extract()
                .body()
                .as(ResponseCuisine.class);
        assertThat(response.getCuisine(), containsStringIgnoringCase("creole"));
    }

    @Test
    @Tag("Positive")
    @DisplayName("POST. Classify Cuisine (Middle Eastern)")
    void POSTClassifyCuisineAmericanTest() throws IOException {
        ResponseCuisine response = given()
                .spec(requestSpecification)
                .queryParam("title", "Aladdin's Eatery Hummus")
                .when()
                .post(getURL() + "/recipes/cuisine")
                .then()
                .spec(responseSpecification)
                .extract()
                .body()
                .as(ResponseCuisine.class);
        assertThat(response.getCuisines(), hasItem("Middle Eastern"));
    }
}
