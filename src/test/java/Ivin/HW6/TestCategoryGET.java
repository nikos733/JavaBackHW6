package Ivin.HW6;

import Ivin.HW5.API.CatService;
import Ivin.HW5.DTO.CatResponseGET;
import Ivin.HW5.UTILS.RetroUtils;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestCategoryGET {

    static CatService catService;
    @BeforeAll
    static void BeforeALL() {
        catService = RetroUtils.getRetrofit().create(CatService.class);
    }

    @SneakyThrows
    @Test
    @Tag("Positive")
    @DisplayName("Get all products of a category")
    void GetCategoryByIdPositiveTest() {
        Response<CatResponseGET> response = catService.getCategory(1).execute();
        assertThat(response.code(), equalTo(200));
        assert response.body() != null;
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo(1));
        assertThat(response.body().getTitle(), equalTo("Food"));
        response.body().getProds().forEach(product ->
                assertThat(product.getCategoryTitle(), equalTo("Food")));
    }

    @SneakyThrows
    @Test
    @Tag("Negative")
    @DisplayName("Get all products of a category")
    void GetCategoryByIdNegativeTest() {
        Response<CatResponseGET> response = catService.getCategory(0).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.code(), equalTo(404));
    }

}
