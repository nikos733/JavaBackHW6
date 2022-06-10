package Ivin.HW;

import Ivin.HW.API.ProdService;
import Ivin.HW.UTILS.RetroUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class TestAllProductsGET {

    static ProdService prodService;

    @BeforeAll
    static void BeforeALL() {
        prodService = RetroUtils.Retro().create(ProdService.class);
    }

    @SneakyThrows
    @Test
    @Tag("Positive")
    @DisplayName("Get all products")
    void GetAllProductsTest() {
        Response<ResponseBody> response = prodService.getProducts().execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
    }
}
