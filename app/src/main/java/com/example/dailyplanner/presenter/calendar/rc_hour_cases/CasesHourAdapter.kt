package com.example.dailyplanner.presenter.calendar.rc_hour_cases

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyplanner.databinding.CaseHourBinding
import com.example.dailyplanner.entity.CaseDB
import com.example.dailyplanner.entity.CaseInt
import com.example.dailyplanner.entity.Constance
import java.text.SimpleDateFormat

class CasesHourAdapter(private val onClick: (CaseInt) -> Unit) :
    ListAdapter<CaseInt, CasesHourViewHolder>(DiffutilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasesHourViewHolder {
        return CasesHourViewHolder(
            CaseHourBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CasesHourViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            caseName.text = item.name

            //преобразование времени в нормальный формат
            caseTime.text =
                "${SimpleDateFormat(Constance.SIMPLE_HOUR_FROMAT).format(item.date_start)}-${SimpleDateFormat(Constance.SIMPLE_HOUR_FROMAT).format(item.date_finish)} : "

            //обработка нажатия на дело
            linearHour.setOnClickListener {
                onClick(item)
            }
        }
    }
}

class CasesHourViewHolder(val binding: CaseHourBinding) : RecyclerView.ViewHolder(binding.root)

class DiffutilCallback : DiffUtil.ItemCallback<CaseInt>() {
    override fun areItemsTheSame(oldItem: CaseInt, newItem: CaseInt): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CaseInt, newItem: CaseInt): Boolean {
        return oldItem.equals(newItem)
    }

}