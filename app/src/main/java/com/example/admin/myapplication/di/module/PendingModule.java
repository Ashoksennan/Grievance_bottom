package com.example.admin.myapplication.di.module;

import com.example.admin.myapplication.contracts.IPendingInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PendingModule {
    IPendingInterface.view view;

    public PendingModule(IPendingInterface.view view) {
        this.view = view;
    }

    @Provides
    @Singleton
    IPendingInterface.view provideIpendingInterfaceView(){
        return view;
    }
}
