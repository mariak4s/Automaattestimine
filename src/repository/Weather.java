package repository;

import reports.CurrentWeatherReport;
import reports.ThreeDayReport;
import weatherRequest.WeatherRequest;

public interface Weather {
    CurrentWeatherReport getCurrentWeather(WeatherRequest weatherRequest);
    ThreeDayReport getForecastForThreeDays(WeatherRequest weatherRequest);
    }

