package com.example.dailyplanner.data.bd

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//база данных
@InstallIn(SingletonComponent::class)
@Module
class ProvederCasesDao {
    @Provides
    fun provideChannelDao(appDatabase: AppDataBase): CasesDao {
        return appDatabase.photoDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java ,
            "db"
        ).build()
    }
}