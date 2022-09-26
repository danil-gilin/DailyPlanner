package com.example.dailyplanner.presenter.calendar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.dailyplanner.R
import com.example.dailyplanner.databinding.FragmentMainBinding
import com.example.dailyplanner.entity.CaseDB
import com.example.dailyplanner.entity.CaseInt
import com.example.dailyplanner.entity.Constance
import com.example.dailyplanner.presenter.calendar.rc_day_cases.CasesDayAdapter
import com.example.dailyplanner.presenter.caseDescription.CaseDescriptionFragment
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
   lateinit var mainFactory: MainViewModelFactory

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels { mainFactory }
    private val adapter = CasesDayAdapter{case->onClick(case)}
    private val calendar=Calendar.getInstance() //создаем календарь для обработки даты с включением экрана и отображения дел

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentMainBinding.inflate(inflater)
        viewModel.getDayCases(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))//загрузка сегодняшних дел сразу при запуске

        //обработка даты при клике на день в календаре и отправка во viewModel
        binding.calendar.setOnDateChangeListener { calendarView, year, month, day ->
            viewModel.getDayCases(year,month,day)
        }


        binding.rcDescription.adapter=adapter

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect{
                //обработка состояния
                when(it){
                    is State.Error ->{
                        Toast.makeText(requireContext(),it.error,Toast.LENGTH_SHORT).show()
                    }
                    State.Loading -> {

                    }
                    is State.Succses -> {
                        adapter.submitList(it.dayCases)
                    }
                }
            }
        }

        //запуска экрана добавлениянового дела
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addCaseFragment)
        }


        return binding.root
    }

    //обработка клика по конкретному делу и запуск экрана с полным описание дела
    fun onClick(caseDB: CaseInt){
        val bundle=Bundle()
        bundle.putInt("ID", caseDB.id!!)
       findNavController().navigate(R.id.action_mainFragment_to_caseDescriptionFragment,bundle)
    }

}