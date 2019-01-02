package com.sph.mobdatausage.features.home.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sph.mobdatausage.R
import com.sph.mobdatausage.features.home.adapter.MobDataConsumptionAdapter.Listener.Companion.NO_OP
import com.sph.mobdatausage.model.DataConsumedYearly
import kotlinx.android.synthetic.main.item_statistics.view.*

class MobDataConsumptionAdapter : RecyclerView.Adapter<MobDataConsumptionAdapter.NetworkStatistics>() {

    private val networkStatistics = mutableListOf<DataConsumedYearly>()
    private var listener: Listener = NO_OP

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetworkStatistics {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_statistics, parent,
                false)
        return NetworkStatistics(view)
    }

    override fun onBindViewHolder(holder: NetworkStatistics, position: Int) {
        holder.bind(networkStatistics[position])

    }

    override fun getItemCount() = networkStatistics.size

    fun updateArticles(newsArtcles: List<DataConsumedYearly>) {
        this.networkStatistics.clear()
        this.networkStatistics.addAll(newsArtcles)
        notifyDataSetChanged()
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    inner class NetworkStatistics(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(mobNetConsumption: DataConsumedYearly) {
            itemView.tvTotalDataVolume.text = "Total Data Volume (${mobNetConsumption.yearOfConsumption})"
            itemView.tvTotalDataVolumeValue.text = "${mobNetConsumption.totalVolumeConsumed} PB"
            if (mobNetConsumption.isDataVolumeConsumptionDecreased) {
                itemView.dataVolumIndicator.setBackgroundColor(Color.parseColor("#E1116F"))
            } else {
                itemView.dataVolumIndicator.setBackgroundColor(Color.parseColor("#139719"))
            }
            itemView.tvQuaOneDataVolume.text = mobNetConsumption.quarterlyData[0]
            itemView.tvQuaTwoDataVolume.text = mobNetConsumption.quarterlyData[1]
            itemView.tvQuaThreeDataVolume.text = mobNetConsumption.quarterlyData[2]
            itemView.tvQuaFourDataVolume.text = mobNetConsumption.quarterlyData[3]

            itemView.setOnClickListener {
                listener.statisticsClicked(mobNetConsumption)
            }
        }
    }

    interface Listener {

        companion object {

            val NO_OP = object : Listener {
                override fun statisticsClicked(dataConsumedYearly: DataConsumedYearly) {
                    // no op
                }
            }
        }

        fun statisticsClicked(dataConsumedYearly: DataConsumedYearly)
    }
}
