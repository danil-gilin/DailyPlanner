package com.example.dailyplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.dailyplanner.presenter.calendar.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //подключение навигации
        val navHostFragment= supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_nav) as NavHostFragment
        val navController = navHostFragment.navController


    }
}