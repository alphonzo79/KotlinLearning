package rowley.kotlinlearning.domain.commands

import rowley.kotlinlearning.data.ForecastRequest
import rowley.kotlinlearning.data.ServerForecastMapper
import rowley.kotlinlearning.domain.model.ForecastList

class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ServerForecastMapper().convertFromDataModel(forecastRequest.execute())
    }
}