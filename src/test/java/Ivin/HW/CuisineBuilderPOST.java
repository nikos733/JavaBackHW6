package Ivin.HW;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class CuisineBuilderPOST {

    private RequestSpecification now;

    public CuisineBuilderPOST() {
        now = given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .queryParam("apiKey", TestVariables.apiKey());
    }

    public Response POST(){
        return now
                .when()
                .post(TestVariables.baseUrl()+ TestVariables.classifyCuisine());
    }

    public String JsonPOST(){
        return POST()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("cuisine")
                .toString();
    }

    private void BodyMod(String key, String value){
        now = now.formParam(key, value);
    }

    public CuisineBuilderPOST Base(String base){
        BodyMod("base", base);
        return this;
    }

    public CuisineBuilderPOST ElementList(String elements){
        BodyMod("elements", elements);
        return this;
    }
}
