package rowley.kotlinlearning.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import rowley.kotlinlearning.R
import rowley.kotlinlearning.domain.commands.RequestForecastCommand
import rowley.kotlinlearning.ui.adapters.ForecastListAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastList.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() = async() {
        val result = RequestForecastCommand(83687).execute()
        uiThread {
            forecastList.adapter = ForecastListAdapter(result) {
                startActivity<DetailActivity>(DetailActivity.ID to it.id, DetailActivity.CITY_NAME to result.city)
            }
//                toolbarTitle = "${result.city} (${result.country}"
        }
    }
}
