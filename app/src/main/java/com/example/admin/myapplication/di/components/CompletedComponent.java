package com.example.admin.myapplication.di.components;

import com.example.admin.myapplication.di.module.CompletedModule;
import com.example.admin.myapplication.di.module.FilterModule;
import com.example.admin.myapplication.di.module.NetworkModule;
import com.example.admin.myapplication.fragments.Completed;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {CompletedModule.class,NetworkModule.class,FilterModule.class})
@Singleton
public interface CompletedComponent {
    void inject(Completed completed);
}
