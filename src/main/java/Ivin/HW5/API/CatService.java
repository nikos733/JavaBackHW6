package Ivin.HW5.API;

import Ivin.HW5.DTO.CatResponseGET;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface CatService {

    @GET("categories/{id}")
    Call<CatResponseGET> getCategory(@Path("id") int id);
}
