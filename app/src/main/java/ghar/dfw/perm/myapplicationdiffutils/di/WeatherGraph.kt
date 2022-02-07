package ghar.dfw.perm.myapplicationdiffutils.di

import dagger.Component
import ghar.dfw.perm.myapplicationdiffutils.view.DiffsViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(DiffsViewModel::class)])
interface WeatherGraph {
    fun getWeatherData() : DiffsViewModel
}