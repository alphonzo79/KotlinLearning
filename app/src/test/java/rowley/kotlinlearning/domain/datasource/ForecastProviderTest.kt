package rowley.kotlinlearning.domain.datasource

import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.*
import rowley.kotlinlearning.domain.model.Forecast

class ForecastProviderTest {

    @Test
    fun requestByZipCode() {

    }

    @Test
    fun requestForecast() {
        val ds = mock(ForecastDataSource::class.java)
        `when`(ds.requestDayForecast(0)).then {
            Forecast(0, 0, "desc", 20, 0, "url")
        }

        val provider = ForecastProvider(listOf(ds))
        assertNotNull(provider.requestForecast(0))
    }

    @Test
    fun requestForecaseEmptyDbReturnsServerValue() {
        val server = mock(ForecastDataSource::class.java)
        `when`(server.requestDayForecast(0)).then {
            Forecast(0, 0, "desc", 20, 0, "url")
        }
        val db = mock(ForecastDataSource::class.java)

        val provider = ForecastProvider(listOf(db, server))
        assertNotNull(provider.requestForecast(0))

        verify(db).requestDayForecast(0)
        verify(server).requestDayForecast(0)
    }

    @Test
    fun requestForecaseGoodDbSkipsServer() {
        val server = mock(ForecastDataSource::class.java)
        val db = mock(ForecastDataSource::class.java)
        `when`(db.requestDayForecast(0)).then {
            Forecast(0, 0, "desc", 20, 0, "url")
        }

        val provider = ForecastProvider(listOf(db, server))
        assertNotNull(provider.requestForecast(0))

        verify(db).requestDayForecast(0)
        verify(server, never()).requestDayForecast(0)
    }

}