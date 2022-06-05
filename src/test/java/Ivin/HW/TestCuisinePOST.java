package Ivin.HW;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestCuisinePOST {

    @BeforeAll
    static void SET() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void Test1CuisinePOST() {
        String cuisine = new CuisineBuilderPOST()
                .Base("Bibimbap")
                .ElementList("rice,bean sprouts,gochujang")
                .JsonPOST();
        assertThat(cuisine, equalTo("Italian"));
    }

    @Test
    void Test2CuisinePOST() {
        String cuisine = new CuisineBuilderPOST()
                .Base("Thai Noodle Salad")
                .ElementList("sesame oil,1 red bell pepper,3 scallions")
                .JsonPOST();
        assertThat(cuisine, equalTo("Italian"));
    }

    @Test
    void Test3CuisinePOST() {
        String cuisine = new CuisineBuilderPOST()
                .Base("Chinese Vegetable Stir-Fry")
                .ElementList("sesame oil")
                .JsonPOST();
        assertThat(cuisine, equalTo("Italian"));
    }

    @Test
    void Test4CuisinePOST() {
        String cuisine = new CuisineBuilderPOST()
                .Base("Pea soup with parmesan and a just-boiled egg")
                .ElementList("peas,edd,parmesan")
                .JsonPOST();
        assertThat(cuisine, equalTo("Italian"));
    }

    @Test
    void Test5CuisinePOST() {
        String cuisine = new CuisineBuilderPOST()
                .Base("Garlic Butter Shrimp with Asparagus")
                .ElementList("5 clove garlic")
                .JsonPOST();
        assertThat(cuisine, equalTo("Italian"));
    }
}
