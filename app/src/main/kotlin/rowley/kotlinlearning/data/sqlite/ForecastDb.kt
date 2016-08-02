package rowley.kotlinlearning.data.sqlite

class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 val dbDataMapper: DbDataMapper = DbDataMapper()) {

    fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.COLUMN_CITY_ID} = ? AND ${DayForecastTable.COLUMN_DATE} >= ?"
//        val dailyForecast = select(DayForecastTable.TABLE_NAME)
//                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
//                .parseList { DayForecast(HashMap(it)) }
//
//        val city = select(CityForecastTable.TABLE_NAME)
//                .whereSimple("${CityForecastTable.COLUMN_ID} = ?", zipCode.toString())
//                .parseOpt { CityForecast(HashMap(it), dailyForecast) }
//
//        city?.let { dataMapper.convertToDomain(it) }
    }
}
