package it.KimoTerru.myweather;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHelper {
    private static NetworkHelper helper;
    private Retrofit retrofit;

    private NetworkHelper() { //Наш помощник/точка входа на сайт
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weatherbit.io/")
                .addConverterFactory(GsonConverterFactory.create()) //Gson Converter Factory - конвертируем чтобы появились Java файлы
                .build();
    }

    public static NetworkHelper getInstance() { //получить экземпляр
        if (helper == null) {
            helper = new NetworkHelper(); //двигаться дальше по ссылке
        }
        return helper;
    }

    public ApiService getService() {
        return retrofit.create(ApiService.class);
    } //получить обслуживание
}