package ghar.dfw.perm.myapplicationdiffutils.di

import dagger.Component
import ghar.dfw.perm.myapplicationdiffutils.repo.WeatherRepository
import ghar.dfw.perm.myapplicationdiffutils.view.DiffsViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (DiffsViewModel::class), (WeatherRepository::class)
])
interface WeatherGraph {
    fun provideWeatherDataViewModel() : DiffsViewModel
    fun providedWeatherRepo() : WeatherRepository
}