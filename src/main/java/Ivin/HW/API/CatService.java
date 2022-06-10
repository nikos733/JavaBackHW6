package Ivin.HW.API;

import Ivin.HW.DTO.CatResponseGET;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface CatService {

    @GET("categories/{id}")
    Call<CatResponseGET> getCategory(@Path("id") int id);
}
