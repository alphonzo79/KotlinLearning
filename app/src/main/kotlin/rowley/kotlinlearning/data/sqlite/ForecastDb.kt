package rowley.kotlinlearning.data.sqlite

import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import rowley.kotlinlearning.domain.datasource.ForecastDataSource
import rowley.kotlinlearning.domain.model.Forecast
import rowley.kotlinlearning.domain.model.ForecastList
import rowley.kotlinlearning.extensions.*
import java.util.*

class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 val dbDataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.COLUMN_CITY_ID} = ? AND ${DayForecastTable.COLUMN_DATE} >= ?"
        val dailyForecast = select(DayForecastTable.TABLE_NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }

        val city = select(CityForecastTable.TABLE_NAME)
                .whereSimple("${CityForecastTable.COLUMN_ID} = ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        if (city != null) dbDataMapper.convertToDomain(city) else null
    }

    override fun requestDayForecast(id: Long): Forecast? = forecastDbHelper.use {
        val forecast = select(DayForecastTable.TABLE_NAME).byId(id).parseOpt { DayForecast(HashMap(it)) }

        if (forecast != null) dbDataMapper.convertDayToDomain(forecast) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.TABLE_NAME)
        clear(DayForecastTable.TABLE_NAME)
        with(dbDataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.TABLE_NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.TABLE_NAME, *it.map.toVarargArray())
            }
        }
    }
}
