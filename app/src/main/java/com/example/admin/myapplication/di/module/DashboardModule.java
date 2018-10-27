package com.example.admin.myapplication.di.module;

import com.example.admin.myapplication.contracts.IdashboardInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DashboardModule {
    IdashboardInterface.view view;

    public DashboardModule(IdashboardInterface.view view) {
        this.view = view;
    }
    @Provides
    @Singleton
    IdashboardInterface.view provideView(){
        return view;
    }
}
