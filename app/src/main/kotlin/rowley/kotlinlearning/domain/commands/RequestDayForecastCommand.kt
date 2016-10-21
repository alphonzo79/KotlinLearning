package rowley.kotlinlearning.domain.commands

import rowley.kotlinlearning.domain.datasource.ForecastProvider
import rowley.kotlinlearning.domain.model.Forecast

class RequestDayForecastCommand(val id: Long,
                                val forecastProvider: ForecastProvider = ForecastProvider()): Command<Forecast> {
    override fun execute() = forecastProvider.requestForecast(id)
}