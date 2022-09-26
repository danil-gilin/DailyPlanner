package com.example.dailyplanner.presenter.calendar.rc_day_cases

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyplanner.databinding.CaseDayBinding
import com.example.dailyplanner.entity.*
import com.example.dailyplanner.presenter.calendar.rc_hour_cases.CasesHourAdapter
import java.sql.Timestamp
import java.text.SimpleDateFormat

class CasesDayAdapter(private val onClickFull: (CaseInt) -> Unit) :
    ListAdapter<CaseHour, CasesDayViewHolder>(DiffutilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasesDayViewHolder {
        return CasesDayViewHolder(
            CaseDayBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CasesDayViewHolder, position: Int) {
        val item = getItem(position)
        val adapter = CasesHourAdapter { case -> onClick(case) }
        with(holder.binding) {
            //преобразование времени в нормальный формат
            time.text = "${SimpleDateFormat(Constance.SIMPLE_HOUR_FROMAT).format(item.hour)}-${SimpleDateFormat(Constance.SIMPLE_HOUR_FROMAT).format(item.hour + Constance.HOUR)}"

            rcManyDescription.adapter = adapter

            adapter.submitList(item.cases)
        }
    }

    //обработка нажатия на дочерний элемент
    fun onClick(caseDB: CaseInt) {
        onClickFull(caseDB)
    }
}

class CasesDayViewHolder(val binding: CaseDayBinding) : RecyclerView.ViewHolder(binding.root)

class DiffutilCallback : DiffUtil.ItemCallback<CaseHour>() {
    override fun areItemsTheSame(oldItem: CaseHour, newItem: CaseHour): Boolean {
        return oldItem.hour == newItem.hour
    }

    override fun areContentsTheSame(oldItem: CaseHour, newItem: CaseHour): Boolean {
        return oldItem == newItem
    }

}