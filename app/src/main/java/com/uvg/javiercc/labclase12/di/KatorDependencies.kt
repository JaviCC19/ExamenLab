package com.uvg.javiercc.labclase12.di

import android.content.Context
import androidx.room.Room
import com.uvg.javiercc.labclase12.data.Local.Database
import com.uvg.javiercc.labclase12.data.network.HttpClientFactory

import io.ktor.client.HttpClient


object KtorDependencies {
    private var httpClient: HttpClient? = null
    private var localDb: Database? = null

    private fun buildHttpClient(): HttpClient = HttpClientFactory.create()

    private fun buildLocalDb(context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "asset.db"
        ).build()
    }

    fun provideHttpClient(): HttpClient {
        return httpClient ?: synchronized(this) {
            httpClient ?: buildHttpClient().also { httpClient = it }
        }
    }

    fun provideLocalDb(context: Context): Database {
        return localDb ?: synchronized(this) {
            localDb ?: buildLocalDb(context).also { localDb = it }
        }
    }
}