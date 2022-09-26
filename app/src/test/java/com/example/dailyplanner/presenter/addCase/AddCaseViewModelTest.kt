package com.example.dailyplanner.presenter.addCase

import androidx.collection.arrayMapOf
import com.example.dailyplanner.domain.AddDayCaseUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock


class AddCaseViewModelTest {
    val addDayCaseUseCase= mock<AddDayCaseUseCase>()
    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun afterEach(){
        Mockito.reset(addDayCaseUseCase)
        Dispatchers.resetMain()
    }

    //тест с верными данными ожидаем StateAddCase.Succses
    @Test
    fun addCase()= runTest {
        val testName="Case"
        val testDescription="Description"
        val testTimeStart=600000L
        val testTimeEnd=650000L
        val testDate=1664223061L


        val viewModel=AddCaseViewModel(addDayCaseUseCase)

        viewModel.addCase(testName,testDescription,testTimeStart,testTimeEnd,testDate)
        val expected=StateAddCase.Succses
        val actual=viewModel.state.value

        assertEquals(expected,actual)
    }


    //тест с неправльным веременем ожидаем StateAddCase.Error(arrayMapOf<String,Boolean>(Pair("NAME",true),Pair("DESCRIPTION",true),Pair("Time",false)))
    @Test
    fun addCase2() {
        val testName="Case"
        val testDescription="Description"
        val testTimeStart=600000L
        val testTimeEnd=550000L
        val testDate=1664223061L


        val viewModel=AddCaseViewModel(addDayCaseUseCase)

        viewModel.addCase(testName,testDescription,testTimeStart,testTimeEnd,testDate)
        val expected=StateAddCase.Error(arrayMapOf<String,Boolean>(Pair("NAME",true),Pair("DESCRIPTION",true),Pair("Time",false)))
        val actual=viewModel.state.value

        assertEquals(expected,actual)
    }

    //тест с пустым полем имени StateAddCase.Error(arrayMapOf<String,Boolean>(Pair("NAME",false),Pair("DESCRIPTION",true),Pair("Time",true)))
    @Test
    fun addCase3() {
        val testName=""
        val testDescription="Description"
        val testTimeStart=600000L
        val testTimeEnd=650000L
        val testDate=1664223061L


        val viewModel=AddCaseViewModel(addDayCaseUseCase)

        viewModel.addCase(testName,testDescription,testTimeStart,testTimeEnd,testDate)
        val expected=StateAddCase.Error(arrayMapOf<String,Boolean>(Pair("NAME",false),Pair("DESCRIPTION",true),Pair("Time",true)))
        val actual=viewModel.state.value

        assertEquals(expected,actual)
    }

    //тест с пустым полем описания StateAddCase.Error(arrayMapOf<String,Boolean>(Pair("NAME",true),Pair("DESCRIPTION",false),Pair("Time",true)))
    @Test
    fun addCase4() {
        val testName="Case"
        val testDescription=""
        val testTimeStart=600000L
        val testTimeEnd=650000L
        val testDate=1664223061L


        val viewModel=AddCaseViewModel(addDayCaseUseCase)

        viewModel.addCase(testName,testDescription,testTimeStart,testTimeEnd,testDate)
        val expected=StateAddCase.Error(arrayMapOf<String,Boolean>(Pair("NAME",true),Pair("DESCRIPTION",false),Pair("Time",true)))
        val actual=viewModel.state.value

        assertEquals(expected,actual)
    }


    //тест с 0 временем конца StateAddCase.Error(arrayMapOf<String,Boolean>(Pair("NAME",true),Pair("DESCRIPTION",true),Pair("Time",false)))
    @Test
    fun addCase5() {
        val testName="Case"
        val testDescription="Description"
        val testTimeStart=600000L
        val testTimeEnd=0L
        val testDate=1664223061L


        val viewModel=AddCaseViewModel(addDayCaseUseCase)

        viewModel.addCase(testName,testDescription,testTimeStart,testTimeEnd,testDate)
        val expected=StateAddCase.Error(arrayMapOf<String,Boolean>(Pair("NAME",true),Pair("DESCRIPTION",true),Pair("Time",false)))
        val actual=viewModel.state.value

        assertEquals(expected,actual)
    }


    //тест с 0 временем начала StateAddCase.Error(arrayMapOf<String,Boolean>(Pair("NAME",true),Pair("DESCRIPTION",true),Pair("Time",false)))
    @Test
    fun addCase6() {
        val testName="Case"
        val testDescription="Description"
        val testTimeStart=0L
        val testTimeEnd=650000L
        val testDate=1664223061L


        val viewModel=AddCaseViewModel(addDayCaseUseCase)

        viewModel.addCase(testName,testDescription,testTimeStart,testTimeEnd,testDate)
        val expected=StateAddCase.Error(arrayMapOf<String,Boolean>(Pair("NAME",true),Pair("DESCRIPTION",true),Pair("Time",false)))
        val actual=viewModel.state.value

        assertEquals(expected,actual)
    }


    //тест с 0 датой StateAddCase.Error(arrayMapOf<String,Boolean>(Pair("NAME",true),Pair("DESCRIPTION",true),Pair("Time",false)))
    @Test
    fun addCase7() {
        val testName="Case"
        val testDescription="Description"
        val testTimeStart=600000L
        val testTimeEnd=650000L
        val testDate=0L


        val viewModel=AddCaseViewModel(addDayCaseUseCase)

        viewModel.addCase(testName,testDescription,testTimeStart,testTimeEnd,testDate)
        val expected=StateAddCase.Error(arrayMapOf<String,Boolean>(Pair("NAME",true),Pair("DESCRIPTION",true),Pair("Time",false)))
        val actual=viewModel.state.value

        assertEquals(expected,actual)
    }

}