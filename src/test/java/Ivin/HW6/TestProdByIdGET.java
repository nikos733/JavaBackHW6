package Ivin.HW6;

import Ivin.HW5.API.ProdService;
import Ivin.HW5.DTO.Prod;
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


public class TestProdByIdGET {

    static ProdService prodService;

    @BeforeAll
    static void BeforeALL() {
        prodService = RetroUtils.getRetrofit().create(ProdService.class);
    }

    @SneakyThrows
    @Test
    @Tag("Positive")
    @DisplayName("Get product by ID != 0")
    void GetCategoryByIdPositiveTest() {
        Response<Prod> response = prodService.getProductById(1).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
        assert response.body() != null;
        assertThat(response.body().getId(), equalTo(1));
        assertThat(response.body().getTitle(), equalTo("Milk"));
        assertThat(response.body().getPrice(), equalTo(95));
        assertThat(response.body().getCategoryTitle(), equalTo("Food"));
    }

    @SneakyThrows
    @Test
    @Tag("Negative")
    @DisplayName("Get product by ID=0")
    void GetCategoryByIdNegativeTest() {
        Response<Prod> response = prodService.getProductById(0).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.code(), equalTo(404));
    }
}
