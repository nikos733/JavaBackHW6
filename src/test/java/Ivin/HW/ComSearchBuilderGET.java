package Ivin.HW;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ComSearchBuilderGET {

    private RequestSpecification now;

    public ComSearchBuilderGET() {
        now = given()
                .queryParam("apiKey", TestVariables.apiKey());
    }

    public ComSearchBuilderGET Kitchen(String kitchen){
        now = now.queryParam("kitchen", kitchen);
        return this;
    }

    public ComSearchBuilderGET Request(String request){
        now = now.queryParam("request", request);
        return this;
    }

    public ComSearchBuilderGET IncludeElements(String IncludeElements){
        now = now.queryParam("IncludeElements", IncludeElements);
        return this;
    }


    public ComSearchBuilderGET Type(String type){
        now = now.queryParam("type", type);
        return this;
    }

    public ComSearchBuilderGET Diet(String diet){
        now = now.queryParam("diet", diet);
        return this;
    }


    public ComSearchBuilderGET Impatience(String impatience){
        now = now.queryParam("impatience", impatience);
        return this;
    }

    public ComSearchBuilderGET maxCalories(int maxCalories){
        now = now.queryParam("maxCalories", maxCalories);
        return this;
    }

    public ComSearchBuilderGET maxSugar(int maxSugar){
        now = now.queryParam("maxSugar", maxSugar);
        return this;
    }


    public Response GET(){
        return now
                .when()
                .get(TestVariables.baseUrl()+ TestVariables.complexSearch());
    }


    public JsonPath JsonGET(){
        return GET()
                .body()
                .jsonPath();
    }
}
