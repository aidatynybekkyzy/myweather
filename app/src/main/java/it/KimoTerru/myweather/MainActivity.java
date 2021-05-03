package it.KimoTerru.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView cityNameText;
    private TextView tempText;
    private TextView descrText;
    private TextView windDirText;
    private TextView windSpeedText;
    private TextView sunriseText;
    private TextView sunsetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityNameText = findViewById(R.id.city_name);
        tempText = findViewById(R.id.temp_text);
        descrText = findViewById(R.id.descr_text);
        windDirText = findViewById(R.id.wind_dir_text);
        windSpeedText = findViewById(R.id.speed_text);
        sunriseText = findViewById(R.id.sunrise_text);
        sunsetText = findViewById(R.id.sunset_text);

        NetworkHelper.getInstance()
                .getService()
                .getCurrentWeather("b96a6f3f18df40ae94a7c47fc96c4339", "ru", "Bishkek")
                .enqueue(new Callback<CurrentWeatherResponse>() {
                    @Override
                    public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) { //Call - вызов, Response - на ответ
                        if (response.body() != null && response.body().getCount() > 0) { //Проверка на наличие данных
                            displayData(response.body().getData().get(0)); //Запускает метод и принимает данные из масива с их наличией
                        } else {
                            Toast.makeText(MainActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show(); //Должно выйти сообщение
                            System.out.println(response.message()); //необработанный ответ
                        }
                    }
                    @Override
                    public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                        System.out.println(t.getMessage());
                        t.printStackTrace(); //При неудаче написать ошибка в Logcat
                    } //при неудаче
                });
    }
    private void displayData(CurrentWeatherItem item) {
        cityNameText.setText(item.getCityName());
        tempText.setText(item.getTemp() + " C");
        descrText.setText(item.getWeather().getDescription());
        windDirText.setText(item.getWindDir());
        windSpeedText.setText(item.getWindSpeed() + "");
        sunriseText.setText(item.getSunrise());
        sunsetText.setText(item.getSunset());
    } //Процесс замены данных на экране
}