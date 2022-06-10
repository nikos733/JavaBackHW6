package Ivin.HW;

import Ivin.HW.API.ProdService;
import Ivin.HW.DTO.Prod;
import Ivin.HW.UTILS.RetroUtils;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class TestModifyProd {

    static ProdService prodService;
    Prod prod = null;
    int id = 1;


    @BeforeAll
    static void beforeAll() {
        prodService = RetroUtils.Retro().create(ProdService.class);
    }

    void setUp(String title, int price, String category) {
        prod = new Prod()
                .withId(id)
                .withTitle(title)
                .withPrice(price)
                .withCategoryTitle(category);
    }

    @Test
    @Tag("Positive")
    @DisplayName("Change product (Positive)")
    void modifyProductTest() throws IOException {

        Response<Prod> response = prodService.getProductById(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assert response.body() != null;

        String titleBeforeChanges = response.body().getTitle();
        int priceBeforeChanges = response.body().getPrice();
        String categoryBeforeChanges = response.body().getCategoryTitle();

        setUp("computer", 5000, "Electronic");

        response = prodService.modifyProduct(prod).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
        assert response.body() != null;
        assertThat(response.body().getTitle() != titleBeforeChanges, is (true));
        assertThat(response.body().getPrice() != priceBeforeChanges, is (true));
        assertThat(response.body().getCategoryTitle() != categoryBeforeChanges, is (true));

        tearDown();
    }


    @SneakyThrows
    void tearDown() {
        Response<Prod> response = prodService.modifyProduct(prod).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
