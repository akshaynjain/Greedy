package com.example.myapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.castncrew.CastNCrewRepository
import com.example.myapplication.ui.castncrew.CastNCrewViewModel

class CastNCrewViewModelfactory(private val castNCrewRepository: CastNCrewRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CastNCrewViewModel(castNCrewRepository)as T
    }
}