package com.example.admin.myapplication.di.module;

import com.example.admin.myapplication.contracts.ICompletedInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CompletedModule {
    ICompletedInterface.view view;

    public CompletedModule(ICompletedInterface.view view) {
        this.view = view;
    }

    @Provides
    @Singleton
    ICompletedInterface.view provideCompletedView(){
        return view;
    }
}
