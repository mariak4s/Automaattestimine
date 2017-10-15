package repository;

import reports.CurrentWeatherReport;
import reports.ThreeDayReport;
import reports.WeatherRequest;

public interface Weather {
    CurrentWeatherReport getCurrentWeather(WeatherRequest request);
    ThreeDayReport getForecastForThreeDays(WeatherRequest request);
    }

