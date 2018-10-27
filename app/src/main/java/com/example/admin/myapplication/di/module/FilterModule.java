package com.example.admin.myapplication.di.module;

import com.example.admin.myapplication.presenter.FilterClass;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FilterModule {
    public FilterModule() {
    }
    @Provides
    @Singleton
    FilterClass provideFilterClassObject(){
        return  new FilterClass();
    }
}
