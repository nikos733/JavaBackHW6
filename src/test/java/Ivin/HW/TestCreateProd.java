package Ivin.HW;

import Ivin.HW.API.ProdService;
import Ivin.HW.DTO.Prod;
import Ivin.HW.UTILS.RetroUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestCreateProd {

    static ProdService prodService;
    Prod prod = null;
    int id;

    @BeforeAll
    static void BeforeALL() {
        prodService = RetroUtils.Retro().create(ProdService.class);
    }

    void SetUP(String title, int price, String category) {
        prod = new Prod()
                .withTitle(title)
                .withPrice(price)
                .withCategoryTitle(category);
    }

    @Test
    @Tag("Positive")
    @DisplayName("Product creation")
    void CreateAndDeleteProductTest() throws IOException {
        SetUP("bread", 9562, "Food");
        Response<Prod> response = prodService.createProduct(prod).execute();
        assertThat(response.code(), equalTo(201));
        assert response.body() != null;
        assertThat(response.body().getId(), notNullValue());
        assertThat(response.body().getCategoryTitle(), equalTo("Food"));
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        id =  response.body().getId();
        DOWN();
        Response<Prod> responseForChecking = prodService.getProductById(id).execute();
        assertThat(responseForChecking.code(), equalTo(404));
    }

    @SneakyThrows
    void DOWN() {
        Response<ResponseBody> response = prodService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}