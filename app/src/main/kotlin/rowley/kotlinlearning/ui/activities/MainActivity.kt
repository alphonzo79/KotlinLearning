package rowley.kotlinlearning.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import rowley.kotlinlearning.R
import rowley.kotlinlearning.domain.commands.RequestForecastCommand
import rowley.kotlinlearning.ui.adapters.ForecastListAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        forecastList.layoutManager = LinearLayoutManager(this)

        async() {
            val result = RequestForecastCommand(83687).execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result) { toast(it.description) }
            }
        }
    }
}
