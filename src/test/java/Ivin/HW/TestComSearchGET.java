package Ivin.HW;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestComSearchGET {

    @BeforeAll
    static void SET() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void Test1ComSearchGET() {
        JsonPath response = new ComSearchBuilderGET()
                .Type("dessert")
                .IncludeElements("chocolate")
                .Impatience("dairy")
                .Diet("vegetarian")
                .JsonGET();
        assertThat(response.get("totalResults"), equalTo(179));
    }

    @Test
    void Test2ComSearchGET() {
        JsonPath response = new ComSearchBuilderGET()
                .maxSugar(30)
                .Request("pizza")
                .IncludeElements("pesto")
                .JsonGET();
        assertThat(response.get("totalResults"), equalTo(4303));
    }

    @Test
    void Test3ComSearchGET() {
        JsonPath response = new ComSearchBuilderGET()
                .Kitchen("italian")
                .Request("pizza")
                .IncludeElements("cheese")
                .JsonGET();
        assertThat(response.get("totalResults"), equalTo(5220));
    }

    @Test
    void Test4ComSearchGET() {
        JsonPath response = new ComSearchBuilderGET()
                .Diet("vegetarian")
                .Type("salad")
                .IncludeElements("tomato")
                .JsonGET();
        assertThat(response.get("totalResults"), equalTo(141));
    }

    @Test
    void Test5ComSearchGET() {
        JsonPath response = new ComSearchBuilderGET()
                .Kitchen("Japanese")
                .Request("soup")
                .JsonGET();
        assertThat(response.get("totalResults"), equalTo(5220));
    }
}

