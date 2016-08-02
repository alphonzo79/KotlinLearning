package rowley.kotlinlearning.data.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import rowley.kotlinlearning.App
import rowley.kotlinlearning.data.sqlite.CityForecastTable
import rowley.kotlinlearning.data.sqlite.DayForecastTable

class ForecastDbHelper(ctx: Context = App.instance) : ManagedSQLiteOpenHelper(ctx,
        DB_NAME, null, DB_VERSION) {

    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance by lazy { ForecastDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(CityForecastTable.TABLE_NAME, true,
                CityForecastTable.COLUMN_ID to INTEGER + PRIMARY_KEY,
                CityForecastTable.COLUMN_CITY to TEXT,
                CityForecastTable.COLUMN_COUNTRY to TEXT)

        db?.createTable(DayForecastTable.TABLE_NAME, true,
                DayForecastTable.COLUMN_ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                DayForecastTable.COLUMN_DATE to INTEGER,
                DayForecastTable.COLUMN_DESCRIPTION to TEXT,
                DayForecastTable.COLUMN_HIGH to INTEGER,
                DayForecastTable.COLUMN_LOW to INTEGER,
                DayForecastTable.COLUMN_ICON_URL to TEXT,
                DayForecastTable.COLUMN_CITY_ID to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(CityForecastTable.TABLE_NAME, true)
        db?.dropTable(DayForecastTable.TABLE_NAME, true)
        onCreate(db)
    }
}