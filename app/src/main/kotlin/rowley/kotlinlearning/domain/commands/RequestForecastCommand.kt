package rowley.kotlinlearning.domain.commands

import rowley.kotlinlearning.domain.datasource.ForecastProvider
import rowley.kotlinlearning.domain.model.ForecastList

class RequestForecastCommand(val zipCode: Long,
                             val forecastProvider: ForecastProvider = ForecastProvider()) : Command<ForecastList> {

    companion object {
        val DAYS = 5
    }

    override fun execute(): ForecastList {
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }
}