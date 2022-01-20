package com.berin.istegelsin.di

import com.berin.istegelsin.data.RecipeRepository
import com.berin.istegelsin.data.remote.RecipeRepositoryImpl
import com.berin.istegelsin.data.remote.RecipesAPI
import com.berin.istegelsin.domain.HomeUseCase
import com.berin.istegelsin.presentation.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeDI {
    companion object{
        val module = module {
            viewModel { HomeViewModel(get()) }

            single <RecipesAPI>{
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://apitest.pazarama.com/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofit.create(RecipesAPI::class.java)
            }

            single <RecipeRepository>{ RecipeRepositoryImpl(get())}

            single { HomeUseCase(get()) }
        }
    }
}