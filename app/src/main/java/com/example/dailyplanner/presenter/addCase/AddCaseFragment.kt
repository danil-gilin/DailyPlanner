package com.example.dailyplanner.presenter.addCase

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.dailyplanner.R
import com.example.dailyplanner.databinding.FragmentAddCaseBinding
import com.example.dailyplanner.entity.Constance
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AddCaseFragment : Fragment() {

    companion object {
        fun newInstance() = AddCaseFragment()
    }

    @Inject
    lateinit var factoryAddCaseViewModel:AddCaseViewModelFactory

    lateinit var binding:FragmentAddCaseBinding
    private val viewModel: AddCaseViewModel by viewModels {factoryAddCaseViewModel}

    private var timeStart=0L//время начала дела
    private var timeEnd=0L//время конца дела
    private var timeDate=0L//дата дела
    private var name=""//имя дела
    private var description=""//описание дела

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAddCaseBinding.inflate(inflater)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
         viewModel.state.collect{
             when(it){
                 is StateAddCase.Error ->{
                     //обработка ошибок и вывод их
                     if(it.error["NAME"] == false){
                         binding.nameLayout.error=Constance.errorAddName
                     }else{
                         binding.nameLayout.error=null
                     }
                     if(it.error["DESCRIPTION"] == false){
                         binding.descriptionLayout.error=Constance.errorAddDESCRIPTION
                     }else{
                         binding.descriptionLayout.error=null
                     }
                     if(it.error["Time"] == false){
                         binding.errorTime.text=Constance.errorAddTime
                        binding.errorTime.visibility=View.VISIBLE
                     }else{
                         binding.errorTime.visibility=View.GONE
                     }
                 }
                 StateAddCase.Loading -> {

                 }
                 StateAddCase.Succses ->{
                     //убираем ошибки и возвращаемя на главный экран
                     binding.nameLayout.error=null
                     binding.descriptionLayout.error=null
                     binding.errorTime.visibility=View.GONE
                     findNavController().popBackStack()
                 }
                 StateAddCase.Start -> {

                 }
             }
         }
        }



         //вызов и обработка календаря
        binding.btnDate.setOnClickListener {
            val datePicker=MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                binding.txtDate.text=SimpleDateFormat(Constance.SIMPLE_DATE_FROMAT).format(it)
                timeDate=it
            }

            datePicker.show(childFragmentManager,"date")
        }

        //вызов и обработка часов для timeStart
        binding.btnStartTime.setOnClickListener {
            val picker= MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build().apply {
                    addOnPositiveButtonClickListener{
                        val timeStartPicker=this.hour*Constance.HOUR+this.minute*60000L
                        binding.txtStartTime.text=SimpleDateFormat(Constance.SIMPLE_HOUR_FROMAT).format(timeStartPicker)
                        timeStart=timeStartPicker
                    }
                }
            picker.show(childFragmentManager,"Start Time")
        }

        //вызов и обработка часов для timeEnd
        binding.btnFinishTime.setOnClickListener {
            val picker= MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build().apply {
                    addOnPositiveButtonClickListener{
                        val timeEndPicker=this.hour*Constance.HOUR+this.minute*60000L
                        binding.txtEndTime.text=SimpleDateFormat(Constance.SIMPLE_HOUR_FROMAT).format(timeEndPicker)
                        timeEnd=timeEndPicker
                    }
                }
            picker.show(childFragmentManager,"Finish Time")
        }


        binding.btnCreat.setOnClickListener {
            name=binding.nameEdit.text.toString()
            description=binding.descriptionEdit.text.toString()
            //вызов метода viewModel для обработки вводимых данных и последующего сохранения
            viewModel.addCase(name,description,timeStart,timeEnd,timeDate)
        }


        binding.btnBackAdd.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }


}