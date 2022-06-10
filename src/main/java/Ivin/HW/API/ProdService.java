package Ivin.HW.API;


import Ivin.HW.DTO.Prod;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ProdService {

    @GET("products")
    Call<ResponseBody> getProducts();

    @POST("products")
    Call<Prod> createProduct(@Body Prod createProdRequest);

    @PUT("products")
    Call<Prod> modifyProduct(@Body Prod modifyProdRequest);

    @GET("products/{id}")
    Call<Prod> getProductById(@Path("id") int id);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id);

}
