package it.KimoTerru.myweather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//данные приходят в виде обьекта, где есть массив данных
public class CurrentWeatherResponse { //реакция на текущмю погоду
    @SerializedName("data") // сами данные
    private List<CurrentWeatherItem> data; //Все данные в Json
    @SerializedName("count") // количество элементов в data
    private int count; //Нужен для проверки на пустоту Json файла

    public List<CurrentWeatherItem> getData() { //Список или масив который должен вернуть
        return data;
    }

    public int getCount() { //Нужен для проверки данных на их наличие
        return count;
    }
}