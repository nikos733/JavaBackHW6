package Ivin.HW;

import Ivin.HW.API.ProdService;
import Ivin.HW.DTO.Prod;
import Ivin.HW.UTILS.RetroUtils;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestProdByIdGET {

    static ProdService prodService;

    @BeforeAll
    static void BeforeALL() {
        prodService = RetroUtils.Retro().create(ProdService.class);
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
