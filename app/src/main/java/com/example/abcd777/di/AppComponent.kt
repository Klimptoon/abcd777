package com.example.abcd777.di

import com.example.abcd777.presentation.MainFragment
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(mainFragment: MainFragment)
}
