package rowley.kotlinlearning.data.server

import rowley.kotlinlearning.data.server.Forecast
import rowley.kotlinlearning.data.server.ForecastResult
import rowley.kotlinlearning.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import rowley.kotlinlearning.domain.model.Forecast as ModelForecast

class ServerForecastMapper {

    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<rowley.kotlinlearning.domain.model.Forecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): rowley.kotlinlearning.domain.model.Forecast {
        return rowley.kotlinlearning.domain.model.Forecast(convertDate(forecast.dt), forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon))
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"
}