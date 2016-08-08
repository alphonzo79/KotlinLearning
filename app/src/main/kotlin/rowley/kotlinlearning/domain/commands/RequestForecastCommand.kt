package rowley.kotlinlearning.domain.commands

import rowley.kotlinlearning.data.server.ForecastRequest
import rowley.kotlinlearning.data.server.ServerForecastMapper
import rowley.kotlinlearning.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: Long) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ServerForecastMapper().convertFromDataModel(zipCode, forecastRequest.execute())
    }
}