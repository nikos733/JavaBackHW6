package Ivin.HW6.API;

import Ivin.HW6.DTO.GetCategoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface CatService {

    @GET("categories/{id}")
    Call<GetCategoryResponse> getCategory(@Path("id") int id);
}
