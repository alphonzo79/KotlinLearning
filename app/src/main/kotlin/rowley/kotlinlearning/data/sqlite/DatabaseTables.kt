package rowley.kotlinlearning.data.sqlite

object CityForecastTable {
    val TABLE_NAME = "CityForecast"
    val COLUMN_ID = "_id"
    val COLUMN_CITY = "city"
    val COLUMN_COUNTRY = "country"
}

object DayForecastTable {
    val TABLE_NAME = "DayForecast"
    val COLUMN_ID = "_id"
    val COLUMN_DATE = "date"
    val COLUMN_DESCRIPTION = "description"
    val COLUMN_HIGH = "high"
    val COLUMN_LOW = "low"
    val COLUMN_ICON_URL = "iconUrl"
    val COLUMN_CITY_ID = "cityId"
}
