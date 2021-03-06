package rowley.kotlinlearning.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*
import rowley.kotlinlearning.R
import rowley.kotlinlearning.domain.model.Forecast
import rowley.kotlinlearning.domain.model.ForecastList
import rowley.kotlinlearning.extensions.ctx
import rowley.kotlinlearning.extensions.toDateString

class ForecastListAdapter(val weekForecast: ForecastList, val itemClick: (Forecast) -> Unit):
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size()

    class ViewHolder(view: View, val itemClick: (Forecast) -> Unit): RecyclerView.ViewHolder(view) {
        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.iconView)
                itemView.dateView.text = date.toDateString()
                itemView.descriptionView.text = description
                itemView.maxTemperatureView.text = "${high.toString()}"
                itemView.minTemperatureView.text = "${low.toString()}"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}