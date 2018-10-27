package com.example.admin.myapplication.di.components;

import com.example.admin.myapplication.di.module.FilterModule;
import com.example.admin.myapplication.di.module.NetworkModule;
import com.example.admin.myapplication.di.module.ReceivedModule;
import com.example.admin.myapplication.fragments.Received;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ReceivedModule.class,NetworkModule.class,FilterModule.class})
@Singleton
public interface ReceivedComponent {
    void injectMethod(Received received);
}
