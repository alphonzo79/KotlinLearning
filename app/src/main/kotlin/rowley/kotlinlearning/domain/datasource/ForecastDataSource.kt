package rowley.kotlinlearning.domain.datasource

import rowley.kotlinlearning.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}