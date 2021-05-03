package it.KimoTerru.myweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/v2.0/current") //ендпоинт текущей погоды
    public Call<CurrentWeatherResponse> getCurrentWeather( //Критерии по которым он должен переходить
                                                           @Query("key") String key,
                                                           @Query("lang") String lang,
                                                           @Query("city") String city
    ); //Query - запрос
}