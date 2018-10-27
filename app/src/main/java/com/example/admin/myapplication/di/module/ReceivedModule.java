package com.example.admin.myapplication.di.module;

import com.example.admin.myapplication.contracts.IReceivedInterface;
import com.example.admin.myapplication.fragments.Received;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
@Module
public class ReceivedModule {
    IReceivedInterface.view view;

    public ReceivedModule(IReceivedInterface.view view) {
        this.view = view;
    }
    @Provides
    @Singleton
    IReceivedInterface.view provideIReceivedInterfaceView(){
        return this.view;
    }
}
