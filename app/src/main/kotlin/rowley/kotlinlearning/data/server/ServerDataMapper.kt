package rowley.kotlinlearning.data.server

import rowley.kotlinlearning.domain.model.ForecastList
import rowley.kotlinlearning.domain.model.Forecast as ModelForecast

class ServerDataMapper {

    fun convertToDomain(zipCode: Long, forecast: ForecastResult) = with(forecast) {
        ForecastList(zipCode, forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<rowley.kotlinlearning.domain.model.Forecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
        rowley.kotlinlearning.domain.model.Forecast(-1, dt * 1000, weather[0].description,
                temp.max.toInt(), temp.min.toInt(), generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"
}