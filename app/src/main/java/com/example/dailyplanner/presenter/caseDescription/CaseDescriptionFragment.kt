package com.example.dailyplanner.presenter.caseDescription

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Insert
import com.example.dailyplanner.R
import com.example.dailyplanner.databinding.FragmentCaseDescriptionBinding
import com.example.dailyplanner.entity.Constance
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.text.SimpleDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class CaseDescriptionFragment : Fragment() {

    companion object {
        fun newInstance() = CaseDescriptionFragment()
    }

    @Inject
    lateinit var caseViewModelFactory :CaseDescriptionViewModelFactory

    lateinit var binding: FragmentCaseDescriptionBinding
    private val viewModel: CaseDescriptionViewModel by viewModels{ caseViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //получение id дела по которому кликнули
        arguments.let {
            //отправляем id в viewModel для запроса дела
            viewModel.getCase(it?.getInt("ID") ?: 0)
        }


        binding=FragmentCaseDescriptionBinding.inflate(inflater)



        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect{
                //обработка состояния
                when(it){
                    is StateDescription.Error ->{
                        //вывод ошибки
                        binding.description.text=it.error
                    }
                    StateDescription.Loading ->{

                    }
                    is StateDescription.Succses -> {
                        //вывод на кэран полной информации по делу
                      binding.description.text=  it.case.description
                      binding.timeFull.text=  "${SimpleDateFormat(Constance.SIMPLE_HOUR_FROMAT).format(it.case.date_start)}-${SimpleDateFormat(Constance.SIMPLE_HOUR_FROMAT).format(it.case.date_finish)}"
                      binding.date.text=  SimpleDateFormat(Constance.SIMPLE_DATE_FROMAT).format(it.case.date_start)
                      binding.name.text=  it.case.name
                    }
                }
            }
        }


        //возврат обратно
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

}