import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Weather;
import okhttp3.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Period;
import java.util.List;

import javax.xml.ws.Response;

import org.omg.CORBA.Request;

public class AccuweatherModel implements WeatherModel {
    private static final String PROTOCOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "rAt18GzoUGj6uY2st6NWDkxSo9s6TWG3";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";
    private static final String LANGUAGE_QUERY_PARAM = "language";
    private static final String LANGUAGE = "ru-ru";

    private static final String textCityName = " в городе ";
    private static final String textWaiting = " ожидается:";
    private static final String textMinimalTemperature = "Минимальная температура ";
    private static final String textMaximalTemperature = "Максимальная температура ";
    private static final String textCelsius = "℃;";
    private static final String textDay = "Днём: ";
    private static final String textNight = "Ночью: ";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private DataBaseRepository dataBaseRepository = new DataBaseRepository();

    public void getWeather(String selectedCity, Period period) throws IOException, SQLException {

        switch (period) {

            case NOW:
                showWeatherForOneDay(selectedCity);
                break;

            case FIVE_DAYS:
                showWeatherForFiveDays(selectedCity);
                break;
        }
    }

    @Override
    public List<Weather> getSavedToDBWeather() {
        return dataBaseRepository.getSavedToDBWeather();
    }

    private int fromFahrenheitToCelsius(int fahrenheit) {
        int celsius = (int) ((fahrenheit - 32) / 1.8);
        return celsius;
    }

    private String detectCityKey(String selectCity) throws IOException {

        /**
         * Эндпоинт для метода определения города
         * http://dataservice.accuweather.com/locations/v1/cities/autocomplete
         **/

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", selectCity)
                .addQueryParameter(LANGUAGE_QUERY_PARAM, LANGUAGE)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();
        return cityKey;
    }

    private void showWeatherForOneDay(String selectedCity) throws IOException, SQLException {

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(BASE_HOST)
                .addPathSegment(FORECASTS)
                .addPathSegment(VERSION)
                .addPathSegment(DAILY)
                .addPathSegment(ONE_DAY)
                .addPathSegment(detectCityKey(selectedCity))
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter(LANGUAGE_QUERY_PARAM, LANGUAGE)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        Response oneDayForecastResponse = okHttpClient.newCall(request).execute();
        String weatherResponse = oneDayForecastResponse.body().string();

        String date = objectMapper.readTree(weatherResponse)
                .at("/DailyForecasts")
                .get(0)
                .at("/Date")
                .asText();

        String correctDate = date.split("T")[0];
        String day = correctDate.split("-")[2];
        String month = correctDate.split("-")[1];
        String year = correctDate.split("-")[0];
        String beatifyDateForOneDay = day + "." + month + "." + year;

        int minimalTemperatureInFahrenheitForOneDay = objectMapper.readTree(weatherResponse)
                .at("/DailyForecasts")
                .get(0)
                .at("/Temperature/Minimum/Value")
                .asInt();

        int maximalTemperatureInFahrenheitForOneDay = objectMapper.readTree(weatherResponse)
                .at("/DailyForecasts")
                .get(0)
                .at("/Temperature/Maximum/Value")
                .asInt();

        String daytimeWeatherForOneDay = objectMapper.readTree(weatherResponse)
                .at("/DailyForecasts")
                .get(0)
                .at("/Day/IconPhrase")
                .asText();

        String weatherAtNightForOneDay = objectMapper.readTree(weatherResponse)
                .at("/DailyForecasts")
                .get(0)
                .at("/Night/IconPhrase")
                .asText();

        System.out.println(beatifyDateForOneDay + textCityName + cityName(selectedCity) + textWaiting + "\n"
                + textMinimalTemperature
                + fromFahrenheitToCelsius(minimalTemperatureInFahrenheitForOneDay) + textCelsius + "\n"
                + textMaximalTemperature
                + fromFahrenheitToCelsius(maximalTemperatureInFahrenheitForOneDay) + textCelsius + "\n"
                + textDay + daytimeWeatherForOneDay + "\n" + textNight + weatherAtNightForOneDay);

        DataBaseRepository dataBaseRepository = new DataBaseRepository();
        dataBaseRepository.saveWeatherToDataBase(new Weather(cityName(selectedCity), beatifyDateForOneDay,
                fromFahrenheitToCelsius(maximalTemperatureInFahrenheitForOneDay)));
    }

    private void showWeatherForFiveDays(String selectedCity) throws IOException, SQLException {

        /**
         * Эндпоинт для погоды на 5 дней
         * http://dataservice.accuweather.com/forecasts/v1/daily/5day/295212
         **/

        HttpUrl httpUrl1 = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(BASE_HOST)
                .addPathSegment(FORECASTS)
                .addPathSegment(VERSION)
                .addPathSegment(DAILY)
                .addPathSegment(FIVE_DAYS)
                .addPathSegment(detectCityKey(selectedCity))
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter(LANGUAGE_QUERY_PARAM, LANGUAGE)
                .build();

        Request request1 = new Request.Builder()
                .url(httpUrl1)
                .build();

        Response fiveDaysForecastResponse = okHttpClient.newCall(request1).execute();
        String weatherResponse = fiveDaysForecastResponse.body().string();

        for (int i = 0; i < 5; i++) {

            String dateForFiveDays = objectMapper.readTree(weatherResponse)
                    .at("/DailyForecasts")
                    .get(i)
                    .at("/Date")
                    .asText();

            String correctDateForFirstDay = dateForFiveDays.split("T")[0];
            String days = correctDateForFirstDay.split("-")[2];
            String months = correctDateForFirstDay.split("-")[1];
            String years = correctDateForFirstDay.split("-")[0];
            String beatifyDateForFiveDays = days + "." + months + "." + years;

            String daytimeWeatherForFiveDays = objectMapper.readTree(weatherResponse)
                    .at("/DailyForecasts")
                    .get(i)
                    .at("/Day/IconPhrase")
                    .asText();

            String weatherAtNightForFiveDays = objectMapper.readTree(weatherResponse)
                    .at("/DailyForecasts")
                    .get(i)
                    .at("/Night/IconPhrase")
                    .asText();

            int minimalTemperatureInFahrenheitForFiveDays = objectMapper.readTree(weatherResponse)
                    .at("/DailyForecasts")
                    .get(i)
                    .at("/Temperature/Minimum/Value")
                    .asInt();

            int maximalTemperatureInFahrenheitFirstDay = objectMapper.readTree(weatherResponse)
                    .at("/DailyForecasts")
                    .get(i)
                    .at("/Temperature/Maximum/Value")
                    .asInt();

            System.out.println(beatifyDateForFiveDays + textCityName + cityName(selectedCity) + textWaiting + "\n"
                    + textMinimalTemperature
                    + fromFahrenheitToCelsius(minimalTemperatureInFahrenheitForFiveDays)
                    + textCelsius + "\n"
                    + textMaximalTemperature + fromFahrenheitToCelsius(maximalTemperatureInFahrenheitFirstDay)
                    + textCelsius + "\n"
                    + textDay + daytimeWeatherForFiveDays + "\n" + textNight + weatherAtNightForFiveDays + "\n"
                    + "_______________________________");

            DataBaseRepository dataBaseRepository = new DataBaseRepository();
            dataBaseRepository.saveWeatherToDataBase(new Weather(cityName(selectedCity), beatifyDateForFiveDays,
                    fromFahrenheitToCelsius(maximalTemperatureInFahrenheitFirstDay)));
        }
    }

    private String cityName(String selectedCity) throws IOException {

        /**
         * Эндпоинт для метода определения города
         * http://dataservice.accuweather.com/locations/v1/
         **/

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(detectCityKey(selectedCity))
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter(LANGUAGE_QUERY_PARAM, LANGUAGE)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        String cityName = objectMapper.readTree(responseString)
                .at("/LocalizedName").asText();

        return cityName;
    }
}
