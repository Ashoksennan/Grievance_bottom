package com.example.admin.myapplication.di.components;

import com.example.admin.myapplication.MainActivity;
import com.example.admin.myapplication.di.module.ReceivedModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ReceivedModule.class})
@Singleton
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
